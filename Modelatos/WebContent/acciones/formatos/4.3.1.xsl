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
				<titulo icono="formatos">Confirmación Guardado</titulo>
				<contenido>
		
					<xsl:choose>

						<xsl:when test="count(//guardarEstructuraFormato/Formato/id_formato)>0">
							<div class="mesg2">
								<p>El formato se ha guardado Exitosamente.</p>
							</div>
						</xsl:when>
						
						<xsl:otherwise>
							
							<div class="mesg2 red">
								<p>No se ha podido realizar la operacion..</p>
							</div>
							
						</xsl:otherwise>
						
					</xsl:choose>
					
					<xsl:choose>
						<xsl:when test="//guardarEstructuraFormato/Formato/tipoformato='E'">
							<formulario destino="formatos/4.2.do" id="form_formatos">
								<variable id="id_formato" valor="{//guardarEstructuraFormato/Formato/id_formato}"/>
							</formulario>
						</xsl:when>
						<xsl:when test="//guardarEstructuraFormato/Formato/tipoformato='S'">
							<formulario destino="formatos/4.8.do" id="form_formatos">
								<variable id="id_formato" valor="{//guardarEstructuraFormato/Formato/id_formato}"/>
							</formulario>
						</xsl:when>
					</xsl:choose>
					
					<area_botones>
						<boton estilo="volver" destino="formatos/4.1.do">Volver al Listado</boton>
						<boton estilo="crear" destino="formatos/4.2.do">Crear Nuevo</boton>
						<boton estilo="editar" formulario="form_formatos">Editar Formato</boton>
					</area_botones>
					
				</contenido>
			</principal>
			
		</pagina>
				
	</xsl:template>

</xsl:stylesheet>
