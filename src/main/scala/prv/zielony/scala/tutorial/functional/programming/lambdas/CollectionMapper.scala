package prv.zielony.scala.tutorial.functional.programming.lambdas

/**
 * Created by zielony on 08.02.16.
 */
object CollectionMapper {

  def translate(collection:Traversable[Int]):Traversable[Int] = {

    val min = collection.min

    collection.map { element =>
      element - min
    }
  }

  def reverseTranslate(collection:Traversable[Int], min:Int):Traversable[Int] = {

    collection.map { element =>
      element + min
    }
  }
}
