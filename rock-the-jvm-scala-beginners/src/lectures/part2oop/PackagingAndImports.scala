package lectures.part2oop

import exercise.{Cons, Empty, MyList}
import playground.{Cenderalla => Princess, PrinceCharming}

import java.util.Date
import java.sql.{Date => SqlDate}



object PackagingAndImports extends App {

  // 1. Package members are accessible by their simple name
  val writer = new Writter("Daniel","RockTheJVM", 2018)

  // Import the package
  val linkedList: MyList[Int] = new Cons(1,Empty)

  // Packages are ordered hierarchy
  // matching folder structure.

  // 2. package Objects
  sayHello
  println(SPEED_OF_LIGHT)

  // 3. Advance feature of Import
  val prince = new PrinceCharming
  val cindie = new Princess // Alias used for Cenderalla class

  val date = new Date
  val sqlDate = new SqlDate(2018,4,5)

  // 4. default imports
  /*
    java.lang - String, Object, Exception
    scala - Int, Nothing, Function
    scalaPredef - println(), ???
   */


}
