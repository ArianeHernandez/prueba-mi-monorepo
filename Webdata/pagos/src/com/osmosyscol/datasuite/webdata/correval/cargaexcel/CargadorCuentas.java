package com.osmosyscol.datasuite.webdata.correval.cargaexcel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class CargadorCuentas implements Cargador {

	public void cargar(String ruta, Integer idCargue, Map<Integer, Object> mapaRespuestas) {

		DaoManager daoManager = null;

		List<String> mensajes = new ArrayList<String>();
		Integer i = 0;

		try {

			InputStream inp = new FileInputStream(ruta);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

			HSSFSheet sheet = wb.getSheet("CUENTA");
			if (sheet == null) {
				sheet = wb.getSheetAt(0);
			}

			String[][] buffer = CargaExcelServicio.getInstance().excelToBuffer(sheet, 14);
			DtoInfo info = new DtoInfo();

			info.setTotalRegistros(buffer.length - 1);
			mapaRespuestas.put(idCargue, info);

			if (buffer != null) {

				daoManager = DaoConfig.getDaoManager(2);

				CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);

				CargadorProductos cargadorProductos = new CargadorProductos();

				// desactivar solamente las cuentas de los usuarios en el excel...
				//desactivarCuentas(cargaDao);
				

				Map<String, Integer> cacheProductos = new HashMap<String, Integer>();
				Map<String, Integer> cacheClientes = new HashMap<String, Integer>();
				List<Integer> clientesDesactivados = new ArrayList<Integer>();
				
				Integer insertados = 0;
				for (i = 1; i < buffer.length; i++) {
					Map<String, Object> cuenta = leerCuenta(mensajes, i, buffer[i]);
					if (cuenta != null) {
						Integer idCliente = buscarCliente(cuenta, cargaDao, cacheClientes);
						if (idCliente == null) {
							mensajes.add(i + " - No se encontro el cliente: " + cuenta.get("IDENTIFICACION") + " " + cuenta.get("TIPO_DOCUMENTO"));
						} else {
							cuenta.remove("IDENTIFICACION");
							cuenta.remove("TIPO_DOCUMENTO");
							
							Integer idProducto =null;
							if(cacheProductos.containsKey(cuenta.get("$ENCARGO.PRODUCTO$"))){
								idProducto = cacheProductos.get(cuenta.get("$ENCARGO.PRODUCTO$"));
							}else{
								idProducto = cargadorProductos.buscarProductoPorCodigo((String) cuenta.get("$ENCARGO.PRODUCTO$"), cargaDao);
								cacheProductos.put((String)cuenta.get("$ENCARGO.PRODUCTO$"), idProducto);
							}

							if (idProducto != null) {
								
								// primero toca desactivar los encargos de la persona seleccionada
								desactivarCuentasUsuariosEnArchivo(cargaDao, buffer[i], mensajes, i, clientesDesactivados);
								if(!clientesDesactivados.contains(idCliente))
									clientesDesactivados.add(idCliente);
								
								Integer idCuenta = buscarCuenta(cuenta, idCliente, cargaDao);

								cuenta.put("$ENCARGO.PRODUCTO$", idProducto);

								cuenta.put("IDCARGA", 0);
								cuenta.put("$ENCARGO.ACTIVO$", Crypto.E(new Boolean(true)));
								cuenta.put("$ENCARGO.ID CLIENTE$", Crypto.E(idCliente));
								Boolean rta;
								if (idCuenta == null) {
									idCuenta = cargaDao.obtenerSiguienteCreadatos();
									cuenta.put("ID", idCuenta);
									rta = insertarCuenta(cuenta, cargaDao);

								} else {
									rta = actualizarCuenta(cuenta, idCuenta, cargaDao);
								}
								if (rta) {
									insertados++;
								} else {
									mensajes.add(i + " - No fue posible insertar el registro");
								}
							} else {
								mensajes.add(i + " - No se encontro el producto: " + Crypto.DF(cuenta.get("$ENCARGO.PRODUCTO$")));
							}
						}

					}
					info.setRegistrosCargados(i);

				}
				i--;
				if (insertados > 0) {
					mensajes.add("Se leyerón " + i + " registros del archivo");
					mensajes.add("Se actualizarón o insertaron " + insertados);
				} else {
					mensajes.add("No se actualizarón registros");
				}
			}
			SimpleLogger.setInfo(mensajes.toString());
		} catch (Exception e) {
			mensajes.add("Ocurrio un error al cargar la información, cargando el registro " + i);
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		mapaRespuestas.put(idCargue, mensajes);

	}

	private void desactivarCuentasUsuariosEnArchivo(CargaDao cargaDao, String[] usuario_info, List<String> mensajes, Integer index, List<Integer> clientesDesactivados) {
		
		Map<String, Integer> cacheClientes = new HashMap<String, Integer>();	
		
			Map<String, Object> cuenta = leerCuenta(mensajes, index, usuario_info);
			if (cuenta != null) {
				Integer idCliente = buscarCliente(cuenta, cargaDao, cacheClientes);
				if (idCliente != null && !clientesDesactivados.contains(idCliente)) {
					String sql = "UPDATE $ENCARGO$ SET $ENCARGO.ACTIVO$ = '" + Crypto.E(new Boolean(false)) +
					"' WHERE IDCARGA = 0 " +
					"AND $ENCARGO.ID CLIENTE$ = '" + Crypto.E(idCliente) + "'";
					
					sql = RDServicio.reemplazarNombres(sql);

					cargaDao.updateSQL(sql);
					
				}
			}
		
		
	}

	private Boolean actualizarCuenta(Map<String, Object> cuenta, Integer idCuenta, CargaDao cargaDao) {
		try {
			Map<String, Object> mapWhere = new HashMap<String, Object>();
			mapWhere.put("ID", idCuenta);

			String sql = SQLServicio.createSQLUpdate("$ENCARGO$", cuenta, mapWhere);
			sql = RDServicio.reemplazarNombres(sql);
			cargaDao.updateSQL(sql);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	private Boolean insertarCuenta(Map<String, Object> cuenta, CargaDao cargaDao) {
		try {
			String sql = SQLServicio.createSQLInsert("$ENCARGO$", cuenta);
			sql = RDServicio.reemplazarNombres(sql);

			cargaDao.insertSQL(sql);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	private void desactivarCuentas(CargaDao cargaDao) {

		String sql = "UPDATE $ENCARGO$ SET $ENCARGO.ACTIVO$ = '" + Crypto.E(new Boolean(false)) + "' WHERE IDCARGA = 0";
		sql = RDServicio.reemplazarNombres(sql);

		cargaDao.updateSQL(sql);
	}
	
	

	private Integer buscarCuenta(Map<String, Object> cuenta, Integer idCliente, CargaDao cargaDao) {
		String sql = "SELECT $ENCARGO$.ID FROM $ENCARGO$ WHERE " + "$ENCARGO.CODIGO$ = '" + cuenta.get("$ENCARGO.CODIGO$") + "'" + " AND $ENCARGO.ID CLIENTE$ = '"
				+ Crypto.E(idCliente) + "'";
		sql = RDServicio.reemplazarNombres(sql);

		List<Map<String, Object>> lista = cargaDao.selectSql(sql);
		if (lista != null && lista.size() > 0) {
			Map<String, Object> map = lista.get(0);
			Object idr = map.get("ID");
			if (idr instanceof Integer) {
				return (Integer) idr;
			}
			return new Integer(idr.toString());

		}

		return null;
	}

	private Integer buscarCliente(Map<String, Object> cuenta, CargaDao cargaDao, Map<String, Integer> cacheClientes) {

		String identificacion = (String) cuenta.get("IDENTIFICACION");
		Integer tipo_documento = (Integer) cuenta.get("TIPO_DOCUMENTO");
		
		String key = identificacion + "|" +tipo_documento;
		
		if(cacheClientes.containsKey(key)){
			return cacheClientes.get(key);
		}

		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(identificacion, tipo_documento);
		if (usuario != null) {
			cacheClientes.put(key, usuario.getId_usuario());
			return usuario.getId_usuario();
		}

		return null;
	}

	public Map<String, Object> leerCuenta(List<String> mensajes, Integer i, String[] fila) {

		Map<String, Object> map = new HashMap<String, Object>();

		Integer numero;

		// Numero de documento
		if (StringUtils.esVacio(fila[0])) {
			mensajes.add(i + " - El número de documento es vacio");
			return null;
		}
		map.put("IDENTIFICACION", fila[0]);

		numero = CargaExcelServicio.leerTipoDocumento(fila[2]);

		if (numero == null) {
			mensajes.add(i + " - Tipo de documento no válido: " + fila[2]);
			return null;
		}

		map.put("TIPO_DOCUMENTO", numero);

		if (StringUtils.esVacio(fila[4])) {
			mensajes.add(i + " - El producto es vacío");
			return null;
		}

		// map.put("NEGOCIO", fila[3]);
		map.put("$ENCARGO.PRODUCTO$", Crypto.E(fila[4]));

		if (StringUtils.esVacio(fila[5])) {
			mensajes.add(i + " - Cuenta es vacía");
			return null;
		}

		String dato = fila[5];
		if (StringUtils.esNoVacio(fila[6])) {
			dato += " - " + fila[6];
		}
		if (StringUtils.esNoVacio(fila[7])) {
			dato += " - " + fila[7];
		}
		if (StringUtils.esNoVacio(fila[8])) {
			dato += " - " + fila[8];
		}
		if (StringUtils.esNoVacio(fila[9])) {
			dato += " - " + fila[9];
		}	
		if (StringUtils.esNoVacio(fila[10])) {
			dato += " - " + fila[10];
		}

		map.put("$ENCARGO.CODIGO$", Crypto.E(dato));

		map.put("$ENCARGO.SALDO TOTAL$", Crypto.E(fila[11]));

		map.put("$ENCARGO.SALDO DISPONIBLE$", Crypto.E(fila[12]));

		return map;

	}

	public String getCodigo() {
		return "2";
	}

}
