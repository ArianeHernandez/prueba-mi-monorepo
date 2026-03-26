package com.osmosyscol.datasuite.webdata.validacion.accion;

import java.util.List;

import org.apache.cocoon.environment.Request;

import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.commons.validacion.ResultadoValidacion;
import com.osmosyscol.commons.validacion.ValidacionAccion;
import com.osmosyscol.datasuite.logica.dto.FtpUsuarioArchivo;
import com.osmosyscol.datasuite.logica.servicios.FtpUsuarioArchivoServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class Validacion_ordenes_pago_99_6_do implements ValidacionAccion {
	
	public ResultadoValidacion validar(Request request) {
		
		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();
		
		// --------------------------------------------------------------------------
		
		Integer id_negocio = 0;
		Object id_negocio_att = request.getSession().getAttribute("id_negocio");
		if (id_negocio_att != null && id_negocio_att instanceof Integer){
			id_negocio = ((Integer)id_negocio_att).intValue();
		}
		
		
		// --------------------------------------------------------------------------
		
		List<FtpUsuarioArchivo> listadoArchivos = FtpUsuarioArchivoServicio.getInstance().obtenerFtpUsuarioArchivoFiltros(null, 1, 10);
		
		if (listadoArchivos != null && !listadoArchivos.isEmpty()){
			for (FtpUsuarioArchivo ftpUsuarioArchivo : listadoArchivos) {
				Carga carga = CargaServicio.getInstance().obtenerCarga(ftpUsuarioArchivo.getId_carga());
				if (carga != null){
					ftpUsuarioArchivo.setTotal_registros(carga.getNumero_registros_bigdecimal().intValue());
					ftpUsuarioArchivo.setValor_total(carga.getValor_total_bigdecimal());
					ftpUsuarioArchivo.setEstado_carga(carga.getEstado());
				}
			}
		}
		request.setAttribute("listadoArchivos", listadoArchivos);
		
		Integer totalArchivos = FtpUsuarioArchivoServicio.getInstance().obtenerCantFtpUsuarioArchivoFiltros(null);
		request.setAttribute("totalArchivos", totalArchivos);
		
		List<Formato> listadoFormatos = FormatoServicio.getInstance().obtenerFormatosPorTipo(Formato.TIPO_FORMATO_ENTRADA);
		request.setAttribute("listadoFormatos", listadoFormatos);
		
		
		
		
		// --------------------------------------------------------------------------

		return resultadoValidacion;
		
	}

}
