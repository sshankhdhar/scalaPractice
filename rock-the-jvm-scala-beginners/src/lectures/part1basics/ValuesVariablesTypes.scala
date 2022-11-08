package lectures.part1basics

object ValuesVariablesTypes extends App {

  // 1. Values
  val x: Int = 42
  println(x)

  // x = 2

  // vals are immutable
  // Complier can infer types as well

  // semi-colons are necessary only if expressions are used in a same line.

  val aString: String = "Hello"
  val anotherString: String = "GoodBye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val anInt: Int = x
  val sShort: Short = 3453
  val aLong: Long = 454645657574567345l
  val aFloat: Float = 2.0f
  val aDouble: Double = 2.3

  // 2. Variables
  var aVariable: Int = 4

  aVariable = 5 // variables are mutable
  println(aChar.getClass)
}
