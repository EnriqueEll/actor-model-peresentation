package com.webstore.payment

import akka.actor.Actor
import akka.actor.Props

object StoreStatus {
  case class Open()
  case class Close()
}

class Store extends Actor{
  
  val orderChecker = context.actorOf(Props[OrderCheckout], "orderChecker")
  
  override def preStart() ={
    val crazyCustomer =  context.actorOf(Props(classOf[CrazyCustomer], self), "crazyCustomer")
    crazyCustomer ! StoreStatus.Open
  }
  
  def receive = {
    case Payment.Checkout(value) => 
      orderChecker ! Payment.Checkout(value)
  }
}