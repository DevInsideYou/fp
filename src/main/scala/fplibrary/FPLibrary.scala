package fplibrary

type Thunk[+A] = () => A

final case class IO[+A](unsafeRun: Thunk[A]):
  def map[B](f: A => B): IO[B] =
    IO.delay {
      val a = unsafeRun()
      val b = f(a)

      b
    }

  def flatMap[B](f: A => IO[B]): IO[B] =
    IO.delay {
      val a = unsafeRun()
      val iob = f(a)

      val b = iob.unsafeRun()

      b
    }

object IO:
  def delay[A](a: => A): IO[A] =
    IO(() => a)

abstract class FPApp extends App:
  def run: IO[Any]

  print(scala.Console.GREEN)
  run.unsafeRun()
  print(scala.Console.RESET)

object FPConsole:
  def println[A](in: => A): IO[Unit] =
    IO.delay(Console.println(in))

  def readLine: IO[String] =
    IO.delay(scala.io.StdIn.readLine())
