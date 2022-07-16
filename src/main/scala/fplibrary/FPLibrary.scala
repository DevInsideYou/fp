package fplibrary

type Thunk[+A] = () => A

final case class IO[+A](unsafeRun: Thunk[A]):
  def map[B](f: A => B): IO[B] =
    IO.delay {
      val a = unsafeRun()
      val b = f(a)

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
