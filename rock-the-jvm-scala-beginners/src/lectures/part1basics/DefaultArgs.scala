package lectures.part1basics

object DefaultArgs extends App {

  def tailRecursiveFactorial(n: Int, acc: Int = 1): Int =
    if (n<= 1) acc
    else tailRecursiveFactorial(n-1, n* acc)

  val fact3 = tailRecursiveFactorial(3, 2)
  println(fact3)

  def savePicture(format: String = "jpeg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")

  savePicture(width = 800)
  savePicture(height = 600, width = 800, format = "png")

  /*
    1. Pass in every leading arguments
    2. Name the arguments
   */

}
