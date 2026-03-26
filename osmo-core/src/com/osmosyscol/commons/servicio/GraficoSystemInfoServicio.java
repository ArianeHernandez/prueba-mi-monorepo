package com.osmosyscol.commons.servicio;

import java.util.ArrayList;
import java.util.List;

import com.osmosyscol.datapi.logica.dto.GraficoSystemInfoDto;

public class GraficoSystemInfoServicio extends Thread {

	private static GraficoSystemInfoServicio instacia = new GraficoSystemInfoServicio();

	private GraficoSystemInfoServicio() {
	}

	public static GraficoSystemInfoServicio getInstance() {
		return instacia;
	}

	private List<GraficoSystemInfoDto> memoria = new ArrayList<GraficoSystemInfoDto>();

	private long maximoSesiones = 0;

	public void run() {

		try {
			while (!AutenticacionServicio.SHUTDOWN) {

				GraficoSystemInfoDto gsi = new GraficoSystemInfoDto();

				memoria.add(gsi);

				if (gsi.getSesiones_activas() > maximoSesiones) {
					maximoSesiones = gsi.getSesiones_activas();
				}

				if (memoria.size() > 4000) {
					memoria.remove(0);
				}

				sleep(20000);
			}

		} catch (Throwable e) {
			return;
		}

	}

	public List<GraficoSystemInfoDto> getDatos() {
		return memoria;
	}

	public long getMaximoSesiones() {
		return maximoSesiones;
	}
}
