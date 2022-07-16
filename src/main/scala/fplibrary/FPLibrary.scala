package fplibrary

type Thunk[+A] = () => A

final case class IO[+A](unsafeRun: Thunk[A])

object IO:
  def delay[A](a: => A): IO[A] =
    IO(() => a)
