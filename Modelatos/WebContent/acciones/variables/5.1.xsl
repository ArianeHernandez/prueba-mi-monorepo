<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Administración Variables">
			<javascript>admin/5.1.js</javascript>
			
			<principal>
				<titulo icono="variable">Administración de Variables</titulo>
				<contenido>
					<xsl:choose>
						<xsl:when test="count(//obtenerVariablesPorModelo/listado/Variable)>0">
						
							<formulario destino="variables/5.2.do" id="form_variable">
								<variable id="id_variable" valor=""/>
							 	<tabla icono="property">
									<encabezado>
										<titulo>Nombre</titulo>
										<titulo>Descripción</titulo>
										<titulo></titulo>
									</encabezado>
									
							 		<xsl:for-each select="//obtenerVariablesPorModelo/listado/Variable[not(number(id_variable)>0)]">
										<xsl:sort select="nombre_variable"/>
										<fila>
											<valor><b><xsl:value-of select="nombre_variable"/></b></valor>
											<valor><xsl:value-of select="descripcion"/></valor>
											<valor></valor>
										</fila>
									</xsl:for-each>
							 		
							 		<xsl:for-each select="//obtenerVariablesPorModelo/listado/Variable[number(id_variable)>0]">
										<xsl:sort select="nombre_variable"/>
										<fila>
											<valor><xsl:value-of select="nombre_variable"/></valor>
											<valor><xsl:value-of select="descripcion"/></valor>
											<valor>
												<xsl:if test="number(id_variable)>0">
													<area_botones>
														<boton estilo="editar" accion="editarVariable({id_variable})">Editar</boton>
													</area_botones>
												</xsl:if>
											</valor>
										</fila>
									</xsl:for-each>
							 		
								</tabla>
							</formulario>
							
						</xsl:when>
						<xsl:otherwise>
							<parrafo estilo="resaltado">
								No existen Variables asociadas al Negocio.
							</parrafo>
						</xsl:otherwise>
						
					</xsl:choose>
					
					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
						<boton estilo="crear" destino="variables/5.2.do">Crear Nueva</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
