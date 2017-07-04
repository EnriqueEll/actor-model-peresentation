package com.webstore.payment

import akka.actor.Actor
import akka.event.Logging
import akka.actor.ActorRef

object Cart {
  case class Checkout(value: Double)
}

class CrazyCustomer(store:ActorRef) extends Actor {
  val log = Logging(context.system, this)
  
  def receive = {
    case Store.Open =>
      var a = 0
      for ( a <- 1 to 10){
        store ! Cart.Checkout(250.00/a)  
      }
    case Store.Close =>
      context.stop(self)
  }
}