package com.osmosyscol.datasuite.logica.servicios;

import java.util.List;

import org.apache.cocoon.environment.Request;
import org.apache.cocoon.environment.SourceResolver;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Credencial;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.dto.UsuarioNegocio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Variable;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.VariableDao;
import com.osmosyscol.datasuite.persistencia.dao.UsuarioDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class UsuarioServicio {

	private static UsuarioServicio instance;

	private UsuarioServicio() {
	}

	public static UsuarioServicio getInstance() {
		if (instance == null) {
			instance = new UsuarioServicio();
		}
		return instance;
	}

	public List<UsuarioNegocio> obtenerUsuariosPorNegocio(Integer id_negocio, Integer pagina) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();

			UsuarioDao dao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);
			return dao.obtenerUsuariosPorNegocio(id_negocio, pagina);

		} catch (Exception e) {
			SimpleLogger.setError("Error en obtenerUsuariosPorNegocio", e);
		}

		return null;
	}

	public Integer contarUsuariosPorNegocio(Integer id_negocio) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();

			UsuarioDao dao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);
			return dao.contarUsuariosPorNegocio(id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Error en obtenerUsuariosPorNegocio", e);
		}

		return null;
	}

	public UsuarioNegocio obtenerUsuarioPorIdentificacionNegocio(String tipo_persona, String identificacion, Integer id_negocio, Integer tipo_documento) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();

			UsuarioDao dao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);
			return dao.obtenerUsuarioPorIdentificacionNegocio(tipo_persona, identificacion, id_negocio, tipo_documento);

		} catch (Exception e) {
			SimpleLogger.setError("Error en obtenerUsuariosPorIdentificacion", e);
		}
		return null;
	}

	public Boolean activarUsuario(Integer id_usuario, String activar) {
		try {
			// Validar que sea S o N
			if (StringUtils.isEmpty(activar) || StringUtils.isEmpty(activar) || activar.length() > 1 || !StringUtils.containsOnly(activar, "SN")) {
				return false;
			}

			DaoManager daoManager = DaoConfig.getDaoManager();

			UsuarioDao dao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);
			return dao.activarUsuario(id_usuario, activar);

		} catch (Exception e) {
			SimpleLogger.setError("Error en activarUsuario", e);
		}
		return false;
	}

	public Boolean eliminarUsuarioNegocio(Integer id_usuario, Integer id_negocio, Integer id_persona) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();

			UsuarioDao dao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);

			Credencial credencial = PersonaServicio.getInstance().obtenerCredencialPersonaUsuario(id_persona, id_usuario);

			Boolean rta = dao.eliminarUsuarioNegocio(id_usuario, id_negocio);

			if (rta && credencial != null) {

				rta = com.osmosyscol.commons.servicio.UsuarioServicio.getInstance().eliminarUsuario(credencial.getLogin());
			}

			return rta;

		} catch (Exception e) {
			SimpleLogger.setError("Error en eliminarUsuarioNegocio", e);
		}
		return false;

	}

	public Integer guardarUsuarioTrans(Usuario usuario, DaoManager daoManager) {

		try {
			if (!validarUsuario(usuario)) {
				return -1;
			}
			if (daoManager == null) {
				daoManager = DaoConfig.getDaoManager();
			}

			usuario.setActivo("S");

			UsuarioDao dao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);
			return dao.guardarUsuario(usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
		}
		return null;

	}

	public Integer guardarUsuario(Usuario usuario) {

		return guardarUsuarioTrans(usuario, null);

	}

	public Integer almacenarUsuarioNegocio(Usuario usuario, List<Variable> variables, Persona persona, Integer id_negocio) {
		return guardarUsuarioNegocio(usuario, variables, persona, id_negocio, null, null);
	}

	public Integer guardarUsuarioNegocio(Usuario usuario, List<Variable> variables, Persona persona, Integer id_negocio, Credencial credencial, SourceResolver resolver) {
		DaoManager daoManager = null;
		try {

			Integer id_persona;
			daoManager = DaoConfig.getDaoManager();
			daoManager.startTransaction();

			persona = PersonaServicio.getInstance().guardarPersona(persona, daoManager);
			if (persona == null) {
				return 2;
			}

			id_persona = persona.getId_persona();
			usuario.setId_persona(id_persona);
			Integer id_usuario = guardarUsuarioTrans(usuario, daoManager);

			if (id_persona > 0 && id_usuario != null && id_usuario > 0) {

				Boolean rta_var = guardarVariablesTrans(variables, id_usuario, daoManager);

				Boolean rta_usua_neg = guardarUsuarioNegocioSimpleTrans(id_usuario, id_negocio, daoManager);

				if (!rta_var) {
					return 4; // No es posible guardar las variables
				} else if (!rta_usua_neg) {
					return 5; // No es posible guardar la relación usuario
					// negocio
				}

				if (credencial != null && StringUtils.isNotEmpty(credencial.getLogin())) {

					credencial.setId_persona(id_persona);
					credencial.setId_usuario(id_usuario);
					credencial = PersonaServicio.getInstance().guardarCredencial(credencial, daoManager);

					if (credencial == null) {
						return 6; // No es posible guardar la credencial
					}

					int[] roles = PersonaServicio.getInstance().obtenerRoles("S", null, null, null, "CN");

					Boolean rtaCorreo = true;
					// Si NO usa sobreflex se envia correo
					if (!com.osmosyscol.commons.utils.StringUtils.esVerdad(ParametrosInicio.getProperty("UsoSobreflex"))) {
						rtaCorreo = PersonaServicio.getInstance().generarUsuarioyEnviarCorreo(persona, roles, resolver, true, credencial);
					}

					if (!rtaCorreo) {
						SimpleLogger.setError("PersonaServicio.guardarPersona");
						return 7; // Error enviando correo
					}

				}

				daoManager.commitTransaction();

				return 1; // Exito

			} else {
				if (id_persona <= 0) {
					return 2; // no se guarda la persona
				}
				return 3; // No se guarda el usuario
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuarioNegocio", e);
			return 8;
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
	}

	public Integer guardarUsuarioVariosNegocios(Usuario usuario, List<Variable> variables, Persona persona, List<Integer> negocios, Credencial credencial, SourceResolver resolver, Request request) {
		DaoManager daoManager = null;
		try {

			Integer id_persona;
			daoManager = DaoConfig.getDaoManager();
			daoManager.startTransaction();

			persona = PersonaServicio.getInstance().guardarPersona(persona, daoManager);
			if (persona == null) {
				return 2;
			}

			id_persona = persona.getId_persona();
			usuario.setId_persona(id_persona);
			Integer id_usuario = guardarUsuarioTrans(usuario, daoManager);

			if (id_persona > 0 && id_usuario != null && id_usuario > 0) {

				Boolean rta_var = guardarVariablesTrans(variables, id_usuario, daoManager);

				// Se insertan las relaciones con cada uno de los negocios
				Boolean rta_usua_neg = true;

				if (negocios != null && negocios.size() > 0) {
					for (Integer id_negocio : negocios) {
						if (id_negocio != null) {
							Boolean esGuardadaRelacion = guardarUsuarioNegocioSimpleTrans(id_usuario, id_negocio, daoManager);

							if (!esGuardadaRelacion) {
								rta_usua_neg = false;

							}
						}

					}
				}

				if (!rta_var) {
					return 4; // No es posible guardar las variables
				} else if (!rta_usua_neg) {
					return 5; // No es posible guardar la relación usuario
					// negocio
				}

				if (credencial != null && StringUtils.isNotEmpty(credencial.getLogin())) {

					credencial.setId_persona(id_persona);
					credencial.setId_usuario(id_usuario);
					credencial = PersonaServicio.getInstance().guardarCredencial(credencial, daoManager);

					if (credencial == null) {
						return 6; // No es posible guardar la credencial
					}

					int[] roles = PersonaServicio.getInstance().obtenerRoles("S", null, null, null, "CN");

					// Si NO usa sobreflex se envia correo
					if (!com.osmosyscol.commons.utils.StringUtils.esVerdad(ParametrosInicio.getProperty("UsoSobreflex"))) {
						Boolean rtaCorreo = PersonaServicio.getInstance().generarUsuarioyEnviarCorreo(persona, roles, resolver, true, credencial);
						if (!rtaCorreo) {
							SimpleLogger.setError("PersonaServicio.guardarPersona");
							return 7; // Error enviando correo
						}
					} else {

						String clave = com.osmosyscol.commons.utils.StringUtils.randomString(5);

						com.osmosyscol.commons.servicio.UsuarioServicio.getInstance().guardarUsuario(credencial.getLoginUsuario(), clave, roles);
						PersonaServicio.getInstance().colocarCambioClave(credencial.getLoginUsuario());

						SimpleLogger.setInfo("El usuario usa sobreflex por ende no se envia correo");

					}

				}

				daoManager.commitTransaction();

				return 1; // Exito

			} else {
				if (id_persona <= 0) {
					return 2; // no se guarda la persona
				}
				return 3; // No se guarda el usuario
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuarioNegocio", e);
			return 8;
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}
	}

	public Boolean guardarVariablesTrans(List<Variable> variables, Integer id_usuario, DaoManager daoManager) {

		try {
			if (daoManager == null) {
				daoManager = DaoConfig.getDaoManager();
			}
			VariableDao variableDao = (VariableDao) daoManager.getDao(VariableDao.class);

			variableDao.eliminarValoresVariables(id_usuario);
			if (variables != null && id_usuario != null) {
				for (Variable variable : variables) {
					variableDao.guardarVariableValor(variable, id_usuario);
				}
			}
			return true;
		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
		}
		return false;

	}

	public Boolean guardarVariables(List<Variable> variables, Integer id_usuario) {

		return guardarVariablesTrans(variables, id_usuario, null);

	}

	public Boolean guardarUsuarioNegocioSimpleTrans(Integer id_usuario, Integer id_negocio, DaoManager daoManager) {

		try {

			if (daoManager == null) {
				daoManager = DaoConfig.getDaoManager();
			}

			UsuarioDao dao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);
			return dao.guardarUsuarioNegocio(id_usuario, id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
		}
		return false;

	}

	public Boolean eliminarTodasRelacionesUsuarioNegocioSimpleTrans(Integer id_usuario, DaoManager daoManager) {

		try {

			if (daoManager == null) {
				daoManager = DaoConfig.getDaoManager();
			}

			UsuarioDao dao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);
			return dao.eliminarTodasRelacionesUsuarioNegocio(id_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
		}
		return false;

	}

	public Boolean guardarUsuarioNegocioSimple(Integer id_usuario, Integer id_negocio) {

		return guardarUsuarioNegocioSimpleTrans(id_usuario, id_negocio, null);
	}

	public List<Usuario> obtenerUsuariosPorAdminCliente(Integer id_persona, Integer id_negocio) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();

			UsuarioDao usuarioDao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);

			return usuarioDao.obtenerUsuariosPorAdminCliente(id_persona, id_negocio);

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
			return null;
		}

	}

	public List<Usuario> obtenerUsuariosActivos() {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();

			UsuarioDao usuarioDao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);

			return usuarioDao.obtenerUsuariosActivos();

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
			return null;
		}
	}

	public Usuario obtenerUsuarioPorLogin(String login) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();

			UsuarioDao usuarioDao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);

			return usuarioDao.obtenerUsuarioPorLogin(login);

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
			return null;
		}
	}

	public Integer actualizarUsuarioNegocio(Usuario usuario, List<Variable> variables, Persona persona, Credencial credencial, SourceResolver resolver) {

		DaoManager daoManager = null;

		try {
			SimpleLogger.setInfo("Actualizando usuario...");

			Integer id_persona;
			daoManager = DaoConfig.getDaoManager();
			daoManager.startTransaction();

			persona = PersonaServicio.getInstance().guardarPersona(persona, daoManager);
			if (persona == null) {
				return 2;
			}
			id_persona = persona.getId_persona();
			usuario.setId_persona(id_persona);
			Integer id_usuario = guardarUsuarioTrans(usuario, daoManager);

			if (id_persona > 0 && id_usuario != null && id_usuario > 0) {

				Boolean rta_var = guardarVariablesTrans(variables, id_usuario, daoManager);

				if (!rta_var) {
					return 4; // No es posible guardar las variables
				}

				if (credencial != null && StringUtils.isNotEmpty(credencial.getLogin())) {

					credencial.setId_persona(id_persona);
					credencial.setId_usuario(id_usuario);
					credencial = PersonaServicio.getInstance().guardarCredencial(credencial, daoManager);

					if (credencial == null) {
						return 6; // No es posible guardar la credencial
					}

					int[] roles = PersonaServicio.getInstance().obtenerRoles("S", null, null, null, "CN");

					Boolean rtaCorreo = true;
					if (!com.osmosyscol.commons.utils.StringUtils.esVerdad(ParametrosInicio.getProperty("UsoSobreflex"))) {
						rtaCorreo = PersonaServicio.getInstance().generarUsuarioyEnviarCorreo(persona, roles, resolver, true, credencial);

					}

					if (!rtaCorreo) {
						SimpleLogger.setError("PersonaServicio.guardarPersona");
						return 7; // Error enviando correo
					}

				}

				daoManager.commitTransaction();
				return 1; // Exito

			}

			else {
				if (id_persona <= 0) {
					return 2; // no se guarda la persona
				}
				return 3; // No se guarda el usuario
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuarioNegocio", e);
			return 8;
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}

	}

	public Integer actualizarUsuarioVariosNegocio(Usuario usuario, List<Variable> variables, Persona persona, List<Integer> negocios, Credencial credencial, SourceResolver resolver) {

		DaoManager daoManager = null;

		try {

			Integer id_persona;
			daoManager = DaoConfig.getDaoManager();
			daoManager.startTransaction();

			persona = PersonaServicio.getInstance().guardarPersona(persona, daoManager);
			if (persona == null) {
				return 2;
			}
			id_persona = persona.getId_persona();
			usuario.setId_persona(id_persona);
			Integer id_usuario = guardarUsuarioTrans(usuario, daoManager);

			if (id_persona > 0 && id_usuario != null && id_usuario > 0) {

				Boolean rta_var = guardarVariablesTrans(variables, id_usuario, daoManager);

				// Se eliman todas la relaciones con los negocios
				Boolean rta_elimina_usua_neg = false;
				rta_elimina_usua_neg = eliminarTodasRelacionesUsuarioNegocioSimpleTrans(id_usuario, daoManager);

				// Se insertan las nuevas relaciones con cada uno de los
				// negocios
				Boolean rta_usua_neg = true;

				if (rta_elimina_usua_neg) {

					if (negocios != null && negocios.size() > 0) {
						for (Integer id_negocio : negocios) {
							if (id_negocio != null) {
								Boolean esGuardadaRelacion = guardarUsuarioNegocioSimpleTrans(id_usuario, id_negocio, daoManager);

								if (!esGuardadaRelacion) {
									rta_usua_neg = false;

								}
							}

						}
					}
				} else {
					SimpleLogger.setError("NO se pueden eliminar las relaciones usuario_negocio");
					rta_usua_neg = false;
				}

				if (!rta_var) {
					return 4; // No es posible guardar las variables
				} else if (!rta_usua_neg) {
					return 5; // No es posible guardar la relación usuario
					// negocio
				}

				if (credencial != null && StringUtils.isNotEmpty(credencial.getLogin())) {

					credencial.setId_persona(id_persona);
					credencial.setId_usuario(id_usuario);
					credencial = PersonaServicio.getInstance().guardarCredencial(credencial, daoManager);

					if (credencial == null) {
						return 6; // No es posible guardar la credencial
					}

					int[] roles = PersonaServicio.getInstance().obtenerRoles("S", null, null, null, "CN");

					// Si la persona NO usa sobreflex se envia correo
					if (!com.osmosyscol.commons.utils.StringUtils.esVerdad(ParametrosInicio.getProperty("UsoSobreflex"))) {
						Boolean rtaCorreo = PersonaServicio.getInstance().generarUsuarioyEnviarCorreo(persona, roles, resolver, true, credencial);
						if (!rtaCorreo) {
							SimpleLogger.setError("PersonaServicio.guardarPersona");
							return 7; // Error enviando correo
						}
					} else {
						com.osmosyscol.commons.servicio.UsuarioServicio.getInstance().guardarUsuario(credencial.getLoginUsuario(), StringUtils.randomString(5), roles);
					}

				}

				daoManager.commitTransaction();
				return 1; // Exito

			}

			else {
				if (id_persona <= 0) {
					return 2; // no se guarda la persona
				}
				return 3; // No se guarda el usuario
			}

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuarioNegocio", e);
			return 8;
		} finally {
			if (daoManager != null) {
				daoManager.endTransaction();
			}
		}

	}

	public Boolean validarUsuario(Usuario usuario) {

		if (usuario == null) {
			return false;
		}

		return true;
	}

	public Usuario obtenerUsuario(Integer id_usuario) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();

			UsuarioDao usuarioDao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);

			return usuarioDao.obtenerUsuario(id_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
			return null;
		}
	}

	public List<Usuario> buscarUsuariosActivos(String texto, String tipo_cliente) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			UsuarioDao usuarioDao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);

			return usuarioDao.buscarUsuariosActivos(texto, tipo_cliente);

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
			return null;
		}
	}

	public List<Usuario> obtenerUsuariosSinCredenciales(Integer numPag) {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			UsuarioDao usuarioDao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);

			return usuarioDao.obtenerUsuariosSinCredenciales(numPag);
		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
		}
		return null;
	}

	public Integer contarUsuariosSinCredenciales() {
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			UsuarioDao usuarioDao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);

			return usuarioDao.contarUsuariosSinCredenciales();
		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
		}
		return null;
	}

	public Boolean desactivarUsuarios(DaoManager daoManager) {
		try {

			UsuarioDao usuarioDao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);
			return usuarioDao.desactivarUsuarios();

		} catch (Exception e) {
			SimpleLogger.setError("Error en guardarUsuario", e);
		}
		return false;
	}

	public Usuario obtenerUsuarioPorDocumento(String documento) {
		return obtenerUsuarioPorIdentificacion(documento, null);
	}

	public Usuario obtenerUsuarioPorIdentificacion(String documento, Integer tipo_documento) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			UsuarioDao usuarioDao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);
			return usuarioDao.obtenerUsuarioPorIdentificacion(documento, tipo_documento);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public List<Usuario> buscarUsuariosSinCredenciales(String texto) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			UsuarioDao usuarioDao = (UsuarioDao) daoManager.getDao(UsuarioDao.class);

			return usuarioDao.buscarUsuariosSinCredenciales(texto);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

}
