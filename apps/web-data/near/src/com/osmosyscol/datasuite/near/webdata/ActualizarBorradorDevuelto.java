package com.osmosyscol.datasuite.near.webdata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.RadicacionAutoOficioServicio;

public class ActualizarBorradorDevuelto implements AccionCarga { 
	
	@Override
	public SMessage ejecutar(Integer id_carga) {

		List<ArchivoAdjunto> borradores = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCargaTipo(id_carga, Constantes.TIPO_ARCHIVO_BORRADOR_FORMULARIO);
		RadicacionAutoOficio solicitud = RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficioBase(id_carga);
		
		if (solicitud != null && borradores != null && borradores.size() > 0) {
			
			Date fecha = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			int cantidad = borradores.size();
			ArchivoAdjunto borrador = borradores.get(cantidad-1);
			borrador.setId_tipo_archivo(Constantes.TIPO_ARCHIVO_BORRADOR_DEVUELTO);
			borrador.setNombre_archivo("Documento devuelto - " + Constantes.TIPOS_BORRADOR.get(solicitud.getTipoBorrador()) + " - Observaciones - " + formato.format(fecha));
			
			ArchivoAdjuntoServicio.getInstance().actualizarArchivoAdjunto(borrador);
			
			SimpleLogger.setInfo("Se realiza la actualización del borrador devuelto para la carga " + id_carga);
			
		} else {
			SimpleLogger.setInfo("No se encuentra el borrador para la carga " + id_carga);
		}
		
		return new SMessage(true, "");
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}

}
