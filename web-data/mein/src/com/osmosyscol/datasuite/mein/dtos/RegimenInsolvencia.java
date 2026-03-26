package com.osmosyscol.datasuite.mein.dtos;

import com.osmosyscol.datasuite.mein.interfaces.SolicitudMI;

public class RegimenInsolvencia extends SolicitudMI {

	private Integer id;
	private Integer idCarga;
	private Integer tipo_solicitante;
	private Integer tipo_solicitud;
	private ObjGenerico tipo_solicitud_obj;
	private String norma_aplicable;
	private Deudor deudor;
	private Integer deudorId;
	private BienesAcreedores bienes_acreedores;
	private Integer bienes_acreedores_id;
	private PropuestaNegociacion propuesta_negociacion;
	private Integer propuesta_negociacion_id;
	private InfoFinancieraMensual info_financiera_mensual;
	private Integer info_financiera_mensual_id;
	private InfoFinancieraAnual info_financiera_anual;
	private Integer info_financiera_anual_id;
	private ConjuntoEEFF conjunto_eeff;
	private Integer conjunto_eeff_id;
	private InventarioActivoPasivo inventario_activos_pasivos;
	private Integer inventario_activos_pasivos_id;
	private OtraInfoRequerida otra_informacion;
	private Integer otra_informacion_id;
	private PlanNegocios plan_negocios;
	private Integer plan_negocios_id;
	private FlujoCaja flujo_caja;
	private Integer flujo_caja_id;
	private ProyectoCalificacion proyecto_calificacion;
	private Integer proyecto_calificacion_id;
	private AcuerdoExtrajudicial acuerdo_extrajudicial;
	private Integer acuerdo_extrajudicial_id;
	private AcuerdoRecuperacion acuerdo_recuperacion;
	private Integer acuerdo_recuperacion_id;
	private PruebaAprobacion prueba_aprobacion;
	private Integer prueba_aprobacion_id;
	private ComunicacionAcreedores comunicacion_acreedores;
	private Integer comunicacion_acreedores_id;
	private BienesSujetosGarantia bienes_sujetos_garantia;
	private Integer bienes_sujetos_garantia_id;
	private BienesGarantiaPNNC bienes_garantia_pnnc;
	private Integer bienes_garantia_pnnc_id;
	private String numero_proceso;
	private String numero_radicado;
	private APVistaDependencias dependencia;
	private Integer dependencia_id;
	private Integer intendencia_regional_id;
	private ObjGenerico intendencia_regional;
	private ProfesionalesAsociados profesionales_asociados;
	private Integer profesionales_asociados_id;
	private APVistaMedioEnvio medio_envio;
	private Integer medio_envio_id;
	private APVistaCuaderno cuaderno;
	private Integer cuaderno_id;
	private APVistaSerie serie;
	private Integer serie_id;
	private APVistaSubserie subserie;
	private Integer subserie_id;
	private APVistaSeguridadTipo tipo_seguridad;
	private Integer tipo_seguridad_id;
	private APVistaTramite tramite;
	private Integer tramite_id;
	
	private DatosBasicosPres datos_basicos_pres;
	private Integer datos_basicos_pres_id;
	private MemoriaExplicativa memoria_explicativa;
	private Integer memoria_explicativa_id;
	private CausasCesacionPagos causas_cesacion_pagos;
	private Integer causas_cesacion_pagos_id;
	private SupuestosAdmisibilidad supuestos_admisibilidad;
	private Integer supuestos_admisibilidad_id;
	private AutorizacionJunta autorizacion_junta;
	private Integer autorizacion_junta_id;
	private CondicionControlante condicion_controlante;
	private Integer condicion_controlante_id;
	private BienesPnnc bienes_pnnc;
	private Integer bienes_pnnc_id;
	private RelacionDePasivos relacion_de_pasivos;
	private Integer relacion_de_pasivos_id;
	private APVistaProcesosClases procesos_clases;
	private Integer procesos_clases_id;
	
	private InfoFinancMensualPNC info_financiera_mes_pnc;
	private Integer info_financiera_mes_pnc_id;
	private ConjuntoEEFFpnc conjunto_eeff_pnc;
	private Integer conjunto_eeff_pnc_id;
	private InventarioAyPNetos inventario_a_y_p_netos;
	private Integer inventario_a_y_p_netos_id;
	
