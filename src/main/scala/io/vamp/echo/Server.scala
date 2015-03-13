package io.vamp.echo

import akka.actor.Actor
import spray.http.CacheDirectives.`no-store`
import spray.http.HttpHeaders.{RawHeader, `Cache-Control`}
import spray.http.MediaTypes._
import spray.http.StatusCodes._
import spray.routing.HttpService

class Server extends Actor with HttpService {
  def actorRefFactory = context

  def receive = runRoute(respondWithHeaders(`Cache-Control`(`no-store`), RawHeader("Pragma", "no-cache")) {
    respondWithMediaType(`application/json`) {
      path(Rest) { path: String =>
        get {
          complete(OK, getContent(path))
        } ~ (post | put) {
          entity(as[String]) {
            body =>
              complete(OK, updateContent(path, body))
          }
        } ~ delete {
          complete(OK, deleteContent(path))
        }
      }
    }
  })

  val content = scala.collection.mutable.Map[String, String]()

  def getContent(path: String): String = content.get(path) match {
    case None => ""
    case Some(value) => value
  }

  def updateContent(path: String, value: String): String = {
    content(path) = value
    value
  }

  def deleteContent(path: String): String = content.remove(path) match {
    case None => ""
    case Some(value) => value
  }
}

