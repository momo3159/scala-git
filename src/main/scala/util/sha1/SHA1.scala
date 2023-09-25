package util.sha1

import java.security.MessageDigest

object SHA1 {
  def get(sb: Seq[Byte]) = {
    MessageDigest.getInstance("SHA1").digest(sb.toArray).map(_ & 0xFF).map(_.toHexString).mkString
  }
}
