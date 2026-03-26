<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://componentes/notas_carga/notas_carga.xsl" />
	<xsl:include href="context://stylesheets/templates/DetalleCarga.xsl"/>
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Consulta de Informacion">
			
			<principal>
				<titulo>Consultar Información - Servicio <xsl:value-of select="obtenerFormato/Formato/nombre"/> - <texto key="TIPO REGISTRO SELECCIONADO" /> </titulo>
				<contenido>
				<div class="box-container">
					
					<bloque-contenido>
						<titulo><texto key="CARGAS REALIZADAS"/></titulo>
						<contenido>
							
							<xsl:choose>
								<xsl:when test="count(//obtenerCarga/Carga)>0">
									
									<formulario id="form_verCarga" destino="consulta_informacion/2.2.do">
										<variable id="id_carga" valor=""/>
									</formulario>
									
									<tabla>
										<encabezado>
											<titulo>Tipo de <texto key="CARGA" /></titulo>
											<titulo>Identificador <texto key="CARGA" /></titulo>
											<titulo>Nombre</titulo>
											<titulo>Estado</titulo>
											
											<!-- VALOR OPCIONAL -->
												<xsl:if test="//obtenerCarga/Carga/valor_total_bigdecimal!=''">
													<titulo>Valor</titulo>
												</xsl:if>
											<titulo>N° registros</titulo>		
											
										</encabezado>
										
										<xsl:for-each select="//obtenerCarga/Carga">
											<fila>
												<valor>
													<tipo_carga id="{id_tipocarga}"/>
												</valor>
												<valor><xsl:value-of select="id_carga"/></valor>
												<valor><xsl:value-of select="nombre"/></valor>
												<valor>
													<b>
														<estado key="{estado}" cliente="S"/>
													</b>
												</valor>
												
												<!-- VALOR OPCIONAL -->
												<xsl:if test="valor_total_bigdecimal!=''">
													<valor>
														$<xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
													</valor>
												</xsl:if>
												<valor>
													<xsl:value-of select="numero_registros_bigdecimal" />
												</valor>	
												
											</fila>
											
										</xsl:for-each>
										
									</tabla>
								</xsl:when>
							</xsl:choose>
							
						</contenido>
					</bloque-contenido>
					
					<xsl:call-template name="DETALLE_CARGA_AJAX" >
						<xsl:with-param name="validacionesActivas">true</xsl:with-param>
					</xsl:call-template>
						
					<!-- NOTAS CARGA -->
					
					<xsl:call-template name="notas_carga">
						<xsl:with-param name="permiteAgregar">false</xsl:with-param>
					</xsl:call-template>
										
					
					<area_botones>
						<xsl:for-each select="//obtenerFormatosImpresion/FormatoImpresion">
							<xsl:if test="count(String)>0">
								<boton estilo="avanzar" destino="consulta_informacion/2.3/Datasuite:reportes:F{//obtenerCarga/Carga/id_formato}:{String}.do_epdf">
									<xsl:value-of select="String"/>
								</boton>
							</xsl:if>
						</xsl:for-each>
						<boton estilo="primary volver" destino="consulta_informacion/14.1.do">Volver</boton>
					</area_botones>
				
				</div>	
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
