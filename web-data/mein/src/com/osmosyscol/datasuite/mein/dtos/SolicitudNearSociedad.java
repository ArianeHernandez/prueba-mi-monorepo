package com.osmosyscol.datasuite.mein.dtos;

import java.util.Date;

import com.osmosyscol.datasuite.mein.interfaces.SolicitudMI;


public class SolicitudNearSociedad extends SolicitudMI{
	
	private Integer id;
	private Integer idcarga;
	private Sociedad deudor;
	private Integer situacion_presentada;
	private ObjGenerico situacion_presentada_obj;
	private String adel_acreedores;
	private ObjGenerico adel_acreedores_obj;
	private Integer emergencia_economica;
	private InformacionFinanciera informacion_financiera;
	private CertificacionRepLegal certificacion_representan;
	private String memoria_explicativa;
	private RelacionPasivos relacion_de_pasivos;
	private Acreedor proyecto_de_calificacion;
	private ProcesoEjecutivo procesos_ejecutivos;
	private CertificacionRevisorFis certificacion_revisor_fis;
	private CertificacionContador certificacion_contador;
	private String numero_radicado_postal;	
	private Integer checklist_solicitud;
	private Checklist checklist_solicitud_obj;
	private TipoSolicitud tipo_solicitud;
	private Integer intendencia_regional;
	private ObjGenerico intendencia_regional_obj;
	private Date fecha_vencimiento;
	private Integer tipo_solicitante;
	private ObjGenerico tipo_solicitante_obj;
	private String numero_proceso;
	
	// Campos adicionales post produccion
	private Integer dependencia;
	private APVistaDependencias dependencia_obj;
	private Integer medio_envio;
	private APVistaMedioEnvio medio_envio_obj;
	private Integer cuaderno;
	private APVistaCuaderno cuaderno_obj;
	private Integer tipo_seguridad;
	private APVistaSeguridadTipo tipo_seguridad_obj;
	private Integer tramite;
	private APVistaTramite tramite_obj;
	private Integer procesos_clases;
	private APVistaProcesosClases procesos_clases_obj;
	private Integer tipo_auto_acta;
	private APVistaTipoAutoActa tipo_auto_acta_obj;
	
	private String estado_envio_bpm;
	private String estado_generacion_sticker;
	
