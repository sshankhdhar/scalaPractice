package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
    A = Cat
    B = Animal
     */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // Variance problem

  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //1. Yes List[Cat] extends List[Animal]

  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  //animal.add[new Dog] ??? Hard Question => return a List[Animal]

  // 2. No = Invariance
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! Contravariance
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  // bounded Types
  // a. Upper Bounded
  class Cage[A <: Animal](animal: A)

  val cage = new Cage(new Dog)

  class Car
  // Generic type needs proper bounded type
  // val newCage = new Cage(new Car)

  // b. Lower Bounded
  // For this check line no. 7


  // expand MyList to be Generic

}


