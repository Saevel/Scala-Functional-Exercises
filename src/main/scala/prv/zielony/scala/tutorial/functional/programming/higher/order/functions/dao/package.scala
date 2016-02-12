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

  def saveDef[PrimaryKeyType, EntityType]
    (entityManager: EntityManager)(entity:EntityType with PrimaryKey[PrimaryKeyType]) = {

    if(entityManager.find(entity.id, entity.getClass).isDefined) {
      entityManager.update[PrimaryKeyType, EntityType](entity)
    }
    else {
      entityManager.persist[EntityType](entity)
    }
  }

  val saveAccount = saveDef[Long, Account](defaultEntityManager)_

  def save[PrimaryKeyType, EntityType](entity: EntityType with PrimaryKey[PrimaryKeyType]) =
    saveDef[PrimaryKeyType, EntityType](defaultEntityManager)(entity)

  val findPersonById:(Long => Option[Person with PrimaryKey[Long]]) = ???
}