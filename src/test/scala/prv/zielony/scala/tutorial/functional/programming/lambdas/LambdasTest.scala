package prv.zielony.scala.tutorial.functional.programming.lambdas

import org.junit.runner.RunWith
import org.scalacheck.{Gen, Prop}
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.{Checkers, PropertyChecks}
import prv.zielony.scala.tutorial.functional.programming.Implicits._

import scala.util.Random

/**
 * Created by zielony on 07.02.16.
 */
@RunWith(classOf[JUnitRunner])
class LambdasTest extends FunSuite with Checkers with PropertyChecks {

  val lowerBoundGenerator = Gen.choose(-50, 49);

  val upperBoundGenerator = Gen.choose(50, 100);

  val sizeGenerator = Gen.choose(2, 20);

  /*
  val collectionGenerator:Gen[List[Int]] = for {
    lowerBound <- lowerBoundGenerator
    upperBound <- upperBoundGenerator
    size <- sizeGenerator
    result <- Gen.listOfN(size, Gen.choose(lowerBound, upperBound))
  } yield(result)
  */

  def sizedCollectionGenerator(size:Int) = Gen.sized { size =>
    val basic = for {
      lowerBound <- lowerBoundGenerator
      upperBound <- upperBoundGenerator
      result <- Gen.listOfN(size, Gen.choose(lowerBound, upperBound))
    } yield(result)

    basic.suchThat { collection =>
      collection.size >= size
    }
  }

  val collectionGenerator = for {
   size <- sizeGenerator
   result <- sizedCollectionGenerator(size)
  } yield(result)

  val intGenerator = Gen.choose[Int](0, 1000);

  val zeroBasedCollectionGenerator:Gen[List[Int]] = for {
    zero <- Gen.const[Int](0)
    size <- sizeGenerator
    list <- sizedCollectionGenerator(size)
  } yield(zero :: list)

  val assuredCountCollectionGenerator:Gen[Tuple3[List[Int], Int, Int]] = for {
    number <- intGenerator
    count <- intGenerator
    numberOccurences <- Gen.listOfN[Int](count, Gen.const[Int](number))
    lowerBound <- lowerBoundGenerator
    upperBound <- upperBoundGenerator
    size <- sizeGenerator
    result <- Gen.listOfN[Int](size, Gen.choose[Int](lowerBound, upperBound)).suchThat { element =>
      element != number
    }
  } yield((result ++ numberOccurences, number, count))


  test("is defined for all integer collections") {
    check( Prop.forAll(sizedCollectionGenerator(20)) { collection => {
        (collection.countingSort() != null);
      }
    })
  }

  test("output elements are sorted") {
    check( Prop.forAll(sizedCollectionGenerator(20)) { collection => {
        val result = collection.countingSort()

        var sorted:Boolean = true;
        var previousElement = Int.MinValue
        for(element <- result) {
          sorted = sorted && (previousElement <= element);
          previousElement = element;
        }

        sorted
      }
    })
  }

  test("for collections with zero as minimum, translation is identity"){
    check(Prop.forAll(zeroBasedCollectionGenerator) { collection =>

      val translated = collection.translateCollection(collection)

      var result = true;
      for(originalElement <- collection; translatedElement <- translated) {
        result = result && (originalElement == translatedElement)
      }

      result
    })
  }

  test("translation value is equal to collection minimum") {

    check(Prop.forAll(sizedCollectionGenerator(20)) {collection => {

      val min = collection.min

      val translated = collection.translateCollection(collection)

      var result = true;

      var originalElement:Int = 0;
      var i:Int = 0
      for(translatedElement <- translated) {
        originalElement = collection(i)
        result = result && (translatedElement == originalElement - min)
        i = i + 1
      }

      result
    }})
  }

  test("occurence count is defined for all integer collections") {
    check( Prop.forAll(sizedCollectionGenerator(20)) { collection =>
      val result = collection.countOccurences(collection);

      result != null && result.size == collection.size
    })
  }

  test("element occurence count") {
    check(Prop.forAll(assuredCountCollectionGenerator) { triple =>

      val (collection, number, count) = triple;

      val occurenceCount = collection.countOccurences(collection);

      occurenceCount(number) == count;
    })
  }

  /*
  test("element is inserted into the final array at a correct place") {
    check( Prop.forAll(assuredCountCollectionGenerator) { triple =>
      val (collection, number, count) = triple;

      val finalArray = collection.fillFinalArray

      finalArray(count-1) == number
    })
  }
  */
}
