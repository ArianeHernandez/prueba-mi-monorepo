package com.osmosyscol.datapi.logica.servicios;

import com.osmosyscol.datapi.constantes.VariablesAplicacion;
import com.osmosyscol.datapi.logica.dto.ServicioDataPi;


public class AppServicio {
	private static AppServicio appServicio;

	public static AppServicio getInstance() {
		if (appServicio == null)
			appServicio = new AppServicio();
		return appServicio;
	}

	private AppServicio() {
	};

	// -------------------------------------------------

	public ServicioDataPi obtenerServicioDataPi(String nombre) {
		ServicioDataPi servicioDataPi = VariablesAplicacion.getInstance().getListadoServicios().get(nombre);
		return servicioDataPi;
	}

}
