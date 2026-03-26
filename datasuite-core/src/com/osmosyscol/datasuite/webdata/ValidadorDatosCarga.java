package com.osmosyscol.datasuite.webdata;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.cocoon.environment.Session;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.servicios.ListaDinamicaCampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaDinamica;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.CampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaDinamicaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.TipoCampoServicio;

public class ValidadorDatosCarga {

	private static Integer FILA_INICIO_CARGA_INFORMACION = 3;

	public Map<String, List<String>> validarDatosArchivoCarga(Integer id_formato, String ruta, Session session) {
		try {
			InputStream inp = new FileInputStream(ruta);
			return validarDatosArchivoCarga(id_formato, inp, session);
		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado un error", e);
			return null;
		}
	}
	
	public Map<String, List<String>> validarDatosArchivoCarga(Integer id_formato, InputStream inp, Session session) {
		HashMap<String, List<String>> mapaErrores = new HashMap<String, List<String>>();
		try {

			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

			Integer numeroHojas = wb.getNumberOfSheets();

			HashMap<String, List<Object[]>> mapaHojas = new HashMap<String, List<Object[]>>();

			Boolean esPrimeraHoja = true;
			String nombrePrimeraHoja = "";
			// Se reccorren cada una de las hojas del archivo
			for (short i = 0; i < numeroHojas; i++) {

				// Se construyen los datos a validar por cada hoja que no este
				// oculta
				if (!wb.isSheetHidden(i)) {

					// es la primer hoja que no esta oculta
					if (esPrimeraHoja) {
						nombrePrimeraHoja = wb.getSheetName(i);
						esPrimeraHoja = false;
					}

					HSSFSheet hoja = wb.getSheetAt(i);
					List<Object[]> filasValidasPorHoja = obtenerFilasValidasPorHoja(hoja);
					mapaHojas.put(wb.getSheetName(i), filasValidasPorHoja);

				}

			}

			// Luego se deben validar los datos de cada una de las hojas
			

			Set<String> nombresDeHoja = mapaHojas.keySet();

			boolean tienedatos = false;

			for (String nombre_hoja : nombresDeHoja) {
				// Se valida cada hoja
				List<String> erroresPorHoja = new ArrayList<String>();
				List<Object[]> filasHoja = mapaHojas.get(nombre_hoja);

				if (!nombre_hoja.startsWith("R")) {
					if (filasHoja.size() >= FILA_INICIO_CARGA_INFORMACION) {

						tienedatos = true;

						// Se validan datos obligatorias y tipos de datos
						validarDatosObligatorios_y_TiposDeDato(nombre_hoja,
								mapaHojas, id_formato, filasHoja,
								erroresPorHoja, session);

						// Se validan la llave primaria
						validarLLavePrimaria(filasHoja, erroresPorHoja);

						// Se validan campos unicos
						validarCamposUnicos(filasHoja, erroresPorHoja);

						imprimirErroresPorHoja(erroresPorHoja);

					}
				}
				// Si hubieron errores se guarda en el mapa de errores por hoja
				if (erroresPorHoja.size() > 0) {
					mapaErrores.put(nombre_hoja, erroresPorHoja);
				}

			}

			if (!tienedatos) {

				List<String> erroresPorHoja = null;
				if (!mapaErrores.containsKey(nombrePrimeraHoja)) {
					erroresPorHoja = new ArrayList<String>();
					mapaErrores.put(nombrePrimeraHoja, erroresPorHoja);
				} else {
					erroresPorHoja = mapaErrores.get(nombrePrimeraHoja);
				}

				erroresPorHoja.add("Documento sin registros. Por lo menos debe haber 1 registro para cargar correctamente el archivo.");
			}

			// Se retorna el mapa de hojas con errores
			return mapaErrores;

		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado un error", e);
			List<String> erroresInesperados = new ArrayList<String>();
			erroresInesperados.add("Por favor verifique que la información se encuentre en un formato de archivo descargado recientemente.");
			mapaErrores.put("Error leyendo archivo", erroresInesperados);
			return mapaErrores;
		}
	}

