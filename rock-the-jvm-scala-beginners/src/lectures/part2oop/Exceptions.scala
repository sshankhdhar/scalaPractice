package lectures.part2oop

object Exceptions extends App {

  val x:String = null
  // println(x.length) // this will crash with a Null Pointer Exception (NPE)

  // 1. how to throwing exceptions

  // val aWeirdValue: String = throw new NullPointerException // this will purposely throw NPE if above print statement at line: 6 is commented

  // throwable classes extend the throwable class.
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exception
  def getInt(withExceptions: Boolean): Int =
    if(withExceptions) throw new RuntimeException("No int for you")
    else 42

  val potentialFail = try {
    // write code that might fail
    getInt(false)
  } catch {
    case e: NullPointerException => println("caught a runtime exception")
  } finally {
    // code that will get executed No matter what!
    // It is optional
    // It does not influence the return type of this expression.
    println("finally")
  }

  println(potentialFail)

  // 3. Hpw to define your own exception?

  class MyException extends Exception
  val exception = new MyException

  // throw exception

  /*
  Exercise
  1. Crash your program with an OutOfMemoryError
  2. Crash with SOError
  3. PocketCalculator
     - add(x,y)
     - subtract(x,y)
     - multiple(x,y)
     - divide(x,y)

     Throw
        - OverflowException if add(x,y) exceeds Int.MAX_VALUE
        - UnderflowException if sunstract(x,y) exceeds Int.MIN_VALUE
        - MathCalculationException for division by 0.
   */

  //// It is called OOM
  //val array = Array.ofDim(Int.MaxValue)

  //// SO
  //val infinite: Int = 1 + infinite
  //val noLimit = infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if(x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x< 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
    def subtract(x: Int, y: Int) = {
      val result = x - y
      if(x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x< 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result

    }
    def divide(x: Int, y: Int) = {
        if(y == 0) throw new MathCalculationException
        else x / y
    }

  }

  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(Int.MaxValue, 0))


}
