package testApp

import java.time.Instant
import java.sql.Timestamp

object TrackerLevelTypeEnum extends Enumeration {

  type TrackerLevelType = Value

  val MR_LOG_NONE = Value("MR_LOG_NONE")
  val MR_LOG_ERROR = Value("MR_LOG_ERROR")
  val MR_LOG_WARN = Value("MR_LOG_WARN")
  val MR_LOG_INFO = Value("MR_LOG_INFO")
  val MR_LOG_TRACE = Value("MR_LOG_TRACE")
}

object OfferGroupExpandableTypeEnum extends Enumeration {

  type OfferGroupExpandableType = Value

  val OFFERGROUPEXPANDABLE_1 = Value("OFFERGROUPEXPANDABLE_1")
  val OFFERGROUPEXPANDABLE_2 = Value("OFFERGROUPEXPANDABLE_2")
  val OFFERGROUPEXPANDABLE_3 = Value("OFFERGROUPEXPANDABLE_3")
  val OFFERGROUPEXPANDABLE_UNKNOWN = Value("OFFERGROUPEXPANDABLE_UNKNOWN")
}

object OfferDispositionTypeEnum extends Enumeration {

  type OfferDispositionType = Value

  val OFFERDISPOSITION_ACCEPTED = Value("OFFERDISPOSITION_ACCEPTED")
  val OFFERDISPOSITION_DECLINED = Value("OFFERDISPOSITION_DECLINED")
  val OFFERDISPOSITION_MAYBELATER = Value("OFFERDISPOSITION_MAYBELATER")
  val OFFERDISPOSITION_NOTPITCHED = Value("OFFERDISPOSITION_NOTPITCHED")
}

object OfferGroupClosingDispositionTypeEnum extends Enumeration {

  type OfferGroupClosingDispositionType = Value

  val OFFERGROUPCLOSINGDISPOSITION_ACCEPT = Value(
    "OFFERGROUPCLOSINGDISPOSITION_ACCEPT"
  )
  val OFFERGROUPCLOSINGDISPOSITION_NULL = Value(
    "OFFERGROUPCLOSINGDISPOSITION_NULL"
  )
}

object OfferGroupDispositionLevelTypeEnum extends Enumeration {

  type OfferGroupDispositionLevelType = Value

  val OFFERGROUPDISPOSITIONLEVEL_MEMBER = Value(
    "OFFERGROUPDISPOSITIONLEVEL_MEMBER"
  )
  val OFFERGROUPDISPOSITIONLEVEL_GROUP = Value(
    "OFFERGROUPDISPOSITIONLEVEL_GROUP"
  )
}

object ValueTypeEnum extends Enumeration {

  type ValueType = Value

  val VALUETYPE_UNDEFINED = Value("VALUETYPE_UNDEFINED")
  val VALUETYPE_RSTRING = Value("VALUETYPE_RSTRING")
  val VALUETYPE_FLOAT64 = Value("VALUETYPE_FLOAT64")
  val VALUETYPE_INT64 = Value("VALUETYPE_INT64")
  val VALUETYPE_LIST_RSTRING = Value("VALUETYPE_LIST_RSTRING")
  val VALUETYPE_LIST_FLOAT64 = Value("VALUETYPE_LIST_FLOAT64")
  val VALUETYPE_LIST_INT64 = Value("VALUETYPE_LIST_INT64")
  val VALUETYPE_DATE_NP = Value("VALUETYPE_DATE_NP")
  val VALUETYPE_DATE_NF = Value("VALUETYPE_DATE_NF")
  val VALUETYPE_BOOLEAN = Value("VALUETYPE_BOOLEAN")
  val VALUETYPE_LIST_INTRANGE = Value("VALUETYPE_LIST_INTRANGE")
  val VALUETYPE_NESTED_RSTRING = Value("VALUETYPE_NESTED_RSTRING")
  val VALUETYPE_NESTED_FLOAT64 = Value("VALUETYPE_NESTED_FLOAT64")
  val VALUETYPE_NESTED_INT64 = Value("VALUETYPE_NESTED_INT64")
  val VALUETYPE_NESTED_DATE_NP = Value("VALUETYPE_NESTED_DATE_NP")
  val VALUETYPE_NESTED_DATE_NF = Value("VALUETYPE_NESTED_DATE_NF")
  val VALUETYPE_NESTED_BOOLEAN = Value("VALUETYPE_NESTED_BOOLEAN")
  val VALUETYPE_XREF_RULELIST = Value("VALUETYPE_XREF_RULELIST")
  val VALUETYPE_WILDCARD = Value("VALUETYPE_WILDCARD")

}

