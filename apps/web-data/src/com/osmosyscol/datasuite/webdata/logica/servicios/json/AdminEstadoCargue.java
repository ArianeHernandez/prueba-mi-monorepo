package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.HashMap;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;

public class AdminEstadoCargue {

	private AdminEstadoCargue(){}
	
	private static AdminEstadoCargue instance;
	
	public synchronized static AdminEstadoCargue getInstance(){
		if(instance == null){
			instance =  new AdminEstadoCargue();
		}
		return instance;
	}

	
	private static Map<Integer,RespuestaCarga> respuestaCargas = new HashMap<Integer, RespuestaCarga>();
	
	private static int ID_ARCHIVO_ACTUAL = 0;
	
	private static Map<Integer,EstadoCargaArchivo> mapEstadoCargaArchivo = new HashMap<Integer, EstadoCargaArchivo>(); 
	
	public synchronized  int getSiguienteIdArchivo() {
		return ID_ARCHIVO_ACTUAL++;
	}

	public synchronized float getProgresoPasos(int idArchivo) {
		try {
			EstadoCargaArchivo estado = mapEstadoCargaArchivo.get(idArchivo);
			
			Integer pasos = estado.getPaso();
			Integer totalPasos = estado.getTotalPasos();
			
			//Si el paso actual es mayor a los pasos totales
			//Se igualan, terminando el proceso
			if(pasos > totalPasos){
				estado.setTotalPasos((int)totalPasos);				
				pasos = totalPasos;
			}
			
			return (pasos.floatValue()/totalPasos.floatValue())*100f;
		} catch (Exception e) {
			//SimpleLogger.setWarn("Exception manejada, retornando progreso 0 ", e);
			return 0f;
		}
	}

	public String getEstadoCarga(Integer idArchivo) {
		try {
			EstadoCargaArchivo estado = mapEstadoCargaArchivo.get(idArchivo);
			return estado.getEstado();
			
		} catch (Exception e) {
			return "Cargando.....";
		}
	}

	public synchronized void setEstadoCarga(Integer idArchivo,String estado) {
		EstadoCargaArchivo estadoCargaArchivo = mapEstadoCargaArchivo.get(idArchivo);
		estadoCargaArchivo.setEstado(estado);
	}

	public synchronized void setRespuestaCarga(Integer idArchivo, RespuestaCarga respuesta) {
		respuestaCargas.put(idArchivo,respuesta);
	}

	public  RespuestaCarga getRespuestaCarga(Integer idArchivo) {
		return respuestaCargas.get(idArchivo);
	}
	
	public EstadoCargaArchivo getEstadoCargaArchivo(Integer idArchivo){
		
		return mapEstadoCargaArchivo.get(idArchivo);
	}
	
	// Total de Pasos en General
	public synchronized void SetPasosCarga(Integer idArchivo, int pasos){
		
		EstadoCargaArchivo estado = mapEstadoCargaArchivo.get(idArchivo);

		estado.setTotalPasos(pasos);
				
	}

	//total de cada uno de los registros
	public synchronized void SetPasosRegistro(Integer idArchivo, int pasos){
		
		EstadoCargaArchivo estado = mapEstadoCargaArchivo.get(idArchivo);
		
		estado.setTotalRegistro(pasos);		
		estado.setPasoRegistro(0);
		
	}
	
	public void finalizarRegistro(Integer idArchivo){
		EstadoCargaArchivo estado = mapEstadoCargaArchivo.get(idArchivo);
		estado.setPasoRegistro(estado.getTotalRegistro());
	}
	
	public void finalizarPasoRegistro(Integer idArchivo, String mensaje){
		EstadoCargaArchivo estado = mapEstadoCargaArchivo.get(idArchivo);
		estado.setMapMensajePasoActual(estado.getPaso(), mensaje);
	}	
	
	//Incrementa los pasos en un regitro especifico
	public synchronized void incrementarPasoRegistro(Integer idArchivo, String mensaje){
		try {
			EstadoCargaArchivo estado = mapEstadoCargaArchivo.get(idArchivo);
								
			int paso = estado.getPasoRegistro();
			paso++;
			estado.setMapMensajePasoActual(estado.getPaso(), mensaje);
			estado.setPasoRegistro(paso);
			
		} catch (Exception e) {
			SimpleLogger.setWarn("Exception manejada, no incrementando el progreso ", e);
		}	
	}
	
	/**
	 * Incrementa Pasos en el Proceso General de Carga
	 * @param idArchivo
	 * @param pasos
	 * @return
	 */
	public synchronized void incrementarProgresoPasos(Integer idArchivo, int pasos){
		try {
			EstadoCargaArchivo estado = mapEstadoCargaArchivo.get(idArchivo);
			
			int paso = estado.getPaso();
			paso += pasos;
			estado.setPaso(paso);
			
		} catch (Exception e) {
			SimpleLogger.setWarn("Exception manejada, no incrementando el progreso ", e);
		}

	}
	
	public void incrementarProgresoPasos(int idArchivo){
		incrementarProgresoPasos(idArchivo,1);
	}

	
	public Boolean getFinCarga(Integer idArchivo) {	
		
		if (getRespuestaCarga(idArchivo)!=null) {
			return true;
		}else{
			return false;
		}		
	}

	public void iniciarCarga(int idArchivo) {
		EstadoCargaArchivo estado = new EstadoCargaArchivo();	
		
		mapEstadoCargaArchivo.put(idArchivo, estado);		
	}

	public void setErrorXPaso(int idArchivo, Integer codError) {
		EstadoCargaArchivo estado = mapEstadoCargaArchivo.get(idArchivo);
		
		estado.setMapErroresXPaso(estado.getPaso(), codError);  
	}


	public void setEstadoRegistro(int idArchivo, String mensaje) {
		try {
			EstadoCargaArchivo estado = mapEstadoCargaArchivo.get(idArchivo);	
			
			estado.setMapMensajePasoActual(estado.getPaso(), mensaje);		
		} catch (Exception e) {
			SimpleLogger.setWarn("Exception manejada, no asigno estado al registro ", e);
		}
		
	}
	
}
