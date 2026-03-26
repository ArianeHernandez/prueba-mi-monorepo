package com.osmosyscol.datasuite.pagos.services;

import java.net.URL;

import com.itosmosys.www.datapi.servicios.aplicar_debito.Aplicar_debitoSOAPStub;
import com.itosmosys.www.datapi.servicios.aplicar_debito.TipoElementoEntradaaplicar_debito;
import com.itosmosys.www.datapi.servicios.aplicar_debito.TipoElementoSalidaaplicar_debito;
import com.itosmosys.www.datapi.servicios.aplicar_debito.TipoEntradaaplicar_debito;
import com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.Aplicar_devolucion_spSOAPStub;
import com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.TipoElementoEntradaaplicar_devolucion_sp;
import com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.TipoElementoSalidaaplicar_devolucion_sp;
import com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.TipoEntradaaplicar_devolucion_sp;
import com.itosmosys.www.datapi.servicios.consulta_sarlaft.Consulta_sarlaftSOAPStub;
import com.itosmosys.www.datapi.servicios.consulta_sarlaft.TipoElementoEntradaconsulta_sarlaft;
import com.itosmosys.www.datapi.servicios.consulta_sarlaft.TipoElementoSalidaconsulta_sarlaft;
import com.itosmosys.www.datapi.servicios.consulta_sarlaft.TipoEntradaconsulta_sarlaft;
import com.itosmosys.www.datapi.servicios.movimiento_tesoreria.Movimiento_tesoreriaSOAPStub;
import com.itosmosys.www.datapi.servicios.movimiento_tesoreria.TipoElementoEntradamovimiento_tesoreria;
import com.itosmosys.www.datapi.servicios.movimiento_tesoreria.TipoElementoSalidamovimiento_tesoreria;
import com.itosmosys.www.datapi.servicios.movimiento_tesoreria.TipoEntradamovimiento_tesoreria;

public class IntegracionPagosService {

	private String endpoint;

	// **************************
	public IntegracionPagosService(String endpoint) {
		this.endpoint = endpoint;
	}

	// ********************************************************************************

	public TipoElementoSalidamovimiento_tesoreria[] enviarMovimientoTesoreria(String codigo_producto, String codigo_cuenta, String numero_orden, String codigo_banco, String tipo_cuenta, String numero_cuenta, Double valor, String tipo_liquidacion, String aplica_gmf) {

		try {

			URL url = new URL(endpoint + "/movimiento_tesoreria");
			
			System.out.println(url);

			Movimiento_tesoreriaSOAPStub stub = new Movimiento_tesoreriaSOAPStub(url, null);

			return stub.movimiento_tesoreria(new TipoEntradamovimiento_tesoreria(new TipoElementoEntradamovimiento_tesoreria(codigo_producto, codigo_cuenta, numero_orden, codigo_banco, tipo_cuenta, numero_cuenta, valor, tipo_liquidacion, aplica_gmf)));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// ********************************************************************************

	public TipoElementoSalidaaplicar_debito[] enviarAplicarDebito(String codigo_producto, String codigo_cuenta, String codigo_concepto, Double valor, String Descripcion, String tipo_liquidacion, String aplica_gmf, String tipo_identificacion, String numero_identificacion, String id_compensacion, String id_cuenta_bancaria, String tipo_cuenta_bancaria, Integer id_carga, String linea_producto) {

		try {

			URL url = new URL(endpoint + "/aplicar_debito");
			
			System.out.println(url);

			Aplicar_debitoSOAPStub stub = new Aplicar_debitoSOAPStub(url, null);

			return stub.aplicar_debito(new TipoEntradaaplicar_debito(new TipoElementoEntradaaplicar_debito(codigo_producto, codigo_cuenta, codigo_concepto, valor, Descripcion, tipo_liquidacion, aplica_gmf, tipo_identificacion, numero_identificacion, id_compensacion, id_cuenta_bancaria, tipo_cuenta_bancaria, id_carga, linea_producto)));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// ********************************************************************************

	public TipoElementoSalidaaplicar_devolucion_sp[] aplicarDevolucion(String codigo_producto, String codigo_cuenta, String numero_orden, String numero_consecutivo, Double valor, String tipo_liquidacion, String aplica_gmf, String codigo_concepto, String linea_producto, String tipo_documento, String numero_documento, String id_archivo) {

		try {

			URL url = new URL(endpoint + "/aplicar_devolucion_sp");
			
			System.out.println(url);

			Aplicar_devolucion_spSOAPStub stub = new Aplicar_devolucion_spSOAPStub(url, null);

			return stub.aplicar_devolucion_sp(new TipoEntradaaplicar_devolucion_sp(new TipoElementoEntradaaplicar_devolucion_sp(codigo_producto, codigo_cuenta, numero_orden, numero_consecutivo, valor, tipo_liquidacion, aplica_gmf, codigo_concepto, linea_producto, tipo_documento, numero_documento, id_archivo)));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	// ********************************************************************************

	public TipoElementoSalidaconsulta_sarlaft[] consultaSarlaft(String tipo_identificacion, String numero_identificacion, String nombre_completo) {

		try {
			
			URL url = new URL(endpoint + "/consulta_sarlaft");

			System.out.println(url);

			Consulta_sarlaftSOAPStub stub = new Consulta_sarlaftSOAPStub(url, null);

			return stub.consulta_sarlaft(new TipoEntradaconsulta_sarlaft(new TipoElementoEntradaconsulta_sarlaft(tipo_identificacion, numero_identificacion, nombre_completo)));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}