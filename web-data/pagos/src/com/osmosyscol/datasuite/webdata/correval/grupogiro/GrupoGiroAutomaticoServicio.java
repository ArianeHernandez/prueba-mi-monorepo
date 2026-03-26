package com.osmosyscol.datasuite.webdata.correval.grupogiro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

/**
 * 
 * Clase encargada de crear los grupos de giro automaticamente y generar los archivos de salida a banco respectivos.
 * 
 * @author efperez
 *
 */
public class GrupoGiroAutomaticoServicio {

	public static void generarArhivos() {

		// -------------------------------------------------------------------------------------
		// 1. ASIGNAR BANCOS

		asignarBancoAutomaticamente();

		// -------------------------------------------------------------------------------------
		// 2. AGRUPAR GIROS

		agruparGirosAutomaticamente();

	}

	// -------------------------------------------------------------

	public static void agruparGirosAutomaticamente() {

		SimpleLogger.setInfo("Iniciando agrupacion de grupos de giro");

		List<GrupoGiroFecha> grupos = null;

		{
			String sql = "select gg.$GRUPO GIRO.FECHA PAGO$ fecha , cb.$CUENTA - BANCO.BANCO$ id_banco " //
					+ "from $GRUPO GIRO$ gg, $CUENTA - BANCO$ cb " //
					+ "where gg.$GRUPO GIRO.CUENTA$ = cb.ID "//
					+ "and $grupo giro.archivo$ is null "//
					+ "group by gg.$GRUPO GIRO.FECHA PAGO$, cb.$CUENTA - BANCO.BANCO$";

			grupos = DS_SqlUtils.queryForList(GrupoGiroFecha.class, sql);
		}

		// para cada uno de los grupos se generara un archivo

		for (GrupoGiroFecha grupoGiroFecha : grupos) {
			Integer id_archivo = generarArchivo(grupoGiroFecha);

			SimpleLogger.setInfo("Archivo Generado: " + id_archivo);

		}

	}

	// --------------------------------------------------------------

	private static Integer generarArchivo(GrupoGiroFecha grupoGiroFecha) {

		// consulta los grupos de giro que se encuentren pendientes segun el banco y fecha..

		String sql = "select gg.ID " //
				+ "from $GRUPO GIRO$ gg, $CUENTA - BANCO$ cb " //
				+ "where gg.$GRUPO GIRO.CUENTA$ = cb.ID "//
				+ "and $grupo giro.archivo$ is null "//
				+ "and gg.$GRUPO GIRO.FECHA PAGO$ = #fecha# " //
				+ "and cb.$CUENTA - BANCO.BANCO$ = $id_banco$";

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("fecha", grupoGiroFecha.getFecha());
		params.put("id_banco", grupoGiroFecha.getId_banco());

		List<Integer> gruposGiro = DS_SqlUtils.queryForList(Integer.class, sql, params);

		// crea el archivo respectivo... segun el banco y fecha

		String nombre_banco = DS_SqlUtils.queryForObject(String.class, "select $BANCO.NOMBRE$ nombre from $BANCO$ where id = $id_banco$", grupoGiroFecha);

		return GrupoGiroServicio.getInstance().crearArchivo(gruposGiro, "AUTOBOT", null, nombre_banco);
	}

	// --------------------------------------------------------------

	public static class GrupoGiroFecha {
		private Date fecha;

		private Integer id_banco;

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public Integer getId_banco() {
			return id_banco;
		}

		public void setId_banco(Integer id_banco) {
			this.id_banco = id_banco;
		}

	}

	// -------------------------------------------------------------

	public static boolean asignarBancoAutomaticamente() {

		SimpleLogger.setInfo("Iniciando asignacion automatica de banco");

		// ---- obtiene las cargas pendientes.

		List<GrupoGiroCarga> cargas = GrupoGiroServicio.getInstance().obtenerCargas();

		if (cargas == null || cargas.size() == 0) {
			return true;
		}

		List<GrupoGiro> registrosGrupoGiro = new ArrayList<GrupoGiro>();

		for (GrupoGiroCarga ggc : cargas) {

			// consulta los retiros de la carga

			List<Retiro> retiros = consultarRetirosporCarga(ggc.getId_carga());

			for (Retiro retiro : retiros) {
				GrupoGiro gg = new GrupoGiro();

				Integer cuenta_origen = calcularCuentaOrigen(retiro.getOrigen(), retiro.getId_producto(), retiro.getId_banco_destino());

				Date hoy = DateUtils.truncate(HorarioServicio.getInstance().obtenerFechaActual(), Calendar.SECOND);

				Date fecha = retiro.getFecha() == null ? hoy : DateUtils.truncate(retiro.getFecha(), Calendar.SECOND);

				if (hoy.getTime() > fecha.getTime()) {
					fecha = hoy;
				}

				gg.setCodigo_registro(retiro.getId());
				gg.setId_carga(ggc.getId_carga());
				gg.setFecha_pago(fecha);
				gg.setId_estructura(ggc.getId_estructura());
				gg.setCuenta(cuenta_origen);

				registrosGrupoGiro.add(gg);
			}

		}

		return GrupoGiroServicio.getInstance().guardarGruposGiro(registrosGrupoGiro);

	}

