package com.osmosyscol.datasuite.pagos.acciones.aplicardevolucion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.itosmosys.www.datapi.servicios.aplicar_devolucion_sp.TipoElementoSalidaaplicar_devolucion_sp;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.TipoDocumentoServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.pagos.services.IntegracionPagosService;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import co.htsoft.commons.lang.StringUtils;

public class AplicarDevolucion {

	public static synchronized boolean ejecutar() {

		
		String endPoint = ParametrosInicio.getProperty("sippte.endpoint");
		boolean activo =  StringUtils.isTrue(ParametrosInicio.getProperty("sippte.devolucion.activo"));

		// Si no se tiene configurada la integracion continua..
		if (endPoint == null || !activo) {
			return true;
		}
		
		boolean exitoso = false;
		
		try {

			String sql;

			sql = "SELECT r.idcarga idCarga, r.id idRetiro,$RETIROS.ID CLIENTE$ idCliente, $PRODUCTO.ID$ producto,"
					+ " $LINEA DE PRODUCTO.ID$ lineaProducto, $RETIROS.ID CUENTA$ encargo, $RETIROS.VALOR$ valor,"
					+ " $RETIROS.CONSECUTIVO DEBITO$ consecutivoDebito,  $RETIROS.NUMERO ORDEN$ numeroOrden,"
					+ " $RETIROS.ESTADO DE RESPUESTA BANCO$ estadoRespuestaBanco, $RETIROS.CODIGO_CONCEPTO$ codigoConcepto,"
					+ "	$GRUPO GIRO.ARCHIVO$ idArchivo " //
					+ " FROM $GRUPO GIRO$ g, $RETIROS$ r, $PRODUCTO$ p,  $LINEA DE PRODUCTO$ lp " //
					+ " where $GRUPO GIRO.CODIGO REGISTRO$ = r.id and p.id = $RETIROS.PRODUCTO$ " //
					+ " and $PRODUCTO.LINEA DE PRODUCTO$ = lp.id " //
					+ " and $RETIROS.CONSECUTIVO DEVOLUCION$ is null " //
					+ " and $RETIROS.ESTADO DE RESPUESTA BANCO$ is not null and $RETIROS.RECHAZADO$ = $B(true)$"
					+ " and $RETIROS.CONSECUTIVO DEBITO$ is not null and $GRUPO GIRO.ARCHIVO$ is not null"
					+ " and $RETIROS.NUMERO ORDEN$ is not null";

			List<RetiroDevolucion> retiros = DS_SqlUtils.queryForList(RetiroDevolucion.class, sql);
			if (retiros == null) {
				return false;
			}

			if (retiros.isEmpty()) {
				return true;
			}

			String validarPropiedad = ParametrosInicio.getProperty("pagos.aplicarDevolucionIndividual");
			if( validarPropiedad != null && validarPropiedad.equals("true")) {
				exitoso = aplicarDevolucionIndividual(retiros, endPoint);
			}else {
				exitoso = aplicarDevolucionPorNumOrden(retiros, endPoint);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exitoso;
	}
	
	public static boolean aplicarDevolucionIndividual(List<RetiroDevolucion> retiros, String endPoint) {
		SimpleLogger.setInfo("Aplica devolucion por retiro");
		boolean exitoso = false;
		Map<Integer, Usuario> usuariosMap = new HashMap<Integer, Usuario>();
		
		for (RetiroDevolucion retiro : retiros) {
			// TODO: Definir la forma en que se va a obtener el Endpoint
			IntegracionPagosService pagos = new IntegracionPagosService(endPoint);

			// TODO: Establecer los valores de tipo de devolución y
			String codConcepto = retiro.getCodigoConcepto();
			String aplica_gmf = (codConcepto == null || codConcepto.equals("PAGO")) ? "S" : "N";
			String documento = "";
			String tipoDocumento = "";
			
			Integer idCliente = retiro.getIdCliente();
			if (usuariosMap.get(idCliente) == null) {
				Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(idCliente);
				usuariosMap.put(idCliente, usuario);
			}
			
			Usuario usuario = usuariosMap.get(retiro.getIdCliente());
			if (usuario != null) {
				TipoDocumento tipo = TipoDocumentoServicio.getInstance().obtenerTipoDocumento(new Integer(usuario.getTipo_documento()));
				documento = usuario.getIdentificacion();
				tipoDocumento = tipo.getPrefijo();
			}
			TipoElementoSalidaaplicar_devolucion_sp[] respuesta = pagos.aplicarDevolucion(retiro.getProducto(), retiro.getEncargo(), retiro.getNumeroOrden(), retiro.getConsecutivoDebito(), retiro.getValor().doubleValue(), "Bruto", aplica_gmf, retiro.getCodigoConcepto(), retiro.getLineaProducto(), tipoDocumento, documento, retiro.getIdArchivo().toString());
			if (respuesta != null && respuesta.length != 0) {
				TipoElementoSalidaaplicar_devolucion_sp elem = respuesta[0];
				if (StringUtils.isNotBlank(elem.getNUMERO_CONSECUTIVO())) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("consecutivo", StringUtils.trimToNull(elem.getNUMERO_CONSECUTIVO()));
					DS_SqlUtils.update("UPDATE $RETIROS$ SET $RETIROS.CONSECUTIVO DEVOLUCION$ = #consecutivo# where $RETIROS.CONSECUTIVO DEVOLUCION$ is null and $RETIROS$.id = " + retiro.getIdRetiro(), params);
				}
					exitoso = true;
				}
			}
		return exitoso;
	}
		
	
	public static boolean aplicarDevolucionPorNumOrden(List<RetiroDevolucion> retiros, String endPoint) {
		SimpleLogger.setInfo("Aplica devolucion por numero de orden");
		boolean exitoso = false;
		Map<String, List<RetiroDevolucion>> map = new HashMap<String, List<RetiroDevolucion>>();
		Map<Integer, Usuario> usuariosMap = new HashMap<Integer, Usuario>();
		
		for (RetiroDevolucion r : retiros) {
			String key = r.getNumeroOrden();
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<RetiroDevolucion>());
			}
			map.get(key).add(r);
			Integer idCliente = r.getIdCliente();
			if (usuariosMap.get(idCliente) == null) {
				Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(idCliente);
				usuariosMap.put(idCliente, usuario);
			}
		}
		
