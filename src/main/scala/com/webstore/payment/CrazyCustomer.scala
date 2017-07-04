package com.webstore.payment

import akka.actor.Actor
import akka.event.Logging
import akka.actor.ActorRef
import com.webstore.payment.Payment._

object Cart {
  case class Checkout(value: Double, payMethod: PayMethod)
}

class CrazyCustomer(store: ActorRef) extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case Store.Open =>
      var a = 0
      for (a <- 1 to 100) {
        log.info(s"I want to by this... $a")
        val payMethod = if (a % 2 == 0)
          CreditCar("MasterCard")
        else
          PayPal(s"User-$a")

        store ! Cart.Checkout(250.00 / a, payMethod)
      }
    case Store.Close =>
      context.stop(self)
  }
}