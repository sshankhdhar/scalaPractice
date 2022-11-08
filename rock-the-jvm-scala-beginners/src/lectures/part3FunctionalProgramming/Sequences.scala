package lectures.part3FunctionalProgramming

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1, 4, 2, 9)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  // ranges
  val aRange: Seq[Int] = 1 to 10 // or 1 Until 10
  println(aRange)
  aRange.foreach(println)

  (1 until 6).foreach(x => println("Hello"))

  // List
  val aList = List(1, 2, 3)
  val prependAndAppend = 42 +: aList :+ 32 // or 42 :: aList :: 32
  println(prependAndAppend)

  val apples5 = List.fill(5)("apple")
  println(apples5)

  println(aList.mkString("-FUCKS-"))

  // Arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[String](3)
  println(threeElements) // returns some garbage
  threeElements.foreach(println) // returns default values as "0" and "null" in case of string

  // mutation of Arrays
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // connection between Arrays & Sequences
  val numbersSeq: Seq[Int] = numbers // Implicit conversions
  println(numbers)

  // Vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random()
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt()) // operation
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tails
  // updating an element in middle takes long
  println(getWriteTime(numbersList))
  // depth of the tre is small
  // it needs to replace entire  32-element chunk
  println(getWriteTime(numbersVector))

}
