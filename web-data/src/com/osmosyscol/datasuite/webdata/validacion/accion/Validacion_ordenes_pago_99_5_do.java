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

public class Validacion_ordenes_pago_99_5_do implements ValidacionAccion {
	
	public ResultadoValidacion validar(Request request) {
		
		ResultadoValidacion resultadoValidacion = new ResultadoValidacion();

		// --------------------------------------------------------------------------

		Integer id_usuario = 0;
		Object id_usuario_att = request.getSession().getAttribute("id_usuario");
		if (id_usuario_att != null && id_usuario_att instanceof Integer){
			id_usuario = ((Integer)id_usuario_att).intValue();
		}
		
		if (id_usuario == 0){
			resultadoValidacion.setPermitido(false);
			resultadoValidacion.setSiguientePagina("/inicio/0.do");
			return resultadoValidacion;
		}
		
		// --------------------------------------------------------------------------
		
		request.setAttribute("id_usuario", id_usuario);
		
		List<FtpUsuarioArchivo> listadoArchivos = FtpUsuarioArchivoServicio.getInstance().obtenerFtpUsuarioArchivoFiltros(id_usuario, 1, 10);
		
		if (listadoArchivos != null && !listadoArchivos.isEmpty()){
			for (FtpUsuarioArchivo ftpUsuarioArchivo : listadoArchivos) {
				Carga carga = CargaServicio.getInstance().obtenerCarga(ftpUsuarioArchivo.getId_carga());
				if (carga != null){
					ftpUsuarioArchivo.setTotal_registros(carga.getNumero_registros_bigdecimal().intValue());
					ftpUsuarioArchivo.setValor_total(carga.getValor_total_bigdecimal());
				}
			}
		}
		request.setAttribute("listadoArchivos", listadoArchivos);
		
		Integer totalArchivos = FtpUsuarioArchivoServicio.getInstance().obtenerCantFtpUsuarioArchivoFiltros(id_usuario);
		request.setAttribute("totalArchivos", totalArchivos);
		
		List<Formato> listadoFormatos = FormatoServicio.getInstance().obtenerFormatosPorCliente(id_usuario);
		request.setAttribute("listadoFormatos", listadoFormatos);
		
		// --------------------------------------------------------------------------

		return resultadoValidacion;
		
	}

}
