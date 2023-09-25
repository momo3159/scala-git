package infra

import domain.{Blob, BlobObject, Commit, CommitObject, GitObject, GitObjectType, Tag, TagObject, Tree, TreeObject}
import util.sha1.SHA1

import scala.util.{Failure, Success, Try}

class GitObjectOnFile(private val gitObjectPath: String) {

  def readObject(hash: String): Either[Throwable, GitObject] = {
    for {
      path <- util.file.File.getPath(s"${gitObjectPath}/${hash.substring(0, 2)}/${hash.substring(2)}")
      sb <- util.file.File.readAllBytes(path)
      deflated <- util.zlib.zlib.inflate(sb)
      deflatedStr <- Right(new String(deflated.toArray))
      header <- readObjectHeader(deflatedStr)
      body <- readObjectBody(deflatedStr)
      gitObject <- header match {
        case (_, size) if size != body.length => Left(new RuntimeException(s"invalid object. size = ${size}, deflated size = ${body.length}"))
        case (Commit, size) => {
          Right(CommitObject(SHA1.get(sb), size, body))
        }
        case (Tree, size) => Right(TreeObject(SHA1.get(sb), size, body))
        case (Blob, size) => Right(BlobObject(SHA1.get(sb), size, body))
        case (Tag, size) => Right(TagObject(SHA1.get(sb), size, body))
        case _ => Left(new RuntimeException(s"invalid object: "))
      }
    } yield gitObject
  }

  private def readObjectHeader(str: String): Either[Throwable, (GitObjectType, Int)] = {
    str.split("\u0000").toSeq match {
      case Seq(header, _) => {
        header.split(" ").toSeq match {
          case Seq(kindStr, sizeStr) => {
            (GitObjectType.fromString(kindStr), Try(sizeStr.toInt)) match {
              case (Success(kind), Success(size)) => Right((kind, size))
              case (Success(_), Failure(e)) => Left(e)
              case (Failure(e), Success(_)) => Left(e)
              case (Failure(e1), Failure(_)) => Left(e1)
            }
          }
          case _ => Left(new RuntimeException("invalid object."))
        }
      }
      case _ => Left(new RuntimeException("invalid object."))
    }
  }

  private def readObjectBody(str: String): Either[Throwable, Seq[Byte]] = {
    str.split("\u0000").toSeq match {
      case Seq(_, body) => Right(body.getBytes.toSeq)
      case _ => Left(new RuntimeException("invalid object."))
    }
  }
}
