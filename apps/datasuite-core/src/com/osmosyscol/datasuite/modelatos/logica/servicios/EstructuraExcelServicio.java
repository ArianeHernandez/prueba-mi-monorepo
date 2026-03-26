package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.Mensaje;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.CampoValor;
import com.osmosyscol.datasuite.logica.dto.Registro;
import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;
import com.osmosyscol.datasuite.logica.servicios.DatosEstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

/**
 * Se utiliza para cargar datos por archivo excel en la edición de datos de las estructuras también permite descargar los datos actuales de la estructura.
 * 
 * @author oaortiz
 *
 */
public class EstructuraExcelServicio {

	// TODO este valor se puede dejar configurable
	private static short REGISTROS = 5000;

	private static EstructuraExcelServicio instance;

	public static final String ATRIBUTO_MENSAJE = "mensaje_carga_excel";

	private static final String ETIQUETA_CONFIGURACION_REGISTROS = "MAXIMO_REGISTROS";

	private EstructuraExcelServicio() {
		String valor = ConfiguracionServicio.getInstance().obtenerValorByEtiqueta(ETIQUETA_CONFIGURACION_REGISTROS);
		if (StringUtils.isNotEmpty(valor) && NumberUtils.isDigits(valor)) {
			REGISTROS = new Short(valor);
		}
	}

	public static EstructuraExcelServicio getInstance() {
		if (instance == null) {
			instance = new EstructuraExcelServicio();
		}
		return instance;
	}

	/**
	 * Inicia la carga de datos de la estructura en segundo plano ver {@link cargarArchivoExcel}
	 * 
	 * @param id_estructura
	 * @param archivo
	 * @param reemplazarTodo
	 * @param mensaje
	 * @author oaortiz
	 */
	public void iniciarCargaArchivoExcel(Integer id_estructura, String archivo, Boolean reemplazarTodo, Mensaje mensaje) {
		try {
			HiloCargarArchivoExcel hilo = new HiloCargarArchivoExcel(id_estructura, archivo, reemplazarTodo, mensaje);
			new Thread(hilo).start();
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			mensaje.setMensaje("Error iniciando la carga");
			mensaje.setEstado("F");
		}

	}

