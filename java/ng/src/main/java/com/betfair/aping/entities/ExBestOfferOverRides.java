package com.betfair.aping.entities;

import com.betfair.aping.enums.RollupModel;

public class ExBestOfferOverRides {

	private int bestPricesDepth;
	private RollupModel rollupModel;
	private int rollupLimit;
	private double rollupLiabilityThreshold;
	private int rollupLiabilityFactor;

	public int getBestPricesDepth() {
		return bestPricesDepth;
	}

	public void setBestPricesDepth(int bestPricesDepth) {
		this.bestPricesDepth = bestPricesDepth;
	}

	public RollupModel getRollupModel() {
		return rollupModel;
	}

	public void setRollupModel(RollupModel rollupModel) {
		this.rollupModel = rollupModel;
	}

	public int getRollupLimit() {
		return rollupLimit;
	}

	public void setRollupLimit(int rollupLimit) {
		this.rollupLimit = rollupLimit;
	}

	public double getRollupLiabilityThreshold() {
		return rollupLiabilityThreshold;
	}

	public void setRollupLiabilityThreshold(double rollupLiabilityThreshold) {
		this.rollupLiabilityThreshold = rollupLiabilityThreshold;
	}

	public int getRollupLiabilityFactor() {
		return rollupLiabilityFactor;
	}

	public void setRollupLiabilityFactor(int rollupLiabilityFactor) {
		this.rollupLiabilityFactor = rollupLiabilityFactor;
	}

}
