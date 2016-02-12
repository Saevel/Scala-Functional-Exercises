package prv.zielony.scala.tutorial.functional.programming.idea

/**
  * Created by kowczarek on 2016-02-08.
  */
class ErastotenesShieveFunctional extends PrimeShieve {

  override def shieve(data:Traversable[Int], searchRange:Range): Traversable[Int] = {
    removeDivisible(data, searchRange);
  }

  def removeDivisible(data:Traversable[Int], searchRange:Range):Traversable[Int] = {

    var removed:List[Int] = List()

    for(divisor <- searchRange) {
      for(element <- data) {

        if(element % divisor == 0 && element != divisor) {
          removed = element :: removed
        }

      }
    }

    removed
  }
}

