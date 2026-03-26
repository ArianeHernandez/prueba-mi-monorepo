package com.osmosyscol.datasuite.modelatos.logica;
import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionEstructura;


public class ValidadorCampoVacio  implements IValidadorDeRegistrosDeCarga{

	public Boolean esRegistroValido(Map<String, Object> registro, List<Map<String, Object>> registros,
			ValidacionEstructura validacionEstructura) {
		
		try{
			Boolean esRegistroValido = false;
							
			List<ValidacionCampo> campos = validacionEstructura.getListaValidacionCampo();
			
			for (ValidacionCampo validacionCampo : campos) {
				
				Integer id_campo = validacionCampo.getId_campo();
				Object valorCampo = (Object)registro.get("C"+id_campo);
				
				if(valorCampo!=null){
					
					String valor = ""+registro.get("C"+id_campo);
					
					if(valor.equals("")){
						esRegistroValido = false;
					}else{
						esRegistroValido = true;
					}
				}else{
					esRegistroValido = false;
				}
			}
				
				return esRegistroValido;
		
		}catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
		
		return false;
		
	}
	
	

}
