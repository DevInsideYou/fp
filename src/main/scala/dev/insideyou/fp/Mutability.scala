package dev.insideyou
package fp

import scala.util.chaining.*

import fplibrary.*

// @main def Mutability(args: String*): Unit =
object Program:
  // println("─" * 50)
  final class MutableBankAccount(initialBalance: Int):
    private var currentBalance: Int =
      initialBalance

    def balance: Int =
      currentBalance

    def deposit(amount: Int): Unit =
      currentBalance += amount

    def withdraw(amount: Int): Unit =
      currentBalance -= amount

    override def toString: String =
      s"MutableBankAccount($balance)"

  val e = IO.delay { println("producing 1"); MutableBankAccount(1) }
  e.map(_.withdraw(amount = 1))

  val te = (e, e)
  // println(te)

  // println("─" * 50)

  val f = IO.delay { println("producing 1"); MutableBankAccount(1) }
  f.map(_.withdraw(amount = 1))

  val tf = (
    IO.delay { println("producing 1"); MutableBankAccount(1) },
    IO.delay { println("producing 1"); MutableBankAccount(1) },
  )

  // println(tf)

  val hyphens =
    FPConsole.println("─" * 50)

  val value =
    import FPConsole.*

    for
      _ <- hyphens

      b1 <- e
      _ <- println(b1)

      _ <- hyphens

      b2 <- te._1
      _ <- println(b2)

      _ <- hyphens

      b3 <- te._2
      _ <- println(b3)

      _ <- hyphens

      b4 <- f
      _ <- println(b4)

      _ <- hyphens

      b5 <- tf._1
      _ <- println(b5)

      _ <- hyphens

      b6 <- tf._2
      _ <- println(b5)

      _ <- hyphens
    yield ()

object Mutability extends FPApp:
  override lazy val run =
    Program.value
