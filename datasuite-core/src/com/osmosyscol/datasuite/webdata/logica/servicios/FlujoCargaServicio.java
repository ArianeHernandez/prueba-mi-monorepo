package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.cocoon.environment.Request;
import org.apache.commons.lang.StringUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.HistorialCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.ValorListaDinamicaCampo;
import com.osmosyscol.datasuite.webdata.logica.dto.VariableLiberacion;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class FlujoCargaServicio {

	
	private static FlujoCargaServicio instance;

	private FlujoCargaServicio() {

	}

	public static FlujoCargaServicio getInstance() {
		if (instance == null) {
			instance = new FlujoCargaServicio();
		}
		return instance;
	}

	public Boolean aprobarCargaLiberacion(Integer id_carga, Date fecha_liberacion, List<ValorListaDinamicaCampo> listaValoresDinamicos, Integer id_formato, Integer id_persona) {
		try {
			
			if(fecha_liberacion==null){
				fecha_liberacion = HorarioServicio.getInstance().obtenerFechaActual();
			}
			
			Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
			carga.setFecha_liberacion(fecha_liberacion);
			
			
			String aprobado = null;
			
			Boolean rta = true;
			
			List<VariableLiberacion> valoresLiberacion = VariableLiberacionServicio.getInstance().obtenerVariablesLiberacionPorCarga(id_carga);
			
			if(valoresLiberacion == null || valoresLiberacion.size()==0){
				rta = CargaServicio.getInstance().guardarValoresLista(listaValoresDinamicos, id_formato, true);
			}
			
			if(rta){
				aprobado = EstadoCargaServicio.getInstance().siguienteEstado(carga, null, id_persona);
			}
			
			return aprobado != null && rta;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error: ", e);
		}

		return false;
	}

	public Boolean aprobarCarga(Integer id_carga, Integer id_persona) {
		String aprobado = EstadoCargaServicio.getInstance().siguienteEstado(id_carga, null, id_persona);
		if (aprobado != null) {
			return true;
		}

		return false;
	}

	public Boolean rechazarCarga(Integer id_carga, Integer id_persona, String motivo) {
		return cambiarEstado(id_carga, Constantes.CARGA_ESTADO_RECHAZADO_CLIENTE, null, id_persona, motivo);

	}

	public Boolean cambiarEstado(Integer id_carga, String nuevoestado, Request request, Integer id_persona, String motivo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			DaoManager daoManagerCre = DaoConfig.getDaoManager(2);
			try {
				
				
				//ESTO es para CARGA AUTOMATICA
				if (nuevoestado.equals(Constantes.CARGA_ESTADO_APROBADO)) {
					Boolean rta = CargaServicio.getInstance().aplicarCarga(id_carga);
					if (rta) {
						nuevoestado = Constantes.CARGA_ESTADO_APLICADA;
					}
				}

				daoManager.startTransaction();
				daoManagerCre.startTransaction();
				
				//Inicialmente se crea el historial
				//Finalmente se crea el historial de la carga
				//Se crea el historial 
				CargaServicio cargaServicio = CargaServicio.getInstance();
				Carga carga = cargaServicio.obtenerCarga(id_carga);
				
				HistorialCarga historialCarga = new HistorialCarga();
				historialCarga.setEstado_inicial(carga.getEstado());
				historialCarga.setId_carga(carga.getId_carga());
				historialCarga.setId_persona(id_persona);
				historialCarga.setEstado(nuevoestado);
				historialCarga.setFecha(HorarioServicio.getInstance().obtenerFechaActual());
				HistorialServicio.getInstance().guardarHistorialCarga(historialCarga);
				
				
				//Luego se modifica el estado de la carga
				CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
				CargaDao cargaDaoCre = (CargaDao) daoManagerCre.getDao(CargaDao.class);

				cargaDao.cambiarEstado(id_carga, nuevoestado);
				cargaDao.cambiarMotivo(id_carga, motivo);
				cargaDaoCre.cambiarEstadoCrea(id_carga, nuevoestado);
				

				if (nuevoestado.equals(Constantes.CARGA_ESTADO_APROBADO) && request != null) {
					String[] registros = request.getParameterValues("registros");
					Boolean rta = rechazarCampos(registros, cargaDaoCre);
					if (!rta) {
						return false;
					}
				}
						
						
				
				daoManager.commitTransaction();
				daoManagerCre.commitTransaction();
			} catch (Exception e) {
				SimpleLogger.setError("No se puede cambiar el estado", e);
			} finally {
				daoManager.endTransaction();
				daoManagerCre.endTransaction();
			}

		} catch (Exception e) {
			SimpleLogger.setError("No se puede cambiar el estado CargaServicio.cambiarEstado", e);
			return false;
		}
		return true;

	}

	public Boolean rechazarCampos(String[] registros, CargaDao cargaDaoCre) {

		if (registros != null) {
			for (String string : registros) {
				if (StringUtils.isNotEmpty(string)) {
					String[] registro = StringUtils.split(string, "_");
					if (registro != null && registro.length == 2) {
						String id_estructura = registro[0];
						String id_campo = registro[1];
						String sql = "update T" + id_estructura + " set estado='" + Constantes.CARGA_ESTADO_RECHAZADO + "'" + " where id=" + id_campo;

						SimpleLogger.setDebug(sql);
						cargaDaoCre.updateSQL(sql);
					}
				}
			}
		}

		return true;
	}

	public Integer liberarCargas() {

		try {
			Integer cargasLiberadas = 0;
			DaoManager daoManager = DaoConfig.getDaoManager();
			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
			
			List<Carga> cargas = cargaDao.obtenerCargasLiberacion(Constantes.CARGA_ESTADO_ENVIO, new Date());

			for (Carga carga : cargas) {

				String aprobado = EstadoCargaServicio.getInstance().siguienteEstado(carga, daoManager, null);

				if (aprobado != null && !aprobado.equals(Constantes.CARGA_ESTADO_ENVIO)) {
					cargasLiberadas++;
				}

			}
			return cargasLiberadas;
		} catch (Exception e) {
			SimpleLogger.setError("Error liberando cargas", e);
		}
		return null;
	}
	
	public Boolean validarSaldo(Integer id_carga, List<ValorListaDinamicaCampo> listaValoresDinamicos){
		String producto = null;
		String id_cuenta = null;
		try{
			for (Iterator iterator = listaValoresDinamicos.iterator(); iterator.hasNext();) {
				ValorListaDinamicaCampo valorListaDinamicaCampo = (ValorListaDinamicaCampo) iterator.next();
				if( valorListaDinamicaCampo.getId_campo() == 1803 ){
					producto = valorListaDinamicaCampo.getValor();
				}else if( valorListaDinamicaCampo.getId_campo() == 2101 ){
					id_cuenta = valorListaDinamicaCampo.getValor();
				}
			}
			
			if(producto != null && id_cuenta != null){
				String sql_saldo = "select $ENCARGO.SALDO DISPONIBLE$ saldo from $ENCARGO$ where $ENCARGO.CODIGO$ = $S(" + id_cuenta + ")$ and $ENCARGO.PRODUCTO$ = " + producto;
				Carga carga = CargaServicio.getInstance().obtenerCarga(id_carga);
				BigDecimal saldo = DS_SqlUtils.queryForObject(BigDecimal.class, sql_saldo);
				BigDecimal valor_total = carga.getValor_total_bigdecimal();
				if(saldo.compareTo(valor_total) < 0){
					return false;
				}
			}
		}catch(Exception e){
			SimpleLogger.setError("Ha ocurrido un error: ", e);
			return false;
		}
		return true;


	}

}
