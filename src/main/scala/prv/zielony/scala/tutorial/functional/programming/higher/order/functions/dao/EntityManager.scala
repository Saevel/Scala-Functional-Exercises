package prv.zielony.scala.tutorial.functional.programming.higher.order.functions.dao

import prv.zielony.scala.tutorial.functional.programming.higher.order.functions.model.PrimaryKey

import scala.collection.mutable.ListBuffer;

/**
  * Created by kowczarek on 2016-02-08.
  */
trait EntityManager {

  val persistedObjects:ListBuffer[AnyRef with PrimaryKey] = new ListBuffer[AnyRef with PrimaryKey];

  def persist[EntityType <: AnyRef with PrimaryKey](entity:EntityType):EntityType  = {
    persistedObjects += entity

    entity
  }

  def find[PrimaryKeyType, EntityType](id:PrimaryKeyType,
                                       clazz:Class[EntityType]):Option[EntityType with PrimaryKey[PrimaryKeyType]] = {

    for(entity <- persistedObjects) {
      if(entity.getClass.isAssignableFrom(clazz) && entity.id == id) {
        return Some(entity.asInstanceOf[EntityType with PrimaryKey[PrimaryKeyType]]);
      }
    }

    None
  }

  def delete[EntityType <: AnyRef with PrimaryKey](entity:EntityType):Boolean = {

    if(persistedObjects.contains(entity)) {
      persistedObjects.-=(entity)
      true;
    }
    else {
      false;
    }
  }

  def update[EntityType <: AnyRef with PrimaryKey](entity:EntityType):Unit = {

    var persistentObject:AnyRef with PrimaryKey = null;
    for(i <- 0 to persistedObjects.size) {

      persistentObject = persistedObjects(i)
      if(persistentObject.getClass.isAssignableFrom(entity.getClass) && entity.id == persistentObject.id) {
        persistedObjects(i) = entity;
      }
    }
  }
}
