<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo>Error en la Conexión</titulo>
			<contenido>
				<parrafo>
					No se pudo establecer conexión con el servidor configurado para el Cliente <b><xsl:value-of select="cliente"/></b>.
				</parrafo>
				<parrafo>
					Para más información ingrese al portal verifique la configuración.
				</parrafo>
			</contenido>
		</email>
		
	</xsl:template>

</xsl:stylesheet>
