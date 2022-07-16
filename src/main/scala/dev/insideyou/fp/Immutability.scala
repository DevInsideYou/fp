package dev.insideyou
package fp

@main def Immutability(args: String*): Unit =
  println("─" * 50)

  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount =
      ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount =
      ImmutableBankAccount(balance - amount)

  val firstBankAccount =
    ImmutableBankAccount(balance = 0)

  println(firstBankAccount.balance)

  val secondBankAccount =
    firstBankAccount.deposit(amount = 20)

  println(secondBankAccount.balance)

  val thirdBankAccount =
    secondBankAccount.withdraw(amount = 5)

  println(thirdBankAccount.balance)

  println("─" * 50)
