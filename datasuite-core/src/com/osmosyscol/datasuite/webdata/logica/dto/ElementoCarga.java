package com.osmosyscol.datasuite.webdata.logica.dto;

import java.util.Date;

import com.osmosyscol.datasuite.logica.constantes.Constantes;

public class ElementoCarga {
	private Integer id_elementocarga;
	private Integer id_carga;
	private Date fecha;
	private String estado = Constantes.ELEMENTOCARGA_ESTADO_ACTIVO;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId_elementocarga() {
		return id_elementocarga;
	}

	public void setId_elementocarga(Integer id_elementocarga) {
		this.id_elementocarga = id_elementocarga;
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
