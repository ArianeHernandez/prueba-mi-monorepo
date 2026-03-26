package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.Date;
import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.InstanciaServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Nota;
import com.osmosyscol.datasuite.webdata.logica.servicios.NotaServicio;

public class NotasJsonServicio implements JsonService{

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public List<Nota> obtenerNotasPorCarga(Integer id_carga){
		
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		
		return NotaServicio.getInstance().obtenerNotasPorCarga(id_carga, id_usuario == null);
	}
	
	public Boolean guardarNota(Integer id_nota, String nota, Integer id_persona, Integer id_carga, String estado, Integer id_revision, Integer id_instancia, Boolean interno) {

		Integer id_usuario = (Integer) session.getAttribute("id_usuario");

		Date fecha = HorarioServicio.getInstance().obtenerFechaActual();

		Instancia instancia = InstanciaServicio.getInstance().obtenerInstancia(id_instancia);

		String nombre_instancia = instancia == null ? null : instancia.getNombre();

		return NotaServicio.getInstance().guardarNota(id_nota, nota, fecha, id_persona, id_carga, estado, id_revision, nombre_instancia, interno && id_usuario == null);

	}

	public Integer obtenerSiguiente() {
		return NotaServicio.getInstance().obtenerSiguiente();
	}

	public Nota obtenerNota(Integer id_nota) {
		return NotaServicio.getInstance().obtenerNota(id_nota);
	}

}
