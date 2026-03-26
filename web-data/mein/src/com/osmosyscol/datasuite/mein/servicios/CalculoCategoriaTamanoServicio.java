package com.osmosyscol.datasuite.mein.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.mein.dtos.ParametroCatEmpresa;
import com.osmosyscol.datasuite.mein.dtos.ParametroTamEmpresa;
import com.osmosyscol.datasuite.mein.dtos.RangosTamEmpresa;
import com.osmosyscol.datasuite.mein.logica.constantes.Constantes;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class CalculoCategoriaTamanoServicio {
	
	private static CalculoCategoriaTamanoServicio instance = null;
	private CalculoCategoriaTamanoServicio(){
		
	}
	
	public static CalculoCategoriaTamanoServicio getInstance(){
		if(instance == null){
			instance = new CalculoCategoriaTamanoServicio();
		}
		
		return instance;
	}
	
	public BigDecimal obtenerUnidadValorTributario(){
		String sql = "SELECT * FROM $PARAMETROS TAM EMPRESA$ WHERE $PARAMETROS TAM EMPRESA.NOMBRE PARAMETRO$ = #param#";
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("param", Constantes.UNIDAD_V_TRIBUTARIO);
		
		ParametroTamEmpresa parametro = DS_SqlUtils.queryForObject(ParametroTamEmpresa.class, sql, params);
		
		return parametro.getValor_parametro();
	}
	
	public BigDecimal obtenerSalarioMinimo(){
		String sql = "SELECT * FROM $PARAMETROS TAM EMPRESA$ WHERE $PARAMETROS TAM EMPRESA.NOMBRE PARAMETRO$ = #param#";
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("param", Constantes.SALARIO);
		
		ParametroTamEmpresa parametro = DS_SqlUtils.queryForObject(ParametroTamEmpresa.class, sql, params);
		
		return parametro.getValor_parametro();
	}
	
	public String obtenerCategoriaPorActivos(BigDecimal activos, Integer tipo_solicitud){
	    String sql = "SELECT * FROM $PARAMETROS CAT EMPRESA$ "
	        + "WHERE $PARAMETROS CAT EMPRESA.VALOR DESDE$ < $D(#param#)$ "
	        + "AND NVL($PARAMETROS CAT EMPRESA.VALOR HASTA$,$D(#param#)$) >= $D(#param#)$";
	    
	    Map<String, Object> params = new HashMap<String, Object>();
	    
	    params.put("param", activos);
	    
	    List<ParametroCatEmpresa> parametros = DS_SqlUtils.queryForList(ParametroCatEmpresa.class, sql, params);
	    
	    String grupo = "";

	    for(ParametroCatEmpresa parametro : parametros){
	      if (parametro.getTipoSolicitud() == null) grupo = parametro.getGrupo();
	      
	      if (tipo_solicitud.equals(parametro.getTipoSolicitud())) {
	        grupo = parametro.getGrupo();
	        break;
	      }
	      
	    }
	    
	    return grupo;
	}
	
	public Integer obtenerTamEmpresaPorUVTCalculado(BigDecimal uvtCalculado, Integer macrosector){
		
		String sql = "SELECT * FROM $RANGOS TAM EMPRESA$ WHERE $RANGOS TAM EMPRESA.MACROSECTOR$ = #macrosector#" ;
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("macrosector", macrosector);
		
		List<RangosTamEmpresa> rangos = DS_SqlUtils.queryForList(RangosTamEmpresa.class, sql, params);
		
		// si el macrosector no se encuentra en los que estan configurados, se deben traer los de manufactura
		if(rangos == null || rangos.isEmpty()){

			sql = "SELECT * FROM $RANGOS TAM EMPRESA$ WHERE $RANGOS TAM EMPRESA.MACROSECTOR$ = #macrosector#" ;
			
			params.put("macrosector", 3);
			
			rangos = DS_SqlUtils.queryForList(RangosTamEmpresa.class, sql, params);
		}
		
		for(RangosTamEmpresa rango : rangos){
			if(rango.getValor_hasta() == null){
				if(uvtCalculado.compareTo(rango.getValor_desde()) == 1){
					return rango.getTam();
				}
			}else{
				if(uvtCalculado.compareTo(rango.getValor_desde()) == 1 && (uvtCalculado.compareTo(rango.getValor_hasta()) == -1|| uvtCalculado.compareTo(rango.getValor_hasta()) == 0)){
					return rango.getTam();
				}
			}
		}
		
		return null;
	}
	
	public Integer calcularTamEmpresa (BigDecimal ingresos, Integer macrosector){
		BigDecimal calculo = ingresos.divide(obtenerUnidadValorTributario(), 10, RoundingMode.UP);
		
		return obtenerTamEmpresaPorUVTCalculado(calculo, macrosector);
	}
	
	public String calcularCatEmpresa(BigDecimal ingresos, Integer tipo_solicitud){
//		BigDecimal calculo = ingresos.divide(obtenerSalarioMinimo(),10, RoundingMode.UP);
		
		return obtenerCategoriaPorActivos(ingresos, tipo_solicitud);
	}
}
