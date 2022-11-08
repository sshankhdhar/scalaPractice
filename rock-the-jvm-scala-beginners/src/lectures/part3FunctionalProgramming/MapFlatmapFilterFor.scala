package lectures.part3FunctionalProgramming

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.tail)
  println(list.head)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + "Is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  /*
  Exercise:--
  Print all combinations between 2 Lists
   */
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("Black", "White")
  // List("a1", "a2",...."d4")

  // "Iterating"
  val combinations = numbers.flatMap(n => chars.map(c => "" + c + n)) // this is for 2 Loops
  println(combinations)

  val combinations1 = numbers.flatMap(
    n => chars.flatMap(c => colors.map(color => "" + c + n + " " + color))
  ) // 3 Loops

  println(combinations1)

  // foreach
  list.foreach(println)

  // for-comprehensions

  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
  Exercise
  1. MyList supports "for comprehensions"

  Answer: To make "for comprehensions" support for MyList, it should have below mentioned signatures
    map(f: A => B) => MyList[B]
    filter(p: A=> Boolean) => MyList[A]
    flatMap(f: A => MyList[B]) => MyList[B]

    -- And if we refer MyListUsingFunctionTypes.scala then we'll find the same
    -- method signature for above mentioned map, flatMap and filter

  2. A small collection of at most ONE element - Maybe[+T]
  -- and Implement flatMap, map, filter

  answer: refer maybe.scala for the answer
 */

}
