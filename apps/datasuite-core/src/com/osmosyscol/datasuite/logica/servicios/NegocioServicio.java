package com.osmosyscol.datasuite.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.cocoon.components.language.markup.xsp.XSPUtil;
import org.apache.cocoon.environment.SourceResolver;
import org.apache.commons.collections.CollectionUtils;
import org.xml.sax.InputSource;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Negocio;
import com.osmosyscol.datasuite.modelatos.logica.clientes.ClienteWS;
import com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.NegociosArrayElement;
import com.osmosyscol.datasuite.modelatos.logica.clientes.listanegocios.TipoElementoSalidalista_negocios;
import com.osmosyscol.datasuite.persistencia.dao.AdministradorDao;
import com.osmosyscol.datasuite.persistencia.dao.NegocioDao;
import com.osmosyscol.datasuite.persistencia.dao.PersonaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class NegocioServicio {

	private static NegocioServicio negocioServicio;

	private NegocioServicio() {
	}

	public static NegocioServicio getInstance() {
		if (negocioServicio == null) {
			negocioServicio = new NegocioServicio();
		}
		return negocioServicio;
	}

	// ------------------------------

	public Negocio obtenerNegocio(Integer id_negocio) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);
			return negocioDao.obtenerNegocio(id_negocio);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Negocio> obtenerListadoNegocios() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);
			return negocioDao.obtenerListadoNegocios();
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}
	
	// ------------------------------

	public List<Negocio> obtenerListadoNegociosPorTipoProceso(Integer id_tipo_proceso) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);
			return negocioDao.obtenerListadoNegociosPorTipoProceso(id_tipo_proceso);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public List<Negocio> obtenerListadoNegociosActivos() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);
			return negocioDao.obtenerListadoNegociosActivos();
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Negocio guardarNegocio(Negocio negocio) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);
			if (negocio == null) {
				return null;
			}
			if (negocio.getId_modelo() == null) {
				negocio.setId_modelo(1);
			}
			if (negocio.getId_negocio() == null && negocioDao.obtenerNegocioPorCodigo(negocio.getCod_negocio()) != null ) {
				return null;
			}
			Negocio negocioRespuesta =  negocioDao.actualizarNegocio(negocio);
			
			actualizarNegociosEnCreadatos();
			
			return negocioRespuesta;
			

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}
	
	public Negocio obtenerNegocioPorCodigo(String cod_negocio){
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);
			return negocioDao.obtenerNegocioPorCodigo(cod_negocio);
		}catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null; 
	}

	public List<Negocio> obtenerListadoNegociosPorLogin(String login) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);

			if (login != null) {

				return negocioDao.obtenerListadoNegociosPorLogin(login);
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	// ----------------------------------------------------

	public List<Negocio> verNegociosVigentes() {

		List<Negocio> negocios = new ArrayList<Negocio>();

		// --------------------------- Obtiene Listado de Negocios
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager(1);
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);

			if (StringUtils.esNoVacio(ParametrosInicio.getProperty("sippagos.endpoint"))) {

				List<Integer> id_modelos = new ArrayList<Integer>();
				
				TipoElementoSalidalista_negocios[] negociosWS = ClienteWS.getInstance().obtenerListaNegocios();
				
				if(negociosWS != null){
					NegociosArrayElement[] listaNegocios = negociosWS[0].getNegocios();
					
					id_modelos.add(1);
					
					for (NegociosArrayElement negocio : listaNegocios) {
						
						Negocio negocio2 = new Negocio();
						
						negocio2.setActivo(Constantes.SI);
						negocio2.setCod_negocio(StringUtils.toString(negocio.getCodigo()));
						negocio2.setNombre(negocio.getDescripcion());
						negocio2.setId_modelo(1);
						
						negocios.add(negocio2);
						
					}
					
				}

				try {

					daoManager.startTransaction();

					for (Integer id_modelo : id_modelos) {
						negocioDao.marcarEliminadoNegociosPorModelo(id_modelo);

						for (Negocio negocio : negocios) {
							if (negocio.getId_modelo().intValue() == id_modelo.intValue()) {
								negocioDao.actualizarNegocio(negocio);
							}
						}
					}

					daoManager.commitTransaction();

				} catch (Exception e) {
					SimpleLogger.setError("Ha ocurrido un error", e);
				} finally {
					daoManager.endTransaction();
				}

			}

			return negocioDao.obtenerListadoNegocios();

			// -------------------------------------
		} catch (Exception e) {
			SimpleLogger.setError("Error en el servicio verNegociosVigentes.", e);
			return null;
		}

	}

	// ----------------------------------------------------

	public Boolean obtenerNegociosPorAdministrador(SourceResolver resolver, String login, Integer id_administrador) {

		List<String> codnegocios = new ArrayList<String>();

		// --------------------------- Obtiene Listado de Negocios

		try {
			String responseServiceXML = null;

			try {
				responseServiceXML = XSPUtil.getSourceContents("cocoon:/negocios/obtenerNegociosPorAdministrador.ws?login=" + login.toUpperCase(), null, resolver);
			} catch (Exception e) {
				SimpleLogger.setWarn("No se puede obtener Listado de Negocios del administrador, ha ocurrido un error al ejecutar el web service.", e);
			}

			if (responseServiceXML != null) {

				XPathFactory factory = XPathFactory.newInstance();
				XPath xPath = factory.newXPath();

				Integer numNegocios = new Integer(xPath.evaluate("count(//obtenerNegociosPorAdministrador/codigo_negocio)",
						new InputSource(StringUtils.toInputStream(responseServiceXML))).toString());

				if (numNegocios == 0) {

					SimpleLogger.setWarn("No se obtienen negocios asociados al usuario " + login + ", posiblemente el servicio no se encuentra disponible.");
					return false;
				}

				for (int i = 1; i <= numNegocios; i++) {

					String cod_negocio = xPath.evaluate("//obtenerNegociosPorAdministrador/codigo_negocio[position()=" + i + "]", new InputSource(StringUtils
							.toInputStream(responseServiceXML)));
					codnegocios.add(cod_negocio);
				}
			}

			// -------------------------------------

			DaoManager daoManager = DaoConfig.getDaoManager();
			AdministradorDao administradorDao = (AdministradorDao) daoManager.getDao(AdministradorDao.class);

			try {

				daoManager.startTransaction();

				administradorDao.eliminarAdministradorNegocioPorAdministrador(id_administrador);

				for (String cod_negocio : codnegocios) {
					administradorDao.agregarAdministradorNegocio(id_administrador, cod_negocio);
				}

				daoManager.commitTransaction();

			} catch (Exception e) {
				SimpleLogger.setError("Ha ocurrido un error inesperado.", e);
				return false;
			} finally {
				daoManager.endTransaction();
			}

			return true;

			// -------------------------------------

		} catch (Exception e) {
			SimpleLogger.setError("Error en el servicio verNegociosVigentes.", e);
			return false;
		}

	}

	public List<Negocio> obtenerListadoNegociosPorIdAdministrador(Integer id_administrador) {

		NegocioDao negocioDao = (NegocioDao) DaoConfig.getDao(NegocioDao.class);
		return negocioDao.obtenerListadoNegociosPorAdministrador(id_administrador);

	}

	// --------------

	public Boolean existeUsuarioNegocio(Integer id_usuario, Integer id_negocio) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);

			return negocioDao.guardarUsuarioNegocio(id_usuario, id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Error en el servicio guardarUsuarioNegocio.", e);

			return false;
		}
	}

	// --------------
	
	// --------------

		public Boolean guardarUsuarioNegocio(Integer id_usuario, Integer id_negocio) {

			try {
				DaoManager daoManager = DaoConfig.getDaoManager();
				NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);

				return negocioDao.guardarUsuarioNegocio(id_usuario, id_negocio);

			} catch (Exception e) {
				SimpleLogger.setError("Error en el servicio guardarUsuarioNegocio.", e);

				return false;
			}
		}

		// --------------

	public Boolean eliminarPersonaNegocio(Integer id_persona, Integer id_negocio) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			return negocioDao.eliminarUsuarioNegocio(personaDao.obtenerIdUsuario(id_persona), id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Error en el servicio eliminarPersonaNegocio.", e);

			return false;
		}
	}

	public Boolean eliminarUsuarioNegocio(Integer id_usuario, Integer id_negocio) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);

			return negocioDao.eliminarUsuarioNegocio(id_usuario, id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Error en el servicio eliminarUsuarioNegocio.", e);

			return false;
		}
	}

	// --------------

	public List<Negocio> obtenerNegociosPorUsuario(Integer id_usuario) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);

			return negocioDao.obtenerNegociosPorUsuario(id_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Error en el servicio obtenerNegociosPorUsuario.", e);

			return null;
		}
	}
	
	public List<Negocio> obtenerNegociosPorUsuarioFormato(Integer id_usuario, Integer id_formato_entrada) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);

			return negocioDao.obtenerNegociosPorUsuarioFormato(id_usuario, id_formato_entrada);

		} catch (Exception e) {
			SimpleLogger.setError("Error en el servicio obtenerNegociosPorUsuarioNegocio.", e);

			return null;
		}
	}
	
	

	// --------------

	public Boolean esPersonaAdministrador(Integer id_persona, Integer id_negocio) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);

			return negocioDao.esPersonaAdministrador(id_persona, id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Error en el servicio guardarUsuarioNegocio.", e);

			return false;
		}
	}

	// --------------

	public Boolean esPersonaCliente(Integer id_persona, Integer id_negocio) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);

			return negocioDao.esPersonaCliente(id_persona, id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Error en el servicio guardarUsuarioNegocio.", e);

			return false;
		}
	}

	// ------------

	public Boolean actualizarActivoNegocio(Integer id_negocio, String activo) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);
			try {

				daoManager.startTransaction();
				negocioDao.actualizarActivoNegocio(id_negocio, activo);
				daoManager.commitTransaction();
				
				actualizarNegociosEnCreadatos();

				return true;

			} catch (Exception e) {
				SimpleLogger.setError("Error en servicio actualizarActivoNegocio", e);
				return false;

			} finally {
				daoManager.endTransaction();
			}
		} catch (Exception e) {
			SimpleLogger.setError("Error en servicio actualizarActivoNegocio", e);
			return false;
		}
	}
	
	public Boolean eliminarNegocio(Integer id_negocio){
		
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);
			
			Boolean  respuesta = negocioDao.eliminarNegocio(id_negocio);
			
			actualizarNegociosEnCreadatos();
			
			return respuesta;
		} catch (Exception e) {
			SimpleLogger.setError("Error al eliminar el negocio: " + id_negocio,e);
		}
		return false;
	}
	
	public Boolean actualizarNegociosEnCreadatos(){

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			NegocioDao negocioDao = (NegocioDao) daoManager.getDao(NegocioDao.class);
			List<Negocio> negocios =  negocioDao.obtenerListadoNegocios();
			
			//Se actualizan cada uno de los negocios en Creadatos
			DaoManager daoManagerCreadatos = DaoConfig.getDaoManager(2);
			NegocioDao negocioDaoCreadatos = (NegocioDao) daoManagerCreadatos.getDao(NegocioDao.class);
			
			
			if(negocios!= null && negocios.size()>0){
				//Se inactivan todos los negocios en creadatos
				Boolean sonNegociosInactivos = negocioDaoCreadatos.inactivarTodosLosNegociosEnCreadatos();
				
				if(sonNegociosInactivos){
					//Se recorren todos los negocios y se actualiza/inserta segun sea el caso
					for (Negocio negocio : negocios) {
						
						if(negocioDaoCreadatos.existeNegocioEnCreadatos(negocio.getId_negocio())==0){
							
							//Si el negocio no existe se crea
							negocioDaoCreadatos.insertarNegocioEnCreadatos(negocio);
							
						}else{
							//De lo contrario se actualiza
							negocioDaoCreadatos.actualizarNegocioEnCreadatos(negocio);
							
						}
						
					}
					
				}
				
			}
			
			
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
		
		
		
	}

	public Boolean sincronizarNegociosSifi(){

		try {
			List<Negocio> lista = verNegociosVigentes();
			return CollectionUtils.isNotEmpty(lista);
		
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
		
	}
	
}
