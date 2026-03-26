<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/EditarPersona.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		<xsl:variable name="editar" select="//editar" />
		<xsl:variable name="contenido"
			select="//obtenerContenidosByURL/listado/Contenido" />
		<xsl:variable name="Administrativo" select="//obtenerAdministrativoPorID/Administrativo"/>

		<pagina titulo="Crear {$contenido[etiqueta='Administrativo']/texto}">

			<stylesheet>16.css</stylesheet>
			<javascript>administrativo/16.2.js</javascript>
			<principal>
				<titulo>
					Crear
					<texto key="Administrativo" />
				</titulo>
				<contenido>
				<div class="box-container">
				
					<formulario id="form_usuario" destino="administrativo/16.3.do">
						<xsl:choose>

							<xsl:when
								test="(count(//PERSONAROL/obtenerPersonaRol/Objeto) = 0) or $editar = 'SI'">
								<xsl:call-template name="EDITARPERSONA">
									<xsl:with-param name="Persona"
										select="//INFOPERSONA/obtenerPersonaPorIdentificacion/Persona" />
									<xsl:with-param name="TipoPersona" select="//DATOS/TIPOPERSONA" />
									<xsl:with-param name="TipoDocumento"
										select="//DATOS//TIPODOCUMENTO/obtenerTipoDocumento/TipoDocumento" />
									<xsl:with-param name="Identificacion" select="//DATOS/IDENTIFICACION" />
									<xsl:with-param name="editar" select="$editar" />
									<xsl:with-param name="Credencial"
										select="//CREDENCIAL/obtenerCredencialPersonaID/Credencial" />
									<xsl:with-param name="rol">
										ADV
									</xsl:with-param>
									<xsl:with-param name="LoginPorIdentificacion"
										select="//LOGIN/esLoginPorIdentificacion/Boolean" />
								</xsl:call-template>
								<bloque-contenido>
									<titulo icono="edicion">Roles</titulo>
									<contenido>
											<div id="div_roles">
												<xsl:for-each select="//obtenerRolesAdministrativo/Listado/Rol">
													<div class="cuadro_fila" id="fila_{id_rol}" name="fila_rol">
														<div class="cuadro_fila_item w20">
															<div class="eliminar" onclick="eliminarRol({id_rol})"></div>
														</div>
														<div class="cuadro_fila_item">
															<p>
																<xsl:value-of select="nombre_rol" />
															</p>
														</div>
														<input type="hidden" name="roles:[{position()}]" id="roles:[{position()}]"
															value="{id_rol}" />
													</div>
												</xsl:for-each>
											</div>
											<div class="cuadro_info_footer">
												
												<xsl:variable name="id_usuario" select="//OSM-INIT-SESSION/Info/Usuario/id_usuario"/>
											
												<cajatextoselector id="roles" accion="agregarRol(this);">
													<opcion valor="" texto="-- Seleccione el rol a agregar--" />
													<xsl:for-each select="//obtenerRolesActivos/Listado/Rol[id_usuario = $id_usuario or count($id_usuario)=0 and count(id_usuario) = 0]">
														<xsl:variable name="texto">
															<xsl:value-of select="nombre_rol" />
															<xsl:if test="string-length(normalize-space(descripcion)) > 0"> - <xsl:value-of select="descripcion"></xsl:value-of>
															</xsl:if>
														</xsl:variable>
														<opcion valor="{id_rol}" texto="{$texto}" />
													</xsl:for-each>
												</cajatextoselector>
											</div>
										<variable id="n_roles" valor="{count(//obtenerRolesAdministrativo/Listado/Rol)}" />
									</contenido>
								</bloque-contenido>

								<area_botones>
									<boton estilo="primary volver" destino="administrativo/16.1.do">Volver</boton>
									<boton estilo="primary guardar" validacion="validarPersona() || !validar_roles()"
										formulario="form_usuario"><i class="fa fa-floppy-o" aria-hidden="true"/> Guardar</boton>
								</area_botones>
							</xsl:when>
							<xsl:otherwise>
								<bloque-contenido>
									<titulo icono="edicion">Error</titulo>
									<contenido>
										<parrafo>
											El usuario ya existe para este negocio!
									</parrafo>
									</contenido>
								</bloque-contenido>
								<area_botones>
									<boton estilo="primary volver" destino="administrativo/16.3.do">Volver</boton>
								</area_botones>
							</xsl:otherwise>
						</xsl:choose>
					</formulario>
					<div id="rol_plantilla" style="display:none">
						<div class="cuadro_fila" id="fila_[ 1 ]" name="fila_rol" >
							<div class="cuadro_fila_item w20">
								<div class="eliminar" onclick="eliminarRol([ 1 ])"></div>
							</div>
							<div class="cuadro_fila_item">
								<p>
									[ 2 ]
								</p>
							</div>
							<input type="hidden" name="roles:[[ 3 ]]" id="roles:[[ 3 ]]"
								value="[ 1 ]" />
						</div>
					</div>
					<xsl:for-each select="//obtenerRolesActivos/Listado/Rol">
						<variable id="nombre_{id_rol}" valor="{nombre_rol}"/>
						<variable id="descrip_{id_rol}" valor="{descripcion}"/>
					</xsl:for-each>
					
				</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
