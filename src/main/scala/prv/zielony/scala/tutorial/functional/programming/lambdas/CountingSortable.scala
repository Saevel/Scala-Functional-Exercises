package prv.zielony.scala.tutorial.functional.programming.lambdas

/**
 * Created by zielony on 07.02.16.
 */
trait CountingSortable { /*collection:Traversable[Int] =>*/

  val collection:Traversable[Int];

  var translateCollection:(Traversable[Int] => Traversable[Int]) = { collection =>

    val min = collection.min
    collection.map { element =>
      element - min
    }
  }

  var countOccurences:(Traversable[Int] => Array[Int]) = { collection =>

    val occurences:Array[Int] = new Array[Int](collection.max)

    for(element <- collection) {
      occurences(element) += 1
    }

    for(i <- 1 to occurences.size){
      occurences(i) += occurences(i-1)
    }

    occurences
  }

  var fillFinalArray:(Array[Int] => Traversable[Int]) = { array =>

    var result:List[Int] = List()

    for(i <- 1 to array.size) {
      for( j <- array(i-1) to array(i)) {
        result = i :: result
      }
    }

    result
  }

  var reverseTranslate:((Traversable[Int], Int) => Traversable[Int]) = { (collection, min) =>
    collection.map { element =>
      element + min
    }
  }

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
