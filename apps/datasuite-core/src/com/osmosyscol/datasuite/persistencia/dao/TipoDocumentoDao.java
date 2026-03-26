package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.TipoDocumento;

public interface TipoDocumentoDao {

	public TipoDocumento buscarTipoDocumentoG3A(String tipoDocumentoG3A);

	public String getNombreTipoDocumento(Integer id);
	
	public List<TipoDocumento> getListaTipoDocumento();
	
	public TipoDocumento getTipoDocumentoCodigo(String codigo);
	
}
