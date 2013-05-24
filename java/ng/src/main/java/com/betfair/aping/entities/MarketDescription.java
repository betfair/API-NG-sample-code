package com.betfair.aping.entities;


import java.util.Date;

public class MarketDescription {

	private Boolean persistenceEnabled;
	private Boolean bspMarket;
	private Date marketTime;
	private Date suspendTime;
	private Date settleTime;
	private String bettingType;
	private Boolean turnInPlayEnabled;
	private String marketType;
	private String regulator;
	private Double marketBaseRate;
	private Boolean discountAllowed;
	private String wallet;
	private String rules;
	private Boolean rulesHasDate;
	private String clarifications;

	public Boolean getPersistenceEnabled() {
		return persistenceEnabled;
	}

	public void setPersistenceEnabled(Boolean persistenceEnabled) {
		this.persistenceEnabled = persistenceEnabled;
	}

	public Boolean getBspMarket() {
		return bspMarket;
	}

	public void setBspMarket(Boolean bspMarket) {
		this.bspMarket = bspMarket;
	}

	public Date getMarketTime() {
		return marketTime;
	}

	public void setMarketTime(Date marketTime) {
		this.marketTime = marketTime;
	}

	public Date getSuspendTime() {
		return suspendTime;
	}

	public void setSuspendTime(Date suspendTime) {
		this.suspendTime = suspendTime;
	}

	public Date getSettleTime() {
		return settleTime;
	}

	public void setSettleTime(Date settleTime) {
		this.settleTime = settleTime;
	}

	public String getBettingType() {
		return bettingType;
	}

	public void setBettingType(String bettingType) {
		this.bettingType = bettingType;
	}

	public Boolean getTurnInPlayEnabled() {
		return turnInPlayEnabled;
	}

	public void setTurnInPlayEnabled(Boolean turnInPlayEnabled) {
		this.turnInPlayEnabled = turnInPlayEnabled;
	}

	public String getMarketType() {
		return marketType;
	}

	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}

	public String getRegulator() {
		return regulator;
	}

	public void setRegulator(String regulator) {
		this.regulator = regulator;
	}

	public Double getMarketBaseRate() {
		return marketBaseRate;
	}

	public void setMarketBaseRate(Double marketBaseRate) {
		this.marketBaseRate = marketBaseRate;
	}

	public Boolean getDiscountAllowed() {
		return discountAllowed;
	}

	public void setDiscountAllowed(Boolean discountAllowed) {
		this.discountAllowed = discountAllowed;
	}

	public String getWallet() {
		return wallet;
	}

	public void setWallet(String wallet) {
		this.wallet = wallet;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public Boolean getRulesHasDate() {
		return rulesHasDate;
	}

	public void setRulesHasDate(Boolean rulesHasDate) {
		this.rulesHasDate = rulesHasDate;
	}

	public String getClarifications() {
		return clarifications;
	}

	public void setClarifications(String clarifications) {
		this.clarifications = clarifications;
	}

	public String toString() {
		return "{" + "" + "persistenceEnabled=" + getPersistenceEnabled() + ","
				+ "bspMarket=" + getBspMarket() + "," + "marketTime="
				+ getMarketTime() + "," + "suspendTime=" + getSuspendTime()
				+ "," + "settleTime=" + getSettleTime() + "," + "bettingType="
				+ getBettingType() + "," + "turnInPlayEnabled="
				+ getTurnInPlayEnabled() + "," + "marketType="
				+ getMarketType() + "," + "regulator=" + getRegulator() + ","
				+ "marketBaseRate=" + getMarketBaseRate() + ","
				+ "discountAllowed=" + getDiscountAllowed() + "," + "wallet="
				+ getWallet() + "," + "rules=" + getRules() + ","
				+ "rulesHasDate=" + getRulesHasDate() + "," + "clarifications="
				+ getClarifications() + "," + "}";
	}
}
