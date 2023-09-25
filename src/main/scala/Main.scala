import infra.GitObjectOnFile

object Main extends App {
  val commitHash = "76945aeb87c1c2f67435dc5d233d6881f8daea7a"
  val objectReader = new GitObjectOnFile(".git/objects")

  objectReader.readObject(commitHash) match {
    case Right(v) => println(v)
    case Left(e) => println(e)
  }
}

/**2af3a3a72a
 *
 * 1. commitオブジェクトを取得する
 * 2-1. parentがあればcommitオブジェクトを引き続き取得
 * 2-2. parentがなければストップ
 * 3. logとして表示する
 */


/**
 * commit {commit_hash}
 * Author: {name} <mail_address>
 * Date: {date}
 *
 * {message}
 */