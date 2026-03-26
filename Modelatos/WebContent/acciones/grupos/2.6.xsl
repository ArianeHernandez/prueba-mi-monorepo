<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Diagrama">
			
			<javascript>utils/jsDraw2D.js</javascript>
			<javascript>admin/2.6.js</javascript>
			
			<stylesheet>diagrama.css</stylesheet>
			
			<principal>
				<contenido>
		
					<div class="diagrama_titulo"><h1>Grupo: <xsl:value-of select="obtenerGrupo/Grupo/nombre"/></h1></div>
					
					<div id="area_diagrama">
						
						<input type="hidden" id="id_grupo" value="{id_grupo}"/>
						
						<xsl:for-each select="obtenerEstructurasPorGrupo/listado/Estructura">
							
							<div id="TE{id_estructura}" class="tabla_diagrama" style="top:{ypos}px; left:{xpos}px; height: { count(campos/Campo) * 16 + 20 }px">
								
								<input type="hidden" name="id_estructura" value="{id_estructura}"/>
								
								<div class="tabla_diagrama_titulo" title="T{id_estructura} - {nombre}" id="T{id_estructura}"><xsl:value-of select="nombre"/></div>
								
								<div class="tabla_diagrama_area_columna">
								
									<xsl:for-each select="campos/Campo">
										<xsl:sort select="orden"/>
										<div id="C{id_campo}" class="tabla_diagrama_columna key_{llaveprimaria}{unico} key_FK{count(id_estructurarelacionada)}"><xsl:value-of select="nombre"/></div>
										<input type="hidden" id="YPOS_C{id_campo}" value="{20 + (position()-1) * 16}"/>
										<input type="hidden" id="EE_C{id_campo}" value="{id_estructura}"/>
										<xsl:if test="string-length(id_estructurarelacionada)>0">
											<div id="RL_C{id_campo}" class="relacion" style="display:none"><xsl:value-of select="id_estructurarelacionada"/></div>
										</xsl:if>
									</xsl:for-each>	
									
								</div>
								
							</div>
							
						</xsl:for-each>
						
					</div>					
					
					<area_botones>
						<boton estilo="volver" destino="grupos/2.1.do">Volver al Listado</boton>
					</area_botones>
					
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
