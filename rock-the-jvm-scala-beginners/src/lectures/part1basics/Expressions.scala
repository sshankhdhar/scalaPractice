package lectures.part1basics

object Expressions extends App {

  val x = 1+2 // Expression
  println(x)

  println(2+3*4)
  // Math operators are  + - * / & | ^
  // Bitwise Operators << >>
  // Scala specific Bitwise Operators  >>> (right shift with zero extension)

  println( 1 == x)
  // relational operators are == != > >= < <=

  println(!(1 ==x))
  // Logical Operators are ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /=  ...... side effects

  println(aVariable)

  // Instrustions (DO) Vs Expressions (VALUE)


  // IF Expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // If returns value. IF statement in Scala is an expression
  println(aConditionedValue)
  println(if(aCondition)  5 else 3)
  println(1 +3)


  // What are Side effects
  var i = 0
  val aWhile = while(i < 10){
    println(i)
    i +=1
  }

  // Never do that again
  // Everything in scala is expression. Scala forces to have everything as expression.

  val aWeirdValue = (aVariable = 3) // Type of aWeirdValue is "Unit". Unit  === void
  println(aWeirdValue)

  // side effects are prinln, whiles, reassigning
  // In SCALA even instructions like While are also expression. the returns Unit as ()



  // Code blocks
  // [In Scala even code block is expression. And its value will be the value of last expression written in the code block ]
  val aCodeBlock = {
    val y = 2
    val z = y +1

    if(z > 2)"hello" else "GoodBye"
  }

  println(aCodeBlock)


  // Questions

  // 1. differene between "Hello World" vs println("Hello World")
  // Answer: "Hello World" is a type of String. Its a string literal. But  println("Hello World") is a side effect which
  // is a type of Unit.

  // 2. Get the value of below mentioned  code blocks
  val someValue = {
    2<3
  }
  println(someValue)

  val someOtherValue = {
    if(someValue) 239 else 986
    42
  }
  println(someOtherValue)

}
