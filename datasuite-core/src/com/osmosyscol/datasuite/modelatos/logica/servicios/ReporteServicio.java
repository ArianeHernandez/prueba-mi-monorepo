package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.XMLFormat;
import com.osmosyscol.datapi.constantes.VariablesAplicacion;
import com.osmosyscol.datapi.logica.dto.Conexion;
import com.osmosyscol.datapi.logica.dto.Consulta;
import com.osmosyscol.datapi.logica.dto.Elemento;
import com.osmosyscol.datapi.logica.dto.Operacion;
import com.osmosyscol.datapi.logica.dto.Parametro;
import com.osmosyscol.datapi.logica.dto.ServicioDataPi;
import com.osmosyscol.datasuite.persistencia.dao.AccionFilaDao;
import com.osmosyscol.datasuite.persistencia.dao.NavegacionDao;
import com.osmosyscol.datasuite.persistencia.dao.ParametroAccionDao;
import com.osmosyscol.datasuite.persistencia.dao.ParametroReporteDao;
import com.osmosyscol.datasuite.persistencia.dao.ReporteDao;
import com.osmosyscol.datasuite.persistencia.dao.ResultadoDao;
import com.osmosyscol.datasuite.reportedim.builder.ServicioReporteDimBuilder;
import com.osmosyscol.datasuite.reportedim.dto.Navegacion;
import com.osmosyscol.datasuite.reportedim.dto.ParametroAccion;
import com.osmosyscol.datasuite.reportedim.dto.ParametroReporte;
import com.osmosyscol.datasuite.reportedim.dto.Resultado;
import com.osmosyscol.datasuite.reportedim.dto.ServicioReporteDim;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ReporteServicio {

	private static ReporteServicio reporteServicio;

	private ReporteServicio() {
	}

	public static ReporteServicio getInstance() {
		if (reporteServicio == null) {
			reporteServicio = new ReporteServicio();
		}
		return reporteServicio;
	}

	public ServicioReporteDim obtenerReporte(String id_reporte) {

		ServicioReporteDim servicioReporteDim = null;

		try {

			if (id_reporte == null || id_reporte.matches("\\s*")) {
				throw new Exception("El id_reporte solicitado es nulo");
			}

			// Consultar de Reporte
			DaoManager daoManager = DaoConfig.getDaoManager();
			ReporteDao reporteServicioDao = (ReporteDao) daoManager.getDao(ReporteDao.class);
			servicioReporteDim = reporteServicioDao.obtenerReporte(id_reporte);

			if (servicioReporteDim == null)
				throw new Exception("No se encontro el reporte buscado con id: " + id_reporte);

			// Consultar Parametros de Reporte
			List<ParametroReporte> parametros = ParametroReporteServicio.getInstance().obtenerParametroReportePorReporte(id_reporte);
			if (parametros != null)
				servicioReporteDim.setParametros(parametros);

			// Consultar Resultados de Reporte
			servicioReporteDim.setResultados(ResultadoServicio.getInstance().obtenerResultadosPorReporte(id_reporte));

			// Consultar Acciones de Reporte
			servicioReporteDim.setAccionfila(AccionFilaServicio.getInstance().obtenerAccionFila(id_reporte));

			// Consultar Navegaciones de Reporte
			servicioReporteDim.setNavegacion(NavegacionServicio.getInstance().obtenerNavegacionPorReporte(id_reporte));

			return servicioReporteDim;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ReporteServicio.obtenerReporte", e);
			return servicioReporteDim;
		}
	}

	public Boolean insertarReporte(String id, String consulta, String nombre_conexion, String nombre) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ReporteDao reporteServicioDao = (ReporteDao) daoManager.getDao(ReporteDao.class);

			return reporteServicioDao.crearReporte(id, consulta, nombre_conexion, nombre);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ReporteServicio.insertarReporte", e);
			return false;
		}
	}

	public Boolean crearReporteNuevo(ServicioReporteDim srd) {

		Boolean correcto = true;
		DaoManager daoManager = null;

		if (srd.getId() == null)
			srd.setId(srd.getNombre());

		try {

			daoManager = DaoConfig.getDaoManager();

			daoManager.startTransaction();

			// Creando el reporte
			ReporteDao reporteServicioDao = (ReporteDao) daoManager.getDao(ReporteDao.class);
			correcto = correcto && reporteServicioDao.crearReporte(srd.getId(), srd.getConsulta(), srd.getNombre_conexion(), srd.getNombre());
			// Validando Creacion de reporte
			if (!correcto) {
				throw new Exception("Hubo un error al crear el reporte en la Base de Datos:\n" + "ID: " + srd.getId() + "\nconsulta: " + srd.getConsulta() + "\nconexion: " + srd.getNombre_conexion() + "\nnombre: " + srd.getNombre());
			}

			// Creando parametros
			if (srd.getParametros() != null) {
				for (ParametroReporte parametroReporte : srd.getParametros()) {
					Integer id = ParametroReporteServicio.getInstance().obtenerSiguienteParametroReporteId();
					correcto = correcto && ParametroReporteServicio.getInstance().insertarParametroReporte(id, parametroReporte.getNombre(), parametroReporte.getTipo(), parametroReporte.getEncriptado(), parametroReporte.getFiltro(), parametroReporte.getTitulo(), parametroReporte.getOrden(), srd.getId());
					// Validando Creacion de parametros
					if (!correcto) {
						throw new Exception("Hubo un error al crear el parametro [ " + parametroReporte.getNombre() + " ] en la Base de Datos\n" + parametroReporte.toString());
					}
				}
			}

			// Creando resultado
			if (srd.getResultados() != null) {
				for (Resultado resultado : srd.getResultados()) {
					Integer id = ResultadoServicio.getInstance().obtenerSiguienteResultadoId();
					correcto = correcto && ResultadoServicio.getInstance().insertarResultado(id, resultado.getTitulo(), resultado.getNombre(), resultado.getTipo(), resultado.getOrden(), resultado.getEncriptado(), resultado.getOculto(), srd.getId());
					// Validando Creacion de parametros
					if (!correcto) {
						throw new Exception("Hubo un error al crear el resultado [ " + resultado.getNombre() + " ] en la Base de Datos");
					}
				}
			}

			// Creando la accion
			if (srd.getAccionfila() != null) {
				srd.getAccionfila().setId_reporte(srd.getId());
				correcto = correcto && AccionFilaServicio.getInstance().insertarAccionFila(srd.getAccionfila());
				if (!correcto) {
					throw new Exception("Hubo un error al crear la accion [ " + srd.getAccionfila().toString() + " ] en la Base de Datos");
				}
			}

			// Creando navegaciones
			if (srd.getNavegacion() != null) {
				for (Navegacion navegacion : srd.getNavegacion()) {
					navegacion.setId_reporte(srd.getId());
					correcto = correcto && NavegacionServicio.getInstance().crearNavegacion(navegacion);
					// Validando Creacion de parametros
					if (!correcto) {
						throw new Exception("Hubo un error al crear la navegacion [ " + navegacion.getNombre() + " ] en la Base de Datos\n" + navegacion.toString());
					}

				}
			}
			if (correcto)
				daoManager.commitTransaction();

			return correcto;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ReporteServicio.insertarReporte(ServicioReporteDim)", e);
			return false;
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
	}

	public List<ServicioReporteDim> obtenerReportes() {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ReporteDao reporteServicioDao = (ReporteDao) daoManager.getDao(ReporteDao.class);

			List<String> idsReportesDb = reporteServicioDao.obtenerReportes();

			if (idsReportesDb == null)
				throw new Exception("No se encontraron reportes en la BD");

			List<ServicioReporteDim> reportes = new ArrayList<ServicioReporteDim>();

			for (String id : idsReportesDb) {
				ServicioReporteDim srp = this.obtenerReporte(id);
				if (srp != null)
					reportes.add(srp);
			}

			return reportes;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ReporteServicio.obtenerReportes", e);
			return null;
		}
	}

	public Boolean actualizarReporte(ServicioReporteDim srd) {

		try {
			if (srd.getId() == null)
				throw new Exception("Se ha recibido un id nulo...");

			DaoManager daoManager = DaoConfig.getDaoManager();
			ReporteDao reporteDao = (ReporteDao) daoManager.getDao(ReporteDao.class);

			boolean resultado = true;

			reporteDao.actualizarReporte(srd);

			AccionFilaDao accionfilaDao = (AccionFilaDao) daoManager.getDao(AccionFilaDao.class);
			resultado = resultado && accionfilaDao.eliminarAccionFila(srd.getId());
			if (srd.getAccionfila() != null) {

				resultado = resultado && accionfilaDao.crearAccionFila(srd.getAccionfila().getDestino(), srd.getAccionfila().getSubreporte(), srd.getId());

				ParametroAccionDao parametroAccionDao = (ParametroAccionDao) daoManager.getDao(ParametroAccionDao.class);

				List<ParametroAccion> parametrosAccion = parametroAccionDao.obtenerParametrosByAccion(srd.getAccionfila().getId_reporte());

				for (ParametroAccion pa : parametrosAccion) {
					resultado = resultado && parametroAccionDao.eliminarParametroAccion(pa.getId_parametro_accion());
				}

				if (srd.getAccionfila() != null && srd.getAccionfila().getParametros() != null) {
					for (ParametroAccion pa : srd.getAccionfila().getParametros()) {
						resultado = resultado && parametroAccionDao.crearParametroAccion(parametroAccionDao.obtenerSiguienteParametroAccionId(), pa.getNombre(), pa.getTipo(), pa.getEncriptado(), pa.getValor(), srd.getId(), pa.getId_navegacion());
					}
				}
			}

			NavegacionDao navegacionDao = (NavegacionDao) daoManager.getDao(NavegacionDao.class);

			List<Navegacion> navegaciones = navegacionDao.obtenerNavegacionesByReporte(srd.getId());
			for (Navegacion n : navegaciones)
				resultado = resultado && navegacionDao.eliminarNavegacion(n.getId_navegacion());

			if (srd.getNavegacion() != null)
				for (Navegacion n : srd.getNavegacion())
					resultado = resultado && navegacionDao.crearNavegacion(navegacionDao.obtenerSiguienteNavegacionId(), n.getNombre(), n.getDestino(), srd.getId());

			ParametroReporteDao parametroReporteDao = (ParametroReporteDao) daoManager.getDao(ParametroReporteDao.class);
			List<ParametroReporte> parametros = parametroReporteDao.obtenerParametrosByReporte(srd.getId());
			for (ParametroReporte pr : parametros)
				resultado = resultado && parametroReporteDao.eliminarParametroReporte(pr.getId_parametro());
			if (srd.getParametros() != null)
				for (ParametroReporte pr : srd.getParametros())
					resultado = resultado && parametroReporteDao.crearParametroReporte(parametroReporteDao.obtenerSiguienteParametroReporteId(), pr.getNombre(), pr.getTipo(), pr.getEncriptado(), pr.getFiltro(), pr.getTitulo(), pr.getOrden(), srd.getId());

			ResultadoDao resultadoDao = (ResultadoDao) daoManager.getDao(ResultadoDao.class);
			List<Resultado> resultados = resultadoDao.obtenerResultadosByReporte(srd.getId());
			for (Resultado r : resultados)
				resultado = resultado && resultadoDao.eliminarResultado(r.getId_resultado());
			if (srd.getResultados() != null)
				for (Resultado r : srd.getResultados())
					resultado = resultado && resultadoDao.crearResultado(resultadoDao.obtenerSiguienteResultadoId(), r.getTitulo(), r.getNombre(), r.getTipo(), r.getOrden(), r.getEncriptado(), r.getOculto(), srd.getId());

			return resultado;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ReporteServicio.actualizarReporte", e);
			return false;
		}

	}

	public Boolean eliminarReporte(String id_reporte) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ReporteDao reporteServicioDao = (ReporteDao) daoManager.getDao(ReporteDao.class);

			return reporteServicioDao.eliminarReporte(id_reporte);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ReporteServicio.eliminarReporte", e);
			return false;
		}

	}

	public Boolean cargarReporte(String id_reporte) {
		try {

			// Se obtiene el servicio
			DaoManager daoManager = DaoConfig.getDaoManager();
			ReporteDao reporteServicioDao = (ReporteDao) daoManager.getDao(ReporteDao.class);

			ServicioReporteDim srd = reporteServicioDao.obtenerReporte(id_reporte);

			if (srd == null) {
				throw new Exception("No se encontró el reporte con id: [" + id_reporte + "] en la BD");
			}

			// se agrega a la lista
			return ServicioReporteDimBuilder.cargaReporteBaseDatos(srd);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ReporteServicio.cargarReporte en lista de ServicioReporteDimBuilder", e);
			return false;
		}

	}

	public Set<String> obtenerServiciosCargados() {
		try {
			// se agrega a la lista
			return ServicioReporteDimBuilder.getListadoServicios();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ReporteServicio.obtenerServiciosCargados", e);
			return null;
		}

	}

	public List<String> obtenerIdReportesBD() {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ReporteDao reporteServicioDao = (ReporteDao) daoManager.getDao(ReporteDao.class);

			return reporteServicioDao.obtenerReportes();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ReporteServicio.obtenerReportes", e);
			return null;
		}
	}

	public List<ServicioReporteDim> obtenerReportesBD() {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			ReporteDao reporteServicioDao = (ReporteDao) daoManager.getDao(ReporteDao.class);

			return reporteServicioDao.obtenerTodosReportes();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ReporteServicio.obtenerReportes", e);
			return null;
		}
	}

	// -----------------------------------------------------

	public Boolean crearServiciosDataPi() {

		Set<String> reportes = ServicioReporteDimBuilder.getListadoServicios();

		Map<String, ServicioDataPi> serviciosDatapi = VariablesAplicacion.getInstance().getListadoServicios();

		for (String id_reporteServicio : reportes) {
			ServicioReporteDim srd = ServicioReporteDimBuilder.getServicio(id_reporteServicio);

			List<ParametroReporte> prs = srd.getParametros();

			boolean adicionar = true;

			if (prs != null) {
				for (ParametroReporte pr : prs) {
					if (!pr.getFiltro()) {
						adicionar = false;
					}
				}
			}

			if (adicionar) {
				// System.out.println("*********************************************");
				// System.out.println("Procesando reporte: " + srd.getNombre());

				ServicioDataPi sdp = crearServiciosDataPi(srd);
				serviciosDatapi.put(sdp.getNombre(), sdp);

				// System.out.println(XMLFormat.format(JavaToXML.exe("sdp", sdp).toString()));
			}
		}

		return true;

	}

	private static Map<String, String> mapTypes = new HashMap<String, String>();

	static {
		mapTypes.put(ParametroReporte.TIPO_BOOLEAN, Parametro.TIPO_BOOLEAN);
		mapTypes.put(ParametroReporte.TIPO_DATE, Parametro.TIPO_DATE);
		mapTypes.put(ParametroReporte.TIPO_DOUBLE, Parametro.TIPO_DOUBLE);
		mapTypes.put(ParametroReporte.TIPO_FLOAT, Parametro.TIPO_FLOAT);
		mapTypes.put(ParametroReporte.TIPO_INT, Parametro.TIPO_INT);
		mapTypes.put(ParametroReporte.TIPO_MONEY, Parametro.TIPO_DOUBLE);
		mapTypes.put(ParametroReporte.TIPO_STRING, Parametro.TIPO_STRING);
	}

	private String reporteTypeToDatapiType(String dptype) {

		String t = mapTypes.get(dptype.toLowerCase());

		if (t == null) {
			t = Parametro.TIPO_STRING;
		}

		return t;
	}

	private ServicioDataPi crearServiciosDataPi(ServicioReporteDim reporte) {

		ServicioDataPi datapi = new ServicioDataPi();

		// ---------------- Define nombre

		String nombre = reporte.getNombre().toLowerCase().replace(" ", "_");
		datapi.setNombre(nombre);

		// ----------------- Define conexion

		Conexion conexion = new Conexion();
		conexion.setNombre(reporte.getNombre_conexion());
		datapi.setConexion(conexion);

		// ----------------- Define operacion y consulta

		List<Operacion> operaciones = new ArrayList<Operacion>();
		Operacion operacion = new Operacion();

		Consulta consulta = new Consulta();
		consulta.setSql(reporte.getConsulta());

		operacion.setConsulta(consulta);
		operacion.setNombre(nombre);

		operaciones.add(operacion);

		datapi.setOperaciones(operaciones);

		// ----------------- Define Parametros

		List<Parametro> parametros = new ArrayList<Parametro>();
		operacion.setParametros(parametros);

		List<ParametroReporte> parametrosReporte = reporte.getParametros();

		for (ParametroReporte parametroReporte : parametrosReporte) {
			Parametro parametro = new Parametro();
			parametro.setNombre(parametroReporte.getNombre());
			parametro.setEncriptado(parametroReporte.getEncriptado());
			parametro.setObligatorio(false);
			parametro.setTipo(reporteTypeToDatapiType(parametroReporte.getTipo()));

			parametros.add(parametro);
		}

		// ----------------- Define resultados

		List<com.osmosyscol.datapi.logica.dto.Resultado> resultados = new ArrayList<com.osmosyscol.datapi.logica.dto.Resultado>();
		operacion.setResultados(resultados);

		List<Resultado> resultadosReporte = reporte.getResultados();

		for (Resultado resultadoRep : resultadosReporte) {
			com.osmosyscol.datapi.logica.dto.Resultado resultado = new com.osmosyscol.datapi.logica.dto.Resultado();

			resultado.setEncriptado(resultadoRep.getEncriptado());
			resultado.setNombre(resultadoRep.getNombre());
			resultado.setTipo(reporteTypeToDatapiType(resultadoRep.getTipo()));

			resultados.add(resultado);
		}

		// --------------------- Agrega subconsultas

		if (reporte.getAccionfila() != null && reporte.getAccionfila().getSubreporte() != null) {

			try {
				ServicioReporteDim subreporte = ServicioReporteDimBuilder.getServicio(reporte.getAccionfila().getSubreporte());

				com.osmosyscol.datapi.logica.dto.Resultado resultado = new com.osmosyscol.datapi.logica.dto.Resultado();
				resultados.add(resultado);
				resultado.setNombre(subreporte.getNombre().toLowerCase().replace(" ", "_"));
				resultado.setTipo("subquery");

				List<Elemento> elementos = new ArrayList<Elemento>();
				resultado.setElementos(elementos);

				List<ParametroAccion> parametrosAccion = null;
				if (reporte.getAccionfila() != null) {
					parametrosAccion = reporte.getAccionfila().getParametros();
				}

				aplicarSubReporte(datapi.getOperaciones().get(0).getConsulta(), elementos, parametrosAccion, subreporte);
			} catch (Exception e) {
				SimpleLogger.setError("Error aplicando subreporte: " + reporte.getAccionfila().getSubreporte(), e);
			}
		}

		return datapi;
	}

	private void aplicarSubReporte(Consulta consulta, List<Elemento> elementos, List<ParametroAccion> parametrosAccion, ServicioReporteDim reporte) {

		List<Consulta> subconsultaList = new ArrayList<Consulta>();
		consulta.setSubconsulta(subconsultaList);

		// ------------- Aplica para las consultas

		Consulta subconsulta = new Consulta();
		subconsultaList.add(subconsulta);

		String nombre = reporte.getNombre().toLowerCase().replace(" ", "_");
		subconsulta.setNombre(nombre);

		String sql = reporte.getConsulta();

		for (ParametroAccion parametroAccion : parametrosAccion) {
			sql = sql.replace("#" + parametroAccion.getNombre() + "#", parametroAccion.getValor()); // TODO Verificar si se debe cambiar la cadena segun tipo de dato
		}

		subconsulta.setSql(sql);

		// -------------- aplica para las resultados

		List<Resultado> resultadosReporte = reporte.getResultados();

		for (Resultado resultadoRep : resultadosReporte) {
			Elemento elemento = new Elemento();

			elemento.setNombre(resultadoRep.getNombre());
			elemento.setTipo(reporteTypeToDatapiType(resultadoRep.getTipo()));

			elementos.add(elemento);
		}

		// ----------------------------------------------- Aplica recursivamente

		if (reporte.getAccionfila() != null && reporte.getAccionfila().getSubreporte() != null) {
			ServicioReporteDim subreporte = ServicioReporteDimBuilder.getServicio(reporte.getAccionfila().getSubreporte());

			Elemento elemento = new Elemento();
			elemento.setNombre(nombre);
			elemento.setTipo("subquery");
			List<Elemento> nelementos = new ArrayList<Elemento>();
			elemento.setElementos(nelementos);
			elementos.add(elemento);

			List<ParametroAccion> nparametrosAccion = null;
			if (reporte.getAccionfila() != null) {
				nparametrosAccion = reporte.getAccionfila().getParametros();
			}

			aplicarSubReporte(subconsulta, nelementos, nparametrosAccion, subreporte);
		}

		// -------------
	}

	// -----------------------------------------------------
}
