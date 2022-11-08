package exercise

object MyScalaRandomPractice extends App {

  def init[A](l: MyList[A]): MyList[A] =
    l match {
      case Empty => sys.error("init of empty list")
      case Cons(_, Empty) => Empty
      case Cons(h, t) => Cons(h, init(t))
    }


  println(init(new Cons(1, new Cons ( 2, new Cons ( 3, Empty)))))
}

