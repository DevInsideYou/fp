package dev.insideyou
package fp

import scala.util.chaining.*

@main def Immutability(args: String*): Unit =
  println("─" * 50)

  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount =
      ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount =
      ImmutableBankAccount(balance - amount)

    override def toString: String =
      s"ImmutableBankAccount($balance)"

  val e = { println("producing 1"); ImmutableBankAccount(1) }
  // e.pipe(_.withdraw(amount = 1))

  val te = (e, e)
  println(te)

  println("─" * 50)

  val f = { println("producing 1"); ImmutableBankAccount(1) }
  // e.pipe(_.withdraw(amount = 1))

  val tf = (
    { println("producing 1"); ImmutableBankAccount(1) },
    { println("producing 1"); ImmutableBankAccount(1) },
  )

  println(tf)

  println("─" * 50)
