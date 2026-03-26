package com.osmosyscol.datasuite.webdata.correval.servicios;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.persistencia.dao.SQLDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.webdata.correval.webservice.bancos.BANCOSSOAPStub;
import com.osmosyscol.datasuite.webdata.correval.webservice.bancos.TipoElementoEntradabancos;
import com.osmosyscol.datasuite.webdata.correval.webservice.bancos.TipoElementoSalidabancos;
import com.osmosyscol.datasuite.webdata.correval.webservice.bancos.TipoEntradabancos;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;


public class BancoServicio {

	private static BancoServicio bancoServicio;
	private BancoServicio() {
	}

	public static BancoServicio getInstance() {
		if (bancoServicio == null) {
			bancoServicio = new BancoServicio();
		}
		return bancoServicio;
	}
	
	public TipoElementoSalidabancos[] obtenerBancos(){
		
		try {
			URL url;
			url = new URL(ParametrosInicio.getProperty("modelatos.endpoint")+"/ws/BANCOS");
			SimpleLogger.setDebug("url "+url);
			
			BANCOSSOAPStub bancossoapStub = new BANCOSSOAPStub(url, null);
			TipoEntradabancos tipoEntradabancos = new TipoEntradabancos();
			tipoEntradabancos.setElementoEntrada(new TipoElementoEntradabancos());
			TipoElementoSalidabancos[] listado = bancossoapStub.bancos(tipoEntradabancos);
		
			return listado;
		} catch (Exception e) {
			
			SimpleLogger.setError("Error", e);
		} 
		return null;
		
		
	}

	public List<Map<String,Object>> obtenerRespuestasBanco(Integer bancoGirador){
		try {
			
			String sql = "select distinct $ESTADO RESPUESTA BANCO.ESTADO INTERNO$ as RESPUESTA " +
					"from $ESTADO RESPUESTA BANCO$, $BANCO$ " +
					"where $ESTADO RESPUESTA BANCO.CODIGO BANCO$ = $BANCO.CODIGO$ " +
					"and $BANCO$.ID = " + bancoGirador;

			sql = RDServicio.reemplazarNombres(sql);

			SimpleLogger.setDebug("sql -> " + sql);

			SQLDao sqlDao = (SQLDao) DaoConfig.getDao(SQLDao.class, 2);

			List<Map<String,Object>> respuestas = sqlDao.selectSQL(sql);
			
			// desencriptar
			if(respuestas != null){
			
				List<Map<String, Object>> newret = new ArrayList<Map<String, Object>>();
	
				for (Map<String, Object> registro : respuestas) {
	
					Set<String> keys = registro.keySet();
	
					Map<String, Object> re = new HashMap<String, Object>();
	
					for (String key : keys) {
						re.put(RDServicio.getNombreCampo(key), Crypto.DF(registro.get(key)));
					}
	
					newret.add(re);
				}
	
				respuestas = newret;
			}
			
			return respuestas;
			
		}catch(Exception e){
			SimpleLogger.setError("Error al traer las respuestas del banco " + bancoGirador, e);
		}
		
		return null;
	}
	
}
