package lectures.part3FunctionalProgramming

object WhatsAFunction extends App {

  // DREAM: use function as first class elements
  // Problem is we come from OOP background

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2)) // doubler was called like a Function here

  // Function Types = Function1[A,B] or Functioon2.....Function22 is supported by SCALA
  // SCALA support FUNCTION TYPES upto 22 parameters.
  val stringToIntConverter: ((String) => Int) = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("7") + 3)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }
  /*
  Here "((Int, Int) => Int)" is a syntactic sugar for Function2[Int, Int, Int]
   */

  // Function types Fuhnction2[A,B,R]  === (A,B) => R
  // this means "All SCALA FUNCTIONS ARE OBJECTS"

  /*
  1. A Function which takes 2 strings and concatenate them
  2. transform the MyPredicate and MyTransformer into Function Types (For solution of this please refer : MyListUsingFunctionTypes.Scala)
  3. define a function which takes an argument int and returns another function which takes
     and int and returns an int.
     - what's the type of this function
     - how to do it
   */

  def concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a+b
  }
  println(concatenator("Hello", "Scala"))


  // Function1[Int, Function1[Int, Int]]  --  final type for 3rd question

  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override  def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(y: Int): Int = x + y
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(6)) // This is called Curried Function

}

trait MyFunction[A,B] {
  def apply(element: A): B
}
