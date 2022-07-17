package dev.insideyou
package fp

import scala.util.chaining.*

import fplibrary.*

object MutableProgram:
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

  val value =
    import FPConsole.*

    for
      _ <- println("─" * 50)
      b <- IO.delay(MutableBankAccount(initialBalance = 0))
      _ <- IO.delay(b.deposit(amount = 20))
      _ <- IO.delay(b.withdraw(amount = 5))
      _ <- println(b)
      _ <- println("─" * 50)
    yield ()

object Mutability extends FPApp:
  override lazy val run =
    MutableProgram.value
