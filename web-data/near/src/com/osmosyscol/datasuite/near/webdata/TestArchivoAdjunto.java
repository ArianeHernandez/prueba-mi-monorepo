package com.osmosyscol.datasuite.near.webdata;

import com.osmosyscol.datasuite.mein.servicios.RadicacionAutoOficioServicio;
import com.osmosyscol.datasuite.servlet.InitApp;

public class TestArchivoAdjunto {

	public static void main(String[] args) {
		
		InitApp.startUp();
		
		RadicacionAutoOficioServicio.getInstance().actualizarTipoArchivoDocumentoSalida(114054);
		
		System.exit(0);
		
	}
	
}
