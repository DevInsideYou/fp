package dev.insideyou
package fp

extension [A](self: A)
  def pipe[B](f: A => B): B =
    f(self)

  def tap[B](f: A => B): A =
    f(self)

    self

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

  val bankAccount =
    MutableBankAccount(initialBalance = 0)
      .tap(_.deposit(amount = 20))
      .tap(_.withdraw(amount = 5))

  println(bankAccount.balance)

  println("─" * 50)
