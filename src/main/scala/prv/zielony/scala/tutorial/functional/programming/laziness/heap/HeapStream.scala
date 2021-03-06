package prv.zielony.scala.tutorial.functional.programming.laziness.heap

/**
 * Created by zielony on 09.02.16.
 */
class HeapStream[ElementType](val content:ElementType, left: => HeapStream[ElementType],
                               right: => HeapStream[ElementType])(val compare:((ElementType, ElementType) => Int)) {

  def ++(element:ElementType):HeapStream[ElementType] = ???

  def --():(ElementType, HeapStream[ElementType]) = ???

  def map[B](transform:(ElementType => B))(compareB:((B,B) => Int)):HeapStream[B] = ???
}

object HeapStream {

  def apply[ElementType](v1: Traversable[ElementType])
                        (implicit compare:((ElementType, ElementType) => Int)):HeapStream[ElementType] = ???
}