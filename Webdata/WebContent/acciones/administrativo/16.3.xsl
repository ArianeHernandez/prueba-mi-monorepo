<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<xsl:variable name="contenido" select="//obtenerContenidosByURL/listado/Contenido"/>
		
		<pagina titulo="Crear {$contenido[etiqueta='Administrativo']/texto}">
			
			<principal>
				<titulo>Guardar <texto key="Administrativo"/></titulo>
				<contenido>
				<div class="box-container">
					<bloque-contenido>
						<titulo icono="edicion">Respuesta</titulo>
						<contenido>
							<parrafo>
								<xsl:variable name="Valor" select="//guardarPersonaAdministrativo/exito" />
								<xsl:choose>
									<xsl:when test="$Valor = '1'">
										La persona se guardó con éxito!
									</xsl:when>
									<xsl:when test="$Valor = '2'">
										No es posible guardar la información de la persona, verifique los campos obligatorios!
									</xsl:when>
									<xsl:when test="$Valor = '3'">
										No es posible guardar la información del administrador!
									</xsl:when>
									<xsl:when test="$Valor = '4'">
										No es posible crear la relación de la persona con el negocio!
									</xsl:when>
									<xsl:when test="$Valor = '5'">
										No se puede guardar la credencial
									</xsl:when>
									<xsl:when test="$Valor = '6'">
										No es posible enviar el correo!
									</xsl:when>
									<xsl:when test="$Valor = '7'">
										Información incompleta!
									</xsl:when>
									<xsl:otherwise>
										Error desconocido!
									</xsl:otherwise>
								</xsl:choose>
								
							</parrafo>
						</contenido>
					</bloque-contenido>
					<area_botones>
						<boton estilo="primary volver" destino="administrativo/16.1.do">Volver</boton>
					</area_botones>
				</div>
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
