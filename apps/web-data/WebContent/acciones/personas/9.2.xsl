<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/EditarPersona.xsl" />	

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<xsl:variable name="editar" select="//editar"/>	
		<xsl:variable name="obtenerTiposProcesosPorIdUsuario" select="//TIPOPROCESO/obtenerTiposProcesosPorIdUsuario/listadoTipoProceso"/>
		<xsl:variable name="obtenerTipoProcesoRol" select="//TIPOPROCESOROL/obtenerTipoProcesoRol/listadoTipoProcesoRol"/>
		<xsl:variable name="obtenerTiposRevisor" select="//TIPOREVISOR/obtenerTiposRevisor/Listado"/>
		
		<xsl:variable name="Titulo">			
			<xsl:choose>
				<xsl:when test="rol_actual='REV'">Revisor</xsl:when>
				<xsl:when test="rol_actual='PRE'">Preparador</xsl:when>
				<xsl:when test="rol_actual='LIB'">Liberador</xsl:when>
			</xsl:choose>
		</xsl:variable>
		<pagina titulo="{$Titulo}">
			
			<javascript>personas/9.2.js</javascript>
		    <stylesheet>personas/9.2.css</stylesheet>
			
			<principal>
				<titulo>Crear <xsl:value-of select="$Titulo" /></titulo>
				<contenido>
					<variable id="nombre_rol" valor="{$Titulo}" />

					<xsl:choose>
							<xsl:when  test="count(//PERSONAUSUARIO/obtenerPersonaUsuario/Objeto) = 0 or $editar = 'SI'">
							<formulario id="form_usuario" destino="personas/9.3.do">
								<xsl:call-template name="EDITARPERSONA">
									<xsl:with-param name="Persona" select="//INFOPERSONA/obtenerPersonaPorIdentificacion/Persona"/>
									<xsl:with-param name="TipoPersona" select="//DATOS/TIPOPERSONA"/>
									<xsl:with-param name="Identificacion" select="//DATOS/IDENTIFICACION"/>
									<xsl:with-param name="editar" select="$editar"/>
									<xsl:with-param name="Credencial" select="//CREDENCIAL/obtenerCredencialPersonaIDUsuario/Credencial"/>
									<xsl:with-param name="rol" select="rol_actual"/>	
									<xsl:with-param name="TipoDocumento" select="//DATOS//TIPODOCUMENTO/obtenerTipoDocumento/TipoDocumento"/>
									<xsl:with-param name="LoginPorIdentificacion" select="//LOGIN/esLoginPorIdentificacion/Boolean"/>
									
								</xsl:call-template>
								
								<div style="display: none;">
									<bloque-contenido>
										<contenido>
											<xsl:for-each select="$obtenerTiposProcesosPorIdUsuario/TipoProceso">
												<xsl:variable name="id_tipo_proceso" select="id_tipo_proceso" />
												<registro>
													<item><xsl:value-of select="nombre"/></item>
														<valor>
															<variable id="TipoProcesoRol:{id_tipo_proceso}.rol" valor="{//rol_actual}" />
															<cajachequeo2 class="tipo_proceso" id="TipoProcesoRol:{id_tipo_proceso}.id_tipo_proceso" valor="{id_tipo_proceso}" valor2="" desactivado="{count($obtenerTiposProcesosPorIdUsuario/TipoProceso) = 1}" seleccionado="{count($obtenerTiposProcesosPorIdUsuario/TipoProceso) = 1 or $id_tipo_proceso=$obtenerTipoProcesoRol/TipoProcesoRol/id_tipo_proceso}" validacion="validacionDesactivarTipoProceso({id_tipo_proceso})" accion="cambiarEstadoTipoProceso({id_tipo_proceso})" />
														</valor>
												</registro>
		            						</xsl:for-each>      					
										</contenido>
									</bloque-contenido>
									
								</div>

								<xsl:if test="rol_actual='REV'">
										<bloque-contenido>
											<titulo icono="edicion">Tipo <xsl:value-of select="$Titulo"/></titulo>
											<contenido>
												<registro>
													<item>Tipo Revisor *:</item>
													<valor>
														<cajatextoselector class="form-control" id="id_tipo_revisor" accion="" valor="">
															<opcion valor="" texto="-- Seleccione --"/>
															<xsl:for-each select="$obtenerTiposRevisor/TipoRevisor">
																<xsl:sort select="*/@name"/>
																<opcion valor="{id_tipo_revisor}" texto="{nombre}" />
															</xsl:for-each>
														</cajatextoselector>
														<div style="display: none;float: right;margin-bottom: 8px;" class="text-danger col-md-7 Normal-1 form-label" id="mensajetiporevisor"></div>
													</valor>
												</registro>
											</contenido>
										</bloque-contenido>
										
								</xsl:if>
								
								<xsl:if test="rol_actual='LIB'">
									<bloque-contenido>
										<titulo icono="edicion">Información Liberador</titulo>
										<contenido>
											<xsl:for-each select="$obtenerTiposProcesosPorIdUsuario/TipoProceso">
												<xsl:variable name="id_tipo_proceso" select="id_tipo_proceso" />
												
												<xsl:variable name="liberadorTipoProceso" select="//LIBERADORUSUARIO/obtenerLiberadorPorIdentificacion/Liberador/listadoLiberadorTipoProceso/LiberadorTipoProceso[id_tipo_proceso=$id_tipo_proceso]"/>
												
												<variable id="ListadoTipoProcesoLiberador:{$id_tipo_proceso}.id_tipo_proceso" valor="{$id_tipo_proceso}"/>
												
												<div id="info_lib_{id_tipo_proceso}">
													<xsl:if test="not(count($obtenerTiposProcesosPorIdUsuario/TipoProceso) = 1 or $id_tipo_proceso=$obtenerTipoProcesoRol/TipoProcesoRol/id_tipo_proceso)">
														<xsl:attribute name="style">display:none</xsl:attribute>
													</xsl:if>
													
													<registro>
														<item>Peso<xsl:if test="count($obtenerTiposProcesosPorIdUsuario/TipoProceso) > 1"> <xsl:value-of select="nombre"/></xsl:if>:</item>
															<valor>
																<cajatextoselector id="ListadoTipoProcesoLiberador:{$id_tipo_proceso}.peso" valor="{$liberadorTipoProceso/peso}" accion="probarNuevoPesoLiberador('{$liberadorTipoProceso/id_liberador}', {$id_tipo_proceso})">														
																	<opcion valor="1">1</opcion>
																	<opcion valor="2">2</opcion>
																	<opcion valor="3">3</opcion>
																	<opcion valor="4">4</opcion>
																	<opcion valor="5">5</opcion>
																	<opcion valor="6">6</opcion>
																	<opcion valor="7">7</opcion>
																	<opcion valor="8">8</opcion>
																	<opcion valor="9">9</opcion>
																	<opcion valor="10" seleccionado="{string-length($liberadorTipoProceso/peso)=0}">10</opcion>
																</cajatextoselector>
															</valor>
														</registro>
													
													<xsl:if test="valor_maximo_liberador = 'S'">
														<registro>
															<item>Valor máximo individual<xsl:if test="count($obtenerTiposProcesosPorIdUsuario/TipoProceso) > 1"> ( <xsl:value-of select="nombre"/> )</xsl:if>:</item>
															<valor>
																<div id="div_sinformato_double_ListadoTipoProcesoLiberador:{$id_tipo_proceso}.valor_max_individual" >
																	<cajatexto class="liberadorTipoProceso_valor_max_individual" valor="{$liberadorTipoProceso/valor_max_individual}" id="ListadoTipoProcesoLiberador:{$id_tipo_proceso}.valor_max_individual" onblur="mostrarDoubleConFormato('ListadoTipoProcesoLiberador:{$id_tipo_proceso}.valor_max_individual')"  /> 
																	(###)																
																</div>
																<div valor="asd" class="con_formato" id="div_formato_double_ListadoTipoProcesoLiberador:{$id_tipo_proceso}.valor_max_individual" onclick = "mostrarDoubleSinFormato('ListadoTipoProcesoLiberador:{$id_tipo_proceso}.valor_max_individual')" style="display:none">															 														
																</div>
															</valor>
														</registro>
														
														<registro>
															<item>Valor máximo lote<xsl:if test="count($obtenerTiposProcesosPorIdUsuario/TipoProceso) > 1">( <xsl:value-of select="nombre"/> )</xsl:if>:</item>
															<valor>
																<div id="div_sinformato_double_ListadoTipoProcesoLiberador:{$id_tipo_proceso}.valor_max_carga" >
																	<cajatexto class="liberadorTipoProceso_valor_max_carga" valor="{$liberadorTipoProceso/valor_max_carga}" id="ListadoTipoProcesoLiberador:{$id_tipo_proceso}.valor_max_carga" onblur="mostrarDoubleConFormato('ListadoTipoProcesoLiberador:{$id_tipo_proceso}.valor_max_carga')" />
																	(###)																
																</div>
																<div  class="con_formato" id="div_formato_double_ListadoTipoProcesoLiberador:{$id_tipo_proceso}.valor_max_carga" onclick = "mostrarDoubleSinFormato('ListadoTipoProcesoLiberador:{$id_tipo_proceso}.valor_max_carga')" style="display:none" >															 																								
																</div>
															</valor>
														</registro>
													</xsl:if>
													
												</div>
												
										</xsl:for-each>
										</contenido>
									</bloque-contenido>
								</xsl:if>
								
							</formulario>
							
							<area_botones>
								<boton estilo="primary volver" destino="personas/9.1.do">Volver</boton>
								<boton estilo="primary guardar" validacion="validarPersona()" formulario="form_usuario">Guardar</boton>
							</area_botones>
						</xsl:when>
						<xsl:otherwise>
							<bloque-contenido>
								<titulo icono="edicion">Error</titulo>
								<contenido>
									<parrafo>
										El <xsl:value-of select="$Titulo" /> ya existe para este cliente!
									</parrafo>
								</contenido>
							</bloque-contenido>
							<area_botones>
								<boton estilo="primary volver" destino="personas/9.1.do">Volver</boton>
							</area_botones>
						</xsl:otherwise>
					</xsl:choose>
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
