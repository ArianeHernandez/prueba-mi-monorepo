<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo>Carga Exitosa</titulo>
			<contenido>
				<parrafo>
					El archivo <b><xsl:value-of select="//archivo"/></b> fue cargado exitosamente.
				</parrafo>
				<parrafo>
					<bloque>
						<registro_cargaExitosa>
							<item>No. de Registros</item>
							<item></item>
							<valor><xsl:value-of select="//registros"/></valor>
						</registro_cargaExitosa>
						<registro_cargaExitosa>
							<item>Valor total de:</item>
							<item></item>
							<valor>$ <xsl:value-of select="format-number(//valor, '###.##0,00', 'pesos')"/></valor>
						</registro_cargaExitosa>
					</bloque>
				</parrafo>
			</contenido>
		</email>
		
	</xsl:template>

</xsl:stylesheet>
