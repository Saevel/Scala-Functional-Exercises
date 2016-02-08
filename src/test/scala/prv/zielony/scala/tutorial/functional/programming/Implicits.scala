package prv.zielony.scala.tutorial.functional.programming

import prv.zielony.scala.tutorial.functional.programming.lambdas.CountingSort

/**
 * Created by zielony on 08.02.16.
 */
object Implicits {

  implicit def addCountingSort[CollectionType <: Traversable[Int]](input:CollectionType):Array[Int]
    with CountingSort[Array[Int]] = {

    val counterSortableArray = new Array[Int](input.size) with CountingSort[Array[Int]];

    var i = 0;
    for(element <- input) {
      counterSortableArray(i) = element;
      i = i+1;
    }

    counterSortableArray;
  }

  implicit val compareInts:((Int, Int) => Int) = (first:Int, second:Int) => {
    first - second
  }
}



