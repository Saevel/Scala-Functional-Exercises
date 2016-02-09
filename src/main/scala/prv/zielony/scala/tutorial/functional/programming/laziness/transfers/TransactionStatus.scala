package prv.zielony.scala.tutorial.functional.programming.laziness.transfers

/**
  * Created by kowczarek on 2016-02-09.
  */
sealed trait TransactionStatus {
  val status:String;
}

case object TransactionSuccessful extends TransactionStatus {
  override val status: String = "SUCCESS"
}

case object TransactionFailed extends TransactionStatus {
  override val status: String = "FAILED"
}
