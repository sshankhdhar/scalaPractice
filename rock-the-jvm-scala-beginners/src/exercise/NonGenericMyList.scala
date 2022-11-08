package exercise

abstract class NonGenericMyList {

  /*
  head = first element of the list
  tail = remainder of the list
  isEmpty = is this list empty
  add(int) => new list with the new element added
  toString => string representation of the list
   */

  def head: Int
  def tail: NonGenericMyList
  def isEmpty: Boolean
  def add(element: Int): NonGenericMyList
  def printElements: String
  // Polymorphic call
  override def toString: String = "[" + printElements + "]"

}

object NonGenericEmpty extends NonGenericMyList {
  def head: Int = throw new NoSuchElementException
  def tail: NonGenericMyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): NonGenericMyList = new NonGenericCons(element, NonGenericEmpty)

  override def printElements: String = ""
}

class NonGenericCons(h: Int, t: NonGenericMyList) extends NonGenericMyList {
  def head: Int = h
  def tail: NonGenericMyList = t
  def isEmpty: Boolean = false
  def add(element: Int): NonGenericMyList = new NonGenericCons(element, this)

  override def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}

object NonGenericListTest extends App {
  val list = new NonGenericCons(1,NonGenericEmpty)
  val list2 = new NonGenericCons(1,new NonGenericCons(2,new NonGenericCons(3, NonGenericEmpty)))
  println(list.head)
  println(list2.tail.head)
  println(list2.add(4).printElements)
  println(list2.isEmpty)

  println(list2.toString)

}