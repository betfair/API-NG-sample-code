package com.betfair.aping.entities;

import java.util.Date;
import java.util.List;

public class MarketBook {
	private String marketId;
	private Boolean isMarketDataDelayed;
	private String status;
	private int betDelay;
	private Boolean bspReconciled;
	private Boolean complete;
	private Boolean inplay;
	private int numberOfWinners;
	private int numberOfRunners;
	private int numberOfActiveRunners;
	private Date lastMatchTime;
	private Double totalMatched;
	private Double totalAvailable;
	private Boolean crossMatching;
	private Boolean runnersVoidable;
	private Long version;
	private List<Runner> runners;

	public List<Runner> getRunners() {
		return runners;
	}

	public void setRunners(List<Runner> runners) {
		this.runners = runners;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public Boolean getIsMarketDataDelayed() {
		return isMarketDataDelayed;
	}

	public void setIsMarketDataDelayed(Boolean isMarketDataDelayed) {
		this.isMarketDataDelayed = isMarketDataDelayed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBetDelay() {
		return betDelay;
	}

	public void setBetDelay(int betDelay) {
		this.betDelay = betDelay;
	}

	public Boolean getBspReconciled() {
		return bspReconciled;
	}

	public void setBspReconciled(Boolean bspReconciled) {
		this.bspReconciled = bspReconciled;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Boolean getInplay() {
		return inplay;
	}

	public void setInplay(Boolean inplay) {
		this.inplay = inplay;
	}

	public int getNumberOfWinners() {
		return numberOfWinners;
	}

	public void setNumberOfWinners(int numberOfWinners) {
		this.numberOfWinners = numberOfWinners;
	}

	public int getNumberOfRunners() {
		return numberOfRunners;
	}

	public void setNumberOfRunners(int numberOfRunners) {
		this.numberOfRunners = numberOfRunners;
	}

	public int getNumberOfActiveRunners() {
		return numberOfActiveRunners;
	}

	public void setNumberOfActiveRunners(int numberOfActiveRunners) {
		this.numberOfActiveRunners = numberOfActiveRunners;
	}

	public Date getLastMatchTime() {
		return lastMatchTime;
	}

	public void setLastMatchTime(Date lastMatchTime) {
		this.lastMatchTime = lastMatchTime;
	}

	public Double getTotalMatched() {
		return totalMatched;
	}

	public void setTotalMatched(Double totalMatched) {
		this.totalMatched = totalMatched;
	}

	public Double getTotalAvailable() {
		return totalAvailable;
	}

	public void setTotalAvailable(Double totalAvailable) {
		this.totalAvailable = totalAvailable;
	}

	public Boolean getCrossMatching() {
		return crossMatching;
	}

	public void setCrossMatching(Boolean crossMatching) {
		this.crossMatching = crossMatching;
	}

	public Boolean getRunnersVoidable() {
		return runnersVoidable;
	}

	public void setRunnersVoidable(Boolean runnersVoidable) {
		this.runnersVoidable = runnersVoidable;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String toString() {
		return "{" + "" + "marketId=" + getMarketId() + ","
				+ "isMarketDataDelayed=" + getIsMarketDataDelayed() + ","
				+ "status=" + getStatus() + "," + "betDelay=" + getBetDelay()
				+ "," + "bspReconciled=" + getBspReconciled() + ","
				+ "complete=" + getComplete() + "," + "inplay=" + getInplay()
				+ "," + "numberOfWinners=" + getNumberOfWinners() + ","
				+ "numberOfRunners=" + getNumberOfRunners() + ","
				+ "numberOfActiveRunners=" + getNumberOfActiveRunners() + ","
				+ "lastMatchTime=" + getLastMatchTime() + "," + "totalMatched="
				+ getTotalMatched() + "," + "totalAvailable="
				+ getTotalAvailable() + "," + "crossMatching="
				+ getCrossMatching() + "," + "runnersVoidable="
				+ getRunnersVoidable() + "," + "version=" + getVersion() + ","
				+ "runners=" + getRunners() + "," + "}";
	}

}
