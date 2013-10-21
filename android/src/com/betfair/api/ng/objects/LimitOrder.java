package com.betfair.api.ng.objects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class LimitOrder {

	private Double size;

	public final Double getSize() {
		return size;
	}

	public final void setSize(Double size) {
		this.size = size;
	}

	private Double price;

	public final Double getPrice() {
		return price;
	}
	
	public final void setPrice(Double price) {
		this.price = price;
	}

	private PersistenceType persistenceType;

	public final PersistenceType getPersistenceType() {
		return persistenceType;
	}


	public final void setPersistenceType(PersistenceType persistenceType)  {
        this.persistenceType = persistenceType;
    }

	public String toString() {
		return "{" + "" + "size=" + getSize() + "," + "price=" + getPrice()
				+ "," + "persistenceType=" + getPersistenceType() + "," + "}";
	}

	public boolean equals(Object o) {
		if (!(o instanceof LimitOrder)) {
			return false;
		}

		if (this == o) {
			return true;
		}
		LimitOrder another = (LimitOrder) o;

		return new EqualsBuilder().append(size, another.size)
				.append(price, another.price)
				.append(persistenceType, another.persistenceType).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(size).append(price)
				.append(persistenceType).toHashCode();
	}

}
