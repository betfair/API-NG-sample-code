package com.betfair.aping.entities;

import java.util.List;

public class ExchangePrices {

	private List<PriceSize> availableToBack;
	private List<PriceSize> availableToLay;
	private List<PriceSize> tradedVolume;

	public List<PriceSize> getAvailableToBack() {
		return availableToBack;
	}

	public void setAvailableToBack(List<PriceSize> availableToBack) {
		this.availableToBack = availableToBack;
	}

	public List<PriceSize> getAvailableToLay() {
		return availableToLay;
	}

	public void setAvailableToLay(List<PriceSize> availableToLay) {
		this.availableToLay = availableToLay;
	}

	public List<PriceSize> getTradedVolume() {
		return tradedVolume;
	}

	public void setTradedVolume(List<PriceSize> tradedVolume) {
		this.tradedVolume = tradedVolume;
	}

	public String toString() {
		return "{" + "" + "availableToBack=" + getAvailableToBack() + ","
				+ "availableToLay=" + getAvailableToLay() + ","
				+ "tradedVolume=" + getTradedVolume() + "," + "}";
	}

}
