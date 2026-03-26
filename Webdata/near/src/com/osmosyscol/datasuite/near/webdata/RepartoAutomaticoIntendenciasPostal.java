package com.osmosyscol.datasuite.near.webdata;

import co.htsoft.commons.util.SMessage;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.mein.dtos.RepartoIntendencias;
import com.osmosyscol.datasuite.mein.dtos.SolicitudNearSociedad;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class RepartoAutomaticoIntendenciasPostal implements AccionCarga{
	
	@Override
	public SMessage ejecutar(Integer id_carga) {
		SolicitudNearSociedad solicitud = null;
		try {
			solicitud = DS_SqlUtils.queryForObject(SolicitudNearSociedad.class, 
					"select solicitud.* " + 
					"  from $solicitud near sociedad$ solicitud " +
					" left join $SOCIEDAD$ sociedad on solicitud.$solicitud near sociedad.deudor$ = sociedad.id " +
					" where solicitud.idcarga = " + id_carga);
			String categoria = DS_SqlUtils.queryForObject(String.class, 
					"select $sociedad.categoria$" + 
					"  from $solicitud near sociedad$ solicitud " +
					" left join $SOCIEDAD$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id " +
					" where solicitud.idcarga = " + id_carga);
			Integer idDepartamentoCiudad = DS_SqlUtils.queryForObject(Integer.class,"select $sociedad.departamento dane$ from $solicitud near sociedad$ solicitud left join $sociedad$ deudor on solicitud.$solicitud near sociedad.deudor$ = deudor.id  where solicitud.idcarga = "+id_carga);
			Integer codDane = null;//validar en estructura solicitud near sociedad
			String sql = "";
			if(categoria != null && !categoria.equals("A")){
				codDane = DS_SqlUtils.queryForObject(Integer.class,"select $departamentos dane.codigo departamento$ from $departamentos dane$ where id = "+idDepartamentoCiudad);
				sql = "SELECT * FROM $REPARTO DE INTENDENCIAS$  WHERE $REPARTO DE INTENDENCIAS.CATEGORIA$ = $S("+categoria+")$ AND $REPARTO DE INTENDENCIAS.CODIGO DANE DEPARTAMENTO$ = $I("+codDane+")$";
			}else if(categoria != null && categoria.equals("A")){
				codDane = 11;//bogota
				sql = "SELECT * FROM $REPARTO DE INTENDENCIAS$  WHERE $REPARTO DE INTENDENCIAS.CATEGORIA$ = $S("+categoria+")$ AND $REPARTO DE INTENDENCIAS.CODIGO DANE DEPARTAMENTO$ = $I("+codDane+")$";
			}else{
				codDane = DS_SqlUtils.queryForObject(Integer.class,"select $departamentos dane.codigo departamento$ from $departamentos dane$ where id = "+idDepartamentoCiudad);
				sql = "SELECT * FROM $REPARTO PNNC$  WHERE $REPARTO PNNC.CODIGO DANE DEPARTAMENTO$ = $I("+codDane+")$";
			}
			RepartoIntendencias reparto = DS_SqlUtils.queryForObject(RepartoIntendencias.class, sql);
			
			DS_SqlUtils
			.update("update $solicitud near sociedad$ set $solicitud near sociedad.intendencia regional$ = $I("+reparto.getIntendencia_asignada()+")$ where ID = "
					+ solicitud.getId());
			
			return new SMessage(true, "");
		} catch (Exception e) {
			SimpleLogger.setError("Error en el reparto de intendencias:", e);

			return new SMessage(false, "");
		}
	}

	@Override
	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}	

}
