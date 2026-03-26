<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/EditarPersona.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		<xsl:variable name="editar" select="//editar"/>	
		<pagina titulo="Crear Director de Negocio">
			
			<principal>
				<titulo>Crear Director de Negocio</titulo>
				<contenido>
				<div class="box-container">
				
					<xsl:choose>
					
						<xsl:when  test="(count(//DIRECTOR/obtenerDirectorNegocioIdentificacionNegocio/Objeto) = 0) or $editar = 'SI'">
							<formulario id="form_usuario" destino="director_negocio/7.3.do">
								<xsl:call-template name="EDITARPERSONA">
									<xsl:with-param name="Persona" select="//INFOPERSONA/obtenerPersonaPorIdentificacion/Persona"/>
									<xsl:with-param name="TipoPersona" select="//DATOS/TIPOPERSONA"/>
									<xsl:with-param name="TipoDocumento" select="//DATOS//TIPODOCUMENTO/obtenerTipoDocumento/TipoDocumento"/>
									<xsl:with-param name="Identificacion" select="//DATOS/IDENTIFICACION"/>	
									<xsl:with-param name="editar" select="$editar"/>
									<xsl:with-param name="Credencial" select="//CREDENCIAL/obtenerCredencialPersonaID/Credencial"/>
									<xsl:with-param name="rol" >DN</xsl:with-param>
									<xsl:with-param name="LoginPorIdentificacion" select="//LOGIN/esLoginPorIdentificacion/Boolean"/>
								</xsl:call-template>
							</formulario>
							
							<area_botones>
								<boton estilo="primary volver" destino="director_negocio/7.1.do">Volver</boton>
								<boton estilo="primary guardar" validacion="validarPersona()" formulario="form_usuario">Guardar</boton>
							</area_botones>
						</xsl:when>
						<xsl:otherwise>
							<bloque-contenido>
								<titulo icono="edicion">Error</titulo>
								<contenido>
									<parrafo>
										El usario ya existe para este negocio!
									</parrafo>
								</contenido>
							</bloque-contenido>
							<area_botones>
								<boton estilo="primary volver" destino="director_negocio/7.1.do">Volver</boton>
							</area_botones>
						</xsl:otherwise>
					</xsl:choose>
				
				</div>	
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
