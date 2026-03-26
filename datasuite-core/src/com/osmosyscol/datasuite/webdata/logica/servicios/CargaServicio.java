package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.cocoon.environment.Session;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddressList;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.MapCache;
import com.osmosyscol.commons.utils.SQLUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.SystemUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.FiltroBandejaEntrada;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.ListaDinamicaCampo;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.dto.Nodo;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;
import com.osmosyscol.datasuite.logica.dto.RestriccionAdministrativo;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.dto.excel.Excel;
import com.osmosyscol.datasuite.logica.servicios.AdministrativoServicio;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.logica.servicios.ExcelServicio;
import com.osmosyscol.datasuite.logica.servicios.FormatoOperacionServicio;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaValidacion;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Operacion;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionResponsableServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CargaInstanciaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.InstanciaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaDinamicaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoAdminServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.TipoCampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ValidacionEstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.VariableServicio;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.CampoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.FormatoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.TipoCampoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ValidacionEstructuraDao;
import com.osmosyscol.datasuite.modelatos.utils.FormatoUtils;
import com.osmosyscol.datasuite.persistencia.dao.CargaInstanciaDao;
import com.osmosyscol.datasuite.persistencia.dao.ListaDinamicaCampoDao;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.utils.dummys.DummySession;
import com.osmosyscol.datasuite.webdata.ValidadorDatosCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.ElementoCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.FormatoDato;
import com.osmosyscol.datasuite.webdata.logica.dto.FormatoFiltro;
import com.osmosyscol.datasuite.webdata.logica.dto.NumeroValor;
import com.osmosyscol.datasuite.webdata.logica.dto.ParametrosBusqCargas;
import com.osmosyscol.datasuite.webdata.logica.dto.TablaOperacion;
import com.osmosyscol.datasuite.webdata.logica.dto.ValorFiltro;
import com.osmosyscol.datasuite.webdata.logica.dto.ValorListaDinamicaCampo;
import com.osmosyscol.datasuite.webdata.logica.servicios.carga.OperacionesTablaControl;
import com.osmosyscol.datasuite.webdata.logica.servicios.utils.XlsUtils;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.datasuite.webdata.utils.CargaMasivaSqlUtil;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class CargaServicio {
	// ---------------------------------------------------------------

	private static CargaServicio cargaServicio;

	private CargaServicio() {
	}

	// ---------------------------------------------------------------

	public static CargaServicio getInstance() {
		if (cargaServicio == null) {
			cargaServicio = new CargaServicio();
		}
		return cargaServicio;
	}

	// ---------------------------------------------------------------

	public Integer obtenerSiguiente() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerSiguiente();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Integer obtenerSiguienteCreadatos() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerSiguienteCreadatos();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public void marcarNotificacion(Integer id_carga) {

		try {
			DaoConfig.getDao(CargaDao.class).marcarNotificacion(id_carga);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

	}

	// ---------------------------------------------------------------

	public Integer crearCargaInteractiva(String nombre, Integer id_persona, Integer id_formato, Integer id_proceso, Integer idUsuario, Integer id_negocio, String ip_usuario) {
		return crearYObtCarga(nombre, id_persona, id_formato, Constantes.TIPOCARGA_INTERACTIVO, id_proceso, idUsuario, id_negocio, ip_usuario);
	}

	public Integer crearCargaArchivo(String nombre, Integer id_persona, Integer id_formato, Integer id_proceso, Integer idUsuario, Integer id_negocio, String ip_usuario) {
		return crearYObtCarga(nombre, id_persona, id_formato, Constantes.TIPOCARGA_ARCHIVO, id_proceso, idUsuario, id_negocio, ip_usuario);
	}

	public Integer crearYObtCarga(String nombre, Integer id_persona, Integer id_formato, Integer id_tipocarga, Integer id_proceso, Integer idUsuario, Integer id_negocio, String ip_usuario) {
		return crearYObtCarga(nombre, id_persona, id_formato, id_tipocarga, id_proceso, idUsuario, id_negocio, ip_usuario, null);
	}

	public Integer crearYObtCarga(String nombre, Integer id_persona, Integer id_formato, Integer id_tipocarga, Integer id_proceso, Integer idUsuario, Integer id_negocio, String ip_usuario, Integer id_tipo_proceso) {

		try {
			HorarioServicio.getInstance().obtenerFechaActual();

			Carga carga = new Carga();

			carga.setEstado(Constantes.CARGA_ESTADO_BORRADOR);
			carga.setNombre(nombre);
			carga.setId_carga(obtenerSiguiente());
			carga.setId_persona(id_persona);
			carga.setId_formato(id_formato);
			carga.setId_tipocarga(id_tipocarga);
			carga.setFecha_subida(HorarioServicio.getInstance().obtenerFechaActual());
			carga.setId_proceso(id_proceso);
			carga.setId_usuario(idUsuario);
			carga.setId_negocio(id_negocio);
			carga.setIp_usuario(ip_usuario);
			carga.setId_tipo_proceso(id_tipo_proceso);

			if (nombre == null) {
				if (id_tipocarga == Constantes.TIPOCARGA_INTERACTIVO) {
					carga.setNombre("Individual " + carga.getId_carga());
				} else if (id_tipocarga == Constantes.TIPOCARGA_ARCHIVO) {
					carga.setNombre("Archivo " + carga.getId_carga());
				} else if (id_tipocarga == Constantes.TIPOCARGA_LOTES) {
					carga.setNombre("Masivo " + carga.getId_carga());
				} else {
					carga.setNombre("Carga " + carga.getId_carga());
				}
			}

			((CargaDao) DaoConfig.getDaoManager().getDao(CargaDao.class)).insertar(carga);

			return carga.getId_carga();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}

	// ---------------------------------------------------------------

	public Boolean insertar(Integer id, String nombre, String extension, String ruta, Integer id_formato, Integer id_persona, Date fecha_subida, int id_tipocarga, Integer idProceso, Integer idUsuario, Integer id_negocio, String ip_usuario) {
		try {
			Carga carga = new Carga();
			carga.setId_negocio(id_negocio);
			carga.setExtension(extension);
			carga.setId_carga(id);
			carga.setNombre(nombre + " - " + id);
			carga.setRuta(ruta);
			carga.setId_formato(id_formato);
			carga.setId_persona(id_persona);
			carga.setFecha_subida(fecha_subida);
			carga.setId_tipocarga(id_tipocarga);
			carga.setEstado(Constantes.CARGA_ESTADO_BORRADOR);
			carga.setId_proceso(idProceso);
			carga.setId_usuario(idUsuario);
			carga.setIp_usuario(ip_usuario);

			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
			cargaDao.insertar(carga);
			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio obtenerCargaCompleto", e);
			return false;
		}
	}

	// ---------------------------------------------------------------

	public Carga obtenerCarga(Integer id_carga) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtener(id_carga);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Carga obtenerCargasBorradorUltimo(Integer id_usuario, String estados) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerCargasBorradorUltimo(id_usuario, estados);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error obteniendo las cargas", e);
		}
		return null;
	}
	
	public List<Carga> obtenerCargas(Integer id_usuario, String estados, Integer id_revision, Integer id_proceso, Integer pagina) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerCargas(id_usuario, estados, id_revision, id_proceso, pagina);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error obteniendo las cargas", e);
		}
		return null;
	}

	// ---------------------------------------------------------------

	public Map<String, Object> obtenerCargaCompleto(Integer id_carga) {

		try {
			Map<String, Object> ret = new HashMap<String, Object>();

			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
			Carga carga = cargaDao.obtener(id_carga);
			ret.put("Carga", carga);

			Excel excel = ExcelServicio.getInstance().crearExcel(carga.getRuta());
			ret.put("Excel", excel);

			return ret;
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio obtenerCargaCompleto", e);
		}
		return null;
	}

	// ---------------------------------------------------------------

	public List<Carga> obtenerCargaPorFormatoEntradaCliente(Integer id_formato, Integer id_persona, String estados) {
		List<String> listaEstados = null;

		if (estados != null && estados.length() > 0) {

			listaEstados = new ArrayList<String>();
			for (int i = 0; i < estados.length(); i++) {
				listaEstados.add("" + estados.toUpperCase().charAt(i));

			}
		}

		return obtenerCargaPorFormatoEntrada(id_formato, id_persona, listaEstados);
	}

	public List<Carga> obtenerCargaPorFormatoEntradaAdmin(Integer id_formato, Integer id_persona) {
		List<String> estados = new ArrayList<String>();
		estados.add(Constantes.CARGA_ESTADO_APROBADO);
		estados.add(Constantes.CARGA_ESTADO_SUBIDO);
		return obtenerCargaPorFormatoEntrada(id_formato, id_persona, estados);
	}

	public List<Carga> obtenerCargaPorFormatoEntrada(Integer id_formato, Integer id_persona, List<String> estados) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerCargaPorFormatoEntrada(id_formato, id_persona, estados);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public List<Carga> obtenerListadoCargasPorFormatoEntrada(Integer id_formato) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerListadoCargasPorFormatoEntrada(id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	// ---------------------------------------------------------------

	public List<ElementoCarga> obtenerElementosCarga(Integer id_carga, Integer numero_pagina) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerElementosCarga(id_carga, numero_pagina);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public Integer obtenerTotalElementosCarga(Integer id_carga) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerTotalElementosCarga(id_carga);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ---------------------------------------------------------------

	public Boolean crearYEnviarElementoCarga(Integer id_persona, Integer id_elementocarga, Integer id_carga, FormatoDato formatodato, Integer id_negocio, Session session, List<ValorListaDinamicaCampo> listaValoresDinamicos) {

		Boolean rtaCrear = crearElementoCarga(id_elementocarga, id_carga, formatodato);
		if (rtaCrear) {
			rtaCrear = realizarCarga(id_carga, id_persona, id_negocio, session, listaValoresDinamicos);
		}

		return rtaCrear;

	}

	public Boolean crearElementoCarga(Integer id_elementocarga, Integer id_carga, FormatoDato formatodato) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			daoManager.startTransaction();

			try {
				cargaDao.crearElementosCarga(id_elementocarga, id_carga, formatodato);
				daoManager.commitTransaction();

				return true;

			} catch (Exception e) {
				SimpleLogger.setError("Error en cargaDao.crearElementosCarga", e);
			} finally {
				daoManager.endTransaction();
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error en Servicio crearElementoCarga", e);
		}

		return false;
	}

	public Boolean eliminarElementoCarga(Integer id_elementocarga) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			daoManager.startTransaction();

			try {
				cargaDao.eliminarElementoCarga(id_elementocarga);
				daoManager.commitTransaction();

				return true;

			} catch (Exception e) {
				SimpleLogger.setError("Error en cargaDao.eliminarElementoCarga", e);
			} finally {
				daoManager.endTransaction();
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error en Servicio eliminarElementoCarga", e);
		}

		return false;
	}

	// ---------------------------------------------------------------

	public List<Carga> obtenerCargasInteractivasBorrador(Integer id_formato, Integer id_persona) {
		return obtenerListaCarga(id_formato, id_persona, Constantes.CARGA_ESTADO_BORRADOR, Constantes.TIPOCARGA_INTERACTIVO);
	}

	// ---------------------------------------------------------------

	public List<Carga> obtenerListaCarga(Integer id_formato, Integer id_persona, String id_estado, Integer id_tipocarga) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerListaPorEstado(id_formato, id_persona, id_estado, id_tipocarga);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	// ---------------------------------------------------------------

		public List<Carga> listarCargasDuplicadas(Integer id_carga, String valor_total, String numero_registros) {

			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

				return cargaDao.listarCargasDuplicadas(id_carga, valor_total, numero_registros);
			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
			}
			return null;
		}

	// ---------------------------------------------------------------

	public Boolean eliminarCargasInteractivasSinEnviar(Integer id_usuario) {

		try {
			DaoManager daoManager;
			daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
			cargaDao.eliminarCargasInteractivasSinEnviar(id_usuario);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	public Boolean eliminarCarga(Integer id_carga) {

		try {
			CargaDao cargaDao = (CargaDao) DaoConfig.getDao(CargaDao.class);
			CargaDao cargaDao2 = (CargaDao) DaoConfig.getDao(CargaDao.class, 2);
			cargaDao.cambiarEstado(id_carga, Constantes.CARGA_ESTADO_ELIMINADO);
			cargaDao2.cambiarEstadoCrea(id_carga, Constantes.CARGA_ESTADO_ELIMINADO);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	public Boolean removerCarga(Integer id_carga) {

		try {
			DaoManager daoManager;
			daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
			cargaDao.removerCarga(id_carga);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	// ---------------------------------------------------------------

	public List<Map<String, Object>> obtenerDatos(Integer id_persona, Integer id_estructura, Integer id_campobusqueda, String valor_busqueda) {

		DaoManager daoManagerData1 = DaoConfig.getDaoManager(1);
		CampoDao campoDao = (CampoDao) daoManagerData1.getDao(CampoDao.class);
		TipoCampoDao tipoCampoDao = (TipoCampoDao) daoManagerData1.getDao(TipoCampoDao.class);

		DaoManager daoManagerData2 = DaoConfig.getDaoManager(2);
		CargaDao cargaDao = (CargaDao) daoManagerData2.getDao(CargaDao.class);

		// -------------

		String str_valor_busqueda = "";

		List<Campo> camposlist = campoDao.obtenerCamposPorEstructura(id_estructura);

		Map<Integer, Campo> campos = new HashMap<Integer, Campo>();

		for (Campo campo : camposlist) {
			id_estructura = campo.getId_estructura();

			if (campo.getId_campo().intValue() == id_campobusqueda) {
				TipoCampo tipoCampo = tipoCampoDao.obtenerTipoCampo(campo.getId_tipocampo());

				str_valor_busqueda = Crypto.E(CampoServicio.getValorReal(valor_busqueda, tipoCampo.getTipo_dato()));
			}

			if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equals(campo.getMultiplicidad())) {
				campos.put(campo.getId_campo(), campo);
			}
		}

		List<Map<String, Object>> data = cargaDao.obtenerDatos(id_persona.toString(), id_estructura.toString(), id_campobusqueda.toString(), str_valor_busqueda, campos);

		for (Map<String, Object> registro : data) {

			Set<String> keys = registro.keySet();
			for (String key : keys) {
				registro.put(key, Crypto.DF(registro.get(key)));
			}
		}

		return data;
	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	public Boolean realizarCarga(Integer id_carga, Integer id_persona, Integer id_negocio, Session session, List<ValorListaDinamicaCampo> listaValoresDinamicos) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			DaoManager daoManagerData = DaoConfig.getDaoManager(2);
			daoManagerData.startTransaction();
			daoManager.startTransaction();

			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			try {
				Carga carga = cargaDao.obtener(id_carga);
				carga.setId_negocio(id_negocio);

				cargaDao.setFechaCarga(id_carga, HorarioServicio.getInstance().obtenerFechaActual());

				Formato formato = FormatoServicio.getInstance().obtenerFormato(carga.getId_formato());

				if (id_negocio != null && id_negocio > 0) {

					if (!validarListadoValorListaDinamicaCampo(listaValoresDinamicos, formato.getId_formato())) {
						SimpleLogger.setError("Los valores ingresados no corresponden con los parametrizados por la lista_dinamica_campo para el formato " + formato.getId_formato() + " carga: " + id_carga);
						return false;
					}

					cargaDao.actualizarNegocio(id_carga, id_negocio);
				}

				CargaDao cargaDaoData = (CargaDao) daoManagerData.getDao(CargaDao.class);

				Boolean ret = false;

				if (carga.getId_tipocarga() == Constantes.TIPOCARGA_LOTES) {

					Mensaje mensaje = new Mensaje();
					mensaje.setEstado("I");
					mensaje.setMensaje("Iniciando carga ...");

					session.setAttribute("mensaje_carga_lotes", mensaje);
					List<FormatoCampo> listadoFCUnicos = new ArrayList<FormatoCampo>();

					HiloCargaLotes hilo = new HiloCargaLotes(cargaDaoData, formato, session, carga, listadoFCUnicos, cargaDaoData, daoManager, id_persona, daoManagerData, listaValoresDinamicos);
					new Thread(hilo).start();
					return true;
				}

				if (carga.getId_tipocarga() == Constantes.TIPOCARGA_INTERACTIVO) {
					ret = realizarCargaInteractivo(cargaDaoData, daoManager, carga, formato, id_persona, session, daoManagerData);
				}

				if (carga.getId_tipocarga() == Constantes.TIPOCARGA_ARCHIVO) {
					ret = realizarCargaInteractivo(cargaDaoData, daoManager, carga, formato, id_persona, session, daoManagerData);
					cargaDao.cambiarTipocarga(id_carga, Constantes.TIPOCARGA_LOTES);
				}

				Boolean rta = false;

				if (ret) {
					rta = finalizarEnvioCarga(cargaDaoData, carga, daoManager, id_persona, formato, daoManagerData, session, listaValoresDinamicos);
				}

				if (rta) {
					daoManager.commitTransaction();
					daoManagerData.commitTransaction();

					ValidacionEstructuraServicio.getInstance().generarValidacionesNoEnLineaHilo(id_carga);
				}

				return rta;

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
				SimpleLogger.setError("Error ocurrido en CargaServicio.realizarCarga", e);
				return false;
			} finally {
				try {
					daoManager.endTransaction();
					daoManagerData.endTransaction();
				} catch (Exception e2) {
					SimpleLogger.setError("Error ocurrido en CargaServicio.realizarCarga", e2);
				}
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			SimpleLogger.setError("Error ocurrido en CargaServicio.realizarCarga", e);
			return false;
		}
	}
	
	public Boolean realizarCarga(Integer id_carga, Integer id_persona, Integer id_negocio, Session session, List<ValorListaDinamicaCampo> listaValoresDinamicos, Boolean borrador) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			DaoManager daoManagerData = DaoConfig.getDaoManager(2);
			daoManagerData.startTransaction();
			daoManager.startTransaction();

			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			try {
				Carga carga = cargaDao.obtener(id_carga);
				carga.setId_negocio(id_negocio);

				cargaDao.setFechaCarga(id_carga, HorarioServicio.getInstance().obtenerFechaActual());

				Formato formato = FormatoServicio.getInstance().obtenerFormato(carga.getId_formato());

				if (id_negocio != null && id_negocio > 0) {

					if (!validarListadoValorListaDinamicaCampo(listaValoresDinamicos, formato.getId_formato())) {
						SimpleLogger.setError("Los valores ingresados no corresponden con los parametrizados por la lista_dinamica_campo para el formato " + formato.getId_formato() + " carga: " + id_carga);
						return false;
					}

					cargaDao.actualizarNegocio(id_carga, id_negocio);
				}

				CargaDao cargaDaoData = (CargaDao) daoManagerData.getDao(CargaDao.class);

				Boolean ret = false;

				if (carga.getId_tipocarga() == Constantes.TIPOCARGA_LOTES) {

					Mensaje mensaje = new Mensaje();
					mensaje.setEstado("I");
					mensaje.setMensaje("Iniciando carga ...");

					session.setAttribute("mensaje_carga_lotes", mensaje);
					List<FormatoCampo> listadoFCUnicos = new ArrayList<FormatoCampo>();

					HiloCargaLotes hilo = new HiloCargaLotes(cargaDaoData, formato, session, carga, listadoFCUnicos, cargaDaoData, daoManager, id_persona, daoManagerData, listaValoresDinamicos);
					new Thread(hilo).start();
					return true;
				}

				if (carga.getId_tipocarga() == Constantes.TIPOCARGA_INTERACTIVO) {
					ret = realizarCargaInteractivo(cargaDaoData, daoManager, carga, formato, id_persona, session, daoManagerData);
				}

				if (carga.getId_tipocarga() == Constantes.TIPOCARGA_ARCHIVO) {
					ret = realizarCargaInteractivo(cargaDaoData, daoManager, carga, formato, id_persona, session, daoManagerData);
					cargaDao.cambiarTipocarga(id_carga, Constantes.TIPOCARGA_LOTES);
				}

				Boolean rta = false;

				if (ret ) {
					rta = finalizarEnvioCarga(cargaDaoData, carga, daoManager, id_persona, formato, daoManagerData, session, listaValoresDinamicos, borrador);	
				}

				if (rta) {
					daoManager.commitTransaction();
					daoManagerData.commitTransaction();

					ValidacionEstructuraServicio.getInstance().generarValidacionesNoEnLineaHilo(id_carga);
				}

				return rta;

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error", e);
				SimpleLogger.setError("Error ocurrido en CargaServicio.realizarCarga", e);
				return false;
			} finally {
				try {
					daoManager.endTransaction();
					daoManagerData.endTransaction();
				} catch (Exception e2) {
					SimpleLogger.setError("Error ocurrido en CargaServicio.realizarCarga", e2);
				}
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			SimpleLogger.setError("Error ocurrido en CargaServicio.realizarCarga", e);
			return false;
		}
	}
	

	protected Boolean finalizarEnvioCarga(CargaDao cargaDaoData, Carga carga, DaoManager daoManager, Integer id_persona, Formato formato, DaoManager daoManagerData, Session session, List<ValorListaDinamicaCampo> listaValoresDinamicos) {

		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(carga.getId_usuario());

		String nombre_usuario = "..";

		if (usuario != null) {
			nombre_usuario = SQLUtils.escapeSql(StringUtils.trim(usuario.getNombre()) + " " + StringUtils.trim(usuario.getApellido()));
		}

		String sqlcompleto = "insert into ts01 (id_carga, id_persona, estado, fecha, nombre_cliente, id_cliente) values (" + carga.getId_carga() + "," + id_persona + ", '" + Constantes.CARGA_ESTADO_BORRADOR + "' , sysdate, '" + nombre_usuario + "' , " + carga.getId_usuario() + ")";
		
		SimpleLogger.setDebug(sqlcompleto);
		cargaDaoData.insertSQL(sqlcompleto);

		EstadoCargaServicio.getInstance().siguienteEstado(carga.getId_carga(), null, id_persona);

		new Thread(new HiloFinalizarCarga(formato, carga, session, listaValoresDinamicos)).start();

		return true;

	}
	
	
	/*recibe parametro borrador, para guardar la carga con el estado borrador*/
	protected Boolean finalizarEnvioCarga(CargaDao cargaDaoData, Carga carga, DaoManager daoManager, Integer id_persona, Formato formato, DaoManager daoManagerData, Session session, List<ValorListaDinamicaCampo> listaValoresDinamicos, Boolean borrador) {

		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(carga.getId_usuario());

		String nombre_usuario = "..";

		if (usuario != null) {
			nombre_usuario = SQLUtils.escapeSql(StringUtils.trim(usuario.getNombre()) + " " + StringUtils.trim(usuario.getApellido()));
		}
		String sqlcompleto = "";
		
		
		if(borrador != true){
			EstadoCargaServicio.getInstance().siguienteEstado(carga.getId_carga(), null, id_persona);
		}

		/*new Thread(new HiloFinalizarCarga(formato, carga, session, listaValoresDinamicos)).start();*/

		return true;

	}

	public boolean finalizarCarga(Carga carga) {

		Formato formato = FormatoServicio.getInstance().obtenerFormato(carga.getId_formato());
		Session session = new DummySession();

		Integer id_formato = carga.getId_formato();

		if (carga.getId_proceso() != null) {
			Proceso proceso = ProcesoServicio.getInstance().obtenerProceso(carga.getId_proceso());
			id_formato = proceso.getId_formato_salida();
		}

		session.setAttribute("var.id_formato", id_formato);

		List<ValorListaDinamicaCampo> listaValoresDinamicos = null;

		try {
			new HiloFinalizarCarga(formato, carga, session, listaValoresDinamicos).run();

		} catch (Exception e) {
			SimpleLogger.setError("Error init - finalizarCarga", e);
			return false;
		}

		return true;

	}

	// ---------------------------------------------------------------

	private Boolean realizarCargaInteractivo(CargaDao cargaDaoData, DaoManager daoManager, Carga carga, Formato formato, Integer id_persona, Session session, DaoManager daoManagerData) {

		CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

		Map<String, Campo> campos = new HashMap<String, Campo>();

		List<ElementoCarga> elementoscarga = cargaDao.obtenerElementosCarga(carga.getId_carga(), null);

		// -------------------------------

		List<TipoCampo> tiposcampolista = TipoCampoServicio.getInstance().obtenerTipoCampos();

		Map<Integer, TipoCampo> tiposcampo = new HashMap<Integer, TipoCampo>();

		for (TipoCampo tipoCampo : tiposcampolista) {
			tiposcampo.put(tipoCampo.getId_tipocampo(), tipoCampo);
		}

		// -------------------------------

		for (ElementoCarga elementoCarga : elementoscarga) {
			FormatoDato formatoDato = cargaDao.obtenerFormatoDatoPorIdElementoCarga(elementoCarga.getId_elementocarga());

			SimpleLogger.setDebug(">> Elemento Carga : " + elementoCarga.getId_elementocarga());
			realizarCargaElementoCarga(carga, cargaDaoData, daoManager, elementoCarga, formatoDato, formato, campos, tiposcampo, id_persona, session, daoManagerData);
		}

		return true;
	}

	// ---------------------------------------------------------------

	private void realizarCargaElementoCarga(Carga carga, CargaDao cargaDaoData, DaoManager daoManager, ElementoCarga elementoCarga, FormatoDato formatoDato, Formato formato, Map<String, Campo> campos, Map<Integer, TipoCampo> tiposcampo, Integer id_persona, Session session, DaoManager daoManagerData) {

		FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);
		FormatoCampo formatoCampoBase = formatoDao.obtenerFormatoCampo(formato.getId_formato());

		if (formatoCampoBase.getId_formato_campo().intValue() == formatoDato.getId_formato_campo().intValue()) {
			realizarCargaFormatoDato(carga, cargaDaoData, daoManager, elementoCarga, formato, formatoDato.getFormatoDatoList(), formatoCampoBase.getFormato_campo_list(), campos, tiposcampo, id_persona, session, daoManagerData);
		}

	}

	// ---------------------------------------------------------------
	// ---------------------------------------------------------------

	private Integer realizarCargaFormatoDato(Carga carga, CargaDao cargaDaoData, DaoManager daoManager, ElementoCarga elementoCarga, Formato formato, List<FormatoDato> formatoDatoList, List<FormatoCampo> formato_campo_list, Map<String, Campo> campos, Map<Integer, TipoCampo> tiposcampo, Integer id_persona, Session session, DaoManager daoManagerData) {

		Map<String, Object> datosRegistro = new HashMap<String, Object>();

		Integer id_estructura = null;

		for (FormatoCampo formatoCampo : formato_campo_list) {

			// obtiene el campo
			Campo campo = campos.get("" + formatoCampo.getId_campo());
			if (campo == null) {
				campo = CampoServicio.getInstance().obtenerCampo(formatoCampo.getId_campo());
				campos.put("" + formatoCampo.getId_campo(), campo);
			}

			if (campo.getId_estructura() != null) {
				id_estructura = campo.getId_estructura();
			}

			// --
			if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equalsIgnoreCase(campo.getMultiplicidad())) {

				// Tipo Ingreso Variable
				if (formatoCampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_SISTEMA) {

					String valor = VariableServicio.getInstance().obtenerValorVariable(formatoCampo.getId_variable(), id_persona);

					if (StringUtils.esNoVacio(valor)) {

						String tipodato = tiposcampo.get(campo.getId_tipocampo()).getTipo_dato();

						Object dato = CampoServicio.getValorReal(valor, tipodato);

						datosRegistro.put("C" + campo.getId_campo(), Crypto.E(dato));
					} else {
						datosRegistro.put("C" + campo.getId_campo(), null);
					}

				} else

				// Tipo Valor Vacio
				if (formatoCampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_VACIO) {

					datosRegistro.put("C" + campo.getId_campo(), null);

				} else

				// valor constante
				if (formatoCampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_CONSTANTE) {

					if (StringUtils.esNoVacio(formatoCampo.getValor_constante())) {

						String tipodato = tiposcampo.get(campo.getId_tipocampo()).getTipo_dato();

						String valor = formatoCampo.getValor_constante();

						// si esta relacionada a una estructura y el valor
						// ingressado no e un numero... el valor ingresado debe
						// ser un sql
						if (StringUtils.esNoVacio(valor) && !StringUtils.validaNumero(valor) && campo.getId_estructurarelacionada() != null) {
							String sql = "SELECT ID FROM T" + campo.getId_estructurarelacionada() + " WHERE " + RDServicio.reemplazarNombres(valor);

							SQLDao sqldao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);
							valor = StringUtils.toString(Crypto.DF(sqldao.selectSQLString(sql)));
						}

						Object dato = CampoServicio.getValorReal(valor, tipodato);

						if (campo.getId_estructurarelacionada() == null) {
							datosRegistro.put("C" + campo.getId_campo(), Crypto.E(dato));
						} else {
							datosRegistro.put("C" + campo.getId_campo(), dato);
						}

					} else {
						datosRegistro.put("C" + campo.getId_campo(), null);
					}

				} else

				// valor sesion
				if (formatoCampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_VALOR_SESION) {

					if (StringUtils.esNoVacio(formatoCampo.getValor_sesion())) {

						String tipodato = tiposcampo.get(campo.getId_tipocampo()).getTipo_dato();

						Object valor = session.getAttribute(formatoCampo.getValor_sesion());

						if (valor != null) {
							Object dato = CampoServicio.getValorReal(StringUtils.toString(valor), tipodato);

							datosRegistro.put("C" + campo.getId_campo(), Crypto.E(dato));
						} else {
							datosRegistro.put("C" + campo.getId_campo(), null);
						}

					} else {
						datosRegistro.put("C" + campo.getId_campo(), null);
					}

				} else

				if (formatoCampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_SELECCION_LISTA_DINAMICA) {

					for (FormatoDato formatoDato : formatoDatoList) {
						if (formatoDato.getId_formato_campo().intValue() == formatoCampo.getId_formato_campo().intValue()) {
							String tipodato = tiposcampo.get(campo.getId_tipocampo()).getTipo_dato();
							Object dato = CampoServicio.getValorReal(formatoDato.getValor(), tipodato);
							if ("object".equalsIgnoreCase(tipodato)) {
								datosRegistro.put("C" + campo.getId_campo(), dato);
							} else {
								datosRegistro.put("C" + campo.getId_campo(), Crypto.E(dato));
							}
						}
					}

				} else if (formatoCampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_SELECCION) {

					for (FormatoDato formatoDato : formatoDatoList) {
						if (formatoDato.getId_formato_campo().intValue() == formatoCampo.getId_formato_campo().intValue()) {
							String tipodato = tiposcampo.get(campo.getId_tipocampo()).getTipo_dato();
							if ("object".equalsIgnoreCase(tipodato)) {
								datosRegistro.put("C" + campo.getId_campo(), formatoDato.getValor());
							} else {
								Object dato = CampoServicio.getValorReal(formatoDato.getValor(), tipodato);
								datosRegistro.put("C" + campo.getId_campo(), Crypto.E(dato));
							}
						}
					}

				} else {

					for (FormatoDato formatoDato : formatoDatoList) {
						if (formatoDato.getId_formato_campo().intValue() == formatoCampo.getId_formato_campo().intValue()) {

							if (campo.getId_estructurarelacionada() == null) {

								datosRegistro.put("C" + campo.getId_campo(), formatoDato.getValor());
							} else {

								datosRegistro.put("C" + campo.getId_campo(), realizarCargaFormatoDato(carga, cargaDaoData, daoManager, elementoCarga, formato, formatoDato.getFormatoDatoList(), formatoCampo.getFormato_campo_list(), campos, tiposcampo, id_persona, session, daoManagerData));
							}
						}
					}
				}
			}

		}

		Integer newId = null;

		if (id_estructura != null) {

			Integer id_registro = buscarRegistroPorLlavePrimaria(id_estructura, datosRegistro, carga.getId_carga(), daoManagerData);
			if (id_registro != null) {
				newId = id_registro;
			} else {
				newId = cargaDaoData.obtenerSiguienteCreadatos();
				datosRegistro.put("ID", newId);
				datosRegistro.put("IDCARGA", carga.getId_carga());
				datosRegistro.put("IDELEMENTOCARGA", elementoCarga.getId_elementocarga());
				datosRegistro.put("ESTADO", Constantes.CARGA_ESTADO_SUBIDO);

				String sql = createSQLInsert("T" + id_estructura, datosRegistro);

				SimpleLogger.setDebug(sql);
				cargaDaoData.insertSQL(sql);
			}

		}
		// ------------------------------------------

		// Guarda registros de campos 1 a N

		for (FormatoCampo formatoCampo : formato_campo_list) {

			Campo campo = campos.get("" + formatoCampo.getId_campo());

			if (Constantes.CAMPO_MULTIPLICIDAD_MULTIPLE.equalsIgnoreCase(campo.getMultiplicidad())) {

				for (FormatoDato formatoDato : formatoDatoList) {
					if (formatoDato.getId_formato_campo().intValue() == formatoCampo.getId_formato_campo().intValue()) {
						if (formatoDato.getFormatoDatoList() != null) {
							realizarCargaFormatoDatoMultiple(carga, newId, cargaDaoData, daoManager, elementoCarga, formato, formatoDato.getFormatoDatoList(), formatoCampo.getFormato_campo_list(), campo, campos, tiposcampo, id_persona, session, daoManagerData);
						}
					}
				}
			}
		}

		return newId;

	}

	private void realizarCargaFormatoDatoMultiple(Carga carga, Integer newId, CargaDao cargaDaoData, DaoManager daoManager, ElementoCarga elementoCarga, Formato formato, List<FormatoDato> formatoDatoList, List<FormatoCampo> formato_campo_list, Campo campo, Map<String, Campo> campos, Map<Integer, TipoCampo> tiposcampo, Integer id_persona, Session session, DaoManager daoManagerData) {

		for (FormatoDato fDato : formatoDatoList) {

			Map<String, Object> mapData = new HashMap<String, Object>();

			mapData.put("ID", cargaDaoData.obtenerSiguienteCreadatos());
			mapData.put("IDCARGA", carga.getId_carga());
			mapData.put("ESTADO", Constantes.CARGA_ESTADO_SUBIDO);
			mapData.put("ID_P", newId);

			if (campo.getId_estructurarelacionada() != null) {
				mapData.put("ID_V", realizarCargaFormatoDato(carga, cargaDaoData, daoManager, elementoCarga, formato, fDato.getFormatoDatoList(), formato_campo_list, campos, tiposcampo, id_persona, session, daoManagerData));
			} else {
				mapData.put("ID_V", fDato.getValor());
			}

			String tabla = "T" + campo.getId_estructura() + "C" + campo.getId_campo();

			String sql = createSQLInsert(tabla, mapData);

			SimpleLogger.setDebug(sql);
			cargaDaoData.insertSQL(sql);
		}
	}

	public String createSQLInsert(String table, Map<String, Object> mapData) {

		String sql = "insert into " + table + " (";

		Object[] key = mapData.keySet().toArray();

		for (int i = 0; i < key.length; i++) {
			if (i > 0) {
				sql += ", ";
			}

			sql += key[i];
		}

		sql += " ) values ( ";

		for (int i = 0; i < key.length; i++) {
			if (i > 0) {
				sql += ", ";
			}

			Object dato = mapData.get(key[i]);

			if (dato instanceof String) {
				sql += "'" + StringUtils.escapeSQL((String) dato) + "'";
			}

			if (dato instanceof Long || dato instanceof Integer || dato instanceof BigDecimal) {
				sql += "" + dato;
			}

			if (dato instanceof Date) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				sql += "TO_DATE('" + dateFormat.format((Date) dato) + "', 'DD/MM/YYYY')";
			}

			if (dato instanceof Boolean) {
				sql += "'" + (((Boolean) dato) ? Constantes.SI : Constantes.NO) + "'";
			}

			if (dato == null) {
				sql += "null";
			}

		}

		sql += " )";

		return sql;
	}

	// ------------------------------------------------

	protected Boolean realizarCargaH(CargaDao cargaDao, Carga carga, Formato formato, List<FormatoCampo> listadoFCUnicos, Session session, Connection connection) throws Exception {

		InputStream inp = new FileInputStream(carga.getRuta());
		HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

		FormatoCampo formatoCampo = FormatoServicio.getInstance().obtenerFormatoCampo(formato.getId_formato());

		realizarCargaH(cargaDao, carga, wb, formato, formatoCampo, null, listadoFCUnicos, session, connection);

		// Guarda las Multiples
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			String sheetName = wb.getSheetName(i);

			if (sheetName != null && sheetName.indexOf("R") == 0) {

				Mensaje mensaje = (Mensaje) session.getAttribute("mensaje_carga_lotes");
				mensaje.setMensaje("Cargando hoja " + sheetName);
				mensaje.setInfo(sheetName);
				mensaje.setEstado("I");

				guardaHojaExcel(cargaDao, wb, wb.getSheet(sheetName), carga, null, session, connection);
			}
		}

		return true;
	}

	// ---------------------------------------------------------------

	public Map<String, Object> obtenerDatosCargaPorFormato(Integer id_carga, Integer id_formatosalida, List<ValorFiltro> valoresFiltro, Integer numero_pagina) {
		try {

			Map<String, Object> res = new HashMap<String, Object>();

			DaoManager daoManagerDS = DaoConfig.getDaoManager(1);
			DaoManager daoManagerCD = DaoConfig.getDaoManager(2);

			Map<Integer, Campo> mapacampos = new HashMap<Integer, Campo>();

			FormatoServicio formatoServicio = FormatoServicio.getInstance();
			Formato formatosalida = formatoServicio.obtenerFormato(id_formatosalida);

			if (formatosalida == null) {
				return res;
			}

			FormatoCampo formatocampobase = formatoServicio.obtenerFormatoCampo(id_formatosalida);

			List<Object> nodos = new ArrayList<Object>();

			List<String> condiciones = generarCondiciones(id_formatosalida, valoresFiltro);

			List<Map<String, Object>> datos = obtenerValorRegistroVistaEstructura(id_carga, formatosalida, formatocampobase, null, null, null, daoManagerDS, daoManagerCD, null, numero_pagina, mapacampos, condiciones);

			Integer total_registros = null;
			
			for (Map<String, Object> map : datos) {
				if (map.containsKey("TOTAL_REGISTROS")) {
					total_registros = (Integer) map.get("TOTAL_REGISTROS");
					res.put("@total_registros", total_registros);
				} else {
					Map<String, Object> data = crearNodoDatosVistaEstructura(id_carga, formatosalida, formatocampobase, daoManagerDS, daoManagerCD, map, new HashMap<String, List<ValorLista>>(), mapacampos, (total_registros != null && total_registros.equals(1)));
					if (data != null) {
						nodos.add(data);
					}
				}
			}
			
			res.put("#element", nodos);
			res.put("@name", formatocampobase.getTitulo());
			res.put("@type", "list");

			return res;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// -----------------------------------
	// -----------------------------------

	private List<String> generarCondiciones(Integer id_formatosalida, List<ValorFiltro> valoresFiltro) {

		List<String> condiciones = new ArrayList<String>();

		CargaDao cargaDao = (CargaDao) DaoConfig.getDao(CargaDao.class);

		List<FormatoFiltro> formatosfiltro = cargaDao.obtenerFormatosFiltro(id_formatosalida);

		if (valoresFiltro != null) {
			for (ValorFiltro valorfiltro : valoresFiltro) {

				for (FormatoFiltro formatoFiltro : formatosfiltro) {

					if (formatoFiltro.getId_formatofiltro().intValue() == valorfiltro.getId_formatofiltro()) {

						if (StringUtils.trimToNull(valorfiltro.getValor()) != null) {

							String condicion = RDServicio.reemplazarNombres(formatoFiltro.getCondicion().replace("#valor#", "" + StringUtils.trimToNull(valorfiltro.getValor())));

							condiciones.add(condicion);
						}
					}

				}
			}
		}

		return condiciones;
	}

	// -----------

	private List<Map<String, Object>> obtenerValorRegistroVistaEstructura(Integer id_carga, Formato formatosalida, FormatoCampo formatocampobase, FormatoCampo formatocampopadre, Integer id_valorpadre, String valor_campo, DaoManager daoManagerDS, DaoManager daoManagerCD, Campo campobase, Integer numero_pagina, Map<Integer, Campo> camposmapa, List<String> condiciones) {

		boolean espadre = Constantes.SI.equalsIgnoreCase(formatocampobase.getEstructura_padre());

		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();

		CargaDao cargaDao = (CargaDao) daoManagerCD.getDao(CargaDao.class);
		CampoDao campoDao = (CampoDao) daoManagerDS.getDao(CampoDao.class);

		Campo campo = null;
		Integer id_estructura = null;
		Integer id_campopadre = null;
		Integer idestructurapadre = null;

		if (formatocampopadre == null) {
			idestructurapadre = null;
			id_estructura = formatosalida.getId_estructura();

		} else {

			condiciones = null;

			if (espadre) {

				Integer id_campo = formatocampobase.getId_campo();
				campo = camposmapa.get(id_campo);
				if (campo == null) {
					campo = campoDao.obtenerCampo(id_campo);
					camposmapa.put(id_campo, campo);
				}

				idestructurapadre = campo.getId_estructurarelacionada();
				id_campopadre = formatocampobase.getId_campo();
				id_estructura = campo.getId_estructura();
			} else {

				Integer id_campo = formatocampobase.getId_campo();
				campo = camposmapa.get(id_campo);
				if (campo == null) {
					campo = campoDao.obtenerCampo(id_campo);
					camposmapa.put(id_campo, campo);
				}

				idestructurapadre = campo.getId_estructura();
				id_campopadre = formatocampobase.getId_campo();
				id_estructura = campo.getId_estructurarelacionada();
			}

		}

		List<Campo> campos = CampoServicio.getInstance().obtenerCamposPorEstructura(id_estructura);

		Boolean multiple = espadre || (campobase != null && Constantes.CAMPO_MULTIPLICIDAD_MULTIPLE.equalsIgnoreCase(campobase.getMultiplicidad()));

		String url = DaoConfig.getUrl(daoManagerCD);

		Boolean esOracle = url.indexOf("oracle") >= 0;

		String valorCampoTmp = StringUtils.getStringCorchetes(valor_campo);
		if (multiple || id_campopadre == null || StringUtils.esNoVacio(valorCampoTmp)) {
			ret = cargaDao.obtenerDatosVistaEstructura(id_carga, id_estructura, idestructurapadre, id_campopadre, id_valorpadre, valor_campo, campos, formatocampobase, multiple, condiciones, numero_pagina, esOracle);
		}

		return ret;
	}

	// -----------

	private Map<String, Object> crearNodoDatosVistaEstructura(Integer id_carga, Formato formatosalida, FormatoCampo formatocampo, DaoManager daoManagerDS, DaoManager daoManagerCD, Map<String, Object> mapdata, Map<String, List<ValorLista>> maplistavalores, Map<Integer, Campo> mapacampos, Boolean nodoAdjuntos) throws Exception {

		if (formatocampo.getId_campo() != null && (!StringUtils.esVerdad(formatocampo.getSeleccion_campo())) && (!StringUtils.esVerdad(formatocampo.getSeleccion_visualizacion()))) {
			return null;
		}

		Map<String, Object> res = new HashMap<String, Object>();
		Integer id_padre = null;
		try {
			id_padre = new Integer(mapdata.get("ID").toString());
		} catch (Exception e) {
			SimpleLogger.setDebug("Sin padre");
		}

		res.put("@name", formatocampo.getTitulo());
		res.put("@helpMessage", formatocampo.getAyuda());
		res.put("@id_campo", formatocampo.getId_campo());

		if (!mapdata.isEmpty() && formatocampo.getFormato_campo_list() != null && formatocampo.getFormato_campo_list().size() > 0) {
			res.put("@ID", id_padre);
			List<Object> nodos = new ArrayList<Object>();
			for (FormatoCampo fcampo : formatocampo.getFormato_campo_list()) {

				boolean espadre = Constantes.SI.equalsIgnoreCase(fcampo.getEstructura_padre());

				String valor_campo = null;

				Campo campo = null;
				if (!espadre) {
					try {
						valor_campo = mapdata.get("C" + fcampo.getId_campo()).toString();
					} catch (Exception e) {
						SimpleLogger.setInfo("Sin valor");
					}

					Integer id_campo = fcampo.getId_campo();
					campo = mapacampos.get(id_campo);
					if (campo == null) {
						campo = CampoServicio.getInstance().obtenerCampo(id_campo);
						mapacampos.put(id_campo, campo);
					}
				}

				if (fcampo.getId_estructura() != null) {

					List<Map<String, Object>> datos = null;
					if ((valor_campo == null || valor_campo.length() == 0) && id_padre == null) {// TODO padre aniadido
						datos = new ArrayList<Map<String, Object>>();
					} else {
						datos = obtenerValorRegistroVistaEstructura(id_carga, formatosalida, fcampo, formatocampo, id_padre, valor_campo, daoManagerDS, daoManagerCD, campo, null, mapacampos, null);
					}

					if (espadre || Constantes.CAMPO_MULTIPLICIDAD_MULTIPLE.equalsIgnoreCase(campo.getMultiplicidad())) {
						// en este momento espadre siempre es falso, solo se usa el else para campos 1..N a estructuras

						if (!espadre || (datos != null && !datos.isEmpty())) {
							Map<String, Object> inres = new HashMap<String, Object>();
							List<Object> innodos = new ArrayList<Object>();

							for (Map<String, Object> map : datos) {
								Map<String, Object> data = crearNodoDatosVistaEstructura(id_carga, formatosalida, fcampo, daoManagerDS, daoManagerCD, map, maplistavalores, mapacampos, false);
								if (data != null) {
									innodos.add(data);
								}
							}

							inres.put("#element", innodos);
							inres.put("@name", fcampo.getTitulo());
							inres.put("@helpMessage", fcampo.getAyuda());
							inres.put("@id_campo", fcampo.getId_campo());
							inres.put("@type", "list");

							res.put("@id_estructura", formatocampo.getId_estructura());
							try {
								res.put("@ESTADO", mapdata.get("ESTADO").toString());
							} catch (Exception e) {
								SimpleLogger.setInfo("Sin estado");
							}

							nodos.add(inres);
						}
					} else {

						for (Map<String, Object> map : datos) {

							if ("S".equalsIgnoreCase(fcampo.getSeleccion_campo())) {
								Map<String, Object> aa = crearNodoDatosVistaEstructura(id_carga, formatosalida, fcampo, daoManagerDS, daoManagerCD, map, maplistavalores, mapacampos, false);
								if (aa != null) {
									aa.put("@type", "object");
									nodos.add(aa);
								}
							} else {
								Map<String, Object> data = crearNodoDatosVistaEstructura(id_carga, formatosalida, fcampo, daoManagerDS, daoManagerCD, mapdata, maplistavalores, mapacampos, false);
								if (data != null) {
									nodos.add(data);
								}
							}

							res.put("@id_estructura", formatosalida.getId_estructura());

							try {
								res.put("@ESTADO", mapdata.get("ESTADO").toString());
							} catch (Exception e) {
								SimpleLogger.setInfo("Sin estado");
							}
						}

						if (datos.isEmpty()) {
							Map<String, Object> aa = crearNodoDatosVistaEstructura(id_carga, formatosalida, fcampo, daoManagerDS, daoManagerCD, new HashMap<String, Object>(), maplistavalores, mapacampos, false);
							if (aa != null) {
								nodos.add(aa);
							}

							try {
								res.put("@ESTADO", mapdata.get("ESTADO").toString());
							} catch (Exception e) {
								SimpleLogger.setInfo("Sin estado");
							}
						}

					}

				} else {

					if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equalsIgnoreCase(campo.getMultiplicidad())) {

						Map<String, Object> data = crearNodoDatosVistaEstructura(id_carga, formatosalida, fcampo, daoManagerDS, daoManagerCD, mapdata, maplistavalores, mapacampos, false);
						if (data != null) {
							nodos.add(data);
						}

					} else {// TODO en este caso es un campo de tipo estructura
							// con multipicidad 1..N

						Map<String, Object> inres = new HashMap<String, Object>();
						List<Object> innodos = new ArrayList<Object>();

						List<Object> data = valoresRelacionMultiple(daoManagerCD, new Long(mapdata.get("ID").toString()), campo.getId_estructura(), campo.getId_campo());

						for (Object object : data) {

							Map<String, Object> resnodos = new HashMap<String, Object>();
							List<Object> lnodos = new ArrayList<Object>();

							Map<String, Object> nodo = new HashMap<String, Object>();
							nodo.put("@name", fcampo.getTitulo());
							nodo.put("@value", FormatoUtils.obtenerValorPorFormato(fcampo, Crypto.DF(object)));
							nodo.put("@id_campo", fcampo.getId_campo());

							if (campo.getId_lista_valores() != null) {
								nodoListavalor(nodo, campo, maplistavalores, StringUtils.toString(object), formatocampo);
							} else {
								nodo.put("@value", FormatoUtils.obtenerValorPorFormato(fcampo, Crypto.DF(object)));
							}

							lnodos.add(nodo);
							resnodos.put("#nodo", lnodos);
							resnodos.put("@name", "-");
							innodos.add(resnodos);
						}

						inres.put("#element", innodos);
						inres.put("@name", fcampo.getTitulo());
						inres.put("@helpMessage", fcampo.getAyuda());
						inres.put("@id_campo", fcampo.getId_campo());
						inres.put("@type", "list");

						res.put("@id_estructura", formatocampo.getId_estructura());
						try {
							res.put("@ESTADO", mapdata.get("ESTADO").toString());
						} catch (Exception e) {
							SimpleLogger.setInfo("Sin estado");
						}

						nodos.add(inres);
					}
				}

			}
			SimpleLogger.setInfo("nodoAdjuntos: " + nodoAdjuntos);
			
			if (nodoAdjuntos && Constantes.SI.equals(formatosalida.getSeccion_adjuntos_reporte())) {
				Map<String, Object> data = new HashMap<>();
				SimpleLogger.setDebug("Solicitud inicial: " + formatosalida.getSolicitud_inicial());
				if(Constantes.SI.equals(formatosalida.getSolicitud_inicial())) {
					data = ArchivoAdjuntoServicio.getInstance().obtenerAdjuntosPorCargaNodo(id_carga, 1);
					SimpleLogger.setInfo("data: " + data);
				} else {
					data = ArchivoAdjuntoServicio.getInstance().obtenerAdjuntosPorCargaNodo(id_carga, null);
				}
				
				if (data != null) {
					nodos.add(data);
				}				
			}
			
			res.put("#nodo", nodos);

		} else {
			if (!Constantes.SI.equalsIgnoreCase(formatocampo.getEstructura_padre())) {

				CampoDao campoDao = (CampoDao) daoManagerDS.getDao(CampoDao.class);

				Integer id_campo = formatocampo.getId_campo();
				Campo campo = mapacampos.get(id_campo);

				if (campo == null) {
					campo = campoDao.obtenerCampo(id_campo);
					mapacampos.put(id_campo, campo);
				}

				if (Constantes.SI.equalsIgnoreCase(campo.getLlaveprimaria())) {
					res.put("@id", Constantes.SI);
				}

				if (campo.getId_lista_valores() != null) {
					nodoListavalor(res, campo, maplistavalores, (String) mapdata.get("C" + campo.getId_campo()), formatocampo);
				} else {

					Object obj_valor = mapdata.get("C" + campo.getId_campo());
					String valor = (obj_valor == null) ? (null) : (obj_valor.toString());

					if (valor != null && !StringUtils.esVacio(valor)) {
						// Si es estructura relaciona
						if (valor.charAt(0) == '[') {
							String registro = valor.substring(1, valor.indexOf("]"));
							res.put("@ID_HIJO", registro);
						}
					}

					res.put("@value", FormatoUtils.obtenerValorPorFormato(formatocampo, Crypto.DF(mapdata.get("C" + campo.getId_campo()))));
				}
			}
		}

		return res;

	}

	// ---------------------------------------------------------------

	private void nodoListavalor(Map<String, Object> nodo, Campo campo, Map<String, List<ValorLista>> maplistavalores, String strvalorlista, FormatoCampo formatoCampo) {
		List<ValorLista> valores = maplistavalores.get(campo.getId_lista_valores().toString());

		if (valores == null) {
			valores = ListaValoresServicio.getInstance().obtenerValoresLV(campo.getId_lista_valores());
			maplistavalores.put(campo.getId_lista_valores().toString(), valores);
		}

		ValorLista valor = null;
		for (ValorLista valorLista : valores) {
			try {
				if (valorLista.getValueid().equals(Crypto.D(StringUtils.getStringCorchetes(strvalorlista)))) {
					valor = valorLista;
				}
			} catch (Exception e) {
			}
		}

		if (valor != null) {
			nodo.put("@colorfondo", valor.getColorfondo());
			nodo.put("@colorletra", valor.getColorletra());
			nodo.put("@value", FormatoUtils.obtenerValorPorFormato(formatoCampo, valor.getNombre()));
		}
	}

	public List<Object> valoresRelacionMultiple(DaoManager daoManagerCD, Long id, Integer id_estructura, Integer id_campo) {

		CargaDao cargaDao = (CargaDao) daoManagerCD.getDao(CargaDao.class);

		return cargaDao.obtenerValoresRelacionMultiple(id_estructura, id_campo, id);
	}

	private Boolean realizarCargaH(CargaDao cargaDao, Carga carga, HSSFWorkbook wb, Formato formato, FormatoCampo formatoCampo, FormatoCampo formatoCampopadre, List<FormatoCampo> listadoFCUnicos, Session session, Connection connection) throws Exception {

		List<FormatoCampo> fcampoList = formatoCampo.getFormato_campo_list();
		if (fcampoList.size() > 0) {

			Boolean repetido = generarFormatosCamposUnicos(formatoCampo, listadoFCUnicos);
			// ------------------------------------------
			// ------------------------------------------
			// Guardar los Hijos
			// ------------------------------------------

			for (FormatoCampo fcampo : fcampoList) {

				if (Constantes.NO.equals(fcampo.getEstructura_padre())) {
					if (fcampo.getFormato_campo_list().size() > 0) {
						realizarCargaH(cargaDao, carga, wb, formato, fcampo, formatoCampo, listadoFCUnicos, session, connection);
					}
				}
			}

			// ------------------------------------------
			// ------------------------------------------
			// Guarda Registro
			// ------------------------------------------

			if (!repetido) {
				Mensaje mensaje = (Mensaje) session.getAttribute("mensaje_carga_lotes");
				mensaje.setMensaje("Cargando hoja " + formatoCampo.getTitulo());
				mensaje.setInfo(formatoCampo.getTitulo());
				Boolean e = guardaHojaExcel(cargaDao, wb, wb.getSheet(formatoCampo.getTituloExcel()), carga, formatoCampo, session, connection);

				if (!e) {
					SimpleLogger.setInfo("Error Guardando: " + formatoCampo.getTituloExcel());
				}
			}

			// ------------------------------------------
			// ------------------------------------------
			// Guarda los padres
			// ------------------------------------------

			for (FormatoCampo fcampo : fcampoList) {

				if (Constantes.SI.equals(fcampo.getEstructura_padre())) {
					if (fcampo.getFormato_campo_list().size() > 0) {
						realizarCargaH(cargaDao, carga, wb, formato, fcampo, formatoCampo, listadoFCUnicos, session, connection);
					}
				}
			}
		}

		return true;
	}

	// --------------------------------------------------------------------------

	private Boolean guardaHojaExcel(CargaDao cargaDao, HSSFWorkbook wb, HSSFSheet sheet, Carga carga, FormatoCampo formatoCampo, Session session, Connection connection) throws Exception {

		// -------------------------------
		// Identifica tabla y campos

		HSSFRow fila1 = getRow(sheet, 0);
		String id_tabla = fila1.getCell((short) 0).getRichStringCellValue().getString();

		SimpleLogger.setDebug("Tabla: " + id_tabla);

		String[] id_campos = new String[fila1.getLastCellNum() - 1];

		Map<String, Campo> campos = new HashMap<String, Campo>();

		for (short i = 1; i < fila1.getLastCellNum(); i++) {

			try {
				id_campos[i - 1] = fila1.getCell(i).getRichStringCellValue().getString();

				if (id_campos[i - 1] != null) {

					Campo campo = CampoServicio.getInstance().obtenerCampo(new Integer(id_campos[i - 1].substring(1)));

					if (campo != null && Constantes.CAMPO_MULTIPLICIDAD_UNICO.equals(campo.getMultiplicidad())) {
						campos.put(id_campos[i - 1], campo);
					}
				}

			} catch (Exception e) {
				id_campos[i - 1] = null;
			}
		}

		if (campos.size() > 0) {

			// -------------------------------

			List<TipoCampo> tiposcampolista = TipoCampoServicio.getInstance().obtenerTipoCampos();

			Map<Integer, TipoCampo> tiposcampo = new HashMap<Integer, TipoCampo>();

			for (TipoCampo tipoCampo : tiposcampolista) {
				tiposcampo.put(tipoCampo.getId_tipocampo(), tipoCampo);
			}

			// -------------------------------
			// Sql basico

			String sql = "insert into " + id_tabla + " ( ID, IDCARGA, ESTADO, NREG ";
			String sqlValues = ",?";
			for (String ncampo : id_campos) {
				if (ncampo != null) {
					sql += ", " + ncampo;
					sqlValues += ",?";
				}
			}

			sql += ") values (?, " + carga.getId_carga() + ", '" + Constantes.CARGA_ESTADO_SUBIDO + "'";

			// -------------------------------
			// Sql basico

			Map<String, String> valor_variable = new HashMap<String, String>();

			Mensaje mensaje = (Mensaje) session.getAttribute("mensaje_carga_lotes");
			mensaje.setEstado("C");

			String sqlInsert = sql + sqlValues + ")";

			CargaMasivaSqlUtil cargaMasivaSqlUtil = new CargaMasivaSqlUtil(connection);
			cargaMasivaSqlUtil.prepararSentencia(sqlInsert);

			try {

				MapCache<String, String> cacheResultados = new MapCache<String, String>(100);

				for (short f = 2; f <= sheet.getLastRowNum(); f++) {
					mensaje.setMensaje("Cargando registro...  " + (f - 1) + " | ");

					HSSFRow fila = getRow(sheet, f);
					Boolean pinta = false;

					Map<Integer, String> parametros = new HashMap<Integer, String>();
					Map<Integer, Class<?>> tipos = new HashMap<Integer, Class<?>>();

					SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

					parametros.put(2, new Integer((f - 1)).toString());
					tipos.put(2, Integer.class);

					short jp = 0;

					String sqlcompleto = sql + ", " + (f - 1);
					for (short j = 0; j < id_campos.length; j++) {

						String ncampo = id_campos[j];
						String valor = null;
						Class<?> tipo = String.class;

						HSSFCell cell = fila.getCell((short) (j + 1));

						if (ncampo != null) {

							if (cell != null) {

								switch (cell.getCellType()) {
								case HSSFCell.CELL_TYPE_STRING:
									valor = cell.getRichStringCellValue().getString();
									break;

								case HSSFCell.CELL_TYPE_BOOLEAN:
									valor = cell.getBooleanCellValue() ? Constantes.SI : Constantes.NO;
									break;

								case HSSFCell.CELL_TYPE_NUMERIC:
									valor = new BigDecimal(cell.getNumericCellValue()).toString();
									break;

								default:
									break;
								}
							}
							// -----------------

							boolean esValorSistema = false;

							Campo campo = campos.get(ncampo);

							FormatoCampo fcampo = null;

							for (FormatoCampo formatocampo : formatoCampo.getFormato_campo_list()) {
								if (formatocampo.getId_campo() != null && formatocampo.getId_campo().intValue() == campo.getId_campo()) {
									fcampo = formatocampo;
								}
							}

							// verifica si es constante
							if (fcampo != null && fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_CONSTANTE) {
								esValorSistema = true;
								valor = fcampo.getValor_constante();
							}

							// verifica si es valor de sesion
							if (fcampo != null && fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_VALOR_SESION) {
								esValorSistema = true;

								Object valor_sesion = session.getAttribute(fcampo.getValor_sesion());
								valor = StringUtils.toString(valor_sesion);
							}

							// verifica si es variable
							if (fcampo != null && fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_SISTEMA) {
								esValorSistema = true;

								valor = valor_variable.get(fcampo.getId_variable() + "_" + carga.getId_persona());

								if (valor == null) {
									valor = VariableServicio.getInstance().obtenerValorVariable(fcampo.getId_variable(), carga.getId_persona());
									valor_variable.put(fcampo.getId_variable() + "_" + carga.getId_persona(), valor);
								}
							}

							TipoCampo tipoCampo = tiposcampo.get(campo.getId_tipocampo());

							if (tipoCampo != null) {

								try {

									if ("Object".equalsIgnoreCase(tipoCampo.getTipo_dato())) {
										tipo = Integer.class;
									}

									if ((fcampo.getId_lista_dinamica() != null || campo.getId_lista_valores() != null) && valor != null && valor.toString().trim().length() > 0) {
										valor = StringUtils.getStringCorchetes(valor);
									}

									if (valor != null) {

										if (!esValorSistema) {
											pinta = true;
										}

										if ("String".equalsIgnoreCase(tipoCampo.getTipo_dato())) {
											valor = Crypto.E(valor);
										}

										if ("Boolean".equalsIgnoreCase(tipoCampo.getTipo_dato())) {
											valor = Crypto.E(StringUtils.esVerdad(valor));
										}

										if ("Date".equalsIgnoreCase(tipoCampo.getTipo_dato())) {

											if (fcampo == null || (fcampo.getTipo_ingreso() != FormatoCampo.TIPOINGRESO_USUARIO_SISTEMA && fcampo.getTipo_ingreso() != FormatoCampo.TIPOINGRESO_USUARIO_VALOR_SESION && fcampo.getTipo_ingreso() != FormatoCampo.TIPOINGRESO_USUARIO_CONSTANTE)) {
												valor = Crypto.E(HSSFDateUtil.getJavaDate(new Double(valor)));
											} else {
												try {
													valor = Crypto.E(new Date(new Long(valor)));
												} catch (Exception e) {
													valor = Crypto.E(new SimpleDateFormat("dd/MM/yyyy").parse(valor));
												}
											}
										}

										if ("Integer".equalsIgnoreCase(tipoCampo.getTipo_dato()) || "Long".equalsIgnoreCase(tipoCampo.getTipo_dato())) {
											valor = Crypto.E(new Long(valor));
										}

										if ("Float".equalsIgnoreCase(tipoCampo.getTipo_dato()) || "Double".equalsIgnoreCase(tipoCampo.getTipo_dato()) || "BigDecimal".equalsIgnoreCase(tipoCampo.getTipo_dato())) {
											valor = Crypto.E(new BigDecimal(valor));
										}

										if ("Object".equalsIgnoreCase(tipoCampo.getTipo_dato())) {

											if (campo.getId_estructurarelacionada() != null) {

												if (FormatoCampo.TIPOINGRESO_USUARIO_SELECCION_LISTA_DINAMICA == fcampo.getTipo_ingreso()) {
													valor = StringUtils.getStringCorchetes(valor);
												} else {
													String sqlNumber = "select ID from T" + campo.getId_estructurarelacionada() + " where NREG=" + StringUtils.getStringCorchetes(valor) + " and IDCARGA = " + carga.getId_carga();
													if (!cacheResultados.contains(sqlNumber)) {
														Integer valorNumero = sqlDao.selectSQLNumber(sqlNumber);
														valor = StringUtils.toString(valorNumero);
														cacheResultados.put(sqlNumber, valor);
													} else {
														valor = cacheResultados.get(sqlNumber);
													}

												}

											} else {
												valor = null;
											}
										}

									}

								} catch (Exception e) {
									valor = null;
									try {
										SimpleLogger.setWarn("No se puede obtener el valor del campo " + campo.getNombre() + " de tipo " + tipoCampo.getTipo_dato() + " con valor [" + valor + "]", e);
									} catch (Exception e2) {
										SimpleLogger.setError("Ha ocurrido un error", e);
									}

								}

								parametros.put(jp + 3, valor);
								tipos.put(jp + 3, tipo);

								if (tipo.equals(Integer.class)) {
									sqlcompleto += ", " + valor;
								} else {
									sqlcompleto += ", '" + valor + "'";
								}

							} else {
								parametros.put(jp + 3, Crypto.E(valor));
								tipos.put(jp + 3, tipo);
								sqlcompleto += ", '" + Crypto.E(valor) + "'";
							}

							jp++;

						}
					}

					sqlcompleto += " )";

					if (pinta) {
						parametros.put(1, obtenerSiguienteCreadatos().toString());
						tipos.put(1, Integer.class);

						cargaMasivaSqlUtil.insertarRegistro(parametros, tipos);
					}

				}

			} catch (Exception e) {
				cargaMasivaSqlUtil.rollback();
				throw new RuntimeException(e);
			}

			mensaje.setMensaje("Guardando registros en base de datos ");
			cargaMasivaSqlUtil.commit();
			SimpleLogger.setDebug("Finaliza para tabla: T" + id_tabla);

		} else if (id_campos != null && id_campos.length > 0) {

			String sql = "insert into " + id_tabla + "(ID, IDCARGA, ESTADO, ID_P, ID_V ) VALUES ( nextvalue('SST'), " + carga.getId_carga() + ", '" + Constantes.CARGA_ESTADO_SUBIDO + "' ";

			String tablabase = id_tabla.split("C")[0];
			String id_campobase = id_tabla.split("C")[1];

			Integer estructurarelacionada = CampoServicio.getInstance().obtenerCampo(new Integer(id_campobase)).getId_estructurarelacionada();
			Integer listavalores = CampoServicio.getInstance().obtenerCampo(new Integer(id_campobase)).getId_lista_valores();

			if (estructurarelacionada != null) {
				String id_estructurabase = estructurarelacionada.toString();

				for (short f = 2; f < sheet.getLastRowNum(); f++) {

					HSSFRow fila = getRow(sheet, f);

					String valor1 = fila.getCell((short) 1).getRichStringCellValue().getString();
					String valor2 = fila.getCell((short) 2).getRichStringCellValue().getString();

					if (valor1 != null && valor1.length() > 0 && valor2 != null && valor2.length() > 0) {

						String cc1 = ", (select ID from " + tablabase + " where NREG=" + StringUtils.getStringCorchetes(valor1) + " and IDCARGA = " + carga.getId_carga() + ") ";

						String cc2 = ", (select ID from T" + id_estructurabase + " where NREG=" + StringUtils.getStringCorchetes(valor2) + " and IDCARGA = " + carga.getId_carga() + ") ";

						String sqlcompleto = sql + cc1 + cc2 + ")";

						SimpleLogger.setDebug(sqlcompleto);
						cargaDao.insertSQL(sqlcompleto);
					}

				}
			} else if (listavalores != null) {

				String id_listavalores = listavalores.toString();

				for (short f = 2; f < sheet.getLastRowNum(); f++) {

					HSSFRow fila = getRow(sheet, f);

					String valor1 = fila.getCell((short) 1).getRichStringCellValue().getString();
					String valor2 = fila.getCell((short) 2).getRichStringCellValue().getString();

					if (valor1 != null && valor1.length() > 0 && valor2 != null && valor2.length() > 0) {

						String cc1 = ", (select ID from " + tablabase + " where NREG=" + StringUtils.getStringCorchetes(valor1) + " and IDCARGA = " + carga.getId_carga() + ") ";

						String cc2 = ", (select ID from L" + id_listavalores + " where ID='" + Crypto.E(valor2) + "' ) ";

						String sqlcompleto = sql + cc1 + cc2 + ")";

						SimpleLogger.setDebug(sqlcompleto);
						cargaDao.insertSQL(sqlcompleto);
					}

				}

			} else {

				for (short f = 2; f < sheet.getLastRowNum(); f++) {

					HSSFRow fila = getRow(sheet, f);

					String valor1 = fila.getCell((short) 1).getRichStringCellValue().getString();
					String valor2 = null;

					HSSFCell cell = fila.getCell((short) 2);
					if (cell != null) {

						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							valor2 = cell.getRichStringCellValue().getString();
							break;

						case HSSFCell.CELL_TYPE_BOOLEAN:
							valor2 = cell.getBooleanCellValue() ? Constantes.SI : Constantes.NO;
							break;

						case HSSFCell.CELL_TYPE_NUMERIC:
							valor2 = new BigDecimal(cell.getNumericCellValue()).toString();
							break;

						default:
							break;
						}

						if (valor2 != null) {
							valor2 = "'" + Crypto.E(valor2) + "'";
						}
					}

					if (valor1 != null && valor1.length() > 0 && valor2 != null && valor2.length() > 0) {

						String cc1 = ", (select ID from " + tablabase + " where NREG=" + StringUtils.getStringCorchetes(valor1) + " and IDCARGA = " + carga.getId_carga() + ") ";

						String cc2 = "," + valor2;

						String sqlcompleto = sql + cc1 + cc2 + ")";

						SimpleLogger.setDebug(sqlcompleto);
						cargaDao.insertSQL(sqlcompleto);
					}

				}
			}
		}

		return true;
	}

	public Boolean guardarTablaControl(Integer id_carga) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			List<TablaOperacion> list = generarTablaControl(id_carga);

			for (TablaOperacion tablaOperacion : list) {
				cargaDao.guardarTablaOperacion(id_carga, tablaOperacion);
			}

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public NumeroValor obtenerDatosExcel(Integer id_carga) {

		NumeroValor numeroValor = new NumeroValor();

		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
			Carga carga = cargaDao.obtener(id_carga);

			if (carga.getRuta() == null) {
				return null;
			}

			Formato formato = FormatoServicio.getInstance().obtenerFormato(carga.getId_formato());

			InputStream inp = new FileInputStream(carga.getRuta());
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

			for (int idSheet = 0; idSheet < wb.getNumberOfSheets(); idSheet++) {
				HSSFSheet sheet = wb.getSheetAt(idSheet);
				HSSFRow row = getRow(sheet, 0);
					for (short idcol = 0; idcol < row.getLastCellNum(); idcol++) {
						HSSFCell cell = row.getCell(idcol);
						if (cell != null) {
							String idCampo = "C" + formato.getId_campo_totalizador();
							if (idCampo.equals(cell.getRichStringCellValue().getString())) {
								Integer numero_registros = 0;
								Double valor_total = 0.0;
								for (int i = 2; i < formato.getRegistrosporcarga() + 2; i++) {
									HSSFCell cell1 = getRow(sheet, i).getCell(idcol);
									if(HSSFCell.CELL_TYPE_NUMERIC == cell1.getCellType()){
										Double dval = cell1.getNumericCellValue();
										valor_total += dval;
										numero_registros += 1; 
									}
								}
								numeroValor.setNumero_registros(numero_registros);
								numeroValor.setValor_total(valor_total);
							}
						}
					}
			}
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		return numeroValor;
	}
	
	// ---------------------
	
	public List<TablaOperacion> generarTablaControl(Integer id_carga) {

		List<TablaOperacion> listTablaOperacion = new ArrayList<TablaOperacion>();

		DaoManager daoManager;
		try {
			daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
			Carga carga = cargaDao.obtener(id_carga);

			if (carga.getRuta() == null) {
				return null;
			}

			Formato formato = FormatoServicio.getInstance().obtenerFormato(carga.getId_formato());

			List<FormatoCampo> ListFormatoCampo = FormatoServicio.getInstance().obtenerFormatosCamposOperacion(carga.getId_formato());

			InputStream inp = new FileInputStream(carga.getRuta());
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

			for (int idSheet = 0; idSheet < wb.getNumberOfSheets(); idSheet++) {
				HSSFSheet sheet = wb.getSheetAt(idSheet);
				HSSFRow row = getRow(sheet, 0);
				for (FormatoCampo formatoCampo : ListFormatoCampo) {
					for (short idcol = 0; idcol < row.getLastCellNum(); idcol++) {
						HSSFCell cell = row.getCell(idcol);
						if (cell != null) {
							String idCampo = "C" + formatoCampo.getId_campo();
							if (idCampo.equals(cell.getRichStringCellValue().getString())) {
								listTablaOperacion.add(realizarOperacion(formato, formatoCampo.getId_operacion(), sheet, idcol, formatoCampo.getId_campo(), formatoCampo.getTitulo()));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
		return listTablaOperacion;
	}

	// ---------------------

	private TablaOperacion realizarOperacion(Formato formato, Integer id_operacion, HSSFSheet sheet, short idcol, Integer id_campo, String titulo) {

		TablaOperacion tOperacion = new TablaOperacion();

		Campo campo = CampoServicio.getInstance().obtenerCampo(id_campo);
		Operacion operacion = FormatoServicio.getInstance().obtenerOperacionId(id_operacion);

		List<String> data = new ArrayList<String>();

		for (int i = 2; i < formato.getRegistrosporcarga() + 2; i++) {

			HSSFCell cell = getRow(sheet, i).getCell(idcol);
			String value = null;

			if (cell != null) {

				switch (cell.getCellType()) {

				case HSSFCell.CELL_TYPE_FORMULA:
					try {
						value = cell.getRichStringCellValue().getString();
					} catch (Exception e) {
						value = cell.getNumericCellValue() + "";
					}
					break;

				case HSSFCell.CELL_TYPE_NUMERIC:
					Double dval = cell.getNumericCellValue();
					value = dval + "";
					break;

				case HSSFCell.CELL_TYPE_STRING:
					value = cell.getRichStringCellValue().getString();
					break;

				case HSSFCell.CELL_TYPE_BLANK:
					value = "";
					break;

				case HSSFCell.CELL_TYPE_BOOLEAN:
					value = (cell.getBooleanCellValue() + "").toLowerCase();
					break;

				default:
				}

				if (value != null && value.trim().length() > 0) {
					data.add(value);
				}
			}

		}

		tOperacion.setResultado(OperacionesTablaControl.operar(data, operacion.getDescripcion().trim().toLowerCase()));
		tOperacion.setCampo(campo);
		tOperacion.setOperacion(operacion);
		tOperacion.setTitulo(titulo);

		return tOperacion;
	}

	// -------------------------------------------

	public List<Carga> obtenerListaCargaPorUsuario(Integer id_persona, Integer id_formato, Integer numero_pagina) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			List<String> estados = new ArrayList<String>();
			estados.add(Constantes.CARGA_ESTADO_APROBADO);
			estados.add(Constantes.CARGA_ESTADO_SUBIDO);

			return cargaDao.obtenerListaCargaPorUsuario(id_persona, estados, id_formato, numero_pagina);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	// Obtiene el total de las cargas por Persona.

	public Integer totalCargasPorPersona(Integer id_persona, Integer id_formato) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			List<String> estados = new ArrayList<String>();
			estados.add(Constantes.CARGA_ESTADO_APROBADO);
			estados.add(Constantes.CARGA_ESTADO_SUBIDO);

			return cargaDao.totalCargasPorPersona(id_persona, estados, id_formato);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Integer contarCargas(Integer id_usuario, String estados, Integer id_revision, Integer id_proceso) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.contarCargas(id_usuario, estados, id_revision, id_proceso);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error contando el numero de cargas", e);
		}

		return null;
	}

	// ----------------------------

	public List<String> obtenerFormatosImpresion(Integer id_carga) {

		List<String> listado = new ArrayList<String>();
		Carga carga = obtenerCarga(id_carga);
		String path = System.getenv("DATASUITE") + "/reportes/F" + carga.getId_formato();

		new File(path).mkdirs();

		File carpeta = new File(path);

		if (carpeta.isDirectory()) {

			File[] files = carpeta.listFiles();

			for (File file : files) {
				if (file.getName().length() > 3 && file.getName().endsWith(".xsl")) {
					listado.add(file.getName().substring(0, file.getName().length() - 4));
				}
			}
		}

		return listado;
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public List<Map<String, Object>> obtenerDatosCargaEstructura(Integer id_estructura, FormatoCampo fcampo, FormatoCampo fcampopadre, Boolean condicional, Integer id_carga, Formato formato, String sqlCondUsu, Session session) {
		DaoManager daoManager2 = DaoConfig.getDaoManager(2);
		CargaDao cargaDao = (CargaDao) daoManager2.getDao(CargaDao.class);

		String sqlCondUsuGenerada = generarSQL(session, sqlCondUsu);

		return cargaDao.obtenerDatosCarga(id_estructura, fcampo, fcampopadre, condicional, id_carga, formato, sqlCondUsuGenerada);
	}

	// ------------------------------------------
	public List<Map<String, Object>> obtenerDatosCargaEstructuraMultiple(Integer id_estructuraPadre, FormatoCampo fcampoHijo, FormatoCampo fcampopadre, Integer id_campoRel, Formato formato, Integer id_carga, Boolean condicional) {
		DaoManager daoManager2 = DaoConfig.getDaoManager(2);
		CargaDao cargaDao = (CargaDao) daoManager2.getDao(CargaDao.class);

		return cargaDao.obtenerDatosCargaMultiple(id_estructuraPadre, fcampoHijo, fcampopadre, id_campoRel, formato, id_carga, condicional);
	}

	// se genera el sql remplazando los atributos y nombres de estructuras
	private String generarSQL(Session session, String sql) {
		if (!StringUtils.esVacio(sql)) {

			for (Enumeration<?> e = session.getAttributeNames(); e.hasMoreElements();) {
				String param = (String) e.nextElement();
				Object atributo = session.getAttribute(param);

				if (atributo != null && !atributo.toString().startsWith("<?xml")) {
					sql = sql.replace("#" + param + "#", "'" + Crypto.E(atributo) + "'");
				}
			}

			sql = RDServicio.reemplazarNombres(sql);
		}

		return sql;
	}

	// ------------------------------------------

	public Boolean crearArchivoExcel(Formato formato, Session session) {
		List<FormatoCampo> listadoFCUnicos = new ArrayList<FormatoCampo>();
		return crearArchivoExcel(formato, false, null, listadoFCUnicos, session);
	}

	public Boolean crearArchivoExcelCondicionado(Formato formato, Integer id_carga, Session session) {

		List<FormatoCampo> listadoFCUnicos = new ArrayList<FormatoCampo>();
		return crearArchivoExcel(formato, true, id_carga, listadoFCUnicos, session);
	}

	public List<Carga> obtenerCargasRelacionadasPorCliente(Integer id_cliente, Integer id_formato) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			FormatoServicio formatoServicio = FormatoServicio.getInstance();
			Formato formato = formatoServicio.obtenerFormato(id_formato);

			List<Carga> listCargas = cargaDao.obtenerCargasRelacionadasPorCliente(id_cliente, formato.getIdformato_carga_relacionada(), formato.getDiasvigencia_carga_relacionada());

			return listCargas;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error contando el numero de cargas", e);
		}

		return null;

	}
	
	private Boolean crearArchivoExcel(Formato formato, Boolean condicional, Integer id_carga, List<FormatoCampo> listadoFCUnicos, Session session) {
		try {

			String nombrecarpeta = ParametrosInicio.getProperty("file.carpeta") + "/FORMATOS";
			new File(nombrecarpeta).mkdirs();
			String nombrearchivo = nombrecarpeta + "/F" + formato.getId_formato() + "-" + StringUtils.toFileName(formato.getNombre()) + ".xls";
			SimpleLogger.setDebug("Creando Archivo: " + nombrearchivo);
			return crearArchivoExcel(formato.getId_formato(), nombrearchivo, condicional, id_carga, listadoFCUnicos, session);

		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	private Boolean crearArchivoExcel(Integer id_formato, String nombrearchivo, Boolean condicional, Integer id_carga, List<FormatoCampo> listadoFCUnicos, Session session) throws Exception {

		FormatoServicio formatoServicio = FormatoServicio.getInstance();

		Formato formato = formatoServicio.obtenerFormato(id_formato);
		FormatoCampo fcampo = formatoServicio.obtenerFormatoCampo(formato.getId_formato());

		HSSFWorkbook wb = new HSSFWorkbook();

		// -------------------------------------------------------------------
		// Define fuentes

		HSSFFont fontTitulo = wb.createFont();
		fontTitulo.setColor(HSSFColor.WHITE.index);
		fontTitulo.setFontHeight((short) 180);
		fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// --

		Map<String, HSSFFont> fonts = new HashMap<String, HSSFFont>();
		fonts.put("titulo", fontTitulo);

		// -------------------------------------------------------------------
		// Define estilo de celdas

		HSSFCellStyle editStyle = wb.createCellStyle();
		editStyle.setLocked(false);
		editStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		editStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		editStyle.setWrapText(true);
		editStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		editStyle.setBorderBottom((short) 1);
		editStyle.setBorderLeft((short) 1);
		editStyle.setBorderRight((short) 1);
		editStyle.setBorderTop((short) 1);
		editStyle.setWrapText(true);

		HSSFCellStyle constStyle = wb.createCellStyle();
		constStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		constStyle.setLocked(true);
		constStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		constStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		constStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		constStyle.setBorderBottom((short) 1);
		constStyle.setBorderLeft((short) 1);
		constStyle.setBorderRight((short) 1);
		constStyle.setBorderTop((short) 1);
		constStyle.setWrapText(true);

		HSSFCellStyle ocultoStyle = wb.createCellStyle();
		ocultoStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		ocultoStyle.setLocked(true);
		ocultoStyle.setWrapText(false);

		// --

		HSSFCellStyle styleTitulo = wb.createCellStyle();
		styleTitulo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleTitulo.setWrapText(true);
		styleTitulo.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		styleTitulo.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
		styleTitulo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleTitulo.setFont(fontTitulo);
		styleTitulo.setBorderBottom((short) 1);
		styleTitulo.setBorderLeft((short) 1);
		styleTitulo.setBorderRight((short) 1);
		styleTitulo.setBorderTop((short) 1);

		// --

		HSSFCellStyle styleTituloId = wb.createCellStyle();
		styleTituloId.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleTituloId.setWrapText(true);
		styleTituloId.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		styleTituloId.setFillForegroundColor(HSSFColor.BLUE.index);
		styleTituloId.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleTituloId.setFont(fontTitulo);
		styleTituloId.setBorderBottom((short) 1);
		styleTituloId.setBorderLeft((short) 1);
		styleTituloId.setBorderRight((short) 1);
		styleTituloId.setBorderTop((short) 1);

		// --

		Map<String, HSSFCellStyle> styles = new HashMap<String, HSSFCellStyle>();
		styles.put("editable", editStyle);
		styles.put("constante", constStyle);
		styles.put("oculto", ocultoStyle);
		styles.put("titulo", styleTitulo);
		styles.put("tituloId", styleTituloId);

		agregarEstilos(styles, wb);
		// -------------------------------------------------------------------

		createFCampo(wb, formato, fcampo, null, null, null, null, styles, null, null, condicional, id_carga, listadoFCUnicos, session);

		// Agregar hoja quer permite verificar el formato cuando se cargue
		// nuevamente

		FormatoServicio.getInstance().crearHojaValidacion(wb, id_formato);

		// -------------------------------------------------------------------
		// Precargar valores

		FileOutputStream fileOut = new FileOutputStream(nombrearchivo);

		wb.write(fileOut);
		fileOut.close();

		return true;

	}

	public Short obtenerFormato(String tipoCampo, HSSFWorkbook wb) {

		HSSFDataFormat df = wb.createDataFormat();

		if ("String".equals(tipoCampo) || "Boolean".equals(tipoCampo)) {
			return df.getFormat("@");
		}

		if ("Date".equals(tipoCampo)) {
			return df.getFormat("dd/mm/yyyy");
		}

		if ("Integer".equals(tipoCampo) || "Long".equals(tipoCampo)) {
			return df.getFormat("0");
		}

		if ("Float".equals(tipoCampo) || "Double".equals(tipoCampo) || "BigDecimal".equals(tipoCampo)) {
			return df.getFormat("#,##0.00");
		}

		return null;
	}

	private void agregarEstilos(Map<String, HSSFCellStyle> styles, HSSFWorkbook wb) {

		HSSFDataFormat df = wb.createDataFormat();

		HSSFCellStyle estilo = wb.createCellStyle();
		estilo.setDataFormat(df.getFormat("@"));
		styles.put("String", estilo);

		estilo = wb.createCellStyle();
		estilo.setDataFormat(df.getFormat("@"));
		styles.put("Boolean", estilo);

		estilo = wb.createCellStyle();
		estilo.setDataFormat(df.getFormat("dd/mm/aaaa"));
		styles.put("Date", estilo);

		estilo = wb.createCellStyle();
		estilo.setDataFormat(df.getFormat("0"));
		styles.put("Integer", estilo);
		styles.put("Long", estilo);

		estilo = wb.createCellStyle();
		estilo.setDataFormat(df.getFormat("#.##0,00"));
		styles.put("Float", estilo);
		styles.put("Double", estilo);
		styles.put("BigDecimal", estilo);

	}

	private void createFCampo(HSSFWorkbook wb, Formato formato, FormatoCampo fcampo, FormatoCampo fcampopadre, Campo campo, HSSFSheet sheet, Integer column, Map<String, HSSFCellStyle> styles, List<Map<String, Object>> datos, List<Map<String, Object>> datosRelacion, Boolean condicional, Integer id_carga, List<FormatoCampo> listadoFCUnicos, Session session) {

		if (fcampo != null) {

			Boolean repetido = false;
			if (formato.getTipoformato().equals(Constantes.FORMATO_ENTRADA) && (campo == null || campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO))) {
				repetido = generarFormatosCamposUnicos(fcampo, listadoFCUnicos);
			}

			if (!repetido && fcampo.getFormato_campo_list().size() > 0 && (campo == null || campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO))) {
				crearHoja(wb, formato, fcampo, fcampopadre, campo, styles, condicional, id_carga, listadoFCUnicos, session);
			}

			if (campo != null && campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO)) {
				crearCampo(formato, fcampo, campo, sheet, column, styles, datos, datosRelacion, listadoFCUnicos, session, wb);
			}

			if (campo != null && campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_MULTIPLE)) {

				if (FormatoCampo.TIPOINGRESO_USUARIO_CREACION == fcampo.getTipo_ingreso()) {
					crearHojaMultiple(wb, formato, fcampo, fcampopadre, campo, styles, condicional, id_carga, listadoFCUnicos, session);
				}
			}
		}

	}

	private boolean sonIguales(Integer valor1, Integer valor2) {

		if (valor1 == null) {
			return (valor2 == null);
		} else {
			return (valor1.intValue() == valor2);
		}
	}

	private boolean sonIguales(String valor1, String valor2) {

		if (valor1 == null) {
			return (valor2 == null);
		} else {
			return (valor1.equals(valor2));
		}
	}

	private Boolean generarFormatosCamposUnicos(FormatoCampo fcampo, List<FormatoCampo> listadoFCUnicos) {

		Boolean repetida = false;
		if (fcampo.getFormato_campo_list().size() > 0) {

			for (FormatoCampo fcUnico : listadoFCUnicos) {

				boolean iguales = true;

				iguales = iguales && sonIguales(fcampo.getId_formato(), fcUnico.getId_formato());
				iguales = iguales && sonIguales(fcampo.getTipo_ingreso(), fcUnico.getTipo_ingreso());
				iguales = iguales && sonIguales(fcampo.getValor_constante(), fcUnico.getValor_constante());
				iguales = iguales && sonIguales(fcampo.getId_variable(), fcUnico.getId_variable());
				iguales = iguales && sonIguales(fcampo.getEstructura_padre(), fcUnico.getEstructura_padre());
				iguales = iguales && sonIguales(fcampo.getId_estructura(), fcUnico.getId_estructura());
				iguales = iguales && sonIguales(fcampo.getSeleccion_campo(), fcUnico.getSeleccion_campo());
				iguales = iguales && sonIguales(fcampo.getSeleccion_visualizacion(), fcUnico.getSeleccion_visualizacion());
				iguales = iguales && sonIguales(fcampo.getPrecarga(), fcUnico.getPrecarga());
				iguales = iguales && sonIguales(fcampo.getCondicional(), fcUnico.getCondicional());
				iguales = iguales && sonIguales(fcampo.getValor_condicion(), fcUnico.getValor_condicion());

				if (iguales) {

					repetida = true;
					return repetida;

				} else {
					repetida = false;
				}
			}
			if (!repetida) {
				listadoFCUnicos.add(fcampo);
			}
		}
		return repetida;
	}

	private void crearCampo(Formato formato, FormatoCampo fcampo, Campo campo, HSSFSheet sheet, Integer column, Map<String, HSSFCellStyle> styles, List<Map<String, Object>> datos, List<Map<String, Object>> datosRelacion, List<FormatoCampo> listadoFCUnicos, Session session, HSSFWorkbook wb) {
		crearCampo(formato, fcampo, campo, null, sheet, column, styles, datos, datosRelacion, listadoFCUnicos, session, wb);
	}

	private static Map<HSSFWorkbook, Map<String, HSSFCellStyle>> estiloEditable = new ConcurrentHashMap<>();

	public HSSFCellStyle obtenerEstiloEditable(String tipoCampo, HSSFWorkbook wb) {

		Map<String, HSSFCellStyle> mp = estiloEditable.get(wb);
		if (mp == null) {
			mp = new ConcurrentHashMap<>();
			estiloEditable.put(wb, mp);
		}

		HSSFCellStyle editStyle = mp.get(tipoCampo);

		if (editStyle == null) {

			Short formato = obtenerFormato(tipoCampo, wb);

			editStyle = wb.createCellStyle();
			editStyle.setLocked(false);
			editStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			editStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			editStyle.setWrapText(true);
			editStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
			editStyle.setBorderBottom((short) 1);
			editStyle.setBorderLeft((short) 1);
			editStyle.setBorderRight((short) 1);
			editStyle.setBorderTop((short) 1);
			editStyle.setWrapText(true);
			if (formato != null) {
				editStyle.setDataFormat(formato);
			}

			mp.put(tipoCampo, editStyle);

		}

		return editStyle;

	}

	private void crearCampo(Formato formato, FormatoCampo fcampo, Campo campo, Campo camporeal, HSSFSheet sheet, Integer column, Map<String, HSSFCellStyle> styles, List<Map<String, Object>> datos, List<Map<String, Object>> datosRelacion, List<FormatoCampo> listadoFCUnicos, Session session, HSSFWorkbook wb) {

		// Se revisa el tipo de ingreso del campo para ocultar o mostrar la
		// columna
		if ((fcampo.getFormato_campo_list().size() > 0 && fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_INDEFINIDO) || fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_SELECCION || fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_CREACION || fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_SISTEMA || fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_SELECCION_LISTA_DINAMICA) {

			sheet.setColumnHidden(column.shortValue(), false);
		} else {

			sheet.setColumnHidden(column.shortValue(), true);
		}

		short anchoColumna = 6000;
		sheet.setColumnWidth(column.shortValue(), (short) anchoColumna);

		if (fcampo.getTipo_ingreso() == 4 || fcampo.getTipo_ingreso() == 5) {
			sheet.setColumnHidden(column.shortValue(), true);
		}

		HSSFRow row = getRow(sheet, 0);

		row.setHeight((short) 0);
		HSSFCell cc = row.createCell(column.shortValue());

		if (camporeal != null) {
			cc.setCellValue(new HSSFRichTextString("C" + camporeal.getId_campo().toString()));
		} else if (campo != null) {
			cc.setCellValue(new HSSFRichTextString("C" + campo.getId_campo().toString()));
		} else {
			cc.setCellValue(new HSSFRichTextString("T" + fcampo.getId_estructura()));
		}

		boolean esObligatorio = StringUtils.esVerdad(fcampo.getObligatorio()) || (campo != null && StringUtils.esVerdad(campo.getObligatorio()));
		HSSFRow row1 = getRow(sheet, 1);
		row1.setHeight((short) 480);
		HSSFCell cc1 = row1.createCell(column.shortValue());
		cc1.setCellStyle(styles.get("titulo"));
		cc1.setCellValue(new HSSFRichTextString((esObligatorio ? "(*) " : "") + fcampo.getTitulo().toUpperCase()));

		Integer registros = 0;
		if (Constantes.FORMATO_SALIDA.equals(formato.getTipoformato())) {
			if (datos != null && datos.size() > 0) {
				registros = datos.size();
			} else if (datosRelacion != null && datosRelacion.size() > 0) {
				registros = datosRelacion.size();
			} else {
				registros = formato.getRegistrosporcarga();
			}
		} else {
			registros = formato.getRegistrosporcarga();
		}

		// Agregar formato

		HSSFCellStyle style = null;
		TipoCampo tipoCampo = null;
		if (campo != null) {
			tipoCampo = TipoCampoServicio.getInstance().obtenerTipoCampo(campo.getId_tipocampo());
		}
		if (tipoCampo != null) {
			style = obtenerEstiloEditable(tipoCampo.getTipo_dato(), wb);
		}

		for (short i = 0; i < registros; i++) {
			row = getRow(sheet, i + 2);
			HSSFCell ccd = row.createCell(column.shortValue());
			if (style == null) {
				style = styles.get("editable");
			}
			ccd.setCellStyle(style);

			if (datos != null) {
				short numReg = (short) datos.size();
				if (numReg > 0) {
					if (i < numReg) {

						Map<String, Object> datosReg = datos.get(i);
						String nombreCol = cc.getRichStringCellValue().toString();

						// -Objeto
						if (camporeal != null) {
							if (datosReg.get("P" + nombreCol.substring(1)) != null) {
								String numCol = "[" + datosReg.get("P" + nombreCol.substring(1)) + "]";
								Object valorCol = datosReg.get("V" + nombreCol.substring(1));
								String valorFinalCol = numCol + " " + valorCol;
								ccd.setCellValue(new HSSFRichTextString(valorFinalCol.toString()));
							}
							// -lista de valores
						} else if (campo != null && campo.getId_lista_valores() != null) {

							Object valorCol = datosReg.get(nombreCol);
							if (valorCol != null) {
								Object visualizacionCol = datosReg.get("V" + nombreCol.substring(1));
								String valorFinalCol = "[" + valorCol + "] " + visualizacionCol;
								ccd.setCellValue(new HSSFRichTextString(valorFinalCol));
							}
							// -lista dinamica
						} else if (campo != null && campo.getId_estructurarelacionada() != null && fcampo.getId_lista_dinamica() != null) {
							if (datosReg.get(nombreCol) != null) {
								String numCol = "[" + datosReg.get(nombreCol) + "]";
								Object valorCol = datosReg.get("V" + nombreCol.substring(1));
								String valorFinalCol = numCol + " " + valorCol;
								ccd.setCellValue(new HSSFRichTextString(valorFinalCol.toString()));
							}
							// -estrucura relacionada
						} else if (campo != null && campo.getId_estructurarelacionada() != null) {
							if (datosReg.get("P" + nombreCol.substring(1)) != null) {
								String numCol = "[" + datosReg.get("P" + nombreCol.substring(1)) + "]";
								Object valorCol = datosReg.get("V" + nombreCol.substring(1));
								String valorFinalCol = numCol + " " + valorCol;
								ccd.setCellValue(new HSSFRichTextString(valorFinalCol.toString()));
							}
							// -Campo normales
						} else {

							Object valorCol = datosReg.get(nombreCol);
							if (valorCol != null) {

								if ("String".equals(tipoCampo.getTipo_dato()) || "Boolean".equals(tipoCampo.getTipo_dato())) {
									ccd.setCellValue(new HSSFRichTextString(StringUtils.toString(valorCol)));
								}

								if ("Date".equals(tipoCampo.getTipo_dato())) {
									ccd.setCellValue(new HSSFRichTextString(StringUtils.toString(valorCol)));
								}

								if ("Integer".equals(tipoCampo.getTipo_dato()) || "Long".equals(tipoCampo.getTipo_dato())) {
									ccd.setCellValue(new Long(StringUtils.toString(valorCol)));
								}

								if ("Float".equals(tipoCampo.getTipo_dato()) || "Double".equals(tipoCampo.getTipo_dato()) || "BigDecimal".equals(tipoCampo.getTipo_dato())) {
									ccd.setCellValue(new Double(StringUtils.toString(valorCol)));
								}

							}
						}
					}
				}
			}
			// Cargar datos en hoja multiple
			if (datosRelacion != null) {
				short numReg = (short) datosRelacion.size();
				if (numReg > 0) {
					if (i < numReg) {
						Map<String, Object> datosReg = datosRelacion.get(i);
						if (column == 1) {
							String numCol = "[" + datosReg.get("POS_P") + "]";
							Object valorCol = datosReg.get("VIS_P");
							String valorFinalCol = numCol + " " + valorCol;
							ccd.setCellValue(new HSSFRichTextString(valorFinalCol.toString()));
						}
						if (column == 2) {
							String numCol = "[" + datosReg.get("POS_V") + "]";
							Object valorCol = datosReg.get("VIS_V");
							String valorFinalCol = numCol + " " + valorCol;
							ccd.setCellValue(new HSSFRichTextString(valorFinalCol.toString()));
						}

					}
				}
			}
		}

		// Seleccion desde el Padre

		if ((campo == null || campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO))) {

			Integer id_estructurarel = null;

			if (campo != null) {
				if (fcampo.getFormato_campo_list().size() > 0) {
					id_estructurarel = CampoServicio.getInstance().obtenerCampo(fcampo.getId_campo()).getId_estructurarelacionada();
				}
			} else if (fcampo.getId_estructura() != null) {
				id_estructurarel = fcampo.getId_estructura();
			} else {
				id_estructurarel = formato.getId_estructura();
			}

			if (id_estructurarel != null) {

				List<Campo> campos = CampoServicio.getInstance().obtenerCamposPorEstructura(id_estructurarel);

				Map<Campo, Integer> formulacampos = new HashMap<Campo, Integer>();
				int ii = 2;
				for (Campo campoestr : campos) {
					if (Constantes.SI.equalsIgnoreCase(campoestr.getVisualizacion())) {
						formulacampos.put(campoestr, ii);
					}
					ii++;
				}

				short numcolumn = (short) (255 - column);

				// coloca en la hoja los valores del listado
				FormatoCampo fcFormula = new FormatoCampo();
				fcFormula = fcampo;
				for (FormatoCampo formatoUnico : listadoFCUnicos) {
					if (fcampo.getId_estructura() != null && fcampo.getId_estructura().equals(formatoUnico.getId_estructura())) {
						fcFormula = formatoUnico;
					}
				}

				for (short i = 1; i <= registros; i++) {
					row = getRow(sheet, i + 1);
					HSSFCell ccd = row.createCell(numcolumn);
					ccd.setCellStyle(styles.get("oculto"));

					String rr = "";
					Iterator<Entry<Campo, Integer>> it = formulacampos.entrySet().iterator();
					while (it.hasNext()) {
						Entry<Campo, Integer> elemento = it.next();
						String columna = StringUtils.convertirAColExcel(elemento.getValue()) + (i + 2);
						TipoCampo tipoCampo2 = TipoCampoServicio.getInstance().obtenerTipoCampo(elemento.getKey().getId_tipocampo());
						if ("Date".equalsIgnoreCase(tipoCampo2.getTipo_dato())) {

							String campoCompleto = "'" + fcFormula.getTituloExcel() + "'!" + columna;

							columna = XlsUtils.agregarFormatoFechaOVacio(campoCompleto);
							rr += ", \" \", " + columna;

						} else {
							rr += ", \" \", '" + fcFormula.getTituloExcel() + "'!" + columna;

						}

					}

					String formula = "CONCATENATE( \"[\", '" + fcFormula.getTituloExcel() + "'!A" + (i + 2) + ", \"]\"" + rr + " )";
					ccd.setCellFormula(formula);
				}

				CellRangeAddressList regions = new CellRangeAddressList((short) 2, (short) (registros + 1), column.shortValue(), column.shortValue()); // :)
				Integer numreg = registros;

				validarCampo(sheet, numcolumn, numreg, regions);
			}
		}

		// verifica si necesita una lista dinamica

		if (campo != null && fcampo.getId_lista_dinamica() != null) {

			// obtiene valores del listado
			List<ValorLista> valores = ListaDinamicaServicio.getInstance().obtenerValoresListaDinamica(fcampo.getId_lista_dinamica(), session);
			short numcolumna = (short) (255 - column);

			// coloca en la hoja los valores del listado
			for (short i = 1; i <= valores.size(); i++) {
				row = getRow(sheet, i + 1);
				HSSFCell ccd = row.createCell(numcolumna);
				ccd.setCellStyle(styles.get("oculto"));

				if (valores.get(i - 1).getId().equalsIgnoreCase(valores.get(i - 1).getNombre())) {
					ccd.setCellValue(new HSSFRichTextString(valores.get(i - 1).getId()));
				} else {
					ccd.setCellValue(new HSSFRichTextString("[" + valores.get(i - 1).getId() + "] " + valores.get(i - 1).getNombre()));
				}
			}

			// Coloca validacion en el campo

			Integer registrosValidar = registros + 1;
			CellRangeAddressList regions = new CellRangeAddressList((short) 2, registrosValidar.shortValue(), column.shortValue(), column.shortValue()); // :)
			Integer numreg = valores.size();

			validarCampo(sheet, numcolumna, numreg, regions);
		}

		// verifica si necesita una lista de valor

		if (campo != null && campo.getId_lista_valores() != null && fcampo.getId_lista_dinamica() == null) {

			// obtiene valores del listado
			List<ValorLista> valores = ListaValoresServicio.getInstance().obtenerValoresLV(campo.getId_lista_valores());
			short numcolumna = (short) (255 - column);

			// coloca en la hoja los valores del listado
			for (short i = 1; i <= valores.size(); i++) {
				row = getRow(sheet, i + 1);
				HSSFCell ccd = row.createCell(numcolumna);
				ccd.setCellStyle(styles.get("oculto"));
				ccd.getCellStyle().setWrapText(false);
				ccd.setCellValue(new HSSFRichTextString("[" + valores.get(i - 1).getId() + "] " + valores.get(i - 1).getNombre()));
			}

			// Coloca validacion en el campo

			Integer registrosValidar = registros + 1;

			CellRangeAddressList regions = new CellRangeAddressList((short) 2, registrosValidar.shortValue(), column.shortValue(), column.shortValue()); // :)

			Integer numreg = valores.size();

			validarCampo(sheet, numcolumna, numreg, regions);
		}

	}

	private void validarCampo(HSSFSheet sheet, short numcolumn, Integer numreg, CellRangeAddressList regions) {

		String ncol = StringUtils.convertirAColExcel(numcolumn + 1);
		String formu = "$" + ncol + "$3:$" + ncol + "$" + (numreg + 2);

		DataValidationConstraint constraint = DVConstraint.createFormulaListConstraint(formu);
		HSSFDataValidation data_validation = new HSSFDataValidation(regions, constraint);

		data_validation.setEmptyCellAllowed(true);
		data_validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
		data_validation.setSuppressDropDownArrow(false);
		data_validation.createErrorBox("DataSuite", "El valor ingresado no es valido, Seleccione un elemento de la lista.");

		sheet.addValidationData(data_validation);

		ajustarColumnasPorLista(sheet, numcolumn, data_validation);
	}

	private void ajustarColumnasPorLista(HSSFSheet sheet, short numcolumna, HSSFDataValidation data_valid) {

		// obtener las columnas que tienen asignada la lista
		for (int column = data_valid.getRegions().getCellRangeAddresses()[0].getFirstColumn(); column <= data_valid.getRegions().getCellRangeAddresses()[0].getLastColumn(); column++) {
			// Poner en el campo el mismo ancho que la lista de validacion
			sheet.autoSizeColumn(numcolumna);
			int colw = sheet.getColumnWidth(numcolumna);
			sheet.autoSizeColumn(column);
			int wactual = sheet.getColumnWidth(column);

			int row = data_valid.getRegions().getCellRangeAddresses()[0].getFirstColumn();
			if (sheet.getRow(row) != null && sheet.getRow(row).getCell(numcolumna) != null) {
				int tipo = sheet.getRow(row).getCell(numcolumna).getCellType();

				if (tipo == HSSFCell.CELL_TYPE_FORMULA) {
					wactual = 25000;
				}
			}

			sheet.setColumnWidth(column, (colw > wactual ? colw : wactual));
		}
	}

	private void crearHojaMultiple(HSSFWorkbook wb, Formato formato, FormatoCampo fcampo, FormatoCampo fcampopadre, Campo campo, Map<String, HSSFCellStyle> styles, Boolean condicional, Integer id_carga, List<FormatoCampo> listadoFCUnicos, Session session) {

		SimpleLogger.setDebug("Creando hoja: " + "R" + fcampo.getTituloExcel());
		HSSFSheet newsheet = wb.createSheet("R" + fcampo.getTituloExcel());

		newsheet.setColumnWidth((short) 0, (short) 1500);
		newsheet.createFreezePane(1, 2);

		for (short h = 1; h <= 255; h++) {
			newsheet.setColumnHidden(h, true);
		}

		// --------------------------------------------------------------
		// agrega propiedades de la hoja

		newsheet.setDefaultColumnWidth((short) 25);

		newsheet.protectSheet("meconio3");
		newsheet.setDisplayRowColHeadings(false);
		newsheet.setDisplayGridlines(false);

		HSSFRow rowc0 = getRow(newsheet, 0);
		rowc0.setHeight((short) 0);
		HSSFCell ccel0 = rowc0.createCell((short) 0);
		ccel0.setCellValue(new HSSFRichTextString("T" + campo.getId_estructura() + "C" + campo.getId_campo()));

		HSSFRow row = getRow(newsheet, 1);
		row.setHeight((short) 480);

		HSSFCell ccel = row.createCell((short) 0);
		ccel.setCellStyle(styles.get("tituloId"));
		ccel.setCellValue(new HSSFRichTextString("ID"));

		// Obtener datos de hoja multiple
		List<Map<String, Object>> datosRelacion = new ArrayList<Map<String, Object>>();
		if ((fcampo.getPrecarga().equals(Constantes.SI) && fcampopadre.getPrecarga().equals(Constantes.SI)) || (Constantes.FORMATO_SALIDA.equals(formato.getTipoformato()) && condicional == true)) {
			Integer id_estructuraPadre = 0;
			if (fcampopadre.getId_estructura() == null) {
				id_estructuraPadre = formato.getId_estructura();
			} else {
				id_estructuraPadre = fcampopadre.getId_estructura();
			}

			datosRelacion = obtenerDatosCargaEstructuraMultiple(id_estructuraPadre, fcampo, fcampopadre, campo.getId_campo(), formato, id_carga, condicional);
		}

		// Crea primera columna
		Integer registros = 0;
		if (Constantes.FORMATO_SALIDA.equals(formato.getTipoformato())) {
			registros = datosRelacion.size();
		} else {
			registros = formato.getRegistrosporcarga();
		}
		for (short i = 0; i < registros; i++) {
			row = getRow(newsheet, i + 2);
			HSSFCell ccd = row.createCell((short) 0);
			ccd.setCellStyle(styles.get("constante"));
			ccd.setCellValue(new HSSFRichTextString("" + (i + 1)));
		}

		crearCampo(formato, fcampopadre, null, newsheet, 1, styles, null, datosRelacion, listadoFCUnicos, session, wb);

		ccel0 = rowc0.createCell((short) 1);
		ccel0.setCellValue(new HSSFRichTextString("ID_P"));

		campo.setMultiplicidad(Constantes.CAMPO_MULTIPLICIDAD_UNICO);
		createFCampo(wb, formato, fcampo, fcampopadre, campo, newsheet, 2, styles, null, datosRelacion, condicional, id_carga, listadoFCUnicos, session);

		ccel0 = rowc0.createCell((short) 2);
		ccel0.setCellValue(new HSSFRichTextString("ID_V"));
	}

	private void crearHoja(HSSFWorkbook wb, Formato formato, FormatoCampo fcampo, FormatoCampo fcampopadre, Campo campo, Map<String, HSSFCellStyle> styles, Boolean condicional, Integer id_carga, List<FormatoCampo> listadoFCUnicos, Session session) {
		SimpleLogger.setDebug("Creando hoja: " + fcampo.getTituloExcel());
		HSSFSheet newsheet = wb.createSheet(fcampo.getTituloExcel());
		newsheet.setColumnWidth((short) 0, (short) 1500);
		newsheet.createFreezePane(1, 2);

		for (short h = 1; h <= 255; h++) {
			newsheet.setColumnHidden(h, true);
		}

		// --------------------------------------------------------------
		// agrega propiedades de la hoja

		newsheet.setDefaultColumnWidth((short) 25);
		newsheet.protectSheet("meconio3");
		newsheet.setDisplayRowColHeadings(false);
		newsheet.setDisplayGridlines(false);

		HSSFRow rowc0 = getRow(newsheet, 0);

		rowc0.setHeight((short) 0);
		HSSFCell ccel0 = rowc0.createCell((short) 0);
		ccel0.setCellValue(new HSSFRichTextString("T" + (fcampo.getId_estructura() != null ? fcampo.getId_estructura() : formato.getId_estructura())));

		HSSFRow row = getRow(newsheet, 1);
		row.setHeight((short) 480);

		HSSFCell ccel = row.createCell((short) 0);
		ccel.setCellStyle(styles.get("tituloId"));
		ccel.setCellValue(new HSSFRichTextString("ID"));

		if (fcampo.getTitulo() == null) {
			fcampo.setTitulo("C" + fcampo.getId_formato_campo());
		}

		// Si las estructuras son de precarga, se obtienen los datos por
		// estructura
		List<Map<String, Object>> datos = new ArrayList<Map<String, Object>>();
		if (fcampo.getPrecarga().equals(Constantes.SI) || (Constantes.FORMATO_SALIDA.equals(formato.getTipoformato()) && condicional == true)) {
			Integer id_estructura = 0;
			if (fcampo.getId_estructura() == null) {
				id_estructura = formato.getId_estructura();
			} else {
				id_estructura = fcampo.getId_estructura();
			}

			String sqlCondUsu = null;
			if (StringUtils.esVerdad(fcampo.getPrecarga())) {
				sqlCondUsu = fcampo.getCondicion_precarga();
			}
			datos = CargaServicio.getInstance().obtenerDatosCargaEstructura(id_estructura, fcampo, fcampopadre, condicional, id_carga, formato, sqlCondUsu, session);
		}

		// Crea primera columna

		Integer registros = 0;
		if (Constantes.FORMATO_SALIDA.equals(formato.getTipoformato())) {
			registros = datos.size();
		} else {
			registros = formato.getRegistrosporcarga();
		}
		for (short i = 0; i < registros; i++) {
			row = getRow(newsheet, i + 2);
			HSSFCell ccd = row.createCell((short) 0);
			ccd.setCellStyle(styles.get("constante"));
			ccd.setCellValue(new HSSFRichTextString("" + (i + 1)));
		}

		Integer cc = 1;
		if (Constantes.SI.equals(fcampo.getEstructura_padre())) {
			cc = 2;
			Campo newcampo = CampoServicio.getInstance().obtenerCampo(fcampopadre.getId_campo());
			Campo camporeal = new Campo();
			camporeal.setId_campo(fcampo.getId_campo());
			crearCampo(formato, fcampopadre, newcampo, camporeal, newsheet, 1, styles, datos, null, listadoFCUnicos, session, wb);
		}

		for (FormatoCampo formatoCampo : fcampo.getFormato_campo_list()) {

			if (formatoCampo.getEstructura_padre().equals(Constantes.NO)) {

				Campo newcampo = CampoServicio.getInstance().obtenerCampo(formatoCampo.getId_campo());

				createFCampo(wb, formato, formatoCampo, fcampo, newcampo, newsheet, cc, styles, datos, null, condicional, id_carga, listadoFCUnicos, session);
				cc++;

			} else if (formatoCampo.getFormato_campo_list().size() > 0) {
				createFCampo(wb, formato, formatoCampo, fcampo, null, newsheet, cc, styles, datos, null, condicional, id_carga, listadoFCUnicos, session);
			}
		}
	}

	private static HSSFRow getRow(HSSFSheet sheet, int index) {
		HSSFRow row = sheet.getRow(index);
		if (row == null) {
			row = sheet.createRow(index);
		}

		return row;
	}

	// --------------------------------------------------------------------------

	/***
	 * Esta funcion retorna el listado de cargas de un proceso administrativo, filtradas por las restricciones que pueda tener el usuario adminsitrativo. <br/>
	 * <br/>
	 * 
	 * Se utiliza en administrar cargas por proceso
	 * 
	 * @param id_administrativo
	 * @param id_proceso_admin
	 * @param parametro_ordenamiento
	 *            - alguno de los campos por los que se pueda ordenar la consulta
	 * @param tipo_ordenamiento
	 *            - descendente o ascendente ('asc' or 'desc')
	 * @param filtros
	 *            - mapa de filtros en el cual esta el nombre del campo y el valor por el cual va aser filtrado
	 * 
	 * @author jcvargasj
	 * 
	 */
	public List<Carga> obtenerCargasPorProcesoAdmin(Integer id_administrativo, Integer id_proceso_admin, String parametro_ordenamiento, String tipo_ordenamiento, Map<String, Object> filtros, Integer numero_pagina) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			if (parametro_ordenamiento == null || parametro_ordenamiento.equals("")) {

				// Si no hay parametro de ordenamiento se ordena por el
				// porcentajeTiempoTranscurrido
				parametro_ordenamiento = "id_carga";
			}

			if (tipo_ordenamiento == null || tipo_ordenamiento.equals("")) {

				// Ordenamiento ascendente=asc, 2=descendente
				tipo_ordenamiento = "desc";

			}

			// Se consultan todas las cargas asociadas al proceso
			List<Carga> cargasSinFiltrar = cargaDao.obtenerCargasPorProcesoAdmin(id_proceso_admin, id_administrativo, parametro_ordenamiento, tipo_ordenamiento, filtros, numero_pagina);

			// Se deben filtrar las cargas segun las restricciones de los roles
			// asociados al administrativo
			AdministrativoServicio administrativoServicio = AdministrativoServicio.getInstance();
			List<RestriccionAdministrativo> restricciones = administrativoServicio.obtenerRestriccionesPorAdministrativo(id_administrativo);

			// Se consulta el proceso
			ProcesoAdminServicio procesoAdminServicio = ProcesoAdminServicio.getInstance();
			ProcesoAdmin procesoAdmin = procesoAdminServicio.obtenerProcesoAdmin(id_proceso_admin);

			// Lista de cargas filtradas luego de revisar restricciones
			List<Carga> cargasFiltradas;

			if (restricciones != null && restricciones.size() > 0 && cargasSinFiltrar != null) {
				// Verificamos si tiene permiso para todos los clientes
				if (!tienePermisoParaTodosLosClientes(restricciones)) {
					// Se debe filtrar los procesos por negocio segun las
					// restricciones
					ArrayList<Carga> cargasFiltradasPorCliente = new ArrayList<Carga>();

					for (RestriccionAdministrativo restriccionAdministrativo : restricciones) {
						for (Carga carga : cargasSinFiltrar) {
							if (carga.getId_usuario().equals(restriccionAdministrativo.getId_usuario())) {

								// Se revisa si el proceso tiene asociado un
								// formato de entrada y se filtran las cargas
								// por dicho formato
								if (procesoAdmin.getId_formato_entrada() != null && procesoAdmin.getId_formato_entrada().equals(carga.getId_formato())) {

									cargasFiltradasPorCliente.add(carga);

								} else {

									cargasFiltradasPorCliente.add(carga);
								}
							}
						}

					}

					cargasFiltradas = cargasFiltradasPorCliente;

				} else {

					cargasFiltradas = cargasSinFiltrar;
				}
			} else {
				// Si no hay restricciones se deben ver las cargas de todos los
				// clientes para el proceso especificado

				cargasFiltradas = cargasSinFiltrar;
			}

			return cargasFiltradas;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error obteniendo las cargas", e);
		}
		return null;

	}

	private Boolean tienePermisoParaTodosLosClientes(List<RestriccionAdministrativo> restricciones) {
		Boolean tienePermisoParaTodosLosClientes = false;

		for (RestriccionAdministrativo restriccionAdministrativo : restricciones) {
			if (restriccionAdministrativo.getId_usuario() == null) {
				tienePermisoParaTodosLosClientes = true;
			}

		}

		return tienePermisoParaTodosLosClientes;
	}

	/***
	 * Esta funcion se encarga de asociar las cargas, que esten en estado subida, a instancias iniciales de los procesos que tengan formato de entrada igual al al formato con el que se realizo la carga <br/>
	 * <br/>
	 * 
	 * 
	 * @author jcvargasj
	 * 
	 */
	public Integer asociarCargasAInstanciasDeProceso() {

		try {
			Integer cargasAsociadas = 0;

			ProcesoAdminServicio procesoAdminServicio = ProcesoAdminServicio.getInstance();
			InstanciaServicio instanciaServicio = InstanciaServicio.getInstance();
			CargaInstanciaServicio cargaInstanciaServicio = CargaInstanciaServicio.getInstance();

			// Se buscan todas las cargas realizadas que se encuentren en estado
			// SUBIDO(S)o REQUIEREN_ADJUNTO(W)
			List<Carga> cargasSinInstancia = obtenerCargasSubidasSinInstancia();

			if (cargasSinInstancia != null && cargasSinInstancia.size() > 0) {
				for (Carga carga : cargasSinInstancia) {

					// Se busca el proceso para cada carga
					ProcesoAdmin procesoAdmin = procesoAdminServicio.obtenerProcesoParaAsociarCarga(carga.getId_carga());

					if (procesoAdmin != null) {
						// Consultamos las instancias iniciales del proceso
						List<Instancia> instancias = instanciaServicio.obtenerInstanciasInicialesPorProceso(procesoAdmin.getId_proceso_admin());

						if (instancias != null && instancias.size() > 0) {
							// Se consulta la fecha de la base de datos para que
							// todas
							// las relaciones
							// tengan la misma hora de llegada
							Date fecha_llegada = HorarioServicio.getInstance().obtenerFechaActual();

							for (Instancia instancia : instancias) {

								// Segun el estado de la carga se crea la
								// relacion con o sin fecha de llegada

								Boolean insertada = false;
								if (carga.getEstado().equals(Constantes.CARGA_ESTADO_SUBIDO)) {

									// Si la carga esta en estado subido se crea
									// la relacion con fecha de entrada

									Boolean existeRelacion = cargaInstanciaServicio.existeRelacionCargaInstanciaSinFechaSalida(carga.getId_carga(), instancia.getId_instancia());
									if (existeRelacion != null && !existeRelacion) {

										SimpleLogger.setDebug("Insertando...");
										insertada = cargaInstanciaServicio.insertarRelacionCargaInstancia(carga.getId_carga(), instancia.getId_instancia(), fecha_llegada);

									} else {
										SimpleLogger.setDebug("Ya existe la relacion..." + carga.getId_carga() + "  instancia " + instancia.getId_instancia());
										insertada = false;
									}

								} else if (carga.getEstado().equals(Constantes.CARGA_ESTADO_REQUIERE_ADJUNTO)) {

									// Si la carga esta en estado REQUIERE
									// ADJUNTI se crea la relacion SIN fecha de
									// entrada
									insertada = cargaInstanciaServicio.insertarRelacionCargaInstancia(carga.getId_carga(), instancia.getId_instancia(), null);

								}

								if (insertada) {
									SimpleLogger.setDebug("Se asocio la carga " + carga.getId_carga() + " a la instancia " + instancia.getId_instancia() + " del proceso " + procesoAdmin.getId_proceso_admin());
									cargasAsociadas = cargasAsociadas + 1;

								} else {
									SimpleLogger.setWarn("NO fue posible asociar la carga " + carga.getId_carga() + " a la instancia " + instancia.getId_instancia() + " del proceso" + procesoAdmin.getId_proceso_admin());

								}
							}

						} else {
							SimpleLogger.setWarn("No hay instancias iniciales del proceso " + procesoAdmin.getId_proceso_admin() + " para asociar la carga " + carga.getId_carga());

						}

					}
				}
			}

			return cargasAsociadas;

		} catch (Exception e) {
			SimpleLogger.setError("Error asociando cargas a instancias", e);
			return 0;
		}

	}

	private List<Carga> obtenerCargasSubidasSinInstancia() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerCargasSubidasSinInstancia();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Carga> obtenerCargasSubidasSinInstanciaPorTipoProceso(Integer id_tipo_proceso) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerCargasSubidasSinInstanciaPorTipoProceso(id_tipo_proceso);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Carga> obtenerCargasPorEstados(String estados, Integer pagina) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerCargasPorEstados(estados, pagina);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error obteniendo las cargas", e);
		}
		return null;
	}

	public Map<String, Object> obtenerDatosCargaPorFormatoSimple(Integer id_carga, Integer id_formatosalida, Integer numero_pagina) {
		try {

			Map<String, Object> res = new HashMap<String, Object>();

			DaoManager daoManagerDS = DaoConfig.getDaoManager(1);
			DaoManager daoManagerCD = DaoConfig.getDaoManager(2);

			Map<Integer, Campo> mapacampos = new HashMap<Integer, Campo>();

			FormatoServicio formatoServicio = FormatoServicio.getInstance();
			Formato formatosalida = formatoServicio.obtenerFormato(id_formatosalida);
			FormatoCampo formatocampobase = formatoServicio.obtenerFormatoCampo(id_formatosalida);

			List<Object> nodos = new ArrayList<Object>();

			List<Map<String, Object>> datos = obtenerValorRegistroVistaEstructura(id_carga, formatosalida, formatocampobase, null, null, null, daoManagerDS, daoManagerCD, null, numero_pagina, mapacampos, null);

			for (Map<String, Object> map : datos) {
				Map<String, Object> data = crearNodoDatosVistaEstructura(id_carga, formatosalida, formatocampobase, daoManagerDS, daoManagerCD, map, new HashMap<String, List<ValorLista>>(), mapacampos, false);
				if (data != null) {
					nodos.add(data);
				}
			}

			res.put("#element", nodos);
			res.put("@name", formatocampobase.getTitulo());
			res.put("@type", "list");

			return res;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean actualizarNegocio(Integer id_carga, Integer id_negocio) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
			return cargaDao.actualizarNegocio(id_carga, id_negocio);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en  CargaServicio.actualizarNegocio", e);
			return false;
		}

	}

	public List<Integer> obtenerIDsRegistrosPorCarga(Integer id_carga, Integer id_estructura) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager(2);
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerIDsRegistrosPorCarga(id_carga, id_estructura);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Map<String, Object>> selectSql(String sql) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager(2);
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.selectSql(sql);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean actualizarSQL(String sql) {
		try {
			SimpleLogger.setDebug("SQL --> " + sql);

			sql = RDServicio.reemplazarNombres(sql);

			DaoManager daoManager = DaoConfig.getDaoManager(2);
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.updateSQL(sql);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	public Boolean insertarSQL(String sql, CargaDao cargaDao) {
		try {

			sql = RDServicio.reemplazarNombres(sql);

			SimpleLogger.setDebug("SQL --> " + sql);

			return cargaDao.insertSQL(sql);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	public Boolean eliminarSQL(String sql) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			sql = RDServicio.reemplazarNombres(sql);

			SimpleLogger.setDebug("SQL --> " + sql);

			return cargaDao.eliminarSQL(sql);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	public Boolean guardarValoresLista(List<ValorListaDinamicaCampo> listaValoresDinamicos, Integer id_formato, Boolean sobreescribirValores) {

		try {

			if (id_formato == null || !validarListadoValorListaDinamicaCampo(listaValoresDinamicos, id_formato)) {
				SimpleLogger.setError("Los valores de ingresados no corresponden con los parametrizados por la lista_dinamica_campo para el formato " + id_formato);
				return false;
			}

			// -------------

			DaoManager daoManager = DaoConfig.getDaoManager(2);
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			Formato formato = FormatoServicio.getInstance().obtenerFormato(id_formato);
			Integer id_estructura = formato.getId_estructura();

			if (listaValoresDinamicos != null) {

				// -----------------------------------------------------
				// actualiza todos los campos de las variables de liberacion de
				// la tabla para la carga

				String sql = "update T" + id_estructura + " set NREG=NREG ";

				Integer id_carga = null;

				for (ValorListaDinamicaCampo valorListaDinamicaCampo : listaValoresDinamicos) {

					if (id_carga == null) {
						id_carga = valorListaDinamicaCampo.getId_carga();
					}

					Object valor = CampoServicio.getInstance().convertirDatoCampo(valorListaDinamicaCampo.getId_campo(), valorListaDinamicaCampo.getValor());

					if (valor != null) {
						sql += ", C" + valorListaDinamicaCampo.getId_campo() + " = " + SQLServicio.sqlCampo(valor);
					}

				}

				if (id_carga == null) {
					return false;
				}

				sql += " where idcarga=" + id_carga + " ";

				// si no se debe sobreescribir.. verifica que los registros
				// tengan los valores en null o que sea el mismo valor a
				// ingresar.
				if (!sobreescribirValores) {

					for (ValorListaDinamicaCampo valorListaDinamicaCampo : listaValoresDinamicos) {

						Object valor = CampoServicio.getInstance().convertirDatoCampo(valorListaDinamicaCampo.getId_campo(), valorListaDinamicaCampo.getValor());

						String campo = "C" + valorListaDinamicaCampo.getId_campo();

						sql += " and ( " + campo + " is null or " + campo + " = " + SQLServicio.sqlCampo(valor) + ") ";
					}

				}

				cargaDao.updateSQL(sql);

				// -----------------------------------------------------
				// Guarda el registro en la tabla de variable_liberacion_carga
				// para tener registro de la accion realizada.

				for (ValorListaDinamicaCampo valorListaDinamicaCampo : listaValoresDinamicos) {

					Object valor = CampoServicio.getInstance().convertirDatoCampo(valorListaDinamicaCampo.getId_campo(), valorListaDinamicaCampo.getValor());

					// Si el valor es referencia a una estructura se debe
					// incriptar
					// para poderlo guardar en la table de variables de
					// liberacion

					if (valor instanceof Integer) {
						valor = Crypto.E(valor);
					}

					// Se guardan las variables de liberacion para la carga
					DaoManager daoManagerDataSuite = DaoConfig.getDaoManager();
					SQLDao sqlDao = (SQLDao) daoManagerDataSuite.getDao(SQLDao.class);

					String insertSQL = " insert into dst_variable_liberacion_carga " + " (id_carga, id_campo, valor) " + " values(" + valorListaDinamicaCampo.getId_carga() + "," + valorListaDinamicaCampo.getId_campo() + ",'" + valor + "')";

					sqlDao.insertSQL(insertSQL);

				}
			}

			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;

	}

	private boolean validarListadoValorListaDinamicaCampo(List<ValorListaDinamicaCampo> listaValoresDinamicos, Integer id_formato) {

		ListaDinamicaCampoDao dao = (ListaDinamicaCampoDao) DaoConfig.getDao(ListaDinamicaCampoDao.class);
		List<ListaDinamicaCampo> lista = dao.obtenerListasPorFormato(id_formato);

		if (lista != null) {
			for (ListaDinamicaCampo listaDinamicaCampo : lista) {

				if (StringUtils.esVerdad(listaDinamicaCampo.getObligatorio())) {

					boolean existe = false;

					if (listaValoresDinamicos != null) {
						for (ValorListaDinamicaCampo valorListaDinamicaCampo : listaValoresDinamicos) {
							if (valorListaDinamicaCampo.getId_campo() == listaDinamicaCampo.getId_campo().intValue() && StringUtils.isNotBlank(valorListaDinamicaCampo.getValor())) {
								existe = true;
							}
						}
					}

					if (!existe) {
						return false;
					}

				}
			}
		}

		return true;
	}

	public List<Carga> obtenerCargasPorInstanciaParaAdminHijo(Integer id_proceso_admin, Integer id_instancia, Integer id_administrativo_padre, Integer id_administrativo_hijo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			// Se consultan todas las cargas asociadas al proceso para el
			// administrativo hijo
			List<Carga> cargasSinFiltrar = cargaDao.obtenerCargasPorInstanciaParaAdminHijo(id_proceso_admin, id_instancia, id_administrativo_padre, id_administrativo_hijo);

			// Se deben filtrar las cargas segun las restricciones de los roles
			// asociados al administrativo hijo
			AdministrativoServicio administrativoServicio = AdministrativoServicio.getInstance();
			List<RestriccionAdministrativo> restricciones = administrativoServicio.obtenerRestriccionesPorAdministrativo(id_administrativo_hijo);

			// Se consulta el proceso
			ProcesoAdminServicio procesoAdminServicio = ProcesoAdminServicio.getInstance();
			ProcesoAdmin procesoAdmin = procesoAdminServicio.obtenerProcesoAdmin(id_proceso_admin);

			// Lista de cargas filtrados luego de revisar restricciones
			List<Carga> cargasFiltradas;

			if (restricciones != null && restricciones.size() > 0 && cargasSinFiltrar != null) {
				// Verificamos si tiene permiso para todos los clientes
				if (!tienePermisoParaTodosLosClientes(restricciones)) {
					// Se debe filtrar las cargas por negocio segun las
					// restricciones
					ArrayList<Carga> cargasFiltradasPorCliente = new ArrayList<Carga>();

					for (RestriccionAdministrativo restriccionAdministrativo : restricciones) {
						for (Carga carga : cargasSinFiltrar) {
							if (carga.getId_usuario().equals(restriccionAdministrativo.getId_usuario())) {

								// Se revisa si el proceso tiene asociado un
								// formato de entrada y se filtran las cargas
								// por dicho formato
								if (procesoAdmin.getId_formato_entrada() != null && procesoAdmin.getId_formato_entrada().equals(carga.getId_formato())) {

									cargasFiltradasPorCliente.add(carga);

								} else {

									cargasFiltradasPorCliente.add(carga);
								}
							}
						}

					}

					cargasFiltradas = cargasFiltradasPorCliente;

				} else {

					cargasFiltradas = cargasSinFiltrar;
				}
			} else {
				// Si no hay restricciones se deben ver las cargas de todos los
				// clientes para el proceso especificado

				cargasFiltradas = cargasSinFiltrar;
			}

			return cargasFiltradas;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error obteniendo las cargas", e);
		}
		return null;

	}

	/***
	 * Esta funcion retorna la cantidad de cargas de un proceso administrativo para intervalo particular, filtradas por las restricciones que pueda tener el usuario adminsitrativo hijo <br/>
	 * <br/>
	 * 
	 * Se utiliza en administrar cargas por proceso
	 * 
	 * @param id_administrativo
	 * @param id_proceso_admin
	 * @param intervalos
	 *            - es el numero de intervalos que desea mostrar en el semaforo. EJEMPLO: 3 equivale a semaforo verde amarillo y rojo
	 * @param invervaloActual
	 *            - es el intervalo al cual se quiere contar el numero de cargas. El intervalo 0 esta reservado para cargas que aun no tienen fecha llegada a una instancia de proceso, es decir, cargas en estado gris. Ejemplo: intevarlo 1: Cargas que estado verde; intervalo 2: cargas en estado amarillo ...
	 * 
	 * @author jcvargasj
	 * 
	 */
	public Integer cargasPendientesPorInstanciaParaAdminHijo(Integer id_proceso_admin, Integer id_instancia, Integer id_administrativo_padre, Integer id_administrativo_hijo, Integer intervalos, Integer intervaloActual) {

		try {
			// Contador de cargas
			Integer contadorCargas = 0;

			// Obtenemos las cargaInstancia filtras
			List<Carga> cargasFiltradas = obtenerCargasPorInstanciaParaAdminHijo(id_proceso_admin, id_instancia, id_administrativo_padre, id_administrativo_hijo);

			if (intervaloActual > 0) {

				// Calculamos el tamano de los intervalos
				Integer maximoIntervaloConLimites = intervalos - 1;
				Integer tamaniooIntervalo = Constantes.TAMANIO_AREA_INTERVALOS / maximoIntervaloConLimites;
				Integer limiteInferiorIntervalo = (intervaloActual - 1) * tamaniooIntervalo;
				Integer limiteSuperiorIntervalo = (intervaloActual) * tamaniooIntervalo;

				// Recorremos las cargas filtras y contamos las que correspondan
				// al
				// intervalo
				if (cargasFiltradas != null && cargasFiltradas.size() > 0) {
					for (Carga carga : cargasFiltradas) {

						// Solo se contabilizan las cargas que esten en estado
						// SUBIDO
						if (carga.getEstado().equals(Constantes.CARGA_ESTADO_SUBIDO)) {
							Integer porcentajeTiempoTranscurrido = carga.getPorcentajeTiempoTranscurrido();

							if (intervaloActual <= maximoIntervaloConLimites) {
								if (porcentajeTiempoTranscurrido >= limiteInferiorIntervalo && porcentajeTiempoTranscurrido < limiteSuperiorIntervalo) {
									contadorCargas = contadorCargas + 1;

								}
							} else {
								if (porcentajeTiempoTranscurrido >= Constantes.TAMANIO_AREA_INTERVALOS) {
									contadorCargas = contadorCargas + 1;
								}
							}
						}
					}

				}

			} else if (intervaloActual == 0) {
				/*
				 * Si el intervalo es cero (0) se contabilizan las cargas que aun no tiene fecha de llegada a la instancia, es decir, instancias que aun se encuentra en estado REQUIRE_ADJUNTO(W). Estas cargas, aunque pertenecen a la instancia, aun no estan activas ya que requieren informacion del cliente.
				 */

				if (cargasFiltradas != null && cargasFiltradas.size() > 0) {
					for (Carga carga : cargasFiltradas) {
						if (carga.getEstado().equals(Constantes.CARGA_ESTADO_REQUIERE_ADJUNTO)) {
							contadorCargas++;
						}

					}

				}

			}

			return contadorCargas;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en CargaInstanciaServicio.cargasPendientesPorProcesoAdmin", e);
			return null;
		}
	}

	/***
	 * Esta funcion retorna la cantidad de cargas de un proceso administrativo para intervalo particular, filtradas por las restricciones que pueda tener el usuario adminsitrativo. <br/>
	 * <br/>
	 * 
	 * Se utiliza en administrar cargas por proceso
	 * 
	 * @param id_administrativo
	 * @param id_proceso_admin
	 * @param intervalos
	 *            - es el numero de intervalos que desea mostrar en el semaforo. EJEMPLO: 3 equivale a semaforo verde amarillo y rojo
	 * @param invervaloActual
	 *            - es el intervalo al cual se quiere contar el numero de cargas. El intervalo 0 esta reservado para cargas que aun no tienen fecha llegada a una instancia de proceso, es decir, cargas en estado gris. Ejemplo: intevarlo 1: Cargas que estado verde; intervalo 2: cargas en estado amarillo ...
	 * 
	 * @author jcvargasj
	 * 
	 */

	public Integer cargasPendientesPorProcesoAdmin(List<Carga> cargasFiltradas, Integer id_administrativo, Integer id_proceso_admin, Integer intervalos, Integer intervaloActual) {

		try {
			// Contador de cargas
			Integer contadorCargas = 0;

			// Obtenemos las cargaInstancia filtras

			if (intervaloActual > 0) {
				/*
				 * Para intervalos mayores a 0 se tiene en cuenta el porcentaje de tiempo transcurrido de la carga en la instancia en la que se cuentra. Por esta razon se requiere limites inferior y superarior
				 */

				// Calculamos el tamano de los intervalos que requieren
				// limites
				Integer maximoIntervaloConLimites = intervalos - 1;
				Integer tamaniooIntervalo = Constantes.TAMANIO_AREA_INTERVALOS / (maximoIntervaloConLimites);
				Integer limiteInferiorIntervalo = (intervaloActual - 1) * tamaniooIntervalo;
				Integer limiteSuperiorIntervalo = (intervaloActual) * tamaniooIntervalo;

				// Recorremos las cargas filtras y contamos las que correspondan
				// al
				// intervalo
				if (cargasFiltradas != null && cargasFiltradas.size() > 0) {
					for (Carga carga : cargasFiltradas) {

						// Solo se contabilizan las cargas que esten en estado
						// SUBIDO
						if (carga.getEstado().equals(Constantes.CARGA_ESTADO_SUBIDO)) {
							Integer porcentajeTiempoTranscurrido = carga.getPorcentajeTiempoTranscurrido();

							if (intervaloActual <= maximoIntervaloConLimites) {
								if (porcentajeTiempoTranscurrido >= limiteInferiorIntervalo && porcentajeTiempoTranscurrido < limiteSuperiorIntervalo) {
									contadorCargas = contadorCargas + 1;

								}
							} else {

								// Dado que es el ultimo intervalo solo se
								// compara con el tope maximo de al area de
								// intervalos
								if (porcentajeTiempoTranscurrido >= Constantes.TAMANIO_AREA_INTERVALOS) {
									contadorCargas = contadorCargas + 1;
								}
							}
						}
					}

				}

			} else if (intervaloActual == 0) {
				/*
				 * Si el intervalo es cero (0) se contabilizan las cargas que aun no tiene fecha de llegada a la instancia, es decir, instancias que aun se encuentra en estado REQUIRE_ADJUNTO(W). Estas cargas, aunque pertenecen a la instancia, aun no estan activas ya que requieren informacion del cliente.
				 */

				if (cargasFiltradas != null && cargasFiltradas.size() > 0) {
					for (Carga carga : cargasFiltradas) {
						if (carga.getEstado().equals(Constantes.CARGA_ESTADO_REQUIERE_ADJUNTO)) {
							contadorCargas++;
						}

					}

				}

			}

			return contadorCargas;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en CargaInstanciaServicio.cargasPendientesPorProcesoAdmin", e);
			return null;
		}
	}

	// ----------------------

	public Date consultarCambioEstado(Integer id_carga, String estado) {

		try {
			return DaoConfig.getDao(CargaDao.class).consultarCambioEstado(id_carga, estado);
		} catch (Exception e) {
		}

		return null;
	}

	// -------------------

	/**
	 * Copia los valores de una carga a carga 0
	 */
	public boolean aplicarCarga(Integer id_carga) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
			FormatoDao formatoDao = (FormatoDao) daoManager.getDao(FormatoDao.class);

			Carga carga = cargaDao.obtener(id_carga);
			Formato formato = formatoDao.obtenerFormato(carga.getId_formato());

			if (Constantes.SI.equals(formato.getAplicar_automatico())) {

				Integer id_estructura = formato.getId_estructura();

				aplicarCarga(carga, id_estructura);

				return true;
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return false;

	}

	private boolean aplicarCarga(Carga carga, Integer id_estructura) {

		(new Thread(new AplicacionCarga(id_estructura, carga))).start();

		return true;

	}

	public Integer aplicarCargaRegistro(Integer id_registro, Carga carga, Integer id_estructura, Map<Integer, List<Campo>> mapacampos, Map<Integer, List<Campo>> mapallaves) {

		CampoDao campoDao = (CampoDao) DaoConfig.getDao(CampoDao.class);
		CargaDao cargaDao = (CargaDao) DaoConfig.getDao(CargaDao.class, 2);

		// obtiene los campos de la etructura
		if (mapacampos.get(id_estructura) == null) {
			mapacampos.put(id_estructura, campoDao.obtenerCamposPorEstructura(id_estructura));
		}

		List<Campo> campos = mapacampos.get(id_estructura);

		// obtiene los campos que componen la llave primaria
		if (mapallaves.get(id_estructura) == null) {
			List<Campo> camposLlave = new ArrayList<Campo>();
			for (Campo campo : campos) {
				if (StringUtils.esVerdad(campo.getLlaveprimaria())) {
					camposLlave.add(campo);
				}
			}
			mapallaves.put(id_estructura, camposLlave);
		}

		// obtiene los valores del registro

		String sql_registro = "select * from T" + id_estructura + " where id = " + id_registro;
		Map<String, Object> registro = cargaDao.simpleselectSql(sql_registro);

		// Verifica que el registro no sea de la carga 0
		if (Integer.parseInt(registro.get("IDCARGA").toString()) != 0) {

			// actualiza los campos del registro que sean de relacion con otra
			// estructura

			for (Campo campo : campos) {
				if (campo.getId_estructurarelacionada() != null) {
					Object valor = registro.get("C" + campo.getId_campo());
					if (valor != null) {
						registro.put("C" + campo.getId_campo(), aplicarCargaRegistro(Integer.parseInt(valor.toString()), carga, campo.getId_estructurarelacionada(), mapacampos, mapallaves));
					}
				}
			}

			List<Campo> camposLlave = mapallaves.get(id_estructura);

			// Verifica si el registro ya existe en la carga cero

			String sql_cuni = "select ID from T" + id_estructura + " where idcarga = 0 ";

			if (camposLlave == null || camposLlave.size() == 0) {
				sql_cuni = sql_cuni + " and id=" + registro.get("ID");
			}

			for (Campo campo : camposLlave) {
				if (campo.getId_estructurarelacionada() != null) {
					sql_cuni += " and c" + campo.getId_campo() + " = " + registro.get("C" + campo.getId_campo());
				} else {
					sql_cuni += " and c" + campo.getId_campo() + " = '" + registro.get("C" + campo.getId_campo()) + "'";
				}
			}
			Map<String, Object> reg = cargaDao.simpleselectSql(sql_cuni);
			Object id_carga_cero = (reg != null) ? reg.get("ID") : null;
			boolean existe = (id_carga_cero != null);

			if (existe) {
				// actualiza registro de la carga cero

				String sql_up = "update T" + id_estructura + " set ESTADO = '" + Constantes.CARGA_ESTADO_APLICADA + "' ";
				for (Campo campo : campos) {
					if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equalsIgnoreCase(campo.getMultiplicidad())) {

						Object valor = registro.get("C" + campo.getId_campo());
						if (valor != null) {
							if (campo.getId_estructurarelacionada() == null) {
								sql_up += ", c" + campo.getId_campo() + " = '" + valor + "' ";
							} else {
								sql_up += ", c" + campo.getId_campo() + " = " + valor + " ";
							}
						} else {
							sql_up += ", c" + campo.getId_campo() + " = null ";
						}
					}
				}

				sql_up += " where id = " + id_carga_cero;

				cargaDao.updateSQL(sql_up);

			} else {
				// inserta nuevo registro

				id_registro = cargaDao.obtenerSiguienteCreadatos();

				String sql_up = "insert into T" + id_estructura + " ( id, idcarga, idelementocarga, estado, nreg ";

				for (Campo campo : campos) {
					if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equalsIgnoreCase(campo.getMultiplicidad())) {
						sql_up += ", c" + campo.getId_campo();
					}
				}

				sql_up += ") values (" + id_registro + ", 0, null, '" + Constantes.CARGA_ESTADO_APLICADA + "', null";

				for (Campo campo : campos) {
					if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equalsIgnoreCase(campo.getMultiplicidad())) {

						Object valor = registro.get("C" + campo.getId_campo());
						if (valor != null) {
							if (campo.getId_estructurarelacionada() != null) {
								sql_up += ", " + registro.get("C" + campo.getId_campo());
							} else {
								sql_up += ", '" + registro.get("C" + campo.getId_campo()) + "'";
							}
						} else {
							sql_up += ", null";
						}
					}
				}

				sql_up += ")";

				// actualiza cada registro
				cargaDao.insertSQL(sql_up);
			}

		}

		return id_registro;

	}

	public Integer contarBusqCargas(ParametrosBusqCargas parametros) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.contarBusqCargas(parametros);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return 0;
	}

	public List<Carga> buscarCargas(ParametrosBusqCargas parametros, Integer pagina) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.buscarCargas(parametros, pagina);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public List<Carga> buscarCargasCliente(ParametrosBusqCargas parametros, Integer pagina) {

		try {
			List<Carga> listaCargas = buscarCargas(parametros, pagina);
			for(int i=0 ; i<listaCargas.size();i++){
				Carga objCarga = listaCargas.get(i);
				String radicado = "";
				String numero_proceso = "";
				if(objCarga.getNumero_proceso()!= null && !objCarga.getNumero_proceso().equals("")){
					numero_proceso = Crypto.DF(objCarga.getNumero_proceso()).toString();
					objCarga.setNumero_proceso(numero_proceso);
				}
				if(objCarga.getRadicado()!= null && !objCarga.getRadicado().equals("")){
					radicado = Crypto.DF(objCarga.getRadicado()).toString(); 
					objCarga.setRadicado(radicado);
				}
				listaCargas.set(i, objCarga);
			}

			return listaCargas;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error obteniendo las cargas", e);
		}
		return null;
	}

	public List<Carga> obtenerCargasQueRequierenAdjuntosPorPersona(Integer id_persona) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			return cargaDao.obtenerCargasQueRequierenAdjuntosPorPersona(id_persona);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean actualizarCargaQueRequiereArchivoAdjunto(Integer id_carga, Integer id_persona) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			DaoManager daoManager2 = DaoConfig.getDaoManager(2);

			daoManager.startTransaction();
			daoManager2.startTransaction();

			try {
				// Actualiza el estaso de la carga
				String nuevoestado = EstadoCargaServicio.getInstance().siguienteEstado(id_carga, daoManager, id_persona);

				// Si el siguiente estado NO es SUBIDO no se guarda la
				// transaccion
				if (!nuevoestado.equals(Constantes.CARGA_ESTADO_SUBIDO)) {
					return false;

				}

				// Se actualiza las relaciones carga - instancia
				Date fecha_llegada = HorarioServicio.getInstance().obtenerFechaActual();
				CargaInstanciaDao cargaInstanciaDao = (CargaInstanciaDao) daoManager.getDao(CargaInstanciaDao.class);
				Boolean actualizacionExitosa = cargaInstanciaDao.inicializarFechaEntradaRelacionCargaInstancia(id_carga, fecha_llegada);

				if (!actualizacionExitosa) {
					return false;
				}

				// Si todo ha sido exitoso
				daoManager.commitTransaction();
				daoManager2.commitTransaction();

				return true;

			} catch (Exception e) {
				SimpleLogger.setError("Error en cargaDao.crearElementosCarga", e);
				return false;
			} finally {
				daoManager.endTransaction();
				daoManager2.endTransaction();

			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en CargaInstanciaServicio.actualizarFechaSalidaRelacionCargaInstancia", e);
			return false;
		}
	}

	protected Boolean crearValorTotalCarga(Carga carga, Formato formato) {

		DaoManager daoManagerData = DaoConfig.getDaoManager("DATASUITE");
		DaoManager daoManagerCrea = DaoConfig.getDaoManager("CREADATOS");

		try {

			CargaDao cargaDaoCrea = (CargaDao) daoManagerCrea.getDao(CargaDao.class);
			CargaDao cargaDaoData = (CargaDao) daoManagerData.getDao(CargaDao.class);

			String vtotal = null;

			Integer id_campo_totalizador = formato.getId_campo_totalizador();

			if (id_campo_totalizador != null) {

				// Se consulta los datos del campo totalizador
				List<Map<String, Object>> data = cargaDaoCrea.obtenerDatosCargaParaCampoTotalizador(carga.getId_carga(), id_campo_totalizador, formato.getId_estructura());

				BigDecimal acumuladorBigDecimal = new BigDecimal(0);
				if (data != null) {
					for (Map<String, Object> map : data) {

						Object valor = map.get("C" + id_campo_totalizador);

						if (valor != null) {
							BigDecimal numero = new BigDecimal(valor.toString());

							acumuladorBigDecimal = acumuladorBigDecimal.add(numero);

						}
					}
				}

				// Se encripta el valor total de la carga
				vtotal = Crypto.E(acumuladorBigDecimal);

			}

			Integer cantidad = null;

			if (formato.getId_estructura() != null) {
				cantidad = cargaDaoCrea.obtenerTotalRegistros(carga.getId_carga(), formato.getId_estructura());
			}

			String nregistros = Crypto.E(cantidad);

			Boolean actualizacionExitosa = cargaDaoData.actualizarValorTotalCarga(carga.getId_carga(), vtotal, nregistros);

			return actualizacionExitosa;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);

		}

		return false;

	}

	public void aplicarEstructuras(Carga carga) {

		List<Integer> estructuras = FormatoServicio.getInstance().obtenerEstructurasAplicar(carga.getId_formato());

		for (Integer id_estructura : estructuras) {
			aplicarCarga(carga, id_estructura);
		}
	}

	public Map<String, List<String>> validarDatosArchivoCarga(Integer id_formato, String rutaArchivo, Session session) {
		ValidadorDatosCarga validadorDatosCarga = new ValidadorDatosCarga();
		return validadorDatosCarga.validarDatosArchivoCarga(id_formato, rutaArchivo, session);

	}

	public List<Object> obtenerDatosCargaPorEstructura(Integer id_carga, Integer id_formato_salida) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager("CREADATOS");
			ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

			EstructuraServicio estructuraServicio = EstructuraServicio.getInstance();
			List<Estructura> estructurasPorFormato = estructuraServicio.obtenerEstructurasPorFormatoDeSalida(id_formato_salida);

			List<Object> listaCargasEst = new LinkedList<Object>();

			for (Estructura estructura : estructurasPorFormato) {

				Map<String, Object> mapEstructura = new HashMap<String, Object>();

				Integer id_estructura = estructura.getId_estructura();

				List<FormatoCampo> formatoscampo = FormatoServicio.getInstance().obtenerFormatosCampoPorEstructura(id_estructura, id_formato_salida);

				List<Campo> campos = CampoServicio.getInstance().obtenerCamposPorEstructura(id_estructura);

				List<Map<String, Object>> datos = validacionCargaDao.obtenerRegistrosDeEstructuraPorCargaVista(id_carga, id_estructura);

				definirFormatoDatos(datos, formatoscampo);

				mapEstructura.put("formatos_campo", formatoscampo);

				mapEstructura.put("campos", campos);

				mapEstructura.put("id_estructura", id_estructura);

				mapEstructura.put("nombre_estructura", estructura.getNombre());
				Boolean bandera = false;
				for (FormatoCampo formato : formatoscampo) {
					if (formato.getTitulo() != null) {
						String tituloCampo = StringUtils.upperCase(formato.getTitulo());
						if (tituloCampo.equals("ID CUENTA")) {
							for (Map<String, Object> dato : datos) {
								if (dato.containsKey("C" + formato.getId_campo().toString())) {
									Integer posicionDato = datos.indexOf(dato);
									Object temp = dato.get("C" + formato.getId_campo().toString());
									String cadenaDato = temp != null ? temp.toString(): "";
									if (cadenaDato.contains("-")) {
										Integer posicionGuion = cadenaDato.lastIndexOf("-");
										String cadenaTemp = cadenaDato.substring(posicionGuion + 1);
										if (!StringUtils.validaExpRegular(cadenaTemp, "[0-9]")) {
											cadenaDato = cadenaDato.substring(0, posicionGuion);
											dato.put("C" + formato.getId_campo().toString(), cadenaDato);
										}
									}
									datos.set(posicionDato, dato);
									bandera = true;
									break;
								}
							}
						}
					}
					if (bandera == true) {
						break;
					}
				}

				mapEstructura.put("datos", datos);

				listaCargasEst.add(mapEstructura);

			}
			return listaCargasEst;

		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error",e);
		}
		return null;

	}

	/**
	 * Actualiza el formato de los datos de acuerdo al formato en el FormatoCampo
	 * 
	 * @param datos
	 * @param formatoscampo
	 */
	private void definirFormatoDatos(List<Map<String, Object>> datos, List<FormatoCampo> formatoscampo) {

		// Se verifica que hayan datos
		if (CollectionUtils.isEmpty(formatoscampo) || CollectionUtils.isEmpty(datos)) {
			return;
		}

		// Formatos campo con formato
		List<FormatoCampo> formatos = new ArrayList<FormatoCampo>();

		for (FormatoCampo formatoCampo : formatoscampo) {
			if (StringUtils.esNoVacio(formatoCampo.getFormato())) {
				formatos.add(formatoCampo);
			}
		}

		if (CollectionUtils.isEmpty(formatos)) {
			return;
		}

		for (Map<String, Object> map : datos) {
			for (FormatoCampo formatoCampo : formatos) {
				String key = "C" + formatoCampo.getId_campo();
				if (map.containsKey(key)) {
					Object dato = map.get(key);

					if (dato != null) {

						String valor = FormatoUtils.obtenerValorPorFormato(formatoCampo, dato);
						map.put(key, valor);

					}

				}
			}
		}

	}

	public Integer obtenerFormatoSalida(Integer id_carga, Integer id_administrativo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

			Integer id_formato_salida = null;

			if (id_administrativo != null) {
				id_formato_salida = cargaDao.obtenerIdFormatoSalidaAdmin(id_carga, id_administrativo);
			}

			if (id_formato_salida == null) {
				id_formato_salida = cargaDao.obtenerIdFormatoSalidaCliente(id_carga);
			}

			if (id_formato_salida == null) {
				Carga carga = obtenerCarga(id_carga);
				Formato formato = FormatoServicio.getInstance().obtenerFormato(carga.getId_formato());
				id_formato_salida = formato.getIdformato_salida();
			}

			return id_formato_salida;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return 0;
	}

	public Integer buscarRegistroPorLlavePrimaria(Integer id_estructura, Map<String, Object> registro, Integer id_carga, DaoManager daoManager) {

		SQLDao sqlDao = (SQLDao) daoManager.getDao(SQLDao.class);

		List<Campo> campos = CampoServicio.getInstance().obtenerCamposLlavePrimaria(id_estructura);

		if (CollectionUtils.isEmpty(campos)) {
			return null;
		}

		String sql_cuni = "select ID from T" + id_estructura + " where idcarga = " + id_carga + " ";

		for (Campo campo : campos) {
			if (campo.getId_estructurarelacionada() != null) {
				sql_cuni += " and C" + campo.getId_campo() + " = " + registro.get("C" + campo.getId_campo());
			} else {
				sql_cuni += " and C" + campo.getId_campo() + " = '" + registro.get("C" + campo.getId_campo()) + "'";
			}
		}

		SimpleLogger.setDebug("SQL Llave primaria -> " + sql_cuni);

		return sqlDao.selectSQLNumber(sql_cuni);

	}

	public Integer contarRegistros(Integer id_estructura, Integer id_carga) {

		SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);
		return sqlDao.selectSQLNumber("select count(1) from T" + id_estructura + " where idcarga=" + id_carga);
	}

	public ArrayList<BigDecimal> obtenerValorRegistrosCarga(Integer id_carga) {
		List<Map<String, Object>> respuesta = null;
		Integer idCampoTotalizador = null;
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);
			SQLDao sqlDao = (SQLDao) daoManager.getDao(SQLDao.class);

			Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
			Formato formato = FormatoServicio.getInstance().obtenerFormato(carga.getId_formato());

			idCampoTotalizador = formato.getId_campo_totalizador();

			if (idCampoTotalizador == null) {
				return null;
			}

			String select = "SELECT C" + idCampoTotalizador + " from T" + formato.getId_estructura() + " where IDCARGA =" + id_carga;

			respuesta = sqlDao.selectSQL(select);
		} catch (Exception e) {
			SimpleLogger.setError("No se pudo obtener los registros de la carga " + id_carga, e);
			return null;
		}

		ArrayList<BigDecimal> valores = new ArrayList<BigDecimal>();
		for (Map<String, Object> registro : respuesta) {

			BigDecimal valor = null;
			if (registro.get("C" + idCampoTotalizador).toString().startsWith("D")) {
				valor = (BigDecimal) Crypto.DF(registro.get("C" + idCampoTotalizador));
			} else {
				Long longValor = (Long) Crypto.DF(registro.get("C" + idCampoTotalizador));
				valor = new BigDecimal(longValor);
			}

			if (valor != null)
				valores.add(valor);
		}

		return valores;

	}

	// --------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------

	public List<FormatoFiltro> obtenerFormatosFiltro(Integer id_formato) {

		try {
			CargaDao cargaDao = (CargaDao) DaoConfig.getDao(CargaDao.class);

			return cargaDao.obtenerFormatosFiltro(id_formato);

		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}

		return null;
	}

	// --------------------------------------------------------------------------------------------------

	public Boolean validarYAprobarAutomaticamente(Integer id_carga, List<Instancia> instancias){
		ProcesoAdmin procesoAdmin = ProcesoAdminServicio.getInstance().obtenerProcesoParaAsociarCarga(id_carga);
		return validarYAprobarAutomaticamente(id_carga, instancias, procesoAdmin);
	}
	
	public Boolean validarYAprobarAutomaticamente(Integer id_carga, List<Instancia> instancias, ProcesoAdmin procesoAdmin){
		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
		return validarAprobarORechazarAutomaticamente(carga, instancias, procesoAdmin);
	}
	
	public Boolean validarAprobarORechazarAutomaticamente(Carga carga, List<Instancia> instancias, ProcesoAdmin procesoAdmin){
		
		SimpleLogger.setInfo("Iniciando Validación");
		
		if (carga == null || instancias == null || procesoAdmin == null){
			SimpleLogger.setWarn("No se ejecuta la validación de la aprobación automática porque alguno de los elementos es nulo");
			return false;
		}
		
		// Luego de insertar la carga en todas las
		// instancias iniciales, validamos si alguna
		// tiene aprobación o rechazo automático

		for (Instancia instancia : instancias) {
			if (Constantes.CARGA_ESTADO_SUBIDO.equals(carga.getEstado()) && "S".equalsIgnoreCase(instancia.getAprobar_automaticamente())){
				// Si la carga no tiene acciones pendientes
				// puede aprobarse
				List<Instancia> instanciasPendientes = InstanciaServicio.getInstance().obtenerInstanciasPreviasConCargaActualPendiente(instancia.getId_instancia(), carga.getId_carga());
				if (instanciasPendientes != null && instanciasPendientes.isEmpty()){
					Boolean ok = CargaInstanciaServicio.getInstance().finalizarRelacionesCargaInstancia(carga.getId_carga(), instancia.getId_instancia(), Constantes.ID_PERSONA_APROB_AUTO)
							&& FlujoCargaServicio.getInstance().cambiarEstado(carga.getId_carga(), Constantes.CARGA_ESTADO_APROBADO, null, Constantes.ID_PERSONA_APROB_AUTO, null);
					if (ok){
						SimpleLogger.setInfo("Se aprobó la carga " + carga.getId_carga() + " de manera automática en la instancia " + instancia.getId_instancia() + " del proceso" + procesoAdmin.getId_proceso_admin());
						return ok;
					}else {
						SimpleLogger.setWarn("No fue posible aprobar la carga " + carga.getId_carga() + " de manera automática en la instancia " + instancia.getId_instancia() + " del proceso" + procesoAdmin.getId_proceso_admin());
					}
				}
			}else if (Constantes.CARGA_ESTADO_SUBIDO.equals(carga.getEstado()) && "S".equalsIgnoreCase(instancia.getRechazar_automaticamente())){
				// Si la carga no tiene acciones pendientes
				// puede aprobarse
				List<Instancia> instanciasPendientes = InstanciaServicio.getInstance().obtenerInstanciasPreviasConCargaActualPendiente(instancia.getId_instancia(), carga.getId_carga());
				if (instanciasPendientes != null && instanciasPendientes.isEmpty()){
					Boolean ok = CargaInstanciaServicio.getInstance().finalizarRelacionesCargaInstancia(carga.getId_carga(), instancia.getId_instancia(), Constantes.ID_PERSONA_APROB_AUTO)
							&& FlujoCargaServicio.getInstance().cambiarEstado(carga.getId_carga(), Constantes.CARGA_ESTADO_RECHAZADO, null, Constantes.ID_PERSONA_APROB_AUTO, null);
					if (ok){
						SimpleLogger.setInfo("Se rechazó la carga " + carga.getId_carga() + " de manera automática en la instancia " + instancia.getId_instancia() + " del proceso" + procesoAdmin.getId_proceso_admin());
						return ok;
					}else {
						SimpleLogger.setWarn("No fue posible rechazar la carga " + carga.getId_carga() + " de manera automática en la instancia " + instancia.getId_instancia() + " del proceso" + procesoAdmin.getId_proceso_admin());
					}
				}
			} 
		}
		return false;
	}
	
	public Map<String,Object> obtenerDatosBandejaEntrada (Integer id_formato, Integer id_carga) {
		String sql = "SELECT * FROM V_BANDEJA_" + id_formato + " WHERE IDCARGA = " + id_carga;
		
		SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

		List<Map<String,Object>> registros = sqlDao.selectSQL(sql); 
		
		if (registros != null) {
			registros = SQLServicio.desencriptarMapaStringFormat(registros);
		}
		
		return registros.get(0);
		
	}
	
	public String obtenerConsultaFiltrosBandejaEntrada (Integer id_formato, FiltroBandejaEntrada[] filtros) {
		String sql = null;
		
		try {
			
			String dblink = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("DBLINK_CREADATOS");
			
			if (dblink != null) {
				
				sql = "SELECT IDCARGA FROM v_filtro_bandeja_" + id_formato + "@" + dblink + " WHERE 1=1 ";
				
				for (FiltroBandejaEntrada filtro: filtros) {
					String campo = "FC" + filtro.getId_formato_campo();
					sql += "AND ( " + campo + " = $S(" + filtro.getFiltro() + ")$ " +
						     "    OR " + campo + " = $I(" + filtro.getFiltro() + ")$ " +
						     "    OR " + campo + " = $D(" + filtro.getFiltro() + ")$ " +
						     "    OR " + campo + " = $T(" + filtro.getFiltro() + ")$ )";
				}
			
				sql = RDServicio.reemplazarNombres(sql);
				SimpleLogger.setDebug("SQL FILTRO BANDEJA ");
				SimpleLogger.setDebug(sql);
				
			}
		} catch (Exception e) {
			SimpleLogger.setError("Ocurrio un error ", e);
			sql = null;
		}
		
		return sql;
	}
	
	@SuppressWarnings("unchecked")
	public List<Nodo> obtenerDetalleCargaPorFormato (Integer id_carga, Integer id_formatosalida) {
		
		List<Nodo> detalleFormulario = new ArrayList<>();
		
		try {
			
			Map<String, Object> map = obtenerDatosCargaPorFormato(id_carga, id_formatosalida, null, 1);
			List<Object> nodos = (List<Object>) map.get("#element");
			Map<String, Object> element = (Map<String, Object>) nodos.get(0);
			
			Queue<Object> q = new LinkedList<>();
			q.add(element);
			
			List<Object> nodosSubEstructuras = (List<Object>) element.get("#nodo");
			for (Object nodoSubEstructuras: nodosSubEstructuras) {
				Map<String, Object> nodo = (Map<String, Object>) nodoSubEstructuras;
				if (nodo.get("@type") != null) {
					q.add(nodo);
				}
			}
			
			Iterator<Object> iterator = q.iterator();
			 
	        while (iterator.hasNext()) {
	        	Map<String, Object> nodo = (Map<String, Object>)iterator.next();
	        	List<Nodo> nodosHijos = new ArrayList<>();
	        	Nodo padre = new Nodo();
	        	padre.setname(nodo.get("@name").toString());
        		if (nodo.get("@helpMessage") != null) {
        			padre.setHelpMessage(nodo.get("@helpMessage").toString());	        		        			
        		}
	        	List<Object> subNodos = (List<Object>) nodo.get("#nodo");
	        	for (Object subNodo: subNodos) {
	        		Map<String, Object> subNodoMap = (Map<String, Object>) subNodo;
	        		Nodo hijo = new Nodo();
	        		hijo.setname(subNodoMap.get("@name").toString());
	        		if (StringUtils.esVacio(subNodoMap.get("@value"))) continue;
	        		
	        		String value = validarCampo((Integer) subNodoMap.get("@id_campo"), subNodoMap.get("@value").toString());
	        		hijo.setvalue(value);
	        		nodosHijos.add(hijo);
	        	}
	        	padre.setNodo(nodosHijos);
	        	if (nodosHijos.size() > 0) {
	        		detalleFormulario.add(padre);
	        	}
	        }
			
		} catch (Exception e) {
			SimpleLogger.setError("CargaServicio.obtenerDetalleCargaPorFormato: Error al generar detalle del formulario ", e);
		}
		
		return detalleFormulario;
		
	}
	
	public String validarCampo(Integer id_campo, String value) {
		if (id_campo == null) return value;
		Campo campo = CampoServicio.getInstance().obtenerCampo(id_campo);
		String nombre = campo.getNombre();
		if ("ID Contador".equalsIgnoreCase(nombre) || "ID Revisor Fiscal".equalsIgnoreCase(nombre) 
				|| "ID Apoderado".equalsIgnoreCase(nombre) || "ID Representante Legal".equalsIgnoreCase(nombre)
				|| "ID Contador PNNC".equalsIgnoreCase(nombre)) {
			Persona persona = PersonaServicio.getInstance().obtenerPersona(Integer.parseInt(value));
			value = persona.getNombreCompleto() + " - " + persona.getIdentificacion();
		}
		
		return value;
	}
	
	public Boolean actualizarFechaLiberacion (Integer id_carga) {
		try {

			CargaDao cargaDao = (CargaDao) DaoConfig.getDao(CargaDao.class);
			CargaDao cargaDaoCreadatos = (CargaDao) DaoConfig.getDao(CargaDao.class, 2);
			
			Carga carga = obtenerCarga(id_carga);
			carga.setFecha_liberacion(HorarioServicio.getInstance().obtenerFechaActual());
			
			cargaDao.actualizarFechaLiberacion(carga);
			cargaDaoCreadatos.actualizarFechaLiberacionCreadatos(carga);
			
			return true;
			
		} catch (Exception e) {
			SimpleLogger.setError("CargaServicio.actualizarFechaLiberacion: Error al actualizar fecha liberacion ", e);
			return false;
		}

	}
	
}

