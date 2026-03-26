<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Confirmación Guardado">
			
			<principal>
				<titulo icono="listavalores">Confirmación Guardado</titulo>
				<contenido>
		
					<xsl:choose>

						<xsl:when test="count(//guardarListaValores/ListaValores/id_lista_valores)>0">
							<bloque-contenido>
								<titulo icono="confirmacion">Confirmación Guardado</titulo>
								<contenido>
									<parrafo>La lista de valores '<xsl:value-of select="//guardarListaValores/ListaValores/nombre"/>' se ha guardado Exitosamente.</parrafo>
								</contenido>
							</bloque-contenido>
						</xsl:when>
						
						<xsl:otherwise>
							<bloque-contenido>
								<titulo icono="error">Confirmación Guardado</titulo>
								<contenido>
									<parrafo>No se ha podido realizar la operacion.</parrafo>
								</contenido>
							</bloque-contenido>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<formulario destino="listasvalores/3.2.do" id="form_listasvalores">
						<variable id="id_lista_valores" valor="{//guardarListaValores/ListaValores/id_lista_valores}"/>
					</formulario>
					
					<area_botones>
						<boton estilo="volver" destino="listasvalores/3.1.do">Volver al Listado</boton>
						<boton estilo="crear" destino="listasvalores/3.2.do">Crear Nuevo</boton>
						<boton estilo="editar" formulario="form_listasvalores">Editar Lista</boton>
					</area_botones>
					
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
