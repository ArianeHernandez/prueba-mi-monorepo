package com.osmosyscol.datapi.logica.servicios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csvreader.CsvReader;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datapi.constantes.VariablesAplicacion;
import com.osmosyscol.datapi.csv.Filtro;
import com.osmosyscol.datapi.csv.Operador;
import com.osmosyscol.datapi.csv.OperadorIgual;
import com.osmosyscol.datapi.logica.dto.ConsultaArchivo;
import com.osmosyscol.datapi.logica.dto.Operacion;

public class LectorArchivosPlanos {
	
	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(VariablesAplicacion.class);
	// #endregion 01 -------------------------------------
	

	private static LectorArchivosPlanos instance;

	private static final String CONSULTA_SELECT = "SELECT";

	private static final String CONSULTA_WHERE = "WHERE";
	
	private static final String CONSULTA_TODOS = "*";

	private static final String COMILLAS = "\"";

	private static final String SEPARADOR_FILTROS = ",";

	static {
		Operador operador = new OperadorIgual();
		Filtro.PRIMER_OPERADOR = operador;
	}
	
	private LectorArchivosPlanos() {
	}

	public static LectorArchivosPlanos getInstance() {
		if (instance == null) {
			instance = new LectorArchivosPlanos();
		}
		return instance;
	}

	public List<Map<String, Object>> ejecutarConsulta(Operacion operacion, Map<String, Object> parametros) {

		ConsultaArchivo consultaArchivo = operacion.getConsultaArchivo();
		List<Map<String, Object>> resultados = new ArrayList<Map<String, Object>>();

		try {
			CsvReader reader = null;

			String longitudes = consultaArchivo.getLongitudes();
			if (StringUtils.esNoVacio(longitudes)) {
				String textoArchivo = convToCsv(consultaArchivo);
				reader = CsvReader.parse(textoArchivo);
			} else {
				reader = new CsvReader(consultaArchivo.getArchivo());
			}

			if (StringUtils.esNoVacio(consultaArchivo.getSeparador())) {
				String separador = org.apache.commons.lang.StringUtils.replace(consultaArchivo.getSeparador(), COMILLAS, "");
				reader.setDelimiter(separador.charAt(0));
			}
			
			//Lectura y definición de encabezados

			Map<String, String> mapCamposConsulta = convCamposConsulta(consultaArchivo);

			Boolean todos = mapCamposConsulta.get(CONSULTA_TODOS) != null;
			
			String[] headers = null;
			if (consultaArchivo.getEncabezado()) {
				if (!reader.readHeaders()) {
					logger.error("No se ha leido el encabezado");
				} else if(!todos){
					headers = reader.getHeaders();
					for (int i = 0; i < headers.length; i++) {
						String header = mapCamposConsulta.get(headers[i].toLowerCase());
						if (header != null) {
							headers[i] = headers[i].toLowerCase();
						}
					}
				}else {
					mapCamposConsulta.clear();
					headers = reader.getHeaders();
					
					for (int i = 0; i < headers.length; i++) {
						
						mapCamposConsulta.put(headers[i].toLowerCase(), headers[i]);
						headers[i] = headers[i].toLowerCase();
					}
				}
			} else {
				Integer columnas = mapCamposConsulta.size();

				Set<String> keySet = mapCamposConsulta.keySet();
				for (String key : keySet) {
					if (org.apache.commons.lang.StringUtils.isNumeric(key)) {
						Integer tmp = new Integer(key);
						if (tmp > columnas) {
							columnas = tmp;
						}
					}
				}
				headers = new String[columnas];

				for (int i = 0; i < headers.length; i++) {
					String llave = (i + 1) + "";
					headers[i] = llave;
				}

			}
			if (headers != null) {
				reader.setHeaders(headers);
			}

			//Filtros
			
			Map<String, List<Filtro>> filtros = convFiltros(consultaArchivo, parametros);
			
			while (reader.readRecord()) {

				Map<String, Object> map = new HashMap<String, Object>();
				
				Set<String> keySet = mapCamposConsulta.keySet();
				
				Boolean valido = true;
				for (String key : keySet) {

					String dato = reader.get(key);
					
					if (filtros != null  && filtros.containsKey(key)) {
						
						List<Filtro> filtrosCampo = filtros.get(key);
						
						for (Filtro filtro : filtrosCampo) {
							valido = valido && filtro.ejecutar(dato);
						}
						
					}
					
					map.put(mapCamposConsulta.get(key), dato);

				}

				if (valido) {
					resultados.add(map);
				}
			}

			return resultados;
		} catch (FileNotFoundException e) {
			logger.error("No se encontro el archivo ", e);
		} catch (IOException e) {
			logger.error("Error en lectura de archivo ", e);
		}
		catch (Exception e) {
			logger.error("Error ejecuntado consulta", e);
		}
		return null;
	}

