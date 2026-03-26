package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

import java.util.List;

import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;

public class InterpreteRespuestaBancolombia implements IInterpreteRespuestaBanco {

//	private static String nombreBanco = "Bancolombia";

	// -----------------------------------------------------
	
	private IInterpreteRespuestaBanco interprete;

	public InterpreteRespuestaBancolombia() {
		
		String tipoFormato = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("FORMATO_BANCOLOMBIA");
		System.out.println("trajo formato interprete para bancolombia: "+tipoFormato);
		if ("SAP".equals(tipoFormato)) {
			interprete = new InterpreteRespuestaBancolombiaSAP();
		} else {
			interprete = new InterpreteRespuestaBancolombiaPAB();
		}
	}

	// -----------------------------------------------------
		public List<RespuestaBanco> obtenerRespuestasPorBanco(String rutaArchivo, String id_banco) {
		return interprete.obtenerRespuestasPorBanco(rutaArchivo, id_banco);
	}
	
	// -----------------------------------------------------
	public Boolean validarArchivo(String rutaArchivo, String id_banco){
		return interprete.validarArchivo(rutaArchivo, id_banco);
	}
	
	// -----------------------------------------------------
		public String getNombreBanco(){
		return interprete.getNombreBanco();
	}

	// -----------------------------------------------------

}