object PitchTypeEnum extends Enumeration {

  type PitchType = Value

  val PITCHTYPE_REACTIVE = Value("PITCHTYPE_REACTIVE")
  val PITCHTYPE_PROACTIVE = Value("PITCHTYPE_PROACTIVE")
  val PITCHTYPE_PREVIOUSLYDECLINED = Value("PITCHTYPE_PREVIOUSLYDECLINED")
  val IHACTION_TYPE_NOT_PITCHED = Value("IHACTION_TYPE_NOT_PITCHED")
  val PITCHTYPE_UNKNOWN = Value("PITCHTYPE_UNKNOWN")

}

object IHActionTypeEnum extends Enumeration {

  type IHActionType = Value

  val IHACTION_TYPE_ACCEPTED = Value("IHACTION_TYPE_ACCEPTED")
  val IHACTION_TYPE_DECLINED = Value("IHACTION_TYPE_DECLINED")
  val IHACTION_TYPE_MAYBE_LATER = Value("IHACTION_TYPE_MAYBE_LATER")
  val IHACTION_TYPE_NOT_PITCHED = Value("IHACTION_TYPE_NOT_PITCHED")
  val IHACTION_TYPE_UNDEFINED = Value("IHACTION_TYPE_UNDEFINED")
  val IHACTION_TYPE_CARD_ACCEPTED = Value("IHACTION_TYPE_CARD_ACCEPTED")
  val IHACTION_TYPE_CARD_DECLINED = Value("IHACTION_TYPE_CARD_DECLINED")

}

import ValueTypeEnum.ValueType
@SerialVersionUID(100L)
class CommonValue(var valueType: ValueType,
                  var rstringValue: String,
                  var int64Value: Int,
                  var float64Value: Float,
                  var listFloat64Value: List[Float],
                  var listRstringValue: List[String],
                  var listIntPairValue: Map[Int, Int],
                  var listInt64Value: List[Int])
    extends Serializable
@SerialVersionUID(1090L)
class SessionCacheType(var sessions: List[String] = List(),
                       var requests: List[String] = List())
    extends Serializable

