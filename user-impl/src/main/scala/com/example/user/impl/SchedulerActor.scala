package com.example.user.impl

import akka.actor.{Actor, Props}
import com.example.user.api.ExternalService
import play.api.Logger
import scala.concurrent.ExecutionContext.Implicits.global

class SchedulerActor(externalService: ExternalService) extends Actor{

  override def receive: Receive = {

    case "scheduler" =>
      val userData = externalService.getUser().invoke()
      userData.map(userData=> Logger.info("User id : "+ userData.id+" User title : "+ userData.title+" User body : "+ userData.body))

    case _ =>
      Logger.info("Invalid Option")
  }

}

object SchedulerActor {

  def props(externalService: ExternalService): Props =
    Props(classOf[SchedulerActor], externalService)

}
