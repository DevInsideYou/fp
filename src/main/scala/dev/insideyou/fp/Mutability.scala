package dev.insideyou
package fp

import scala.util.chaining.*

import fplibrary.*

// @main def Mutability(args: String*): Unit =
object Mutability extends FPApp:
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

  lazy val e = IO.delay { println("producing 1"); MutableBankAccount(1) }
  e.map(_.withdraw(amount = 1))

  lazy val te = (e, e)
  // println(te)

  // println("─" * 50)

  lazy val f = IO.delay { println("producing 1"); MutableBankAccount(1) }
  f.map(_.withdraw(amount = 1))

  lazy val tf = (
    IO.delay { println("producing 1"); MutableBankAccount(1) },
    IO.delay { println("producing 1"); MutableBankAccount(1) },
  )

  // println(tf)

  // ---------------------------------

  override lazy val run =
    IO.delay {
      println("─" * 50)

      println(e.unsafeRun())
      println("─" * 50)

      println(te._1.unsafeRun())
      println("─" * 50)

      println(te._2.unsafeRun())
      println("─" * 50)

      println(f.unsafeRun())
      println("─" * 50)

      println(tf._1.unsafeRun())
      println("─" * 50)

      println(tf._2.unsafeRun())

      println("─" * 50)
    }
