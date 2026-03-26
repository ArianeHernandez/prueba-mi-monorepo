package com.osmosyscol.datasuite.logica.servicios.json;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;


public class UsuarioJSONServicio {

	public List<Usuario> buscarUsuariosActivos(String texto, String tipo_cliente) {
		return UsuarioServicio.getInstance().buscarUsuariosActivos(texto, tipo_cliente);
	}
	
	public List<Usuario> buscarUsuariosSinCredenciales(String texto) {
		return UsuarioServicio.getInstance().buscarUsuariosSinCredenciales(texto);
	}
	
	public Usuario obtenerUsuario(Integer id_usuario) {
		return UsuarioServicio.getInstance().obtenerUsuario(id_usuario);
	}
	
}