// OD Change
@SerialVersionUID(100L)
class RealtimeContextDataType(var requestID: String = "",
                              var browserId: String = "",
                              var userId: String = "",
                              var userIdType: String = "",
                              var billingAccountNumber: Int = 0,
                              var accountNumberType: String = "",
                              var consumptionEngine: String = "",
                              var deviceType: String = "",
                              var pageId: String = "",
                              var tenureGroup: String = "",
                              var tcpinst: Int = 0,
                              var manZipCode: String = "",
                              var inboundchatMsg: String = "",
                              var outboundchatMsgList: String = "",
                              var chatStatus: String = "",
                              var chatId: String = "",
                              var addressId: String = "",
                              var callIntentLevel1Code: String = "",
                              var callIntentLevel2Code: String = "",
                              var loginUserRole: String = "",
                              var environment: String = "",
                              var decisionRequestTransactionID: String = "",
                              var acceptedContentIDs: String = "",
                              var declinedContentIDs: String = "",
                              var maybelaterContentIDs: String = "",
                              var originatorSystem: String = "",
                              var originatorSessionID: String = "",
                              var eventType: String = "",
                              var decisionXML: String = "",
                              var consumerType: String = "",
                              var launchPageUrl: String = "",
                              var eligibleOfferList: String = "",
                              var callIntentLevel3CodeList: String = "",
                              var propositionResponseList: String = "",
                              var salesAgentID: String = "",
                              var channel: String = "",
                              var subChannel: String = "",
                              var notPitched: String = "",
                              var cardAccepted: String = "",
                              var cardDeclined: String = "",
                              var originalParallelPath: Int = 0,
                              var originalParallelPath2: Int = 0,
                              var hhid: Int = 0,
                              var inTimeStamp: Instant = null,
) extends Serializable {
  override def toString(): String = {
    return "[ requestID : " + requestID + ", browserId : " + browserId + ", userId : " + userId + ", userIdType : " + userIdType + ", billingAccountNumber : " + billingAccountNumber + ",accountNumberType:" + accountNumberType + ",consumptionEngine:" + consumptionEngine + ",deviceType:" + deviceType + ",pageId:" + pageId + ",tenureGroup:" + tenureGroup + ",inTimeStamp:" + inTimeStamp + ",tcpinst:" + tcpinst + ",manZipCode:" + manZipCode + ",inboundchatMsg:" + inboundchatMsg + ",outboundchatMsgList:" + outboundchatMsgList + ",chatStatus:" + chatStatus + ",chatId:" + chatId + ",addressId:" + addressId + ",callIntentLevel1Code:" + callIntentLevel1Code + ",callIntentLevel2Code:" + callIntentLevel2Code + ",loginUserRole:" + loginUserRole + ",environment:" + environment + ",decisionRequestTransactionID:" + decisionRequestTransactionID + ",acceptedContentIDs:" + acceptedContentIDs + ",declinedContentIDs:" + declinedContentIDs + ",maybelaterContentIDs:" + maybelaterContentIDs + ",originatorSystem:" + originatorSystem + ",originatorSessionID:" + originatorSessionID + ",eventType:" + eventType + ",decisionXML:" + decisionXML + ",consumerType:" + consumerType + ",launchPageUrl:" + launchPageUrl + ",eligibleOfferList:" + eligibleOfferList + ",callIntentLevel3CodeList:" + callIntentLevel3CodeList + ",propositionResponseList:" + propositionResponseList + ",salesAgentID:" + salesAgentID + ",channel:" + channel + ",subChannel:" + subChannel + ",notPitched:" + notPitched + ",cardAccepted:" + cardAccepted + ",cardDeclined:" + cardDeclined + ",originalParallelPath:" + originalParallelPath + ",originalParallelPath2:" + originalParallelPath2 + ",hhid:" + hhid + "]"
  }
}

import TrackerLevelTypeEnum.TrackerLevelType

@SerialVersionUID(100L)
class RequestType5(var trackerLevel: TrackerLevelType,
                   var trackerSequence: Int,
                   var perf: PerfMeasurementsType)
    extends RealtimeContextDataType
@SerialVersionUID(100L)
class PerfMeasurementsType(var perfDebugOn: Boolean,
                           var in: Instant,
                           var tsSMPIn: Instant,
                           var tsSMPOut: Instant,
                           var tsInteractionHistoryIn: Instant,
                           var tsInteractionHistoryOut: Instant,
                           var tsRulesEngine1In: Instant,
                           var tsRulesEngine1Out: Instant,
                           var tsCCSEngineIn: Instant,
                           var tsCCSEngineOut: Instant,
                           var tsRulesEngine2In: Instant,
                           var tsRulesEngine2Out: Instant,
                           var tsInitialActionsIn: Instant,
                           var tsInitialActionsOut: Instant,
                           var tsRulesEngine3In: Instant,
                           var tsRulesEngine3Out: Instant,
                           var tsPitchActionsIn: Instant,
                           var tsPitchActionsOut: Instant,
                           var tsRulesEngine4In: Instant,
                           var tsRulesEngine4Out: Instant,
                           var tsMBSEngineIn: Instant,
                           var tsMBSEngineOut: Instant,
                           var tsRulesEngine5In: Instant,
                           var tsRulesEngine5Out: Instant,
                           var tsRemainingActionsIn: Instant,
                           var tsRemainingActionsOut: Instant,
                           var tsCCPEngineIn: Instant,
                           var tsCCPEngineOut: Instant,
                           var tsOverrideIn: Instant,
                           var tsOverrideOut: Instant,
                           var tsRemActions1In: Instant,
                           var tsRemActions1Out: Instant,
                           var tsRemDynamicTreatmentsIn: Instant,
                           var tsRemDynamicTreatmentsOut: Instant,
                           var tsRemPrioritizingIn: Instant,
                           var tsRemPrioritizingOut: Instant,
                           var tsRemActions2In: Instant,
                           var tsRemActions2Out: Instant,
                           var tsRemMaxGroupsIn: Instant,
                           var tsRemMaxGroupsOut: Instant,
                           var tsRemInitialSortIn: Instant,
                           var tsRemInitialSortOut: Instant,
                           var tsRemCheckDupOffersIn: Instant,
                           var tsRemCheckDupOffersOut: Instant,
                           var tsRemExcludeMaxRTDIn: Instant,
                           var tsRemExcludeMaxRTDOut: Instant,
                           var tsRemMutuallyExclusiveIn: Instant,
                           var tsRemMutuallyExclusiveOut: Instant,
                           var tsRemAllOrNoneIn: Instant,
                           var tsRemAllOrNoneOut: Instant,
                           var tsRemPrioritizeWithinGroupsIn: Instant,
                           var tsRemPrioritizeWithinGroupsOut: Instant,
                           var tsRemCheckExpandableIn: Instant,
                           var tsRemCheckExpandableOut: Instant,
                           var ruleEngineCount: Int)
    extends Serializable

