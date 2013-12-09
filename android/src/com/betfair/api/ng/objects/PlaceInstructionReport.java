package com.betfair.api.ng.objects;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PlaceInstructionReport {

    private String status;
    
    public final String getStatus()  {

            return status;

    }

    public final void setStatus(String status)  {
        this.status = status;
    }
     
    
    private String errorCode;
    public final String getErrorCode()  {
            return errorCode;
    }

    public final void setErrorCode(String errorCode)  {
        this.errorCode = errorCode;
    }
   
   

    private PlaceInstruction instruction;

    public final PlaceInstruction getInstruction()  {
            return instruction;
    }

    public final void setInstruction(PlaceInstruction instruction)  {
            this.instruction=instruction;
    }

    

    private String betId;
    public final String getBetId()  {
            return betId;
    }

    /**
     * The bet ID of the new bet. May be null on failure.
     */
    public final void setBetId(String betId)  {
            this.betId=betId;
    }

    

    private Date placedDate;

    
    public final Date getPlacedDate()  {
            return placedDate;
    }

    public final void setPlacedDate(Date placedDate)  {
            this.placedDate=placedDate;
    }

    private Double averagePriceMatched;
    
    public final Double getAveragePriceMatched()  {
            return averagePriceMatched;
    }
    
    public final void setAveragePriceMatched(Double averagePriceMatched)  {
            this.averagePriceMatched=averagePriceMatched;
    }

    private Double sizeMatched;
    public final Double getSizeMatched()  {
            return sizeMatched;
    }

    public final void setSizeMatched(Double sizeMatched)  {
            this.sizeMatched=sizeMatched;
    }

    
    public String toString() {
    	return "{"+""+"status="+getStatus()+","+"errorCode="+getErrorCode()+","+"instruction="+getInstruction()+","+"betId="+getBetId()+","+"placedDate="+getPlacedDate()+","+"averagePriceMatched="+getAveragePriceMatched()+","+"sizeMatched="+getSizeMatched()+","+"}";
    }
 

    public boolean equals(Object o) {
        if (!(o instanceof PlaceInstructionReport)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        PlaceInstructionReport another = (PlaceInstructionReport)o;

        return new EqualsBuilder()
            .append(status, another.status)
            .append(errorCode, another.errorCode)
            .append(instruction, another.instruction)
            .append(betId, another.betId)
            .append(placedDate, another.placedDate)
            .append(averagePriceMatched, another.averagePriceMatched)
            .append(sizeMatched, another.sizeMatched)
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(status)
            .append(errorCode)
            .append(instruction)
            .append(betId)
            .append(placedDate)
            .append(averagePriceMatched)
            .append(sizeMatched)
            .toHashCode();
    }

}
