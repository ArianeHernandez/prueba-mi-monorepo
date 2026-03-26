<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="LISTADONEGOCIOS">
		<xsl:param name="Destino" />
		<xsl:param name="NegocioActual" />
		<xsl:param name="Negocio" />
		
		<javascript>templates/ListadoNegocios.js</javascript>
		
		<formulario destino="{$Destino}" id="form_negocio">
			<registro>
				<item>
					Negocio:
				</item>
		
				<valor>
					<cajatextoselector name="id_negocio" id="id_negocio"
						accion="cambiarNegocio(this)" valor="{$NegocioActual}">
						<opcion>--Seleccione un negocio--</opcion>
						<xsl:for-each select="$Negocio">
							<xsl:sort select="cod_negocio" />
							<opcion valor="{id_negocio}">
								(<xsl:value-of select="cod_negocio"/>) <xsl:value-of select="nombre"/>
							</opcion>
						</xsl:for-each>
					</cajatextoselector>
				</valor>

			</registro>
		</formulario>
					
	</xsl:template>

</xsl:stylesheet>