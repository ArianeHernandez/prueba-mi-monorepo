package com.osmosyscol.datasuite.webdata.persistencia.dao.ibatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.SalidaCoreCorrevalDao;
import com.osmosyscol.datasuite.webdata.utils.ORASQLUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class SalidaCoreCorrevalDaoImp extends BaseSqlMapDao implements SalidaCoreCorrevalDao {

	private String obtenerSqlBasicoSalidaCore(String tipo_archivo) {

		String select = " select " + //
				" ssc.$SALIDA SISTEMA CORREVAL.TIPO RETIRO$ c_tipo_retiro, " + //
				" ssc.$SALIDA SISTEMA CORREVAL.SISTEMA CORE$ c_sistema_core, " + //
				" ''||r.ID id_retiro, coalesce(r.$RETIROS.ID CLIENTE$,'') c_id_cliente, " + //
				" coalesce(r.$RETIROS.ID CUENTA$,'') c_num_cuenta_cliente_cor," + //
				" coalesce(r.$RETIROS.VALOR$,'') c_valor_retiro, " + //
				" coalesce(b.$BENEFICIARIO.NUMERO DE DOCUMENTO$,'') c_identificacion_ben," + //
				" coalesce(b.$BENEFICIARIO.PRIMER NOMBRE$||' ','')||coalesce(b.$BENEFICIARIO.SEGUNDO NOMBRE$||' ','')||coalesce(b.$BENEFICIARIO.PRIMER APELLIDO$||' ','')||coalesce(b.$BENEFICIARIO.SEGUNDO APELLIDO$,'') c_nombre_ben," + //
				" coalesce(bb.$BANCO.NOMBRE$,'') c_nombre_banco_destino_ben, " + //
				" coalesce(b.$BENEFICIARIO.NUMERO DE CUENTA$,'') c_num_cuenta_destino_ben," + //
				" coalesce(tcb.$TIPO DE CUENTA.DESCRIPCION$,'') c_tipo_cuenta_destino_ben, " + //
				" coalesce(p.$PRODUCTO.NOMBRE$,'') c_descripcion_producto, " + //
				" coalesce(pn.$LINEA DE PRODUCTO.NOMBRE$,'') c_descripcion_negocio," + //
				" coalesce(r.$RETIROS.TIPO DE RETIRO$,'') c_tipo_de_medio_f," + //
				" r.IDCARGA lote_origen," + //
				" r.$RETIROS.ESTADO DE RESPUESTA BANCO$ c_estado_retiro";

		String from_inner = " FROM $RETIROS$ r " + //
				" INNER JOIN $SALIDA SISTEMA CORREVAL$ ssc ON (r.$RETIROS.TIPO DE RETIRO$ = ssc.$SALIDA SISTEMA CORREVAL.TIPO RETIRO$ AND ssc.$SALIDA SISTEMA CORREVAL.TIPO ARCHIVO$ = $S(" + tipo_archivo + ")$)" + " INNER JOIN $PRODUCTO$ p ON (r.$RETIROS.PRODUCTO$ = p.ID)" + //
				" INNER JOIN $LINEA DE PRODUCTO$ pn ON (p.$PRODUCTO.LINEA DE PRODUCTO$ = pn.ID)" + //
				" INNER JOIN $SALIDA SISTEMA CORREVAL$ ssc2 ON (ssc2.id = ssc.id AND pn.ID = ssc2.$SALIDA SISTEMA CORREVAL.ID NEGOCIO$ )" + " INNER JOIN TS01 ON (r.IDCARGA = TS01.id_carga and TS01.estado='C')" + ""; //

		String from_outer = " INNER JOIN $BENEFICIARIO$ b ON (r.$RETIROS.BENEFICIARIO$ = b.ID)" + //
				" INNER JOIN $BANCO$ bb ON (b.$BENEFICIARIO.BANCO$ = bb.ID)" + //
				" INNER JOIN $TIPO DE CUENTA$ tcb ON(b.$BENEFICIARIO.TIPO DE CUENTA$ = tcb.ID)" + //
				"";

		if (tipo_archivo.contains("ACH")) {

			// Se complementa el select
			select = select + ", " + //
					" coalesce(lp.$LOCALIZACION DE PAGO.TIPO ENTREGA PAGO$,'') c_tipo_de_entrega_medio_f," + //
					" coalesce(lp.$LOCALIZACION DE PAGO.DIRECCION DE ENTREGA$,'') c_direccion_entrega_medio_f," + //
					" coalesce(atd.$TIPO DE DOCUMENTO.NOMBRE$,'') c_tipo_identificacion_auto_p_r, coalesce(a.$AUTORIZADO CHEQUE.NUMERO DE DOCUMENTO$,'') c_num_identificacion_auto_p_r," + //
					" coalesce(a.$AUTORIZADO CHEQUE.NOMBRES$||' ','')||coalesce(a.$AUTORIZADO CHEQUE.APELLIDOS$,'') c_nombre_auto_p_r , " + //
					" coalesce(cc.$CUENTA - BANCO.CUENTA BANCARIA$,'') c_num_cuenta_origen_correval,  coalesce(tcc.$TIPO DE CUENTA.DESCRIPCION$,'') c_tipo_cuenta_o_c," + //
					" coalesce(cc.$CUENTA - BANCO.CUENTA CONTABLE$,'') c_codigo_contable_cuenta_o_c," + " coalesce(bc.$BANCO.NOMBRE$,'') c_nombre_banco_o_c";

			// Se complementa el from
			from_inner = from_inner + //
					" INNER JOIN $GRUPO GIRO$ gg ON (r.ID = gg.$GRUPO GIRO.CODIGO REGISTRO$_NUM AND ( (r.$RETIROS.TIPO DE RETIRO$ = $S(ACH)$ AND gg.$GRUPO GIRO.ARCHIVO$ is not null )) )" + //
					""; //

			from_outer = from_outer + //
					" LEFT OUTER JOIN $LOCALIZACION DE PAGO$ lp ON(r.$RETIROS.LOCALIZACION DE PAGO$ = lp.ID)" + //
					" LEFT OUTER JOIN $AUTORIZADO CHEQUE$ a ON(lp.$LOCALIZACION DE PAGO.AUTORIZADO$ = a.ID)" + //
					" LEFT OUTER JOIN $TIPO DE DOCUMENTO$ atd ON(a.$AUTORIZADO CHEQUE.TIPO DE DOCUMENTO$ = atd.ID)" + //
					" INNER JOIN $CUENTA - BANCO$ cc ON (gg.$GRUPO GIRO.CUENTA$ = cc.ID)" + //
					" INNER JOIN $BANCO$ bc ON (cc.$CUENTA - BANCO.BANCO$= bc.ID)" + //
					" INNER JOIN $TIPO DE CUENTA$ tcc ON(cc.$CUENTA - BANCO.TIPO DE CUENTA$ = tcc.ID)" + //
					"";

		} else {// tipo cheque
			select = select + " , " + //
					" null c_tipo_cuenta_origen_correval, " + //
					" null c_nombre_banco_origen_correval ";
		}

		String sql = select + from_inner + from_outer;

		return sql;

	}

	public SalidaCoreCorrevalDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Integer contarRetirosPorTipoArchivoSinDescargar(String tipo_archivo) {

		String sistema_core = Constantes.MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO.get(tipo_archivo);

		String sql = obtenerSqlBasicoSalidaCore(tipo_archivo);

		sql = sql + " WHERE NVL2(r.$RETIROS.ARCHIVO SALIDA " + sistema_core + "$, 2, 1) = 1" 
		+ " AND ts01.estado in ('C','A') ";

		String sql_nuevo = RDServicio.reemplazarNombres(sql);
		sql_nuevo = "select count(1) from ( " + sql_nuevo + " ) t";

		return (Integer) queryForObject("SalidaCoreCorreval.selectNumero", sql_nuevo);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> obtenerRetirosPorTipoArchivoSinDescargar(String tipo_archivo, List<String> retiros_seleccionados, Integer pagina) {
		try {

			String sistema_core = Constantes.MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO.get(tipo_archivo);

			String sql = obtenerSqlBasicoSalidaCore(tipo_archivo);

			sql = sql + " WHERE r.$RETIROS.ARCHIVO SALIDA " + sistema_core + "$ is null" + " AND ts01.estado in ('C','A') ";

			if (CollectionUtils.isNotEmpty(retiros_seleccionados)) {

				StringBuffer sBuf = new StringBuffer(" and ''||r.ID in (");
				int i = 0;
				for (String string : retiros_seleccionados) {
					if (i > 0) {
						sBuf.append(", ");
					}
					sBuf.append("'");
					sBuf.append(string);
					sBuf.append("'");

					i++;
				}
				sBuf.append(") ");
				sql = sql + sBuf.toString();
			}

			sql = sql + " order by r.ID ";

			if (pagina != null) {
				// Paginar resultados directamente en la base de datos
				sql = ORASQLUtils.addPaginacion(sql, pagina,  com.osmosyscol.datasuite.config.Constantes.PAGINACIONLISTADO);
			}

			String sql_nuevo = RDServicio.reemplazarNombres(sql);

			List<Map<String, Object>> resp = desencriptarMapa(queryForList("SalidaCoreCorreval.selectSQL", sql_nuevo));

			return resp;

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	private List<Map<String, Object>> desencriptarMapa(List<Map<String, Object>> resp) {

		if (resp == null || resp.isEmpty()) {
			return resp;
		}

		// desencripta
		for (Map<String, Object> registro : resp) {

			Set<String> keys = registro.keySet();
			for (String key : keys) {

				// se verifica si es un campo
				Boolean esCampo;
				try {
					esCampo = (key.toUpperCase().startsWith("C") || key.toUpperCase().startsWith("V"));
				} catch (Exception e) {
					esCampo = false;
				}

				// se verifica si el valor es texto
				if (registro.get(key) instanceof String && esCampo) {
					// System.out
					// .println("CargaDaoImp.obtenerDatosCargaMultiple() "+key);
					registro.put(key, Crypto.DF(registro.get(key)));
				}
			}
		}

		return resp;
	}

	@SuppressWarnings("unchecked")
	public List<String> obtenerTiposArchivoSalidaCorreval() {
		
		ArrayList<String> resp_des = new ArrayList<String>();

		try {
			List<String> resp = queryForList("SalidaCoreCorreval.obtenerTiposArchivoSalidaCorreval", null);
			if (resp != null && resp.size() > 0) {
				for (String string : resp) {

					String valor = Crypto.DF(string).toString();
					resp_des.add(valor);
				}

				return resp_des;

			} else {
				return resp;

			}

		} catch (Exception e) {
			return null;
		}

	}

	public Integer obtenerSiguienteIDArchivoSalidaCore() {
		try {

			return (Integer) queryForObject("SalidaCoreCorreval.obtenerSiguienteIDArchivoSalidaCore", null);

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public Boolean crearArchivoSalidaCore(Integer id_archivo, String tipo_archivo, Date fecha_creacion, String nombre_generador) {

		try {

			String fecha_string = StringUtils.toString(fecha_creacion);

			String sql = "insert into $ARCHIVO SALIDA CORE$ " + " ( ID, IDCARGA,  $ARCHIVO SALIDA CORE.TIPO ARCHIVO$, $ARCHIVO SALIDA CORE.FECHA CREACION$, $ARCHIVO SALIDA CORE.NOMBRE DEL GENERADOR$ )" + " VALUES(" + id_archivo + ", 0,  $S(" + tipo_archivo + ")$, $T(" + fecha_string + ")$, $S(" + nombre_generador + ")$ )";

			String sql_nuevo = RDServicio.reemplazarNombres(sql);

			insert("SalidaCoreCorreval.insertSQL", sql_nuevo);

			return true;

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public Boolean actualizarArchivoSalidaORIONPorRegistro(Integer id_registro, Integer id_archivo) {
		try {

			String sql = "update $RETIROS$" + " set $RETIROS.ARCHIVO SALIDA ORION$ = " + id_archivo + " where ID = " + id_registro;

			String sql_nuevo = RDServicio.reemplazarNombres(sql);

			update("SalidaCoreCorreval.updateSQL", sql_nuevo);
			return true;

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public Boolean actualizarArchivoSalidaSIFPorRegistro(Integer id_registro, Integer id_archivo) {
		try {

			String sql = "update $RETIROS$" + " set $RETIROS.ARCHIVO SALIDA SIF$ = " + id_archivo + " where ID = " + id_registro;

			String sql_nuevo = RDServicio.reemplazarNombres(sql);

			update("SalidaCoreCorreval.updateSQL", sql_nuevo);
			return true;

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}

	public List<Map<String, Object>> obtenerRetirosPorArchivoDescargado(Integer id_archivo, String tipo_archivo) {

		try {

			String sistema_core = Constantes.MAP_SISTEMA_CORE_POR_TIPO_ARCHIVO.get(tipo_archivo);

			String sql = obtenerSqlBasicoSalidaCore(tipo_archivo);

			sql = sql + " WHERE r.$RETIROS.ARCHIVO SALIDA " + sistema_core + "$ = " + id_archivo;

			String sql_nuevo = RDServicio.reemplazarNombres(sql);

			List<Map<String, Object>> resp = desencriptarMapa(queryForList("SalidaCoreCorreval.selectSQL", sql_nuevo));

			return resp;

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public List<Map<String, Object>> obtenerArchivosDescargadosPorTipoArchivo(String tipo_archivo, Integer numero_pagina) {

		try {

			String sql = " SELECT ID id_archivo, $ARCHIVO SALIDA CORE.TIPO ARCHIVO$ c_tipo_archivo, $ARCHIVO SALIDA CORE.FECHA CREACION$ c_fecha_creacion, $ARCHIVO SALIDA CORE.NOMBRE DEL GENERADOR$ c_nombre_del_generador  from $ARCHIVO SALIDA CORE$ " + " WHERE $ARCHIVO SALIDA CORE.TIPO ARCHIVO$ = $S(" + tipo_archivo + ")$ order by $ARCHIVO SALIDA CORE.FECHA CREACION$ desc ";

			String sql_nuevo = RDServicio.reemplazarNombres(sql);

			List<Map<String, Object>> resp = desencriptarMapa(queryForListPag("SalidaCoreCorreval.selectSQL", sql_nuevo, numero_pagina, com.osmosyscol.datasuite.config.Constantes.PAGINACIONLISTADO));

			return resp;

		} catch (Exception e) {

			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public Integer obtenerTotalArchivosDescargadosPorTipoArchivo(String tipo_archivo) {

		String sql = " SELECT count(1) from $ARCHIVO SALIDA CORE$ " + " WHERE $ARCHIVO SALIDA CORE.TIPO ARCHIVO$ = $S(" + tipo_archivo + ")$ ";

		String sql_nuevo = RDServicio.reemplazarNombres(sql);

		return (Integer) queryForObject("SalidaCoreCorreval.obtenerTotalArchivosDescargadosPorTipoArchivo", sql_nuevo);

	}

}
