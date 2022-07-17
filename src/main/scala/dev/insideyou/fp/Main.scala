package dev.insideyou
package fp

package our_tf_version:
  import fplibrary.*

  def program[F[_]: Monad](using FPC: FPConsole[F]): F[Unit] =
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

package zio_version:
  import zio.*

  lazy val program =
    import zio.Console.*

    for
      _ <- printLine("─" * 50)

      _ <- printLine("What's your name?")
      name <- readLine
      _ <- printLine(s"Hello $name!")

      _ <- printLine("─" * 50)
    yield ()

  object Main extends ZIOAppDefault:
    override lazy val run =
      program

package ce3_version:
  import cats.effect.*

  lazy val program =
    val C = cats.effect.std.Console[IO]
    import C.*

    for
      _ <- println("─" * 50)

      _ <- println("What's your name?")
      name <- readLine
      _ <- println(s"Hello $name!")

      _ <- println("─" * 50)
    yield ()

  object Main extends IOApp.Simple:
    override lazy val run =
      program

package mix:
  import cats.*
  import cats.syntax.all.*
  import cats.effect.*

  def program[F[_]: Monad](using C: std.Console[F]): F[Unit] =
    import C.*

    for
      _ <- println("─" * 50)

      _ <- println("What's your name?")
      name <- readLine
      _ <- println(s"Hello $name!")

      _ <- println("─" * 50)
    yield ()

  object Main extends IOApp.Simple:
    override lazy val run =
      program
