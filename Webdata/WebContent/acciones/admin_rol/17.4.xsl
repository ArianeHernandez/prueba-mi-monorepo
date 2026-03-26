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
				<titulo icono="formatos">Confirmación Eliminado</titulo>
				<contenido>
					<div class="box-container">
					<xsl:choose>

						<xsl:when test="eliminarRol/eliminado='true'">
							<bloque-contenido>
								<titulo icono="confirmacion">Confirmación Eliminado</titulo>
								<contenido>
									<parrafo>El Rol ha sido eliminado Exitosamente.</parrafo>
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
						<boton estilo="primary volver" destino="admin_rol/17.1.do">Volver al Listado</boton>
						<boton estilo="primary" destino="admin_rol/17.2.do">Crear Nuevo</boton>
						
					</area_botones>
					</div>
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
