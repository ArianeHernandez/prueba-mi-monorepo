package com.osmosyscol.datasuite.mein.dtos;

public class InventarioActivoPasivo {

	private Integer id;
	private Integer idCarga;
	private String activos_mes_anterior;
	private String pasivos_mes_anterior;
	private String bienes_garantia;
	private String bienes_necesarios;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCarga() {
		return idCarga;
	}
	public void setIdCarga(Integer idCarga) {
		this.idCarga = idCarga;
	}
	public String getActivos_mes_anterior() {
		return activos_mes_anterior;
	}
	public void setActivos_mes_anterior(String activos_mes_anterior) {
		this.activos_mes_anterior = activos_mes_anterior;
	}
	public String getPasivos_mes_anterior() {
		return pasivos_mes_anterior;
	}
	public void setPasivos_mes_anterior(String pasivos_mes_anterior) {
		this.pasivos_mes_anterior = pasivos_mes_anterior;
	}
	public String getBienes_garantia() {
		return bienes_garantia;
	}
	public void setBienes_garantia(String bienes_garantia) {
		this.bienes_garantia = bienes_garantia;
	}
	public String getBienes_necesarios() {
		return bienes_necesarios;
	}
	public void setBienes_necesarios(String bienes_necesarios) {
		this.bienes_necesarios = bienes_necesarios;
	}
	
}
