<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<xsl:template match="OSM-EMAIL">
		
		<email>
			<titulo>Archivo con Datos Inválidos</titulo>
			<contenido>
				<parrafo>
					El archivo <b><xsl:value-of select="//archivo"/></b>, cargado el <b><xsl:value-of select="//fechaCargue"/></b>
					contiene datos inválidos.
				</parrafo>
				<parrafo>
					<bloque>
						<xsl:for-each select="//listaErrores/*">
							<xsl:variable name="nombreHoja" select="@name" />
							<xsl:for-each select="String">
								<registro>
									<item><xsl:value-of select="$nombreHoja"/></item>
									<valor><xsl:value-of select="."/></valor>
								</registro>	
							</xsl:for-each>
						</xsl:for-each>
					</bloque>
				</parrafo>
				<parrafo>
					Por favor verifique los datos del archivo.
				</parrafo>
			</contenido>
		</email>
		
	</xsl:template>

</xsl:stylesheet>
