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
				<titulo icono="estructura">Confirmación Envío</titulo>
				<contenido>
				<div class="box-container">
				
					<bloque-contenido>
						<titulo>Confirmación Envío</titulo>
						<contenido>
							<xsl:choose>
								<xsl:when test="//enviarCarga/exito='true'">
									<parrafo>Se ha iniciado el proceso de carga en el sistema. Al finalizar, revise los detalles que muestra en pantalla</parrafo>
								</xsl:when>
								<xsl:otherwise>
									<parrafo estilo="resaltado">No se puede realizar la operación.</parrafo>
								</xsl:otherwise>
							</xsl:choose>
							
						</contenido>
					</bloque-contenido>
					
					<area_botones>
						<boton estilo="primary volver" destino="administracion_carga/3.3.do?pestana=2">Volver</boton>
					</area_botones>
				
				</div>	
				</contenido>
				
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
