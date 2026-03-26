package com.osmosyscol.datasuite.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Estructura;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.logica.servicios.EstructuraServicio;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.CampoDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ListaValoresDao;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.TipoCampoDao;
import com.osmosyscol.datasuite.persistencia.dao.CreaDatosDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class CreaDatosServicio {

	private static CreaDatosServicio creadatosServicio;

	private CreaDatosServicio() {
	}

	public static CreaDatosServicio getInstance() {
		if (creadatosServicio == null) {
			creadatosServicio = new CreaDatosServicio();
		}
		return creadatosServicio;
	}

	// -------------------------------------------------------------

	public Boolean existeTabla(DaoManager daoManager, String nombre_tabla) {
		CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);
		return creaDatosDao.existeTabla(nombre_tabla.toUpperCase());
	}

	// -------------------------------------------------------------

	public Boolean crearTabla(DaoManager daoManager, Estructura estructura) {

		CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);

		return creaDatosDao.crearTabla(("T" + estructura.getId_estructura()).toUpperCase());

	}

	// ------------------------------------------------------------------

	public Boolean crearVista(DaoManager daoManager, Integer id_estructura) {

		DaoManager daoManagerData1 = DaoConfig.getDaoManager(1);
		CampoDao campoDao = (CampoDao) daoManagerData1.getDao(CampoDao.class);
		CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);

		List<Campo> camposlist = campoDao.obtenerCamposPorEstructuraOrdenId(id_estructura);

		boolean ret = creaDatosDao.crearVista(id_estructura.toString(), camposlist);

		if (ret) {
			List<Estructura> estructuras = EstructuraServicio.getInstance().obtenerEstructurasRelacionadas(id_estructura);
			if (estructuras != null) {
				for (Estructura estructura : estructuras) {
					if (estructura.getId_estructura().intValue() != id_estructura) {
						camposlist = campoDao.obtenerCamposPorEstructuraOrdenId(estructura.getId_estructura());
						creaDatosDao.crearVista(estructura.getId_estructura().toString(), camposlist);
					}
				}
			}
		}

		return ret;
	}

	// -------------------------------------------------------------

	public Boolean crearTabla(DaoManager daoManagerb, DaoManager daoManager, ListaValores listaValores) {
		try {

			if (!existeTabla(daoManager, "L" + listaValores.getId_lista_valores())) {

				TipoCampoDao tipoCampoDao = (TipoCampoDao) daoManagerb.getDao(TipoCampoDao.class);
				CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);

				TipoCampo tipocampo = tipoCampoDao.obtenerTipoCampo(listaValores.getId_tipocampo());

				return (creaDatosDao.crearTablaLista(("L" + listaValores.getId_lista_valores()).toUpperCase(), tipocampo));
			}

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return false;
	}

	// -------------------------------------------------------------

	public Boolean guardarCampo(DaoManager daoManagerb, DaoManager daoManager, Campo campo, TipoCampo tipoCampo, List<Campo> camposAnteriores) {

		if (campo != null && campo.getId_lista_valores() != null) {
			ListaValoresDao listaValoresDao = (ListaValoresDao) daoManagerb.getDao(ListaValoresDao.class);
			campo.setId_tipocampo(listaValoresDao.obtenerListaValores(campo.getId_lista_valores()).getId_tipocampo());
		}

		if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equals(campo.getMultiplicidad())) {
			if (!existeCampo(daoManager, campo)) {
				return crearCampo(daoManager, campo, tipoCampo);
			} else {
				return actualizarCampo(daoManager, campo, tipoCampo, camposAnteriores);
			}
		} else {

			if (!existeTabla(daoManager, "T" + campo.getId_estructura() + "C" + campo.getId_campo())) {
				return crearCampo(daoManager, campo, tipoCampo);
			} else {
				return actualizarCampo(daoManager, campo, tipoCampo, camposAnteriores);
			}

		}

	}

	// -------------------------------------------------------------

	private Boolean actualizarCampo(DaoManager daoManager, Campo campo, TipoCampo tipoCampo, List<Campo> camposAnteriores) {
		CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);
		return (creaDatosDao.actualizarCampo(campo, tipoCampo, camposAnteriores));
	}

	// -------------------------------------------------------------

	private Boolean crearCampo(DaoManager daoManager, Campo campo, TipoCampo tipoCampo) {
		CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);
		return (creaDatosDao.crearCampo(campo, tipoCampo));
	}

	// -------------------------------------------------------------

	private boolean existeCampo(DaoManager daoManager, Campo campo) {
		CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);
		return (creaDatosDao.existeCampo(("T" + campo.getId_estructura()).toUpperCase(), ("C" + campo.getId_campo()).toUpperCase()));
	}

	// -------------------------------------------------------------

	public Boolean eliminarCampo(DaoManager daoManager, Campo campo) {
		CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);
		return (creaDatosDao.eliminarCampo(campo));
	}

	public void eliminarVista(DaoManager daoManager, Integer id_estructura) {

		CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);
		DaoManager daoManagerData1 = DaoConfig.getDaoManager(1);
		CampoDao campoDao = (CampoDao) daoManagerData1.getDao(CampoDao.class);

		List<Estructura> estructuras = EstructuraServicio.getInstance().obtenerEstructurasRelacionadas(id_estructura);
		if (estructuras != null) {
			for (Estructura estructura : estructuras) {
				if (estructura.getId_estructura().intValue() != id_estructura) {

					List<Campo> camposlist = campoDao.obtenerCamposPorEstructuraOrdenId(estructura.getId_estructura().intValue());

					creaDatosDao.crearVistaDummy(estructura.getId_estructura(), camposlist);
				}
			}
		}
		
		String url = DaoConfig.getUrl(daoManager);

		if (url.indexOf("oracle") < 0) {
			creaDatosDao.eliminarVista(id_estructura);
		}

	}

	public void eliminarPKUnique(DaoManager daoManager, Integer id_estructura) {
		CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);
		creaDatosDao.eliminarPKUnique(id_estructura);
	}

	public void crearPKUnique(DaoManager daoManager, List<Campo> camposIdNuevos, Integer id_estructura) {
		CreaDatosDao creaDatosDao = (CreaDatosDao) daoManager.getDao(CreaDatosDao.class);
		creaDatosDao.crearPKUnique(camposIdNuevos, id_estructura);
	}

	// ---------------------------------------------------------

}
