package com.osmosyscol.datasuite.mein.servicios.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.lang.StringUtils;
import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.bpm.dto.ApVistaIdentificacionTiposDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaMedioEnvioDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaSeguridadTipoDto;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.TipoDocumentoServicio;
import com.osmosyscol.datasuite.mein.dtos.APVistaCuaderno;
import com.osmosyscol.datasuite.mein.dtos.APVistaDependencias;
import com.osmosyscol.datasuite.mein.dtos.APVistaDocumentoTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaSerie;
import com.osmosyscol.datasuite.mein.dtos.APVistaSubserie;
import com.osmosyscol.datasuite.mein.dtos.APVistaTramite;
import com.osmosyscol.datasuite.mein.dtos.JSONResponse;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.mein.servicios.RadicacionAutoOficioServicio;
import com.osmosyscol.datasuite.mein.servicios.SignAppServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Ciudad;
import com.osmosyscol.datasuite.webdata.logica.dto.Pais;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import edu.emory.mathcs.backport.java.util.Collections;

public class RadicacionAutoOficioJsonServicio implements JsonService  {
	
	private Session session;

	private static Map<String, Boolean> MAP_TOKEN_VALIDOS = new HashMap<String, Boolean>();
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	public List<APVistaCuaderno> obtenerCuaderno(){
		return RadicacionAutoOficioServicio.getInstance().obtenerApVistaCuaderno();
	}
	
	public List<APVistaDependencias> obtenerDependencia(){
		return RadicacionAutoOficioServicio.getInstance().obtenerApVistaDependencia();
	}
	
	public List<APVistaDocumentoTipo> obtenerDocumentoTipo(){
		return RadicacionAutoOficioServicio.getInstance().obtenerApVistaDocumentoTipo();	
	}
	
	public List<ApVistaIdentificacionTiposDto> obtenerIdentificacionTipos(){
		return RadicacionAutoOficioServicio.getInstance().obtenerApVistaIdentificacionTipos();		
	}
	
	public List<ApVistaMedioEnvioDto> obtenerMedioEnvio(){
		return RadicacionAutoOficioServicio.getInstance().obtenerApVistaMedioEnvio();
	}
	public List<ApVistaSeguridadTipoDto> obtenerSeguridadTipo(){
		return RadicacionAutoOficioServicio.getInstance().obtenerApVistaSeguridadTipo();
	}
	
	public List<APVistaTramite> obtenerApVistaTramite(){
		return RadicacionAutoOficioServicio.getInstance().obtenerApVistaTramite();
	}
	
	public List<APVistaTramite> obtenerApVistaTramitePasante(){
		return RadicacionAutoOficioServicio.getInstance().obtenerApVistaTramitePasante();
	}
		
	public List<RadicacionAutoOficio> obtenerRadicacionAutoOficio(){
		return RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficio();
	}
	
	public RadicacionAutoOficio obtenerRadicacionAutoOficio (Integer id_carga) {
		return RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficio(id_carga);
	}
	
	public List<APVistaSerie> obtenerApVistaSeriePasante(){
		return RadicacionAutoOficioServicio.getInstance().obtenerApVistaSeriePasante();
	}
	
	public List<APVistaSubserie> obtenerApVistaSubseriePasante(){
		return RadicacionAutoOficioServicio.getInstance().obtenerApVistaSubseriePasante();
	}
	
	public Boolean guardarRadicacionAutoOficio(RadicacionAutoOficio radicacion){
		String identificacion = (String)session.getAttribute("identificacion");
		RadicacionAutoOficio solicitud = RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficioBase(radicacion.getIdcarga());
		radicacion.setFuncionarioId(identificacion);
		
		if (solicitud == null) {
			return RadicacionAutoOficioServicio.getInstance().guardarRadicacionAutoOficio(radicacion);			
		} else {
			return RadicacionAutoOficioServicio.getInstance().actualizarRadicacionAutoOficio(radicacion);
		}
		
	}
	
	public Boolean actualizarRadicacionAutoOficio(RadicacionAutoOficio radicacion) {
		return RadicacionAutoOficioServicio.getInstance().actualizarRadicacionAutoOficio(radicacion);
	}
	
	public String obtenerRadicacionFirmaSticker (RadicacionAutoOficio radicacion) {
		return RadicacionAutoOficioServicio.getInstance().obtenerRadicacionFirmaSticker(radicacion, session);
	}

	public String radicarDocumentoPostal (Integer id_carga) {
		return RadicacionAutoOficioServicio.getInstance().radicarDocumentoPostal(id_carga);
	}
	
}
