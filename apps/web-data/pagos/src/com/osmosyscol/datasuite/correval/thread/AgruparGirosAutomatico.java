package com.osmosyscol.datasuite.correval.thread;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.correval.grupogiro.GrupoGiroAutomaticoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

/**
 * 
 * Realiza la agrupacion de giros.. y generacion de archivo a carga r en el banco de forma automatica
 * 
 * @author efperez
 *
 */
public class AgruparGirosAutomatico implements Runnable {

	public void run() {

		try {
			Thread.sleep(2 * 60000); // Espera dos minutos antes de iniciar
		} catch (InterruptedException e1) {
			return;
		}

		try {

			while (true) {
				if (ControlHora.esHoraDisponible()) {
					SimpleLogger.setInfo("Iniciando servicio de generacion de grupos de giro automatico ");
					GrupoGiroAutomaticoServicio.generarArhivos();
				}

				Thread.sleep(3 * 60000); // Espera 3 minutos..
			}

		} catch (Throwable e) {
			SimpleLogger.setError("No se puede inicializar de generacion de grupos de giro automatico", e);
		}

	}

	// *************************************************************************************************************

	public static class ControlHora {

		private static int hora_inicio = 8; // desde 8am
		private static int hora_fin = 20; // hasta 8pm

		// TODO: VALIDAR QUE NO SE REALICE LOS DIAS SABADOS Y DOMINGOS

		public static boolean esHoraDisponible() {

			Calendar hoy = Calendar.getInstance();

			// si es sabado o domingo no es permitido
			{
				int dia = hoy.get(Calendar.DAY_OF_WEEK);

				if (dia == Calendar.SUNDAY || dia == Calendar.SATURDAY) {
					// return false; //SE DESACTIVA
				}
			}

			// si esta fuera dew horario no es permitido
			{
				int hora = hoy.get(Calendar.HOUR_OF_DAY);
				System.out.println("Hora actual del servidor: " + hora);

				if (hora < hora_inicio || hora >= hora_fin) {
					// return false; //SE DESACTIVA
				}
			}

			// si es festivo no es permitido
			{
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				String date_formated = format.format(new Date());

				String sql = "select count(*) from $FESTIVOS$ where $FESTIVOS.FESTIVO$ = $T('" + date_formated + "')$";

				sql = RDServicio.reemplazarNombres(sql);

				SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

				Integer cantidadResultados = sqlDao.selectSQLNumber(sql);

				if (cantidadResultados > 0) {
					// return false; //SE DESACTIVA
				}
			}

			return true;
		}

	}

}
