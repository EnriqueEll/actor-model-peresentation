package com.webstore.payment

import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging

object Order {
  case class Accept(order: Int)
  case class Reject(order: Int)
  case class Fail(order: Int)
}

class OrderCheckout extends Actor {

  val log = Logging(context.system, this)
  val paymentChecker = context.actorOf(Props[PaymentChecker], "paymentChecker")
  var orderNum = 0
  
  def receive = {
    case Cart.Checkout(value) =>
      orderNum = orderNum + 1
      paymentChecker ! Messages.CreditCar(orderNum, value, "MasterCard")
    case Order.Accept(order) =>
      log.info(s"Order: $order was Accepted")
    case Order.Reject(order) =>
      log.info(s"Order: $order was Rejected")
    case Order.Fail(order) =>
      log.info(s"Order: $order was Failed")

  }
}