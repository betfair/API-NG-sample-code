package com.betfair.api.ng.objects;

import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Event {
  
    private String id;
    private String name;
    private String countryCode;
    private String timezone;
    private String venue;
    private Date openDate;
    
    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCountryCode() {
		return countryCode;
	}


	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	public String getTimezone() {
		return timezone;
	}


	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}


	public String getVenue() {
		return venue;
	}


	public void setVenue(String venue) {
		this.venue = venue;
	}


	public Date getOpenDate() {
		return openDate;
	}


	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}


	public String toString() {
    	return "{"+""+"id="+getId()+","+"name="+getName()+","+"countryCode="+getCountryCode()+","+"timezone="+getTimezone()+","+"venue="+getVenue()+","+"openDate="+getOpenDate()+","+"}";
    }


    public boolean equals(Object o) {
        if (!(o instanceof Event)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        Event another = (Event)o;

        return new EqualsBuilder()
            .append(id, another.id)
            .append(name, another.name)
            .append(countryCode, another.countryCode)
            .append(timezone, another.timezone)
            .append(venue, another.venue)
            .append(openDate, another.openDate)
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(id)
            .append(name)
            .append(countryCode)
            .append(timezone)
            .append(venue)
            .append(openDate)
            .toHashCode();
    }

}
