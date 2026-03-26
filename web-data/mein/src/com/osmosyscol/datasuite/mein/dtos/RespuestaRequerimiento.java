package com.osmosyscol.datasuite.mein.dtos;

import com.osmosyscol.datasuite.mein.interfaces.SolicitudMI;

public class RespuestaRequerimiento extends SolicitudMI {
	
	private Integer id;
	private Integer idcarga;
	private Integer numero_solicitud; // linkedIdCarga
	private String observacion;
	private String numero_radicado_postal;
	private String intendencia;
	private Integer deudor;
	private Deudor deudor_obj;
	private String numero_proceso;
	private Integer tipo_solicitante;
	private ObjGenerico tipo_solicitante_obj;
	private Integer tipo_solicitud;
	private ObjGenerico tipo_solicitud_obj;
	private Integer tramite_otros_documentos;
	private ObjGenerico tramite_otros_documentos_obj;
	private Integer dependencia;
	private APVistaDependencias dependencia_obj;
	private Integer intendencia_regional;
	private ObjGenerico intendencia_regional_obj;
	private Integer medio_envio;
	private APVistaMedioEnvio medio_envio_obj;
	private Integer tipo_seguridad;
	private APVistaSeguridadTipo tipo_seguridad_obj;
	private Integer tramite;
	private APVistaTramite tramite_obj;
	private Integer proceso_clase;
	private APVistaProcesosClases proceso_clase_obj;
	private Integer tipo_cuaderno;
	private APVistaCuaderno tipo_cuaderno_obj;
	private Integer tipo_auto_acta;
	private APVistaTipoAutoActa tipo_auto_acta_obj;
	private Integer serie;
	private APVistaSerie tipo_serie_obj;
	private Integer subserie;
	private APVistaSubserie tipo_subserie_obj;
	
