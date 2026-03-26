package com.osmosyscol.datasuite.pagos.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.TipoDocumentoServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

import co.htsoft.commons.lang.P;
import co.htsoft.commons.net.GET;
import co.htsoft.commons.net.POST;
import co.htsoft.commons.net.RestService;

@SuppressWarnings("serial")
@WebServlet("/pagos/*")
public class IntegracionPagosREST extends RestService {

	// ------------------------------------------------------------------------------------------------

	public static String estado = "OK";

	@GET("/pagos/test")
	public String test() {
		return estado;
	}

	// --------------------------------------------------------------------------------

	@POST("/pagos/obtenerDatosDevolucion")
	public EntradaDevolucion obtenerDatosDevolucion(Consecutivo consecutivo_debito) {

		String sql = "SELECT $BANCO.CODIGO$ codigo_banco, $TIPO DE CUENTA.CODIGO$ tipo_cuenta" //
				+ " FROM $GRUPO GIRO$ g, $RETIROS$ r, $PRODUCTO$ p,  $LINEA DE PRODUCTO$ lp, $BANCO$ b,  $CUENTA - BANCO$ c, $TIPO DE CUENTA$ t" //
				+ " where $RETIROS.CONSECUTIVO DEBITO$ = $S(" + consecutivo_debito.getConsecutivo_debito() + ")$"//
				+ " and $GRUPO GIRO.CODIGO REGISTRO$ = r.id"//
				+ " and c.id = $GRUPO GIRO.CUENTA$"//
				+ " and t.id = $CUENTA - BANCO.TIPO DE CUENTA$"//
				+ " and b.id = $CUENTA - BANCO.BANCO$"//
				+ " and p.id = $RETIROS.PRODUCTO$"//
				+ " and $PRODUCTO.LINEA DE PRODUCTO$ = lp.id"//
				+ " and $RETIROS.CONSECUTIVO DEVOLUCION$ is null"//
				+ " and $RETIROS.ESTADO DE RESPUESTA BANCO$ is not null"//
				+ " and $RETIROS.RECHAZADO$ = $B(true)$";

		return DS_SqlUtils.queryForObject(EntradaDevolucion.class, sql);
	}
	
	// --------------------------------------------------------------------------------
	
	@POST("/pagos/obtenerNumeroCuenta")
	public NumeroCuenta obtenerNumeroCuenta(Archivo archivo) {

		String sql = "SELECT $CUENTA - BANCO.CUENTA BANCARIA$ numero_cuenta "
				+ "FROM $GRUPO GIRO$, $CUENTA - BANCO$ "
				+ "WHERE $GRUPO GIRO.CUENTA$ = $CUENTA - BANCO$.ID "
				+ "AND $GRUPO GIRO.ARCHIVO$ = " + archivo.getId_archivo();

		return DS_SqlUtils.queryForObject(NumeroCuenta.class, sql);
	}

	// --------------------------------------------------------------------------------

	@POST("/pagos/registrarCuentasBeneficiario")
	public Boolean registrarCuentasBeneficiario(ListadoCuentas cuentas) {

		System.out.println("REGISTRANDO CUENTAS BANCARIAS...");
		P.println(cuentas);

		TipoDocumento td = TipoDocumentoServicio.getInstance().obtenerTipoDocumentoCodigo(cuentas.tipo_documento);

		System.out.println(cuentas.numero_documento + ", " + td.getId());

		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(cuentas.numero_documento, td.getId());

		if (usuario == null) {
			System.out.println("Usuario No encontrado..!");
			return false;
		}

		P.println(usuario);

		// desactiva todos los beneficiarios del usuario

		System.out.println("DESACTIVANDO LAS CUENTAS ANTERIORES");

		String sql = "update $beneficiario$ set idcarga = -2 where idcarga = -1 and $beneficiario.id cliente$ = #id_usuario#";
		DS_SqlUtils.update(sql, usuario);

		// ------

		for (CuentaBancaria cuenta : cuentas.cuentas) {

			System.out.println("APLICANDO CUENTA " + cuenta.tipo_cuenta + " " + cuenta.numero_cuenta);
			try {

				Integer id_banco = DS_SqlUtils.queryForObject(Integer.class, "select id from $banco$ where $banco.codigo$ = ##", cuenta.cod_banco);
				Integer id_tipocuenta = DS_SqlUtils.queryForObject(Integer.class, "select id from $tipo de cuenta$ where $tipo de cuenta.prefijo$ = ##", cuenta.tipo_cuenta);
				Integer id_tipodocumento = DS_SqlUtils.queryForObject(Integer.class, "select id from $tipo de documento$ where $tipo de documento.codigo$ = ##", td.getId());

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id_usuario", usuario.getId_usuario());
				map.put("id_banco", id_banco);
				map.put("id_tipocuenta", id_tipocuenta);
				map.put("id_tipodocumento", id_tipodocumento);
				map.put("numero_cuenta", cuenta.numero_cuenta);
				map.put("numero_documento", usuario.getIdentificacion());
				map.put("nombre", usuario.getNombre());

				// verifica si la cuenta existe

				
				System.out.println("VERIFICANDO SI EXISTE CUENTA..");
				
				sql = "select bn.id from $beneficiario$ bn " //
						+ " where $beneficiario.id cliente$ = #id_usuario# " //
						+ " and $beneficiario.banco$ = $id_banco$ " //
						+ " and $beneficiario.tipo de cuenta$ = $id_tipocuenta$ " //
						+ " and $beneficiario.numero de cuenta$ = #numero_cuenta# " //
						+ " and idcarga = -2 ";

				Integer id_beneficiario = DS_SqlUtils.queryForObject(Integer.class, sql, map);

				// si ya existe lo activa
				if (id_beneficiario != null) {
					
					System.out.println("ACTUALIZANDO CARGA CUENTA..");
					
					sql = "update $beneficiario$ set idcarga = -1 where id = " + id_beneficiario;
					DS_SqlUtils.update(sql);

				} else { // si no lo crea
					
					System.out.println("INSERTANDO CUENTA..");
					
					sql = "insert into $beneficiario$ " //
							+ "(id, " + " idcarga, " + " $beneficiario.id cliente$, " + " $beneficiario.banco$, " + " $beneficiario.tipo de cuenta$, " + " $beneficiario.numero de cuenta$, " + " $beneficiario.tipo de documento$, " + " $beneficiario.numero de documento$, " + " $beneficiario.primer nombre$ ) " //

							+ " values " + " (sst.nextval, " + " -1, " + " #id_usuario#, " + " $id_banco$, " + " $id_tipocuenta$, " + " #numero_cuenta#, " + " $id_tipodocumento$, " + " #numero_documento#, " + " #nombre# ) ";

					DS_SqlUtils.insert(sql, map);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return true;

	}

	public static class CuentaBancaria {
		public String cod_banco;
		public String tipo_cuenta;
		public String numero_cuenta;

	}

	public static class ListadoCuentas {
		public String tipo_documento;
		public String numero_documento;
		public CuentaBancaria[] cuentas;
	}

	// --------------------------------------------------------------------------------
}
