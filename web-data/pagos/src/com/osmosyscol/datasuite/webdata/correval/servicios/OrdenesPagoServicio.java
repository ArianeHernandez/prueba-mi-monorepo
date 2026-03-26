package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.util.HashMap;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.correval.thread.HiloCargaFtpUsuario;
import com.osmosyscol.datasuite.logica.dto.FtpUsuario;
import com.osmosyscol.datasuite.logica.servicios.FtpUsuarioServicio;

public class OrdenesPagoServicio {
	
	private static OrdenesPagoServicio instance = new OrdenesPagoServicio();
	
	private static Map<Integer, HiloCargaFtpUsuario> mapaActivos = new HashMap<Integer, HiloCargaFtpUsuario>();
	
	private OrdenesPagoServicio(){
	}
	
	public static OrdenesPagoServicio getInstance(){
		return instance;
	}
	
	public void cambiarEstadoHilo(Integer id_ftp_usuario){
		FtpUsuario ftpUsuario = FtpUsuarioServicio.getInstance().obtenerFtpUsuario(id_ftp_usuario);
		if (ftpUsuario != null){
			if (ftpUsuario.getId_proceso() != null){
				iniciarHilo(ftpUsuario);
			}else {
				detenerHilo(ftpUsuario);
			}
		}
	}
	
	public void reiniciarEstadoHilo(Integer id_ftp_usuario){
		SimpleLogger.setInfo("Iniciando reiniciarEstadoHilo(id_ftp_usuario="+id_ftp_usuario+")");
		FtpUsuario ftpUsuario = FtpUsuarioServicio.getInstance().obtenerFtpUsuario(id_ftp_usuario);
		detenerHilo(ftpUsuario);
		iniciarHilo(ftpUsuario);
	}
	
	@SuppressWarnings("deprecation")
	public void detenerHilo(FtpUsuario ftpUsuario){
		if(ftpUsuario != null && ftpUsuario.getId_ftp_usuario() != null){
			HiloCargaFtpUsuario hilo = mapaActivos.get(ftpUsuario.getId_ftp_usuario());
			if (hilo != null){
				hilo.stop();
				mapaActivos.remove(ftpUsuario.getId_ftp_usuario());
			}
		}else{
			String mensaje = "No puede detener el hilo (no se encuentra el valor porque puede ser null). [ftpUsuario="+ftpUsuario;
			if(ftpUsuario != null){
				mensaje += ", ftpUsuario.getId_ftp_usuario()="+ftpUsuario.getId_ftp_usuario();
			}
			mensaje += "]";
			SimpleLogger.setError(mensaje);
		}
	}
	
	private void iniciarHilo(FtpUsuario ftpUsuario){
		if(ftpUsuario != null && ftpUsuario.getId_ftp_usuario() != null){
			HiloCargaFtpUsuario hilo = mapaActivos.get(ftpUsuario.getId_ftp_usuario());
			if (hilo == null){
				hilo = new HiloCargaFtpUsuario(ftpUsuario);
				mapaActivos.put(ftpUsuario.getId_ftp_usuario(), hilo);
				hilo.start();
			}
		}else{
			String mensaje = "No puede detener el hilo (no se encuentra el valor porque puede ser null). [ftpUsuario="+ftpUsuario;
			if(ftpUsuario != null){
				mensaje += ", ftpUsuario.getId_ftp_usuario()="+ftpUsuario.getId_ftp_usuario();
			}
			mensaje += "]";
			SimpleLogger.setError(mensaje);
		}
	}

}

