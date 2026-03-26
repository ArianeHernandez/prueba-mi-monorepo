package com.osmosyscol.datasuite.webdata.persistencia.dao;

import com.itosmosys.firmadigital.dto.DocumentoFirmado;

public interface FirmaDigitalDao {

	public Boolean crearAuditoriaDocumento(DocumentoFirmado documentoFirmado);
	
	public Boolean actualizarIDCargaParaDocumentoAuditoria(String id_documento_firmado, Integer id_carga);
	
	public Boolean tieneFormularioFirmadoPorFaseProceso(Integer id_carga, String fase);
	
	public Boolean tieneDocumentoFirmadoPorFaseProceso(Integer id_carga, String fase);
	
	
}