	private String convToCsv(ConsultaArchivo consultaArchivo) {

		List<String> lineas = FileUtils.getContentFileList(consultaArchivo.getArchivo());

		String longs[] = org.apache.commons.lang.StringUtils.split(consultaArchivo.getLongitudes());

		Integer longitudes[] = new Integer[longs.length];

		for (int i = 0; i < longitudes.length; i++) {
			if (org.apache.commons.lang.StringUtils.isNumeric(longs[i])) {
				longitudes[i] = new Integer(longs[i]);
			} else {
				throw new NumberFormatException(longs[i] + "no es un número");
			}
		}

		StringBuffer textoCsv = new StringBuffer();
		for (String linea : lineas) {
			
			int posicion = 0;
			
			for (int i = 0; i < longitudes.length; i++) {
				String texto = "";

				if (posicion + longitudes[i] <= linea.length()) {
					texto = linea.substring(posicion, posicion + longitudes[i]);
				}

				textoCsv.append(COMILLAS + texto + COMILLAS);

				if (i < longitudes.length - 1) {
					textoCsv.append(consultaArchivo.getSeparador());
				}
				posicion = posicion + longitudes[i];
			}
			textoCsv.append("\n");

		}

		return textoCsv.toString();

	}

	public Map<String, String> convCamposConsulta(ConsultaArchivo consultaArchivo) {
		String consulta = consultaArchivo.getConsulta();

		if (consulta.toUpperCase().startsWith(CONSULTA_SELECT)) {

			Integer finConsulta = consulta.length();

			if (consulta.toUpperCase().contains(CONSULTA_WHERE)) {
				finConsulta = consulta.toUpperCase().indexOf(CONSULTA_WHERE);
			}
			consulta = consulta.substring(CONSULTA_SELECT.length(), finConsulta).trim();

			StringTokenizer tokenizer = new StringTokenizer(consulta, ",");

			Map<String, String> resultadosConsulta = new HashMap<String, String>();

			while (tokenizer.hasMoreTokens()) {

				String token = tokenizer.nextToken().trim();

				String[] registro = token.split(" ");

				if (registro != null && registro.length == 1) {
					resultadosConsulta.put(registro[0].toLowerCase(), registro[0]);
				} else {
					resultadosConsulta.put(registro[0].toLowerCase(), registro[1]);
				}
			}

			return resultadosConsulta;
		}

		return null;
	}

	public Map<String, List<Filtro>> convFiltros(ConsultaArchivo consultaArchivo, Map<String, Object> parametros) {

		String consultaFiltros = consultaArchivo.getConsulta();

		Integer inicioFiltos = consultaFiltros.length();

		if (consultaFiltros.toUpperCase().contains(CONSULTA_WHERE)) {
			inicioFiltos = consultaFiltros.toUpperCase().indexOf(CONSULTA_WHERE) + CONSULTA_WHERE.length();
		}

		consultaFiltros = consultaFiltros.substring(inicioFiltos);

		if (StringUtils.esNoVacio(consultaFiltros)) {

			String filtros[] = org.apache.commons.lang.StringUtils.split(consultaFiltros, SEPARADOR_FILTROS);

			Map<String, List<Filtro>> mapFiltros = new HashMap<String, List<Filtro>>();

			String operadores = org.apache.commons.lang.StringUtils.join(Filtro.OPERADORES);

			for (String string : filtros) {

				String[] filtroLec = org.apache.commons.lang.StringUtils.split(string, operadores);

				if (filtroLec != null && filtroLec.length == 2) {
					Filtro filtro = new Filtro(filtroLec[0], filtroLec[1], parametros);
					
					
					if (filtro.calcOperador(string)) {
						
						List<Filtro> filtrosCampo = null;
						
						if (mapFiltros.containsKey(filtro.getCampo())) {
							filtrosCampo = mapFiltros.get(filtro.getCampo());
						}else {
							filtrosCampo = new ArrayList<Filtro>();
						}
						
						filtrosCampo.add(filtro);
						mapFiltros.put(filtro.getCampo(), filtrosCampo);
					}
				}
			}
			return mapFiltros;

		}
		return null;

	}

	
}
