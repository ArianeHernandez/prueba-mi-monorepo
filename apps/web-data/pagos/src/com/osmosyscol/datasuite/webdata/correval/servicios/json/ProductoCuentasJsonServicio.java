package com.osmosyscol.datasuite.webdata.correval.servicios.json;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.TipoDocumentoServicio;
import com.osmosyscol.datasuite.webdata.correval.productocuenta.dto.ProductoEncargos;
import com.osmosyscol.datasuite.webdata.correval.servicios.ProductoCuentasServicio;

public class ProductoCuentasJsonServicio implements JsonService {

	@SuppressWarnings("unused")
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	// --------------------------------------------------------

	public List<TipoDocumento> listarTiposDocumento() {
		return TipoDocumentoServicio.getInstance().listarTiposDocumento();
	}

	// --------------------------------------------------------

	public Persona obtenerPersona(String numeroDocumento, Integer tipoDocumento){
		return PersonaServicio.getInstance().obtenerPersonaPorIdentificacion(numeroDocumento, tipoDocumento);
	}
	
	public Persona obtenerPersonaCliente(){
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		String numeroDocumento = usuario.getIdentificacion();
		Integer tipoDocumento = Integer.parseInt(usuario.getTipo_documento());
		return PersonaServicio.getInstance().obtenerPersonaPorIdentificacion(numeroDocumento, tipoDocumento);
	}
	
	public List<ProductoEncargos> obtenerProductosCuentas(String numeroDocumento, Integer tipoDocumento){
		return ProductoCuentasServicio.getInstance().obtenerProductosCuentas(numeroDocumento, tipoDocumento);
	}
	
	public List<ProductoEncargos> obtenerProductosCuentasCliente(){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		String numeroDocumento = usuario.getIdentificacion();
		Integer tipoDocumento = Integer.parseInt(usuario.getTipo_documento());
		return ProductoCuentasServicio.getInstance().obtenerProductosCuentas(numeroDocumento, tipoDocumento);
	}
	
}
