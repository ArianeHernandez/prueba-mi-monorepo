package com.osmosyscol.datasuite.pagos.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.htsoft.commons.lang.StringUtils;

import com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto.SolicitudAnulacionPago;
import com.osmosyscol.datasuite.servlet.ListenerServlet;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ActualizadorEstadoOrden {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

	public static void iniciar() {

		Boolean activo = StringUtils.isTrue(ParametrosInicio.getProperty("CentralPagos.actualizarOrdenes"));

		if (!activo) {
			return;
		}

		// -------------------------

		new Thread(new Runnable() {

			public void run() {

				while (ListenerServlet.running) {

					System.out.println("INICIANDO ACTUALIZACION DE ESTADOS DE ORDEN DE PAGO");

					try {

						// --- consultar todos las solicitudes que tengan ordenes de pago no finalidas

						String query = "Select ss.idcarga, SS.ID id, $LINEA DE PRODUCTO.ID$ empresa_orden_pago, $ENCARGO.CODIGO$ codigo, $SOLICITUD GENERAL DE PAGO.ORDEN PAGO$ numero_orden_pago " //
								+ "from $SOLICITUD GENERAL DE PAGO$ ss, $LINEA DE PRODUCTO$, $ENCARGO$ " //
								+ "where $SOLICITUD GENERAL DE PAGO.EMPRESA$ = $LINEA DE PRODUCTO$.ID " //
								+ " AND $SOLICITUD GENERAL DE PAGO.NUMERO CONTRATO$ = $ENCARGO$.ID " //
								+ " AND $SOLICITUD GENERAL DE PAGO.ORDEN PAGO$ IS NOT NULL " //
								+ " AND ($SOLICITUD GENERAL DE PAGO.ESTADO ORDEN$ IS NULL OR $SOLICITUD GENERAL DE PAGO.ESTADO ORDEN$ NOT IN ( $S(Pagada)$, $S(Aceptada)$, $S(Anulada)$ ) )";

						List<SolicitudAnulacionPago> saop = DS_SqlUtils.queryForList(SolicitudAnulacionPago.class, query);

						for (SolicitudAnulacionPago solicitud : saop) {

							try {

								Carga carga = CargaServicio.getInstance().obtenerCarga(solicitud.getIdcarga());

								String estado = CentralPagosService.actualizarEstadoOrdenPago(solicitud.getEmpresa_orden_pago(), new Integer(sdf.format(new Date())), solicitud.getNumero_orden_pago(), carga.getId_negocio());

								if (estado != null) {
									DS_SqlUtils.update("update $SOLICITUD GENERAL DE PAGO$ set $SOLICITUD GENERAL DE PAGO.ESTADO ORDEN$ = " + "$S(" + estado + ")$" + " where id = " + solicitud.getId());
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						Thread.sleep(1 * 60000);
					} catch (Exception e) {
						return;
					}

				}

			}
		}).start();

	}

}
