package com.osmosyscol.datasuite.near.servicios.radica;

import java.util.Collection;
import java.util.List;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.RespuestaRequerimiento;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.servicios.RegimenInsolvenciaServicio;
import com.osmosyscol.datasuite.mein.servicios.RespuestaRequerimientoServicio;

import org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass;
import org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna;

import com.osmosyscol.datasuite.near.servicios.IPostalInteraccion;
import com.osmosyscol.datasuite.near.servicios.InfoRadicadoLocalServicio;
import com.osmosyscol.datasuite.near.servicios.InfoRespuestaRequerimientoLocalServicio;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.datasuite.near.utils.ObjectUtils;

public class LadoLocal {
	
	private static LadoLocal _instance = new LadoLocal(); 
	private LadoLocal(){}
	public static LadoLocal getInstance() {
		return _instance;
	}
	
	static final Logger logger = LoggerFactory.getLogger(LadoLocal.class);

	public Either<Exception,Collection<ArchivoAdjunto>> leeAdjuntos(Integer id_carga, boolean internas ) {
		InfoRadicadoLocalServicio _service = InfoRadicadoLocalServicio.getInstance();
		Collection<ArchivoAdjunto> result =  _service.buscarSetArchivosLocal(id_carga, internas);
		
		if(null == result || result.size() == 0 ) {
			String message = String.format("no se han encontrado archivos con el idCarga %d", id_carga);
			return Either.left( new Exception( message));
		}
		return Either.right( result );
	}
	
	public Either<Exception, Collection<ArchivoAdjunto>> leeAdjuntos(Integer id_carga, String estado, boolean internos,	Integer filterIdTipoArchivo) {
		// TODO Auto-generated method stub
		Collection<ArchivoAdjunto> result = ArchivoAdjuntoServicio.getInstance().obtenerAdjuntosSegunTipologia(id_carga, estado, internos, filterIdTipoArchivo );
		return getOrError(result, String.format("no se han encontrado archivos con el idCarga %d", id_carga));
	}
	
	
	protected <T extends Collection<?> > Either<Exception, T> getOrError(T collection, String message) {
		
		if(null == collection || collection.size() == 0 ) {
			return Either.left( new Exception( message));
		}
		return Either.right( collection );
	}

	protected Either<Exception, String> getOrError(String radicadoPostal, String message) {
		if( StringUtils.isBlank(radicadoPostal)) {
			return Either.left(new Exception( message));
		}
		return Either.right(radicadoPostal);
	}

	
	
	
	public Long leeAdjuntos_Count(Integer id_carga) {
		InfoRadicadoLocalServicio _service = InfoRadicadoLocalServicio.getInstance();
		return _service.buscarSetArchivosLocal_Count(id_carga);
	}
	
	
	public SolicitudNearSociedad leeMaster(Integer id_carga) {
		InfoRadicadoLocalServicio _service = InfoRadicadoLocalServicio.getInstance();
		SolicitudNearSociedad result = _service.buscarSolicitudLocal(id_carga);
//		if( logger.isDebugEnabled() ) {
			logger.debug(" + leeMaster / solicitudLocal: {}", ObjectUtils.toJson(result));
//		}
		return result;
	}
	
	public RegimenInsolvencia leeMasterRegimenInsolvencia(Integer id_carga) { 
//		RegimenInsolvencia result = RegimenInsolvenciaServicio.getInstance().buscarSolicitudLocalRegimen(id_carga); 
		RegimenInsolvencia result = RegimenInsolvenciaServicio.getInstance().obtenerInfoRegimenInsolvenciaPorIdCarga(id_carga); 
//		if( logger.isDebugEnabled() ) {
			logger.debug(" + leeMaster / solicitudLocal: {}", ObjectUtils.toJson(result));
//		}
		return result;
	}
	
	public RespuestaRequerimiento leeMasterRtaRequerimiento(Integer id_carga) {
		RespuestaRequerimientoServicio _service = RespuestaRequerimientoServicio.getInstance();
		RespuestaRequerimiento result = _service.obtenerRespuestaRequerimientoCompleto(id_carga);
		if( logger.isDebugEnabled() ) {
			logger.debug(" + leeMaster / solicitudLocal: {}", ObjectUtils.toJson(result));
		}
		return result;
	}
	
	public Either<Exception, String> leeRadicadoPostal_UsingRespuestaReq(Integer id_carga) {
		String radicadoPostal = InfoRespuestaRequerimientoLocalServicio.getInstance().buscarInfoLocal_RadicadoPostal(id_carga);
		return getOrError(radicadoPostal, String.format("no se ha encontrado radicado en postal con idCarga %d", id_carga));
	}
	
