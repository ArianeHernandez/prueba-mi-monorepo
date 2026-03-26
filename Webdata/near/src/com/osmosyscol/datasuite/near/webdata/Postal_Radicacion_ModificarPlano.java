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
import com.osmosyscol.datasuite.mein.dtos.APVistaTramite;
import com.osmosyscol.datasuite.mein.dtos.DepartamentoDane;
import com.osmosyscol.datasuite.mein.dtos.EnrolamientoCliente;
import com.osmosyscol.datasuite.mein.dtos.MunicipioDane;
import com.osmosyscol.datasuite.mein.dtos.ParticularPostal;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.mein.servicios.RadicacionAutoOficioServicio;

import org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionSalida;
import org.tempuri.Radicacion_ModificarPlano;

import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.near.servicios.radica.LadoLocal;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto2;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.datasuite.webdata.logica.servicios.SolicitudEnrolamientoServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class Postal_Radicacion_ModificarPlano extends Abstract_Postal_Envio_Principal {

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
		return LadoLocal.getInstance().sync_UsingRadicacionModificarPlano(id_carga, serviceResponse);
	}

	@Override
	Either<Exception, Map<String, Object>> mapInputPayload(Integer id_carga) {
		
		//Either<Exception,RadicacionSalida> infoSource = llenarDummy();
		Either<Exception,Radicacion_ModificarPlano> infoSource = obtenerInformacionRadicacionModificarPlano(id_carga);
		
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
	
	public Either<Exception, Radicacion_ModificarPlano> obtenerInformacionRadicacionModificarPlano(Integer id_carga) {
		RadicacionAutoOficio solicitudRadicacionAutoOficio = RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficio(id_carga);
		
		if( solicitudRadicacionAutoOficio == null ) {
			String m = String.format("No se encuentra radicacion con id: %s" , id_carga);
			logger.warn(m);
			return Either.left(new Exception(m));
		}
		
		Radicacion_ModificarPlano informacionRadicacionModificarPlano = mapRequest (solicitudRadicacionAutoOficio);
		
		if( informacionRadicacionModificarPlano == null ) {
			String m = String.format("Error al generar la informacion del mensaje con id: %s" , id_carga);
			logger.warn(m);
			return Either.left(new Exception(m));
		}
		
		return Either.right(informacionRadicacionModificarPlano);
		
	}
	
	public Radicacion_ModificarPlano mapRequest (RadicacionAutoOficio radicacion) {
		RadicacionPlano radicacionPlano = null;
		
		try {
			if (radicacion != null) {
				radicacionPlano = new RadicacionPlano();
				
				EnrolamientoCliente deudor = radicacion.getDeudor();
				APVistaDependencias dependencia = radicacion.getDependencia();
				APVistaDependencias dependenciaAsignada = radicacion.getDependenciaAsignada();
				APVistaMedioEnvio codigoMedioEnvio = radicacion.getCodigoMedioEnvio();
				APVistaSeguridadTipo codigoTipoSeguridad = radicacion.getCodigoTipoSeguridad();
				APVistaDocumentoTipo documentalTipo = radicacion.getDocumentalTipo();
				APVistaCuaderno cuadernoTipo = radicacion.getCuadernoTipo();
				APVistaTramite tramite = radicacion.getTramite();
				
				radicacionPlano.setAnexosFisicos(Objects.toString(radicacion.getAnexosFisicos(), "0"));
				radicacionPlano.setEntregaFisica(BooleanUtils.toBooleanObject(radicacion.getEntregaFisica()));
//				radicacionModificarPlano.setExtensionArchivo(radicacion.getExtensionArchivo());
				radicacionPlano.setFolios(radicacion.getFoliosNumero());
//				radicacionModificarPlano.setLoginUsuario(radicacion.getLoginUsuario());
				radicacionPlano.setRadicacionAnteriorNumero(radicacion.getRadicacionAnterior());
				radicacionPlano.setReferenciaExterna(radicacion.getReferenciaExterna());
				
				String propiedad_funcionario = getPuntoInteraccion() + "." + IPostalInteraccion.SVC_FUNCIONARIO_ID;
				String funcionarioId = ParametrosInicio.getProperty(propiedad_funcionario);
				
				if (radicacion.getFuncionarioAsignadoId() != null) {
					radicacionPlano.setFuncionarioAsignado_Id(radicacion.getFuncionarioAsignadoId());					
				} else {
					radicacionPlano.setFuncionarioAsignado_Id(funcionarioId);
				}
				
				if (radicacion.getFuncionarioId() != null) {
					radicacionPlano.setFuncionario_Id(radicacion.getFuncionarioId());					
				} else {
					radicacionPlano.setFuncionario_Id(funcionarioId);
				}
				
				if (deudor != null) {	
					Map<String,Object> rep_legal = SolicitudEnrolamientoServicio.getInstance().obtenerEnrolamientoRepLegal(deudor.getDatos_representante());
					
					radicacionPlano.setAplicaA_Direccion(deudor.getDireccion());
					radicacionPlano.setCorresponsal_Particular_Direccion(deudor.getDireccion());
					radicacionPlano.setAplicaA_Email(deudor.getCorreo_electronico());
					radicacionPlano.setAplicaA_Identificacion(deudor.getNumero_identificacion());
					radicacionPlano.setAplicaA_Nombre(deudor.getRazon_social());
					
					String telefono = ParametrosInicio.getProperty(getPuntoInteraccion() + "." + IPostalInteraccion.SVC_TELEFONO);
					
					if (deudor.getTelefono() != null) {
						radicacionPlano.setAplicaA_Telefono(deudor.getTelefono());
						radicacionPlano.setCorresponsal_Particular_Telefono(deudor.getTelefono());
					} else {
						radicacionPlano.setAplicaA_Telefono(telefono);
						radicacionPlano.setCorresponsal_Particular_Telefono(telefono);
					}
					
					
					if (deudor.getTipo_identificacion() != null) {
						radicacionPlano.setAplicaA_TipoIdentificacion_Id(new Long(deudor.getTipo_identificacion().getCodigo_numerico_postal()));
						radicacionPlano.setAplicaA_TipoIdentificacion_Nombre(deudor.getTipo_identificacion().getNombre_postal());						
					}
					
					if (deudor.getMunicipio_dane_obj() != null) {
						radicacionPlano.setAplicaA_Ciudad_Codigo_CiudadId_CiudadCodigo(new Long(deudor.getMunicipio_dane_obj().getCodigoMunicipioPostal()));
						radicacionPlano.setCorresponsal_Particular_Ciudad_Codigo_CiudadCodigo(new Long(deudor.getMunicipio_dane_obj().getCodigoMunicipioPostal()));
					}
					
					if (deudor.getDepartamento_dane_obj() != null) {
						radicacionPlano.setAplicaA_Ciudad_Codigo_CiudadId_DepartamentoCodigo(new Long(deudor.getDepartamento_dane_obj().getCodigo_departamento()));
						radicacionPlano.setCorresponsal_Particular_Ciudad_Codigo_DepartamentoCodigo(new Long(deudor.getDepartamento_dane_obj().getCodigo_departamento()));
						
					}
					
					if (deudor.getPais() != null) {
						radicacionPlano.setAplicaA_Ciudad_Codigo_CiudadId_PaisCodigo(new Long(deudor.getPais().getCodigo_ssoc()));	
						radicacionPlano.setCorresponsal_Particular_Ciudad_Codigo_PaisCodigo(new Long(deudor.getPais().getCodigo_ssoc()));
					}
					
					if (rep_legal != null) {
						
						String email = (String)rep_legal.get("EMAIL");
						String identificacion = (String)rep_legal.get("IDENTIFICACION");
						String nombre = (String)rep_legal.get("NOMBRE");
						String apellido = (String)rep_legal.get("APELLIDOS");
						Integer tipo_identificacion_id = Integer.parseInt((String)rep_legal.get("TIPO_IDENTIFICACION_ID"));
						String tipo_identificacion_nombre = (String)rep_legal.get("TIPO_IDENTIFICACION_NOMBRE");
						
						radicacionPlano.setCorresponsal_Particular_Email(email);
						radicacionPlano.setCorresponsal_Particular_Identificacion(identificacion);
						radicacionPlano.setCorresponsal_Particular_Nombre(nombre + " " + apellido);
						radicacionPlano.setCorresponsal_Particular_TipoIdentificacion_Id(new Long(tipo_identificacion_id));
						radicacionPlano.setCorresponsal_Particular_TipoIdentificacion_Nombre(tipo_identificacion_nombre);						
							
					}
				}

				if (codigoMedioEnvio != null) {
					radicacionPlano.setMedioDeEnvio_Id(codigoMedioEnvio.getId_vista());
					radicacionPlano.setMedioDeEnvio_Codigo(codigoMedioEnvio.getCodigo());
					radicacionPlano.setMedioDeEnvio_Nombre(codigoMedioEnvio.getMedio_envio());
				}
				
				if (codigoTipoSeguridad != null) {
					radicacionPlano.setTipoSeguridad_Id(codigoTipoSeguridad.getId_vista());
					radicacionPlano.setTipoSeguridad_Codigo(codigoTipoSeguridad.getCodigoAlfanumerico());
					radicacionPlano.setTipoSeguridad_Nombre(codigoTipoSeguridad.getSeguridadTipo());
				}
				
				if (cuadernoTipo != null) {
					radicacionPlano.setTipoCuaderno_Id(cuadernoTipo.getId_vista());
					radicacionPlano.setTipoCuaderno_Codigo(cuadernoTipo.getCodigo());
					radicacionPlano.setTipoCuaderno_Nombre(cuadernoTipo.getCuadernoTipo());
				}
				
				if (dependencia != null) {
					radicacionPlano.setDependencia_Id(dependencia.getCodigo());
					radicacionPlano.setDependencia_Nombre(dependencia.getNombreDependencia());
				}
				
				if (documentalTipo != null) {
					radicacionPlano.setTipoDocumental_Id(new Long(documentalTipo.getCodigo_numerico()));
					radicacionPlano.setTipoDocumental_Codigo(documentalTipo.getCodigo_alfanumerico());
					radicacionPlano.setTipoDocumental_Nombre(documentalTipo.getDocumento_tipo());
					radicacionPlano.setTipoDocumentalConsecutivo(""); // TODO: Revisar valor
				}
				
				if (tramite != null) {
					radicacionPlano.setTramite_Id(tramite.getCodigo());
					radicacionPlano.setTramite_Nombre(tramite.getNombreTramite());
					radicacionPlano.setTramite_Proceso_Codigo(tramite.getCodigo());
					
				}
				
				if (dependenciaAsignada != null) {
					radicacionPlano.setDependenciaAsignada_Dependencia_Id(new Long(dependenciaAsignada.getCodigo()));
					radicacionPlano.setDependenciaAsignada_Dependencia_Nombre(dependenciaAsignada.getNombreDependencia());					
				}

			}
						
		} catch (Exception e) {
			SimpleLogger.setError("Ocurrió un error en Postal_RadicarInterna.mapRequest ", e);
			radicacionPlano = null;
		}
		Radicacion_ModificarPlano radicacion_ModificarPlano = new Radicacion_ModificarPlano();
		radicacion_ModificarPlano.setRequest(radicacionPlano);
		return radicacion_ModificarPlano;
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
