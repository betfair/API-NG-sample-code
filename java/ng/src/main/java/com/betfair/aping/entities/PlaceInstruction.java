package com.betfair.aping.entities;

import com.betfair.aping.enums.OrderType;
import com.betfair.aping.enums.Side;

public class PlaceInstruction {

	private OrderType orderType;
	private long selectionId;
	private double handicap;
	private Side side;
	private LimitOrder limitOrder;
	private LimitOnCloseOrder limitOnCloseOrder;
	private MarketOnCloseOrder marketOnCloseOrder;

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public long getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(long selectionId) {
		this.selectionId = selectionId;
	}

	public double getHandicap() {
		return handicap;
	}

	public void setHandicap(double handicap) {
		this.handicap = handicap;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public LimitOrder getLimitOrder() {
		return limitOrder;
	}

	public void setLimitOrder(LimitOrder limitOrder) {
		this.limitOrder = limitOrder;
	}

	public LimitOnCloseOrder getLimitOnCloseOrder() {
		return limitOnCloseOrder;
	}

	public void setLimitOnCloseOrder(LimitOnCloseOrder limitOnCloseOrder) {
		this.limitOnCloseOrder = limitOnCloseOrder;
	}

	public MarketOnCloseOrder getMarketOnCloseOrder() {
		return marketOnCloseOrder;
	}

	public void setMarketOnCloseOrder(MarketOnCloseOrder marketOnCloseOrder) {
		this.marketOnCloseOrder = marketOnCloseOrder;
	}

}
