package com.betfair.api.ng.objects;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
		return "{"+""+"availableToBack="+getAvailableToBack()+","+"availableToLay="+getAvailableToLay()+","+"tradedVolume="+getTradedVolume()+","+"}";
	}
	
	
	public boolean equals(Object o) {
	    if (!(o instanceof ExchangePrices)) {
	        return false;
	    }
	
	    if (this == o) {
	        return true;
	    }
	    ExchangePrices another = (ExchangePrices)o;
	
	    return new EqualsBuilder()
	        .append(availableToBack, another.availableToBack)
	        .append(availableToLay, another.availableToLay)
	        .append(tradedVolume, another.tradedVolume)
	        .isEquals();
	}
	
	public int hashCode() {
	    return new HashCodeBuilder()
	        .append(availableToBack)
	        .append(availableToLay)
	        .append(tradedVolume)
	        .toHashCode();
	}

}
