package com.osmosyscol.datasuite.webdata.correval.ordenespago;

import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.mensajeria.EnviaMails;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.servicios.ContenidoServicio;
import com.osmosyscol.datasuite.servlet.ListenerServlet;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import co.htsoft.commons.lang.P;

public class EnvioNotificacionBeneficiario {

	public static void iniciar() {

		try {

			// verifica que este activa la funcionalidad

			Boolean activo = StringUtils.esVerdad(ParametrosInicio.getProperty("pagos.notificacionpagobeneficiario"));

			if (!activo) {
				return;
			}

			// inicia hilo de ejecucion

			new Thread(new Runnable() {
				public void run() {

					EnvioNotificacionBeneficiario.limpiar();

					while (ListenerServlet.running) {

						EnvioNotificacionBeneficiario.ejecutar();

						try {
							Thread.sleep(60000);
						} catch (InterruptedException e) {
							return;
						}
					}
				}
			}).start();

		} catch (Exception e) {
		}

	}

	protected static void limpiar() {

		try {

			// consultar los retiros que finalizaron que no han sido notificado

			String sql = " select distinct r.id id_retiro, r.$RETIROS.VALOR$ valor, tt.nombre_cliente, " + //
					" be.$beneficiario.primer nombre$ nombre1_beneficiario, " + //
					" be.$beneficiario.segundo nombre$ nombre2_beneficiario, " + //
					" be.$beneficiario.primer apellido$ apellido1_beneficiario, " + //
					" be.$beneficiario.segundo apellido$ apellido2_beneficiario, " + //
					" be.$beneficiario.correo electronico$ correo_beneficiario, " + //
					" be.$beneficiario.numero de cuenta$ cuenta_beneficiario, " + //
					" bc2.$banco.nombre$ banco_beneficiario, " + //
					" r.$RETIROS.ESTADO DE RESPUESTA BANCO$ estado_banco " + //

					" from $RETIROS$ r, $ESTADO RESPUESTA BANCO$ e, $GRUPO GIRO$ gg, $CUENTA - BANCO$ cc, $BANCO$ bc, $BENEFICIARIO$ be, $BANCO$ bc2, ts01 tt  " + //

					" where tt.id_carga = r.idcarga " + //
					" and r.$RETIROS.ESTADO DE RESPUESTA BANCO$ = $ESTADO RESPUESTA BANCO.ESTADO INTERNO$ " + //
					" and $ESTADO RESPUESTA BANCO.FINAL$ = $B(true)$ " + //
					" and r.ID = gg.$GRUPO GIRO.CODIGO REGISTRO$_NUM  " + //
					" and gg.$GRUPO GIRO.CUENTA$ = cc.ID  " + //
					" and cc.$CUENTA - BANCO.BANCO$= bc.ID " + //
					" and e.$ESTADO RESPUESTA BANCO.CODIGO BANCO$ = bc.$BANCO.CODIGO$ " + //
					" and r.$RETIROS.BENEFICIARIO$ = be.id " + //
					" and be.$BENEFICIARIO.BANCO$ = bc2.id " + //
					" and r.$RETIROS.FECHA NOTIFICA BENEF$ is null ";

			List<NotificacionBeneficiario> tmp_notificaciones = DS_SqlUtils.queryForList(NotificacionBeneficiario.class, sql);

			P.println(tmp_notificaciones);

			// Filtra para eliminar las rechazadas ( quedarian solo las aprobadas )

			for (NotificacionBeneficiario n : tmp_notificaciones) {

				SimpleLogger.setInfo("Actualizando Fecha de Notificacion de Beneficiario por reinicio: " + n.getId_retiro());
				String sql2 = "update $RETIROS$ set $RETIROS.FECHA NOTIFICA BENEF$ = $T(SYSDATE)$ where id = " + n.getId_retiro();
				DS_SqlUtils.update(sql2);
			}

			// --------- Envia los correos

		} catch (Exception e) {
			SimpleLogger.setError(e);
		}

	}