class AplicacionCarga implements Runnable {

	private Carga carga;
	private Integer id_estructura;

	public AplicacionCarga(Integer id_estructura, Carga carga) {

		this.carga = carga;
		this.id_estructura = id_estructura;
	}

	public void run() {

		try {

			SimpleLogger.setDebug("AplicacionCarga. id_estructura:" + id_estructura + " Carga" + carga);

			DaoManager daoManager2 = DaoConfig.getDaoManager("CREADATOS");

			CargaDao cargaDao = (CargaDao) daoManager2.getDao(CargaDao.class);

			// obtener datos de la carga..

			String sql_cuni = "select distinct c.id from T" + id_estructura + " c where c.idcarga = " + carga.getId_carga();
			List<Map<String, Object>> datos = null;

			long t = System.currentTimeMillis();

			while ((datos == null || datos.size() <= 0) && t + 10000 > System.currentTimeMillis()) {
				datos = cargaDao.selectSql(sql_cuni);
			}

			Map<Integer, List<Campo>> mapacampos = new HashMap<Integer, List<Campo>>();
			Map<Integer, List<Campo>> mapallaves = new HashMap<Integer, List<Campo>>();

			for (Map<String, Object> registro : datos) {
				CargaServicio.getInstance().aplicarCargaRegistro(Integer.parseInt(registro.get("ID").toString()), carga, id_estructura, mapacampos, mapallaves);
			}

			// ////////////////////////////////////////////////77777

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

	}

}

class HiloFinalizarCarga implements Runnable {

