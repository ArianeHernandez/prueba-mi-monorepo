package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.ArchivoAdjunto;
import com.osmosyscol.datasuite.logica.dto.Liberador;
import com.osmosyscol.datasuite.logica.dto.LiberadorTipoProceso;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.logica.servicios.ArchivoAdjuntoServicio;
import com.osmosyscol.datasuite.logica.servicios.FormatoConfiguracionServicio;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.LiberadorServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ValidacionEstructuraServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.Estado;
import com.osmosyscol.datasuite.webdata.logica.dto.HistorialCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.ValorListaDinamicaCampo;
import com.osmosyscol.datasuite.webdata.logica.dto.VariableLiberacion;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.datasuite.webdata.persistencia.dao.EstadoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class EstadoCargaServicio {

	private static EstadoCargaServicio instance;

	private EstadoCargaServicio() {
	}

	public static EstadoCargaServicio getInstance() {
		if (instance == null) {
			instance = new EstadoCargaServicio();
		}
		return instance;
	}

	private String siguienteEstado(Carga carga) {

		try {

			CargaDao cargaDao = (CargaDao) DaoConfig.getDao(CargaDao.class);
			CargaDao cargaDaoCreadatos = (CargaDao) DaoConfig.getDao(CargaDao.class, 2);

			if (StringUtils.esVacio(carga.getEstado())) {
				carga.setEstado(Constantes.CARGA_ESTADO_BORRADOR);

			} else if (carga.getEstado().equals(Constantes.CARGA_ESTADO_BORRADOR) || carga.getEstado().equals(Constantes.CARGA_ESTADO_REVISION)) {

				if (carga.getId_proceso() == null) {

					if (carga.getFecha_liberacion() == null) {
						carga.setFecha_liberacion(HorarioServicio.getInstance().obtenerFechaActual());
					}

					carga.setEstado(Constantes.CARGA_ESTADO_ENVIO);

				} else {
					Integer id_revision = cargaDao.obtenerSiguienteRevision(carga.getId_carga());
					if (id_revision != null) {
						carga.setEstado(Constantes.CARGA_ESTADO_REVISION);
						carga.setId_revision(id_revision);

					} else {

						carga.setEstado(Constantes.CARGA_ESTADO_LIBERACION);
						carga.setId_revision(null);
					}
				}

			} else if (carga.getEstado().equals(Constantes.CARGA_ESTADO_LIBERACION)) {

				if (carga.getFecha_liberacion() == null) {
					if (carga.getId_tipo_proceso() ==  null){
						if (esAutorizacionAutomatica(carga)) {
							carga.setEstado(Constantes.CARGA_ESTADO_APROBADO);
	
						} else {

							if (requiereArchivosAdjuntoLuegoDeLaLiberacion(carga)) {
								carga.setEstado(Constantes.CARGA_ESTADO_REQUIERE_ADJUNTO);
	
							} else {
								carga.setEstado(Constantes.CARGA_ESTADO_SUBIDO);
							}
						}
					}else {
						carga.setEstado(Constantes.CARGA_ESTADO_ENVIO);
						if (carga.getFecha_liberacion() == null) {
							carga.setFecha_liberacion(HorarioServicio.getInstance().obtenerFechaActual());
						}
					}
				} else {
					carga.setEstado(Constantes.CARGA_ESTADO_ENVIO);
				}

			} else if (carga.getEstado().equals(Constantes.CARGA_ESTADO_REQUIERE_ADJUNTO)) {
				carga.setEstado(Constantes.CARGA_ESTADO_SUBIDO);
			}

			if (carga.getEstado().equals(Constantes.CARGA_ESTADO_ENVIO)) {

				Boolean enHorario = true;
				Integer id_horario = null;
				Boolean horarioOficina = false;
				if (FormatoServicio.getInstance().esHorarioActivo()) {
					Formato formato = FormatoServicio.getInstance().obtenerFormato(carga.getId_formato());
					if (carga.getId_tipo_proceso() == null){
						id_horario = formato.getId_horario();
					}else {
						String id_horario_t = FormatoConfiguracionServicio.getInstance().obtenerConfiguracionFormato(formato.getId_formato(), "HORARIO_LIBERACION").getValor();
						id_horario = new Integer(id_horario_t);
						String horarioOficina_t = FormatoConfiguracionServicio.getInstance().obtenerConfiguracionFormato(formato.getId_formato(), "HORARIO_OFICINA").getValor();
						horarioOficina = StringUtils.esVerdad(horarioOficina_t);
					}
					enHorario = HorarioServicio.getInstance().estaDentroDeHorario(id_horario, carga.getFecha_liberacion(), horarioOficina);
				}

				if (enHorario) {

					if (esAutorizacionAutomatica(carga)) {
						carga.setEstado(Constantes.CARGA_ESTADO_APROBADO);

					} else {

						if (requiereArchivosAdjuntoLuegoDeLaLiberacion(carga)) {
							carga.setEstado(Constantes.CARGA_ESTADO_REQUIERE_ADJUNTO);

						} else {
							carga.setEstado(Constantes.CARGA_ESTADO_SUBIDO);
						}

					}

				}else {
					Date fecha = HorarioServicio.getInstance().calcularSiguienteFecha(id_horario, carga.getFecha_liberacion());
					carga.setFecha_liberacion(fecha);
				}

			}

			if (Constantes.CARGA_ESTADO_SUBIDO.equals(carga.getEstado())) {
				CargaServicio.getInstance().aplicarEstructuras(carga);
				ValidacionEstructuraServicio.getInstance().generarValidacionesNoEnLineaHilo(carga.getId_carga());
			}
			
			// Se llama la funcion aplicar carga que verifica si el formato debe
			// aplicarla, en ese caso se cambia el estado a aplicada
			if (Constantes.CARGA_ESTADO_APROBADO.equals(carga.getEstado())) {
				Boolean rta = CargaServicio.getInstance().aplicarCarga(carga.getId_carga());
				if (rta) {
					carga.setEstado(Constantes.CARGA_ESTADO_APLICADA);
				}
			}

			// Se actualiza el estado en datasuite
			cargaDao.actualizarEstadoCarga(carga);

			// Se actualiza el estado en creadatos
			cargaDaoCreadatos.cambiarEstadoCrea(carga.getId_carga(), carga.getEstado());

			// Si la carga tiene fecha de liberacion se actualiza en creadatos
			cargaDaoCreadatos.actualizarFechaLiberacionCreadatos(carga);

			return carga.getEstado();

		} catch (Exception e) {
			SimpleLogger.setError("Error determinando siguiente estado de la carga", e);
		}

		return null;
	}

	public String siguienteEstado(Integer id_carga, DaoManager daoManager, Integer id_persona) {
		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
		return siguienteEstado(carga, daoManager, id_persona);
	}

	public boolean estadoLiberacionIntermedio(Integer idCarga, List<ValorListaDinamicaCampo> listaValoresDinamicos,  Integer idFormato, Integer idPersona) {
		
		boolean valoresLista = true;
		Carga carga = CargaServicio.getInstance().obtenerCarga(idCarga);

		HistorialCarga historialCarga = new HistorialCarga();
		historialCarga.setEstado_inicial(carga.getEstado());
		
		List<VariableLiberacion> valoresLiberacion = VariableLiberacionServicio.getInstance().obtenerVariablesLiberacionPorCarga(idCarga);
		
		if(valoresLiberacion == null || valoresLiberacion.size()==0 )
			valoresLista = CargaServicio.getInstance().guardarValoresLista(listaValoresDinamicos, idFormato, true);
		
		if(!valoresLista) return true;
		
		carga.setEstado(Constantes.CARGA_ESTADO_VALIDACION_ODG);
		
		CargaDao cargaDao = (CargaDao) DaoConfig.getDao(CargaDao.class);
		CargaDao cargaDaoCreadatos = (CargaDao) DaoConfig.getDao(CargaDao.class, 2);
		
		// Se actualiza el estado en datasuite
		cargaDao.actualizarEstadoCarga(carga);

		// Se actualiza el estado en creadatos
		cargaDaoCreadatos.cambiarEstadoCrea(carga.getId_carga(), carga.getEstado());
		
		cargaDaoCreadatos.actualizarFechaLiberacionCreadatos(carga);
		
		
		historialCarga.setId_carga(carga.getId_carga());
		historialCarga.setEstado(Constantes.CARGA_ESTADO_VALIDACION_ODG);
		historialCarga.setId_persona(idPersona);
		historialCarga.setFecha(HorarioServicio.getInstance().obtenerFechaActual());
		HistorialServicio.getInstance().guardarHistorialCarga(historialCarga);
		
		return true;
	}
	
	/**
	 * Se revisa si el formato de la carga tiene establecido que pasa a estado
	 * APROBADA de forma automatica.
	 */
	private boolean esAutorizacionAutomatica(Carga carga) {
		boolean esAutorizacionAutomatica = false;

		FormatoServicio formatoServicio = FormatoServicio.getInstance();
		Formato formato = formatoServicio.obtenerFormato(carga.getId_formato());
		if(formato!=null){
			if (formato.getAutorizacion_automatica().equals(Constantes.SI)) {
				esAutorizacionAutomatica = true;
			}
		}

		return esAutorizacionAutomatica;
	}

	/**
	 * Se revisa si el formato de la carga tiene establecido que requiere
	 * archivos adjuntos luego de la liberacion
	 * 
	 */
	private boolean requiereArchivosAdjuntoLuegoDeLaLiberacion(Carga carga) {

		FormatoServicio formatoServicio = FormatoServicio.getInstance();
		Formato formato = formatoServicio.obtenerFormato(carga.getId_formato());
		if(formato!=null){
			if (Constantes.SI.equals(formato.getArchivos_adjuntos_posteriores())) {
				List<ArchivoAdjunto> adjuntos = ArchivoAdjuntoServicio.getInstance().obtenerArchivosAdjuntosPorCarga(carga.getId_carga(), null, null);
				return CollectionUtils.isEmpty(adjuntos);
			}
		}
		return false;
	}

	public String siguienteEstado(Carga carga, DaoManager daoManager, Integer id_persona) {

		HistorialCarga historialCarga = new HistorialCarga();
		historialCarga.setEstado_inicial(carga.getEstado());
		String estado = null;
		Integer pesoLiberador = null;
		
		if(!Constantes.CARGA_ESTADO_LIBERACION.equals(carga.getEstado()))
				estado = siguienteEstado(carga);
		else{
			
			// validar que un liberador no libere dos veces la misma carga
			Integer count = HistorialServicio.getInstance().obtenerVecesLiberadoHistorialCarga(id_persona, carga.getId_carga(), carga.getEstado());
			if(count > 0){
				return Constantes.CARGA_ESTADO_LIBERACION; 
			}
			
			//revisar si los maximos permitidos concuerdan
			boolean permitido=validarTotalesLiberacion(carga.getId_carga(), id_persona);		
			if(!permitido){
				return Constantes.CARGA_ESTADO_LIBERACION;
			}
			
			//revisar si se puede liberar basado en el peso
			// buscar  liberador
			Persona persona = PersonaServicio.getInstance().obtenerPersona(id_persona);
			Liberador liberador = LiberadorServicio.getInstance().obtenerLiberadorPorIdentificacion(carga.getId_usuario(), persona.getTipo_persona(), persona.getIdentificacion(), persona.getTipo_documento());			
			
			Proceso proceso = ProcesoServicio.getInstance().obtenerProceso(carga.getId_proceso());
			
			LiberadorTipoProceso liberadorTipoProceso = LiberadorServicio.getInstance().obtenerLiberadorTipoProceso(liberador.getId_liberador(), proceso.getId_tipo_proceso());
			
			pesoLiberador = liberadorTipoProceso.getPeso();
			estado = calcularSiguienteEstadoCargaLiberacion(carga, pesoLiberador);
			
			
			
		}
		
		historialCarga.setId_carga(carga.getId_carga());
		historialCarga.setId_persona(id_persona);
		historialCarga.setEstado(estado);
		historialCarga.setPeso(pesoLiberador);
		historialCarga.setFecha(HorarioServicio.getInstance().obtenerFechaActual());
		HistorialServicio.getInstance().guardarHistorialCarga(historialCarga);

		return estado;
	}

	public Boolean devolverCarga(Integer id_carga, Integer id_persona) {
		Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
		return devolverCarga(carga, id_persona);
	}
	
	public Boolean devolverCarga(Carga carga, Integer id_persona) {

		HistorialCarga historialCarga = new HistorialCarga();
		historialCarga.setEstado_inicial(carga.getEstado());
		String estado = Constantes.CARGA_ESTADO_RECHAZADO_CLIENTE;

		carga.setEstado(estado);
		Boolean ok = true;
		try {

			CargaDao cargaDao = (CargaDao) DaoConfig.getDao(CargaDao.class);
			CargaDao cargaDaoCreadatos = (CargaDao) DaoConfig.getDao(CargaDao.class, 2);
		
			// Se actualiza el estado en datasuite
			ok = cargaDao.actualizarEstadoCarga(carga);

			// Se actualiza el estado en creadatos
			cargaDaoCreadatos.cambiarEstadoCrea(carga.getId_carga(), carga.getEstado());
			
			historialCarga.setId_carga(carga.getId_carga());
			historialCarga.setId_persona(id_persona);
			historialCarga.setEstado(estado);
			historialCarga.setPeso(null);
			historialCarga.setFecha(HorarioServicio.getInstance().obtenerFechaActual());
			HistorialServicio.getInstance().guardarHistorialCarga(historialCarga);

		} catch (Exception e) {
			SimpleLogger.setError("Error determinando siguiente estado de la carga", e);
			ok = false;
		}

		return ok;
	}

	public boolean validarTotalesLiberacion(Integer id_carga, Integer id_persona){
		//obtener carga
		Carga carga=CargaServicio.getInstance().obtenerCarga(id_carga);
		//obtener persona
		Persona persona = PersonaServicio.getInstance().obtenerPersona(id_persona);
		Liberador liberador = LiberadorServicio.getInstance().obtenerLiberadorPorIdentificacion(carga.getId_usuario(), persona.getTipo_persona(), persona.getIdentificacion(), persona.getTipo_documento());

		Proceso proceso = ProcesoServicio.getInstance().obtenerProceso(carga.getId_proceso());
		
		LiberadorTipoProceso liberadorTipoProceso = LiberadorServicio.getInstance().obtenerLiberadorTipoProceso(liberador.getId_liberador(), proceso.getId_tipo_proceso());
		
		boolean valorCargaValido = true;
		try{
			if(carga.getValor_total_bigdecimal()!=null&&liberadorTipoProceso.getValor_max_carga()!=null&&carga.getId_tipocarga()!=1){
				valorCargaValido = validarTotalValorCargaLiberacion(carga.getValor_total_bigdecimal(), liberadorTipoProceso.getValor_max_carga());
			}
		}catch(Exception e){
			SimpleLogger.setError("Error validando carga",e);
		}
				
		if(!valorCargaValido){
			return false;
		}

		boolean valorIndividualValido = validarTotalIndividualCargaLiberacion(id_carga, liberadorTipoProceso.getValor_max_individual());
		if(!valorIndividualValido){
			return false;
		}
		
		return true;
		
	}
	
	private boolean validarTotalIndividualCargaLiberacion(Integer id_carga, Long valor_max_individual_liberador) {
		
		
		if(valor_max_individual_liberador == null){
			return true;
		}
		
		BigDecimal valor_max_individual_lib = new BigDecimal(valor_max_individual_liberador);
		
		
		// lista de valores
			// comparar por cada valor por si hay un valor mayor
		ArrayList<BigDecimal> listaValores = null;
		try{
			listaValores =CargaServicio.getInstance().obtenerValorRegistrosCarga(id_carga);
		}catch(Exception e){
			SimpleLogger.setError("Error cargando lista de valores", e);
		}
		// comparar por cada valor por si hay un valor mayor
		if(listaValores==null){
			return true;
		}
		if(CollectionUtils.isEmpty(listaValores)){
			return true;
		}else{
			for(BigDecimal bd : listaValores){
				if(bd.compareTo(valor_max_individual_lib) == 1){//encontro un registro con valor mayo al permitido
					return false;
				}
				
			}
		}
		
		return true;
	}

	private boolean validarTotalValorCargaLiberacion(BigDecimal valorCarga, Long valor_max) {		
		
		
		if(valor_max == null){
			return true;
		}
		if(valorCarga== null){
			return true;
		}
		//validar que el liberador pueda liberar la carga de pendiendo de los valores maximos
		if(BigDecimal.valueOf(valor_max).compareTo(valorCarga)==-1){
			//no puede liberar
			return false; 
		}
		else{
			return true;
		}
	}

	private String calcularSiguienteEstadoCargaLiberacion(Carga carga, Integer pesoLiberador) {
		String estado;
		// buscar proceso
		Proceso proceso = ProcesoServicio.getInstance().obtenerProceso(carga.getId_proceso());
		
		// buscar la sumatoria dado el historico segun la carga y el estado
		Integer pesoHistorico = HistorialServicio.getInstance().obtenerPesoHistorialCarga(carga.getId_carga(), carga.getEstado());
		if(pesoHistorico == null)
			pesoHistorico  = 0;
		
		Integer pesoTotal = pesoHistorico + pesoLiberador;  
		
		// si el peso del liberador + el peso historico es mayor o igual al peso del proceso entonces siguienteEstado()
		if(pesoTotal >= proceso.getPeso()){
			estado = siguienteEstado(carga);
		}else{
			// sigue en liberacion
			estado = Constantes.CARGA_ESTADO_LIBERACION;
		}
		return estado;
	}

	public List<Estado> obtenerEstados() {
		try {

			EstadoDao estadoDao = (EstadoDao) DaoConfig.getDao(EstadoDao.class);

			return estadoDao.obtenerEstados();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Estado obtenerEstado(String estado) {
		try {

			EstadoDao estadoDao = (EstadoDao) DaoConfig.getDao(EstadoDao.class);

			return estadoDao.obtenerEstado(estado);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean cambiarEstadoCargaEjecutada(Integer id_carga) {

		try {
			CargaDao cargaDao = (CargaDao) DaoConfig.getDao(CargaDao.class);
			CargaDao cargaDaoCreadatos = (CargaDao) DaoConfig.getDao(CargaDao.class, 2);

			Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);

			carga.setEstado(Constantes.CARGA_ESTADO_EJECUTADO);

			cargaDao.actualizarEstadoCarga(carga);
			cargaDaoCreadatos.cambiarEstadoCrea(carga.getId_carga(), carga.getEstado());

			return true;

		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}
}
