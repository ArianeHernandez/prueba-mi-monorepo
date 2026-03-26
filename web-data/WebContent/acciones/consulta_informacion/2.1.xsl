<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Consulta de Informacion">
			
			<javascript>webdata/2.1.js</javascript>
			
			<principal>
				<titulo>Consultar Información - Servicio <xsl:value-of select="obtenerFormato/Formato/nombre"/></titulo>
				<contenido>
				<div class="box-container">
					
					<bloque-contenido>
						<titulo>solicitudes realizadas</titulo>
						<contenido>
							<parrafo><texto key="LISTADO DE CARGAS APROBADAS" /></parrafo>
							
							<xsl:choose>
								<xsl:when test="count(//obtenerCargaPorFormatoEntrada/listado/Carga)>0">
									
									<formulario id="form_verCarga" destino="consulta_informacion/2.2.do">
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
										
										<xsl:for-each select="//obtenerCargaPorFormatoEntrada/listado/Carga">
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
									<parrafo estilo="resaltado"><texto key="NO HAY CARGAS REGISTRADAS" /></parrafo>
								</xsl:otherwise>
							</xsl:choose>
							
						</contenido>
					</bloque-contenido>
					
					<area_botones>
						<boton estilo="primary volver" destino="consulta_informacion/2.do">Volver</boton>
					</area_botones>
				</div>	
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