	private List<Object[]> obtenerFilasValidasPorHoja(HSSFSheet hoja) {
		// Se calculan el numero de columnas visibles por hoja
		Boolean esVacio = false;
		HSSFRow filaEncabezado = hoja.getRow(0);
		Integer totalColumnasVisibles = 0;
		ArrayList<Integer> posicionesCamposVisiblesPorHoja = new ArrayList<Integer>();

		short i = 0;
		while (!esVacio) {
			HSSFCell celda = filaEncabezado.getCell(i);

			if (celda != null) {
				if (!hoja.isColumnHidden(i)) {
					if (getValorCelda(celda) != null) {
						posicionesCamposVisiblesPorHoja.add(celda.getColumnIndex());
						totalColumnasVisibles++;

					} else {
						esVacio = true;
					}

				} else {
					if (getValorCelda(celda) == null) {
						esVacio = true;
					}

				}
			} else {
				esVacio = true;
			}

			i++;

		}

		// Se crea la matriz de datos por hoja
		ArrayList<Object[]> filasValidasPorHoja = new ArrayList<Object[]>();

		// Se recorren las filas de una misma hoja
		for (short j = 0; j < hoja.getLastRowNum(); j++) {

			try {

				HSSFRow fila = hoja.getRow(j);

				// Se crean array de valores de las columnas
				Object[] valoresColumnas = new Object[totalColumnasVisibles];

				Boolean esFilaVacia = true;
				// Se recorren las columnas de la fila
				for (int k = 0; k < totalColumnasVisibles; k++) {
					Object valor = getValorCelda(fila.getCell(posicionesCamposVisiblesPorHoja.get(k)));

					if (k > 0) {
						if (valor != null) {
							esFilaVacia = false;
						}
					}

					valoresColumnas[k] = valor;

				}

				// Si la fila es valida se adiciona a la lista de filas por hoja
				if (!esFilaVacia) {
					filasValidasPorHoja.add(valoresColumnas);
				}

			} catch (Exception e) {
				SimpleLogger.setError("Problema leyendo la fila " + j, e);
			}
		}

		// imprimirFilasHoja(filasValidasPorHoja);

		return filasValidasPorHoja;

	}

	private Object getValorCelda(HSSFCell celda) {
		Object valor = null;

		if (celda == null) {
			return null;
		}

		switch (celda.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			valor = celda.getRichStringCellValue();
			break;

		case HSSFCell.CELL_TYPE_BOOLEAN:
			valor = celda.getBooleanCellValue();
			break;

		case HSSFCell.CELL_TYPE_NUMERIC:
			valor = new BigDecimal(celda.getNumericCellValue());
			break;

		default:
			break;
		}

		return valor;

	}

	private void imprimirErroresPorHoja(List<String> erroresPorHoja) {
		SimpleLogger.setInfo("Errores por hoja");

		StringBuffer error = new StringBuffer();
		for (String string : erroresPorHoja) {
			error.append(string);
			error.append(".\n");
			;
		}

		SimpleLogger.setInfo(error.toString());

	}

