package lectures.part1basics

object Functions extends App {
  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("Hello", 3))

  def aParameterLessFunction(): Int = 42

  println(aParameterLessFunction())
  println(aParameterLessFunction)

  // When you nee Loops use recursion but return type is mandatory in recursive method
  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("Hello", 3))

  // Function without return type
  private def aFunctionWithoutReturTtype(a: String, b: Int) = {
    a + " " + b
  }

  println(aFunctionWithoutReturTtype("Hello", 3))

  // Void kind off function
  def aFunctionWithSideEffect(aString: String): Unit = println(aString)

  aFunctionWithSideEffect(aString = "Shalabh")

  // Auxilary Functions
  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  /*
    1. A greeting function (name, age) => "Hi my name is $name and I am $g years old"
    2. Factorial function 1* 2* 3 * .. * n
    3. A Fabonacci Function
        f(1) = 1
        f(2) = 1
        f(n) = F(n-1) + f(n+2)
    4. Test if a Number is prime.
   */

  def greetingForKids(name: String, age: Int): String = {
    "Hi my name is " + name + " and I am " + age + " years old"
  }

  println(greetingForKids("Shalabh", 26))

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n - 1)

  println(factorial(5)) // 120

  def fabonacci(n: Int): Int =
    if (n <= 2) 1
    else fabonacci(n - 1) + fabonacci(n - 2)

  // 1 1 2 3 5 8 13 21
  println(fabonacci(8))

  def isPrime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))

}
