<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	
	<xsl:template match="OSM-ACCION">
		<pagina_simple>
			<alerta>Acceso no Autorizado</alerta>
		</pagina_simple>
	</xsl:template>
	
</xsl:stylesheet>