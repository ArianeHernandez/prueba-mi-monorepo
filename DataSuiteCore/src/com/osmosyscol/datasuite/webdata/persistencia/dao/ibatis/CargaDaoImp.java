package com.osmosyscol.datasuite.webdata.persistencia.dao.ibatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.ElementoCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.FormatoDato;
import com.osmosyscol.datasuite.webdata.logica.dto.FormatoFiltro;
import com.osmosyscol.datasuite.webdata.logica.dto.ParametrosBusqCargas;
import com.osmosyscol.datasuite.webdata.logica.dto.TablaOperacion;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.datasuite.webdata.utils.ORASQLUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class CargaDaoImp extends BaseSqlMapDao implements CargaDao {

	public CargaDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Integer obtenerSiguiente() {
		return (Integer) queryForObject("Carga.obtenerSiguiente", null);
	}

	public void insertar(Carga carga) {
		insert("Carga.insertar", carga);
	}

	public Carga obtener(Integer id_carga) {
		return (Carga) queryForObject("Carga.obtener", id_carga);
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerListaPorEstado(Integer id_formato, Integer id_persona, String id_estado, Integer id_tipocarga) {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_formato", id_formato);
		parametros.put("id_persona", id_persona);
		parametros.put("id_estado", id_estado);
		parametros.put("id_tipocarga", id_tipocarga);

		List<Carga> listadoSubidos = (List<Carga>) queryForList("Carga.obtenerListaPorEstado", parametros);
		return listadoSubidos;

	}
	
	@SuppressWarnings("unchecked")
	public List<Carga> listarCargasDuplicadas(Integer id_carga, String valor_total, String numero_registros){
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_carga", id_carga);
		parametros.put("valor_total", valor_total);
		parametros.put("numero_registros", numero_registros);
		
		return  queryForList("Carga.listarCargasDuplicadas", parametros);
	}

	public void cambiarEstado(Integer id_carga, String id_estado) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("id_estado", id_estado);

		update("Carga.cambiarEstado", map);
	}

	public Boolean cambiarEstadoCrea(Integer id_carga, String id_estado) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("id_estado", id_estado);

		update("Carga.cambiarEstadoCrea", map);
		return true;

	}

	public Boolean insertSQL(String exesql) {

		insert("Carga.insertSQL", verifyQuery(exesql));
		return true;
	}

	public Boolean setFechaCarga(Integer id_carga, Date fecha_carga) {
		try {
			Carga carga = new Carga();
			carga.setFecha_carga(fecha_carga);
			carga.setId_carga(id_carga);
			update("Carga.setFechaCarga", carga);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// --------------------

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> obtenerDatos(String id_persona, String id_estructura, String id_campobusqueda, String valor_busqueda, Map<Integer, Campo> campos) {
		try {

			id_persona = StringEscapeUtils.escapeSql(id_persona);
			id_estructura = StringEscapeUtils.escapeSql(id_estructura);
			id_campobusqueda = StringEscapeUtils.escapeSql(id_campobusqueda);

			if (StringUtils.esVacio(valor_busqueda)) {
				valor_busqueda = "%";
			}

			StringBuffer sql = new StringBuffer();
			sql.append("select ID, (select to_char(cc.fecha, 'yyyy/mm/dd - HH24:MI:SS') from TS01 cc where cc.id_carga = t.idcarga) FECHA");
			int cid = 0;
			for (Integer id_campo : campos.keySet()) {
				sql.append(" , C" + id_campo.toString() + " as N" + cid);
				cid++;
			}

			while (cid < 50) {
				sql.append(" , null as N" + cid);
				cid++;
			}

			sql.append(" from T" + id_estructura.toString() + " t WHERE t.idcarga=0 AND t.C" + id_campobusqueda + " like '" + valor_busqueda + "'");

			SimpleLogger.setInfo("SQLCREADATOSSELECT: " + sql.toString());

			List<Map<String, Object>> resp = queryForList("Carga.selectSQL", sql.toString());
			List<Map<String, Object>> sal = new ArrayList<Map<String, Object>>();

			for (Map<String, Object> map : resp) {

				HashMap<String, Object> dd = new HashMap<String, Object>();
				sal.add(dd);
				dd.put("ID", map.get("ID"));
				dd.put("FECHA", map.get("FECHA"));
				cid = 0;
				for (Integer id_campo : campos.keySet()) {
					Object obj = map.get("N" + cid);
					if (obj != null) {
						dd.put("C" + id_campo.toString(), obj);
					}
					cid++;
				}
			}

			return sal;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	// --------------------

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> obtenerDatosCarga(Integer id_estructura, FormatoCampo fcampo, FormatoCampo fcampopadre, Boolean condicional, Integer id_carga, Formato formato, String sqlCondUsu) {

		String sqlCondicionUsuario = " and 1=1 ";

		if (!StringUtils.esVacio(sqlCondUsu)) {
			sqlCondicionUsuario = " and  " + sqlCondUsu + " ";
		}

		String sqlCondicional = "";
		String sqlCondicionalPadre = "";
		if (condicional == true && id_carga != null) {
			sqlCondicional = " and id_carga = " + id_carga;

			// Crear condiciones de la estructura
			for (FormatoCampo fcCondicion : fcampo.getFormato_campo_list()) {
				if (com.osmosyscol.datasuite.logica.constantes.Constantes.SI.equals(fcCondicion.getCondicional())) {
					sqlCondicional += " and C" + fcCondicion.getId_campo() + fcCondicion.getValor_condicion();
				}

			}
			// Crear condiciones de la estructura padre
			if (fcampopadre != null) {
				sqlCondicionalPadre = obtenerSQLPadre(fcampo, fcampopadre, formato, id_carga, false);
			}
		}

		StringBuffer sql = new StringBuffer();
		sql.append("select * ");

		sql.append(" from V" + id_estructura.toString() + " WHERE ID_CARGA = 0 " + sqlCondicionUsuario + sqlCondicional + sqlCondicionalPadre + " ORDER BY ID");

		SimpleLogger.setDebug("CargaDaoImp.obtenerDatosCarga() " + sql);

		try {
			List<Map<String, Object>> resp = desencriptarMapa(queryForList("Carga.selectSQL", sql.toString()));

			return resp;

		} catch (Exception e) {

			SimpleLogger.setError(sql + "", e);

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	private List<Map<String, Object>> desencriptarMapa(List<Map<String, Object>> resp) {

		if (resp == null || resp.isEmpty()) {
			return resp;
		}

		// desencripta
		for (Map<String, Object> registro : resp) {

			Set<String> keys = registro.keySet();
			for (String key : keys) {

				// se verifica si es un campo
				Boolean esCampo;
				try {
					esCampo = (key.toUpperCase().startsWith("C") || key.toUpperCase().startsWith("V"));
				} catch (Exception e) {
					esCampo = false;
				}

				// se verifica si el valor es texto
				if (registro.get(key) instanceof String && esCampo) {
					// System.out
					// .println("CargaDaoImp.obtenerDatosCargaMultiple() "+key);
					registro.put(key, Crypto.D(registro.get(key)));
				}
			}
		}

		return resp;
	}

	private String obtenerSQLPadre(FormatoCampo fcampo, FormatoCampo fcampopadre, Formato formato, Integer id_carga, Boolean relacional) {
		String sqlCondicionesPadre = "";
		String vista = "";

		for (FormatoCampo fc : fcampopadre.getFormato_campo_list()) {
			if (com.osmosyscol.datasuite.logica.constantes.Constantes.SI.equals(fc.getCondicional())) {
				sqlCondicionesPadre += "C" + fc.getId_campo() + fc.getValor_condicion() + " and ";
			}
		}
		Campo campo = CampoServicio.getInstance().obtenerCampo(fcampo.getId_campo());
		sqlCondicionesPadre += "id_carga=" + id_carga;
		if (fcampopadre.getId_formato_campo_padre() == null) {
			vista = formato.getId_estructura().toString();
		} else {
			vista = fcampopadre.getId_estructura().toString();
		}

		Boolean espadre = com.osmosyscol.datasuite.logica.constantes.Constantes.SI.equals(fcampo.getEstructura_padre());
		if (relacional == true) {
			sqlCondicionesPadre = " and pa.ID in (select id from V" + vista + " where " + (sqlCondicionesPadre);
		} else if (com.osmosyscol.datasuite.logica.constantes.Constantes.CAMPO_MULTIPLICIDAD_UNICO.equals(campo.getMultiplicidad()) && !espadre) {
			sqlCondicionesPadre = " and ID in (select C" + fcampo.getId_campo() + " from V" + vista + " where " + (sqlCondicionesPadre);
		} else if (espadre) {
			sqlCondicionesPadre = " and C" + fcampo.getId_campo() + " in (select ID from V" + vista + " where " + (sqlCondicionesPadre);
		} else {
			sqlCondicionesPadre = " and ID in (select b.id_v from V" + vista + " a, T" + vista + "C" + fcampo.getId_campo() + " b where a.id=b.id_p and " + (sqlCondicionesPadre);
		}

		if (fcampopadre.getId_formato_campo_padre() != null) {
			FormatoCampo fcBase = FormatoServicio.getInstance().obtenerFormatoCampo(formato.getId_formato());
			FormatoCampo fcampoPP = new FormatoCampo();
			for (FormatoCampo fcPP : fcBase.getFormato_campo_list()) {
				if (fcPP.getId_formato_campo() == fcampopadre.getId_formato_campo_padre()) {
					fcampoPP = fcPP;
				}
			}
			if (fcampoPP.getId_formato_campo() == null) {
				fcampoPP = fcBase;
			}
			sqlCondicionesPadre += obtenerSQLPadre(fcampopadre, fcampoPP, formato, id_carga, false);
		}
		sqlCondicionesPadre += ")";
		return sqlCondicionesPadre;
	}

	// ----------------------------------------
	@SuppressWarnings("unchecked")
	public List<ElementoCarga> obtenerElementosCarga(Integer id_carga, Integer numero_pagina) {

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id_carga", id_carga);

			return (List<ElementoCarga>) queryForListPag("Carga.obtenerElementosCarga", map, numero_pagina, Constantes.PAGINACIONLISTADO);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public Integer obtenerTotalElementosCarga(Integer id_carga) {

		return (Integer) queryForObject("Carga.obtenerTotalElementosCarga", id_carga);
	}

	private Integer obtenerSiguienteElementoCarga() {
		return (Integer) queryForObject("Carga.obtenerSiguienteElementoCarga", null);
	}

	public void crearElementosCarga(Integer id_elementocarga, Integer id_carga, FormatoDato formatodato) {

		if (id_elementocarga == null) {
			ElementoCarga elementocarga = new ElementoCarga();
			elementocarga.setFecha(HorarioServicio.getInstance().obtenerFechaActual());
			elementocarga.setId_carga(id_carga);
			elementocarga.setId_elementocarga(obtenerSiguienteElementoCarga());

			id_elementocarga = elementocarga.getId_elementocarga();

			insert("Carga.crearElementosCarga", elementocarga);

		} else {
			ElementoCarga elementocarga = new ElementoCarga();
			elementocarga.setFecha(HorarioServicio.getInstance().obtenerFechaActual());
			elementocarga.setId_carga(id_carga);
			elementocarga.setId_elementocarga(id_elementocarga);

			delete("Carga.eliminarFormatoDatoPorElementoCarga", id_elementocarga);

			update("Carga.actualizarElementosCarga", elementocarga);
		}

		crearFormatoDato(formatodato, id_elementocarga);

	}

	public void eliminarElementoCarga(Integer id_elementocarga) {
		delete("Carga.eliminarFormatoDatoPorElementoCarga", id_elementocarga);
		delete("Carga.eliminarElementoCarga", id_elementocarga);
	}

	private Integer obtenerSiguienteFormatoDato() {
		return (Integer) queryForObject("Carga.obtenerSiguienteFormatoDato", null);
	}

	private void crearFormatoDato(FormatoDato formatodato, Integer id_elementocarga) {

		if (formatodato == null) {
			return;
		}

		Integer id_formatodato = obtenerSiguienteFormatoDato();
		formatodato.setId_formatodato(id_formatodato);

		formatodato.setId_elementocarga(id_elementocarga);

		String tipodato = (String) queryForObject("Carga.obtenerTipoDatoPorIdFormatoCampo", formatodato.getId_formato_campo());

		if (StringUtils.esNoVacio(formatodato.getValor())) {

			if ("Boolean".equalsIgnoreCase(tipodato)) {
				formatodato.setValor(Crypto.E(StringUtils.esVerdad(formatodato.getValor())));
			}

			if ("String".equalsIgnoreCase(tipodato)) {
				formatodato.setValor(Crypto.E(formatodato.getValor()));
			}

			if ("Integer".equalsIgnoreCase(tipodato) || "Long".equalsIgnoreCase(tipodato)) {
				formatodato.setValor(Crypto.E(new Long(formatodato.getValor())));
			}

			if ("Date".equalsIgnoreCase(tipodato)) {
				formatodato.setValor(Crypto.E(StringUtils.toDate(formatodato.getValor())));
			}

			if ("Float".equalsIgnoreCase(tipodato) || "Double".equalsIgnoreCase(tipodato) || "BigDecimal".equalsIgnoreCase(tipodato)) {
				formatodato.setValor(Crypto.E(new BigDecimal(formatodato.getValor())));
			}
		} else {
			formatodato.setValor(null);
		}

		// Encripta el valor del formato dato antes de guardar

		insert("Carga.insertarFormatoDato", formatodato);

		if (formatodato.getFormatoDatoList() != null) {
			for (FormatoDato fdato : formatodato.getFormatoDatoList()) {
				fdato.setId_padre(id_formatodato);
				crearFormatoDato(fdato, id_elementocarga);
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerListaCargaPorUsuario(Integer id_persona, List<String> estados, Integer id_formato, Integer numero_pagina) {

		int t = estados.size();
		String in = "";
		int i = 0;
		for (String estado : estados) {
			i++;
			in += "'" + estado + "'";
			if (t != i) {
				in += ",";
			}
		}

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("id_persona", id_persona);
		parametros.put("in", in);
		parametros.put("id_formato", id_formato);

		List<Carga> listadoSubidos = (List<Carga>) queryForListPag("Carga.obtenerListaCargaPorUsuario", parametros, numero_pagina, Constantes.PAGINACIONLISTADO);
		return listadoSubidos;
	}

	public Integer totalCargasPorPersona(Integer id_persona, List<String> estados, Integer id_formato) {

		int t = estados.size();
		String in = "";
		int i = 0;
		for (String estado : estados) {
			i++;
			in += "'" + estado + "'";
			if (t != i) {
				in += ",";
			}
		}

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_persona", id_persona);
		parametros.put("in", in);
		parametros.put("id_formato", id_formato);

		return (Integer) queryForObject("Carga.totalCargasPorPersona", parametros);
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargaPorFormatoEntrada(Integer id_formato, Integer id_persona, List<String> estados) {

		Map<String, Object> parametros = new HashMap<String, Object>();

		if (estados != null) {
			int t = estados.size();
			String in = "";
			int i = 0;
			for (String estado : estados) {
				i++;
				in += "'" + estado + "'";
				if (t != i) {
					in += ",";
				}
			}
			parametros.put("in", in);
		}

		parametros.put("id_formato", id_formato);
		parametros.put("id_persona", id_persona);

		return queryForList("Carga.obtenerCargaPorFormatoEntrada", parametros);
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerListadoCargasPorFormatoEntrada(Integer id_formato) {
		return queryForList("Carga.obtenerListadoCargasPorFormatoEntrada", id_formato);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> obtenerDatosVistaEstructura(Integer id_carga, Integer id_estructura, Integer id_estructurapadre, Integer id_campopadre, Integer id_valorpadre, String valor_campo, List<Campo> campos, FormatoCampo formatoCampo, Boolean multiple, List<String> condiciones, Integer numero_pagina, Boolean esOracle) {

		valor_campo = StringUtils.getStringCorchetes(valor_campo);

		boolean espadre = com.osmosyscol.datasuite.logica.constantes.Constantes.SI.equalsIgnoreCase(formatoCampo.getEstructura_padre());

		StringBuffer sql_sel_1 = new StringBuffer();
		StringBuffer sql_sel_2 = new StringBuffer();

		sql_sel_1.append("select ID,ESTADO ");

		int cid = 0;

		for (Campo campo : campos) {
			Integer id_campo = campo.getId_campo();

			if (com.osmosyscol.datasuite.logica.constantes.Constantes.CAMPO_MULTIPLICIDAD_UNICO.equalsIgnoreCase(campo.getMultiplicidad())) {
				if (campo.getId_lista_valores() != null || campo.getId_estructurarelacionada() != null) {
					sql_sel_1.append(" , '[' || C" + id_campo.toString() + " || '] ' || V" + id_campo.toString() + " as N" + cid);
				} else {
					sql_sel_1.append(" , C" + id_campo.toString() + " as N" + cid);
				}
				cid++;
			}
		}

		while (cid < 50) {
			sql_sel_1.append(" , null as N" + cid);
			cid++;
		}

		sql_sel_1.append(" from V" + id_estructura.toString() + " WHERE ");
		sql_sel_2.append("select 1 from T" + id_estructura.toString() + " WHERE ");

		StringBuffer sql = new StringBuffer();

		if (StringUtils.esNoVacio(valor_campo) && !multiple) {
			sql.append(" ID = " + valor_campo);
			sql_sel_2.append(" ID = " + valor_campo);
		} else {
			sql.append(" ID_CARGA = " + id_carga.toString());
			sql_sel_2.append(" IDCARGA = " + id_carga.toString());
		}

		if (id_valorpadre != null && multiple && !espadre) {
			sql.append(" and ID in (select ID_V from T" + id_estructurapadre + "C" + id_campopadre + " where ID_P = '" + id_valorpadre + "' ) ");
		}

		if (id_valorpadre != null && multiple && espadre) {
			sql.append(" and C" + id_campopadre + " = '" + id_valorpadre + "'");
		}

		if (condiciones != null && condiciones.size() > 0) {
			for (String condicion : condiciones) {
				sql.append(" and (" + condicion + ")");
				sql_sel_2.append(" and (" + condicion + ")");
			}
		}

		sql.append(" order by ID ");

		try {
			String sqlquery;
			if (numero_pagina == null) {
				sqlquery = sql_sel_1.toString() + sql.toString();
			} else if (esOracle) {
				sqlquery = ORASQLUtils.addPaginacion(sql_sel_1.toString() + sql.toString(), numero_pagina, Constantes.PAGINACIONLISTADO);
			} else {
				Integer inicio = ((numero_pagina - 1) * Constantes.PAGINACIONLISTADO);
				sqlquery = sql_sel_1.toString() + sql.toString() + " limit " + Constantes.PAGINACIONLISTADO + " offset " + inicio;
			}

			// SQL para totalizar registros
			String sqlquery2 = sql_sel_2.toString();

			// TODO Se debe mejorar la paginación

			SimpleLogger.setDebug(sqlquery);

			List<Map<String, Object>> resp = null;
			List<Map<String, Object>> sal = new ArrayList<Map<String, Object>>();

			if (numero_pagina != null) {
				Map<String, Object> mapTotal = new HashMap<String, Object>();
				Integer total = (Integer) queryForObject("Carga.totalSelectSQL", sqlquery2);
				resp = queryForList("Carga.selectSQL", sqlquery);
				mapTotal.put("TOTAL_REGISTROS", total);
				sal.add(mapTotal);
			} else {
				resp = queryForList("Carga.selectSQL", sqlquery);
			}
			for (Map<String, Object> map : resp) {

				HashMap<String, Object> dd = new HashMap<String, Object>();
				sal.add(dd);
				dd.put("ID", map.get("ID"));
				dd.put("ESTADO", map.get("ESTADO"));
				cid = 0;

				for (Campo campo : campos) {
					Integer id_campo = campo.getId_campo();

					if (com.osmosyscol.datasuite.logica.constantes.Constantes.CAMPO_MULTIPLICIDAD_UNICO.equalsIgnoreCase(campo.getMultiplicidad())) {
						Object obj = map.get("N" + cid);
						if (obj != null) {
							dd.put("C" + id_campo, obj);
						} else {
							dd.put("C" + id_campo, "");
						}
						cid++;
					}
				}
			}

			return sal;

		} catch (Exception e) {

			SimpleLogger.setError(sql + "", e);

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public void guardarTablaOperacion(Integer id_carga, TablaOperacion tablaOperacion) {

		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("id_carga", id_carga);
		parametros.put("id_campo", tablaOperacion.getCampo().getId_campo());
		parametros.put("id_operacion", tablaOperacion.getOperacion().getId_operacion());
		parametros.put("valor", tablaOperacion.getResultado());

		insert("Carga.guardarTablaOperacion", parametros);

	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> obtenerDatosCargaMultiple(Integer id_estructuraPadre, FormatoCampo fcampohijo, FormatoCampo fcampopadre, Integer id_campoRel, Formato formato, Integer id_carga, Boolean condicional) {

		StringBuffer sql = new StringBuffer();
		String condiciones = "";
		if (condicional == true) {
			condiciones = obtenerSQLPadre(fcampohijo, fcampopadre, formato, id_carga, true);
		}

		sql.append("select pa.visualizacion as vis_p , ( select count(1) + 1 from T" + id_estructuraPadre + " d where d.idcarga = pa.id_carga and d.id < pa.id ) as pos_p, v.visualizacion as vis_v, ( select count(1) + 1 from T" + fcampohijo.getId_estructura() + " d where d.idcarga = v.id_carga and d.id < v.id ) as pos_v ");
		
		sql.append(" from V" + id_estructuraPadre + " pa, V" + fcampohijo.getId_estructura() + " v, T" + id_estructuraPadre + "C" + id_campoRel + " rel" + " WHERE rel.id_p = pa.id and rel.id_v = v.id and pa.id_carga=v.id_carga and v.id_carga=rel.idcarga " + "and rel.idcarga = 0" + condiciones);

		try {
			List<Map<String, Object>> resp = desencriptarMapa(queryForList("Carga.selectSQL", sql.toString()));

			return resp;

		} catch (Exception e) {

			SimpleLogger.setError(sql + "", e);

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public FormatoDato obtenerFormatoDatoPorIdElementoCarga(Integer id_elementocarga) {

		FormatoDato formatoDato = (FormatoDato) queryForObject("Carga.obtenerPrimerFormatoDato", id_elementocarga);
		formatoDato.setFormatoDatoList(obtenerFormatoDatosPorIdPadre(formatoDato.getId_formatodato()));

		return formatoDato;
	}

	@SuppressWarnings("unchecked")
	private List<FormatoDato> obtenerFormatoDatosPorIdPadre(Integer id_formatodato) {

		List<FormatoDato> formatoDatoList = queryForList("Carga.obtenerFormatoDatosPorIdPadre", id_formatodato);

		if (formatoDatoList != null && formatoDatoList.size() == 0) {
			formatoDatoList = null;
		}

		if (formatoDatoList != null) {
			for (FormatoDato formatoDato : formatoDatoList) {
				formatoDato.setFormatoDatoList(obtenerFormatoDatosPorIdPadre(formatoDato.getId_formatodato()));
			}
		}

		return formatoDatoList;
	}

	public Integer obtenerSiguienteCreadatos() {
		return (Integer) queryForObject("Carga.obtenerSiguienteCreadatos", null);
	}

	public void removerCarga(Integer idCarga) {
		delete("Carga.removerCarga", idCarga);
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargas(Integer idUsuario, String estados, Integer idRevision, Integer idProceso, Integer pagina) {
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("id_usuario", idUsuario);
		parametros.put("estados", estados);
		parametros.put("id_revision", idRevision);
		parametros.put("id_proceso", idProceso);

		return queryForListPag("Carga.obtenerCargas", parametros, pagina, Constantes.PAGINACIONLISTADO);
	}
	
	@SuppressWarnings("unchecked")
	public Carga obtenerCargasBorradorUltimo(Integer idUsuario, String estados) {
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("id_usuario", idUsuario);
		parametros.put("estados", estados);

		return (Carga) queryForObject("Carga.obtenerCargasBorradorUltimo", parametros);
	}

	public Integer obtenerSiguienteRevision(Integer idCarga) {

		return (Integer) queryForObject("Carga.obtenerSiguienteRevision", idCarga);
	}

	public Boolean actualizarEstadoCarga(Carga carga) {
		update("Carga.actualizarEstadoCarga", carga);
		return true;
	}

	public Integer contarCargas(Integer idUsuario, String estados, Integer idRevision, Integer idProceso) {
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("id_usuario", idUsuario);
		parametros.put("estados", estados);
		parametros.put("id_revision", idRevision);
		parametros.put("id_proceso", idProceso);
		return (Integer) queryForObject("Carga.contarCargas", parametros);
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargasLiberacion(String cargaEstadoEnvio, Date fecha) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("estado", cargaEstadoEnvio);
		params.put("fecha_actual", fecha);
		
		return queryForList("Carga.obtenerCargasLiberacion", params);
	}

	public Boolean updateSQL(String exesql) {
		update("Carga.updateSQL", verifyQuery(exesql));
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Object> obtenerValoresRelacionMultiple(Integer id_estructura, Integer id_campo, Long id_valor) {

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("tabla", ("t" + id_estructura + "c" + id_campo));
		data.put("id_valor", id_valor);

		return queryForList("Carga.obtenerValoresRelacionMultiple", data);
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargasRelacionadasPorCliente(Integer id_cliente, Integer id_formato, Integer diasvigencia) {

		if (diasvigencia == null || diasvigencia == 0) {
			diasvigencia = 100000;
		}

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_cliente", id_cliente);
		parametros.put("id_formato", id_formato);
		parametros.put("diasvigencia", diasvigencia);

		List<Carga> listadoCargas = (List<Carga>) queryForList("Carga.obtenerCargasRelacionadasPorCliente", parametros);

		return listadoCargas;
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargasPorProcesoAdmin(Integer id_proceso_admin, Integer id_administrativo, String parametro_ordenamiento, String tipo_ordenamiento, Map<String, Object> filtros, Integer numero_pagina) {

		Map<String, Object> parametros = null;
		if (filtros != null && !filtros.isEmpty()) {
			filtros.put("id_proceso_admin", id_proceso_admin);
			filtros.put("id_administrativo", id_administrativo);
			filtros.put("parametro_ordenamiento", parametro_ordenamiento);
			filtros.put("tipo_ordenamiento", tipo_ordenamiento);
			parametros = filtros;
		} else {
			parametros = new HashMap<String, Object>();
			parametros.put("id_proceso_admin", id_proceso_admin);
			parametros.put("id_administrativo", id_administrativo);
			parametros.put("parametro_ordenamiento", parametro_ordenamiento);
			parametros.put("tipo_ordenamiento", tipo_ordenamiento);
		}
		
		if (numero_pagina == null) {
			return queryForList("Carga.obtenerCargasPorProcesoAdmin", parametros);
		} else {
			return queryForListPag("Carga.obtenerCargasPorProcesoAdmin", parametros, numero_pagina, Constantes.PAGINACIONLISTADO);			
		}
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargasSubidasSinInstancia() {
		return (List<Carga>) queryForList("Carga.obtenerCargasSubidasSinInstancia", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargasSubidasSinInstanciaPorTipoProceso(Integer id_tipo_proceso){
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("id_tipo_proceso", id_tipo_proceso);
		
		return (List<Carga>) queryForList("Carga.obtenerCargasSubidasSinInstanciaPorTipoProceso", parametros);
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargasPorEstados(String estados, Integer numPagina) {
		Map<String, Object> parametros = new HashMap<String, Object>();

		parametros.put("estados", estados);

		if (numPagina != null) {
			queryForListPag("Carga.obtenerCargas", parametros, numPagina, Constantes.PAGINACIONLISTADO);
		}

		return queryForList("Carga.obtenerCargas", parametros);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> obtenerIDsRegistrosPorCarga(Integer idCarga, Integer id_estructura) {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("tabla", id_estructura);
		parametros.put("id_carga", idCarga);

		return queryForList("Carga.obtenerIDsRegistrosPorCarga", parametros);
	}

	public Boolean actualizarNegocio(Integer id_carga, Integer id_negocio) {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_carga", id_carga);
		parametros.put("id_negocio", id_negocio);

		Integer cant = update("Carga.actualizarNegocio", parametros);

		return cant > 0;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectSql(String sql) {
		return StringUtils.ListMapToUpperCase((List<Map<String, Object>>) queryForList("Carga.selectSQL", sql));
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> simpleselectSql(String sql) {
		return StringUtils.MapToUpperCase((Map<String, Object>) queryForObject("Carga.selectSQL", sql));
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargasPorInstanciaParaAdminHijo(Integer id_proceso_admin, Integer id_instancia, Integer id_administrativo_padre, Integer id_administrativo_hijo) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_proceso_admin", id_proceso_admin);
		map.put("id_instancia", id_instancia);
		map.put("id_administrativo_padre", id_administrativo_padre);
		map.put("id_administrativo_hijo", id_administrativo_hijo);

		return queryForList("Carga.obtenerCargasPorInstanciaParaAdminHijo", map);
	}

	public void eliminarCargasInteractivasSinEnviar(Integer id_usuario) {
		update("Carga.eliminarCargasInteractivasSinEnviar", id_usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Carga> buscarCargas(ParametrosBusqCargas parametros, Integer pagina) {
		if (pagina == null) {
			return queryForList("Carga.buscarCargas", parametros);
		} else {
			return queryForListPag("Carga.buscarCargas", parametros, pagina, Constantes.PAGINACIONLISTADO);
		}
	}

	public Integer contarBusqCargas(ParametrosBusqCargas parametros) {
		return (Integer) queryForObject("Carga.contarBusqCargas", parametros);
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargasQueRequierenAdjuntosPorPersona(Integer id_persona) {
		return queryForList("Carga.obtenerCargasQueRequierenAdjuntosPorPersona", id_persona);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> obtenerDatosCargaParaCampoTotalizador(Integer id_carga, Integer id_campo, Integer id_estructura) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("id_campo_totalizador", id_campo);
		map.put("id_estructura", id_estructura);

		try {
			List<Map<String, Object>> resp = desencriptarMapa(queryForList("Carga.obtenerDatosCargaParaCampoTotalizador", map));

			return resp;

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public Boolean actualizarValorTotalCarga(Integer id_carga, String vtotal, String nregistros) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_carga", id_carga);
		map.put("vtotal", vtotal);
		map.put("nregistros", nregistros);

		update("Carga.actualizarValorTotalCarga", map);
		return true;

	}

	public Boolean actualizarFechaLiberacion(Carga carga) {
		update("Carga.actualizarFechaLiberacion", carga);
		return true;

	}
	
	public Boolean actualizarFechaLiberacionCreadatos(Carga carga) {
		update("Carga.actualizarFechaLiberacionCreadatos", carga);
		return true;

	}

	public Boolean eliminarSQL(String sql) {
		delete("Carga.eliminarSQL", sql);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargasSimilaresCliente(Integer id_carga, Integer id_formato_salida, Integer id_usuario) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("id_formato_salida", id_formato_salida);
		map.put("id_usuario", id_usuario);

		return queryForList("Carga.obtenerCargasSimilaresCliente", map);

	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargasSimilaresLiberadas(Integer id_carga, Integer id_formato_salida, Integer id_usuario) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("id_formato_salida", id_formato_salida);
		map.put("id_usuario", id_usuario);

		return queryForList("Carga.obtenerCargasSimilaresLiberadas", map);
	}

	public Integer obtenerTotalRegistros(Integer id_carga, Integer id_estructura) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("id_estructura", id_estructura);

		return (Integer) queryForObject("Carga.obtenerTotalRegistros", map);
	}

	public void cambiarTipocarga(Integer id_carga, int tipocarga) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("tipocarga", tipocarga);

		update("Carga.cambiarTipocarga", map);

	}

	public Integer obtenerIdFormatoSalidaCliente(Integer id_carga) {
		return (Integer) queryForObject("Carga.obtenerIdFormatoSalidaCliente", id_carga);
	}

	public Integer obtenerIdFormatoSalidaAdmin(Integer id_carga, Integer id_administrativo) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("id_administrativo", id_administrativo);

		return (Integer) queryForObject("Carga.obtenerIdFormatoSalidaAdmin", map);
	}

	@SuppressWarnings("unchecked")
	public List<FormatoFiltro> obtenerFormatosFiltro(Integer id_formato) {
		return queryForList("Carga.obtenerFormatosFiltro", id_formato);
	}

	@SuppressWarnings("unchecked")
	public List<Carga> obtenerCargasSinFinalizar() {
		return queryForList("Carga.obtenerCargasSinFinalizar", null);
	}

	@Override
	public Date consultarCambioEstado(Integer id_carga, String estado) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("estado", estado);
		
		return (Date) queryForObject("Carga.consultarCambioEstado", map);
	}

	@Override
	public void marcarNotificacion(Integer id_carga) {
		update("Carga.marcarNotificacion", id_carga);		
	}

	@Override
	public void cambiarMotivo(Integer id_carga, String motivo) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_carga", id_carga);
		map.put("motivo", StringUtils.trimToEmpty(motivo));

		update("Carga.cambiarMotivo", map);
		
	}

}
