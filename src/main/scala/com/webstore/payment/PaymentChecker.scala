package com.webstore.payment

import akka.actor.Actor
import akka.event.Logging

/**
 * Payment Message Factory
 */
object Payment {
  trait PayMethod
  case class CreditCar(flag: String) extends PayMethod
  case class PayPal(account: String) extends PayMethod
}

class PaymentChecker extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case Order.Check(order, value, payMethod) =>
      payMethod match {
        case Payment.CreditCar(flag) =>
          log.info(s"Check with Credicard Flag: $flag - Value:  $value")
          sender() ! Order.Accept(order)
        case Payment.PayPal(account) =>
          log.info(s"Check with PayPal Account: $account- Value:  $value")
          sender() ! Order.Accept(order)
      }
  }
}