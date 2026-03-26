package com.osmosyscol.datasuite.correval.thread;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.datasuite.logica.dto.FtpUsuario;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.FtpUsuarioServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.webdata.correval.servicios.OrdenesPagoServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaAutomaticaServicio;

public class HiloCargaFtpUsuario extends Thread {

	private FtpUsuario ftpUsuario;

	public HiloCargaFtpUsuario(FtpUsuario ftpUsuario) {
		this.ftpUsuario = ftpUsuario;
	}

	@Override
	public void run() {

		try {

			while (!AutenticacionServicio.SHUTDOWN) {
				
				if(ftpUsuario != null){
					this.ftpUsuario = FtpUsuarioServicio.getInstance().obtenerFtpUsuario(this.ftpUsuario.getId_ftp_usuario());
				}
				
				SimpleLogger.setDebug("Iniciando Carga Automatica de Ordenes de Pago, parametros [id: " + ftpUsuario.getId_ftp_usuario() + ", id_usuario=" + ftpUsuario.getId_usuario() + "]\n" + ftpUsuario.toString());

				// Verificamos que correval no haya desactivado el servicio
				Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(ftpUsuario.getId_usuario());
				if (usuario != null) {
					if (!"S".equals(usuario.getUso_ftp_usuario())) {
						SimpleLogger.setInfo("Se detiene la Carga Automatica de Ordenes de Pago debido a que ha sido desactivado para el usuario, parametros [id: " + ftpUsuario.getId_ftp_usuario() + ", id_usuario=" + ftpUsuario.getId_usuario() + "]");
						FtpUsuarioServicio.getInstance().asociarFtpUsuarioProceso(ftpUsuario.getId_ftp_usuario(), null);
						OrdenesPagoServicio.getInstance().detenerHilo(ftpUsuario);
						return;
					}
				}else{
					SimpleLogger.setInfo("Se detiene la Carga Automatica de Ordenes de Pago debido a que el usuario no se pudo recuperar, parametros [id: " + ftpUsuario.getId_ftp_usuario() + ", id_usuario=" + ftpUsuario.getId_usuario() + "]");
					FtpUsuarioServicio.getInstance().asociarFtpUsuarioProceso(ftpUsuario.getId_ftp_usuario(), null);
					OrdenesPagoServicio.getInstance().detenerHilo(ftpUsuario);
					return;
				}

				if (FtpUsuarioServicio.getInstance().validarHorario(ftpUsuario)) {
					CargaAutomaticaServicio cargaAutomaticaServicio = new CargaAutomaticaServicio();
					cargaAutomaticaServicio.cargarArchivosSamba(ftpUsuario);
					cargaAutomaticaServicio = null;
				} else {
					SimpleLogger.setInfo("No se realizo la carga Automatica de Ordenes de Pago, debido a que no se encuentra en el horario establecido parametros [id: " + ftpUsuario.getId_ftp_usuario() + ", id_usuario=" + ftpUsuario.getId_usuario() + "]");
				}

				SimpleLogger.setInfo("Esperando " + ftpUsuario.getFrecuencia_lectura() + " minutos");

				sleep(ftpUsuario.getFrecuencia_lectura() * 60000);

			}
			
		} catch (Exception e) {
			SimpleLogger.setInfo("Finalizando Hilo Carga Automatica de Ordenes de Pago, parametros [id: " + ftpUsuario.getId_ftp_usuario() + ", id_usuario=" + ftpUsuario.getId_usuario() + "]");
			return;
		}

	}
}
