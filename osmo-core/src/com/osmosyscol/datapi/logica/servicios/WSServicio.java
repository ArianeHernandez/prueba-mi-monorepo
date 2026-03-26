package com.osmosyscol.datapi.logica.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.cocoon.environment.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.XMLFormat;
import com.osmosyscol.commons.utils.XmlUtils;
import com.osmosyscol.datapi.constantes.VariablesAplicacion;
import com.osmosyscol.datapi.logica.dto.Elemento;
import com.osmosyscol.datapi.logica.dto.Operacion;
import com.osmosyscol.datapi.logica.dto.Parametro;
import com.osmosyscol.datapi.logica.dto.Resultado;
import com.osmosyscol.datapi.logica.dto.ServicioDataPi;
import com.osmosyscol.datapi.persistencia.dao.ibatis.core.ManejadorConsultas;

public class WSServicio {

	// #region 01 logger declaration ---------------------
	private static final Logger logger = LoggerFactory.getLogger(WSServicio.class);
	// #endregion 01 -------------------------------------

	private static WSServicio wsServicio;

	private List<IFiltroConsultaSql> listFiltrosSql = null;

	private XPathFactory xPathFactory = XPathFactory.newInstance();
	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

	public static WSServicio getInstance() {
		if (wsServicio == null)
			wsServicio = new WSServicio();
		return wsServicio;
	}

	private WSServicio() {
		try {
			documentBuilderFactory.setNamespaceAware(true);
		} catch (Exception e) {
			logger.error("error iniciando servicio WSServicio", e);
		}
	}

	// -------------------------------------------------

