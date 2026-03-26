package com.osmosyscol.datasuite.persistencia.dao;

import java.util.Date;
import java.util.List;

import com.osmosyscol.datasuite.logica.dto.CargaInstancia;




public interface CargaInstanciaDao {

	public Boolean insertarRelacionCargaInstancia(Integer id_carga,Integer id_instancia, Date fecha_llegada);
	
	public Boolean actualizarFechaSalidaRelacionCargaInstancia(Integer id_carga,Integer id_instancia, Integer id_administrativo);
	
	public Integer obtenerIDUsuarioDeRelacionCargaInstancia(Integer id_carga, Integer id_instancia);
	
	public Boolean existeRelacionCargaInstanciaSinFechaSalida(Integer id_carga, Integer id_instancia);
	
	public Boolean actualizarFechaEntradaRelacionCargaInstancia(Integer id_carga, Integer id_instancia, Date fecha_llegada);

	public List<CargaInstancia> obtenerHistorialCargaInstancia (Integer id_carga);
	
	public Boolean eliminarRelacionesCargaInstanciaSinFechaSalida(Integer id_carga);
	
	public Boolean inicializarFechaEntradaRelacionCargaInstancia(Integer id_carga, Date fecha_llegada);

	public CargaInstancia obtenerCargaInstanciaPorId(Integer id_carga_instancia);

	public List<CargaInstancia> obtenerCargaInstanciaActual (Integer id_carga);
	
	public List<CargaInstancia> obtenerCargasInstanciaActualPorInstanciaFecha (String nombre_instancia, Date fecha);
	
	
}
