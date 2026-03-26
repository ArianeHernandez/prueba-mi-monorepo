<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Administración de Carga">
			
			<javascript>administracion_carga/3.1.js</javascript>
			
			<principal>
				<titulo><xsl:value-of select="//obtenerFormato/Formato/nombre"/> - Listado de Usuario</titulo>
				<contenido>
				<div class="box-container">
					<xsl:choose>
						<xsl:when test="count(//obtenerClientesFormato/listado/Persona)>0">
							 	<tabla icono="person">
									<encabezado>
										<titulo>Identificación</titulo>
										<titulo>Nombre</titulo>
										<titulo>Correo Electronico</titulo>
									</encabezado>
									<xsl:for-each select="//obtenerClientesFormato/listado/Persona">
										<fila accion="verCargasPersona({id_persona})">
											<valor><xsl:value-of select="identificacion"/></valor>
											<valor><xsl:value-of select="nombreCompleto"/></valor>
											<valor><xsl:value-of select="correo"/></valor>
										</fila>
									</xsl:for-each>
								</tabla>
							
							<formulario destino="administracion_carga/3.2.do" id="form_siguiente">
								<variable id="id_persona" valor=""/>
							</formulario>

							<!--formulario destino="administracion_carga/3.1.do" id="form_pag">
								<paginacion id="_numeropagina" formulario="form_pag" numero="{numeroPagina}" paginacion="{//TAMANO_PAGINA}" total="{//totalClientesFormato/total}"/>
							</formulario-->
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen Usuarios con <texto key="CARGAS" />.
							</parrafo>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="primary volver" destino="administracion_carga/3.do">Volver</boton>
					</area_botones>
					
				</div>	
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
