package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto.Producto;

public class ProductoServicio {

	private static ProductoServicio productoServicio;

	private ProductoServicio() {
	}

	public static ProductoServicio getInstance() {
		if (productoServicio == null) {
			productoServicio = new ProductoServicio();
		}
		return productoServicio;
	}

	// ---------------------------------------------------------------

	public List<Producto> obtenerProductos() {

		try {

			String sql = "select p.$PRODUCTO.ID$ codigo, p.*, lp.$LINEA DE PRODUCTO.NOMBRE$ nombreNegocio " //
					+ " from $PRODUCTO$ p, $LINEA DE PRODUCTO$ lp " //
					+ " WHERE lp.ID = p.$PRODUCTO.LINEA DE PRODUCTO$";

			return DS_SqlUtils.queryForList(Producto.class, sql);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProductoServicio.obtenerProductos", e);
		}

		return null;

	}

	// ---------------------------------------------------------------

	public Producto obtenerProducto(Integer idProducto) {

		try {

			String sql = "select p.$PRODUCTO.ID$ codigo,  p.*, lp.$LINEA DE PRODUCTO.NOMBRE$ nombreNegocio " //
					+ " from $PRODUCTO$ p, $LINEA DE PRODUCTO$ lp " //
					+ " WHERE lp.ID = p.$PRODUCTO.LINEA DE PRODUCTO$ AND p.id = " + idProducto;

			return DS_SqlUtils.queryForObject(Producto.class, sql);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProductoServicio.obtenerProducto", e);
		}

		return null;

	}

	// ---------------------------------------------------------------

	public Producto obtenerProductoPorcodigo(String codigo) {

		try {

			String sql = "select p.$PRODUCTO.ID$ codigo,  p.*, lp.$LINEA DE PRODUCTO.NOMBRE$ nombreNegocio " //
					+ " from $PRODUCTO$ p, $LINEA DE PRODUCTO$ lp " //
					+ " WHERE lp.ID = p.$PRODUCTO.LINEA DE PRODUCTO$ AND p.$PRODUCTO.ID$ =  $S(" + codigo + ")$";

			return DS_SqlUtils.queryForObject(Producto.class, sql);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProductoServicio.obtenerProducto", e);
		}

		return null;

	}

	// ---------------------------------------------------------------

	public List<Producto> productosPorCliente(Integer idCliente) {
		try {

			String sql = "select distinct p.$PRODUCTO.ID$ codigo, p.*, lp.$LINEA DE PRODUCTO.NOMBRE$ nombreNegocio " //
					+ " from $ENCARGO$ e, $PRODUCTO$ p, $LINEA DE PRODUCTO$ lp " //
					+ " WHERE " //
					+ " lp.ID = p.$PRODUCTO.LINEA DE PRODUCTO$ " //
					+ " and e.$ENCARGO.PRODUCTO$ = p.id " //
					+ " and e.$ENCARGO.ACTIVO$ = $B(true)$ " //
					+ " and e.$ENCARGO.ID CLIENTE$ = #id_cliente# ";

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id_cliente", idCliente);

			return DS_SqlUtils.queryForList(Producto.class, sql, map);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProductoServicio.productosPorCliente", e);
		}

		return null;
	}

	// ---------------------------------------------------------------
	
	public List<Producto> obtenerProductosActivos() {

		try {

			String sql = "select p.$PRODUCTO.ID$ codigo, p.*, lp.$LINEA DE PRODUCTO.NOMBRE$ nombreNegocio " //
					+ " from $PRODUCTO$ p, $LINEA DE PRODUCTO$ lp " //
					+ " WHERE lp.ID = p.$PRODUCTO.LINEA DE PRODUCTO$" //
					+ " AND $PRODUCTO.ACTIVO$ LIKE($B(true)$)";

			return DS_SqlUtils.queryForList(Producto.class, sql);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en ProductoServicio.obtenerProductos", e);
		}

		return null;

	}

	
	// ---------------------------------------------------------------

}
