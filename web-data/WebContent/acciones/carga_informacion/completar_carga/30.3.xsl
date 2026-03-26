<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		<pagina>

			<principal>
			
				<titulo>Respuesta por banco</titulo>

				<contenido>
				<div class="box-container">
					<xsl:choose>
						<xsl:when test="//actualizarCargaQueRequiereArchivoAdjunto/exitoso='true'">
							<parrafo>Transacción exitosa. Se ha actualizado con exito</parrafo>
						</xsl:when>
						
						<xsl:otherwise>
							<parrafo>Error! La actualizacion no se ha llevado con exito. Por favor intente nuevamnete</parrafo>
							
						</xsl:otherwise>
					</xsl:choose>
					
					<area_botones>
					<br/>
					<br/>
						<boton estilo="primary volver" id="bnt_volver" 
								accion="osm_go('carga_informacion/completar_carga/30.1.do');return false;">
						Volver</boton>
					</area_botones>
				</div>
				</contenido>
			</principal>
			
			
		</pagina>
		
	</xsl:template>
	
</xsl:stylesheet>
