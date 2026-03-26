<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
			
		<pagina titulo="Resultado Asignación">
			
			<stylesheet>20.1.css</stylesheet>
						
			<principal>
				<titulo icono="formatos">Asignando Reporte</titulo>
				<contenido>
					
				<xsl:choose>
					<xsl:when test="//guardarAsignacion/exitoso = 'true'">
						La Asignación se ha creado con éxito.
					</xsl:when>
					
					<xsl:when test="//actualizarAsignacion/exitoso = 'true'">
						La Asignación se ha actualizado con éxito.
					</xsl:when>
					
					<xsl:otherwise>
						Lo sentimos, la operación no se ha podido llevar a cabo. Por favor inténtelo nuevamente.
					</xsl:otherwise>
				</xsl:choose>	
								
				<area_botones>
					<boton estilo="primary" destino="admin_reportes/asign_rep.1.do">Volver al listado de asignaciones</boton>
				</area_botones>
							
				</contenido>
					
			</principal>

		</pagina>
		
	</xsl:template>
	
</xsl:stylesheet>
