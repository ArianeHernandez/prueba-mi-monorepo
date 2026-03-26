<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
			
		<pagina titulo="Asignación de Reportes">
			
			<stylesheet>20.1.css</stylesheet>
						
			<principal>
				<titulo icono="formatos">Reportes Disponibles</titulo>
				<contenido>
		
					<xsl:choose>
					<xsl:when test="count(//reportesCargados/ReportesCargados/String) > 0">
						
						<div id="encabezado_lista_reportes_admin" class="tabla_encabezado_float">
							<div class="item_lista_encabezado">Reportes Cargados</div>
							<p style="clear:left"></p>
						</div>
						
						<xsl:for-each select="//reportesCargados/ReportesCargados/String">
							
							<div id="nombre_reporte_admin_[ 1 ]" class="celda">
							<a class="btn btn-primary"	onclick="realizarEdicionReporteAdmin('[ 1 ]');">
								<xsl:value-of select="."/> 
							</a>
							</div>
						</xsl:for-each>
						
					</xsl:when>
					
					<xsl:otherwise>
						No se cargaron reportes
					</xsl:otherwise>
				</xsl:choose>	
										
				</contenido>
					
			</principal>

		</pagina>
		
	</xsl:template>
	
</xsl:stylesheet>
