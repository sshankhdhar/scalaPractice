package lectures.part3FunctionalProgramming
import scala.language.postfixOps
import scala.annotation.tailrec

object TuplesAndMaps extends App {

  // tupples = finite ordered "Lists"

  val aTuple = new Tuple2(2, "hello, Scala") //Syntactic sugar is -- Tuple2[Int, String] = (Int, String)
  val aTuple2 = (2, "hello gandu")

  println(aTuple._1)  // prints 2
  println(aTuple.copy(_2 = "good bye Java"))
  println(aTuple.swap)

  // Maps - Keys -> values
  val aMap: Map[String, Int] = Map()

  val phoneBook = Map(("Jim", 555),("Shalabh", 832), "Browny" -> 554 )
  // a -> b is a sugar of tuple for (a,b)
  println(phoneBook)

  // map ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
  // println(phoneBook("Mary")) // this will through error as Mary does not exist. To prevent that we can use "withDefaultValue"

  val phoneBookWithErrorhandling = Map(("Jim", 555),("Shalabh", 832), "Browny" -> 554, "JIM" -> 900 ).withDefaultValue(-1)
  println(phoneBookWithErrorhandling("Mary"))

  // add a paring
  val newPairing = "Marry" -> 748
  val newPhonebook = phoneBook + newPairing

  println(newPhonebook)

  // functionals on map
  // map, filter, flatmap

  println(phoneBook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phoneBook.filterKeys(x => x.startsWith("J")))
  // mapValues
  println(phoneBook.mapValues(number => number * 10))
  println(phoneBook.mapValues(number => "034_" + number ))

  // conversions to other collections
  println(phoneBook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("boa","james", "mary", "shalabh", "bob", "Angela","jim")
  println(names.groupBy(names => names.charAt(0)))
  /*
  1. What would happen if I had 2 original entries "Jim" -> 555 and "JIM" -> 900?

  !!! careful with mapping keys
  2. Overly simplified social network based on maps
  Person = String
  - add a person to the network
  - remove
  - friend (mutual)
  - unfriend
  -
  - number of friends
  - person with most friends
  - how many people have NO friends
  - If there is a social connection between 2 people ( direct or not )
   */

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]) : Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }


  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary") // this way of adding is Cumbersome but this is not the scope of this exercise
  println(network)
  println(friend(network, "Bob", "Mary"))

  println(unfriend(friend(network, "Bob", "Mary"),"Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"),"Bob"))

  // Jim, Bob, Mary

  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if(!network.contains(person)) 0
    else network(person).size


  println(nFriends(testNet,"Bob"))

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)_1
  }

  println(mostFriends(network))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.filter(pair => pair._2.isEmpty).size

 println(nPeopleWithNoFriends(testNet))

  def socialConnectin(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople:Set[String], discoveredPeople: Set[String] ): Boolean = {
      if (discoveredPeople.isEmpty) false
      else{
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople+ person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }

  println(socialConnectin(testNet, "Mary", "Jim"))
  println(socialConnectin(network, "Mary", "Bob"))



}
