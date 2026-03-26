<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<xsl:variable name="Titulo">
			<xsl:choose>
				<xsl:when test="rol_actual='REV'">Revisor</xsl:when>
				<xsl:when test="rol_actual='PRE'">Preparador</xsl:when>
				<xsl:when test="rol_actual='LIB'">Liberador</xsl:when>
			</xsl:choose>
		</xsl:variable>
		
		<pagina titulo="{$Titulo}">
			
			
			<principal>
				<titulo>Crear <xsl:value-of select="$Titulo"/></titulo>
				<contenido>
					<bloque-contenido>
						<titulo icono="edicion">Respuesta</titulo>
						<contenido>
							<parrafo>
							<xsl:choose>
									<xsl:when test="//Editar = 'SI'">
										<xsl:choose>
											<xsl:when test="count(//guardarPersona) > 0">
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
												No es posible guardar la información del <xsl:value-of select="$Titulo" />
											</xsl:when>
											<xsl:when test="$Valor = '4'">
												No es posible crear la relación del <xsl:value-of select="$Titulo" /> con el negocio,
												 posiblemente la persona ya tiene un relación con este Cliente!
											</xsl:when>
											<xsl:when test="$Valor = '5'">
												Lo sentimos, en este momento no se puede crear el usuario. Por favor verifique el correo electronico del usuario que desea crear.
											</xsl:when>
											<xsl:otherwise>
												No se pudo realizar la operacion. Intentelo nuevamente.
											</xsl:otherwise>
										</xsl:choose>
									</xsl:otherwise>
								</xsl:choose>
							</parrafo>
						</contenido>
					</bloque-contenido>
					<area_botones>
						<boton estilo="primary volver" destino="personas/9.1.do">Volver</boton>
					</area_botones>
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
