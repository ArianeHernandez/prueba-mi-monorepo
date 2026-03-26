package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.util.ArrayList;
import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.correval.productocuenta.dto.ProductoEncargos;

public class ProductoCuentasServicio {

	// ----------------------------------------------------

	private static ProductoCuentasServicio confCuentaACH;

	// ----------------------------------------------------

	private ProductoCuentasServicio() {
	}

	// ----------------------------------------------------

	public static ProductoCuentasServicio getInstance() {
		if (confCuentaACH == null) {
			confCuentaACH = new ProductoCuentasServicio();
		}
		return confCuentaACH;
	}

	// ----------------------------------------------------

	public List<ProductoEncargos> obtenerProductosCuentas(String numeroDocumento, Integer tipoDocumento) {
		// solo cuentas activas ...
		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(numeroDocumento, tipoDocumento);
		List<ProductoEncargos> lista = new ArrayList<ProductoEncargos>();

		if (usuario != null) {
			String sql = "SELECT $ENCARGO.CODIGO$ cuenta, " //
					+ " $PRODUCTO.NOMBRE$ nombreProducto, " //
					+ " $PRODUCTO.ID$ codigoProducto, " //
					+ " $ENCARGO.SALDO DISPONIBLE$ saldo, " //
					+ " $LINEA DE PRODUCTO.NOMBRE$ nombreLinea, " //
					+ " $LINEA DE PRODUCTO.ID$ codigoLinea " //
					+ " FROM $ENCARGO$, $PRODUCTO$, $LINEA DE PRODUCTO$ " //
					+ " WHERE $ENCARGO.ID CLIENTE$ = $I(" + usuario.getId_usuario() + ")$ " //
					+ " AND $ENCARGO.PRODUCTO$ = $PRODUCTO$.ID " //
					+ " AND $PRODUCTO.LINEA DE PRODUCTO$ = $LINEA DE PRODUCTO$.ID " //
					+ " AND $PRODUCTO.ACTIVO$ = $B(true)$" //
					+ " AND $ENCARGO.ACTIVO$ = $B(true)$";

			lista = DS_SqlUtils.queryForList(ProductoEncargos.class, sql);
		}

		return lista;
	}

}
