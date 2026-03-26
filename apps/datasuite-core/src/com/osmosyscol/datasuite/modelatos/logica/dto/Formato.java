package com.osmosyscol.datasuite.modelatos.logica.dto;

import java.util.ArrayList;
import java.util.List;

import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.FormatoConfiguracion;
import com.osmosyscol.datasuite.logica.dto.FormatoOperacion;
import com.osmosyscol.datasuite.logica.servicios.FormatoConfiguracionServicio;

public class Formato {

	public static final String TIPO_FORMATO_ENTRADA = "E";
	public static final String TIPO_FORMATO_SALIDA = "S";

	public static final Integer DEFAULT_NUM_REGISTROS_POR_CARGA = 2000;

	// --
	private Integer id_formato;
	private Integer id_modelo;
	private Integer id_estructura;
	private Integer id_negocio;
	private Integer id_horario;
	private Integer id_grupoformato;

	private String nombre;
	private String descripcion;
	private String tipoformato = TIPO_FORMATO_ENTRADA;
	private Integer registrosporcarga = DEFAULT_NUM_REGISTROS_POR_CARGA;

	private String medio_formulario = Constantes.NO;
	private String medio_excel = Constantes.NO;
	private String medio_csv = Constantes.NO;
	private String medio_webservice = Constantes.NO;
	private String medio_pdf = Constantes.NO;
	private String control_usuario = Constantes.NO;
	private String activo = Constantes.NO;
	private String prog_liberacion = Constantes.NO;
	private String rechaza_registro = Constantes.NO;
	private String autorizacion_automatica = Constantes.NO;
	private String cargas_relacionadas = Constantes.NO;
	private Integer idformato_carga_relacionada;
	private Integer diasvigencia_carga_relacionada;
	private String horario_estricto = Constantes.SI;
	private String aplicar_automatico = Constantes.NO;
	private String archivos_adjuntos_posteriores = Constantes.NO;
	private Integer id_campo_totalizador;
	private String bloqueado = Constantes.NO;
	private String formato_externo;
	private String mensaje_liberacion;
	private String alerta_html;
	private String encabezado_html;
	private String pie_html;

	private String id_componente;

	private Integer tiempo_validacion_preparacion;
	private Integer tiempo_validacion_liberacion;

	private String para_liberador;
	private String para_director;

	private Integer idformato_salida;

	List<FormatoOperacion> operaciones = new ArrayList<FormatoOperacion>();
	
	private Integer valortramite;
	
	private String endpoint_validacion;
	
	private String usa_archivos_adjuntos;
	
	private String usa_adjuntos_formulario;

	private Integer validar_formato_menu;
	
	private String seccion_adjuntos_reporte;
	
	private String info_adjuntos_reporte; 
	
	private String extensiones_adjuntos_reporte; 
	
	private Integer limite_adjuntos_reporte; 
	
	private String titulo_seccion_adjuntos_reporte; 
	
	private String ayuda_adjuntos_reporte; 
	
	private String solicitud_inicial; 
	
	// ------------------------

	private FormatoCampo formatocampobase;

	// ------------------------
	
	private List<FormatoConfiguracion> configuraciones;
	
	// ------------------------

	public String getId_componente() {
		return id_componente;
	}

	public void setId_componente(String id_componente) {
		this.id_componente = id_componente;
	}

	public FormatoCampo getFormatocampobase() {
		return formatocampobase;
	}

	public void setFormatocampobase(FormatoCampo formatocampobase) {
		this.formatocampobase = formatocampobase;
	}

	public String getMensaje_liberacion() {
		return mensaje_liberacion;
	}

	public void setMensaje_liberacion(String mensaje_liberacion) {
		this.mensaje_liberacion = mensaje_liberacion;
	}

	public Integer getId_campo_totalizador() {
		return id_campo_totalizador;
	}

	public void setId_campo_totalizador(Integer id_campo_totalizador) {
		this.id_campo_totalizador = id_campo_totalizador;
	}

	public String getArchivos_adjuntos_posteriores() {
		return archivos_adjuntos_posteriores;
	}

	public void setArchivos_adjuntos_posteriores(String requiere_archivos_adjuntos_posteriores) {
		this.archivos_adjuntos_posteriores = requiere_archivos_adjuntos_posteriores;
	}

	public String getHorario_estricto() {
		return horario_estricto;
	}

	public void setHorario_estricto(String horario_estricto) {
		this.horario_estricto = horario_estricto;
	}

	public Integer getIdformato_carga_relacionada() {
		return idformato_carga_relacionada;
	}

	public void setIdformato_carga_relacionada(Integer idformato_carga_relacionada) {
		this.idformato_carga_relacionada = idformato_carga_relacionada;
	}