	private void validarDatosObligatorios_y_TiposDeDato(String nombre_hoja, HashMap<String, List<Object[]>> mapaHojas, Integer id_formato, List<Object[]> filasHoja, List<String> erroresPorHoja, Session session) {

		if (filasHoja != null) {

			String StrId_formato_campo = nombre_hoja.split("_")[0];
			
			Integer id_formato_campo;
			List<FormatoCampo> formatos_campo;
			if(!nombre_hoja.startsWith("R")){
				id_formato_campo = new Integer(StrId_formato_campo);
				formatos_campo = FormatoServicio.getInstance().obtenerHijosFormatoCampo(id_formato, id_formato_campo);
			}else{
				formatos_campo = new LinkedList<FormatoCampo>();
//				StrId_formato_campo = StrId_formato_campo.substring(1);
//				id_formato_campo = new Integer(StrId_formato_campo);
//				FormatoCampo formatoCampo = FormatoServicio.getInstance().obtenerFormatoCampo(id_formato_campo);
//				formatos_campo.add(formatoCampo); TODO Queda pendiente validaciones de hojas de relacion, el nombre del formatoCampo no se encuentra en este formatocampo
				return;//Eliminar
			}
			
			Map<String, FormatoCampo> formatoCampoMap = new HashMap<String, FormatoCampo>();				

			if (formatos_campo != null) {
				for (FormatoCampo formatoCampo : formatos_campo) {
					formatoCampoMap.put(formatoCampo.getTitulo().toUpperCase().trim(), formatoCampo);
				}
			}

			List<TipoCampo> tiposCampos = TipoCampoServicio.getInstance().obtenerTipoCampos();

			// Se crea el mapa de tipos
			HashMap<Integer, TipoCampo> mapTipos = new HashMap<Integer, TipoCampo>();
			for (TipoCampo tipoCampo : tiposCampos) {
				mapTipos.put(tipoCampo.getId_tipocampo(), tipoCampo);
			}

			// Se obtiene la primera fila
			Object[] columnasPrimeraFila = filasHoja.get(0);
			Object[] columnasSegundaFila = filasHoja.get(1);

			// la columna 0 es el numero de la fila por eso no se tiene en
			// cuenta
			for (int i = 1; i < columnasPrimeraFila.length; i++) {
				// Se evalua cada columna
				Integer id_campo = Integer.parseInt(columnasPrimeraFila[i].toString().substring(1));
				Campo campo = CampoServicio.getInstance().obtenerCampo(id_campo);

				boolean esObligatorio = columnasSegundaFila[i].toString().indexOf("(*)") >= 0 || StringUtils.esVerdad(campo.getObligatorio());

				FormatoCampo fcampo = formatoCampoMap.get(columnasSegundaFila[i].toString().replace("(*)", " ").trim());

				// Se recorre apartir de la fila 2 ya que las filas 0 y 1 son
				// parte del encabezado
				for (int j = 2; j < filasHoja.size(); j++) {

					Object valor = filasHoja.get(j)[i];

					String valorColumna = StringUtils.toString(valor);

					// Se se revisa si la columna es de tipo fecha
					if (valorColumna != null) {

						valorColumna = StringUtils.getStringCorchetes(valorColumna);

						if (valorColumna.matches("^[0-9]+$") && mapTipos.get(campo.getId_tipocampo()).getTipo_dato().equals("Date")) {
							Date valorFecha = HSSFDateUtil.getJavaDate(new Double(valorColumna));
							valorColumna = StringUtils.toString(valorFecha);
						}

					}

					TipoCampo tipoCampo = mapTipos.get(campo.getId_tipocampo());

					// Se revisa si el campo es obligatorio
					if (esObligatorio && valorColumna == null) {
						// Guardar error
						erroresPorHoja.add("Linea " + filasHoja.get(j)[0] + ". El campo " + campo.getNombre() + " es obligatorio. ");
					}

					// si el valor hace referencia a una estructura verificar
					// que exista en otra hoja del excel
					if (tipoCampo.getTipo_dato().equals("Object") && valorColumna != null && !esValidoValorEnHojas(valorColumna, campo, mapaHojas)) {
						erroresPorHoja.add("Linea " + filasHoja.get(j)[0] + ". El campo " + campo.getNombre() + " tiene un valor no valido. ");
					}

					// Se valida si el valor corresponde al tipo de campo

					String patronValidacion = tipoCampo.getPatron_validacion();

					// Si el campo es de tipo estructura el patron de validacion
					// debe ser un entero
					if (tipoCampo.getTipo_dato().equals("Object")) {
						patronValidacion = "^[0-9]+$";
					}

					// Finalmente se aplica el patron de validacion segun el el
					// tipo de campo
					if (valorColumna != null && !valorColumna.matches(patronValidacion)) {
						// Guardar error
						erroresPorHoja.add("Linea " + filasHoja.get(j)[0] + ". El campo " + campo.getNombre() + " no corresponde con el tipo " + tipoCampo.getNombre());

					}

					if (valorColumna != null && campo.getPatronvalidacion() != null && !valorColumna.matches(campo.getPatronvalidacion())) {
						// Guardar error
						erroresPorHoja.add("Linea " + filasHoja.get(j)[0] + ". El campo " + campo.getNombre() + " tiene un formato incorrecto.");

					}

					// Verifica si cumple con la validacion del valor definido en el formato de entrada

					if (!StringUtils.isBlank(valorColumna) && "S".equalsIgnoreCase(fcampo.getValidacion_campo_lista())) {

						Integer id_lista_dinamica = fcampo.getLista_dinamica_validacion();

						ListaDinamica listaDinamica = ListaDinamicaServicio.getInstance().obtenerListaDinamica(id_lista_dinamica);
						
						String parametrosLD="";
						if(!listaDinamica.getConsulta().contains("parametro_json_2")){							
							parametrosLD="[" + StringUtils.trimToEmpty(valorColumna) + "],[]";
							}else{
							try{
								if(fcampo.getCampo_validacion()!=null){
									String campoVAL=fcampo.getCampo_validacion();
									String[] varCAMP=campoVAL.split(":");
									//
									Integer nivelF = Integer.parseInt(varCAMP[0]);
									String nombreFormatoCampo = varCAMP[1];
									Integer id_fc_padre_sel=fcampo.getId_formato_campo_padre();
									//Fila y hoja por defecto en nivel 0 las actuales
									Integer filaH=j;
									List<Object[]> filasHojaTMP=filasHoja;
									
									FormatoCampo fcPadre = FormatoServicio.getInstance().obtenerFormatoCampoPorID(id_fc_padre_sel);
									for(int c=0;c<nivelF;c++){
										fcPadre = FormatoServicio.getInstance().obtenerFormatoCampoPorID(id_fc_padre_sel);
										id_fc_padre_sel=fcPadre.getId_formato_campo_padre();
										//TODO: falta detectar fila perteneciente al padre y cargar hoja correspondiente a mayor nivel
									}
									List<FormatoCampo> listaHijosFC=FormatoServicio.getInstance().obtenerHijosFormatoCampo(fcPadre.getId_formato(), fcPadre.getId_formato_campo());
									
									for (FormatoCampo formatoCampo : listaHijosFC) {
										if(formatoCampo.getTitulo().equals(nombreFormatoCampo)){
											//Al encontrar el formato indicado busco la correspondencia con 
											//el valor de excel que necesito
											//con el campo detectamos el indice en la hoja de excel
											Object[] columnasPrimeraFilaTMP = filasHojaTMP.get(0);
											Integer columnaTMP=0;
											for(int p=0;p<columnasPrimeraFilaTMP.length;p++){
												Integer id_campoTMP = Integer.parseInt(columnasPrimeraFila[p].toString().substring(1));
												if(id_campoTMP.equals(formatoCampo.getId_campo())){
													columnaTMP=p;
												}		
											}
											Object valorFCTMP = filasHojaTMP.get(filaH)[columnaTMP];
											String valorColumnaFCTMP = StringUtils.toString(valorFCTMP);
											
											if (valorColumnaFCTMP != null) {
												valorColumnaFCTMP = StringUtils.getStringCorchetes(valorColumnaFCTMP);
											}
											
											
											parametrosLD="[" + StringUtils.trimToEmpty(valorColumna) + "],[" + StringUtils.trimToEmpty(valorColumnaFCTMP) + "]";
										}
									}	
								}
							}catch(Exception e){
								SimpleLogger.setError("Error realizando validación. ", e);
								if(campo!=null){
									erroresPorHoja.add("El campo " + campo.getNombre() + " falla en validación con lista dinamica.");
								}else{
									erroresPorHoja.add("Falla en validación con lista dinamica.");
								}
							}
						}
						List<ValorLista> resp = ListaDinamicaCampoServicio.getInstance().obtenerValoresListaDinamicaParams(id_lista_dinamica, parametrosLD, session);
						
						if (resp == null || resp.size() == 0) {

							String mensaje = StringUtils.trimToNull(fcampo.getMensaje_validacion());

							if (mensaje == null) {
								erroresPorHoja.add("Linea " + filasHoja.get(j)[0] + ". El campo " + campo.getNombre() + " tiene un valor incorrecto.");
							} else {
								erroresPorHoja.add("Linea " + filasHoja.get(j)[0] + ". " + mensaje);
							}
						}
					}
				}
			}
		}
	}

