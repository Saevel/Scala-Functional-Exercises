package prv.zielony.scala.tutorial.functional.programming.lambdas

import scala.collection.mutable.ListBuffer

/**
 * Created by zielony on 07.02.16.
 */
trait CountingSortable { /*collection:Traversable[Int] =>*/

  val collection:Traversable[Int];

  var translateCollection:(Traversable[Int] => Traversable[Int]) = ???

  var countOccurences:(Traversable[Int] => Array[Int]) = ???

  var fillFinalArray:(Array[Int] => Traversable[Int]) = ???

  var reverseTranslate:((Traversable[Int], Int) => Traversable[Int]) =  ???

  def countingSort():Traversable[Int] = {

    if(collection.size == 0 || collection.min == collection.max) {
      return collection;
    }

    val mappedCollection = translateCollection(collection)

    val occurences = countOccurences(mappedCollection);

    val summedOccurences = sumOccurences(occurences)

    reverseTranslate(fillFinalArray(summedOccurences), collection.min)
  }

  private def sumOccurences(input:Array[Int]):Array[Int] = {
    for(i <- 1 until input.size) {
      input(i) = input(i) + input(i-1)
    }

    input
  }
}
