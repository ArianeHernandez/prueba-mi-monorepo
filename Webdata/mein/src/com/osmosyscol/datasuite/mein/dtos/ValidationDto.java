package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class ValidationDto {
	private Boolean validated;
	private List<ChangeLogDto> changeLog;
	
	public Boolean getValidated() {
		return validated;
	}
	public void setValidated(Boolean b) {
		this.validated = b;
	}
	public List<ChangeLogDto> getChangeLog() {
		return changeLog;
	}
	public void setChangeLog(List<ChangeLogDto> changeLog) {
		this.changeLog = changeLog;
	}

}
