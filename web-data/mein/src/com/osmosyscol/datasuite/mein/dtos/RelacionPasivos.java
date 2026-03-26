package com.osmosyscol.datasuite.mein.dtos;

import java.util.List;

public class RelacionPasivos {
	
	private Integer id;
	private Integer idcarga;
	private Integer pasivos_por_retenciones;
	private ObjGenerico pasivos_por_retencionesObj;
	private Integer pasivos_por_descuentos;
	private ObjGenerico pasivos_por_descuentosObj;
	private Integer pasivos_por_aportes;
	private ObjGenerico pasivos_por_aportesObj;
	private List<Pasivo> pasivos;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdcarga() {
		return idcarga;
	}
	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}
	public Integer getPasivos_por_retenciones() {
		return pasivos_por_retenciones;
	}
	public void setPasivos_por_retenciones(Integer pasivos_por_retenciones) {
		this.pasivos_por_retenciones = pasivos_por_retenciones;
	}
	public Integer getPasivos_por_descuentos() {
		return pasivos_por_descuentos;
	}
	public void setPasivos_por_descuentos(Integer pasivos_por_descuentos) {
		this.pasivos_por_descuentos = pasivos_por_descuentos;
	}
	public Integer getPasivos_por_aportes() {
		return pasivos_por_aportes;
	}
	public void setPasivos_por_aportes(Integer pasivos_por_aportes) {
		this.pasivos_por_aportes = pasivos_por_aportes;
	}
	public List<Pasivo> getPasivos() {
		return pasivos;
	}
	public void setPasivos(List<Pasivo> pasivos) {
		this.pasivos = pasivos;
	}
	public ObjGenerico getPasivos_por_retencionesObj() {
		return pasivos_por_retencionesObj;
	}
	public void setPasivos_por_retencionesObj(ObjGenerico pasivos_por_retencionesObj) {
		this.pasivos_por_retencionesObj = pasivos_por_retencionesObj;
	}
	public ObjGenerico getPasivos_por_descuentosObj() {
		return pasivos_por_descuentosObj;
	}
	public void setPasivos_por_descuentosObj(ObjGenerico pasivos_por_descuentosObj) {
		this.pasivos_por_descuentosObj = pasivos_por_descuentosObj;
	}
	public ObjGenerico getPasivos_por_aportesObj() {
		return pasivos_por_aportesObj;
	}
	public void setPasivos_por_aportesObj(ObjGenerico pasivos_por_aportesObj) {
		this.pasivos_por_aportesObj = pasivos_por_aportesObj;
	}

	
}
