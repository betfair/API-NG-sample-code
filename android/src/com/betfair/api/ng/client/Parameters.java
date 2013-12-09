package com.betfair.api.ng.client;

import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface Parameters {
	final String FILTER = "filter";
	final String LOCALE = "locale";
	 final String GRANULARITY = "granularity";
	 final String CURRENCY_CODE = "currencyCode";
	 final String SORT = "sort";
	 final String MAX_RESULTS = "maxResults"; 
	 final String MARKET_IDS = "marketIds";
	 final String MARKET_ID = "marketId";
	 final String MARKET_PROJECTION = "marketProjection";
	 final String MATCH_PROJECTION = "matchProjection";
	 final String ORDER_PROJECTION = "orderProjection";
	 final String PRICE_PROJECTION = "priceProjection";
	 final String INSTRUCTIONS = "instructions";
	 final String CUSTOMER_REFERENCE = "customerRef";
	 final String BET_IDS = "betIds";
	 final String PLACED_DATE_RANGE = "placedDateRange";
	 final String ORDER_BY = "orderBy";
	 final String SORT_DIR = "sortDir";
	 final String FROM_RECORD = "fromRecord";
	 final String RECORD_COUNT = "recordCount";
	 final String locale = Locale.getDefault().toString();
	 final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();


}
