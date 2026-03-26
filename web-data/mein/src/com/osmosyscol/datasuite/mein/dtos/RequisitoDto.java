package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class RequisitoDto {
	
	private String id;
	private String name;
	private List<RulesDto> rules;
    private Boolean validated;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RulesDto> getRules() {
		return rules;
	}
	public void setRules(List<RulesDto> rules) {
		this.rules = rules;
	}
	public Boolean getValidated() {
		return validated;
	}
	public void setValidated(Boolean validated) {
		this.validated = validated;
	}
	
}
