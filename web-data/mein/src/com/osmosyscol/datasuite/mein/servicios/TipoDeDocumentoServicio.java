package com.osmosyscol.datasuite.mein.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.mein.dtos.ParametroCatEmpresa;
import com.osmosyscol.datasuite.mein.dtos.ParametroTamEmpresa;
import com.osmosyscol.datasuite.mein.dtos.RangosTamEmpresa;
import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class TipoDeDocumentoServicio {
	
	private static TipoDeDocumentoServicio instance = null;
	private TipoDeDocumentoServicio(){
		
	}
	
	public static TipoDeDocumentoServicio getInstance(){
		if(instance == null){
			instance = new TipoDeDocumentoServicio();
		}
		
		return instance;
	}
	
	public List<TipoDeDocumento> getAllDocumentsType(){
		String sql = "select * from $tipo de documento$ tipo_documento where tipo_documento.$tipo de documento.id postal$ is not null";
		
		List<TipoDeDocumento> tiposDeDocumento = DS_SqlUtils.queryForList(TipoDeDocumento.class, sql);
		
		return tiposDeDocumento;
	}
	
	public Map<String, TipoDeDocumento> getMapForCodHts(){
		List<TipoDeDocumento> tipos = getAllDocumentsType();
		
		Map<String, TipoDeDocumento> mapaTipos = new HashMap<String, TipoDeDocumento>();
		
		for(TipoDeDocumento tipo: tipos) {
			mapaTipos.put(tipo.getCod_hts(), tipo);
		}
		
		return mapaTipos;
		
	}
}
