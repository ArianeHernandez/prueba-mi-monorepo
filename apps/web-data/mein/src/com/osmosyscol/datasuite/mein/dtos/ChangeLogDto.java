package com.osmosyscol.datasuite.mein.dtos;

public class ChangeLogDto {
	private String modificationDate;
	private String modificationStatus;
	private String modifiedBy;
	
	public String getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}
	public String getModificationStatus() {
		return modificationStatus;
	}
	public void setModificationStatus(String modificationStatus) {
		this.modificationStatus = modificationStatus;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
