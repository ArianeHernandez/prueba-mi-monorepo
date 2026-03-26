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
				<titulo icono="formatos">Confirmación de la operación</titulo>
				<contenido>
		
					<xsl:choose>

						<xsl:when test="//guardarGrupoformato/exitoso ='true'">
								<xsl:choose>
									<xsl:when test="count(//obtenerGrupoFormato/GrupoFormato/id_grupoformato)>0">
										<parrafo>El grupo'<xsl:value-of select="//obtenerGrupoFormato/GrupoFormato/nombre"/>' se ha guardado Exitosamente.</parrafo>
									</xsl:when>
									
									<xsl:otherwise>
										<parrafo>El grupo se ha guardado Exitosamente.</parrafo>
									</xsl:otherwise>
									
								</xsl:choose>	
						</xsl:when>
						
						<xsl:otherwise>
								<parrafo>No se ha podido realizar la operacion.</parrafo>
						</xsl:otherwise>
						
					</xsl:choose>
					
					
					<formulario id="form_editar" destino="formatos/grupoformato/38.2.do">
						<variable id="id_grupoformato" valor="{//obtenerGrupoFormato/GrupoFormato/id_grupoformato}"/>
					</formulario>
										
					<area_botones>
						<xsl:if test="(//obtenerGrupoFormato/GrupoFormato/id_grupoformato)>0">
							<boton estilo="editar" formulario="form_editar">Editar Grupo</boton>
							
						</xsl:if>
						<boton estilo="volver" destino="formatos/grupoformato/38.1.do">Volver al Listado</boton>
						<boton estilo="crear" destino="formatos/grupoformato/38.2.do">Crear Nuevo</boton>
						
					</area_botones>
					
				</contenido>
			</principal>
			
		</pagina>
				
	</xsl:template>

</xsl:stylesheet>
