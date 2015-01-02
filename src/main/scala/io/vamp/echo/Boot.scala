package io.vamp.echo

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http

object Boot extends App {
  implicit val system = ActorSystem("echo")
  val service = system.actorOf(Props[Server], "echo-server")
  IO(Http) ! Http.Bind(service, interface = "localhost", port = system.settings.config.getInt("echo.port"))
}