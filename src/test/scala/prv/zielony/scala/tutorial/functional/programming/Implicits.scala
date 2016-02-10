package prv.zielony.scala.tutorial.functional.programming

import prv.zielony.scala.tutorial.functional.programming.lambdas.CountingSortable

/**
 * Created by zielony on 08.02.16.
 */

object Implicits {

  implicit def addCountingSort(input:Traversable[Int]):CountingSortable = new CountingSortable {
     override val collection: Traversable[Int] = input
  }

  implicit val compareInts:((Int, Int) => Int) = (first:Int, second:Int) => {
    first - second
  }
}



