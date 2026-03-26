package com.osmosyscol.datasuite.pagos.acciones.aplicardebito;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.htsoft.commons.lang.StringUtils;
import co.htsoft.commons.util.SMessage;

import com.itosmosys.www.datapi.servicios.aplicar_debito.TipoElementoSalidaaplicar_debito;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.TipoDocumentoServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.pagos.services.IntegracionPagosService;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class AplicarDebito implements AccionCarga {

	public SMessage ejecutar(Integer id_carga) {

		String endPoint = ParametrosInicio.getProperty("sippte.endpoint");

		// Si no se tiene configurada la integracion continua..
		if (endPoint == null) {
			return new SMessage(true, "Sin integracion");
		}

		Integer id_usuario = CargaServicio.getInstance().obtenerCarga(id_carga).getId_usuario();

		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(id_usuario);

		String sql = "select R.ID ID_RETIRO, $PRODUCTO.ID$ codigo_producto, $RETIROS.ID CUENTA$ codigo_cuenta," + //
				"$RETIROS.CODIGO_CONCEPTO$ codigo_concepto,  $RETIROS.VALOR$ valor_total, $LINEA DE PRODUCTO.ID$ linea_producto from $PRODUCTO$ p," + //
				" $RETIROS$ r, $LINEA DE PRODUCTO$ lp  where p.id = $RETIROS.PRODUCTO$" + //
				" and $PRODUCTO.LINEA DE PRODUCTO$ = lp.id and r.idCarga =" + id_carga + //
				" and $RETIROS.NUMERO ORDEN$ is null";

		TipoDocumento tipoDocumento = TipoDocumentoServicio.getInstance().obtenerTipoDocumento(new Integer(usuario.getTipo_documento()));

		List<Debito> debitos = DS_SqlUtils.queryForList(Debito.class, sql);
		
		if(debitos == null){
			return new SMessage(false, "Error inesperado.");
		}
		
		if (debitos.isEmpty()) {
			return new SMessage(true, "No hay retiros pendientes de debitar.");
		}

		BigDecimal suma = new BigDecimal(0);

		for (Debito debito : debitos) {
			suma = suma.add(debito.getValor_total());
		}

		

		debitos.get(0).setNumero_identificacion(usuario.getIdentificacion());
		debitos.get(0).setTipo_identificacion(tipoDocumento.getPrefijo());
		debitos.get(0).setTipo_liquidacion("Bruto");
		debitos.get(0).setAplica_gmf("TRASLADO".equals(debitos.get(0).getCodigo_concepto()) ? "N" : "S");
		debitos.get(0).setId_carga(id_carga);

		if ("TRASLADO".equals(debitos.get(0).getCodigo_concepto())) {

			String sqlCuentaBeneficiario = " Select $BANCO.CODIGO$ codigo_banco, " //
					+ " $TIPO DE CUENTA.CODIGO$ tipo_cuenta," //
					+ " $BENEFICIARIO.NUMERO DE CUENTA$ numero_cuenta " //
					+ " from $RETIROS$ r, $BENEFICIARIO$ b, $BANCO$ bn,  $TIPO DE CUENTA$ t" //
					+ " WHERE r.id = " + debitos.get(0).getId_retiro() //
					+ " and $BENEFICIARIO.BANCO$=bn.id" //
					+ " and b.id = $RETIROS.BENEFICIARIO$" + " and $BENEFICIARIO.TIPO DE CUENTA$ = t.id";

			CuentaBeneficiario cuentaBeneficiario = DS_SqlUtils.queryForObject(CuentaBeneficiario.class, sqlCuentaBeneficiario);
			debitos.get(0).setId_compensacion(cuentaBeneficiario.getCodigo_banco());
			debitos.get(0).setTipo_cuenta_bancaria(cuentaBeneficiario.getTipo_cuenta());
			debitos.get(0).setId_cuenta_bancaria(cuentaBeneficiario.getNumero_cuenta());
		}

		IntegracionPagosService pagos = new IntegracionPagosService(endPoint);

		TipoElementoSalidaaplicar_debito[] respuesta = pagos.enviarAplicarDebito(debitos.get(0).getCodigo_producto(), debitos.get(0).getCodigo_cuenta(), debitos.get(0).getCodigo_concepto(), suma.doubleValue(), debitos.get(0).getDescripcion(), debitos.get(0).getTipo_liquidacion(), debitos.get(0).getAplica_gmf(), debitos.get(0).getTipo_identificacion(), debitos.get(0).getNumero_identificacion(), debitos.get(0).getId_compensacion(), debitos.get(0).getId_cuenta_bancaria(), debitos.get(0).getTipo_cuenta_bancaria(), debitos.get(0).getId_carga(), debitos.get(0).getLinea_producto());

		if (respuesta != null && respuesta.length != 0) {
			TipoElementoSalidaaplicar_debito elem = respuesta[0];
			if (StringUtils.isNoneBlank(elem.getNUMERO_CONSECUTIVO())) {

				Map<String, Object> params = new HashMap<String, Object>();
				params.put("consecutivo", StringUtils.trimToNull(elem.getNUMERO_CONSECUTIVO()));
				Boolean res = DS_SqlUtils.update("UPDATE $RETIROS$ SET $RETIROS.NUMERO ORDEN$ = #consecutivo# where $RETIROS.NUMERO ORDEN$ is null and $RETIROS$.idCarga = " + id_carga, params);
				return new SMessage(res, "Operacion Exitosa");
			} else {
				return new SMessage(false, elem.getDESCRIPCION_ERROR());
			}
		}

		return new SMessage(false, "Operacion No Exitosa");
	}

	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
