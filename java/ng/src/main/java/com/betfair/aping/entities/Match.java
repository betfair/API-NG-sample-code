package com.betfair.aping.entities;

import java.util.Date;

public class Match {

	private String betId;
	private String matchId;
	private String side;
	private Double price;
	private Double Size;
	private Date matchDate;

	public String getBetId() {
		return betId;
	}

	public void setBetId(String betId) {
		this.betId = betId;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSize() {
		return Size;
	}

	public void setSize(Double size) {
		Size = size;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public String toString() {
		return "{" + "" + "betId=" + getBetId() + "," + "matchId="
				+ getMatchId() + "," + "side=" + getSide() + "," + "price="
				+ getPrice() + "," + "Size=" + getSize() + "," + "matchDate="
				+ getMatchDate() + "," + "}";
	}

}
