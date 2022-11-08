package lectures.part3FunctionalProgramming

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // Create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unSafeMethod(): String =
    throw new RuntimeException("No String for you BUSTER!")

  // Try objects via the apply method
  val potentialFailure = Try(unSafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess) // or you can use "IsFailure"

  // orElse
  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unSafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // If you design an API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallback
    : Try[String] = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // => for-comprehensions

  /*
  Exercise
   */
  val host = "localhost"
  val port = "0000"
  def renderHTML(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }
    def getSafe(url: String): Try[String] = Try(get(url))

  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port: String): Try[Connection] =
      Try(getConnection(host, port))
  }

  // if you get the html page from the connection, print it to the console i.e. call renderHTML
  val possibleConnection = HttpService.getSafeConnection(host, port)
  val possibleHTML =
    possibleConnection.flatMap(connection => connection.getSafe("/Home"))
  possibleHTML.foreach(println)

  // ShortHand version
  HttpService
    .getSafeConnection(host, port)
    .flatMap(connection => connection.getSafe("/Home"))
    .foreach(renderHTML)

  // for-comprehension version
  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)

  println(
    "crosswalks/areaOfExpertise/sdcfds"
      .split("/")
      .toList
      .lastOption
      .getOrElse("nofile.json")
      .split("\\.")
      .toList
      .headOption
      .getOrElse("nofile")
  )
  /*
   In an Imperative language the equivalent logic would be

   try{
      connection = HttpService.getConnection (host, port)
      try{
         page = connection.get("/home")
         renderHTML(page)
      } catch(some other exception) {

      }
   } catch(exception) {

   }
 */

}
