<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo><xsl:value-of select="//notificacion/titulo" disable-output-escaping="yes"/></titulo>
			<contenido>
				<parrafo><xsl:value-of select="//notificacion/contenido" disable-output-escaping="yes"/></parrafo>
			</contenido>
			
		</email>
		
	</xsl:template>

</xsl:stylesheet>
