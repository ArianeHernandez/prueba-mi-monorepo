<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo>Formato de Archivo Erroneo</titulo>
			<contenido>
				<parrafo>
					El archivo <b><xsl:value-of select="//archivo"/></b>, cargado el <b><xsl:value-of select="//fechaCargue"/></b>
					no cumple con el formato de entrada establecido.
				</parrafo>
				<parrafo>
					Por favor verifique el formato del archivo.
				</parrafo>
			</contenido>
		</email>
		
	</xsl:template>

</xsl:stylesheet>
