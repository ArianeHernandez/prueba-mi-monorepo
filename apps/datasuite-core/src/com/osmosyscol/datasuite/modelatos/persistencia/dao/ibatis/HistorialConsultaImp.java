package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.config.Constantes;
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
import com.osmosyscol.datasuite.modelatos.persistencia.dao.FormatoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.HistorialConsultaDao;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class HistorialConsultaImp extends BaseSqlMapDao implements HistorialConsultaDao {

	public HistorialConsultaImp(DaoManager daoManager) {
		super(daoManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialConsulta> obtenerConsultas() {

		try{
			return queryForList("HistorialConsulta.obtenerConsultas");
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.obtenerConsultas", e);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> obtenerHistorial(String sql) {

		try{
			return queryForList("Carga.selectSQL", sql);
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.obtenerConsultas", e);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialConsultaConf> obtenerConsultasConf() {

		try{
			return queryForList("HistorialConsulta.obtenerConsultasConf");
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.obtenerConsultasConf", e);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialConsultaConf> obtenerConsultasConfVis() {

		try{
			return queryForList("HistorialConsulta.obtenerConsultasConfVis");
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.obtenerConsultasConfVis", e);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialConsultaConf> obtenerConsultasConfFiltro() {

		try{
			return queryForList("HistorialConsulta.obtenerConsultasConfFiltro");
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.obtenerConsultasConfFiltro", e);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialConsultaConf> obtenerConsultasConfExcel() {

		try{
			return queryForList("HistorialConsulta.obtenerConsultasConfExcel");
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.obtenerConsultasConfExcel", e);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialConsultaRol> obtenerConsultasRol() {

		try{
			return queryForList("HistorialConsulta.obtenerConsultasRol");
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.obtenerConsultasRol", e);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialConsultaDetalle> obtenerConsultasDetalle() {

		try{
			return queryForList("HistorialConsulta.obtenerConsultaDetalle", "V_SOLICITUD_UNION");
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.obtenerConsultaDetalle", e);
		}
		
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialConsulta> obtenerConsultasId(Integer id_historial) {
		try{
			return queryForList("HistorialConsulta.obtenerConsultasId", id_historial);
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.obtenerConsultasId", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialConsultaConf> obtenerConsultasConfId(Integer id_historial) {
		try{
			return queryForList("HistorialConsulta.obtenerConsultasConfId", id_historial);
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.obtenerConsultasConfId", e);
		}
		return null;
	}

	@Override
	public boolean guardarConsulta(HistorialConsulta historiaConsulta) {
		try{
			insert("HistorialConsulta.guardarEstructura", historiaConsulta);
			return true;
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.guardarEstructura", e);
		}
		return false;
	}
	
	@Override
	public boolean guardarConsultaConf(HistorialConsultaConf historiaConsultaConf) {
		try{
			insert("HistorialConsulta.guardarConsultasConf", historiaConsultaConf);
			return true;
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.guardarConsultasConf", e);
		}
		return false;
	}
	
	@Override
	public boolean guardarConsultasRol(HistorialConsultaRol historiaConsultaRol) {
		try{
			insert("HistorialConsulta.guardarConsultasRol", historiaConsultaRol);
			return true;
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.guardarConsultasRol", e);
		}
		return false;
	}

	@Override
	public boolean actualizarConsulta(HistorialConsulta historiaConsulta) {
		try{
			update("HistorialConsulta.actualizarConsulta", historiaConsulta);
			return true;
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.actualizarConsulta", e);
		}
		return false;
	}
	
	
	@Override
	public boolean actualizarConsultaConf(HistorialConsultaConf historiaConsultaConf) {
		try{
			update("HistorialConsulta.actualizarConsultaConf", historiaConsultaConf);
			return true;
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.actualizarConsultaConf", e);
		}
		return false;
	}
	
	
	@Override
	public boolean actualizarConsultaRol(HistorialConsultaRol historiaConsultaRol) {
		try{
			update("HistorialConsulta.actualizarConsultaRol", historiaConsultaRol);
			return true;
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.actualizarConsultaRol", e);
		}
		return false;
	}
	
	@Override
	public boolean eliminarConsultaRol(Integer idHistoriaConsultaRol) {
		try{
			delete("HistorialConsulta.eliminarConsultaRol", idHistoriaConsultaRol);
			return true;
		}catch(Exception e){
			SimpleLogger.setError("HistorialConsulta.eliminarConsultaRol", e);
		}
		return false;
	}


	// --------------------------------------------------------

	
}
