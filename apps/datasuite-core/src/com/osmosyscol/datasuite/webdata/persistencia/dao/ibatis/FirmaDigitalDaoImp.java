package com.osmosyscol.datasuite.webdata.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.itosmosys.firmadigital.dto.DocumentoFirmado;
import com.osmosyscol.datasuite.webdata.persistencia.dao.FirmaDigitalDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class FirmaDigitalDaoImp extends BaseSqlMapDao implements FirmaDigitalDao {

	public FirmaDigitalDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	public Boolean crearAuditoriaDocumento(DocumentoFirmado documentoFirmado) {
		
		insert("FirmaDigital.crearAuditoriaDocumento", documentoFirmado);
		
		return true;
	}

	public Boolean actualizarIDCargaParaDocumentoAuditoria(
			String id_documento_firmado, Integer id_carga) {
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_carga", id_carga);
		parametros.put("id_documento_firmado", id_documento_firmado);
		
		update("FirmaDigital.actualizarIDCargaParaDocumentoAuditoria", parametros);
		return true;
	}

	

	public Boolean tieneFormularioFirmadoPorFaseProceso(Integer id_carga,
			String fase) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_carga", id_carga);
		parametros.put("fase", fase);
		
		Integer cuantos =  (Integer)queryForObject("FirmaDigital.tieneFormularioFirmadoPorFaseProceso", parametros);
		
		if(cuantos>0){
			return true;
		}else{
			return false;
		}
	}

	public Boolean tieneDocumentoFirmadoPorFaseProceso(Integer id_carga,
			String fase) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_carga", id_carga);
		parametros.put("fase", fase);
		
		Integer cuantos =  (Integer)queryForObject("FirmaDigital.tieneDocumentoFirmadoPorFaseProceso", parametros);
		
		if(cuantos>0){
			return true;
		}else{
			return false;
		}
	}

}
