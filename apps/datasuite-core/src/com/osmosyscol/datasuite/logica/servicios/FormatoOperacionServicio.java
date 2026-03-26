package com.osmosyscol.datasuite.logica.servicios;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cocoon.environment.Session;
import org.apache.commons.collections.CollectionUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.FormatoOperacion;
import com.osmosyscol.datasuite.logica.operaciones.Operacion;
import com.osmosyscol.datasuite.logica.operaciones.OperacionContar;
import com.osmosyscol.datasuite.logica.operaciones.OperacionPromediar;
import com.osmosyscol.datasuite.logica.operaciones.OperacionSumar;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.persistencia.dao.FormatoOperacionDao;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class FormatoOperacionServicio {

	private static FormatoOperacionServicio instance;

	private Map<Long, Operacion> operaciones;

	public static final long OPERACION_CONTAR = 1;
	public static final long OPERACION_SUMAR = 2;
	public static final long OPERACION_PROMEDIAR = 3;

	private FormatoOperacionServicio() {

		operaciones = new HashMap<Long, Operacion>();

		operaciones.put(OPERACION_CONTAR, new OperacionContar());
		operaciones.put(OPERACION_SUMAR, new OperacionSumar());
		operaciones.put(OPERACION_PROMEDIAR, new OperacionPromediar());

	}

	public static FormatoOperacionServicio getInstance() {
		if (instance == null) {
			instance = new FormatoOperacionServicio();
		}
		return instance;
	}

	public List<FormatoOperacion> obtenerOperacionesPorFormato(Integer id_formato) {

		try {

			if (id_formato == null) {
				return null;
			}
			FormatoOperacionDao formatoOperacionDao = (FormatoOperacionDao) DaoConfig.getDao(FormatoOperacionDao.class);

			return formatoOperacionDao.obtenerOperacionesPorFormato(id_formato);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return null;
	}

	public List<FormatoOperacion> obtenerOperacionesPorCarga(Integer id_carga) {

		try {

			if (id_carga == null) {
				return null;
			}
			FormatoOperacionDao formatoOperacionDao = (FormatoOperacionDao) DaoConfig.getDao(FormatoOperacionDao.class);

			return formatoOperacionDao.obtenerOperacionesPorCarga(id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return null;
	}

	/**
	 * Se guardan las operaciones del formato en un transaccion, solo se guarda un FormatoOperacion si tiene id de campo y id de lista dinamica
	 * 
	 * @param daoManager
	 * @param formato
	 * @throws SQLException
	 */
	public void guardarFormatoOperacionesTrans(DaoManager daoManager, Formato formato) throws SQLException {

		FormatoOperacionDao formatoOperacionDao = (FormatoOperacionDao) daoManager.getDao(FormatoOperacionDao.class);

		formatoOperacionDao.eliminarPorFormato(formato.getId_formato());

		if (!formato.getOperaciones().isEmpty()) {
			for (FormatoOperacion formatoOperacion : formato.getOperaciones()) {
				formatoOperacion.setId_formato(formato.getId_formato().longValue());
				if (formatoOperacion.getId_formato_operacion() == null) {
					Long id_formato_operacion = formatoOperacionDao.obtenerSiguienteId();
					formatoOperacion.setId_formato_operacion(id_formato_operacion);
				}
				if (formatoOperacion.getId_campo() != null && formatoOperacion.getId_listadinamica() != null) {
					formatoOperacionDao.insert(formatoOperacion);
				}
			}
		}

	}

	/**
	 * Calcula los valores por cada Operacion asociada al formato y los guarda en el campo correspondiente, se respeta el tipo del campo, si el tipo de campo no corresponde puede generar error.
	 * 
	 * La operación es transaccional si falla al guardar alguna de las operaciones genera excepciones.
	 * 
	 * @param id_carga
	 * @param id_formato
	 * @author oaortiz
	 */
	public Boolean calcularValorPorCargaTrans(Integer id_carga, Integer id_formato, Session session, DaoManager daoManager) {

		// Se obtiene las operaciones por formato (parametro o formato con el cual se hizo la carga)
		List<FormatoOperacion> formatoOperaciones = null;
		if (id_formato == null) {
			formatoOperaciones = obtenerOperacionesPorCarga(id_carga);
		} else {
			formatoOperaciones = obtenerOperacionesPorFormato(id_formato);
		}

		if (CollectionUtils.isNotEmpty(formatoOperaciones)) {

			// se itera cada operacion para calcular el valor.
			for (FormatoOperacion formatoOperacion : formatoOperaciones) {

				Long listaDinamica = formatoOperacion.getId_listadinamica();

				Integer idCampo = formatoOperacion.getId_campo().intValue();

				Campo campo = CampoServicio.getInstance().obtenerCampo(idCampo);

				// Se obtienen los registros de la carga
				String sqlObtenerRegistrosCarga = "select id from T" + campo.getId_estructura() + " where idcarga = " + id_carga;

				SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

				List<Integer> registros = sqlDao.selectSQLNumberList(sqlObtenerRegistrosCarga);

				if (CollectionUtils.isNotEmpty(registros)) {

					// Se itera cada registro de la carga
					for (Integer idRegistro : registros) {

						String parametros = "[" + idRegistro + "]";

						// Se calcula el valor de la lista dinamica pasando como parametro el id del registro
						List<ValorLista> valoresLista = ListaDinamicaCampoServicio.getInstance().obtenerValoresListaDinamicaParams(listaDinamica.intValue(), parametros, session);

						List<BigDecimal> valores = new ArrayList<BigDecimal>();

						if (CollectionUtils.isNotEmpty(valoresLista)) {
							for (ValorLista valorLista : valoresLista) {
								String valorStr = Crypto.D(valorLista.getId()).toString();
								BigDecimal valor = new BigDecimal(valorStr);
								valores.add(valor);
							}

						}

						Operacion operacion = operaciones.get(formatoOperacion.getId_operacion());

						BigDecimal total = operacion.calcular(valores);

						// Se actualiza el campo referenciado en la operacion
						DatosEstructuraServicio.getInstance().actualizarCampoEstructuraPorIDTrans(idRegistro, formatoOperacion.getId_campo().intValue(), total.toString(), daoManager);

					}

				}

			}

		}

		return true;

	}

	/**
	 * Versión no transaccional del metodo {@link calcularValorPorCargaTrans}
	 * 
	 * @param id_carga
	 * @param id_formato
	 * @param session
	 * @return
	 */
	public Boolean calcularValorPorCarga(Integer id_carga, Integer id_formato, Session session) {

		DaoManager daoManager = null;
		try {

			daoManager = DaoConfig.getDaoManager(2);
			daoManager.startTransaction();

			Boolean rta = calcularValorPorCargaTrans(id_carga, id_formato, session, daoManager);
			daoManager.commitTransaction();
			
			// TODO: No se debe ejecutar si no hay validaciones relacionadas 
			calcularValoresPorCargaRelacionada(id_carga, id_formato, session);

			return rta;
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}

		return false;

	}

	/**
	 * Actualiza el valor de los registros de otras estructuras de acuerdo al formato con el cual el registro fue cargado
	 * 
	 * @param id_carga
	 * @param id_formato
	 * @param session
	 */
	private void calcularValoresPorCargaRelacionada(Integer id_carga, Integer id_formato, Session session) {

		DaoManager daoManager = null;

		try {

			FormatoOperacionDao formatoOperacionDao = (FormatoOperacionDao) DaoConfig.getDao(FormatoOperacionDao.class);

			// Campos que se incluyen en el formato los cuales son de tipo estructura
			List<Map<String, Object>> camposTipoEstructura = formatoOperacionDao.obtenerCamposTipoEstructura(id_formato);

			Set<Integer> idsCargasRelacionadasIntegers = new HashSet<Integer>();

			if (CollectionUtils.isNotEmpty(camposTipoEstructura)) {

				// Se itera cada campo de tipo estructura
				for (Map<String, Object> map : camposTipoEstructura) {

					Integer id_estructuraRef = Integer.valueOf(map.get("ID_ESTRUCTURA_REF").toString());

					Integer id_campo = Integer.valueOf(map.get("ID_CAMPO").toString());

					Integer id_estructura = Integer.valueOf(map.get("ID_ESTRUCTURA").toString());

					if (id_estructuraRef != null && id_campo != null) {

						// Se obtienen los registros que fueron referenciados en la carga actual y corresponden a registros ya cargados de otras estructuras.
						obtenerCargasRelacionadas(id_estructura, id_campo, id_estructuraRef, id_carga, idsCargasRelacionadasIntegers);

					}

				}

			}

			if (idsCargasRelacionadasIntegers != null) {
				daoManager = DaoConfig.getDaoManager(2);
				daoManager.startTransaction();
				for (Integer idCargaRelacionada : idsCargasRelacionadasIntegers) {

					// Se actualizan los valores de las cargas que se
					calcularValorPorCargaTrans(idCargaRelacionada, null, session, daoManager);
					daoManager.commitTransaction();

				}
			}

		} catch (Throwable e) {
			SimpleLogger.setError("Error", e);
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}

	}

	/**
	 * Retorna el id de carga de los registros que fueron referenciados.
	 * 
	 * @param id_estructura
	 * @param id_campo
	 * @param id_estructuraReferenciada
	 * @param id_carga
	 * @param idsCargasRelacionadasIntegers2
	 * @return
	 */
	private Set<Integer> obtenerCargasRelacionadas(Integer id_estructura, Integer id_campo, Integer id_estructuraReferenciada, Integer id_carga, Set<Integer> idsCargasRelacionadasIntegers) {

		try {

			String sqlObtenerIdsRegistros = "select C" + id_campo + " from t" + id_estructura + " where idcarga = " + id_carga;

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			List<Integer> idsRegistrosRelacionados = sqlDao.selectSQLNumberList(sqlObtenerIdsRegistros);

			if (CollectionUtils.isNotEmpty(idsRegistrosRelacionados)) {

				for (Integer idRegistroRelacionado : idsRegistrosRelacionados) {

					String sqlObtenerCargaRelacionadas = "select idcarga from t" + id_estructuraReferenciada + " where id = " + idRegistroRelacionado;

					Integer idCarga = sqlDao.selectSQLNumber(sqlObtenerCargaRelacionadas);

					if (idCarga != null) {
						idsCargasRelacionadasIntegers.add(idCarga);
					}

				}
				return idsCargasRelacionadasIntegers;
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}

		return null;
	}

}
