package util.file
import scala.util.Try
import java.nio.file.{Files, Path, Paths}

object File {

  def getPath(path: String): Either[Throwable, Path] = {
    Try(Paths.get(path)).toEither
  }

  def readAllBytes(path: Path): Either[Throwable, Seq[Byte]] = {
    Try(Files.readAllBytes(path).toSeq).toEither
  }
}
