package exercise

abstract class MyListUsingFunctionTypes[+A] {

  /*
  head = first element of the list
  tail = remainder of the list
  isEmpty = is this list empty
  add(int) => new list with the new element added
  toString => string representation of the list
   */

  def head: A
  def tail: MyListUsingFunctionTypes[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyListUsingFunctionTypes[B]
  def printElements: String
  // Polymorphic call
  override def toString: String = "[" + printElements + "]"

  // below syntax is called Higher Order function which is a critical concept in functional programming
  def map[B] (transformer: A => B): MyListUsingFunctionTypes[B]
  def flatMap[B](transformer: A => MyListUsingFunctionTypes[B]): MyListUsingFunctionTypes[B]
  def filter(predicate: A => Boolean): MyListUsingFunctionTypes[A]

  // concatenation
  def ++[B >:A](list: MyListUsingFunctionTypes[B]): MyListUsingFunctionTypes[B]

  // HOFs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A,A) => Int): MyListUsingFunctionTypes[A]
  def zipWith[B,C](list: MyListUsingFunctionTypes[B], zip:(A,B) => C): MyListUsingFunctionTypes[C]
  def fold[B](start:B)(operator: (B, A) => B) : B

}

case object EmptyUsingFunctionTypes extends MyListUsingFunctionTypes[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListUsingFunctionTypes[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListUsingFunctionTypes[B] = new ConsUsingFunctionTypes(element, EmptyUsingFunctionTypes)

  override def printElements: String = ""

  def map[B] (transformer: Nothing => B): MyListUsingFunctionTypes[B] = EmptyUsingFunctionTypes
  def flatMap[B](transformer: Nothing => MyListUsingFunctionTypes[B]): MyListUsingFunctionTypes[B] = EmptyUsingFunctionTypes
  def filter(predicate: Nothing => Boolean ): MyListUsingFunctionTypes[Nothing] = EmptyUsingFunctionTypes

  def ++[B >: Nothing](list: MyListUsingFunctionTypes[B]): MyListUsingFunctionTypes[B] = list

  // HOFs
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing,Nothing) => Int): MyListUsingFunctionTypes[Nothing] = EmptyUsingFunctionTypes
  def zipWith[B,C](list: MyListUsingFunctionTypes[B], zip:(Nothing ,B) => C): MyListUsingFunctionTypes[C] =
    if (!list.isEmpty) throw new RuntimeException("List Do not have the same length")
    else EmptyUsingFunctionTypes

  def fold[B](start:B)(operator: (B, Nothing) => B) : B = start

}

case class ConsUsingFunctionTypes[+A](h: A, t: MyListUsingFunctionTypes[A]) extends MyListUsingFunctionTypes[A] {
  def head: A = h
  def tail: MyListUsingFunctionTypes[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyListUsingFunctionTypes[B] = new ConsUsingFunctionTypes(element, this)

  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  /*
  [1,2,3].map[n*2]
   = new ConsUsingFunctionTypes(2,[2,3].map(n * 2]))
   =new ConsUsingFunctionTypes(2, new ConsUsingFunctionTypes(4, [3].map(n* 2)))
   =new ConsUsingFunctionTypes(2, new ConsUsingFunctionTypes(4, new ConsUsingFunctionTypes(6, EmptyUsingFunctionTypes.map(n*2))))
   =new ConsUsingFunctionTypes(2, new ConsUsingFunctionTypes(4, new ConsUsingFunctionTypes(6, EmptyUsingFunctionTypes))))
   */
  def map[B] (transformer: A => B): MyListUsingFunctionTypes[B] =
    new ConsUsingFunctionTypes(transformer(h), t.map(transformer))

  /*
  [1,2] ++ [3,4,5]
  = new ConsUsingFunctionTypes(1, [2] ++ [3,4,5])
  = new ConsUsingFunctionTypes(1, new ConsUsingFunctionTypes(2, EmptyUsingFunctionTypes ++ [3,4,5]))
  = new ConsUsingFunctionTypes(1, new ConsUsingFunctionTypes(2, new ConsUsingFunctionTypes(3, new ConsUsingFunctionTypes(4, new ConsUsingFunctionTypes(5)))))
   */
  def ++[B >:A](list: MyListUsingFunctionTypes[B]): MyListUsingFunctionTypes[B] = new ConsUsingFunctionTypes(h, t ++ list)

  /*
  [1,2].flatMap(n => [n, n+1])
  = [1,2] ++ [2].flatMap(n , n+1])
  = [1,2] ++ [2,3] ++ EmptyUsingFunctionTypes.flatMap(n , n+1])
  = [1,2] ++ [2,3] ++ EmptyUsingFunctionTypes
  = [1,2,2,3]
   */
  def flatMap[B](transformer: A => MyListUsingFunctionTypes[B]): MyListUsingFunctionTypes[B] =
    transformer(h) ++ t.flatMap(transformer)

  /*
  [1,2,3].filter(n%2 == 0) =
    [2,3].filter(n%2 == 0) =
    = new ConsUsingFunctionTypes(2, [3].filter(n % 2 == 0))
    = new ConsUsingFunctionTypes(2, EmptyUsingFunctionTypes.filter(n % 2 == 0))
    = new ConsUsingFunctionTypes(2, EmptyUsingFunctionTypes)
 */
  def filter(predicate: A => Boolean): MyListUsingFunctionTypes[A] =
    if (predicate(h)) new ConsUsingFunctionTypes(h, t.filter(predicate))
    else t.filter(predicate)

  //HOFs
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A ,A) => Int): MyListUsingFunctionTypes[A] = {
    def insert(x:A, sortedList: MyListUsingFunctionTypes[A]): MyListUsingFunctionTypes[A] = {
      if (sortedList.isEmpty) new ConsUsingFunctionTypes(x, EmptyUsingFunctionTypes)
      else if (compare(x, sortedList.head) <= 0) new ConsUsingFunctionTypes(x, sortedList)
      else new ConsUsingFunctionTypes(sortedList.head, insert(x, sortedList.tail))
    }
    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B,C](list: MyListUsingFunctionTypes[B], zip:(A,B) => C): MyListUsingFunctionTypes[C] =
    if (list.isEmpty) throw new RuntimeException("List Do not have the same length")
    else new ConsUsingFunctionTypes(zip(h, list.head), t.zipWith(list.tail, zip))


  /*
  [1,2,3].fold(0)(+) =
  = [2,3].fold(1)(+)=
  = [3].fold(3)(+)=
  = [].fold(6)(+)
  = 6
   */
  def fold[B](start:B)(operator: (B, A) => B) : B = {
    val newStart = operator(start, h)
    t.fold(newStart) (operator)

    /*
    or you may also write like
    t.fold(operator(start, h)) (operator)
     */
  }



}

