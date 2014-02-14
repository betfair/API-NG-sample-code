#!/usr/bin/env python3

import urllib
import urllib.request
import urllib.error
import json
import datetime
import sys

"""
make a call API-NG
"""

def callAping(jsonrpc_req):
    try:
        req = urllib.request.Request(url, jsonrpc_req.encode('utf-8'), headers)
        response = urllib.request.urlopen(req)
        jsonResponse = response.read()
        return jsonResponse.decode('utf-8')
    except urllib.error.URLError as e:
        print (e.reason) 
        print ('Oops no service available at ' + str(url))
        exit()
    except urllib.error.HTTPError:
        print ('Oops not a valid operation from the service ' + str(url))
        exit()


"""
calling getEventTypes operation
"""

def getEventTypes():
    event_type_req = '{"jsonrpc": "2.0", "method": "SportsAPING/v1.0/listEventTypes", "params": {"filter":{ }}, "id": 1}'
    print ('Calling listEventTypes to get event Type ID')
    eventTypesResponse = callAping(event_type_req)
    eventTypeLoads = json.loads(eventTypesResponse)
    """
print(eventTypeLoads)
"""

    try:
        eventTypeResults = eventTypeLoads['result']
        return eventTypeResults
    except:
        print ('Exception from API-NG' + str(eventTypeLoads['error']))
        exit()


"""
Extraction eventypeId for eventTypeName from evetypeResults
"""

def getEventTypeIDForEventTypeName(eventTypesResult, requestedEventTypeName):
    if(eventTypesResult is not None):
        for event in eventTypesResult:
            eventTypeName = event['eventType']['name']
            if( eventTypeName == requestedEventTypeName):
                return event['eventType']['id']
    else:
        print ('Oops there is an issue with the input')
        exit()


"""
Calling marketCatalouge to get marketDetails
"""

def getMarketCatalogueForNextGBWin(eventTypeID):
    if (eventTypeID is not None):
        print ('Calling listMarketCatalouge Operation to get MarketID and selectionId')
        now = datetime.datetime.now().strftime('%Y-%m-%dT%H:%M:%SZ')
        market_catalogue_req = '{"jsonrpc": "2.0", "method": "SportsAPING/v1.0/listMarketCatalogue", "params": {"filter":{"eventTypeIds":["' + eventTypeID + '"],"marketCountries":["GB"],"marketTypeCodes":["WIN"],'\
                                                                                                                                                             '"marketStartTime":{"from":"' + now + '"}},"sort":"FIRST_TO_START","maxResults":"1","marketProjection":["RUNNER_METADATA"]}, "id": 1}'
        """
print(market_catalogue_req)
"""
        market_catalogue_response = callAping(market_catalogue_req)
        """
print(market_catalogue_response)
"""
        market_catalouge_loads = json.loads(market_catalogue_response)
        try:
            market_catalouge_results = market_catalouge_loads['result']
            return market_catalouge_results
        except:
            print ('Exception from API-NG' + str(market_catalouge_results['error']))
            exit()


def getMarketId(marketCatalogueResult):
    if( marketCatalogueResult is not None):
        for market in marketCatalogueResult:
            return market['marketId']


def getSelectionId(marketCatalogueResult):
    if(marketCatalogueResult is not None):
        for market in marketCatalogueResult:
            return market['runners'][0]['selectionId']


def getMarketBookBestOffers(marketId):
    print ('Calling listMarketBook to read prices for the Market with ID :' + marketId)
    market_book_req = '{"jsonrpc": "2.0", "method": "SportsAPING/v1.0/listMarketBook", "params": {"marketIds":["' + marketId + '"],"priceProjection":{"priceData":["EX_BEST_OFFERS"]}}, "id": 1}'
    """
print(market_book_req)
"""
    market_book_response = callAping(market_book_req)
    """
print(market_book_response)
"""
    market_book_loads = json.loads(market_book_response)
    try:
        market_book_result = market_book_loads['result']
        return market_book_result
    except:
        print ('Exception from API-NG' + str(market_book_result['error']))
        exit()


def printPriceInfo(market_book_result):
    if(market_book_result is not None):
        print ('Please find Best three available prices for the runners')
        for marketBook in market_book_result:
            runners = marketBook['runners']
            for runner in runners:
                print ('Selection id is ' + str(runner['selectionId']))
                if (runner['status'] == 'ACTIVE'):
                    print ('Available to back price :' + str(runner['ex']['availableToBack']))
                    print ('Available to lay price :' + str(runner['ex']['availableToLay']))
                else:
                    print ('This runner is not active')


def placeFailingBet(marketId, selectionId):
    if( marketId is not None and selectionId is not None):
        print ('Calling placeOrder for marketId :' + marketId + ' with selection id :' + str(selectionId))
        place_order_Req = '{"jsonrpc": "2.0", "method": "SportsAPING/v1.0/placeOrders", "params": {"marketId":"' + marketId + '","instructions":'\
                                                                                                                              '[{"selectionId":"' + str(
            selectionId) + '","handicap":"0","side":"BACK","orderType":"LIMIT","limitOrder":{"size":"0.01","price":"1.50","persistenceType":"LAPSE"}}],"customerRef":"test12121212121"}, "id": 1}'
        """
print(place_order_Req)
"""
        place_order_Response = callAping(place_order_Req)
        place_order_load = json.loads(place_order_Response)
        try:
            place_order_result = place_order_load['result']
            print ('Place order status is ' + place_order_result['status'])
            """
print('Place order error status is ' + place_order_result['errorCode'])
"""
            print ('Reason for Place order failure is ' + place_order_result['instructionReports'][0]['errorCode'])
        except:
            print ('Exception from API-NG' + str(place_order_result['error']))
        """
print(place_order_Response)
"""


url = "https://api.betfair.com/exchange/betting/json-rpc/v1"

"""
headers = { 'X-Application' : 'xxxxxx', 'X-Authentication' : 'xxxxx' ,'content-type' : 'application/json' }
"""

args = len(sys.argv)

if ( args < 3):
    print ('Please provide Application key and session token')
    appKey = input('Enter your application key :')
    sessionToken = input('Enter your session Token/SSOID :')
    print ('Thanks for the input provided')
else:
    appKey = sys.argv[1]
    sessionToken = sys.argv[2]

headers = {'X-Application': appKey, 'X-Authentication': sessionToken, 'content-type': 'application/json'}

eventTypesResult = getEventTypes()
horseRacingEventTypeID = getEventTypeIDForEventTypeName(eventTypesResult, 'Horse Racing')

print ('Eventype Id for Horse Racing is :' + str(horseRacingEventTypeID))

marketCatalogueResult = getMarketCatalogueForNextGBWin(horseRacingEventTypeID)
marketid = getMarketId(marketCatalogueResult)
runnerId = getSelectionId(marketCatalogueResult)
"""
print(marketid)
print(runnerId)
"""
market_book_result = getMarketBookBestOffers(marketid)
printPriceInfo(market_book_result)

placeFailingBet(marketid, runnerId)