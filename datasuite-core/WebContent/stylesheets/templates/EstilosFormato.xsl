<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="ESTILOS_FORMATO">
	
		<javascript>templates/estilos_formato.js</javascript>
		<stylesheet>templates/estilos_formato.css</stylesheet>
	
		<pestana titulo="Estilos del Formato">
			
			<bloque-contenido>
				<titulo>Estilos personalizados para el Formato</titulo>
				<contenido>
				
					<xsl:for-each select="//obtenerTipoEstilos/Listado/TipoEstilo">
						<xsl:sort select="id_tipo_estilo" />
						<xsl:variable name="id_tipo_estilo" select="id_tipo_estilo" />
						<xsl:variable name="valor_estilo" select="//obtenerFormatoEstilos/Listado/FormatoEstilo[id_tipo_estilo=$id_tipo_estilo]/estilo"/>
						
						<registro>
							<item><xsl:value-of select="nombre" /></item>
							<valor>
								<div class="tooltip_estilo">
									<areatexto id="FormatoEstilo:{position()}.estilo" valor="{$valor_estilo}"/>
									<span class="tooltiptext_estilo"><xsl:value-of select="texto_ayuda" /></span>
								</div>
								<variable id="FormatoEstilo:{position()}.id_formato" valor="{//obtenerFormato/Formato/id_formato}"/>
								<variable id="FormatoEstilo:{position()}.id_tipo_estilo" valor="{$id_tipo_estilo}"/>
							</valor>
						</registro>
					</xsl:for-each>
				
				</contenido>
			</bloque-contenido>
			
		</pestana>

	</xsl:template>
	
</xsl:stylesheet>