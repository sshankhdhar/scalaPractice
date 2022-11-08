package lectures.Part4pm

import exercise.{Cons, MyList, Empty}

object AllThePatterns extends App {

  //  1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a Scala"
    case "Scala" => "The Scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 -  match anything
  // 2.1 wildcard

  val matchAnything = x match {
    case _ =>
  }
  // 2.2 variable
  val matchVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1,2)
  val matchATuple = aTuple match {
    case (1,1) =>
    case (something, 2) =>  s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_ , (2, v)) =>
  }
  //Pattern Matches can be nested.

  // 4. - Case classes - Constructor Pattern
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty =>
    case Cons(head, tail) =>
    case Cons(head, Cons(subhead, subtaiil)) =>
  }

  // 5 - List Patterns
  val aStandardList = List(1,2,3,42)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _) => // extractor - advanced
    case List(1, _*) => // List of arbitrary length - advanced
    case 1 :: List(_) => // Infix Pattern
    case List(1,2,3) :+ 42 => // aslo an Infix Pattern
  }

  // 6 = Type Specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type specifier
    case _ =>
  }

  // 7 - Name Binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_ , _) => // name binding => use the name Later(here)
    case Cons(1, rest @ Cons(2, _)) => // name binding inside nested patterns
  }

  // 8 - Multi patterns
  val multipattern = aList match {
    case Empty | Cons(0, _) => // Compound pattern (multi-pattern)
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(_ , Cons(specialElement, _ )) if specialElement % 2 == 0 =>
  }

  // ALL.

  /*
  Question,
   */

  val numbers = List(1,2,3)
  val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a List of strings"
    case listOfNumbers: List[Int] => "a List of Numbers"
    case _ => ""
  }

  println(numbersMatch) // expected "a List of Numbers" but returns "a List of strings"
  // JVM trick question

}
