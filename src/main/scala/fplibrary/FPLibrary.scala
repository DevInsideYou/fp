package fplibrary

type Thunk[+A] = () => A

final case class IO[+A](unsafeRun: Thunk[A])

object IO:
  def delay[A](a: => A): IO[A] =
    IO(() => a)

  given Monad[IO] with
    extension [A](io: IO[A])
      def map[B](f: A => B): IO[B] =
        IO.delay {
          val a = io.unsafeRun()
          val b = f(a)

          b
        }

      def flatMap[B](f: A => IO[B]): IO[B] =
        IO.delay {
          val a = io.unsafeRun()
          val iob = f(a)

          val b = iob.unsafeRun()

          b
        }

abstract class FPApp extends App:
  def run: IO[Any]

  run.unsafeRun()

object FPConsole:
  def println[A](in: => A): IO[Unit] =
    IO.delay(Console.println(in))

  def readLine: IO[String] =
    IO.delay(scala.io.StdIn.readLine())

  given FPConsole[IO] with
    export FPConsole.*

trait Functor[F[_]]:
  extension [A](fa: F[A]) def map[B](f: A => B): F[B]

trait Monad[F[_]] extends Functor[F]:
  extension [A](fa: F[A]) def flatMap[B](f: A => F[B]): F[B]

trait FPConsole[F[_]]:
  def println[A](in: => A): F[Unit]
  def readLine: F[String]
