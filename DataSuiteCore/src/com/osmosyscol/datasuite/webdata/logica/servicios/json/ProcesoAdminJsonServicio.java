package com.osmosyscol.datasuite.webdata.logica.servicios.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cocoon.environment.Session;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.json.JsonService;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Accion;
import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.FiltroBandejaEntrada;
import com.osmosyscol.datasuite.logica.dto.Instancia;
import com.osmosyscol.datasuite.logica.dto.Negocio;
import com.osmosyscol.datasuite.logica.dto.ProcesoAdmin;
import com.osmosyscol.datasuite.logica.servicios.AdministrativoServicio;
import com.osmosyscol.datasuite.logica.servicios.NegocioServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.SemaforoProcesoAdmin;
import com.osmosyscol.datasuite.modelatos.logica.servicios.AccionServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.InstanciaServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoAdminServicio;
import com.osmosyscol.datasuite.webdata.GeneradorDeCargasOrdenasFiltradas;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.ClienteCarga;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class ProcesoAdminJsonServicio implements JsonService {

	private static String INSTANCIA_ADMIN_HIJO = "id_instancia_para_admins_hijo";
	private Session session;

	public void setSession(Session session) {
		this.session = session;

	}

	public static Boolean insertarProcesoAdmin(Integer id_proceso_admin, Integer id_negocio, Integer id_formato_entrada, String nombre) {
		return ProcesoAdminServicio.getInstance().insertarProcesoAdmin(id_proceso_admin, id_negocio, id_formato_entrada, nombre, null);
	}

	public static ProcesoAdmin obtenerProcesoAdmin(Integer id_proceso_admin) {
		return ProcesoAdminServicio.getInstance().obtenerProcesoAdmin(id_proceso_admin);
	}

	public static Integer obtenerSiguienteId() {
		return ProcesoAdminServicio.getInstance().obtenerSiguienteId();
	}

	public static Boolean borrarProcesoAdmin(Integer id_proceso_admin) {

		return ProcesoAdminServicio.getInstance().borrarProcesoAdmin(id_proceso_admin);

	}

	public static Boolean actualizarProcesoAdmin(Integer id_proceso_admin, Integer id_negocio, Integer id_formato_entrada, String nombre) {

		return ProcesoAdminServicio.getInstance().actualizarProcesoAdmin(id_proceso_admin, id_negocio, id_formato_entrada, nombre, null);

	}

	public static List<Negocio> obtenerListadoNegociosActivos() {

		return NegocioServicio.getInstance().obtenerListadoNegociosActivos();

	}

	public static List<Formato> obtenerFormatosEntradaPorNegocio(Integer id_negocio) {

		return FormatoServicio.getInstance().obtenerFormatosEntradaPorNegocio(id_negocio);

	}

	public List<ProcesoAdmin> obtenerProcesosAdmin() {

		return ProcesoAdminServicio.getInstance().obtenerProcesosAdmin();

	}

	public Boolean verificarExistencia(Integer id_negocio, Integer id_formato_entrada, Integer id_proceso_admin) {

		return ProcesoAdminServicio.getInstance().verificarExistencia(id_negocio, id_formato_entrada, id_proceso_admin);

	}

	private Integer obtenerIdAdministrativo() {
		try {
			return (Integer) session.getAttribute("id_administrativo");

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error.", e);
		}
		return null;
	}

	private Integer obtenerIdProcesoAdmin() {
		try {

			String id_proceso_admin = (String) session.getAttribute("var.id_proceso_admin");

			if (id_proceso_admin != null && !id_proceso_admin.equals("")) {
				return Integer.parseInt(id_proceso_admin);
			} else {
				return null;
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error.", e);
		}
		return null;
	}

	private Integer obtenerIdInstanciaParaListarAdministrativosHijo() {
		try {

			return (Integer) session.getAttribute(INSTANCIA_ADMIN_HIJO);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error.", e);
		}
		return null;
	}

	public List<SemaforoProcesoAdmin> obtenerSemaforoProcesoAdminPorAdministrativo() {
		Integer id_administrativo = obtenerIdAdministrativo();
		return ProcesoAdminServicio.getInstance().obtenerSemaforoProcesoAdminPorAdministrativo(id_administrativo);
	}

	public List<SemaforoProcesoAdmin> obtenerSemaforoParaAdminsHijoPorInstancia() {

		try {

			Integer intervalos = Constantes.INTERVALOS_SEMAFORO_PROCESOS;
			Integer id_administrativo_padre = obtenerIdAdministrativo();
			Integer id_proceso_admin = obtenerIdProcesoAdmin();
			Integer id_instancia = obtenerIdInstanciaParaListarAdministrativosHijo();

			// Se consultan los administrativos hijos de un administrativo padre para una instancia
			AdministrativoServicio administrativoServicio = AdministrativoServicio.getInstance();
			List<Administrativo> administrativosHijosPorInstancia = administrativoServicio.obtenerAdminsHijoPorInstanciaParaAdminPadre(id_administrativo_padre, id_instancia);

			// Se consulta la informacion del proceso
			ProcesoAdminServicio procesoAdminServicio = ProcesoAdminServicio.getInstance();
			ProcesoAdmin procesoAdmin = procesoAdminServicio.obtenerProcesoAdmin(id_proceso_admin);

			CargaServicio cargaServicio = CargaServicio.getInstance();

			List<SemaforoProcesoAdmin> listadoDeSemaforos = new ArrayList<SemaforoProcesoAdmin>();
			if (administrativosHijosPorInstancia != null && administrativosHijosPorInstancia.size() > 0) {

				// Por cada adminsitrativo hijo se cuentan las cargas y se crea un semaforo

				for (Administrativo administrativo : administrativosHijosPorInstancia) {
					List<Integer> listadoCargasPendientes = new ArrayList<Integer>();

					// Se cuenta las cargas pendientes para cada uno de los intervalos
					// incluyendo el intervalo 0 en el cual se contabilizan las cargas
					// que aun estan estado REQUIREN_ADJUNTO(Cargas que aun necesitan informacion del cliente );
					for (int i = 0; i <= intervalos; i++) {
						Integer cargasPendientes = cargaServicio.cargasPendientesPorInstanciaParaAdminHijo(id_proceso_admin, id_instancia, id_administrativo_padre, administrativo.getId_administrativo(), intervalos, i);

						listadoCargasPendientes.add(cargasPendientes);
					}

					SemaforoProcesoAdmin semaforoProcesoAdmin = new SemaforoProcesoAdmin();
					semaforoProcesoAdmin.setId_proceso_admin(procesoAdmin.getId_proceso_admin());
					semaforoProcesoAdmin.setNombre_proceso(procesoAdmin.getNombre());
					semaforoProcesoAdmin.setCargasPorIntervalo(listadoCargasPendientes);
					semaforoProcesoAdmin.setTotal_intervalos(intervalos);
					semaforoProcesoAdmin.setAdministrativo(administrativo);

					listadoDeSemaforos.add(semaforoProcesoAdmin);

				}

			}

			// Se retorna un listado de semaforo en el que cada semaforo pertenece a un administrativo
			return listadoDeSemaforos;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	public Boolean guardarIdInstanciaParaListarAdministrativosHijo(Integer id_instancia) {

		try {
			SimpleLogger.setDebug("guardado InstanciaParaListarAdministrativosHijo ");
			session.setAttribute(INSTANCIA_ADMIN_HIJO, id_instancia);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Funcion que retorna las cargas para el administrativo hijo junto con las instancias y acciones que por lo menos tengan una instancia destino
	 * 
	 * @return
	 */
	public List<Object> obtenerCargasPorInstanciaParaAdminHijo(Integer id_administrativo_hijo) {

		List<Object> objetos = new ArrayList<Object>();

		try {
			Integer id_administrativo_padre = obtenerIdAdministrativo();
			Integer id_instancia = obtenerIdInstanciaParaListarAdministrativosHijo();
			Integer id_proceso_admin = obtenerIdProcesoAdmin();

			CargaServicio cargaServicio = CargaServicio.getInstance();
			List<Carga> cargas = cargaServicio.obtenerCargasPorInstanciaParaAdminHijo(id_proceso_admin, id_instancia, id_administrativo_padre, id_administrativo_hijo);

			objetos.add(cargas);

			// Se consultan las acciones por instancia para el administrativo hijo
			List<Instancia> instanciasPorAdministrativoHijo = InstanciaServicio.getInstance().obtenerInstanciasDelProcesoPorAdministrativo(id_administrativo_hijo, id_proceso_admin);

			if (instanciasPorAdministrativoHijo != null && instanciasPorAdministrativoHijo.size() > 0) {
				for (Instancia instancia : instanciasPorAdministrativoHijo) {

					// Se consultan las acciones con instancias destino
					List<Accion> accionesPorInstancia = AccionServicio.getInstance().obtenerAccionesConInstanciaDestinoPorInstanciaOrigen(instancia.getId_instancia());
					instancia.setAccionesPorInstancia(accionesPorInstancia);
				}

				objetos.add(instanciasPorAdministrativoHijo);
			}

			return objetos;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return null;
		}

	}

	/**
	 * Funcion que retorna las cargas ordenas y filtradas para un proceso del tren administrativo, los clientes asociados a las cargas, y las instancias a las cuales esta asociado el administrativo
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<Object> obtenerCargasOrdenadasFiltradas(Integer num_pagina) {

		List<Object> objetos = new ArrayList<Object>();

		GeneradorDeCargasOrdenasFiltradas generadorDeCargasOrdenasFiltradas = GeneradorDeCargasOrdenasFiltradas.getInstance();
		Map<String, Object> resultado = generadorDeCargasOrdenasFiltradas.obtenerCargasOrdenadasFiltradas(this.session,null,null,null,null, num_pagina, null);
		List<Carga> cargas = (List<Carga>)resultado.get("cargas");
		Integer total_registros = (Integer)resultado.get("total");

		if (cargas != null) {
			this.session.setAttribute("listadoCargasOrdenadasFiltradas", cargas);
			objetos.add(cargas);

			// Se consultan los clientes por las cargas ordenadas
			List<ClienteCarga> clientes = generadorDeCargasOrdenasFiltradas.obtenerListoadoClientesPorListadoCargasFiltradasOrdenadas(cargas);

			objetos.add(clientes);

		}

		// Se consultan las acciones por instancia
		Integer id_administrativo = obtenerIdAdministrativo();
		Integer id_proceso_admin = obtenerIdProcesoAdmin();
		List<Instancia> instanciasPorAdministrativo = InstanciaServicio.getInstance().obtenerInstanciasDelProcesoPorAdministrativo(id_administrativo, id_proceso_admin);

		if (instanciasPorAdministrativo != null && instanciasPorAdministrativo.size() > 0) {
			for (Instancia instancia : instanciasPorAdministrativo) {

				// Se obtienen las acciones por instancia origen que tengan instancias destino
				List<Accion> accionesPorInstancia = AccionServicio.getInstance().obtenerAccionesConInstanciaDestinoPorInstanciaOrigen(instancia.getId_instancia());
				instancia.setAccionesPorInstancia(accionesPorInstancia);
			}

			objetos.add(instanciasPorAdministrativo);
		}

		if (total_registros != null) {
			objetos.add(total_registros);
		}
		
		return objetos;

	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> obtenerCargasFiltradas(String instancia_carga, String cliente, String fecha_carga, String fecha_liberacion, Integer num_pagina, FiltroBandejaEntrada[] filtros) {
		
		Map <String, Object> map = new HashMap<String, Object>();

		GeneradorDeCargasOrdenasFiltradas generadorDeCargasOrdenasFiltradas = GeneradorDeCargasOrdenasFiltradas.getInstance();
		Map<String, Object> resultado = generadorDeCargasOrdenasFiltradas.obtenerCargasOrdenadasFiltradas(this.session, instancia_carga, cliente, fecha_carga, fecha_liberacion, num_pagina, filtros);
		List<Carga> cargas = (List<Carga>)resultado.get("cargas");
		Integer total_registros = (Integer)resultado.get("total");

		if (cargas != null && cargas.size() > 0) {
			this.session.setAttribute("listadoCargasOrdenadasFiltradas", cargas);
			map.put("cargas", cargas);

			// Se consultan los clientes por las cargas ordenadas
			List<ClienteCarga> clientes = generadorDeCargasOrdenasFiltradas.obtenerListoadoClientesPorListadoCargasFiltradasOrdenadas(cargas);

			map.put("cliente",clientes);

		}

		// Se consultan las acciones por instancia
		Integer id_administrativo = obtenerIdAdministrativo();
		Integer id_proceso_admin = obtenerIdProcesoAdmin();
		List<Instancia> instanciasPorAdministrativo = InstanciaServicio.getInstance().obtenerInstanciasDelProcesoPorAdministrativo(id_administrativo, id_proceso_admin);

		if (instanciasPorAdministrativo != null && instanciasPorAdministrativo.size() > 0) {
			for (Instancia instancia : instanciasPorAdministrativo) {

				// Se obtienen las acciones por instancia origen que tengan instancias destino
				List<Accion> accionesPorInstancia = AccionServicio.getInstance().obtenerAccionesConInstanciaDestinoPorInstanciaOrigen(instancia.getId_instancia());
				instancia.setAccionesPorInstancia(accionesPorInstancia);
			}

			map.put("instanciaPorAdministrativo", instanciasPorAdministrativo);
		}

		if (total_registros != null) {
			map.put("total", total_registros);
		}
		
		return map;

	}

}
