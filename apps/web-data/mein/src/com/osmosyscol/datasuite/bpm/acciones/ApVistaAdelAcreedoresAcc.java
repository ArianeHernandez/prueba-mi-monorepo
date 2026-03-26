package com.osmosyscol.datasuite.bpm.acciones;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.bpm.dto.ApVistaAdelAcreedoresDto;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ApVistaAdelAcreedoresAcc {
	
	private static ApVistaAdelAcreedoresAcc instance;	
	private static Gson gson = new Gson();
	
	private ApVistaAdelAcreedoresAcc() {
		
	}
	
	public static ApVistaAdelAcreedoresAcc getInstance() {
		if(instance == null) {
			instance = new ApVistaAdelAcreedoresAcc();
		}
		return instance;
	}
	
	public String getAll(){

		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA ADEL ACREEDORES$";	
		
		List<ApVistaAdelAcreedoresDto> listaGrupos = new ArrayList<ApVistaAdelAcreedoresDto>();
		
		try {
			listaGrupos = DS_SqlUtils.queryForList(ApVistaAdelAcreedoresDto.class, querySelect);
			entrega = gson.toJson(listaGrupos);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA ADEL ACREEDORES. Error: ", e);
		}
		
		
//		System.out.println("ENTREGA: "+ entrega);
		
		return entrega;
	}
	
	public String getPorId(int id){
		String entrega = "{}";
		String querySelect = "SELECT * FROM $APVISTA ADEL ACREEDORES$ WHERE $APVISTA ADEL ACREEDORES.ID$=$I("+id+")$";
		
//		System.out.println("CONSULTA: "+querySelect);
		
		ApVistaAdelAcreedoresDto grupo = null;
		
		try {
			grupo = DS_SqlUtils.queryForObject(ApVistaAdelAcreedoresDto.class, querySelect);
			entrega = gson.toJson(grupo);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA ADEL ACREEDORES por ID. Error: ", e);
		}
		
		return entrega;
	}
	
	public ApVistaAdelAcreedoresDto getPorLista(String lista) {

		List<ApVistaAdelAcreedoresDto> listaGrupos = new ArrayList<ApVistaAdelAcreedoresDto>();
		String querySelect = "SELECT * FROM $APVISTA ADEL ACREEDORES$ WHERE $APVISTA ADEL ACREEDORES.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			listaGrupos = DS_SqlUtils.queryForList(ApVistaAdelAcreedoresDto.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA ADEL ACREEDORES. por lista. Error: ", e);
		}
		
		if(listaGrupos.isEmpty()){
			return null;
		}
		
		return listaGrupos.get(0);
	}
	
	
	public String getIdVistaPorLista(String lista) throws Exception{

		String entrega = "{}";
		String querySelect = "SELECT $APVISTA ADEL ACREEDORES.ID VISTA$ FROM $APVISTA ADEL ACREEDORES$ WHERE $APVISTA ADEL ACREEDORES.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			entrega = DS_SqlUtils.queryForObject(String.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA ADEL ACREEDORES. por lista. Error: ", e);
		}
		
		return entrega;
	}
	
	
	public String getNombrePorLista(String lista) {

		String entrega = "{}";
		String querySelect = "SELECT $APVISTA ADEL ACREEDORES.NOMBRE$ FROM $APVISTA ADEL ACREEDORES$ WHERE $APVISTA ADEL ACREEDORES.LISTA$=$I("+lista+")$";
		
		System.out.println("CONSULTA: "+querySelect);
		
		try {
			entrega = DS_SqlUtils.queryForObject(String.class, querySelect);
		} catch (Exception e){
			SimpleLogger.setError("Error al consultar APVISTA ADEL ACREEDORES. por lista. Error: ", e);
		}
		
		return entrega;
	}
	
	
}
