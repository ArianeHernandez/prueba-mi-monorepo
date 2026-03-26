package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Ipprivada;
import com.osmosyscol.datasuite.logica.dto.Ippublica;

public interface IpDao {

	public List<Ippublica> obtenerIPPublicas(Integer id_usuario);
	
	public List<Ippublica> obtenerIPPublicasPorEstado(Integer id_usuario, String estado);

	public List<Ipprivada> obtenerIPPrivadas(Integer id_ippublica);

	public Integer guardarIPPrivada(Ipprivada ipprivada);

	public Integer guardarIPPublica(Ippublica ippublica);

	public Boolean eliminarIPPublica(Integer idIppublica);

	public Boolean eliminarIPPrivada(Integer idIpprivada);
	
	public Integer obtenerSiguienteIdIPPrivada();
	
	public Integer obtenerSiguienteIdIPPublica();
	
	public List<Ipprivada> obtenerIPPrivPorUsuario(Integer id_usuario);
}