	/**
	 * Carga el archivo excel en el formato descargado
	 * 
	 * @param id_estructura
	 * @param archivo
	 *            - ruta del archivo
	 * @param reemplazarTodo
	 *            - determina si se reemplazan todos los datos de la estructura
	 * @param mensaje
	 *            - Mensaje que indica el estado de la carga del archivo
	 * @return
	 * @author oaortiz
	 */
	public Boolean cargarArchivoExcel(Integer id_estructura, String archivo, Boolean reemplazarTodo, Mensaje mensaje) {

		try {

			mensaje.setEstado("Iniciando Carga ... ");// Iniciando

			Estructura estructura = EstructuraServicio.getInstance().obtenerEstructura(id_estructura);

			InputStream inp = new FileInputStream(archivo);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

			HSSFSheet sheet = wb.getSheet(estructura.getNombre());

			List<String> errores = new ArrayList<String>();
			List<Registro> registros = leerHoja(sheet, estructura, errores, mensaje);

			if (errores.isEmpty()) {
				return DatosEstructuraServicio.getInstance().guardarDatosEstructuraTodo(registros, id_estructura, reemplazarTodo, mensaje);
			} else {
				StringBuffer mensajeError = new StringBuffer();
				mensajeError.append("Hay errores en el archivo:");

				for (String error : errores) {
					mensajeError.append("\n");
					mensajeError.append(error);
				}
				mensaje.setMensaje(mensajeError.toString());
				mensaje.setEstado("F");
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			mensaje.setMensaje("No se ha podido cargar el archivo");
			mensaje.setEstado("F");
		} finally {
			try {
				File file = new File(archivo);
				file.delete();
			} catch (Exception e2) {
			}
		}

		return false;
	}

	private List<Registro> leerHoja(HSSFSheet sheet, Estructura estructura, List<String> errores, Mensaje mensaje) {

		List<Registro> registros = new ArrayList<Registro>();
		List<Campo> campos = CampoServicio.getInstance().obtenerCamposPorEstructura(estructura.getId_estructura());

		for (short f = 2; f <= sheet.getLastRowNum(); f++) {
			HSSFRow fila = sheet.getRow(f);

			Registro registro = new Registro();
			List<CampoValor> valores = new ArrayList<CampoValor>();

			registro.setCampos(valores);

			Boolean hayValores = false;

			// Antes de hacer las validaciones, debemos verificar que haya valores en la fila
			for (short j = 0; j < campos.size(); j++) {
				Campo campo = campos.get(j);
				String valor = null;

				HSSFCell cell = fila.getCell((short) (j + 1));

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

					CampoValor campoValor = new CampoValor();
					campoValor.setId_campo(campo.getId_campo());
					if (StringUtils.isNotEmpty(valor)) {
						campoValor.setValor(valor);
						hayValores = true;
					}
				}

			}

			if (hayValores) {
				for (short j = 0; j < campos.size(); j++) {

					mensaje.setMensaje("Leyendo fila " + (j + 1));

					Campo campo = campos.get(j);
					String valor = null;

					HSSFCell cell = fila.getCell((short) (j + 1));

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

						CampoValor campoValor = new CampoValor();
						campoValor.setId_campo(campo.getId_campo());

						if (StringUtils.isNotEmpty(valor) && campo.getId_lista_valores() != null || campo.getId_estructurarelacionada() != null) {
							valor = com.osmosyscol.commons.utils.StringUtils.getStringCorchetes(valor);
						}

						TipoCampo tipoCampo = TipoCampoServicio.getInstance().obtenerTipoCampo(campo.getId_tipocampo());

						if (valor != null) {

							if ("Date".equals(tipoCampo.getTipo_dato()) && NumberUtils.isNumber(valor)) {
								try {
									Date valorDate = HSSFDateUtil.getJavaDate(new Double(valor));
									valor = com.osmosyscol.commons.utils.StringUtils.toString(valorDate);

								} catch (Exception e) {
								}

							}

							if (tipoCampo.getPatron_validacion() != null && !valor.matches(tipoCampo.getPatron_validacion())) {
								errores.add("Fila " + (f - 1) + ". El campo " + campo.getNombre() + " no corresponde con el tipo " + tipoCampo.getNombre());
							}

							// TODO Validacion de formato de campo.

							if ((campo.getPatronvalidacion() != null) && (!valor.matches(campo.getPatronvalidacion()))) {
								errores.add("Fila " + (f - 1) + ". El campo " + campo.getNombre() + " no corresponde con el patron " + campo.getPatronvalidacion());
							}

							if (Constantes.SI.equals(campo.getObligatorio())) {
								if (StringUtils.isEmpty(valor)) {
									errores.add("Fila " + (f - 1) + ". El campo " + campo.getNombre() + " es obligatorio");
								}
							}

							if (StringUtils.isNotEmpty(valor)) {
								campoValor.setValor(valor);
								hayValores = true;
							}
							valores.add(campoValor);

						}
					}

				}

				HSSFCell cell = fila.getCell((short) (campos.size() + 1));
				if (cell != null) {
					String id_registro = cell.getRichStringCellValue().getString();
					if (StringUtils.isNotEmpty(id_registro)) {
						registro.setId_registro(Integer.valueOf(id_registro));
					}
				}
				registros.add(registro);
			}

		}

