<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Guardar">
			
			<principal>
				<titulo>Guardar</titulo>
				<contenido>
					<bloque-contenido>
						<titulo icono="edicion">Respuesta</titulo>
						<contenido>

						
							<parrafo>
								<xsl:variable name="Valor" >
									<xsl:choose>
										<xsl:when test="count(//guardarUsuarioVariosNegocios/Valor)=0">
											<xsl:value-of select="//actualizarUsuarioVariosNegocio/Valor" /> 
										</xsl:when>
										<xsl:otherwise>
											<xsl:value-of select="//guardarUsuarioVariosNegocios/Valor" />
										</xsl:otherwise>
									</xsl:choose>
								</xsl:variable>
								
								<xsl:choose>
									<xsl:when test="$Valor = '1'">
										El cliente se almacenó con éxito!
									</xsl:when>
									<xsl:when test="$Valor = '2'">
										No es posible guardar la información de la persona, verifique los campos obligatorios!
									</xsl:when>
									<xsl:when test="$Valor = '3'">
										No es posible guardar la información del cliente, verique que el la persona no sea un cliente!
									</xsl:when>
									<xsl:when test="$Valor = '4'">
										No es posible guardar las variables!
									</xsl:when>
									<xsl:when test="$Valor = '5'">
										No es posible crear la relación del cliente con el negocio!
									</xsl:when>
									<xsl:when test="$Valor = '6'">
										No es posible crear el login para este cliente
									</xsl:when>
									<xsl:when test="$Valor = '7'">
										No es posible enviar el correo electrónico
									</xsl:when>
									<xsl:otherwise>
										Error desconocido!
									</xsl:otherwise>
								</xsl:choose>
								
							</parrafo>
						</contenido>
					</bloque-contenido>
					<area_botones>
						<boton estilo="primary volver" destino="cliente/6.1.do">Volver</boton>
					</area_botones>
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
