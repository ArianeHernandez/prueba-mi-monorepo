package com.osmosyscol.datasuite.webdata.logica.servicios;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Horario;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.ParametrizacionClienteDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class ParametrizacionClienteServicio {
	// ---------------------------------------------------------------

	private static ParametrizacionClienteServicio cargaServicio;

	private ParametrizacionClienteServicio() {
	}

	// ---------------------------------------------------------------

	public static ParametrizacionClienteServicio getInstance() {
		if (cargaServicio == null) {
			cargaServicio = new ParametrizacionClienteServicio();
		}
		return cargaServicio;
	}

	
	// ---------------------------------------------------------------

	public Boolean guardarHorarioLiberacion(Integer id_usuario, String franjas){
		try {
				Boolean haSidoGuardado  = false; 
			
				DaoManager daoManager = DaoConfig.getDaoManager();
				ParametrizacionClienteDao parametrizacionClienteDao = (ParametrizacionClienteDao) daoManager.getDao(ParametrizacionClienteDao.class);

				HorarioServicio horarioServicio = HorarioServicio.getInstance();
				
				Horario horario = parametrizacionClienteDao.obtenerHorarioLiberacion(id_usuario);
				
				
				if (horario!= null) {
					//Actualizamos las franjas del horario existente
					haSidoGuardado=horarioServicio.guardarFranjas(franjas, horario.getId_horario());
					
				}else{
					//Creamos un nuevo horario de liberacion
					Integer id_nuevo_horario=horarioServicio.obtenerSiguienteIDHorario();
					horario = new Horario();
					horario.setId_horario(id_nuevo_horario);
					
					if(horarioServicio.crearHorario(horario)){
						horarioServicio.guardarFranjas(franjas, id_nuevo_horario);
						parametrizacionClienteDao.crearHorarioLiberacion(id_nuevo_horario, id_usuario);
						haSidoGuardado =true;
					}
					
					
				}
				
				return haSidoGuardado;
				

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			
		}
	
		return null;
		
	} 
	
	public Horario obtenerHorarioLiberacion(Integer id_usuario){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			ParametrizacionClienteDao  parametrizacionClienteDao = (ParametrizacionClienteDao) daoManager.getDao(ParametrizacionClienteDao.class);

			return parametrizacionClienteDao.obtenerHorarioLiberacion(id_usuario);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	public Integer totalFranjasHorarioLiberacion(Integer id_usuario){
		try {
			Horario horario = obtenerHorarioLiberacion(id_usuario);
			HorarioServicio horarioServicio= HorarioServicio.getInstance();
		
			if(horario==null){
				return 0;
			}
			
			return horarioServicio.obtenerNumeroTotalFranjas(horario.getId_horario());
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
		
		
	}
	
	
}
