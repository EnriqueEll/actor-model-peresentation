package com.webstore.payment

import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging

object Messages {
  trait Payment
  case class CreditCar(order: Int, value: Double, flag: String) extends Payment
  case class PayPal(order: Int, value: Double, account: String) extends Payment
}

object Payment {
  case class Accept(order: Int)
  case class Reject(order: Int)
  case class Fail(order: Int)
  case class Checkout(value: Double)
}

class OrderCheckout extends Actor {

  val log = Logging(context.system, this)
  val paymentChecker = context.actorOf(Props[PaymentChecker], "paymentChecker")
  var orderNum = 0
  
  def receive = {
    case Payment.Checkout(value) =>
      orderNum = orderNum + 1
      paymentChecker ! Messages.CreditCar(orderNum, value, "MasterCard")
    case Payment.Accept(order) =>
      log.info(s"Order: $order was Accepted")
    case Payment.Reject(order) =>
      log.info(s"Order: $order was Rejected")
    case Payment.Fail(order) =>
      log.info(s"Order: $order was Failed")

  }
}