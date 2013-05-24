package com.betfair.aping.enums;

public enum MarketBettingType {
	ODDS("ODDS"), 
	LINE("LINE"), 
	RANGE("RANGE"), 
	ASIAN_HANDICAP_DOUBLE_LINE("ASIAN_HANDICAP_DOUBLE_LINE"),
	ASIAN_HANDICAP_SINGLE_LINE("ASIAN_HANDICAP_SINGLE_LINE"), 
	FIXED_ODDS("FIXED_ODDS");
	
	private String value;

	private MarketBettingType(String value) {
		this.value=value;
	}

	private MarketBettingType(int id) {
		value=String.format("%04d", id);
	}
	
	public String getCode() {
		return value;
	}
}
