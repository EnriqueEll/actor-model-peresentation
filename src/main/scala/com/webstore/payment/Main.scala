package com.webstore.payment

import akka.actor.ActorSystem
import akka.actor.Props

object Main {
  def main(args: Array[String]): Unit = {
    akka.Main.main(Array(classOf[Store].getName))
  }
}
