package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.List;

import com.osmosyscol.datasuite.webdata.correval.servicios.reporte_tipo_negocio.ReporteTipoNegocioService;
import com.osmosyscol.datasuite.webdata.correval.servicios.reporte_tipo_negocio.ReporteTipoNegocioService.Filtro;
import com.osmosyscol.datasuite.webdata.correval.servicios.reporte_tipo_negocio.TipoNegocio;

public class ReporteTipoNegocioJson {
	
	public static Integer generarReporte(Filtro filtro){	
		return ReporteTipoNegocioService.generarReporte(ReporteTipoNegocioService.consultar(filtro));
	}
	
	
	public static List<TipoNegocio> consultar(Filtro filtro){
		return ReporteTipoNegocioService.consultar(filtro);
	}
}
