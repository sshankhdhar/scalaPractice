package lectures.part1basics

object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("By Value" + x)
    println("By Value" + x)
  }

  def calledByName(x: => Long): Unit = {
    println("By Name" + x)
    println("By Name" + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  // printFirst(infinite(), 34) // this throws stack overflow error as "infinite" function is passed
  // through value parameter

  printFirst(34, infinite()) // but this one will work and print 34 as "infinite()"
  // is passed via by name parameter and by name parameter delays the evaluation.
  // Which means it will not evaluate until the parameter is used in the function

  val result = "AAABBCDTDD".toCharArray()

  for (m1 <- result) {
    println(m1)
  }

}
