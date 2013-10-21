package com.betfair.api.ng.objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MarketOnCloseOrder {

	private Double liability;

	public final Double getLiability() {
		return liability;
	}

	public final void setLiability(Double liability) {
		this.liability = liability;
	}

	public String toString() {
		return "{" + "" + "liability=" + getLiability() + "," + "}";
	}

	public boolean equals(Object o) {
		if (!(o instanceof MarketOnCloseOrder)) {
			return false;
		}

		if (this == o) {
			return true;
		}
		MarketOnCloseOrder another = (MarketOnCloseOrder) o;

		return new EqualsBuilder().append(liability, another.liability)
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(liability).toHashCode();
	}

}
