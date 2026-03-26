<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Confirmación Eliminación">
			
			<principal>
				<titulo icono="estructura">Confirmación Eliminación</titulo>
				<contenido>
		
					<xsl:choose>
						<xsl:when test="//exitoso">
							<div class="alert  alert-info">
								<i class="fa fa-info-circle" aria-hidden="true"></i>
  								<span class="sr-only">Info:</span>
								<p><texto key="LA CARGA SE ELIMINO" /></p>
							</div>
							
						</xsl:when>
						<xsl:otherwise>
							
							<div class="alert alert-danger">
								<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
								<span class="sr-only">Error:</span>
								<p>No se ha podido realizar la eliminación de la <texto key="CARGA" />.</p>
							</div>
							
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="primary volver" destino="carga_informacion/lotes/1.2.do">Volver al Listado</boton>
					</area_botones>
					
				</contenido>
				
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
