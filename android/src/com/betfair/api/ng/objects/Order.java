package com.betfair.api.ng.objects;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Order {

	private String betId;
    private String orderType;
    private String status;
    private String persistenceType;
    private String side;
    private Double price;
    private Double size;
    private Double bspLiability;
    private Date placedDate;
    private Double avgPriceMatched;
    private Double sizeMatched;
    private Double sizeRemaining;
    private Double sizeLapsed;
    private Double sizeCancelled;
    private Double sizeVoided;
    
    
    public String getBetId() {
		return betId;
	}

	public void setBetId(String betId) {
		this.betId = betId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPersistenceType() {
		return persistenceType;
	}

	public void setPersistenceType(String persistenceType) {
		this.persistenceType = persistenceType;
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
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Double getBspLiability() {
		return bspLiability;
	}

	public void setBspLiability(Double bspLiability) {
		this.bspLiability = bspLiability;
	}

	public Date getPlacedDate() {
		return placedDate;
	}

	public void setPlacedDate(Date placedDate) {
		this.placedDate = placedDate;
	}

	public Double getAvgPriceMatched() {
		return avgPriceMatched;
	}

	public void setAvgPriceMatched(Double avgPriceMatched) {
		this.avgPriceMatched = avgPriceMatched;
	}

	public Double getSizeMatched() {
		return sizeMatched;
	}

	public void setSizeMatched(Double sizeMatched) {
		this.sizeMatched = sizeMatched;
	}

	public Double getSizeRemaining() {
		return sizeRemaining;
	}

	public void setSizeRemaining(Double sizeRemaining) {
		this.sizeRemaining = sizeRemaining;
	}

	public Double getSizeLapsed() {
		return sizeLapsed;
	}

	public void setSizeLapsed(Double sizeLapsed) {
		this.sizeLapsed = sizeLapsed;
	}

	public Double getSizeCancelled() {
		return sizeCancelled;
	}

	public void setSizeCancelled(Double sizeCancelled) {
		this.sizeCancelled = sizeCancelled;
	}

	public Double getSizeVoided() {
		return sizeVoided;
	}

	public void setSizeVoided(Double sizeVoided) {
		this.sizeVoided = sizeVoided;
	}

	public String toString() {
    	return "{"+""+"betId="+getBetId()+","+"orderType="+getOrderType()+","+"status="+getStatus()+","+"persistenceType="+getPersistenceType()+","+"side="+getSide()+","+"price="+getPrice()+","+"size="+getSize()+","+"bspLiability="+getBspLiability()+","+"placedDate="+getPlacedDate()+","+"avgPriceMatched="+getAvgPriceMatched()+","+"sizeMatched="+getSizeMatched()+","+"sizeRemaining="+getSizeRemaining()+","+"sizeLapsed="+getSizeLapsed()+","+"sizeCancelled="+getSizeCancelled()+","+"sizeVoided="+getSizeVoided()+","+"}";
    }

    public boolean equals(Object o) {
        if (!(o instanceof Order)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        Order another = (Order)o;

        return new EqualsBuilder()
            .append(betId, another.betId)
            .append(orderType, another.orderType)
            .append(status, another.status)
            .append(persistenceType, another.persistenceType)
            .append(side, another.side)
            .append(price, another.price)
            .append(size, another.size)
            .append(bspLiability, another.bspLiability)
            .append(placedDate, another.placedDate)
            .append(avgPriceMatched, another.avgPriceMatched)
            .append(sizeMatched, another.sizeMatched)
            .append(sizeRemaining, another.sizeRemaining)
            .append(sizeLapsed, another.sizeLapsed)
            .append(sizeCancelled, another.sizeCancelled)
            .append(sizeVoided, another.sizeVoided)
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(betId)
            .append(orderType)
            .append(status)
            .append(persistenceType)
            .append(side)
            .append(price)
            .append(size)
            .append(bspLiability)
            .append(placedDate)
            .append(avgPriceMatched)
            .append(sizeMatched)
            .append(sizeRemaining)
            .append(sizeLapsed)
            .append(sizeCancelled)
            .append(sizeVoided)
            .toHashCode();
    }
}
