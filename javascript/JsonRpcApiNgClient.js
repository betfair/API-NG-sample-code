var https = require('https');

// Start the app
DemoApiNgClient();

// Main class that contains all operations
function DemoApiNgClient(){

// Retrieve command line args 
var myArgs = process.argv.slice(2);
// App key
var appkey = myArgs[0];
// Session token
var ssid = myArgs[1];
var FIRST_INDEX = 0;
var DEFAULT_ENCODING = 'utf-8';
var DEFAULT_JSON_FORMAT = '\t';

var options = {
    hostname: 'api.betfair.com/exchange/betting',
    port: 443,
    path: '/json-rpc/v1',
    method: 'POST',
    headers: {
        'X-Application' : appkey,
        'Accept': 'application/json',
        'Content-type' : 'application/json',
        'X-Authentication' : ssid
        }
    }

    start();

	// Start from finding the horse race event type id
    function start() {
        findHorseRaceId(options);
    }
    
	// Construct request and POST it to API-NG
    function findHorseRaceId(options) {
		console.log("Get horse racing event id");
		// Define Horse Racing in filter object
        var requestFilters = '{"filter":{}}';
        var jsonRequest = constructJsonRpcRequest('listEventTypes', requestFilters );
        var str = '';
        var req = https.request(options,function (res){
            res.setEncoding(DEFAULT_ENCODING);
            res.on('data', function (chunk) {
                str += chunk;
            });

            res.on('end', function (chunk) {
				// On resposne parse Json and check for errors
                var response = JSON.parse(str);
                handleError(response);
                // Retrieve id from response and get next available horse race
                getNextAvailableHorseRace(options, response);
            });
            
        });
        // Send Json request object
        req.write(jsonRequest, DEFAULT_ENCODING);
        req.end();

        req.on('error', function(e) {
            console.log('Problem with request: ' + e.message);
        }); 
    }

	// Get next horse race based on current date
    function getNextAvailableHorseRace(options, response) {
        // Retrieve event id from previous response
        var eventId = retrieveEventId(response);
        var jsonDate = new Date().toJSON();
        console.log("Get next available horse race starting from date: " + jsonDate);
        var str = '';
        var requestFilters = '{"filter":{"eventTypeIds": [' + eventId + '],"marketCountries":["GB"],"marketTypeCodes":["WIN"],"marketStartTime":{"from":"'+jsonDate+'"}},"sort":"FIRST_TO_START","maxResults":"1","marketProjection":["RUNNER_DESCRIPTION"]}}';
        var jsonRequest = constructJsonRpcRequest('listMarketCatalogue', requestFilters );
		
        var req = https.request(options,function (res){
            res.setEncoding(DEFAULT_ENCODING);
            res.on('data', function (chunk) {
                str += chunk;
            });

            res.on('end', function (chunk) {
                var response = JSON.parse(str);
                handleError(response);
                // Get list of runners that are available in that race
                getListOfRunners(options, response);
            });
        });
        req.write(jsonRequest, DEFAULT_ENCODING);
        req.end();
        req.on('error', function(e) {
            console.log('Problem with request: ' + e.message);
        });
    }

    function getListOfRunners(options, response) {
        var marketId = retrieveMarketId(response);
        console.log("Get list of runners for market Id: " + marketId);
        var requestFilters = '{"marketIds":["' + marketId + '"],"priceProjection":{"priceData":["EX_BEST_OFFERS"],"exBestOfferOverRides":{"bestPricesDepth":2,"rollupModel":"STAKE","rollupLimit":20},"virtualise":false,"rolloverStakes":false},"orderProjection":"ALL","matchProjection":"ROLLED_UP_BY_PRICE"}}';
        var jsonRequest = constructJsonRpcRequest('listMarketBook', requestFilters );
        var str = '';
        var req = https.request(options,function (res){
            res.setEncoding(DEFAULT_ENCODING);
            res.on('data', function (chunk) {
                str += chunk;
            });

            res.on('end', function (chunk) {
                var response = JSON.parse(str);
                handleError(response);
                // Place bet on first runner
                placeBet(options, response, marketId);
            });
        });
        req.write(jsonRequest, DEFAULT_ENCODING);
        req.end();
        req.on('error', function(e) {
            console.log('Problem with request: ' + e.message);
            return;
        });
    }

    function placeBet(options, response, marketId) {
        var str = '';
        var selectionId  = retrieveSelectionId(response);
        // Invalid price and size, change that to minimum price of 2.0
        var price = '2';
        var size = '0.01';
        var customerRef = new Date().getMilliseconds();
        console.log("Place bet on runner with selection Id: " + selectionId);
        var requestFilters = '{"marketId":"'+ marketId+'","instructions":[{"selectionId":"' + selectionId + '","handicap":"0","side":"BACK","orderType":"LIMIT","limitOrder":{"size":"' + size + '","price":"' + price + '","persistenceType":"LAPSE"}}],"customerRef":"'+customerRef+'"}}';
        var jsonRequest = constructJsonRpcRequest('placeOrders', requestFilters );
        var req = https.request(options,function (res){
            res.setEncoding(DEFAULT_ENCODING);
            res.on('data', function (chunk) {
                str += chunk;
            });
            res.on('end', function (chunk) {
				var response = JSON.parse(str);
				handleError(response);
                console.log(JSON.stringify(response, null, DEFAULT_JSON_FORMAT));
            });
        });
        req.write(jsonRequest, DEFAULT_ENCODING);
        req.end();
        req.on('error', function(e) {
            console.log('Problem with request: ' + e.message);
        });
    }

    // get event id from the response
    function retrieveEventId(response) {
        for (var i = 0; i<= Object.keys(response.result).length; i++ ) {
            if (response.result[i].eventType.name == 'Horse Racing'){
                return response.result[i].eventType.id;
            }
        }
    }

    // get selection id from the response
    function retrieveSelectionId(response) {
        return response.result[FIRST_INDEX].runners[FIRST_INDEX].selectionId;
    }

    // get market id from the response
    function retrieveMarketId(response) {
        return response.result[FIRST_INDEX].marketId;
    }


    function constructJsonRpcRequest(operation, params) {
        return '{"jsonrpc":"2.0","method":"SportsAPING/v1.0/' +  operation + '", "params": ' + params + ', "id": 1}';
    }
    
    // Handle Api-NG errors, exception details are wrapped within response object 
    function handleError(response) {
        // check for errors in response body, we can't check for status code as jsonrpc returns always 200
		if (response.error != null) {
            // if error in response contains only two fields it means that there is no detailed message of exception thrown from API-NG
            if (Object.keys(response.error).length > 2) {
                console.log("Error with request!!");
                console.log(JSON.stringify(response, null, DEFAULT_JSON_FORMAT));
                console.log("Exception Details: ");
                console.log(JSON.stringify(retrieveExceptionDetails(response), null, DEFAULT_JSON_FORMAT));
            }
			process.exit(1);
		}
	}
	
	// Get exception message out of a response object
	function retrieveExceptionDetails(response) {
		return response.error.data.APINGException;
	}
}
