package com.webstore

import com.webstore.payment.Store

object Main {
  def main(args: Array[String]): Unit = {
    akka.Main.main(Array(classOf[Store].getName))
  }
}