//trait MyPredicateUsingFunctionTypes[-T] { // It is a function Type T => Boolean
  //def test(elem: T): Boolean
//}

//trait MyTransformerUsingFunctionTypes[-A, B]  { // It is a function Type A => B
  //def transform(elem: A): B
//}

object ListTestUsingFunctionTypes extends App {

  val ListOfIntegers: MyListUsingFunctionTypes[Int] = new ConsUsingFunctionTypes(1,new ConsUsingFunctionTypes(2,new ConsUsingFunctionTypes(3, EmptyUsingFunctionTypes)))
  val cloneListOfIntegers: MyListUsingFunctionTypes[Int] = new ConsUsingFunctionTypes(1,new ConsUsingFunctionTypes(2,new ConsUsingFunctionTypes(3, EmptyUsingFunctionTypes)))
  val anotherListOfIntegers: MyListUsingFunctionTypes[Int] = new ConsUsingFunctionTypes(4,new ConsUsingFunctionTypes(5, EmptyUsingFunctionTypes))
  val ListOfString: MyListUsingFunctionTypes[String] = new ConsUsingFunctionTypes("Shalabh",new ConsUsingFunctionTypes("Amit",new ConsUsingFunctionTypes("Manoj", EmptyUsingFunctionTypes)))

  println(ListOfIntegers.toString)
  println(ListOfString.toString)

  println(ListOfIntegers.map(elem => elem * 2).toString)

/*
// This is equivalent to  above syntax ^^^
  println(ListOfIntegers.map(new Function1[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  }).toString)
 */

  println(ListOfIntegers.filter(elem => elem % 2 == 0).toString)
  /*
  this is equivalent to above syntax ^^^
  println(ListOfIntegers.filter(new Function1[Int, Boolean] {
    override def apply(elem: Int): Boolean = elem % 2 == 0
  }).toString)

   */

  println((ListOfIntegers ++ anotherListOfIntegers).toString)


  println(ListOfIntegers.flatMap((elem: Int) =>
    new ConsUsingFunctionTypes(elem, new ConsUsingFunctionTypes(elem + 1, EmptyUsingFunctionTypes))).toString)
  /*
    this is equivalent to above syntax ^^^
    println(ListOfIntegers.flatMap(new Function1[Int, MyListUsingFunctionTypes[Int]] {
    override def apply(elem: Int): MyListUsingFunctionTypes[Int] = new ConsUsingFunctionTypes(elem, new ConsUsingFunctionTypes(elem + 1, EmptyUsingFunctionTypes))
  }).toString)
   */

  println(ListOfIntegers == cloneListOfIntegers) //  this will return "True" because
  // "EmptyUsingFunctionTypes" Object and "Case" class have been converted to case Object and case class

  ListOfIntegers.foreach(println)
  println(ListOfIntegers.sort((x,y) => y - x))

  println(ListOfIntegers.zipWith[String, String](ListOfString, _ + "-" + _))

  println(ListOfIntegers.fold(0)(_ + _))

  // for comprehensions
  val combinations = for {
    n <- ListOfIntegers
    string <- ListOfString
  } yield n + " " + string
  println(combinations)


}