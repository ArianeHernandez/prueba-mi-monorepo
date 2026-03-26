<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>

		<xsl:choose>
			<xsl:when test="string-length(PAGINA_SIMPLE) > 0">
				<pagina_simple titulo="Servicio">
					
					<principal>
						<contenido>[APP_CONTENIDO]</contenido>
					</principal>
					
				</pagina_simple>
			</xsl:when>
			<xsl:otherwise>
				<pagina titulo="Servicio">
					
					<principal>
						<contenido>[APP_CONTENIDO]</contenido>
					</principal>
					
				</pagina>
			</xsl:otherwise>
		</xsl:choose>		
		
		
	</xsl:template>

</xsl:stylesheet>
