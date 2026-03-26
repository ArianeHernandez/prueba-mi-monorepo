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
				<titulo icono="usuarios">Confirmación Guardado</titulo>
				<contenido>

					
					<xsl:if test="count(//editarContrasena/exito)">
						
						<bloque-contenido>
							<titulo icono="confirmacion">Confirmación Guardado</titulo>
							<contenido>
								
								<xsl:choose>
									<xsl:when test="//editarContrasena/exito='true'">
										<parrafo>La contraseña ha sido cambiada exitosamente.</parrafo>
									</xsl:when>
									
									<xsl:otherwise>
										<parrafo><xsl:value-of select="//editarContrasena/mensaje"/>.</parrafo>
									</xsl:otherwise>
								</xsl:choose>
								
							</contenido>
						</bloque-contenido>
						
					</xsl:if>
					
					
					
					<xsl:if test="count(//guardarSimplePersona/exito)">
						
						<bloque-contenido>
							<titulo icono="confirmacion">Confirmación Guardado</titulo>
							<contenido>
								
								<xsl:choose>
									<xsl:when test="//guardarSimplePersona/exito='true'">
										<parrafo>Los cambios sobre su información de usuario han cambiado exitosamente.</parrafo>
									</xsl:when>
									
									<xsl:otherwise>
										<parrafo>Lo sentimos, ha ocurrido un error inesperado al guardar..</parrafo>
									</xsl:otherwise>
								</xsl:choose>
								
							</contenido>
						</bloque-contenido>
						
					</xsl:if>
							
					
					
					<area_botones>
						<boton estilo="volver" destino="inicio/0.do">Volver al Inicio</boton>
					</area_botones>
					
				</contenido>
			</principal>
			
		</pagina>
				
	</xsl:template>

</xsl:stylesheet>
