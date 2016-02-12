package prv.zielony.scala.tutorial.functional.programming.laziness.heap

import java.io.File

import org.junit.runner.RunWith
import org.scalacheck.{Gen, Prop}
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.{Checkers, PropertyChecks}
import prv.zielony.scala.tutorial.functional.programming.Implicits
;

import prv.zielony.scala.tutorial.functional.programming.Implicits._

/**
  * Created by kowczarek on 2016-02-09.
  */
@RunWith(classOf[JUnitRunner])
class StreamHeapTest extends FunSuite with Checkers with PropertyChecks {

  val intCollectionGenerator:Gen[List[Int]] = for {
    size <- Gen.choose(0, 100)
    list <- Gen.listOfN(size, Gen.choose(0, 100))
  } yield(list)

  val intHeap:HeapStream[Int] = new HeapStream[Int](0, null, null)(Implicits.compareInts)

  test("Fulfills max heap property") {
    check( Prop.forAll(intCollectionGenerator) { list =>

      var heap:HeapStream[Int] = intHeap;
      var i:Int = 0;
      for(element <- list) {
        heap = heap ++ element
        i = i + 1
      }

      var correct:Boolean = true;
      var element:Int = Int.MinValue
      var previousElement = Int.MaxValue
      for(j <- 0 until i) {
        val heapResult = heap -- ;
        element = heapResult._1
        heap = heapResult._2
        correct = correct && (previousElement >= element)
      }

      correct
    })
  }
}
