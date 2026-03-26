package com.osmosyscol.datasuite.modelatos.logica;
import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionEstructura;


public class ValidadorCamposRepetidos  implements IValidadorDeRegistrosDeCarga{

	public Boolean esRegistroValido(Map<String, Object> registro, List<Map<String, Object>> registros, ValidacionEstructura validacionEstructura) {
		
		try{
			Boolean esRegistroValido = false;
				
			//Se verifica si para los campos especificos hay registros repetidos
			List<ValidacionCampo> campos = validacionEstructura.getListaValidacionCampo();
			
			//Se revisa la cantidad de registros repetidos por campo
			Integer totalRegistrosRepetidos = 0;
			
			for (Map<String, Object> registro_tabla : registros) {
				
				boolean iguales = true;
				if(!registro_tabla.get("ID").equals(registro.get("ID"))){
					
					for (ValidacionCampo validacionCampo : campos) {
						Integer id_campo = validacionCampo.getId_campo();
						
						Object valor1 = "" + registro.get("C" + id_campo);
						Object valor2 = "" + registro_tabla.get("C" + id_campo);
						
						if(!valor1.equals(valor2)){
							iguales = false;
						}
					}
					if(iguales){
						totalRegistrosRepetidos++;
						break;
					}
				}
				
				
			}
			
			if(totalRegistrosRepetidos>0){
				esRegistroValido = false;
				
			}else{
				esRegistroValido = true;
			}
			
			
			//finalmente se retorna si el registgro es valido
			return esRegistroValido;
		
		}catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
		return null;
		
	}
	
	

}
