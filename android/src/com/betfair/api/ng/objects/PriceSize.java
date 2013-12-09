package com.betfair.api.ng.objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PriceSize {
	private Double price;
    private Double size;
    
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	    
	public String toString() {
	    	return "{"+""+"price="+getPrice()+","+"size="+getSize()+","+"}";
    }
	
    public boolean equals(Object o) {
        if (!(o instanceof PriceSize)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        PriceSize another = (PriceSize)o;

        return new EqualsBuilder()
            .append(price, another.price)
            .append(size, another.size)
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(price)
            .append(size)
            .toHashCode();
    }

}
