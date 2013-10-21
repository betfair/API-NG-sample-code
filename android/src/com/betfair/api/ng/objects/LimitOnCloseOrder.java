package com.betfair.api.ng.objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class LimitOnCloseOrder {

	private Double liability;

	public final Double getLiability() {

		return liability;

	}

	public final void setLiability(Double liability) {

		this.liability = liability;

	}

	private Double price;

	public final Double getPrice() {

		return price;

	}

	public final void setPrice(Double price) {

		this.price = price;

	}

	public String toString() {
		return "{" + "" + "liability=" + getLiability() + "," + "price="
				+ getPrice() + "," + "}";
	}

	public boolean equals(Object o) {
		if (!(o instanceof LimitOnCloseOrder)) {
			return false;
		}

		if (this == o) {
			return true;
		}
		LimitOnCloseOrder another = (LimitOnCloseOrder) o;

		return new EqualsBuilder().append(liability, another.liability)
				.append(price, another.price).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(liability).append(price)
				.toHashCode();
	}

}
