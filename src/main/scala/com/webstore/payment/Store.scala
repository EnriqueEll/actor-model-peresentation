package com.webstore.payment

import akka.actor.Actor
import akka.actor.Props

object Store {
  case class Open()
  case class Close()
}

class Store extends Actor{
  
  val orderChecker = context.actorOf(Props[OrderCheckout], "orderChecker")
  
  override def preStart() ={
    val crazyCustomer =  context.actorOf(Props(classOf[CrazyCustomer], self), "crazyCustomer")
    crazyCustomer ! Store.Open
  }
  
  def receive = {
    case checkout:Cart.Checkout => 
      orderChecker ! checkout
  }
}