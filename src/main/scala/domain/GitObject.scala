package domain


sealed trait GitObject {
  val hash: String
  val size: Int
  val data: Seq[Byte]
}

// SHA1 は 20バイト

case class CommitObject(val hash: String, val size: Int, val data: Seq[Byte]) extends GitObject
case class TreeObject(val hash: String, val size: Int, val data: Seq[Byte]) extends GitObject

case class BlobObject(val hash: String, val size: Int, val data: Seq[Byte]) extends GitObject
case class TagObject(val hash: String, val size: Int, val data: Seq[Byte]) extends GitObject










//case class CommitObject(val header: GitObjectHeader, val body: GitObjectBody) extends GitObject {
//  override def parseBody: Either[Throwable, CommitObjectBody] = {
//    val split = body.str.split("\n\n").toSeq
//    if (split.length < 2) Left(new RuntimeException(s"${body.str}"))
//
//    val metadata = split(0).split("\n").toSeq.flatMap(a => a.split(" ").toSeq)
//    val message = split.slice(1, split.length).mkString("\n\n")
//
//    metadata match {
//      case Seq("tree", treeHash, "parent", parentHash, "author", author, mailAddress, timestamp, tz, "committer", committer, mailAddress1, timestamp1, tz1) => {
//        Right(CommitObjectBody(
//          treeHash = treeHash,
//          parentHash = Some(parentHash),
//          author = author,
//          authorMail = Option(mailAddress),
//          timestamp1 = timestamp,
//          tz1 = tz,
//          committer = committer,
//          committerMail = Option(mailAddress1),
//          timestamp2 = timestamp1,
//          tz2 = tz1,
//          message = message
//        ))
//      }
//      case Seq("tree", treeHash, "author", author, mailAddress, timestamp, tz, "committer", committer, mailAddress1, timestamp1, tz1) => {
//        Right(CommitObjectBody(
//          treeHash = treeHash,
//          parentHash = None,
//          author = author,
//          authorMail = Option(mailAddress),
//          timestamp1 = timestamp,
//          tz1 = tz,
//          committer = committer,
//          committerMail = Option(mailAddress1),
//          timestamp2 = timestamp1,
//          tz2 = tz1,
//          message = message
//        ))
//      }
//      case _ => Left(new RuntimeException(s"${body.str}"))
//    }
//  }
//}
//case class TreeObject(val header: GitObjectHeader, val body: GitObjectBody) extends GitObject {
//  override def parseBody: Either[Throwable, CommitObjectBody] = ???
//}
//case class BlobObject(val header: GitObjectHeader, val body: GitObjectBody) extends GitObject {
//  override def parseBody: Either[Throwable, CommitObjectBody] = ???
//}
//case class TagObject(val header: GitObjectHeader, val body: GitObjectBody) extends GitObject {
//  override def parseBody: Either[Throwable, CommitObjectBody] = ???
//}