package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.Atributo;
import com.osmosyscol.datasuite.logica.dto.RestriccionAdministrativo;
import com.osmosyscol.datasuite.logica.dto.ValorAtributo;


public interface AdministrativoDao {

	public List<RestriccionAdministrativo> obtenerRestriccionesAdministrativos();
	
	public Integer obtenerIdSiguiente();
	
	public Boolean crearRestriccionAdministrativo(Integer id_rol, Integer id_administrativo, Integer id_usuario, Integer id_negocio,Integer id_restriccion_administrativo);
	
	public RestriccionAdministrativo obtenerRestriccionAdministrativo(Integer id_restriccion_administrativo);

	public Boolean eliminarRestriccionAdministrativo(Integer id_restriccion_administrativo);
	
	public Boolean actualizarRol(Integer id_restriccion_administrativo, Integer id_rol);
	
	public List<Administrativo> obtenerAdministrativosPorRol(Integer id_rol); 
	
	public Boolean actualizarAdministrativo(Integer id_restriccion_administrativo, Integer id_administrativo);
	
	public Boolean actualizarCliente(Integer id_restriccion_administrativo, Integer id_usuario);
	
	public Boolean actualizarNegocio(Integer id_restriccion_administrativo, Integer id_negocio);

	public Integer obtenerIdAdministrativoPersona(Integer idPersona, Integer id_usuario);
	
	public List<RestriccionAdministrativo> obtenerRestriccionesPorAdministrativo (Integer id_administrativo);
	
	public List<Administrativo> obtenerAdministrativosActivos();
	
	public List<Administrativo> obtenerAdministrativosParaNotificacion(Integer id_accion);
	
	public Administrativo obtenerAdministrativoPorID(Integer id_administrativo);
	
	public List<Administrativo> obtenerAdminsHijoPorInstanciaParaAdminPadre(Integer id_administrativo_padre, Integer id_instancia);

	public Integer guardarAdministrativo(Administrativo administrativo);
	
	public List<Atributo> obtenerAtributos(Integer id_usuario);

	public List<ValorAtributo> obtenerValoresAtributo(Integer id_atributo, Integer id_administrativo);
	
	public List<Administrativo> obtenerAdministrativosPor(Integer id_usuario);

	public Administrativo obtenerAdministrativoPorCodigo(String codigo);
	
}
