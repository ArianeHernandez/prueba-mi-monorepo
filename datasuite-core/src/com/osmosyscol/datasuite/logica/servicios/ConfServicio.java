package com.osmosyscol.datasuite.logica.servicios;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ConfServicio {

	private static ConfServicio confServicio;

	public static ConfServicio getInstance() {
		if (confServicio == null)
			confServicio = new ConfServicio();
		return confServicio;
	}

	private Boolean crearFormatoConf(Formato formato) {
		return crearFormatoConf(formato, true) && crearFormatoConf(formato, false);
	}

	// -----------------------------------------------------------
	// Crear .conf de los formatos de salida

	public Boolean crearFormatoConf(Formato formato, Boolean alias) {
		try {

			String DATASUITE = System.getenv(ParametrosInicio.SYSVAR);

			if (DATASUITE == null || DATASUITE.equals("")) {
				SimpleLogger.setError("Se debe configurar la Variable de entorno DATASUITE.");
			}

			String nombrecarpeta = DATASUITE + "/servicios";
			new File(nombrecarpeta).mkdirs();
			String nombrearchivo = "";
			if (alias) {
				nombrearchivo = nombrecarpeta + "/" + StringUtils.toFileName("F" + formato.getId_formato()) + ".conf";
			} else {
				nombrearchivo = nombrecarpeta + "/" + StringUtils.toFileName("F" + formato.getId_formato() + "_IDs") + ".conf";
			}
			SimpleLogger.setInfo("Creando Archivo: " + nombrearchivo);
			// crear archivo conf
			String texto = "# " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date()) + "\n";
			texto += "# --------------------------------------------------------\n";

			if (alias) {
				texto += "nombre     " + StringUtils.toFileName("F" + formato.getId_formato()) + "\n";
			} else {
				texto += "nombre     " + StringUtils.toFileName("F" + formato.getId_formato() + "_IDs") + "\n";
			}

			texto += concatenarConexion(2);

			texto += concatenarOperaciones(formato, alias);

			FileUtils.setContentFile(nombrearchivo, texto);
			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	// ------------------------------

	private static String concatenarConexion(Integer idConexion) {

		String texto = "";
		texto += "\n# --------------------------------------------------------\n";
		texto += "conexion.nombre           CREADATOS \n";

		return texto;
	}

	// ------------------------------

	private static String concatenarOperaciones(Formato formato, Boolean alias) {

		FormatoCampo fc = FormatoServicio.getInstance().obtenerFormatoCampo(formato.getId_formato());

		String texto = "";
		Integer numoperacion = 1;
		String nameoperacion = "operaciones:" + numoperacion;
		String nombreOperacion = StringUtils.toFileName(fc.getTitulo());
		String sql = "select ID, ";
		String subC = "";
		int i = 0;

		List<FormatoCampo> listFC = fc.getFormato_campo_list();
		List<String> parametrosSalida = new ArrayList<String>();
		parametrosSalida.add("ID");
		for (FormatoCampo formatoCampo : listFC) {

			Campo campo = CampoServicio.getInstance().obtenerCampo(formatoCampo.getId_campo());
			
			if(campo != null){
				
				// parametros de salida
				if (alias) {
					if (formatoCampo.getSeleccion_campo() != null && formatoCampo.getSeleccion_campo().equals(Constantes.SI) && campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO)) {
						// dar formato para campos tipo fecha
						parametrosSalida.add(formatoCampo.getTitulo());
						sql += "C" + formatoCampo.getId_campo() + " as " + StringUtils.toFileName(formatoCampo.getTitulo()) + ", ";
					}
					if (formatoCampo.getSeleccion_visualizacion() != null && formatoCampo.getSeleccion_visualizacion().equals(Constantes.SI) && campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO)) {
						parametrosSalida.add("des " + formatoCampo.getTitulo());
						sql += "V" + formatoCampo.getId_campo() + " as " + StringUtils.toFileName("des " + formatoCampo.getTitulo()) + ", ";
					}
				} else {
					if (formatoCampo.getSeleccion_campo() != null && formatoCampo.getSeleccion_campo().equals(Constantes.SI) && campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO)) {
						parametrosSalida.add("C" + formatoCampo.getId_campo().toString());
						// dar formato para campos tipo fecha
						sql += "C" + formatoCampo.getId_campo() + ", ";
					}
					if (formatoCampo.getSeleccion_visualizacion() != null && formatoCampo.getSeleccion_visualizacion().equals(Constantes.SI) && campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO)) {
						parametrosSalida.add("V" + formatoCampo.getId_campo());
						sql += "V" + formatoCampo.getId_campo() + ", ";
					}
				}
				// subconsultas
				if (formatoCampo.getId_estructura() != null) {
					subC += generacionSubSql(alias, nameoperacion + ".consulta.subconsulta:" + i, formatoCampo, campo);
					i++;
				}
			}
		}
		sql = sql.substring(0, sql.length() - 2);
		sql += " from V" + formato.getId_estructura();
		sql += " where id_carga= #id_carga#";

		texto += "\n# --------------------------------------------------------\n";
		texto += nameoperacion + ".nombre             " + nombreOperacion + "\n";
		texto += nameoperacion + ".consulta.sql       " + sql + "\n";
		texto += subC;

		// ----------------- parametros de entrada

		texto += "\n";
		texto += "operaciones:1.parametros:1.nombre       id_carga \n";
		texto += "operaciones:1.parametros:1.tipo         int \n";

		// ----------------- parametros de salida
		int j;
		for (j = 0; j < parametrosSalida.size(); j++) {
			texto += "\n";
			texto += nameoperacion + ".resultados:" + j + ".nombre        " + StringUtils.toFileName(parametrosSalida.get(j)) + "\n";
			texto += nameoperacion + ".resultados:" + j + ".tipo          " + "string" + "\n";
		}

		// ----------------- parametros de salida

		for (FormatoCampo fcP : listFC) {
			if (fcP.getId_estructura() != null) {
				texto += obtenerSubPar(fcP, alias, nameoperacion + ".resultados:" + j);
				j++;
			}
		}

		return texto;
	}

	private static String generacionSubSql(Boolean alias, String nameoperacion, FormatoCampo formatoCampo, Campo campo) {
		String subC1 = "";
		if (alias) {
			subC1 += "\n" + nameoperacion + ".nombre     listado_" + StringUtils.toFileName(formatoCampo.getTitulo()) + "\n";
		} else {
			subC1 += "\n" + nameoperacion + ".nombre     listado_C" + formatoCampo.getId_campo() + "\n";
		}

		subC1 += nameoperacion + ".sql        " + crearSubSql(formatoCampo, campo, alias) + "\n";

		if (formatoCampo.getFormato_campo_list().size() > 0) {
			int i = 0;
			for (FormatoCampo fcHijo : formatoCampo.getFormato_campo_list()) {
				if (fcHijo.getId_estructura() != null) {
					Campo campoH = CampoServicio.getInstance().obtenerCampo(fcHijo.getId_campo());
					nameoperacion += ".subconsulta:" + i;
					subC1 += generacionSubSql(alias, nameoperacion, fcHijo, campoH);
					i++;
				}
			}
		}
		return subC1;
	}

	// ------------------------------

	private static String crearSubSql(FormatoCampo fc, Campo campo, Boolean alias) {

		String sql = "select ID, ";
		List<FormatoCampo> listFC = fc.getFormato_campo_list();
		for (FormatoCampo formatoCampo : listFC) {
			// parametros de salida
			if (alias) {
				if (formatoCampo.getSeleccion_campo() != null && formatoCampo.getSeleccion_campo().equals(Constantes.SI)) {
					sql += "C" + formatoCampo.getId_campo() + " as " + StringUtils.toFileName(formatoCampo.getTitulo()) + ", ";
				}
				if (formatoCampo.getSeleccion_visualizacion() != null && formatoCampo.getSeleccion_visualizacion().equals(Constantes.SI)) {
					sql += "V" + formatoCampo.getId_campo() + " as " + StringUtils.toFileName("des " + formatoCampo.getTitulo()) + ", ";
				}
			} else {
				if (formatoCampo.getSeleccion_campo() != null && formatoCampo.getSeleccion_campo().equals(Constantes.SI)) {
					sql += "C" + formatoCampo.getId_campo() + ", ";
				}
				if (formatoCampo.getSeleccion_visualizacion() != null && formatoCampo.getSeleccion_visualizacion().equals(Constantes.SI)) {
					sql += "V" + formatoCampo.getId_campo() + ", ";
				}
			}
		}
		if (campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO)) {
			sql = sql.substring(0, sql.length() - 2);
			sql += " from V" + fc.getId_estructura();
			sql += " where id_carga= #id_carga#";

			if (Constantes.SI.equals(fc.getEstructura_padre())) {
				sql += " and C" + fc.getId_campo() + "=#ID#";
			} else {
				if (alias) {
					sql += " and ID=#" + StringUtils.toFileName(fc.getTitulo()).toUpperCase() + "#";
				} else {
					sql += " and ID=#" + StringUtils.toFileName("C" + fc.getId_campo()).toUpperCase() + "#";
				}
			}

		} else if (campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_MULTIPLE)) {
			sql = sql.substring(0, sql.length() - 2);
			sql += " from V" + fc.getId_estructura();
			sql += " where id_carga= #id_carga#";
			sql += " and ID in (select ID_V from " + "T" + campo.getId_estructura() + "C" + campo.getId_campo() + " where idcarga=#id_carga# and ID_P = #ID#)";
		}
		return sql;
	}

	// ------------------------------

	private static String obtenerSubPar(FormatoCampo fc, Boolean alias, String nameoperacion) {
		String subp = "";
		if (alias) {
			subp = "\n" + nameoperacion + ".nombre        listado_" + StringUtils.toFileName(fc.getTitulo()) + "\n";
		} else {
			subp = "\n" + nameoperacion + ".nombre        listado_" + StringUtils.toFileName("C" + fc.getId_campo()) + "\n";
		}
		subp += nameoperacion + ".tipo          subquery \n";
		int i = 0;

		subp += "\n" + nameoperacion + ".elementos:" + i + ".nombre        ID\n";
		subp += nameoperacion + ".elementos:" + i + ".tipo          string \n";
		i++;

		for (FormatoCampo fcH : fc.getFormato_campo_list()) {
			if (fcH.getFormato_campo_list().size() > 0) {
				subp += obtenerSubPar(fcH, alias, nameoperacion + ".elementos:" + i);
				i++;
			} else {
				if (alias) {

					if (fcH.getSeleccion_campo() != null && fcH.getSeleccion_campo().equals(Constantes.SI)) {
						subp += "\n" + nameoperacion + ".elementos:" + i + ".nombre        " + StringUtils.toFileName(fcH.getTitulo()) + "\n";
						subp += nameoperacion + ".elementos:" + i + ".tipo          string \n";
						i++;
					}

					if (fcH.getSeleccion_visualizacion() != null && fcH.getSeleccion_visualizacion().equals(Constantes.SI)) {
						subp += "\n" + nameoperacion + ".elementos:" + i + ".nombre        " + StringUtils.toFileName("des_" + fcH.getTitulo()) + "\n";
						subp += nameoperacion + ".elementos:" + i + ".tipo          string \n";
						i++;
					}

				} else {
					if (fcH.getSeleccion_campo() != null && fcH.getSeleccion_campo().equals(Constantes.SI)) {
						subp += "\n" + nameoperacion + ".elementos:" + i + ".nombre        " + StringUtils.toFileName("C" + fcH.getId_campo()) + "\n";
						subp += nameoperacion + ".elementos:" + i + ".tipo          string \n";
						i++;
					}
					if (fcH.getSeleccion_visualizacion() != null && fcH.getSeleccion_visualizacion().equals(Constantes.SI)) {
						subp += "\n" + nameoperacion + ".elementos:" + i + ".nombre        " + StringUtils.toFileName("V" + fcH.getId_campo()) + "\n";
						subp += nameoperacion + ".elementos:" + i + ".tipo          string \n";
						i++;
					}
				}

			}
		}
		return subp;
	}

	// -----------------------------------------------------------
	// Crear .conf para obtener registro por transaccion

	private Boolean crearFormatoConf_Reg(Formato formato) {
		try {

			String DATASUITE = System.getenv(ParametrosInicio.SYSVAR);

			if (DATASUITE == null || DATASUITE.equals("")) {
				SimpleLogger.setError("Se debe configurar la Variable de entorno DATASUITE.");
			}

			String nombrecarpeta = DATASUITE + "/servicios";
			new File(nombrecarpeta).mkdirs();
			String nombrearchivo = nombrecarpeta + "/" + StringUtils.toFileName(formato.getNombre()) + "_reg.conf";
			SimpleLogger.setInfo("Creando Archivo: " + nombrearchivo);
			// crear archivo conf
			String texto = "# " + DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(new Date()) + "\n";
			texto += "# --------------------------------------------------------\n";

			texto += "nombre     " + StringUtils.toFileName(formato.getNombre() + "_reg") + "\n";

			texto += concatenarConexion(2);

			texto += concatenarOperaciones_reg(formato);

			FileUtils.setContentFile(nombrearchivo, texto);
			return true;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	// ------------------------------

	private static String concatenarOperaciones_reg(Formato formato) {

		FormatoCampo fc = FormatoServicio.getInstance().obtenerFormatoCampo(formato.getId_formato());

		String texto = "";
		Integer numoperacion = 1;
		String nameoperacion = "operaciones:" + numoperacion;
		String nombreOperacion = StringUtils.toFileName(fc.getTitulo());
		String sql = "select ID, ";
		String filtroName = "";

		List<FormatoCampo> listFC = fc.getFormato_campo_list();
		List<String> parametrosSalida = new ArrayList<String>();
		List<String> parametrosEntrada = new ArrayList<String>();
		for (FormatoCampo formatoCampo : listFC) {

			if(formatoCampo.getId_campo() != null){
				
				Campo campo = CampoServicio.getInstance().obtenerCampo(formatoCampo.getId_campo());
				// parametros de salida
				if (formatoCampo.getSeleccion_campo() != null && formatoCampo.getSeleccion_campo().equals(Constantes.SI) && campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO)) {
					parametrosSalida.add(formatoCampo.getTitulo());
					sql += "C" + formatoCampo.getId_campo() + " as " + StringUtils.toFileName(formatoCampo.getTitulo()) + ", ";
				}
				if (formatoCampo.getSeleccion_visualizacion() != null && formatoCampo.getSeleccion_visualizacion().equals(Constantes.SI) && campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO)) {
					parametrosSalida.add("des " + formatoCampo.getTitulo());
					sql += "V" + formatoCampo.getId_campo() + " as " + StringUtils.toFileName("des " + formatoCampo.getTitulo()) + ", ";
				}
				// Obtener identificador para filtro
				if (campo.getLlaveprimaria().equals(Constantes.SI)) {
					filtroName = StringUtils.toFileName(campo.getNombre());
					parametrosEntrada.add(campo.getNombre());
				}
			}
		}
		sql = sql.substring(0, sql.length() - 2);
		sql += " from V" + formato.getId_estructura();
		sql += " where id_carga= #id_carga# and ID =#" + filtroName + "#";

		texto += "\n# --------------------------------------------------------\n";
		texto += nameoperacion + ".nombre             " + nombreOperacion + "\n";
		texto += nameoperacion + ".consulta.sql       " + sql + "\n";

		// ----------------- parametros de entrada

		texto += "\n";
		texto += "operaciones:1.parametros:0.nombre       id_carga \n";
		texto += "operaciones:1.parametros:0.tipo         int \n";

		for (int i = 1; i < parametrosEntrada.size() + 1; i++) {
			texto += "\n";
			texto += nameoperacion + ".parametros:" + i + ".nombre        " + StringUtils.toFileName(parametrosEntrada.get(i - 1)) + "\n";
			texto += nameoperacion + ".parametros:" + i + ".tipo          " + "string" + "\n";
		}

		// ----------------- parametros de salida
		for (int j = 0; j < parametrosSalida.size(); j++) {
			texto += "\n";
			texto += nameoperacion + ".resultados:" + j + ".nombre        " + StringUtils.toFileName(parametrosSalida.get(j)) + "\n";
			texto += nameoperacion + ".resultados:" + j + ".tipo          " + "string" + "\n";
		}
		return texto;
	}

}
