<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Consultar Información">
			
			<javascript>consulta_informacion/2.js</javascript>
			
			<principal>
				<titulo>Consultar Información</titulo>
				<contenido>
				<div class="box-container">
					
					<xsl:choose>
						<xsl:when test="count(//obtenerFormatosPorCliente/listado/Formato)>0">
							 	<tabla icono="service">
									<encabezado>
										<titulo>Servicio</titulo>
										<titulo>Descripción</titulo>
									</encabezado>
									<xsl:for-each select="//obtenerFormatosPorCliente/listado/Formato">
										<xsl:sort select="nombre"/>
										<fila accion="verListadoCargas({id_formato})">
											<valor><b><xsl:value-of select="nombre"/></b></valor>
											<valor><xsl:value-of select="descripcion"/></valor>
										</fila>
									</xsl:for-each>
								</tabla>
							
							<formulario destino="consulta_informacion/2.1.do" id="form_siguiente">
								<variable id="id_formato" valor=""/>
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
