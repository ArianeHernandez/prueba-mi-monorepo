<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Confirmación Importación">
			
			
			<principal>
				<titulo icono="estructura">Confirmación Envío</titulo>
				<contenido>
			
					<xsl:choose>
						<xsl:when test="//exitoso='true'">
							<javascript>webdata/1.2.2.js</javascript>
							<div id="mensaje">Cargando datos desde el archivo...</div>
							<div class="alert alert-info" style="display:none" id="msj_exito">
								<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
 								<span class="sr-only">Info:</span>
								<p><texto key="LA CARGA SE ENVIO CORRECTAMENTE" /> <xsl:value-of select="//ID_CARGA"></xsl:value-of></p>
							</div>
							<div class="alert alert-danger" id="msj_fallo" style="display:none">
								<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
 								<span class="sr-only">Error:</span>
								<p><texto key="LA CARGA NO SE ENVIO" /></p>
							</div>
						</xsl:when>
						<xsl:otherwise>
							<div class="alert alert-danger">
								<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
  								<span class="sr-only">Error:</span>
								<p><texto key="LA CARGA NO SE ENVIO" /></p>
							</div>
						</xsl:otherwise>
						
					</xsl:choose>
					
					
					<area_botones>
						<boton id="volver" estilo="volver" destino="carga_informacion/lotes/1.2.do">Volver al Listado</boton>
					</area_botones>
					
				</contenido>
				
			</principal>
			
		</pagina>
		
		
	</xsl:template>

</xsl:stylesheet>
