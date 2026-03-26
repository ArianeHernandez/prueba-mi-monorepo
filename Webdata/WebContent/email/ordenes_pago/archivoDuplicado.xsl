<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo>Archivo Duplicado</titulo>
			<contenido>
				<parrafo>
					El archivo <b><xsl:value-of select="//archivo"/></b>, cargado el <b><xsl:value-of select="//fechaCargue"/></b>
					ya ha sido procesado anteriormente.
				</parrafo>
				<parrafo>
					Por favor verifique el archivo.
				</parrafo>
			</contenido>
		</email>
		
	</xsl:template>

</xsl:stylesheet>
