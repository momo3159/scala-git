package domain

case class GitObjectBody(str: String)

case class CommitObjectBody(
   val treeHash: String,
   val parentHash: Option[String],
   val author: String,
   val authorMail: Option[String],
   val timestamp1: String,
   val tz1: String,
   val committer: String,
   val committerMail: Option[String],
   val timestamp2: String,
   val tz2: String,
   val message: String
)