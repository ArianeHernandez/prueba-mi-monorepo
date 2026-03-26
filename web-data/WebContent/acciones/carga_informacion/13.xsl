<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Carga de Información">
			
			<javascript>carga_informacion/13.js</javascript>
			<principal>
				<titulo>Carga de Información</titulo>
				<contenido>
				<div class="box-container">	
					
					<xsl:choose>
						<xsl:when test="count(//obtenerFormatosClienteActivo/listado/Formato)>0">
							 	<tabla icono="service">
									<encabezado>
										<titulo>Servicio</titulo>
										<titulo>Descripción</titulo>
									</encabezado>
									<xsl:for-each select="//obtenerFormatosClienteActivo/listado/Formato">
										<fila accion="enviar('{id_formato}');">
											<valor><xsl:value-of select="nombre"/></valor>
											<valor><xsl:value-of select="descripcion"/></valor>
										</fila>
									</xsl:for-each>
								</tabla>
							<formulario id="form_formato" destino="carga_informacion/1a.do">
									<variable id="id_formato" />
							</formulario>
								
							<formulario destino="carga_informacion/13.do" id="form_pag">
								<paginacion id="_numeropagina" formulario="form_pag" numero="{numeroPagina}" paginacion="{//TAMANO_PAGINA}" total="{//totalFormatosPersonaClienteActivo/total}"/>
							</formulario>
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen formatos asociados a su usuario.
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
