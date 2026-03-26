<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
			
		
		<pagina titulo="Reporte Resultado">
						
			<principal>
				<titulo icono="formatos">Reporte Resultado</titulo>
				<contenido>

					<xsl:choose>
					<xsl:when test="//guardarReporte/exitoso = 'true'">
						La creación del Reporte en la Base de Datos se ha realizado con éxito.
					</xsl:when>
						
					<xsl:when test="//actualizarReporte/exitoso = 'true'">
						La actualización del Reporte en la Base de Datos se ha realizado con éxito.
						El Reporte se ha deshabilitado, por favor vuelva al listado de Reportes y habilítelo nuevamente.
					</xsl:when>
					
					<xsl:otherwise>
						Lo sentimos, la operación no se ha podido llevar a cabo. Por favor inténtelo nuevamente.
					</xsl:otherwise>
				</xsl:choose>	
				
				<div id="area_botones" >
					<area_botones>
						<boton estilo="primary" destino="admin_reportes/admin_rep.1.do">Volver al listado de Reportes</boton>
					</area_botones>
				</div>
				
				</contenido>
					
			</principal>

		</pagina>
		
	</xsl:template>
	

</xsl:stylesheet>