	private Integer hipotesis;
	private CumplimientoHipotesis hipotesis_obj;
	
	private String estado_envio_bpm;
	private String estado_generacion_sticker;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCarga() {
		return idCarga;
	}
	public void setIdCarga(Integer idCarga) {
		this.idCarga = idCarga;
	}
	public Integer getTipo_de_solicitante() {
		return tipo_solicitante;
	}
	public void setTipo_de_solicitante(Integer tipo_de_solicitante) {
		this.tipo_solicitante = tipo_de_solicitante;
	}
	public String getNorma_aplicable() {
		return norma_aplicable;
	}
	public void setNorma_aplicable(String norma_aplicable) {
		this.norma_aplicable = norma_aplicable;
	}
	public Deudor getDeudor() {
		return deudor;
	}
	public void setDeudor(Deudor deudor) {
		this.deudor = deudor;
	}
	public Integer getDeudorId() {
		return deudorId;
	}
	public void setDeudorId(Integer deudorId) {
		this.deudorId = deudorId;
	}
	public BienesAcreedores getBienes_acreedores() {
		return bienes_acreedores;
	}
	public void setBienes_acreedores(BienesAcreedores bienes_acreedores) {
		this.bienes_acreedores = bienes_acreedores;
	}
	public Integer getBienes_acreedores_id() {
		return bienes_acreedores_id;
	}
	public void setBienes_acreedores_id(Integer bienes_acreedores_id) {
		this.bienes_acreedores_id = bienes_acreedores_id;
	}
	public PropuestaNegociacion getPropuesta_negociacion() {
		return propuesta_negociacion;
	}
	public void setPropuesta_negociacion(PropuestaNegociacion propuesta_negociacion) {
		this.propuesta_negociacion = propuesta_negociacion;
	}
	public Integer getPropuesta_negociacion_id() {
		return propuesta_negociacion_id;
	}
	public void setPropuesta_negociacion_id(Integer propuesta_negociacion_id) {
		this.propuesta_negociacion_id = propuesta_negociacion_id;
	}
	public InfoFinancieraMensual getInfo_financiera_mensual() {
		return info_financiera_mensual;
	}
	public void setInfo_financiera_mensual(
			InfoFinancieraMensual info_financiera_mensual) {
		this.info_financiera_mensual = info_financiera_mensual;
	}
	public Integer getInfo_financiera_mensual_id() {
		return info_financiera_mensual_id;
	}
	public void setInfo_financiera_mensual_id(Integer info_financiera_mensual_id) {
		this.info_financiera_mensual_id = info_financiera_mensual_id;
	}
	public InfoFinancieraAnual getInfo_financiera_anual() {
		return info_financiera_anual;
	}
	public void setInfo_financiera_anual(InfoFinancieraAnual info_financiera_anual) {
		this.info_financiera_anual = info_financiera_anual;
	}
	public Integer getInfo_financiera_anual_id() {
		return info_financiera_anual_id;
	}
	public void setInfo_financiera_anual_id(Integer info_financiera_anual_id) {
		this.info_financiera_anual_id = info_financiera_anual_id;
	}
	public ConjuntoEEFF getConjunto_eeff() {
		return conjunto_eeff;
	}
	public void setConjunto_eeff(ConjuntoEEFF conjunto_eeff) {
		this.conjunto_eeff = conjunto_eeff;
	}
	public Integer getConjunto_eeff_id() {
		return conjunto_eeff_id;
	}
	public void setConjunto_eeff_id(Integer conjunto_eeff_id) {
		this.conjunto_eeff_id = conjunto_eeff_id;
	}
	public InventarioActivoPasivo getInventario_activos_pasivos() {
		return inventario_activos_pasivos;
	}
	public void setInventario_activos_pasivos(
			InventarioActivoPasivo inventario_activos_pasivos) {
		this.inventario_activos_pasivos = inventario_activos_pasivos;
	}
	public Integer getInventario_activos_pasivos_id() {
		return inventario_activos_pasivos_id;
	}
	public void setInventario_activos_pasivos_id(
			Integer inventario_activos_pasivos_id) {
		this.inventario_activos_pasivos_id = inventario_activos_pasivos_id;
	}
	public OtraInfoRequerida getOtra_informacion() {
		return otra_informacion;
	}
	public void setOtra_informacion(OtraInfoRequerida otra_informacion) {
		this.otra_informacion = otra_informacion;
	}
	public Integer getOtra_informacion_id() {
		return otra_informacion_id;
	}
	public void setOtra_informacion_id(Integer otra_informacion_id) {
		this.otra_informacion_id = otra_informacion_id;
	}
	public PlanNegocios getPlan_negocios() {
		return plan_negocios;
	}
	public void setPlan_negocios(PlanNegocios plan_negocios) {
		this.plan_negocios = plan_negocios;
	}
	public Integer getPlan_negocios_id() {
		return plan_negocios_id;
	}
	public void setPlan_negocios_id(Integer plan_negocios_id) {
		this.plan_negocios_id = plan_negocios_id;
	}
	public Integer getTipo_solicitante() {
		return tipo_solicitante;
	}
	public void setTipo_solicitante(Integer tipo_solicitante) {
		this.tipo_solicitante = tipo_solicitante;
	}
	public Integer getTipo_solicitud() {
		return tipo_solicitud;
	}
	public void setTipo_solicitud(Integer tipo_solicitud) {
		this.tipo_solicitud = tipo_solicitud;
	}
	public FlujoCaja getFlujo_caja() {
		return flujo_caja;
	}
	public void setFlujo_caja(FlujoCaja flujo_caja) {
		this.flujo_caja = flujo_caja;
	}
	public Integer getFlujo_caja_id() {
		return flujo_caja_id;
	}
	public void setFlujo_caja_id(Integer flujo_caja_id) {
		this.flujo_caja_id = flujo_caja_id;
	}
	public ProyectoCalificacion getProyecto_calificacion() {
		return proyecto_calificacion;
	}
	public void setProyecto_calificacion(ProyectoCalificacion proyecto_calificacion) {
		this.proyecto_calificacion = proyecto_calificacion;
	}
	public Integer getProyecto_calificacion_id() {
		return proyecto_calificacion_id;
	}
	public void setProyecto_calificacion_id(Integer proyecto_calificacion_id) {
		this.proyecto_calificacion_id = proyecto_calificacion_id;
	}
	public AcuerdoExtrajudicial getAcuerdo_extrajudicial() {
		return acuerdo_extrajudicial;
	}
	public void setAcuerdo_extrajudicial(AcuerdoExtrajudicial acuerdo_extrajudicial) {
		this.acuerdo_extrajudicial = acuerdo_extrajudicial;
	}
	public Integer getAcuerdo_extrajudicial_id() {
		return acuerdo_extrajudicial_id;
	}
	public void setAcuerdo_extrajudicial_id(Integer acuerdo_extrajudicial_id) {
		this.acuerdo_extrajudicial_id = acuerdo_extrajudicial_id;
	}
	public AcuerdoRecuperacion getAcuerdo_recuperacion() {
		return acuerdo_recuperacion;
	}
	public void setAcuerdo_recuperacion(AcuerdoRecuperacion acuerdo_recuperacion) {
		this.acuerdo_recuperacion = acuerdo_recuperacion;
	}
	public Integer getAcuerdo_recuperacion_id() {
		return acuerdo_recuperacion_id;
	}
	public void setAcuerdo_recuperacion_id(Integer acuerdo_recuperacion_id) {
		this.acuerdo_recuperacion_id = acuerdo_recuperacion_id;
	}
	public PruebaAprobacion getPrueba_aprobacion() {
		return prueba_aprobacion;
	}
	public void setPrueba_aprobacion(PruebaAprobacion prueba_aprobacion) {
		this.prueba_aprobacion = prueba_aprobacion;
	}
	public Integer getPrueba_aprobacion_id() {
		return prueba_aprobacion_id;
	}
	public void setPrueba_aprobacion_id(Integer prueba_aprobacion_id) {
		this.prueba_aprobacion_id = prueba_aprobacion_id;
	}
	public ComunicacionAcreedores getComunicacion_acreedores() {
		return comunicacion_acreedores;
	}
	public void setComunicacion_acreedores(
			ComunicacionAcreedores comunicacion_acreedores) {
		this.comunicacion_acreedores = comunicacion_acreedores;
	}
	public Integer getComunicacion_acreedores_id() {
		return comunicacion_acreedores_id;
	}
	public void setComunicacion_acreedores_id(Integer comunicacion_acreedores_id) {
		this.comunicacion_acreedores_id = comunicacion_acreedores_id;
	}
	public BienesSujetosGarantia getBienes_sujetos_garantia() {
		return bienes_sujetos_garantia;
	}
	public void setBienes_sujetos_garantia(
			BienesSujetosGarantia bienes_sujetos_garantia) {
		this.bienes_sujetos_garantia = bienes_sujetos_garantia;
	}
	public Integer getBienes_sujetos_garantia_id() {
		return bienes_sujetos_garantia_id;
	}
	public void setBienes_sujetos_garantia_id(Integer bienes_sujetos_garantia_id) {
		this.bienes_sujetos_garantia_id = bienes_sujetos_garantia_id;
	}
	public BienesGarantiaPNNC getBienes_garantia_pnnc() {
		return bienes_garantia_pnnc;
	}
	public void setBienes_garantia_pnnc(BienesGarantiaPNNC bienes_garantia_pnnc) {
		this.bienes_garantia_pnnc = bienes_garantia_pnnc;
	}
	public Integer getBienes_garantia_pnnc_id() {
		return bienes_garantia_pnnc_id;
	}
	public void setBienes_garantia_pnnc_id(Integer bienes_garantia_pnnc_id) {
		this.bienes_garantia_pnnc_id = bienes_garantia_pnnc_id;
	}
	public String getNumero_proceso() {
		return numero_proceso;
	}
	public void setNumero_proceso(String numero_proceso) {
		this.numero_proceso = numero_proceso;
	}
	public String getNumero_radicado() {
		return numero_radicado;
	}
	public void setNumero_radicado(String numero_radicado) {
		this.numero_radicado = numero_radicado;
	}
	public APVistaDependencias getDependencia() {
		return dependencia;
	}
	public void setDependencia(APVistaDependencias dependencia) {
		this.dependencia = dependencia;
	}
	public Integer getDependencia_id() {
		return dependencia_id;
	}
	public void setDependencia_id(Integer dependencia_id) {
		this.dependencia_id = dependencia_id;
	}
	public Integer getIntendencia_regional_id() {
		return intendencia_regional_id;
	}
	public void setIntendencia_regional_id(Integer intendencia_regional) {
		this.intendencia_regional_id = intendencia_regional;
	}
	public ProfesionalesAsociados getProfesionales_asociados() {
		return profesionales_asociados;
	}
	public void setProfesionales_asociados(ProfesionalesAsociados profesionales_asociados) {
		this.profesionales_asociados = profesionales_asociados;
	}
	public APVistaMedioEnvio getMedio_envio() {
		return medio_envio;
	}
	public void setMedio_envio(APVistaMedioEnvio medio_envio) {
		this.medio_envio = medio_envio;
	}
	public Integer getMedio_envio_id() {
		return medio_envio_id;
	}
	public void setMedio_envio_id(Integer medio_envio_id) {
		this.medio_envio_id = medio_envio_id;
	}
	public APVistaCuaderno getCuaderno() {
		return cuaderno;
	}
	public void setCuaderno(APVistaCuaderno cuaderno) {
		this.cuaderno = cuaderno;
	}
	public Integer getCuaderno_id() {
		return cuaderno_id;
	}
	public void setCuaderno_id(Integer cuaderno_id) {
		this.cuaderno_id = cuaderno_id;
	}
	public APVistaSerie getSerie() {
		return serie;
	}
	public void setSerie(APVistaSerie serie) {
		this.serie = serie;
	}
	public Integer getSerie_id() {
		return serie_id;
	}
	public void setSerie_id(Integer serie_id) {
		this.serie_id = serie_id;
	}
	public APVistaSubserie getSubserie() {
		return subserie;
	}
	public void setSubserie(APVistaSubserie subserie) {
		this.subserie = subserie;
	}
	public Integer getSubserie_id() {
		return subserie_id;
	}
	public void setSubserie_id(Integer subserie_id) {
		this.subserie_id = subserie_id;
	}
	public APVistaSeguridadTipo getTipo_seguridad() {
		return tipo_seguridad;
	}
	public void setTipo_seguridad(APVistaSeguridadTipo tipo_seguridad) {
		this.tipo_seguridad = tipo_seguridad;
	}
	public Integer getTipo_seguridad_id() {
		return tipo_seguridad_id;
	}
	public void setTipo_seguridad_id(Integer tipo_seguridad_id) {
		this.tipo_seguridad_id = tipo_seguridad_id;
	}
	public APVistaTramite getTramite() {
		return tramite;
	}
	public void setTramite(APVistaTramite tramite) {
		this.tramite = tramite;
	}
	public Integer getTramite_id() {
		return tramite_id;
	}
	public void setTramite_id(Integer tramite_id) {
		this.tramite_id = tramite_id;
	}
	public DatosBasicosPres getDatos_basicos_pres() {
		return datos_basicos_pres;
	}
	public void setDatos_basicos_pres(DatosBasicosPres datos_basicos_pres) {
		this.datos_basicos_pres = datos_basicos_pres;
	}
	public MemoriaExplicativa getMemoria_explicativa() {
		return memoria_explicativa;
	}
	public void setMemoria_explicativa(MemoriaExplicativa memoria_explicativa) {
		this.memoria_explicativa = memoria_explicativa;
	}
	public CausasCesacionPagos getCausas_cesacion_pagos() {
		return causas_cesacion_pagos;
	}
	public void setCausas_cesacion_pagos(CausasCesacionPagos causas_cesacion_pagos) {
		this.causas_cesacion_pagos = causas_cesacion_pagos;
	}
	public SupuestosAdmisibilidad getSupuestos_admisibilidad() {
		return supuestos_admisibilidad;
	}
	public void setSupuestos_admisibilidad(
			SupuestosAdmisibilidad supuestos_admisibilidad) {
		this.supuestos_admisibilidad = supuestos_admisibilidad;
	}
	public AutorizacionJunta getAutorizacion_junta() {
		return autorizacion_junta;
	}
	public void setAutorizacion_junta(AutorizacionJunta autorizacion_junta) {
		this.autorizacion_junta = autorizacion_junta;
	}
	public CondicionControlante getCondicion_controlante() {
		return condicion_controlante;
	}
	public void setCondicion_controlante(
			CondicionControlante condicion_controlante) {
		this.condicion_controlante = condicion_controlante;
	}
	public BienesPnnc getBienes_pnnc() {
		return bienes_pnnc;
	}
	public void setBienes_pnnc(BienesPnnc bienes_pnnc) {
		this.bienes_pnnc = bienes_pnnc;
	}
	public RelacionDePasivos getRelacion_de_pasivos() {
		return relacion_de_pasivos;
	}
	public void setRelacion_de_pasivos(RelacionDePasivos relacion_de_pasivos) {
		this.relacion_de_pasivos = relacion_de_pasivos;
	}
	public APVistaProcesosClases getProcesos_clases() {
		return procesos_clases;
	}
	public void setProcesos_clases(APVistaProcesosClases procesos_clases) {
		this.procesos_clases = procesos_clases;
	}
	public Integer getDatos_basicos_pres_id() {
		return datos_basicos_pres_id;
	}
	public void setDatos_basicos_pres_id(Integer datos_basicos_pres_id) {
		this.datos_basicos_pres_id = datos_basicos_pres_id;
	}
	public Integer getMemoria_explicativa_id() {
		return memoria_explicativa_id;
	}
	public void setMemoria_explicativa_id(Integer memoria_explicativa_id) {
		this.memoria_explicativa_id = memoria_explicativa_id;
	}
	public Integer getCausas_cesacion_pagos_id() {
		return causas_cesacion_pagos_id;
	}
	public void setCausas_cesacion_pagos_id(Integer causas_cesacion_pagos_id) {
		this.causas_cesacion_pagos_id = causas_cesacion_pagos_id;
	}
	public Integer getSupuestos_admisibilidad_id() {
		return supuestos_admisibilidad_id;
	}
	public void setSupuestos_admisibilidad_id(Integer supuestos_admisibilidad_id) {
		this.supuestos_admisibilidad_id = supuestos_admisibilidad_id;
	}
	public Integer getAutorizacion_junta_id() {
		return autorizacion_junta_id;
	}
	public void setAutorizacion_junta_id(Integer autorizacion_junta_id) {
		this.autorizacion_junta_id = autorizacion_junta_id;
	}
	public Integer getCondicion_controlante_id() {
		return condicion_controlante_id;
	}
	public void setCondicion_controlante_id(Integer condicion_controlante_id) {
		this.condicion_controlante_id = condicion_controlante_id;
	}
	public Integer getBienes_pnnc_id() {
		return bienes_pnnc_id;
	}
	public void setBienes_pnnc_id(Integer bienes_pnnc_id) {
		this.bienes_pnnc_id = bienes_pnnc_id;
	}
	public Integer getRelacion_de_pasivos_id() {
		return relacion_de_pasivos_id;
	}
	public void setRelacion_de_pasivos_id(Integer relacion_de_pasivos_id) {
		this.relacion_de_pasivos_id = relacion_de_pasivos_id;
	}
	public Integer getProcesos_clases_id() {
		return procesos_clases_id;
	}
	public void setProcesos_clases_id(Integer procesos_clases_id) {
		this.procesos_clases_id = procesos_clases_id;
	}
	public Integer getProfesionales_asociados_id() {
		return profesionales_asociados_id;
	}
	public void setProfesionales_asociados_id(Integer profesionales_asociados_id) {
		this.profesionales_asociados_id = profesionales_asociados_id;
	}
	public ObjGenerico getTipo_solicitud_obj() {
		return tipo_solicitud_obj;
	}
	public void setTipo_solicitud_obj(ObjGenerico tipo_solicitud_obj) {
		this.tipo_solicitud_obj = tipo_solicitud_obj;
	}
	public InfoFinancMensualPNC getInfo_financiera_mes_pnc() {
		return info_financiera_mes_pnc;
	}
	public ConjuntoEEFFpnc getConjunto_eeff_pnc() {
		return conjunto_eeff_pnc;
	}
	public void setConjunto_eeff_pnc(ConjuntoEEFFpnc conjunto_eeff_pnc) {
		this.conjunto_eeff_pnc = conjunto_eeff_pnc;
	}
	public Integer getConjunto_eeff_pnc_id() {
		return conjunto_eeff_pnc_id;
	}
	public void setConjunto_eeff_pnc_id(Integer conjunto_eeff_pnc_id) {
		this.conjunto_eeff_pnc_id = conjunto_eeff_pnc_id;
	}
	public InventarioAyPNetos getInventario_a_y_p_netos() {
		return inventario_a_y_p_netos;
	}
	public void setInventario_a_y_p_netos(InventarioAyPNetos inventario_a_y_p_netos) {
		this.inventario_a_y_p_netos = inventario_a_y_p_netos;
	}
	public Integer getInventario_a_y_p_netos_id() {
		return inventario_a_y_p_netos_id;
	}
	public void setInventario_a_y_p_netos_id(Integer inventario_a_y_p_netos_id) {
		this.inventario_a_y_p_netos_id = inventario_a_y_p_netos_id;
	}
	public Integer getInfo_financiera_mes_pnc_id() {
		return info_financiera_mes_pnc_id;
	}
	public void setInfo_financiera_mes_pnc_id(Integer info_financiera_mes_pnc_id) {
		this.info_financiera_mes_pnc_id = info_financiera_mes_pnc_id;
	}
	public void setInfo_financiera_mes_pnc(
			InfoFinancMensualPNC info_financiera_mes_pnc) {
		this.info_financiera_mes_pnc = info_financiera_mes_pnc;
	}
	public Integer getHipotesis() {
		return hipotesis;
	}
	public void setHipotesis(Integer hipotesis) {
		this.hipotesis = hipotesis;
	}
	public CumplimientoHipotesis getHipotesis_obj() {
		return hipotesis_obj;
	}
	public void setHipotesis_obj(CumplimientoHipotesis hipotesis_obj) {
		this.hipotesis_obj = hipotesis_obj;
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
		return this.numero_radicado;
	}
	@Override
	public String obtenerNumeroProceso() {
		return this.numero_proceso;
	}
	public ObjGenerico getIntendencia_regional() {
		return intendencia_regional;
	}
	public void setIntendencia_regional(ObjGenerico intendencia_regional) {
		this.intendencia_regional = intendencia_regional;
	}
	
}
