package com.betfair.aping.exceptions;

public class APINGException extends Throwable {
	private String errorDetails;
	private String errorCode;
	private String requestUUID;

    public APINGException() {
        super();
    }

	public APINGException(String errorDetails, String errorCode, String requestUUID) {
		this.errorCode=errorCode;
		this.errorDetails=errorDetails;
		this.requestUUID=requestUUID;
	}
	
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getRequestUUID() {
		return requestUUID;
	}
	public void setRequestUUID(String requestUUID) {
		this.requestUUID = requestUUID;
	}

	@Override
	public String toString() {
		return "ErrorCode: " + errorCode + " ErrorDetails: " + errorDetails + " RequestUUID: " + requestUUID;
	}

}
