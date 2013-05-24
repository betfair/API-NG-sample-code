package com.betfair.aping.entities;

import java.util.Date;

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

}