	private Formato formato;
	private Carga carga;
	private Session session;
	private List<ValorListaDinamicaCampo> listaValoresDinamicos;

	public HiloFinalizarCarga(Formato formato, Carga carga, Session session, List<ValorListaDinamicaCampo> listaValoresDinamicos) {
		this.formato = formato;
		this.carga = carga;
		this.session = new DummySession(session);
		this.listaValoresDinamicos = listaValoresDinamicos;
	}

	public void run() {

		try {

			esperarGuardadoCarga();

			SimpleLogger.setInfo("Finalizando carga " + carga.getId_carga());

			long n = System.currentTimeMillis();

			// Se guardan los valores dinamicos

			Integer id_formato = formato.getId_formato();
			CargaServicio.getInstance().guardarValoresLista(listaValoresDinamicos, id_formato, true);

			Boolean rta = FormatoOperacionServicio.getInstance().calcularValorPorCarga(carga.getId_carga(), id_formato, session);

			if (!rta) {
				SimpleLogger.setError("Error al ejecutar las operaciones del formato " + id_formato + " en la carga " + carga.getId_carga());
			}

			// Se crea el valor total de la carga
			if (!CargaServicio.getInstance().crearValorTotalCarga(carga, formato)) {

				SimpleLogger.setError("Ha ocurrido un error, no se ha podido totalizar el valor de la carga");
				return;
			}

			long m = System.currentTimeMillis();

			SimpleLogger.setInfo("Fin creacion de Carga " + carga.getId_carga() + " ( Tiempo finalizacion: " + ((m - n) / 1000) + " segundos )");

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al guardar valores dinamicos", e);
		}

	}