	// -----------------------------------------------------------------------------------------------

	private static Integer calcularCuentaOrigen(String cod_origen, Integer id_producto, Integer id_banco_destino) {

		Integer id_cuenta = null;

		// si ya tiene definido el origen.. toma el origen

		if (cod_origen != null) {
			String sql = "select max(ID) ID from $CUENTA - BANCO$ cb " //
					+ "where $CUENTA - BANCO.PRODUCTO$ = $id_producto$ " //
					+ "and $CUENTA - BANCO.CODIGO$ = #cod_origen#";

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id_producto", id_producto);
			params.put("cod_origen", cod_origen);

			id_cuenta = DS_SqlUtils.queryForObject(Integer.class, sql, params);
		}

		// si no tiene origen definido mira el que tenga el mismo banco destino

		if (id_cuenta == null) {
			String sql = "select max(ID) ID from $CUENTA - BANCO$ cb " //
					+ "where $CUENTA - BANCO.PRODUCTO$ = $id_producto$ " //
					+ "and $CUENTA - BANCO.BANCO$ = $id_banco$";

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id_producto", id_producto);
			params.put("id_banco", id_banco_destino);

			id_cuenta = DS_SqlUtils.queryForObject(Integer.class, sql, params);
		}

		// si no toma el de por defecto...

		if (id_cuenta == null) {
			String sql = "select max(ID) ID from $CUENTA - BANCO$ cb " //
					+ "where $CUENTA - BANCO.PRODUCTO$ = $id_producto$ " //
					+ "and $CUENTA - BANCO.POR DEFECTO$ = $B(true)$";

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id_producto", id_producto);

			id_cuenta = DS_SqlUtils.queryForObject(Integer.class, sql, params);
		}

		return id_cuenta;
	}

	// -----------------------------------------------------------------------------------------------

	private static List<Retiro> consultarRetirosporCarga(Integer id_carga) {
		String sql = "select r.ID, " //
				+ "$RETIROS.PRODUCTO$ ID_PRODUCTO, " //
				+ "$RETIROS.BENEFICIARIO$ ID_BENEFICIARIO, " //
				+ "$RETIROS.BENEFICIARIO$ ESTADO, " //
				+ "$RETIROS.FECHA TRANSACCION$ FECHA, " //
				+ "$RETIROS.CUENTA ORIGEN$ ORIGEN, " //
				+ "$BENEFICIARIO.BANCO$ ID_BANCO_DESTINO " //
				+ "from $RETIROS$ r, $BENEFICIARIO$ b " //
				+ "where $RETIROS.BENEFICIARIO$ = b.ID " //
				+ "and r.idcarga = $id_carga$"; //

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_carga", id_carga);

		return DS_SqlUtils.queryForList(Retiro.class, sql, params);
	}

	// -----------------------------------------------------------------------------------------------

	public static class Retiro {
		private Integer id;
		private Integer id_producto;
		private Integer id_beneficiario;
		private String estado;
		private Integer id_banco_destino;
		private Date fecha;
		private String origen;

		public Date getFecha() {
			return fecha;
		}

		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getId_producto() {
			return id_producto;
		}

		public void setId_producto(Integer id_producto) {
			this.id_producto = id_producto;
		}

		public Integer getId_beneficiario() {
			return id_beneficiario;
		}

		public void setId_beneficiario(Integer id_beneficiario) {
			this.id_beneficiario = id_beneficiario;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public Integer getId_banco_destino() {
			return id_banco_destino;
		}

		public void setId_banco_destino(Integer id_banco_destino) {
			this.id_banco_destino = id_banco_destino;
		}

		public String getOrigen() {
			return origen;
		}

		public void setOrigen(String origen) {
			this.origen = origen;
		}

	}

}
