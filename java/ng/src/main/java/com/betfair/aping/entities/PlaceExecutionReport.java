package com.betfair.aping.entities;

import com.betfair.aping.enums.ExecutionReportErrorCode;
import com.betfair.aping.enums.ExecutionReportStatus;

import java.util.List;


public class PlaceExecutionReport {

	private String customerRef;
	private ExecutionReportStatus status;
	private ExecutionReportErrorCode errorCode;
	private String marketId;
	private List<PlaceInstructionReport> instructionReports;

	public String getCustomerRef() {
		return customerRef;
	}

	public void setCustomerRef(String customerRef) {
		this.customerRef = customerRef;
	}

	public ExecutionReportStatus getStatus() {
		return status;
	}

	public void setStatus(ExecutionReportStatus status) {
		this.status = status;
	}

	public ExecutionReportErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ExecutionReportErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public List<PlaceInstructionReport> getInstructionReports() {
		return instructionReports;
	}

	public void setInstructionReports(
			List<PlaceInstructionReport> instructionReports) {
		this.instructionReports = instructionReports;
	}
}
