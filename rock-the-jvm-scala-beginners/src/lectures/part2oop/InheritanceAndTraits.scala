package lectures.part2oop

object InheritanceAndTraits extends App {

  // single class inheritance
  sealed class Animal {

    // Scala has 3 modifiers :  Private Protected and Public
    // protected def eat = println("nomnomnom")
     def eat = println("nomnomnom")
     final def gender: Unit = println("Male")
    val creatureType = "Wild"
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("Crunch Crunch")
    }
  }

  val cat = new Cat
  // cat.crunch

  // Constructors
  class Person(name: String, age: Int) {
  def this(name: String) = this (name , 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  class Dog(override val creatureType: String) extends Animal {
    override def eat ={
      // super.eat
      println("crunch crunch")

    }

    // override val creatureType = "K9"
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broadsense: Polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overriding vs overloading

  // SUPER

  // Preventing Overrides
  // By using keyword final
  // 1. Using final on member
  // 2. Using final on entire class
  // 3. seal the class -- Using sealed is soft prevention of overriding as the class can be
  //  extended in this file but prevents extension in other file

}
