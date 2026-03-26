<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="VISUALIZADOR_CARGA">
		<xsl:param name="funcionJSVerDetalleCarga"/>	<!-- Obligatorio: Nombre de la funcion JS que ve el detalle de la carga. Esta debe recibir dos parametros id_carga, id_instancia -->
		<xsl:param name="funcionJSEjecutarAccion"/>    	<!-- Obligatorio: Nombre de la funcion JS que ejecuta las acciones de la carga. Esta debe recibir 4 parametros: id_carga, id_accion, id_instancia, nombre_instancia -->
		<xsl:param name="funcionJSAprobarCarga"/>		<!-- Obligatorio: Nombre de la funcion JS que aprueba la carga. Esta debe recibir 2 parametros: id_carga, id_instancia -->
		<xsl:param name="funcionJSRechazarCarga"/>		<!-- Obligatorio: Nombre de la funcion JS que rechaza la carga. Esta debe recibir 2 parametros: id_carga, id_instancia -->

		<javascript>templates/visualizadorCargaProcesoAdmin.js</javascript>
		<stylesheet>carga/visualizadorCargaProcesoAdmin.css</stylesheet>
		
		<!-- PLANTILLA CARGA -->
 		<div id="PLANTILLA_CARGA" style="display:none">
			<!-- CARGA-->
			<div class="carga">
				<div class="header_carga">
					<div class="tiempo_carga [ 3 ]">[ 4 ]&#160;min</div>
					<div class="fecha_carga">[ 5 ]</div>
				</div>		
				<a href="#" class="body_carga" onclick="{$funcionJSVerDetalleCarga}('[ 1 ]', '[ 2 ]')">
					
					<div class="icono_carga  [ 6 ]"/>
					<div class="id_carga">No.[ 1 ]</div>
					
					<!-- info firma digital -->
					<div class="area_icono_documento_firmado  [ 12 ]"/>
					<div class="area_icono_transaccion_firmada [ 13 ]"/>
					
					<!-- info detallada carga -->
					<div class="cliente_carga">
						[ 7 ]
						&#160;
						[ 8 ]
					</div>
					
					<div class="valor_carga">
						<b>#[ 11 ]</b> | [ 9 ] 
					</div>
					
					<div class="instancia_carga">
						<b>Instancia:</b> [ 10 ]
						
					</div>
					
					<!-- info variables de liberacion -->
					<div id="div_area_variables_liberacion_[ 1 ]" class="area_variables_liberacion">
						
					</div>
					
				</a>
				<div id="div_acciones_carga_[ 2 ]_[ 1 ]" class="area_acciones">
					
				</div>
				
			</div>
		</div>
		
		<div id="PLANTILLA_VARIABLE_LIBERACION_CARGA" style="display:none">
			<div id="var_liberacion_[ 1 ]_[ 2 ]" class="variable_liberacion" >
				<b>[ 3 ]</b> : [ 4 ]
			</div>
		</div>
		
		<div id="PLANTILLA_ACCION_CARGA" style="display:none">
			<div id="accion_[ 1 ]" class="accion_carga" onclick="{$funcionJSEjecutarAccion}('[ 4 ]', '[ 1 ]', '[ 3 ]', '[ 2 ]')">
				[ 2 ]
			</div>
		</div>
 		
 		<div id="PLANTILLA_APROBAR_CARGA" style="display:none">
			<div id="aprobar_carga_[ 1 ]" class="accion_carga aprobar_carga" onclick="{$funcionJSAprobarCarga}('[ 1 ]', '[ 2 ]')">
				Aprobar
			</div>
		</div>
 		
 		<div id="PLANTILLA_RECHAZAR_CARGA" style="display:none">
			<div id="rechazar_carga_[ 1 ]" class="accion_carga rechazar_carga" onclick="{$funcionJSRechazarCarga}('[ 1 ]', '[ 2 ]')">
				Rechazar
			</div>
		</div>
		
	</xsl:template>

</xsl:stylesheet>