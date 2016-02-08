package prv.zielony.scala.tutorial.functional.programming.higher.order.functions.dao

import prv.zielony.scala.tutorial.functional.programming.higher.order.functions.model.PrimaryKey

import scala.collection.mutable.ListBuffer;

/**
  * Created by kowczarek on 2016-02-08.
  */
trait EntityManager {

  //TODO: Implement

  val persistedObjects:ListBuffer[AnyRef with PrimaryKey] = new ListBuffer[AnyRef with PrimaryKey];

  def save(entity:AnyRef with PrimaryKey):AnyRef with PrimaryKey;

  def find[PrimaryKeyType, EntityType](id:PrimaryKeyType,
                                       clazz:Class[EntityType]):Option[EntityType with PrimaryKey[PrimaryKeyType]]

  def delete[PrimaryKeyType, EntityType](entity:EntityType with PrimaryKey[PrimaryKeyType]):Boolean

  def update[PrimaryKeyType, EntityType](entity:EntityType with PrimaryKey[PrimaryKeyType]):EntityType with PrimaryKey[PrimaryKeyType]
}
