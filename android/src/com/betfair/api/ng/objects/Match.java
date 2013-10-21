package com.betfair.api.ng.objects;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    	return "{"+""+"betId="+getBetId()+","+"matchId="+getMatchId()+","+"side="+getSide()+","+"price="+getPrice()+","+"Size="+getSize()+","+"matchDate="+getMatchDate()+","+"}";
    }

    public boolean equals(Object o) {
        if (!(o instanceof Match)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        Match another = (Match)o;

        return new EqualsBuilder()
            .append(betId, another.betId)
            .append(matchId, another.matchId)
            .append(side, another.side)
            .append(price, another.price)
            .append(Size, another.Size)
            .append(matchDate, another.matchDate)
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(betId)
            .append(matchId)
            .append(side)
            .append(price)
            .append(Size)
            .append(matchDate)
            .toHashCode();
    }
}
