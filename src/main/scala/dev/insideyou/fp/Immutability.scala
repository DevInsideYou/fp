package dev.insideyou
package fp

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
  // e.withdraw(amount = 1)

  val te = (e, e)
  println(te)

  println("─" * 50)

  val f = { println("producing 1"); ImmutableBankAccount(1) }
  // f.withdraw(amount = 1)

  val tf = (
    { println("producing 1"); ImmutableBankAccount(1) },
    { println("producing 1"); ImmutableBankAccount(1) },
  )

  println(tf)

  println("─" * 50)
