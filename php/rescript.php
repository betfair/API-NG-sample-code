<?php

if (count($argv) != 3) {
    echo 'usage: php -f rescript.php AppKey SessionToken';
    exit(-1);
}


$APP_KEY = $argv[1];
$SESSION_TOKEN = $argv[2];

// Setting DEBUG to true will output all request / responses to api-ng.
$DEBUG = False;

echo "1. Get all Event Types....\n";
$allEventTypes = getAllEventTypes($APP_KEY, $SESSION_TOKEN);

echo "\n2. Extract Event Type Id for Horse Racing....\n";
$horseRacingEventTypeId = extractHorseRacingEventTypeId($allEventTypes);

echo "\n3. EventTypeId for Horse Racing is: $horseRacingEventTypeId \n";
//
echo "\n4. Get next horse racing market in the UK....\n";
$nextHorseRacingMarket = getNextUkHorseRacingMarket($APP_KEY, $SESSION_TOKEN, $horseRacingEventTypeId);

echo "\n5. Print static marketId, name and runners....\n";
printMarketIdAndRunners($nextHorseRacingMarket);

echo "\n6. Get volatile info for Market including best 3 exchange prices available...\n";
$marketBook = getMarketBook($APP_KEY, $SESSION_TOKEN, $nextHorseRacingMarket->marketId);

echo "\n7. Print volatile price data along with static runner info....\n";
printMarketIdRunnersAndPrices($nextHorseRacingMarket, $marketBook);

echo "\n\n8. Place a bet below minimum stake to prevent the bet actually being placed....\n";
$betResult = placeBet($APP_KEY, $SESSION_TOKEN, $nextHorseRacingMarket->marketId, $nextHorseRacingMarket->runners[0]->selectionId);

echo "\n9. Print result of bet....\n\n";
printBetResult($betResult);

function getAllEventTypes($appKey, $sessionToken)
{

    $jsonResponse = sportsApingRequest($appKey, $sessionToken, 'listEventTypes', '{"filter":{}}');

    return $jsonResponse;

}

function extractHorseRacingEventTypeId($allEventTypes)
{
    foreach ($allEventTypes as $eventType) {
        if ($eventType->eventType->name == 'Horse Racing') {
            return $eventType->eventType->id;
        }
    }

}

function getNextUkHorseRacingMarket($appKey, $sessionToken, $horseRacingEventTypeId)
{

    $params = '{"filter":{"eventTypeIds":["' . $horseRacingEventTypeId . '"],
              "marketCountries":["GB"],
              "marketTypeCodes":["WIN"],
              "marketStartTime":{"from":"' . date('c') . '"}},
              "sort":"FIRST_TO_START",
              "maxResults":"1",
              "marketProjection":["RUNNER_DESCRIPTION"]}';

    $jsonResponse = sportsApingRequest($appKey, $sessionToken, 'listMarketCatalogue', $params);

    return $jsonResponse[0];
}

function printMarketIdAndRunners($nextHorseRacingMarket)
{

    echo "MarketId: " . $nextHorseRacingMarket->marketId . "\n";
    echo "MarketName: " . $nextHorseRacingMarket->marketName . "\n\n";

    foreach ($nextHorseRacingMarket->runners as $runner) {
        echo "SelectionId: " . $runner->selectionId . " RunnerName: " . $runner->runnerName . "\n";
    }

}

function getMarketBook($appKey, $sessionToken, $marketId)
{
    $params = '{"marketIds":["' . $marketId . '"], "priceProjection":{"priceData":["EX_BEST_OFFERS"]}}';

    $jsonResponse = sportsApingRequest($appKey, $sessionToken, 'listMarketBook', $params);

    return $jsonResponse[0];
}

function printMarketIdRunnersAndPrices($nextHorseRacingMarket, $marketBook)
{

    function printAvailablePrices($selectionId, $marketBook)
    {

        // Get selection
        foreach ($marketBook->runners as $runner)
            if ($runner->selectionId == $selectionId) break;

        echo "\nAvailable to Back: \n";
        foreach ($runner->ex->availableToBack as $availableToBack)
            echo $availableToBack->size . "@" . $availableToBack->price . " | ";

        echo "\n\nAvailable to Lay: \n";
        foreach ($runner->ex->availableToLay as $availableToLay)
            echo $availableToLay->size . "@" . $availableToLay->price . " | ";

    }


    echo "MarketId: " . $nextHorseRacingMarket->marketId . "\n";
    echo "MarketName: " . $nextHorseRacingMarket->marketName;

    foreach ($nextHorseRacingMarket->runners as $runner) {
        echo "\n\n\n===============================================================================\n";

        echo "SelectionId: " . $runner->selectionId . " RunnerName: " . $runner->runnerName . "\n";
        echo printAvailablePrices($runner->selectionId, $marketBook);
    }
}

function placeBet($appKey, $sessionToken, $marketId, $selectionId)
{

    $params = '{"marketId":"' . $marketId . '",
                "instructions":
                     [{"selectionId":"' . $selectionId . '",
                       "handicap":"0",
                       "side":"BACK",
                       "orderType":
                       "LIMIT",
                       "limitOrder":{"size":"1",
                                    "price":"1000",
                                    "persistenceType":"LAPSE"}
                       }], "customerRef":"fsdf"}';

    $jsonResponse = sportsApingRequest($appKey, $sessionToken, 'placeOrders', $params);

    return $jsonResponse;

}

function printBetResult($betResult)
{
    echo "Status: " . $betResult->status;

    if ($betResult->status == 'FAILURE') {
        echo "\nErrorCode: " . $betResult->errorCode;
        echo "\n\nInstruction Status: " . $betResult->instructionReports[0]->status;
        echo "\nInstruction ErrorCode: " . $betResult->instructionReports[0]->errorCode;
    } else
        echo "Warning!!! Bet placement succeeded !!!";
}


function sportsApingRequest($appKey, $sessionToken, $operation, $params)
{
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, "https://api.betfair.com/exchange/betting/rest/v1/$operation/");
    curl_setopt($ch, CURLOPT_POST, 1);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array(
        'X-Application: ' . $appKey,
        'X-Authentication: ' . $sessionToken,
        'Accept: application/json',
        'Content-Type: application/json'
    ));

    curl_setopt($ch, CURLOPT_POSTFIELDS, $params);

    debug('Post Data: ' . $params);
    $response = json_decode(curl_exec($ch));
    debug('Response: ' . json_encode($response));

    $http_status = curl_getinfo($ch, CURLINFO_HTTP_CODE);
    curl_close($ch);

    if ($http_status == 200) {
        return $response;
    } else {
        echo 'Call to api-ng failed: ' . "\n";
        echo  'Response: ' . json_encode($response);
        exit(-1);
    }


}


function debug($debugString)
{
    global $DEBUG;
    if ($DEBUG)
        echo $debugString . "\n\n";
}


?>



