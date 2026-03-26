package com.osmosyscol.datasuite.reportedim.servicio;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.SQLUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datapi.constantes.VariablesAplicacion;
import com.osmosyscol.datapi.logica.dto.Conexion;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.datasuite.reportedim.dto.Parametro;
import com.osmosyscol.datasuite.reportedim.dto.ParametroReporte;
import com.osmosyscol.datasuite.reportedim.dto.Resultado;
import com.osmosyscol.datasuite.reportedim.dto.ResultadoConsulta;
import com.osmosyscol.datasuite.reportedim.dto.ServicioReporteDim;

public class RDServicio {
	private static RDServicio rdServicio;

	public static Map<String, Integer> ESTRUCTURAS = new HashMap<String, Integer>();
	private static Map<String, Integer> listaValores = new HashMap<String, Integer>();
	private static Map<String, Integer> campos = new HashMap<String, Integer>();

	public static RDServicio getInstance() {
		if (rdServicio == null)
			rdServicio = new RDServicio();
		return rdServicio;
	}

	private RDServicio() {
	};

	// -------------------------------------------------

	public ResultadoConsulta ejecutarServicio(ServicioReporteDim srd, Map<String, Object> parametros) {
		return ejecutarServicio(srd, parametros, 1);
	}

	// -------------------------------------------------

	public ResultadoConsulta ejecutarServicio(ServicioReporteDim srd, Map<String, Object> parametros, Integer numero_pagina) {
		return ejecutarServicio(srd, parametros, numero_pagina, srd.getPaginacion());
	}

