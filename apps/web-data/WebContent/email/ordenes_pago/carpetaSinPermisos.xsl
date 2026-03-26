<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo>Carpeta sin Permisos de Lectura</titulo>
			<contenido>
				<parrafo>
					La carpeta configurada para el Cliente <xsl:value-of select="cliente"/>, no tiene permisos de lectura y/o escritura.
					No se puede acceder y cargar la información.
				</parrafo>
				<parrafo>
					Por favor verifique los permisos de lectura de la carpeta.
				</parrafo>
			</contenido>
		</email>
		
	</xsl:template>

</xsl:stylesheet>
