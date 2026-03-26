package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsulta;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsultaConf;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsultaDetalle;
import com.osmosyscol.datasuite.modelatos.logica.dto.HistorialConsultaRol;

public interface HistorialConsultaDao {

	public List<HistorialConsulta> obtenerConsultas();
		
	public List<Map<String, Object>> obtenerHistorial(String sql);
	
	public List<HistorialConsultaConf> obtenerConsultasConf();
	
	public List<HistorialConsultaRol> obtenerConsultasRol();
	
	public List<HistorialConsultaConf> obtenerConsultasConfVis();
	
	public List<HistorialConsultaConf> obtenerConsultasConfFiltro();
	
	public List<HistorialConsultaConf> obtenerConsultasConfExcel();
	
	public List<HistorialConsultaDetalle> obtenerConsultasDetalle();
	
	public List<HistorialConsulta> obtenerConsultasId(Integer id_historial);
	
	public List<HistorialConsultaConf> obtenerConsultasConfId(Integer id_historial);
	
	public boolean guardarConsulta(HistorialConsulta historia_consulta);
	
	public boolean guardarConsultaConf(HistorialConsultaConf historia_consulta_conf);
	
	public boolean guardarConsultasRol(HistorialConsultaRol historia_consulta_rol);
	
	public boolean actualizarConsulta(HistorialConsulta historia_consulta);
	
	public boolean actualizarConsultaConf(HistorialConsultaConf historia_consulta_conf);
	
	public boolean actualizarConsultaRol(HistorialConsultaRol historia_consulta_rol);
	
	public boolean eliminarConsultaRol(Integer idHistoriaConsultaRol);
	
}


