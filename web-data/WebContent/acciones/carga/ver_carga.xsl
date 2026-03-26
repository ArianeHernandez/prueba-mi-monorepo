<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:variable name="datosEstructuras"
		select="//obtenerDatosCargaPorEstructura/listado/HashMap" />

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Detalle Información">

			<principal>

				<titulo>Detalle solicitud: <xsl:value-of select="//id_carga_seleccionada" /></titulo>

				<contenido>
				
					<div class="box-container">
						<xsl:for-each select="$datosEstructuras">
							<xsl:call-template name="datos-estructura">
								<xsl:with-param name="estructura" select="." />
							</xsl:call-template>
						</xsl:for-each>
					</div>
				</contenido>

			</principal>

		</pagina>
	</xsl:template>

	<xsl:template name="datos-estructura">
		<xsl:param name="estructura" />

		<xsl:variable name="formatos_campo"
			select="$estructura/formatos_campo/FormatoCampo" />

		<xsl:if test="count($formatos_campo) > 0">

			<xsl:variable name="id_estructura" select="$estructura/id_estructura" />
			<xsl:variable name="camposRegistro" select="$estructura/datos/HashMap" />

			<bloque-contenido>

				<contenido>
					<subtitulo>
						<xsl:value-of select="$estructura/nombre_estructura" />
					</subtitulo>

					<tabla>
						<encabezado>
							<xsl:for-each select="$formatos_campo">
								<xsl:if test="seleccion_visualizacion = 'S' or seleccion_campo = 'S'">
									<xsl:variable name="id_campo" select="id_campo" />
									<titulo>
										<xsl:value-of select="titulo" />
									</titulo>
								</xsl:if>
							</xsl:for-each>
						</encabezado>
						<xsl:for-each select="$camposRegistro"> 	<!-- Registro -->
							<xsl:variable name="posicion-datos" select="position()" />

							<!-- Formatos Campo -->
							<!-- <encabezado> <titulo>Campo</titulo> <titulo>Valor</titulo> </encabezado> -->
							<fila>
								<xsl:for-each select="$formatos_campo"> <!-- Campo por cada registros -->
									<xsl:if
										test="seleccion_visualizacion = 'S' or seleccion_campo = 'S'">
										<xsl:variable name="id_campo" select="id_campo" />
										<!-- <valor> <xsl:value-of select="titulo" /> </valor> -->
										<valor>
											<xsl:choose>
												<xsl:when
													test="count($camposRegistro[$posicion-datos]/*[@name=concat('V', $id_campo)]) > 0">
													<xsl:value-of
														select="$camposRegistro[$posicion-datos]/*[@name=concat('V', $id_campo)]" />
												</xsl:when>
												<xsl:otherwise>
													<xsl:value-of
														select="$camposRegistro[$posicion-datos]/*[@name=concat('C', $id_campo)]" />
												</xsl:otherwise>
											</xsl:choose>
										</valor>
									</xsl:if>
								</xsl:for-each>
							</fila>
						</xsl:for-each>
					</tabla>

				</contenido>
			</bloque-contenido>

		</xsl:if>

	</xsl:template>

</xsl:stylesheet>
