package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import co.htsoft.commons.lang.StringUtils;
import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.TipoArchivoServicio;
import com.osmosyscol.datasuite.logica.servicios.TipoDocumentoServicio;
import com.osmosyscol.datasuite.mein.dtos.ObjGenerico;
import com.osmosyscol.datasuite.mein.servicios.SignAppServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.utils.dummys.DummySession;
import com.osmosyscol.datasuite.webdata.logica.dto.Ciudad;
import com.osmosyscol.datasuite.webdata.logica.dto.Pais;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

import edu.emory.mathcs.backport.java.util.Collections;

public class SolicitudEnrolamientoJsonServicio implements JsonService  {
	
	private Session session;

	private static Map<String, Boolean> MAP_TOKEN_VALIDOS = new HashMap<String, Boolean>();
	
	public void setSession(Session session) {
		this.session = session;
	}

		
	public List<TipoDocumento> obtenerTiposDocumento(){
		List<TipoDocumento> tiposDocumento = TipoDocumentoServicio.getInstance().listarTiposDocumento();
		return tiposDocumento;
	}
	public List<ObjGenerico> obtenerTiposSolicitante(){
		List<ObjGenerico> listaSolicitantes = SolicitudEnrolamientoServicio.getInstance().obtenerTiposSolicitante();
		return listaSolicitantes;
	}
	
	public List<String> obtenerExtensionesPorTipoArchivo(Integer id_tipo_archivo) {
		return SolicitudEnrolamientoServicio.getInstance().obtenerExtensionesPorTipoArchivo(id_tipo_archivo);
	}
	
	public List<ValorLista> obtenerRelacionesSociedad(){
		ListaValores lista = ListaValoresServicio.getInstance().obtenerListaValoresPorNombre("Relacion Sociedad");
		ListaValoresServicio.getInstance().obtenerValoresLV(lista.getId_lista_valores());
		
		// TODO: SSOC Integrar con lista de valores
		
		ValorLista v1 = new ValorLista();
		v1.setId("1");
		v1.setNombre("Representante Legal");
		
		ValorLista v2 = new ValorLista();
		v2.setId("2");
		v2.setNombre("Contador");
		
		List<ValorLista> valores = new ArrayList<ValorLista>();
		
		valores.add(v1);
		valores.add(v2);
		
		return valores;
	}
	
	public Integer crearCarga(Boolean registroSignapp) {
		return SolicitudEnrolamientoServicio.getInstance().crearCarga(registroSignapp);
	}
	
	public Mensaje crearSolicitud(SolicitudEnrolamiento solicitud) {
		String k = solicitud.getDatos_representante().getNumero_identificacion() + "-" + solicitud.getDatos_representante().getTipo_identificacion();
		Boolean val = SolicitudEnrolamientoJsonServicio.MAP_TOKEN_VALIDOS.get(k);
		if (val != null && !val && solicitud.getOtp() != null) {
			return new Mensaje("ERROR", "El token no ha sido verificado!", false);
		}
		return SolicitudEnrolamientoServicio.getInstance().crearSolicitud(solicitud, false, null);
	}
	
	public Mensaje crearSolicitudSignapp(SolicitudEnrolamiento solicitud) {
		String k = solicitud.getDatos_representante().getNumero_identificacion() + "-" + solicitud.getDatos_representante().getTipo_identificacion();
		Boolean val = SolicitudEnrolamientoJsonServicio.MAP_TOKEN_VALIDOS.get(k);
		if (val != null && !val && solicitud.getOtp() != null) {
			return new Mensaje("ERROR", "El token no ha sido verificado!", false);
		}
		return SolicitudEnrolamientoServicio.getInstance().crearSolicitud(solicitud, false, null);
	}
	
	public List<Pais> obtenerPaises(){
		return SolicitudEnrolamientoServicio.getInstance().obtenerPaises();
	}
	
	public Pais obtenerPaisPorCodigo(String codigo){
		return SolicitudEnrolamientoServicio.getInstance().obtenerPaisPorCodigo(codigo);
	}
	
	public List<Ciudad> obtenerCiudades(Integer id_pais){
		return SolicitudEnrolamientoServicio.getInstance().obtenerCiudades(id_pais);
	}

	public Boolean loginDisponible(String login){
		return PersonaServicio.getInstance().loginDisponible(login);
	}
	
