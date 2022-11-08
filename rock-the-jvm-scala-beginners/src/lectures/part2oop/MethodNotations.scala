package lectures.part2oop
import scala.language.postfixOps

object MethodNotations extends App {

  class Person(val name: String, favouriteMovie: String, val age: Int = 0) {
    def likes(movie: String) = movie == favouriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(nickname: String): Person = new Person(s"$name ${nickname}", favouriteMovie)

    def unary_! = s"$name, what the heck"

    def unary_+ : Person = new Person(name, favouriteMovie, age + 1)

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favouriteMovie"
    def apply(n: Int): String = s"$name watched $favouriteMovie $n times"

    def learns(thing: String) = s"$name is learning $thing"
    def learnsScala = this learns "Scala"

  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Incption") // this code is equivalent to above statement
  // this is called Infix notation or operator. but works only with method having single parameter
  // above thing also known as (Syntactic sugar)

 // "Operators" in Scala can also be used as method
  val tom = new Person("tom", "Fight Club")

  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // All Operators are Methods
  // Akka actors have ! ?

  // Prefix  notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !
  println(x)
  println(!mary)
  println(mary.unary_!)

  // Postfix notation
  println(mary.isAlive) // this is equivalent to
  println(mary isAlive)

  //Special method in Scala "apply"
  println(mary.apply()) // is equivalent to
  println(mary())
  // whenever object is called as method (as shown in line 47), compiler look for apply method in the class.


  // Exercises
  /*
  1. Overload the + operator
      mary + "the rockstar" => new person "Mary (the rockstar)"

  2. Add as age to the Person class
     Add a unary + operator => new person with the age + 1
     +mary => mary with the age incrementer

  3. Add a "learns" method in the Person class => "Mary learns Scala"
     Add a learnScala method, calls learns method with "Scala"
     Use it in Postfix notation

  4. Overload the apply method
     mary.apply(2) => "Mary watched Inception 2 times"
   */
  println((mary + "the rockstar")()) // 1
  println((mary + "the rockstar").apply()) // 2


  println((+mary).age) // 3
  println(mary learnsScala)

  println(mary(10)) // 4




}