	public Either<Exception,String> leeRadicadoPostal_UsingMaster(Integer id_carga) {
		String radicadoPostal = InfoRadicadoLocalServicio.getInstance().buscarSolicitudLocal_RadicadoPostal(id_carga);
		return getOrError(radicadoPostal, String.format("no se ha encontrado radicado en postal con idCarga %d", id_carga));
	}
	public Either<Exception, String> leeRadicadoPostal_UsingArchivoAdjunto( Integer id_carga, Integer filterIdTipoArchivo) {
		List<String> radicadosPostal = ArchivoAdjuntoServicio.getInstance().obtenerRadicadoAdjuntosSegunTipologia(id_carga, "S", true, filterIdTipoArchivo );
		
		if(null == radicadosPostal || radicadosPostal.size() == 0 ) {
			String message = String.format("no se ha encontrado radicado en postal con idCarga %d de tipo %d", id_carga, filterIdTipoArchivo);
			return Either.left( new Exception( message));
		}
		return Either.right( radicadosPostal.get(0) );
	}
	
	
	/*
	public String buscarArchivoAdjuntoLocal_RadicadoPostal(Integer idCarga, String idRadicadoPostal, String puntoInteraccion ) {
		boolean filterInterno = true;
		Integer filterIdTipoArchivo = IPostalConfig.getInstance(puntoInteraccion).getTipoArchivoOfPuntoInteraccion().right();

		
		// Carga carga = CargaServicio.getInstance().obtenerCarga(idCarga);
		// como se hace el populate de este objeto?
		String radicadoPostal = DS_SqlUtils.queryForObject(String.class, 
				"select $solicitud near sociedad.numero radicado postal$ " + 
				"  from $solicitud near sociedad$ " +
				" where idcarga = " + idCarga);
		
		// solicitud.setIdcarga(idCarga);
		
		return radicadoPostal;
	}	
	*/	
	
	
	
	public Either<Exception, Boolean> sync_UsingMaster(Integer idCarga, Either<Exception, String> idRadicadoPostal) {
		String _radicado = getOrElse(idRadicadoPostal,IPostalInteraccion.CODIGO_ERROR_RADICADO);
		InfoRadicadoLocalServicio _service = InfoRadicadoLocalServicio.getInstance();
		Boolean localResult =_service.sincronizarSolicitudLocalConRadicadoPostal(idCarga, _radicado); 
		logger.info("SYNC: sincronizarLocal - usando solicitud (end) [resultado={}, id_carga={}, _radicado={}] ", localResult, idCarga, _radicado );
		return Either.right(localResult);

	}
	
	public Either<Exception, Boolean> sync_UsingAdjuntoActivo(Integer id_carga, Either<Exception, String> idRadicadoPostal, String puntoInteraccion) {
		String _radicado = getOrElse(idRadicadoPostal, IPostalInteraccion.CODIGO_ERROR_RADICADO);
		InfoRadicadoLocalServicio _service = InfoRadicadoLocalServicio.getInstance();
		Boolean localResult = _service.sincronizarArchivoAdjuntoLocalConRadicadoPostal(id_carga, _radicado, puntoInteraccion);
		logger.info("SYNC: sincronizarLocal - usando ArchivoAdjuntoInternoActivo (end) [resultado={}, id_carga={}, _radicado={}, puntoInteraccion={}] ", localResult, id_carga, _radicado, puntoInteraccion );
		return Either.right(localResult);
	}

	
	public Either<Exception, Boolean> sync_UsingRespuestaReq(Integer id_carga, Either<Exception, String> idRadicadoPostal) {
		String _radicado = getOrElse(idRadicadoPostal, IPostalInteraccion.CODIGO_ERROR_RADICADO);
		InfoRespuestaRequerimientoLocalServicio _service =  InfoRespuestaRequerimientoLocalServicio.getInstance();
		Boolean localResult = _service.sincronizarRequerimientoLocalConRadicadoPostal(id_carga, _radicado);
		logger.info("SYNC: sincronizarLocal - usando RespuestaReq (end) [resultado={}, id_carga={}, _radicado={}] ", localResult, id_carga, _radicado );
		return Either.right(localResult);
	}
	
