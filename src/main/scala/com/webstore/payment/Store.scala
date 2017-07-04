package com.webstore.payment

import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging

/**
 * Store Message Factory
 */
object Store {
  case class Open()
  case class Close()
}

class Store extends Actor{
  val log = Logging(context.system, this)
  
  val orderChecker = context.actorOf(Props[OrderCheckout], "orderChecker")
  
  override def preStart() ={
    val crazyCustomer =  context.actorOf(Props(classOf[CrazyCustomer], self), "crazyCustomer")
    crazyCustomer ! Store.Open
  }
  
  def receive = {
    case checkout:Cart.Checkout => 
      log.info(s"Customer wants checkout a cart with value: US$$ ${checkout.value}")
      orderChecker ! checkout
  }
}