<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Administración de Listas de Valores">
			<javascript>admin/3.1.js</javascript>
			
			<principal>
				<titulo icono="listavalores">Administración de Listas de Valores</titulo>
				<contenido>
					<xsl:choose>
						<xsl:when test="count(//obtenerListaValoresPorPersona/listado/ListaValores)>0">
						
							<formulario destino="listasvalores/3.1.1.do" id="form_listasvalores">
								<variable id="id_lista_valores" valor=""/>
							</formulario>
							
						 	<tabla icono="list">
								<encabezado>
									<titulo>Lista de Valores</titulo>
									<titulo>Descripción</titulo>
								</encabezado>
								<xsl:for-each select="//obtenerListaValoresPorPersona/listado/ListaValores">
									<xsl:sort select="nombre"/>
									<fila id="fila_listavalor_{position()}">
										<valor>
											<variable id="id_lista_valores_{position()}" valor="{id_lista_valores}"/>
											<p>L<xsl:value-of select="id_lista_valores"/> | <b><xsl:value-of select="nombre"/></b></p>
										</valor>
										<valor><xsl:value-of select="descripcion"/></valor>
									</fila>
								</xsl:for-each>
							</tabla>
							
							<formulario destino="listasvalores/3.1.do" id="form_pag">
								<paginacion id="_numeropagina_listavalores" formulario="form_pag" numero="{numeroPagina}" paginacion="{//TAMANO_PAGINA}" total="{totalListadoValoresPorPersona/total}"/>
							</formulario>
							
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen listas de valores.
							</parrafo>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
						<boton estilo="crear" destino="listasvalores/3.2.do">Crear Nueva</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
