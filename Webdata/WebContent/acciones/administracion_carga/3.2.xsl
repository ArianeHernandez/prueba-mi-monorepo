<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Administración de Carga">
			
			<javascript>administracion_carga/3.2.js</javascript>
			
			
			<principal>
				<titulo>Consultar Información - Servicio <xsl:value-of select="obtenerFormato/Formato/nombre"/></titulo>
				<contenido>
				<div class="box-container">
					
					<bloque-contenido>
						<titulo><texto key="CARGAS REALIZADAS"/></titulo>
						<contenido>
							<parrafo><texto key="CARGAS REALIZADAS POR EL USUARIO" /></parrafo>
							
							<xsl:choose>
								<xsl:when test="count(//obtenerCargaPorFormatoEntrada/listado/Carga)>0">
									
									<formulario id="form_siguiente" destino="administracion_carga/3.3.do">
										<variable id="id_carga" valor=""/>
									</formulario>
									
									<tabla icono="box">
										<encabezado>
											<titulo>Fecha</titulo>
											<titulo>Tipo de <texto key="CARGA" /></titulo>
											<titulo>Identificador <texto key="CARGA" /></titulo>
											<titulo>Nombre</titulo>
											<titulo>Estado</titulo>
											<titulo>Valor</titulo>
											<titulo>N° registros</titulo>
										</encabezado>
										
										<xsl:for-each select="//obtenerCargaPorFormatoEntrada/listado/Carga">
											<xsl:sort data-type="number" select="id_carga"/>
											
											<fila accion="verDetalleCarga({id_carga})">
												
												<valor><xsl:value-of select="fecha_subida"/></valor>
												<valor>
													<tipo_carga id="{id_tipocarga}"/>
												</valor>
												<valor><xsl:value-of select="id_carga"/></valor>
												<valor><xsl:value-of select="nombre"/></valor>
												<valor>
													<parrafo estilo="resaltado">
													<estado key="{estado}" cliente="S"/>
												</parrafo>
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
						<boton estilo="primary volver" destino="administracion_carga/3.1.do">Volver</boton>
					</area_botones>
				</div>	
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
