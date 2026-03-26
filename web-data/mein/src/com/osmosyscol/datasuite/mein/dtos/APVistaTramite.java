package com.osmosyscol.datasuite.mein.dtos;

public class APVistaTramite {

	private Integer id;
	private Integer idBPM;
	private Long idPostal;
	private Long codigo;
	private String nombreTramite;
	private Integer termino_dias;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdBPM() {
		return idBPM;
	}
	public void setIdBPM(Integer idBPM) {
		this.idBPM = idBPM;
	}
	public Long getIdPostal() {
		return idPostal;
	}
	public void setIdPostal(Long idPostal) {
		this.idPostal = idPostal;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNombreTramite() {
		return nombreTramite;
	}
	public void setNombreTramite(String nombreTramite) {
		this.nombreTramite = nombreTramite;
	}
	public Integer getTermino_dias() {
		return termino_dias;
	}
	public void setTermino_dias(Integer termino_dias) {
		this.termino_dias = termino_dias;
	}
	
}
