package com.betfair.api.ng.objects;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import android.os.Parcel;
import android.os.Parcelable;

public class MarketFilter {

	private String textQuery;

	public final String getTextQuery() {
		return textQuery;
	}

	public final void setTextQuery(String textQuery) {

		this.textQuery = textQuery;
	}

	private Set<String> exchangeIds = null;
	public final Set<String> getExchangeIds() {
		return exchangeIds;

	}

	public final void setExchangeIds(Set<String> exchangeIds) {
		this.exchangeIds = exchangeIds;
	}

	
	private Set<String> eventTypeIds = null;
	public final Set<String> getEventTypeIds() {
		return eventTypeIds;
	}

	public final void setEventTypeIds(Set<String> eventTypeIds) {
		this.eventTypeIds = eventTypeIds;
	}

	private Set<String> eventIds = null;
	public final Set<String> getEventIds() {
		return eventIds;
	}

	public final void setEventIds(Set<String> eventIds) {
		this.eventIds = eventIds;
	}

	private Set<String> competitionIds = null;
	public final Set<String> getCompetitionIds() {
		return competitionIds;
	}

	public final void setCompetitionIds(Set<String> competitionIds) {
		this.competitionIds = competitionIds;
	}

	
	private Set<String> marketIds = null;
	public final Set<String> getMarketIds() {
		return marketIds;
	}

	public final void setMarketIds(Set<String> marketIds) {
		this.marketIds = marketIds;
	}

	private Set<String> venues = null;
	public final Set<String> getVenues() {
		return venues;
	}

	public final void setVenues(Set<String> venues) {
		this.venues = venues;
	}
	
	private Boolean bspOnly;
	public final Boolean getBspOnly() {
		return bspOnly;
	}

	public final void setBspOnly(Boolean bspOnly) {
		this.bspOnly = bspOnly;
	}

	private Boolean turnInPlayEnabled;
	public final Boolean getTurnInPlayEnabled() {
		return turnInPlayEnabled;
	}

	public final void setTurnInPlayEnabled(Boolean turnInPlayEnabled) {
		this.turnInPlayEnabled = turnInPlayEnabled;
	}
	
	private Boolean inPlayOnly;
	public final Boolean getInPlayOnly() {
		return inPlayOnly;
	}

	public final void setInPlayOnly(Boolean inPlayOnly) {
		this.inPlayOnly = inPlayOnly;
	}

	
	private Set<MarketBettingType> marketBettingTypes = null;
	public final Set<MarketBettingType> getMarketBettingTypes() {
		return marketBettingTypes;
	}

	public final void setMarketBettingTypes(Set<MarketBettingType> marketBettingTypes) {
		this.marketBettingTypes = marketBettingTypes;
	}

	private Set<String> marketCountries = null;
	public final Set<String> getMarketCountries() {
		return marketCountries;
	}

	public final void setMarketCountries(Set<String> marketCountries) {
		this.marketCountries = marketCountries;
	}

	private Set<String> marketTypeCodes = null;
	public final Set<String> getMarketTypeCodes() {
			return marketTypeCodes;
	}

	public final void setMarketTypeCodes(Set<String> marketTypeCodes) {
		this.marketTypeCodes = marketTypeCodes;
	}

	private TimeRange marketStartTime;
	public final TimeRange getMarketStartTime() {
			return marketStartTime;
	}

	public final void setMarketStartTime(TimeRange marketStartTime) {
		this.marketStartTime = marketStartTime;
	}

	private Set<OrderStatus> withOrders = null;
	public final Set<OrderStatus> getWithOrders() {
		return withOrders;
	}

	public final void setWithOrders(Set<OrderStatus> withOrders) {
		this.withOrders = withOrders;
	}

	public String toString() {
		return "{" + "" + "textQuery=" + getTextQuery() + "," + "exchangeIds="
				+ getExchangeIds() + "," + "eventTypeIds=" + getEventTypeIds()
				+ "," + "eventIds=" + getEventIds() + "," + "competitionIds="
				+ getCompetitionIds() + "," + "marketIds=" + getMarketIds()
				+ "," + "venues=" + getVenues() + "," + "bspOnly="
				+ getBspOnly() + "," + "turnInPlayEnabled="
				+ getTurnInPlayEnabled() + "," + "inPlayOnly="
				+ getInPlayOnly() + "," + "marketBettingTypes="
				+ getMarketBettingTypes() + "," + "marketCountries="
				+ getMarketCountries() + "," + "marketTypeCodes="
				+ getMarketTypeCodes() + "," + "marketStartTime="
				+ getMarketStartTime() + "," + "withOrders=" + getWithOrders()
				+ "," + "}";
	}

	public MarketFilter() {
	}


	public boolean equals(Object o) {
		if (!(o instanceof MarketFilter)) {
			return false;
		}

		if (this == o) {
			return true;
		}
		MarketFilter another = (MarketFilter) o;

		return new EqualsBuilder().append(textQuery, another.textQuery)
				.append(exchangeIds, another.exchangeIds)
				.append(eventTypeIds, another.eventTypeIds)
				.append(eventIds, another.eventIds)
				.append(competitionIds, another.competitionIds)
				.append(marketIds, another.marketIds)
				.append(venues, another.venues)
				.append(bspOnly, another.bspOnly)
				.append(turnInPlayEnabled, another.turnInPlayEnabled)
				.append(inPlayOnly, another.inPlayOnly)
				.append(marketBettingTypes, another.marketBettingTypes)
				.append(marketCountries, another.marketCountries)
				.append(marketTypeCodes, another.marketTypeCodes)
				.append(marketStartTime, another.marketStartTime)
				.append(withOrders, another.withOrders).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(textQuery).append(exchangeIds)
				.append(eventTypeIds).append(eventIds).append(competitionIds)
				.append(marketIds).append(venues).append(bspOnly)
				.append(turnInPlayEnabled).append(inPlayOnly)
				.append(marketBettingTypes).append(marketCountries)
				.append(marketTypeCodes).append(marketStartTime)
				.append(withOrders).toHashCode();
	}

}
