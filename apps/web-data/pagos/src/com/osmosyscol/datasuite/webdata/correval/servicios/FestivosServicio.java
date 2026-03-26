package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class FestivosServicio {

	private static FestivosServicio retiroServicio;

	private FestivosServicio() {
	}

	public static FestivosServicio getInstance() {
		if (retiroServicio == null) {
			retiroServicio = new FestivosServicio();
		}
		return retiroServicio;
	}
	
	public Boolean esFestivo(Date fecha){
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String date_formated = format.format(fecha);
		
		String sql = "select count(*) from $FESTIVOS$ where $FESTIVOS.FESTIVO$ = $T('" + date_formated + "')$";
		
		sql = RDServicio.reemplazarNombres(sql);

		SimpleLogger.setDebug("sql -> " + sql);

		SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

		Integer cantidadResultados = sqlDao.selectSQLNumber(sql);
		
		if(cantidadResultados != 0)
			return true;
		else
			return false;

	}
	
	public List<String> getFestivos(){
		String sql = "select * from $FESTIVOS$";
		
		sql = RDServicio.reemplazarNombres(sql);

		SimpleLogger.setDebug("sql -> " + sql);

		SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

		List<Map<String, Object>> result = sqlDao.selectSQL(sql);
		
		result = SQLServicio.desencriptarMapaStringFormat(result);
		
		String etiqueta = "$FESTIVOS.FESTIVO$";
		etiqueta = RDServicio.reemplazarNombres(etiqueta);
		
		List<String> festivos = new ArrayList<String>();
		
		for(Map<String, Object> registro : result){
			festivos.add((String)registro.get(etiqueta));
		}
		
		return festivos;
		
	}
	
}
