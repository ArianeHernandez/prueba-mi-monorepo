<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/horario.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Edición de Formato de Entrada">
			<javascript>admin/4.2.1.js</javascript>
			<stylesheet>4.2.css</stylesheet>
			<principal>
				<titulo icono="formatos">Edición de Formato de Entrada</titulo>
				<contenido>
					
						<bloque-pestanas>
							<!-- PESTANA PARAMETROS DEL FORMATO -->
							<pestana titulo="PARAMETROS DEL FORMATO" action="mostrarEdicionParametrosFormato()">
								<formulario id="form_edicion_parametros" destino="formatos/4.2.do">
									<variable id= "id_formato" valor="{//obtenerFormato/Formato/id_formato}"/>
								</formulario>
								
							</pestana> 
									
									
							<!-- PESTANA DE CAMPOS DEL FORMATO -->
							<pestana titulo="ESTRUCTURA DEL FORMATO" visible='true'>		
								<formulario id="form_edicion" destino="formatos/4.3.1.do">
									<xsl:variable name="id_campo_totalizador" select="//obtenerFormato/Formato/id_campo_totalizador"/>
									<xsl:variable name="nombre_campo_totalizador" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo_totalizador]/nombre"/>
											 
									<variable id="id_totalizador" valor="{$id_campo_totalizador}"/>
									<variable id="valor_totalizador" valor="{$nombre_campo_totalizador}"/>
									<variable id="id_formato" valor="{//obtenerFormato/Formato/id_formato}"/>
									<variable id="Formato.id_formato" valor="{//obtenerFormato/Formato/id_formato}"/>
															
									<registro>
										<item>Titulo del Formato</item>
										<valor>
											<cajatexto id="FormatoCampo.titulo" valor="{//obtenerFormatoCampoCompleto/Formato/FormatoCampo/titulo}" maxlength='25'/>
										</valor>
									</registro>
									
									<registro>
										<item>Estructura Base</item>
										<valor>
											<cajatextoselector id="Formato.id_estructura" valor="{//obtenerFormato/Formato/id_estructura}">
												<opcion valor="" texto="-- Seleccione --"/>
												<xsl:for-each select="//obtenerEstructurasPorPersona/listado/Estructura">
													<opcion valor="{id_estructura}" texto="{nombre}" />
												</xsl:for-each>
											</cajatextoselector>
										</valor>
									</registro>
									
									<registro>
										<item>Campo totalizador</item>
										<valor>
											<cajatextoselector id="Formato.id_campo_totalizador" valor="{//obtenerFormato/Formato/id_campo_totalizador}">
												<opcion valor="" texto="-- Seleccione --"/>
											</cajatextoselector>
										</valor>
									</registro>
									
									
									<registro>
										<item>Realizar Precarga</item>
										<valor>
											<cajachequeo id="FormatoCampo.precarga" valor="S" seleccionado="{boolean(//obtenerFormatoCampoCompleto/Formato/FormatoCampo/precarga = 'S')}"/>
										</valor>
									</registro>
									
									<registro>
										<item>Condicion Precarga</item>
										<valor>
											<areatexto id="FormatoCampo.condicion_precarga" valor="{//obtenerFormatoCampoCompleto/Formato/FormatoCampo/condicion_precarga}"/>
										</valor>
									</registro>
									
									<bloque-contenido>
										<titulo icono="edicion">Campos del Formato</titulo>
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
									
									<area_botones>
										<xsl:if test="not(//obtenerFormato/Formato/bloqueado = 'S')">
											<boton estilo="guardar" formulario="form_edicion" validacion="page_validarGuardar()">Guardar Estructura Formato</boton>
										</xsl:if>
									</area_botones>
									
								</formulario>
							</pestana>
							
							
							
							
						</bloque-pestanas>
					
						<area_botones>
							<boton estilo="cancelar" destino="formatos/4.1.do">Cancelar</boton>
						</area_botones>
					
						
						<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
						
						
						<bloque id="plantilla_campo">

							<variable id="PLANTILLA.estructura_padre" valor="N"/>
							<variable id="PLANTILLA.id_padre" valor="IDPADRE"/>
							<variable id="PLANTILLA.id_campo" valor="IDCAMPO"/>
							<variable id="PLANTILLA.multiplicidad" valor="MULTIPLICIDAD"/>
							<variable id="PLANTILLA.id_estructura" valor="IDESTRUCTURA"/>
							<variable id="PLANTILLA.id_estructura|POSICION" valor="POSICIONCAMPO"/>
														
							<bloque estilo="grupo">
								<subtitulo  texto="POSICIONCAMPO NOMBRECAMPO"/>
									
								<registro>
									<item>Titulo</item>
									<valor>
										<cajatexto id="PLANTILLA.titulo" valor="NOMBRECAMPO" maxlength='150' />
									</valor>
								</registro>
				
								<registro>
									<item>Usar HTML alterno para el campo Titulo</item>
									<valor>
										<cajatextoselector id="PLANTILLA.usa_titulo_html" valor="" 
											accion="page_cambioTitulo(this, 'PLANTILLA')">
											<opcion valor="N"  texto="NO"/>
											<opcion valor="S"  texto="SI"/>
										</cajatextoselector>
									</valor>
								</registro>
								
								<bloque id="PLANTILLA.titulo_html_Block" visible="false">
									<registro>
										<item>Titulo HTML</item>
										<valor>
											<areatexto id="PLANTILLA.titulo_html" valor=""/>
										</valor>
									</registro>
								</bloque>
								
								<registro>
									<item>Texto Ayuda</item>
									<valor>
										<areatexto id="PLANTILLA.ayuda" valor=""/>
									</valor>
								</registro>
								
								<registro>
									<item>Tipo de Valor</item>
									<valor>
										<cajatextoselector id="PLANTILLA.tipo_ingreso" valor="" accion="page_cambioTipoingreso(this, 'PLANTILLA')">
											<opcion valor="0" texto="-- Seleccione --" />
										</cajatextoselector>
									</valor>
								</registro>
								
								<registro>
									<item>Bloquear Edición</item>
									<valor>
										<cajachequeo id="PLANTILLA.bloqueado" valor="S" texto="" seleccionado="false"/>
									</valor>
								</registro>
								
								<registro>
									<item>Tipo de Control</item>
									<valor>
										<cajatextoselector id="PLANTILLA.id_operacion" valor="">
											<opcion valor="0"  texto="-- Ninguna --"/>
											<xsl:for-each select="obtenerOperacionesControl/listado/Operacion">
												<opcion valor="{id_operacion}" texto="{descripcion}" />
											</xsl:for-each>
										</cajatextoselector>
									</valor>
								</registro>
								
								<registro>
									<item>Obligatorio</item>
									<valor>
										<cajatextoselector id="PLANTILLA.obligatorio" valor="">
											<opcion valor="N" texto="NO"/>
											<opcion valor="S" texto="SI"/>
										</cajatextoselector>
									</valor>
								</registro>
								
								<bloque id="PLANTILLA.valorConstante_Block" visible="false">
									<registro>
										<item>Valor Constante</item>
										<valor>
											<cajatexto id="PLANTILLA.valor_constante" valor="" />
										</valor>
									</registro>
								</bloque>
								
								<bloque id="PLANTILLA.visualizarValorConstante_Block" visible="false">
									<registro visible="false">
										<item>Visualizar Valor Constante</item>
										<valor>
											<cajachequeo id="PLANTILLA.visualizar_valor_constante" valor="S" texto="" seleccionado="false"/>
										</valor>
									</registro>
								</bloque>
								
								<bloque id="PLANTILLA.valorSesion_Block" visible="false">
									<registro>
										<item>Valor de Sesion</item>
										<valor>
											<cajatextoselector id="PLANTILLA.valor_sesion" valor="">
												<opcion valor="login" texto="login" />
												<opcion valor="id_negocio" texto="id negocio" />
												<opcion valor="cod_negocio" texto="codigo negocio" />
												<opcion valor="id_persona" texto="id_persona" />
												<opcion valor="tipo_persona" texto="tipo de persona" />
												<opcion valor="tipo_documento" texto="tipo de documento" />
												<opcion valor="identificacion" texto="identificacion persona" />
												<opcion valor="nombre_persona" texto="nombre persona" />
												<opcion valor="id_usuario" texto="id cliente" />
												<opcion valor="nombre_usuario" texto="nombre cliente" />
												<opcion valor="id_administrativo" texto="id administrativo" />
												<opcion valor="tipo_documento_usuario" texto="Tipo de documento cliente" />
												<opcion valor="identificacion_usuario" texto="Identificación cliente" />
											</cajatextoselector>
										</valor>
									</registro>
								</bloque>
								
								<bloque id="PLANTILLA.visualizarValorSesion_Block" visible="false">
									<registro visible="false">
										<item>Visualizar Valor de Sesion</item>
										<valor>
											<cajachequeo id="PLANTILLA.visualizar_valor_sesion" valor="S" texto="" seleccionado="false"/>
										</valor>
									</registro>
								</bloque>
								
								<bloque id="PLANTILLA.id_variable_Block" visible="false">
									<registro>
										<item>Variable del Sistema</item>
										<valor>
											<cajatextoselector id="PLANTILLA.id_variable" valor="">
												<xsl:for-each select="//obtenerVariablesPorModelo/listado/Variable">
													<xsl:sort select="nombre_variable"/>
													<opcion valor="{id_variable}" texto="{nombre_variable}" />
												</xsl:for-each>
											</cajatextoselector>
										</valor>
									</registro>
								</bloque>
								
								
								<bloque id="PLANTILLA.id_listadinamica_Block" visible="false">
									<registro>
										<item>Lista Dinamica</item>
										<valor>
											<cajatextoselector id="PLANTILLA.id_lista_dinamica" valor="">
												
												<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
													<xsl:sort select="nombre"/>
													<opcion valor="{id_lista_dinamica}" texto="{nombre}" />
												</xsl:for-each>
												
											</cajatextoselector>
										</valor>
									</registro>
								</bloque>
								
								<bloque id="PLANTILLA.lista_dinamica_valor_inicial_Block" visible="false">
									<registro visible="false">
										<item>Valor Inicial</item>
										<valor>
											<cajatextoselector id="PLANTILLA.lista_dinamica_valor_inicial" valor="">
												<opcion valor="" texto="-- Seleccione --"/>
												<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
													<xsl:sort select="nombre"/>
													<opcion valor="{id_lista_dinamica}" texto="{nombre}" />
												</xsl:for-each>
												
											</cajatextoselector>
										</valor>
									</registro>
								</bloque>
								
								<bloque id="PLANTILLA.precarga_Block" visible="false">
									<registro>
										<item>Realizar Precarga</item>
										<valor>
											<cajachequeo id="PLANTILLA.precarga" valor="S" seleccionado=""/>
										</valor>
									</registro>
								</bloque>
								
								<registro>
									<item>Condicion Precarga</item>
									<valor>
										<areatexto id="PLANTILLA.condicion_precarga" valor=""/>
									</valor>
								</registro>
								
									<registro>
										<item>Campo Dependiente</item>
										<valor>
											<cajatexto id="PLANTILLA.patron_dependiente" valor="" />
										</valor>
									</registro>
									
								<!-- Campos para definir si es obligatorio segun otro campo -->
								<registro>
									<item>Obligatorio Según Valor de Campo</item>
									<valor>
										<cajatextoselector id="PLANTILLA.obligatorio_valor_campo" valor=""
											accion="page_cambioObligatorio(this, 'PLANTILLA')">
											<opcion valor="N" texto="NO"/>
											<opcion valor="S" texto="SI"/>
										</cajatextoselector>
									</valor>
								</registro>
								
								<bloque id="PLANTILLA.obligatorio_campo_Block" visible="false">
								
									<!-- Campo dependiente para pasar a la lista dinamica -->
									<registro>
										<item>Campo Dependiente para Obligatoriedad</item>
										<valor>
											<cajatexto id="PLANTILLA.campo_obligatoriedad" valor="" />
										</valor>
									</registro>
									
									<!-- Lista dinamica que define si es obligatorio el campo -->
									<registro>
										<item>Lista Dinamica de Campo Dependiente para Obligatoriedad</item>
										<valor>
											<cajatextoselector id="PLANTILLA.lista_dinamica_obligatoriedad" valor="">
												
												<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
													<xsl:sort select="nombre"/>
													<opcion valor="{id_lista_dinamica}" texto="{nombre}" />
												</xsl:for-each>
												
											</cajatextoselector>
										</valor>
									</registro>
								</bloque>
							
								
								<!-- Campos para definir si es ocultable segun otro campo -->
								<registro>
									<item>Ocultable Según Valor de Campo</item>
									<valor>
										<cajatextoselector id="PLANTILLA.ocultable_valor_campo" valor=""
											accion="page_cambioOcultable(this, 'PLANTILLA')">
											<opcion valor="N" texto="NO"/>
											<opcion valor="S" texto="SI"/>
										</cajatextoselector>
									</valor>
								</registro>
								
								<bloque id="PLANTILLA.ocultable_campo_Block" visible="false">
								
									<!-- Campo dependiente para pasar a la lista dinamica -->
									<registro>
										<item>Campo dependiente para ocultar</item>
										<valor>
											<cajatexto id="PLANTILLA.ocultable_campo" valor="" />
										</valor>
									</registro>
									
									<!-- Lista dinamica que define si es ocultable el campo -->
									<registro>
										<item>Lista Dinamica de Campo Dependiente para Ocultar</item>
										<valor>
											<cajatextoselector id="PLANTILLA.lista_dinamica_ocultable" valor="">
												
												<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
													<xsl:sort select="nombre"/>
													<opcion valor="{id_lista_dinamica}" texto="{nombre}" />
												</xsl:for-each>
												
											</cajatextoselector>
										</valor>
									</registro>
								</bloque>
								
								<!-- Campos para definir si es valido segun otro campo -->
								<registro>
									<item>Validar Según Valor de Campo</item>
									<valor>
										<cajatextoselector id="PLANTILLA.validacion_campo_lista" valor=""
											accion="page_cambioValidacion(this, 'PLANTILLA')">
											<opcion valor="N" texto="NO"/>
											<opcion valor="S" texto="SI"/>
										</cajatextoselector>
									</valor>
								</registro>
								
								<bloque id="PLANTILLA.validacion_campo_Block" visible="false">
								
									<!-- Campo dependiente para pasar a la lista dinamica -->
									<registro>
										<item>Campo Dependiente para Validación</item>
										<valor>
											<cajatexto id="PLANTILLA.campo_validacion" valor="" />
										</valor>
									</registro>
									
									<!-- Lista dinamica que define si es valido el campo -->
									<registro>
										<item>Lista Dinamica de Campo Dependiente para Validación</item>
										<valor>
											<cajatextoselector id="PLANTILLA.lista_dinamica_validacion" valor="">
												
												<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
													<xsl:sort select="nombre"/>
													<opcion valor="{id_lista_dinamica}" texto="{nombre}" />
												</xsl:for-each>
												
											</cajatextoselector>
										</valor>
									</registro>
									
									<!-- Lista dinamica que define si es valido el campo -->
									<registro>
										<item>Mensaje de Error para Validación</item>
										<valor>
											<areatexto id="PLANTILLA.mensaje_validacion" valor=""/>
										</valor>
									</registro>
								</bloque>
							
								<bloque id="PLANTILLA.mensaje_campo_padre_Block" visible="false">
									<registro visible="false">
										<item>Mensaje Campo Padre</item>
										<valor>
											<cajatexto id="PLANTILLA.mensaje_campo_padre" valor="" />
										</valor>
									</registro>
								</bloque>
								
							
								<!--  -->
							
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
										<cajatexto id="PLANTILLA.titulo" valor="REL-NOMBREESTRUCTURA" maxlength='25'/>
									</valor>
								</registro>
								
								<bloque id="PLANTILLA.CONTENT" >&#160;</bloque>
								<bloque id="PLANTILLA.CONTENT_PADRE" >&#160;</bloque>
								
							</bloque>
						</bloque>
				
				</contenido>
					
			</principal>
			<xsl:call-template name="HORARIO_VENTANA" />
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
						<cajatexto id="{$plantilla}.titulo" valor="{$listado/FormatoCampo[estructura_padre='S' and id_estructura=$id_estructura]/titulo}" maxlength='25'/>
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
			<variable id="{$plantilla}.multiplicidad" valor="{//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/multiplicidad}"/>
			<variable id="{$plantilla}.id_estructura" valor="{//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_estructurarelacionada}"/>
			<variable id="{$plantilla}.id_estructura|POSICION" valor="{$posicion}"/>

			<bloque estilo="grupo">
				<subtitulo  texto="{$posicion} {//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/nombre}"/>
				
				<registro>
					<item>Titulo</item>
					<valor>
						<cajatexto id="{$plantilla}.titulo" valor="{titulo}" maxlength='150' />
					</valor>
				</registro>
				
				<registro>
					<item>Usar HTML alterno para el campo Titulo</item>
					<valor>
						<cajatextoselector id="{$plantilla}.usa_titulo_html" valor="{usa_titulo_html}" 
							accion="page_cambioTitulo(this, '{$plantilla}')">
							<opcion valor="N"  texto="NO"/>
							<opcion valor="S"  texto="SI"/>
						</cajatextoselector>
					</valor>
				</registro>
				
				<bloque id="{$plantilla}.titulo_html_Block" >
					<xsl:if test="not(boolean(usa_titulo_html='S'))">
						<xsl:attribute name="display">none</xsl:attribute>
					</xsl:if>
					<registro>
						<item>Titulo HTML</item>
						<valor>
							<areatexto id="{$plantilla}.titulo_html" valor="{titulo_html}"/>
						</valor>
					</registro>
				</bloque>
				
				<registro>
					<item>Texto Ayuda</item>
					<valor>
						<areatexto id="{$plantilla}.ayuda" valor="{ayuda}"/>
					</valor>
				</registro>
				
				<registro>
					<item>Tipo de Valor</item>
					<valor>
						<cajatextoselector id="{$plantilla}.tipo_ingreso" valor="{tipo_ingreso}" accion="page_cambioTipoingreso(this, '{$plantilla}')">
							<opcion valor="0" texto="-- Seleccione --" />
							<xsl:if test="count(//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/id_estructurarelacionada)>0">
									<opcion valor="1" texto="Seleccionar existente" />
							</xsl:if>
							<opcion valor="3" texto="Ingresado por Usuario"  />
							
							<xsl:if test="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/multiplicidad='1...1'">
								<opcion valor="4" texto="Valor Constante"/>
									<opcion valor="5" texto="Variable del Sistema" />
								<opcion valor="12" texto="Valor de Sesion" />
							</xsl:if>
	
							<opcion valor="7" texto="Seleccionar de lista dinamica" />
							<opcion valor="10" texto="Valor Vacio" />
							<opcion valor="11" texto="Automatico" />	
						</cajatextoselector>
					</valor>
				</registro>
				
				<registro>
					<item>Bloquear Edición</item>
					<valor>
						<cajachequeo id="{$plantilla}.bloqueado" valor="S" texto="" seleccionado="{boolean(bloqueado='S')}" accion=""/>
					</valor>
				</registro>
				
				<registro>
					<item>Tipo de Control</item>
					<valor>
						<cajatextoselector id="{$plantilla}.id_operacion" valor="{id_operacion}">
							<opcion valor="0"  texto="-- Ninguna --"/>
							<xsl:for-each select="//obtenerOperacionesControl/listado/Operacion">
								<opcion valor="{id_operacion}" texto="{descripcion}" />
							</xsl:for-each>
						</cajatextoselector>
					</valor>
				</registro>
				
				<registro>
					<item>Obligatorio</item>
					<valor>
						<cajatextoselector id="{$plantilla}.obligatorio" valor="{obligatorio}">
							<xsl:choose>
								<xsl:when test="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo]/obligatorio = 'S'">
									<opcion valor="S"  texto="SI"/>
								</xsl:when>
								<xsl:otherwise>
									<opcion valor="N"  texto="NO"/>
									<opcion valor="S"  texto="SI"/>
								</xsl:otherwise>
							</xsl:choose>
						</cajatextoselector>
					</valor>
				</registro>
				
				<bloque id="{$plantilla}.valorConstante_Block" visible="{boolean(tipo_ingreso=4)}">
					<registro>
						<item>Valor Constante</item>
						<valor>
							<cajatexto id="{$plantilla}.valor_constante" valor="{valor_constante}" />
						</valor>
					</registro>
				</bloque>
				
				<bloque id="{$plantilla}.visualizarValorConstante_Block" visible="{boolean(tipo_ingreso=4)}">					
					<registro visible="{boolean(tipo_ingreso=4)}">
						<item>Visualizar Valor Constante</item>
						<valor>
							<cajachequeo id="{$plantilla}.visualizar_valor_constante" valor="S" texto="" seleccionado="{boolean(visualizar_valor_constante='S')}" accion=""/>
						</valor>
					</registro>
				</bloque>
				
				<bloque id="{$plantilla}.valorSesion_Block" visible="{boolean(tipo_ingreso=12)}">
					<registro>
						<item>Valor de Sesion</item>
						<valor>
							<cajatextoselector id="{$plantilla}.valor_sesion" valor="{valor_sesion}">
								<opcion valor="login" texto="login" />
								<opcion valor="id_negocio" texto="id negocio" />
								<opcion valor="cod_negocio" texto="codigo negocio" />
								<opcion valor="id_persona" texto="id_persona" />
								<opcion valor="tipo_persona" texto="tipo de persona" />
								<opcion valor="tipo_documento" texto="tipo de documento" />
								<opcion valor="identificacion" texto="identificacion persona" />
								<opcion valor="nombre_persona" texto="nombre persona" />
								<opcion valor="id_usuario" texto="id cliente" />
								<opcion valor="nombre_usuario" texto="nombre cliente" />
								<opcion valor="id_administrativo" texto="id administrativo" />
								<opcion valor="tipo_documento_usuario" texto="Tipo de documento cliente" />
								<opcion valor="identificacion_usuario" texto="Identificación cliente" />								
							</cajatextoselector>
						</valor>
					</registro>
				</bloque>
				
				<bloque id="{$plantilla}.visualizarValorSesion_Block" visible="{boolean(tipo_ingreso=12)}">					
					<registro visible="{boolean(tipo_ingreso=12)}">
						<item>Visualizar Valor de Sesion</item>
						<valor>
							<cajachequeo id="{$plantilla}.visualizar_valor_sesion" valor="S" texto="" seleccionado="{boolean(visualizar_valor_sesion='S')}" accion=""/>
						</valor>
					</registro>
				</bloque>
					
				<bloque id="{$plantilla}.id_variable_Block" visible="{boolean(tipo_ingreso=5)}">
					<registro>
						<item>Variable del Sistema</item>
						<valor>
							<cajatextoselector id="{$plantilla}.id_variable" valor="{id_variable}">
								
								<xsl:for-each select="//obtenerVariablesPorModelo/listado/Variable">
									<xsl:sort select="nombre_variable"/>
									<opcion valor="{id_variable}" texto="{nombre_variable}" />
								</xsl:for-each>
								
							</cajatextoselector>
						</valor>
					</registro>
				</bloque>
				
				<bloque id="{$plantilla}.id_listadinamica_Block" visible="{boolean(tipo_ingreso=7)}">
					<registro>
						<item>Lista Dinamica</item>
						<valor>
							<cajatextoselector id="{$plantilla}.id_lista_dinamica" valor="{id_lista_dinamica}">
								
								<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
									<xsl:sort select="nombre"/>
									<opcion valor="{id_lista_dinamica}" texto="{nombre}" />
								</xsl:for-each>
								
							</cajatextoselector>
						</valor>
					</registro>
				</bloque>
				
				<bloque id="{$plantilla}.lista_dinamica_valor_inicial_Block" visible="{tipo_ingreso=7 or tipo_ingreso=3}">
					<registro visible="{tipo_ingreso=7 or tipo_ingreso=3}">
						<item>Valor Inicial</item>
						<valor>
							<cajatextoselector id="{$plantilla}.lista_dinamica_valor_inicial" valor="{lista_dinamica_valor_inicial}">
								<opcion valor="" texto="-- Seleccione --"/>
								<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
									<xsl:sort select="nombre"/>
									<opcion valor="{id_lista_dinamica}" texto="{nombre}" />
								</xsl:for-each>
								
							</cajatextoselector>
						</valor>
					</registro>
				</bloque>
				
				<bloque id="{$plantilla}.precarga_Block" visible="{boolean(count(id_estructura)>0)}">
					<registro>
						<item>Realizar Precarga</item>
						<valor>
							<cajachequeo id="{$plantilla}.precarga" valor="S" seleccionado="{boolean(precarga='S')}"/>
						</valor>
					</registro>
					
					<registro>
						<item>Condicion Precarga</item>
						<valor>
							<areatexto id="{$plantilla}.condicion_precarga" valor="{condicion_precarga}"/>
						</valor>
					</registro>
				
				</bloque>
					<registro>
						<item>Campo Dependiente</item>
						<valor>
							<cajatexto id="{$plantilla}.patron_dependiente" valor="{patron_dependiente}" />
						</valor>
					</registro>
					
				<!-- Campos para definir si es obligatorio segun otro campo -->
				<registro>
					<item>Obligatorio Según Valor de Campo</item>
					<valor>
						<cajatextoselector id="{$plantilla}.obligatorio_valor_campo" valor="{obligatorio_valor_campo}" 
							accion="page_cambioObligatorio(this, '{$plantilla}')">
							<opcion valor="N"  texto="NO"/>
							<opcion valor="S"  texto="SI"/>
						</cajatextoselector>
					</valor>
				</registro>
				
				<bloque id="{$plantilla}.obligatorio_campo_Block" visible="{boolean(obligatorio_valor_campo = 'S')}" >
									
					<!-- Campo dependiente para pasar a la lista dinamica -->
					<registro>
						<item>Campo Dependiente para Obligatoriedad</item>
						<valor>
							<cajatexto id="{$plantilla}.campo_obligatoriedad" valor="{campo_obligatoriedad}" />
						</valor>
					</registro>
					
					<!-- Lista dinamica que define si es obligatorio el campo -->
					<registro>
						<item>Lista Dinamica de Campo Dependiente para Obligatoriedad</item>
						<valor>
							<cajatextoselector id="{$plantilla}.lista_dinamica_obligatoriedad" valor="{lista_dinamica_obligatoriedad}">
								
								<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
									<xsl:sort select="nombre"/>
									<opcion valor="{id_lista_dinamica}" texto="{nombre}" />
								</xsl:for-each>
								
							</cajatextoselector>
						</valor>
					</registro>
					
				</bloque>
				
				<!-- Campos para definir si es ocultable segun otro campo -->
				<registro>
				<item>Ocultable Según Valor de Campo</item>
					<valor>
						<cajatextoselector id="{$plantilla}.ocultable_valor_campo" valor="{ocultable_valor_campo}" 
							accion="page_cambioOcultable(this, '{$plantilla}')">
							<opcion valor="N"  texto="NO"/>
							<opcion valor="S"  texto="SI"/>
						</cajatextoselector>
					</valor>
				</registro>
				
				<bloque id="{$plantilla}.ocultable_campo_Block" visible="{boolean(ocultable_valor_campo = 'S')}" >
									
					<!-- Campo dependiente para pasar a la lista dinamica -->
					<registro>
						<item>Campo dependiente para ocultar</item>
						<valor>
							<cajatexto id="{$plantilla}.ocultable_campo" valor="{ocultable_campo}" />
						</valor>
					</registro>
					
					<!-- Lista dinamica que define si es obligatorio el campo -->
					<registro>
						<item>Lista Dinamica de Campo Dependiente para Ocultar</item>
						<valor>
							<cajatextoselector id="{$plantilla}.lista_dinamica_ocultable" valor="{lista_dinamica_ocultable}">
								
								<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
									<xsl:sort select="nombre"/>
									<opcion valor="{id_lista_dinamica}" texto="{nombre}" />
								</xsl:for-each>
								
							</cajatextoselector>
						</valor>
					</registro>
					
				</bloque>
				
				<!-- Campos para definir si es valido segun otro campo -->
				<registro>
				<item>Validar Según Valor de Campo</item>
					<valor>
						<cajatextoselector id="{$plantilla}.validacion_campo_lista" valor="{validacion_campo_lista}" 
							accion="page_cambioValidacion(this, '{$plantilla}')">
							<opcion valor="N"  texto="NO"/>
							<opcion valor="S"  texto="SI"/>
						</cajatextoselector>
					</valor>
				</registro>
				
				<bloque id="{$plantilla}.validacion_campo_Block" visible="{boolean(validacion_campo_lista = 'S')}" >
									
					<!-- Campo dependiente para pasar a la lista dinamica -->
					<registro>
						<item>Campo dependiente para Validación</item>
						<valor>
							<cajatexto id="{$plantilla}.campo_validacion" valor="{campo_validacion}" />
						</valor>
					</registro>
					
					<!-- Lista dinamica que define si es valido el campo -->
					<registro>
						<item>Lista Dinamica de Campo Dependiente para Validación</item>
						<valor>
							<cajatextoselector id="{$plantilla}.lista_dinamica_validacion" valor="{lista_dinamica_validacion}">
								
								<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
									<xsl:sort select="nombre"/>
									<opcion valor="{id_lista_dinamica}" texto="{nombre}" />
								</xsl:for-each>
								
							</cajatextoselector>
						</valor>
					</registro>
					
					<registro>
						<item>Mensaje de Error para Validación</item>
						<valor>
							<areatexto id="{$plantilla}.mensaje_validacion" valor="{mensaje_validacion}"/>
						</valor>
					</registro>
					
				</bloque>
				<!--  -->
				
				<registro>
					<item>Validar con Servicio Externo</item>
					<valor>
						<cajatextoselector id="{$plantilla}.validacion_servicio" valor="{validacion_servicio}" 
							accion="page_cambioValidacionServicio(this, '{$plantilla}')">
							<opcion valor="N"  texto="NO"/>
							<opcion valor="S"  texto="SI"/>
						</cajatextoselector>
					</valor>
				</registro>
				
				<bloque id="{$plantilla}.validacion_servicio_Block" >
					<xsl:if test="not(boolean(validacion_servicio='S'))">
						<xsl:attribute name="display">none</xsl:attribute>
					</xsl:if>
					<registro>
						<item>Endpoint Servicio</item>
						<valor>
							<cajatexto id="{$plantilla}.endpoint_validacion" valor="{endpoint_validacion}" />
						</valor>
					</registro>
				</bloque>
				
				<xsl:if test="count(formato_campo_list/FormatoCampo[estructura_padre='N'])>0">
					<bloque id="{$plantilla}.mensaje_campo_padre_Block" >
						<registro>
							<item>Mensaje Campo Padre</item>
							<valor>
								<cajatexto id="{$plantilla}.mensaje_campo_padre" valor="{mensaje_campo_padre}" />
							</valor>
						</registro>
					</bloque>
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