	protected static void ejecutar() {

		try {

			// consultar los retiros que finalizaron que no han sido notificado

			String sql = " select distinct be.id id_beneficiario, r.id id_retiro, r.$RETIROS.VALOR$ valor, tt.nombre_cliente, " + //
					" be.$beneficiario.primer nombre$ nombre1_beneficiario, " + //
					" be.$beneficiario.segundo nombre$ nombre2_beneficiario, " + //
					" be.$beneficiario.primer apellido$ apellido1_beneficiario, " + //
					" be.$beneficiario.segundo apellido$ apellido2_beneficiario, " + //
					" be.$beneficiario.correo electronico$ correo_beneficiario, " + //
					" be.$beneficiario.numero de cuenta$ cuenta_beneficiario, " + //
					" bc2.$banco.nombre$ banco_beneficiario, " + //
					" r.$RETIROS.ESTADO DE RESPUESTA BANCO$ estado_banco " + //

					" from $RETIROS$ r, $ESTADO RESPUESTA BANCO$ e, $GRUPO GIRO$ gg, $CUENTA - BANCO$ cc, $BANCO$ bc, $BENEFICIARIO$ be, $BANCO$ bc2, ts01 tt  " + //

					" where tt.id_carga = r.idcarga " + //
					" and r.$RETIROS.ESTADO DE RESPUESTA BANCO$ = $ESTADO RESPUESTA BANCO.ESTADO INTERNO$ " + //
					" and $ESTADO RESPUESTA BANCO.FINAL$ = $B(true)$ " + //
					" and r.ID = gg.$GRUPO GIRO.CODIGO REGISTRO$_NUM  " + //
					" and gg.$GRUPO GIRO.CUENTA$ = cc.ID  " + //
					" and cc.$CUENTA - BANCO.BANCO$= bc.ID " + //
					" and e.$ESTADO RESPUESTA BANCO.CODIGO BANCO$ = bc.$BANCO.CODIGO$ " + //
					" and r.$RETIROS.BENEFICIARIO$ = be.id " + //
					" and be.$BENEFICIARIO.BANCO$ = bc2.id " + //
					" and r.$RETIROS.FECHA NOTIFICA BENEF$ is null ";

			List<NotificacionBeneficiario> tmp_notificaciones = DS_SqlUtils.queryForList(NotificacionBeneficiario.class, sql);

			// Filtra para eliminar las rechazadas ( quedarian solo las aprobadas )

			String nombre_empresa = ParametrosInicio.getProperty("nombreEmpresa");

			for (NotificacionBeneficiario n : tmp_notificaciones) {

				n.setNombre_empresa(nombre_empresa);

				if (!n.getEstado_banco().toLowerCase().contains("rechazado")) {
					EnvioNotificacionBeneficiario.enviarCorreo(n);
				} else {
					// si es un rechazo.. lo marca como notificado...
					DS_SqlUtils.update("update $RETIROS$ set $RETIROS.FECHA NOTIFICA BENEF$ = $T(SYSDATE)$ where id = " + n.getId_retiro());
				}
			}

			// --------- Envia los correos

		} catch (Exception e) {
			SimpleLogger.setError(e);
		}

	}

	/**
	 * Envia la notificacion a un beneficiario y actualiza el recaudo
	 * 
	 * @param notificacion
	 */
	private static void enviarCorreo(NotificacionBeneficiario notificacion) {

		// Enviar email al cliente administrativo
		try {
			String html = ContenidoServicio.getInstance().obtenerContenido("General", "EMAIL NOTIFICACION BENEFICIARIO").getTexto();

			html = EnviaMails.replazarTextos(html, notificacion);

			String correo = notificacion.getCorreo_beneficiario();

			// si no tiene correo.. busca en los preinscritos ( carga 0 )
			if (StringUtils.isBlank(correo)) {

				String sql = "select max(b.$beneficiario.correo electronico$) correo from $BENEFICIARIO$ b, $BENEFICIARIO$ b2 " + //

						" WHERE b.idcarga = 0 and b2.id = " + notificacion.getId_beneficiario() + //
						" and b.$beneficiario.id cliente$ = b2.$beneficiario.id cliente$ " + //
						" and b.$beneficiario.tipo de documento$ = b2.$beneficiario.tipo de documento$ " + //
						" and b.$beneficiario.numero de documento$ = b2.$beneficiario.numero de documento$ " + //
						" and b.$beneficiario.banco$ = b2.$beneficiario.banco$ " + //
						" and b.$beneficiario.tipo de cuenta$ = b2.$beneficiario.tipo de cuenta$ " + //
						" and b.$beneficiario.numero de cuenta$ = b2.$beneficiario.numero de cuenta$ "; //

				correo = DS_SqlUtils.queryForObject(String.class, sql);

				if (StringUtils.validaCorreoE(correo)) {
					DS_SqlUtils.update("update $beneficiario$ set $beneficiario.correo electronico$ = $S(" + correo.toLowerCase() + ")$ where id = " + notificacion.getId_beneficiario());
				}

			}

			if (StringUtils.validaCorreoE(correo)) {
				EnviaMails.enviar(notificacion.getCorreo_beneficiario(), notificacion.getNombre_completo(), "Notificacion de Pago", "plano:" + html, null);
			} else {
				SimpleLogger.setWarn("El beneficiario no tiene un correo valido. (" + notificacion.getCorreo_beneficiario() + "), id_retiro = " + notificacion.getId_retiro());
			}

			DS_SqlUtils.update("update $RETIROS$ set $RETIROS.FECHA NOTIFICA BENEF$ = $T(SYSDATE)$ where id = " + notificacion.getId_retiro());

		} catch (Throwable e) {
			SimpleLogger.setError(e);
		}

	}

}
