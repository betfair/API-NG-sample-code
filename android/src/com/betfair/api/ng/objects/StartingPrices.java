package com.betfair.api.ng.objects;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class StartingPrices {
    private Double nearPrice;
    private Double farPrice;
    private List<PriceSize> backStakeTaken = null;
    private List<PriceSize> layLiabilityTaken = null;     
    private Double actualSP;
    
    
    
    public Double getNearPrice() {
		return nearPrice;
	}

	public void setNearPrice(Double nearPrice) {
		this.nearPrice = nearPrice;
	}

	public Double getFarPrice() {
		return farPrice;
	}

	public void setFarPrice(Double farPrice) {
		this.farPrice = farPrice;
	}

	public List<PriceSize> getBackStakeTaken() {
		return backStakeTaken;
	}

	public void setBackStakeTaken(List<PriceSize> backStakeTaken) {
		this.backStakeTaken = backStakeTaken;
	}

	public List<PriceSize> getLayLiabilityTaken() {
		return layLiabilityTaken;
	}

	public void setLayLiabilityTaken(List<PriceSize> layLiabilityTaken) {
		this.layLiabilityTaken = layLiabilityTaken;
	}

	public Double getActualSP() {
		return actualSP;
	}

	public void setActualSP(Double actualSP) {
		this.actualSP = actualSP;
	}

	public String toString() {
    	return "{"+""+"nearPrice="+getNearPrice()+","+"farPrice="+getFarPrice()+","+"backStakeTaken="+getBackStakeTaken()+","+"layLiabilityTaken="+getLayLiabilityTaken()+","+"actualSP="+getActualSP()+","+"}";
    }

    public boolean equals(Object o) {
        if (!(o instanceof StartingPrices)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        StartingPrices another = (StartingPrices)o;

        return new EqualsBuilder()
            .append(nearPrice, another.nearPrice)
            .append(farPrice, another.farPrice)
            .append(backStakeTaken, another.backStakeTaken)
            .append(layLiabilityTaken, another.layLiabilityTaken)
            .append(actualSP, another.actualSP)
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(nearPrice)
            .append(farPrice)
            .append(backStakeTaken)
            .append(layLiabilityTaken)
            .append(actualSP)
            .toHashCode();
    }

}
