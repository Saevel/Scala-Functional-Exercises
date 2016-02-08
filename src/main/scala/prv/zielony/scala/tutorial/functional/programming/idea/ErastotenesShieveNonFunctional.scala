package prv.zielony.scala.tutorial.functional.programming.idea

import scala.collection.immutable.Range.Inclusive
import scala.collection.mutable.ListBuffer

/**
  * Created by kowczarek on 2016-02-08.
  */
object ErastotenesShieveNonFunctional extends PrimeShieve {

  def shieve(data:Traversable[Int], searchRange:Range):Traversable[Int] = {

    val removed:ListBuffer[Int] = new ListBuffer[Int]()
    removeDivisible(data, searchRange, removed)

    removed
  }

  def removeDivisible(data: Traversable[Int], searchRange: Range, removed: ListBuffer[Int]): Unit = {
    for (divisor <- searchRange) {

      for (element <- data) {

        if (element != divisor && element % divisor == 0) {
          removed += element
        }
      }
    }
  }

}
