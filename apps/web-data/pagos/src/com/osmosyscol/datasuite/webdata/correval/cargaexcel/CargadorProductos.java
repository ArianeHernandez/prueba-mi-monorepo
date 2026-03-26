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
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class CargadorProductos implements Cargador {

	public void cargar(String ruta, Integer idCargue, Map<Integer, Object> mapaRespuestas) {

		DaoManager daoManager = null;

		List<String> mensajes = new ArrayList<String>();
		Integer i = 0;

		try {

			InputStream inp = new FileInputStream(ruta);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));

			HSSFSheet sheet = wb.getSheet("PRODUCTO");
			if (sheet == null) {
				sheet = wb.getSheetAt(0);
			}

			String[][] buffer = CargaExcelServicio.getInstance().excelToBuffer(sheet, 6);
			DtoInfo info = new DtoInfo();

			info.setTotalRegistros(buffer.length - 1);
			mapaRespuestas.put(idCargue, info);

			Map<Integer, Integer> ids_negocio = new HashMap<Integer, Integer>();

			if (buffer != null) {
				daoManager = DaoConfig.getDaoManager(2);

				CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
				Integer insertados = 0;

				desactivarProductos(cargaDao);

				for (i = 1; i < buffer.length; i++) {

					Map<String, Object> producto = leerProducto(mensajes, i, buffer[i], cargaDao, ids_negocio);
					if (producto != null) {
						Integer idProducto = buscarProductoPorCodigo((String) producto.get("$PRODUCTO.ID$"), cargaDao);

						producto.put("IDCARGA", 0);
						producto.put("$PRODUCTO.ACTIVO$", Crypto.E(new Boolean(true)));
						Boolean rta;
						if (idProducto == null) {
							idProducto = cargaDao.obtenerSiguienteCreadatos();
							producto.put("ID", idProducto);
							rta = insertarProducto(producto, cargaDao);

						} else {
							rta = actualizarProducto(producto, idProducto, cargaDao);
						}
						if (!rta) {
							mensajes.add(i + " - No fue posible insertar el registro");
						} else {
							insertados++;
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

	private void desactivarProductos(CargaDao cargaDao) {

		String sql = "UPDATE $PRODUCTO$ SET $PRODUCTO.ACTIVO$ = '" + Crypto.E(new Boolean(false)) + "' WHERE IDCARGA = 0";
		sql = RDServicio.reemplazarNombres(sql);

		cargaDao.updateSQL(sql);

	}

	private Boolean actualizarProducto(Map<String, Object> producto, Integer idProducto, CargaDao cargaDao) {
		try {
			Map<String, Object> mapWhere = new HashMap<String, Object>();
			mapWhere.put("ID", idProducto);

			String sql = SQLServicio.createSQLUpdate("$PRODUCTO$", producto, mapWhere);
			sql = RDServicio.reemplazarNombres(sql);
			cargaDao.updateSQL(sql);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	private Boolean insertarProducto(Map<String, Object> producto, CargaDao cargaDao) {
		try {
			String sql = SQLServicio.createSQLInsert("$PRODUCTO$", producto);
			sql = RDServicio.reemplazarNombres(sql);

			cargaDao.insertSQL(sql);
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	public Integer buscarProductoPorCodigo(String id, CargaDao cargaDao) {

		String sql = "SELECT $PRODUCTO$.ID FROM $PRODUCTO$ WHERE $PRODUCTO.ID$ = '" + id + "'";

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

	public Map<String, Object> leerProducto(List<String> mensajes, Integer i, String[] fila, CargaDao cargaDao, Map<Integer, Integer> ids_negocio) {

		Map<String, Object> map = new HashMap<String, Object>();

		if (StringUtils.esVacio(fila[0])) {
			mensajes.add(i + " - El negocio es vacio");
		}

		Integer negocio = buscarNegocioPorCodigo(new Integer(fila[0].trim()), cargaDao, ids_negocio);

		if (negocio == null) {
			mensajes.add(i + " - No se encuentra el negocio: " + fila[0]);
			return null;
		}

		// Linea de producto o negocio
		map.put("$PRODUCTO.LINEA DE PRODUCTO$", negocio);

		map.put("$PRODUCTO.ID$", Crypto.E(fila[1]));

		map.put("$PRODUCTO.NOMBRE$", Crypto.E(fila[2]));

		return map;

	}

	public Integer buscarNegocioPorCodigo(Integer codigo, CargaDao cargaDao, Map<Integer, Integer> ids_negocio) {

		if (ids_negocio.containsKey(codigo)) {
			return ids_negocio.get(codigo);
		}

		String sql = "SELECT ID FROM $LINEA DE PRODUCTO$ WHERE $LINEA DE PRODUCTO.ID$ = '" + Crypto.E(codigo) + "'";
		sql = RDServicio.reemplazarNombres(sql);

		List<Map<String, Object>> lista = cargaDao.selectSql(sql);
		Integer id_negocio = null;
		if (lista != null && lista.size() > 0) {
			Map<String, Object> map = lista.get(0);
			Object id = map.get("ID");
			if (id instanceof Integer) {
				id_negocio = (Integer) id;
			} else {
				id_negocio = new Integer(id.toString());
			}
		}
		ids_negocio.put(codigo, id_negocio);

		return id_negocio;
	}

	public String getCodigo() {
		return "3";
	}

}