	private Integer serieId; 
	private Integer subserieId; 
	private APVistaSerie serie; 
	private APVistaSubserie subserie;
	
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
	public Sociedad getDeudor() {
		return deudor;
	}
	public void setDeudor(Sociedad deudor) {
		this.deudor = deudor;
	}
	public Integer getSituacion_presentada() {
		return situacion_presentada;
	}
	public void setSituacion_presentada(Integer situacion_presentada) {
		this.situacion_presentada = situacion_presentada;
	}
	public ObjGenerico getSituacion_presentada_obj() {
		return situacion_presentada_obj;
	}
	public void setSituacion_presentada_obj(ObjGenerico situacion_presentada_obj) {
		this.situacion_presentada_obj = situacion_presentada_obj;
	}
	public String getAdel_acreedores() {
		return adel_acreedores;
	}
	public void setAdel_acreedores(String adel_acreedores) {
		this.adel_acreedores = adel_acreedores;
	}
	public ObjGenerico getAdel_acreedores_obj() {
		return adel_acreedores_obj;
	}
	public void setAdel_acreedores_obj(ObjGenerico adel_acreedores_obj) {
		this.adel_acreedores_obj = adel_acreedores_obj;
	}
	public Integer getEmergencia_economica() {
		return emergencia_economica;
	}
	public void setEmergencia_economica(Integer emergencia_economica) {
		this.emergencia_economica = emergencia_economica;
	}
	public InformacionFinanciera getInformacion_financiera() {
		return informacion_financiera;
	}
	public void setInformacion_financiera(
			InformacionFinanciera informacion_financiera) {
		this.informacion_financiera = informacion_financiera;
	}
	public CertificacionRepLegal getCertificacion_representan() {
		return certificacion_representan;
	}
	public void setCertificacion_representan(
			CertificacionRepLegal certificacion_representan) {
		this.certificacion_representan = certificacion_representan;
	}
	public String getMemoria_explicativa() {
		return memoria_explicativa;
	}
	public void setMemoria_explicativa(String memoria_explicativa) {
		this.memoria_explicativa = memoria_explicativa;
	}
	public RelacionPasivos getRelacion_de_pasivos() {
		return relacion_de_pasivos;
	}
	public void setRelacion_de_pasivos(RelacionPasivos relacion_de_pasivos) {
		this.relacion_de_pasivos = relacion_de_pasivos;
	}
	public Acreedor getProyecto_de_calificacion() {
		return proyecto_de_calificacion;
	}
	public void setProyecto_de_calificacion(Acreedor proyecto_de_calificacion) {
		this.proyecto_de_calificacion = proyecto_de_calificacion;
	}
	public ProcesoEjecutivo getProcesos_ejecutivos() {
		return procesos_ejecutivos;
	}
	public void setProcesos_ejecutivos(ProcesoEjecutivo procesos_ejecutivos) {
		this.procesos_ejecutivos = procesos_ejecutivos;
	}
	public CertificacionRevisorFis getCertificacion_revisor_fis() {
		return certificacion_revisor_fis;
	}
	public void setCertificacion_revisor_fis(
			CertificacionRevisorFis certificacion_revisor_fis) {
		this.certificacion_revisor_fis = certificacion_revisor_fis;
	}
	public CertificacionContador getCertificacion_contador() {
		return certificacion_contador;
	}
	public void setCertificacion_contador(
			CertificacionContador certificacion_contador) {
		this.certificacion_contador = certificacion_contador;
	}
	public String getNumero_radicado_postal() {
		return numero_radicado_postal;
	}
	public void setNumero_radicado_postal(String numero_radicado_postal) {
		this.numero_radicado_postal = numero_radicado_postal;
	}
	public Integer getChecklist_solicitud() {
		return checklist_solicitud;
	}
	public void setChecklist_solicitud(Integer checklist_solicitud) {
		this.checklist_solicitud = checklist_solicitud;
	}
	public TipoSolicitud getTipo_solicitud() {
		return tipo_solicitud;
	}
	public void setTipo_solicitud(TipoSolicitud tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
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
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	public Integer getTipo_solicitante() {
		return tipo_solicitante;
	}
	public void setTipo_solicitante(Integer tipo_solicitante) {
		this.tipo_solicitante = tipo_solicitante;
	}
	public String getNumero_proceso() {
		return numero_proceso;
	}
	public void setNumero_proceso(String numero_proceso) {
		this.numero_proceso = numero_proceso;
	}
	public Checklist getChecklist_solicitud_obj() {
		return checklist_solicitud_obj;
	}
	public void setChecklist_solicitud_obj(Checklist checklist_solicitud_obj) {
		this.checklist_solicitud_obj = checklist_solicitud_obj;
	}
	public APVistaDependencias getDependencia_obj() {
		return dependencia_obj;
	}
	public void setDependencia_obj(APVistaDependencias dependencia_obj) {
		this.dependencia_obj = dependencia_obj;
	}
	public Integer getDependencia() {
		return dependencia;
	}
	public void setDependencia(Integer dependencia) {
		this.dependencia = dependencia;
	}
	public APVistaMedioEnvio getMedio_envio_obj() {
		return medio_envio_obj;
	}
	public void setMedio_envio_obj(APVistaMedioEnvio medio_envio_obj) {
		this.medio_envio_obj = medio_envio_obj;
	}
	public Integer getMedio_envio() {
		return medio_envio;
	}
	public void setMedio_envio(Integer medio_envio) {
		this.medio_envio = medio_envio;
	}
	public APVistaCuaderno getCuaderno_obj() {
		return cuaderno_obj;
	}
	public void setCuaderno_obj(APVistaCuaderno cuaderno_obj) {
		this.cuaderno_obj = cuaderno_obj;
	}
	public Integer getCuaderno() {
		return cuaderno;
	}
	public void setCuaderno(Integer cuaderno) {
		this.cuaderno = cuaderno;
	}
	public APVistaSeguridadTipo getTipo_seguridad_obj() {
		return tipo_seguridad_obj;
	}
	public void setTipo_seguridad_obj(APVistaSeguridadTipo tipo_seguridad_obj) {
		this.tipo_seguridad_obj = tipo_seguridad_obj;
	}
	public Integer getTipo_seguridad() {
		return tipo_seguridad;
	}
	public void setTipo_seguridad(Integer tipo_seguridad) {
		this.tipo_seguridad = tipo_seguridad;
	}
	public APVistaTramite getTramite_obj() {
		return tramite_obj;
	}
	public void setTramite_obj(APVistaTramite tramite_obj) {
		this.tramite_obj = tramite_obj;
	}
	public Integer getTramite() {
		return tramite;
	}
	public void setTramite(Integer tramite) {
		this.tramite = tramite;
	}
	public APVistaProcesosClases getProcesos_clases_obj() {
		return procesos_clases_obj;
	}
	public void setProcesos_clases_obj(APVistaProcesosClases procesos_clases_obj) {
		this.procesos_clases_obj = procesos_clases_obj;
	}
	public Integer getProcesos_clases() {
		return procesos_clases;
	}
	public void setProcesos_clases(Integer procesos_clases) {
		this.procesos_clases = procesos_clases;
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
	public ObjGenerico getTipo_solicitante_obj() {
		return tipo_solicitante_obj;
	}
	public void setTipo_solicitante_obj(ObjGenerico tipo_solicitante_obj) {
		this.tipo_solicitante_obj = tipo_solicitante_obj;
	}
	
	public APVistaSerie getSerie() {
		return serie;
	}
	public void setSerie(APVistaSerie serie) {
		this.serie = serie;
	}
	
	public APVistaSubserie getSubserie() {
		return subserie;
	}
	public void setSubserie(APVistaSubserie subserie) {
		this.subserie = subserie;
	}
	
	public Integer getSerieId() {
		return serieId;
	}
	public void setSerieId(Integer serieId) {
		this.serieId = serieId;
	}
	
	public Integer getSubserieId() {
		return subserieId;
	}
	public void setSubserieId(Integer subserieId) {
		this.subserieId = subserieId;
	}
}
