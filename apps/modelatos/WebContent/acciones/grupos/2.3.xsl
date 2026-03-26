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
				<titulo icono="grupos">Confirmación Guardado</titulo>
				<contenido>
		
					<xsl:choose>

						<xsl:when test="guardarGrupo/exitoso='true'">
							<bloque-contenido>
								<titulo icono="confirmacion">Confirmación Guardado</titulo>
								<contenido>
									<parrafo>El Grupo ha sido guardado Exitosamente.</parrafo>
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
					
					<area_botones>
						<boton estilo="volver" destino="grupos/2.1.do">Volver al Listado</boton>
						<boton estilo="crear" destino="grupos/2.2.do">Crear Nuevo</boton>
					</area_botones>
					
				</contenido>
				
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
