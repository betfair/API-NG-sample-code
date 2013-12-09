package com.betfair.api.ng.objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ExBestOffersOverrides {

	private Integer bestPricesDepth;

	public final Integer getBestPricesDepth() {
		return bestPricesDepth;
	}

	public final void setBestPricesDepth(Integer bestPricesDepth) {
		this.bestPricesDepth = bestPricesDepth;
	}

	private RollupModel rollupModel;

	public final RollupModel getRollupModel() {
		return rollupModel;
	}

	public final void setRollupModel(RollupModel rollupModel) {
		this.rollupModel = rollupModel;
	}

	private Integer rollupLimit;

	public final Integer getRollupLimit() {
		return rollupLimit;
	}

	public final void setRollupLimit(Integer rollupLimit) {
		this.rollupLimit = rollupLimit;
	}

	private Double rollupLiabilityThreshold;

	public final Double getRollupLiabilityThreshold() {
		return rollupLiabilityThreshold;
	}

	public final void setRollupLiabilityThreshold(
			Double rollupLiabilityThreshold) {
		this.rollupLiabilityThreshold = rollupLiabilityThreshold;
	}

	private Integer rollupLiabilityFactor;

	public final Integer getRollupLiabilityFactor() {
		return rollupLiabilityFactor;
	}

	public final void setRollupLiabilityFactor(Integer rollupLiabilityFactor) {
		this.rollupLiabilityFactor = rollupLiabilityFactor;
	}

	public String toString() {
		return "{" + "" + "bestPricesDepth=" + getBestPricesDepth() + ","
				+ "rollupModel=" + getRollupModel() + "," + "rollupLimit="
				+ getRollupLimit() + "," + "rollupLiabilityThreshold="
				+ getRollupLiabilityThreshold() + ","
				+ "rollupLiabilityFactor=" + getRollupLiabilityFactor() + ","
				+ "}";
	}

	public boolean equals(Object o) {
		if (!(o instanceof ExBestOffersOverrides)) {
			return false;
		}

		if (this == o) {
			return true;
		}
		ExBestOffersOverrides another = (ExBestOffersOverrides) o;

		return new EqualsBuilder()
				.append(bestPricesDepth, another.bestPricesDepth)
				.append(rollupModel, another.rollupModel)
				.append(rollupLimit, another.rollupLimit)
				.append(rollupLiabilityThreshold,
						another.rollupLiabilityThreshold)
				.append(rollupLiabilityFactor, another.rollupLiabilityFactor)
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(bestPricesDepth)
				.append(rollupModel).append(rollupLimit)
				.append(rollupLiabilityThreshold).append(rollupLiabilityFactor)
				.toHashCode();
	}

}
