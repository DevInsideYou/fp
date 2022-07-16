package dev.insideyou
package fp

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

  val firstBankAccount =
    MutableBankAccount(initialBalance = 0)

  println(firstBankAccount.balance)

  val _ =
    firstBankAccount.deposit(amount = 20)

  println(firstBankAccount.balance)

  val _ =
    firstBankAccount.withdraw(amount = 5)

  println(firstBankAccount.balance)

  println("─" * 50)
