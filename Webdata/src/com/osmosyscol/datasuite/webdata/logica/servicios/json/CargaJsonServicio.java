package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import com.itosmosys.firmadigital.servicios.FirmaServicio;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Administrador;
import com.osmosyscol.datasuite.logica.dto.CargaInstancia;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.servicios.AdministradorServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CargaInstanciaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.GeneradorArchivoCargaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.TipoCampoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.HistorialCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.ParametrosBusqCargas;
import com.osmosyscol.datasuite.webdata.logica.dto.ValorFiltro;
import com.osmosyscol.datasuite.webdata.logica.dto.VariableLiberacion;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.HistorialServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.VariableLiberacionServicio;

public class CargaJsonServicio implements JsonService {

	private Integer id_usuario;

	private Integer id_administrativo;

	private Integer id_persona;

	private Session session;

	private Integer id_administrador;

	public void setSession(Session session) {

		this.session = session;

		Object text = session.getAttribute("id_usuario");
		if (text != null) {
			id_usuario = new Integer(text.toString());
			id_persona = (Integer) session.getAttribute("id_persona");
		}

		text = session.getAttribute("id_administrativo");
		if (text != null) {
			id_administrativo = new Integer(text.toString());
		}
		
		Administrador administrador = AdministradorServicio.getInstance().obtenerAdministradorPorLogin((String) session.getAttribute("login"));
		
		if (administrador != null) {
			id_administrador = administrador.getId_administrador();
		}
		
	}
	
	public Boolean inicioBusqueda(){
		try {
			ParametrosBusqCargas parametros = (ParametrosBusqCargas) session.getAttribute("parametros_busqueda_cargas");
			Integer pagina = (Integer) session.getAttribute("pagina_busqueda_cargas");
			return parametros != null && pagina != null;
		}catch (Exception e){
			SimpleLogger.setError("Error en CargaJsonServicio.inicioBusqueda", e);
			return false;
		}
	}
	
	public List<Carga> listarCargasDuplicadas(Integer id_carga, String valor_total, String numero_registros){
		try {
			CargaServicio servicio = CargaServicio.getInstance();
			
			return servicio.listarCargasDuplicadas(id_carga, valor_total, numero_registros);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en CargaJsonServicio.inicioBusqueda", e);
			return null;
		}
	}
	
	public List<Carga> listarCargasDuplicadasE(String valor_total, String numero_registros){
		try {
			CargaServicio servicio = CargaServicio.getInstance();
			
			return servicio.listarCargasDuplicadas(null, Crypto.E(new Double(valor_total)), Crypto.E(new Integer(numero_registros)));
			
		}catch (Exception e){
			SimpleLogger.setError("Error en CargaJsonServicio.inicioBusqueda", e);
			return null;
		}
	}
	
	public Map<String, Object> buscarCargasInicio(){
		ParametrosBusqCargas parametros = (ParametrosBusqCargas) session.getAttribute("parametros_busqueda_cargas");
		Integer pagina = (Integer) session.getAttribute("pagina_busqueda_cargas");
		
		Map<String, Object> map = new HashMap<String, Object>();

		CargaServicio servicio = CargaServicio.getInstance();
		Integer total = servicio.contarBusqCargas(parametros);
		map.put("total", total);
		map.put("pagina", pagina);
		if (total > 0) {
			List<Carga> res = servicio.buscarCargasCliente(parametros, pagina);

			map.put("cargas", res);
		}
		
		map.put("parametros", parametros);

		return map;
	}

