package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.auditoria.dto.Auditoria;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.AuditoriaServicio;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.correval.bancos.entrada.RespuestaBanco;
import com.osmosyscol.datasuite.webdata.correval.webservice.retiros.ActualizarRetiroPorRespuestaBancoSOAPStub;
import com.osmosyscol.datasuite.webdata.correval.webservice.retiros.TipoElementoEntradaactualizarRetiroPorRespuestaBanco;
import com.osmosyscol.datasuite.webdata.correval.webservice.retiros.TipoElementoSalidaactualizarRetiroPorRespuestaBanco;
import com.osmosyscol.datasuite.webdata.correval.webservice.retiros.TipoEntradaactualizarRetiroPorRespuestaBanco;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class RetiroServicio {

	private static RetiroServicio retiroServicio;

	private RetiroServicio() {
	}

	public static RetiroServicio getInstance() {
		if (retiroServicio == null) {
			retiroServicio = new RetiroServicio();
		}
		return retiroServicio;
	}

	public TipoElementoSalidaactualizarRetiroPorRespuestaBanco[] actualizarRetiroPorRespuestaBanco(RespuestaBanco respuestaBanco, String tipo_actualizacion) {

		try {
			URL url;
			url = new URL(ParametrosInicio.getProperty("modelatos.endpoint") + "/ws/ActualizarRetiroPorRespuestaBanco");
			SimpleLogger.setDebug("url " + url);

			ActualizarRetiroPorRespuestaBancoSOAPStub soapStub = new ActualizarRetiroPorRespuestaBancoSOAPStub(url, null);

			// Elmento de entrada
			TipoElementoEntradaactualizarRetiroPorRespuestaBanco elementoEntrada = new TipoElementoEntradaactualizarRetiroPorRespuestaBanco();
			elementoEntrada.setCodigo_referencia(respuestaBanco.getCodigo_referencia());
			elementoEntrada.setEstado_respuesta(respuestaBanco.getEstado_respuesta());
			elementoEntrada.setTipo_actualizacion(tipo_actualizacion);
			elementoEntrada.setRechazado(respuestaBanco.getEstado_respuesta().toLowerCase().contains("rechazado"));
			
			// Tipo entrada
			TipoEntradaactualizarRetiroPorRespuestaBanco tipoEntrada = new TipoEntradaactualizarRetiroPorRespuestaBanco();
			tipoEntrada.setElementoEntrada(elementoEntrada);

			// Consumo del web service
			return soapStub.actualizarRetiroPorRespuestaBanco(tipoEntrada);

		} catch (Exception e) {

			SimpleLogger.setError("Error al actualizar el retir " + respuestaBanco.getCodigo_referencia() + " de estado", e);
		}
		return null;

	}

	public Integer actualizarRetirosPorRespuestasBanco(List<RespuestaBanco> respuestasBanco, HttpServletRequest request) {
		Integer registrosActualizados = 0;

		try {

			if (respuestasBanco != null && respuestasBanco.size() > 0) {
				for (RespuestaBanco respuestaBanco : respuestasBanco) {

					if (!respuestaBanco.getTieneErrores()) {

						TipoElementoSalidaactualizarRetiroPorRespuestaBanco[] respuesta = actualizarRetiroPorRespuestaBanco(respuestaBanco, "Archivo");
						Boolean exitoso = false;

						// Obtenemos el resultado para el primer registro
						if (respuesta != null && respuesta[0].getSalida() == 1) {
							respuestaBanco.setTieneErrores(false);
							registrosActualizados++;
							exitoso = true;
						} else {
							respuestaBanco.setTieneErrores(true);

						}

						// insertar auditoria
						AuditoriaServicio.getInstance().insertarAuditoria(new Integer(respuestaBanco.getCodigo_referencia()), "ACARERB", request, exitoso, respuestaBanco.getEstado_respuesta());

					}

				}

			} else {
				registrosActualizados = 0;
			}
		} catch (Exception e) {
			SimpleLogger.setError("Se ha generado un error", e);

		}

		return registrosActualizados;
	}

	private String obtenerRetirosSQL(String identifica_cliente, Integer idcarga, Integer id_banco_girador) {
		String sql = " SELECT " + //
				" r.id as id_retiro, " + //
				" r.idcarga as id_carga, " + //
				" r.$retiros.id cliente$ as cliente, " + //
				" r.$RETIROS.TIPO DE RETIRO$ as tipo_retiro, " + //
				" g.$GRUPO GIRO.FECHA PAGO$ as fecha_giro, " + //
				" b.$BANCO.NOMBRE$ as banco_girador, " + //
				" b.id as cod_banco_girador, " + //
				" r.$RETIROS.VALOR$ as valor, " + //
				" r.$RETIROS.ESTADO DE RESPUESTA BANCO$ as respuesta_banco, " + //
				" be.$beneficiario.numero de documento$ as identificacion_beneficiario, " + //
				" be.$beneficiario.primer nombre$ as nombre_beneficiario, " + //
				" b2.$BANCO.NOMBRE$ as banco_destino, " + //
				" be.$beneficiario.numero de cuenta$ as cuenta_destino " + //
				" FROM " + //
				" $RETIROS$ r, $GRUPO GIRO$ g, $CUENTA - BANCO$ c, $BANCO$ b, $BENEFICIARIO$ be, $BANCO$ b2 " + //
				" where " + //
				" r.ID = g.$GRUPO GIRO.CODIGO REGISTRO$_num " + //
				" and c.ID = g.$GRUPO GIRO.CUENTA$ " + //
				" and b.ID = c.$CUENTA - BANCO.BANCO$ " + //
				" and be.ID = $RETIROS.BENEFICIARIO$ " + //
				" and b2.id = be.$beneficiario.banco$ " + //
				" and $GRUPO GIRO.ARCHIVO$ is not null ";

		if (idcarga != null) {
			sql += "and r.IDCARGA = " + idcarga + " ";
		}

		if (id_banco_girador != null) {
			sql += "and c.$CUENTA - BANCO.BANCO$ = " + id_banco_girador + " ";
		}

		if (StringUtils.isNotEmpty(identifica_cliente)) {

			Usuario usuario = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(identifica_cliente, null);

			Integer id_usuario = 0;

			if (usuario != null) {
				id_usuario = usuario.getId_usuario();
			}

			sql += "and r.$retiros.id cliente$ = $I(" + id_usuario + ")$ ";
		}

		sql += " order by g.$GRUPO GIRO.FECHA PAGO$ desc";

		sql = RDServicio.reemplazarNombres(sql);

		return sql;
	}

	private String obtenerRetirosSQLContar(String identifica_cliente, Integer idcarga, Integer id_banco_girador) {
		String sql = " select count(*) FROM " + //
				" $RETIROS$ r, $GRUPO GIRO$ g, $CUENTA - BANCO$ c" + //
				" where " + //
				" r.ID = g.$GRUPO GIRO.CODIGO REGISTRO$_num " + //
				" and c.ID = g.$GRUPO GIRO.CUENTA$ " + //
				" and g.$GRUPO GIRO.ARCHIVO$ is not null ";

		if (idcarga != null) {
			sql += "and r.IDCARGA = " + idcarga + " ";
		}

		if (id_banco_girador != null) {
			sql += "and c.$CUENTA - BANCO.BANCO$ = " + id_banco_girador + " ";
		}

		if (StringUtils.isNotEmpty(identifica_cliente)) {

			Usuario usuario = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(identifica_cliente, null);

			Integer id_usuario = 0;

			if (usuario != null) {
				id_usuario = usuario.getId_usuario();
			}

			sql += "and r.$retiros.id cliente$ = $I(" + id_usuario + ")$ ";
		}

		sql = RDServicio.reemplazarNombres(sql);

		return sql;
	}

	public List<Map<String, Object>> obtenerRetirosMultiplesParametros(String identifica_cliente, Integer idcarga, Integer id_banco_girador, Integer tamanoPagina, Integer numeroPagina) {
		try {

			String sql = obtenerRetirosSQL(identifica_cliente, idcarga, id_banco_girador);

			SimpleLogger.setDebug("sql -> " + sql);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			List<Map<String, Object>> retiros = sqlDao.selectSQLPaginado(sql, numeroPagina, tamanoPagina);

			// desencriptar
			if (retiros != null) {
				retiros = SQLServicio.desencriptarMapaStringFormat(retiros);
			}

			return retiros;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public Integer obtenerCantidadRetirosMultiplesParametros(String identifica_cliente, Integer idcarga, Integer id_banco_girador) {
		try {

			String sql = obtenerRetirosSQLContar(identifica_cliente, idcarga, id_banco_girador);

			SimpleLogger.setDebug("sql -> " + sql);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			return sqlDao.selectSQLNumber(sql);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public List<Integer> obtenerSQLRetirosConArchivoSalidaSinRespuestaBanco(Integer cantidadDiasHabilesT) {

		Date fechaLimiteAntiguedad = fechaRestandoDiasHabiles(cantidadDiasHabilesT);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		String fechaLimiteAntiguedad_formated = format.format(fechaLimiteAntiguedad);

		SimpleLogger.setInfo("Buscando retiros con archivo salida banco generado antes de: " + fechaLimiteAntiguedad_formated);

		try {

			String sql = "select r.ID ID " + "from $RETIROS$ r, $GRUPO GIRO$ gg, $ARCHIVO$ a " + "where r.ID = gg.$GRUPO GIRO.CODIGO REGISTRO$_num " + // une retiros y gg
					"and gg.$GRUPO GIRO.ARCHIVO$ = a.ID " + // une gg y archivo
					"and r.$RETIROS.ESTADO DE RESPUESTA BANCO$ IS null " + "and a.$ARCHIVO.FECHA CREACION$ < $T('" + fechaLimiteAntiguedad_formated + "')$ ";

			SimpleLogger.setDebug("sql -> " + sql);

			sql = RDServicio.reemplazarNombres(sql);

			SimpleLogger.setDebug("sql -> " + sql);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			List<Integer> retiros = sqlDao.selectSQLNumberList(sql);

			return retiros;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error al obtenerSQLRetirosConArchivoSalidaSinRespuestaBanco", e);
		}

		return null;
	}

	public Date fechaRestandoDiasHabiles(Integer cantidadDiasHabilesT) {

		Calendar calendario = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		List<String> festivos = FestivosServicio.getInstance().getFestivos();

		int i = 0;
		while (i < cantidadDiasHabilesT) {
			calendario.add(Calendar.DATE, -1);
			Date dia = calendario.getTime();
			String dia_str = format.format(dia);
			if (calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && festivos.indexOf(dia_str) == -1)
				i++;
		}

		return calendario.getTime();
	}

	// ---------------------------------------------------------------------------------------------------
	/**
	 * Determina si hoy es un dia habil
	 * 
	 * @return
	 */

	public Boolean esDiaHabil() {

		Calendar calendario = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		List<String> festivos = FestivosServicio.getInstance().getFestivos();

		Date dia = calendario.getTime();
		String dia_str = format.format(dia);

		return (calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && festivos.indexOf(dia_str) == -1);
	}

	// ---------------------------------------------------------------------------------------------------

	public Integer actualizarEstadosRetirosAntiguos(Integer cantidadDiasHabilesT) {
		Integer registrosActualizados = 0;

		List<Integer> retiros = this.obtenerSQLRetirosConArchivoSalidaSinRespuestaBanco(cantidadDiasHabilesT);
		String estadoLog = "N";
		for (Integer id : retiros) {

			try {
				String updateSql = "update $RETIROS$" // 
						+ " set $RETIROS.ESTADO DE RESPUESTA BANCO$ = $S('APLICADO')$, " // 
						+ " $RETIROS.TIPO ACTUALIZACION$ = $S('Automatico')$, " // 
						+ " $RETIROS.ESTADO$=$S('F')$, " //
						+ " $RETIROS.ACTUALIZACION$ = '" + Crypto.E(new Date()) + "'" 
						+ " where ID = " + id;

				SimpleLogger.setDebug("sql -> " + updateSql);

				updateSql = RDServicio.reemplazarNombres(updateSql);

				SimpleLogger.setDebug("sql -> " + updateSql);

				SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

				registrosActualizados += sqlDao.updateSQL(updateSql);

				estadoLog = "Y";

			} catch (Exception e) {
				SimpleLogger.setError("Se ha generado un error al actualizarEstadosRetirosAntiguos", e);
			}

			Auditoria auditoria = new Auditoria();
			auditoria.setCodigo_accion("AARERB");
			auditoria.setFecha_accion(new Date());
			auditoria.setIp("localhost");
			auditoria.setNuevo_valor("APLICADO");
			auditoria.setTarget(id);
			auditoria.setUsuario("system");
			auditoria.setEstado(estadoLog);
			AuditoriaServicio.getInstance().insertarAuditoria(auditoria);

		}

		return registrosActualizados;

	}

	public List<Auditoria> obtenerHistorialRetiro(Integer idRetiro) {

		return AuditoriaServicio.getInstance().obtenerAuditoriasEspecificas(idRetiro, "AARERB", "AMRERB", "ACARERB");

	}

}
