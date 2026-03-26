package com.osmosyscol.datasuite.pagos.acciones.centralpagos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.htsoft.commons.lang.P;
import co.htsoft.commons.util.SMessage;

import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto.SolicitudGeneralPago;
import com.osmosyscol.datasuite.pagos.services.CentralPagosService;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class ObtenerOrdenPagoPorRadicacion implements AccionCarga {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	
	public SMessage ejecutar(Integer id_carga) {

		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
		
		boolean ok = true;

		try {

			String sql = "Select SP.*, $LINEA DE PRODUCTO.ID$ empresa, $ENCARGO.CODIGO$ codigo_contrato " //
					+ "from $SOLICITUD GENERAL DE PAGO$ SP,$LINEA DE PRODUCTO$ LP, $ENCARGO$ E  " //
					+ "where sp.idcarga = " + id_carga //
					+ " AND $SOLICITUD GENERAL DE PAGO.EMPRESA$ = LP.ID " //
					+ " AND $SOLICITUD GENERAL DE PAGO.ORDEN PAGO$  IS NULL " //
					+ " AND $SOLICITUD GENERAL DE PAGO.NUMERO CONTRATO$ = E.ID "; //

			List<SolicitudGeneralPago> sop = DS_SqlUtils.queryForList(SolicitudGeneralPago.class, sql);

			for (SolicitudGeneralPago solicitud : sop) {

				P.println(solicitud);

				Integer vigencia_radicacion = new Integer(sdf.format(new Date()));

				Integer orden_pago = CentralPagosService.obtenerOrdenPagoPorRadicacion(solicitud.getEmpresa(), vigencia_radicacion, solicitud.getRadicacion(), carga.getId_negocio());

				if (orden_pago != null) {
					if (!DS_SqlUtils.update("update $SOLICITUD GENERAL DE PAGO$ set $SOLICITUD GENERAL DE PAGO.ESTADO ORDEN$ = null, $SOLICITUD GENERAL DE PAGO.ORDEN PAGO$ = $I(" + orden_pago + ")$ where id = " + solicitud.getId())) {
						ok = false;
					}
				} else {
					ok = false;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			ok = false;
		}

		if (ok) {
			return new SMessage(true, "Operacion Exitosa.");
		} else {
			return new SMessage(false, "No se ha obtenido la orden de pago.");
		}
	}

	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		
		return true;
	}
}
