package com.osmosyscol.osmoautenticador.servicio;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.osmoautenticador.constantes.ConstantesGenerales;
import com.osmosyscol.osmoautenticador.persistencia.dao.RolDao;
import com.osmosyscol.osmoautenticador.persistencia.dao.ServicioDao;
import com.osmosyscol.osmoautenticador.persistencia.dao.ServicioExDao;
import com.osmosyscol.osmoautenticador.persistencia.dto.RolDto;
import com.osmosyscol.osmoautenticador.persistencia.dto.ServicioDto;
import com.osmosyscol.osmoautenticador.persistencia.dto.ServicioExDto;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class AdministracionServicioRol {

	private static AdministracionServicioRol administracionServicio;

	public static AdministracionServicioRol getInstance() {
		if (administracionServicio == null) {
			administracionServicio = new AdministracionServicioRol();
		}
		return administracionServicio;
	}

	// ---------------------

	public String[] crearRol(RolDto rolDto,List<ServicioDto> servicioDto, String naturaleza) {
		DaoManager daoManager = null;
		String[] result = null;
		try {
			result = validarExcluyentes(servicioDto, naturaleza);

			if(result[0].equalsIgnoreCase("OK")){
				daoManager = DaoConfig.getDaoManager();
				daoManager.startTransaction();

				RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);
				rolDto.setNaturaleza(naturaleza);
				rolDao.insertarRol(rolDto);
				int idRol = rolDao.getIdRol();
				List<ServicioDto> tmpServicio = new ArrayList<ServicioDto>(); 
				for (ServicioDto servicio : servicioDto) {
					servicio.setIdRol(idRol);
					tmpServicio.add(servicio);
				}
				ServicioDao servicioDao = (ServicioDao) daoManager.getDao(ServicioDao.class);
				servicioDao.insertarServicioporRol(tmpServicio);

				daoManager.commitTransaction();
			}
			SimpleLogger.setDebug(">> AdminServ Crea Rol: " + result[0]);
			return result;
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio crearRol", e);
			result = new String[1];
			result[0] = "FAILED";
			return result;
		} finally {
			if(daoManager!=null){
				daoManager.endTransaction();
			}
		}
	}


	public String[] modificarRol(Integer idRol,List<ServicioDto> servicioDto, String naturaleza) {
		DaoManager daoManager = DaoConfig.getDaoManager(1);

		String[] result = null;
		try {
			result = validarExcluyentes(servicioDto, naturaleza);

			if(result[0].equalsIgnoreCase("OK")){
				daoManager.startTransaction();
				ServicioDao servicioDao = (ServicioDao) daoManager.getDao(ServicioDao.class);

				Boolean resultado=false;
				resultado = servicioDao.eliminarServicioporRol(idRol);
				SimpleLogger.setDebug("Eliminado servicioXrol : " + idRol +"," + servicioDto + " - " + resultado);

				ServicioDto servicio = new ServicioDto();
				servicio.setIdRol(idRol);
				servicio.setIdServicio(ConstantesGenerales.SERVICIO_HOME);

				servicioDto.add(servicio);
				resultado = servicioDao.insertarServicioporRol(servicioDto);


				SimpleLogger.setDebug("Insertado servicioXrol : " + idRol +"," + servicioDto + " - " + resultado);

				daoManager.commitTransaction();
			}
			SimpleLogger.setDebug(">> AdminServ Modifica Rol: " + result[0]);
			return result;
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio modificarRol", e);
			result = new String[1];
			result[0] = "FAILED";
			return result;
		}finally{
			if(daoManager!=null){
				daoManager.endTransaction();
			}
		}
	}

	public Boolean eliminarRol(Integer idRol, String naturaleza) {
		DaoManager daoManager = DaoConfig.getDaoManager(1);
		try {
			daoManager.startTransaction();
			ServicioDao servicioDao = (ServicioDao) daoManager.getDao(ServicioDao.class);
			RolDao rolDao = (RolDao) daoManager.getDao(RolDao.class);

			Boolean resultado=false;
			resultado = servicioDao.eliminarServicioporRol(idRol);
			SimpleLogger.setDebug("Eliminado servicioXrol : " + idRol + " - " + resultado);

			resultado = rolDao.eliminarRol(idRol);
			SimpleLogger.setDebug("Eliminado rol : " + idRol + " - " + resultado);

			daoManager.commitTransaction();
			SimpleLogger.setDebug(">> AdminServ Elimina Rol: " + resultado);
			return resultado;
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio modificarRol", e);
			return false;
		}finally{
			if(daoManager!=null){
				daoManager.endTransaction();
			}
		}
	}


	public String[] validarExcluyentes(List<ServicioDto> servicioDto, String naturaleza){
		SimpleLogger.setInfo("Validando Conflictos...");
		DaoManager daoManager = null;
		String[] msnResult = null;
		try{
			daoManager = DaoConfig.getDaoManager(1);
			ServicioExDao servicioEx = (ServicioExDao) daoManager.getDao(ServicioExDao.class);

			List<String> result = new ArrayList<String>();

			for (ServicioDto servicio : servicioDto) {
				List<ServicioExDto> serviciosEx = servicioEx.obtenerServiciosEx(servicio.getIdServicio(), naturaleza);
				for (ServicioExDto servicioExDto : serviciosEx) {
					for (ServicioDto ListServicios : servicioDto) {
						if((servicioExDto.getId_servicio_ex().intValue())==(ListServicios.getIdServicio().intValue())){
							if(result.size()==0){
								result.add("ERR");
							}
							String nServicio = obtenerNombreServicio(servicioExDto.getId_servicio());
							String nServicioEx = obtenerNombreServicio(ListServicios.getIdServicio());
							
							if(nServicio.equalsIgnoreCase("")){
								nServicio = ""+servicioExDto.getId_servicio();
							}
							if(nServicioEx.equalsIgnoreCase("")){
								nServicioEx = "" + ListServicios.getIdServicio();
							}
							result.add(nServicio+"$|$"+nServicioEx);
							SimpleLogger.setDebug(">>> Conflictos: " + nServicio+"$|$"+nServicioEx);
						}
					}
				}
			}
			if(result.size()<=0){
				msnResult = new String[1];
				msnResult[0] = "OK";
			}else{
				msnResult = new String[result.size()];
				result.toArray(msnResult);
			}

			return msnResult;
		}catch (Exception e) {
			SimpleLogger.setDebug("Error en la validacion de Excluyentes");
			msnResult = new String[1];
			msnResult[0] = "FAILED";
			return msnResult;
		}finally{
			if(daoManager!=null){
				daoManager.endTransaction();
			}
		}
	}

	private String obtenerNombreServicio(Integer id_servicio) {
		DaoManager daoManager = null;
		try{
			daoManager = DaoConfig.getDaoManager(1);
			ServicioDao servicioDao = (ServicioDao) daoManager.getDao(ServicioDao.class);
			ServicioDto servicio = servicioDao.obtenerServicio(id_servicio);
			
			return servicio.getNombre();
		}catch (Exception e) {
			SimpleLogger.setDebug("Error al obtener el nombre del servicio");
			return "";
		}finally{
			if(daoManager!=null){
				daoManager.endTransaction();
			}
		}
	}
}
// --------------------------------------

