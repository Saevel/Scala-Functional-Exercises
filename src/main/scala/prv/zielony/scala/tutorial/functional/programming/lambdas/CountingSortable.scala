package prv.zielony.scala.tutorial.functional.programming.lambdas

import scala.collection.mutable.ListBuffer

/**
 * Created by zielony on 07.02.16.
 */
trait CountingSortable { /*collection:Traversable[Int] =>*/

  val collection:Traversable[Int];

  var translateCollection:(Traversable[Int] => Traversable[Int]) = { collection =>

    if(collection.size == 0) {
      collection;
    }
    else {
      val min = collection.min
      collection.map { element =>
        element - min
      }
    }
  }

  var countOccurences:(Traversable[Int] => Array[Int]) = { collection =>

    if(collection.size == 0 ){
      Array.empty
    }
    else {
      val occurences:Array[Int] = new Array[Int](collection.max + 1)

      for(element <- collection) {
        occurences(element) += 1
      }

      for(i <- 1 until occurences.size){
        occurences(i) += occurences(i-1)
      }

      occurences
    }
  }

  var fillFinalArray:(Array[Int] => Traversable[Int]) = { array =>

    if(array.size == 0) {
      array
    }
    else {
      var result:ListBuffer[Int] = new ListBuffer()

      for(i <- 1 until array.size) {
        for( j <- (array(i-1) until array(i))) {
          result += i
        }
      }

      result
    }
  }

  var reverseTranslate:((Traversable[Int], Int) => Traversable[Int]) = { (collection, min) =>

    if(collection.size == 0 || collection.min == collection.max) {
      collection;
    }
    else {
      collection.map { element =>
        element + min
      }
    }
  }

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
