<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		<javascript>superadmin/9.2.js</javascript>
		
		<pagina titulo="Administradores Negocio '{//obtenerNegocio/Negocio/nombre}'">
			
			<principal>
				<titulo icono="usuarios">Administradores Negocio '<xsl:value-of select="//obtenerNegocio/Negocio/nombre"/>'</titulo>
				<contenido>
					
					<bloque-contenido>
						<titulo icono="edicion">Administradores Activos</titulo>
						<contenido>
					
						<xsl:choose>
							<xsl:when test="count(//obtenerAdministradoresPorNegocio/listado/Administrador[activo='S'])>0">
							
							 	<tabla>
									<encabezado>
										<titulo>login</titulo>
										<titulo>identificacion</titulo>
										<titulo>nombre</titulo>
										<titulo>correo</titulo>
										<titulo>Accion</titulo>
									</encabezado>
										<xsl:for-each select="//obtenerAdministradoresPorNegocio/listado/Administrador[activo='S']">
											<xsl:sort select="persona/nombreCompleto"/>
												<fila>
													<valor><xsl:value-of select="login"/></valor>
													<valor><xsl:value-of select="persona/identificacion"/></valor>
													<valor><xsl:value-of select="persona/nombreCompleto"/></valor>
													<valor><xsl:value-of select="persona/correo"/></valor>

													<valor>
														<area_botones>
															<boton estilo="guardar" accion="desactivarAdministrador({id_administrador})">Desactivar</boton>
														</area_botones>
													</valor>
												</fila>
											
											
										</xsl:for-each>
									</tabla>
									
								</xsl:when>
								<xsl:otherwise>
									<parrafo estilo="resaltado">
										No existen administradores activos.
									</parrafo>
								</xsl:otherwise>
								
							</xsl:choose>
						
						</contenido>
					
					</bloque-contenido>
					
					
					<!-- - - - - - - - - - - - - - - -->
					
					<bloque-contenido>
						<titulo icono="edicion">Administradores Inactivos</titulo>
						<contenido>
					
						<xsl:choose>
							<xsl:when test="count(//obtenerAdministradoresPorNegocio/listado/Administrador[activo='N'])>0">
							
							 	<tabla>
									<encabezado>
										<titulo>login</titulo>
										<titulo>identificacion</titulo>
										<titulo>nombre</titulo>
										<titulo>correo</titulo>
										<titulo>Accion</titulo>
									</encabezado>
										<xsl:for-each select="//obtenerAdministradoresPorNegocio/listado/Administrador[activo='N']">
											<xsl:sort select="activo" order="descending"/>
												<fila>
													<valor><xsl:value-of select="login"/></valor>
													<valor><xsl:value-of select="persona/identificacion"/></valor>
													<valor><xsl:value-of select="persona/nombreCompleto"/></valor>
													<valor><xsl:value-of select="persona/correo"/></valor>

													<valor>
														<area_botones>
															<boton estilo="guardar" accion="activarAdministrador({id_administrador})">Activar</boton>
														</area_botones>
													</valor>
												</fila>
										</xsl:for-each>
									</tabla>
									
								</xsl:when>
								<xsl:otherwise>
									<parrafo estilo="resaltado">
										No existen administradores inactivos.
									</parrafo>
								</xsl:otherwise>
								
							</xsl:choose>
						
						</contenido>
					
					</bloque-contenido>
					
					<formulario destino="negociosvigentes/9.2.do" id="form_accion">
						<variable id="activo" valor=""/>
						<variable id="id_administrador" valor=""/>
					</formulario>
					
					<area_botones>
						<boton estilo="volver" destino="negociosvigentes/9.1.do">Volver</boton>
					</area_botones>
				</contenido>
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
