package com.example.user.api

import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method

trait UserService extends Service {

  def greetUser(name: String): ServiceCall[NotUsed, String]
  def getUserData(): ServiceCall[NotUsed, String]

  override final def descriptor: Descriptor = {
    import Service._
    named("user")
      .withCalls(
        restCall(Method.GET, "/api/user/:name", greetUser _),
        restCall(Method.GET, "/api/userdata",getUserData  _)
      )
      .withAutoAcl(true)
  }

}

