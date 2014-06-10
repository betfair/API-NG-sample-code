package com.betfair.aping.containers;

import com.betfair.aping.entities.CompetitionResult;

import java.util.List;


public class CompetitionResultContainer extends Container{
	
	private List<CompetitionResult> result;
	
	public List<CompetitionResult> getResult() {
		return result;
	}
	public void setResult(List<CompetitionResult> result) {
		this.result = result;
	}
}
