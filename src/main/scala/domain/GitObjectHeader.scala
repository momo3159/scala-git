package domain

case class GitObjectHeader(val kind: GitObjectType, val size: Int)
