package com.osmosyscol.datasuite.correval.thread;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.correval.listascautelares.ApplicationServicesBindingStub;
import com.osmosyscol.datasuite.correval.listascautelares.Resultado;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.datasuite.webdata.utils.ORASQLUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ListaCautela implements Runnable {

	public void run() {

		try {
			Thread.sleep(60000);
		} catch (InterruptedException e1) {
			return;
		}

		try {
			String urlLista = ParametrosInicio.getProperty("ws_listacautela.endpoint");
			if (StringUtils.esVacio(urlLista)) {
				SimpleLogger.setInfo("No existe configuracion para ejecucion de Listas de Cautela");
				return;
			}

			SimpleLogger.setInfo("Iniciando Cron de Listas de Cautela");

			URL url = new URL(urlLista);

			// determina porcentaje de coincidencia
			String coincidencia = ParametrosInicio.getProperty("ws_listacautela.coincidencia");

			if (StringUtils.esVacio(coincidencia)) {
				coincidencia = "80";
			}

			SimpleLogger.setInfo("Coincidencia en la lista de cautela de " + coincidencia + "%");

			while (!AutenticacionServicio.SHUTDOWN) {

				List<PersonaValidacion> personas = null;

				try {

					ApplicationServicesBindingStub a = new ApplicationServicesBindingStub(url, null);

					SimpleLogger.setDebug("Lista de cautela: Cargando listado de personas...");

					personas = obtenerSiguientesPersonas();

					if (personas != null && personas.size() > 0) {

						int errores = 0;

						SimpleLogger.setDebug("Lista de cautela: personas encontradas..." + personas.size());

						for (PersonaValidacion persona : personas) {

							try {

								Boolean encontrado = false;

								SimpleLogger.setDebug("AutenticacionServicio.SHUTDOWN: " + AutenticacionServicio.SHUTDOWN);

								if (persona.getNombre() != null) {
									SimpleLogger.setDebug("Lista de cautela: validando..." + persona.getNombre());

									Resultado resp_nombre = a.f_VERIFICATERCERO(persona.getNombre().trim().toUpperCase(), "2", coincidencia);
									encontrado = "S".equalsIgnoreCase(resp_nombre.getEncontrado());
								}

								if (persona.getDocumento() != null && !encontrado) {
									SimpleLogger.setDebug("Lista de cautela: validando..." + persona.getDocumento());
									Resultado resp_documento = a.f_VERIFICATERCERO(persona.getDocumento().trim(), "1", null);
									encontrado = "S".equalsIgnoreCase(resp_documento.getEncontrado());
								}

								SimpleLogger.setDebug("Lista de cautela: encontrado..." + encontrado);
								actualizarEstado(persona, encontrado);

								// --

								if (AutenticacionServicio.SHUTDOWN) {
									return;
								}

							} catch (Throwable e) {
								SimpleLogger.setError("Error al validar Persona: " + persona.getNombre() + " : " + persona.getDocumento(), e);
								errores++;
							}

						}

						if (errores == personas.size()) {
							throw new Exception("Lista de cautela: Multiples errores ocurridos..");
						}

					} else {
						SimpleLogger.setDebug("Lista de cautela: No se han encontrado personas ha validar.");
					}

				} catch (Throwable e) {
					SimpleLogger.setError("Error al realizar validacion de lista de cautela, reintentando en 1 hora.", e);
					Thread.sleep(3600000);
				}

				if (personas == null) {
					Thread.sleep(15 * 60 * 1000);
				}

			}

		} catch (InterruptedException e) {
			SimpleLogger.setInfo("Finalizando Hilo de Lista de Cautela");
			return;
		} catch (Throwable e) {
			SimpleLogger.setError("No se puede inicializar el servicio para listas de cautela.", e);
		}

	}

	private void actualizarEstado(PersonaValidacion persona, Boolean encontrado) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager("CREADATOS");
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			String sql = "update t1300 set c1860 = '" + Crypto.E(encontrado) + "' where id = " + persona.getId_registro();

			cargaDao.updateSQL(sql);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al actualizar el estado de la validacion de la lista de cautela.", e);
		}

	}

	private static String cleanText(String d) {
		return StringUtils.trimToEmpty(StringUtils.reemplazarCaracteresEspecialesParaNombre(d));
	}

	private List<PersonaValidacion> obtenerSiguientesPersonas() {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager("CREADATOS");
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			String c = "select ID, " + //
					" $beneficiario.numero de documento$ NUM, " + //
					" $beneficiario.primer nombre$ N1, " + //
					" $beneficiario.segundo nombre$ N2, " + //
					" $beneficiario.primer apellido$ A1, " + //
					" $beneficiario.segundo apellido$ A2" + //
					" from $beneficiario$ " + //
					" where $beneficiario.validacion persona$ is null " + //
					" order by id desc ";

			c = ORASQLUtils.addPaginacion(c, 1, 500);

			String sql = RDServicio.reemplazarNombres(c);

			List<Map<String, Object>> registros = cargaDao.selectSql(sql);

			if (registros != null && registros.size() > 0) {

				List<PersonaValidacion> resp = new ArrayList<PersonaValidacion>();

				for (Map<String, Object> registro : registros) {

					try {

						PersonaValidacion persona = new PersonaValidacion();
						persona.setId_registro(Integer.parseInt(registro.get("ID").toString()));
						persona.setDocumento(Crypto.DF(registro.get("NUM")).toString());

						String n1 = cleanText((String) Crypto.DF(registro.get("N1")));
						String n2 = cleanText((String) Crypto.DF(registro.get("N2")));
						String a1 = cleanText((String) Crypto.DF(registro.get("A1")));
						String a2 = cleanText((String) Crypto.DF(registro.get("A2")));

						String nombre = StringUtils.trim(n1 + " " + n2 + " " + a1 + " " + a2);

						persona.setNombre(nombre);

						resp.add(persona);

					} catch (Exception e) {
						SimpleLogger.setWarn("Error al obtener la indormacion del tercero al realizar validacion de listas de cautela.", e);
					}

				}

				return resp;

			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}

}
