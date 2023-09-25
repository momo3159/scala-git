package util.zlib

import java.io.ByteArrayOutputStream
import java.util.zip.{DeflaterOutputStream, Inflater, InflaterOutputStream}
import scala.util.Using

object zlib {
  def inflate(compressed: Seq[Byte]): Either[Throwable, Seq[Byte]] = {
    val bo = new ByteArrayOutputStream()

    Using(new InflaterOutputStream(bo)) { ios =>
      ios.write(compressed.toArray)
      bo.toByteArray.toSeq
    }.toEither
  }
}
