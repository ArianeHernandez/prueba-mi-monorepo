<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://stylesheets/templates/visualizadorCargaProcesoAdmin.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template name="semaforoCargasPorAdminHijo">

		<javascript>templates/semaforoCargasPendientesPorAdminHijo.js</javascript>
		<stylesheet>semaforoCargasPendientesAdminHijo.css</stylesheet>
		
		<formulario id="form_carga_admin_hijo" destino="administracion_carga/proceso/22.2.1.do">
			<variable id="id_carga_admin_hijo" valor=""/>
		</formulario>
		
		<div class="resumen_rcargas_pendientes_adminhijo">
			<div id="titulo_rcargas_pendientes" class="titulo_rcargas_pendientes_adminhijo clearfix" style="display:none"> 
				<span  class="titulo_rcargas_pendientes_adminhijo"><texto key="CARGAS" /> pendientes</span>
			</div>
			<div class="lista_rcargas_pendientes_adminhijo" id="lista_semaforo_admin_hijo">
				<div id="sin_admin_hijo" style="display:none">No hay administrativos para esa instancia</div>
			</div>
			
		</div>
		
		<div class="oculto_rcargas_pendientes_adminhijo" id="template_semaforo_admin_hijo">
			<div name="[ 3 ]" onclick="mostrarCargasPendientes([ 1 ])" id="div_admin_hijo_[ 1 ]">
				<div class="item_rcargas_pendientes_adminhijo" id="semaforo" >
					<span class="titulo_rsemaforo_adminhijo">[ 2 ]</span>
					<table class="intervalos_adminhijo">
						<tr id="[ 4 ]">
						</tr>
					</table>
				</div>
				
				<div class="area_cargas" id="lista_cargas_admin_hijo_[ 1 ]" style="display:none">
					<div id="sin_cargas_admin_hijo">No tiene <texto key="CARGAS" /> asociadas</div>
				</div>
				<div style="clear:both"></div>
			</div>
		</div>
		<table style="display:none">
			<td id="template_intervalo_admin_hijo" />
		</table>
		
		<!-- VISUALIZADOR DE CARGA -->
		<xsl:call-template name="VISUALIZADOR_CARGA">
			<xsl:with-param name="funcionJSVerDetalleCarga">verDetalleCargaAdminHijo</xsl:with-param>
			<xsl:with-param name="funcionJSEjecutarAccion">ejecutarAccionAdminHijo</xsl:with-param>
			<xsl:with-param name="funcionJSAprobarCarga">aprobarCargaAdminHijo</xsl:with-param>
			<xsl:with-param name="funcionJSRechazarCarga">rechazarCargaAdminHijo</xsl:with-param>
		</xsl:call-template>
		
		
	</xsl:template>
</xsl:stylesheet>