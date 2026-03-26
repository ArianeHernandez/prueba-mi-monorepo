<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="semaforoCargasPorProcesos">

		<javascript>templates/semaforoCargasPendientesPorProcesoAdmin.js</javascript>
		<stylesheet>semaforoCargasPendientes.css</stylesheet>
		
		<div class="resumen_rcargas_pendientes">
			<div class="titulo_rcargas_pendientes clearfix"> 
				<span class="titulo_rcargas_pendientes"><texto key="CARGAS" /> pendientes</span>
			</div>
			<div class="lista_rcargas_pendientes" id="lista_proceso">
				<div id="sin_procesos">No tiene procesos asociados</div>
				<formulario destino="administracion_carga/proceso/22.1.do" id="form_ver_cargas">
					<variable id="id_proceso_admin" valor=""/>
				</formulario>
			</div>
			
		</div>
		<div class="oculto_rcargas_pendientes" id="template_semaforo">
			<div class="item_rcargas_pendientes" onclick="verCargas([ 1 ])" name="[ 3 ]">
				<span class="titulo_rsemaforo">[ 2 ]</span>
				<div  id="semaforo" >
					<table class="intervalos">
						<tr id="[ 4 ]">
						</tr>
					</table>
				</div>
			</div>
		</div>
		<table style="display:none">
			<td id="template_intervalo" />
		</table>
	
	
		
	</xsl:template>
</xsl:stylesheet>