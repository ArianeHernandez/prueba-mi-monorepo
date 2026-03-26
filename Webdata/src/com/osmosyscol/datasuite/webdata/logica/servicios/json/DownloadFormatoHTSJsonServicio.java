package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import com.osmosyscol.datasuite.webdata.logica.servicios.FormatoHTSService;


public class DownloadFormatoHTSJsonServicio {

	public static Integer downloadFormatoHTS(){	
		return FormatoHTSService.generarFormato();
	}
	
	
//	public static List<TipoNegocio> downloadFormatoHTS(){
//		return ReporteTipoNegocioService.consultar();
//	}
}

