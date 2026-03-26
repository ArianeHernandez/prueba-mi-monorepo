package com.osmosyscol.datasuite.modelatos.logica.servicios;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Persona;
import com.osmosyscol.datasuite.logica.servicios.PersonaServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.Variable;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.VariableDao;
import com.osmosyscol.datasuite.persistencia.dao.PersonaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class VariableServicio {

	private static VariableServicio variablesServicio;

	private VariableServicio() {

	}

	public static VariableServicio getInstance() {
		if (variablesServicio == null) {
			variablesServicio = new VariableServicio();
		}
		return variablesServicio;
	}

	// -----------------------------------------------

	public List<Variable> obtenerVariablesPorModeloId(Integer id_modelo, String tipo_persona, String identificacion, Integer tipo_documento) {

		List<Variable> listado = null;
		try {

			DaoManager daoManager = DaoConfig.getDaoManager();

			Persona persona = PersonaServicio.getInstance().obtenerPersonaPorIdentificacion(identificacion, tipo_documento);
			VariableDao variableDao = (VariableDao) daoManager.getDao(VariableDao.class);
			if (persona != null) {
				listado = variableDao.obtenerVariablesPorModeloId(id_modelo, persona.getId_persona());
			} else {
				listado = variableDao.obtenerVariablesPorModeloId(id_modelo, -1);
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return listado;
	}

	// -----------------------------------------------

	public boolean guardarVariableValor(Integer id_variable, Integer id_usuario, String valor) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			VariableDao variableDao = (VariableDao) daoManager.getDao(VariableDao.class);

			variableDao.eliminarVariableValor(id_variable, id_usuario);
			Variable variable = new Variable();
			variable.setId_variable(id_variable);
			variable.setValor_variable(valor);
			return variableDao.guardarVariableValor(variable, id_usuario);

		} catch (Exception e) {
			return false;
		}
	}

	// -----------------------------------------------

	public List<Variable> obtenerVariablesPorModelo(Integer id_modelo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			VariableDao variableDao = (VariableDao) daoManager.getDao(VariableDao.class);

			List<Variable> listado = variableDao.obtenerVariablesPorModelo(id_modelo);

			if (listado == null) {
				listado = new ArrayList<Variable>();
			}

			listado.add(new Variable(Constantes.VARIABLE_NOMBRE, "Nombre", "Nombre de la persona", null, null, Constantes.NO, Constantes.SI));
			listado.add(new Variable(Constantes.VARIABLE_APELLIDO, "Apellido", "Apellido de la persona", null, null, Constantes.NO, Constantes.SI));
			listado.add(new Variable(Constantes.VARIABLE_IDENTIFICACION, "Identificacion", "Identificación de la persona", null, null, Constantes.NO, Constantes.SI));
			listado.add(new Variable(Constantes.VARIABLE_TELEFONO, "Telefono", "Telefono de la persona", null, null, Constantes.SI, Constantes.SI));
			listado.add(new Variable(Constantes.VARIABLE_CORREO, "Correo Electronico", "Correo Electronico de la persona", null, null, Constantes.SI, Constantes.SI));
			listado.add(new Variable(Constantes.VARIABLE_DIRECCION, "Direccion de Recidencia", "Direccion de Reciodencia de la persona", null, null, Constantes.SI, Constantes.SI));
			listado.add(new Variable(Constantes.VARIABLE_FECHA, "Fecha", "Fecha del Sistema", null, null, Constantes.NO, Constantes.NO));

			return listado;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// -----------------------------------------------

	public List<Variable> obtenerVariablesPorPersonaModelo(Integer id_persona, Integer id_modelo, Integer numero_pagina) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			VariableDao variableDao = (VariableDao) daoManager.getDao(VariableDao.class);

			return variableDao.obtenerVariablesPorPersona(id_persona, id_modelo, numero_pagina);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public Integer totalVariablesPorPersona(Integer id_persona, Integer id_modelo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			VariableDao variableDao = (VariableDao) daoManager.getDao(VariableDao.class);

			return variableDao.totalVariablesPorPersona(id_persona, id_modelo);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	public Variable obtenerVariable(Integer id_variable) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			VariableDao variableDao = (VariableDao) daoManager.getDao(VariableDao.class);

			return variableDao.obtenerVariable(id_variable);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------
	public Boolean guardarVariable(Variable variable, Integer id_modelo) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			VariableDao variableDao = (VariableDao) daoManager.getDao(VariableDao.class);

			variable.setId_modelo(id_modelo);

			return variableDao.guardarVariable(variable);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	// ------------------------------

	public String obtenerValorVariable(Integer id_variable, Integer id_persona) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			VariableDao variableDao = (VariableDao) daoManager.getDao(VariableDao.class);
			PersonaDao personaDao = (PersonaDao) daoManager.getDao(PersonaDao.class);

			if (id_variable < 0) {

				Persona persona = personaDao.obtenerPersona(id_persona);

				if (Constantes.VARIABLE_NOMBRE.intValue() == id_variable) {
					return persona.getNombre();
				}

				if (Constantes.VARIABLE_APELLIDO.intValue() == id_variable) {
					return persona.getApellido();
				}

				if (Constantes.VARIABLE_CORREO.intValue() == id_variable) {
					return persona.getCorreo();
				}

				if (Constantes.VARIABLE_DIRECCION.intValue() == id_variable) {
					return persona.getDireccion();
				}

				if (Constantes.VARIABLE_IDENTIFICACION.intValue() == id_variable) {
					return persona.getIdentificacion();
				}

				if (Constantes.VARIABLE_FECHA.intValue() == id_variable) {
					return "" + System.currentTimeMillis();
				}

				if (Constantes.VARIABLE_LOGIN.intValue() == id_variable) {
					// TODO: MUERTE
					// return persona.getLogin();
				}

				if (Constantes.VARIABLE_TELEFONO.intValue() == id_variable) {
					return persona.getTelefono();
				}

				return null;
			}

			return variableDao.obtenerValorVariable(id_variable, id_persona);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public List<Variable> obtenerVariablesPorPersona(Integer id_persona) {
		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			VariableDao variableDao = (VariableDao) daoManager.getDao(VariableDao.class);

			return variableDao.obtenerVariablesPorPersona(id_persona);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

}
