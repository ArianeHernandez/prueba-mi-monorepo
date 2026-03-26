package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.utils.XMLFormat;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValidacionEstructura;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ValidacionEstructuraDao;
import com.osmosyscol.datasuite.modelatos.utils.FormatoUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class GeneradorArchivoCargaServicio {
	// ---------------------------------------------------------------

	private static GeneradorArchivoCargaServicio instancia;

	private GeneradorArchivoCargaServicio() {
	}

	// ---------------------------------------------------------------

	public static GeneradorArchivoCargaServicio getInstance() {
		if (instancia == null) {
			instancia = new GeneradorArchivoCargaServicio();
		}
		return instancia;
	}

	// ---------------------------------------------------------------

	/***
	 * Obtiene el mapa con los valores del excel
	 */

	public HSSFWorkbook obtenerDatosCarga(Integer id_carga, Integer id_formato_salida) {

		HSSFWorkbook wb = new HSSFWorkbook();
		Map<String, HSSFCellStyle> estilos = definirEstilos(wb);

		DaoManager daoManager = DaoConfig.getDaoManager("CREADATOS");
		ValidacionEstructuraDao validacionCargaDao = (ValidacionEstructuraDao) daoManager.getDao(ValidacionEstructuraDao.class);

		try {
			// Se debe buscar todas las estructuras asociadas al formato de
			// salida
			EstructuraServicio estructuraServicio = EstructuraServicio.getInstance();
			List<Estructura> estructurasPorFormato = estructuraServicio.obtenerEstructurasPorFormatoDeSalida(id_formato_salida);

			Map<String, Object> validaciones = ValidacionEstructuraServicio.getInstance().verificarValidacionesPorCarga(id_carga, id_formato_salida);

			// Por cada una de las estructuras se buscan los registros asociados
			// a la carga y las validaciones
			for (Estructura estructura : estructurasPorFormato) {

				Integer id_estructura = estructura.getId_estructura();

				String nombrehoja = StringUtils.toFileName(estructura.getNombre().toUpperCase());

				HSSFSheet newsheet = wb.createSheet(nombrehoja);
				newsheet.setDefaultColumnWidth((short) 25);

				String nombre_hoja = StringUtils.toFileName(estructura.getNombre());

				if (nombre_hoja.length() > 20) {
					nombre_hoja = nombre_hoja.substring(0, 20);
				}

				List<FormatoCampo> formatoscampo = FormatoServicio.getInstance().obtenerFormatosCampoPorEstructura(id_estructura, id_formato_salida);

				// obtiene los valores de la carga para la estructura segun el
				// formato.
				List<Map<String, Object>> datos = validacionCargaDao.obtenerRegistrosDeEstructuraPorCargaVista(id_carga, id_estructura);

				try{
					for (Map<String, Object> registro : datos) {
	
						Object id_reg = registro.get("ID");
	
						// obtienes las validaciones para el registro
						Map validacionesregistro = (Map) ((Map) validaciones.get("registros_validados")).get(id_reg + "");
	
						if (validacionesregistro != null) {
							Set validaciones_id = validacionesregistro.keySet();
							for (Object id_validacion : validaciones_id) {
								if (validacionesregistro.get(id_validacion) != null && !(Boolean) validacionesregistro.get(id_validacion)) {
	
									ValidacionEstructura ve = (ValidacionEstructura) ((Map) validaciones.get("validaciones_activadas")).get(id_validacion);
	
									if (ve != null) {
										String str_validacion = (String) registro.get("VALIDACION");
										if (str_validacion == null) {
											str_validacion = "";
										}
	
										str_validacion = (StringUtils.esVacio(str_validacion)) ? ve.getDescripcion() : (str_validacion + "\n " + ve.getDescripcion());
	
										registro.put("VALIDACION", str_validacion);
									}
	
								}
							}
						}
					}
				}catch (Exception e) {
					SimpleLogger.setError("Error al realizar la carga de validaciones de datos.");
				}

				// crea el encabezado

				HSSFRow fila0 = getRow(newsheet,  0);
				short columna = 0;
				for (FormatoCampo formatoCampo : formatoscampo) {

					if (StringUtils.esVerdad(formatoCampo.getSeleccion_campo()) || StringUtils.esVerdad(formatoCampo.getSeleccion_visualizacion())) {

						Integer id_campo = formatoCampo.getId_campo();

						HSSFCell ccd = fila0.createCell(columna);
						ccd.setCellValue(new HSSFRichTextString(formatoCampo.getTitulo()));
						ccd.setCellStyle(estilos.get("tituloId"));

						short fila = 1;
						for (Map<String, Object> registro : datos) {

							HSSFRow row = getRow(newsheet, fila);
							HSSFCell cel = row.createCell(columna);
							cel.setCellStyle(estilos.get("editable"));

							Object dato = registro.get("V" + id_campo);

							try{
								if (dato != null) {
									// verifica la validacion en el campo
	
									Object id_reg = registro.get("C" + id_campo);
	
									// obtienes las validaciones para el registro
									Map validacionesregistro = (Map) ((Map) validaciones.get("registros_validados")).get(id_reg + "");
	
									if (validacionesregistro != null) {
										Set validaciones_id = validacionesregistro.keySet();
										for (Object id_validacion : validaciones_id) {
											if (validacionesregistro.get(id_validacion) != null && !(Boolean) validacionesregistro.get(id_validacion)) {
	
												ValidacionEstructura ve = (ValidacionEstructura) ((Map) validaciones.get("validaciones_activadas")).get(id_validacion);
	
												if (ve != null) {
													String str_validacion = (String) registro.get("VALIDACION");
	
													cel.setCellStyle(estilos.get("editable_resaltado"));
	
													str_validacion = (StringUtils.esVacio(str_validacion)) ? (formatoCampo.getTitulo() + ": " + ve.getDescripcion()) : (str_validacion + "\n " + formatoCampo.getTitulo() + ": " + ve.getDescripcion());
	
													registro.put("VALIDACION", str_validacion);
												}
	
											}
										}
									}
								}
							}catch (Exception e) {
								SimpleLogger.setError("Error al cargar las validaciones", e);
							}

							if (dato == null) {
								if(formatoCampo.getTitulo()!= null){
									String tituloCampo = StringUtils.upperCase(formatoCampo.getTitulo());
									if(tituloCampo.equals("ID CUENTA")){
										dato = registro.get("C" + id_campo);
										if(dato!=null){
											String cadenaDato = dato.toString();
											if(cadenaDato.contains("-")){
												Integer posicion = cadenaDato.lastIndexOf("-");
												if(posicion!=null){
												String cadenaTemp = cadenaDato.substring(posicion + 1);
												if(!StringUtils.validaExpRegular(cadenaTemp, "[0-9]")){
													cadenaDato = cadenaDato.substring(0, posicion);
													registro.put("C" + id_campo, cadenaDato);				
												}
											}	
										   }										
										}
									}
								}
								dato = registro.get("C" + id_campo);
							}

							if (dato != null) {
								
								if(StringUtils.isNotBlank(formatoCampo.getFormato())) {
									dato = FormatoUtils.obtenerValorPorFormato(formatoCampo, dato);
								}
								
								if (dato instanceof BigDecimal || dato instanceof Long || dato instanceof Integer) {
									cel.setCellValue(new Double(dato.toString()));
								} else {
									cel.setCellValue(new HSSFRichTextString(StringUtils.toString(dato)));
								}
							}

							fila++;
						}

						columna++;
					}
				}

				if (columna == 0) {
					wb.setSheetHidden(wb.getSheetIndex(newsheet), true);
				} else {
					try {
						wb.setSelectedTab((short)wb.getSheetIndex(newsheet));
						
						HSSFCell ccd = fila0.createCell(columna);

						boolean conValidaciones = false;
						short fila = 1;
						for (Map<String, Object> registro : datos) {

							HSSFRow row = getRow(newsheet, fila);
							HSSFCell cel = row.createCell(columna);
							cel.setCellStyle(estilos.get("editable_resaltado"));

							if (registro.get("VALIDACION") != null) {
								cel.setCellValue(new HSSFRichTextString(StringUtils.toString(registro.get("VALIDACION"))));
								conValidaciones = true;
							}

							fila++;
						}

						if (conValidaciones) {
							ccd.setCellValue(new HSSFRichTextString("Validación"));
							ccd.setCellStyle(estilos.get("tituloId"));
						}
					} catch (Exception e) {
						SimpleLogger.setDebug("Error al cargar las validaciones", e);
					}
				}
				
			}

			return wb;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;

	}
	
	private static HSSFRow getRow(HSSFSheet sheet, int index) {
		HSSFRow row = sheet.getRow(index);
		if (row == null) {
			row = sheet.createRow(index);
		}

		return row;
	}

	public File crearArchivo(Integer id_carga, Integer id_formato_salida) {

		File archivo = null;

		HSSFWorkbook wb = obtenerDatosCarga(id_carga, id_formato_salida);

		String nombrecarpeta = ParametrosInicio.getProperty("file.carpeta") + "/TMP";

		(new File(nombrecarpeta)).mkdirs();

		String nombrearchivo = nombrecarpeta + "/TMP_" + StringUtils.randomString(10) + System.currentTimeMillis() + ".xls";

		try {
			FileOutputStream fileOut = new FileOutputStream(nombrearchivo);

			wb.write(fileOut);
			fileOut.close();

			archivo = new File(nombrearchivo);
		} catch (Throwable e) {
			SimpleLogger.setError("Error al generar archivo", e);
		}

		return archivo;

	}

	private Map<String, HSSFCellStyle> definirEstilos(HSSFWorkbook wb) {
		// -------------------------------------------------------------------
		// Define fuentes

		HSSFFont fontTitulo = wb.createFont();
		fontTitulo.setColor(HSSFColor.WHITE.index);
		fontTitulo.setFontHeight((short) 180);
		fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFFont fontResaltado = wb.createFont();
		fontResaltado.setColor(HSSFColor.RED.index);
		fontResaltado.setFontHeight((short) 180);
		fontResaltado.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// --

		Map<String, HSSFFont> fonts = new HashMap<String, HSSFFont>();
		fonts.put("titulo", fontTitulo);
		fonts.put("resaltado", fontResaltado);

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

		HSSFCellStyle editredStyle = wb.createCellStyle();
		editredStyle.setLocked(false);
		editredStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		editredStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		editredStyle.setWrapText(true);
		editredStyle.setFont(fontResaltado);
		editredStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		editredStyle.setBorderBottom((short) 1);
		editredStyle.setBorderLeft((short) 1);
		editredStyle.setBorderRight((short) 1);
		editredStyle.setBorderTop((short) 1);
		editredStyle.setWrapText(true);

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
		styles.put("editable_resaltado", editredStyle);
		styles.put("editable", editStyle);
		styles.put("constante", constStyle);
		styles.put("oculto", ocultoStyle);
		styles.put("titulo", styleTitulo);
		styles.put("tituloId", styleTituloId);

		agregarEstilos(styles, wb);

		return styles;
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

}
