package dev.insideyou
package fp

import fplibrary.*

def program[F[_]](using M: Monad[F], FPC: FPConsole[F]): F[Unit] =
  import FPC.*

  for
    _ <- println("─" * 50)

    _ <- println("What's your name?")
    name <- readLine
    _ <- println(s"Hello $name!")

    _ <- println("─" * 50)
  yield ()

object Main extends FPApp:
  override lazy val run =
    program
