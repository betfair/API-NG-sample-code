__author__ = 'annangiv'

import urllib2
import json
import datetime
import sys


"""
make a call API-NG
"""

def callAping(url, request):
    try:
        req = urllib2.Request(url, request, headers)
        response = urllib2.urlopen(req)
        jsonResponse = response.read()
        return jsonResponse

    except urllib2.URLError:
        print 'Oops there is some issue with the request'
        exit()
    except urllib2.HTTPError:
        print 'Oops there is some issue with the request' + urllib2.HTTPError.getcode()
        exit()


"""
calling getEventTypes operation
"""

def getEventTypes():
    endPoint = 'https://api.betfair.com/exchange/betting/rest/v1.0/listEventTypes/'
    event_type_req = '{"filter":{ }}'
    print 'Calling listEventTypes to get event Type ID'
    eventTypesResponse = callAping(endPoint, event_type_req)
    """
    print eventTypesResponse
    """
    eventTypeLoads = json.loads(eventTypesResponse)
    """
    print eventTypeLoads
    """
    return eventTypeLoads


"""
Extraction eventypeId for eventTypeName from evetypeResults
"""


def getEventTypeIDForHorseRacing(eventTypesResult, requestedEventTypeName):
    if(eventTypesResult is not None):
        for event in eventTypesResult:
            eventTypeName = event['eventType']['name']
            if( eventTypeName == requestedEventTypeName):
                return  event['eventType']['id']


"""
Calling marketCatalouge to get marketDetails
"""


def getMarketCatalouge(eventTypeID):
    if(eventTypeID is not None):
        print 'Calling listMarketCatalouge Operation to get MarketID and selectionId'
        endPoint = 'https://api.betfair.com/exchange/betting/rest/v1.0/listMarketCatalogue/'
        now = datetime.datetime.now().strftime('%Y-%m-%dT%H:%M:%SZ')
        market_catalouge_req = '{"filter":{"eventTypeIds":["' + eventTypeID + '"],"marketCountries":["GB"],"marketTypeCodes":["WIN"],'\
                                                                              '"marketStartTime":{"from":"' + now + '"}},"sort":"FIRST_TO_START","maxResults":"1","marketProjection":["RUNNER_METADATA"]}'
        """
        print  market_catalouge_req
        """
        market_catalouge_response = callAping(endPoint, market_catalouge_req)
        """
        print market_catalouge_response
        """
        market_catalouge_loads = json.loads(market_catalouge_response)
        return market_catalouge_loads


def getMarketId(marketCatalougeResult):
    if(marketCatalougeResult is not None):
        for market in marketCatalougeResult:
            return market['marketId']


def getSelectionId(marketCatalougeResult):
    if(marketCatalougeResult is not None):
        for market in marketCatalougeResult:
            return market['runners'][0]['selectionId']


def getMarketBook(marketId):
    if( marketId is not None):
        print 'Calling listMarketBook to read prices for the Market with ID :' + marketId
        market_book_req = '{"marketIds":["' + marketId + '"],"priceProjection":{"priceData":["EX_BEST_OFFERS"]}}'
        """
        print  market_book_req
        """
        endPoint = 'https://api.betfair.com/exchange/betting/rest/v1.0/listMarketBook/'

        market_book_response = callAping(endPoint, market_book_req)
        """
        print market_book_response
        """
        market_book_loads = json.loads(market_book_response)
        return market_book_loads


def printPriceInfo(market_book_result):
    print 'Please find Best three available prices for the runners'
    for marketBook in market_book_result:
        try:
            runners = marketBook['runners']
            for runner in runners:
                print 'Selection id is ' + str(runner['selectionId'])
                if (runner['status'] == 'ACTIVE'):
                    print 'Available to back price :' + str(runner['ex']['availableToBack'])
                    print 'Available to lay price :' + str(runner['ex']['availableToLay'])
                else:
                    print 'This runner is not active'
        except:
            print 'No runners available for this market'


def placeBet(marketId, selectionId):
    if( marketId is not None and selectionId is not None):
        print 'Calling placeOrder for marketId :' + marketId + ' with selection id :' + str(selectionId)
        place_order_Req = '{"marketId":"' + marketId + '","instructions":'\
                                                       '[{"selectionId":"' + str(
            selectionId) + '","handicap":"0","side":"BACK","orderType":"LIMIT","limitOrder":{"size":"0.01","price":"1.50","persistenceType":"LAPSE"}}],"customerRef":"test12121212121"}'
        endPoint = 'https://beta-api.betfair.com/rest/v1.0/placeOrders/'
        """
        print place_order_Req
        """
        place_order_Response = callAping(endPoint, place_order_Req)
        place_order_load = json.loads(place_order_Response)
        print 'Place order status is ' + place_order_load['status']
        """
        print 'Place order error status is ' + place_order_load['errorCode']
        """
        print 'Reason for Place order failure is ' + place_order_load['instructionReports'][0]['errorCode']
        """
        print place_order_Response
        """


"""
headers = { 'X-Application' : 'xxxxxxx', 'X-Authentication' : 'xxxxxxxxx' ,'content-type' : 'application/json' , 'Accept': 'application/json'}
"""

args = len(sys.argv)

if ( args < 3):
    print 'Please provide Application key and session token'
    appKey = raw_input('Enter your application key :')
    sessionToken = raw_input('Enter your session Token/SSOID :')
    print 'Thanks for the input provided'
else:
    appKey = sys.argv[1]
    sessionToken = sys.argv[2]

headers = {'X-Application': appKey, 'X-Authentication': sessionToken, 'content-type': 'application/json',
           'accept': 'application/json'}

eventTypesResult = getEventTypes()
horseRacingEventTypeID = getEventTypeIDForHorseRacing(eventTypesResult, 'Horse Racing')

print 'Eventype Id for Horse Racing is :' + str(horseRacingEventTypeID)

marketCatalougeResult = getMarketCatalouge(horseRacingEventTypeID)
marketid = getMarketId(marketCatalougeResult)
runnerId = getSelectionId(marketCatalougeResult)

print marketid
print runnerId

market_book_result = getMarketBook(marketid)
printPriceInfo(market_book_result)

placeBet(marketid, runnerId)



