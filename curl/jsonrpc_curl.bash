#! /bin/bash

# This script list available horse races, selects random horse race, random runner and places a bet, all operations are executed through curl requests, 
# uses json_rpc


# Initialise variables, script takes app key and session token as arguments
APP_KEY=$1
SESSION_TOKEN=$2

HOST=https://api.betfair.com/exchange/betting
PORT=443

# List all event types, providing app key and session token
function list_all_event_types() {
 echo "LIST ALL EVENT TYPES"

 OUT=`curl -s -X POST --header "Accept: application/json" --header "Content-Type: application/json" --header "X-Application: $APP_KEY" --header "X-Authentication:   $SESSION_TOKEN" --data "[{ \"jsonrpc\": \"2.0\", \"method\": \"SportsAPING/v1.0/listEventTypes\", \"params\": {\"filter\":{}}, \"id\": 1}] " $HOST:$PORT/json-rpc`
 echo $OUT | json_reformat
}


# List next available horse races, id of a event type is 7, method returns first horse race,this can be changed in MAX_RESULTS variable
function list_available_horse_races() {
 echo "LIST AVAILABLE HORSE RACES"

 # number of results returned, can be increased
 local MAX_RESULTS=1
 # from the response we can see that Horse Racing eventTypeId is 7
 local HORSE_RACING_EVENT_TYPE_ID=7
 # get date now and format it
 local DATE=`date +"%Y-%m-%dT%TZ"`

 OUT=`curl -s -X POST --header "Accept: application/json" --header "Content-Type: application/json" --header "X-Application: $APP_KEY" --header "X-Authentication:   $SESSION_TOKEN"  --data "[{ \"jsonrpc\": \"2.0\", \"method\": \"SportsAPING/v1.0/listMarketCatalogue\", \"params\": {\"filter\":{\"eventTypeIds\":[$HORSE_RACING_EVENT_TYPE_ID], \"marketCountries\" :[\"GB\"],\"marketTypeCodes\" :[\"WIN\"],\"marketStartTime\":{\"from\":\"$DATE\"}},\"sort\":\"FIRST_TO_START\",\"maxResults\":\"$MAX_RESULTS\",\"marketProjection\":[\"RUNNER_DESCRIPTION\"]}, \"id\": 1}]" $HOST:$PORT/json-rpc/v1`
 echo $OUT | json_reformat
}


# List list of runners given market id of selected race
function list_runners() {
 echo "GET LIST OF RUNNERS FOR THAT MARKET"

 # extract market id from prev. response
 MARKET_ID=`echo $OUT | cut -f10 -d "\""`
 
 OUT=`curl -s -X POST --header "Accept: application/json" --header "Content-Type: application/json" --header "X-Application: $APP_KEY" --header "X-Authentication:   $SESSION_TOKEN"  --data "[{ \"jsonrpc\": \"2.0\", \"method\": \"SportsAPING/v1.0/listMarketBook\", \"params\": {\"marketIds\":[\"$MARKET_ID\"],\"priceProjection\":{\"priceData\":[\"EX_BEST_OFFERS\"],\"exBestOfferOverRides\":{\"bestPricesDepth\":2,\"rollupModel\":\"STAKE\",\"rollupLimit\":20},\"virtualise\":false,\"rolloverStakes\":false},\"orderProjection\":\"ALL\",\"matchProjection\":\"ROLLED_UP_BY_PRICE\"}, \"id\": 1}]" $HOST:$PORT/json-rpc/v1`
 echo $OUT | json_reformat
}


# Place bet, given bet order details
function place_bet() {
 # get list of selection ids from response
 local SELECTION_IDS_LIST=`echo $OUT | json_reformat | grep  'selectionId' | cut -f2 -d":"` 
 # pick first selection id
 local SELECTION_ID=`echo $SELECTION_IDS_LIST | grep [[:digit:]] | cut -f1 -d","`
 local PRICE=2
 local SIZE=0.01
 local SIDE=BACK
 # unique reference for that transaction
 local REFERENCE=`date +"%Y-%m-%dT%TZ"`

 echo "Placing bet on : Selection Id: $SELECTION_ID, Side: $SIDE, Price: $PRICE, Size: $SIZE"

 OUT=`curl -s -X POST --header "Accept: application/json" --header "Content-Type: application/json" --header "X-Application: $APP_KEY" --header "X-Authentication:	$SESSION_TOKEN" --data "[{ \"jsonrpc\": \"2.0\", \"method\": \"SportsAPING/v1.0/placeOrders\", \"params\": {\"marketId\":\"$MARKET_ID\",\"instructions\":[{\"selectionId\":\"$SELECTION_ID\",\"handicap\":\"0\",\"side\":\"$SIDE\",\"orderType\":\"LIMIT\",\"limitOrder\":{\"size\":\"$SIZE\",\"price\":\"$PRICE\",\"persistenceType\":\"LAPSE\"}}],\"customerRef\":\"$REFERENCE\"}, \"id\": 1}]" $HOST:$PORT/json-rpc/v1`
 echo $OUT | json_reformat
 # operation should return error as bet size is too small
 handleError $OUT
}

# function retrieves error codes from the response
function handleError() {
 local RESPONSE=$1
 local ERROR=`echo $RESPONSE | json_reformat | grep "errorCode" | cut -f2 -d":"`
 local ERROR_CODE_EXECUTION_REPORT=`echo $ERROR | cut -f1 -d","`
 local ERROR_CODE_INSTRUCTION_REPORT=`echo $ERROR | cut -f2 -d","`

 echo "Response Error: " $ERROR_CODE_EXECUTION_REPORT
 echo "Error Description : " $ERROR_CODE_INSTRUCTION_REPORT
}


# Chekc if app key and session token are set
if [ "$APP_KEY" == "" ]; then
	read -p "Please provide application key: " APP_KEY
fi
if [ "$SESSION_TOKEN" == "" ]; then
	read -p "Please provide session token: " SESSION_TOKEN
fi


list_all_event_types
list_available_horse_races
list_runners
place_bet


