package com.osmosyscol.datasuite.near.webdata;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

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

import org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionInterna;

import com.osmosyscol.datasuite.near.servicios.IPostalConfig;
import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.near.servicios.radica.LadoLocal;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto2;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.SolicitudEnrolamiento;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class Postal_RadicarInterna extends Abstract_Postal_Envio_Principal {

	@Override
	public String getPuntoInteraccion() {
		return IPostalInteraccion.PUNTOINTERACCION_RADICACION_AUTOOFICIO;
	}

	@Override
	public boolean stopIfErrorOnSync() {
		return true;
	}

	@Override
	public Either<Exception, Boolean> mapOutputPayload(Integer id_carga,
			Either<Exception, Object> serviceResponse) {
		// TODO Auto-generated method stub
		return LadoLocal.getInstance().sync_UsingRadicarInterna(id_carga, serviceResponse);
	}

	@Override
	public Either<Exception, Map<String, Object>> mapInputPayload(Integer id_carga) {
		
		//Either<Exception,RadicacionInterna> infoSource = llenarDummy();
		Either<Exception,RadicacionInterna> infoSource = obtenerInformacionRadicacionInterna(id_carga);
		
		if( infoSource.isLeft() ) {
			return Either.left( infoSource.left() ); 
		}
		
		Map<String,Object> payload = new HashMap<String,Object>(); // Stream.of
		
		payload.put(IPostalInteraccion.PARAM_CORRELATION_ID1, id_carga);
		payload.put(IPostalInteraccion.PARAM_SOLICITUD, infoSource.right() );
		return Either.right(payload);
	}
	
	@Override
	public Either<Exception, Object> ladoRemoto_SyncMaster(Integer id_carga,
			Either<Exception, Map<String, Object>> payloadSource) {
		
		return LadoRemoto2.getInstance().sync_RadicarInterna(id_carga, payloadSource.right());
		
	}
	
	public Either<Exception, RadicacionInterna> obtenerInformacionRadicacionInterna (Integer id_carga) {
		RadicacionAutoOficio solicitudRadicacionAutoOficio = RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficio(id_carga);
		if( solicitudRadicacionAutoOficio == null ) {
			String m = String.format("No se encuentra radicacion con id: %s" , id_carga);
			SimpleLogger.setWarn(m);
			return Either.left(new Exception(m));
		}
		RadicacionInterna informacionRadicacionInterna = mapRequest (solicitudRadicacionAutoOficio);
		
		if( informacionRadicacionInterna == null ) {
			String m = String.format("Error al generar la informacion del mensaje con id: %s" , id_carga);
			SimpleLogger.setWarn(m);
			return Either.left(new Exception(m));
		}
		
		return Either.right(informacionRadicacionInterna);
		
	}
	
	public RadicacionInterna mapRequest (RadicacionAutoOficio radicacion) {
		RadicacionInterna radicarInterna = null;
		
		try {
			if (radicacion != null) {
				radicarInterna = new RadicacionInterna();
				
				EnrolamientoCliente deudor = radicacion.getDeudor();
				APVistaDependencias dependencia = radicacion.getDependencia();
				APVistaDependencias dependenciaAsignada = radicacion.getDependenciaAsignada();
				APVistaDependencias corresponsalDependencia = radicacion.getCorresponsalDependencia();
				APVistaMedioEnvio codigoMedioEnvio = radicacion.getCodigoMedioEnvio();
				APVistaSeguridadTipo codigoTipoSeguridad = radicacion.getCodigoTipoSeguridad();
				APVistaDocumentoTipo documentalTipo = radicacion.getDocumentalTipo();
				APVistaCuaderno cuadernoTipo = radicacion.getCuadernoTipo();
				APVistaTramite tramite = radicacion.getTramite();
				APVistaSerie serie = radicacion.getSerie(); 
				APVistaSubserie subserie = radicacion.getSubserie(); 
				
				if (radicacion.getAnexosFisicos() != null && radicacion.getAnexosFisicos() > 0) {
					radicarInterna.setAnexosFisicos(radicacion.getAnexosFisicos().toString());
				}
				radicarInterna.setEntregaFisica(BooleanUtils.toBooleanObject(radicacion.getEntregaFisica()));
				radicarInterna.setExtensionArchivo(radicacion.getExtensionArchivo());
				radicarInterna.setFoliosNumero(radicacion.getFoliosNumero());
				radicarInterna.setLoginUsuario(radicacion.getLoginUsuario());
				radicarInterna.setRadicacionAnterior(radicacion.getRadicacionAnterior());
				radicarInterna.setReferenciaExterna(radicacion.getReferenciaExterna());
				
				String propiedad_funcionario = getPuntoInteraccion() + "." + IPostalInteraccion.SVC_FUNCIONARIO_ID;
				String funcionarioId = ParametrosInicio.getProperty(propiedad_funcionario);
				
				if (radicacion.getFuncionarioAsignadoId() != null) {
					radicarInterna.setFuncionarioAsignadoId(radicacion.getFuncionarioAsignadoId());	
					radicarInterna.setFuncionarioId(radicacion.getFuncionarioAsignadoId());	
				} else {
					radicarInterna.setFuncionarioAsignadoId(funcionarioId);
					radicarInterna.setFuncionarioId(funcionarioId);
				}
				
//				if (radicacion.getFuncionarioId() != null) {
//					radicarInterna.setFuncionarioId(radicacion.getFuncionarioId());					
//				} else {
//					radicarInterna.setFuncionarioId(funcionarioId);
//				}
				
				
				if (deudor != null) {		
					
					radicarInterna.setAplicaDireccion(deudor.getDireccion());
					radicarInterna.setAplicaEmail(deudor.getCorreo_electronico());
					radicarInterna.setAplicaIdentificacion(deudor.getNumero_identificacion());
					radicarInterna.setAplicaNombre(deudor.getRazon_social());
					
					radicarInterna.setParticularDireccion(deudor.getDireccion());
					radicarInterna.setParticularEmail(deudor.getCorreo_electronico());
					radicarInterna.setParticularIdentificacion(deudor.getNumero_identificacion());
					radicarInterna.setParticularNombre(deudor.getRazon_social());
					
					String telefono = ParametrosInicio.getProperty(getPuntoInteraccion() + "." + IPostalInteraccion.SVC_TELEFONO);
					
					if (deudor.getTelefono() != null) {
						radicarInterna.setAplicaTelefono(deudor.getTelefono());
						radicarInterna.setParticularTelefono(deudor.getTelefono());
					} else {
						radicarInterna.setAplicaTelefono(telefono);
						radicarInterna.setParticularTelefono(telefono);
					}
					
					
					if (deudor.getTipo_identificacion() != null) {
						radicarInterna.setAplicaTipoIdentificacionId(deudor.getTipo_identificacion().getCodigo_numerico_postal());
						radicarInterna.setAplicaTipoIdentificacionNombre(deudor.getTipo_identificacion().getNombre_postal());
						
						radicarInterna.setParticularTipoIdentificacionId(deudor.getTipo_identificacion().getCodigo_numerico_postal());
						radicarInterna.setParticularTipoIdentificacionNombre(deudor.getTipo_identificacion().getNombre_postal());
					}
					
					if (deudor.getMunicipio_dane_obj() != null) {
						radicarInterna.setAplicaCiudadCodigo(deudor.getMunicipio_dane_obj().getCodigoMunicipioPostal());
						radicarInterna.setParticularCiudadCodigo(deudor.getMunicipio_dane_obj().getCodigoMunicipioPostal());
					}
					
					if (deudor.getDepartamento_dane_obj() != null) {
						radicarInterna.setAplicaDepartamentoCodigo(deudor.getDepartamento_dane_obj().getCodigo_departamento());
						radicarInterna.setParticularDepartamentoCodigo(deudor.getDepartamento_dane_obj().getCodigo_departamento());
						
					}
					
					if (deudor.getPais() != null) {
						radicarInterna.setAplicaPaisCodigo(deudor.getPais().getCodigo_ssoc());	
						radicarInterna.setParticularPaisCodigo(deudor.getPais().getCodigo_ssoc());
					}
					
				}

				if (codigoMedioEnvio != null) {
					radicarInterna.setCodigoMedioEnvio(codigoMedioEnvio.getCodigo().intValue());					
				}
				
				if (codigoTipoSeguridad != null) {
					radicarInterna.setCodigoTipoSeguridad(codigoTipoSeguridad.getSeguridadTipo());					
				}
				
				if (cuadernoTipo != null) {
					radicarInterna.setCuadernoCodigo(cuadernoTipo.getCodigo());
					radicarInterna.setCuadernoTipoId(0);
				}
				
				if (dependencia != null) {
					radicarInterna.setDependenciaId(dependencia.getCodigo().intValue());
					radicarInterna.setDependenciaNombre(dependencia.getNombreDependencia());
				}
				
				if (documentalTipo != null) {
					radicarInterna.setDocumentalTipoId(0);
					radicarInterna.setDocumentalTipoCodigo(documentalTipo.getDocumento_tipo());
				}
				
				if (tramite != null) {
					radicarInterna.setTramiteId(0);		
					radicarInterna.setTramiteCodigo(tramite.getIdPostal());		
				}
				
				if (corresponsalDependencia != null) {
					radicarInterna.setCorresponsal_Dependencia_Id(Long.valueOf(corresponsalDependencia.getCodigo()));
					radicarInterna.setCorresponsal_Dependencia_Nombre(corresponsalDependencia.getNombreDependencia());					
				}
				
				if (dependenciaAsignada != null) {
					radicarInterna.setDependenciaAsignadaId(Long.valueOf(dependenciaAsignada.getCodigo()));
					radicarInterna.setDependenciaAsignadaNombre(dependenciaAsignada.getNombreDependencia());					
				}
				
				if (serie != null) {
					radicarInterna.setNombreSerie(serie.getNombre());
					radicarInterna.setCodigoSerie(serie.getCodigo().toString());					
				}
				
				if (subserie != null) {
					radicarInterna.setNombreSubSerie(subserie.getNombre());
					radicarInterna.setCodigoSubSerie(subserie.getCodigo().toString());				
				}

			}
						
		} catch (Exception e) {
			SimpleLogger.setError("Ocurrió un error en Postal_RadicarInterna.mapRequest ", e);
			radicarInterna = null;
		}
		return radicarInterna;
		
	}
	
	@Override
	protected SMessage handleReturn_WhenErr( Either<Exception, Boolean> master_Result) {
		String message = String.format("PipelineSolicitud / sincronizar (error) : %s" , master_Result.left().getMessage());
		logger.warn(message);
		return new SMessage(false, message ); // se solicita se escriba "true" siempre
	}
	
	public Either<Exception, RadicacionInterna> llenarDummy () {
		
		RadicacionInterna radicarInterna = new RadicacionInterna();
		
		radicarInterna.setAnexosFisicos("1");
		radicarInterna.setAplicaCiudadCodigo(1);
		radicarInterna.setAplicaDepartamentoCodigo(11);
		radicarInterna.setAplicaDireccion("CALLE 1 NO. 62 35");
		radicarInterna.setAplicaEmail("JUANPA@SUPERSOCIEDADES.GOV.CO");
		radicarInterna.setAplicaIdentificacion("999999999");
		radicarInterna.setAplicaNombre("SOCIEDAD DE PRUEBA");
		radicarInterna.setAplicaPaisCodigo(80);
		radicarInterna.setAplicaTelefono("2222222");
		radicarInterna.setAplicaTipoIdentificacionId(5);
		radicarInterna.setAplicaTipoIdentificacionNombre("NIT");
		radicarInterna.setCodigoMedioEnvio(10);
		radicarInterna.setCodigoTipoSeguridad("ABIERTA");
		radicarInterna.setCuadernoTipoId(30292457);
		radicarInterna.setDependenciaId(400);
		radicarInterna.setDependenciaNombre("DELEGATURA PARA  PROCEDIMIENTOS DE INSOLVENCIA");
		radicarInterna.setDocumentalTipoId(81399);
		radicarInterna.setEntregaFisica(false);
		radicarInterna.setExtensionArchivo(".pdf");
		radicarInterna.setFoliosNumero(8);
		radicarInterna.setLoginUsuario("SHidvegi");
		radicarInterna.setParticularCiudadCodigo(0);
		radicarInterna.setParticularDepartamentoCodigo(0);
		radicarInterna.setParticularDireccion("");
		radicarInterna.setParticularEmail("");
		radicarInterna.setParticularIdentificacion("");
		radicarInterna.setParticularNombre("");
		radicarInterna.setParticularPaisCodigo(0);
		radicarInterna.setParticularTelefono("");
		radicarInterna.setParticularTipoIdentificacionId(0);
		radicarInterna.setParticularTipoIdentificacionNombre("");
		radicarInterna.setRadicacionAnterior("");
		radicarInterna.setReferenciaExterna("2020-INS-1475");
		radicarInterna.setTramiteId(340662442);
		radicarInterna.setCorresponsal_Dependencia_Id(4151L);
		radicarInterna.setCorresponsal_Dependencia_Nombre("ARCHIVO APOYO JUDICIAL");
		radicarInterna.setDependenciaAsignadaId(400L);
		radicarInterna.setDependenciaAsignadaNombre("DELEGATURA PARA  PROCEDIMIENTOS DE INSOLVENCIA");
		radicarInterna.setFuncionarioAsignadoId("0");
		radicarInterna.setFuncionarioId("53014244");
		
		return Either.right(radicarInterna);
	}

	
}
