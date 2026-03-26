package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.ArrayList;
import java.util.List;

import com.osmosyscol.datasuite.logica.constantes.Constantes;

public class FormatoCampo {

	public static final int TIPOINGRESO_USUARIO_INDEFINIDO = 0;
	public static final int TIPOINGRESO_USUARIO_SELECCION = 1;
	public static final int TIPOINGRESO_USUARIO_CREACION = 3;
	public static final int TIPOINGRESO_USUARIO_CONSTANTE = 4;
	public static final int TIPOINGRESO_USUARIO_SISTEMA = 5;
	public static final int TIPOINGRESO_USUARIO_SELECCION_LISTA_DINAMICA = 7;

	public static final int TIPOINGRESO_USUARIO_VACIO = 10;
	public static final int TIPOINGRESO_USUARIO_AUTOMATICO = 11;
	public static final int TIPOINGRESO_USUARIO_VALOR_SESION = 12;

	// ----------------------------------------------
	private Integer id_formato_campo;
	private Integer id_formato_campo_padre;

	private String titulo = "";
	private String ayuda = "";

	private Integer id_formato;
	private Integer id_campo;

	private Integer tipo_ingreso = TIPOINGRESO_USUARIO_INDEFINIDO;
	private String valor_constante;
	private String valor_sesion;
	private Integer id_variable;
	private Integer id_lista_dinamica;
	private List<FormatoCampo> formato_campo_list = new ArrayList<FormatoCampo>();

	private Integer id_operacion;
	private String estructura_padre;

	private Integer id_estructura;

	private String seleccion_campo = Constantes.NO;
	private String seleccion_visualizacion = Constantes.NO;

	private String precarga = Constantes.NO;

	private String condicional = Constantes.NO;
	private String valor_condicion;

	private String condicion_precarga;

	private String obligatorio = Constantes.NO;

	private String patron_dependiente;

	// Obligatorio según el valor de otro campo
	private String obligatorio_valor_campo = Constantes.NO;

	private String campo_obligatoriedad;

	private Integer lista_dinamica_obligatoriedad;

	// Ocultable según el valor de otro campo
	private String validacion_campo_lista = Constantes.NO;

	// Posicion ascendente del campo y nombre del campo, ej: 1:Campo Padre
	private String campo_validacion;

	// Id de la lista dinamica, si trae valores el campo se oculta
	private Integer lista_dinamica_validacion;

	// Ocultable según el valor de otro campo
	private String ocultable_valor_campo = Constantes.NO;

	// Posicion ascendente del campo y nombre del campo, ej: 1:Campo Padre
	private String ocultable_campo;

	// Id de la lista dinamica, si trae valores el campo se oculta
	private Integer lista_dinamica_ocultable;
	
	private String mensaje_validacion;
	
	//Formato del campo, aplica para formatos de salida: Debe ser acorde a la clase FormatNumber o la clase SimpleDateFormat
	private String formato;
	
	private String titulo_html;
	
	private String usa_titulo_html = Constantes.NO;
	
	private String validacion_servicio = Constantes.NO;
	
	private String endpoint_validacion;
	
	private Integer orden_bandeja_entrada;
	private String bandeja_entrada = Constantes.NO;
	private String filtro_bandeja_entrada = Constantes.NO;
	
	private String visualizar_valor_sesion = Constantes.NO;
	private String visualizar_valor_constante = Constantes.NO;
	
	private Integer lista_dinamica_valor_inicial;
	
	private String mensaje_campo_padre;
	
	private String bloqueado = Constantes.NO;
	
	private Integer campo_orden;
	// ----------------------------------------------

	public String getValidacion_campo_lista() {
		return validacion_campo_lista;
	}

	public void setValidacion_campo_lista(String validacion_campo_lista) {
		this.validacion_campo_lista = validacion_campo_lista;
	}

	public String getCampo_validacion() {
		return campo_validacion;
	}

	public void setCampo_validacion(String campo_validacion) {
		this.campo_validacion = campo_validacion;
	}

	public Integer getLista_dinamica_validacion() {
		return lista_dinamica_validacion;
	}

	public void setLista_dinamica_validacion(Integer lista_dinamica_validacion) {
		this.lista_dinamica_validacion = lista_dinamica_validacion;
	}

	public String getPatron_dependiente() {
		return patron_dependiente;
	}

	public String getCampo_obligatoriedad() {
		return campo_obligatoriedad;
	}

