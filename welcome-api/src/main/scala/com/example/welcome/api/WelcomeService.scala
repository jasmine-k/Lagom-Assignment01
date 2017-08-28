package com.example.welcome.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.broker.kafka.{KafkaProperties, PartitionKeyStrategy}
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}

trait WelcomeService extends Service {

  def welcome(name: String) : ServiceCall[NotUsed, String]

  override final def descriptor: Descriptor = {
    import Service._
    named("welcome")
      .withCalls(
        restCall(Method.GET, "/api/welcome/:name", welcome _)
      )
      .withAutoAcl(true)
  }

}
