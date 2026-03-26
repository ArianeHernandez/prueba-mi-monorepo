package com.osmosyscol.datasuite.pagos.services;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto.SolicitudAnulacionPago;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class OrdenPagoJsonServicio implements JsonService {

	public void setSession(Session session) {
	}

	// ----------------------------------------

	public Boolean existeArchivo(Integer id) {

		try {

			String query = "Select $SOLICITUD GENERAL DE PAGO$.IDCARGA, $SOLICITUD GENERAL DE PAGO$.ID id, $LINEA DE PRODUCTO.ID$ empresa_orden_pago, $ENCARGO.CODIGO$ codigo, $SOLICITUD GENERAL DE PAGO.ORDEN PAGO$ numero_orden_pago " //
					+ "from $SOLICITUD GENERAL DE PAGO$, $LINEA DE PRODUCTO$, $ENCARGO$ " //
					+ "where $SOLICITUD GENERAL DE PAGO.EMPRESA$ = $LINEA DE PRODUCTO$.ID " //
					+ " AND $SOLICITUD GENERAL DE PAGO.NUMERO CONTRATO$ = $ENCARGO$.ID " //
					+ " AND $SOLICITUD GENERAL DE PAGO$.ID = " + id;//

			SolicitudAnulacionPago saop = DS_SqlUtils.queryForObject(SolicitudAnulacionPago.class, query);

			String[] s = saop.getCodigo().split("-");
			saop.setVigencia_orden_pago(new Integer(s[1]));
			
			Carga carga = CargaServicio.getInstance().obtenerCarga(saop.getIdcarga());

			return CentralPagosService.reporteOrdenPago(saop.getEmpresa_orden_pago(), saop.getVigencia_orden_pago(), saop.getNumero_orden_pago(), carga.getId_negocio()) != null;

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

	}

	// ----------------------------------------

	public Boolean tieneOrdenPago(Integer id) {

		String query = "Select $SOLICITUD GENERAL DE PAGO.ORDEN PAGO$ codigo " //
				+ "from $SOLICITUD GENERAL DE PAGO$ " //
				+ "where $SOLICITUD GENERAL DE PAGO$.ID = " + id;//

		Integer codigo = DS_SqlUtils.queryForObject(Integer.class, query);

		return codigo != null;

	}

	// ----------------------------------------

}
