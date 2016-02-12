package prv.zielony.scala.tutorial.functional.programming.laziness.transfers

import scala.util.Random

/**
  * Created by kowczarek on 2016-02-09.
  */
object TransferService {

  def transfer(request:TransferRequest, token:Long):TransferResult = executeTransaction(request) match {

    case TransactionSuccessful => TransferResult(TransactionSuccessful, Some(token));

    case _ => TransferResult(TransactionFailed, None);
  }

  private def executeTransaction(request: TransferRequest):TransactionStatus =
    if(Random.nextBoolean()) TransactionSuccessful else TransactionFailed
}
