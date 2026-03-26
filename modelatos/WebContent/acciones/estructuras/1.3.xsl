<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Confirmación Guardado">
			
			<principal>
				<titulo icono="estructura">Confirmación Guardado</titulo>
				<contenido>
		
					<xsl:choose>
						<xsl:when test="guardarEstructura/exitoso='true'">
							
							<div class="mesg2">
								<p>La estructura ha sido guardada Exitosamente.</p>
							</div>
						</xsl:when>
						<xsl:otherwise>
							<div class="mesg2 red">
								<p>No se ha podido realizar la operacion.</p>
							</div>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="volver" destino="estructuras/1.1.do">Volver al Listado</boton>
						<boton estilo="crear" destino="estructuras/1.2.do">Crear Nueva</boton>
					</area_botones>
					
				</contenido>
				
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
