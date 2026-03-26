package com.osmosyscol.datasuite.mein.servicios.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;
import org.junit.Assert;

import co.htsoft.commons.lang.P;

import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.mein.dtos.DepartamentoCiudad;
import com.osmosyscol.datasuite.mein.dtos.DocumentoAdjunto;
import com.osmosyscol.datasuite.mein.dtos.Sociedad;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedadResponseDto;
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.mein.servicios.rest.client.PasanteAppRestClient;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
 
public class SolicitudNearSociedadJsonServicio implements JsonService  {
	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	public Mensaje crearSolicitud(SolicitudNearSociedad solicitud) {
		Integer id_persona = (Integer) session.getAttribute("id_persona");
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		
		Integer id_negocio = null;
		try {
			id_negocio = (Integer) (session.getAttribute("var.id_negocio_carga"));
			if (id_negocio == null) {
				id_negocio = (Integer) (session.getAttribute("id_negocio"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		P.println("id_negocio :" + id_negocio);
		
		String string_proceso = (String) (session.getAttribute("id_proceso"));
		Integer id_proceso = null;
		if (string_proceso != null) {
			id_proceso = new Integer(string_proceso);
		}
		return SolicitudNearSociedadServicio.getInstance().crearSolicitud(solicitud, id_persona, id_usuario, id_proceso);
	}
	
	public Mensaje crearSolicitud(SolicitudNearSociedad solicitud, Integer idCarga) {
		Integer id_persona = (Integer) session.getAttribute("id_persona");
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		
		Integer id_negocio = null;
		try {
			id_negocio = (Integer) (session.getAttribute("var.id_negocio_carga"));
			if (id_negocio == null) {
				id_negocio = (Integer) (session.getAttribute("id_negocio"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		P.println("id_negocio :" + id_negocio);
		
		String string_proceso = (String) (session.getAttribute("id_proceso"));
		Integer id_proceso = null;
		if (string_proceso != null) {
			id_proceso = new Integer(string_proceso);
		}
		return SolicitudNearSociedadServicio.getInstance().crearSolicitud(solicitud, id_persona, id_usuario, id_proceso, idCarga);
	}
	
	public Mensaje guardarSolicitud(SolicitudNearSociedad solicitud) {
		Integer id_persona = (Integer) session.getAttribute("id_persona");
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		
		Integer id_negocio = null;
		try {
			id_negocio = (Integer) (session.getAttribute("var.id_negocio_carga"));
			if (id_negocio == null) {
				id_negocio = (Integer) (session.getAttribute("id_negocio"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		P.println("id_negocio :" + id_negocio);
		
		String string_proceso = (String) (session.getAttribute("id_proceso"));
		Integer id_proceso = null;
		if (string_proceso != null) {
			id_proceso = new Integer(string_proceso);
		}
		return SolicitudNearSociedadServicio.getInstance().guardarSolicitud(solicitud, id_persona, id_usuario, id_proceso);
	}
	
	public Mensaje guardarSolicitud(SolicitudNearSociedad solicitud, Integer idCarga) {
		Integer id_persona = (Integer) session.getAttribute("id_persona");
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		
		Integer id_negocio = null;
		try {
			id_negocio = (Integer) (session.getAttribute("var.id_negocio_carga"));
			if (id_negocio == null) {
				id_negocio = (Integer) (session.getAttribute("id_negocio"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		P.println("id_negocio :" + id_negocio);
		
		String string_proceso = (String) (session.getAttribute("id_proceso"));
		Integer id_proceso = null;
		if (string_proceso != null) {
			id_proceso = new Integer(string_proceso);
		}
		return SolicitudNearSociedadServicio.getInstance().guardarSolicitud(solicitud, id_persona, id_usuario, id_proceso, idCarga);
	}
	
//	public Mensaje crearPdf(Integer id_carga) {
//		return SolicitudNearSociedadServicio.getInstance().crearPdf(id_carga);
//	}
	
	public Mensaje enviarInfoPasante(Integer id_carga) {
		//consumir servicio pasante
		return SolicitudNearSociedadServicio.getInstance().EnviarInfoPasante(id_carga);
	}
	
	public String obtenerInfoPasante(Integer id_carga){
		return SolicitudNearSociedadServicio.getInstance().obtenerInfoPasante(id_carga);
	}
	
	public Mensaje ConsultaMiPasante(Integer id_carga){
		return SolicitudNearSociedadServicio.getInstance().ConsultaMiPasante(id_carga);
	}
	
	public String comunicacionPasante(String url, String method, String body){
		return SolicitudNearSociedadServicio.getInstance().comunicacionPasante(url, method, body);
	}
	
	public Mensaje borrarAdjuntoPasante(Integer id_archivo){
		return SolicitudNearSociedadServicio.getInstance().ConsultaMiPasante(id_archivo);
	}
	
	public Persona obtenerInfoCliente(){
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(id_usuario);
		Persona persona = PersonaServicio.getInstance().obtenerPersona(usuario.getId_persona());

		return persona;
	}
	
	public SolicitudEnrolamiento obtenerInfoClienteEnrolamiento(){
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(id_usuario);
		Persona persona = PersonaServicio.getInstance().obtenerPersona(usuario.getId_persona());
		SolicitudEnrolamiento solicitud = SolicitudEnrolamientoServicio.getInstance().SolicitudEnrolamientoIdentificacion(persona.getIdentificacion());
		return solicitud;
	}
	
	public List<ValorLista> obtenerListaSiNO(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Seleccion Si/No");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public List<ValorLista> obtenerListaSituacionPresentada(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Situacion Presentada");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public List<ValorLista> obtenerListaAdelAcreedores(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Adel Acreedores");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public List<ValorLista> obtenerListaNaturalezaSociedad(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Naturaleza Sociedad");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public List<ValorLista> obtenerListaContador(){
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		return SolicitudNearSociedadServicio.getInstance().obtenerRevisoresPorUsuario(id_usuario, Constantes.ID_TIPO_REVISOR_CONTADOR);
	}
	
	public List<ValorLista> obtenerListaRepresentanteLegal(){
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		return SolicitudNearSociedadServicio.getInstance().obtenerAdministradoresClientePorUsuario(id_usuario);
	}
	
	public List<ValorLista> obtenerListaRevisor(){
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		return SolicitudNearSociedadServicio.getInstance().obtenerRevisoresPorUsuario(id_usuario, Constantes.ID_TIPO_REVISOR_REVISOR_F);
	}
	
	public List<ValorLista> obtenerListaApoderado(){
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		return SolicitudNearSociedadServicio.getInstance().obtenerPreparadoresNoAdminClientePorUsuario(id_usuario);
	}
	
	public List<ValorLista> obtenerGruposNIF(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Grupo NIIF");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public List<ValorLista> obtenerMetodosInversion(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Metodo Inversion");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public List<ValorLista> obtenerCodigosCIIU(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Codigo CIIU Concatenado");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public List<ValorLista> obtenerListaMacrosector(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Macrosector");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public List<String> obtenerDepartamentos(){
		return SolicitudNearSociedadServicio.getInstance().obtenerDepartamentos();
	}
	
	public List<ValorLista> obtenerCiudadesPorDepartamento(String departamento){
		return SolicitudNearSociedadServicio.getInstance().obtenerCiudadesPorDepartamento(departamento);
	}
	
	public Integer obtenerIdDepartamentoCiudad(String departamento, String ciudad){
		return SolicitudNearSociedadServicio.getInstance().obtenerIdDepartamentoCiudad(departamento, ciudad);
	}
	
	public DepartamentoCiudad obtenerDepartamentoCiudad(Integer idDeptoCiudad){
		return SolicitudNearSociedadServicio.getInstance().obtenerDepartamentoCiudad(idDeptoCiudad);
	}
	
	public Sociedad obtenerSociedad(){
		return SolicitudNearSociedadServicio.getInstance().obtenerSociedad(obtenerInfoCliente().getIdentificacion());
	}
	
	public List<ValorLista> obtenerTamanosEmpresa(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Tamano Empresa");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public List<ValorLista> obtenerCategoriasEmpresa(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Categoria");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public List<ValorLista> obtenerTiposSolicitud(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Tipo de Solicitud");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public List<ValorLista> obtenerTipoSolicitud(Integer id_carga){
		return SolicitudNearSociedadServicio.getInstance().obtenerTipoSolicitudPorCarga(id_carga);
	}
	
	public List<ValorLista> obtenerTipoFormulario(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Tipo Solicitante");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public String obtenerNombreTipoSolicitante(Integer solicitante){
		return SolicitudNearSociedadServicio.getInstance().obtenerNombreTipoSolicitante(solicitante);
	}
	
	public String obtenerNombreTipoDocumento(Integer idDocumento){
		return SolicitudNearSociedadServicio.getInstance().obtenerNombreTipoDocumento(idDocumento);
	}
	
	public List<ValorLista> obtenerNormasAplicables(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Norma Aplicable");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}
	
	public SolicitudNearSociedad obtenerSolicitudNearSociedadBorrador(Integer id_usuario){
		return SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadBorrador(id_usuario);
	}
	
	public SolicitudNearSociedad obtenerSolicitudNearSociedadPorCarga(Integer id_carga){
		return SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadPorCarga(id_carga);
	}
	
	public List<ValorLista> obtenerPaises(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Pais");
		if(lista != null){
			return ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		}else{
			return null;
		}
	}

	public List<DocumentoAdjunto> obtenerDocumentosPorTipoArchivo (Integer id_carga, Integer id_tipo_archivo) {
		return SolicitudNearSociedadServicio.getInstance().obtenerDocumentosPorTipoArchivo(id_carga, id_tipo_archivo);
	}
	
	public List<DocumentoAdjunto> obtenerDocumentosPorTiposArchivo (Integer id_carga, Integer[] tipos_archivo) {
		
		return SolicitudNearSociedadServicio.getInstance().obtenerDocumentosPorTiposArchivo(id_carga, tipos_archivo);
	}
	
}
