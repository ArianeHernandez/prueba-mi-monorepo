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
				<titulo icono="formatos">Confirmación de la operación</titulo>
				<contenido>
		
					<xsl:choose>

						<xsl:when test="//eliminarGrupoFormato/exitoso='true'">
							<bloque-contenido>
								<titulo icono="confirmacion">Confirmación de la operacion</titulo>
								<contenido>
									<parrafo>El Grupo ha sido eliminado Exitosamente.</parrafo>
								</contenido>
							</bloque-contenido>
						</xsl:when>
						
						<xsl:otherwise>
							<bloque-contenido>
								<titulo icono="error">Confirmación de la operacion</titulo>
								<contenido>
									<parrafo>¡No se ha podido realizar la operacion!. Por favor intentelo nuevamente.</parrafo>
								</contenido>
							</bloque-contenido>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="volver" destino="formatos/grupoformato/38.1.do">Volver al Listado</boton>
						<boton estilo="crear" destino="formatos/grupoformato/38.2.do">Crear Nuevo</boton>
						
					</area_botones>
					
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
