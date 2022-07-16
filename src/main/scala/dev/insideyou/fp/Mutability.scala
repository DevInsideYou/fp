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
    for
      _ <- hyphens

      _ <- e.flatMap(FPConsole.println)
      _ <- hyphens

      _ <- te._1.flatMap(FPConsole.println)
      _ <- hyphens

      _ <- te._2.flatMap(FPConsole.println)
      _ <- hyphens

      _ <- f.flatMap(FPConsole.println)
      _ <- hyphens

      _ <- tf._1.flatMap(FPConsole.println)
      _ <- hyphens

      _ <- tf._2.flatMap(FPConsole.println)

      _ <- IO.delay(println("─" * 50))
    yield ()

object Mutability extends FPApp:
  override lazy val run =
    Program.value
