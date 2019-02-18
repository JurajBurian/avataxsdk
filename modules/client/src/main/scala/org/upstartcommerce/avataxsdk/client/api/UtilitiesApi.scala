/* Copyright 2019 UpStart Commerce, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.upstartcommerce.avataxsdk.client.api

import akka.actor.ActorSystem
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import org.upstartcommerce.avataxsdk.client._
import org.upstartcommerce.avataxsdk.client.internal._
import org.upstartcommerce.avataxsdk.core.data.enums._
import org.upstartcommerce.avataxsdk.core.data.models._
import akka.http.scaladsl.model.headers.Authorization

import org.upstartcommerce.avataxsdk.json._
import play.api.libs.json._
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport._

/** /api/v2/utilities/ */
trait UtilitiesRootApi {
  def getMySubscription(serviceTypeId:ServiceTypeId):AvataxSimpleCall[SubscriptionModel]
  def listMySubscriptions():AvataxCollectionCall[SubscriptionModel]
  def ping:AvataxSimpleCall[PingResultModel]

}

object UtilitiesRootApi {
  def apply(requester: Requester, security: Option[Authorization])(implicit system: ActorSystem, materializer: ActorMaterializer): UtilitiesRootApi =
    new ApiRoot(requester, security) with UtilitiesRootApi {
      def getMySubscription(serviceTypeId:ServiceTypeId):AvataxSimpleCall[SubscriptionModel] = {
        val uri = Uri(s"/api/v2/utilities/subscriptions/$serviceTypeId")
        val req = HttpRequest(uri = uri).withMethod(GET)
        avataxSimpleCall[SubscriptionModel](req)
      }

      def listMySubscriptions():AvataxCollectionCall[SubscriptionModel] = {
        val uri = Uri(s"/api/v2/utilities/subscriptions")
        val req = HttpRequest(uri = uri).withMethod(GET)
        avataxCollectionCall[SubscriptionModel](req)
      }

      def ping:AvataxSimpleCall[PingResultModel] = {
        val uri = Uri(s"/api/v2/utilities/ping")
        val req = HttpRequest(uri = uri).withMethod(GET)
        avataxSimpleCall[PingResultModel](req)
      }
    }
}