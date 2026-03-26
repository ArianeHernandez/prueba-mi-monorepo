<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Confirmación Eliminado">
			
			<principal>
				<titulo icono="listavalores">Confirmación Eliminado</titulo>
				<contenido>
		
					<xsl:choose>

						<xsl:when test="eliminarListaValores/exitoso='true'">
							<bloque-contenido>
								<titulo icono="confirmacion">Confirmación Eliminado</titulo>
								<contenido>
									<parrafo>La Lista de Valores ha sido eliminada Exitosamente.</parrafo>
								</contenido>
							</bloque-contenido>
						</xsl:when>
						
						<xsl:otherwise>
							<bloque-contenido>
								<titulo icono="error">Confirmación Eliminado</titulo>
								<contenido>
									<parrafo>No se ha podido realizar la operacion.</parrafo>
								</contenido>
							</bloque-contenido>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="volver" destino="listasvalores/3.1.do">Volver al Listado</boton>
						<boton estilo="crear" destino="listasvalores/3.2.do">Crear Nueva</boton>
					</area_botones>
					
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
