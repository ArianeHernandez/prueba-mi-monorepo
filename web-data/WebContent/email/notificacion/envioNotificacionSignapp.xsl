<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo><xsl:value-of select="//notificacion/titulo"/></titulo>
			<contenido>
				<parrafo>Se le informa que su solicitud de registro a Signapp ha sido rechazada.</parrafo>
			 				
				<parrafo>
					Cordialmente,<br />
				</parrafo>
				<img src="#logo#"/>
				<parrafo>
					Superintendencia de Sociedades
				</parrafo>
			</contenido>
		</email>
	</xsl:template>

</xsl:stylesheet>