	public Either<Exception, Boolean> sync_UsingRadicarInterna (Integer id_carga, Either<Exception, Object> serviceResponse) {
		RespuestaRadicacionInterna respuestaRadicarInterna = (RespuestaRadicacionInterna)serviceResponse.right();
		Boolean resultado = false;
		
		if (respuestaRadicarInterna != null) {
			
			if (!IPostalInteraccion.CODIGO_ERROR_RADICADO.equals(respuestaRadicarInterna.getCodigoDevolucion().toString())) {
				resultado = InfoRadicadoLocalServicio.getInstance().sincronizarRadicacionAutoOficio(id_carga, respuestaRadicarInterna.getNumeroRadicacion());				
			} else {
				SimpleLogger.setError("Error en el servicio postal Radicar Interna - ID Carga: " + id_carga + 
						" Codigo: " + respuestaRadicarInterna.getCodigoDevolucion() +
						" Numero Radicacion: " + respuestaRadicarInterna.getNumeroRadicacion());
			}
			
		} 
		
		return Either.right(resultado);
	}
	
	public Either<Exception, Boolean> sync_UsingRadicarSalida (Integer id_carga, Either<Exception, Object> serviceResponse) {
		IBPMWSDevolucionClass respuestaRadicarSalida = (IBPMWSDevolucionClass)serviceResponse.right();
		Boolean resultado = false;
		
		if (respuestaRadicarSalida != null) {
			
			if (!IPostalInteraccion.CODIGO_ERROR_RADICADO.equals(respuestaRadicarSalida.getCodigoDevolucion().toString().trim())) {
				resultado = InfoRadicadoLocalServicio.getInstance().sincronizarRadicacionAutoOficio(id_carga, respuestaRadicarSalida.getMensajeDevolucion());
				return Either.right(resultado);
			} else {
				SimpleLogger.setError("Error en el servicio postal Radicar Salida - ID Carga: " + id_carga + 
						" Codigo: " + respuestaRadicarSalida.getCodigoDevolucion() +
						" Numero Radicacion: " + respuestaRadicarSalida.getMensajeDevolucion());
				return Either.left(serviceResponse.left());
			}
		} else {
			return Either.left(serviceResponse.left());
		}
	}
	
	public Either<Exception, Boolean> sync_UsingRadicacionModificarPlano (Integer id_carga, Either<Exception, Object> serviceResponse) {
		IBPMWSDevolucionClass respuestaRadicacionModificarPlano = (IBPMWSDevolucionClass)serviceResponse.right();
		Boolean resultado = false;
		
		if (respuestaRadicacionModificarPlano != null) {
			
			if (!IPostalInteraccion.CODIGO_ERROR_RADICADO.equals(respuestaRadicacionModificarPlano.getCodigoDevolucion().toString())) {
				resultado = InfoRadicadoLocalServicio.getInstance().sincronizarRadicacionAutoOficio(id_carga, respuestaRadicacionModificarPlano.getMensajeDevolucion());
			} else {
				SimpleLogger.setError("Error en el servicio postal Radicacion ModificarPlano - ID Carga: " + id_carga + 
						" Codigo: " + respuestaRadicacionModificarPlano.getCodigoDevolucion() +
						" Numero Radicacion: " + respuestaRadicacionModificarPlano.getMensajeDevolucion());
			}
		} 
		
		return Either.right(resultado);
	}
	
	protected String getOrElse(Either<Exception, String> idRadicadoPostal, String whenLeft ) {
		if( idRadicadoPostal.isLeft() ) {
			logger.error("SYNC: sincronizarLocal  (error)", idRadicadoPostal.left() );
		}
		return idRadicadoPostal.getOrElse(whenLeft);
	}
	
	public Either<Exception, String> obtenerRadicado(Integer idCarga, Object serviceResponse) {
	    try {
	        String radicadoPostal;
	        JXPathContext jxServiceResponse = JXPathContext.newContext((IBPMWSDevolucionClass)serviceResponse);
	        Integer codigoDevolucion = (Integer) jxServiceResponse.getValue("codigoDevolucion");
	        if (codigoDevolucion != null && codigoDevolucion != -1) {
	        	SimpleLogger.setInfo("Codigo devolución correcto: " + codigoDevolucion);
	            radicadoPostal = (String) jxServiceResponse.getValue("mensajeDevolucion");
	            SimpleLogger.setInfo("Radicado: " + radicadoPostal);
	            return Either.right(radicadoPostal);
	        } else {
	            SimpleLogger.setInfo("Codigo de devolución no válido: " + codigoDevolucion);
	            return Either.left(new Exception("Código de devolución inválido ."));
	        }
	    } catch (Exception e) {
	        logger.error("Error al procesar respuesta Postal: ", e);
	        return Either.left(e);
	    }
	}


}
