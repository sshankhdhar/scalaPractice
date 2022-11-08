package lectures.part2oop

object AnonymousClasses extends App{

  abstract class Animal {
   def eat: Unit
  }

  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahaha")
  }
  /*
  Equivalent AnonymousClasses$$anon$1 extends = new Animal {
    override def eat: Unit = println("hahahaha")
  }
  */
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi = println(s"Hi, My name is $name")
  }

  val jim = new Person("Jim") {
    override def sayHi = println(s"Hi my name is Jim")
  }

  println(jim.sayHi)

  /*
  1. generic trait MyPredicate[-T]
  2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
  3. MyList:
     - map(transformer) => MyList
     - filter(predicate) => MyList
     - flatMap(transformer rom A to MyList[B] => MyList[B]

     class EvenPredicate extends MyPredicate[Int]
     class StringToIntTransformer extends MyTransformer[String, Int]

     [1,2,3].map(n * 2) = [2.4.6]
     [1,2,3,4].filter(n % 2) = [2,4]
     [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]


For solution refer MyList.Scala
   */



}
