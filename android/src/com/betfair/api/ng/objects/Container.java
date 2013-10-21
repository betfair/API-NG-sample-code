package com.betfair.api.ng.objects;


import com.google.gson.JsonElement;

public class Container {

	private JsonElement result;
	private Error error;
	private String jsonrpc;

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String jsonrpc) {
		this.jsonrpc = jsonrpc;
	}

	public Error getError() {
		return error;
	}

	

	public void setError(Error error) {
		this.error = error;
	}

	public JsonElement getResult() {
		return result;
	}

	public void setResult(JsonElement result) {
		this.result = result;
	}

	
	
	

}
