<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Respuesta Crear Administrador de Cliente">
			
			<principal>
				<titulo>Respuesta Crear Administrador de Cliente</titulo>
				<contenido>
					<bloque-contenido>
						<titulo icono="edicion">Respuesta</titulo>
						<contenido>
							<parrafo>
								<xsl:choose>
									<xsl:when test="//Editar = 'SI'">
										<xsl:choose>
											<xsl:when test="count(//guardarPersona/*) > 0">
												La persona se guardó con éxito!
											</xsl:when>
											<xsl:otherwise>
												No es posible actualizar la información de la Persona!
											</xsl:otherwise>
										</xsl:choose>
											
									</xsl:when>
									<xsl:otherwise>
										<xsl:variable name="Valor" select="//guardarPersonaUsuarioRol/exito" />
										<xsl:choose>
											<xsl:when test="$Valor = '1'">
												La persona se creó con éxito!
											</xsl:when>
											<xsl:when test="$Valor = '2'">
												No es posible guardar la información de la persona!
											</xsl:when>
											<xsl:when test="$Valor = '3'">
												No es posible guardar la información del administrador!
											</xsl:when>
											<xsl:when test="$Valor = '4'">
												No es posible crear la relación de la persona con el negocio!
											</xsl:when>
											<xsl:otherwise>
												Error desconocido!
											</xsl:otherwise>
										</xsl:choose>
									</xsl:otherwise>
								</xsl:choose>
								
								
							</parrafo>
						</contenido>
					</bloque-contenido>
					<area_botones>
						<boton estilo="primary volver" destino="admin_cliente/8.1.do">Volver</boton>
					</area_botones>
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
