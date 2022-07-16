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

  val value =
    for
      _ <- IO.delay(println("─" * 50))

      _ <- e.map(println)
      _ <- IO.delay(println("─" * 50))

      _ <- te._1.map(println)
      _ <- IO.delay(println("─" * 50))

      _ <- te._2.map(println)
      _ <- IO.delay(println("─" * 50))

      _ <- f.map(println)
      _ <- IO.delay(println("─" * 50))

      _ <- tf._1.map(println)
      _ <- IO.delay(println("─" * 50))

      _ <- tf._2.map(println)

      _ <- IO.delay(println("─" * 50))
    yield ()

object Mutability extends FPApp:
  override lazy val run =
    Program.value
