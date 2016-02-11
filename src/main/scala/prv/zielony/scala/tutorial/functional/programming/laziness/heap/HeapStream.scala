package prv.zielony.scala.tutorial.functional.programming.laziness.heap

/**
 * Created by zielony on 09.02.16.
 */
class HeapStream[ElementType](val content:ElementType, left: => HeapStream[ElementType],
                               right: => HeapStream[ElementType])(val compare:((ElementType, ElementType) => Int)) {

  def ++(element:ElementType):HeapStream[ElementType] = {

    if(compare(element, content) > 0 ) {
      return new HeapStream[ElementType](element, this, null)(compare)
    }
    else if(left != null) {
      left ++ element
    }
    else if(right != null){
      right ++ element
    }

    //TODO: Fixme
    return new HeapStream(element, left, right)(compare);
  }

  def --():(ElementType, HeapStream[ElementType]) = {

    // TODO: Max - heapify before extract

    ((compare(this.content, left.content) >=0 ), (compare(this.content, right.content) >= 0),
      (compare(left.content, right.content) >= 0 )) match {
      case (true, true, true) => ???
      case (true, true, false) => ???
      case (true, false, _) => left --
      case (false, true, _) => right --
      case (false, false, true) => ???
      case (false, false, false) => ???
    }

    ???
  }

  def map[B](transform:(ElementType => B))(compareB:((B,B) => Int)):HeapStream[B] = {
    new HeapStream[B](transform(content), left.map(transform)(compareB), right.map(transform)(compareB))(compareB)
  }
}

object HeapStream {

  def apply[ElementType](v1: Traversable[ElementType])
                        (implicit compare:((ElementType, ElementType) => Int)):HeapStream[ElementType] = ???
}