package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.FtpUsuarioArchivo;
import com.osmosyscol.datasuite.logica.servicios.FtpUsuarioArchivoServicio;
import com.osmosyscol.datasuite.servlet.ExcelServlet;
import com.osmosyscol.datasuite.webdata.correval.servicios.OrdenesPagoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.Estado;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.EstadoCargaServicio;

public class OrdenesPagoJsonServicio implements JsonService {
	
	private Session session;
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public void cambiarEstadoHilo(Integer id_ftp_usuario){
		OrdenesPagoServicio.getInstance().cambiarEstadoHilo(id_ftp_usuario);
	}
	
	public void reiniciarEstadoHilo(Integer id_ftp_usuario){
		OrdenesPagoServicio.getInstance().reiniciarEstadoHilo(id_ftp_usuario);
	}
	
	public List<FtpUsuarioArchivo> buscarArchivos(Integer id_usuario, Integer id_ftp_usuario_archivo, Integer id_formato, Integer id_carga,
                                                  String fecha_inicial, String fecha_final, String nombre, String estado, String estado_carga, 
                                                  Integer numero_pagina, Integer tamano_pagina){
		List<FtpUsuarioArchivo> listadoArchivos = FtpUsuarioArchivoServicio.getInstance().obtenerFtpUsuarioArchivoFiltros(id_usuario, id_ftp_usuario_archivo, id_formato, id_carga, fecha_inicial, fecha_final, nombre, estado, estado_carga, numero_pagina, tamano_pagina); 
		
		if (listadoArchivos != null && !listadoArchivos.isEmpty()){
			for (FtpUsuarioArchivo ftpUsuarioArchivo : listadoArchivos) {
				Carga carga = CargaServicio.getInstance().obtenerCarga(ftpUsuarioArchivo.getId_carga());
				if (carga != null){
					Integer total = (carga.getNumero_registros() != null) ? carga.getNumero_registros_bigdecimal().intValue() : null;
					ftpUsuarioArchivo.setTotal_registros(total);
					ftpUsuarioArchivo.setValor_total(carga.getValor_total_bigdecimal());
					ftpUsuarioArchivo.setEstado_carga(carga.getEstado());
				}
			}
		}
		return listadoArchivos;
		
	}
	
	public Integer contarBuscarArchivos(Integer id_usuario, Integer id_ftp_usuario_archivo, Integer id_formato, Integer id_carga,
                                        String fecha_inicial, String fecha_final, String nombre, String estado, String estado_carga){
		return FtpUsuarioArchivoServicio.getInstance().obtenerCantFtpUsuarioArchivoFiltros(id_usuario, id_ftp_usuario_archivo, id_formato, id_carga, fecha_inicial, fecha_final, nombre, estado, estado_carga);
	}
	
	public String generarArchivoExcel(Integer id_usuario, Integer id_ftp_usuario_archivo, Integer id_formato, Integer id_carga,
                                      String fecha_inicial, String fecha_final, String nombre, String estado, String estado_carga) {
		List	<FtpUsuarioArchivo> listadoArchivos = FtpUsuarioArchivoServicio.getInstance().obtenerFtpUsuarioArchivoFiltros(id_usuario, id_ftp_usuario_archivo, id_formato, id_carga, fecha_inicial, fecha_final, nombre, estado, estado_carga, null, null); 
		
		if (listadoArchivos != null && !listadoArchivos.isEmpty()){
			for (FtpUsuarioArchivo ftpUsuarioArchivo : listadoArchivos) {
				Carga carga = CargaServicio.getInstance().obtenerCarga(ftpUsuarioArchivo.getId_carga());
				if (carga != null){
					Integer total = (carga.getNumero_registros() != null) ? carga.getNumero_registros_bigdecimal().intValue() : null;
					ftpUsuarioArchivo.setTotal_registros(total);
					ftpUsuarioArchivo.setValor_total(carga.getValor_total_bigdecimal());
				}
			}
		}
		
		HSSFWorkbook wb = FtpUsuarioArchivoServicio.getInstance().generarArchivoExcel(listadoArchivos);
		
		return ExcelServlet.registrarWorkbook(wb);
	}
	
	public String generarArchivoExcelAdministrativo(Integer id_usuario, Integer id_ftp_usuario_archivo, Integer id_formato, Integer id_carga,
            String fecha_inicial, String fecha_final, String nombre, String estado, String estado_carga) {
		List<FtpUsuarioArchivo> listadoArchivos = FtpUsuarioArchivoServicio.getInstance().obtenerFtpUsuarioArchivoFiltros(id_usuario, id_ftp_usuario_archivo, id_formato, id_carga, fecha_inicial, fecha_final, nombre, estado, estado_carga, null, null);

		if (listadoArchivos != null && !listadoArchivos.isEmpty()) {
			for (FtpUsuarioArchivo ftpUsuarioArchivo : listadoArchivos) {
				Carga carga = CargaServicio.getInstance().obtenerCarga(ftpUsuarioArchivo.getId_carga());
				if (carga != null) {
					Integer total = (carga.getNumero_registros() != null) ? carga.getNumero_registros_bigdecimal().intValue() : null;
					ftpUsuarioArchivo.setTotal_registros(total);
					ftpUsuarioArchivo.setValor_total(carga.getValor_total_bigdecimal());
					List<Estado> estados = EstadoCargaServicio.getInstance().obtenerEstados();
					String estadoc = "";
					for (Estado estado2 : estados) {
						if(estado2.getEstado().equals(carga.getEstado())){
							estadoc = estado2.getNombre_interno();
						}
					}
					ftpUsuarioArchivo.setEstado_carga(estadoc);
				}
			}
		}

		HSSFWorkbook wb = FtpUsuarioArchivoServicio.getInstance().generarArchivoExcelAdministrativo(listadoArchivos);

		return ExcelServlet.registrarWorkbook(wb);
	}
	
	public List<FtpUsuarioArchivo> obtenerDuplicados(Integer id_ftp_usuario_archivo){
		
		FtpUsuarioArchivo ftpUsuarioArchivo = FtpUsuarioArchivoServicio.getInstance().obtenerFtpUsuarioArchivo(id_ftp_usuario_archivo);
		
		List<FtpUsuarioArchivo> listadoArchivos = FtpUsuarioArchivoServicio.getInstance().obtenerDuplicados(ftpUsuarioArchivo); 
		
		if (listadoArchivos != null && !listadoArchivos.isEmpty()){
			for (FtpUsuarioArchivo archivo : listadoArchivos) {
				Carga carga = CargaServicio.getInstance().obtenerCarga(archivo.getId_carga());
				if (carga != null){
					Integer total = (carga.getNumero_registros() != null) ? carga.getNumero_registros_bigdecimal().intValue() : null;
					archivo.setTotal_registros(total);
					archivo.setValor_total(carga.getValor_total_bigdecimal());
				}
			}
		}
		
		return listadoArchivos;
	}
	
}
