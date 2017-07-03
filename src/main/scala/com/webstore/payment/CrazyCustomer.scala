package com.webstore.payment

import akka.actor.Actor
import akka.event.Logging
import akka.actor.ActorRef

class CrazyCustomer(store:ActorRef) extends Actor {
  val log = Logging(context.system, this)
  
  def receive = {
    case StoreStatus.Open =>
      var a = 0
      for ( a <- 1 to 10){
        store ! Payment.Checkout(250.00/a)  
      }
    case StoreStatus.Close =>
      context.stop(self)
  }
}