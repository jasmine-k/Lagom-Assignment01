package com.example.user.api

import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import play.api.libs.json.{Format, Json}

trait ExternalService extends Service {

  def getUser(): ServiceCall[NotUsed, UserData]

  override final def descriptor: Descriptor = {
    import Service._
    named("external-service")
      .withCalls(
        restCall(Method.GET, "/posts/1", getUser _)
      )
      .withAutoAcl(true)
  }

}

case class UserData(userId: Int, id: Int, title:String, body: String)

object UserData {
  /**
    * Format for converting user messages to and from JSON.
    *
    * This will be picked up by a Lagom implicit conversion from Play's JSON format to Lagom's message serializer.
    */
  implicit val format: Format[UserData] = Json.format[UserData]
}
