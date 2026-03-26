<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>
	<xsl:include href="context://stylesheets/templates/DetalleCarga.xsl"/>
	<xsl:include href="context://stylesheets/templates/firma/InformacionFirmaDigitalCarga.xsl"/>
	<xsl:include href="context://componentes/notas_carga/notas_carga.xsl" />
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina	titulo="Historicos">

			<javascript>administracion_carga/proceso/22.2.js</javascript>
			
			<stylesheet>aprobacion.css</stylesheet>

			<principal>
				<titulo>Históricos / Detalle <texto key="CARGA" />: <xsl:value-of select="//obtenerCarga/Carga/id_carga" /></titulo>
				<contenido>
					<bloque-pestanas>
						<pestana titulo="Información general">
								
								<!-- INFO CARGA -->
								<tabla>
									<encabezado>
										<titulo class="hidden-xs">Tipo</titulo>
										<titulo>Identificador <texto key="CARGA" /></titulo>
										<titulo class="hidden-xs">Deudor Solicitante</titulo>
										<titulo>Fecha de <texto key="Liberación"/></titulo>
										<titulo>Estado</titulo>
										
										<!-- VALOR OPCIONAL -->
										<xsl:if test="//obtenerCarga/Carga/valor_total_bigdecimal!=''">
											<titulo>Valor</titulo>
										</xsl:if>
<!-- 										<titulo class="hidden-xs">N° Registros</titulo>	 -->
									</encabezado>
									<xsl:for-each select="//obtenerCarga/Carga">
										<xsl:variable name="nusuario" select="nombre_usuario" />
										<xsl:variable name="npersona" select="nombre_persona" />
										<fila>
											<valor class="hidden-xs">
												<xsl:choose>
													<xsl:when test="extension='xls'">
														<bloque	estilo="extension_xls" />
													</xsl:when>
													<xsl:otherwise>
														<bloque estilo="extension_none" />
													</xsl:otherwise>
												</xsl:choose>
											</valor>
											<valor>
												<xsl:value-of select="id_carga" />
											</valor>
											<valor class="hidden-xs">
												<xsl:if test="$nusuario=$npersona">
												<xsl:value-of select="nombre_usuario" />&#160;<xsl:value-of select="apellido_persona" />
													
												</xsl:if>
												<xsl:if test="$nusuario!=$npersona">
													<xsl:value-of select="nombre_usuario" />
												</xsl:if>
											</valor>
											<valor>
												<xsl:value-of select="fecha_liberacion" />
											</valor>
											<valor>
												<b>
													<xsl:if test="estado = 'R'">
														<xsl:attribute name="style">color:red</xsl:attribute>
													</xsl:if>
													<estado key="{estado}" cliente="S"/>
													<p><xsl:value-of select="motivo_estado"/></p>
												</b>
											</valor>
											
											<!-- VALOR OPCIONAL -->
											<xsl:if test="valor_total_bigdecimal!=''">
												<valor>
													<xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
												</valor>
											</xsl:if>
<!-- 											<valor class="hidden-xs"> -->
<!-- 												<xsl:value-of select="numero_registros_bigdecimal" /> -->
<!-- 											</valor> -->
											
											
										</fila>
									</xsl:for-each>
								</tabla>
			
								<xsl:call-template name="INFORMACION_FIRMA_DIGITAL_CARGA"/>
							
								<!-- DETALLE DE LA CARGA -->
								<xsl:call-template name="DETALLE_CARGA_AJAX" >
									<xsl:with-param name="validacionesActivas">false</xsl:with-param>
								</xsl:call-template>
								
							</pestana>
						
						
						
						<!-- NOTAS CARGA -->

						<pestana titulo="Notas">
							<xsl:call-template name="notas_carga">
								<xsl:with-param name="permiteAgregar">false</xsl:with-param>
							</xsl:call-template>
						</pestana>
					
						<!-- ARCHIVOS ADJUNTOS -->
						<pestana titulo="Archivos adjuntos" pdfview="false">
							<xsl:call-template name="archivos_adjuntos">
								<xsl:with-param name="permiteAdjuntar">false</xsl:with-param>
								<xsl:with-param name="permiteEliminar">false</xsl:with-param>
							</xsl:call-template>
						</pestana>
						
						
						
						<!-- DETALLE DE ERRORES -->
						<xsl:if test="//obtenerValorByEtiqueta/Valor='S'">
							<xsl:if test="count(obtenerLogErrorEjecucionesCarga/listado/Log)>0">
							
								<pestana titulo="Detalle de errores" pdfview="false">
									<xsl:call-template name="detalle_errores"/>
									<boton estilo="pdf" accion="window.open(CONTEXTPATH +'/historico/detalle_errores.do.pdf','file')">
										Ver PDF
									</boton>
								</pestana>
							
							</xsl:if>	
						</xsl:if>					
						
					</bloque-pestanas>	
					
					<area_botones>
						<boton estilo="primary volver" destino="{//url_volver}">Volver</boton>
					</area_botones>
				</contenido>	
			</principal>

		</pagina>

	</xsl:template>

	<xsl:template name="detalle_errores">
		<tabla id="tabla_errores">
		<encabezado>
			<titulo>Fecha</titulo>
			<titulo>Código de error</titulo>
			<titulo>Mensaje</titulo>
			<titulo colspan="3">Información de registro</titulo>
		</encabezado>
			<xsl:for-each select="//obtenerLogErrorEjecucionesCarga/listado/Log">
				<xsl:sort data-type="number" select="id_log" />
				<fila>
					<valor class="w150">
						<xsl:value-of select="fecha"></xsl:value-of>
					</valor>
					<valor>
						<xsl:value-of select="cod_error"></xsl:value-of>
					</valor>
					<valor>
						<xsl:value-of select="descripcion"></xsl:value-of>
					</valor>
					<valor class="w10p">
						<xsl:value-of select="ref1"></xsl:value-of>
					</valor>
					<valor class="w10p">
						<xsl:value-of select="ref2"></xsl:value-of>
					</valor>
					<valor class="w10p">
						<xsl:value-of select="ref3"></xsl:value-of>
					</valor>
				</fila>
			</xsl:for-each>
		</tabla>
	</xsl:template>
	
</xsl:stylesheet>
