package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class Requisito {
	
    public String id;
    public String name;
    public List<Rule> rules;
    
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
	public List<Rule> getRules() {
		return rules;
	}
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	
}