		for (Entry<String, List<RetiroDevolucion>> entry : map.entrySet()) {
			BigDecimal valorTotal = new BigDecimal(0);
			List<RetiroDevolucion> retirosXNumOrden = entry.getValue();

			for (RetiroDevolucion r : retirosXNumOrden) {
				valorTotal = valorTotal.add(r.getValor());
			}

			RetiroDevolucion retiro = retirosXNumOrden.get(0);
			// TODO: Definir la forma en que se va a obtener el Endpoint
			IntegracionPagosService pagos = new IntegracionPagosService(endPoint);

			// TODO: Establecer los valores de tipo de devolución y
			String codConcepto = retiro.getCodigoConcepto();
			String aplica_gmf = (codConcepto == null || codConcepto.equals("PAGO")) ? "S" : "N";
			String documento = "";
			String tipoDocumento = "";
			
			
			
			Usuario usuario = usuariosMap.get(retiro.getIdCliente());
			if (usuario != null) {
				TipoDocumento tipo = TipoDocumentoServicio.getInstance().obtenerTipoDocumento(new Integer(usuario.getTipo_documento()));
				documento = usuario.getIdentificacion();
				tipoDocumento = tipo.getPrefijo();
			}
			TipoElementoSalidaaplicar_devolucion_sp[] respuesta = pagos.aplicarDevolucion(retiro.getProducto(), retiro.getEncargo(), retiro.getNumeroOrden(), retiro.getConsecutivoDebito(), valorTotal.doubleValue(), "Bruto", aplica_gmf, retiro.getCodigoConcepto(), retiro.getLineaProducto(), tipoDocumento, documento, retiro.getIdArchivo().toString());
			if (respuesta != null && respuesta.length != 0) {
				TipoElementoSalidaaplicar_devolucion_sp elem = respuesta[0];
				if (StringUtils.isNotBlank(elem.getNUMERO_CONSECUTIVO())) {
					for (RetiroDevolucion r : retirosXNumOrden) {

						Map<String, Object> params = new HashMap<String, Object>();
						params.put("consecutivo", StringUtils.trimToNull(elem.getNUMERO_CONSECUTIVO()));
						DS_SqlUtils.update("UPDATE $RETIROS$ SET $RETIROS.CONSECUTIVO DEVOLUCION$ = #consecutivo# where $RETIROS.CONSECUTIVO DEVOLUCION$ is null and $RETIROS$.id = " + r.getIdRetiro(), params);
					}

					exitoso = true;
				}
			}
		}
		return exitoso;
	}

}
