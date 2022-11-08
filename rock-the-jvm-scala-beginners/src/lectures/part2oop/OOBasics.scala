package lectures.part2oop

import java.io.Writer

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  person.greet("Shalabh")
  person.greet

  val author = new Writter("Shalabh", "Shankhdhar", 1994)
  val authorImposter = new Writter("Shalabh", "Shankhdhar", 1994)
  val novel = new Novel("Great Expectations",2006, author)

  println(novel.AuthorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(authorImposter))

  val counter = new Counter
  counter.inc.print
  counter.inc.print
  counter.inc.print
  counter.inc.inc.inc.inc(10).print
}

// Constructor
class Person(name: String, val age: Int) {
  // body
  val x = 2

  println(1+3)

  def greet(name: String): Unit = println(s"${this.name} says Hi, $name")

  //  Overloading
  def greet(): Unit = println(s"Hi, I am $name")

  //multiple constructors or overloading constructors
   def this(name:String) = this(name, 0)
  // Which means Implementation of the Auxilary or Secondary constructor has to be
  // another constructor which means they can only be used to pass default value to
  // parameters of primary constructor but default values can be assigned to primary constructor
  // itself. So secondary constructor are not much of a use.
  def this() = this("John Doe")
}
// Class parameters are not fields, members or property of Class

/*
   Exercise:
  Novel and a writer
  Writer: first name, surname, year
   - method full name

   Novel: name, year of release, author
   - authorAge
   - isWrittenBy(author)
   - copy (new year of release) = new instance of Novel

 */
class Writter(firstName: String, surname: String, val year: Int) {

  def fullName: String = firstName + " " + surname

}

class Novel(name: String, year: Int, author: Writter){
  def AuthorAge = year - author.year
  def isWrittenBy(author: Writter) = author == this.author
  def copy(newYear: Int): Novel = new Novel(name, newYear, author)
}
/*
  Counter Class
   - receives an Int value
   - method for current count
   - method to increment/decrement => new counter
   - overload inc/dec to receive an amount
 */

class Counter(val count: Int = 0){
  def inc = {
    println("incrementing")
    new Counter(count + 1)
  } // immutability : which means we cannot modify the existing instance of class
  // Instead we need to create the new instance of class using vlues of existing instance of class.

  def dec = {
    println("decrementing")
    new Counter(count - 1)
  } //

  // def inc(n: Int) = new Counter(count + n)
  // def dec(n: Int) = new Counter(count - n)

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n-1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n-1)
  }

  def print = println(count)
}

