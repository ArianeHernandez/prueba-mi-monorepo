package com.osmosyscol.datasuite.webdata.correval.bancos.entrada;

import java.util.List;

public interface IInterpreteRespuestaBanco {
	
	public List<RespuestaBanco> obtenerRespuestasPorBanco(String rutaArchivo, String id_banco);
	
	public Boolean validarArchivo(String rutaArchivo, String id_banco);
	
	public String getNombreBanco();

}
