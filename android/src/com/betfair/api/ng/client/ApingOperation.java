package com.betfair.api.ng.client;

public enum ApingOperation {
	LISTEVENTTYPES("listEventTypes"), 
	LISTCOMPETITIONS("listCompetitions"),
	LISTTIMERANGES("listTimeRanges"),
	LISTEVENTS("listEvents"),
	LISTMARKETTYPES("listMarketTypes"),
	LISTCOUNTRIES("listCountries"),
	LISTVENUES("listVenues"),
	LISTMARKETCATALOGUE("listMarketCatalogue"),
	LISTMARKETBOOK("listMarketBook"),
	PLACEORDERS("placeOrders"),
	CANCELORDERS("cancelOrders"),
	REPLACEORDERS("replaceOrders"),
	LISTCURRENTORDERS("listCurrentOrders");
	
	private String operationName;
	
	private ApingOperation(String operationName){
		this.operationName = operationName;
	}

	public String getOperationName() {
		return operationName;
	}

}
