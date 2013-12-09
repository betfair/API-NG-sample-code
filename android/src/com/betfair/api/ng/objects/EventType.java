package com.betfair.api.ng.objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public class  EventType{
    private String id;	
    private String name;
    
    public EventType () {}

    
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


	public String toString() {
    	return "{"+""+"id="+getId()+","+"name="+getName()+","+"}";
    }

    
    

    public boolean equals(Object o) {
        if (!(o instanceof EventType)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        EventType another = (EventType)o;

        return new EqualsBuilder()
            .append(id, another.id)
            .append(name, another.name)
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(id)
            .append(name)
            .toHashCode();

    }
}
