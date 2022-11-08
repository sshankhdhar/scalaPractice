package lectures.part3FunctionalProgramming

object HOFsCurries extends App {

  val superFunctions: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) =
    null
  // higher order functions (HOF)

  // map, flatMap, filter in MyList

  // function that applies a function n times over a value x
  // nTimes(f, n , x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x)) = f(f(f(x)))
  // nTimes(f,n,x) = f(f(....(x))) = nTimes(f, n-1, f(x))

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1

  println(nTimes(plusOne, 10, 1))

  // nTimesBetter(f,n) = x => f(f(f(...(x)))
  // increment10 = nTimesBetter(plusOne,10) = x => plusOne(plusOne(plusOne....(x)))
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val Plus10 = nTimesBetter(plusOne, 10)
  println(Plus10(1))

  // curries functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(4))

  // IN SCALA -- Function supports with multiple parameter lists

  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.2f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
  println(curriedFormatter("%4.2f")(Math.PI))

  /*
  1. Expand MyList
     - foreach methodA =>> Unit
       [1,2,3].foreach(x => println(x))
     - sort function ((A,A) => Int) => MyList
       [1,2,3].sort((x,y) => y - x) => [3,2,1]
     - zipWith (list, (A , A) => B) => MyList[B])
       [1,2,3].zipWith([4,5,6], x * y) => [4,10,18]
     - fold
       fold(start) (function) => a value
       [1,2,3].fold(0)(x + y) = 6

       Solution for above exercise is at MyListUsingFunctionTypes.Scala

   2. toCurry(f: (Int, Int) => Int) gives (Int => Int =>Int)
      fromCurry:(f: (Int => Int =>Int)) gives (Int, Int) => Int

   3. compose(f,g) => x => f(g(x))
      andThen(f,g) => x => g(f(x))
   */

  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y => f(x, y)

  def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int =
    (x, y) => f(x)(y)

  /*
    // FunctionX
  def compose(f: Int => Int, g: Int => Int): Int => Int =
    x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int): Int => Int =
    x => g(f(x))
   */

  // FunctionX
  def compose[A, B, T](f: A => B, g: T => A): T => B =
    x => f(g(x))

  def andThen[A, B, T](f: T => A, g: A => B): T => B =
    x => g(f(x))

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(17))

  val simpleAdder = fromCurry(superAdder2)
  println(simpleAdder(4, 17))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val copmoseIt = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(copmoseIt(4))
  println(ordered(4))

}
