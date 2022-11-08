package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App{
  def factorial(n: Int): Int =
    if (n <= 0) 1
    else {
      println("Computing factorial of "+ n + " -  I first need factorial of"+ (n-1))
      val result = n * factorial(n - 1)
      println("Computed factorial of "+ n )

      result
    }

  // println(factorial(5000)) // 120

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x-1, x * accumulator) // Tail Recursion = to use recursive call as the last expression.

    factHelper(n, 1)
  }
/*
 How it works is ...

anotherFactorial(10) = factHelper(10, 1)
= factHelper(9, 10  * 1)
= factHelper(8, 9 * 10  * 1)
= factHelper(7, 8 * 9  * 10  * 1)
=...
= factHelper(2, 3 * 4 * .. * 10 * 1)
= factHelper(1, 1 * 2 * 3 *..* 10 * 1)
 */
   println(anotherFactorial(5000))


  /*
  When we need loop use_Tail_Recursion
   */

  /*
  Exercise
  1. Concatenate a string n times
  2. IsPrime function using Tail recursion
  3. Fibonacci Series using Tail recursion
   */

  def concatenateString(word: String, n: Int): String = {
    @tailrec
    def concatenateHelper(x: Int, accumulator: String): String =
      if (x <= 1) accumulator
      else concatenateHelper(x-1, word + " " + accumulator) // Tail Recursion = to use recursive call as the last expression.

    concatenateHelper(n, word)
  }

  println(concatenateString("Shalabh",4))

  @tailrec
  def concatenateTailrec(aString: String, n: Int, accumulator: String): String =
    if(n <=0) accumulator
    else concatenateTailrec(aString, n-1, aString + accumulator)

  println(concatenateTailrec("hello ",4, ""))


  def isPrimeUsingTailrec(n: Int): Boolean ={
    @tailrec
    def isPrimeTailrec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if (t <=1) true
      else isPrimeTailrec(t-1, n%t != 0 && isStillPrime)
    isPrimeTailrec(n/2, true)
  }
  println(isPrimeUsingTailrec(2003))
  println(isPrimeUsingTailrec(629))

  // for fibonacci: tail recursion will take 2 accumulator as it take F(n-1) & F(n-2) in the same code path
  // So however many recursive call you have on the same code path that's how many accumulator you need to have in
  // the tail recursive function you want to implement.
  def fibonacciUsingTailRec (n : Int): Int = {
    def fiboTailrec(i: Int, last: Int, nextToLast: Int): Int=
      if (i >= n) last
      else fiboTailrec(i+1, last + nextToLast, last)

    if (n <=2) 1
    else fiboTailrec(2,1,1)
  }

  println(fibonacciUsingTailRec(8))
}
