package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto.ProductoCuentaDefecto;
import com.osmosyscol.datasuite.webdata.correval.confCuentaACH.dto.ProductoCuentas;
import com.osmosyscol.datasuite.webdata.correval.servicios.ConfCuentaACHServicio;

public class ConfCuentaACHJsonServicio implements JsonService {

	@SuppressWarnings("unused")
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	// --------------------------------------------------------

	public List<ProductoCuentas> obtenereProductosCuentas() {
		return ConfCuentaACHServicio.getInstance().obtenereProductosCuentas();
	}

	// --------------------------------------------------------

	public Boolean actualizarCuentaPorDefecto(Integer idProducto, Integer idCuenta) {
		return ConfCuentaACHServicio.getInstance().actualizarCuentaPorDefecto(idProducto, idCuenta);
	}

	// --------------------------------------------------------

	public Integer obtenerIdCuentaPorDefectoProducto(Integer id_producto){
		return ConfCuentaACHServicio.getInstance().obtenerIdCuentaPorDefectoProducto(id_producto);
	}

	// --------------------------------------------------------

	public Boolean actualizarCuentasPorDefecto(ProductoCuentaDefecto[] productoCuentaDef) {
		return ConfCuentaACHServicio.getInstance().actualizarCuentasPorDef(productoCuentaDef);
	}
	
	// --------------------------------------------------------
	// --------------------------------------------------------
	// --------------------------------------------------------

}
