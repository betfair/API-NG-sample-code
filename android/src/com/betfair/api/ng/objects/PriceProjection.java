package com.betfair.api.ng.objects;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PriceProjection {
	private Set<PriceData> priceData = null;

	public final Set<PriceData> getPriceData() {
		return priceData;
	}

	public final void setPriceData(Set<PriceData> priceData) {
		this.priceData = priceData;
	}

	private ExBestOffersOverrides exBestOffersOverrides;

	public final ExBestOffersOverrides getExBestOffersOverrides() {
		return exBestOffersOverrides;
	}

	public final void setExBestOffersOverrides(
			ExBestOffersOverrides exBestOffersOverrides) {
		this.exBestOffersOverrides = exBestOffersOverrides;
	}

	private Boolean virtualise;

	public final Boolean getVirtualise() {
		return virtualise;
	}

	public final void setVirtualise(Boolean virtualise) {
		this.virtualise = virtualise;
	}

	private Boolean rolloverStakes;

	public final Boolean getRolloverStakes() {
		return rolloverStakes;
	}

	public final void setRolloverStakes(Boolean rolloverStakes) {
		this.rolloverStakes = rolloverStakes;
	}

	public String toString() {
		return "{" + "" + "priceData=" + getPriceData() + ","
				+ "exBestOffersOverrides=" + getExBestOffersOverrides() + ","
				+ "virtualise=" + getVirtualise() + "," + "rolloverStakes="
				+ getRolloverStakes() + "," + "}";
	}

	public boolean equals(Object o) {
		if (!(o instanceof PriceProjection)) {
			return false;
		}

		if (this == o) {
			return true;
		}
		PriceProjection another = (PriceProjection) o;

		return new EqualsBuilder().append(priceData, another.priceData)
				.append(exBestOffersOverrides, another.exBestOffersOverrides)
				.append(virtualise, another.virtualise)
				.append(rolloverStakes, another.rolloverStakes).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(priceData)
				.append(exBestOffersOverrides).append(virtualise)
				.append(rolloverStakes).toHashCode();
	}
}
