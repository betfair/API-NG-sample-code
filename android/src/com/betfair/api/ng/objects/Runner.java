package com.betfair.api.ng.objects;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Runner {
	    private Long selectionId;
	    private Double handicap;
	    private String status;
	    private Double adjustmentFactor;
	    private Double lastPriceTraded;
	    private Double totalMatched;
	    private Date removalDate;
	    private StartingPrices sp;
	    private ExchangePrices ex;
	    private List<Order> orders;
        private List<Match> matches;
        
	        
	    public Long getSelectionId() {
			return selectionId;
		}
		public void setSelectionId(Long selectionId) {
			this.selectionId = selectionId;
		}
		public Double getHandicap() {
			return handicap;
		}
		public void setHandicap(Double handicap) {
			this.handicap = handicap;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Double getAdjustmentFactor() {
			return adjustmentFactor;
		}
		public void setAdjustmentFactor(Double adjustmentFactor) {
			this.adjustmentFactor = adjustmentFactor;
		}
		public Double getLastPriceTraded() {
			return lastPriceTraded;
		}
		public void setLastPriceTraded(Double lastPriceTraded) {
			this.lastPriceTraded = lastPriceTraded;
		}
		public Double getTotalMatched() {
			return totalMatched;
		}
		public void setTotalMatched(Double totalMatched) {
			this.totalMatched = totalMatched;
		}
		public Date getRemovalDate() {
			return removalDate;
		}
		public void setRemovalDate(Date removalDate) {
			this.removalDate = removalDate;
		}
		public StartingPrices getSp() {
			return sp;
		}
		public void setSp(StartingPrices sp) {
			this.sp = sp;
		}
		public ExchangePrices getEx() {
			return ex;
		}
		public void setEx(ExchangePrices ex) {
			this.ex = ex;
		}
		public List<Order> getOrders() {
			return orders;
		}
		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}
		public List<Match> getMatches() {
			return matches;
		}
		public void setMatches(List<Match> matches) {
			this.matches = matches;
		}
		public String toString() {
	    	return "{"+""+"selectionId="+getSelectionId()+","+"handicap="+getHandicap()+","+"status="+getStatus()+","+"adjustmentFactor="+getAdjustmentFactor()+","+"lastPriceTraded="+getLastPriceTraded()+","+"totalMatched="+getTotalMatched()+","+"removalDate="+getRemovalDate()+","+"sp="+getSp()+","+"ex="+getEx()+","+"orders="+getOrders()+","+"matches="+getMatches()+","+"}";
	    }
	    public Runner () {}
	    
	    

		
	    

	    public boolean equals(Object o) {
	        if (!(o instanceof Runner)) {
	            return false;
	        }

	        if (this == o) {
	            return true;
	        }
	        Runner another = (Runner)o;

	        return new EqualsBuilder()
	            .append(selectionId, another.selectionId)
	            .append(handicap, another.handicap)
	            .append(status, another.status)
	            .append(adjustmentFactor, another.adjustmentFactor)
	            .append(lastPriceTraded, another.lastPriceTraded)
	            .append(totalMatched, another.totalMatched)
	            .append(removalDate, another.removalDate)
	            .append(sp, another.sp)
	            .append(ex, another.ex)
	            .append(orders, another.orders)
	            .append(matches, another.matches)
	            .isEquals();
	    }

	    public int hashCode() {
	        return new HashCodeBuilder()
	            .append(selectionId)
	            .append(handicap)
	            .append(status)
	            .append(adjustmentFactor)
	            .append(lastPriceTraded)
	            .append(totalMatched)
	            .append(removalDate)
	            .append(sp)
	            .append(ex)
	            .append(orders)
	            .append(matches)
	            .toHashCode();
	    }

}