	private boolean esValidoValorEnHojas(String valor, Campo campo, HashMap<String, List<Object[]>> mapaHojas) {

		if (valor == null || campo.getId_estructurarelacionada() == null) {
			return true;
		}

		String tablaEstructura = "T" + campo.getId_estructurarelacionada();

		Set<String> nombreHojas = mapaHojas.keySet();

		boolean existeHoja = false;
		for (String nombreHoja : nombreHojas) {

			List<Object[]> valoresHoja = mapaHojas.get(nombreHoja);

			Object[] encabezadosHoja = valoresHoja.get(0);

			// verifica que la hoja contenga los valores de la tabla
			if (tablaEstructura.equals(encabezadosHoja[0] + "")) {
				existeHoja = true;

				for (Object[] registro : valoresHoja) {

					if (valor.equals(registro[0] + "")) {
						return true;
					}
				}
			}
		}

		return !existeHoja;
	}

	private void validarCamposUnicos(List<Object[]> filasHoja, List<String> erroresPorHoja) {

		// Arreglo de columnas para la fila 0 donde esta los nombres de campo
		Object[] columnasFilaInicial = filasHoja.get(0);

		// La columna 0 se utiliza para el numero de fila
		for (int i = 1; i < columnasFilaInicial.length; i++) {

			Integer id_campo = Integer.parseInt(columnasFilaInicial[i].toString().substring(1));
			Campo campo = CampoServicio.getInstance().obtenerCampo(id_campo);

			// Se revisan que campo es campo unico
			if (StringUtils.esVerdad(campo.getUnico())) {
				ArrayList<Integer> posicionCampoUnico = new ArrayList<Integer>();
				posicionCampoUnico.add(i);

				// Se ejecuta el validador de campos unicos
				validarCamposRepetidosPorFilas(filasHoja, posicionCampoUnico, erroresPorHoja);
			}

		}

	}

