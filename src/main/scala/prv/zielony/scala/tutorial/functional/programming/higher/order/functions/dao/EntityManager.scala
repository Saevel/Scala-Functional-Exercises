package prv.zielony.scala.tutorial.functional.programming.higher.order.functions.dao

import prv.zielony.scala.tutorial.functional.programming.higher.order.functions.model.PrimaryKey

import scala.collection.mutable.ListBuffer;

/**
  * Created by kowczarek on 2016-02-08.
  */
trait EntityManager {

  val persistedObjects:ListBuffer[AnyRef with PrimaryKey[Any]] = new ListBuffer[AnyRef with PrimaryKey[Any]];

  def persist[EntityType <: AnyRef with PrimaryKey[Any]](entity:EntityType):EntityType  = {
    persistedObjects.+=(entity)

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

  def delete[EntityType <: PrimaryKey[Any]](entity:EntityType):Boolean = {

    if(persistedObjects.contains(entity)) {
      persistedObjects -= entity
      true;
    }
    else {
      false;
    }
  }

  def update[PrimaryKeyType, EntityType <: PrimaryKey[Any]](entity:EntityType):Unit = {

    for(i <- 0 to persistedObjects.size) {
      if(persistedObjects(i).getClass.isAssignableFrom(entity.getClass) && entity.id == persistedObjects(i).id) {
        persistedObjects(i) = entity
      }
    }
  }
}
