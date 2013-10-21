package com.betfair.api.ng.objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class  Competition{

    private String id;
    private String name;
    
    
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
        if (!(o instanceof Competition)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        Competition another = (Competition)o;

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



