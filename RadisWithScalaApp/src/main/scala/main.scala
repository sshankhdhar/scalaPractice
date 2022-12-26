package testApp

import com.redis.RedisClient
import upickle.default._

object dscscds extends App {

  def connectToRedis: Map[String, Map[String, Map[Int, IHDataRecordType]]] = {
//    val r = new RedisClient("localhost", 6379)
//    r.set(
//      "67645",
//      "[{"test123":[{"111":{"accountId":"000","ihAsfTmstmpStr":"somevalue","decisionResultID":"somevalue"}}]},{"test456":[{"111":{"accountId":"000","ihAsfTmstmpStr":"somevalue","decisionResultID":"somevalue"}}]}]"
//    )
//    r.hmget[String, String]("ihmap", "67645")

    val ihMap = Some(
      Map(
        "67645" ->
          """{"test123": { "111": {"accountId": 22 ,"ihAsfTmstmpStr":"somedfvvalue","decisionResultID":"somevalue", "eventType":"Confirm Shopping Cart", "response": "IHACTION_TYPE_ACCEPTED"}, "222" : {"accountId":"000","ihAsfTmstmpStr":"somevalue" } } , "test456": { "111": {"accountId": 32 ,"ihAsfTmstmpStr":"some44value","decisionResultID":"somevalue", "eventType":"Confirm Shopping Cart"}, "222" : {"accountId":"000","ihAsfTmstmpStr":"somevalue" } } }"""
      )
    )

    // HMSET ihmap 67645 "{\"test123\": { \"111\": {\"accountId\": 22 ,\"ihAsfTmstmpStr\":\"somedfvvalue\",\"decisionResultID\":\"somevalue\", \"eventType\":\"Confirm Shopping Cart\", \"response\": \"IHACTION_TYPE_ACCEPTED\" }, \"222\" :{\"accountId\":\"000\",\"ihAsfTmstmpStr\":\"somevalue\", \"response\": \"IHACTION_TYPE_ACCEPTED\" } } , \"test456\": { \"345\": {\"accountId\": 32 ,\"ihAsfTmstmpStr\":\"some44value\",\"decisionResultID\":\"somevalue\", \"eventType\":\"Confirm Shopping Cart\" , \"response\": \"IHACTION_TYPE_ACCEPTED\" } } }"

    ihMap.get.map { singleStream: (String, String) =>
      singleStream._1 -> read[Map[String, Map[Int, IHDataRecordType]]](
        singleStream._2
      )
    }

  }

  var request: RealtimeContextDataType = new RealtimeContextDataType(
    billingAccountNumber = 67645,
    accountNumberType = "ANON",
    originatorSessionID = "XYA"
  );
  // var IHHistoryMapType:Map[Integer, IHDataRecordType] = Map()
  var sessCache: SessionCacheType = new SessionCacheType(
    sessions = List("XYA", "ABC")
  )
  var record: IHDataRecordType = new IHDataRecordType(
    eventType = "Confirm Shopping Cart"
  )

  var sdcsd = scala.collection.mutable.Map()

  var ihMap: Map[String, Map[String, Map[Int, IHDataRecordType]]] =
    connectToRedis // need verification on the Type of this "ihMap" variable

  def getHistoryFromSession(
    sessionData: Map[Int, IHDataRecordType],
    requestIH: (Int, IHDataRecordType), // verify data type of this Parameter
    currentSession: Boolean
  ): Unit = {

    var includeAccepts: Boolean = !currentSession
    if (includeAccepts) {
      var foundConfirm: Boolean = false
      var breakLoop: Boolean = false

      sessionData.foreach { ihTransaction: (Int, IHDataRecordType) =>
        if ("Confirm Shopping Cart" == ihTransaction._2.eventType && !breakLoop) {
          foundConfirm = true;
        } else if ((("Abandon Shopping Cart" == ihTransaction._2.eventType) ||
                   ("Cancel after Submit" == ihTransaction._2.eventType)) && !breakLoop) {
          breakLoop = true
          includeAccepts = false
        }
      }
      if (!foundConfirm)
        includeAccepts = false;
    }
    sessionData.foreach { ihTransaction: (Int, IHDataRecordType) =>
      println(
        "condition is : " + ((IHActionType.IHACTION_TYPE_ACCEPTED == ihTransaction._2.response) && includeAccepts || (IHActionType.IHACTION_TYPE_DECLINED == ihTransaction._2.response))
      )
      println(IHActionType.IHACTION_TYPE_ACCEPTED == ihTransaction._2.response)
      println(includeAccepts)
      println(IHActionType.IHACTION_TYPE_DECLINED == ihTransaction._2.response)
      if ((IHActionType.IHACTION_TYPE_ACCEPTED == ihTransaction._2.response) && includeAccepts || (IHActionType.IHACTION_TYPE_DECLINED == ihTransaction._2.response)) {
        val someresult: Map[Int, IHDataRecordType] = sessionData ++ Map(
          (requestIH._1, requestIH._2)
        )
        println(someresult)
      }
    }
  }

  //def execute():Unit ={
  def main(): Unit = {
    if (((-99999 != request.billingAccountNumber) || ("ANON" != request.accountNumberType)) && (sessCache.sessions
          .contains(request.originatorSessionID))) {
      var requestWithHistory: RequestType20 = new RequestType20(None);
      if (ihMap.contains(request.billingAccountNumber.toString)) {
        if ((-99999 == request.billingAccountNumber) && ("ANON" == request.accountNumberType)) {
          if (ihMap.contains(request.billingAccountNumber.toString) && ihMap
                .contains(request.originatorSessionID)) {
            getHistoryFromSession(
              Map(request.billingAccountNumber -> record),
              // requestWithHistory.ih, -- need clarification
              (1, record),
              currentSession = true
            );
          }
        } else {
          ihMap
            .filter(x => x._1 == request.billingAccountNumber.toString)
            .foreach {
              ihSession: (String, Map[String, Map[Int, IHDataRecordType]]) =>
                val currentSession: Boolean =
                  if (ihSession._1 == request.originatorSessionID) true
                  else false;
                getHistoryFromSession(
                  Map(
                    request.billingAccountNumber -> ihSession._2.head._2.head._2
                  ),
                  // requestWithHistory.ih, -- need clarification
                  (1, record),
                  currentSession
                );
            }
        }
      }
    }
  }
  main()
}