@SerialVersionUID(100L)
class RequestType10(
  var agentID: String,
  var profileAttributeListType: ProfileAttributeListType,
  var dynamicProfileSchemaFrequentIndex: DynamicProfileSchemaFrequentIndex
)
@SerialVersionUID(100L)
class RequestType15(var requestType10: RequestType10,
                    var ih: List[IHDataRecordType])

import PitchTypeEnum.PitchType
import IHActionTypeEnum.IHActionType
@SerialVersionUID(100L)
class IHDataRecordType(
  //  BanType accountId,
  var accountId: Int = 0,
  var ihAsfTmstmpStr: String = "",
  var decisionResultID: String = "",
  var originatorSessionID: String = "",
  var eventType: String = "",
  var acvMinimum: Float = 0,
  var acvFactor: Float = 0,
  var acvReinvestmentAmount: Float = 0,
  var ampuMinimum: Float = 0,
  var ampuFactor: Float = 0,
  var ampuReinvestmentAmount: Float = 0,
  var customDimensionItem: String = "",
  var pendingResponseFlag: Boolean = false,
  var agentRole: String = "",
  var ihAsfTmstmp: Instant = null,
  var category: String = "",
  var displayCategory: String = "",
  var reason: String = "",
  var Mode: String = "",
  var propositionId: String = "",
  //DataSetType propositionDataSetID,
  var propositionDataSetID: String = "",
  var rank: Int = 0,
  var position: Int = 0,
  var parentPosition: Int = 0,
  //PitchType pitchType,
  var pitchType: PitchType = PitchTypeEnum.PITCHTYPE_REACTIVE,
  //IHActionType response,
  var response: IHActionType = IHActionTypeEnum.IHACTION_TYPE_ACCEPTED,
  var positiveFlag: Boolean = false,
  var reinvestment: Boolean = false,
  var offerGroup: String = "",
  var parentOfferGroup: String = "",
  var expanded: Boolean = false,
  var businessGoal: String = "",
  var resp_tmstmp: String = "",
  var typeHdr: String = "",
  var originator: String = "",
  var businessProcessName: String = "",
  var sequenceNumber: String = "",
  var expirationSeconds: String = "",
  var userId: String = "",
  var userGroup: String = "",
  var serviceName: String = "",
  var serviceVersion: String = "",
  //com.att.streams.tdata.mr.common::CallIntentType callIntentLevel1Code,
  var callIntentLevel1Code: String = "",
  //com.att.streams.tdata.mr.common::CallIntentType callIntentLevel2Code,
  var callIntentLevel2Code: String = "",
  //list<com.att.streams.tdata.mr.common::CallIntentType> callIntentLevel3CodeList,
  var callIntentLevel3CodeList: List[String] = List(),
  var dataSource: String = "",
  var agentID: String = "",
  //list<var> productDisconnect,
  var productDisconnect: List[String] = List(),
  var ih_rank: Int = 0,
  var ih_position: Int = 0
) extends Serializable

class ProfileAttributeListType(var attr: List[CommonValue])

