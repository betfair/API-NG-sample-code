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

    function updateHeaders(operationName) {
        return  {
            port: 443,
            hostname: 'api.betfair.com/exchange/betting',
            path: '/rest/v1.0/' + operationName + '/',
            method: 'POST',
            headers: {
                'X-Application' : appkey,
                'Accept': 'application/json',
                'Content-type' : 'application/json',
                'X-Authentication' : ssid
            }
        }
    }

    start();

    // Start by finding the horse race event type id
    function start() {
        findHorseRaceId(updateHeaders('listEventTypes'));
    }

    // Construct request and POST it to API-NG
    function findHorseRaceId(options) {
        console.log("Get horse racing event id");
        var str = '';

        // Define Horse Racing in filter object
        var requestFilters = '{"filter":{}}';

        var req = https.request(options,function (res){
            res.setEncoding(DEFAULT_ENCODING);
            res.on('data', function (chunk) {
                str += chunk;
            });

            res.on('end', function (chunk) {
                // On resposne parse Json and check for errors
                var response = JSON.parse(str);
                handleError(res.statusCode, response);
                // Retrieve id from response and get next available horse race
                getNextAvailableHorseRace(updateHeaders('listMarketCatalogue'), response);
            });
        });

        // Send Json request object
        req.write(requestFilters, DEFAULT_ENCODING);
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

        var req = https.request(options,function (res){

            res.setEncoding(DEFAULT_ENCODING);
            res.on('data', function (chunk) {
                str += chunk;
            });

            res.on('end', function (chunk) {
                var response = JSON.parse(str);
                handleError(res.statusCode, response);
                // Get list of runners that are available in that race
                getListOfRunners(updateHeaders('listMarketBook'), response);
            });
        });

        req.write(requestFilters, DEFAULT_ENCODING);
        req.end();
        req.on('error', function(e) {
            console.log('Problem with request: ' + e.message);
        });
    }

    // Get next horse race based on current date
    function getListOfRunners(options, response) {
        var marketId = retrieveMarketId(response);
        
        console.log("Get list of runners for market Id: " + marketId);
        var str = '';
        
        var requestFilters = '{"marketIds":["' + marketId + '"],"priceProjection":{"priceData":["EX_BEST_OFFERS"],"exBestOfferOverRides":{"bestPricesDepth":2,"rollupModel":"STAKE","rollupLimit":20},"virtualise":false,"rolloverStakes":false},"orderProjection":"ALL","matchProjection":"ROLLED_UP_BY_PRICE"}}';
        
        var req = https.request(options,function (res){
            res.setEncoding(DEFAULT_ENCODING);
            res.on('data', function (chunk) {
                str += chunk;
            });

            res.on('end', function (chunk) {
                var response = JSON.parse(str);
                handleError(res.statusCode, response);
                // Place bet on first runner
                placeBet(updateHeaders('placeOrders'), response, marketId);
            });
        });
        req.write(requestFilters, DEFAULT_ENCODING);
        req.end();
        req.on('error', function(e) {
            console.log('Problem with request: ' + e.message);
        });
    }

    function placeBet(options, response, marketId) {
        var selectionId  = retrieveSelectionId(response);
        
        console.log("Place bet on runner with selection Id: " + selectionId);
        var str = '';
        
        // Invalid price and size, change that to minimum price of 2.0
        var price = '2';
        var size = '0.01';
        var customerRef = new Date().getMilliseconds();
        var requestFilters = '{"marketId":"'+ marketId+'","instructions":[{"selectionId":"' + selectionId + '","handicap":"0","side":"BACK","orderType":"LIMIT","limitOrder":{"size":"' + size + '","price":"' + price + '","persistenceType":"LAPSE"}}],"customerRef":"'+customerRef+'"}}';
        
        var req = https.request(options,function (res){
            res.setEncoding(DEFAULT_ENCODING);
            res.on('data', function (chunk) {
                str += chunk;
            });
            res.on('end', function (chunk) {
                var response = JSON.parse(str);
                handleError(res.statusCode, response);
                console.log(JSON.stringify(response, null, DEFAULT_JSON_FORMAT));
            });
        });
        req.write(requestFilters, DEFAULT_ENCODING);
        req.end();
        req.on('error', function(e) {
            console.log('Problem with request: ' + e.message);
        });
    }

    // get selection id from the response
    function retrieveSelectionId(response) {
        return response[FIRST_INDEX].runners[FIRST_INDEX].selectionId;
    }

    // get event id from the response
    function retrieveEventId(response) {
        for (var i = 0; i<= response.length; i++ ) {
            if (response[i].eventType.name == 'Horse Racing'){
                return response[i].eventType.id;
            }
        }
    }

    // get market id from the response
    function retrieveMarketId(response) {
        return response[FIRST_INDEX].marketId;
    }

    // Handle Api-NG errors, exception details are wrapped within response object
    function handleError(statusCode, response) {
        // check for status code of the response
        if(statusCode != 200) {
            // if response contains exception retrieve details and log
            if (Object.keys(response.detail).length != 0) {
                console.log("Error with request: ");
                console.log(JSON.stringify(response, null, DEFAULT_JSON_FORMAT));
                console.log("Exception Details: ");
                console.log(JSON.stringify(retrieveExceptionDetails(response), null, DEFAULT_JSON_FORMAT));
                console.log("Exception Error Code: ");
                console.log(JSON.stringify(retrieveExceptionErrorCode(response), null, DEFAULT_JSON_FORMAT));
            }
            // if error is thrown stop the app
            process.exit(1);
        }
    }

    // Get exception message out of a response object
    function retrieveExceptionDetails(response) {
        return response.detail.APINGException.errorDetails;
    }

    // Get exception error code out of a response object
    function retrieveExceptionErrorCode(response) {
        return response.detail.APINGException.errorCode;
    }
}
