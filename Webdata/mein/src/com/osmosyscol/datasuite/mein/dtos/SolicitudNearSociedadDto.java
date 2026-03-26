package com.osmosyscol.datasuite.mein.dtos;


public class SolicitudNearSociedadDto {
	
	private String id;
	private String idPadre;
	private Boolean isNear;
	private EnterpriseInfoDto enterpriseInfo;
	private Integer processId;
	private Interviniente validatingBy;
	private Interviniente uploadedBy;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getIsNear() {
		return isNear;
	}
	public void setIsNear(Boolean isNear) {
		this.isNear = isNear;
	}
	public EnterpriseInfoDto getEnterpriseInfo() {
		return enterpriseInfo;
	}
	public void setEnterpriseInfo(EnterpriseInfoDto enterpriseInfo) {
		this.enterpriseInfo = enterpriseInfo;
	}
	public long getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public Interviniente getValidatingBy() {
		return validatingBy;
	}
	public void setValidatingBy(Interviniente validatingBy) {
		this.validatingBy = validatingBy;
	}
	public Interviniente getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(Interviniente uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public String getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(String idPadre) {
		this.idPadre = idPadre;
	}
			
}
