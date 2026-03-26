package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.Accion;
import com.osmosyscol.datasuite.logica.dto.Horario;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.Rol;
import com.osmosyscol.datasuite.logica.servicios.HorarioServicio;
import com.osmosyscol.datasuite.persistencia.dao.InstanciaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class InstanciaServicio {

	private static InstanciaServicio instancia;

	private InstanciaServicio() {
	}

	public static InstanciaServicio getInstance() {
		if (instancia == null) {
			instancia = new InstanciaServicio();
		}
		return instancia;
	}

	public Boolean insertarInstancia(Integer id_instancia, String nombre, String aprobar, String rechazar, Integer tiempo, Integer posicion_x, Integer posicion_y, Integer id_proceso_admin, String inicial, Integer id_formato_salida, Integer id_horario, String aprobar_automaticamente, String rechazar_automaticamente, String solicitar_otp) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.insertarInstancia(id_instancia, nombre, aprobar, rechazar, tiempo, posicion_x, posicion_y, id_proceso_admin, inicial, id_formato_salida, id_horario, aprobar_automaticamente, rechazar_automaticamente, solicitar_otp);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.insertarInstancia", e);
			return false;
		}
	}

	/**
	 * Permite obtener una instancia por id.
	 * 
	 * @param id_instancia
	 * @return
	 */
	public Instancia obtenerInstancia(Integer id_instancia) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerInstancia(id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.obtenerInstancia", e);
			return null;
		}
	}

	public List<Instancia> obtenerInstanciasPorProceso(Integer id_proceso_admin) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerInstanciasPorProceso(id_proceso_admin);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.obtenerInstanciasPorProceso", e);
			return null;
		}
	}
	
	public Map<Integer,Instancia> obtenerInstanciasPorProcesoMap(Integer id_proceso_admin) {
		try {
			Map<Integer, Instancia> map = new HashMap<>();
			List<Instancia> instancias = obtenerInstanciasPorProceso(id_proceso_admin);
			
			for(Instancia instancia: instancias) {
				List<Accion> acciones = AccionServicio.getInstance().obtenerAccionesPorInstancia(instancia.getId_instancia());
				instancia.setAccionesPorInstancia(acciones);
				map.put(instancia.getId_instancia(), instancia);
			}
			
			return map;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.obtenerInstanciasPorProcesoMap", e);
			return null;
		}
	}

	public Integer obtenerSiguienteId() {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerSiguienteId();

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.obtenerSiguienteId", e);
			return null;
		}
	}

	public Boolean borrarInstancia(Integer id_instancia) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.borrarInstancia(id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.borrarInstancia", e);
			return false;
		}
	}

	public Boolean actualizarPosicionInstancia(Integer id_instancia, Integer posicion_x, Integer posicion_y) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.actualizarPosicionInstancia(id_instancia, posicion_x, posicion_y);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.actualizarPosicionInstancia", e);
			return false;
		}
	}

	public Boolean actualizarPermisosInstancia(Integer id_instancia, String aprobar, String rechazar, String inicial, String adicionar_docs, String gestionar_docs, String aprobar_automaticamente, String rechazar_automaticamente, String solicitar_otp, String ocultar_cargas_sin_filtro) {
		try {
			if (actualizarAdicionarDocs(id_instancia, adicionar_docs) && actualizarGestionarDocs(id_instancia, gestionar_docs)) {
				DaoManager daoManager = DaoConfig.getDaoManager();
				InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
				return instanciaDao.actualizarPermisosInstancia(id_instancia, aprobar, rechazar, inicial, aprobar_automaticamente, rechazar_automaticamente, solicitar_otp, ocultar_cargas_sin_filtro);
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.actualizarPermisosInstancia", e);
		}
		return false;
	}

	public Boolean actualizarTiempo(Integer id_instancia, Integer tiempo) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.actualizarTiempo(id_instancia, tiempo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.actualizarTiempo", e);
			return false;
		}
	}

	public Boolean actualizarNombre(Integer id_instancia, String nombre) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.actualizarNombre(id_instancia, nombre);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.actualizarNombre", e);
			return false;
		}

	}

	public Boolean actualizarFormatoSalida(Integer id_instancia, Integer id_formato_salida) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.actualizarFormatoSalida(id_instancia, id_formato_salida);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.actualizarFormatoSalida", e);
			return false;
		}

	}

	public Boolean actualizarHorarioInstancia(Integer id_instancia, String franjas) {

		try {
			Instancia instancia = obtenerInstancia(id_instancia);
			Integer id_horario = instancia.getId_horario();

			
			if (id_horario == null) {

				id_horario = HorarioServicio.getInstance().obtenerSiguienteIDHorario();

				Horario horario = new Horario();
				horario.setId_horario(id_horario);
				horario.setDescripcion("Instancia " + id_instancia);
				HorarioServicio.getInstance().crearHorario(horario);
			}
			
			InstanciaDao instanciaDao = (InstanciaDao) DaoConfig.getDaoManager().getDao(InstanciaDao.class);
			instanciaDao.actualizarHorario(id_instancia, id_horario);
			
			Boolean rta = HorarioServicio.getInstance().guardarFranjas(franjas, id_horario);
			return rta;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.actualizarFormatoSalida", e);
			return false;
		}

	}

	public Boolean insertarRolInstancia(Integer id_rol, Integer id_instancia) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.insertarRolInstancia(id_rol, id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.insertarRolInstancia", e);
			return false;
		}

	}

	public Rol obtenerRolInstancia(Integer id_rol, Integer id_instancia) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerRolInstancia(id_rol, id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.obtenerRolInstancia", e);
			return null;
		}

	}

	public List<Rol> obtenerRolesInstancia(Integer id_instancia) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerRolesInstancia(id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}

	}

	public Boolean borrarRolInstancia(Integer id_rol, Integer id_instancia) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.borrarRolInstancia(id_rol, id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return false;
		}

	}

	public List<Rol> obtenerRolesDispParaAsignar(Integer id_instancia) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerRolesDispParaAsignar(id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}

	}

	public Instancia obtenerInstanciaDelProcesoPorCarga(Integer id_carga, Integer id_instancia) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerInstanciaDelProcesoPorCarga(id_carga, id_instancia);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
	}

	public List<Instancia> obtenerInstanciasInicialesPorProceso(Integer id_proceso_admin) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerInstanciasInicialesPorProceso(id_proceso_admin);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}

	}

	public List<Instancia> obtenerInstanciasDelProcesoPorAdministrativo(Integer id_administrativo, Integer id_proceso_admin) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerInstanciasDelProcesoPorAdministrativo(id_administrativo, id_proceso_admin);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}

	}

	/**
	 * Esta funcion retorna el listado de instancias de todos los roles que sean hijos de los roles que tenga asociado un usuario administrativo (administrativo padre). Todo esto para un un proceso en particular <br/>
	 * <br/>
	 * 
	 * Se utiliza en administrar cargas por proceso
	 *
	 * @param id_proceso_admin
	 * @param id_administrativo_padre
	 * 
	 * @author jcvargasj
	 * 
	 */

	public List<Instancia> obtenerInstanciasDelProcesoParaRolesHijo(Integer id_proceso_admin, Integer id_administrativo_padre) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerInstanciasDelProcesoParaRolesHijo(id_proceso_admin, id_administrativo_padre);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}

	}

	public List<Instancia> obtenerInstanciasPreviasConCargaActualPendiente(Integer id_instancia_actual, Integer id_carga) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerInstanciasPreviasConCargaActualPendiente(id_instancia_actual, id_carga);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}

	}
	
	public Boolean actualizarAdicionarDocs(Integer id_instancia, String adiciona) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.actualizarAdicionarDocs(id_instancia, adiciona);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.actualizarAdicionarDocs", e);
			return false;
		}
	}

	public Boolean actualizarGestionarDocs(Integer id_instancia, String gestiona) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.actualizarGestionarDocs(id_instancia, gestiona);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.actualizarGestionarDocs", e);
			return false;
		}
	}

	public Instancia obtenerInstanciaActual(Integer id_carga) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.obtenerInstanciaActual(id_carga);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.obtenerInstanciaActual", e);
			return null;
		}
	}

	public Boolean actualizarInstancia(Instancia instancia) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			InstanciaDao instanciaDao = (InstanciaDao) daoManager.getDao(InstanciaDao.class);
			return instanciaDao.actualizarInstancia(instancia);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en InstanciaServicio.actualizarInstancia", e);
			return false;
		}
	}
	
}