	public Integer getDiasvigencia_carga_relacionada() {
		return diasvigencia_carga_relacionada;
	}

	public void setDiasvigencia_carga_relacionada(Integer diasvigencia_carga_relacionada) {
		this.diasvigencia_carga_relacionada = diasvigencia_carga_relacionada;
	}

	public String getCargas_relacionadas() {
		return cargas_relacionadas;
	}

	public void setCargas_relacionadas(String cargas_relacionadas) {
		this.cargas_relacionadas = cargas_relacionadas;
	}

	public String getAutorizacion_automatica() {
		return autorizacion_automatica;
	}

	public void setAutorizacion_automatica(String autorizacion_automatica) {
		this.autorizacion_automatica = autorizacion_automatica;
	}

	public String getProg_liberacion() {
		return prog_liberacion;
	}

	public void setProg_liberacion(String progLiberacion) {
		prog_liberacion = progLiberacion;
	}

	private String endPoint;

	// -----------------------------------------

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getControl_usuario() {
		return control_usuario;
	}

	public void setControl_usuario(String control_usuario) {
		this.control_usuario = control_usuario;
	}

	public Integer getId_formato() {
		return id_formato;
	}

	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}

	public Integer getId_modelo() {
		return id_modelo;
	}

	public void setId_modelo(Integer id_modelo) {
		this.id_modelo = id_modelo;
	}

	public String getNombre() {
		return StringUtils.trim(nombre);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId_estructura() {
		return id_estructura;
	}

	public void setId_estructura(Integer id_estructura) {
		this.id_estructura = id_estructura;
	}

	public String getTipoformato() {
		return tipoformato;
	}

	public void setTipoformato(String tipoFormato) {
		this.tipoformato = tipoFormato;
	}

	public Integer getRegistrosporcarga() {
		return registrosporcarga;
	}

	public void setRegistrosporcarga(Integer registrosPorCarga) {
		this.registrosporcarga = registrosPorCarga;
	}

	public String getMedio_formulario() {
		return medio_formulario;
	}

	public void setMedio_formulario(String medio_Formulario) {
		this.medio_formulario = medio_Formulario;
	}

	public String getMedio_excel() {
		return medio_excel;
	}

	public void setMedio_excel(String medio_Excel) {
		this.medio_excel = medio_Excel;
	}

	public String getMedio_csv() {
		return medio_csv;
	}

	public void setMedio_csv(String medio_CVS) {
		this.medio_csv = medio_CVS;
	}

	public String getMedio_webservice() {
		return medio_webservice;
	}

	public void setMedio_webservice(String medio_webservice) {
		this.medio_webservice = medio_webservice;
	}

	public void setMedio_pdf(String medio_pdf) {
		this.medio_pdf = medio_pdf;
	}

	public String getMedio_pdf() {
		return medio_pdf;
	}

	public Integer getId_negocio() {
		return id_negocio;
	}

	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}

	public Integer getMedios_total() {
		int total = 0;
		if (medio_csv.equals("S")) {
			total += 1;
		}
		if (medio_excel.equals("S")) {
			total += 1;
		}
		if (medio_formulario.equals("S")) {
			total += 1;
		}
		if (medio_pdf.equals("S")) {
			total += 1;
		}
		if (medio_webservice.equals("S")) {
			total += 1;
		}
		return total;
	}

	public String getRechaza_registro() {
		return rechaza_registro;
	}

	public void setRechaza_registro(String rechazaRegistro) {
		rechaza_registro = rechazaRegistro;
	}

	public Integer getId_horario() {
		return id_horario;
	}

	public void setId_horario(Integer id_horario) {
		this.id_horario = id_horario;
	}

	public String toString() {
		return this.id_formato + " : " + this.nombre;
	}

	public String getAplicar_automatico() {
		return aplicar_automatico;
	}

	public void setAplicar_automatico(String aplica_automatico) {
		this.aplicar_automatico = aplica_automatico;
	}

	public String getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}

	public String getFormato_externo() {
		return formato_externo;
	}

	public void setFormato_externo(String formato_externo) {
		this.formato_externo = formato_externo;
	}

	public Integer getTiempo_validacion_preparacion() {
		return tiempo_validacion_preparacion;
	}

	public void setTiempo_validacion_preparacion(Integer tiempo_validacion_preparacion) {
		this.tiempo_validacion_preparacion = tiempo_validacion_preparacion;
	}

	public Integer getTiempo_validacion_liberacion() {
		return tiempo_validacion_liberacion;
	}

	public void setTiempo_validacion_liberacion(Integer tiempo_validacion_liberacion) {
		this.tiempo_validacion_liberacion = tiempo_validacion_liberacion;
	}

	public Integer getId_grupoformato() {
		return id_grupoformato;
	}

	public void setId_grupoformato(Integer id_grupoformato) {
		this.id_grupoformato = id_grupoformato;
	}

	public String getPara_liberador() {
		return para_liberador;
	}

	public void setPara_liberador(String para_liberador) {
		this.para_liberador = para_liberador;
	}

	public String getPara_director() {
		return para_director;
	}

	public void setPara_director(String para_director) {
		this.para_director = para_director;
	}

	public void setIdformato_salida(Integer idformato_salida) {
		this.idformato_salida = idformato_salida;
	}

	public Integer getIdformato_salida() {
		return idformato_salida;
	}

	public List<FormatoOperacion> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(List<FormatoOperacion> formatoOperaciones) {
		this.operaciones = formatoOperaciones;
	}
	
	public List<FormatoConfiguracion> getConfiguraciones(){
		if (configuraciones == null){
			configuraciones = FormatoConfiguracionServicio.getInstance().obtenerConfiguracionesFormato(id_formato);
		}
		return configuraciones;
	}

	public Integer getValortramite() {
		return valortramite;
	}
	
	public void setValortramite(Integer valortramite) {
		this.valortramite = valortramite;
	}

	public String getEndpoint_validacion() {
		return endpoint_validacion;
	}

	public void setEndpoint_validacion(String endpoint_validacion) {
		this.endpoint_validacion = endpoint_validacion;
	}

	public String getAlerta_html() {
		return alerta_html;
	}

	public void setAlerta_html(String alerta_html) {
		this.alerta_html = alerta_html;
	}

	public String getEncabezado_html() {
		return encabezado_html;
	}

	public void setEncabezado_html(String encabezado_html) {
		this.encabezado_html = encabezado_html;
	}

	public String getPie_html() {
		return pie_html;
	}

	public void setPie_html(String pie_html) {
		this.pie_html = pie_html;
	}

	public String getUsa_archivos_adjuntos() {
		return usa_archivos_adjuntos;
	}

	public void setUsa_archivos_adjuntos(String usa_archivos_adjuntos) {
		this.usa_archivos_adjuntos = usa_archivos_adjuntos;
	}

	public String getUsa_adjuntos_formulario() {
		return usa_adjuntos_formulario;
	}

	public void setUsa_adjuntos_formulario(String usa_adjuntos_formulario) {
		this.usa_adjuntos_formulario = usa_adjuntos_formulario;
	}

	public Integer getValidar_formato_menu() {
		return validar_formato_menu;
	}

	public void setValidar_formato_menu(Integer validar_formato_menu) {
		this.validar_formato_menu = validar_formato_menu;
	}

	public String getSeccion_adjuntos_reporte() {
		return seccion_adjuntos_reporte;
	}

	public void setSeccion_adjuntos_reporte(String seccion_adjuntos_reporte) {
		this.seccion_adjuntos_reporte = seccion_adjuntos_reporte;
	}
	
	public String getInfo_adjuntos_reporte() {
		return info_adjuntos_reporte;
	}

	public void setInfo_adjuntos_reporte(String info_adjuntos_reporte) {
		this.info_adjuntos_reporte = info_adjuntos_reporte;
	}
	
	
	public String getExtensiones_adjuntos_reporte() {
		return extensiones_adjuntos_reporte;
	}

	public void setExtensiones_adjuntos_reporte(String extensiones_adjuntos_reporte) {
		this.extensiones_adjuntos_reporte = extensiones_adjuntos_reporte;
	}
	
	
	public Integer getLimite_adjuntos_reporte() {
		return limite_adjuntos_reporte;
	}

	public void setLimite_adjuntos_reporte(Integer limite_adjuntos_reporte) {
		this.limite_adjuntos_reporte = limite_adjuntos_reporte;
	}
	
	public String getTitulo_seccion_adjuntos_reporte() {
		return titulo_seccion_adjuntos_reporte;
	}

	public void setTitulo_seccion_adjuntos_reporte(String titulo_seccion_adjuntos_reporte) {
		this.titulo_seccion_adjuntos_reporte = titulo_seccion_adjuntos_reporte;
	}
	
	public String getAyuda_adjuntos_reporte() {
		return ayuda_adjuntos_reporte;
	}

	public void setAyuda_adjuntos_reporte(String ayuda_adjuntos_reporte) {
		this.ayuda_adjuntos_reporte = ayuda_adjuntos_reporte;
	}

	public String getSolicitud_inicial() {
		return solicitud_inicial;
	}

	public void setSolicitud_inicial(String solicitud_inicial) {
		this.solicitud_inicial = solicitud_inicial;
	}
}
