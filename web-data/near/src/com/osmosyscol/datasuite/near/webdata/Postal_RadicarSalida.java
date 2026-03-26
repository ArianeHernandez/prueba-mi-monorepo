package com.osmosyscol.datasuite.near.webdata;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.mein.dtos.APVistaCuaderno;
import com.osmosyscol.datasuite.mein.dtos.APVistaDependencias;
import com.osmosyscol.datasuite.mein.dtos.APVistaDocumentoTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaMedioEnvio;
import com.osmosyscol.datasuite.mein.dtos.APVistaSeguridadTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaSerie;
import com.osmosyscol.datasuite.mein.dtos.APVistaSubserie;
import com.osmosyscol.datasuite.mein.dtos.APVistaTramite;
import com.osmosyscol.datasuite.mein.dtos.DepartamentoDane;
import com.osmosyscol.datasuite.mein.dtos.EnrolamientoCliente;
import com.osmosyscol.datasuite.mein.dtos.MunicipioDane;
import com.osmosyscol.datasuite.mein.dtos.ParticularPostal;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.mein.servicios.RadicacionAutoOficioServicio;

import org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionSalida;

import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.near.servicios.radica.LadoLocal;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto2;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class Postal_RadicarSalida extends Abstract_Postal_Envio_Principal {

	@Override
	String getPuntoInteraccion() {
		return IPostalInteraccion.PUNTOINTERACCION_RADICACION_AUTOOFICIO;
	}

	@Override
	boolean stopIfErrorOnSync() {
		return true;
	}

	@Override
	Either<Exception, Boolean> mapOutputPayload(Integer id_carga,
			Either<Exception, Object> serviceResponse) {
		return LadoLocal.getInstance().sync_UsingRadicarSalida(id_carga, serviceResponse);
	}

	@Override
	Either<Exception, Map<String, Object>> mapInputPayload(Integer id_carga) {
		
		//Either<Exception,RadicacionSalida> infoSource = llenarDummy();
		Either<Exception,RadicacionSalida> infoSource = obtenerInformacionRadicacionSalida(id_carga);
		
		if( infoSource.isLeft() ) {
			return Either.left( infoSource.left() ); 
		}
		
		Map<String,Object> payload = new HashMap<String,Object>(); // Stream.of
		
		payload.put(IPostalInteraccion.PARAM_PUNTOINTERACCION, getPuntoInteraccion());
		payload.put(IPostalInteraccion.PARAM_CORRELATION_ID1, id_carga);
		payload.put(IPostalInteraccion.PARAM_SOLICITUD, infoSource.right() );
		return Either.right(payload);
	}

	@Override
	Either<Exception, Object> ladoRemoto_SyncMaster(Integer id_carga,
			Either<Exception, Map<String, Object>> payloadSource) {
		
		return LadoRemoto2.getInstance().sync_RadicarSalida(id_carga, payloadSource.right());
	}
	
	public Either<Exception, RadicacionSalida> obtenerInformacionRadicacionSalida (Integer id_carga) {
		RadicacionAutoOficio solicitudRadicacionAutoOficio = RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficio(id_carga);
		
		if( solicitudRadicacionAutoOficio == null ) {
			String m = String.format("No se encuentra radicacion con id: %s" , id_carga);
			logger.warn(m);
			return Either.left(new Exception(m));
		}
		
		RadicacionSalida informacionRadicacionSalida = mapRequest (solicitudRadicacionAutoOficio);
		
		if( informacionRadicacionSalida == null ) {
			String m = String.format("Error al generar la informacion del mensaje con id: %s" , id_carga);
			logger.warn(m);
			return Either.left(new Exception(m));
		}
		
		return Either.right(informacionRadicacionSalida);
		
	}
	
	public RadicacionSalida mapRequest (RadicacionAutoOficio radicacion) {
		RadicacionSalida radicarSalida = null;
		
		try {
			if (radicacion != null) {
				radicarSalida = new RadicacionSalida();
				
				EnrolamientoCliente deudor = radicacion.getDeudor();
				APVistaDependencias dependencia = radicacion.getDependencia();
				APVistaDependencias dependenciaAsignada = radicacion.getDependenciaAsignada();
				APVistaMedioEnvio codigoMedioEnvio = radicacion.getCodigoMedioEnvio();
				APVistaSeguridadTipo codigoTipoSeguridad = radicacion.getCodigoTipoSeguridad();
				APVistaDocumentoTipo documentalTipo = radicacion.getDocumentalTipo();
				APVistaCuaderno cuadernoTipo = radicacion.getCuadernoTipo();
				APVistaTramite tramite = radicacion.getTramite();
				APVistaSerie serie = radicacion.getSerie();
				APVistaSubserie subserie = radicacion.getSubserie();
				String radicacionAnterior = radicacion.getRadicacionAnterior();
				
				radicarSalida.setAnexosFisicos(Objects.toString(radicacion.getAnexosFisicos(), "0"));
				if (deudor.getMunicipio_dane_obj() != null) {
					radicarSalida.setAplicaCiudadCodigo(deudor.getMunicipio_dane_obj().getCodigoMunicipioPostal());
					radicarSalida.setParticularCiudadCodigo(deudor.getMunicipio_dane_obj().getCodigoMunicipioPostal());
				}
				if (deudor.getDepartamento_dane_obj() != null) {
					radicarSalida.setAplicaDepartamentoCodigo(deudor.getDepartamento_dane_obj().getCodigo_departamento());
					radicarSalida.setParticularDepartamentoCodigo(deudor.getDepartamento_dane_obj().getCodigo_departamento());
					
				}
				radicarSalida.setEntregaFisica("1".equals(radicacion.getEntregaFisica()));
				radicarSalida.setExtensionArchivo(radicacion.getExtensionArchivo());
				radicarSalida.setFoliosNumero(radicacion.getFoliosNumero());
				radicarSalida.setLoginUsuario(radicacion.getLoginUsuario());
				radicarSalida.setRadicacionAnterior((radicacionAnterior == null || radicacionAnterior.isEmpty()) ? "0" : radicacionAnterior);
				radicarSalida.setReferenciaExterna(radicacion.getReferenciaExterna());
				
				String propiedad_funcionario = getPuntoInteraccion() + "." + IPostalInteraccion.SVC_FUNCIONARIO_ID;
				String funcionarioId = ParametrosInicio.getProperty(propiedad_funcionario);
				
				if (radicacion.getFuncionarioAsignadoId() != null) {
					radicarSalida.setFuncionarioAsignadoId(radicacion.getFuncionarioAsignadoId());	
					radicarSalida.setFuncionarioId(radicacion.getFuncionarioAsignadoId());
				} else {
					radicarSalida.setFuncionarioAsignadoId(funcionarioId);
					radicarSalida.setFuncionarioId(funcionarioId);
				}
				
//				if (radicacion.getFuncionarioId() != null) {
//					radicarSalida.setFuncionarioId(radicacion.getFuncionarioId());			
//					radicarSalida.setFuncionarioId(radicacion.getFuncionarioAsignadoId());
//				} else {
//					radicarSalida.setFuncionarioId(funcionarioId);
//					radicarSalida.setFuncionarioId(radicacion.getFuncionarioAsignadoId());
//				}
				
				if (deudor != null) {	
					Map<String,Object> rep_legal = SolicitudEnrolamientoServicio.getInstance().obtenerEnrolamientoRepLegal(deudor.getDatos_representante());
					
					radicarSalida.setAplicaDireccion(deudor.getDireccion());
					radicarSalida.setParticularDireccion(deudor.getDireccion());
					radicarSalida.setAplicaEmail(deudor.getCorreo_electronico());
					radicarSalida.setAplicaIdentificacion(deudor.getNumero_identificacion());
					radicarSalida.setAplicaNombre(deudor.getRazon_social());
					
					String telefono = ParametrosInicio.getProperty(getPuntoInteraccion() + "." + IPostalInteraccion.SVC_TELEFONO);
					
					if (deudor.getTelefono() != null) {
						radicarSalida.setAplicaTelefono(deudor.getTelefono());
						radicarSalida.setParticularTelefono(deudor.getTelefono());
					} else {
						radicarSalida.setAplicaTelefono(telefono);
						radicarSalida.setParticularTelefono(telefono);
					}
					
					
					if (deudor.getTipo_identificacion() != null) {
						radicarSalida.setAplicaTipoIdentificacionId(deudor.getTipo_identificacion().getCodigo_numerico_postal());
						radicarSalida.setAplicaTipoIdentificacionNombre(deudor.getTipo_identificacion().getNombre_postal());						
					}
					
					
					
					
					
					if (deudor.getPais() != null) {
						radicarSalida.setAplicaPaisCodigo(deudor.getPais().getCodigo_ssoc());	
						radicarSalida.setParticularPaisCodigo(deudor.getPais().getCodigo_ssoc());
					}
					
					if (rep_legal != null) {
						
						String email = (String)rep_legal.get("EMAIL");
						String identificacion = (String)rep_legal.get("IDENTIFICACION");
						String nombre = (String)rep_legal.get("NOMBRE");
						String apellido = (String)rep_legal.get("APELLIDOS");
						Integer tipo_identificacion_id = Integer.parseInt((String)rep_legal.get("TIPO_IDENTIFICACION_ID"));
						String tipo_identificacion_nombre = (String)rep_legal.get("TIPO_IDENTIFICACION_NOMBRE");
						
						radicarSalida.setParticularEmail(email);
						radicarSalida.setParticularIdentificacion(identificacion);
						radicarSalida.setParticularNombre(nombre + " " + apellido);
						radicarSalida.setParticularTipoIdentificacionId(tipo_identificacion_id);
						radicarSalida.setParticularTipoIdentificacionNombre(tipo_identificacion_nombre);						
							
					}
				}

				if (codigoMedioEnvio != null) {
					radicarSalida.setCodigoMedioEnvio(codigoMedioEnvio.getCodigo().intValue());					
				}
				
				if (codigoTipoSeguridad != null) {
					radicarSalida.setCodigoTipoSeguridad(codigoTipoSeguridad.getSeguridadTipo());					
				}
				
				if (cuadernoTipo != null) {
					radicarSalida.setCuadernoTipoId(0);
					radicarSalida.setCuadernoCodigo(cuadernoTipo.getCodigo());
				}
				
				if (dependencia != null) {
					radicarSalida.setDependenciaId(dependencia.getCodigo().intValue());
					radicarSalida.setDependenciaNombre(dependencia.getNombreDependencia());
				}
				
				if (documentalTipo != null) {
					radicarSalida.setDocumentalTipoId(documentalTipo.getCodigo_numerico());
					radicarSalida.setDocumentalTipoCodigo(documentalTipo.getDocumento_tipo());
				}
				
				if (tramite != null) {
					radicarSalida.setTramiteId(0);		
					radicarSalida.setTramiteCodigo(tramite.getIdPostal());		
				}
				
				if (dependenciaAsignada != null) {
					radicarSalida.setDependenciaAsignadaId(Long.valueOf(dependenciaAsignada.getCodigo()));
					radicarSalida.setDependenciaAsignadaNombre(dependenciaAsignada.getNombreDependencia());					
				}
				
				if (serie != null) {
					radicarSalida.setCodigoSerie(serie.getCodigo().toString());
					radicarSalida.setNombreSerie(serie.getNombre());					
				}
				
				if (subserie != null) {
					radicarSalida.setCodigoSubSerie(subserie.getCodigo().toString());
					radicarSalida.setNombreSubSerie(subserie.getNombre());					
				}
				

			}
						
		} catch (Exception e) {
			SimpleLogger.setError("Ocurrió un error en Postal_RadicarInterna.mapRequest ", e);
			radicarSalida = null;
		}
		return radicarSalida;
	}
	
	@Override
	protected SMessage handleReturn_WhenErr( Either<Exception, Boolean> master_Result) {
		String message = String.format("PipelineSolicitud / sincronizar (error) : %s" , master_Result.left().getMessage());
		logger.warn(message);
		return new SMessage(false, message ); // se solicita se escriba "true" siempre
	}
	
	Either<Exception, RadicacionSalida> llenarDummy () {
		
		RadicacionSalida radicarSalida = new RadicacionSalida();
		
		radicarSalida.setAnexosFisicos("1");
		radicarSalida.setAplicaCiudadCodigo(1);
		radicarSalida.setAplicaDepartamentoCodigo(11);
		radicarSalida.setAplicaDireccion("CALLE 1 NO. 62 35");
		radicarSalida.setAplicaEmail("JUANPA@SUPERSOCIEDADES.GOV.CO");
		radicarSalida.setAplicaIdentificacion("999999999");
		radicarSalida.setAplicaNombre("SOCIEDAD DE PRUEBA");
		radicarSalida.setAplicaPaisCodigo(80);
		radicarSalida.setAplicaTelefono("2222222");
		radicarSalida.setAplicaTipoIdentificacionId(5);
		radicarSalida.setAplicaTipoIdentificacionNombre("NIT");
		radicarSalida.setCodigoMedioEnvio(2);
		radicarSalida.setCodigoTipoSeguridad("ABIERTA");
		radicarSalida.setCuadernoTipoId(30292457);
		radicarSalida.setDependenciaId(400);
		radicarSalida.setDependenciaNombre("DELEGATURA PARA  PROCEDIMIENTOS DE INSOLVENCIA");
		radicarSalida.setDocumentalTipoId(8880);
		radicarSalida.setEntregaFisica(false);
		radicarSalida.setExtensionArchivo(".pdf");
		radicarSalida.setFoliosNumero(7);
		radicarSalida.setLoginUsuario("SHidvegi");
		radicarSalida.setParticularCiudadCodigo(1);
		radicarSalida.setParticularDepartamentoCodigo(11);
		radicarSalida.setParticularDireccion("CALLE 1 NO. 62 35");
		radicarSalida.setParticularEmail("JUANPA@SUPERSOCIEDADES.GOV.CO");
		radicarSalida.setParticularIdentificacion("999999999");
		radicarSalida.setParticularNombre("NO EXISTE NO EXISTE");
		radicarSalida.setParticularPaisCodigo(80);
		radicarSalida.setParticularTelefono("1234567");
		radicarSalida.setParticularTipoIdentificacionId(1);
		radicarSalida.setParticularTipoIdentificacionNombre("CÉDULA");
		radicarSalida.setRadicacionAnterior("");
		radicarSalida.setReferenciaExterna("2020-INS-1475");
		radicarSalida.setTramiteId(370786420);
		radicarSalida.setDependenciaAsignadaId(400L);
		radicarSalida.setDependenciaAsignadaNombre("DELEGATURA PARA  PROCEDIMIENTOS DE INSOLVENCIA");
		radicarSalida.setFuncionarioAsignadoId("53014244");
		radicarSalida.setFuncionarioId("53014244");
		
		return Either.right(radicarSalida);
	}

}
