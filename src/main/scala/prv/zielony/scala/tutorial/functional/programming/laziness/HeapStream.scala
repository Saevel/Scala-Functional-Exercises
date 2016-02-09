package prv.zielony.scala.tutorial.functional.programming.laziness

/**
 * Created by zielony on 09.02.16.
 */
class HeapStream[TYPE] {

  def add(element:TYPE):Unit = ???

  def top():TYPE = ???

  private def heapify() = ???
}

object HeapStream extends (Traversable[_] => HeapStream[_]) {

  override def apply(v1: Traversable[_]): HeapStream[_] = {
    ???
  }
}