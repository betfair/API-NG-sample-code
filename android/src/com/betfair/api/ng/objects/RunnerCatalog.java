package com.betfair.api.ng.objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RunnerCatalog {
	
	private Long selectionId;
    private String runnerName;
    private Double handicap;
    
    public Long getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(Long selectionId) {
		this.selectionId = selectionId;
	}

	public String getRunnerName() {
		return runnerName;
	}

	public void setRunnerName(String runnerName) {
		this.runnerName = runnerName;
	}

	public Double getHandicap() {
		return handicap;
	}

	public void setHandicap(Double handicap) {
		this.handicap = handicap;
	}

	public String toString() {
    	return "{"+""+"selectionId="+getSelectionId()+","+"runnerName="+getRunnerName()+","+"handicap="+getHandicap()+","+"}";
    }

    public boolean equals(Object o) {
        if (!(o instanceof RunnerCatalog)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        RunnerCatalog another = (RunnerCatalog)o;

        return new EqualsBuilder()
            .append(selectionId, another.selectionId)
            .append(runnerName, another.runnerName)
            .append(handicap, another.handicap)
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(selectionId)
            .append(runnerName)
            .append(handicap)
            .toHashCode();
    }

}