	public ResultadoConsulta ejecutarServicio(ServicioReporteDim srd, Map<String, Object> parametros, Integer numero_pagina, Integer cantidad_pagina) {

		verificarData();

		ResultadoConsulta resultadoConsulta = new ResultadoConsulta();
		resultadoConsulta.setCantidad_pagina(cantidad_pagina);

		List<Map<String, Object>> maparsado = new ArrayList<Map<String, Object>>();

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Integer total_registros = 0;

		try {

			Conexion conexion = new Conexion();
			conexion.setNombre(srd.getNombre_conexion());

			connection = VariablesAplicacion.getInstance().getConnection(conexion);
			if (connection == null) {
				SimpleLogger.setError("No se puede realizar la consulta. No se puede realizar la conexion a la base de datos.");
				return null;
			}

			connection.setAutoCommit(false);

			String sql = srd.getConsulta();

			// Remplaza los parametros en la consulta
			if (srd.getParametros() != null) {
				for (ParametroReporte parametro : srd.getParametros()) {
					if (parametros != null) {

						String tipo = parametro.getTipo().toLowerCase();
						Object valor = parametros.get(parametro.getNombre());

						if (StringUtils.esVerdad(parametro.getEncriptado())) {
							if (valor != null && valor.toString().length() > 0) {
								valor = Crypto.E(valor);
							}
							tipo = "string";
						}

						if (valor != null && "string".equals(tipo)) {
							valor = "'" + SQLUtils.escapeSql(valor) + "'";
						}

						// Si el valor es fecha y no esta encriptado
						if (valor != null && "date".equals(tipo) && !StringUtils.esVerdad(parametro.getEncriptado())) {
							valor = StringUtils.toString(valor);
						}

						if (valor == null) {
							valor = "null";
						}

						sql = sql.replace("#" + parametro.getNombre() + "#", valor.toString());
					}
				}
			}

			// Remplaza las estructuras y campos

			sql = reemplazarNombres(sql);

			// -----------------

			SimpleLogger.setDebug("SQL --> " + sql);

			if (sql.toLowerCase().trim().indexOf("update") == 0 || sql.toLowerCase().trim().indexOf("insert") == 0) {

				Integer actualizados = 0;
				String nombrecampo = "rownums";

				if (srd.getResultados() != null && srd.getResultados().size() > 0) {
					nombrecampo = srd.getResultados().get(0).getNombre();
				}

				try {

					PreparedStatement ps2 = connection.prepareStatement(sql);
					actualizados = ps2.executeUpdate();
					ps2.close();

				} catch (Exception e) {
					SimpleLogger.setError("Ha ocurido un error al ejecutar la sentencia sql (update/insert).", e);
				}

				sql = "select " + actualizados + " as " + nombrecampo + " from dual";

				SimpleLogger.setDebug("SQL --> " + sql);

			}

			// consulta cuantos elementos retorna
			try {

				PreparedStatement ps3 = connection.prepareStatement("select COUNT(*) as CANTIDAD FROM (" + sql + ") CONTADOR");
				ResultSet rs3 = null;

				try {
					rs3 = ps3.executeQuery();
				} catch (Exception e) {
					SimpleLogger.setError(sql, e);
					throw new RuntimeException("Error en la consulta.");
				}

				if (rs3.next()) {
					total_registros = Integer.parseInt(rs3.getString("CANTIDAD"));
				}

				ps3.close();

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurido un error al consultar el total de registros.", e);
			}

			// ----------------------------------

			ps = connection.prepareStatement(sql);

			try {
				rs = ps.executeQuery();
			} catch (Exception e) {
				SimpleLogger.setError(sql, e);
				throw new RuntimeException("Error en la consulta.");
			}

			ResultSetMetaData rsmd = rs.getMetaData();
			List<String> columnsnames = new ArrayList<String>();
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				columnsnames.add(rsmd.getColumnName(i + 1).toUpperCase());
			}

			Integer numero_desde = (numero_pagina - 1) * resultadoConsulta.getCantidad_pagina();
			Integer cantidad_marcados = 0;
			Integer numero_registro = 0;

			while (cantidad_marcados < resultadoConsulta.getCantidad_pagina() && rs.next()) {

				if (numero_registro >= numero_desde) {
					Map<String, Object> mr = new HashMap<String, Object>();

					for (int i = 0; i < srd.getResultados().size(); i++) {
						Resultado resultado = (Resultado) srd.getResultados().get(i);

						Object dato = rs.getString(resultado.getNombre().toUpperCase());

						if (dato != null && !StringUtils.esVerdad(resultado.getEncriptado())) {
							if (Parametro.TIPO_INT.equalsIgnoreCase(resultado.getTipo())) {
								try {
									dato = new Integer(dato.toString());
								} catch (Exception e) {
									SimpleLogger.setError("Parse Error Integer", e);
								}
							}

							if (Parametro.TIPO_DOUBLE.equalsIgnoreCase(resultado.getTipo()) || Parametro.TIPO_FLOAT.equalsIgnoreCase(resultado.getTipo())) {
								try {
									dato = new BigDecimal(dato.toString());
								} catch (Exception e) {
									SimpleLogger.setError("Parse Error BigDecimal", e);
								}
							}

							if (Parametro.TIPO_DATE.equalsIgnoreCase(resultado.getTipo())) {
								try {
									dato = rs.getDate(resultado.getNombre().toUpperCase());
								} catch (Exception e) {
									SimpleLogger.setError("Parse Error Date", e);
								}
							}
						}

						if (StringUtils.esVerdad(resultado.getEncriptado())) {
							dato = Crypto.DF(dato);
						}

						if (Parametro.TIPO_MONEY.equalsIgnoreCase(resultado.getTipo())) {
							try {
								NumberFormat formatter = new DecimalFormat("##,##0.00");
								dato = "$" + formatter.format(new BigDecimal(dato.toString())); // -001235
							} catch (Exception e) {
								SimpleLogger.setError("Parse Error Money", e);
							}
						}

						mr.put(resultado.getNombre(), dato);
					}

					maparsado.add(mr);

					cantidad_marcados++;
				}

				numero_registro++;
			}

			rs.close();
			ps.close();

			connection.commit();
		} catch (Exception e) {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
			}

			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e2) {
			}

			SimpleLogger.setError("Ha ocurrido un error al realizar la consulta.", e);
			return null;

		} finally {
			if (connection != null) {
				try {
					connection.rollback();
					connection.setAutoCommit(true);
					connection.close();
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}
			}
		}

		resultadoConsulta.setDatos(maparsado);
		resultadoConsulta.setTotal_registros(total_registros);

		return resultadoConsulta;

	}

	public static String quitarComillas(String str) {

		if (str == null) {
			return "";
		}

		String s = str.trim();

		if (s.length() > 1 && s.charAt(0) == '\'' && s.charAt(s.length() - 1) == '\'') {
			s = s.substring(1, s.length() - 1);
			return s;
		}

		return str;
	}

	public static String reemplazarNombres(String sql) {

		if (sql == null) {
			return null;
		}

		verificarData();

		int i = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Calendar today = Calendar.getInstance();

		try {
			today.setTime(sdf.parse(sdf.format(today.getTime())));
		} catch (ParseException e1) {
		}

		Calendar yesterday = Calendar.getInstance();

		try {
			yesterday.setTime(sdf.parse(sdf.format(yesterday.getTime())));
			yesterday.add(Calendar.HOUR_OF_DAY, -24);
		} catch (ParseException e1) {
		}

		Calendar tomorrow = Calendar.getInstance();

		try {
			tomorrow.setTime(sdf.parse(sdf.format(tomorrow.getTime())));
			tomorrow.add(Calendar.HOUR_OF_DAY, 24);
		} catch (ParseException e1) {
		}

		sql = sql.replace("$I(null)$", "null").replace("$I()$", "null");
		sql = sql.replace("$S(null)$", "null").replace("$S()$", "null");
		sql = sql.replace("$T(null)$", "null").replace("$T()$", "null");
		sql = sql.replace("$D(null)$", "null").replace("$D()$", "null");
		sql = sql.replace("$B(null)$", "null").replace("$B()$", "null");
		sql = sql.replace("$T(SYSDATE)$", "'" + Crypto.E(new Date()) + "'");
		sql = sql.replace("$T(sysdate)$", "'" + Crypto.E(new Date()) + "'");

		sql = sql.replace("$T(TODAY)$", "'" + Crypto.E(today.getTime()) + "'");
		sql = sql.replace("$T(today)$", "'" + Crypto.E(today.getTime()) + "'");

		sql = sql.replace("$T(YESTERDAY)$", "'" + Crypto.E(yesterday.getTime()) + "'");
		sql = sql.replace("$T(yesterday)$", "'" + Crypto.E(yesterday.getTime()) + "'");

		sql = sql.replace("$T(TOMORROW)$", "'" + Crypto.E(tomorrow.getTime()) + "'");
		sql = sql.replace("$T(tomorrow)$", "'" + Crypto.E(tomorrow.getTime()) + "'");

		// -- remplazar entero
		while ((i = sql.indexOf("$I(")) >= 0) {
			String r = quitarComillas(sql.substring(i + 3, sql.indexOf(")$", i + 3)));
			try {
				String c = Crypto.E(new Long(Crypto.DF(r) + ""));
				sql = sql.replace("$I(" + r + ")$", "'" + c + "'");
				sql = sql.replace("$I('" + r + "')$", "'" + c + "'");
			} catch (Exception e) {
				sql = sql.replace("$I(" + r + ")$", "null");
				sql = sql.replace("$I('" + r + "')$", "null");
			}
		}

		// -- remplazar texto
		while ((i = sql.indexOf("$S(")) >= 0) {
			String r = quitarComillas(sql.substring(i + 3, sql.indexOf(")$", i + 3)));
			try {
				String c = Crypto.E(Crypto.DF(r) + "");
				sql = sql.replace("$S(" + r + ")$", "'" + c + "'");
				sql = sql.replace("$S('" + r + "')$", "'" + c + "'");
			} catch (Exception e) {
				sql = sql.replace("$S(" + r + ")$", "null");
				sql = sql.replace("$S('" + r + "')$", "null");
			}
		}

		// -- remplazar fecha
		while ((i = sql.indexOf("$T(")) >= 0) {
			String r = quitarComillas(sql.substring(i + 3, sql.indexOf(")$", i + 3)));
			try {
				String c = Crypto.E(StringUtils.toDate(r));
				sql = sql.replace("$T(" + r + ")$", "'" + c + "'");
				sql = sql.replace("$T('" + r + "')$", "'" + c + "'");
			} catch (Exception e) {
				sql = sql.replace("$T(" + r + ")$", "null");
				sql = sql.replace("$T('" + r + "')$", "null");
			}
		}

		// -- remplazar decimal
		while ((i = sql.indexOf("$D(")) >= 0) {
			String r = quitarComillas(sql.substring(i + 3, sql.indexOf(")$", i + 3)));
			try {
				String c = Crypto.E(new BigDecimal(Crypto.DF(r) + ""));
				sql = sql.replace("$D(" + r + ")$", "'" + c + "'");
				sql = sql.replace("$D('" + r + "')$", "'" + c + "'");
			} catch (Exception e) {
				sql = sql.replace("$D(" + r + ")$", "null");
				sql = sql.replace("$D('" + r + "')$", "null");
			}
		}

		// -- remplazar booleano
		while ((i = sql.indexOf("$B(")) >= 0) {
			String r = quitarComillas(sql.substring(i + 3, sql.indexOf(")$", i + 3)));
			try {
				String c = Crypto.E(StringUtils.esVerdad(Crypto.DF(r) + ""));
				sql = sql.replace("$B(" + r + ")$", "'" + c + "'");
				sql = sql.replace("$B('" + r + "')$", "'" + c + "'");
			} catch (Exception e) {
				sql = sql.replace("$B(" + r + ")$", "null");
			}
		}

		for (String nombreestructura : ESTRUCTURAS.keySet()) {
			sql = sql.replace("$" + nombreestructura + "$", "T" + ESTRUCTURAS.get(nombreestructura));
		}

		for (String nombrecampo : campos.keySet()) {
			sql = sql.replace("$" + nombrecampo + "$", "C" + campos.get(nombrecampo));
		}

		for (String nombrecampo : listaValores.keySet()) {
			sql = sql.replace("$" + nombrecampo + "$", "L" + listaValores.get(nombrecampo));
		}
		
		//Fix para diferenciar lista de valores. Se deja la original para retrocompatibilidad
		for (String nombrecampo : listaValores.keySet()) {
			sql = sql.replace("$LV(" + nombrecampo + ")$", "L" + listaValores.get(nombrecampo));
		}

		return sql;
	}

	public static synchronized void verificarData() {

		if (ESTRUCTURAS.isEmpty()) {
			clear();

			List<Estructura> estructuras_m = EstructuraServicio.getInstance().obtenerEstructuras();

			if (estructuras_m != null) {

				for (Estructura estructura : estructuras_m) {

					ESTRUCTURAS.put(estructura.getNombre().trim().toUpperCase(), estructura.getId_estructura());
					ESTRUCTURAS.put(estructura.getNombre().trim().toLowerCase(), estructura.getId_estructura());

					List<Campo> campos_m = CampoServicio.getInstance().obtenerCamposPorEstructura(estructura.getId_estructura());

					for (Campo campo : campos_m) {
						campos.put(estructura.getNombre().trim().toUpperCase() + "." + campo.getNombre().trim().toUpperCase(), campo.getId_campo());
						campos.put(estructura.getNombre().trim().toLowerCase() + "." + campo.getNombre().trim().toLowerCase(), campo.getId_campo());

					}
				}
			}
		}
		if (listaValores.isEmpty()) {
			listaValores.clear();
			List<ListaValores> lista = ListaValoresServicio.getInstance().obtenerListasDeValores(1);

			if (lista != null) {
				for (ListaValores listaValor : lista) {
					listaValores.put(listaValor.getNombre().trim().toUpperCase(), listaValor.getId_lista_valores());
					listaValores.put(listaValor.getNombre().trim().toLowerCase(), listaValor.getId_lista_valores());
					listaValores.put("$LV(" + listaValor.getNombre().trim().toUpperCase() + ")$", listaValor.getId_lista_valores());
					listaValores.put("$LV(" + listaValor.getNombre().trim().toLowerCase() + ")$", listaValor.getId_lista_valores());
					
				}
			}
		}
	}

	public static synchronized void clear() {

		ESTRUCTURAS.clear();
		campos.clear();
		listaValores.clear();

	}

	public static String getNombreCampo(String stridcampo) {

		if (stridcampo.matches("^[a-zA-Z][0-9]*$")) {

			Integer id_campo = Integer.parseInt(stridcampo.substring(1));

			Set<String> setcampos = campos.keySet();

			for (String nombre_campo : setcampos) {

				if (campos.get(nombre_campo).intValue() == id_campo) {
					return "(" + stridcampo + ") " + nombre_campo;
				}
			}
		}

		return stridcampo;
	}

	public static String getNombreCampoReal(String stridcampo) {

		if (stridcampo.matches("^[a-zA-Z][0-9]*$")) {

			Integer id_campo = Integer.parseInt(stridcampo.substring(1));

			Set<String> setcampos = campos.keySet();

			for (String nombre_campo : setcampos) {

				if (campos.get(nombre_campo).intValue() == id_campo) {
					return nombre_campo.toUpperCase();
				}
			}
		}

		return stridcampo;
	}

}
