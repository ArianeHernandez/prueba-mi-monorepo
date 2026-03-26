<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Uris">
			<javascript>admin/7.1.js</javascript>
			
			<principal>
				<titulo icono="variable">Administración de Uris</titulo>
				<contenido>
					
					<xsl:choose>
						<xsl:when test="count(//obtenerUris/listado/Uri)>0">
						
							<formulario destino="uris/7.2.do" id="formEdicion">
								<variable id="id_uri" valor=""/>
							 	<tabla>
									<encabezado>
										<titulo>Nombre de Uri</titulo>
										<titulo>Endpoint</titulo>
									</encabezado>
										<xsl:for-each select="//obtenerUris/listado/Uri">
												<fila id="fila_uri_{position()}">
													<valor>
														<variable id="id_uri_{position()}" valor="{id_uri}"/>
														[<xsl:value-of select="id_uri"/>] | <b><xsl:value-of select="nombre"/></b>
													</valor>
													<valor><xsl:value-of select="endPoint"/></valor>
												</fila>
										</xsl:for-each>
								</tabla>
							</formulario>
							
							<formulario destino="uris/7.1.do" id="form_pag">
								<paginacion id="_numeropagina_uris" formulario="form_pag" numero="{numeroPagina}" paginacion="{//TAMANO_PAGINA}" total="{totalUris/total}"/>
							</formulario>
							
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen Uris.
							</parrafo>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
						<boton estilo="crear" destino="uris/7.2.do">Crear Nueva</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
