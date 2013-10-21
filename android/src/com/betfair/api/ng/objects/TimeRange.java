package com.betfair.api.ng.objects;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TimeRange {

	private Date from;

	public final Date getFrom() {
		return from;
	}

	public final void setFrom(Date from) {
		this.from = from;
	}

	private Date to;

	public final Date getTo() {
		return to;
	}

	public final void setTo(Date to) {
		this.to = to;
	}

	public boolean equals(Object o) {
		if (!(o instanceof TimeRange)) {
			return false;
		}

		if (this == o) {
			return true;
		}
		TimeRange another = (TimeRange) o;

		return new EqualsBuilder().append(from, another.from)
				.append(to, another.to).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(from).append(to).toHashCode();
	}

}
