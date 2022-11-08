package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2.  Sensible toString
  // println(instance) = println(instance.toString) // syntactic sugar
  println(jim.toString)
  println(jim)

  // 3. Equals and hashCode implemented Out Of The Box
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // this will return Trun when we use "case class"

  // 4. case classes have handy copy method
  val jim3 = jim.copy() //  returns same instance
  val jim4 = jim.copy(age = 45) // returns new instance with age = 45

  // 5. Case classes have companion object
  val thePerson = Person
  println(thePerson)
  val mary = Person("Mary", 23) // delegates object apply method
  println(mary)

  // 6. CCs are serializable
  // Akka

  // 7. CCs have extracted patterns = Which means CCs can be used for pattern matching

  // 8. Case object as shown below is also possible
  case object UnitedKingdom {
    def name: String = "The UK is Evil"
  }
  /*
  Case object have same property as case class except they don't have "companion object" because
  they are object by nature.
   */

/*
 Exercise:
 Expand My List = case classes and case objects

 Solution of this exercise is on MyList.scala

 */
}
