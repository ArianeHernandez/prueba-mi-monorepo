package com.osmosyscol.datasuite.webdata.persistencia.dao;

import java.util.Date;
import java.util.List;

import com.osmosyscol.datasuite.webdata.logica.dto.Nota;

public interface NotaDao {

	public List<Nota> obtenerNotasPorCarga(Integer id_carga, Boolean internas);

	public Boolean guardarNota(Integer id_nota, String nota, Date fecha, Integer id_persona, Integer id_carga, String estado, Integer id_revision, String nombre_instancia, Boolean interno);

	public Integer obtenerSiguiente();
	
	public Nota obtenerNota(Integer id_nota);
	

}
