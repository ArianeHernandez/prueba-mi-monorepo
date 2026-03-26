package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class DataSolicitudNearSociedadDto extends SolicitudNearSociedadDto {
	
	private String _id;
	private RazonSocialDto razonSocial;
	private String mlValidated;
	private List<RequirementDto> validations;
	private List<ChangeLogDto> changeLog;

	
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getMlValidated() {
		return mlValidated;
	}
	public void setMlValidated(String mlValidated) {
		this.mlValidated = mlValidated;
	}
	public List<RequirementDto> getValidations() {
		return validations;
	}
	public void setValidations(List<RequirementDto> validations) {
		this.validations = validations;
	}
	public List<ChangeLogDto> getChangeLog() {
		return changeLog;
	}
	public void setChangeLog(List<ChangeLogDto> changeLog) {
		this.changeLog = changeLog;
	}
	public RazonSocialDto getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(RazonSocialDto razonSocial) {
		this.razonSocial = razonSocial;
	}
	
}
