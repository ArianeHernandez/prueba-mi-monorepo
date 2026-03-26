<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://componentes/carga/aprobacionCarga.xsl" />
	<xsl:include href="context://componentes/notas_carga/notas_carga.xsl" />
	<xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>
	<xsl:include href="context://stylesheets/templates/DetalleCarga.xsl"/>
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="{//obtenerFormato/Formato/nombre} - {//obtenerPersona/Persona/nombre}">

			<javascript>revisiones/11.3.js</javascript>

			<principal>
				<titulo><xsl:value-of select="//obtenerFormato/Formato/nombre" /> -	<xsl:value-of select="//obtenerPersona/Persona/nombre" />&#160;<xsl:value-of select="//obtenerPersona/Persona/apellido" /> - <texto key="CARGA" /> - <xsl:value-of select="//obtenerCarga/Carga/id_carga" /></titulo>
				<contenido>
					
					<!-- INFO CARGA -->
					
					
					<bloque-pestanas>
						<pestana titulo="Aprobar / Rechazar">
						
						<h2 class="bloque_contenido_titulo">Aprobar / Rechazar</h2>
								
						<tabla>
							<encabezado>
								<titulo>Tipo</titulo>
								<titulo>Identificador <texto key="CARGA" /></titulo>
<!-- 								<titulo>Nombre</titulo> -->
								<titulo>Deudor Solicitante</titulo>
								<titulo>Fecha de Carga de Información</titulo>
								<titulo>Estado</titulo>
								
								
								<!-- VALOR OPCIONAL -->
								<xsl:if test="//obtenerCarga/Carga/valor_total_bigdecimal!=''">
									<titulo>Valor</titulo>
								</xsl:if>
<!-- 								<titulo>N° registros</titulo> -->
								
							</encabezado>
							<xsl:for-each select="//obtenerCarga/Carga">
								<xsl:variable name="nusuario" select="nombre_usuario" />
								<xsl:variable name="npersona" select="nombre_persona" />
								<fila>
									<valor>
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
									<valor>
										<xsl:if test="$nusuario=$npersona">
											<xsl:value-of select="nombre_usuario" />&#160;<xsl:value-of select="apellido_persona" />
											
										</xsl:if>
										<xsl:if test="$nusuario!=$npersona">
											<xsl:value-of select="nombre_usuario" />
										</xsl:if>
									</valor>
									<valor>
										<xsl:value-of select="fecha_subida" />
									</valor>
									<valor>
										<b><estado key="{estado}" cliente="S"/></b>
									</valor>
									
									<!-- VALOR OPCIONAL -->
									<xsl:if test="valor_total_bigdecimal!=''">
										<valor>
											<xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
										</valor>
									</xsl:if>
<!-- 									<valor> -->
<!-- 										<xsl:value-of select="numero_registros_bigdecimal" /> -->
<!-- 									</valor> -->
									
								</fila>
								
								
							</xsl:for-each>
						</tabla>
						
						
					
							<xsl:if test="count(//obtenerCargasSimilaresCliente/listado/Carga)>0">
							
								<div id="area_advertencia">
					
									<h2 class="bloque_contenido_titulo">Advertencia</h2>
									
									<xsl:if test="count(//obtenerCargasSimilaresCliente/listado/Carga)=1">
										<p><b class="txt_alerta"><texto key="LA CARGA TIENE EL MISMO VALOR Y NUMERO DE PAGOS QUE LA CARGA" />:</b></p>
									</xsl:if>
									
									<xsl:if test="count(//obtenerCargasSimilaresCliente/listado/Carga)>1">
										<p><b class="txt_alerta"><texto key="LA CARGA TIENE EL MISMO VALOR Y NUMERO DE PAGOS QUE LAS CARGAS" />:</b></p>
									</xsl:if>
							
									<tabla>
										<encabezado>
											<titulo>Tipo</titulo>
											<titulo><texto key="IDENTIFICADOR CARGA" /></titulo>
											<titulo>Deudor Solicitante</titulo>
											<titulo>Fecha de Carga de Información</titulo>
											<titulo>Estado</titulo>
											
											
											<!-- VALOR OPCIONAL -->
											<xsl:if test="//obtenerCarga/Carga/valor_total_bigdecimal!=''">
												<titulo>Valor</titulo>
											</xsl:if>
											<titulo>N° registros</titulo>
											
										</encabezado>
										<xsl:for-each select="//obtenerCargasSimilaresCliente/listado/Carga">
											<fila>
												<valor>
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
												<valor>
													<xsl:value-of select="nombre" />
												</valor>
												<valor>
													<xsl:value-of select="fecha_subida" />
												</valor>
												<valor>
													<b><estado key="{estado}" cliente="S"/></b>
												</valor>
												
												<!-- VALOR OPCIONAL -->
												<xsl:if test="valor_total_bigdecimal!=''">
													<valor>
														<xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
													</valor>
												</xsl:if>
												<valor>
													<xsl:value-of select="numero_registros_bigdecimal" />
												</valor>
												
											</fila>
											
											
										</xsl:for-each>
									</tabla>
									
									<area_botones>
										<boton estilo="continuar" accion="osm_setVisible('area_advertencia',false); osm_setVisible('area_aprobacion',true);">Continuar</boton>
									</area_botones>
								
								</div>		
									
							</xsl:if>
					
							<div id="area_aprobacion">
								<xsl:if test="count(//obtenerCargasSimilaresCliente/listado/Carga)>0">
									<xsl:attribute name="style">display:none</xsl:attribute>
								</xsl:if>
							

								<xsl:call-template name="aprobacion-botones"/>

							</div>
					
						</pestana>
						
						<pestana titulo="Detalle" visible="{count(//ver_detalle)>0}">
							
							<!-- DETALLE DE LA CARGA -->
							
							<xsl:call-template name="DETALLE_CARGA_AJAX" >
								<xsl:with-param name="validacionesActivas">false</xsl:with-param>
							</xsl:call-template>
							
						</pestana>
						
						<pestana titulo="Notas">
							<!-- NOTAS CARGA -->
							<xsl:call-template name="notas_carga">
								<xsl:with-param name="permiteAgregar">true</xsl:with-param>
							</xsl:call-template>
						</pestana>
						
						<pestana titulo="Archivos Adjuntos">
							<!-- ARCHIVOS ADJUNTOS -->
							<xsl:call-template name="archivos_adjuntos">
								<xsl:with-param name="permiteAdjuntar">false</xsl:with-param>
								<xsl:with-param name="permiteEliminar">true</xsl:with-param>
							</xsl:call-template>
						</pestana>
						
					</bloque-pestanas>

					<area_botones>
						<boton estilo="primary volver" destino="revisiones/11.2.do">Volver</boton>
					</area_botones>
							
					
				</contenido>
			</principal>
			
			<xsl:call-template name="aprobacion-carga">
				<xsl:with-param name="siguiente_url">revisiones/11.4.do</xsl:with-param>
				<xsl:with-param name="nombreFaseProceso">REVISION</xsl:with-param>
				<xsl:with-param name="usar_firma_digital">
					<xsl:choose>
						<xsl:when test="//OSM-INIT-SESSION/Info/Usuario/uso_firma_revisor='S'">S</xsl:when>
						<xsl:otherwise>N</xsl:otherwise>					
					</xsl:choose>
				</xsl:with-param>
				
				
			</xsl:call-template>

		</pagina>

	</xsl:template>
	
	
</xsl:stylesheet>
