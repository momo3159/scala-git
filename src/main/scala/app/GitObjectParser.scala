//package app
//
//import scala.util.{Failure, Success, Try}
//import domain.{Blob, BlobObject, Commit, CommitObject, GitObject, GitObjectBody, GitObjectHeader, GitObjectType, Tag, TagObject, Tree, TreeObject}
//
//class GitObjectParser(val input: String) {
//  def parse: Either[Throwable, GitObject] = {
//    for {
//      header <- getHeader
//      body <- getBody
//    } yield header.kind match {
//      case Commit => CommitObject(header, body)
//      case Tree => TreeObject(header, body)
//      case Blob => BlobObject(header, body)
//      case Tag => TagObject(header, body)
//    }
//  }
//
//  private def getHeader: Either[Throwable, GitObjectHeader] = {
//    // TODO: 独自のエラーを定義した方がよさそう
//    input.split("\u0000").toSeq match {
//      case Seq(header, _) => {
//        header.split(" ").toSeq match {
//          case Seq(kind, size) => {
//            (GitObjectType.fromString(kind), Try(size.toInt)) match {
//              case (Success(kind), Success(n)) => Right(GitObjectHeader(kind, n))
//              case (Success(_), Failure(e)) => Left(e)
//              case (Failure(e), Success(_)) => Left(e)
//              case (Failure(e1), Failure(_)) => Left(e1)
//            }
//          }
//          case _ => Left(new RuntimeException("failed to parse: invalid format"))
//        }
//      }
//      case _ => Left(new RuntimeException("failed to parse: invalid format"))
//    }
//  }
//  private def getBody: Either[Throwable, GitObjectBody] = {
//    // TODO: 独自のエラーを定義した方がよさそう
//    input.split("\u0000").toSeq match {
//      case Seq(_, body) => Right(GitObjectBody(body))
//      case _ => Left(new RuntimeException("failed to parse: invalid format"))
//    }
//  }
//}