	public Boolean activoSolicitudEnrolamiento(){
		String activo = ParametrosInicio.getProperty("solicitud_enrolamiento.activo");
		return StringUtils.isTrue(activo);
	}
	
	public Persona obtenerInformacionUsuario(){
		Integer id_usuario = (Integer) session.getAttribute("id_usuario");
		return SolicitudEnrolamientoServicio.getInstance().obtenerInformacionUsuario(id_usuario);
	}
	
	public Boolean existeCredencialPersonaNatural(String identificacion, Integer tipoDocumento){
		return SolicitudEnrolamientoServicio.getInstance().existeCredencialPersonaNatural(identificacion, tipoDocumento);
	}
	
	public Boolean existeAdministradorEnUsuario(SolicitudEnrolamiento solicitud){
		String identificacion_cliente = solicitud.getNumero_identificacion();
		Integer tipo_documento_cliente = solicitud.getTipo_identificacion();
		String tipo_persona = "N";
		String identificacion = solicitud.getDatos_usuario().getNumero_de_documento();
		Integer tipoDocumento = solicitud.getDatos_usuario().getTipo_de_documento();
		return SolicitudEnrolamientoServicio.getInstance().existeAdministradorEnUsuario(identificacion_cliente, tipo_documento_cliente, tipo_persona, identificacion, tipoDocumento);
	}
	
	public Integer obtenerSiguiente() {
		return ArchivoAdjuntoServicio.getInstance().obtenerSiguiente();
	}
	
	public List<ArchivoAdjunto> obtenerArchivosAdjuntosPorCarga(Integer id_carga) {
		return ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCarga(id_carga, false, null);
	}
	
	public Boolean borrarAdjuntos(Integer id_carga) {
		return ArchivoAdjuntoServicio.getInstance().borrarAdjuntos(id_carga);
	}
	
	public boolean actualizarDescripcion(Integer id_archivo_adjunto, String descripcion) {
		return ArchivoAdjuntoServicio.getInstance().actualizarDescripcion(id_archivo_adjunto, descripcion);
	}
	
	public SMessage validarTokenSinSesion(String identificacion, Integer tipo_identificacion, String token){
		SMessage val = SignAppServicio.getInstance().validarTokenSinSesion(identificacion, tipo_identificacion, token);
		SolicitudEnrolamientoJsonServicio.MAP_TOKEN_VALIDOS.put(identificacion + "-"+ tipo_identificacion, val.getValid());
		return val;
	}
	
	public SMessage validarTokenSinSesionPorCredencial(String login, String token){
		Persona persona = PersonaServicio.getInstance().obtenerPersonaPorLogin(login);
		
		return SignAppServicio.getInstance().validarTokenSinSesion(persona.getIdentificacion(), persona.getTipo_documento(), token);
	}
	
	public String obtenerCaptchaSecret(){
		return ParametrosInicio.getProperty("recaptcha.secret");
	}
	
	public static List<String> obtenerDepartamentos(){
		String sql = "SELECT DISTINCT($DEPARTAMENTO CIUDAD.DEPARTAMENTO$) FROM $DEPARTAMENTO CIUDAD$";
		
		List<String> departamentos = DS_SqlUtils.queryForList(String.class, sql);
		
		Collections.sort(departamentos);
		
		return departamentos;
	}
	
	public List<ValorLista> obtenerDepartamentosDane() {
		return SolicitudEnrolamientoServicio.getInstance().obtenerDepartamentosDane(session);
	}
	
	public List<ValorLista> obtenerCiudadesPorDepartamento(String departamento){
		String sql = "SELECT ID || '' ID, $DEPARTAMENTO CIUDAD.MUNICIPIO$ NOMBRE FROM $DEPARTAMENTO CIUDAD$ WHERE $DEPARTAMENTO CIUDAD.DEPARTAMENTO$ = #departamento# order by  $DEPARTAMENTO CIUDAD.MUNICIPIO$";
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("departamento", departamento);
		
		List<ValorLista> ciudades_departamento = DS_SqlUtils.queryForList(ValorLista.class, sql, params);
		
		return ciudades_departamento;
	}
	
	public List<ValorLista> obtenerMunicipiosDanePorDepartamento(Integer id_departamento) {
		Session session = new DummySession();
		return SolicitudEnrolamientoServicio.getInstance().obtenerMunicipiosDanePorDepartamento(id_departamento, session);
	}
	
}
