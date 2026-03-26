package com.osmosyscol.datasuite.pagos.services;

import java.net.URL;
import java.util.Date;
import java.util.List;

import co.htsoft.centralpagosbots.services.botservicio.BotServicioSoap11BindingStub;
import co.htsoft.centralpagosbots.services.botservicio.Factura;
import co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoSoap11BindingStub;
import co.htsoft.commons.lang.P;

import com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.Actualizar_estado_orden_pagoSOAPStub;
import com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.TipoElementoEntradaactualizar_estado_orden_pago;
import com.itosmosys.www.datapi.servicios.actualizar_estado_orden_pago.TipoEntradaactualizar_estado_orden_pago;
import com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.Consultar_contratos_vigentesSOAPStub;
import com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.R_CONTRATOSArrayElement;
import com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.TipoElementoEntradaconsultar_contratos_vigentes;
import com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.TipoEntradaconsultar_contratos_vigentes;
import com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.Consultar_entrada_almacenSOAPStub;
import com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.TipoElementoEntradaconsultar_entrada_almacen;
import com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.TipoElementoSalidaconsultar_entrada_almacen;
import com.itosmosys.www.datapi.servicios.consultar_entrada_almacen.TipoEntradaconsultar_entrada_almacen;
import com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.Obtener_orden_pago_radicacionSOAPStub;
import com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.TipoElementoEntradaobtener_orden_pago_radicacion;
import com.itosmosys.www.datapi.servicios.obtener_orden_pago_radicacion.TipoEntradaobtener_orden_pago_radicacion;
import com.osmosyscol.commons.utils.FileUtils;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.pagos.acciones.centralpagos.dto.SolicitudRadicacionPago;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class CentralPagosService {

	// **************************

	public static String actualizarEstadoOrdenPago(Integer empresa_orden_pago, Integer vigencia_orden_pago, Integer numero_orden_pago, Integer id_negocio) {

		try {

			URL url = new URL(obtenerEndPointPorCore(id_negocio, "SIP") + "/ws/actualizar_estado_orden_pago");

			System.out.println(url);

			Actualizar_estado_orden_pagoSOAPStub stub = new Actualizar_estado_orden_pagoSOAPStub(url, null);

			return stub.actualizar_estado_orden_pago(new TipoEntradaactualizar_estado_orden_pago(new TipoElementoEntradaactualizar_estado_orden_pago(empresa_orden_pago, vigencia_orden_pago, numero_orden_pago)))[0].getR_ESTADO_ORDEN_PAGO();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// ********************************************************************************

	public static Integer obtenerOrdenPagoPorRadicacion(Integer empresa_radicacion, Integer vigencia_radicacion, Integer numero_radicacion, Integer id_negocio) {

		try {

			URL url = new URL(obtenerEndPointPorCore(id_negocio, "SIP") + "/ws/obtener_orden_pago_radicacion");

			System.out.println(url);

			Obtener_orden_pago_radicacionSOAPStub stub = new Obtener_orden_pago_radicacionSOAPStub(url, null);

			return stub.obtener_orden_pago_radicacion(new TipoEntradaobtener_orden_pago_radicacion(new TipoElementoEntradaobtener_orden_pago_radicacion(empresa_radicacion, vigencia_radicacion, numero_radicacion)))[0].getR_ORDENES_PAGO()[0].getNUMERO_ORDEN_PAGO();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// ********************************************************************************

	public static TipoElementoSalidaconsultar_entrada_almacen[] consultarEntradaAlmacen(Integer p_ID_SOLICITANTE, Integer p_NUMERO_FACTURA, Date p_FECHA_FACTURA, String p_TIPO_CONTRATO, Integer p_NUMERO_CONTRATO, Integer id_negocio) {

		try {

			URL url = new URL(obtenerEndPointPorCore(id_negocio, "SIP") + "/ws/consultar_entrada_almacen");

			System.out.println(url);

			Consultar_entrada_almacenSOAPStub stub = new Consultar_entrada_almacenSOAPStub(url, null);

			TipoElementoEntradaconsultar_entrada_almacen elementoEntrada = new TipoElementoEntradaconsultar_entrada_almacen(p_ID_SOLICITANTE, p_NUMERO_FACTURA, p_FECHA_FACTURA, p_TIPO_CONTRATO, p_NUMERO_CONTRATO);
			return stub.consultar_entrada_almacen(new TipoEntradaconsultar_entrada_almacen(elementoEntrada));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// ********************************************************************************

	public static Boolean anularOrdenPago(Integer p_EMPRESA_ORDEN_PAGO, Integer p_VIGENCIA_ORDEN_PAGO, Integer p_NUMERO_ORDEN_PAGO, Integer id_negocio) {

		try {

			URL urlBot = new URL(obtenerEndPointPorCore(id_negocio, "BOT") + "/services/BotServicio.BotServicioHttpSoap11Endpoint");

			System.out.println(urlBot);

			BotServicioSoap11BindingStub stubB = new BotServicioSoap11BindingStub(urlBot, null);

			Integer resp = stubB.anularSolicitud(p_EMPRESA_ORDEN_PAGO, p_VIGENCIA_ORDEN_PAGO, p_NUMERO_ORDEN_PAGO);

			return resp.equals(0);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// ********************************************************************************

	public static byte[] reporteOrdenPago(Integer p_EMPRESA_ORDEN_PAGO, Integer p_VIGENCIA_ORDEN_PAGO, Integer p_NUMERO_ORDEN_PAGO, Integer id_negocio) {

		try {

			URL urlBot = new URL(obtenerEndPointPorCore(id_negocio, "BOT") + "/services/ReporteOrdenPago.ReporteOrdenPagoHttpSoap11Endpoint");

			System.out.println(urlBot);
			System.out.println("Empresa: " + p_EMPRESA_ORDEN_PAGO + ", Vigencia: " + p_VIGENCIA_ORDEN_PAGO + ", Numero Orden :" + p_NUMERO_ORDEN_PAGO);

			ReporteOrdenPagoSoap11BindingStub stubB = new ReporteOrdenPagoSoap11BindingStub(urlBot, null);

			String file64 = StringUtils.trimToNull(stubB.obtenerReporteOrdenPago(p_EMPRESA_ORDEN_PAGO, p_VIGENCIA_ORDEN_PAGO, p_NUMERO_ORDEN_PAGO));

			if (file64 == null) {
				return null;
			}

			return FileUtils.decodeBase64(file64);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// ********************************************************************************

	public static Integer radicarSolicitudPago(List<SolicitudRadicacionPago> radicado, Integer id_negocio) {

		
		if (radicado == null || radicado.size() == 0) {
			return null;
		}

		SolicitudRadicacionPago solibase = radicado.get(0);

		P.println(radicado);

		try {

			URL urlBot = new URL(obtenerEndPointPorCore(id_negocio, "BOT") + "/services/BotServicio.BotServicioHttpSoap11Endpoint");
			System.out.println(urlBot);

			BotServicioSoap11BindingStub stubB = new BotServicioSoap11BindingStub(urlBot, null);

			Factura[] facturas = null;

			if (solibase.getNumero_factura() != null) {
				facturas = new Factura[radicado.size()];
				
				for (int i=0; i< radicado.size();i++) {
					
					SolicitudRadicacionPago s = radicado.get(i);
					
					facturas[i] = new Factura(s.getFecha_factura(), s.getNumero_factura(), s.getPrefijo_factura(), s.getValor_total().doubleValue(), s.getValor_iva().doubleValue());
				}
			}

			Double valor_total = 0d;
			
			for (SolicitudRadicacionPago s : radicado) {
				valor_total += s.getValor_total().doubleValue();
			}
			
			
			Integer resp = stubB.radicarSolicitud(solibase.getEmpresa(), solibase.getVigencia_radicacion(), solibase.getId_solicitante(), solibase.getTipo_doc_presupuesto(), solibase.getVigencia_doc_presupuesto(), solibase.getNumero_doc_presuspuesto(), valor_total, facturas);

			return resp;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// ********************************************************************************

	public static R_CONTRATOSArrayElement[] consultar_contratos_vigentes(Integer identificacion, Integer id_negocio) {

		try {

			String urls = obtenerEndPointPorCore(id_negocio, "SIP");

			if (urls != null) {

				URL url = new URL(urls + "/ws/consultar_contratos_vigentes");

				System.out.println(url);

				Consultar_contratos_vigentesSOAPStub stub = new Consultar_contratos_vigentesSOAPStub(url, null);

				return stub.consultar_contratos_vigentes(new TipoEntradaconsultar_contratos_vigentes(new TipoElementoEntradaconsultar_contratos_vigentes(identificacion)))[0].getR_CONTRATOS();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// ---
	public static String obtenerEndPointPorCore(Integer codigoCore, String tipo) {

		try {
			// consultar encargo existente y coherente ...

			String sql = "SELECT endpointCore.ID, $RC - ENDPOINT CORE.CORE$ core, " //
					+ " $RC - ENDPOINT CORE.ENDPOINT$ endpoint "//
					+ " FROM $RC - ENDPOINT CORE$ endpointCore, $LINEA DE PRODUCTO$ "//
					+ " WHERE $RC - ENDPOINT CORE.CORE$ = $LINEA DE PRODUCTO.CORE$ " //
					+ " and $LINEA DE PRODUCTO$.ID =  " + codigoCore //
					+ " and $RC - ENDPOINT CORE.TIPO SERVICIO$ = $S(" + tipo + ")$ ";

			EndPointCoreDto endPointCore = DS_SqlUtils.queryForObject(EndPointCoreDto.class, sql);

			if (endPointCore == null) {
				return null;
			}

			return endPointCore.getEndPoint();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
