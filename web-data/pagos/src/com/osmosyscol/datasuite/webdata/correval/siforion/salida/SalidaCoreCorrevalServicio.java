package com.osmosyscol.datasuite.webdata.correval.siforion.salida;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.CargaInstancia;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CargaInstanciaServicio;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.generador.GeneradorORIONAdminPortafolioTerceroACH;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.generador.GeneradorORIONAdminPortafolioTerceroFISICO;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.generador.GeneradorORIONSociedadComisionistaACH;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.generador.GeneradorORIONSociedadComisionistaFISICO;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.generador.GeneradorSIFAdminPortafolioTerceroACH;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.generador.GeneradorSIFAdminPortafolioTerceroFISICO;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.generador.GeneradorSIFCarterasColectivasACH;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.generador.GeneradorSIFCarterasColectivasFISICO;
import com.osmosyscol.datasuite.webdata.correval.siforion.salida.generador.IGeneradorSalidaSistemaCorreval;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.SalidaCoreCorrevalDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class SalidaCoreCorrevalServicio {
	// ---------------------------------------------------------------

	private static SalidaCoreCorrevalServicio instancia;
	private static String SISTEMA_CORE_ORION = "ORION";
	private static String SISTEMA_CORE_SIF = "SIF";
	public static final String ATRIBUTO_MENSAJE = "salida_correval_core_mensaje";

	private SalidaCoreCorrevalServicio() {
	}

	// ---------------------------------------------------------------

	public static SalidaCoreCorrevalServicio getInstance() {
		if (instancia == null) {
			instancia = new SalidaCoreCorrevalServicio();
		}
		return instancia;
	}

	public static List<IGeneradorSalidaSistemaCorreval> generadores = new ArrayList<IGeneradorSalidaSistemaCorreval>();
	// Se deben colocar las implementacion de los generadores de archivos

	static {
		generadores.add(new GeneradorSIFCarterasColectivasACH());
		generadores.add(new GeneradorSIFCarterasColectivasFISICO());
		generadores.add(new GeneradorORIONSociedadComisionistaACH());
		generadores.add(new GeneradorORIONSociedadComisionistaFISICO());
		generadores.add(new GeneradorSIFAdminPortafolioTerceroACH());
		generadores.add(new GeneradorSIFAdminPortafolioTerceroFISICO());
		generadores.add(new GeneradorORIONAdminPortafolioTerceroACH());
		generadores.add(new GeneradorORIONAdminPortafolioTerceroFISICO());

	}

	// ---------------------------------------------------------------
	/**
	 * Genera el contenido del archivo para sistema correval
	 * @param mensaje 
	 */
	private File generarArchivoSistemaCorreval(String tipo_archivo, List<SalidaCoreCorreval> listaSalidaSistemaCorreval, Integer id_persona, Mensaje mensaje) {

		try {
			// Archivo de salida que visualizara el usuario
			File archivoSalida = null;

			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SalidaCoreCorrevalDao creadatosDao = (SalidaCoreCorrevalDao) daoManager.getDao(SalidaCoreCorrevalDao.class);

			try {
				// Se inicia la transaccion
				daoManager.startTransaction();

				// Se crea un archivo salida core en creadatos
				Integer id_archivo = creadatosDao.obtenerSiguienteIDArchivoSalidaCore();

				if (id_archivo != null && id_archivo > 0) {

					Date fechaCreacion = HorarioServicio.getInstance().obtenerFechaActual();
					Persona persona = PersonaServicio.getInstance().obtenerPersona(id_persona);

					// Se crea el archivo
					String nombre_generador = persona.getNombre() + " " + persona.getApellido();
					Boolean archivoCreado = creadatosDao.crearArchivoSalidaCore(id_archivo, tipo_archivo, fechaCreacion, nombre_generador);

					// Se actualizan los retiros con el id_archivo generado
					Boolean registrosActualizados = true;

					mensaje.setPorcentajeInicial(60);
					mensaje.setMensaje("Actualizando registros ...");
					
					if (archivoCreado) {

						Boolean actualizacionExitosa = false;
						// Se actualizan los retiros
						Integer i = 0;
						for (SalidaCoreCorreval salidaCoreCorreval : listaSalidaSistemaCorreval) {
							
							i++;
							Integer porcentaje = i * 30 / listaSalidaSistemaCorreval.size();
							mensaje.actualizarPorcentaje(porcentaje);
							
							String sistemaCore = salidaCoreCorreval.getSistema_core();

							Integer id_registro = Integer.parseInt(salidaCoreCorreval.getId_retiro());

							if (sistemaCore.equals(SISTEMA_CORE_SIF)) {
								actualizacionExitosa = creadatosDao.actualizarArchivoSalidaSIFPorRegistro(id_registro, id_archivo);

							} else if (sistemaCore.equals(SISTEMA_CORE_ORION)) {
								actualizacionExitosa = creadatosDao.actualizarArchivoSalidaORIONPorRegistro(id_registro, id_archivo);

							}

							// Si alguno de los registros falla en la
							// actualizacion la actualizacion no es exitosa
							if (!actualizacionExitosa) {
								registrosActualizados = false;
							}
						}

						// Se genera el archivo fisico si la transaccion es
						// exitosa
						mensaje.setMensaje("Generando archivo...");
						mensaje.setPorcentaje(95);
						
						if (registrosActualizados) {

							for (IGeneradorSalidaSistemaCorreval generador : generadores) {

								if (tipo_archivo.equals(generador.getTipo_arhivo())) {
									// Se crea el archivo que se mostrara en la
									// descarga
									File file = generador.generar(listaSalidaSistemaCorreval);

									if (file.exists()) {

										daoManager.commitTransaction();
										archivoSalida = file;

									}
								}
							}
							mensaje.setPorcentaje(95);
							mensaje.setMensaje("Finalizando...");

						} else {
							// Si alguna actualizacion de los retiros falla
							archivoSalida = null;
						}

					} else {
						// Si no se pudo crear el archivo en creadatos
						archivoSalida = null;
					}

				} else {
					// Si no se pudo obtener el ID de archivo
					archivoSalida = null;
				}

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
				archivoSalida = null;

			} finally {
				daoManager.endTransaction();
			}

			return archivoSalida;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public List<SalidaCoreCorreval> obtenerRetirosPorArchivoDescargado(Integer id_archivo, String tipo_archivo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SalidaCoreCorrevalDao correvalDao = (SalidaCoreCorrevalDao) daoManager.getDao(SalidaCoreCorrevalDao.class);

			List<Map<String, Object>> maps = correvalDao.obtenerRetirosPorArchivoDescargado(id_archivo, tipo_archivo);

			return obtenerSalidasCorePorListadoRetiros(maps, tipo_archivo, null);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	private List<SalidaCoreCorreval> obtenerSalidasCorePorListadoRetiros(List<Map<String, Object>> maps, String tipo_archivo, List<String> retiros_seleccionados) {
		List<SalidaCoreCorreval> salidasCoreCorreval = new ArrayList<SalidaCoreCorreval>();

		Map<Integer, List<CargaInstancia>> mapCargaInstancias = new HashMap<Integer, List<CargaInstancia>>();
		Map<Integer, Carga> mapCargas = new HashMap<Integer, Carga>();
		Map<Integer, Usuario> mapUsuarios = new HashMap<Integer, Usuario>();
		
		if (maps != null) {
			for (Map<String, Object> map : maps) {

				String id_retiro = map.get("ID_RETIRO").toString();
				// Filtrar retiros seleccionados si hay
				if (retiros_seleccionados == null || retiros_seleccionados.contains(id_retiro)) {

					try {

						String id_usuario = map.get("C_ID_CLIENTE").toString();
						Integer id_carga = Integer.parseInt(StringUtils.toString(map.get("LOTE_ORIGEN")));
						
						Carga carga = null;
						if(mapCargas.containsKey(id_carga)){
							carga = mapCargas.get(id_carga);
						}else{
							carga = CargaServicio.getInstance().obtenerCarga(id_carga);
							mapCargas.put(id_carga, carga);
						}
						
						Usuario clienteUsuario = null;
						if(mapUsuarios.containsKey(Integer.parseInt(id_usuario))){
							clienteUsuario = mapUsuarios.get(Integer.parseInt(id_usuario));
						}else{
							clienteUsuario = UsuarioServicio.getInstance().obtenerUsuario(Integer.parseInt(id_usuario));
							mapUsuarios.put(Integer.parseInt(id_usuario), clienteUsuario);
						}

						SalidaCoreCorreval salidaCoreCorreval = new SalidaCoreCorreval();

						String tipo_retiro = StringUtils.toString(map.get("C_TIPO_RETIRO"));
						salidaCoreCorreval.setTipo_retiro(tipo_retiro);

						String sistema_core = StringUtils.toString(map.get("C_SISTEMA_CORE"));
						salidaCoreCorreval.setSistema_core(sistema_core);

						salidaCoreCorreval.setId_retiro(StringUtils.toString(map.get("ID_RETIRO")));
						salidaCoreCorreval.setEstado_retiro(StringUtils.toString(map.get("C_ESTADO_RETIRO")));

						String codigo_contable_cuenta_origen = StringUtils.toString(map.get("C_CODIGO_CONTABLE_CUENTA_O_C"));
						salidaCoreCorreval.setCodigo_contable_cuenta_origen_correval(codigo_contable_cuenta_origen);

						String descripcion_negocio = StringUtils.toString(map.get("C_DESCRIPCION_NEGOCIO"));
						salidaCoreCorreval.setDescripcion_negocio(descripcion_negocio);

						String descripcion_producto = StringUtils.toString(map.get("C_DESCRIPCION_PRODUCTO"));
						salidaCoreCorreval.setDescripcion_producto(descripcion_producto);

						// TODO Se debe colcoar el del usuario

						if (clienteUsuario != null) {
							Integer digito_verificacion_identifiacion_cliente = Integer.parseInt("" + clienteUsuario.getIdentificacion().charAt(0));
							salidaCoreCorreval.setDigito_verificacion_identifiacion_cliente(digito_verificacion_identifiacion_cliente);

						} else {
							salidaCoreCorreval.setDigito_verificacion_identifiacion_cliente(0);
						}

						// TODO Se debe colocar la fecha de la carga
						salidaCoreCorreval.setFecha_liberacion_lote_origen(carga.getFecha_liberacion());

						if (map.get("C_IDENTIFICACION_BEN").toString().length() > 0) {
							Long identificacion_beneficiario = new Long(StringUtils.toString(map.get("C_IDENTIFICACION_BEN")));
							salidaCoreCorreval.setIdentificacion_beneficiario(identificacion_beneficiario);
						} else {
							salidaCoreCorreval.setIdentificacion_beneficiario(new Long(0));
						}

						// TODO se debe colocar la carga
						salidaCoreCorreval.setLote_origen("" + id_carga);

						List<CargaInstancia> lista  = null;
						if(mapCargaInstancias.containsKey(id_carga)){
							lista = mapCargaInstancias.get(id_carga);
						}else{
							// TODO SE debe colcar el adminsitrativo que la recibio
							lista = CargaInstanciaServicio.getInstance().obtenerHistorialCargaInstancia(id_carga);
							mapCargaInstancias.put(id_carga, lista);
						}
						

						String administrativo = "";
						if (lista != null && lista.size() > 0) {
							CargaInstancia cargaInstancia = lista.get(0);
							administrativo = cargaInstancia.getNombre_persona() + " " + cargaInstancia.getApellido_persona();

						}

						salidaCoreCorreval.setNombre_agente_comercial(administrativo);

						String nombre_banco_destino = StringUtils.toString(map.get("C_NOMBRE_BANCO_DESTINO_BEN"));
						salidaCoreCorreval.setNombre_banco_destino_beneficiario(nombre_banco_destino);

						String nombre_banco_origen = StringUtils.toString(map.get("C_NOMBRE_BANCO_O_C"));
						salidaCoreCorreval.setNombre_banco_origen_correval(nombre_banco_origen);

						String nombre_beneficiario = StringUtils.toString(map.get("C_NOMBRE_BEN"));
						salidaCoreCorreval.setNombre_beneficiario(nombre_beneficiario);

						// TODO se debe colcoar el nombre de cliente
						if (clienteUsuario != null) {
							salidaCoreCorreval.setNombre_cliente(clienteUsuario.getNombre() + " " + clienteUsuario.getApellido());
						} else {
							salidaCoreCorreval.setNombre_cliente("");
						}

						String num_cuenta_cliente_correval = StringUtils.toString(map.get("C_NUM_CUENTA_CLIENTE_COR"));
						salidaCoreCorreval.setNum_cuenta_cliente_correval(num_cuenta_cliente_correval);

						String num_cuenta_origen_correval = StringUtils.toString(map.get("C_NUM_CUENTA_ORIGEN_CORREVAL"));
						salidaCoreCorreval.setNum_cuenta_origen_correval(num_cuenta_origen_correval);

						// TODO se debe colcoar el del usuario
						if (clienteUsuario != null) {
							salidaCoreCorreval.setIdentificacion_cliente(clienteUsuario.getIdentificacion());

						} else {
							salidaCoreCorreval.setIdentificacion_cliente("0");
						}

						String numero_cuenta_destino_beneficiario = StringUtils.toString(map.get("C_NUM_CUENTA_DESTINO_BEN"));
						salidaCoreCorreval.setNum_cuenta_destino_beneficiario(numero_cuenta_destino_beneficiario);

						salidaCoreCorreval.setTipo_archivo_salida_correval(tipo_archivo);

						String tipo_cuenta_destino_beneficiario = StringUtils.toString(map.get("C_TIPO_CUENTA_DESTINO_BEN"));
						salidaCoreCorreval.setTipo_cuenta_destino_beneficiario(tipo_cuenta_destino_beneficiario);

						String tipo_cuenta_origen_correval = StringUtils.toString(map.get("C_TIPO_CUENTA_O_C"));
						salidaCoreCorreval.setTipo_cuenta_origen_correval(tipo_cuenta_origen_correval);

						String tipo_de_medio_fisico = StringUtils.toString(map.get("C_TIPO_DE_MEDIO_F"));
						salidaCoreCorreval.setTipo_de_medio_fisico(tipo_de_medio_fisico);

						BigDecimal valor_retiro = new BigDecimal(StringUtils.toString(map.get("C_VALOR_RETIRO")));
						salidaCoreCorreval.setValor_retiro(valor_retiro);

						// Si el tipo de archivo no es ACH
						if (!tipo_archivo.contains("ACH")) {
							// Informacion sobre la localizacion de pago y la
							// persona autorizada a recoger

							String tipo_identificacion_autorizado_para_recoger = StringUtils.toString(map.get("C_TIPO_IDENTIFICACION_AUTO_P_R"));
							salidaCoreCorreval.setTipo_identificacion_autorizado_para_recoger(tipo_identificacion_autorizado_para_recoger);

							String tipo_de_entrega_medio_fisico = StringUtils.toString(map.get("C_TIPO_DE_ENTREGA_MEDIO_F"));
							salidaCoreCorreval.setTipo_de_entrega_medio_fisico(tipo_de_entrega_medio_fisico);

							if (map.get("C_NUM_IDENTIFICACION_AUTO_P_R") != null && StringUtils.toString(map.get("C_NUM_IDENTIFICACION_AUTO_P_R")).length() > 0) {
								Long num_identificacion_autorizado_para_recoger = new Long(StringUtils.toString(map.get("C_NUM_IDENTIFICACION_AUTO_P_R")));
								salidaCoreCorreval.setNum_identificacion_autorizado_para_recoger(num_identificacion_autorizado_para_recoger);
							} else {
								salidaCoreCorreval.setNum_identificacion_autorizado_para_recoger(new Long(0));
							}

							String nombre_autorizado_para_recoger = StringUtils.toString(map.get("C_NOMBRE_AUTO_P_R"));
							salidaCoreCorreval.setNombre_autorizado_para_recoger(nombre_autorizado_para_recoger);

							String direccion_entrega_medio_fisico = StringUtils.toString(map.get("C_DIRECCION_ENTREGA_MEDIO_F"));
							salidaCoreCorreval.setDireccion_entrega_medio_fisico(direccion_entrega_medio_fisico);

						}

						salidasCoreCorreval.add(salidaCoreCorreval);

					} catch (Exception e) {
						SimpleLogger.setError("Error creando saliadaCore", e);
					}

				}
			}
		}

		return salidasCoreCorreval;

	}

	public Integer contarRetirosPorTipoArchivoSinDescargar(String tipo_archivo) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SalidaCoreCorrevalDao correvalDao = (SalidaCoreCorrevalDao) daoManager.getDao(SalidaCoreCorrevalDao.class);

			return correvalDao.contarRetirosPorTipoArchivoSinDescargar(tipo_archivo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public List<SalidaCoreCorreval> obtenerRetirosSinDescargarPorTipoArchivoFiltrado(String tipo_archivo, Integer pagina, List<String> retiros_seleccionados) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SalidaCoreCorrevalDao correvalDao = (SalidaCoreCorrevalDao) daoManager.getDao(SalidaCoreCorrevalDao.class);

			List<Map<String, Object>> maps = correvalDao.obtenerRetirosPorTipoArchivoSinDescargar(tipo_archivo, retiros_seleccionados,pagina);

			return obtenerSalidasCorePorListadoRetiros(maps, tipo_archivo, retiros_seleccionados);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public List<SalidaCoreCorreval> obtenerRetirosSinDescargarPorTipoArchivoSinFiltrar(String tipo_archivo, Integer pagina) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SalidaCoreCorrevalDao correvalDao = (SalidaCoreCorrevalDao) daoManager.getDao(SalidaCoreCorrevalDao.class);

			List<Map<String, Object>> maps = correvalDao.obtenerRetirosPorTipoArchivoSinDescargar(tipo_archivo, null, pagina);

			return obtenerSalidasCorePorListadoRetiros(maps, tipo_archivo, null);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public List<String> obtenerTiposArchivoSalidaCorreval() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SalidaCoreCorrevalDao correvalDao = (SalidaCoreCorrevalDao) daoManager.getDao(SalidaCoreCorrevalDao.class);

			return correvalDao.obtenerTiposArchivoSalidaCorreval();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean crearArchivoParaRetirosPorComandos(List<ComandoRegistro> comandos, String tipo_archivo, Session session) {

		try {
			if (CollectionUtils.isEmpty(comandos) || StringUtils.esVacio(tipo_archivo)) {
				return false;
			}

			Mensaje mensaje = new Mensaje();

			session.setAttribute(ATRIBUTO_MENSAJE, mensaje);

			HiloSalidaSistemaCore hilo = new HiloSalidaSistemaCore(comandos, tipo_archivo, mensaje, session);
			new Thread(hilo).start();
			return true;

		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	public Boolean crearArchivoParaRetirosSeleccionados(String tipo_archivo, List<String> retiros_seleccionados, Session session, List<SalidaCoreCorreval> salidasSinFiltrar,
			Mensaje mensaje) {

		try {

			session.removeAttribute("archivo_sif_orion");
			session.removeAttribute("tipo_archivo_sif_orion");

			if (retiros_seleccionados != null && retiros_seleccionados.size() > 0) {

				HashMap<String, Object> mapSalidasSistemasCorreval = new HashMap<String, Object>();

				List<SalidaCoreCorreval> salidasFiltradas = new ArrayList<SalidaCoreCorreval>();

				// Se obtinen los retiros sin filtrar
				if (salidasSinFiltrar == null) {

					mensaje.setMensaje("Consultando información...");

					salidasSinFiltrar = obtenerRetirosSinDescargarPorTipoArchivoFiltrado(tipo_archivo, null, retiros_seleccionados);
					salidasFiltradas = salidasSinFiltrar;

				} else {
					for (SalidaCoreCorreval salidaCoreCorreval : salidasSinFiltrar) {
						mapSalidasSistemasCorreval.put(salidaCoreCorreval.getId_retiro(), salidaCoreCorreval);
					}
					for (String retiro : retiros_seleccionados) {
						if (mapSalidasSistemasCorreval.containsKey(retiro)) {
							salidasFiltradas.add((SalidaCoreCorreval) mapSalidasSistemasCorreval.get(retiro));

						}

					}
				}

				mensaje.setPorcentaje(55);

				

				// Se filtran los retiros segun la seleccion

				// Luego de filtrar las salidas se establecen las variablas para
				// generar el archivo
				mensaje.setMensaje("Generando archivo...");

				Integer id_persona = (Integer) session.getAttribute("id_persona");

				File file = generarArchivoSistemaCorreval(tipo_archivo, salidasFiltradas, id_persona, mensaje);
				mensaje.setPorcentaje(100);
				if (file != null && file.exists()) {

					session.setAttribute("archivo_sif_orion", file.getPath());
					session.setAttribute("tipo_archivo_sif_orion", tipo_archivo);
					return true;
				} else {

					return false;
				}

			} else {
				return false;
			}

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado un error", e);
			return false;
		}

	}

	public Boolean generarArchivoHistoricoSistemaCore(ArchivoSalidaCore archivoSalidaCore, Session session) {
		try {
			session.removeAttribute("archivo_sif_orion");
			session.removeAttribute("tipo_archivo_sif_orion");

			Boolean archivoGenerado = false;

			if (archivoSalidaCore != null) {
				// Se consultan los retiros asociados al archivo
				String tipo_archivo = archivoSalidaCore.getTipoArchivo();

				List<SalidaCoreCorreval> lista = obtenerRetirosPorArchivoDescargado(archivoSalidaCore.getId_archivo(), tipo_archivo);

				for (IGeneradorSalidaSistemaCorreval generador : generadores) {

					if (tipo_archivo.equals(generador.getTipo_arhivo())) {
						// Se crea el archivo que se mostrara en la descarga
						File file = generador.generar(lista);

						if (file.exists()) {

							archivoGenerado = true;
							session.setAttribute("archivo_sif_orion", file.getPath());
							session.setAttribute("tipo_archivo_sif_orion", tipo_archivo);

						}
					}
				}
			}

			return archivoGenerado;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public List<ArchivoSalidaCore> obtenerArchivoSalidaCoreDescargadosPorTipoArchivo(String tipo_archivo, Integer numero_pagina) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SalidaCoreCorrevalDao correvalDao = (SalidaCoreCorrevalDao) daoManager.getDao(SalidaCoreCorrevalDao.class);

			ArrayList<ArchivoSalidaCore> archivos = new ArrayList<ArchivoSalidaCore>();

			List<Map<String, Object>> maps = correvalDao.obtenerArchivosDescargadosPorTipoArchivo(tipo_archivo, numero_pagina);

			for (Map<String, Object> map : maps) {
				try {

					ArchivoSalidaCore archivoSalidaCore = new ArchivoSalidaCore();

					Date fechaCreacion = (Date) map.get("C_FECHA_CREACION");
					archivoSalidaCore.setFechaCreacion(fechaCreacion);

					Integer id_archivo = Integer.parseInt(map.get("ID_ARCHIVO").toString());
					archivoSalidaCore.setId_archivo(id_archivo);

					String nombreGenerador = map.get("C_NOMBRE_DEL_GENERADOR").toString();
					archivoSalidaCore.setNombreGenerador(nombreGenerador);

					String tipoArchivo = map.get("C_TIPO_ARCHIVO").toString();

					archivoSalidaCore.setTipoArchivo(tipoArchivo);
					archivos.add(archivoSalidaCore);

				} catch (Exception e) {
					SimpleLogger.setError("ha ocurrido un error", e);

				}
			}

			return archivos;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public Integer obtenerTotalArchivosDescargadosPorTipoArchivo(String tipo_archivo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SalidaCoreCorrevalDao correvalDao = (SalidaCoreCorrevalDao) daoManager.getDao(SalidaCoreCorrevalDao.class);

			return correvalDao.obtenerTotalArchivosDescargadosPorTipoArchivo(tipo_archivo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	class HiloSalidaSistemaCore implements Runnable {

		private List<ComandoRegistro> comandos;
		private String tipo_archivo;
		private Mensaje mensaje;
		private Session session;

		public HiloSalidaSistemaCore(List<ComandoRegistro> comandos, String tipo_archivo, Mensaje mensaje, Session session) {
			this.comandos = comandos;
			this.tipo_archivo = tipo_archivo;
			this.mensaje = mensaje;
			this.session = session;
		}

		public void run() {
			try {

				mensaje.setEstado("I");

				Collections.sort(comandos, new ComparadorComandoRegistro());
				List<SalidaCoreCorreval> registros = null;
				List<String> retiros = new ArrayList<String>();

				Integer i = 0;

				mensaje.setMensaje("Iniciando la generación del archivo...");

				for (ComandoRegistro comandoRegistro : comandos) {
					i++;
					Integer porcentaje = i * 50 / comandos.size();
					mensaje.setPorcentaje(porcentaje);

					if (!NumberUtils.isNumber(comandoRegistro.getId_registro())) {
						// Seleccion todos
						if (comandoRegistro.getAgregar()) {
							registros = obtenerRetirosSinDescargarPorTipoArchivoSinFiltrar(tipo_archivo, null);
							for (SalidaCoreCorreval salidaCoreCorreval : registros) {
								retiros.add(salidaCoreCorreval.getId_retiro());
							}

						} else {
							retiros.clear();
						}
					} else {
						if (comandoRegistro.getAgregar()) {
							retiros.add(comandoRegistro.getId_registro());
						} else {
							retiros.remove(comandoRegistro.getId_registro());
						}
					}
				}
				if (retiros.isEmpty()) {
					mensaje.setEstado("F");
					mensaje.setMensaje("No se encontrarón registros seleccionados.");
				}

				Boolean salida = crearArchivoParaRetirosSeleccionados(tipo_archivo, retiros, session, registros, mensaje);
				mensaje.setEstado("F");
				if (salida) {
					mensaje.setExitoso(true);
				}
			} catch (Throwable e) {
				mensaje.setEstado("F");
				mensaje.setMensaje("Error generando el archivo.");
				SimpleLogger.setError("Error en hilo core.", e);
			}
		}

	}

}
