package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.List;

import org.apache.cocoon.environment.Session;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servicio.AutenticacionServicio;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Administrativo;
import com.osmosyscol.datasuite.logica.dto.Menu;
import com.osmosyscol.datasuite.logica.dto.SeccionMenu;
import com.osmosyscol.datasuite.logica.dto.TipoProceso;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.AdministrativoServicio;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.persistencia.dao.MenuDao;
import com.osmosyscol.datasuite.webdata.logica.servicios.AccesoRolServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class MenuServicio {

	private static MenuServicio instancia;

	private MenuServicio() {
	}

	public static MenuServicio getInstance() {
		if (instancia == null) {
			instancia = new MenuServicio();
		}
		return instancia;
	}

	public static List<Menu> obtenerMenusPorAplicacion(String aplicacion, Session session) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			MenuDao menuDao = (MenuDao) daoManager.getDao(MenuDao.class);

			List<Menu> lista = menuDao.obtenerMenusPorAplicacion(aplicacion);

			Integer id_administrativo = (Integer) session.getAttribute("id_administrativo");

			for (Menu menu : lista) {

				boolean autorizado = AutenticacionServicio.getInstance().autorizarUrl(session, menu.getAccion());

				// --- validar servicios de administrativo

				if (autorizado && id_administrativo != null && menu.getServicio() != null) {
					autorizado = AccesoRolServicio.getInstance().validarAccesoAdministrativo(menu.getServicio(), id_administrativo);
				}

				// --- validar tipo de proceso del cliente
				Integer id_persona = (Integer) session.getAttribute("id_persona");
				Usuario usuario = (Usuario) session.getAttribute("usuario");

				if (autorizado && usuario != null) {
					autorizado = validarAccesoPersona(usuario, id_persona, menu.getId_tipo_proceso(), StringUtils.esVerdad(menu.getValida_proceso()));
				}

				menu.setAutorizado(autorizado);
			}
			return lista;
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en MenuServicio.obtenerMenusPorAplicacion", e);
			return null;
		}
	}

	public static boolean validarAccesoPersona(Usuario usuario, Integer id_persona, Integer id_tipo_proceso, boolean validarProceso) {

		// -------- debe ser una persona para tener acceso

		if (id_persona == null) {
			return false;
		}

		// si no se especifica tipo de proceso o no es usuario se permite el acceso
		if (id_tipo_proceso == null || usuario == null || usuario.getId_usuario() == null) {
			return true;
		}

		// ------------- se identifica el tipo de proceso

		List<TipoProceso> tiposProceso = PersonaServicio.getInstance().obtenerTiposProcesosPorIdUsuario(usuario.getId_usuario());

		TipoProceso tipoProceso = null;

		for (TipoProceso tp : tiposProceso) {
			if (tp.getId_tipo_proceso().intValue() == id_tipo_proceso) {
				tipoProceso = tp;
			}
		}

		// -------------- si el usuario no tiene el tipo de proceso no puede acceder

		if (tipoProceso == null) {
			return false;
		}

		// --------------- Si es persona natural o administrador del cliente verifica que el cliente debe tener un producto con el mismo tipo de proceso

		if (Constantes.PERSONA_NATURAL.equals(usuario.getTipo_persona()) || PersonaServicio.getInstance().esAdminCliente(usuario.getId_usuario(), id_persona)) {

			List<TipoProceso> tiposProcesoUsuario = PersonaServicio.getInstance().obtenerTiposProcesosPorIdUsuario(usuario.getId_usuario());

			for (TipoProceso tipoProcesoUsuario : tiposProcesoUsuario) {
				if (tipoProcesoUsuario.getId_tipo_proceso().intValue() == tipoProceso.getId_tipo_proceso()) {
					return true;
				}
			}

			return false;
		}

		// ---------------- si es un administrativo del cliente.. tiene acceso.

		Administrativo administrativo = AdministrativoServicio.getInstance().obtenerAdministrativosPor(usuario.getId_usuario(), id_persona);

		if (administrativo != null) {
			return true;
		}

		// ---------------- Si es persona juridica se verifica que tenga el delegado el tipo de proceso asignado

		List<TipoProceso> tipoProcesosDelegado = PersonaServicio.getInstance().obtenerTiposProcesosPorPersona(usuario.getId_usuario(), id_persona);

		TipoProceso tipoProcesoDelegado = null;

		for (TipoProceso tp : tipoProcesosDelegado) {
			if (tp.getId_tipo_proceso().intValue() == tipoProceso.getId_tipo_proceso()) {
				tipoProcesoDelegado = tp;
			}
		}

		// ---------------- Si el delegado no tiene el tipo de proceso asignado.. no tiene acceso
		if (tipoProcesoDelegado == null) {
			return false;
		}

		// ----------------- Si debe estar en un proceso.. se cuenta
		if (validarProceso) {
			return PersonaServicio.getInstance().contarProcesosUsuario(usuario.getId_usuario(), id_persona, id_tipo_proceso) > 0;
		}

		return true;

	}

	public static List<SeccionMenu> obtenerSeccionesMenu() {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			MenuDao menuDao = (MenuDao) daoManager.getDao(MenuDao.class);

			List<SeccionMenu> lista = menuDao.obtenerSeccionesMenu();

			return lista;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error en MenuServicio.obtenerSeccionesMenu", e);
			return null;
		}

	}

}