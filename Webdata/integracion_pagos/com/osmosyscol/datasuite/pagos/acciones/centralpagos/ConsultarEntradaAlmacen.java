package com.osmosyscol.datasuite.pagos.acciones.centralpagos;

import java.util.List;

import co.htsoft.commons.util.SMessage;

import com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.R_ENTRADAS_ALMACENArrayElement;
import com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.TipoElementoSalidaconsultar_entrada_almacen;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto.SolicitudEntradaConsultaAlmacenes;
import com.osmosyscol.datasuite.pagos.services.CentralPagosService;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class ConsultarEntradaAlmacen implements AccionCarga {

	public SMessage ejecutar(Integer id_carga) {

		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
		
		try {

			boolean error = false;

			boolean respProcedimiwento = true;

			String query = "SELECT  $SOLICITUD GENERAL DE PAGO$.ID id, " //
					+ "$ENCARGO.ID CLIENTE$ id_cliente, " //
					+ "$SOLICITUD GENERAL DE PAGO.NUM FACTURA$ num_factura, " //
					+ "$SOLICITUD GENERAL DE PAGO.FECHA DEL DOCUMENTO$ fecha_documento, " //
					+ "$ENCARGO.CODIGO$ cod_contrato " //
					+ "from $SOLICITUD GENERAL DE PAGO$, $ENCARGO$ " //
					+ "WHERE $ENCARGO$.ID = $SOLICITUD GENERAL DE PAGO.NUMERO CONTRATO$ " //
					+ "AND $SOLICITUD GENERAL DE PAGO$.IDCARGA = " + id_carga; //

			List<SolicitudEntradaConsultaAlmacenes> seca = DS_SqlUtils.queryForList(SolicitudEntradaConsultaAlmacenes.class, query);

			for (SolicitudEntradaConsultaAlmacenes solicitudConsult : seca) {
				String[] s = solicitudConsult.getCod_contrato().split("-");
				solicitudConsult.setPtipo_contrato(s[0]);
				solicitudConsult.setNum_contrato(new Integer(s[2]));

				Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(solicitudConsult.getId_cliente());

				TipoElementoSalidaconsultar_entrada_almacen[] respConsulta = CentralPagosService.consultarEntradaAlmacen(Integer.parseInt(usuario.getIdentificacion()), solicitudConsult.getNum_factura(), solicitudConsult.getFecha_documento(), solicitudConsult.getPtipo_contrato(), solicitudConsult.getNum_contrato(), carga.getId_negocio());

				for (TipoElementoSalidaconsultar_entrada_almacen elementAlmacen : respConsulta) {

					if (elementAlmacen.getR_ENTRADAS_ALMACEN().length > 0 && elementAlmacen.getR_ENTRADAS_ALMACEN()[0].getNUMERO_ENTRADA() != null) {
						R_ENTRADAS_ALMACENArrayElement element = elementAlmacen.getR_ENTRADAS_ALMACEN()[0];

						DS_SqlUtils.update("update $SOLICITUD GENERAL DE PAGO$ " //
								+ "set $SOLICITUD GENERAL DE PAGO.EMPRESA ENTRADA$  = " + "$I(" + element.getEMPRESA_ENTRADA() + ")$, " //
								+ "$SOLICITUD GENERAL DE PAGO.TIPO ENTRADA$  = " + "$S(" + element.getTIPO_ENTRADA() + ")$, " //
								+ "$SOLICITUD GENERAL DE PAGO.PERIODO ENTRADA$  = " + "$I(" + element.getPERIODO_ENTRADA() + ")$, " //
								+ "$SOLICITUD GENERAL DE PAGO.NUMERO ENTRADA$  = " + "$I(" + element.getNUMERO_ENTRADA() + ")$ " //
								+ " where id = " + solicitudConsult.getId()); //

					} else {
						error = true;
					}
				}

			}

			if (error) {
				return new SMessage(true, "Consulta Exitosa.");
			} else {
				return new SMessage(respProcedimiwento, "Consulta Exitosa.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new SMessage(false, "Error Inesperado al realizar la consulta.");
		}

	}

	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
