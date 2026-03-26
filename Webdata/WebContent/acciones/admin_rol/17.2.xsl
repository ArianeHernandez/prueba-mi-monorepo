<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/horario.xsl"/>
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">

		<javascript>admin_rol/17.2.js</javascript>
		<pagina titulo="Edición de Formato de Entrada">
			
			<principal>
				<titulo icono="formatos">Edición de roles</titulo>
				
				<contenido>
					<div class="box-container">
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
							<formulario id="form_edicion" destino="admin_rol/17.3.do">
								<variable id="Rol.id_rol" valor="{//obtenerRol/Rol/id_rol}"/>
									
										<registro>
											<item>Nombre del Rol</item>
											<valor>
												<cajatexto id="Rol.nombre_rol" valor="{//obtenerRol/Rol/nombre_rol}" />
											</valor>
										</registro>
										
										<registro>
											<item>Descripción</item>
											<valor>
												<areatexto id="Rol.descripcion" valor="{//obtenerRol/Rol/descripcion}" />
											</valor>
										</registro>
										
										<registro>
											<item>Rol Padre <xsl:value-of select="//OSM-INIT-SESSION/Info/Usuario/id_usuario"/></item>
											<valor>
												<xsl:variable name="id_usuario" select="//OSM-INIT-SESSION/Info/Usuario/id_usuario"/>
												<xsl:variable name="rol" select="//obtenerRol/Rol/id_rol"></xsl:variable>
											
												<cajatextoselector id="Rol.id_rol_padre" valor="{//obtenerRol/Rol/id_rol_padre}">
													<opcion valor="" texto="-- Seleccione --"/>
													<xsl:for-each select="//obtenerRolesActivos/Listado/Rol[(id_rol!=$rol or count(//obtenerRol/Rol/id_rol)=0) and (id_usuario = $id_usuario or count($id_usuario)=0 and count(id_usuario) = 0 )]">
														<xsl:sort select="nombre_rol"/>
														<opcion valor="{id_rol}" texto="{nombre_rol}" />
													</xsl:for-each>
												</cajatextoselector>
											</valor>
										</registro>		
								
										<registro>
											<item>Activo</item>
											<valor>
												<cajachequeo id="Rol.activo" valor="S" seleccionado="{boolean(//obtenerRol/Rol/activo='S')}"/>
											</valor>
										</registro>
									
							</formulario>
							
							<formulario id="form_eliminar" destino="admin_rol/17.4.do">
								<variable id="Rol.id_rol" valor="{//obtenerRol/Rol/id_rol}"/>
								<variable id="personas_por_rol" valor="{//totalPersonasPorRol/total}"/>
								
							</formulario>
							</div>	
						</div>
					<div class="panel-footer">
						<boton estilo="volver" destino="admin_rol/17.1.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"/>&#160;Cancelar</boton>
						<xsl:if test="(//obtenerRol/Rol/id_rol) != 0">
							<boton estilo="eliminar" formulario="form_eliminar" validacion="page_validarEliminar()"><i class="fa fa-times" aria-hidden="true"/>&#160;Eliminar</boton>
						</xsl:if>
						<boton estilo="guardar" formulario="form_edicion" validacion="page_validarGuardar()"><i class="fa fa-floppy-o" aria-hidden="true"/>&#160;Guardar Rol</boton>
																			
					</div>
					
					</div>
					</div>
				</contenido>
					
			</principal>
			<xsl:call-template name="HORARIO_VENTANA" />
		</pagina>
		
	</xsl:template>
	

</xsl:stylesheet>
