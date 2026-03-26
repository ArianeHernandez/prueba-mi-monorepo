package com.osmosyscol.datasuite.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.cocoon.components.language.markup.xsp.XSPUtil;
import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.SourceResolver;
import org.xml.sax.InputSource;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Administrador;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Negocio;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.persistencia.dao.AdministradorDao;
import com.osmosyscol.datasuite.persistencia.dao.NegocioDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class AdministradorServicio {

	private static AdministradorServicio administradorServicio;

	private AdministradorServicio() {
	}

	public static AdministradorServicio getInstance() {
		if (administradorServicio == null) {
			administradorServicio = new AdministradorServicio();
		}
		return administradorServicio;
	}

	// ----------------------------------------------------

	public Administrador obtenerAdministradorPorLogin(String login) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministradorDao administradorDao = (AdministradorDao) daoManager.getDao(AdministradorDao.class);

			return administradorDao.obtenerAdministradorPorLogin(login);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un Error en el servicio obtenerAdministradorPorLogin.", e);
		}

		return null;

	}

	// ----------------------------------------------------

	public List<Administrador> obtenerAdministradoresPorNegocio(Integer id_negocio, SourceResolver resolver, Request request) {

		List<Administrador> administradores = new ArrayList<Administrador>();

		// --------------------------- Obtiene Listado de Administradores

		try {

			if (StringUtils.esNoVacio(ParametrosInicio.getProperty("sippagos.endpoint"))) {

				NegocioDao negocioDao = (NegocioDao) DaoConfig.getDao(NegocioDao.class);

				Negocio negocio = negocioDao.obtenerNegocio(id_negocio);

				String responseServiceXML = null;

				try {
					responseServiceXML = XSPUtil.getSourceContents("cocoon:/administrador/obtenerAdministradoresPorNegocio.ws?id_negocio=" + negocio.getCod_negocio(), null, resolver);
				} catch (Exception e) {
					SimpleLogger.setWarn("No se puede obtener el listado de administradores, ha ocurrido un error al ejecutar el web service.", e);
				}

				if (responseServiceXML != null) {

					XPathFactory factory = XPathFactory.newInstance();
					XPath xPath = factory.newXPath();

					Integer numAdministradores = new Integer(xPath.evaluate("count(//obtenerAdministradoresPorNegocio/Administrador)", new InputSource(StringUtils.toInputStream(responseServiceXML))).toString());

					for (int i = 1; i <= numAdministradores; i++) {

						Administrador administrador = new Administrador();

						String path = "//obtenerAdministradoresPorNegocio/Administrador[position()=" + i + "]";

						administrador.setLogin(xPath.evaluate(path + "/login", new InputSource(StringUtils.toInputStream(responseServiceXML))).toLowerCase());

						// -- obtiene la informacion de la persona admin

						Persona persona = new Persona();

						persona.setNombre(xPath.evaluate(path + "/nombre", new InputSource(StringUtils.toInputStream(responseServiceXML))));
						persona.setApellido(xPath.evaluate(path + "/apellido", new InputSource(StringUtils.toInputStream(responseServiceXML))));
						persona.setCorreo(xPath.evaluate(path + "/correo", new InputSource(StringUtils.toInputStream(responseServiceXML))));
						persona.setIdentificacion(xPath.evaluate(path + "/identificacion", new InputSource(StringUtils.toInputStream(responseServiceXML))));
						persona.setTelefono(xPath.evaluate(path + "/telefono", new InputSource(StringUtils.toInputStream(responseServiceXML))));
						persona.setDireccion(xPath.evaluate(path + "/direccion", new InputSource(StringUtils.toInputStream(responseServiceXML))));
						persona.setGenero(xPath.evaluate(path + "/genero", new InputSource(StringUtils.toInputStream(responseServiceXML))));

						persona.setTipo_documento(Constantes.TIPODOCUMENTO_CEDULA);
						persona.setTipo_persona("N");

						if (!"F".equalsIgnoreCase(persona.getGenero())) {
							persona.setGenero("M");
						}

						if (persona.getIdentificacion() == null || persona.getIdentificacion().trim().length() == 0) {
							persona.setIdentificacion(administrador.getLogin());
						}

						if (persona.getNombre() == null || persona.getNombre().trim().length() == 0) {
							persona.setNombre(administrador.getLogin());
						}

						if (persona.getApellido() == null || persona.getApellido().trim().length() == 0) {
							persona.setApellido(".");
						}

						administrador.setPersona(persona);
						administrador.setActivo(Constantes.SI);

						administradores.add(administrador);

					}
				}

				// -------------------------------------

				DaoManager daoManager = DaoConfig.getDaoManager();
				AdministradorDao administradorDao = (AdministradorDao) daoManager.getDao(AdministradorDao.class);

				try {

					daoManager.startTransaction();
					administradorDao.eliminarAdministradorNegocioPorNegocio(id_negocio);
					daoManager.commitTransaction();
				} catch (Exception e) {
					SimpleLogger.setError("Ha ocurrido un error", e);
				} finally {
					daoManager.endTransaction();
				}

				for (Administrador administrador : administradores) {
					Credencial credencial = new Credencial();
					credencial.setLogin(administrador.getLogin());
					Persona persona = PersonaServicio.getInstance().obtenerPersonaPorLogin(administrador.getLogin());
					if (persona != null) {
						administrador.getPersona().setId_persona(persona.getId_persona());
						credencial.setId_persona(persona.getId_persona());
					}

					PersonaServicio.getInstance().guardarDirectorNegocio(administrador.getPersona(), credencial, id_negocio, resolver);
				}

			}

			AdministradorDao administradorDaoConsulta = (AdministradorDao) DaoConfig.getDaoManager().getDao(AdministradorDao.class);

			return administradorDaoConsulta.obtenerListadoAdministradoresPorNegocio(id_negocio);

			// -------------------------------------

		} catch (Exception e) {
			SimpleLogger.setError("Error en el servicio obtenerAdministradoresPorNegocio.", e);
			return null;
		}

	}

	// --------------------

	public Boolean actualizarActivoAdministrador(Integer id_administrador, String activo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministradorDao administradorDao = (AdministradorDao) daoManager.getDao(AdministradorDao.class);
			try {

				daoManager.startTransaction();
				administradorDao.actualizarActivoAdministrador(id_administrador, activo);
				daoManager.commitTransaction();

				return true;

			} catch (Exception e) {
				SimpleLogger.setError("Error en servicio actualizarActivoAdministrador", e);
				return false;

			} finally {
				daoManager.endTransaction();
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio actualizarActivoAdministrador", e);
			return false;
		}
	}

	public Administrador obtenerDirectorNegocioIdentificacionNegocio(String identificacion, String tipo_persona, Integer id_negocio) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			AdministradorDao dao = (AdministradorDao) daoManager.getDao(AdministradorDao.class);
			return dao.obtenerDirectorNegocioIdentificacionNegocio(tipo_persona, identificacion, id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Error en obtenerDirectorNegocioIdentificacionNegocio", e);
		}

		return null;

	}

}