	private String estado_envio_bpm;
	private String estado_generacion_sticker;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdcarga() {
		return idcarga;
	}
	public void setIdcarga(Integer idcarga) {
		this.idcarga = idcarga;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Integer getNumero_solicitud() {
		return numero_solicitud;
	}
	
	public String getNumero_solicitud_formato() {
		if (tipo_solicitud_obj != null) {
			return numero_solicitud + " - " + tipo_solicitud_obj.getNombre();
		}
		return numero_solicitud.toString();
	}
	
	public void setNumero_solicitud(Integer numero_solicitud) {
		this.numero_solicitud = numero_solicitud;
	}
	public String getNumero_radicado_postal() {
		return numero_radicado_postal;
	}
	public void setNumero_radicado_postal(String numero_radicado_postal) {
		this.numero_radicado_postal = numero_radicado_postal;
	}
	public String getIntendencia() {
		return intendencia;
	}
	public void setIntendencia(String intendencia) {
		this.intendencia = intendencia;
	}
	public Integer getDeudor() {
		return deudor;
	}
	public void setDeudor(Integer deudor) {
		this.deudor = deudor;
	}
	public Deudor getDeudor_obj() {
		return deudor_obj;
	}
	public void setDeudor_obj(Deudor deudor_obj) {
		this.deudor_obj = deudor_obj;
	}
	public String getNumero_proceso() {
		return numero_proceso;
	}
	public void setNumero_proceso(String numero_proceso) {
		this.numero_proceso = numero_proceso;
	}
	public Integer getTipo_solicitante() {
		return tipo_solicitante;
	}
	public void setTipo_solicitante(Integer tipo_solicitante) {
		this.tipo_solicitante = tipo_solicitante;
	}
	public ObjGenerico getTipo_solicitante_obj() {
		return tipo_solicitante_obj;
	}
	public void setTipo_solicitante_obj(ObjGenerico tipo_solicitante_obj) {
		this.tipo_solicitante_obj = tipo_solicitante_obj;
	}
	public Integer getTipo_solicitud() {
		return tipo_solicitud;
	}
	public void setTipo_solicitud(Integer tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
	}
	public ObjGenerico getTipo_solicitud_obj() {
		return tipo_solicitud_obj;
	}
	public void setTipo_solicitud_obj(ObjGenerico tipo_solicitud_obj) {
		this.tipo_solicitud_obj = tipo_solicitud_obj;
	}
	public Integer getTramite_otros_documentos() {
		return tramite_otros_documentos;
	}
	public void setTramite_otros_documentos(Integer tramite_otros_documentos) {
		this.tramite_otros_documentos = tramite_otros_documentos;
	}
	public ObjGenerico getTramite_otros_documentos_obj() {
		return tramite_otros_documentos_obj;
	}
	public void setTramite_otros_documentos_obj(
			ObjGenerico tramite_otros_documentos_obj) {
		this.tramite_otros_documentos_obj = tramite_otros_documentos_obj;
	}
	public Integer getDependencia() {
		return dependencia;
	}
	public void setDependencia(Integer dependencia) {
		this.dependencia = dependencia;
	}
	public APVistaDependencias getDependencia_obj() {
		return dependencia_obj;
	}
	public void setDependencia_obj(APVistaDependencias dependencia_obj) {
		this.dependencia_obj = dependencia_obj;
	}
	public Integer getIntendencia_regional() {
		return intendencia_regional;
	}
	public void setIntendencia_regional(Integer intendencia_regional) {
		this.intendencia_regional = intendencia_regional;
	}
	public ObjGenerico getIntendencia_regional_obj() {
		return intendencia_regional_obj;
	}
	public void setIntendencia_regional_obj(ObjGenerico intendencia_regional_obj) {
		this.intendencia_regional_obj = intendencia_regional_obj;
	}
	public Integer getMedio_envio() {
		return medio_envio;
	}
	public void setMedio_envio(Integer medio_envio) {
		this.medio_envio = medio_envio;
	}
	public APVistaMedioEnvio getMedio_envio_obj() {
		return medio_envio_obj;
	}
	public void setMedio_envio_obj(APVistaMedioEnvio medio_envio_obj) {
		this.medio_envio_obj = medio_envio_obj;
	}
	public Integer getTipo_seguridad() {
		return tipo_seguridad;
	}
	public void setTipo_seguridad(Integer tipo_seguridad) {
		this.tipo_seguridad = tipo_seguridad;
	}
	public APVistaSeguridadTipo getTipo_seguridad_obj() {
		return tipo_seguridad_obj;
	}
	public void setTipo_seguridad_obj(APVistaSeguridadTipo tipo_seguridad_obj) {
		this.tipo_seguridad_obj = tipo_seguridad_obj;
	}
	public Integer getTramite() {
		return tramite;
	}
	public void setTramite(Integer tramite) {
		this.tramite = tramite;
	}
	public APVistaTramite getTramite_obj() {
		return tramite_obj;
	}
	public void setTramite_obj(APVistaTramite tramite_obj) {
		this.tramite_obj = tramite_obj;
	}
	public Integer getProceso_clase() {
		return proceso_clase;
	}
	public void setProceso_clase(Integer proceso_clase) {
		this.proceso_clase = proceso_clase;
	}
	public APVistaProcesosClases getProceso_clase_obj() {
		return proceso_clase_obj;
	}
	public void setProceso_clase_obj(APVistaProcesosClases proceso_clase_obj) {
		this.proceso_clase_obj = proceso_clase_obj;
	}
	public Integer getTipo_cuaderno() {
		return tipo_cuaderno;
	}
	public void setTipo_cuaderno(Integer tipo_cuaderno) {
		this.tipo_cuaderno = tipo_cuaderno;
	}
	public APVistaCuaderno getTipo_cuaderno_obj() {
		return tipo_cuaderno_obj;
	}
	public void setTipo_cuaderno_obj(APVistaCuaderno tipo_cuaderno_obj) {
		this.tipo_cuaderno_obj = tipo_cuaderno_obj;
	}
	public Integer getSerie() {
		return serie;
	}
	public void setSerie(Integer serie) {
		this.serie = serie;
	}
	public APVistaSerie getTipo_serie_obj() {
		return tipo_serie_obj;
	}
	public void setTipo_serie_obj(APVistaSerie tipo_serie_obj) {
		this.tipo_serie_obj = tipo_serie_obj;
	}
	public Integer getSubserie() {
		return subserie;
	}
	public void setSubserie(Integer subserie) {
		this.subserie = subserie;
	}
	public APVistaSubserie getTipo_subserie_obj() {
		return tipo_subserie_obj;
	}
	public void setTipo_subserie_obj(APVistaSubserie tipo_subserie_obj) {
		this.tipo_subserie_obj = tipo_subserie_obj;
	}
	public Integer getTipo_auto_acta() {
		return tipo_auto_acta;
	}
	public void setTipo_auto_acta(Integer tipo_auto_acta) {
		this.tipo_auto_acta = tipo_auto_acta;
	}
	public APVistaTipoAutoActa getTipo_auto_acta_obj() {
		return tipo_auto_acta_obj;
	}
	public void setTipo_auto_acta_obj(APVistaTipoAutoActa tipo_auto_acta_obj) {
		this.tipo_auto_acta_obj = tipo_auto_acta_obj;
	}
	public String getEstado_envio_bpm() {
		return estado_envio_bpm;
	}
	public void setEstado_envio_bpm(String estado_envio_bpm) {
		this.estado_envio_bpm = estado_envio_bpm;
	}
	public String getEstado_generacion_sticker() {
		return estado_generacion_sticker;
	}
	public void setEstado_generacion_sticker(String estado_generacion_sticker) {
		this.estado_generacion_sticker = estado_generacion_sticker;
	}
	@Override
	public String obtenerNumeroRadicado() {
		return this.numero_radicado_postal;
	}
	@Override
	public String obtenerNumeroProceso() {
		return this.numero_proceso;
	}
	

}
