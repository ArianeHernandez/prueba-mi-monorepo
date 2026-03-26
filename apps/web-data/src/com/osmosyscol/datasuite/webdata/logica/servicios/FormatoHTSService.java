package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.io.File;

import com.osmosyscol.commons.servlet.ContextInfo;
import com.osmosyscol.datasuite.webdata.servlet.DescargaArchivoServlet;

public class FormatoHTSService {

	// ---------------------------------------------------------------

//	private static FormatoHTSService instance;
	
	private static FormatoHTSService instance = new FormatoHTSService();

	private FormatoHTSService() {
	}

	public static FormatoHTSService getInstance() {
		return instance;
	}

	public static Integer  generarFormato() {
		File fileXLSX = new File(ContextInfo.getInstance().getDiskPathForResource("resources/files/formato/FormatoHTS.xlsx"));
		return DescargaArchivoServlet.almacenarArchivo(fileXLSX);
	}
}