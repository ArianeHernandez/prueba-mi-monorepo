<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/VariablesLiberacion.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:variable name="acciones"  select="//OSM-INIT-SESSION/acciones/obtenerMenusPorAplicacion/Listado"/>
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Grupos">
			<javascript>admin/4.8.js</javascript>
			
			<principal>
				<titulo icono="formatos">Edición de Formato de Salida</titulo>
				<contenido>
					
					<formulario id="form_edicion" destino="formatos/4.3.do">
						
						<variable id="Formato.id_formato" valor="{//obtenerFormato/Formato/id_formato}"/>
						<variable id="Formato.id_modelo" valor="{//obtenerFormato/Formato/id_modelo}"/>
						
						
						<bloque-pestanas>
					
							<pestana titulo="Información Basica">
							
								<registro>
									<item>Nombre del Formato</item>
									<valor>
										<cajatexto id="Formato.nombre" valor="{//obtenerFormato/Formato/nombre}" />
									</valor>
								</registro>
								
								<registro>
									<item>Descripción</item>
									<valor>
										<areatexto id="Formato.descripcion" valor="{//obtenerFormato/Formato/descripcion}" />
									</valor>
								</registro>
								
								<variable id="Formato.tipoformato" valor="S"/>
								
								<registro>
									<item>Grupo formato</item>
									<valor>
										<cajatextoselector id="Formato.id_grupoformato" valor="{//obtenerFormato/Formato/id_grupoformato}">
											<opcion valor="" texto="-- Seleccione --"/>
											<xsl:for-each select="//obtenerTodosLosGruposformato/Listado/GrupoFormato">
												<opcion valor="{id_grupoformato}" texto="{nombre}" />
											</xsl:for-each>
										</cajatextoselector>
									</valor>
								</registro>
								
								<registro>
									<item>Activo usuario</item>
									<valor>
										<cajachequeo id="Formato.activo" valor="S" seleccionado="{boolean(//obtenerFormato/Formato/activo='S')}"/>
									</valor>
								</registro>
								<xsl:if test="$acciones/Menu[direccion='estructuras/1.1.do']/autorizado='true'">
									<registro>
										<item>Rechazar Por Registro</item>
										<valor>
											<cajachequeo id="Formato.rechaza_registro" valor="S" seleccionado="{boolean(//obtenerFormato/Formato/rechaza_registro='S')}"/>
										</valor>
									</registro>
								</xsl:if>
								<registro>
									<item>Activar sección adjuntos en reporte</item>
									<valor>
										<cajachequeo id="Formato.seccion_adjuntos_reporte" valor="S" seleccionado="{boolean(//obtenerFormato/Formato/seccion_adjuntos_reporte='S')}"/>
									</valor>
								</registro>
								
								<registro>
									<item>Tiempo para comparación de cargas repetidas para Cliente (min)</item>
									<valor>
										<cajatexto id="Formato.tiempo_validacion_preparacion" valor="{//obtenerFormato/Formato/tiempo_validacion_preparacion}" />
									</valor>
								</registro>
								
								<registro>
									<item>Tiempo para comparación de cargas repetidas para Administrador (min)</item>
									<valor>
										<cajatexto id="Formato.tiempo_validacion_liberacion" valor="{//obtenerFormato/Formato/tiempo_validacion_liberacion}" />
									</valor>
								</registro>
								
								<xsl:if test="count(//obtenerComponentes/listado/Componente)>0">
									<registro>
										<item>Accion a Realizar</item>
										<valor>
											<cajatextoselector id="Formato.id_componente" valor="{//obtenerFormato/Formato/id_componente}">
												<opcion valor="" texto="-- Ninguna --"/>
												<xsl:for-each select="//obtenerComponentes/listado/Componente">
													<opcion valor="{id_componente}" texto="{descripcion}"/>
												</xsl:for-each>
											</cajatextoselector>
										</valor>
									</registro>
								</xsl:if>
								
							</pestana>
							
							
							
							<pestana titulo="Información de Acceso">
								
								<variable id="Formato.registrosporcarga" valor="0"/>
								
								<registro>
									<item>Medios Disponibles</item>
									<valor>
										<cajachequeo id="Formato.medio_formulario" valor="S" texto="Html" seleccionado="{boolean(//obtenerFormato/Formato/medio_formulario='S')}"/>
										<cajachequeo id="Formato.medio_excel" valor="S" texto="Excel" seleccionado="{boolean(//obtenerFormato/Formato/medio_excel='S')}"/>
										<xsl:if test="$acciones/Menu[direccion='estructuras/1.1.do']/autorizado='true'">
											<cajachequeo id="Formato.medio_pdf" valor="S" texto="PDF" seleccionado="{boolean(//obtenerFormato/Formato/medio_pdf='S')}"/>
										</xsl:if>
										<!--cajachequeo id="Formato.medio_webservice" valor="S" texto="Web Service" seleccionado="{boolean(//obtenerFormato/Formato/medio_webservice='S')}"/-->
									</valor>
								</registro>
								
							</pestana>
							
							
							
							<pestana titulo="Destinos">
								
								<variable id="var.num_elemento" valor="{count(//Destino)+1}"/>
								
								<xsl:for-each select="num_elemento">
									
									<xsl:variable name="pp" select="position()"/>
									
									<bloque id="BloqueDestino_{position()}" visible="{boolean(not(count(//Destino[position()=$pp])=0))}">
										
										<variable id="bloqueDestinoVisible_{position()}" valor="{boolean(not(count(//Destino[position()=$pp])=0))}"/>
										
										<variable id="Destino:{position()}.id_destino" valor="{//Destino[position()=$pp]/id_destino}"/>
										
										<registro>
											<item>Titulo</item>
											<valor>
												<cajatexto id="Destino:{position()}.titulo" valor="{//Destino[position()=$pp]/titulo}" />
											</valor>
										</registro>
										
										<registro>
											<item>Servicio</item>
											<valor>
												<cajatextoselector id="Destino:{position()}.id_uri" valor="{//Destino[position()=$pp]/id_uri}">
													<opcion valor="" texto="-- Seleccione --"/>
													<xsl:for-each select="//obtenerUris/listado/Uri">
														<opcion valor="{id_uri}" texto="{nombre}"/>
													</xsl:for-each>
												</cajatextoselector>
											</valor>
										</registro>
										
										
										<area_botones>
											<boton estilo="eliminar" accion="page_eliminarElemento('{position()}')">Eliminar Destino</boton>
										</area_botones>
									</bloque>
									
								</xsl:for-each>	
								
								<area_botones>
									<boton id="btn_agregar_destino" estilo="crear" >Agregar Destino</boton>
								</area_botones>
								
							</pestana>
						
							<pestana titulo="Definición del Formato">
								
								<registro>
									<item>Titulo del Formato</item>
									<valor>
										<cajatexto id="FormatoCampo.titulo" valor="{//obtenerFormatoCampoCompleto/Formato/FormatoCampo/titulo}" />
									</valor>
								</registro>
								
								<registro>
									<item>Estructura Base</item>
									<valor>
										<cajatextoselector id="Formato.id_estructura" valor="{//obtenerFormato/Formato/id_estructura}">
											<opcion valor="" texto="-- Seleccione --"/>
											<xsl:for-each select="//obtenerEstructurasPorPersona/listado/Estructura">
												<opcion valor="{id_estructura}" texto="{nombre}"/>
											</xsl:for-each>
										</cajatextoselector>
									</valor>
								</registro>
								
								<bloque-contenido>
									<titulo icono="edicion">Definición del Formato</titulo>
									<contenido>
										
										<bloque id="Formato.id_estructura|CONTENT">
											<xsl:for-each select="//obtenerFormatoCampoCompleto/Formato/FormatoCampo/formato_campo_list/FormatoCampo[estructura_padre='N']">
												<xsl:variable name="posi" select="position()"/>
												<xsl:call-template name="FormatoCampo">
													<xsl:with-param name="plantilla" select="concat('FormatoCampo.formato_campo_list:',$posi)"/>
													<xsl:with-param name="id_padre" select="//obtenerFormato/Formato/id_estructura"/>
													<xsl:with-param name="posicion" select="concat($posi,'.')"/>
												</xsl:call-template>
											</xsl:for-each>
										
										</bloque>
										<bloque id="Formato.id_estructura|CONTENT_PADRE">
										
											<xsl:for-each select="//obtenerFormatoCampoCompleto/Formato/FormatoCampo/formato_campo_list">
												<xsl:variable name="posi" select="count(//obtenerFormatoCampoCompleto/Formato/FormatoCampo/formato_campo_list/FormatoCampo[estructura_padre='N']) + 1"/>
												
												<xsl:call-template name="ListadoFormatoCampoEstructura">
													<xsl:with-param name="plantilla" select="concat('FormatoCampo.formato_campo_list:',$posi)"/>
													<xsl:with-param name="id_padre" select="//obtenerFormato/Formato/id_estructura"/>
													<xsl:with-param name="posicion" select="concat($posi,'.')"/>
												</xsl:call-template>
												
											</xsl:for-each>
										
										</bloque>
										
									</contenido>
								</bloque-contenido>
								
							</pestana>
							
							<pestana titulo="Formatos Referenciados">
								<bloque>
									<registro>
										<item>Formato a referenciar</item>
										<valor>
											<cajatextoselector id="sel_formatos">
												<opcion>--Seleccione--</opcion>
												<xsl:for-each select="//obtenerFormatosPorTipo/Listado/Formato[id_formato != //obtenerFormato/Formato/id_formato ]">
														<opcion valor="{id_formato}">
															<xsl:value-of select="id_formato"></xsl:value-of> - <xsl:value-of select="nombre"></xsl:value-of>
														</opcion>
												</xsl:for-each>
											</cajatextoselector>
										</valor>
									</registro>
					
									<area_botones>
										<boton estilo="agregar" accion="agregarFormatoRef()">Agregar</boton>
									</area_botones>
									
								</bloque>
								
								<bloque-contenido>
									<titulo>Formatos referenciados</titulo>
									<contenido>
										<bloque id="FORMATOS_REFERENCIADOS">
											<xsl:for-each select="//obtenerSubFormatosPorPadre/listado/Formato">
												<bloque estilo="grupo" id="formato_ref_{id_formato}">
													<input type="hidden" name="formatos_referenciados:{id_formato}" value="{id_formato}" />
													<subtitulo><xsl:value-of select="nombre"></xsl:value-of></subtitulo>
													<area_botones>
														<boton accion="eliminarFormatoRef('{id_formato}')">Eliminar</boton>
													</area_botones>
												</bloque>
											</xsl:for-each>
										</bloque>
									</contenido>
								</bloque-contenido>
								
								<bloque id="PLANTILLA_FORMATO_REF" display="none">
									<bloque estilo="grupo" id="formato_ref_[ 1 ]">
										<input type="hidden" name="formatos_referenciados:[ 1 ]" value="[ 1 ]" />
										<subtitulo>[ 2 ]</subtitulo>
										<area_botones>
											<boton accion="eliminarFormatoRef('[ 1 ]')">Eliminar</boton>
										</area_botones>
									</bloque>
								</bloque>
							</pestana>
							
							<xsl:call-template name="VARIABLES_LIBERACION"/>
						</bloque-pestanas>
						
						
						<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
						
						<bloque id="plantilla_campo">

							<variable id="PLANTILLA.estructura_padre" valor="N"/>
							<variable id="PLANTILLA.id_padre" valor="IDPADRE"/>
							<variable id="PLANTILLA.id_campo" valor="IDCAMPO"/>
							<variable id="PLANTILLA.id_estructura" valor="IDESTRUCTURA"/>
							<variable id="PLANTILLA.id_estructura|POSICION" valor="POSICIONCAMPO"/>
														
							<bloque estilo="grupo">
								<subtitulo  texto="POSICIONCAMPO NOMBRECAMPO"/>
									
								<registro>
									<item>Titulo</item>
									<valor>
										<cajatexto id="PLANTILLA.titulo" valor="NOMBRECAMPO" />
									</valor>
								</registro>
								
								<registro>
									<item>Seleccion</item>
									<valor>
										<div id="PLANTILLA.seleccion_visualizacion_Block" class="DISPLAY_SV">
											<cajachequeo id="PLANTILLA.seleccion_visualizacion" valor="S" texto="Seleccion Visualizacion" seleccionado="false"/>
										</div>
										
										<cajachequeo id="PLANTILLA.seleccion_campo" valor="S" texto="Seleccion Campo" seleccionado="false" accion="page_cambioTipovisualizacion(this, 'PLANTILLA')"/>
									</valor>
								</registro>
								
								<registro>
									<item>Condicional</item>
									<valor>
										<cajachequeo id="PLANTILLA.condicional" valor="S" texto="" seleccionado="false" accion="verValorCondicion(this, 'PLANTILLA')"/>
									</valor>
								</registro>
								
								<registro>
									<item>Formato</item>
									<valor>
										<cajatexto id="PLANTILLA.formato" valor="" />
									</valor>
								</registro>
								
								<bloque id="PLANTILLA.valorCondicion_Block" visible="false">
									<registro>
										<item>Valor de la Condición</item>
										<valor>
											<cajatexto id="PLANTILLA.valor_condicion" valor=""/>
										</valor>
									</registro>
								</bloque>	
								
								<variable id="PLANTILLA.id_operacion" valor="0"/>
								
								<bloque id="PLANTILLA.valorConstante_Block" visible="false">
									<registro>
										<item>Valor Constante</item>
										<valor>
											<cajatexto id="PLANTILLA.valor_constante" valor="" />
										</valor>
									</registro>
								</bloque>
								
								<bloque id="PLANTILLA.id_variable_Block" visible="false">
									<registro>
										<item>Variable del Sistema</item>
										<valor>
											<cajatextoselector id="PLANTILLA.id_variable" valor="">
												<opcion valor="" texto="-- Seleccione --" />
											</cajatextoselector>
										</valor>
									</registro>
								</bloque>
								
								<registro>
									<item>Visualizar en Bandeja de Entrada</item>
									<valor>
										<cajachequeo id="PLANTILLA.bandeja_entrada" valor="S" texto="" seleccionado="false" accion="verOrdenBandejaEntrada(this, 'PLANTILLA')"/>
									</valor>
								</registro>
								
								<bloque id="PLANTILLA.ordenBandejaEntrada_Block" visible="false">
									<registro visible="false">
										<item>Orden Campo en Bandeja de Entrada</item>
										<valor>
											<cajatexto id="PLANTILLA.orden_bandeja_entrada" valor=""/>
										</valor>
									</registro>
								</bloque>	
								
								<div class="DISPLAY_FILTRO">
									<registro>
										<item>Visualizar Filtro en Bandeja de Entrada</item>
										<valor>
											<cajachequeo id="PLANTILLA.filtro_bandeja_entrada" valor="S" texto="" seleccionado="false" accion=""/>
										</valor>
									</registro>
								</div>
								
								<bloque id="PLANTILLA.CONTENT" >&#160;</bloque>
								<bloque id="PLANTILLA.CONTENT_PADRE" >&#160;</bloque>
									
							</bloque>
							
						</bloque>

						
						<bloque id="plantilla_estructura">
							
							<variable id="PLANTILLA.estructura_padre" valor="S"/>
							<variable id="PLANTILLA.id_padre" valor="IDPADRE"/>
							<variable id="PLANTILLA.id_campo" valor="IDCAMPO"/>
							<variable id="PLANTILLA.id_estructura" valor="IDESTRUCTURA"/>
							<variable id="PLANTILLA.id_estructura|POSICION" valor="POSICIONESTRUCTURA"/>
							
							<bloque estilo="grupo">
								<subtitulo  texto="POSICIONESTRUCTURA NOMBREESTRUCTURA"/>
								
								<registro>
									<item>Agregar al Formato</item>
									<valor>
										<cajachequeo id="PLANTILLA.agregarFormato" accion="page_cargaCamposEstructura(this, 'PLANTILLA')" />
									</valor>
								</registro>
								
								<registro>
									<item>Titulo</item>
									<valor>
										<cajatexto id="PLANTILLA.titulo" valor="REL-NOMBREESTRUCTURA" />
									</valor>
								</registro>
								
								<bloque id="PLANTILLA.CONTENT" >&#160;</bloque>
								<bloque id="PLANTILLA.CONTENT_PADRE" >&#160;</bloque>
								
							</bloque>
						</bloque>
						
					</formulario>
					
					<formulario id="form_eliminar" destino="formatos/4.7.do">
						<variable id="id_formato" valor="{//obtenerFormato/Formato/id_formato}"/>
					</formulario>
						
					<area_botones>
						<xsl:if test="(//obtenerFormato/Formato/id_formato) != 0">
							<boton estilo="eliminar" formulario="form_eliminar" validacion="confirm('¿Desea eliminar el formato?')">Eliminar</boton>
						</xsl:if>	
						<boton estilo="cancelar" destino="formatos/4.1.do">Cancelar</boton>
						<boton estilo="guardar" formulario="form_edicion" validacion="page_validarGuardar()">Guardar Formato</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>
	
	<xsl:template name="ListadoFormatoCampoEstructura">
		
		<xsl:param name="plantilla"/>
		<xsl:param name="id_padre"/>
		<xsl:param name="id_campo"/>
		<xsl:param name="id_abuelo"/>
		<xsl:param name="posicion"/>
		
		<xsl:variable name="listado" select="."/>
		
		<xsl:for-each select="//obtenerEstructurasPorPersona/listado/Estructura[id_estructura != $id_abuelo and id_estructura = //obtenerEstructurasPorPersona/listado/Estructura[id_estructura = $id_padre]/id_estructuras_relacionadas/Integer]">
			<xsl:variable name="id_estructura" select="id_estructura"/>
			
			<variable id="{$plantilla}.estructura_padre" valor="S"/>
			<variable id="{$plantilla}.id_padre" valor="{$id_padre}"/>
			<variable id="{$plantilla}.id_campo" valor="{$id_campo}"/>
			<variable id="{$plantilla}.id_estructura" valor="{$id_estructura}"/>
			<variable id="{$plantilla}.id_estructura|POSICION" valor="{$posicion}"/>
			
			<bloque estilo="grupo">
				<subtitulo  texto="{$posicion} {nombre}"/>
				
				<registro>
					<item>Agregar al Formato</item>
					<valor>
						<cajachequeo id="{$plantilla}.agregarFormato" seleccionado="{boolean(count($listado/FormatoCampo[estructura_padre='S' and id_estructura=$id_estructura and count(formato_campo_list/FormatoCampo)>0])>0)}" accion="page_cargaCamposEstructura(this, '{$plantilla}')" />
					</valor>
				</registro>
				
				<registro>
					<item>Titulo</item>
					<valor>
						<cajatexto id="{$plantilla}.titulo" valor="{$listado/FormatoCampo[estructura_padre='S' and id_estructura=$id_estructura]/titulo}" />
					</valor>
				</registro>
				
				<bloque id="{$plantilla}.CONTENT" >
					
					<xsl:for-each select="$listado/FormatoCampo[estructura_padre='S' and id_estructura=$id_estructura]/formato_campo_list/FormatoCampo[estructura_padre='N']">
						<xsl:variable name="posi" select="position()"/>
						<xsl:call-template name="FormatoCampo">
							<xsl:with-param name="plantilla" select="concat($plantilla,'.formato_campo_list:',$posi)"/>
							<xsl:with-param name="id_padre" select="$id_estructura"/>
							<xsl:with-param name="posicion" select="concat($posicion,$posi,'.')"/>
						</xsl:call-template>
					</xsl:for-each>
	
				</bloque>
				<bloque id="{$plantilla}.CONTENT_PADRE" >
	
					<xsl:if test="count($listado/FormatoCampo[estructura_padre='S' and id_estructura=$id_estructura and count(formato_campo_list/FormatoCampo)>0])>0">
						<xsl:for-each select="$listado/FormatoCampo[estructura_padre='S' and id_estructura=$id_estructura]/formato_campo_list">
							<xsl:variable name="posi" select="count($listado/FormatoCampo[estructura_padre='S' and id_estructura=$id_estructura]/formato_campo_list/FormatoCampo[estructura_padre='N']) + 1"/>
							
							<xsl:call-template name="ListadoFormatoCampoEstructura">
								<xsl:with-param name="plantilla" select="concat($plantilla,'.formato_campo_list:',$posi)"/>
								<xsl:with-param name="id_padre" select="$id_estructura"/>
								<xsl:with-param name="id_campo" select="$id_campo"/>
								<xsl:with-param name="posicion" select="concat($posicion,$posi,'.')"/>
							</xsl:call-template>
							
						</xsl:for-each>
					</xsl:if>
					
				</bloque>
			</bloque>
		
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template name="FormatoCampo">
		
		<xsl:param name="plantilla"/>
		<xsl:param name="id_padre"/>
		<xsl:param name="posicion"/>
		
		<!-- ********************************************** -->
		
		<xsl:variable name="id_campo" select="id_campo"/>
		
		<xsl:if test="estructura_padre='N'">
			
			<variable id="{$plantilla}.estructura_padre" valor="{estructura_padre}"/>
			<variable id="{$plantilla}.id_padre" valor="{$id_padre}"/>
			<variable id="{$plantilla}.id_campo" valor="{$id_campo}"/>
			<variable id="{$plantilla}.id_estructura" valor="{//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_estructurarelacionada}"/>
			<variable id="{$plantilla}.id_estructura|POSICION" valor="{$posicion}"/>

			<bloque estilo="grupo">
				<subtitulo  texto="{$posicion} {//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/nombre}"/>
					
				<registro>
					<item>Titulo</item>
					<valor>
						<cajatexto id="{$plantilla}.titulo" valor="{titulo}" />
					</valor>
				</registro>
				
				<registro>
					<item>Seleccion</item>
					<valor>
						<bloque id="{$plantilla}.seleccion_visualizacion.seleccion_visualizacion_Block" visible="{//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_tipocampo=0}">
							<cajachequeo id="{$plantilla}.seleccion_visualizacion" valor="S" texto="Seleccion Visualizacion" seleccionado="{boolean(seleccion_visualizacion='S')}"/>
						</bloque>
						
						<cajachequeo id="{$plantilla}.seleccion_campo" valor="S" texto="Seleccion Campo" seleccionado="{boolean(seleccion_campo='S')}" accion="page_cambioTipovisualizacion(this, '{$plantilla}')"/>
					</valor>
				</registro>
				
				<registro>
					<item>Condicional</item>
					<valor>
						<cajachequeo id="{$plantilla}.condicional" valor="S" texto="" seleccionado="{boolean(condicional='S')}" accion="verValorCondicion(this, '{$plantilla}')"/>
					</valor>
				</registro>
				
				<registro>
					<item>Formato</item>
					<valor>
						<cajatexto id="{$plantilla}.formato" valor="{formato}" />
					</valor>
				</registro>
				
				<bloque id="{$plantilla}.valorCondicion_Block" visible="{boolean(condicional='S')}">
					<registro>
						<item>Valor de la Condición</item>
						<valor>
							<cajatexto id="{$plantilla}.valor_condicion" valor="{valor_condicion}"/>
						</valor>
					</registro>
				</bloque>
				
				<variable id="{$plantilla}.id_operacion" valor="{id_operacion}"/>
				
				<bloque id="{$plantilla}.valorConstante_Block" visible="{boolean(tipo_ingreso=4)}">
					<registro>
						<item>Valor Constante</item>
						<valor>
							<cajatexto id="{$plantilla}.valor_constante" valor="{valor_constante}" />
						</valor>
					</registro>
				</bloque>
				
				<bloque id="{$plantilla}.id_variable_Block" visible="{boolean(tipo_ingreso=5)}">
					<registro>
						<item>Variable del Sistema</item>
						<valor>
							<cajatextoselector id="{$plantilla}.id_variable" valor="{id_variable}">
								<opcion valor="" texto="-- Seleccione --" />
							</cajatextoselector>
						</valor>
					</registro>
				</bloque>
				
				<xsl:if test="boolean(//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/multiplicidad='1...1')">
					<registro>
						<item>Visualizar en Bandeja de Entrada</item>
						<valor>
							<cajachequeo id="{$plantilla}.bandeja_entrada" valor="S" texto="" seleccionado="{boolean(bandeja_entrada='S')}" accion="verOrdenBandejaEntrada(this, '{$plantilla}')"/>
						</valor>
					</registro>
					
					<bloque id="{$plantilla}.ordenBandejaEntrada_Block" visible="{bandeja_entrada='S'}">
						<registro visible="{bandeja_entrada='S'}">
							<item>Orden Campo en Bandeja de Entrada</item>
							<valor>
								<cajatexto id="{$plantilla}.orden_bandeja_entrada" valor="{orden_bandeja_entrada}"/>
							</valor>
						</registro>
					</bloque>
					
				</xsl:if>
				
				<xsl:if test="boolean(not(//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_tipocampo=0))">			
					<registro>
						<item>Visualizar Filtro en Bandeja de Entrada</item>
						<valor>
							<cajachequeo id="{$plantilla}.filtro_bandeja_entrada" valor="S" texto="" seleccionado="{boolean(filtro_bandeja_entrada='S')}" accion=""/>
						</valor>
						</registro>
				</xsl:if>	
				
				<bloque id="{$plantilla}.CONTENT">
								
					<xsl:for-each select="formato_campo_list/FormatoCampo[estructura_padre='N']">
						<xsl:variable name="posi" select="position()"/>
						<xsl:call-template name="FormatoCampo">
							<xsl:with-param name="plantilla" select="concat($plantilla,'.formato_campo_list:',$posi)"/>
							<xsl:with-param name="id_padre" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_estructurarelacionada"/>
							<xsl:with-param name="posicion" select="concat($posicion,$posi,'.')"/>
						</xsl:call-template>
					</xsl:for-each>
				
				</bloque>
				<bloque id="{$plantilla}.CONTENT_PADRE">
				
					<xsl:if test="tipo_ingreso = '6' and count(//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_estructurarelacionada)>0">
						<xsl:for-each select="formato_campo_list">
							<xsl:variable name="posi" select="count(FormatoCampo[estructura_padre='N']) + 1"/>
							
							<xsl:call-template name="ListadoFormatoCampoEstructura">
								<xsl:with-param name="plantilla" select="concat($plantilla,'.formato_campo_list:',$posi)"/>
								<xsl:with-param name="id_padre" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_estructurarelacionada"/>
								<xsl:with-param name="id_abuelo" select="$id_padre"/>
								<xsl:with-param name="posicion" select="concat($posicion,$posi,'.')"/>
							</xsl:call-template>
							
						</xsl:for-each>
					</xsl:if>
				
				</bloque>
			</bloque>
			
		</xsl:if>
		
		<!-- ********************************************** -->
		
	</xsl:template>
	

</xsl:stylesheet>
