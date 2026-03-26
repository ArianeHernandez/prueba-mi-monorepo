package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.ExcepcionFormatoCliente;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoEstilo;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoNegocio;
import com.osmosyscol.datasuite.modelatos.logica.dto.GrupoFormato;
import com.osmosyscol.datasuite.modelatos.logica.dto.Operacion;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.FormatoDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class FormatoDaoImp extends BaseSqlMapDao implements FormatoDao {

	public FormatoDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	public Formato obtenerFormato(Integer id_formato) {
		Formato formato = new Formato();
		formato.setId_formato(id_formato);
		return (Formato) queryForObject("Formato.obtenerFormato", formato);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosPorModelo(Integer id_modelo, Integer numero_pagina) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id_modelo", id_modelo);

			return queryForListPag("Formato.obtenerFormatosPorModelo", map, numero_pagina, Constantes.PAGINACIONLISTADO);

		} catch (Exception e) {
			SimpleLogger.setError("Formato.obtenerFormatosPorModelo", e);
			return null;
		}

	}

	// --------------------------------------------------------

	public Formato guardarFormato(Formato formato, FormatoCampo formatoCampo, Integer id_persona, Integer id_modelo, Integer id_negocio) {

		formato.setId_modelo(id_modelo);
		formato.setId_negocio(id_negocio);

		delete("Formato.eliminarFormatoDato", null);

		if (formato.getId_formato() == null) {
			Integer id_formato = (Integer) queryForObject("Formato.siguienteId", null);
			formato.setId_formato(id_formato);
			insert("Formato.insertarFormato", formato);

		} else {
			update("Formato.actualizarFormato", formato);
		}

		delete("Formato.eliminarFormatoCampo", formato);

		/** Insercion del formatocampo** */
		formatoCampo.setId_formato(formato.getId_formato());
		guardarFormatoCampo(formatoCampo);

		return formato;
	}
	
	
	
	

	// --------------------------------------------------------

	public Boolean guardarFormatoCampo(FormatoCampo formatoCampo) {
		Integer id_formato_campo = (Integer) queryForObject("Formato.siguienteIdFormatoCampo", null);
		formatoCampo.setId_formato_campo(id_formato_campo);

		// Solo deja la variable cuando sea de tipo ingreso Variable
		if (formatoCampo.getTipo_ingreso() != FormatoCampo.TIPOINGRESO_USUARIO_SISTEMA) {
			formatoCampo.setId_variable(null);
		}

		// Solo deja la lista dinamica cuando sea de tipo ingreso Lista dinamica
		if (formatoCampo.getTipo_ingreso() != FormatoCampo.TIPOINGRESO_USUARIO_SELECCION_LISTA_DINAMICA) {
			formatoCampo.setId_lista_dinamica(null);
		}

		if (formatoCampo.getId_operacion() != null && formatoCampo.getId_operacion().intValue() == 0) {
			formatoCampo.setId_operacion(null);
		}

		insert("Formato.insertarFormatoCampo", formatoCampo);

		List<FormatoCampo> formatoCampoList = formatoCampo.getFormato_campo_list();

		for (FormatoCampo fcampo : formatoCampoList) {
			fcampo.setId_formato(formatoCampo.getId_formato());
			fcampo.setId_formato_campo_padre(formatoCampo.getId_formato_campo());

			if ((fcampo.getId_estructura() == null) || (fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_SELECCION_LISTA_DINAMICA)
					|| (fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_SELECCION) || (fcampo.getFormato_campo_list().size() > 0)
					|| (fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_INDEFINIDO && Constantes.TRUE.equalsIgnoreCase(fcampo.getSeleccion_visualizacion()))
					|| fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_VACIO || fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_SELECCION_LISTA_DINAMICA
					|| fcampo.getTipo_ingreso() == FormatoCampo.TIPOINGRESO_USUARIO_AUTOMATICO
					|| fcampo.getId_campo() != null
					|| StringUtils.trim(fcampo.getValor_constante()).length() > 0) {
				guardarFormatoCampo(fcampo);
			}
		}

		return true;
	}

	// --------------------------------------------------------

	public FormatoCampo obtenerFormatoCampo(Integer id_formato) {

		FormatoCampo formatoCampo = (FormatoCampo) queryForObject("Formato.obtenerFormatosCampoPorFormato", id_formato);
		if (formatoCampo != null) {
			formatoCampo.setFormato_campo_list(obtenerFormatoCampo(id_formato, formatoCampo.getId_formato_campo()));
		}

		return formatoCampo;
	}
	
	public FormatoCampo obtenerFormatoCampoSinVacios(Integer id_formato) {

		FormatoCampo formatoCampo = (FormatoCampo) queryForObject("Formato.obtenerFormatosCampoPorFormato", id_formato);
		if (formatoCampo != null) {
			formatoCampo.setFormato_campo_list(obtenerFormatoCampoSinVacios(id_formato, formatoCampo.getId_formato_campo()));
		}

		return formatoCampo;
	}
	
	public FormatoCampo obtenerFormatoCampoPorID(Integer id_formato_campo) {
		FormatoCampo formatoCampo = (FormatoCampo) queryForObject("Formato.obtenerFormatosCampoPorID", id_formato_campo);
		return formatoCampo;
	}

	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatoCampo(Integer id_formato, Integer id_formato_campo_padre) {

		FormatoCampo formatoCampo = new FormatoCampo();
		formatoCampo.setId_formato(id_formato);
		formatoCampo.setId_formato_campo(id_formato_campo_padre);

		List<FormatoCampo> listado = (List<FormatoCampo>) queryForList("Formato.obtenerFormatosCampoPorPadre", formatoCampo);

		if (listado != null) {
			for (FormatoCampo fCampo : listado) {
				fCampo.setFormato_campo_list(obtenerFormatoCampo(id_formato, fCampo.getId_formato_campo()));
			}
		}

		return listado;
	}
	
	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatoCampoSinVacios(Integer id_formato, Integer id_formato_campo_padre) {

		FormatoCampo formatoCampo = new FormatoCampo();
		formatoCampo.setId_formato(id_formato);
		formatoCampo.setId_formato_campo(id_formato_campo_padre);

		List<FormatoCampo> listado = (List<FormatoCampo>) queryForList("Formato.obtenerFormatosCampoPorPadreSinVacios", formatoCampo);

		if (listado != null) {
			for (FormatoCampo fCampo : listado) {
				fCampo.setFormato_campo_list(obtenerFormatoCampoSinVacios(id_formato, fCampo.getId_formato_campo()));
			}
		}

		return listado;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerHijosFormatoCampo(Integer id_formato, Integer id_formato_campo_padre) {

		FormatoCampo formatoCampo = new FormatoCampo();
		formatoCampo.setId_formato(id_formato);
		formatoCampo.setId_formato_campo(id_formato_campo_padre);

		List<FormatoCampo> listado = (List<FormatoCampo>) queryForList("Formato.obtenerFormatosCampoPorPadre", formatoCampo);

		return listado;
	}

	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatoCampoHojaPorFormato(Integer id_formato) {
		return queryForList("Formato.obtenerFormatosCampoHojaPorFormato", id_formato);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Operacion> obtenerOperacionesControl() {
		List<Operacion> operaciones = queryForList("Formato.obtenerOperacionesControl", null);
		return operaciones;
	}

	@SuppressWarnings("unchecked")
	public List<Operacion> obtenerOperacionesCampoAct() {
		List<Operacion> operaciones = queryForList("Formato.obtenerOperacionesCampoAct", null);
		return operaciones;
	}

	// ----------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatosCamposOperacion(Integer id_formato) {
		return queryForList("Formato.obtenerFormatosCamposOperacion", id_formato);
	}

	// ----------------------------------------------------------

	public Operacion obtenerOperacionId(Integer id_operacion) {
		return (Operacion) queryForObject("Formato.obtenerOperacionId", id_operacion);
	}

	// ----------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosPorEstructura(Integer id_estructura) {
		return queryForList("Formato.obtenerFormatosPorEstructura", id_estructura);
	}

	public Boolean eliminarFormato(Integer id_formato) {
		try {
			update("Formato.eliminarFormato", id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
		return true;
	}

	public Integer totalFormatosPorModelo(Integer id_modelo) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id_modelo", id_modelo);

			return (Integer) queryForObject("Formato.totalFormatosPorModelo", map);

		} catch (Exception e) {
			SimpleLogger.setError("Formato.totalFormatosPorModelo", e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosClienteActivo(Integer id_persona, Integer id_negocio, Integer numero_pagina) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id_persona", id_persona);
			map.put("id_negocio", id_negocio);

			if (numero_pagina != null) {
				return queryForListPag("Formato.obtenerFormatosClienteActivo", map, numero_pagina, Constantes.PAGINACIONLISTADO);
			} else {
				return queryForList("Formato.obtenerFormatosClienteActivo", map);
			}

		} catch (Exception e) {
			SimpleLogger.setError("Formato.obtenerFormatosClienteActivo", e);
			return null;
		}
	}

	public Integer totalFormatosClienteActivo(Integer id_persona, Integer id_negocio) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id_persona", id_persona);
			map.put("id_negocio", id_negocio);

			return (Integer) queryForObject("Formato.totalFormatosClienteActivo", map);

		} catch (Exception e) {
			SimpleLogger.setError("Formato.totalFormatosClienteActivo", e);
			return null;
		}
	}

	// -------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosPorTipoFormato(String tipoformato) {
		return queryForList("Formato.obtenerFormatosPorTipoFormato", tipoformato);
	}

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosSalidaPorModelo(Integer id_modelo) {
		return queryForList("Formato.obtenerFormatosSalidaPorModelo", id_modelo);
	}

	@SuppressWarnings("unchecked")
	public List<FormatoNegocio> obtenerFormatoNegocioPorNegocio(Integer id_negocio) {
		return queryForList("Formato.obtenerFormatoNegocioPorNegocio", id_negocio);
	}

	public Boolean activarFormato(Integer id_formato, Boolean activar) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_formato", id_formato);
		map.put("activo", activar ? "S" : "N");

		update("Formato.activarFormato", map);

		return true;
	}

	public Boolean disponibleFormatoNegocio(Integer id_formato, Integer id_negocio, Boolean activo, Boolean todos) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_formato", id_formato);
		map.put("id_negocio", id_negocio);

		if (todos) {
			delete("Formato.eliminarDisponibleTodosFormatoNegocio", map);
			if (activo) {
				insert("Formato.disponibleTodosFormatoNegocio", map);
			}
		} else {
			delete("Formato.eliminarDisponibleFormatoNegocio", map);
			if (activo) {
				insert("Formato.disponibleFormatoNegocio", map);
			}
		}

		delete("Formato.eliminarFormatoProcesoSinUso", null);

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosListaValores(Integer id_lista_valores) {
		return queryForList("Formato.obtenerFormatosListaValores", id_lista_valores);
	}

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosPorNegocio(Integer id_negocio) {
		return queryForList("Formato.obtenerFormatosPorNegocio", id_negocio);
	}

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosEntradaPorNegocio(Integer id_negocio) {
		return queryForList("Formato.obtenerFormatosEntradaPorNegocio", id_negocio);
	}

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosSalidaPorNegocio(Integer id_negocio) {
		return queryForList("Formato.obtenerFormatosSalidaPorNegocio", id_negocio);
	}

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosPorCliente(Integer id_usuario) {
		return queryForList("Formato.obtenerFormatosPorCliente", id_usuario);
	}

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosProceso(Integer id_proceso) {
		return (List<Formato>) queryForList("Formato.obtenerFormatosProceso", id_proceso);
	}

	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatosCampoRaizPorEstructura(Integer id_estructura) {
		return queryForList("Formato.obtenerFormatosCampoRaizPorEstructura", id_estructura);
	}

	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatosCampoTipoEstructuraConHijosPorEstructura(Integer id_estructura) {
		return queryForList("Formato.obtenerFormatosCampoTipoEstructuraConHijosPorEstructura", id_estructura);
	}

	public Integer totalFormatoCampoPorIDCampo(Integer id_campo) {
		return (Integer) queryForObject("Formato.totalFormatoCampoPorIDCampo", id_campo);
	}

	public Integer obtenerSiguienteIDFormatoCampo() {

		return (Integer) queryForObject("Formato.siguienteIdFormatoCampo", null);
	}

	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatosCampoPorCampo(Integer id_campo) {
		return queryForList("Formato.obtenerFormatosCampoPorCampo", id_campo);
	}

	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatosCampoHijoPorFormatoCampoPadre(Integer id_formato_campo_padre) {
		return queryForList("Formato.obtenerFormatosCampoHijoPorFormatoCampoPadre", id_formato_campo_padre);
	}

	public Boolean eliminarFormatoCampoPorID(Integer id_formato_campo) {
		delete("Formato.eliminarFormatoCampoPorID", id_formato_campo);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<ExcepcionFormatoCliente> obtenerExcepcionesFormatoPorCliente(Integer id_usuario) {
		return queryForList("Formato.obtenerExepcionesFormatoPorCliente", id_usuario);

	}

	public Integer totalExcepcionesFormatoPorCliente(Integer id_usuario) {
		return (Integer) queryForObject("Formato.totalExepcionesFormatoPorCliente", id_usuario);
	}

	@SuppressWarnings("unchecked")
	public List<ExcepcionFormatoCliente> obtenerExcepcionesFormatoPorClientePaginado(Integer id_usuario, Integer numero_pagina) {

		return queryForListPag("Formato.obtenerExepcionesFormatoPorCliente", id_usuario, numero_pagina, Constantes.PAGINACIONLISTADO);

	}

	public Boolean crearExcepcionFormatoCliente(ExcepcionFormatoCliente excepcionFormatoCliente) {

		insert("Formato.crearExcepcionFormatoCliente", excepcionFormatoCliente);
		return true;
	}

	public Boolean eliminarTodasExcepcionesFormatoCliente(Integer id_usuario) {

		delete("Formato.eliminarTodasExcepcionesFormatoCliente", id_usuario);
		return true;
	}

	
	public void guardarEstructurasAplicar(Integer id_formato, List<Integer> estructuras) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id_formato", id_formato);
		
		delete("Formato.eliminarEstructurasAplicar", id_formato);
		
		for (Integer id_estructura : estructuras) {
			if(id_estructura != null){
				map.put("id_estructura", id_estructura);
				insert("Formato.guardarEstructurasAplicar", map);
			}
		}
		
	}

	
	@SuppressWarnings("unchecked")
	public List<Integer> obtenerEstructurasAplicar(Integer id_formato) {
		return queryForList("Formato.obtenerEstructurasAplicar", id_formato);
	}

	@SuppressWarnings("unchecked")
	public List<GrupoFormato> obtenerTodosLosGruposformato() {
		return queryForList("Formato.obtenerTodosLosGruposformato", null);
	}

	
	public GrupoFormato obtenerGrupoFormato(Integer id_grupoformato) {
		return (GrupoFormato) queryForObject("Formato.obtenerGrupoFormato", id_grupoformato);
	}

	
	public Boolean actualizarGrupoformato(GrupoFormato grupoFormato) {
		update("Formato.actualizarGrupoformato", grupoFormato);
		return true;
	}


	public Boolean guardarGrupoformato(GrupoFormato grupoFormato) {
		insert("Formato.guardarGrupoformato", grupoFormato);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosPorGrupoFormato(Integer id_grupoformato) {
		return queryForList("Formato.obtenerFormatosPorGrupoFormato", id_grupoformato);
	}

	public Integer obtenerSiguienteIDGrupoFormato() {
		return (Integer)queryForObject("Formato.obtenerSiguienteIDGrupoFormato", null);
	}

	public Boolean eliminarGrupoFormato(Integer id_grupoformato) {
		
		delete("Formato.eliminarGrupoFormato", id_grupoformato);
		return true;
	}
	
	public Boolean eliminarRelacionesFormatoGrupoformatoGrupo(Integer id_grupoformato){
		update("Formato.eliminarRelacionesFormatoGrupoformatoGrupo", id_grupoformato);
		return true;
		
	}
	
	public Boolean eliminarRelacionesGrupoformatoProcesoPorGrupo(Integer id_grupoformato){
		update("Formato.eliminarRelacionesGrupoformatoProcesoPorGrupo", id_grupoformato);
		return true;
		
	}

	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatosCampoPorEstructura(Integer id_estructura, Integer id_formato_salida) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id_estructura", id_estructura);
		data.put("id_formato_salida", id_formato_salida);
		
		List<FormatoCampo> lista = queryForList("Formato.obtenerFormatosCampoPorEstructura", data);
		
		if(lista != null){
			List<Integer> id_campos = new ArrayList<Integer>();
			List<FormatoCampo> duplicados = new ArrayList<FormatoCampo>();
			
			for (FormatoCampo formatoCampo : lista) {
				if(id_campos.contains(formatoCampo.getId_campo())){
					duplicados.add(formatoCampo);
				}else{
					id_campos.add(formatoCampo.getId_campo());
				}
			}
			
			for (FormatoCampo formatoCampo : duplicados) {
				lista.remove(formatoCampo);
			}
		}
		
		return lista;
	}


	@SuppressWarnings("unchecked")
	public List<Formato> obtenerFormatosPorClienteTodosProcesos(
			Integer id_usuario) {
		return queryForList("Formato.obtenerFormatosPorClienteTodosProcesos", id_usuario);
	}
	
	//---------------------------------------------------------------
	
	public Formato guardarParametrosFormato(Formato formato, Integer id_persona, Integer id_modelo, Integer id_negocio) {

		formato.setId_modelo(id_modelo);
		formato.setId_negocio(id_negocio);

		delete("Formato.eliminarFormatoDato", null);

		if (formato.getId_formato() == null) {
			Integer id_formato = (Integer) queryForObject("Formato.siguienteId", null);
			formato.setId_formato(id_formato);
			insert("Formato.insertarFormato", formato);

		} else {
			update("Formato.actualizarFormato", formato);
		}


		return formato;
	}
	
	public Formato guardarEstructuraFormato(Formato formato, FormatoCampo formatoCampo){
		
		//Se actualiza la informaicion de la estructura base del formato y el campo totalizador
		
		if(formato.getId_formato()!=null){
		
			update("Formato.actualizarInfoEstructuraFormato", formato);
			
			delete("Formato.eliminarFormatoDatoPorFormato", formato);
			delete("Formato.eliminarFormatoCampo", formato);
			
			/** Insercion del formatocampo** */
			formatoCampo.setId_formato(formato.getId_formato());
			guardarFormatoCampo(formatoCampo);
	
			return formato;
		
		}else{
			
			SimpleLogger.setError("No se puede guardar la informacion de la estructura del formato. El id_formato es null");
			return null;
			
		}
	}
	
	//-----------------------

	public Boolean guardarFormatosReferenciados(Integer id_formato, List<Integer> formatos_referenciados) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id_formato_padre", id_formato);
		
		delete("Formato.eliminarSubFormatos", id_formato);
		
		for (Integer id_formato_hijo : formatos_referenciados) {

			if(id_formato_hijo != null){
				map.put("id_formato_hijo", id_formato_hijo);
				insert("Formato.guardarSubFormato", map);
			}
		}
		
		return true;
		
	}

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerSubFormatosPorPadre(Integer id_formato_padre) {
		return (List<Formato>) queryForList("Formato.obtenerSubFormatosPorFormatoPadre", id_formato_padre);
	}

	@SuppressWarnings("unchecked")
	public List<Formato> obtenerSubFormatosPorFamilia(Integer id_formato_padre) {
		
		HashMap<Integer,Formato> familiaFormatos = new HashMap<Integer, Formato>();
		obtenerSubFormatosPorFamiliaRecursivo(id_formato_padre, familiaFormatos);
		
		return new LinkedList(familiaFormatos.values());
	}
	
	private void obtenerSubFormatosPorFamiliaRecursivo(Integer id_formato_padre, HashMap<Integer,Formato> familiaFormatos){
		
		List<Formato> hijos = (List<Formato>) queryForList("Formato.obtenerSubFormatosPorFamilia", id_formato_padre);
		
		for (Formato hijo : hijos) {//condicion de parada de recurcion: no tiene hijos
			if( ! familiaFormatos.containsKey(hijo.getId_formato()) ){//si no se ha agregado al resultado. Condicion deparada de recurcion: todos los hijos ya fueron agregados
				familiaFormatos.put(hijo.getId_formato(), hijo);//agreguelo al resultado 
				obtenerSubFormatosPorFamiliaRecursivo(hijo.getId_formato(),familiaFormatos);//y agregue a sus hijos
			}
		}
	}

	@Override
	public List<Formato> obtenerFormatosPorModeloFiltro(Integer id_modelo, Integer numero_pagina, String filtro) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_modelo", id_modelo);
		if (filtro != null) {
			map.put("filtro", filtro);
		}

		return queryForListPag("Formato.obtenerFormatosPorModeloFiltro", map, numero_pagina,
				Constantes.PAGINACIONLISTADO);

	}

	@Override
	public Integer totalFormatosPorModeloFiltro(Integer id_modelo, String filtro) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_modelo", id_modelo);
		if (filtro != null) {
			map.put("filtro", filtro);
		}
		
		return (Integer) queryForObject("Formato.totalFormatosPorModeloFiltro", map);
	}

	@SuppressWarnings("unchecked")
	public List<FormatoEstilo> obtenerFormatoEstilos(Integer id_formato){
		return queryForList("Formato.obtenerFormatoEstilos", id_formato);
	}
	
	public Boolean guardarFormatoEstilo(FormatoEstilo formatoEstilo){
		try {			
			insert("Formato.guardarFormatoEstilo", formatoEstilo);
			return true;
		}catch (Exception e){
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}

	public Boolean eliminarFormatoEstilo(Integer id_formato) {
		try {			
			delete("Formato.eliminarFormatoEstilo", id_formato);
			return true;
		}catch (Exception e){
			SimpleLogger.setError("Ha ocurrido un error", e);
			return false;
		}
	}
	
	// -----------------------

	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatoCampoBandejaEntrada (Integer id_formato) {
		try {
			return queryForList("Formato.obtenerFormatoCampoBandejaEntrada", id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatoCampoPadreBandejaEntrada (Integer id_formato) {
		try {
			return queryForList("Formato.obtenerFormatoCampoPadreBandejaEntrada", id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatoCampoFiltroBandejaEntrada (Integer id_formato) {
		try {
			return queryForList("Formato.obtenerFormatoCampoFiltroBandejaEntrada", id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatoCampoPadreFiltroBandejaEntrada (Integer id_formato) {
		try {
			return queryForList("Formato.obtenerFormatoCampoPadreFiltroBandejaEntrada", id_formato);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FormatoCampo> obtenerFormatoCampoValorSesion (Integer id_formato) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("id_formato", id_formato);
			params.put("tipo_valor_sesion", FormatoCampo.TIPOINGRESO_USUARIO_VALOR_SESION);
			
			return queryForList("Formato.obtenerFormatoCampoValorSesion", params);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error ", e);
			return null;
		}
	}
	
}
