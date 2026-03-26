<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo>Archivo sin Permisos de Lectura</titulo>
			<contenido>
				<parrafo>
					El <b><xsl:value-of select="//fechaCargue"/></b> se intentó cargar el archivo <b><xsl:value-of select="//archivo"/></b>,
					pero este no tiene permisos de lectura. No se puede acceder y cargar la información.
				</parrafo>
				<parrafo>
					Por favor verifique los permisos de lectura del archivo.
				</parrafo>
			</contenido>
		</email>
		
	</xsl:template>

</xsl:stylesheet>
