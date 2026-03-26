package com.osmosyscol.datasuite.mein.dtos;

import java.util.HashMap;
import java.util.Map;


public class EnterpriseInfoDto {
	
//	private List<DocumentoAdjunto> listaDocumentos;
	private Map<String, DocumentoAdjunto> documentos = new HashMap<String, DocumentoAdjunto>();
	
	private String representanteLegal_radicado;
	private String revisorFiscal_radicado;
	private String fechaSolicitud_radicado;
	private String cedulaRepresentanteLegal_radicado;
	private String cedulaRevisorFiscal_radicado;
//	private String informacionFinancieraValorActivos_radicado;
	private String apoderado_radicado;
	private String cedulaApoderado_radicado;
	private String pasivosPensionalesCargo_radicado;
	private String estadosFinancierosAnioAnterior_radicado;
	private String estadosFinancierosPenultimoAnio_radicado;
	private String estadosFinancierosAntepenultimoAnio_radicado;
	private String fechaEstadosFinancierosMesAnterior_radicado;
	private String valorTotalActivosCorteMesAnterior_radicado;
	private String valorTotalPasivosCorteMesAnterior_radicado;
	private String valorTotalPatrimonioCorteMesAnterior_radicado;
	private String grupoNIIF_radicado;
	private String bienesSujetosGarantiasMobiliarias_radicado;
	private String numeroProceso_radicado;
	private String numeroRadicado_radicado;
	private String tipoSolicitud_radicado;
	private String tipoSolicitante_radicado;
	private String fechaRadicado_radicado;
	private String nombreSolicitante_radicado;
	private String garanteAvalistaCodeudorTerceros_radicado;
	private String numeroDeIdentificacion_radicado;
	private String domicilioPrincipal_radicado;
	private String telefonoNotificacion_radicado;
	private String ciudad_radicado;
	private String direccionNotificacionIdentificacion_radicado;
	private String email_radicado;
	private String idDelDeudor_radicado;	
	private String tieneCausalDeDisolucion_radicado;
	private String estadosFinancierosRemitidosAnterioridad_radicado;
	private String cesacionPagos_radicado;
	private String fechaActa_radicado;
	
	private String dependencia_radicado;
	
	private String tienePasivosPorRetencionesConFisco_radicado;
	private String tienePasivosPorDescuentosTrabajadores_radicado;
	private String tienePasivosPorSeguridadSocial_radicado;
	private String valorTotalActivosAnoAnterior_radicado;
	private String valorTotalPasivosAnoAnterior_radicado;
	private String valorTotalIngresosOrdinariosAnoAnterior_radicado;
	private String valorTotalOtrosIngresosAnoAnterior_radicado;
	private String tieneContador_radicado;
	private String tieneApoderado_radicado;
	private String tieneRevisorFiscal_radicado;
	private String salarioMinimo_radicado;
	private String fechaRelacionBienesAcreedores_radicado;
	private String esControlanteDeSociedad_radicado;
	private String numeroRadicadoSolicitudoAuto_radicado;
	private String nitPNNC_radicado;
	private String nombreSociedadPNNC_radicado;
	
	private String baseGravableUltimoAnio_radicado;
	private String baseGravablePenultimoAnio_radicado;
	private String baseGravableAntepenultimoAnio_radicado;
	private String baseGravableMesAnterior_radicado;
	private String condicionDeudor_radicado;
	
	private String representanteConLimitaciones_radicado;
	private String adjuntaDocumentoConAutorizacion_radicado;
	private String adjuntaComposicionAccionaria_radicado;
	
