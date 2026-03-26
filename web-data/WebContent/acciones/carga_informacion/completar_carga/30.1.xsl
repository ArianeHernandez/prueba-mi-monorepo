<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Cargas que requieren archivos adjuntos">
			
			<javascript>carga_informacion/completar_carga/30.1.js</javascript>
			
			<principal>
				<titulo>Consultar Información</titulo>
				<contenido>
					<div class="box-container">	
					<div class="panel panel-default">
						<div class="panel-heading">Carga</div>
						<div class="panel-body">
							<div class=" form-horizontal">
							
								<xsl:choose>
									<xsl:when test="count(//obtenerCargasQueRequierenAdjuntosPorPersona/listado/Carga)>0">
										 		<formulario id="form_verCarga" destino="carga_informacion/completar_carga/30.2.do">
													<variable id="id_carga" valor=""/>
												</formulario>
												
												<tabla icono="box">
													<encabezado>
														<titulo>Fecha</titulo>
														<titulo>Tipo de <texto key="CARGA" /></titulo>
														<titulo>Identificador <texto key="CARGA" /></titulo>
														<titulo>Nombre</titulo>
														<titulo>Estado</titulo>
														<titulo>Nombre Cliente</titulo>
														<titulo>Valor</titulo>
														<titulo>N° registros</titulo>
													</encabezado>
													
													<xsl:for-each select="//obtenerCargasQueRequierenAdjuntosPorPersona/listado/Carga">
														<xsl:sort data-type="number" select="id_carga"/>
														
														<fila accion="verCarga({id_carga})">
															
															<valor><xsl:value-of select="fecha_subida"/></valor>
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
															<valor>
																<xsl:value-of select="concat(nombre_usuario,' ',apellido_usuario)" />
															</valor>
															<valor>
																<xsl:value-of select="format-number(valor_total_bigdecimal, '###.##0,00', 'pesos')"/>
															</valor>
															<valor>
																<xsl:value-of select="numero_registros_bigdecimal" />
															</valor>
														</fila>
													</xsl:for-each>
													
												</tabla>
										
									</xsl:when>
									<xsl:otherwise>
										<parrafo estilo="info">
											<i class="fa fa-info-circle" aria-hidden="true"></i> No existen <texto key="CARGAS" /> que requieran archivos adjuntos
										</parrafo>
									</xsl:otherwise>
									
								</xsl:choose>
							</div>
						</div>
						<div class="panel-footer">
							<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
						</div>
					</div>
				</div>	
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
