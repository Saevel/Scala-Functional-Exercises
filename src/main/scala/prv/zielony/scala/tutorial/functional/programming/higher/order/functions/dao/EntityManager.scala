package prv.zielony.scala.tutorial.functional.programming.higher.order.functions.dao

import prv.zielony.scala.tutorial.functional.programming.higher.order.functions.model.PrimaryKey

import scala.collection.mutable.ListBuffer;

/**
  * Created by kowczarek on 2016-02-08.
  */
trait EntityManager {

  val persistedObjects:ListBuffer[PrimaryKey[_]] = new ListBuffer[PrimaryKey[_]];

  def persist[EntityType](entity:EntityType with PrimaryKey[_]):EntityType with PrimaryKey[_]  = {
    persistedObjects += entity

    entity
  }

  def find[PrimaryKeyType, EntityType](id:PrimaryKeyType,
                                       clazz:Class[EntityType]):Option[EntityType with PrimaryKey[_]] = {

    for(entity <- persistedObjects) {
      if(clazz.isAssignableFrom(entity.getClass) && entity.id == id) {
        return Some(entity.asInstanceOf[EntityType with PrimaryKey[PrimaryKeyType]]);
      }
    }

    None
  }

  def delete[EntityType](entity:EntityType with PrimaryKey[_]):Boolean = {

    if(persistedObjects.contains(entity)) {
      persistedObjects -= entity
      true;
    }
    else {
      false;
    }
  }

  def update[PrimaryKeyType, EntityType](entity:EntityType with PrimaryKey[PrimaryKeyType]):Unit = {

    for(i <- 0 to persistedObjects.size) {
      if(persistedObjects(i).getClass.isAssignableFrom(entity.getClass) && entity.id == persistedObjects(i).id) {
        persistedObjects(i) = entity
      }
    }
  }
}
