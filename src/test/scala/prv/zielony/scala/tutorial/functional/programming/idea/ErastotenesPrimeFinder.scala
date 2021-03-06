package prv.zielony.scala.tutorial.functional.programming.idea

import scala.collection.mutable.ListBuffer

/**
  * Created by kowczarek on 2016-02-10.
  */
object ErastotenesPrimeFinder {

  def findPrimes(data:Traversable[Int]):Traversable[Int] = {

    val upperCheckLimit = Math.floor(Math.sqrt(data.max)).toInt

    val shieves = for {
      i <- 0 to 4
    } yield(new ErastotenesShieveFunctional)

    /*
    val ranges = for {
      denominator <- 1 until 4
      min <- Math.floorDiv((denominator - 1) * upperCheckLimit, 4).toInt
      max <- Math.floorDiv(denominator * upperCheckLimit, 4).toInt
    } yield(Range(min, max))
    */

    val ranges = IndexedSeq((2 to Math.ceil(upperCheckLimit/4).toInt),
      (Math.ceil(upperCheckLimit/4).toInt to Math.ceil(upperCheckLimit/2).toInt),
      (Math.ceil(upperCheckLimit/2).toInt to Math.ceil(3*upperCheckLimit/4).toInt),
      (Math.ceil(3*upperCheckLimit/4).toInt to upperCheckLimit))

    val removedSets = for {
      shieve <- shieves
      range <- ranges
    } yield(shieve.shieve(data, range))

    val removed:ListBuffer[Int] = new ListBuffer[Int]
    for(set <- removedSets) {
      removed ++= set
    }

    val result = data.dropWhile { element =>
      removed.contains(element)
    }

    result
  }
}
