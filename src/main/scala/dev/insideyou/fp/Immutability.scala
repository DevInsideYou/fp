package dev.insideyou
package fp

@main def Immutability(args: String*): Unit =
  println("─" * 50)

  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount =
      ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount =
      ImmutableBankAccount(balance - amount)

  val bankAccount =
    ImmutableBankAccount(balance = 0)
      .deposit(amount = 20)
      .withdraw(amount = 5)

  println(bankAccount.balance)

  println("─" * 50)
