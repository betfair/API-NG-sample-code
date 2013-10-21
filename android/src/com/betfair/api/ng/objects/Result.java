package com.betfair.api.ng.objects;

import java.util.List;

public class Result {
	private int marketCount;
	private EventType eventType;
	private Event event;
	private Competition competition;
	private String countryCode;
	private MarketCatalogue marketCatalogues;
	private String venue;
	private String marketType;
	private TimeRange timeRange;
	
	
	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	public int getMarketCount() {
		return marketCount;
	}

	public void setMarketCount(int marketCount) {
		this.marketCount = marketCount;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getMarketType() {
		return marketType;
	}

	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}

	public TimeRange getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(TimeRange timeRange) {
		this.timeRange = timeRange;
	}

	public MarketCatalogue getMarketCatalogues() {
		return marketCatalogues;
	}

	public void setMarketCatalogues(MarketCatalogue marketCatalogues) {
		this.marketCatalogues = marketCatalogues;
	}



	

	
}
