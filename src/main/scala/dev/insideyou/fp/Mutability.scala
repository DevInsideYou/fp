package dev.insideyou
package fp

import scala.util.chaining.*

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

  val e = { println("producing 1"); MutableBankAccount(1) }
  // e.withdraw(amount = 1)

  val te = (e, e)
  println(te)

  println("─" * 50)

  val f = { println("producing 1"); MutableBankAccount(1) }
  // f.withdraw(amount = 1)

  val tf = (
    { println("producing 1"); MutableBankAccount(1) },
    { println("producing 1"); MutableBankAccount(1) },
  )

  println(tf)

  println("─" * 50)