	public Map<String, DocumentoAdjunto> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(Map<String, DocumentoAdjunto> documentos) {
		this.documentos = documentos;
	}
	public String getRepresentanteLegal_radicado() {
		return representanteLegal_radicado;
	}
	public void setRepresentanteLegal_radicado(String representanteLegal_radicado) {
		this.representanteLegal_radicado = representanteLegal_radicado;
	}
	public String getRevisorFiscal_radicado() {
		return revisorFiscal_radicado;
	}
	public void setRevisorFiscal_radicado(String revisorFiscal_radicado) {
		this.revisorFiscal_radicado = revisorFiscal_radicado;
	}
	public String getFechaSolicitud_radicado() {
		return fechaSolicitud_radicado;
	}
	public void setFechaSolicitud_radicado(String fechaSolicitud_radicado) {
		this.fechaSolicitud_radicado = fechaSolicitud_radicado;
	}
	public String getCedulaRepresentanteLegal_radicado() {
		return cedulaRepresentanteLegal_radicado;
	}
	public void setCedulaRepresentanteLegal_radicado(
			String cedulaRepresentanteLegal_radicado) {
		this.cedulaRepresentanteLegal_radicado = cedulaRepresentanteLegal_radicado;
	}
	public String getCedulaRevisorFiscal_radicado() {
		return cedulaRevisorFiscal_radicado;
	}
	public void setCedulaRevisorFiscal_radicado(String cedulaRevisorFiscal_radicado) {
		this.cedulaRevisorFiscal_radicado = cedulaRevisorFiscal_radicado;
	}
	public String getApoderado_radicado() {
		return apoderado_radicado;
	}
	public void setApoderado_radicado(String apoderado_radicado) {
		this.apoderado_radicado = apoderado_radicado;
	}
	public String getCedulaApoderado_radicado() {
		return cedulaApoderado_radicado;
	}
	public void setCedulaApoderado_radicado(String cedulaApoderado_radicado) {
		this.cedulaApoderado_radicado = cedulaApoderado_radicado;
	}
	public String getPasivosPensionalesCargo_radicado() {
		return pasivosPensionalesCargo_radicado;
	}
	public void setPasivosPensionalesCargo_radicado(
			String pasivosPensionalesCargo_radicado) {
		this.pasivosPensionalesCargo_radicado = pasivosPensionalesCargo_radicado;
	}
	public String getEstadosFinancierosAnioAnterior_radicado() {
		return estadosFinancierosAnioAnterior_radicado;
	}
	public void setEstadosFinancierosAnioAnterior_radicado(
			String estadosFinancierosAnioAnterior_radicado) {
		this.estadosFinancierosAnioAnterior_radicado = estadosFinancierosAnioAnterior_radicado;
	}
	public String getEstadosFinancierosPenultimoAnio_radicado() {
		return estadosFinancierosPenultimoAnio_radicado;
	}
	public void setEstadosFinancierosPenultimoAnio_radicado(
			String estadosFinancierosPenultimoAnio_radicado) {
		this.estadosFinancierosPenultimoAnio_radicado = estadosFinancierosPenultimoAnio_radicado;
	}
	public String getEstadosFinancierosAntepenultimoAnio_radicado() {
		return estadosFinancierosAntepenultimoAnio_radicado;
	}
	public void setEstadosFinancierosAntepenultimoAnio_radicado(
			String estadosFinancierosAntepenultimoAnio_radicado) {
		this.estadosFinancierosAntepenultimoAnio_radicado = estadosFinancierosAntepenultimoAnio_radicado;
	}
	public String getFechaEstadosFinancierosMesAnterior_radicado() {
		return fechaEstadosFinancierosMesAnterior_radicado;
	}
	public void setFechaEstadosFinancierosMesAnterior_radicado(
			String fechaEstadosFinancierosMesAnterior_radicado) {
		this.fechaEstadosFinancierosMesAnterior_radicado = fechaEstadosFinancierosMesAnterior_radicado;
	}
	public String getValorTotalActivosCorteMesAnterior_radicado() {
		return valorTotalActivosCorteMesAnterior_radicado;
	}
	public void setValorTotalActivosCorteMesAnterior_radicado(
			String valorTotalActivosCorteMesAnterior_radicado) {
		this.valorTotalActivosCorteMesAnterior_radicado = valorTotalActivosCorteMesAnterior_radicado;
	}
	public String getValorTotalPasivosCorteMesAnterior_radicado() {
		return valorTotalPasivosCorteMesAnterior_radicado;
	}
	public void setValorTotalPasivosCorteMesAnterior_radicado(
			String valorTotalPasivosCorteMesAnterior_radicado) {
		this.valorTotalPasivosCorteMesAnterior_radicado = valorTotalPasivosCorteMesAnterior_radicado;
	}
	public String getValorTotalPatrimonioCorteMesAnterior_radicado() {
		return valorTotalPatrimonioCorteMesAnterior_radicado;
	}
	public void setValorTotalPatrimonioCorteMesAnterior_radicado(
			String valorTotalPatrimonioCorteMesAnterior_radicado) {
		this.valorTotalPatrimonioCorteMesAnterior_radicado = valorTotalPatrimonioCorteMesAnterior_radicado;
	}
	public String getBienesSujetosGarantiasMobiliarias_radicado() {
		return bienesSujetosGarantiasMobiliarias_radicado;
	}
	public void setBienesSujetosGarantiasMobiliarias_radicado(
			String bienesSujetosGarantiasMobiliarias_radicado) {
		this.bienesSujetosGarantiasMobiliarias_radicado = bienesSujetosGarantiasMobiliarias_radicado;
	}
	public String getNumeroProceso_radicado() {
		return numeroProceso_radicado;
	}
	public void setNumeroProceso_radicado(String numeroProceso_radicado) {
		this.numeroProceso_radicado = numeroProceso_radicado;
	}
	public String getNumeroRadicado_radicado() {
		return numeroRadicado_radicado;
	}
	public void setNumeroRadicado_radicado(String numeroRadicado_radicado) {
		this.numeroRadicado_radicado = numeroRadicado_radicado;
	}
	public String getTipoSolicitud_radicado() {
		return tipoSolicitud_radicado;
	}
	public void setTipoSolicitud_radicado(String tipoSolicitud_radicado) {
		this.tipoSolicitud_radicado = tipoSolicitud_radicado;
	}
	public String getTipoSolicitante_radicado() {
		return tipoSolicitante_radicado;
	}
	public void setTipoSolicitante_radicado(String tipoSolicitante_radicado) {
		this.tipoSolicitante_radicado = tipoSolicitante_radicado;
	}
	public String getFechaRadicado_radicado() {
		return fechaRadicado_radicado;
	}
	public void setFechaRadicado_radicado(String fechaRadicado_radicado) {
		this.fechaRadicado_radicado = fechaRadicado_radicado;
	}
	public String getNombreSolicitante_radicado() {
		return nombreSolicitante_radicado;
	}
	public void setNombreSolicitante_radicado(String nombreSolicitante_radicado) {
		this.nombreSolicitante_radicado = nombreSolicitante_radicado;
	}
	public String getGaranteAvalistaCodeudorTerceros_radicado() {
		return garanteAvalistaCodeudorTerceros_radicado;
	}
	public void setGaranteAvalistaCodeudorTerceros_radicado(
			String garanteAvalistaCodeudorTerceros_radicado) {
		this.garanteAvalistaCodeudorTerceros_radicado = garanteAvalistaCodeudorTerceros_radicado;
	}
	public String getNumeroDeIdentificacion_radicado() {
		return numeroDeIdentificacion_radicado;
	}
	public void setNumeroDeIdentificacion_radicado(
			String numeroDeIdentificacion_radicado) {
		this.numeroDeIdentificacion_radicado = numeroDeIdentificacion_radicado;
	}
	public String getDomicilioPrincipal_radicado() {
		return domicilioPrincipal_radicado;
	}
	public void setDomicilioPrincipal_radicado(String domicilioPrincipal_radicado) {
		this.domicilioPrincipal_radicado = domicilioPrincipal_radicado;
	}
	public String getCiudad_radicado() {
		return ciudad_radicado;
	}
	public void setCiudad_radicado(String ciudad_radicado) {
		this.ciudad_radicado = ciudad_radicado;
	}
	public String getDireccionNotificacionIdentificacion_radicado() {
		return direccionNotificacionIdentificacion_radicado;
	}
	public void setDireccionNotificacionIdentificacion_radicado(
			String direccionNotificacionIdentificacion_radicado) {
		this.direccionNotificacionIdentificacion_radicado = direccionNotificacionIdentificacion_radicado;
	}
	public String getEmail_radicado() {
		return email_radicado;
	}
	public void setEmail_radicado(String email_radicado) {
		this.email_radicado = email_radicado;
	}
	public String getIdDelDeudor_radicado() {
		return idDelDeudor_radicado;
	}
	public void setIdDelDeudor_radicado(String idDelDeudor_radicado) {
		this.idDelDeudor_radicado = idDelDeudor_radicado;
	}
	public String getTieneCausalDeDisolucion_radicado() {
		return tieneCausalDeDisolucion_radicado;
	}
	public void setTieneCausalDeDisolucion_radicado(
			String tieneCausalDeDisolucion_radicado) {
		this.tieneCausalDeDisolucion_radicado = tieneCausalDeDisolucion_radicado;
	}
	public String getEstadosFinancierosRemitidosAnterioridad_radicado() {
		return estadosFinancierosRemitidosAnterioridad_radicado;
	}
	public void setEstadosFinancierosRemitidosAnterioridad_radicado(
			String estadosFinancierosRemitidosAnterioridad_radicado) {
		this.estadosFinancierosRemitidosAnterioridad_radicado = estadosFinancierosRemitidosAnterioridad_radicado;
	}
	public String getCesacionPagos_radicado() {
		return cesacionPagos_radicado;
	}
	public void setCesacionPagos_radicado(String cesacionPagos_radicado) {
		this.cesacionPagos_radicado = cesacionPagos_radicado;
	}
	public String getFechaActa_radicado() {
		return fechaActa_radicado;
	}
	public void setFechaActa_radicado(String fechaActa_radicado) {
		this.fechaActa_radicado = fechaActa_radicado;
	}
	public String getDependencia_radicado() {
		return dependencia_radicado;
	}
	public void setDependencia_radicado(String dependencia_radicado) {
		this.dependencia_radicado = dependencia_radicado;
	}
	public String getTienePasivosPorRetencionesConFisco_radicado() {
		return tienePasivosPorRetencionesConFisco_radicado;
	}
	public void setTienePasivosPorRetencionesConFisco_radicado(
			String tienePasivosPorRetencionesConFisco_radicado) {
		this.tienePasivosPorRetencionesConFisco_radicado = tienePasivosPorRetencionesConFisco_radicado;
	}
	public String getTienePasivosPorDescuentosTrabajadores_radicado() {
		return tienePasivosPorDescuentosTrabajadores_radicado;
	}
	public void setTienePasivosPorDescuentosTrabajadores_radicado(
			String tienePasivosPorDescuentosTrabajadores_radicado) {
		this.tienePasivosPorDescuentosTrabajadores_radicado = tienePasivosPorDescuentosTrabajadores_radicado;
	}
	public String getTienePasivosPorSeguridadSocial_radicado() {
		return tienePasivosPorSeguridadSocial_radicado;
	}
	public void setTienePasivosPorSeguridadSocial_radicado(
			String tienePasivosPorSeguridadSocial_radicado) {
		this.tienePasivosPorSeguridadSocial_radicado = tienePasivosPorSeguridadSocial_radicado;
	}
	public String getValorTotalActivosAnoAnterior_radicado() {
		return valorTotalActivosAnoAnterior_radicado;
	}
	public void setValorTotalActivosAnoAnterior_radicado(
			String valorTotalActivosAnoAnterior_radicado) {
		this.valorTotalActivosAnoAnterior_radicado = valorTotalActivosAnoAnterior_radicado;
	}
	public String getValorTotalPasivosAnoAnterior_radicado() {
		return valorTotalPasivosAnoAnterior_radicado;
	}
	public void setValorTotalPasivosAnoAnterior_radicado(
			String valorTotalPasivosAnoAnterior_radicado) {
		this.valorTotalPasivosAnoAnterior_radicado = valorTotalPasivosAnoAnterior_radicado;
	}
	public String getValorTotalIngresosOrdinariosAnoAnterior_radicado() {
		return valorTotalIngresosOrdinariosAnoAnterior_radicado;
	}
	public void setValorTotalIngresosOrdinariosAnoAnterior_radicado(
			String valorTotalIngresosOrdinariosAnoAnterior_radicado) {
		this.valorTotalIngresosOrdinariosAnoAnterior_radicado = valorTotalIngresosOrdinariosAnoAnterior_radicado;
	}
	public String getValorTotalOtrosIngresosAnoAnterior_radicado() {
		return valorTotalOtrosIngresosAnoAnterior_radicado;
	}
	public void setValorTotalOtrosIngresosAnoAnterior_radicado(
			String valorTotalOtrosIngresosAnoAnterior_radicado) {
		this.valorTotalOtrosIngresosAnoAnterior_radicado = valorTotalOtrosIngresosAnoAnterior_radicado;
	}
	public String getTieneContador_radicado() {
		return tieneContador_radicado;
	}
	public void setTieneContador_radicado(String tieneContador_radicado) {
		this.tieneContador_radicado = tieneContador_radicado;
	}
	public String getTieneApoderado_radicado() {
		return tieneApoderado_radicado;
	}
	public void setTieneApoderado_radicado(String tieneApoderado_radicado) {
		this.tieneApoderado_radicado = tieneApoderado_radicado;
	}
	public String getTieneRevisorFiscal_radicado() {
		return tieneRevisorFiscal_radicado;
	}
	public void setTieneRevisorFiscal_radicado(String tieneRevisorFiscal_radicado) {
		this.tieneRevisorFiscal_radicado = tieneRevisorFiscal_radicado;
	}
	public String getNumeroRadicadoSolicitudoAuto_radicado() {
		return numeroRadicadoSolicitudoAuto_radicado;
	}
	public void setNumeroRadicadoSolicitudoAuto_radicado(
			String numeroRadicadoSolicitudoAuto_radicado) {
		this.numeroRadicadoSolicitudoAuto_radicado = numeroRadicadoSolicitudoAuto_radicado;
	}
	public String getSalarioMinimo_radicado() {
		return salarioMinimo_radicado;
	}
	public void setSalarioMinimo_radicado(String salarioMinimo_radicado) {
		this.salarioMinimo_radicado = salarioMinimo_radicado;
	}
	public String getNitPNNC_radicado() {
		return nitPNNC_radicado;
	}
	public void setNitPNNC_radicado(String nitPNNC_radicado) {
		this.nitPNNC_radicado = nitPNNC_radicado;
	}
	public String getNombreSociedadPNNC_radicado() {
		return nombreSociedadPNNC_radicado;
	}
	public void setNombreSociedadPNNC_radicado(String nombreSociedadPNNC_radicado) {
		this.nombreSociedadPNNC_radicado = nombreSociedadPNNC_radicado;
	}
	public String getFechaRelacionBienesAcreedores_radicado() {
		return fechaRelacionBienesAcreedores_radicado;
	}
	public void setFechaRelacionBienesAcreedores_radicado(
			String fechaRelacionBienesAcreedores_radicado) {
		this.fechaRelacionBienesAcreedores_radicado = fechaRelacionBienesAcreedores_radicado;
	}
	public String getBaseGravableUltimoAnio_radicado() {
		return baseGravableUltimoAnio_radicado;
	}
	public void setBaseGravableUltimoAnio_radicado(
			String baseGravableUltimoAnio_radicado) {
		this.baseGravableUltimoAnio_radicado = baseGravableUltimoAnio_radicado;
	}
	public String getBaseGravablePenultimoAnio_radicado() {
		return baseGravablePenultimoAnio_radicado;
	}
	public void setBaseGravablePenultimoAnio_radicado(
			String baseGravablePenultimoAnio_radicado) {
		this.baseGravablePenultimoAnio_radicado = baseGravablePenultimoAnio_radicado;
	}
	public String getBaseGravableAntepenultimoAnio_radicado() {
		return baseGravableAntepenultimoAnio_radicado;
	}
	public void setBaseGravableAntepenultimoAnio_radicado(
			String baseGravableAntepenultimoAnio_radicado) {
		this.baseGravableAntepenultimoAnio_radicado = baseGravableAntepenultimoAnio_radicado;
	}
	public String getBaseGravableMesAnterior_radicado() {
		return baseGravableMesAnterior_radicado;
	}
	public void setBaseGravableMesAnterior_radicado(
			String baseGravableMesAnterior_radicado) {
		this.baseGravableMesAnterior_radicado = baseGravableMesAnterior_radicado;
	}
	public String getCondicionDeudor_radicado() {
		return condicionDeudor_radicado;
	}
	public void setCondicionDeudor_radicado(String condicionDeudor_radicado) {
		this.condicionDeudor_radicado = condicionDeudor_radicado;
	}
	public String getRepresentanteConLimitaciones_radicado() {
		return representanteConLimitaciones_radicado;
	}
	public void setRepresentanteConLimitaciones_radicado(
			String representanteConLimitaciones_radicado) {
		this.representanteConLimitaciones_radicado = representanteConLimitaciones_radicado;
	}
	public String getEsControlanteDeSociedad_radicado() {
		return esControlanteDeSociedad_radicado;
	}
	public void setEsControlanteDeSociedad_radicado(
			String esControlanteDeSociedad_radicado) {
		this.esControlanteDeSociedad_radicado = esControlanteDeSociedad_radicado;
	}
	public String getAdjuntaDocumentoConAutorizacion_radicado() {
		return adjuntaDocumentoConAutorizacion_radicado;
	}
	public void setAdjuntaDocumentoConAutorizacion_radicado(
			String adjuntaDocumentoConAutorizacion_radicado) {
		this.adjuntaDocumentoConAutorizacion_radicado = adjuntaDocumentoConAutorizacion_radicado;
	}
	public String getAdjuntaComposicionAccionaria_radicado() {
		return adjuntaComposicionAccionaria_radicado;
	}
	public void setAdjuntaComposicionAccionaria_radicado(
			String adjuntaComposicionAccionaria_radicado) {
		this.adjuntaComposicionAccionaria_radicado = adjuntaComposicionAccionaria_radicado;
	}
	public String getGrupoNIIF_radicado() {
		return grupoNIIF_radicado;
	}
	public void setGrupoNIIF_radicado(String grupoNIIF_radicado) {
		this.grupoNIIF_radicado = grupoNIIF_radicado;
	}
	public String getTelefonoNotificacion_radicado() {
		return telefonoNotificacion_radicado;
	}
	public void setTelefonoNotificacion_radicado(
			String telefonoNotificacion_radicado) {
		this.telefonoNotificacion_radicado = telefonoNotificacion_radicado;
	}
	
}
