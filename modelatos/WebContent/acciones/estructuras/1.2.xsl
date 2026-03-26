<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Estructuras">
			<javascript>admin/1.2.js</javascript>
			
			<principal>
				<titulo icono="estructura">Edición de Estructuras</titulo>
				<contenido>
					
					<variable id="id_modelo_activo" valor="{//OSM-INIT-SESSION/Info/Negocio/id_modelo}"/>
					
					<formulario id="form_edicion" destino="estructuras/1.3.do">
						<variable id="Accion" valor="GuardarEstructura"/>
						
						<variable id="Estructura.id_estructura" valor="{obtenerEstructura/Estructura/id_estructura}"/>
						<variable id="Estructura.id_modelo" valor="{obtenerEstructura/Estructura/id_modelo}"/>
						
						
						<bloque-pestanas>
					
							<pestana titulo="Información Básica">
						
								<registro>
									<item>Nombre de la estructura</item>
									<valor>
										<cajatexto estilo="mayusculas" id="Estructura.nombre" valor="{obtenerEstructura/Estructura/nombre}" maxlength='25' desactivado="{obtenerEstructura/Estructura/bloqueo_edicion = 'S'}"/>
									</valor>
								</registro>
								
								<registro>
									<item>Descripción</item>
									<valor>
										<areatexto id="Estructura.descripcion" valor="{obtenerEstructura/Estructura/descripcion}" desactivado="{obtenerEstructura/Estructura/bloqueo_edicion = 'S'}"/>
									</valor>
								</registro>
							
							</pestana>
							
							<pestana titulo="Campos de la Estructura">
							
									<xsl:for-each select="num_elemento">
										<xsl:variable name="pp" select="position()"/>
										
										<bloque id="BloqueCampo_{position()}" visible="{boolean(not(count(//Campo[position()=$pp])=0))}">
											
											<variable id="bloqueCampoVisible_{position()}" valor="{boolean(not(count(//Campo[position()=$pp])=0))}"/>
											<xsl:variable name="tipo_campo" select="//Campo[position()=$pp]/id_tipocampo"/>
											
											<bloque estilo="grupo">
												
												<table style="width:100%">
												<tr>
												<td>
												
												<variable id="Campo:{position()}.orden" valor="{position()}"/>
												<variable id="Campo:{position()}.id_campo" valor="{//Campo[position()=$pp]/id_campo}"/>
												
												<registro>
													<item>Nombre</item>
													<valor>
														<cajatexto id="Campo:{position()}.nombre" valor="{//Campo[position()=$pp]/nombre}" maxlength='25' desactivado="{//Campo[position()=$pp]/bloqueo_edicion='S'}" />
													</valor>
												</registro>
												
												<registro>
													<item>Tipo</item>
													<valor>
														<cajatextoselector id="Campo:{position()}.tiposeleccionado" accion="page_validarTipoDato(this, {position()})">
	
															<opcion valor="" texto="-- Seleccione --"/>
																													
															<grupo_opcion titulo="Tipos Basicos">
																<xsl:variable name="tipc" select="//Campo[position()=$pp]/id_tipocampo"></xsl:variable>
																<xsl:for-each select="//TipoCampo[id_tipocampo>0]">
																	<xsl:sort select="nombre"/>
																	<opcion valor="tc_{id_tipocampo}" texto="&#160;&#160;&#160;&#160;&#160;&#160;{nombre}" seleccionado="{boolean(id_tipocampo=$tipc)}" desactivado="{boolean(count(//Campo[position()=$pp]/id_campo)>0 and not(id_tipocampo=$tipc))}" />
																</xsl:for-each>
															</grupo_opcion>
															
															<grupo_opcion titulo="Entidades Existentes">
																<xsl:variable name="estr" select="//Campo[position()=$pp]/id_estructurarelacionada"></xsl:variable>
																<xsl:for-each select="//obtenerEstructurasPorPersona/listado/Estructura">
																	<xsl:sort select="nombre"/>
																	<opcion valor="es_{id_estructura}" texto="&#160;&#160;&#160;&#160;&#160;&#160;{nombre}" seleccionado="{boolean(id_estructura=$estr)}" desactivado="{boolean(count(//Campo[position()=$pp]/id_campo)>0 and not(id_estructura=$estr))}"/>
																</xsl:for-each>
															</grupo_opcion>
															
															
															<xsl:if test="count(//obtenerListaValoresPorPersona/listado/ListaValores)>0">
																<grupo_opcion titulo="Listas de Valores">
																	<xsl:variable name="estr" select="//Campo[position()=$pp]/id_lista_valores"></xsl:variable>
																	<xsl:for-each select="//obtenerListaValoresPorPersona/listado/ListaValores">
																		<xsl:sort select="nombre"/>
																		<opcion valor="lv_{id_lista_valores}" texto="&#160;&#160;&#160;&#160;&#160;&#160;{nombre}" seleccionado="{boolean(id_lista_valores=$estr)}" desactivado="{boolean(count(//Campo[position()=$pp]/id_campo)>0 and not(id_lista_valores=$estr))}"/>
																	</xsl:for-each>
																</grupo_opcion>
															</xsl:if>
															
														</cajatextoselector>
													</valor>
												</registro>

												<bloque id="Campo.{position()}.tipo_archivo" visible="{boolean(count($tipo_campo)>0 and $tipo_campo=8)}">
													<registro visible="{boolean(count($tipo_campo)>0 and $tipo_campo=8)}">
														<item>Tipo de archivo</item>
														<valor>
															<cajatextoselector id="Campo:{position()}.id_tipo_archivo" valor="">
																<xsl:if test="count(//obtenerTiposArchivo/listado/TipoArchivo)>0">
																	<xsl:variable name="tipoarch" select="//Campo[position()=$pp]/id_tipo_archivo"></xsl:variable>
																	<opcion valor="" texto="-- Seleccione --"/>
																	<xsl:for-each select="//obtenerTiposArchivo/listado/TipoArchivo">
																		<xsl:sort select="nombre"/>
																		<opcion valor="{id_tipo_archivo}" texto="{nombre}" seleccionado="{boolean(id_tipo_archivo=$tipoarch)}" />
																	</xsl:for-each>
																</xsl:if>
															</cajatextoselector>
														</valor>
													</registro>
												</bloque>

												<registro>
													<item>Descripción</item>
													<valor>
														<areatexto id="Campo:{position()}.descripcion" valor="{//Campo[position()=$pp]/descripcion}" desactivado="{//Campo[position()=$pp]/bloqueo_edicion='S'}"/>
													</valor>													
												</registro>
													
												<registro>
													<item>Identificador</item>
													<valor>
														<cajachequeo id="Campo:{position()}.llaveprimaria" valor="S" seleccionado="{boolean(//Campo[position()=$pp]/llaveprimaria='S')}" />
													</valor>
												</registro>
												
												<bloque id="Campo:{position()}.visualizacion_Block" visible="true">
													<registro>
														<item>Visualización</item>
														<valor>
															<cajachequeo id="Campo:{position()}.visualizacion" valor="S" seleccionado="{boolean(//Campo[position()=$pp]/visualizacion='S')}" />
														</valor>
													</registro>
												</bloque>
												
												<bloque id="Campo:{position()}.info_adicional_Block" visible="true">
													<registro>
														<item>Información Adicional</item>
														<valor>
															<cajachequeo id="Campo:{position()}.info_adicional" valor="S" seleccionado="{boolean(//Campo[position()=$pp]/info_adicional='S')}" />
														</valor>
													</registro>
												</bloque>
													
												<ocultable visible="false" textovisible="ocultar" textooculto="opciones avanzadas" >
												
													<bloque id="Campo:{position()}.obligatorio_Block" visible="true">
														<registro>
															<item>Obligatorio</item>
															<valor>
																<cajachequeo id="Campo:{position()}.obligatorio" valor="S" seleccionado="{boolean(//Campo[position()=$pp]/obligatorio='S')}" />
															</valor>
														</registro>
													</bloque>
											
													<registro>
														<item>Unico</item>
														<valor>
															<cajachequeo id="Campo:{position()}.unico" valor="S" seleccionado="{boolean(//Campo[position()=$pp]/unico='S')}" />
														</valor>
													</registro>
												
													<registro>
														<item>Actualizable</item>
														<valor>
															<cajachequeo id="Campo:{position()}.actualizable" valor="S" seleccionado="{boolean(//Campo[position()=$pp]/actualizable='S')}" accion="page_cambioActualizable(this, 'Campo:{position()}')"/>
														</valor>
													</registro>
												
													<bloque id="Campo:{position()}.endPoint_Block" visible="{boolean(//Campo[position()=$pp]/actualizable='S')}">
														<registro>
															<item>Endpoint de Actualización</item>
															<valor>
																<cajatexto id="Campo:{position()}.endpoint" valor="{//Campo[position()=$pp]/endpoint}"/>
															</valor>
														</registro>
													</bloque>
													
													<registro>
															<item>Multiplicidad</item>
															<valor>
																<cajatextoselector id="Campo:{position()}.multiplicidad" valor="{//Campo[position()=$pp]/multiplicidad}" accion="page_cambioMultiplicidad(this, 'Campo:{position()}')">
																	<opcion valor="1...1" texto="Unico (1..1)" desactivado="{boolean(count(//Campo[position()=$pp]/id_campo)>0 and not(//Campo[position()=$pp]/multiplicidad='1...1'))}"/>
																	<opcion valor="1...N" texto="Multiple (1..N)" desactivado="{boolean(count(//Campo[position()=$pp]/id_campo)>0 and not(//Campo[position()=$pp]/multiplicidad='1...N'))}"/>
																</cajatextoselector>
															</valor>
													</registro>
													
													<registro>
														<item>Patron de Validación</item>
														<valor>
															<cajatexto id="Campo:{position()}.patronvalidacion" valor="{//Campo[position()=$pp]/patronvalidacion}"/>
														</valor>
													</registro>
												
												</ocultable>
													
												</td>
												
												<td  style="width:100px">
												<area_botones>
													<xsl:if test="not(//Campo[position()=$pp]/bloqueo_edicion='S')">
														<boton estilo="eliminar" accion="page_eliminarElemento('{position()}')">Eliminar</boton>
													</xsl:if>
												</area_botones>
												
												</td>
												</tr>
												</table>
											</bloque>
										</bloque>
										
									</xsl:for-each>
									
									
									<area_botones>
										<boton id="btn_agregar_campo" estilo="crear" >Agregar Campo</boton>
									</area_botones>
										
								
							</pestana>
						</bloque-pestanas>
						
						<variable id="var.num_elemento" valor="{count(//Campo)+1}"/>
						
						
							
					</formulario>
					
					<formulario id="form_eliminar" destino="estructuras/1.7.do">
						<variable id="id_estructura" valor="{obtenerEstructura/Estructura/id_estructura}"/>
					</formulario>
					
					<area_botones>
						
						<boton estilo="cancelar" destino="estructuras/1.1.do">Cancelar</boton>
						
						<xsl:if test="not(//obtenerEstructura/Estructura/bloqueo_edicion='S') and count(//obtenerFormatosPorEstructura/listado/Formato)=0 and count(//obtenerEstructurasPadre/listado/Estructura)=0 and count(//obtenerEstructura/Estructura) != 0">	
							<boton estilo="eliminar" formulario="form_eliminar">Eliminar</boton>
						</xsl:if>
						
						<xsl:if test="count(//obtenerEstructura/Estructura) != 0">
							<boton estilo="exportar" accion="osm_go('estructuras/estruc.exportEst?id_estructura={obtenerEstructura/Estructura/id_estructura}', false);" >Exportar</boton>
						</xsl:if>
						
						<boton estilo="guardar" formulario="form_edicion" validacion="page_validarGuardar()">Guardar</boton>
						
					</area_botones>
					
					<xsl:if test="count(//obtenerFormatosPorEstructura/listado/Formato)>0 or count(//obtenerEstructurasPadre/listado/Estructura)>0">
						
						<div class="mesg2 red">
							
							<p>
								<b>La estructura "<xsl:value-of select="//obtenerEstructura/Estructura/nombre"/>" no puede eliminarse por que tiene los siguientes elementos relacionados</b>
							</p>
							
							<xsl:for-each select="//obtenerEstructurasPadre/listado">
								<p>
									Estructura '<xsl:value-of select="//obtenerEstructurasPadre/listado/Estructura/nombre"/>'
								</p>
							</xsl:for-each>
							
							<xsl:for-each select="//obtenerFormatosPorEstructura/listado">
								<p>
									Formato '<xsl:value-of select="//obtenerFormatosPorEstructura/listado/Formato/nombre"/>'
								</p>
							</xsl:for-each>
						</div>
							
					</xsl:if>
				</contenido>
				
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
