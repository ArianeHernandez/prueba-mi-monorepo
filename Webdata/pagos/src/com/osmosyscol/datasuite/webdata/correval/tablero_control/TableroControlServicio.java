package com.osmosyscol.datasuite.webdata.correval.tablero_control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.servlet.InitApp;

import co.htsoft.commons.lang.P;

public class TableroControlServicio {

	private static TableroControlServicio instacia;

	private TableroControlServicio() {
	}

	public static TableroControlServicio getInstance() {
		if (instacia == null) {
			instacia = new TableroControlServicio();
		}
		return instacia;
	}

	// ---------------------------

	public List<Banco> obtenerBancos() {
		try {
			Runtime.getRuntime().gc();

			String sql = "select distinct banco.id, banco.c1220 codigo, banco.c1221 nombre from t1180 banco, t1181 bcorreval where banco.id = bcorreval.c1224 order by banco.c1221";

			List<Map<String, Object>> datos = SQLServicio.selectSQL(sql, 2);

			if (datos == null) {
				return null;
			}

			List<Banco> bancos = new ArrayList<Banco>();
			for (Map<String, Object> registro : datos) {
				Banco banco = new Banco();
				banco.setId(toLong(registro.get("ID")));
				banco.setCodigo(StringUtils.toString(Crypto.DF(registro.get("CODIGO"))));
				banco.setNombre(StringUtils.toString(Crypto.DF(registro.get("NOMBRE"))));
				bancos.add(banco);
			}

			return bancos;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ---------------------------

	public List<Banco> obtenerTotalBancos() {
		try {

			String sql = "select distinct banco.id, banco.c1220 codigo, banco.c1221 nombre from t1180 banco, t1181 bcorreval where banco.id = bcorreval.c1224 order by banco.c1221";

			List<Map<String, Object>> datos = SQLServicio.selectSQL(sql, 2);

			if (datos == null) {
				return null;
			}

			List<Retiro> retiros = obtenerTotalRetiros();
			List<Long> cargas_pendientes = obtenerCargasPendientes();
			List<Long> cargas_retrazadas = obtenerCargasRetrazadas();

			for (Retiro retiro : retiros) {
				retiro.setPendiente(cargas_pendientes.contains(retiro.getId_carga()));
				retiro.setRetrazado(cargas_retrazadas.contains(retiro.getId_carga()));
			}

			List<Banco> bancos = new ArrayList<Banco>();

			Map<String, Object> bancodefault = new HashMap<String, Object>();
			bancodefault.put("ID", -1L);
			bancodefault.put("CODIGO", "-1");
			bancodefault.put("NOMBRE", "Z_INDEFINIDO");
			datos.add(bancodefault);

			for (Map<String, Object> registro : datos) {

				Banco banco = new Banco();
				banco.setId(toLong(registro.get("ID")));
				banco.setCodigo(StringUtils.toString(Crypto.DF(registro.get("CODIGO"))));
				banco.setNombre(StringUtils.toString(Crypto.DF(registro.get("NOMBRE"))));
				bancos.add(banco);

				for (Retiro retiro : retiros) {

					long id_banco = (retiro.getId_banco_actual() != null) ? retiro.getId_banco_actual() : (retiro.getId_banco_beneficiario() != null) ? retiro.getId_banco_beneficiario() : (retiro.getId_banco_default() != null ? retiro.getId_banco_default() : -1L);

					if (banco.getId() == id_banco) {

						// -- Recibidos
						if (retiro.getPendiente() && Constantes.CARGA_ESTADO_SUBIDO.equalsIgnoreCase(retiro.getEstado())) {
							banco.setNum_recibidos(banco.getNum_recibidos() + 1);
							if (retiro.getValor() != null) {
								banco.setTotal_recibidos(banco.getTotal_recibidos().add(retiro.getValor()));
							}
						}

						// -- Transito
						if ((!retiro.getPendiente()) && Constantes.CARGA_ESTADO_SUBIDO.equalsIgnoreCase(retiro.getEstado())) {
							banco.setNum_transito(banco.getNum_transito() + 1);
							if (retiro.getValor() != null) {
								banco.setTotal_transito(banco.getTotal_transito().add(retiro.getValor()));
							}
						}

						// -- Retrazadas (Pendientes)
						if (retiro.getRetrazado() && Constantes.CARGA_ESTADO_SUBIDO.equalsIgnoreCase(retiro.getEstado())) {
							banco.setNum_pendiente(banco.getNum_pendiente() + 1);
							if (retiro.getValor() != null) {
								banco.setTotal_pendiente(banco.getTotal_pendiente().add(retiro.getValor()));
							}
						}

						// -- Aprobados
						if (Constantes.CARGA_ESTADO_APROBADO.equalsIgnoreCase(retiro.getEstado())) {

							if (retiro.getId_archivo() == null) {

								banco.setNum_aprobado(banco.getNum_aprobado() + 1);
								if (retiro.getValor() != null) {
									banco.setTotal_aprobados(banco.getTotal_aprobados().add(retiro.getValor()));
								}

							} else {
								banco.setNum_agrupado(banco.getNum_agrupado() + 1);
								if (retiro.getValor() != null) {
									banco.setTotal_agrupados(banco.getTotal_agrupados().add(retiro.getValor()));
								}

								if (StringUtils.contains(retiro.getRespuesta_banco(), "RECHAZADO")) {
									banco.setNum_rechazo_banco(banco.getNum_rechazo_banco() + 1);
									if (retiro.getValor() != null) {
										banco.setTotal_rechazo_banco(banco.getTotal_rechazo_banco().add(retiro.getValor()));
									}
								}
							}
						}

						// -- Rechazos
						if (Constantes.CARGA_ESTADO_RECHAZADO.equalsIgnoreCase(retiro.getEstado())) {
							banco.setNum_rechazos(banco.getNum_rechazos() + 1);
							if (retiro.getValor() != null) {
								banco.setTotal_rechazos(banco.getTotal_rechazos().add(retiro.getValor()));
							}
						}
					}
				}
			}

			return bancos;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ---------------------------
	public List<Retiro> obtenerTotalRetiros() {

		List<Retiro> retiros = new ArrayList<Retiro>();

		try {

			String sql = "select retiro.idcarga, retiro.id, retiro.c1422 valor,  carga.estado, " + " retiro.C1881 respuesta_banco, " //
					+ " (select max(grupogiro.c1542) from t1220 grupogiro where grupogiro.c1261_num = retiro.id) id_archivo, " //
					+ " (select banco.id from t1220 grupogiro, t1181 cuentacorreval, t1180 banco where grupogiro.c1261_num = retiro.id || '' and grupogiro.c1380 = cuentacorreval.id and banco.id = cuentacorreval.c1224 and rownum = 1) id_banco_actual, " //
					+ " (select distinct beneficiario.c2067 from t1181 cuentacorreval, t1300 beneficiario where cuentacorreval.c1800 = retiro.c1803 and retiro.c1940 = beneficiario.ID and beneficiario.c2067 = cuentacorreval.c1224  and rownum = 1 ) id_banco_beneficiario, " //
					+ " (select banco.id from t1181 cuentacorreval, t1180 banco where cuentacorreval.c1800 = retiro.c1803 and cuentacorreval.c1801 = $B(true)$ and banco.id = cuentacorreval.c1224 and rownum = 1 ) id_banco_default "

					+ " from t1340 retiro, ts01 carga " //
					+ " where ( (carga.estado in ('A', 'R') and carga.fecha > trunc(sysdate)) or carga.estado in ('S') or " //
					+ " ( carga.estado in ('C') and (select count(1) from t1220 grupogiro where grupogiro.c1261_num = retiro.id and c1542 is not null and C1263 >= $T(TODAY)$) > 0 ) or " //
					+ " ( carga.estado in ('C') and (select count(1) from t1220 grupogiro where grupogiro.c1261_num = retiro.id and c1542 is not null) = 0 ) ) " //
					+ " and  retiro.idcarga = carga.id_carga and retiro.c1804 = $S(ACH)$ ";

			List<Map<String, Object>> datos = SQLServicio.selectSQL(RDServicio.reemplazarNombres(sql), 2);

			if (datos == null) {
				return retiros;
			}

			for (Map<String, Object> registro : datos) {

				Retiro retiro = new Retiro();

				retiro.setId_carga(toLong(registro.get("IDCARGA")));
				retiro.setId_retiro(toLong(registro.get("ID")));
				retiro.setId_banco_actual(toLong(registro.get("ID_BANCO_ACTUAL")));
				retiro.setId_banco_default(toLong(registro.get("ID_BANCO_DEFAULT")));
				retiro.setId_banco_beneficiario(toLong(registro.get("ID_BANCO_BENEFICIARIO")));
				retiro.setId_archivo(toLong(registro.get("ID_ARCHIVO")));
				retiro.setRespuesta_banco((String) Crypto.DF(registro.get("RESPUESTA_BANCO")));

				Object valor = registro.get("VALOR");
				retiro.setValor(valor == null ? null : new BigDecimal(Crypto.DF(valor).toString()));
				retiro.setEstado((String) registro.get("ESTADO"));

				retiros.add(retiro);
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return retiros;
	}

	private static Long toLong(Object num) {
		if (num == null) {
			return null;
		}

		if (num instanceof BigDecimal) {
			return ((BigDecimal) num).toBigInteger().longValue();
		}

		return new Long(num.toString());

	}

	// ---------------------------
	/**
	 * Retorna el id de las cargas que no han iniciado proceso correval
	 */
	public List<Long> obtenerCargasPendientes() {

		List<Long> cargas = new ArrayList<Long>();

		try {

			String sql = "select distinct c.id_carga ID from dsv_carga c, dst_formato f " + "where c.id_formato = f.id_formato " + "and f.id_estructura = 1340 " // ESTRUCTURA DE RETIROS
					+ "and c.estado = 'S'" + "and id_carga not in (select ci.id_carga from dst_carga_instancia ci where ci.fecha_salida is not null)";

			List<Map<String, Object>> datos = SQLServicio.selectSQL(sql, 1);

			if (datos == null) {
				return cargas;
			}

			for (Map<String, Object> registro : datos) {
				cargas.add(toLong(registro.get("ID")));
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return cargas;
	}

	// ---------------------------
	/**
	 * retorna el listado de carggas que en alguna instancia se encuentran retrazados
	 */
	public List<Long> obtenerCargasRetrazadas() {

		List<Long> cargas = new ArrayList<Long>();

		try {

			String sql = "select distinct c.id_carga id " + "from dst_carga_instancia ci, dst_instancia ins, dst_carga c, dst_formato f " + "where ci.id_instancia = ins.id_instancia " + "and f.id_formato = c.id_formato " + "and f.id_estructura = 1340 " + "and ci.fecha_salida is null " + "and c.id_carga = ci.id_carga " + "and c.estado = 'S' " + "and ins.tiempo < minutes(ci.fecha_llegada, sysdate)";

			List<Map<String, Object>> datos = SQLServicio.selectSQL(sql, 1);

			if (datos == null) {
				return cargas;
			}

			for (Map<String, Object> registro : datos) {
				cargas.add(toLong(registro.get("ID")));
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return cargas;
	}

	// ---------------------------
	/**
	 * retorna el listado de cargga
	 */
	public Map<String, Object> obtenerCargasEnProceso() {

		Runtime.getRuntime().gc();

		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, BigDecimal> valores = new HashMap<String, BigDecimal>();
		List<Carga> cargas = new ArrayList<Carga>();

		try {

			String sql = "select c.id_carga id_carga, " //
					+ "p.nombre nombre_cliente, p.apellido apellido_cliente, " //
					+ "pr.nombre nombre_proceso, " //
					+ "c.valor_total valor_lote, i.nombre nombre_instancia, " //
					+ "ci.fecha_llegada," //
					+ "minutes( ci.fecha_llegada, sysdate) tiempo_cola, " //
					+ "i.tiempo tiempo_instancia, " //
					+ "(minutes( ci.fecha_llegada, sysdate) * 100)/i.tiempo porcentaje " //
					+ "from dst_carga c, dst_usuario u, dst_persona p, dst_carga_instancia ci, dst_proceso_admin pr, dst_instancia i " //
					+ "where c.estado = 'S' " //
					+ "and ci.id_carga = c.id_carga " //
					+ "and u.id_usuario = c.id_usuario " //
					+ "and p.id_persona = u.id_persona " //
					+ "and pr.id_proceso_admin = i.id_proceso_admin " //
					+ "and i.id_instancia = ci.id_instancia " //
					+ "and ci.fecha_salida is null";

			List<Map<String, Object>> datos = SQLServicio.selectSQL(sql, 1);

			if (datos == null) {
				return ret;
			}

			for (Map<String, Object> registro : datos) {
				Carga carga = new Carga();
				carga.setId_carga(toLong(registro.get("ID_CARGA")));
				carga.setNombre_cliente((String) registro.get("NOMBRE_CLIENTE"));
				carga.setApellido_cliente((String) registro.get("APELLIDO_CLIENTE"));
				carga.setNombre_proceso((String) registro.get("NOMBRE_PROCESO"));
				carga.setTiempo_cola(toLong(registro.get("TIEMPO_COLA")));
				carga.setTiempo_instancia(toLong(registro.get("TIEMPO_INSTANCIA")));
				carga.setValor_lote((BigDecimal) Crypto.DF(registro.get("VALOR_LOTE")));
				carga.setNombre_instancia((String) registro.get("NOMBRE_INSTANCIA"));
				carga.setFecha_llegada((Date) registro.get("FECHA_LLEGADA"));
				carga.setPorcentaje(toLong(registro.get("PORCENTAJE")));

				cargas.add(carga);
				valores.put(carga.getId_carga() + "", carga.getValor_lote());
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		ret.put("cargas", cargas);
		ret.put("valores", valores);

		return ret;
	}

	public static void main(String[] args) {
		InitApp.startUp();

		P.println(TableroControlServicio.getInstance().obtenerTotalBancos());

		System.exit(0);

	}

}
