package lectures.part3FunctionalProgramming

object AnonymousFunctions {

  val doubler = new Function1[Int, Int] {
    override def apply(x: Int) = x * 2
  }
  // this is the OO way of instantiating a Scala Function.

  // but it can also be written as Syntactic Sugar expression of SCALA called Anonymous function.
  // also called as (LAMBDA form)

  val doubler2: Int => Int = (x: Int) => x * 2
  val doubler3: Int => Int = x => x * 2

  // Multiple params in a Lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // No Params
  val justDoSomething: () => Int = () => 3

  println(justDoSomething) // Function Itself
  println(justDoSomething()) // call

  // Curly braces with lambdas

  val stringToInt = { (str: String) =>
    str.toInt
  }

  // More Syntactic Sugar
  val niceIncrementer: Int => Int = (x: Int) => x + 1
    // can ve converted into

  val niceIncrementer1: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  /*
  1. Go to MyList: replace all FunctionX calls with Lambdas
  2. Rewrite the "special" adder of file "WhatsAFunction.Scala" as an anonymous function
   */

  val superAdd : (Int) => ((Int) => Int) = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))


}