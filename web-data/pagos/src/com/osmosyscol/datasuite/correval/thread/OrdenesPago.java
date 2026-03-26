package com.osmosyscol.datasuite.correval.thread;

import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.FtpUsuario;
import com.osmosyscol.datasuite.logica.servicios.FtpUsuarioServicio;
import com.osmosyscol.datasuite.webdata.correval.servicios.OrdenesPagoServicio;

public class OrdenesPago implements Runnable {
	
	public void run(){
		
		try {
			Thread.sleep(180000);
		} catch (InterruptedException e1) {
			return;
		}
		
		try {
			
			SimpleLogger.setInfo("Iniciando servicio para carga automatica de ordenes de pago");
				
			List<FtpUsuario> listado = FtpUsuarioServicio.getInstance().obtenerFtpUsuarioActivos();
			
			if (listado != null && !listado.isEmpty()){
				for (FtpUsuario ftpUsuario : listado) {
					iniciarHilo(ftpUsuario);
				}
			}
			
		} catch (Throwable e) {
			SimpleLogger.setError("No se puede inicializar el servicio para carga automatica de ordenes de pago.", e);
		}
		
	}
	
	private void iniciarHilo(FtpUsuario ftpUsuario){
		OrdenesPagoServicio.getInstance().cambiarEstadoHilo(ftpUsuario.getId_ftp_usuario());
	}

}


