package com.webstore.payment

import akka.actor.Actor
import akka.event.Logging

class PaymentChecker extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case Messages.CreditCar(order, value, flag) =>
      log.info(s"Order: $order, Value:  $value and CredicardFlag: $flag")
      sender() ! Payment.Accept(order) 
    case Messages.PayPal(order, value, account) =>
      log.info(s"Order: $order, Value:  $value and Account: $account")
      sender() ! Payment.Reject(order)
  }
}