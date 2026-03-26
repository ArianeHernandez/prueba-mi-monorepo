<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="CAMPOS_ADICIONALES">
		
		<xsl:if test="(//OSM_PAGE/OSM-SESSION/credencial_usuario/id_usuario = '' and //OSM-ACCION/obtenerFormato/Formato/para_director = 'S') or (//OSM_PAGE/OSM-SESSION/credencial_usuario/id_usuario!='' and //OSM-ACCION/obtenerFormato/Formato/para_liberador = 'S')">
			<xsl:variable name="listaDC" select="//obtenerListasDCPorFormatoValores/listado"/>
			
				<javascript>templates/campos_adicionales.js</javascript>
				<xsl:for-each select="$listaDC/ListaDinamicaCampo">
					
					<xsl:sort select="id_campo_padre"/>
					<xsl:variable name="id_lista_dinamica" select="id_lista_dinamica" />
					
					<div class="campo_adicional">
					
						<registro>
							<item>
								<xsl:if test="obligatorio = 'S'">(*)&#160;</xsl:if>
								<xsl:value-of select="nombre_campo" />
							</item>
							<valor>
								
								
								<xsl:if test="string-length(id_campo_padre) > 0">
									<input type="hidden" name="campo_actualizable" value="{id_campo}" />
									<input type="hidden" name="id_campo_padre" id="id_campo_padre_{id_campo}" value="{id_campo_padre}" />
									<input type="hidden" id="id_lista_dinamica_{id_campo}" value="{id_lista_dinamica}" />
									<input type="hidden" name="campo_hijo_{id_campo_padre}" value="{id_campo}" />
								</xsl:if>
								
								<variable id="select_campo_{id_campo}_name" valor="{nombre_campo}" />
								
								<cajatextoselector id="select_campo_{id_campo}" accion="osm_setValor('listaValoresDinamicos_{id_campo}_valor',this.value)">
									<xsl:if test="obligatorio = 'S'">
										<xsl:attribute name="class">obligatorio put</xsl:attribute>
									</xsl:if>
									
									<opcion>--Seleccione--</opcion>
									
									<xsl:for-each
										select="$listaDC/HashMap/*[@name=concat('LV_',$id_lista_dinamica)]/ValorLista">
										<opcion valor="{id}">
											<xsl:value-of select="nombre" />
										</opcion>
									</xsl:for-each>
									
								</cajatextoselector>
								
							</valor>
						</registro>
						
					</div>
					
				</xsl:for-each>
			</xsl:if>
	</xsl:template>

</xsl:stylesheet>