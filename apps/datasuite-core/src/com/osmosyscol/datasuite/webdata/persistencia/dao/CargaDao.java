package com.osmosyscol.datasuite.webdata.persistencia.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.FormatoCampo;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.ElementoCarga;
import com.osmosyscol.datasuite.webdata.logica.dto.FormatoDato;
import com.osmosyscol.datasuite.webdata.logica.dto.FormatoFiltro;
import com.osmosyscol.datasuite.webdata.logica.dto.ParametrosBusqCargas;
import com.osmosyscol.datasuite.webdata.logica.dto.TablaOperacion;

public interface CargaDao {

	public Integer obtenerSiguiente();

	public void insertar(Carga archivo);

	public Carga obtener(Integer id_carga);

	public List<Carga> obtenerListaPorEstado(Integer id_formato, Integer id_persona, String id_estado, Integer id_tipocarga);

	public void cambiarEstado(Integer id_carga, String id_estado);

	public Boolean insertSQL(String exesql);

	public Boolean setFechaCarga(Integer id_carga, Date fecha_carga);

	public List<Map<String, Object>> obtenerDatosCarga(Integer id_estructura, FormatoCampo fcampo, FormatoCampo fcampopadre, Boolean condicional, Integer id_carga, Formato formato, String sqlCondUsu);

	public List<ElementoCarga> obtenerElementosCarga(Integer id_carga, Integer numero_pagina);

	public void crearElementosCarga(Integer id_elementocarga, Integer id_carga, FormatoDato formatodato);

	public Integer obtenerTotalElementosCarga(Integer id_carga);

	public List<Map<String, Object>> obtenerDatos(String id_persona, String id_estructura, String id_campobusqueda, String valor_busqueda, Map<Integer, Campo> campos);

	public void eliminarElementoCarga(Integer id_elementocarga);

	public List<Carga> obtenerListaCargaPorUsuario(Integer id_persona, List<String> estados, Integer id_formato, Integer numero_pagina);

	public Integer totalCargasPorPersona(Integer id_persona, List<String> estados, Integer id_formato);

	public List<Carga> obtenerCargaPorFormatoEntrada(Integer id_formato, Integer id_persona, List<String> estados);

	public List<Map<String, Object>> obtenerDatosVistaEstructura(Integer id_carga, Integer id_estructura, Integer id_estructurapadre, Integer id_campopadre, Integer id_valorpadre, String valor_campo, List<Campo> campos, FormatoCampo formatoCampo, Boolean multiple, List<String> condiciones, Integer numero_pagina, Boolean esOracle);

	public List<Carga> obtenerListadoCargasPorFormatoEntrada(Integer id_formato);
	
	public List<Carga> listarCargasDuplicadas(Integer id_Carga, String valor_total, String numero_registros);

	public Boolean cambiarEstadoCrea(Integer id_carga, String nuevoestado);

	public void guardarTablaOperacion(Integer id_carga, TablaOperacion tablaOperacion);

	public List<Map<String, Object>> obtenerDatosCargaMultiple(Integer id_estructuraPadre, FormatoCampo fcampoHijo, FormatoCampo fcampopadre, Integer id_campoRel, Formato formato, Integer id_carga, Boolean condicional);

	public FormatoDato obtenerFormatoDatoPorIdElementoCarga(Integer id_elementocarga);

	public Integer obtenerSiguienteCreadatos();

	public void removerCarga(Integer idCarga);

	public List<Carga> obtenerCargas(Integer idUsuario, String estados, Integer idRevision, Integer idProceso, Integer pagina);
	
	public Carga obtenerCargasBorradorUltimo(Integer idUsuario, String estados);

	public Integer obtenerSiguienteRevision(Integer idCarga);

	public Boolean actualizarEstadoCarga(Carga carga);

	public Integer contarCargas(Integer idUsuario, String estados, Integer idRevision, Integer idProceso);

	public List<Carga> obtenerCargasLiberacion(String cargaEstadoEnvio, Date fecha);

	public Boolean updateSQL(String exesql);

	public List<Object> obtenerValoresRelacionMultiple(Integer id_estructura, Integer id_campo, Long id);

	public List<Carga> obtenerCargasRelacionadasPorCliente(Integer id_cliente, Integer id_formato, Integer diasvigencia);

	public List<Carga> obtenerCargasPorProcesoAdmin(Integer id_proceso_admin, Integer id_administrativo, String parametro_ordenamiento, String tipo_ordenamiento, Map<String, Object> filtros, Integer numero_pagina);

	public List<Carga> obtenerCargasSubidasSinInstancia();
	
	public List<Carga> obtenerCargasSubidasSinInstanciaPorTipoProceso(Integer id_tipo_proceso);

	public List<Carga> obtenerCargasPorEstados(String estados, Integer numPagina);

	public List<Integer> obtenerIDsRegistrosPorCarga(Integer idCarga, Integer idEstructura);

	public Boolean actualizarNegocio(Integer id_carga, Integer id_negocio);

	public List<Map<String, Object>> selectSql(String sql);

	public Map<String, Object> simpleselectSql(String sql);

	public List<Carga> obtenerCargasPorInstanciaParaAdminHijo(Integer id_proceso_admin, Integer id_instancia, Integer id_administrativo_padre, Integer id_administrativo_hijo);

	public void eliminarCargasInteractivasSinEnviar(Integer id_usuario);

	public List<Carga> buscarCargas(ParametrosBusqCargas parametros, Integer pag);

	public Integer contarBusqCargas(ParametrosBusqCargas parametros);

	public List<Carga> obtenerCargasQueRequierenAdjuntosPorPersona(Integer id_persona);

	public List<Map<String, Object>> obtenerDatosCargaParaCampoTotalizador(Integer id_carga, Integer id_campo, Integer id_estructura);

	public Boolean actualizarValorTotalCarga(Integer id_carga, String vtotal, String nregistros);

	public Boolean actualizarFechaLiberacion(Carga carga);
	
	public Boolean actualizarFechaLiberacionCreadatos(Carga carga);

	public Boolean eliminarSQL(String sql);

	public List<Carga> obtenerCargasSimilaresCliente(Integer id_carga, Integer id_formato_salida, Integer id_cliente);

	public List<Carga> obtenerCargasSimilaresLiberadas(Integer id_carga, Integer id_formato_salida, Integer id_usuario);

	public Integer obtenerTotalRegistros(Integer id_carga, Integer id_estructura);

	public void cambiarTipocarga(Integer id_carga, int tipocarga);

	public Integer obtenerIdFormatoSalidaCliente(Integer id_carga);

	public Integer obtenerIdFormatoSalidaAdmin(Integer id_carga, Integer id_administrativo);

	public List<FormatoFiltro> obtenerFormatosFiltro(Integer id_formato);
	
	public List<Carga> obtenerCargasSinFinalizar();

	public Date consultarCambioEstado(Integer id_carga, String estado);

	public void marcarNotificacion(Integer id_carga);

	public void cambiarMotivo(Integer id_carga, String motivo);

}
