package com.webstore.payment

import akka.actor.Actor
import akka.event.Logging


object Messages {
  case class CreditCar(order: Int, value: Double, flag: String) 
  case class PayPal(order: Int, value: Double, account: String) 
}

class PaymentChecker extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case Messages.CreditCar(order, value, flag) =>
      log.info(s"Order: $order, Value:  $value and CredicardFlag: $flag")
      sender() ! Order.Accept(order) 
    case Messages.PayPal(order, value, account) =>
      log.info(s"Order: $order, Value:  $value and Account: $account")
      sender() ! Order.Reject(order)
  }
}