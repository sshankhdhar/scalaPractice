package lectures.Part4pm

import scala.util.Random

object PatternMatching extends App {

  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  // Its like "Switch" of other languages but much more powerful than that
  val description = x match {
    case 1 => "the one"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else" // "_" is called a wild card
  }

  println(x)
  println(description)

  // 1. It can Decompose value

  case class Person(name: String, age: Int)
  val bob = Person("Bob", 22)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi my name is $n and I can't drink in the INDIA"
    case Person(n, a) => s"Hi my name is $n and I am $a years old"
    case _ => "I don't know who I am."
  }

  println(greeting)
  /*
  1. Cases are matched in order
  2. What if no case is matched // It will give match error
  3. Type of the pattern match expression // Compilier try to give unified type of all types in all the cases
  4. PM works really well with case classes
   */

  // PM on "Se3aled" hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
  }

  // Don't Pattern match everything. For example:
  val isEven = x match {
    case n if n % 2 ==0 => true
    case _ => false
  }
  // WHY? use common sense
  val isEveCond = if (x % 2 == 0) true else false // Again WHY???
  val isEvenNormal = x % 2 ==0 // this is basic one and normal one too...
  // Don't overthink just because you know pattern matching

  /*
  Exercise
  Write simple function that takes PM
   takes an Expr => human readable form

   Sum(Number(2), Number(3)) => 2 + 3
   Sum(Number(2), Number(3) , Number(4)) => 2+3+4
   Prod(Sum(Number(2), Number(1)), Number(3)) => (2+1) * 3
   Sum(Prod(Number(2), Number(1)), Number(3)) => 2 * 1 + 3
   */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2)  => show(e1) + " + " + show(e2)
    case Prod(e1, e2) => {
      def maybeShowParentheses(exp: Expr) = exp match {
        case Prod(_ , _) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }
      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
    }
  }

  println(show( Sum(Number(2), Number(3)) ))
  println(show( Sum(Sum(Number(2), Number(3)) , Number(4)) ))
  println(show( Prod(Sum(Number(2), Number(1)) , Number(3)) ))
  print(show( Sum(Prod(Number(2), Number(1)), Number(3)) ))






}
