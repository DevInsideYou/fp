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

  val a = 1
  val ta = (a, a)
  println(ta)

  println("─" * 50)

  val b = 1
  val tb = (1, 1)
  println(tb)

  println("─" * 50)

  val c = { println("producing 1"); 1 }
  val tc = (c, c)
  println(tc)

  println("─" * 50)

  val d = { println("producing 1"); 1 }
  val td = ({ println("producing 1"); 1 }, { println("producing 1"); 1 })
  println(td)

  println("─" * 50)
