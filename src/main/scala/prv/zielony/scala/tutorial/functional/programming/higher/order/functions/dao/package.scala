package prv.zielony.scala.tutorial.functional.programming.higher.order.functions

import prv.zielony.scala.tutorial.functional.programming.higher.order.functions.model.{PrimaryKey, Person, Account}

/**
  * Created by kowczarek on 2016-02-08.
  */
package object dao {

  //TODO: Create when implemented
  private val defaultEntityManager:EntityManager = ???

  //TODO: Kursant sam ma podkonfigurowac metody defaultEntityManager'em zeby uzyskac poprawne sygnatury

  private def findAccountDef(entityManager:EntityManager)(id:Long) =
    entityManager.find(id, classOf[Account])

  private def findPersonDef(entityManager: EntityManager)(id:Long) =
    entityManager.find(id, classOf[Person])


  val findAccountById:(Long => Option[Account with PrimaryKey[Long]]) = findAccountDef(defaultEntityManager)

  val findPersonById:(Long => Option[Person with PrimaryKey[Long]]) = findPersonDef(defaultEntityManager)
}
