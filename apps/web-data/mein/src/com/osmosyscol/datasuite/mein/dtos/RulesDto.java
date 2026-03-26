package com.osmosyscol.datasuite.mein.dtos;

public class RulesDto {
	private Boolean applies;
	private RuleDto rule;
	private ValidationDto MLValidation;
	private ValidationDto manualValidation;
	
	public Boolean getApplies() {
		return applies;
	}
	public void setApplies(Boolean applies) {
		this.applies = applies;
	}
	public RuleDto getRule() {
		return rule;
	}
	public void setRule(RuleDto rule) {
		this.rule = rule;
	}
	public ValidationDto getMLValidation() {
		return MLValidation;
	}
	public void setMLValidation(ValidationDto mLValidation) {
		MLValidation = mLValidation;
	}
	public ValidationDto getManualValidation() {
		return manualValidation;
	}
	public void setManualValidation(ValidationDto manualValidation) {
		this.manualValidation = manualValidation;
	}

}