	public Map<String, Object> buscarCargas(String estados, String fecha_final, String fecha_inicial, Integer id_formato, Integer id_negocio, Integer tipo_carga, Integer id_cliente, Integer ordenarPor, Integer pagina, String fecha_final_lib, String fecha_inicial_lib, Integer id_carga ) {



		if (id_usuario == null && id_administrativo == null && id_administrador == null) {
			return null;
		}

		ParametrosBusqCargas parametros = new ParametrosBusqCargas();

		parametros.setEstados(estados);
		Date fecha = null;
		if (StringUtils.esNoVacio(fecha_final)) {
			fecha = StringUtils.toDate(fecha_final);

			fecha.setTime(fecha.getTime() + (1000 * 60 * 60 * 24));

			parametros.setFecha_final(fecha);
		}

		if (StringUtils.esNoVacio(fecha_inicial)) {
			fecha = StringUtils.toDate(fecha_inicial);
			parametros.setFecha_inicial(fecha);
		}

		parametros.setId_formato(id_formato);
		parametros.setId_negocio(id_negocio);
		parametros.setId_tipocarga(tipo_carga);
		parametros.setId_usuario(id_cliente);
		parametros.setOrdenarPor(ordenarPor);

		parametros.setId_usuarioSesion(id_usuario);
		parametros.setId_persona(id_persona);
		parametros.setId_administrativo(id_administrativo);
		parametros.setId_administrador(id_administrador);
		
		if (StringUtils.esNoVacio(fecha_final_lib)) {
			fecha = StringUtils.toDate(fecha_final_lib);

			fecha.setTime(fecha.getTime() + (1000 * 60 * 60 * 24));

			parametros.setFecha_final_lib(fecha);
		}

		if (StringUtils.esNoVacio(fecha_inicial_lib)) {
			fecha = StringUtils.toDate(fecha_inicial_lib);
			parametros.setFecha_inicial_lib(fecha);
		}
		
		parametros.setId_carga(id_carga);

		Map<String, Object> map = new HashMap<String, Object>();

		CargaServicio servicio = CargaServicio.getInstance();
		Integer total = servicio.contarBusqCargas(parametros);
		map.put("total", total);
		if (total > 0) {
			List<Carga> res = servicio.buscarCargasCliente(parametros, pagina);

			map.put("cargas", res);
		}
		
		session.setAttribute("parametros_busqueda_cargas", parametros);
		session.setAttribute("pagina_busqueda_cargas", pagina);

		return map;

	}
	

	public Integer obtenerFormatoSalidaCarga(Integer id_carga) {
		return CargaServicio.getInstance().obtenerFormatoSalida(id_carga, id_administrativo);
	}

	public List<CargaInstancia> obtenerHistorialCargaInstancia(Integer id_carga) {
		return CargaInstanciaServicio.getInstance().obtenerHistorialCargaInstancia(id_carga);
	}

	public List<HistorialCarga> obtenerHistorialCarga(Integer id_carga) {
		return HistorialServicio.getInstance().obtenerHistorialCarga(id_carga);
	}

	public List<TipoCampo> obtenerTipoCampos() {
		return TipoCampoServicio.getInstance().obtenerTipoCampos();
	}

