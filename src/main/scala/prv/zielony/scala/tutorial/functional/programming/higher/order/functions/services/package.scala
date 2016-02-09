package prv.zielony.scala.tutorial.functional.programming.higher.order.functions

import prv.zielony.scala.tutorial.functional.programming.higher.order.functions.model.{Account, PrimaryKey, Person}

import prv.zielony.scala.tutorial.functional.programming.higher.order.functions.dao._

/**
  * Created by kowczarek on 2016-02-08.
  */
package object services {

  private def findPersonsAccountBalanceDef(findPerson:(Long => Option[Person]))(findAccount:(Long => Option[Account]))(id:Long) = {

    findPerson(id).flatMap { person =>
      findAccount(person.accountId).map { account =>
        account.balance
      }
    }.getOrElse(0.0)
  }

  val findAccountBalanceForPerson:(Long => Double) = ???

  val findPerson:(Long => Option[Person]) = ???
}
