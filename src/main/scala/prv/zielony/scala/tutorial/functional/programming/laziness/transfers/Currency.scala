package prv.zielony.scala.tutorial.functional.programming.laziness.transfers

/**
  * Created by kowczarek on 2016-02-09.
  */
sealed trait Currency {
  val code:String;
}

case object PLN extends Currency {
  override val code: String = "PLN"
}

case object USD extends Currency {
  override val code: String = "USD"
}

case object EUR extends Currency {
  override val code: String = "EUR"
}