	public void setCampo_obligatoriedad(String campo_obligatoriedad) {
		this.campo_obligatoriedad = campo_obligatoriedad;
	}

	public void setPatron_dependiente(String patron_dependiente) {
		this.patron_dependiente = patron_dependiente;
	}

	public String getSeleccion_campo() {
		return seleccion_campo;
	}

	public String getCondicion_precarga() {

		if (condicion_precarga != null && condicion_precarga.length() > 0) {
			//TODO se elimina caracter invaldio, la solución válida debe ser a nivel de xhtml en el campo textarea (evita conflictos hacia atras)
			condicion_precarga = condicion_precarga.replace((char)160,(char)32);
			return condicion_precarga;
		}
		return null;
	}

	public void setCondicion_precarga(String condicionPrecarga) {
		condicion_precarga = condicionPrecarga;
	}

	public Integer getId_lista_dinamica() {
		return id_lista_dinamica;
	}

	public void setId_lista_dinamica(Integer id_lista_dinamica) {
		this.id_lista_dinamica = id_lista_dinamica;
	}

	public String getCondicional() {
		return condicional;
	}

	public void setCondicional(String condicional) {
		this.condicional = condicional;
	}

	public String getValor_condicion() {
		return valor_condicion;
	}

	public void setValor_condicion(String valor_condicion) {
		this.valor_condicion = valor_condicion;
	}

	public String getPrecarga() {
		return precarga;
	}

	public void setPrecarga(String precarga) {
		this.precarga = precarga;
	}

	public void setSeleccion_campo(String seleccion_campo) {
		this.seleccion_campo = seleccion_campo;
	}

	public String getSeleccion_visualizacion() {
		return seleccion_visualizacion;
	}

	public void setSeleccion_visualizacion(String seleccion_visualizacion) {
		this.seleccion_visualizacion = seleccion_visualizacion;
	}

	public Integer getId_estructura() {
		return id_estructura;
	}

	public void setId_estructura(Integer id_estructura) {
		this.id_estructura = id_estructura;
	}

	public String getEstructura_padre() {
		return estructura_padre;
	}

	public void setEstructura_padre(String estructura_padre) {
		this.estructura_padre = estructura_padre;
	}

	public boolean isUltimoNivel() {
		return formato_campo_list == null || formato_campo_list.size() == 0;
	}

	public Integer getId_formato_campo() {
		return id_formato_campo;
	}

	public void setId_formato_campo(Integer id_formatoCampo) {
		this.id_formato_campo = id_formatoCampo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getId_formato() {
		return id_formato;
	}

	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}

	public Integer getId_campo() {
		return id_campo;
	}

	public void setId_campo(Integer id_campo) {
		this.id_campo = id_campo;
	}

	public Integer getTipo_ingreso() {
		return tipo_ingreso;
	}

	public void setTipo_ingreso(Integer tipoIngreso) {
		this.tipo_ingreso = tipoIngreso;
	}

	public String getValor_constante() {
		return valor_constante;
	}

	public void setValor_constante(String valorConstante) {
		this.valor_constante = valorConstante;
	}

	public String getValor_sesion() {
		return valor_sesion;
	}

	public void setValor_sesion(String valor_sesion) {
		this.valor_sesion = valor_sesion;
	}

	public Integer getId_variable() {
		return id_variable;
	}

	public void setId_variable(Integer id_variable) {
		this.id_variable = id_variable;
	}

	public List<FormatoCampo> getFormato_campo_list() {
		return formato_campo_list;
	}

	public void setFormato_campo_list(List<FormatoCampo> formatoCampoList) {
		this.formato_campo_list = formatoCampoList;
	}

	public Integer getId_formato_campo_padre() {
		return id_formato_campo_padre;
	}

	public void setId_formato_campo_padre(Integer id_formato_campo_padre) {
		this.id_formato_campo_padre = id_formato_campo_padre;
	}

	public Integer getId_operacion() {
		return id_operacion;
	}

	public void setId_operacion(Integer id_operacion) {
		this.id_operacion = id_operacion;
	}

	public String getObligatorio() {
		return obligatorio;
	}

	public void setObligatorio(String obligatorio) {
		this.obligatorio = obligatorio;
	}

	// --------------

	public String toString() {
		return this.id_formato_campo + " : " + this.titulo;
	}

	public String getObligatorio_valor_campo() {
		return obligatorio_valor_campo;
	}

