import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"
  
  lazy val akka = Seq("com.typesafe.akka" % "akka-actor_2.12" % "2.5.3")
}