	@SuppressWarnings("unchecked")
	public Map<String, Object> ejecutarServicio(String nombreServicio, Request request) {

		try {

			ServicioDataPi servicioDataPi = VariablesAplicacion.getInstance().getListadoServicios().get(nombreServicio);
			String xml = (String) request.getAttribute("OSM_REQUEST_INPUTSTREAM");

			int p = xml.indexOf("<?");
			if (p >= 0) {
				xml = xml.substring(p);
			}

			int q = xml.lastIndexOf(">") + 1;

			if (q > 1) {
				xml = xml.substring(0, q);
			}

			if (logger.isDebugEnabled()) {
				logger.debug(String.format(" REQUEST [nombreServicio=%s]\n\n", nombreServicio));
			}

			if (logger.isTraceEnabled()) {
				logger.trace("XML-REQUEST:" + XMLFormat.format(xml));
			}

			if (SimpleLogger.isDebug()) {
				System.out.println("DATAPI: " + String.format(" REQUEST [nombreServicio=%s]\n\n", nombreServicio));
				System.out.println("XML-REQUEST:\n" + XMLFormat.format(xml));
			}

			// ---------------------------------------------------------------
			// IDENTIFICA OPERACION

			DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(com.osmosyscol.commons.utils.StringUtils.toInputStream(xml)));

			Operacion operacion = null;
			{
				List<Operacion> operaciones = servicioDataPi.getOperaciones();
				for (Operacion boper : operaciones) {
					String pathResult = null;

					if (!numericExpressionEq(pathResult, 0)) { // soap 1.1
						pathResult = evaluatePath(nombreServicio, "count(//SOAP-ENV:Body/dpi:entrada" + boper.getNombre() + ")", document);
					}
					if (!numericExpressionGt(pathResult, 0)) { // soap 1.2
						pathResult = evaluatePath(nombreServicio, "count(//env:Body/dpi:entrada" + boper.getNombre() + ")", document);
					}
					if (numericExpressionGt(pathResult, 0)) {
						operacion = boper;
						break;
					}

				}

				if (operacion == null) {
					logger.warn("No se puede identificar la operacion a ejecutar.");
				} else {
					logger.debug("Operacion a ejecutar: " + operacion);
				}
			}

			// ---------------------------------------------------------------
			// OBTIENE LOS PARAMETROS

			Map<String, Object> parametros = new HashMap<String, Object>();
			List<Parametro> listaparametros = operacion.getParametros();

			if (listaparametros != null) {
				for (Parametro parametro : listaparametros) {
					String data = null;
					String baseoperacion01 = null;
					String baseoperacion02 = null;

					baseoperacion01 = "//SOAP-ENV:Body/dpi:entrada" + operacion.getNombre();
					// baseoperacion02 = "//SOAP-ENV:Body/*[name()='entrada" +
					// operacion.getNombre()+ "']";
					baseoperacion02 = "//env:Body/dpi:entrada" + operacion.getNombre();

					String pathdata = null;

					{
						pathdata = baseoperacion01 + "/dpi:elementoEntrada/dpi:" + parametro.getNombre();
						data = evaluatePath(nombreServicio, pathdata, document, parametro.getTipo());

						if (data == null) { // soap 1.2
							pathdata = baseoperacion02 + "/dpi:elementoEntrada/dpi:" + parametro.getNombre();
							data = evaluatePath(nombreServicio, pathdata, document, parametro.getTipo());
						}

						if (data == null) {
							pathdata = baseoperacion01 + "/dpi:elementoEntrada/" + parametro.getNombre();
							data = evaluatePath(nombreServicio, pathdata, document, parametro.getTipo());
						}

						if (data == null) {
							pathdata = baseoperacion01 + "/dpi:elementoEntrada/" + parametro.getNombre();
							data = evaluatePath(nombreServicio, pathdata, document, parametro.getTipo());
						}

						if (data == null) {
							pathdata = baseoperacion01 + "/elementoEntrada/dpi:" + parametro.getNombre();
							data = evaluatePath(nombreServicio, pathdata, document, parametro.getTipo());
						}

						if (data == null) {
							pathdata = baseoperacion01 + "/elementoEntrada/" + parametro.getNombre();
							data = evaluatePath(nombreServicio, pathdata, document, parametro.getTipo());
						}
					}

					Object valorparametro = null;

					if (data != null && data.trim().length() > 0) {

						data = data.trim();

						if (Parametro.TIPO_STRING.equalsIgnoreCase(parametro.getTipo())) {
							valorparametro = data;
						}

						else if (Parametro.TIPO_DATE.equalsIgnoreCase(parametro.getTipo())) {
							valorparametro = data;
						}

						else if (Parametro.TIPO_DATETIME.equalsIgnoreCase(parametro.getTipo())) {
							valorparametro = data;
						}

						else if (Parametro.TIPO_INT.equalsIgnoreCase(parametro.getTipo())) {
							valorparametro = new BigDecimal(toNumber(data));
						}

						else if (Parametro.TIPO_LONG.equalsIgnoreCase(parametro.getTipo())) {
							valorparametro = new BigDecimal(toNumber(data));
						}

						else if (Parametro.TIPO_BOOLEAN.equalsIgnoreCase(parametro.getTipo())) {
							valorparametro = StringUtils.esVerdad(data);
						}

						else if (Parametro.TIPO_FLOAT.equalsIgnoreCase(parametro.getTipo())) {
							valorparametro = new Float(toNumber(data));
						}

						else if (Parametro.TIPO_DOUBLE.equalsIgnoreCase(parametro.getTipo())) {
							valorparametro = new BigDecimal(toNumber(data));
						}

						else if (Parametro.TIPO_CLOB.equalsIgnoreCase(parametro.getTipo())) {
							valorparametro = data.toString();
						}

						else if (Parametro.TIPO_OBJECT.equalsIgnoreCase(parametro.getTipo())) {
							// TODO: Falta soporte a tipos RECORD.
							valorparametro = null;
						}

						else if (Parametro.TIPO_ARRAY.equalsIgnoreCase(parametro.getTipo())) {
							List<Map<String, String>> listadatos = new ArrayList<Map<String, String>>();

							String pathElementos = pathdata + "/dpi:" + parametro.getNombre() + "Elemento";
							Integer cantidad = Integer.parseInt(evaluatePath(nombreServicio, "count(" + pathElementos + ")", document));

							if (cantidad == 0) {
								pathElementos = pathdata + "/" + parametro.getNombre() + "Elemento";
								cantidad = Integer.parseInt(evaluatePath(nombreServicio, "count(" + pathElementos + ")", document));
							}

							for (int i = 1; i <= cantidad; i++) {
								Map<String, String> registro = new HashMap<String, String>();

								String pathElemento = pathElementos + "[" + i + "]";

								for (Elemento elemento : parametro.getElementos()) {
									String baseentrada = pathElemento + "/dpi:" + elemento.getNombre();
									data = evaluatePath(nombreServicio, baseentrada, document);

									if (data == null) {
										baseentrada = pathElemento + "/" + elemento.getNombre();
										data = evaluatePath(nombreServicio, baseentrada, document);
									}

									registro.put(elemento.getNombre(), data);
								}

								listadatos.add(registro);
							}

							valorparametro = listadatos;
						}

					}

					if (com.osmosyscol.commons.utils.StringUtils.esVerdad(parametro.getEncriptado())) {

						if (valorparametro != null && Parametro.TIPO_INT.equalsIgnoreCase(parametro.getTipo())) {
							valorparametro = new Long(valorparametro.toString());
						}

						if (valorparametro != null && Parametro.TIPO_LONG.equalsIgnoreCase(parametro.getTipo())) {
							valorparametro = new Long(valorparametro.toString());
						}

						valorparametro = Crypto.E(valorparametro);
					}

					parametros.put(parametro.getNombre(), valorparametro);

					debugParameterValue(parametros, parametro.getNombre(), parametro.getTipo());
				}
			}

			// ---------------------------------------------------------------
			// REALIZA LA CONSULTA
			if (logger.isTraceEnabled())
				logger.trace("Parametros: " + parametros);

			List<Map<String, Object>> maparesultado = null;

			long inittime = System.currentTimeMillis();

			if (operacion.getConsulta() != null) {
				maparesultado = ManejadorConsultas.ejecutarConsulta(servicioDataPi.getConexion(), operacion, parametros, listFiltrosSql);
			}

			else if (StringUtils.isNotBlank(operacion.getProcedimiento())) {
				maparesultado = ManejadorConsultas.ejecutarProcedimiento(servicioDataPi.getConexion(), operacion, parametros);
			}

			else if (operacion.getConsultaArchivo() != null) {
				maparesultado = LectorArchivosPlanos.getInstance().ejecutarConsulta(operacion, parametros);
			}

			AutenticacionServicio.addLogPage("Datapi://" + servicioDataPi.getNombre() + "." + operacion.getNombre(), (System.currentTimeMillis() - inittime));

			if (maparesultado == null) {
				logger.error("RESPONSE ERROR: No se obtuvieron resultados al realizar la operación.");
				throw new Exception("error al realizar la operacion.");
			}

			if (logger.isDebugEnabled()) {
				logger.debug(String.format(" RESPONSE [nombreServicio=%s,operacion=%s,delta=%d ms]\n\n", nombreServicio, operacion.getNombre(), (System.currentTimeMillis() - inittime)));
			}
			if (logger.isTraceEnabled()) {
				logger.trace("XML-RESPONSE: " + XMLFormat.format(JavaToXML.exe("RESULTADO", maparesultado).toString()));
			}

			// ---------------------------------------------------------------
			// GENERA SALIDA

			Map<String, Object> map = new HashMap<String, Object>();
			{
				try {
					Map<String, Object> elementos = new HashMap<String, Object>();

					List<Map<String, Object>> elementosSalida = new ArrayList<Map<String, Object>>();

					if (maparesultado != null) {
						for (Map<String, Object> mapres : maparesultado) {

							Map<String, Object> elems = new HashMap<String, Object>();
							List<Resultado> resultados = operacion.getResultados();

							for (Resultado resultado : resultados) {

								if (Parametro.TIPO_ARRAY.equals(resultado.getTipo())) {

									Object res = mapres.get(resultado.getNombre().toUpperCase());

									Map<String, Object> mapsalida = new HashMap<String, Object>();
									List alista = new ArrayList();

									try {
										List elem = (ArrayList) res;
										for (Object m : elem) {

											try {
												Map tmap = (Map) m;

												alista.add(createMapElementos(resultado.getElementos(), tmap));
											} catch (Exception e) {
											}
										}

									} catch (Exception e) {
									}

									mapsalida.put("#" + resultado.getNombre() + "Elemento", alista);
									elems.put(resultado.getNombre(), mapsalida);

								}

								else if (Parametro.TIPO_SUBQUERY.equals(resultado.getTipo())) {

									Object res = mapres.get(resultado.getNombre().toUpperCase());

									Map<String, Object> mapsalida = new HashMap<String, Object>();
									List alista = new ArrayList();

									try {
										List elem = (ArrayList) res;
										for (Object m : elem) {

											try {
												Map tmap = (Map) m;
												alista.add(createMapElementosSubquery(resultado.getElementos(), tmap));
											} catch (Exception e) {
											}
										}

									} catch (Exception e) {
									}

									mapsalida.put("#" + resultado.getNombre() + "Elemento", alista);
									elems.put(resultado.getNombre(), mapsalida);

								} else {
									Object data = mapres.get(resultado.getNombre().toUpperCase());

									if (data == null && Parametro.TIPO_STRING.equalsIgnoreCase(resultado.getTipo())) {
										data = "";
									}

									if (data != null) {
										data = Crypto.DF(data);
										elems.put(resultado.getNombre(), data);
									}
								}
							}
							elementosSalida.add(elems);
						}
					}

					elementos.put("#elementoSalida", elementosSalida);

					map.put("salida" + operacion.getNombre(), elementos);

				} catch (Exception e) {
					logger.error(String.format("(error) ejecutarServicio [nombreServicio=%s,1]", nombreServicio), e);
					return null;
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("(end) ejecutarServicio [nombreServicio=%s]", nombreServicio));
			}

			return map;

			// ---------------------------------------------------------------

		} catch (Exception e) {
			logger.error(String.format("(error) ejecutarServicio [nombreServicio=%s,2]", nombreServicio), e);
		}

