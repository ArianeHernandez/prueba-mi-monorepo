package com.osmosyscol.datasuite.modelatos.logica;

import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.servicios.DatosEstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionEstructura;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;

public class ValidadorConsulta implements IValidadorDeRegistrosDeCarga {
	
	
	public Boolean esRegistroValido(Map<String, Object> registro, List<Map<String, Object>> registros, ValidacionEstructura validacionEstructura) {

		try {
			Boolean esRegistroValido = false;

			Integer id_estructura = validacionEstructura.getId_estructura();

			List<ValidacionCampo> campos = validacionEstructura.getListaValidacionCampo();
			
			//Se agrega el campo en el registro
			if(registro.containsKey("esRegistroValido_"+validacionEstructura.getId_validacion_estructura())){
				
				esRegistroValido = (Boolean)registro.get("esRegistroValido_"+validacionEstructura.getId_validacion_estructura());
				
			}else{
				//Si no existe el llave en el mapa se consulta para todos los registros y se crea la llave para cada mapa
				for (ValidacionCampo validacionCampo : campos) {

					String condition = validacionCampo.getPrimer_valor();
					
					if(StringUtils.esVacio(condition)){
						SimpleLogger.setWarn("La validacion " + validacionEstructura.getDescripcion() + " en el campo " + validacionCampo.getId_campo() + " no tiene valor de condicion.");
						condition = "1=1";
					}

					String sql = "select ID from t" + id_estructura + " t where t.idcarga=" + registro.get("IDCARGA") + " and ( " + RDServicio.reemplazarNombres(condition) + " ) ";

					
					List<Map<String, Object>> resultado = null;
					
					try{
						resultado = DatosEstructuraServicio.getInstance().obtenerDatos(sql);
					}catch (Exception e) {
						SimpleLogger.setError("Error al obtener datos de la validacion.", e);
					}
					
					if(resultado!=null)
					{
						for (Map<String, Object> objRegistro : registros) {
							
							Boolean esValido = false;
							
							if(resultado != null){
								for (Map<String, Object> map : resultado) {
									
									if(  (map.get("ID").toString()).equals(objRegistro.get("ID").toString()) ){
										esValido = true;
									}
									
									//Se verifica el resultado para el registro consultado
									if(objRegistro.get("ID").equals(registro.get("ID"))){
										esRegistroValido = esValido;
									}
									
								}
							}
							
							
							objRegistro.put("esRegistroValido_"+validacionEstructura.getId_validacion_estructura(), esValido);
						}
					}
					
				}
								
			}
			
			
			return esRegistroValido;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;

	}
	
	
	
}
