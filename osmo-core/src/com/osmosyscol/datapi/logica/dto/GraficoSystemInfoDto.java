package com.osmosyscol.datapi.logica.dto;

import java.util.Date;

import com.osmosyscol.commons.servlet.SessionListener;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.SystemUtils;
public class GraficoSystemInfoDto {
	
	private long memoriaMaquinaVirtual;
	private long memoriaUtilizada;
	private Date tiempo;
	private long sesiones_activas;
	
	public long getMemoriaMaquinaVirtual() {
		return memoriaMaquinaVirtual;
	}
	public void setMemoriaMaquinaVirtual(long memoriaMaquinaVirtual) {
		this.memoriaMaquinaVirtual = memoriaMaquinaVirtual;
	}
	public long getMemoriaUtilizada() {
		return memoriaUtilizada;
	}
	public void setMemoriaUtilizada(long memoriaUtilizada) {
		this.memoriaUtilizada = memoriaUtilizada;
	}
	public Date getTiempo() {
		return tiempo;
	}
	public void setTiempo(Date tiempo) {
		this.tiempo = tiempo;
	}
	
	public String getTiempoFormato(){
		if (tiempo != null){
			return StringUtils.toStringFormat(tiempo);
		}
		return null;
	}
	
	public String getHora(){
		String tiempoF = getTiempoFormato();
		if (tiempoF != null){
			String[] array = tiempoF.split(" ");
			return array[1];
		}
		return null;
	}
	
	public long getSesiones_activas() {
		return sesiones_activas;
	}
	public void setSesiones_activas(long sesiones_activas) {
		this.sesiones_activas = sesiones_activas;
	}
	
// -------------- Constructor -----------------	
	
	public GraficoSystemInfoDto() {
		memoriaMaquinaVirtual = SystemUtils.memoriaMaquinaVirtual();
		memoriaUtilizada = SystemUtils.memoriaUtilizada();
		tiempo = new Date();
		sesiones_activas = SessionListener.getSession();
	}

	// --------------------------------------------	

}
