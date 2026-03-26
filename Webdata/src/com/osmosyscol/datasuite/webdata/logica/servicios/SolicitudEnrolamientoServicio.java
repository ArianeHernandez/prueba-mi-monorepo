package com.osmosyscol.datasuite.webdata.logica.servicios;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cocoon.environment.Session;
import org.apache.commons.jxpath.JXPathContext;

import co.htsoft.commons.lang.StringUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.CredencialServicio;
import com.osmosyscol.datasuite.logica.servicios.ListaDinamicaCampoServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.mein.dtos.DepartamentoDane;
import com.osmosyscol.datasuite.mein.dtos.EnrolamientoCliente;
import com.osmosyscol.datasuite.mein.dtos.MunicipioDane;
import com.osmosyscol.datasuite.mein.dtos.ObjGenerico;
import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaDinamicaServicio;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.persistencia.dao.TipoArchivoDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.utils.dummys.DummySession;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.Ciudad;
import com.osmosyscol.datasuite.webdata.logica.dto.DatosRepresentante;
import com.osmosyscol.datasuite.webdata.logica.dto.Pais;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.dto.ValorListaDinamicaCampo;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class SolicitudEnrolamientoServicio {

	public static final Integer ID_FORMATO_PN = -500;
	public static final Integer ID_FORMATO_AC = -501;
	public static final Integer ID_FORMATO_LC = -502;
	public static final Integer ID_FORMATO_REGISTRO_SIGN_APP = -504;
	public static final String NIT_SUPERSOCIEDADES = "899999086";
	public static final Integer TIPO_DOCUMENTO_USUARIO = 10;
	public static final Integer LISTA_DINAMICA_DEPARTAMENTOS_DANE = 50340;
	public static final Integer LISTA_DINAMICA_MUNICIPIOS_DANE = 50360;
	
	private static SolicitudEnrolamientoServicio instance = new SolicitudEnrolamientoServicio();

	private SolicitudEnrolamientoServicio() {
	}

	public static SolicitudEnrolamientoServicio getInstance() {
		return instance;
	}
	
	public Integer crearCarga (Boolean registroSignapp) {
		try {
			Integer id_negocio = 1; 

			String identificacion = NIT_SUPERSOCIEDADES;		//TODO: Dejar como parámetro

			Integer tipoDocumento = TIPO_DOCUMENTO_USUARIO;
			
			Usuario usuario = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(identificacion, tipoDocumento );

			Persona persona = PersonaServicio.getInstance().obtenerPersona(usuario.getId_persona());
			
			String ip_usuario = "127.0.0.1";
			
			Integer id_formato = null;
			
			if (registroSignapp) {
				id_formato = ID_FORMATO_REGISTRO_SIGN_APP;
			} else{
				id_formato = ID_FORMATO_AC;
			}
			
			Integer id_carga = CargaServicio.getInstance().crearCargaInteractiva(null, persona.getId_persona(), id_formato, null, usuario.getId_usuario(), id_negocio, ip_usuario);
			
			return id_carga;
			
		} catch (Exception e) {
			SimpleLogger.setError("SolicitudEnrolamientoServicio.crearCarga: Error al crear carga ", e);
			return -1;
		}

	}
	
	public Mensaje crearSolicitud(SolicitudEnrolamiento solicitud, boolean tiene_sesion, Integer id_persona) {
		
		
		SimpleLogger.setInfo("=========================================================================================");
		SimpleLogger.setInfo("SOLICITUD A CREAR");
		SimpleLogger.setInfo(solicitud.toString());
		SimpleLogger.setInfo("SOLICITUD A CREAR");
		SimpleLogger.setInfo("=========================================================================================");
		
		try {
			Integer id_negocio = 1; 

			if(solicitud.getNumero_identificacion() != null){
				solicitud.setNumero_identificacion(solicitud.getNumero_identificacion().trim());
			}
			
			Integer id_persona_usuario = null;
			
			if (solicitud.getOtp() == null) {
				Mensaje repValido = validarDatosRepresentante(solicitud.getDatos_representante());
				
				if (!repValido.getExitoso()) {
					return repValido;
				}
			}
			
			if (StringUtils.equals("2", solicitud.getTipo_formulario())
					|| StringUtils.equals("3", solicitud.getTipo_formulario())
					) {
				solicitud.setNumero_identificacion(solicitud.getDatos_representante().getNumero_identificacion());
				solicitud.setTipo_identificacion(1);
			}

			Persona persona_usuario = PersonaServicio.getInstance().obtenerPersonaPorIdentificacion(solicitud.getNumero_identificacion(), solicitud.getTipo_identificacion());
			if(persona_usuario != null){
				id_persona_usuario = persona_usuario.getId_persona();
			}
			
			if(id_persona_usuario != null){
				
				Usuario usuarioSociedad = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(solicitud.getNumero_identificacion().trim(), solicitud.getTipo_identificacion() );
				if (usuarioSociedad!= null) {
					Credencial credencial = CredencialServicio.getInstance().obtenerCredencialPersonaUsuario(id_persona_usuario, usuarioSociedad.getId_usuario());
					if(credencial != null){
						return new Mensaje("USUARIO_EXISTE", "El usuario que intenta registrar ya existe.", false);
					}
				}
			}
			
			Integer id_carga = solicitud.getIdcarga(); 
			Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
			
			if (id_persona == null) {
				id_persona = carga.getId_persona();
			}
			
			if(solicitud.getDatos_representante() != null){
				Integer id_tipo_documento_rep = DS_SqlUtils.queryForObject(Integer.class, "select id from $tipo de documento$ where $tipo de documento.codigo$ = $I(" +  solicitud.getDatos_representante().getTipo_identificacion() + ")$" );
				solicitud.getDatos_representante().setTipo_identificacion(id_tipo_documento_rep);
			}
			if(solicitud.getDatos_usuario() != null){
				Integer tipo_de_documento = DS_SqlUtils.queryForObject(Integer.class, "select id from $tipo de documento$ where $tipo de documento.codigo$ = $I(" + solicitud.getDatos_usuario().getTipo_de_documento() + ")$" );
				solicitud.getDatos_usuario().setTipo_de_documento(tipo_de_documento);
			}
			
			Integer id_tipo_documento = DS_SqlUtils.queryForObject(Integer.class, "select id from $tipo de documento$ where $tipo de documento.codigo$ = $I(" + solicitud.getTipo_identificacion() + ")$" );
			solicitud.setTipo_identificacion(id_tipo_documento);
			
			if(StringUtils.isNumeric(solicitud.getTipo_formulario())) {
				solicitud.setTipo_solicitante(Integer.parseInt(solicitud.getTipo_formulario()));
			}
			
			Boolean exito = guardarSolicitudEnrolamiento(solicitud, id_carga);
			if (!exito) {
				return new Mensaje("Error", "Error guardando solicitud", false);
			}

			Session session = new DummySession();

			CargaServicio.getInstance().realizarCarga(id_carga, id_persona, id_negocio, session, null);

			carga = CargaServicio.getInstance().obtenerCarga(id_carga);

			List<ValorListaDinamicaCampo> listaValoresDinamicos = new ArrayList<ValorListaDinamicaCampo>();

			CargaServicio.getInstance().guardarValoresLista(listaValoresDinamicos, carga.getId_formato(), true);
			CargaServicio.getInstance().finalizarCarga(carga);
			CargaServicio.getInstance().asociarCargasAInstanciasDeProceso();
			return new Mensaje("OK", "Se creó la solicitud con id_carga " + id_carga, true, id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Error al crear soliciutd", e);
			return new Mensaje("Error al crear la solicitud", false);
		}

	}
	
	private Mensaje validarDatosRepresentante(DatosRepresentante datos_representante) {
		
		JXPathContext c = JXPathContext.newContext(datos_representante);
		
		Map<String, String> val = new  HashMap<String, String>();
		val.put("fecha_expedicion", "Fecha de expedición no válida");
		val.put("fecha_nacimiento", "Fecha de nacimiento no válida");
		val.put("sexo", "Sexo no válido");
		val.put("lugar_nacimiento", "Lugar de nacimiento no válida");
		val.put("celular", "Celular no válido");
		val.put("correo_electronico", "Correo electrónico no válido");
		val.put("numero_identificacion", "Número de identificación no válido");
		
		
		Set<String> ks = val.keySet();
		for (String k : ks) {
			Object value = c.getValue(k);
			
			if (value == null) {
				return new Mensaje("Error", val.get(k), false); 
				
			}
			if (value instanceof String && StringUtils.isEmpty(value)) {
				return new Mensaje("Error", val.get(k), false);
			}
		}
				
		return new Mensaje("OK", true);
	}

	public Boolean guardarSolicitudEnrolamiento(SolicitudEnrolamiento solicitud, Integer id_carga) {
		
		DaoManager daoManager = DaoConfig.getDaoManager(2);
		
		daoManager.startTransaction();

		try {
			Integer id_datos_usuario = null;
			Integer id_datos_representante = null;
			
			Boolean exito = true;
			if(solicitud.getDatos_representante() != null){
				
				id_datos_representante = DS_SqlUtils.nextId();
				
				String insert_datos_representante = "INSERT INTO $ENROLAMIENTO REP LEGAL$ ( " 
						 + "$ENROLAMIENTO REP LEGAL.NOMBRES$, "
						 + "$ENROLAMIENTO REP LEGAL.APELLIDOS$, "
						 + "$ENROLAMIENTO REP LEGAL.TIPO IDENTIFICACION$, "
						 + "$ENROLAMIENTO REP LEGAL.NUMERO IDENTIFICACION$, "
						 + "$ENROLAMIENTO REP LEGAL.CORREO ELECTRONICO$, "
						 + "$ENROLAMIENTO REP LEGAL.FECHA EXPEDICION$, "
						 + "$ENROLAMIENTO REP LEGAL.FECHA NACIMIENTO$, "
						 + "$ENROLAMIENTO REP LEGAL.SEXO$, "
						 + "$ENROLAMIENTO REP LEGAL.LUGAR NACIMIENTO$, "
						 + "$ENROLAMIENTO REP LEGAL.CELULAR$, "
						 + "ID , "
						 + "IDCARGA "
						 + ") "
						 + "VALUES "
						 + "( "
						 + "#nombres#, "
						 + "#apellidos#, "
						 + "$tipo_identificacion$, "
						 + "#numero_identificacion#, "
						 + "#correo_electronico#, "
						 + "#fecha_expedicion#, "
						 + "#fecha_nacimiento#, "
						 + "#sexo#, "
						 + "#lugar_nacimiento#, "
						 + "#celular#, "
						 + id_datos_representante + ", "
						 + id_carga
						 + ") ";
				
				SimpleLogger.setDebug("SIGSSS QUERY REP_LEGAL" + insert_datos_representante);
				
				exito = exito && DS_SqlUtils.insert(insert_datos_representante, solicitud.getDatos_representante());
				
			}
			
			Integer id_solicitud_enrolamiento = DS_SqlUtils.nextId();
			
			String estadoEnrolamiento = solicitud.getOtp() == null ? "$S(SignApp)$" : null;
			
			String insert_solicitud_enrolamiento = "INSERT INTO $ENROLAMIENTO CLIENTE$ ( " 
					 + "$ENROLAMIENTO CLIENTE.NOMBRES$, "
					 + "$ENROLAMIENTO CLIENTE.APELLIDOS$, "
					 + "$ENROLAMIENTO CLIENTE.RAZON SOCIAL$, "
					 + "$ENROLAMIENTO CLIENTE.TIPO IDENTIFICACION$, "
					 + "$ENROLAMIENTO CLIENTE.NUMERO IDENTIFICACION$, "
					 + "$ENROLAMIENTO CLIENTE.FECHA DE EXPEDICION$, "
					 + "$ENROLAMIENTO CLIENTE.DIRECCION$, "
					 + "$ENROLAMIENTO CLIENTE.TELEFONO$, "
					 + "$ENROLAMIENTO CLIENTE.PAIS$, "
					 + "$ENROLAMIENTO CLIENTE.DEPARTAMENTO DANE$, "
					 + "$ENROLAMIENTO CLIENTE.MUNICIPIO DANE$, "
					 + "$ENROLAMIENTO CLIENTE.CORREO ELECTRONICO$, "
					 + "$ENROLAMIENTO CLIENTE.CELULAR$, "
					 + "$ENROLAMIENTO CLIENTE.LOGIN$, "
					 + "$ENROLAMIENTO CLIENTE.VALOR INDIVIDUAL$, "
					 + "$ENROLAMIENTO CLIENTE.VALOR LOTE$, "
					 + "$ENROLAMIENTO CLIENTE.DATOS USUARIO$, "
					 + "$ENROLAMIENTO CLIENTE.DATOS REPRESENTANTE$, "
					 + "$ENROLAMIENTO CLIENTE.ESTADO OPERACION$, "
					 + "$ENROLAMIENTO CLIENTE.TIPO FORMULARIO$, "
					 + "$ENROLAMIENTO CLIENTE.TIPO SOLICITANTE$, "
					 + "ID , "
					 + "IDCARGA "
					 + ") "
					 + "VALUES "
					 + "( "
					 + "#nombres#, "
					 + "#apellidos#, "
					 + "#razon_social#, "
					 + "$tipo_identificacion$, "
					 + "#numero_identificacion#, "
					 + "#fecha_de_expedicion#, "
					 + "#direccion#, "
					 + "#telefono#, "
					 + "$pais$, "
					 + "$departamento_dane$, "
					 + "$municipio_dane$, "
					 + "#correo_electronico#, "
					 + "#celular#, "
					 + "#login#, "
					 + "#valor_individual#, "
					 + "#valor_lote#, "
					 + id_datos_usuario + ", "
					 + id_datos_representante + ", "
					 + estadoEnrolamiento + ", "
					 + "#tipo_formulario#,"
					 + "#tipo_solicitante#, "
					 + id_solicitud_enrolamiento + ", "
					 + id_carga
					 + ") ";
			
			SimpleLogger.setDebug("SIGSSS QUERY SOL_ENRO: " + insert_solicitud_enrolamiento);

			if (exito)
				exito = DS_SqlUtils.insert(insert_solicitud_enrolamiento, solicitud);

			if (exito) {
				daoManager.commitTransaction();
			}
			return exito;

		} catch (Exception e) {
			SimpleLogger.setError("Error guardando datos", e);
			return false;
		}finally{
			daoManager.endTransaction();
		}
	}
	
	public SolicitudEnrolamiento obtenerSolicitudPersona(String documento){
		SolicitudEnrolamiento solicitud = DS_SqlUtils.queryForObject(SolicitudEnrolamiento.class, "select * from $ENROLAMIENTO CLIENTE$ " +
				"where idcarga = 0 "+
				"and  $ENROLAMIENTO CLIENTE.NUMERO IDENTIFICACION$ = $S("+ documento +")$ "+
				"and rownum = 1");
		return solicitud;
	}
	
	public SolicitudEnrolamiento obtenerSolicitudEnrolamiento (Integer id_carga){
		
		SolicitudEnrolamiento solicitud = DS_SqlUtils.queryForObject(SolicitudEnrolamiento.class, "select * from $ENROLAMIENTO CLIENTE$ where idcarga = " + id_carga);
		
		return completarSolicitud(solicitud);
	}
	
	public SolicitudEnrolamiento completarSolicitud (SolicitudEnrolamiento solicitud) {
		
		Integer id_carga = solicitud.getIdcarga();
		
		String pais_nombre = DS_SqlUtils.queryForObject(String.class, "select $PAISES DANE.NOMBRE$ from $PAISES DANE$ where id = " + solicitud.getPais() );
		solicitud.setPais_nombre(pais_nombre);
		
		DepartamentoDane departamento_obj = DS_SqlUtils.queryForObject(DepartamentoDane.class, "select * from $DEPARTAMENTOS DANE$ where id = " + solicitud.getDepartamento_dane());
		solicitud.setDepartamento_obj(departamento_obj);
		
		MunicipioDane municipio_obj = DS_SqlUtils.queryForObject(MunicipioDane.class, "select * from $MUNICIPIOS DANE$ where id = " + solicitud.getMunicipio_dane());
		solicitud.setMunicipio_obj(municipio_obj);
		
		TipoDeDocumento tipo_identificacion_obj = DS_SqlUtils.queryForObject(TipoDeDocumento.class, "select * from $TIPO DE DOCUMENTO$ where id = " + solicitud.getTipo_identificacion());
		solicitud.setTipo_identificacion_obj(tipo_identificacion_obj);
		
		DatosRepresentante datos_representante = DS_SqlUtils.queryForObject(DatosRepresentante.class, "select * from $ENROLAMIENTO REP LEGAL$ where idcarga = " + id_carga);
		
		if(datos_representante != null){
			TipoDeDocumento tipo_identificacion_representante_obj = DS_SqlUtils.queryForObject(TipoDeDocumento.class, "select * from $TIPO DE DOCUMENTO$ where id = " + datos_representante.getTipo_identificacion());
			datos_representante.setTipo_identificacion_obj(tipo_identificacion_representante_obj);
			
		}
		
		solicitud.setDatos_representante(datos_representante);
		
		return solicitud;
		
	}
	
	public SolicitudEnrolamiento SolicitudEnrolamientoIdentificacion (String numero_identificacion){
		
		SolicitudEnrolamiento solicitud = DS_SqlUtils.queryForObject(SolicitudEnrolamiento.class, "select * from $ENROLAMIENTO CLIENTE$ e where e.$ENROLAMIENTO CLIENTE.NUMERO IDENTIFICACION$ = $S("+ numero_identificacion +")$ AND IDCARGA <> 0");
		
		
		return completarSolicitud(solicitud);
	}
	
	public List<Pais> obtenerPaises(){
		List<Pais> paises = DS_SqlUtils.queryForList(Pais.class, "SELECT * FROM $PAISES DANE$ WHERE $PAISES DANE.NOMBRE SSOC$ IS NOT NULL");
		return paises;
	}
	
	public Pais obtenerPaisPorCodigo(String codigo){
		Pais pais = DS_SqlUtils.queryForObject(Pais.class, "select * from $PAISES DANE$ C WHERE C.$PAISES DANE.CODIGO$ = $S("+codigo+")$");
		return pais;
	}
	
	public List<ObjGenerico> obtenerTiposSolicitante(){
		List<ObjGenerico> listaTipos = DS_SqlUtils.queryForList(ObjGenerico.class, "select * from $tipo solicitante$ where estado = 'A' order by id asc");
		return listaTipos;
	}
	
	public List<Ciudad> obtenerCiudades(Integer id_pais){
		List<Ciudad> ciudades = DS_SqlUtils.queryForList(Ciudad.class, "SELECT * FROM $CIUDADES DANE$ WHERE $CIUDADES DANE.CODIGO PAIS$ IN (SELECT $PAISES DANE.CODIGO$ FROM $PAISES DANE$ WHERE ID = " + id_pais + " )");
		return ciudades;
	}

	public Persona obtenerInformacionUsuario(Integer id_usuario) {
		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(id_usuario);
		return PersonaServicio.getInstance().obtenerPersona(usuario.getId_persona());
	}
	
	public List<String> obtenerExtensionesPorTipoArchivo (Integer id_tipo_archivo) {
		try {
			TipoArchivoDao tipoArchivoDao = (TipoArchivoDao) DaoConfig.getDao(TipoArchivoDao.class);
			return tipoArchivoDao.obtenerExtensionesPorTipoArchivo(id_tipo_archivo);
		} catch (Exception e) {
			SimpleLogger.setError("TipoArchivoServicio.obtenerExtensionesPorTipoArchivo: Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Boolean existeCredencialPersonaNatural(String identificacion, Integer tipoDocumento){
		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(identificacion, tipoDocumento);
		Credencial credencial = CredencialServicio.getInstance().obtenerCredencialPersonaUsuario(usuario.getId_persona(), usuario.getId_usuario());
		if(credencial != null){
			return true;
		}else{
			return false;
		}
	}
	
	public Boolean existeAdministradorEnUsuario( String identificacion_cliente, Integer tipo_documento_cliente, String tipo_persona, String identificacion, Integer tipoDocumento){
		Usuario usuario = UsuarioServicio.getInstance().obtenerUsuarioPorIdentificacion(identificacion_cliente, tipo_documento_cliente);
		String rol = Constantes.ROL_ADMINISTRATIVOCL;
		Integer id_usuario = usuario.getId_usuario();
		Persona persona = PersonaServicio.getInstance().obtenerPersonaUsuario(id_usuario, rol, tipo_persona, identificacion, tipoDocumento);
		return persona != null;
	}
	
	public Boolean existeLiberadorEnUsuario(Integer id_usuario, String tipo_persona, String identificacion, Integer tipoDocumento){
		String rol = Constantes.ROL_LIBERADOR;
		Persona persona = PersonaServicio.getInstance().obtenerPersonaUsuario(id_usuario, rol, tipo_persona, identificacion, tipoDocumento);
		return persona != null;
	}

	public List<ValorLista> obtenerDepartamentosDane(Session session) {
		return ListaDinamicaServicio.getInstance().obtenerValoresListaDinamica(LISTA_DINAMICA_DEPARTAMENTOS_DANE, session);
	}
	
	public List<ValorLista> obtenerMunicipiosDanePorDepartamento(Integer id_departamento, Session session) {
		List<ValorLista> municipios = null;
		if (id_departamento != null) {
			municipios = ListaDinamicaCampoServicio.getInstance().obtenerValoresListaDinamica(LISTA_DINAMICA_MUNICIPIOS_DANE, id_departamento.toString(), session);
		}
		return municipios;
	}
	
	public Map<String,Object> obtenerEnrolamientoRepLegal (Integer id) {
		Map<String, Object> datos = null;
		try {
			DaoManager daoManager = DaoConfig.getDaoManager(2);

			SQLDao dao = (SQLDao) daoManager.getDao(SQLDao.class);
			
			String sql = "SELECT E.$ENROLAMIENTO REP LEGAL.NOMBRES$ NOMBRE, "
					+ " E.$ENROLAMIENTO REP LEGAL.APELLIDOS$ APELLIDOS, "
					+ " E.$ENROLAMIENTO REP LEGAL.NUMERO IDENTIFICACION$ IDENTIFICACION, "
					+ " E.$ENROLAMIENTO REP LEGAL.CORREO ELECTRONICO$ EMAIL, "
					+ " T.$TIPO DE DOCUMENTO.CODIGO NUMERICO POSTAL$ TIPO_IDENTIFICACION_ID, "
					+ " T.$TIPO DE DOCUMENTO.NOMBRE POSTAL$ TIPO_IDENTIFICACION_NOMBRE"
					+ "  FROM $ENROLAMIENTO REP LEGAL$ E, $TIPO DE DOCUMENTO$ T "
					+ "  WHERE E.$ENROLAMIENTO REP LEGAL.TIPO IDENTIFICACION$ = T.ID AND E.ID = " + id;
			sql = RDServicio.reemplazarNombres(sql);
			List<Map<String, Object>> lista = dao.selectSQL(sql);
			
			if (lista != null && lista.size() > 0) {
				lista = SQLServicio.desencriptarMapaStringFormat(lista);
				datos = lista.get(0);
				
				
			}
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
		}
		
		return datos;
	}
	
	public EnrolamientoCliente obtenerEnrolamientoCliente (Integer id_carga) {
		try {
			
			EnrolamientoCliente datos = DS_SqlUtils.queryForObject(EnrolamientoCliente.class, 
					"select deudor.* " +
					" from $enrolamiento cliente$ deudor " +
					" where deudor.idcarga = " + id_carga);
			
			return datos;
			
		} catch (Exception e) {
			SimpleLogger.setError("obtenerEnrolamientoCliente: ", e);
			return null;
		}
	}
}
