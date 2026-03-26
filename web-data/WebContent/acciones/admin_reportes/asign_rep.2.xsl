<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/ListadoClientes.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
			
		<pagina titulo="Asignación de Reportes">
			
			<javascript>admin_reportes/asign_rep.2.js</javascript>
			<stylesheet>20.1.css</stylesheet>
						
			<principal>
				<titulo icono="formatos">Asignación de Reportes</titulo>
				<contenido>
					
					<formulario destino="admin_reportes/asign_rep.3.do" id="form_crear_asignacion">
						<variable id="Asignacion.id_asignacion" valor="{id_asignacion}" />
						<variable id="Asignacion.id_cliente" valor="" />
						<variable id="nuevo" valor="false" />
						
						<registro>
							<item>Nombre de la Asignación</item>
							<valor>
								<cajatexto id="Asignacion.titulo" />
							</valor>
						</registro>
						
						<registro>
							<item>Reporte</item>
							<valor>
								<cajatextoselector id="Asignacion.id_reporte">
									<xsl:for-each select="//reportesDB/reportesDB/ServicioReporteDim">
										<opcion valor="{id}"><xsl:value-of select="nombre"/></opcion>
									</xsl:for-each>
								</cajatextoselector>
							</valor>
						</registro>

						<registro>
							<item>Rol</item>
							<valor>
								<cajatextoselector id="Asignacion.id_rol" accion="rolCambiado()">
									
									<grupo_opcion titulo="Roles Administradores">
										<opcion valor="0" texto="Administrador Sistema Modelatos"/>
										<opcion valor="1" texto="Administrador Usuarios Webdata"/>
										<!--opcion valor="2" texto="Director de Negocio Webdata"/-->
									</grupo_opcion>
									
									<grupo_opcion titulo="Roles Usuario">	
										<opcion valor="3" texto="Administrador Cliente"/>
										<opcion valor="4" texto="Preparador"/>
										<opcion valor="5" texto="Revisor"/>
										<opcion valor="6" texto="Liberador"/>
										<opcion valor="7" texto="Cliente Natural"/>
									</grupo_opcion>
									
									<grupo_opcion titulo="Administrativos">
										<opcion valor="8" texto="Administrativo"/>
									</grupo_opcion>
									
								</cajatextoselector>
							</valor>
						</registro>
												
						<registro>
							<item>Activo</item>
							<valor>
								<cajachequeo id="Asignacion.activo" valor="S" accion="check()" seleccionado="true">
								</cajachequeo>
							</valor>
						</registro>
						
						<variable id="Asignacion.id_rol_auth" valor="" />
						<variable id="Asignacion.id_cliente" valor="" />
						
					</formulario>
					
						<registro id="registro_auth" visible="false">
							<item>Tipo de administrativo</item>
							<valor>
								<cajatextoselector id="id_rol_auth">
									<opcion valor="" texto="-- Todos --"/>
									<xsl:for-each select="//obtenerRolesActivos/Listado/Rol">
										<xsl:sort select="nombre_rol"/>
										<opcion valor="{id_rol}">
											<xsl:value-of select="nombre_rol"/>
										</opcion>
									</xsl:for-each>
								</cajatextoselector>
							</valor>
						</registro>
					
						<registro id="registro_cliente" visible="false">
							<item>Buscar</item>
							<valor>
								<xsl:call-template name="LISTADOCLIENTES">
								</xsl:call-template>
							</valor>
						</registro>
					
						<bloque id="asignarTodosClientes" visible="false">
							<area_botones>
								<boton estilo="danger" accion="desasignarCliente()">Asignar a Todos los Clientes</boton>
							</area_botones>
						</bloque>
								
				<area_botones>
					<boton estilo="primary" accion="validar()">Guardar</boton>
					<boton estilo="danger" destino="admin_reportes/asign_rep.1.do">Cancelar</boton>
				</area_botones>
							
				</contenido>
					
			</principal>

		</pagina>
		
	</xsl:template>
	
</xsl:stylesheet>
