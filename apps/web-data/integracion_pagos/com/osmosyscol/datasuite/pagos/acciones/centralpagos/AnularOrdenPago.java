package com.osmosyscol.datasuite.pagos.acciones.centralpagos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto.SolicitudAnulacionPago;
import com.osmosyscol.datasuite.pagos.services.CentralPagosService;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class AnularOrdenPago implements AccionCarga {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	
	public SMessage ejecutar(Integer id_carga) {

		
		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
		
		try {

			boolean respProcedimiento = true;

			String query = "Select $SOLICITUD GENERAL DE PAGO$.ID id, $LINEA DE PRODUCTO.ID$ empresa_orden_pago, $ENCARGO.CODIGO$ codigo, $SOLICITUD GENERAL DE PAGO.ORDEN PAGO$ numero_orden_pago " //
					+ "from $SOLICITUD GENERAL DE PAGO$, $LINEA DE PRODUCTO$, $ENCARGO$ " //
					+ "where $SOLICITUD GENERAL DE PAGO.EMPRESA$ = $LINEA DE PRODUCTO$.ID " //
					+ " AND $SOLICITUD GENERAL DE PAGO.NUMERO CONTRATO$ = $ENCARGO$.ID " //
					+ " AND $SOLICITUD GENERAL DE PAGO.ORDEN PAGO$ IS NOT NULL " //
					+ " AND $SOLICITUD GENERAL DE PAGO$.IDCARGA = " + id_carga;//

			List<SolicitudAnulacionPago> saop = DS_SqlUtils.queryForList(SolicitudAnulacionPago.class, query);

			for (SolicitudAnulacionPago solicitud : saop) {
				solicitud.setVigencia_orden_pago(new Integer(sdf.format(new Date())));

				Boolean respAnulacion = CentralPagosService.anularOrdenPago(solicitud.getEmpresa_orden_pago(), solicitud.getVigencia_orden_pago(), solicitud.getNumero_orden_pago(), carga.getId_negocio());

				if (respAnulacion) {
					DS_SqlUtils.update("update $SOLICITUD GENERAL DE PAGO$ " //
							+ "set $SOLICITUD GENERAL DE PAGO.ORDEN PAGO$ = null, $SOLICITUD GENERAL DE PAGO.ESTADO ORDEN$ = null " //
							+ " where ID = " + solicitud.getId()); //
				}

				respProcedimiento = respProcedimiento && respAnulacion;

			}

			if(respProcedimiento){
				return new SMessage(true, "Finalizo");
			}else{
				return new SMessage(false, "No es posible realizar la anulación.");

			}
			

		} catch (Exception e) {
			e.printStackTrace();
			return new SMessage(false, "Error Inesperado");

		}
	}

	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
