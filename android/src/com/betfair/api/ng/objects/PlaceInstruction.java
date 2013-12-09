package com.betfair.api.ng.objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PlaceInstruction {

	private OrderType orderType;

	public final OrderType getOrderType() {
		return orderType;
	}

	public final void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	private Long selectionId;

	public final Long getSelectionId() {
		return selectionId;
	}

	public final void setSelectionId(Long selectionId) {
		this.selectionId = selectionId;
	}

	private Double handicap;


	public final Double getHandicap() {
		return handicap;
	}


	public final void setHandicap(Double handicap) {
		this.handicap = handicap;
	}

	private Side side;

	public final Side getSide() {
		return side;
	}


	public final void setSide(Side side) {
		this.side = side;
	}

	private LimitOrder limitOrder;

	public final LimitOrder getLimitOrder() {

		return limitOrder;
	}

	public final void setLimitOrder(LimitOrder limitOrder) {

		this.limitOrder = limitOrder;

	}

	private LimitOnCloseOrder limitOnCloseOrder;

	public final LimitOnCloseOrder getLimitOnCloseOrder() {

		return limitOnCloseOrder;

	}


	public final void setLimitOnCloseOrder(LimitOnCloseOrder limitOnCloseOrder) {

		this.limitOnCloseOrder = limitOnCloseOrder;

	}

	private MarketOnCloseOrder marketOnCloseOrder;

	public final MarketOnCloseOrder getMarketOnCloseOrder() {

		return marketOnCloseOrder;

	}


	public final void setMarketOnCloseOrder(
			MarketOnCloseOrder marketOnCloseOrder) {

		this.marketOnCloseOrder = marketOnCloseOrder;
	}

	public String toString() {
		return "{" + "" + "orderType=" + getOrderType() + "," + "selectionId="
				+ getSelectionId() + "," + "handicap=" + getHandicap() + ","
				+ "side=" + getSide() + "," + "limitOrder=" + getLimitOrder()
				+ "," + "limitOnCloseOrder=" + getLimitOnCloseOrder() + ","
				+ "marketOnCloseOrder=" + getMarketOnCloseOrder() + "," + "}";
	}

	public boolean equals(Object o) {
		if (!(o instanceof PlaceInstruction)) {
			return false;
		}

		if (this == o) {
			return true;
		}
		PlaceInstruction another = (PlaceInstruction) o;

		return new EqualsBuilder().append(orderType, another.orderType)
				.append(selectionId, another.selectionId)
				.append(handicap, another.handicap).append(side, another.side)
				.append(limitOrder, another.limitOrder)
				.append(limitOnCloseOrder, another.limitOnCloseOrder)
				.append(marketOnCloseOrder, another.marketOnCloseOrder)
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(orderType).append(selectionId)
				.append(handicap).append(side).append(limitOrder)
				.append(limitOnCloseOrder).append(marketOnCloseOrder)
				.toHashCode();
	}

}
