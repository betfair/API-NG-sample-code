package com.betfair.api.ng.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.betfair.api.ng.objects.Event;
import com.betfair.api.ng.objects.EventType;
import com.betfair.api.ng.objects.MarketBook;
import com.betfair.api.ng.objects.MarketCatalogue;
import com.betfair.api.ng.objects.MarketFilter;
import com.betfair.api.ng.objects.MarketProjection;
import com.betfair.api.ng.objects.MarketSort;
import com.betfair.api.ng.objects.MatchProjection;
import com.betfair.api.ng.objects.OrderProjection;
import com.betfair.api.ng.objects.PlaceExecutionReport;
import com.betfair.api.ng.objects.PlaceInstruction;
import com.betfair.api.ng.objects.PriceProjection;
import com.betfair.api.ng.objects.Result;
import com.betfair.api.ng.objects.TimeRange;
import com.google.gson.reflect.TypeToken;

public class Operations extends Requester {

	private static Operations instance = null;

	private Operations() {
	}

	public static Operations getInstance() {
		if (instance == null) {
			instance = new Operations();
		}
		return instance;
	}

	public List<EventType> listEventTypes(MarketFilter filter) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FILTER, filter);
		List<Result> sportsMenu = gson.fromJson(
				makeRequest(ApingOperation.LISTEVENTTYPES.getOperationName(),
						params).getResult(), new TypeToken<List<Result>>() {
				}.getType());
		List<EventType> eventTypes = new ArrayList<EventType>();
		for (Result result : sportsMenu) {
			eventTypes.add(result.getEventType());
		}
		return eventTypes;
	}

	public List<TimeRange> listTimeRanges(MarketFilter filter,
			String granularity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FILTER, filter);
		params.put(GRANULARITY, granularity);
		List<TimeRange> timeRangeViews = new ArrayList<TimeRange>();
		List<Result> timeRangeMenu = gson.fromJson(
				makeRequest(ApingOperation.LISTTIMERANGES.getOperationName(),
						params).getResult(), new TypeToken<List<Result>>() {
				}.getType());
		for (Result result : timeRangeMenu) {
			timeRangeViews.add(result.getTimeRange());
		}
		return timeRangeViews;
	}

	public List<Event> listEvents(MarketFilter filter) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FILTER, filter);
		List<Event> events = new ArrayList<Event>();
		List<Result> eventMenu = gson.fromJson(
				makeRequest(ApingOperation.LISTEVENTS.getOperationName(),
						params).getResult(), new TypeToken<List<Result>>() {
				}.getType());
		for (Result result : eventMenu) {
			events.add(result.getEvent());
		}
		return events;
	}

	public List<String> listMarketTypes(MarketFilter filter) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FILTER, filter);
		List<String> marketTypes = new ArrayList<String>();
		List<Result> marketTypeMenu = gson.fromJson(
				makeRequest(ApingOperation.LISTMARKETTYPES.getOperationName(),
						params).getResult(), new TypeToken<List<Result>>() {
				}.getType());
		for (Result result : marketTypeMenu) {
			marketTypes.add(result.getMarketType());
		}
		return marketTypes;
	}

	public List<String> listCountries(MarketFilter filter) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FILTER, filter);
		List<String> countries = new ArrayList<String>();
		List<Result> countryMenu = gson.fromJson(
				makeRequest(ApingOperation.LISTCOUNTRIES.getOperationName(),
						params).getResult(), new TypeToken<List<Result>>() {
				}.getType());
		for (Result result : countryMenu) {
			countries.add(result.getCountryCode());
		}
		return countries;
	}

	public List<String> listVenues(MarketFilter filter) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FILTER, filter);
		List<String> venues = new ArrayList<String>();
		List<Result> venueMenu = gson.fromJson(
				makeRequest(ApingOperation.LISTVENUES.getOperationName(),
						params).getResult(), new TypeToken<List<Result>>() {
				}.getType());
		for (Result result : venueMenu) {
			venues.add(result.getVenue());
		}
		return venues;
	}

	public List<MarketCatalogue> listMarketCatalogue(MarketFilter filter,
			Set<MarketProjection> marketProjections, MarketSort sort,
			int maxResult) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FILTER, filter);
		params.put(MARKET_PROJECTION, marketProjections);
		params.put(SORT, sort);
		params.put(MAX_RESULTS, maxResult);
		List<MarketCatalogue> marketCatalogues = gson.fromJson(
				makeRequest(
						ApingOperation.LISTMARKETCATALOGUE.getOperationName(),
						params).getResult(), new TypeToken<List<MarketCatalogue>>() {}.getType());
		/*
		 * List<MarketCatalogue> marketCatalogues = new
		 * ArrayList<MarketCatalogue>(); for (Result result : markets) {
		 * marketCatalogues.add(result.getMarketCatalogues()); }
		 */

		return marketCatalogues;

	}

	public List<MarketBook> listMarketBook(Set<String> marketIds,
			PriceProjection priceProjection, OrderProjection orderProjection,
			MatchProjection matchProjection, String currencyCode) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(MARKET_IDS, marketIds);
		params.put(PRICE_PROJECTION, priceProjection);
		params.put(ORDER_PROJECTION, orderProjection);
		params.put(MATCH_PROJECTION, matchProjection);
		params.put(CURRENCY_CODE, currencyCode);

		return gson.fromJson(
				makeRequest(ApingOperation.LISTMARKETBOOK.getOperationName(),
						params).getResult(), new TypeToken<List<MarketBook>>() {}.getType());
	}

	public PlaceExecutionReport placeOrders(String marketId,
			List<PlaceInstruction> instructions, String customerRef) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(MARKET_ID, marketId);
		params.put(INSTRUCTIONS, instructions);
		params.put(CUSTOMER_REFERENCE, customerRef);
		return gson.fromJson(makeRequest(ApingOperation.PLACEORDERS.getOperationName(),
				params).getResult(), PlaceExecutionReport.class);
	}

}
