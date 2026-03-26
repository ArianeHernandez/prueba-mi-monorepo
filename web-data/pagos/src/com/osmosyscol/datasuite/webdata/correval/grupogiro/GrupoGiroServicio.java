package com.osmosyscol.datasuite.webdata.correval.grupogiro;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.mutable.MutableInt;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.dto.ReporteDinamico;
import com.osmosyscol.datasuite.logica.pagos.AccionArchivo;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.logica.servicios.ReporteDimServicio;
import com.osmosyscol.datasuite.pagos.acciones.movimiento_tesoreria.EnviarMovimientoTesoreria;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.dto.ResultadoConsulta;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBancoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class GrupoGiroServicio {

	public static final String ATRIBUTO_MENSAJE = "crear_archivo_mensaje";

	private static GrupoGiroServicio instance;

	private GrupoGiroDao grupoGiroDao;

	private HashMap<String, MutableInt> mapArchivosBanco = new HashMap<String, MutableInt>();

	private String fechaMapaArchivos = "";

	private SimpleDateFormat formatFechaDia = new SimpleDateFormat("yyyy/MM/dd");

	private String REPORTE_REGISTROS_RETIRO = "gruposgiro_registros_simple";

	private String REPORTE_GRUPOSGIRO = "agrupar_registros_simple";

	private GrupoGiroServicio() {

		grupoGiroDao = new GrupoGiroDao();

	}

	public static GrupoGiroServicio getInstance() {
		if (instance == null) {
			instance = new GrupoGiroServicio();
		}
		return instance;
	}

	/***
	 * 
	 * Retorna las cargas de la <b>estructura</b> en estado subido de la fecha actual y las que no tienen un GRUPO GIRO
	 * 
	 * @param estructura
	 *            - nombre de la estructura
	 * @return
	 * 
	 * 		Lista con información básica de las cargas
	 * 
	 * @author oaortizs
	 */

	private Integer obtenerIdEstructura(String estructura) {
		RDServicio.verificarData();
		estructura = estructura.trim().toLowerCase();
		return RDServicio.ESTRUCTURAS.get(estructura);
	}

	public List<GrupoGiroCarga> obtenerCargas() {
		try {

			List<GrupoGiroCarga> listado = new ArrayList<GrupoGiroCarga>();

			String sql = " SELECT t1.idcarga FROM T1340 t1 " //
					+ " inner join ts01 h   on t1.idcarga = h.id_carga   and 'C' = h.estado "//
					+ " WHERE t1.C1804 = $S(ACH)$" //
					+ " and ($RETIROS.ESTADO$ is null or $RETIROS.ESTADO$ <> $S(F)$ )" //
					+ " and t1.C2223 is null and t1.C2224 is null" //
					+ " and (SELECT count(1) FROM T1220 WHERE t1.id = C1261_NUM and C1542 is not null) = 0 " //
					+ " group by t1.idcarga";

			sql = RDServicio.reemplazarNombres(sql);

			SimpleLogger.setDebug("sql -> " + sql);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			List<Integer> ids_cargas = sqlDao.selectSQLNumberList(sql);

			if (ids_cargas == null || ids_cargas.isEmpty()) {
				return listado;
			}

			// ---

			CargaDao cargaDao = (CargaDao) DaoConfig.getDao(CargaDao.class);

			Integer id_estructura = obtenerIdEstructura("RETIROS");

			for (Integer id_carga : ids_cargas) {
				Carga carga = cargaDao.obtener(id_carga);

				if (carga != null) {
					GrupoGiroCarga grupoGiroCarga = new GrupoGiroCarga();
					grupoGiroCarga.setId_carga(carga.getId_carga());
					grupoGiroCarga.setTipo_carga(carga.getId_tipocarga());
					grupoGiroCarga.setId_estructura(id_estructura);
					grupoGiroCarga.setValorTotal(carga.getValor_total_bigdecimal());
					listado.add(grupoGiroCarga);
				}
			}

			return listado;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}

	/**
	 * Los grupos de giro se guardan por comandos para optimizar el envío del formulario, los comandos se deben ejecutar en orden,
	 * 
	 * @param comandos
	 * @return
	 */
	public Boolean guardarGruposGiroPorComandos(List<Comando> comandos, Session session) {

		try {

			SimpleLogger.setInfo("Guardando Grupos de giro: " + comandos);

			ReporteDinamico salida = ReporteDimServicio.getInstance().crearSubReporte(REPORTE_REGISTROS_RETIRO, "", session);

			if (CollectionUtils.isEmpty(comandos) || salida == null) {

				SimpleLogger.setInfo("Guardando Grupos de giro: Sin Comandos");
				return false;
			}

			GrupoGiroEstado grupoGiroEstado = new GrupoGiroEstado();
			grupoGiroEstado.setPorcentaje(0);

			session.setAttribute("estado_grupo_giro", grupoGiroEstado);

			HiloGruposGiro hilo = new HiloGruposGiro(comandos, salida.getIdReporte(), session, grupoGiroEstado);

			new Thread(hilo).start();

			return true;

		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	/**
	 * Se ejecuta la instrucción del comando en los registros necesarios
	 * 
	 * @param registros
	 *            - mapa para insertar o actualizar registros
	 * @param comando
	 *            - comando por carga
	 * @param resultado
	 *            - datos de los registros de la carga
	 * @param grupoGiroEstado
	 *            - para actualizar el estado de la operación (el usuario ve la información por jsonrpc)
	 * @param proximoAvance
	 *            - para calcular el porcentaje
	 */
	void convertirComandoCarga(Map<Integer, GrupoGiro> registros, Comando comando, ResultadoConsulta resultado, GrupoGiroEstado grupoGiroEstado, Integer proximoAvance) {

		List<Map<String, Object>> datos = resultado.getDatos();

		Integer porcentajeBase = grupoGiroEstado.getPorcentaje();

		Integer i = 0;
		Integer porcentaje;

		for (Map<String, Object> map : datos) {
			Integer id = (Integer) map.get("id_registro");

			// Si el registro fue modificado primero
			if (registros.containsKey(id)) {
				GrupoGiro registro = registros.get(id);
				if (comando.getCuenta() != null) {
					registro.setCuenta(comando.getCuenta());
				}
				registro.setFecha_pago(comando.getFecha_pago());
			} else if (comando.getFecha_pago() != null) {

				GrupoGiro registro = new GrupoGiro();
				registro.setFecha_pago(comando.getFecha_pago());
				registro.setCodigo_registro(id);

				Integer cuenta_defecto = (Integer) map.get("cuenta_id_defecto");
				Integer id_cuenta = (Integer) map.get("id_cuenta");

				// Si se define cuenta para todos los registros
				if (comando.getCuenta() != null) {
					registro.setCuenta(comando.getCuenta());
					registros.put(registro.getCodigo_registro(), registro);
				} else if (id_cuenta != null) {
					registro.setCuenta(id_cuenta);
					registros.put(registro.getCodigo_registro(), registro);
				}
				// Si tiene cuenta por defecto
				else if (cuenta_defecto != null) {
					registro.setCuenta(cuenta_defecto);
					registros.put(registro.getCodigo_registro(), registro);
				}
				// Los demás registros no tienen cuenta, no se guardan

			}

			i++;
			porcentaje = i * proximoAvance / datos.size();
			grupoGiroEstado.setPorcentaje(porcentajeBase + porcentaje);
		}

	}

	/**
	 * 
	 * Se ejecuta la instrucción del comando en un registro
	 * 
	 * @param registros
	 *            - mapa para insertar o actualilzar el registro
	 * @param comando
	 *            - comando por registro
	 */
	void convertirComandoARegistro(Map<Integer, GrupoGiro> registros, Comando comando) {

		if (comando.getFecha_pago() != null && comando.getCuenta() != null) {

			GrupoGiro registro = null;
			if (registros.containsKey(comando.getCodigo_registro())) {
				registro = registros.get(comando.getCodigo_registro());
			} else {
				registro = new GrupoGiro();
			}

			registro.setCodigo_registro(comando.getCodigo_registro());
			registro.setCuenta(comando.getCuenta());
			registro.setFecha_pago(comando.getFecha_pago());
			registro.setId_carga(comando.getId_carga());
			registros.put(comando.getCodigo_registro(), registro);
		} else if (registros.containsKey(comando.getCodigo_registro())) {
			registros.remove(comando.getCodigo_registro());
		}

	}

	/**
	 * Se guarda la relación del registro de una carga con un GRUPO GIRO
	 * 
	 * 
	 * @param registrosGrupoGiro
	 *            - registros a guardar
	 * @param idPersona
	 *            - persona que crea el GRUPO GIRO
	 * @return exito
	 * 
	 * @author oaortizs
	 */
	public Boolean guardarGruposGiro(List<GrupoGiro> registrosGrupoGiro) {

		DaoManager daoManager = null;

		try {

			daoManager = DaoConfig.getDaoManager(2);

			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			daoManager.startTransaction();

			Map<Integer, GrupoGiro> registros = new HashMap<Integer, GrupoGiro>();

			for (GrupoGiro grupoGiroRegistro : registrosGrupoGiro) {

				if (grupoGiroRegistro.getFecha_pago() != null && grupoGiroRegistro.getCuenta() != null) {

					// Registros enviados desde el cliente
					if (grupoGiroRegistro.getCodigo_registro() != null) {
						registros.put(grupoGiroRegistro.getCodigo_registro(), grupoGiroRegistro);

					} else if (grupoGiroRegistro.getId_carga() != null) {
						// Cuando el cliente marca los valores para la carga
						List<Integer> registrosCarga = cargaDao.obtenerIDsRegistrosPorCarga(grupoGiroRegistro.getId_carga(), grupoGiroRegistro.getId_estructura());

						for (Integer id : registrosCarga) {
							if (!registros.containsKey(id)) {

								GrupoGiro registro = new GrupoGiro();

								registro.setFecha_pago(grupoGiroRegistro.getFecha_pago());
								registro.setCuenta(grupoGiroRegistro.getCuenta());
								registro.setCodigo_registro(id);
								registros.put(registro.getCodigo_registro(), registro);

							}
						}
					}
				}

			}

			if (registros.size() > 0) {

				Collection<GrupoGiro> registrosNuevos = registros.values();
				for (GrupoGiro grupoGiroRegistro : registrosNuevos) {
					grupoGiroDao.guardarGrupoGiroSql(grupoGiroRegistro, cargaDao, GrupoGiro.ESTADO_NUEVO);
				}

				daoManager.commitTransaction();
				return true;
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
		return false;
	}

	public void crearArchivoPorComandos(List<ComandoBanco> comandos, Session session, String persona, String nombreArchivo) {

		Mensaje mensaje = new Mensaje();

		try {
			mensaje.setEstado("V"); // Validando
			session.setAttribute(ATRIBUTO_MENSAJE, mensaje);
			if (CollectionUtils.isEmpty(comandos)) {
				mensaje.setMensaje("Debe seleccionar cargas para crear el archivo.");
				mensaje.setEstado("F"); // Finalizado
				return;
			}
			ReporteDinamico salida = ReporteDimServicio.getInstance().crearSubReporte(REPORTE_GRUPOSGIRO, "", session);

			if (salida == null) {

				mensaje.setMensaje("Error al consultar la información de las cargas.");
				mensaje.setEstado("F"); // Finalizado
				return;
			}

			List<Integer> gruposGiro = crearRegistrosArchivoPorComandos(comandos, salida, session, persona, nombreArchivo);

			if (CollectionUtils.isNotEmpty(gruposGiro)) {
				mensaje.setEstado("I"); // Iniciado

				Boolean rta = crearArchivo(gruposGiro, persona, session, nombreArchivo) != null;

				if (rta) {
					mensaje.setMensaje("Proceso finalizado exitosamente.");
				} else {
					mensaje.setMensaje("Lo sentimos, No es posible generar el archivo.");
				}
				mensaje.setExitoso(rta);
				mensaje.setEstado("F");
			} else {
				if (mensaje.getEstado() == null) {
					mensaje.setEstado("F");
				}
			}

		} catch (Throwable e) {
			mensaje.setEstado("F"); // Finalizado con errores
			mensaje.setMensaje("No es posible crear el archivo.");
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}

	private List<Integer> crearRegistrosArchivoPorComandos(List<ComandoBanco> comandos, ReporteDinamico salida, Session session, String persona, String nombreArchivo) {
		try {

			Mensaje mensaje = (Mensaje) session.getAttribute(GrupoGiroServicio.ATRIBUTO_MENSAJE);
			mensaje.setMensaje("Obteniendo información...");

			List<Integer> gruposGiro = new ArrayList<Integer>();

			// Las fechas del reporte se obtiene encriptadas, para mejorar el
			// rendimiento, solo se necesitan para comparar
			Map<String, String> fechas = new HashMap<String, String>();
			Map<String, Integer> cuentas = new HashMap<String, Integer>();

			Integer i = 0;
			for (ComandoBanco comandoBanco : comandos) {

				i++;
				Integer porcentaje = i * 40 / comandos.size();
				mensaje.setPorcentaje(porcentaje);

				if (comandoBanco.getId_grupo_giro() != null) {
					if (comandoBanco.isAgregar()) {

						String parametros = "id_registro:" + comandoBanco.getId_grupo_giro();
						ResultadoConsulta resultado = ReporteDimServicio.getInstance().obtenerDatosReporte(salida.getIdReporte(), parametros, session);
						Map<String, Object> map = resultado.getDatos().get(0);
						Integer cuenta = (Integer) map.get("cuenta_pago_id");
						String fecha = (String) map.get("fecha_pago");

						if (cuenta != null && fecha != null) {
							fechas.put("R" + comandoBanco.getId_grupo_giro(), fecha);
							cuentas.put("R" + comandoBanco.getId_grupo_giro(), cuenta);
						}

						gruposGiro.add(comandoBanco.getId_grupo_giro());

					} else {
						gruposGiro.remove(comandoBanco.getId_grupo_giro());
						fechas.remove("R" + comandoBanco.getId_grupo_giro());
						cuentas.remove("R" + comandoBanco.getId_grupo_giro());
					}
				} else if (comandoBanco.getId_banco() != null) {

					String parametros = "id_banco:" + comandoBanco.getId_banco();
					ResultadoConsulta resultado = ReporteDimServicio.getInstance().obtenerDatosReporte(salida.getIdReporte(), parametros, session);

					if (resultado != null) {
						for (Map<String, Object> map : resultado.getDatos()) {
							Integer id_grupo_giro = (Integer) map.get("id_grupo_giro");
							if (comandoBanco.isAgregar()) {

								Integer cuenta = (Integer) map.get("cuenta_pago_id");
								String fecha = (String) map.get("fecha_pago");

								if (cuenta != null && fecha != null) {
									fechas.put("B" + id_grupo_giro, fecha);
									cuentas.put("B" + id_grupo_giro, cuenta);
								}

								gruposGiro.add((Integer) map.get("id_grupo_giro"));
							} else {
								gruposGiro.remove((Integer) map.get("id_grupo_giro"));
								fechas.remove("B" + id_grupo_giro);
								cuentas.remove("B" + id_grupo_giro);
							}
						}
					}
				}

			}

			Iterator<String> iteradorFechas = fechas.values().iterator();
			Boolean valido = true;
			String fechaComparar = null;

			while (iteradorFechas.hasNext()) {
				String fecha = iteradorFechas.next();

				if (fechaComparar == null) {
					fechaComparar = fecha;
				} else {
					valido = valido && fechaComparar.equals(fecha);
				}
			}
			if (!valido) {
				mensaje.setMensaje("Debe seleccionar registros de la misma fecha");
				mensaje.setEstado("F");
				return null;
			}

			// obtiene todas las cuentas bancarias configuradas
			List<CuentaBanco> cuentasBanco = DS_SqlUtils.queryForList(CuentaBanco.class, "select * from $CUENTA - BANCO$");
			Map<Integer, String> hashCuentasBanco = new HashMap<Integer, String>();

			// genera mapa con el hash de cada cuenta
			for (CuentaBanco cuentaBanco : cuentasBanco) {
				hashCuentasBanco.put(cuentaBanco.getId(), cuentaBanco.getHash());
			}

			Iterator<Integer> iteradorCuentas = cuentas.values().iterator();
			Integer cuentaComparar = null;
			while (iteradorCuentas.hasNext()) {
				Integer cuenta = iteradorCuentas.next();
				if (cuentaComparar == null) {
					cuentaComparar = cuenta;
				} else {
					valido = valido && hashCuentasBanco.get(cuentaComparar).equals(hashCuentasBanco.get(cuenta));
				}
			}

			if (valido) {
				return gruposGiro;
			} else {
				mensaje.setMensaje("Debe seleccionar registros de la misma cuenta");
				mensaje.setEstado("F");
				return null;
			}

		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
		}
		return null;

	}

	public Integer crearArchivo(List<Integer> gruposGiro, String persona, Session session, String nombreArchivo) {

		DaoManager daoManager = null;
		if (session != null) {
			session.removeAttribute("nombre_archivo_salida_banco");
		}

		try {
			Integer porcentaje = 0;
			Mensaje mensaje = null;

			if (session != null) {
				mensaje = (Mensaje) session.getAttribute(GrupoGiroServicio.ATRIBUTO_MENSAJE);
				mensaje.setMensaje("Creando Archivo...");
				mensaje.setEstado("C"); // Creando archivo

				porcentaje = mensaje.getPorcentaje() + 5;
				mensaje.setPorcentaje(porcentaje);
			}

			if (gruposGiro == null || gruposGiro.size() == 0) {
				return null;
			}

			String nameArchivo = null;

			daoManager = DaoConfig.getDaoManager(2);
			daoManager.startTransaction();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			String ruta = ParametrosInicio.getProperty("RutaArchivosSalidaBancos");

			if (ruta != null) {

				Date fechaCreacionArchivo = HorarioServicio.getInstance().obtenerFechaActual();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

				String carpeta = dateFormat.format(fechaCreacionArchivo);

				File fileCarpeta = new File(ruta + "/" + carpeta);
				if (fileCarpeta.exists() || fileCarpeta.mkdirs()) {

					// Se consulta el numero de cuenta para construir el nombre
					// del archivo
					String sqlCuenta = "select  c.$CUENTA - BANCO.CUENTA BANCARIA$ CUENTA " + //
							" from $GRUPO GIRO$ gg , $CUENTA - BANCO$ c, $BANCO$ b" + //
							" WHERE gg.$GRUPO GIRO.CUENTA$ = c .ID" + //
							" and c.$CUENTA - BANCO.BANCO$ = b.ID" + //
							" and gg.ID =" + gruposGiro.get(0);

					sqlCuenta = RDServicio.reemplazarNombres(sqlCuenta);

					Map<String, Object> selectCuenta = cargaDao.simpleselectSql(sqlCuenta);

					String cuenta = Crypto.DF(selectCuenta.get("CUENTA")).toString();

					if (cuenta != null) {
						SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
						nameArchivo = nombreArchivo.toLowerCase() + cuenta + "_" + carpeta + "_" + timeFormat.format(fechaCreacionArchivo);
					} else {
						return null;
					}

					String ruta_archivo = ruta + "/" + carpeta + "/" + nameArchivo;

					System.out.println("GENERANDO ARCHIVO DE GRUPO DE GIRO: " + ruta_archivo);

					File archivoSalidaBanco = new File(ruta_archivo);
					if (archivoSalidaBanco.createNewFile()) {

						Integer idArchivo = crearContenidoArchivo(daoManager, gruposGiro, nameArchivo, persona, fechaCreacionArchivo);

						// 50%
						if (mensaje != null) {
							porcentaje += 5;
							mensaje.setPorcentaje(porcentaje);
						}

						if (idArchivo != null) {

							File datos = SalidaBancoServicio.getInstance().generarArchivo(idArchivo, mensaje);

							System.out.println("GENERANDO ARCHIVO DE PAGOS: " + archivoSalidaBanco.getAbsolutePath());

							FileUtils.setContentFile(archivoSalidaBanco.getAbsolutePath(), datos.toString());

							if (session != null) {
								session.setAttribute("id_archivo", idArchivo);
								session.setAttribute("nombre_archivo_salida_banco", nameArchivo);
							}

							AccionArchivo accionArchivo = new EnviarMovimientoTesoreria();
							if (!accionArchivo.ejecutar(idArchivo)) {
								System.out.println("No es Posible Enviar el Movimiento a Tesoreria");
								throw new RuntimeException("No es Posible Enviar el Movimiento a Tesoreria");
							}

							daoManager.commitTransaction();
							return idArchivo;

						} else {
							return null;
						}
					} else {

						return null;
					}

				} else {
					SimpleLogger.setError("Revisar la ruta y propiedades de la carpeta, propiedad RutaArchivosSalidaBancos: " + fileCarpeta);
					return null;
				}
			} else {
				return null;
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
		return null;
	}

	public Mensaje obtenerMensajeCreacionArchivo(Session session) {
		Mensaje mensaje = (Mensaje) session.getAttribute(GrupoGiroServicio.ATRIBUTO_MENSAJE);

		return mensaje;
	}

	private Integer crearContenidoArchivo(DaoManager daoManager, List<Integer> gruposGiro, String nombreCompletoArchivo, String persona, Date fechaCreacionArchivo) {

		try {

			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			// Se guarda el orden los archivos generados para el mismo dia
			String sqlIDBanco = "select  b.$BANCO.CODIGO$ from $GRUPO GIRO$ gg , $CUENTA - BANCO$ c, $BANCO$ b" //
					+ " WHERE gg.$GRUPO GIRO.CUENTA$ = c .ID" //
					+ " and c.$CUENTA - BANCO.BANCO$ = b.ID" //
					+ " and gg.ID =" + gruposGiro.get(0);

			sqlIDBanco = RDServicio.reemplazarNombres(sqlIDBanco);

			Map<String, Object> selectBanco = cargaDao.simpleselectSql(sqlIDBanco);

			String idBanco = Crypto.DF(selectBanco.get("C1220")).toString();

			// Se revisa si el mapa de bancos posee el banco consultado
			if (!mapArchivosBanco.containsKey(idBanco)) {
				mapArchivosBanco.put(idBanco, new MutableInt(1));
			}

			String stringFechaCreacionArchivo = formatFechaDia.format(fechaCreacionArchivo);
			if (fechaMapaArchivos.equals(stringFechaCreacionArchivo)) {
				mapArchivosBanco.get(idBanco).increment();

			} else {
				mapArchivosBanco.get(idBanco).setValue(1);
				fechaMapaArchivos = stringFechaCreacionArchivo;

			}

			// Se obtiene el siguiente ID de archivo
			Integer ordenArchivo = mapArchivosBanco.get(idBanco).intValue();
			Integer idArchivo = cargaDao.obtenerSiguienteCreadatos();

			ArchivoGrupoGiro archivoGrupoGiro = new ArchivoGrupoGiro();
			archivoGrupoGiro.setCodigo(idArchivo);
			archivoGrupoGiro.setFecha_creacion(fechaCreacionArchivo);
			archivoGrupoGiro.setPersona(persona);
			archivoGrupoGiro.setNombre(nombreCompletoArchivo);
			archivoGrupoGiro.setOrden(ordenArchivo);
			archivoGrupoGiro.setHash("");

			grupoGiroDao.guardarArchivoGrupoGiroSql(archivoGrupoGiro, cargaDao);

			Integer num_grupo = 0;
			StringBuffer idsGrupo = null;

			for (Integer idGrupoGiro : gruposGiro) {

				if (idsGrupo == null) {
					idsGrupo = new StringBuffer(idGrupoGiro + "");
				} else {
					idsGrupo.append(", " + idGrupoGiro);
				}

				num_grupo++;

				if (num_grupo == gruposGiro.size() || num_grupo % 100 == 0) {

					String update = "UPDATE $GRUPO GIRO$ SET $GRUPO GIRO.ARCHIVO$ = " + idArchivo + " where $GRUPO GIRO$.ID in (" + idsGrupo.toString() + ")";
					cargaDao.updateSQL(RDServicio.reemplazarNombres(update));

					idsGrupo = null;
				}
			}

			String selectIdsRegistros = "SELECT $GRUPO GIRO.CODIGO REGISTRO$_NUM FROM $GRUPO GIRO$ WHERE $GRUPO GIRO.ARCHIVO$ = " + idArchivo;
			grupoGiroDao.actualizarEstadoRetiro(cargaDao, GrupoGiro.ESTADO_DEBITADO, selectIdsRegistros);

			return idArchivo;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);

			return null;
		}

	}

	public Boolean actualizarFechasGruposGiro() {

		try {

			Date fechaHoy = HorarioServicio.getInstance().obtenerFechaActual();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaHoy);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			fechaHoy = calendar.getTime();
			Object fechaE = Crypto.E(fechaHoy);
			String update = "UPDATE $GRUPO GIRO$ set $GRUPO GIRO.FECHA PAGO$ = '" + fechaE + "' " + "WHERE $GRUPO GIRO$.id in " + "(select id from $GRUPO GIRO$ where $GRUPO GIRO.FECHA PAGO$ < '" + fechaE + "' and $GRUPO GIRO.ARCHIVO$ is null)";
			update = RDServicio.reemplazarNombres(update);

			SimpleLogger.setDebug("Sql -> " + update);

			DaoManager daoManager = DaoConfig.getDaoManager(2);
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			cargaDao.updateSQL(update);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;

	}

	public List<RegistroEntradaBanco> obtenerRegistrosEntradaBanco(Integer id_archivo) {

		DaoManager daoManager = DaoConfig.getDaoManager(2);

		try {
			List<RegistroEntradaBanco> listadoRegistros = new ArrayList<RegistroEntradaBanco>();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			String sql = grupoGiroDao.obtenerRegistrosGrupoGiro(id_archivo);

			SimpleLogger.setDebug(sql);

			List<Map<String, Object>> lista = cargaDao.selectSql(sql);

			for (Map<String, Object> map : lista) {
				RegistroEntradaBanco registroEntradaBanco = convertirMapARegistro(map);
				listadoRegistros.add(registroEntradaBanco);
			}

			return listadoRegistros;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error creando el registro", e);
		}

		return null;

	}

	public SalidaBanco crearSalidaBanco(Integer id_archivo) {

		DaoManager daoManager = DaoConfig.getDaoManager(2);
		try {

			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			String sql = grupoGiroDao.obtenerArchivoGrupoGiro(id_archivo);

			SimpleLogger.setDebug(sql);

			List<Map<String, Object>> lista = cargaDao.selectSql(sql);

			if (lista != null && lista.size() == 1) {
				SalidaBanco salidaBanco;
				salidaBanco = convRegistroASalidaBanco(lista.get(0));
				List<RegistroEntradaBanco> registro = obtenerRegistrosEntradaBanco(id_archivo);
				salidaBanco.setRegistro(registro);
				return salidaBanco;
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error creando el registro", e);
		}

		return null;
	}

	private SalidaBanco convRegistroASalidaBanco(Map<String, Object> map) {
		SalidaBanco salidaBanco = new SalidaBanco();

		String dato = null;
		Long numero = null;
		Long numero_prin = null;
		Date fecha = null;
		Date fecha_creacion = null;
		Integer orden = null;

		dato = convDatoString(map.get("CODIGO_BANCO"));
		salidaBanco.setCod_banco_empresa(dato);

		fecha = convDatoDate(map.get("FECHA"));
		salidaBanco.setFecha_grupo(fecha);
		
		fecha_creacion = convDatoDate(map.get("FECHA_CREACION"));
		salidaBanco.setFecha_creacion(fecha_creacion);

		numero = convDatoNumero(map.get("CUENTA"));
		salidaBanco.setNum_cuenta_empresa(numero);

		numero_prin = convDatoNumero(map.get("CUENTA_PRINCIPAL"));
		salidaBanco.setNum_cuenta_principal_empresa(numero_prin);

		dato = convDatoString(map.get("TIPO_CUENTA"));
		salidaBanco.setTipo_cuenta_empresa(dato);

		orden = convDatoNumero(map.get("ORDEN")).intValue();
		salidaBanco.setNum_cargadeldia(orden);

		return salidaBanco;
	}

	private Date convDatoDate(Object object) {
		if (object == null) {
			return null;
		}
		object = Crypto.DF(object);
		return (Date) object;
	}

	private RegistroEntradaBanco convertirMapARegistro(Map<String, Object> map) {
		RegistroEntradaBanco registroEntradaBanco = new RegistroEntradaBanco();
		String dato = null;
		Long numero = null;

		dato = convDatoString(map.get("IDCARGA"));
		registroEntradaBanco.setId_carga(dato);

		dato = convDatoString(map.get("OBSERVACION"));
		registroEntradaBanco.setObservacion(dato);

		dato = convDatoString(map.get("CODIGO_BANCO"));
		registroEntradaBanco.setCod_banco_beneficiario(dato);

		dato = convDatoString(map.get("ID"));
		registroEntradaBanco.setConsecutivo_interno(dato);

		dato = StringUtils.trimToNull(convDatoString(map.get("NOMBRE_BEN")));
		if (dato == null) {
			dato = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("BENEFICIARIO_POR_DEFECTO");
		}
		registroEntradaBanco.setNombre_beneficiario(dato);

		dato = convDatoString(map.get("CUENTA"));
		registroEntradaBanco.setNum_cuenta_beneficiario(dato);

		numero = convDatoNumero(map.get("NUM_DOC_BEN"));
		registroEntradaBanco.setNum_identificacion_beneficiario(numero);

		dato = convDatoString(map.get("TIPO_CUENTA"));
		registroEntradaBanco.setTipo_cuenta_beneficiario(dato);

		dato = convDatoString(map.get("TIPO_DOC_BEN"));
		registroEntradaBanco.setTipo_identificacion_beneficiario(dato);

		BigDecimal decimal = convDatoDecimal(map.get("VALOR"));
		registroEntradaBanco.setValor(decimal);

		dato = convDatoString(map.get("DIGITO_VERIFICACION"));
		registroEntradaBanco.setDigito_verificacion(dato);

		dato = convDatoString(map.get("DIR_BEN"));
		registroEntradaBanco.setDireccion_beneficiario(dato);

		dato = convDatoString(map.get("NEGOCIO"));
		registroEntradaBanco.setCod_negocio_cliente(dato);

		dato = convDatoString(map.get("PRODUCTO"));
		registroEntradaBanco.setCod_producto_cliente(dato);

		dato = convDatoString(map.get("CUENTA_CLIENTE"));
		registroEntradaBanco.setCod_cuenta_cliente(dato);

		return registroEntradaBanco;
	}

	private BigDecimal convDatoDecimal(Object object) {

		if (object == null) {
			return null;
		}
		object = Crypto.DF(object);
		return new BigDecimal(object.toString());
	}

	private Long convDatoNumero(Object object) {

		if (object == null) {
			return null;
		}
		object = Crypto.DF(object);

		String[] code = object.toString().split("[-]");

		String fcode = code[code.length - 1];

		object = StringUtils.onlyNumbers(fcode);

		return new Long(object.toString());
	}

	private String convDatoString(Object object) {
		if (object == null) {
			return null;
		}
		object = Crypto.DF(object);
		return object.toString();
	}

	public GrupoGiroDao getGrupoGiroDao() {
		return grupoGiroDao;
	}

}

class HiloGruposGiro implements Runnable {

	private List<Comando> comandos;

	private String idReporteGruposGiro;

	private Session session;

	private GrupoGiroEstado grupoGiroEstado;

	public HiloGruposGiro(List<Comando> comandos, String idReporteGruposGiro, Session session, GrupoGiroEstado grupoGiroEstado) {
		this.comandos = comandos;
		this.idReporteGruposGiro = idReporteGruposGiro;
		this.session = session;
		this.grupoGiroEstado = grupoGiroEstado;
	}

	public void run() {

		DaoManager daoManager = null;

		try {

			daoManager = DaoConfig.getDaoManager(2);
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			Collections.sort(comandos, new ComparadorComando());

			Map<Integer, GrupoGiro> registros = new HashMap<Integer, GrupoGiro>();

			Integer i = 0;
			// Se estima 40 % del tiempo
			for (Comando comando : comandos) {

				SimpleLogger.setInfo("Procesando Comando : [ " + comando.getCodigo_registro() + ", " + comando.getId_carga() + ", " + comando.getCuenta() + ", " + comando.getFecha_pago() + " ]");

				i++;
				Integer porcentaje = i * 40 / comandos.size();
				Integer proximoAvance = porcentaje - grupoGiroEstado.getPorcentaje();

				// Modificación de un registro
				if (comando.getCodigo_registro() != null) {

					GrupoGiroServicio.getInstance().convertirComandoARegistro(registros, comando);
					grupoGiroEstado.setPorcentaje(porcentaje);
				} else if (comando.getId_carga() != null) {
					// Cuando el cliente marca los valores para la carga
					grupoGiroEstado.setPorcentaje(grupoGiroEstado.getPorcentaje() + (proximoAvance / 2));

					String parametros = "id_carga:" + comando.getId_carga() + ",estado_nuevo:E,inicio:0,registros:" + Integer.MAX_VALUE;
					ResultadoConsulta resultado = ReporteDimServicio.getInstance().obtenerDatosReporte(idReporteGruposGiro, parametros, session);

					GrupoGiroServicio.getInstance().convertirComandoCarga(registros, comando, resultado, grupoGiroEstado, (proximoAvance / 2));
				}

			}

			if (registros.size() > 0) {
				daoManager.startTransaction();

				GrupoGiroDao dao = GrupoGiroServicio.getInstance().getGrupoGiroDao();
				Collection<GrupoGiro> registrosNuevos = registros.values();

				i = 0;

				Integer porcentaje;

				for (GrupoGiro grupoGiroRegistro : registrosNuevos) {

					i++;
					porcentaje = (i * 60 / registrosNuevos.size()) + 40;

					dao.guardarGrupoGiroSql(grupoGiroRegistro, cargaDao, GrupoGiro.ESTADO_NUEVO);

					grupoGiroEstado.setPorcentaje(porcentaje);
				}

				daoManager.commitTransaction();

				grupoGiroEstado.setExito(true);

			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			grupoGiroEstado.setFinalizado(true);
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
			grupoGiroEstado.setPorcentaje(100);
			grupoGiroEstado.setFinalizado(true);
		}

	}

	private List<String> paginarPorMil(Collection<GrupoGiro> registrosNuevos) {

		int cant = 1;
		String ids = "";
		List<String> paginas = new LinkedList<String>();
		for (GrupoGiro grupoGiro : registrosNuevos) {
			if (cant < 1000) {
				ids += grupoGiro.getCodigo_registro() + ",";
				cant++;
			} else {
				// guradar la pagina
				ids = ids.substring(0, ids.length() - 1);// quitar la última coma
				paginas.add(ids);
				// empezar la siguiente pagina
				ids = grupoGiro.getCodigo_registro() + ",";
				cant = 1;
			}
		}

		// guardar la última página
		if (cant < 1000 && ids.length() != 0) {
			ids = ids.substring(0, ids.length() - 1);// quitar la última coma
			paginas.add(ids);
		}

		return paginas;
	}

}
