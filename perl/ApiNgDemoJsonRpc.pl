#!/usr/bin/perl
#
# Program to do the obvious
#
use JSON::RPC::LWP;
use HTTP::Headers;
use Data::Dumper;
use JSON::RPC::Common::Procedure::Return;
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

#prepare the json-rpc client
my $apiClient = JSON::RPC::LWP->new;
my $headers =  HTTP::Headers->new(
       "X-Application" => "$appKey",
       "X-Authentication" => "$sessionToken");
$apiClient->ua->default_headers($headers);
my $url="https://api.betfair.com/exchange/betting/json-rpc/v1";

#make a call to listEventTypes	
my @EventTypeResults = getEventTypes();


#Extract the result and extract eventTypeId for horse racing.
my $horseRacingEventId = getEventId('Horse Racing', @EventTypeResults);
print "Got eventTypeId: $horseRacingEventId \n";
#Get a merketCatalogue for the next GB WIN horse race market.
my $MarketCatalogue = getMarketCatalogueForNextGBWin($horseRacingEventId);
#Extract the marketId from the catalogue
my $marketId = $MarketCatalogue -> {marketId};
#Get and print prices for the runner
my $marketBook = getMarketBook($marketId);
printPrices($marketBook);

#SelectionId of the firstRunner in the market
my $selectionId = @{$MarketCatalogue -> {runners}}[0] -> {selectionId};
#place failing bet
placeFailingBet($marketId, $selectionId);


sub placeFailingBet{
	my ($marketId, $selectionId) = @_;
	my $PlaceExecutionReport = callAPI("SportsAPING/v1/placeOrders",
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
	print Dumper($PlaceExecutionReport);
	
}

sub printPrices{
	my ($MarketBook) = @_;
	my @Runners = @{$MarketBook -> {runners}};
	foreach my $Runner (@Runners){
		print "SelectionId of runner is: ", $Runners -> {selectionId}, "\n";
		if ($Runners -> {status} eq "ACTIVE") {
			print Dumper($Runners -> {ex});
		} else {
			print "Runner is not active, current status is: ", $Runner -> {status},  "\n";
		}
	}
	
}

sub getMarketBook {
	my (@marketIds) = @_;
	return @{
		callAPI("SportsAPING/v1/listMarketBook",
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
	my $now=DateTime->now; #creates the current date time in UTC
	return @{
		callAPI("SportsAPING/v1/listMarketCatalogue", 
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
	my ($eventName, @EventTypeResults) = @_;
	for $EventTypeResult (@EventTypeResults) {
		if ($EventTypeResult -> {eventType} -> {name} eq $eventName) {
			return $EventTypeResult -> {eventType} -> {id};
		}
	}
}

sub getEventTypes {
	return @{callAPI("SportsAPING/v1/listEventTypes",{filter => {}})};
}

sub callAPI {
	my($method,$params) = @_;
	print "Calling api-ng method $method with params:\n";
	print Dumper($params);
	my $call = $apiClient->call($url, $method, $params);
	if ($call->has_error){
			handleError($call->error);
	}
	
	return $call->result;
}

sub handleError {
	my ($error) = @_;
	print Dumper($error);
	exit;
}
