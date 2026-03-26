package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.MapCache;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datapi.logica.dto.Conexion;
import com.osmosyscol.datapi.logica.dto.Elemento;
import com.osmosyscol.datapi.logica.dto.Operacion;
import com.osmosyscol.datapi.logica.dto.Parametro;
import com.osmosyscol.datapi.logica.dto.Resultado;
import com.osmosyscol.datapi.persistencia.dao.ibatis.core.ManejadorConsultas;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.servicios.CreaDatosServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ListaValoresDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.webservices.WSListaValoresSoapBindingStub;
import com.osmosyscol.datasuite.wsclient.procedimientolv.ProcedimientoLVSoapBindingStub;
import com.osmosyscol.datasuite.wsclient.procedimientolv.Valor;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ListaValoresServicio {

	private static ListaValoresServicio listaValoresServicio;

	private final Integer MAXIMA_CAPACIDAD_CACHE = 50;
	// Integer, List<ValorLista>
	private MapCache<Integer, List<ValorLista>> cacheValoresLista = new MapCache<Integer, List<ValorLista>>(MAXIMA_CAPACIDAD_CACHE);

	private final String SERVICIOWEB_WEBDATA = "services/WSListaValores";

	private ListaValoresServicio() {
	}

	public static ListaValoresServicio getInstance() {
		if (listaValoresServicio == null) {
			listaValoresServicio = new ListaValoresServicio();
		}
		return listaValoresServicio;
	}

	// ------------------------------

	public List<ListaValores> obtenerListaValoresPorPersona(Integer id_modelo, Integer numeroPagina) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaValoresDao listaValoresDao = (ListaValoresDao) daoManager.getDao(ListaValoresDao.class);

			return listaValoresDao.obtenerListaValoresPorPersona(id_modelo, numeroPagina);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Integer totalListaValoresPorPersona(Integer id_negocio) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaValoresDao listaValoresDao = (ListaValoresDao) daoManager.getDao(ListaValoresDao.class);

			return listaValoresDao.totalListaValoresPorPersona(id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public ListaValores obtenerListaValoresPorNombre(String nombre) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaValoresDao listaValoresDao = (ListaValoresDao) daoManager.getDao(ListaValoresDao.class);

			return listaValoresDao.obtenerListaValoresPorNombre(nombre);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public ListaValores obtenerListaValores(Integer id_listavalores) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaValoresDao listaValoresDao = (ListaValoresDao) daoManager.getDao(ListaValoresDao.class);

			return listaValoresDao.obtenerListaValores(id_listavalores);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public ListaValores guardarListaValores(ListaValores listaValores, List<ValorLista> valoresLista, Integer id_persona, Integer id_modelo) {

		CreaDatosServicio creaDatosServicio = CreaDatosServicio.getInstance();

		if (listaValores != null) {
			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				DaoManager daoManager2 = DaoConfig.getDaoManager(2);

				ListaValoresDao listaValoresDao = (ListaValoresDao) daoManager.getDao(ListaValoresDao.class);

				listaValores = listaValoresDao.guardarListaValores(listaValores, id_persona, id_modelo);
				creaDatosServicio.crearTabla(daoManager, daoManager2, listaValores);

				// Se agregan los valores consultados desde la base de datos
				List<ValorLista> listaBD = obtenerValoresBD(listaValores);
				if (listaBD != null) {
					valoresLista.addAll(listaBD);
				}

				actualizarValoresLV(listaValores.getId_lista_valores(), valoresLista);
				FormatoServicio.getInstance().borrarFormatoXListaValores(listaValores.getId_lista_valores());
				return listaValores;

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un Error en el servicio ( ListaValoresServicio.guardarListaValores ): " + listaValores, e);
			}
		}
		return null;
	}

	public List<ValorLista> obtenerValoresLV(Integer id_listavalores) {
		DaoManager daoManager;
		DaoManager daoManager2;
		try {
			daoManager = DaoConfig.getDaoManager();
			daoManager2 = DaoConfig.getDaoManager(2);

			ListaValoresDao listaValoresDao = (ListaValoresDao) daoManager.getDao(ListaValoresDao.class);
			ListaValoresDao listaValoresDao2 = (ListaValoresDao) daoManager2.getDao(ListaValoresDao.class);

			ListaValores listavalores = listaValoresDao.obtenerListaValores(id_listavalores);

			if (listavalores != null && cacheValoresLista.contains(id_listavalores)) {
				return (List<ValorLista>) cacheValoresLista.get(id_listavalores);
			}

			List<ValorLista> valoresLV = listaValoresDao2.obtenerValoresLV(listavalores);
			cacheValoresLista.put(id_listavalores, valoresLV);

			return valoresLV;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio ( ListaValoresServicio.obtenerValoresLV ): " + id_listavalores, e);
		}
		return null;
	}

	public Boolean actualizarValoresLV(Integer id_listavalores, List<ValorLista> valoresLista) {

		DaoManager daoManager2 = DaoConfig.getDaoManager(2);

		try {

			daoManager2.startTransaction();

			ListaValoresDao listaValoresDao1 = (ListaValoresDao) DaoConfig.getDao(ListaValoresDao.class);
			ListaValoresDao listaValoresDao2 = (ListaValoresDao) daoManager2.getDao(ListaValoresDao.class);

			ListaValores listaValores = listaValoresDao1.obtenerListaValores(id_listavalores);

			TipoCampo tipocampo = TipoCampoServicio.getInstance().obtenerTipoCampo(listaValores.getId_tipocampo());

			Boolean exitoso = listaValoresDao2.actualizarValoresLV(listaValores, tipocampo, valoresLista);

			daoManager2.commitTransaction();

			actualizarWebData(id_listavalores);
			
			List<ValorLista> valoresLimpios = new ArrayList<ValorLista>();

			for (ValorLista valorLista : valoresLista) {
				if(valorLista!=null && StringUtils.isNotBlank(valorLista.getId())){
					valoresLimpios.add(valorLista);
				}
			}
			
			
			if (cacheValoresLista.get(id_listavalores) != null) {
				cacheValoresLista.put(id_listavalores, valoresLimpios);
			}

			return exitoso;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio ( ListaValoresServicio.obtenerValoresLV )", e);
		} finally {
			daoManager2.endTransaction();
		}
		return false;
	}

	private void actualizarWebData(Integer id_listavalores) {

		try {

			String endpoint = ParametrosInicio.getProperty("webdata.endpoint") + "/" + SERVICIOWEB_WEBDATA;
			URL url = new URL(endpoint);

			WSListaValoresSoapBindingStub stub = new WSListaValoresSoapBindingStub(url, null);

			stub.actualizarListaValores(id_listavalores);

		} catch (Exception e) {
			SimpleLogger.setError("Error actualizando lista de valores en webdata", e);
		}
	}

	// ------------------------------

	public Boolean eliminarListaValores(Integer id_lista_valores) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaValoresDao listaValoresDao = (ListaValoresDao) daoManager.getDao(ListaValoresDao.class);

			return listaValoresDao.eliminarListaValores(id_lista_valores);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	// ------------------------------
	public List<ListaValores> obtenerListasDeValores(Integer id_modelo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ListaValoresDao listaValoresDao = (ListaValoresDao) daoManager.getDao(ListaValoresDao.class);

			return listaValoresDao.obtenerListasDeValores(id_modelo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// -------------------------------
	public File generarArchivoDescarga(Integer id_lista_valores) {

		try {
			String titulo = "listaValores";

			String nombrecarpeta = ParametrosInicio.getProperty("file.carpeta") + "/LISTA_VALORES";
			new File(nombrecarpeta).mkdirs();

			String nombrearchivo = nombrecarpeta + "/" + titulo + ".xls";
			SimpleLogger.setInfo("Creando Archivo: " + nombrearchivo);

			// Si el archivo existe previamente se elimina el archivo
			File file_temp = new File(nombrearchivo);
			if (file_temp.exists()) {
				file_temp.delete();
			}

			HSSFWorkbook wb = new HSSFWorkbook();

			HSSFCellStyle styleCelda = wb.createCellStyle();
			styleCelda.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			styleCelda.setWrapText(true);
			styleCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			styleCelda.setBorderBottom((short) 1);
			styleCelda.setBorderLeft((short) 1);
			styleCelda.setBorderRight((short) 1);
			styleCelda.setBorderTop((short) 1);

			SimpleLogger.setInfo("Creando hoja: ");
			HSSFSheet newsheet = wb.createSheet(titulo);
			newsheet.setDefaultColumnWidth((short) 10);

			// Se crea el encabezado del archivo
			HSSFRow rowc0 = newsheet.createRow((short) 0);
			rowc0.setHeight((short) 500);

			HSSFCell ccel0 = rowc0.createCell((short) 0);
			ccel0.setCellValue(new HSSFRichTextString("Identificacion"));
			ccel0.setCellStyle(styleCelda);

			HSSFCell ccel1 = rowc0.createCell((short) 1);
			ccel1.setCellValue(new HSSFRichTextString("Nombre"));
			ccel1.setCellStyle(styleCelda);

			// Se crea el cuerpo del archivo
			List<ValorLista> valores = obtenerValoresLV(id_lista_valores);

			if (valores != null && valores.size() > 0) {

				int registro = 1;
				for (ValorLista valorLista : valores) {

					HSSFRow row = newsheet.createRow((short) registro);

					HSSFCell cel0 = row.createCell((short) 0);
					cel0.setCellValue(new HSSFRichTextString(valorLista.getId()));
					cel0.setCellStyle(styleCelda);

					HSSFCell cel1 = row.createCell((short) 1);
					cel1.setCellValue(new HSSFRichTextString(valorLista.getNombre()));
					cel1.setCellStyle(styleCelda);

					registro++;
				}
			}

			FileOutputStream fileOut = new FileOutputStream(nombrearchivo);

			wb.write(fileOut);
			fileOut.close();

			File file = new File(nombrearchivo);

			return file;

		} catch (Exception e) {
			return null;
		}
	}

	// ----------------------------
	public List<ValorLista> obtenerValoresPorArchivo(String rutaArchivo) {

		try {

			InputStream inp = new FileInputStream(rutaArchivo);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			HSSFSheet hoja1 = wb.getSheetAt(0);

			// Listado de respuestaBanco
			ArrayList<ValorLista> valoresPorArchivo = new ArrayList<ValorLista>();

			for (short i = 1; i <= hoja1.getLastRowNum(); i++) {
				try {

					HSSFRow fila = hoja1.getRow(i);

					HSSFCell celdaIdentificacion = fila.getCell((short) 0);
					String identificacion = getValorCelda(celdaIdentificacion);

					HSSFCell celdaNombre = fila.getCell((short) 1);
					String nombre = getValorCelda(celdaNombre);

					if (!identificacion.equals("") || !nombre.equals("")) {

						ValorLista valorLista = new ValorLista();
						valorLista.setId(identificacion);
						valorLista.setNombre(nombre);
						valorLista.setPosicion(new Integer(i));

						valoresPorArchivo.add(valorLista);
					}

				} catch (Exception e) {
					SimpleLogger.setError("Ha ocurrido un error", e);
				}

			}

			return valoresPorArchivo;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	private String getValorCelda(HSSFCell celda) {
		String valor = "";

		switch (celda.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			valor = celda.getRichStringCellValue().getString();
			break;

		case HSSFCell.CELL_TYPE_BOOLEAN:
			valor = celda.getBooleanCellValue() ? Constantes.SI : Constantes.NO;
			break;

		case HSSFCell.CELL_TYPE_NUMERIC:
			valor = new BigDecimal(celda.getNumericCellValue()).toString();
			break;

		default:
			break;
		}

		return valor;

	}

	private List<ValorLista> obtenerValoresBD(ListaValores listaValores) throws Exception {

			if (listaValores.getBase_datos() != null && StringUtils.esNoVacio(listaValores.getC_id(), listaValores.getC_nombre(), listaValores.getConsulta())) {

				if (listaValores.getConsulta().trim().startsWith("{")) {

					return ejecutarProcedimiento(listaValores);

				} else {

					DaoManager daoManager = DaoConfig.getDaoManager(listaValores.getBase_datos());
					ListaValoresDao listaValoresDao = (ListaValoresDao) daoManager.getDao(ListaValoresDao.class);

					String sql = "select " + listaValores.getC_id() + " as ID, " + listaValores.getC_nombre() + " as NOMBRE" + " FROM " + listaValores.getConsulta();

					sql = RDServicio.reemplazarNombres(sql);

					SimpleLogger.setDebug("SQL ==> " + sql);

					return listaValoresDao.obtenerValoresBD(sql);
				}

			}
			return null;

	}

	public Boolean actualizarValoresBD(ListaValores listaValores) {
		try {

			List<ValorLista> valoresLista = obtenerValoresBD(listaValores);

			return actualizarValoresLV(listaValores.getId_lista_valores(), valoresLista);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	public Set<String> obtenerConexiones() {
		try {

			Set<String> conexiones =  DaoConfig.getPoolNames();
			
			Boolean activarSipPagos = StringUtils.esNoVacio(ParametrosInicio.getProperty("sippagos.endpoint"));
			
			if (activarSipPagos) {
				HashSet<String> set = new HashSet<String>();
				set.add("SIFI");
				set.addAll(conexiones);
				return set;
			}
			return conexiones;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<ValorLista> ejecutarProcedimiento(ListaValores listaValores) throws SQLException {
		

		if (com.osmosyscol.datasuite.config.Constantes.NOMBRE_CONEXION_SIPPAGOS.equals(listaValores.getBase_datos())) {
			SimpleLogger.setDebug("Consultando valores desde SipPagos");
			return obtenerLVSifi(listaValores);
		}
		
		Conexion conexion = new Conexion();
		conexion.setNombre(listaValores.getBase_datos());

		Operacion operacion = new Operacion();
		operacion.setProcedimiento(listaValores.getConsulta());

		Resultado resultado = new Resultado();
		resultado.setTipo(Parametro.TIPO_ARRAY);
		resultado.setOrden(1);
		resultado.setNombre("LISTADO");

		List<Elemento> elementos = new ArrayList<Elemento>();

		Elemento elemento = new Elemento();

		elemento.setId(listaValores.getC_id());
		elemento.setNombre(listaValores.getC_id());
		elemento.setTipo(Parametro.TIPO_STRING);

		elementos.add(elemento);

		elemento = new Elemento();

		elemento.setId(listaValores.getC_nombre());
		elemento.setNombre(listaValores.getC_nombre());
		elemento.setTipo(Parametro.TIPO_STRING);

		elementos.add(elemento);

		resultado.setElementos(elementos);

		List<Resultado> resultados = new ArrayList<Resultado>();
		resultados.add(resultado);

		operacion.setResultados(resultados);

		List<Map<String, Object>> lista = ManejadorConsultas.ejecutarProcedimiento(conexion, operacion, null);

		ArrayList<ValorLista> valoresLista = new ArrayList<ValorLista>();

		if (lista == null) {
			return null;
		}

		Map<String, Object> map = lista.get(0);

		ArrayList listado = (ArrayList) map.get("LISTADO");

		for (Object object : listado) {
			Map mapDatos = (Map) object;
			Set<String> keySet = mapDatos.keySet();

			ValorLista valorLista = new ValorLista();

			for (String key : keySet) {
				Object obj = mapDatos.get(key);
				if (key.toLowerCase().endsWith(listaValores.getC_id().toLowerCase())) {
					if (obj != null) {
						valorLista.setId(obj.toString());
					}
				} else if (key.toLowerCase().endsWith(listaValores.getC_nombre().toLowerCase())) {
					if (obj != null) {
						valorLista.setNombre(obj.toString());
					}
				}
			}

			valoresLista.add(valorLista);
		}
		return valoresLista;
	}

	public void eliminarListaValoresCache(Integer idListaValores) {
		cacheValoresLista.remove(idListaValores);
	}

	public List<ValorLista> obtenerLVSifi(ListaValores listaValores) {
		try {
			String endPoint = ParametrosInicio.getProperty("sippagos.endpoint");
			URL url = new URL(endPoint + "/services/ProcedimientoLV");

			ProcedimientoLVSoapBindingStub stub = new ProcedimientoLVSoapBindingStub(url, null);

			String consulta = listaValores.getConsulta();
			String id = listaValores.getC_id();
			String nombre = listaValores.getC_nombre();
			Valor[] salida = stub.ejecutarProcedimiento(consulta, id, nombre);

			if (!ArrayUtils.isEmpty(salida)) {

				List<ValorLista> valores = new ArrayList<ValorLista>();

				for (Valor valor : salida) {
					ValorLista valorLista = new ValorLista();
					
					valorLista.setId(valor.getId());
					valorLista.setNombre(valor.getNombre());
					valores.add(valorLista);
				}
				return valores;
				
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error", e);
		}
		return null;
	}

}
