package com.betfair.aping.containers;

import java.util.List;

import com.betfair.aping.entities.CountryCodeResult;

public class CountryCodeContainer extends Container {
	private List<CountryCodeResult> result;

	public List<CountryCodeResult> getResult() {
		return result;
	}

	public void setResult(List<CountryCodeResult> result) {
		this.result = result;
	}

}
