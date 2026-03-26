package com.osmosyscol.datasuite.mein.dtos;

public class ProcesoPasante {

	private Integer id_proceso_pasante;
	private String nombre_proceso;
	private Integer id_formato;
	private String proceso_near;
	
	public Integer getId_proceso_pasante() {
		return id_proceso_pasante;
	}
	public void setId_proceso_pasante(Integer id_proceso_pasante) {
		this.id_proceso_pasante = id_proceso_pasante;
	}
	public String getNombre_proceso() {
		return nombre_proceso;
	}
	public void setNombre_proceso(String nombre_proceso) {
		this.nombre_proceso = nombre_proceso;
	}
	public Integer getId_formato() {
		return id_formato;
	}
	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}
	public String getProceso_near() {
		return proceso_near;
	}
	public void setProceso_near(String proceso_near) {
		this.proceso_near = proceso_near;
	}
	
}
