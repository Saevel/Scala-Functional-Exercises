package prv.zielony.scala.tutorial.functional.programming.higher.order.functions

import prv.zielony.scala.tutorial.functional.programming.higher.order.functions.model.{PrimaryKey, Person, Account}

/**
  * Created by kowczarek on 2016-02-08.
  */
package object dao {

  private val defaultEntityManager:EntityManager = new EntityManager{};

  private def findAccountDef(entityManager:EntityManager)(id:Long) =
    entityManager.find(id, classOf[Account])

  private def findPersonDef(entityManager: EntityManager)(id:Long) =
    entityManager.find(id, classOf[Person])

  def saveDef[PrimaryKeyType, EntityType <: AnyRef with PrimaryKey[PrimaryKeyType]]
    (entityManager: EntityManager)(entity:EntityType) = {

    if(entityManager.find(entity.id, entity.getClass).isDefined) {
      entityManager.update(entity)
    }
    else {
      entityManager.persist(entity)
    }
  }

  val save = saveDef(defaultEntityManager)

  val findAccountById:(Long => Option[Account with PrimaryKey[Long]]) = ???

  val findPersonById:(Long => Option[Person with PrimaryKey[Long]]) = ???
}