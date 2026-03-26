package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.webdata.logica.dto.VariableLiberacion;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

/**
 * Servicio para limitar el acceso a los servicios por Rol 
 * 
 * @author oaortizs
 *
 */
public class VariableLiberacionServicio {
	
	private static VariableLiberacionServicio instance;
	
	private VariableLiberacionServicio() {
	}

	public static VariableLiberacionServicio getInstance() {
		
		if(instance == null){
			instance = new VariableLiberacionServicio();
		}
		
		return instance;
	}

	public List<VariableLiberacion> obtenerVariablesLiberacionPorCarga(Integer id_carga){
		
		try{
			//Se guardan las variables de liberacion para la carga
			DaoManager daoManagerDataSuite = DaoConfig.getDaoManager();
			SQLDao sqlDao= (SQLDao) daoManagerDataSuite.getDao(SQLDao.class);
			
			String sql = " select id_campo, valor from dst_variable_liberacion_carga " +
						 " where id_carga = "+id_carga;
							   
			
			List<Map<String, Object>> datos = sqlDao.selectSQL(sql);
			
			List<VariableLiberacion> variables = new ArrayList<VariableLiberacion>();
			
			if (datos == null) {
				return variables;
			}
			
			for (Map<String, Object> registro : datos) {
				
				Integer id_campo = new Integer(registro.get("ID_CAMPO").toString());
				Object valor = Crypto.DF(registro.get("VALOR"));
				
				String valorVisualizacion = CampoServicio.getInstance().obtenerValorVisualizacionCampo(id_campo, valor);
				String nombreCampo = CampoServicio.getInstance().obtenerCampo(id_campo).getNombre();
				
				VariableLiberacion variableLiberacion = new VariableLiberacion();
				variableLiberacion.setId_carga(id_carga);
				variableLiberacion.setNombreVariableLiberacion(nombreCampo);
				variableLiberacion.setValor(valorVisualizacion);
				variableLiberacion.setId_campo(id_campo);
				
				variables.add(variableLiberacion);
				
			}

			return variables;
			
		
		
		}catch (Exception e) {
			SimpleLogger.setDebug("Ha ocurrido un error", e);
			return null;
		}
		
		
		
		
	}
}
