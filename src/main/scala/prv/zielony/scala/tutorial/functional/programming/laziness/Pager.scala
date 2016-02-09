package prv.zielony.scala.tutorial.functional.programming.laziness

/**
 * Created by zielony on 09.02.16.
 */
class Pager[T](collection:Traversable[T], val pageSize:Int) {

  def page(start:Int):Traversable[T] = ???
}