	public List<Map<String, Object>> obtenerDatosPrecarga(Integer id_formato, Integer id_formato_campo_precarga, String[] listaIdCamposLlave, String[] listaValoresLlave) {

		FormatoServicio formatoServicio = FormatoServicio.getInstance();

		Formato formato = formatoServicio.obtenerFormato(id_formato);
		FormatoCampo fcampo = formatoServicio.obtenerFormatoCampo(id_formato);
		List<Map<String, Object>> datos = null;

		List<TipoCampo> tiposCampos = TipoCampoServicio.getInstance().obtenerTipoCampos();

		// Se crea el mapa de tipos
		HashMap<Integer, TipoCampo> mapTipos = new HashMap<Integer, TipoCampo>();
		for (TipoCampo tipoCampo : tiposCampos) {
			mapTipos.put(tipoCampo.getId_tipocampo(), tipoCampo);

		}

		if (fcampo != null) {

			List<FormatoCampo> lista = buscarFormatoCampoPrecarga(null, fcampo, id_formato_campo_precarga);

			if (lista != null && !lista.get(1).equals(fcampo) && lista.get(1).getPrecarga().equals(Constantes.SI)) {
				Integer id_estructura = 0;
				if (lista.get(1).getId_estructura() == null) {
					id_estructura = formato.getId_estructura();
				} else {
					id_estructura = lista.get(1).getId_estructura();
				}

				String sqlCondUsu = null;
				if (StringUtils.esVerdad(lista.get(1).getPrecarga())) {
					sqlCondUsu = lista.get(1).getCondicion_precarga();
					sqlCondUsu = "(" + sqlCondUsu + ")";

					if (listaIdCamposLlave != null && listaIdCamposLlave.length > 0) {
						for (int i = 0; i < listaIdCamposLlave.length; i++) {

							Campo campo = CampoServicio.getInstance().obtenerCampo(Integer.parseInt(listaIdCamposLlave[i]));

							TipoCampo tipoCampo = mapTipos.get(campo.getId_tipocampo());

							if (tipoCampo.getTipo_dato().equals("Integer")) {
								sqlCondUsu = sqlCondUsu + " " + " and " + "C" + listaIdCamposLlave[i] + "= $I(" + listaValoresLlave[i] + ")$ ";

							} else if (tipoCampo.getTipo_dato().equals("String")) {
								sqlCondUsu = sqlCondUsu + " " + " and " + "C" + listaIdCamposLlave[i] + "= $S(" + listaValoresLlave[i] + ")$ ";

							} else if (tipoCampo.getTipo_dato().equals("Double")) {
								sqlCondUsu = sqlCondUsu + " " + " and " + "C" + listaIdCamposLlave[i] + "= $D(" + listaValoresLlave[i] + ")$ ";

							} else if (tipoCampo.getTipo_dato().equals("Boolean")) {
								sqlCondUsu = sqlCondUsu + " " + " and " + "C" + listaIdCamposLlave[i] + "= $B(" + listaValoresLlave[i] + ")$ ";

							} else if (tipoCampo.getTipo_dato().equals("Date")) {
								sqlCondUsu = sqlCondUsu + " " + " and " + "C" + listaIdCamposLlave[i] + "= $T(" + listaValoresLlave[i] + ")$ ";

							} else if (tipoCampo.getTipo_dato().equals("Object")) {
								sqlCondUsu = sqlCondUsu + " " + " and " + "C" + listaIdCamposLlave[i] + "= " + listaValoresLlave[i] + " ";
							}

						}

					}

				}

				SimpleLogger.setInfo("consulta precarga: " + sqlCondUsu);
				datos = CargaServicio.getInstance().obtenerDatosCargaEstructura(id_estructura, lista.get(1), lista.get(0), false, null, formato, sqlCondUsu, this.session);
			}
		}

		return datos;

	}

	/*
	 * Esta funcion retorna una lista donde el primer valor el formato campo padre y el segundo es el formato campo de precarga
	 */

	private List<FormatoCampo> buscarFormatoCampoPrecarga(FormatoCampo formatoCampoPadre, FormatoCampo formatoCampoHijo, Integer id_formato_campo_precarga) {

		if (formatoCampoHijo.getId_formato_campo().equals(id_formato_campo_precarga)) {
			ArrayList<FormatoCampo> lista = new ArrayList<FormatoCampo>();
			lista.add(formatoCampoPadre);
			lista.add(formatoCampoHijo);

			return lista;
		} else {

			List<FormatoCampo> formatoCamposNieto = formatoCampoHijo.getFormato_campo_list();

			List<FormatoCampo> lista = null;

			if (formatoCamposNieto != null & formatoCamposNieto.size() > 0) {
				Boolean formatoCampoEncontrado = false;

				for (FormatoCampo formatoCampoNieto : formatoCamposNieto) {

					if (formatoCampoEncontrado == false) {

						lista = buscarFormatoCampoPrecarga(formatoCampoHijo, formatoCampoNieto, id_formato_campo_precarga);
						if (lista != null) {
							formatoCampoEncontrado = true;
						}
					}

				}

			} else {
				lista = null;
			}

			return lista;
		}

	}

	public List<VariableLiberacion> obtenerVariablesLiberacionPorCarga(Integer id_carga) {

		return VariableLiberacionServicio.getInstance().obtenerVariablesLiberacionPorCarga(id_carga);

	}

	public Boolean tieneDocumentoFirmadoPorFasePreparacion(Integer id_carga) {

		return FirmaServicio.getInstance().tieneDocumentoFirmadoPorFasePreparacion(id_carga);

	}

