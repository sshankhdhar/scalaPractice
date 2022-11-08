package lectures.part3FunctionalProgramming

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // Work with unsafe APIs
  def unsafeMethod(): String = null
  // val result = Some(unsafeMethod()) // WRONG

  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained methods
  def backUpMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod())orElse(Option(backUpMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DO not use this

  // map, flatMap, filter

  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10)) // returns none as "4" does not match the predicate

  println(myFirstOption.flatMap(x => Option(x* 10)))

  // for - comprehension

  /*
  Exercise

   */
 val config: Map[String, String] = Map(
   // fetched from elsewhere
   "host" -> "176.45.35.1",
   "port" -> "80"
 )

  class Connection{
    def connect = "Connected" // connect to some server
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host: String, port: String): Option[Connection] =
      if(random.nextBoolean()) Some(new Connection)
      else None
  }
  // try to establish connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")
/*
if (h != null)
  if(p ! null)
    return Connection.apply(h,p)
 */
  val connection = host.flatMap(h => port.flatMap(p => Connection.apply(h,p)))
  /*
if (c != null)
    return c.connect
 */
  val connectionStatus = connection.map(c => c.connect)
  /*
if (connectionStatus == null) print(None) else print(Some(connectionStatus.get)
 */
  println(connectionStatus)
  /*
if (status != null) print(Status) else "don't do anything"
*/
  connectionStatus.foreach(println)

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
    .flatMap(port => Connection(host, port))
    .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for{
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)

  // SO bottom line is IF you deign methods that might return "NULLS" then use Options instead.

}
