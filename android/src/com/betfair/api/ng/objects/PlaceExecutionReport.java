package com.betfair.api.ng.objects;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PlaceExecutionReport {

	private String customerRef;

	public final String getCustomerRef() {
		return customerRef;
	}

	public final void setCustomerRef(String customerRef) {
		this.customerRef = customerRef;
	}

	private String status;

	public final String getStatus() {
		return status;
	}

	public final void setStatus(String status) {
		this.status = status;
	}

	private String errorCode;

	public final String getErrorCode() {
		return errorCode;
	}

	public final void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	private String marketId;

	public final String getMarketId() {

		return marketId;
	}

	public final void setMarketId(String marketId) {

		this.marketId = marketId;
	}

	private List<PlaceInstructionReport> instructionReports = null;

	public final List<PlaceInstructionReport> getInstructionReports() {

		return instructionReports;
	}

	public final void setInstructionReports(
			List<PlaceInstructionReport> instructionReports) {
		this.instructionReports = instructionReports;
	}

	public String toString() {
		return "{" + "" + "customerRef=" + getCustomerRef() + "," + "status="
				+ getStatus() + "," + "errorCode=" + getErrorCode() + ","
				+ "marketId=" + getMarketId() + "," + "instructionReports="
				+ getInstructionReports() + "," + "}";
	}

	public boolean equals(Object o) {
		if (!(o instanceof PlaceExecutionReport)) {
			return false;
		}

		if (this == o) {
			return true;
		}
		PlaceExecutionReport another = (PlaceExecutionReport) o;

		return new EqualsBuilder().append(customerRef, another.customerRef)
				.append(status, another.status)
				.append(errorCode, another.errorCode)
				.append(marketId, another.marketId)
				.append(instructionReports, another.instructionReports)
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(customerRef).append(status)
				.append(errorCode).append(marketId).append(instructionReports)
				.toHashCode();
	}

}
