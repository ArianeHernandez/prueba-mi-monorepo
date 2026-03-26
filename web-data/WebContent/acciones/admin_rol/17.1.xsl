<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Bienvenido">
			<javascript>admin_rol/17.1.js</javascript>
			
			<principal>
				<titulo icono="inicio">Administración de roles</titulo>
				<contenido>
					<div class="box-container">
					
							<div class="row-btn">
								<boton estilo="primary" destino="admin_rol/17.2.do"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Crear Rol</boton>
							</div>
					
							<div class="form-horizontal">
							
							
								<xsl:choose>
									<xsl:when test="count(//obtenerTodosLosRoles/Listado/Rol)>0">
								
										<formulario destino="admin_rol/17.2.do" id="form_roles">
											<variable id="id_rol" valor="" />
										</formulario>
										
										<tabla>
											<encabezado>
												<titulo>Nombre de Rol</titulo>
												<titulo>Descripción</titulo>
												<titulo>Estado</titulo>
												<titulo>Rol Padre</titulo>
											</encabezado>
											
											<xsl:for-each select="//obtenerTodosLosRoles/Listado/Rol">
												<xsl:sort select="nombre_rol"/>
												<xsl:variable name="idrol" select="id_rol" />
						
												<fila>
													
													<valor accion="seleccionarRol({id_rol})">
															<b><xsl:value-of select="nombre_rol" /></b>
													</valor>
													
													<valor accion="seleccionarRol({id_rol})">
															<xsl:value-of select="descripcion" />
													</valor>
													
													
													<valor accion="seleccionarRol({id_rol})">
														<xsl:choose>
															<xsl:when test="activo='S'">Activo</xsl:when>
															<xsl:otherwise>Inactivo</xsl:otherwise>
														</xsl:choose>
													</valor>
													
													<valor accion="seleccionarRol({id_rol})">
															<xsl:value-of select="nombre_rol_padre" />
															
													</valor>
													
						
												</fila>
											</xsl:for-each>
										</tabla>
										
										
									</xsl:when>
								</xsl:choose>
					
							</div>
							
						</div>
				</contenido>
			</principal>
			
		</pagina>

	</xsl:template>

</xsl:stylesheet>
