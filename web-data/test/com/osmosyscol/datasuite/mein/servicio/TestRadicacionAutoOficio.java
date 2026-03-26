package com.osmosyscol.datasuite.mein.servicio;

import java.util.Map;

import com.osmosyscol.datasuite.mein.builder.RadicacionAutoOficioFetchBuilder;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;

public class TestRadicacionAutoOficio {

	public static void main(String[] args) {
		
		InitApp.startUp();
		
		RadicacionAutoOficio radicacion = RadicacionAutoOficioFetchBuilder.newInstance().withIdCarga(113343).fetchRadicacion().fetchRadicacion_Deudor().fetchRadicacion_Deudor_Municipio().getRadicacion();
		
		System.out.println(" - " + radicacion.getDeudor().getTelefono());
		
		Map<String, Object> datos = SolicitudEnrolamientoServicio.getInstance().obtenerEnrolamientoRepLegal(radicacion.getDeudor().getDatos_representante());
		
		System.out.println("Finaliza");
		
		System.exit(0);
		
	}
	
}
