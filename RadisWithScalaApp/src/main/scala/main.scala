package testApp
//import Interfaces._
//import scala.util.control.Breaks._

//var ihhistttory = new IHistory()
//ihhistttory.
import com.redis._

/*class IHistory() { */
object scala extends App {

  def connectToRedis: Unit = {
    val r = new RedisClient("localhost", 6379)
    r.set(
      "67645",
      "[{\"test123\":[{\"111\":{\"accountId\":\"000\",\"ihAsfTmstmpStr\":\"somevalue\",\"decisionResultID\":\"somevalue\"}}]},{\"test456\":[{\"111\":{\"accountId\":\"000\",\"ihAsfTmstmpStr\":\"somevalue\",\"decisionResultID\":\"somevalue\"}}]}]"
    )
    println(r.get("67645"))
  }

  var request: RealtimeContextDataType = new RealtimeContextDataType(
    billingAccountNumber = 12345,
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
  // var ihMap: Map[String, Map[String, Map[Int, IHDataRecordType]]]  = Map(("12345" -> Map(("test123" -> Map((111 -> record))))));
  var ihMap: Map[String, Map[String, Map[Int, IHDataRecordType]]] = Map(
    "12345" -> Map(
      "test123" -> Map(111 -> record),
      "test456" -> Map(111 -> record)
    )
  ); // need verification on the Type of this "ihMap" variable

  "12345" -> "[{\"test123\":[{\"111\":{\"accountId\":\"000\",\"ihAsfTmstmpStr\":\"somevalue\",\"decisionResultID\":\"somevalue\"}}]}," +
    "{\"test456\":[{\"111\":{\"accountId\":\"000\",\"ihAsfTmstmpStr\":\"somevalue\",\"decisionResultID\":\"somevalue\"}}]}]"

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
    sessionData.foreach { ihTransaction =>
      if ((IHActionTypeEnum.IHACTION_TYPE_ACCEPTED == ihTransaction._2.response) && includeAccepts || (IHActionTypeEnum.IHACTION_TYPE_DECLINED == ihTransaction._2.response)) {
        val someresult = sessionData ++ Map((requestIH._1, requestIH._2))
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
    connectToRedis
  }
  main()
}
