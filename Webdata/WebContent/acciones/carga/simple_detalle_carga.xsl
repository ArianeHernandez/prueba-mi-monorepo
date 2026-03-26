<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://stylesheets/templates/DetalleCarga.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina_simple>

			<div id="CONTENIDO_HTML">
				
				<xsl:call-template name="DETALLE_CARGA" >
					<xsl:with-param name="validacionesActivas">false</xsl:with-param>
				</xsl:call-template>
				
			</div>

		</pagina_simple>
	</xsl:template>

</xsl:stylesheet>
