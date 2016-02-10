package prv.zielony.scala.tutorial.functional.programming.lambdas

/**
 * Created by zielony on 07.02.16.
 */
trait CountingSortable { /*collection:Traversable[Int] =>*/

  val collection:Traversable[Int];

  var translateCollection:(Traversable[Int] => Traversable[Int]) = ???

  var countOccurences:(Traversable[Int] => Array[Int]) = ???

  var fillFinalArray:(Array[Int] => Traversable[Int]) = ???

  var reverseTranslate:((Traversable[Int], Int) => Traversable[Int]) = ???

  def countingSort():Traversable[Int] = {

    val mappedCollection = translateCollection(collection)

    val occurences = countOccurences(mappedCollection);

    val summedOccurences = sumOccurences(occurences)

    reverseTranslate(fillFinalArray(summedOccurences), findMin(collection))
  }

  private def sumOccurences(input:Array[Int]):Array[Int] = {
    for(i <- 1 to input.size) {
      input(i) = input(i) + input(i-1)
    }

    input
  }

  private def findMin(col:Traversable[Int]):Int = {
    var min = Int.MaxValue;

    for(element <- col) {
      if(element < min) {
        min = element
      }
    }

    min
  }
}
