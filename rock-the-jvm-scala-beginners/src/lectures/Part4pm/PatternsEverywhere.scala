package lectures.Part4pm

object PatternsEverywhere extends App {

  // big Idea #1
  try {
    // code
    throw new NoSuchElementException(
      s"HTTP 404 returned from Area Of Expertise Service"
    )
  } catch {
    case e: RuntimeException       => "Do something"
    case npe: NullPointerException => "npe"
    case errorNSE: NoSuchElementException =>
      println("AOE Service has an exception NoSuchElementException")
    case errorRE: RuntimeException =>
      println("AOE Service has an exception RuntimeException")
  }

  try {
    throw new NoSuchElementException(
      s"HTTP 404 returned from Area Of Expertise Service"
    )
  } catch {
    //catching all Throwable exceptions
    case e: Throwable => println("An exception occurred" + e.getMessage)
  }

  // "catches" are actually MATCHES
  /*
   try{
     // code
   } catch (e) {
         case e : RuntimeException => "Do something"
         case npe : NullPointerException => "npe"
         case _ => "something else"
   }
   */

  // big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?!
  } yield 10 * x

  // generators are also based on PATTERN MATCHING
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // case Classes, :: operators, ..

  // Big IIdea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(b)

  // multiple value definition based on PATTERN MATCHING
  // ALL THE POWER

  val head :: tail = list
  print(head)
  print(tail)

  // Big Idea #4
  // "Partial function" based on pattern matching
  val mappedList = list.map {
    case v if v % 2 == 0 => v + "is even"
    case 1               => "the one"
    case _               => "something else"
  } // this is called "Partial function Literal
  // above thing is equivalent to below thing

  val mappedList2 = list.map(x => {
    x match {
      case v if v % 2 == 0 => v + "is even"
      case 1               => "the one"
      case _               => "something else"
    }
  })

  println(mappedList)

}