		return registros;
	}

	/**
	 * Crea el archivo excel de la estructura para edición de datos
	 * 
	 * @param id_estructura
	 *            --
	 * @param descargarDatos
	 *            -Determina si se descargan los datos existentes
	 * @return
	 * @author oaortiz
	 */
	public String crearArchivoExcel(Integer id_estructura, Boolean descargarDatos) {

		try {

			String rutaBase = ParametrosInicio.getProperty("file.carpeta");

			if (StringUtils.isEmpty(rutaBase)) {
				SimpleLogger.setError("Error de configuración, la propiedad file.carpeta no es valida.");
				return null;
			}

			String archivo = rutaBase + "/" + "datos_estructura_" + id_estructura + ".xls";

			HSSFWorkbook wb = crearWorkBook();

			Map<String, HSSFCellStyle> estilos = crearEstilos(wb);

			crearHoja(id_estructura, wb, estilos, descargarDatos);

			guardarWorkBook(wb, archivo);

			return archivo;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	private void crearHoja(Integer id_estructura, HSSFWorkbook wb, Map<String, HSSFCellStyle> estilos, Boolean descargarDatos) {

		Estructura estructura = EstructuraServicio.getInstance().obtenerEstructura(id_estructura);
		List<Campo> campos = DatosEstructuraServicio.getInstance().obtenerCamposPorEstructura(id_estructura);

		HSSFSheet newsheet = wb.createSheet(estructura.getNombre());
		newsheet.setDisplayRowColHeadings(false);
		newsheet.setColumnWidth((short) 0, (short) 1500);
		newsheet.setDefaultColumnWidth((short) 25);
		newsheet.setDisplayGridlines(false);

		short columnas = (short) (campos.size() + 1);
		for (short h = columnas; h <= 255; h++) {
			newsheet.setColumnHidden(h, true);
		}

		crearDatosHoja(wb, newsheet, estructura, campos, estilos, descargarDatos);

		newsheet.protectSheet("meconio3");

	}

	private void crearDatosHoja(HSSFWorkbook wb, HSSFSheet sheet, Estructura estructura, List<Campo> campos, Map<String, HSSFCellStyle> estilos, Boolean descargarDatos) {

		// Crear el encabezado
		HSSFRow rowEncabezado = getRow(sheet, 1);

		HSSFCellStyle estiloTitulo = estilos.get("tituloId");
		HSSFCell cell = rowEncabezado.createCell((short) 0);
		cell.setCellValue(new HSSFRichTextString("ID"));
		cell.setCellStyle(estiloTitulo);

		estiloTitulo = estilos.get("titulo");

		// Primera fila oculta
		HSSFRow row = getRow(sheet, 0);
		row.setHeight((short) 0);

		// Id de la estructura
		HSSFCell cc = row.createCell((short) 0);
		cc.setCellValue(new HSSFRichTextString("T" + estructura.getId_estructura()));

		// Encabezado y nombres de campos ocultos
		for (int i = 0; i < campos.size(); i++) {

			Campo campo = campos.get(i);
			// Titulos
			cell = rowEncabezado.createCell((short) (i + 1));
			cell.setCellStyle(estiloTitulo);
			String nombre = "";

			if (Constantes.SI.equals(campo.getObligatorio())) {
				nombre = "(*) ";
			}

			nombre += campo.getNombre();

			cell.setCellValue(new HSSFRichTextString(nombre));

			// Id de campos ocultos
			cell = row.createCell((short) (i + 1));
			cell.setCellValue(new HSSFRichTextString("C" + campo.getId_campo()));
			cell.setCellStyle(estilos.get("oculto"));

			if (campo.getId_lista_valores() != null || campo.getId_estructurarelacionada() != null) {

				crearListado(sheet, (short) (i + 1), campo, estilos);

			}

		}

		HSSFCellStyle estiloEditable = estilos.get("editable");
		for (short i = 0; i < REGISTROS; i++) {

			row = getRow(sheet, i + 2);
			cell = row.createCell((short) 0);
			cell.setCellStyle(estilos.get("constante"));
			cell.setCellValue(new HSSFRichTextString("" + (i + 1)));

			for (int j = 0; j < campos.size(); j++) {
				Campo campo = campos.get(j);
				TipoCampo tipoCampo = TipoCampoServicio.getInstance().obtenerTipoCampo(campo.getId_tipocampo());

				if (tipoCampo != null) {
					estiloEditable = CargaServicio.getInstance().obtenerEstiloEditable(tipoCampo.getTipo_dato(), wb);
				}

				cell = row.createCell((short) (j + 1));
				cell.setCellStyle(estiloEditable);
			}

		}

		List<Map<String, Object>> datos = null;
		if (descargarDatos) {
			datos = DatosEstructuraServicio.getInstance().obtenerDatosEstructura(estructura.getId_estructura());
		}

		if (datos != null) {

			for (int i = 0; i < datos.size(); i++) {

				row = getRow(sheet, i + 2);
				Map<String, Object> registro = datos.get(i);

				for (int j = 0; j < campos.size(); j++) {

					Campo campo = campos.get(j);

					cell = row.createCell((short) (j + 1));
					cell.setCellStyle(estilos.get("editable"));

					TipoCampo tipoCampo = TipoCampoServicio.getInstance().obtenerTipoCampo(campo.getId_tipocampo());
					Object valorCol = registro.get("C" + campo.getId_campo());
					if (valorCol != null) {
						if ("Integer".equals(tipoCampo.getTipo_dato()) || "Long".equals(tipoCampo.getTipo_dato())) {
							cell.setCellValue(new Long(com.osmosyscol.commons.utils.StringUtils.toString(valorCol)));
						}

						else if ("Float".equals(tipoCampo.getTipo_dato()) || "Double".equals(tipoCampo.getTipo_dato()) || "BigDecimal".equals(tipoCampo.getTipo_dato())) {
							cell.setCellValue(new Double(com.osmosyscol.commons.utils.StringUtils.toString(valorCol)));
						}

						else if (campo.getId_estructurarelacionada() != null || campo.getId_lista_valores() != null) {
							Object visualizacionCol = obtenerValorListado(campo, valorCol.toString());
							String valorFinalCol = "[" + valorCol + "] " + visualizacionCol;
							cell.setCellValue(new HSSFRichTextString(valorFinalCol));
						} else {
							cell.setCellValue(new HSSFRichTextString(com.osmosyscol.commons.utils.StringUtils.toString(valorCol)));
						}
					}

				}

				// Agregar el id del registro en la siguiente columna
				cell = row.createCell((short) (campos.size() + 1));
				cell.setCellValue(new HSSFRichTextString(registro.get("ID").toString()));

			}
		}

	}

	private String obtenerValorListado(Campo campo, String id) {

		if (campo.getId_lista_valores() != null) {
			List<ValorLista> valores = campo.getValores();

			for (ValorLista valorLista : valores) {
				if (valorLista.getId().equals(id)) {
					return valorLista.getNombre();
				}
			}
		} else if (campo.getId_estructurarelacionada() != null) {
			List<Map<String, Object>> datos = campo.getDatos();
			for (Map<String, Object> map : datos) {
				if (map.get("ID").toString().equals(id))
					return (String) map.get("VISUALIZACION");
			}
		}
		return "";

	}

	private void crearListado(HSSFSheet sheet, short columna, Campo campo, Map<String, HSSFCellStyle> estilos) {

		Integer numreg = 0;
		short numcolumna = (short) (255 - columna);

		if (campo.getId_lista_valores() != null) {

			List<ValorLista> valores = campo.getValores();

			for (short i = 1; i <= valores.size(); i++) {
				HSSFRow row = getRow(sheet, i + 1);
				HSSFCell ccd = row.createCell(numcolumna);
				ccd.setCellStyle(estilos.get("oculto"));

				ccd.setCellValue(new HSSFRichTextString("[" + valores.get(i - 1).getId() + "] " + valores.get(i - 1).getNombre()));

			}
			numreg = valores.size();

		}

		else if (campo.getId_estructurarelacionada() != null) {

			List<Map<String, Object>> datos = campo.getDatos();

			for (int i = 1; i <= datos.size(); i++) {

				Map<String, Object> map = datos.get(i - 1);
				HSSFRow row = getRow(sheet, i + 1);
				HSSFCell ccd = row.createCell(numcolumna);
				ccd.setCellStyle(estilos.get("oculto"));

				ccd.setCellValue(new HSSFRichTextString("[" + map.get("ID") + "] " + map.get("VISUALIZACION")));

			}
			numreg = datos.size();
		}

		if (numreg > 0) {
			// Coloca validacion en el campo

			Integer registrosValidar = REGISTROS + 1;

			String ncol = com.osmosyscol.commons.utils.StringUtils.convertirAColExcel(numcolumna + 1);
			String formu = "$" + ncol + "$3:$" + ncol + "$" + (numreg + 2);

			CellRangeAddressList regions = new CellRangeAddressList((short) 2, registrosValidar.shortValue(), columna, columna);
			DataValidationConstraint constraint = DVConstraint.createFormulaListConstraint(formu);
			HSSFDataValidation data_validation = new HSSFDataValidation(regions, constraint);

			data_validation.setEmptyCellAllowed(true);
			data_validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
			data_validation.setSuppressDropDownArrow(false);
			data_validation.createErrorBox("DataSuite", "El valor ingresado no es valido, Seleccione un elemento de la lista.");

			sheet.addValidationData(data_validation);

		}

	}

	private void guardarWorkBook(HSSFWorkbook wb, String archivo) throws IOException {

		FileOutputStream fileOut = new FileOutputStream(archivo);

		wb.write(fileOut);
		fileOut.close();

	}

	private HSSFWorkbook crearWorkBook() {
		HSSFWorkbook wb = new HSSFWorkbook();
		return wb;

	}

	private Map<String, HSSFCellStyle> crearEstilos(HSSFWorkbook wb) {

		HSSFFont fontTitulo = wb.createFont();
		fontTitulo.setColor(HSSFColor.WHITE.index);
		fontTitulo.setFontHeight((short) 180);
		fontTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

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

		Map<String, HSSFCellStyle> styles = new HashMap<String, HSSFCellStyle>();
		styles.put("titulo", styleTitulo);
		styles.put("tituloId", styleTituloId);
		styles.put("editable", editStyle);
		styles.put("constante", constStyle);
		styles.put("oculto", ocultoStyle);

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

	private static HSSFRow getRow(HSSFSheet sheet, int index) {
		HSSFRow row = sheet.getRow(index);
		if (row == null) {
			row = sheet.createRow(index);
		}

		return row;
	}

	class HiloCargarArchivoExcel implements Runnable {

		private Integer id_estructura;
		private String archivo;
		private Boolean reemplazarTodo;
		private Mensaje mensaje;

		public HiloCargarArchivoExcel(Integer id_estructura, String archivo, Boolean reemplazarTodo, Mensaje mensaje) {
			this.id_estructura = id_estructura;
			this.archivo = archivo;
			this.reemplazarTodo = reemplazarTodo;
			this.mensaje = mensaje;
		}

		public void run() {
			EstructuraExcelServicio.getInstance().cargarArchivoExcel(id_estructura, archivo, reemplazarTodo, mensaje);
		}

	}
}