class DynamicProfileSchemaFrequentIndex(var initialOfferNamesIndex: Int,
                                        var banIndex: Int,
                                        var AmpuIndex: Int,
                                        var AcvIndex: Int,
                                        var ReinvestAmpuIndex: Int,
                                        var ReinvestAcvIndex: Int,
                                        var AsOfDateIndex: Int,
                                        var CallIntent1: Int,
                                        var CallIntent2: Int,
                                        var CallIntent3: Int,
                                        var AgentRole: Int,
                                        var EligOfferGrp: Int,
                                        var EligOfferCat: Int,
                                        var EligOfferFtrs: Int,
                                        var OfferId: Int,
                                        var OfferFtrs: Int,
                                        var OfferGrp: Int,
                                        var statusIndex: Int,
                                        var PRId: Int,
                                        var PRStatus: Int,
                                        var PRType: Int,
                                        var origSystem: Int,
                                        var origSessionID: Int,
                                        var agentIDIndex: Int,
                                        var ProductDisconnect: Int)
@SerialVersionUID(100L)
class RequestType20(var requestType40: Option[RequestType40])
    extends Serializable

@SerialVersionUID(100L)
class RequestType40(var requestType15: RequestType15,
                    var currentOffers: CurrentPropositionInfoListType,
                    var currentOffersIH: CurrentPropositionInfoListType,
                    //var baseOffers: BaseOfferListType
                    var baseOffers: BaseOfferType,
                    var premiumOffers: CurrentPropositionInfoListType,
                    var actions: ValidActionType,
                    var actionableRuleList: List[String],
                    var ccActionableRuleList: List[String],
                    var mbsActionableRule: String,
                    var mbsResults: MBSResultsType,
                    var dataSetID: List[String],
                    var hasPR: Boolean)
    extends Serializable
@SerialVersionUID(100L)
class MBSResultsType(var mbsACVFactor: Float,
                     var mbsACVMinimum: Float,
                     var mbsAMPUFactor: Float,
                     var mbsAMPUMinimum: Float)
    extends Serializable

@SerialVersionUID(100L)
class ValidActionType(var actionId: String, var actionExecuted: Boolean)
    extends Serializable
@SerialVersionUID(100L)
class BaseOfferType(var propInfo: CurrentPropositionInfoListType,
                    var promoInfo: List[PromoToBaseInfoType])
    extends Serializable

@SerialVersionUID(100L)
class PromoToBaseInfoType(var promoOfferId: String,
                          var baseOfferId: String,
                          var sequence: Int)
    extends Serializable

import OfferGroupExpandableTypeEnum.OfferGroupExpandableType
import OfferGroupDispositionLevelTypeEnum.OfferGroupDispositionLevelType
import OfferGroupClosingDispositionTypeEnum.OfferGroupClosingDispositionType
import OfferDispositionTypeEnum.OfferDispositionType
@SerialVersionUID(100L)
class CurrentPropositionInfoListType(
  var parentPosition: Int,
  var RelativeRankType: Int,
  var position: Int,
  var maxRTD1: Int,
  var maxRTD2: Int,
  var maxOutputFactor1: Float,
  var maxOutputFactor2: Float,
  var position1: Int,
  var position2: Int,
  var position3: Int,
  var position4: Int,
  var expandable1: OfferGroupExpandableType,
  var expandable2: OfferGroupExpandableType,
  var filterByProduct1: Boolean,
  var filterByProduct2: Boolean,
  var mbsProposition: Boolean,
  var mbsPropositionRuleId: String,
  var pitch: PitchType,
  var dispositionLevel1: OfferGroupDispositionLevelType,
  var dispositionLevel2: OfferGroupDispositionLevelType,
  var closingDisposition1: OfferGroupClosingDispositionType,
  var closingDisposition2: OfferGroupClosingDispositionType,
  var fromPR: Boolean,
  var offerDisposition: OfferDispositionType,
  var memberCount1: Int,
  var pickAndChooseCount1: Int,
  var memberCount2: Int,
  var contentID: String,
  var businessGoal: String,
  var historyAction: IHActionType,
  var declineDate: Instant,
  var dynamicTreatmentAttrValues: List[String],
  var isPromo: Boolean,
  var isPremiumGroup1: Boolean,
  var isPremiumGroup2: Boolean,
  var callIntentLevel1Code: String,
  var callIntentLevel2Code: String,
  var incompatibilityBehaviour: String,
  var endDate: Instant,
  var dynamicGroupHeaderValuesl1: List[String],
  var dynamicGroupHeaderValuesl2: List[String],
  var dynamicGroupHeaderValuesl3: List[String],
  var dynamicGroupHeaderValuesl4: List[String],
  var ih_rank: Int,
  var ih_position: Int
) extends Serializable
