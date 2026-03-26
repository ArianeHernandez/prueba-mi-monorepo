package com.osmosyscol.datasuite.near.servicios;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.htsoft.commons.lang.StringUtils;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.mein.builder.SolicitudFetchBuilder;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.mein.dtos.RegimenInsolvencia;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.mein.servicios.SolicitudNearSociedadServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.near.servicios.radica.LadoRemoto;
import com.osmosyscol.datasuite.near.utils.Either;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class InfoRadicadoLocalServicio {
	
	private static InfoRadicadoLocalServicio _instance = new InfoRadicadoLocalServicio();
	private InfoRadicadoLocalServicio(){}
	
	public static InfoRadicadoLocalServicio getInstance() {
		return _instance;
	}
	
	static final Logger logger = LoggerFactory.getLogger(InfoRadicadoLocalServicio.class);
	

	/**
	 * Cuenta archivos adjuntos atados a determinado idCarga
	 * @param id_carga
	 * @return
	 */
	public Long buscarSetArchivosLocal_Count(Integer id_carga) {
		ArchivoAdjuntoServicio _service = ArchivoAdjuntoServicio.getInstance();
		Boolean internas = true;
		Long cnt = _service.contarArchivosAdjuntosPorCarga(id_carga, internas, null);
		
		return cnt;
	}
	
	/**
	 * asume que los archivos locales a cponline
	 * se encuentran referenciados en s3. (NOT IMPLEMENTED)
	 * @param idCarga
	 * @return
	 */
	public Collection<ArchivoAdjunto> buscarSetArchivosLocal(Integer idCarga, boolean internas ) {
		
		ArchivoAdjuntoServicio _service = ArchivoAdjuntoServicio.getInstance();
		Collection<ArchivoAdjunto> archivos = _service.obtenerArchivosAdjuntosPorCarga(idCarga, internas, null);
		return archivos;
	}
	
	/**
	 * Hace consulta de solicitud.
	 * Por limitaciones en la personalización del orm, 
	 * las relaciones fetch se deben hacer manualmente (TODO: revisar: no deberia ser así porque mybatis lo permite)
	 * 
	 * @param idCarga
	 * @return
	 */
	public SolicitudNearSociedad buscarSolicitudLocal(Integer idCarga) {
		
		SolicitudNearSociedad solicitud = null;
		
		try {
			solicitud = SolicitudNearSociedadServicio.getInstance().obtenerSolicitudNearSociedadPorCarga(idCarga);
			
		}catch (Exception e) {
			SimpleLogger.setError("Error al consultar solicitud con id:" + idCarga + ". Descripción: ", e);
		}
		
		return solicitud;
	}

	public String buscarSolicitudLocal_RadicadoPostal(Integer idCarga) {

		String radicadoPostal = "";
		
		if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(obtenerNombreEstructuraFormatoPorCarga(idCarga))) {
			radicadoPostal = DS_SqlUtils.queryForObject(String.class, 
					"select $regimen de insolvencia.numero de radicado$ " + 
							"  from $regimen de insolvencia$ " +
							" where idcarga = " + idCarga);
		} else {
			radicadoPostal = DS_SqlUtils.queryForObject(String.class, 
					"select $solicitud near sociedad.numero radicado postal$ " + 
							"  from $solicitud near sociedad$ " +
							" where idcarga = " + idCarga);
		}
		
		return radicadoPostal;
	}
	
	
	
	public Boolean sincronizarSolicitudLocalConRadicadoPostal(Integer idCarga, String idRadicadoPostal) {
		
		RDServicio.verificarData();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("radicado_postal", StringUtils.trimToNull(idRadicadoPostal));

		Boolean updated = false; 
				
		if (Constantes.ESTRUCTURA_REGIMEN_INSOLVENCIA.equals(obtenerNombreEstructuraFormatoPorCarga(idCarga))) {
			updated = DS_SqlUtils.update(
					"update $regimen de insolvencia$" +
							" set $regimen de insolvencia.numero de radicado$=#radicado_postal#" +
							" WHERE IDCARGA = " + idCarga, params );
		} else {
			updated = DS_SqlUtils.update(
					"update $solicitud near sociedad$" +
							" set $solicitud near sociedad.numero radicado postal$=#radicado_postal#" +
							" WHERE IDCARGA = " + idCarga, params );
		}
		
		SimpleLogger.setInfo("InfoRadicadoLocal.sincronizarSolicitudLocalConRadicadoPostal: Solicitud " + idCarga + " Actualizacion radicado " + updated);
		
		updated = CargaServicio.getInstance().actualizarFechaLiberacion(idCarga);
		
		SimpleLogger.setInfo("InfoRadicadoLocal.sincronizarSolicitudLocalConRadicadoPostal: Solicitud " + idCarga + " Actualizacion fecha radicado " + updated);
		
		return updated;
	}

	public Boolean sincronizarArchivoAdjuntoLocalConRadicadoPostal( Integer id_carga, String idRadicadoPostal, String puntoInteraccion) {
		
		boolean filterInterno = true;
		Integer filterIdTipoArchivo = IPostalConfig.getInstance(puntoInteraccion).getTipoArchivoOfPuntoInteraccion().right();

		Boolean updated = ArchivoAdjuntoServicio.getInstance().actualizarRadicadoAdjuntosSegunTipologia(id_carga,idRadicadoPostal, "R", filterInterno, filterIdTipoArchivo );
		
		logger.info("SYNC: sincronizarArchivoAdjuntoLocalConRadicadoPostal / actualizarRadicadoAdjuntos (end) [resultado={}, id_carga={}, _radicado={}, estado={},interno={},tipoArchivo={}] ", updated , id_carga,idRadicadoPostal, "R", filterInterno, filterIdTipoArchivo );
		return updated;
	}
	
	public Boolean sincronizarRadicacionAutoOficio (Integer id_carga, String numero_radicado) {
		Boolean update = false;
		
		try {
			
			Date fechaRadicado = new Date();
			
			String sql = "update $radicacion auto oficio$ " +
							" set $radicacion auto oficio.numero radicado$ = #numero_radicado#," +
							" $radicacion auto oficio.fecha radicado$ = #fecha_radicado#" +
							" where idcarga = $id_carga$";
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("numero_radicado", numero_radicado);
			params.put("fecha_radicado", fechaRadicado);
			params.put("id_carga", id_carga);
			
			update  = DS_SqlUtils.update(sql, params);
			
		} catch (Exception e) {
			SimpleLogger.setError("Error al sincronizar la radicacion auto oficio", e);
		}
		
		return update;
	}

	public String obtenerNombreEstructuraFormatoPorCarga (Integer id_carga) {
		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
		Formato formato = FormatoServicio.getInstance().obtenerFormato(carga.getId_formato());
		Estructura estructura = EstructuraServicio.getInstance().obtenerEstructura(formato.getId_estructura());
		return estructura.getNombre().toUpperCase(); 
	}
	
	public String obtenerIdFuncionarioPorCarga (Integer id_carga) {
		String sql = "select $radicacion auto oficio.funcionarioid$ from $radicacion auto oficio$ where idcarga = "+ id_carga;
		String idFuncionario = DS_SqlUtils.queryForObject(String.class, sql);
		
		if(null != idFuncionario) return idFuncionario;
		else return "";
	}
	
}
