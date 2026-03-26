<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>

	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Consulta de Informacion">
			
			<javascript>carga_informacion/completar_carga/30.2.js</javascript>
			
			<principal>
				<titulo>Consultar Información - Servicio <xsl:value-of select="obtenerFormato/Formato/nombre"/></titulo>
				<contenido>
				<div class="box-container">	
					<variable id="id_carga" valor="{//id_carga_seleccionada}" />
					
					<bloque-contenido>
						<titulo>Informacion general</titulo>
						<contenido>
							
							<parrafo><br/><texto key="INFORMACION ASOCIADA A LA CARGA" /><br/></parrafo>
								
								<!-- INFO CARGA -->
								<tabla>
									<encabezado>
										<titulo>Tipo</titulo>
										<titulo>Identificador <texto key="CARGA" /></titulo>
										<titulo>Nombre</titulo>
										<titulo>Fecha de <texto key="CARGA" /></titulo>
										<titulo>Estado</titulo>
										<titulo>N° registros</titulo>
										<xsl:if test="//obtenerCarga/Carga/valor_total_bigdecimal!=''">
											<titulo>Valor</titulo>
										</xsl:if>
										
									</encabezado>
									<xsl:for-each select="//obtenerCarga/Carga">
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
												<b>
													<estado key="{estado}" cliente="S"/>
												</b>
											</valor>
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
							</contenido>
					</bloque-contenido>
								
					<!-- ARCHIVOS ADJUNTOS -->
				
					<xsl:call-template name="archivos_adjuntos">
						<xsl:with-param name="permiteAdjuntar">true</xsl:with-param>
						<xsl:with-param name="permiteEliminar">true</xsl:with-param>
					</xsl:call-template>
								
								
					<bloque-contenido>
					<titulo>Envío de archivos</titulo>
					<contenido>
						<!-- BOTON ENVIAR -->
						
						<div id="div_mensaje_usuario" style="display:none">
							<nota>
								Debe asociar por lo menos un archivo adjunto.
							</nota>
						</div>
						
						<div id="div_btn_enviar_carga" style="display:none">
							<parrafo>Los archivos adjuntos han sido asociados de forma correcta.</parrafo>	
							
							<area_botones>
								<boton estilo="primary volver" id="btn_enviar_carga"  destino="carga_informacion/completar_carga/30.3.do">Terminar</boton>
							</area_botones>
						</div>
					</contenido>
					</bloque-contenido>
					
				</div>	
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
