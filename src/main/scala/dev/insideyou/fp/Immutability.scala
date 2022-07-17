package dev.insideyou
package fp

import scala.util.chaining.*

import fplibrary.*

object ImmutableProgram:
  final class ImmutableBankAccount(val balance: Int):
    def deposit(amount: Int): ImmutableBankAccount =
      ImmutableBankAccount(balance + amount)

    def withdraw(amount: Int): ImmutableBankAccount =
      ImmutableBankAccount(balance - amount)

    override def toString: String =
      s"ImmutableBankAccount($balance)"

  val value =
    import FPConsole.*

    for
      _ <- println("─" * 50)
      b1 <- IO.delay(ImmutableBankAccount(balance = 0))
      b2 <- IO.delay(b1.deposit(amount = 20))
      b3 <- IO.delay(b2.withdraw(amount = 5))
      _ <- println(b3)
      _ <- println("─" * 50)
    yield ()

object Immutability extends FPApp:
  override lazy val run =
    ImmutableProgram.value
