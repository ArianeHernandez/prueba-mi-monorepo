package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.ExcepcionFormatoCliente;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoEstilo;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoNegocio;
import com.osmosyscol.datasuite.modelatos.logica.dto.GrupoFormato;
import com.osmosyscol.datasuite.modelatos.logica.dto.Operacion;

public interface FormatoDao {

	public Formato obtenerFormato(Integer id_formato);

	public List<Formato> obtenerFormatosPorModelo(Integer id_modelo, Integer numero_pagina);

	public Formato guardarFormato(Formato formato, FormatoCampo formatoCampo, Integer id_persona, Integer id_modelo, Integer id_negocio);

	public FormatoCampo obtenerFormatoCampo(Integer id_formato);
	
	public FormatoCampo obtenerFormatoCampoSinVacios(Integer id_formato);
	
	public FormatoCampo obtenerFormatoCampoPorID(Integer id_formato_campo);

	public List<FormatoCampo> obtenerHijosFormatoCampo(Integer id_formato, Integer id_formato_campo);

	public List<FormatoCampo> obtenerFormatoCampoHojaPorFormato(Integer id_formato);

	public List<Operacion> obtenerOperacionesControl();

	public List<FormatoCampo> obtenerFormatosCamposOperacion(Integer id_formato);

	public Operacion obtenerOperacionId(Integer id_operacion);

	public List<Formato> obtenerFormatosPorEstructura(Integer id_estructura);

	public Boolean eliminarFormato(Integer id_formato);

	public Integer totalFormatosPorModelo(Integer id_modelo);

	public List<Formato> obtenerFormatosClienteActivo(Integer id_persona, Integer id_negocio, Integer numero_pagina);

	public Integer totalFormatosClienteActivo(Integer id_persona, Integer id_negocio);

	public List<Formato> obtenerFormatosPorTipoFormato(String tipoformato);

	public List<Formato> obtenerFormatosSalidaPorModelo(Integer id_modelo);

	public List<FormatoNegocio> obtenerFormatoNegocioPorNegocio(Integer id_negocio);

	public Boolean activarFormato(Integer id_formato, Boolean activar);

	public Boolean disponibleFormatoNegocio(Integer id_formato, Integer id_negocio, Boolean activo, Boolean todos);
	
	public List<Formato> obtenerFormatosListaValores(Integer id_lista_valores);

	public List<Formato> obtenerFormatosPorNegocio(Integer id_negocio);
	
	public List<Formato> obtenerFormatosEntradaPorNegocio(Integer id_negocio);
	
	public List<Formato> obtenerFormatosSalidaPorNegocio(Integer id_negocio);
	
	public List<Formato> obtenerFormatosPorCliente(Integer id_usuario);

	public List<Formato> obtenerFormatosProceso(Integer id_proceso);
	
	public List<FormatoCampo> obtenerFormatosCampoRaizPorEstructura(Integer id_estructura);
	
	public List<FormatoCampo> obtenerFormatosCampoTipoEstructuraConHijosPorEstructura(Integer id_estructura);
	
	public Integer totalFormatoCampoPorIDCampo(Integer id_campo);
	
	public Integer obtenerSiguienteIDFormatoCampo();
	
	public Boolean guardarFormatoCampo(FormatoCampo formatoCampo);
	
	public List<FormatoCampo> obtenerFormatosCampoPorCampo(Integer id_campo);
	
	public List<FormatoCampo> obtenerFormatosCampoHijoPorFormatoCampoPadre(Integer id_formato_campo_padre);
	
	public Boolean eliminarFormatoCampoPorID(Integer id_formato_campo);
		
	public List<ExcepcionFormatoCliente> obtenerExcepcionesFormatoPorCliente (Integer id_usuario);
	
	public List<ExcepcionFormatoCliente> obtenerExcepcionesFormatoPorClientePaginado (Integer id_usuario, Integer numero_pagina);
	
	public Integer totalExcepcionesFormatoPorCliente(Integer id_usuario);
	
	public Boolean crearExcepcionFormatoCliente(ExcepcionFormatoCliente excepcionFormatoCliente);
	
	public Boolean eliminarTodasExcepcionesFormatoCliente(Integer id_usuario);

	public void guardarEstructurasAplicar(Integer id_formato, List<Integer> estructuras);

	public List<Integer> obtenerEstructurasAplicar(Integer id_formato);
	
	//GrupoForma
	
	public List<GrupoFormato> obtenerTodosLosGruposformato();
	
	public GrupoFormato obtenerGrupoFormato(Integer id_grupoformato);
	
	public Boolean actualizarGrupoformato(GrupoFormato grupoFormato);
	
	public Boolean guardarGrupoformato(GrupoFormato grupoFormato);
	
	public List<Formato> obtenerFormatosPorGrupoFormato(Integer id_grupoformato);

	public Integer obtenerSiguienteIDGrupoFormato ();
	
	public Boolean eliminarGrupoFormato(Integer id_grupoformato);
	
	public Boolean eliminarRelacionesFormatoGrupoformatoGrupo(Integer id_grupoformato);
		
	public Boolean eliminarRelacionesGrupoformatoProcesoPorGrupo(Integer id_grupoformato);

	public List<FormatoCampo> obtenerFormatosCampoPorEstructura(Integer id_estructura, Integer id_formato_salida);
		
	public List<Formato> obtenerFormatosPorClienteTodosProcesos(Integer id_usuario);
	
	public Formato guardarParametrosFormato(Formato formato, Integer id_persona, Integer id_modelo, Integer id_negocio);
	
	public Formato guardarEstructuraFormato(Formato formato, FormatoCampo formatoCampo);

	public Boolean guardarFormatosReferenciados(Integer id_formato,	List<Integer> formatos_referenciados);

	public List<Formato> obtenerSubFormatosPorPadre(Integer idFormatoPadre);

	public List<Formato> obtenerSubFormatosPorFamilia(Integer idFormatoPadre);

	public List<Operacion> obtenerOperacionesCampoAct();

	public List<Formato> obtenerFormatosPorModeloFiltro(Integer id_modelo, Integer numero_pagina, String filtro);

	public Integer totalFormatosPorModeloFiltro(Integer id_modelo, String filtro);
	
	public List<FormatoEstilo> obtenerFormatoEstilos(Integer id_formato);
	
	public Boolean guardarFormatoEstilo(FormatoEstilo formatoEstilo);
	
	public Boolean eliminarFormatoEstilo(Integer id_formato);
	
	public List<FormatoCampo> obtenerFormatoCampoBandejaEntrada (Integer id_formato);
	
	public List<FormatoCampo> obtenerFormatoCampoPadreBandejaEntrada (Integer id_formato);
	
	public List<FormatoCampo> obtenerFormatoCampoFiltroBandejaEntrada (Integer id_formato);
	
	public List<FormatoCampo> obtenerFormatoCampoPadreFiltroBandejaEntrada (Integer id_formato);
	
	public List<FormatoCampo> obtenerFormatoCampoValorSesion (Integer id_formato);
}


