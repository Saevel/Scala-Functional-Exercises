package prv.zielony.scala.tutorial.functional.programming.laziness.transfers

import scala.util.Random

/**
  * Created by kowczarek on 2016-02-09.
  */
trait TokenGenerator {

  def generateToken(transactionRecord: TransferRequest):Long = ((Math.floor((Math.pow(transactionRecord.sourceId,
    (Random.nextInt() % 10)) % 1000) * transactionRecord.currency.code.length + (Math.pow(transactionRecord.targetId,
    (Random.nextInt() % 10)) % 1000) * transactionRecord.transferredSum)) % 1000).asInstanceOf[Long]
}
