package practice

object ConsoleApp extends App {

  // Write a program to avoid duplicate elements in a list and sort the elements in desc order. Append '-a' with each sorted value.
  // input - List("1","10","34","56","7","2","56","10")
  // Expected output - List(56-a,34-a,10-a,7-a,2-a,1-a)

  def RemmoveDuplicate(inputList: List[String]): Seq[String] = {
    println(inputList.map(_.toInt).flatMap(List(_)))
    println(inputList.map(_.toInt).flatMap(x => Seq(x -> x * 2)))
    println(inputList.map(_.toInt).map(x => Map(x -> x * 2)))
    println(inputList.map(_.toInt).flatMap(x => List(x * 2)))
    inputList
      .map(_.toInt)
      .sorted
      .foldRight(Seq[String]()) {
        case (x, y) => {
          if (!y.contains(x + "-a")) {
            y ++ Seq(x + "-a")
          } else y
        }
      }

  }
  println(RemmoveDuplicate(List("1", "10", "34", "56", "7", "2", "56", "10")))

}