	public Boolean tieneFormularioFirmadoPorFaseLiberacion(Integer id_carga) {

		return FirmaServicio.getInstance().tieneFormularioFirmadoPorFaseLiberacion(id_carga);

	}

	public Boolean tieneFormularioFirmadoPorFasePreparacion(Integer id_carga) {

		return FirmaServicio.getInstance().tieneFormularioFirmadoPorFasePreparacion(id_carga);

	}

	public Mensaje obtenerMensajeCarga() {
		Mensaje mensaje = (Mensaje) session.getAttribute("mensaje_carga_lotes");
		return mensaje;
	}

	public Integer contarRegistros(Integer id_formato) {

		Formato formato = FormatoServicio.getInstance().obtenerFormato(id_formato);

		return CargaServicio.getInstance().contarRegistros(formato.getId_estructura(), Integer.parseInt(session.getAttribute("var.id_carga_simple_detalle") + ""));

	}

	public static Map<String, GenerarArchivoExcel> avanceMap = new HashMap<String, GenerarArchivoExcel>();

	public String generarArchivoExcel(Integer id_carga, Integer id_formato) {

		String idFile = "F" + System.currentTimeMillis() + StringUtils.randomString(10);

		GenerarArchivoExcel gen = new GenerarArchivoExcel(id_carga, id_formato);

		avanceMap.put(idFile, gen);

		new Thread(gen).start();

		return idFile;

	}

	public Long verificarAvanceDetalleExcel(String idFile) {

		GenerarArchivoExcel gen = avanceMap.get(idFile);

		if (gen == null) {
			return null;
		} else {
			return gen.getPorcentaje();
		}

	}

	public static File getFileExcel(String idFile) {
		GenerarArchivoExcel gen = avanceMap.get(idFile);

		if (gen == null) {
			return null;
		} else {
			return gen.getFile();
		}

	}
	
	public Map<String,Object> obtenerDatosBandejaEntrada (Integer id_formato, Integer id_carga) {
		return CargaServicio.getInstance().obtenerDatosBandejaEntrada(id_formato, id_carga);
	}
	
	public Carga obtenerCarga (Integer id_carga) {
		return CargaServicio.getInstance().obtenerCarga(id_carga);
	}
	
	public Map<String,Object> obtenerDatosCargaPorFormato (Integer id_carga, Integer id_formatosalida, List<ValorFiltro> valoresFiltro, Integer numero_pagina) {
		return CargaServicio.getInstance().obtenerDatosCargaPorFormato(id_carga, id_formatosalida, valoresFiltro, numero_pagina);
	}

}

class GenerarArchivoExcel implements Runnable {

	private static double registrosPorsegundo = 7d;

	private long initTime = 0;
	private long finalTime = 0;
	private Integer id_carga;
	private Integer id_formato;
	private boolean finalizado = false;
	private File file = null;

	public GenerarArchivoExcel(Integer id_carga, Integer id_formato) {
		this.id_carga = id_carga;
		this.id_formato = id_formato;
	}

	public void run() {
		//TODO Ajustar implementación, sacar del hilo, manejar exitoso true o false
		try {
			
			initTime = System.currentTimeMillis();
			
			Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
			
			double num = carga.getNumero_registros_bigdecimal();
			
			finalTime = (long) ((num * 1000d) / registrosPorsegundo);
			
			file = GeneradorArchivoCargaServicio.getInstance().crearArchivo(id_carga, id_formato);
			
			double time = num / (double) ((System.currentTimeMillis() - initTime) / 1000l);
			
			registrosPorsegundo = (registrosPorsegundo * 4 + time) / 5;
			
			SimpleLogger.setInfo("Excel - Registros por Segundo: " + registrosPorsegundo);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		finalizado = true;
	}

	public Long getPorcentaje() {

		if (finalizado) {
			return 100l;
		} else {
			long timeExe = System.currentTimeMillis() - initTime;

			if (timeExe < finalTime) {
				return (100 * timeExe) / finalTime;
			} else {
				return 99l;
			}
		}
	}

	public File getFile() {
		return file;
	}

}
