package com.osmosyscol.datasuite.logica.servicios;

import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.FtpUsuario;
import com.osmosyscol.datasuite.logica.dto.FtpUsuarioArchivo;
import com.osmosyscol.datasuite.persistencia.dao.FtpUsuarioArchivoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;


public class FtpUsuarioArchivoServicio {
	
	private static FtpUsuarioArchivoServicio instance = new FtpUsuarioArchivoServicio();
	
	private FtpUsuarioArchivoServicio(){
	}
	
	public static FtpUsuarioArchivoServicio getInstance(){
		return instance;
	}
	
	public FtpUsuarioArchivo obtenerFtpUsuarioArchivo(Integer id_ftp_usuario_archivo){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioArchivoDao dao = (FtpUsuarioArchivoDao) daoManager.getDao(FtpUsuarioArchivoDao.class);
			
			return dao.obtenerFtpUsuarioArchivo(id_ftp_usuario_archivo);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioArchivoServicio.obtenerFtpUsuarioArchivo", e);
			return null;
		}
	}
	
	public List<FtpUsuarioArchivo> obtenerFtpUsuarioArchivoPorFtpUsuario(Integer id_ftp_usuario){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioArchivoDao dao = (FtpUsuarioArchivoDao) daoManager.getDao(FtpUsuarioArchivoDao.class);
			
			return dao.obtenerFtpUsuarioArchivoPorFtpUsuario(id_ftp_usuario);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioArchivoServicio.obtenerFtpUsuarioArchivoPorFtpUsuario", e);
			return null;
		}
	}
	
	public Boolean guardarFtpUsuarioArchivo(FtpUsuarioArchivo ftpUsuarioArchivo){
		try {
			
			FtpUsuarioArchivo ftpUsuarioArchivo_act = obtenerFtpUsuarioArchivo(ftpUsuarioArchivo.getId_ftp_usuario_archivo());
			
			if (ftpUsuarioArchivo_act != null){
				return actualizarFtpUsuarioArchivo(ftpUsuarioArchivo);
			}
			
			Integer id = obtenerSiguienteId();
			
			ftpUsuarioArchivo.setId_ftp_usuario_archivo(id);
			
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioArchivoDao dao = (FtpUsuarioArchivoDao) daoManager.getDao(FtpUsuarioArchivoDao.class);
			
			return dao.insertarFtpUsuarioArchivo(ftpUsuarioArchivo);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioArchivoServicio.obtenerFtpUsuarioArchivo", e);
			return false;
		}
	}
	
	public Boolean actualizarFtpUsuarioArchivo(FtpUsuarioArchivo ftpUsuarioArchivo){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioArchivoDao dao = (FtpUsuarioArchivoDao) daoManager.getDao(FtpUsuarioArchivoDao.class);
			
			return dao.actualizarFtpUsuarioArchivo(ftpUsuarioArchivo);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioArchivoServicio.actualizarFtpUsuarioArchivo", e);
			return false;
		}
	}
	
	public Integer obtenerSiguienteId(){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioArchivoDao dao = (FtpUsuarioArchivoDao) daoManager.getDao(FtpUsuarioArchivoDao.class);
			
			return dao.obtenerSiguienteId();
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioArchivoServicio.obtenerSiguienteId", e);
			return null;
		}
	}
	
	public Boolean actualizarEstado(Integer id_ftp_usuario_archivo, String estado){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioArchivoDao dao = (FtpUsuarioArchivoDao) daoManager.getDao(FtpUsuarioArchivoDao.class);
			
			return dao.actualizarEstado(id_ftp_usuario_archivo, estado);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioArchivoServicio.actualizarEstado", e);
			return false;
		}
	}
	
	public void validarDuplicado(FtpUsuarioArchivo ftpUsuarioArchivo){
		try {
				
			List<FtpUsuarioArchivo> listado = obtenerDuplicados(ftpUsuarioArchivo);
			
			if (listado != null){
				if (listado.isEmpty()){
					ftpUsuarioArchivo.setDuplicado("N");
				}else {
					ftpUsuarioArchivo.setDuplicado("S");
				}
			}
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioArchivoServicio.validarDuplicado", e);
		}
		
	}
	
	public List<FtpUsuarioArchivo> obtenerDuplicados(FtpUsuarioArchivo ftpUsuarioArchivo){
		try {
			
			FtpUsuario ftpUsuario = FtpUsuarioServicio.getInstance().obtenerFtpUsuario(ftpUsuarioArchivo.getId_ftp_usuario());
			
			if (ftpUsuario != null){
				
				Calendar c = Calendar.getInstance();
				c.setTime(ftpUsuarioArchivo.getFecha_cargue());
				
				//validamos la fecha en la cual un archivo se considera repetido
				c.add(Calendar.DATE, -ftpUsuario.getDias_validacion());
				
				DaoManager daoManager = DaoConfig.getDaoManager();
				
				FtpUsuarioArchivoDao dao = (FtpUsuarioArchivoDao) daoManager.getDao(FtpUsuarioArchivoDao.class);
				
				return (List<FtpUsuarioArchivo>) dao.obtenerDuplicados(ftpUsuarioArchivo.getMd5(), c.getTime());
				
			}

			return null;
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioArchivoServicio.obtenerDuplicados", e);
			return null;
		}
	}
	
	public List<FtpUsuarioArchivo> obtenerFtpUsuarioArchivoFiltros(Integer id_usuario, Integer numero_pagina, Integer tamano_pagina){
		return obtenerFtpUsuarioArchivoFiltros(id_usuario, null, null, null, null, null, null, null, null, numero_pagina, tamano_pagina);
	}
	
	public List<FtpUsuarioArchivo> obtenerFtpUsuarioArchivoFiltros(Integer id_usuario, Integer id_ftp_usuario_archivo, Integer id_formato, Integer id_carga,
                                                                   String fecha_inicial, String fecha_final, String nombre, String estado, String estado_carga,
                                                                   Integer numero_pagina, Integer tamano_pagina){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioArchivoDao dao = (FtpUsuarioArchivoDao) daoManager.getDao(FtpUsuarioArchivoDao.class);
			
			return dao.buscarArchivos(id_ftp_usuario_archivo, id_formato, id_carga, fecha_inicial, fecha_final, nombre, estado, id_usuario, estado_carga, numero_pagina, tamano_pagina);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioArchivoServicio.obtenerFtpUsuarioArchivoFiltros", e);
			return null;
		}
		
	}
	
	public Integer obtenerCantFtpUsuarioArchivoFiltros(Integer id_usuario){
		return obtenerCantFtpUsuarioArchivoFiltros(id_usuario, null, null, null, null, null, null, null, null);
	}
	
	public Integer obtenerCantFtpUsuarioArchivoFiltros(Integer id_usuario, Integer id_ftp_usuario_archivo, Integer id_formato, Integer id_carga, String fecha_inicial, 
                                                       String fecha_final, String nombre, String estado, String estado_carga){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			
			FtpUsuarioArchivoDao dao = (FtpUsuarioArchivoDao) daoManager.getDao(FtpUsuarioArchivoDao.class);
			
			return dao.totalArchivos(id_ftp_usuario_archivo, id_formato, id_carga, fecha_inicial, fecha_final, nombre, estado, id_usuario, estado_carga);
			
		}catch (Exception e){
			SimpleLogger.setError("Error en FtpUsuarioArchivoServicio.obtenerCantFtpUsuarioArchivoFiltros", e);
			return null;
		}
		
	}
	
	public HSSFWorkbook generarArchivoExcel(List<FtpUsuarioArchivo> listado){
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet hoja = wb.createSheet("Datos");
		
		HSSFRow fila = hoja.createRow(0);
		
		fila.createCell((short) 0).setCellValue(new HSSFRichTextString("ID"));
		fila.createCell((short) 1).setCellValue(new HSSFRichTextString("No. lote"));
		fila.createCell((short) 2).setCellValue(new HSSFRichTextString("Nombre del Archivo"));
		fila.createCell((short) 3).setCellValue(new HSSFRichTextString("Fecha"));
		fila.createCell((short) 4).setCellValue(new HSSFRichTextString("Formato"));
		fila.createCell((short) 5).setCellValue(new HSSFRichTextString("# Reg."));
		fila.createCell((short) 6).setCellValue(new HSSFRichTextString("Valor Total"));
		fila.createCell((short) 7).setCellValue(new HSSFRichTextString("Estado"));
		fila.createCell((short) 8).setCellValue(new HSSFRichTextString("Característica"));
		
		Integer i = 1;
		
		for (FtpUsuarioArchivo archivo : listado) {
			
			fila = hoja.createRow(i);
			
			String total_registros = (archivo.getTotal_registros() != null) ? archivo.getTotal_registros().toString() : "";
			String valor_total = (archivo.getValor_total() != null) ? archivo.getValor_total().toString() : "";
			
			String id_carga = (archivo.getId_carga() != null) ? archivo.getId_carga().toString() : "";
			
			fila.createCell((short) 0).setCellValue(new HSSFRichTextString(archivo.getId_ftp_usuario_archivo().toString()));
			fila.createCell((short) 1).setCellValue(new HSSFRichTextString(id_carga));
			fila.createCell((short) 2).setCellValue(new HSSFRichTextString(archivo.getNombre()));
			fila.createCell((short) 3).setCellValue(new HSSFRichTextString(archivo.getFecha_cargue_formato()));
			fila.createCell((short) 4).setCellValue(new HSSFRichTextString(archivo.getNombre_formato()));
			fila.createCell((short) 5).setCellValue(new HSSFRichTextString(total_registros));
			fila.createCell((short) 6).setCellValue(new HSSFRichTextString(valor_total));
			fila.createCell((short) 7).setCellValue(new HSSFRichTextString(archivo.getEstado_formato()));
			fila.createCell((short) 8).setCellValue(new HSSFRichTextString(archivo.getCaracteristica()));
			
			i++;
			
		}
		
		return wb;
		
	}
	
	public HSSFWorkbook generarArchivoExcelAdministrativo(List<FtpUsuarioArchivo> listado){
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet hoja = wb.createSheet("Datos");
		
		HSSFRow fila = hoja.createRow(0);
		
		fila.createCell((short) 0).setCellValue(new HSSFRichTextString("ID"));
		fila.createCell((short) 1).setCellValue(new HSSFRichTextString("No. lote"));
		fila.createCell((short) 2).setCellValue(new HSSFRichTextString("Nombre del Cliente"));
		fila.createCell((short) 3).setCellValue(new HSSFRichTextString("Nombre del Archivo"));
		fila.createCell((short) 4).setCellValue(new HSSFRichTextString("Fecha"));
		fila.createCell((short) 5).setCellValue(new HSSFRichTextString("Formato"));
		fila.createCell((short) 6).setCellValue(new HSSFRichTextString("# Reg."));
		fila.createCell((short) 7).setCellValue(new HSSFRichTextString("Valor Total"));
		fila.createCell((short) 8).setCellValue(new HSSFRichTextString("Estado"));
		fila.createCell((short) 9).setCellValue(new HSSFRichTextString("Característica"));
		fila.createCell((short) 10).setCellValue(new HSSFRichTextString("Estado Lote"));
		
		Integer i = 1;
		
		for (FtpUsuarioArchivo archivo : listado) {
			
			fila = hoja.createRow(i);
			
			String total_registros = (archivo.getTotal_registros() != null) ? archivo.getTotal_registros().toString() : "";
			String valor_total = (archivo.getValor_total() != null) ? archivo.getValor_total().toString() : "";
			
			String id_carga = (archivo.getId_carga() != null) ? archivo.getId_carga().toString() : "";
			
			fila.createCell((short) 0).setCellValue(new HSSFRichTextString(archivo.getId_ftp_usuario_archivo().toString()));
			fila.createCell((short) 1).setCellValue(new HSSFRichTextString(id_carga));
			fila.createCell((short) 2).setCellValue(new HSSFRichTextString(archivo.getNombre_usuario()));
			fila.createCell((short) 3).setCellValue(new HSSFRichTextString(archivo.getNombre()));
			fila.createCell((short) 4).setCellValue(new HSSFRichTextString(archivo.getFecha_cargue_formato()));
			fila.createCell((short) 5).setCellValue(new HSSFRichTextString(archivo.getNombre_formato()));
			fila.createCell((short) 6).setCellValue(new HSSFRichTextString(total_registros));
			fila.createCell((short) 7).setCellValue(new HSSFRichTextString(valor_total));
			fila.createCell((short) 8).setCellValue(new HSSFRichTextString(archivo.getEstado_formato()));
			fila.createCell((short) 9).setCellValue(new HSSFRichTextString(archivo.getCaracteristica()));
			fila.createCell((short) 10).setCellValue(new HSSFRichTextString(archivo.getEstado_carga()));
			
			i++;
			
		}
		
		return wb;
		
	}


}
