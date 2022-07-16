package dev.insideyou
package fp

import scala.util.chaining.*

import fplibrary.*

@main def Mutability(args: String*): Unit =
  println("─" * 50)

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
  // e.pipe(_.withdraw(amount = 1))

  val te = (e, e)
  println(te)

  println("─" * 50)

  val f = IO.delay { println("producing 1"); MutableBankAccount(1) }
  // f.pipe(_.withdraw(amount = 1))

  val tf = (
    IO.delay { println("producing 1"); MutableBankAccount(1) },
    IO.delay { println("producing 1"); MutableBankAccount(1) },
  )

  println(tf)

  println("─" * 50)

  // ---------------------------------

  e.unsafeRun()
  println("─" * 50)

  te._1.unsafeRun()
  println("─" * 50)

  te._2.unsafeRun()
  println("─" * 50)

  f.unsafeRun()
  println("─" * 50)

  tf._1.unsafeRun()
  println("─" * 50)

  tf._2.unsafeRun()
  println("─" * 50)