		return null;
	}

	/**
	 * Prints the value of a parameter according the type
	 * 
	 * @param parametros
	 * @param paramName
	 */
	private void debugParameterValue(Map<String, Object> parametros, String paramName, String paramType) {
		logger.debug("Parametro: " + paramName + " --> " + getDebugValue(parametros, paramName, paramType));
	}

	/**
	 * Prints the paramValue. For CLOBS it's restricted to have the trace mode enabled in logger for performance reasons
	 * 
	 * @param parametros
	 * @param paramName
	 * @param paramType
	 * @return
	 */
	private Object getDebugValue(Map<String, Object> parametros, String paramName, String paramType) {
		if (Parametro.TIPO_CLOB.equals(paramType)) {
			if (logger.isTraceEnabled()) {
				return parametros.get(paramName);
			}
			return "[CLOB-DATA]";
		}
		return parametros.get(paramName);
	}

	private boolean numericExpressionGt(String pathResult, int compareTo) {
		try {
			return null != pathResult && Integer.parseInt(pathResult) > compareTo;
		} catch (Exception e) {
			logger.warn("pathResult no evalua una expresion numerica " + pathResult, e);
			return false;
		}
	}

	private boolean numericExpressionEq(String pathResult, int compareTo) {
		try {
			return null != pathResult && Integer.parseInt(pathResult) == compareTo;
		} catch (Exception e) {
			logger.warn("pathResult no evalua una expresion numerica " + pathResult, e);
			return false;
		}
	}

	// ----------------------------------

	public static String toNumber(String texto) {

		if (texto == null) {
			return null;
		}

		texto = texto.trim();

		if (texto.length() == 0) {
			return null;
		}

		texto = texto.replace(',', '.');

		if (texto.matches("^[-]?[0-9][.][0-9]*[E][0-9]*$")) {
			return new BigDecimal(texto).toPlainString();
		}

		String textodef = "";

		// -------------------------

		for (int i = 0; i < texto.length(); i++) {
			if ("-1234567890.".indexOf(texto.charAt(i) + "") >= 0) {
				textodef += texto.charAt(i);
			}
		}

		return textodef;
	}

	// ----------------------------------

	@SuppressWarnings("unchecked")
	private Map<String, Object> createMapElementos(List<Elemento> elementos, Map tmap) {

		Map<String, Object> mmap = new HashMap<String, Object>();
		for (int i = 0; i < elementos.size(); i++) {

			Elemento elemento = elementos.get(i);

			if ((!Parametro.TIPO_OBJECT.equals(elemento.getTipo())) && (!Parametro.TIPO_ARRAY.equals(elemento.getTipo()))) {

				Object data = tmap.get(elemento.getId().toUpperCase());

				if (data == null && Parametro.TIPO_STRING.equalsIgnoreCase(elemento.getTipo())) {
					data = "";
				}

				if ( data != null) {
					data = Crypto.DF(data);
					mmap.put(elemento.getNombre(), data);
				}
			} else {
				// TODO: Faltan los subelementos
			}
		}
		return mmap;
	}

	// ----------------------------------

	@SuppressWarnings("unchecked")
	private Map<String, Object> createMapElementosSubquery(List<Elemento> elementos, Map<?, ?> tmap) {

		Map<String, Object> mmap = new HashMap<String, Object>();
		for (int i = 0; i < elementos.size(); i++) {

			Elemento elemento = elementos.get(i);

			if (!Parametro.TIPO_SUBQUERY.equals(elemento.getTipo())) {
				Object dato = tmap.get(elemento.getNombre().toUpperCase());
				dato = Crypto.DF(dato);
				mmap.put(elemento.getNombre(), dato);
			} else {

				Object res = tmap.get(elemento.getNombre().toUpperCase());

				Map<String, Object> mapsalida = new HashMap<String, Object>();
				List alista = new ArrayList();

				try {
					List elem = (ArrayList) res;
					for (Object m : elem) {

						try {
							Map tmap2 = (Map) m;
							alista.add(createMapElementosSubquery(elemento.getElementos(), tmap2));
						} catch (Exception e) {
						}
					}

				} catch (Exception e) {
				}

				mapsalida.put("#" + elemento.getNombre() + "Elemento", alista);
				mmap.put(elemento.getNombre(), mapsalida);
			}
		}
		return mmap;
	}

	// ----------------------------------

	public String evaluatePath(String nombreServicio, String consulta, Document document) {
		return evaluatePath(nombreServicio, consulta, document, null);
	}

	XmlUtils xmlUtils = new XmlUtils();

	@SuppressWarnings("unused")
	public String evaluatePath(String nombreServicio, String consulta, Document document, String tipoParametro) {
		try {

			boolean evaluated = false;

			XPath xPath = xPathFactory.newXPath();
			xPath.setNamespaceContext(new NamespaceService(nombreServicio));

			String test = null;

			if ((null != tipoParametro) && (StringUtils.equals(Parametro.TIPO_CLOB, tipoParametro))) {

				// add special cases
				// when is a clob, u need to evaluate if it's an embed xml. If so, xml should be evaluated and sent as string

				evaluate_as_dom_element: {
					if (null == test) {
						if (logger.isDebugEnabled()) {
							logger.debug(String.format("evaluatePath [datatype=%s,as=%s]", tipoParametro, "dom-element"));
						}

						Object localResult = null;
						localResult = xPath.evaluate(consulta + "/*", document, XPathConstants.NODE);

						if (localResult instanceof Element) {
							test = xmlUtils.w3cXmlToString((Element) localResult);
						}
					}
				} // :evaluate_as_dom_element

				evaluate_as_text: {
					if (null == test) {
						if (logger.isDebugEnabled()) {
							logger.debug(String.format("evaluatePath [datatype=%s,as=%s]", tipoParametro, "text"));
						}

						Object localResult = null;
						localResult = xPath.evaluate(consulta, document, XPathConstants.STRING);

						if (localResult instanceof String) {
							test = (String) localResult;
						}
					}

				} // :evaluate_as_text

			} else {
				// add normal case
				test = xPath.evaluate(consulta, document);
				evaluated = true;

			}

			if (test != null && test.length() == 0) {
				test = null;
			}

			return test;

		} catch (Exception e) {
			logger.error("error al evaluar el Path", e);
			return null;
		}

	}

	/**
	 * Permite registrar filtros a las consultas sql, con el fin de modificarlas o traducirlas antes de ejecutarlas en la base de datos
	 * 
	 * @param filtroSqlImp
	 *            - Objeto que implementa la interfaz y modifica las consultas sql
	 */
	public void registrarFiltroSql(IFiltroConsultaSql filtroSqlImp) {

		if (listFiltrosSql == null) {
			listFiltrosSql = new ArrayList<IFiltroConsultaSql>();
		}
		listFiltrosSql.add(filtroSqlImp);
	}

	public void eliminarFiltroSql(IFiltroConsultaSql filtroSqlImp) {
		if (listFiltrosSql != null) {
			listFiltrosSql.remove(filtroSqlImp);
		}
	}
}

// ------------------------------------------------------------------------------
// ------------------------------------------------------------------------------

class NamespaceService implements NamespaceContext {

	private Map<String, String> namespaces = null;

	public NamespaceService(String nombreServicio) {
		namespaces = new HashMap<String, String>();

		// added soap 1.1 envelope version
		namespaces.put("SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
		// added the service namespace
		namespaces.put("dpi", "http://www.itosmosys.com/datapi/servicios/" + nombreServicio);
		// added as default namespace
		namespaces.put(XMLConstants.DEFAULT_NS_PREFIX, "http://www.itosmosys.com/datapi/servicios/" + nombreServicio);
		// added another soap 1.2 envelope version
		namespaces.put("env", "http://www.w3.org/2003/05/soap-envelope");

	}

	public String getNamespaceURI(String prefix) {
		return namespaces.get(prefix);
	}

	public String getPrefix(String namespaceURI) {
		Set<String> prefixes = namespaces.keySet();
		for (String prefix : prefixes) {
			if (namespaces.get(prefix).toString().equals(namespaceURI)) {
				return prefix;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Iterator getPrefixes(String namespaceURI) {
		return namespaces.keySet().iterator();
	}

}