	private void esperarGuardadoCarga() throws Exception {

		SQLDao sqldao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

		String sqlcompleto = "select count(1) from ts01 where id_carga = " + carga.getId_carga();

		while (sqldao.selectSQLNumber(sqlcompleto) == 0) {
			Thread.sleep(1000);
		}

	}

}

class HiloCargaLotes implements Runnable {
	private CargaDao cargaDao;
	private Formato formato;
	private Session session;
	private Carga carga;
	private List<FormatoCampo> listadoFCUnicos;
	private CargaDao cargaDaoData;
	private DaoManager daoManager;
	private Integer id_persona;
	private DaoManager daoManagerData;
	private List<ValorListaDinamicaCampo> listaValoresDinamicos;

	public HiloCargaLotes(CargaDao cargaDao, Formato formato, Session session, Carga carga, List<FormatoCampo> listadoFCUnicos, CargaDao cargaDaoData, DaoManager daoManager, Integer id_persona, DaoManager daoManagerData, List<ValorListaDinamicaCampo> listaValoresDinamicos) {
		super();
		this.cargaDao = cargaDao;
		this.formato = formato;
		this.session = session;
		this.carga = carga;
		this.listadoFCUnicos = listadoFCUnicos;
		this.cargaDaoData = cargaDaoData;
		this.daoManager = daoManager;
		this.id_persona = id_persona;
		this.daoManagerData = daoManagerData;
		this.listaValoresDinamicos = listaValoresDinamicos;
	}

