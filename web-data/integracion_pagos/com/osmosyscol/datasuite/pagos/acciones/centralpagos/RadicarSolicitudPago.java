package com.osmosyscol.datasuite.pagos.acciones.centralpagos;

import java.util.List;

import co.htsoft.commons.util.SMessage;

import com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.R_CONTRATOSArrayElement;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto.SolicitudRadicacionPago;
import com.osmosyscol.datasuite.pagos.services.CentralPagosService;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class RadicarSolicitudPago implements AccionCarga {

	public SMessage ejecutar(Integer id_carga) {

		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);

		boolean resulatdo = true;

		String query = "Select $SOLICITUD GENERAL DE PAGO$.ID id, " //
				+ "$LINEA DE PRODUCTO.ID$ empresa, " //
				+ "$ENCARGO.ID CLIENTE$ id_solicitante, " //
				+ "$SOLICITUD GENERAL DE PAGO.PREFIJO FACTURA$ prefijo_factura, " //
				+ "$SOLICITUD GENERAL DE PAGO.NUM FACTURA$ numero_factura, " //
				+ "$SOLICITUD GENERAL DE PAGO.FECHA DEL DOCUMENTO$ fecha_factura, " //
				+ "$SOLICITUD GENERAL DE PAGO.VALOR TOTAL$ valor_total, " //
				+ "$SOLICITUD GENERAL DE PAGO.SUBTOTAL$ valor_base, " //
				+ "$SOLICITUD GENERAL DE PAGO.VALOR IVA$ valor_iva, " //
				+ "$ENCARGO.CODIGO$ codigo " //
				+ "from $SOLICITUD GENERAL DE PAGO$, $ENCARGO$, $LINEA DE PRODUCTO$ " //
				+ "WHERE $SOLICITUD GENERAL DE PAGO.EMPRESA$ = $LINEA DE PRODUCTO$.ID " //
				+ "AND $ENCARGO$.ID = $SOLICITUD GENERAL DE PAGO.NUMERO CONTRATO$ " //
				+ "AND $SOLICITUD GENERAL DE PAGO.RADICACION$ is null " + "AND $SOLICITUD GENERAL DE PAGO$.IDCARGA = " + id_carga; //

		List<SolicitudRadicacionPago> srp = DS_SqlUtils.queryForList(SolicitudRadicacionPago.class, query);

		SolicitudRadicacionPago sbase = srp.get(0);
		{
			String[] s = sbase.getCodigo().split("-");
			sbase.setVigencia_radicacion(new Integer(s[1]));

			Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(sbase.getId_solicitante());
			sbase.setId_solicitante(new Integer(usuario.getIdentificacion()));
		}
		// llama a la consulta que trae la informacion restante :-D

		R_CONTRATOSArrayElement[] info_contratos = CentralPagosService.consultar_contratos_vigentes(sbase.getId_solicitante(), carga.getId_negocio());

		for (SolicitudRadicacionPago solicitudRadicacion : srp) {

			sbase.setVigencia_radicacion(sbase.getVigencia_radicacion());
			sbase.setId_solicitante(sbase.getId_solicitante());

			for (R_CONTRATOSArrayElement info_contrato : info_contratos) {

				if (solicitudRadicacion.getCodigo().equals(info_contrato.getTIPO_CONTRATO() + "-" + info_contrato.getVIGENCIA_CONTRATO() + "-" + info_contrato.getNUMERO_CONTRATO())) {
					solicitudRadicacion.setTipo_doc_presupuesto(info_contrato.getTIPO_DOC_PRESUPUESTO());
					solicitudRadicacion.setVigencia_doc_presupuesto(info_contrato.getVIGENCIA_DOC_PRESUPUESTO());
					solicitudRadicacion.setNumero_doc_presuspuesto(info_contrato.getNUMERO_DOC_PRESUPUESTO());
				}
			}
		}

		Integer resp = CentralPagosService.radicarSolicitudPago(srp, carga.getId_negocio());

		if (resp != null) {
			for (SolicitudRadicacionPago solicitudRadicacion : srp) {
				DS_SqlUtils.update("update $SOLICITUD GENERAL DE PAGO$ set $SOLICITUD GENERAL DE PAGO.RADICACION$ = " + "$I(" + resp + ")$" + " where ID = " + solicitudRadicacion.getId());
			}
		}

		return new SMessage(resulatdo, "Finalizo");

	}

	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
