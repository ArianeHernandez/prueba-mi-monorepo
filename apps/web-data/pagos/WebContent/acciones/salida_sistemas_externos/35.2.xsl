<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Descarga de archivo sistema SIF-ORION">
			
			<principal>
				<titulo>Descarga de archivo sistema SIF-ORION</titulo>
				<contenido>
					<javascript>salida_sistemas_externos/35.2.js</javascript>
					
					<xsl:choose>
						<xsl:when test="normalize-space(crearArchivoParaRetirosPorComandos/exitoso)='true'">
							<parrafo id="mensaje">
								Generando archivo...
							</parrafo>
								
							<div class="barra-progreso" id="barra-progreso">
								<div class="barra-progreso-avance" id="progreso">
								</div>
							</div>
							
							<div style="display:none" id="mensaje2">
								Para descargar el archivo <xsl:value-of select="//tipo_archivo"/>.xsl por favor oprima el boton DESCARGAR ARCHIVO.
							</div>
							
							
						</xsl:when>
						<xsl:otherwise>
							Lo sentimos, la operacion no se ha podido completar con exito. Por favor intentelo nuevamente.
						</xsl:otherwise>
					</xsl:choose>
						
					<br/><br/>
					<area_botones>
						<xsl:if test="normalize-space(crearArchivoParaRetirosPorComandos/exitoso)='true'">
							<boton id="boton_descargar" accion="osm_go('descarga.siforion',false)" style="display:none">Descargar Archivo</boton>
						</xsl:if>
						
						<boton  estilo="volver" id="boton_volver" destino="salida_sistemas_externos/35.1.do">Volver</boton>
					</area_botones>
				</contenido>
			
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