	public void setObligatorio_valor_campo(String obligatorio_valor_campo) {
		this.obligatorio_valor_campo = obligatorio_valor_campo;
	}

	public Integer getlista_dinamica_obligatoriedad() {
		return lista_dinamica_obligatoriedad;
	}

	public void setlista_dinamica_obligatoriedad(Integer lista_dinamica_obligatoriedad) {
		this.lista_dinamica_obligatoriedad = lista_dinamica_obligatoriedad;
	}

	public String getOcultable_valor_campo() {
		return ocultable_valor_campo;
	}

	public void setOcultable_valor_campo(String ocultable_valor_campo) {
		this.ocultable_valor_campo = ocultable_valor_campo;
	}

	public String getOcultable_campo() {
		return ocultable_campo;
	}

	public void setOcultable_campo(String ocultable_campo) {
		this.ocultable_campo = ocultable_campo;
	}

	public Integer getlista_dinamica_ocultable() {
		return lista_dinamica_ocultable;
	}

	public void setlista_dinamica_ocultable(Integer lista_dinamica_ocultable) {
		this.lista_dinamica_ocultable = lista_dinamica_ocultable;
	}

	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

	public String getAyuda() {
		return ayuda;
	}

	public String getMensaje_validacion() {
		return mensaje_validacion;
	}

	public void setMensaje_validacion(String mensaje_validacion) {
		this.mensaje_validacion = mensaje_validacion;
	}

	public String getTituloExcel(){
		String tituloExcel =  getId_formato_campo() + "_" + getTitulo();
		if (tituloExcel.length() > 30) {
			return tituloExcel.substring(0, 31);
		}
		return tituloExcel;
		
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getTitulo_html() {
		return titulo_html;
	}

	public void setTitulo_html(String titulo_html) {
		this.titulo_html = titulo_html;
	}

	public String getUsa_titulo_html() {
		return usa_titulo_html;
	}

	public void setUsa_titulo_html(String usa_titulo_html) {
		this.usa_titulo_html = usa_titulo_html;
	}

	public String getValidacion_servicio() {
		return validacion_servicio;
	}

	public void setValidacion_servicio(String validacion_servicio) {
		this.validacion_servicio = validacion_servicio;
	}

	public String getEndpoint_validacion() {
		return endpoint_validacion;
	}

	public void setEndpoint_validacion(String endpoint_validacion) {
		this.endpoint_validacion = endpoint_validacion;
	}

	public String getBandeja_entrada() {
		return bandeja_entrada;
	}

	public void setBandeja_entrada(String bandeja_entrada) {
		this.bandeja_entrada = bandeja_entrada;
	}

	public Integer getOrden_bandeja_entrada() {
		return orden_bandeja_entrada;
	}

	public void setOrden_bandeja_entrada(Integer orden_bandeja_entrada) {
		this.orden_bandeja_entrada = orden_bandeja_entrada;
	}

	public String getFiltro_bandeja_entrada() {
		return filtro_bandeja_entrada;
	}

	public void setFiltro_bandeja_entrada(String filtro_bandeja_entrada) {
		this.filtro_bandeja_entrada = filtro_bandeja_entrada;
	}

	public String getVisualizar_valor_sesion() {
		return visualizar_valor_sesion;
	}

	public void setVisualizar_valor_sesion(String visualizar_valor_sesion) {
		this.visualizar_valor_sesion = visualizar_valor_sesion;
	}

	public String getVisualizar_valor_constante() {
		return visualizar_valor_constante;
	}

	public void setVisualizar_valor_constante(String visualizar_valor_constante) {
		this.visualizar_valor_constante = visualizar_valor_constante;
	}

	public Integer getLista_dinamica_valor_inicial() {
		return lista_dinamica_valor_inicial;
	}

	public void setLista_dinamica_valor_inicial(
			Integer lista_dinamica_valor_inicial) {
		this.lista_dinamica_valor_inicial = lista_dinamica_valor_inicial;
	}

	public String getMensaje_campo_padre() {
		return mensaje_campo_padre;
	}

	public void setMensaje_campo_padre(String mensaje_campo_padre) {
		this.mensaje_campo_padre = mensaje_campo_padre;
	}

	public String getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}

	public Integer getCampo_orden() {
		return campo_orden;
	}

	public void setCampo_orden(Integer campo_orden) {
		this.campo_orden = campo_orden;
	}

}
