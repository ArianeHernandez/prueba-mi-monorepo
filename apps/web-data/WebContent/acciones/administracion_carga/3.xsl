<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Administración de Carga">
			
			<javascript>administracion_carga/3.js</javascript>
			
			<principal>
				<titulo>Administración de <texto key="CARGA" /></titulo>
				<contenido>
				<div class="box-container">
					
					<xsl:choose>
						<xsl:when test="count(//obtenerFormatosSalida/listado/Formato)>0">
							 	<tabla icono="service">
									<encabezado>
										<titulo>ID</titulo>
										<titulo>Servicio</titulo>
										<titulo>Descripción</titulo>
										<titulo>Estado</titulo>
									</encabezado>
									<xsl:for-each select="//obtenerFormatosSalida/listado/Formato">
										<fila accion="verListadoPersona({id_formato})">
											<valor>L<xsl:value-of select="id_formato"/></valor>
											<valor><xsl:value-of select="nombre"/></valor>
											<valor><xsl:value-of select="descripcion"/></valor>
											<valor>
												<xsl:choose>
													<xsl:when test="activo='S'">Activo</xsl:when>
													<xsl:otherwise>Desactivado</xsl:otherwise>
												</xsl:choose>
											</valor>
											
										</fila>
									</xsl:for-each>
								</tabla>
							
							<formulario destino="administracion_carga/3.1.do" id="form_siguiente">
								<variable id="id_formato" valor=""/>
							</formulario>

							<!--formulario destino="administracion_carga/3.do" id="form_pag">
								<paginacion id="_numeropagina" formulario="form_pag" numero="{numeroPagina}" paginacion="{//TAMANO_PAGINA}" total="{//totalFormatosEntrada/total}"/>
							</formulario-->
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen servicios.
							</parrafo>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>
					
				</div>	
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