	public void run() {

		Mensaje mensaje = (Mensaje) session.getAttribute("mensaje_carga_lotes");

		Connection connection = null;
		try {

			connection = DaoConfig.getSqlMapClient(daoManagerData).getDataSource().getConnection();

			connection.setAutoCommit(false);

			Boolean ret = CargaServicio.getInstance().realizarCargaH(cargaDao, carga, formato, listadoFCUnicos, session, connection);
			mensaje.setEstado("F");
			mensaje.setExitoso(ret);

			Boolean rta = false;

			if (ret) {
				rta = CargaServicio.getInstance().finalizarEnvioCarga(cargaDaoData, carga, daoManager, id_persona, formato, daoManagerData, session, listaValoresDinamicos);
			}

			if (rta) {
				daoManager.commitTransaction();
				daoManagerData.commitTransaction();

				ValidacionEstructuraServicio.getInstance().generarValidacionesNoEnLineaHilo(carga.getId_carga());
			}
			connection.commit();
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			mensaje.setEstado("F");
			mensaje.setExitoso(false);
		} finally {
			try {
				daoManager.endTransaction();
				daoManagerData.endTransaction();
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e2) {
				SimpleLogger.setError("Error ocurrido en CargaServicio", e2);
			}
		}
		SystemUtils.liberarMemoria();
	}

}
