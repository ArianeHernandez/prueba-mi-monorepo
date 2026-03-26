package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Liberador;
import com.osmosyscol.datasuite.logica.dto.LiberadorTipoProceso;


public interface LiberadorDao {

	public void asociarLiberador(Integer id_proceso, Integer id_liberador);

	public void desasociarLiberador(Integer id_liberador);
	
	public void desasociarLiberadorPorProceso(Integer id_liberador,Integer id_proceso);

	public List<Liberador> obtenerLiberadores(Integer id_usuario, Integer id_tipo_proceso);
	
	public List<Liberador> obtenerLiberadoresPorProceso(Integer id_proceso);

	public Liberador obtenerLiberadorPorIdentificacion(Integer id_usuario, String tipo_persona, String identificacion, Integer tipoDocumento);

	public Liberador obtenerLiberadorPorId(Integer id_liberador);
	
	public Liberador obtenerLiberadorPorPersonaUsuario(Integer id_persona, Integer id_usuario);
	
	public Boolean actualizarLiberadorTipoProceso(LiberadorTipoProceso liberadorTipoProceso);

	public LiberadorTipoProceso obtenerLiberadorTipoProceso(Integer id_liberador, Integer id_tipo_proceso);

}
