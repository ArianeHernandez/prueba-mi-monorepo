package com.osmosyscol.datasuite.correval.thread;

import java.io.File;
import java.net.URL;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ActualizadorCRL implements Runnable {

	public void run() {

		try {
			Thread.sleep(30000);
		} catch (InterruptedException e1) {
			return;
		}

		try {
			String str_filecrl = ParametrosInicio.getProperty("firma.RutaArchivoCRL");
			String str_urlcrl = ParametrosInicio.getProperty("firma.RutaURLActualizacionCRL");
			String str_intervaloAct = ParametrosInicio.getProperty("firma.intervaloActCRL");

			Integer intervaloAct = StringUtils.esVacio(str_intervaloAct) ? (60 * 60 * 1000) : (new Integer(str_intervaloAct) * 60 * 60 * 1000);

			if (!StringUtils.esNoVacio(str_filecrl, str_urlcrl)) {
				SimpleLogger.setInfo("No existe configuracion para actualizacion de CRLs");
				return;
			}

			SimpleLogger.setInfo("Iniciando Actualizacion de CRL");

			while (!AutenticacionServicio.SHUTDOWN) {

				try {

					SimpleLogger.setInfo("Actualizando CRL: [" + str_urlcrl + "] to [" + str_filecrl + "]");

					if (FileUtils.copyURLToFile(new URL(str_urlcrl), new File(str_filecrl))) {
						SimpleLogger.setInfo("Actualizacion de CRL Exitosamente");
					}

				} catch (Exception e) {
					SimpleLogger.setError("Actualizar CRL", e);
				}

				try {
					Thread.sleep(intervaloAct);
				} catch (Exception e) {
					SimpleLogger.setInfo("Ha finalizado el servicio de ActualizadorCRL");
					return;
				}
			}

		} catch (Throwable e) {
			SimpleLogger.setError("No se puede inicializar el servicio para listas de cautela.", e);
		}

	}

}
