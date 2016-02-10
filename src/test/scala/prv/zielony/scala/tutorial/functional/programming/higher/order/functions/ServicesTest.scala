package prv.zielony.scala.tutorial.functional.programming.higher.order.functions

import org.junit.runner.RunWith
import org.scalacheck.{Gen, Prop}
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.prop.{Checkers, PropertyChecks}
import prv.zielony.scala.tutorial.functional.programming.higher.order.functions.model.{Account, PrimaryKey, Person}
;

/**
  * Created by kowczarek on 2016-02-09.
  */
@RunWith(classOf[JUnitRunner])
class ServicesTest extends FunSuite with Checkers with PropertyChecks {

  val ageGenerator = Gen.choose(0, 99);

  /*
  val personGenerator:Gen[Person with PrimaryKey[Long]] = for {
    uuid <- Gen.uuid
    id <- uuid.getLeastSignificantBits
    name <- Gen.alphaStr
    surname <- Gen.alphaStr
    age <- Gen.choose(0, 99)
  } yield(new Person(name, surname, age) with PrimaryKey[Long] {
    override val id:Long = id
  })

  val personWithAccountGenerator:Gen[Tuple2[Person with PrimaryKey[Long], Account with PrimaryKey[Long]]] = for {
    uuid <- Gen.uuid
    accountUuid <- Gen.uuid
    id <- uuid.getLeastSignificantBits
    name <- Gen.alphaStr
    surname <- Gen.alphaStr
    age <- Gen.choose(0, 99)
    accountId <- accountUuid.getLeastSignificantBits
    balance <- Gen.choose(0.0, 2000.0)
  } yield((new Person(name, surname, age) with PrimaryKey[Long] {
    override val id:Long = id
  }, new Account(accountId, balance) with PrimaryKey[Long] {
    override val id: Long = accountId
  }))
  */

}
