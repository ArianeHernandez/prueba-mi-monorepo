package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class RuleDto{
	
	private int id;
	private String name;
	private String type;
    private List<TagDto> tagss;
    private TagDto tags;
    public TagDto getTags() {
		return tags;
	}
	public List<TagDto> getTagss() {
		return tagss;
	}
	public void setTagss(List<TagDto> tagss) {
		this.tagss = tagss;
	}
	public void setTags(TagDto tags) {
		this.tags = tags;
	}
	private Document document;
    private String observations;
    private boolean validated;
    private boolean applies;
    
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	public boolean isApplies() {
		return applies;
	}
	public void setApplies(boolean applies) {
		this.applies = applies;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
//	public List<TagDto> getTags() {
//		return tags;
//	}
//	public void setTags(List<TagDto> tags) {
//		this.tags = tags;
//	}
	
}