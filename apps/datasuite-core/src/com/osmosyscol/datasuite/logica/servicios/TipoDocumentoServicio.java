package com.osmosyscol.datasuite.logica.servicios;

import java.util.List;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.dto.TipoDocumento;
import com.osmosyscol.datasuite.persistencia.dao.TipoDocumentoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class TipoDocumentoServicio {

	private static TipoDocumentoServicio instance;

	private TipoDocumentoServicio() {
	}

	public static TipoDocumentoServicio getInstance() {
		if (instance == null) {
			instance = new TipoDocumentoServicio();
		}
		return instance;
	}

	public TipoDocumento buscarTipoDocumentoG3A(String tipoDocumentoG3A) {

		try {

			TipoDocumentoDao tipoDocumentoDao = (TipoDocumentoDao) DaoConfig.getDao(TipoDocumentoDao.class);

			return tipoDocumentoDao.buscarTipoDocumentoG3A(tipoDocumentoG3A);

		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error.", e);
		}
		return null;
	}

	public List<TipoDocumento> listarTiposDocumento() {
		// code ----
		try {

			TipoDocumentoDao tipoDocumentoDao = (TipoDocumentoDao) DaoConfig.getDao(TipoDocumentoDao.class);

			return tipoDocumentoDao.getListaTipoDocumento();

		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error.", e);
		}
		return null;
	}

	public TipoDocumento obtenerTipoDocumento(int id_tipo_documento) {

		List<TipoDocumento> tiposDocumento = listarTiposDocumento();
		for (TipoDocumento tipoDocumento : tiposDocumento) {
			if (tipoDocumento.getId() == id_tipo_documento) {
				return tipoDocumento;
			}
		}

		return null;
	}

	public TipoDocumento obtenerTipoDocumentoCodigo(String codigo) {
		try {
			TipoDocumentoDao tipoDocumentoDao = (TipoDocumentoDao) DaoConfig.getDao(TipoDocumentoDao.class);
			return tipoDocumentoDao.getTipoDocumentoCodigo(codigo);

		} catch (Throwable e) {
			SimpleLogger.setError("Ha ocurrido un error.", e);
		}
		return null;
	}

}
