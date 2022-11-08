package lectures.part2oop

object Objects {

  // SCALA DOES NOT HAVE CONCEPT OF CLASS LEVEL FUNCTIONALITY ("STATIC")

  object Person { // type + its only instance
    // "Static"/"Class" - Level functionality
    val N_EYES = 2
    def canFly : Boolean = false

    // factory method
    //def from(mother: Person, father: Person): Person = new Person("Bobbie")  or
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // Instance level functionality
  }

  def main(args: Array[String]): Unit = {

  //  COMPANIONS
  println(Person.N_EYES)
  println(Person.canFly)

  // IN SCALA WE USE OBJECTS AS SINGLETON OBJECTS
  // Scala Objects = Singleton Instance

  val mary = new Person("Mary")
  val john = new Person("John")
  println(john == mary)

  val person1 = Person
  val person2 = Person
  println(person1 == person2)

  // val bobbie = Person.from(mary, john) or
  val bobbie = Person.apply(mary, john)

  }
  // Scala Application  = Scala object with
  // def main(args: Array[String]): Unit

}