	private void validarLLavePrimaria(List<Object[]> filasHoja, List<String> erroresPorHoja) {

		// fila inicial en el cual esta los nombres de campo
		Object[] columnasFilaInicial = filasHoja.get(0);

		ArrayList<Integer> posicionCamposLlavePrimaria = new ArrayList<Integer>();

		// La columna 0 se utiliza para el numero de fila
		for (int i = 1; i < columnasFilaInicial.length; i++) {

			Integer id_campo = Integer.parseInt(columnasFilaInicial[i].toString().substring(1));
			Campo campo = CampoServicio.getInstance().obtenerCampo(id_campo);

			// Se revisan de los campos visibles cuales son parte de la llave
			// primaria
			if (StringUtils.esVerdad(campo.getLlaveprimaria())) {
				posicionCamposLlavePrimaria.add(i);

			}

		}

		// Se ejecuta el validador de campos unicos
		validarCamposRepetidosPorFilas(filasHoja, posicionCamposLlavePrimaria, erroresPorHoja);

	}

	private void validarCamposRepetidosPorFilas(List<Object[]> filasHoja, ArrayList<Integer> posicionCampos, List<String> erroresPorHoja) {

		// Deben haber mas de 3 filas ya que las dos primeras son parte de la
		// plantilla
		if (posicionCampos != null && posicionCampos.size() > 0 && filasHoja.size() >= FILA_INICIO_CARGA_INFORMACION) {

			String nombreCampos = " ";
			for (Integer posicionCampo : posicionCampos) {
				nombreCampos = nombreCampos + filasHoja.get(1)[posicionCampo] + " ,";

			}

			// Se crea el mapa para la busqueda de valores repetidos
			HashMap<String, Object[]> hashMap = new HashMap<String, Object[]>();

			for (int j = 2; j < filasHoja.size(); j++) {
				Object[] fila = filasHoja.get(j);

				// Se construye la union de campos unicos como llave para el
				// hash
				String unionCamposUnicos = "";
				for (int i = 0; i < fila.length; i++) {
					for (Integer campoUnico : posicionCampos) {
						String valorCampo = StringUtils.toString(fila[campoUnico]);
						unionCamposUnicos = unionCamposUnicos + StringUtils.getStringCorchetes(valorCampo);

					}

				}

				// Se verifica si la union esta repetida en el hash
				if (!hashMap.containsKey(unionCamposUnicos)) {
					hashMap.put(unionCamposUnicos, fila);

				} else {
					erroresPorHoja.add("Las filas " + hashMap.get(unionCamposUnicos)[0] + " y " + fila[0] + " no puden tener valores repetidos para el(los) campo(s): " + nombreCampos);
				}

			}

		}

	}

}
