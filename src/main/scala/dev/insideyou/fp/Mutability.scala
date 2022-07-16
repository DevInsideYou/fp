package dev.insideyou
package fp

import scala.util.chaining.*

import fplibrary.*

// @main def Mutability(args: String*): Unit =
object Program:
  // println("─" * 50)
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

  val e = IO.delay { println("producing 1"); MutableBankAccount(1) }
  e.map(_.withdraw(amount = 1))

  val te = (e, e)
  // println(te)

  // println("─" * 50)

  val f = IO.delay { println("producing 1"); MutableBankAccount(1) }
  f.map(_.withdraw(amount = 1))

  val tf = (
    IO.delay { println("producing 1"); MutableBankAccount(1) },
    IO.delay { println("producing 1"); MutableBankAccount(1) },
  )

  // println(tf)

  val oldValue =
    IO.delay {
      println("─" * 50)

      println(e.unsafeRun())
      println("─" * 50)

      println(te._1.unsafeRun())
      println("─" * 50)

      println(te._2.unsafeRun())
      println("─" * 50)

      println(f.unsafeRun())
      println("─" * 50)

      println(tf._1.unsafeRun())
      println("─" * 50)

      println(tf._2.unsafeRun())

      println("─" * 50)
    }

  val value =
    IO.delay {
      val _ = println("─" * 50)

      val _ = e.map(println)
      val _ = println("─" * 50)

      val _ = te._1.map(println)
      val _ = println("─" * 50)

      val _ = te._2.map(println)
      val _ = println("─" * 50)

      val _ = f.map(println)
      val _ = println("─" * 50)

      val _ = tf._1.map(println)
      val _ = println("─" * 50)

      val _ = tf._2.map(println)

      val _ = println("─" * 50)
    }

object Mutability extends FPApp:
  override lazy val run =
    Program.value
