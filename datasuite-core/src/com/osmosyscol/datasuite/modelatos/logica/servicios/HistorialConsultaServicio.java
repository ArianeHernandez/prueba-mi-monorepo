package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cocoon.environment.Session;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Horario;
import com.osmosyscol.datasuite.logica.dto.ListaDinamicaCampo;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.logica.servicios.FormatoOperacionServicio;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.logica.servicios.ListaDinamicaCampoServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Destino;
import com.osmosyscol.datasuite.modelatos.logica.dto.ExcepcionFormatoCliente;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoEstilo;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoNegocio;
import com.osmosyscol.datasuite.modelatos.logica.dto.GrupoFormato;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsulta;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsultaConf;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsultaDetalle;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsultaRol;
import com.osmosyscol.datasuite.modelatos.logica.dto.Operacion;
import com.osmosyscol.datasuite.modelatos.logica.dto.ParametroCarga;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.CampoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.DestinoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.FormatoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.HistorialConsultaDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ParametroCargaDao;
import com.osmosyscol.datasuite.persistencia.dao.HorarioDao;
import com.osmosyscol.datasuite.persistencia.dao.ProcesoDao;
import com.osmosyscol.datasuite.persistencia.dao.RevisionDao;
import com.osmosyscol.datasuite.persistencia.dao.RolDao;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class HistorialConsultaServicio {

	private static HistorialConsultaServicio historialConsultaServicio;

	private HistorialConsultaServicio() {
	}

	public static HistorialConsultaServicio getInstance() {
		if (historialConsultaServicio == null) {
			historialConsultaServicio = new HistorialConsultaServicio();
		}
		return historialConsultaServicio;
	}

	// ------------------------------
	
	public List<HistorialConsulta> obtenerConsultas(){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.obtenerConsultas();
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.obtenerConsultas", e);
		}
		return null;
	}
	
	public List<HistorialConsultaRol> obtenerConsultasRol(){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.obtenerConsultasRol();
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.obtenerConsultasRol", e);
		}
		return null;
	}
	
	public List<HistorialConsultaDetalle> obtenerConsultasDetalle(){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager("CREADATOS");
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.obtenerConsultasDetalle();
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.obtenerConsultasDetalle", e);
		}
		return null;
	}
	
	public List<HistorialConsultaConf> obtenerConsultasConf(){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.obtenerConsultasConf();
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.obtenerConsultasConf", e);
		}
		return null;
	}
	
	public List<HistorialConsultaConf> obtenerConsultasConfVis(){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.obtenerConsultasConfVis();
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.obtenerConsultasConfVis", e);
		}
		return null;
	}
	
	public List<HistorialConsultaConf> obtenerConsultasConfFiltro(){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.obtenerConsultasConfFiltro();
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.obtenerConsultasConfFiltro", e);
		}
		return null;
	}
	
	public List<HistorialConsultaConf> obtenerConsultasConfExcel(){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.obtenerConsultasConfExcel();
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.obtenerConsultasConfExcel", e);
		}
		return null;
	}
	
	public List<HistorialConsulta> obtenerConsultasId(Integer id_consulta){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.obtenerConsultasId(id_consulta);
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.obtenerConsultasId", e);
		}
		return null;
	}
	
	public List<HistorialConsultaConf> obtenerConsultasConfId(Integer id_consulta){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.obtenerConsultasConfId(id_consulta);
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.obtenerConsultasConfId", e);
		}
		return null;
	}
	
	public boolean actualizarConsulta(HistorialConsulta historialConsulta){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.actualizarConsulta(historialConsulta);
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.actualizarConsulta", e);
		}
		return false;
	}
	
	public boolean guardarConsultaConf(HistorialConsultaConf historialConsultaConf){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.guardarConsultaConf(historialConsultaConf);
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.actualizarConsultaConf", e);
		}
		return false;
	}
	
	public boolean guardarConsultasRol(HistorialConsultaRol historialConsultaRol){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.guardarConsultasRol(historialConsultaRol);
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.actualizarConsultaRol", e);
		}
		return false;
	}
	
	
	public boolean actualizarConsultaConf(HistorialConsultaConf historialConsultaConf){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.actualizarConsultaConf(historialConsultaConf);
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.actualizarConsultaConf", e);
		}
		return false;
	}
	
	public boolean actualizarConsultaRol(HistorialConsultaRol historialConsultaRol){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.actualizarConsultaRol(historialConsultaRol);
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.actualizarConsultaRol", e);
		}
		return false;
	}
	
	public boolean eliminarConsultaRol(Integer idHistorialConsultaRol){
		try{
			DaoManager daoManager = DaoConfig.getDaoManager();
			HistorialConsultaDao historialConsultaDao = (HistorialConsultaDao) daoManager.getDao(HistorialConsultaDao.class);
			return historialConsultaDao.eliminarConsultaRol(idHistorialConsultaRol);
			
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsultaServicio.eliminarConsultaRol", e);
		}
		return false;
	}
	
	
	
	public List<Rol> obtenerRolesActivos(){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
			return rolDao.obtenerRolesActivos();
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}
	}
//	
//	public 
//	
//	try {
//		String sql = "SELECT  *  FROM dsv_carga where ESTADO = 'B' AND ID_CARGA = ( select max(ID_CARGA) from dsv_carga ) AND ID_USUARIO="
//				+ id_usuario;
//
//		Integer id_carga = DS_SqlUtils.queryForObject(Integer.class, sql);
//
//		String sql_solicitud = "SELECT * "
//				+ "FROM $SOLICITUD NEAR SOCIEDAD$ WHERE IDCARGA="
//				+ id_carga;
//
//		return DS_SqlUtils.queryForObject(SolicitudNearSociedad.class,
//				sql_solicitud);
//	} catch (Exception e) {
//		return null;
//	}
	
}
