package com.example.user.impl

import akka.NotUsed
import akka.actor.ActorSystem
import com.example.user.api.{ExternalService, UserData, UserService}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import scala.concurrent.duration._

import scala.concurrent.{ExecutionContext, Future}

class UserServiceImpl(externalService: ExternalService)(implicit ec: ExecutionContext) extends UserService {

  override def greetUser(name: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    Future.successful(s"Welcome, $name")

  }

  override def getUserData() = ServiceCall { _ =>
    val result: Future[UserData] = externalService.getUser().invoke()
    result.map(response => response.toString)
  }

  val message = "scheduler"
  val system = ActorSystem("UserImplActor")
  val schedulerActorRef = system.actorOf(SchedulerActor.props(externalService))
  system.scheduler.schedule(0 seconds, 20 seconds, schedulerActorRef, message)

}
