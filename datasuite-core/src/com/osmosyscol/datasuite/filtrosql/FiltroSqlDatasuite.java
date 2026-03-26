/**
 * 
 */
package com.osmosyscol.datasuite.filtrosql;

import com.osmosyscol.datapi.logica.servicios.IFiltroConsultaSql;
import com.osmosyscol.datapi.logica.servicios.WSServicio;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;

/**
 * @author oaortizs
 *
 */
public class FiltroSqlDatasuite implements IFiltroConsultaSql {

	/** Filtro para que datapi soporte consultas del tipo $TABLA$
	 * @see com.osmosyscol.datapi.logica.servicios.IFiltroConsultaSql#filtrarConsultar(java.lang.String)
	 */
	public String filtrarConsultar(String consulta) {
		return RDServicio.reemplazarNombres(consulta);
	}
	
	/**
	 *Para registrar este filtro en los servicios datapi
	 * 
	 */
	public static void registrarFiltro(){
		WSServicio.getInstance().registrarFiltroSql(new FiltroSqlDatasuite());
	}

}
