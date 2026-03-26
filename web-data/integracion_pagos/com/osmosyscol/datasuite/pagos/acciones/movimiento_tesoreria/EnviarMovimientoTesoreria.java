package com.osmosyscol.datasuite.pagos.acciones.movimiento_tesoreria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.itosmosys.www.datapi.servicios.movimiento_tesoreria.TipoElementoSalidamovimiento_tesoreria;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionArchivo;
import com.osmosyscol.datasuite.pagos.services.IntegracionPagosService;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import co.htsoft.commons.lang.StringUtils;

public class EnviarMovimientoTesoreria implements AccionArchivo {

	public boolean ejecutar(Integer id_archivo) {

		String endPoint = ParametrosInicio.getProperty("sippte.endpoint");
		boolean activo =  StringUtils.isTrue(ParametrosInicio.getProperty("sippte.movimientotesoreria.activo"));

		// Si no se tiene configurada la integracion continua..
		if (endPoint == null || !activo) {
			return true;
		}

		try {

			String sql = "SELECT r.idcarga id_carga, r.id id_retiro, $PRODUCTO.ID$ codigo_producto, $RETIROS.CODIGO_CONCEPTO$ codigo_concepto, " //
					+ " $RETIROS.ID CUENTA$ codigo_cuenta, $RETIROS.NUMERO ORDEN$ numero_orden," //
					+ " $BANCO.CODIGO$ codigo_banco, $TIPO DE CUENTA.CODIGO$ tipo_cuenta, $CUENTA - BANCO.CUENTA BANCARIA$ numero_cuenta," //
					+ " $RETIROS.VALOR$ valor " //
					+ " FROM $GRUPO GIRO$ g, $RETIROS$ r, $PRODUCTO$ p, $CUENTA - BANCO$ c, $TIPO DE CUENTA$ t, $BANCO$ b" //
					+ " where $GRUPO GIRO.ARCHIVO$ = " + id_archivo //
					+ " and c.id = $GRUPO GIRO.CUENTA$ " //
					+ " and t.id = $CUENTA - BANCO.TIPO DE CUENTA$ " //
					+ " and b.id = $CUENTA - BANCO.BANCO$ " //
					+ " and $GRUPO GIRO.CODIGO REGISTRO$ = r.id and p.id = $RETIROS.PRODUCTO$ " //
					+ " and $RETIROS.CONSECUTIVO DEBITO$ is null ";

			List<RetiroMovimiento> retiros = DS_SqlUtils.queryForList(RetiroMovimiento.class, sql);
			
			if (retiros == null) {
				return false;
			}
			
			if(retiros.isEmpty()){
				return true;
			}

			Map<String, List<RetiroMovimiento>> map = new HashMap<String, List<RetiroMovimiento>>();

			for (RetiroMovimiento r : retiros) {
				String key = r.getNumero_orden();
				if (!map.containsKey(key)) {
					map.put(key, new ArrayList<RetiroMovimiento>());
				}
				map.get(key).add(r);
			}

			for (Entry<String, List<RetiroMovimiento>> entry : map.entrySet()) {
				BigDecimal valorTotal = new BigDecimal(0);
				List<RetiroMovimiento> retirosXNumOrden = entry.getValue();

				for (RetiroMovimiento r : retirosXNumOrden) {
					valorTotal = valorTotal.add(r.getValor());
				}

				RetiroMovimiento retiro = retirosXNumOrden.get(0);

				IntegracionPagosService pagos = new IntegracionPagosService(endPoint);

				Map<String, String> mpt = new HashMap<String, String>();
				mpt.put("A", "AH");
				mpt.put("C", "CC");
				String tc = mpt.get(retiro.getTipo_cuenta());
				
				String aplica_gmf = ("TRASLADO".equals(retiro.getCodigo_concepto()) ? "N" : "S");
				SimpleLogger.setDebug("pagos.enviarMovimientoTesoreria " + retiro.toString());
				TipoElementoSalidamovimiento_tesoreria[] respuesta = pagos.enviarMovimientoTesoreria(retiro.getCodigo_producto(), retiro.getCodigo_cuenta(), retiro.getNumero_orden(), retiro.getCodigo_banco(), tc, retiro.getNumero_cuenta(), valorTotal.doubleValue(), "Bruto", aplica_gmf );
				
				if (respuesta != null && respuesta.length != 0) {
					TipoElementoSalidamovimiento_tesoreria elem = respuesta[0];
					if (StringUtils.isNoneBlank(elem.getNUMERO_CONSECUTIVO())) {
						for (RetiroMovimiento r : retirosXNumOrden) {

							Map<String, Object> params = new HashMap<String, Object>();
							params.put("consecutivo", StringUtils.trimToNull(elem.getNUMERO_CONSECUTIVO()));
							DS_SqlUtils.update("UPDATE $RETIROS$ SET $RETIROS.CONSECUTIVO DEBITO$ = #consecutivo# where $RETIROS.CONSECUTIVO DEBITO$ is null and $RETIROS$.id = " + r.getId_retiro(), params);
						}

						return true;
					}
				}
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error en enviar movimientos: ", e);
		}

		return false;

	}

}
