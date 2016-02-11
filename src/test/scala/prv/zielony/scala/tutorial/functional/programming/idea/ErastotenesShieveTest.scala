package prv.zielony.scala.tutorial.functional.programming.idea

import java.io.File

import org.junit.runner.RunWith
import org.scalacheck.{Gen, Prop}
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.{Checkers, PropertyChecks};

/**
  * Created by kowczarek on 2016-02-09.
  */
@RunWith(classOf[JUnitRunner])
class ErastotenesShieveTest extends FunSuite with Checkers with PropertyChecks {

  val intervalSizeGenerator = Gen.choose(8, 100)

  val listGenerator:Gen[List[Int]] = Gen.listOfN(20, Gen.choose(300, 1000))

  val sourceGenerator = listGenerator.suchThat {list =>
    list.size >= 12
  }

  test("All returned results are primes") {
    check( Prop.forAll(sourceGenerator){ data =>

      var allPrimes = true;
      for(number <- ErastotenesPrimeFinder.findPrimes(data)) {
        allPrimes = allPrimes && isPrime(number)
      }

      allPrimes
    })
  }

  private def isPrime(number:Int):Boolean = {

    for(i <-  2 to Math.floor(Math.sqrt(number)).toInt) {
      if(number % i == 0) return false;
    }

    return true;
  }

}
