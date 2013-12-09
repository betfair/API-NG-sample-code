package com.betfair.api.ng.objects;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MarketCatalogue {
	
	private String marketId;
    private String marketName;
    private MarketDescription description;
    private List<RunnerCatalog> runners = null; 
    private EventType eventType;
    private Competition competition;
    private Event event;
    
	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public MarketDescription getDescription() {
		return description;
	}

	public void setDescription(MarketDescription description) {
		this.description = description;
	}

	public List<RunnerCatalog> getRunners() {
		return runners;
	}

	public void setRunners(List<RunnerCatalog> runners) {
		this.runners = runners;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String toString() {
		return getMarketName();
	}
	
	public boolean equals(Object o) {
	    if (!(o instanceof MarketCatalogue)) {
	        return false;
	    }
	
	    if (this == o) {
	        return true;
	    }
	    MarketCatalogue another = (MarketCatalogue)o;
	
	    return new EqualsBuilder()
	        .append(marketId, another.marketId)
	        .append(marketName, another.marketName)
	        .append(description, another.description)
	        .append(runners, another.runners)
	        .append(eventType, another.eventType)
	        .append(competition, another.competition)
	        .append(event, another.event)
	        .isEquals();
	}
	
	public int hashCode() {
	    return new HashCodeBuilder()
	        .append(marketId)
	        .append(marketName)
	        .append(description)
	        .append(runners)
	        .append(eventType)
	        .append(competition)
	        .append(event)
	        .toHashCode();
	}

}
