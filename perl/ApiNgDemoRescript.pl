#!/usr/bin/perl
#
# Program to do the obvious
#
use REST::Client;
use JSON;
use Data::Dumper;
use DateTime;

my $totalArgs = $#ARGV + 1;
if ($totalArgs != 2 ) { #check two arguments have been passed
	print "wrong number of arguments\n";
	print "usage: perl $0 <appKey> <sessionToken>\n";
	exit;
}

#Get the appKey
my $appKey = $ARGV[0];
print "App key being used: $appKey\n";

#Get the session token
my $sessionToken = $ARGV[1];
print "Session token being used: $sessionToken\n";

my $url="https://api.betfair.com/exchange/betting/rest/v1";
my $apiClient = REST::Client->new({host => $url});
$apiClient -> addHeader("X-Application", "$appKey");
$apiClient -> addHeader("X-Authentication", "$sessionToken");
$apiClient -> addHeader("Content-Type", "application/json");
$apiClient -> addHeader("Accept", "application/json");

#make a call to listEventTypes	
my @eventTypeResults = getEventTypes();

#Extract the result and extract eventTypeId for horse racing.
my $horseRacingEventId = getEventId('Horse Racing', @eventTypeResults);
print "Got eventTypeId: $horseRacingEventId \n";
#Get a merketCatalogue for the next GB WIN horse race market.
my $marketCatalogue = getMarketCatalogueForNextGBWin($horseRacingEventId);
#Extract the marketId from the catalogue
my $marketId = $marketCatalogue -> {marketId};
#Get and print prices for the runner
my $marketBook = getMarketBook($marketId);
printPrices($marketBook);

#SelectionId of the firstRunner in the market
my $selectionId = @{$marketCatalogue -> {runners}}[0] -> {selectionId};
#place failing bet
placeFailingBet($marketId, $selectionId);

sub placeFailingBet{
	my ($marketId, $selectionId) = @_;
	my $placeExecutionReport = callAPI("/placeOrders/",
		{
			marketId => $marketId,
			instructions => [
				{
					selectionId => $selectionId,
					handicap => "0",
					side => "BACK",
					orderType => "LIMIT",
					limitOrder => {
						size => "0.01",
						price => "1.50",
						persistenceType => "LAPSE" 
					}
				}
			],
			customerRef => "test1212121221212"
		}
	);
	print "Got execution report: \n";
	print Dumper($placeExecutionReport);
	
}


sub printPrices{
	my ($marketBook) = @_;
	my @runners = @{$marketBook -> {runners}};
	foreach my $runner (@runners){
		print "SelectionId of runner is: ", $runner -> {selectionId}, "\n";
		if ($runner -> {status} eq "ACTIVE") {
			print Dumper($runner -> {ex});
		} else {
			print "Runner is not active, current status is: ", $runner -> {status},  "\n";
		}
	}
	
}

sub getMarketBook {
	my (@marketIds) = @_;
	return @{
		callAPI("/listMarketBook/",
			{
				marketIds => \@marketIds,
				priceProjection => {
					priceData => ["EX_BEST_OFFERS"]
				} 
			}
		)
	}[0];
}


sub getMarketCatalogueForNextGBWin{
	my (@eventTypeIds) = @_;
	my $now=DateTime->now;
	return @{
		callAPI("/listMarketCatalogue/", 
			{
				filter => {
					eventTypeIds => \@eventTypeIds,
					marketCountries => ["GB"],
					marketTypeCodes => ["WIN"],
					marketStartTime => {
						from => "$now"
					}
				},
				'sort' => 'FIRST_TO_START',
				maxResults => '1',
				marketProjection => ["RUNNER_METADATA"]
			}
		)
	}[0]; #we only asked for one market so grab the first one in the array response

	
}

sub getEventId {
	my ($eventName, @eventTypeResults) = @_;
	for $eventTypeResult (@eventTypeResults) {
		if ($eventTypeResult -> {'eventType'} -> {'name'} eq $eventName) {
			return $eventTypeResult -> {'eventType'} -> {'id'};
		}
	}
}

sub getEventTypes {
	return @{callAPI("/listEventTypes/", {filter => {}})};
}


sub callAPI{
	my($method,$params) = @_;
	print "Calling api-ng method $method with params: \n";
	print Dumper($params);
	$apiClient -> POST($method, encode_json($params));
	if( $apiClient->responseCode() ne '200' ){
		handleError(
			{
				responseCode => $apiClient->responseCode(), 
				content => $apiClient->responseContent()
			}
		);
	}
	my $response = decode_json($apiClient->responseContent());
	return $response;
}

sub handleError {
	my ($error) = @_;
	print Dumper($error);
	exit;
}







