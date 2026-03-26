<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/horario.xsl"/>
	<xsl:include href="context://stylesheets/templates/VariablesLiberacion.xsl"/>
	<xsl:include href="context://stylesheets/templates/CamposCalculados.xsl"/>
	<xsl:include href="context://stylesheets/templates/EstilosFormato.xsl"/>
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Edición de Formato de Entrada">
			<javascript>admin/4.2.js</javascript>
			<stylesheet>4.2.css</stylesheet>
			<principal>
				<titulo icono="formatos">Edición de Formato de Entrada</titulo>
				<contenido>
					
					<bloque-pestanas>
						<!-- PESTANA PARAMETROS DEL FORMATO -->
						<pestana titulo="PARAMETROS DEL FORMATO">
							<formulario id="form_edicion" destino="formatos/4.3.do">
								<xsl:variable name="id_campo_totalizador" select="//obtenerFormato/Formato/id_campo_totalizador"/>
								<xsl:variable name="nombre_campo_totalizador" select="//obtenerFormatoCampoCompleto/Formato/Campo[id_campo=$id_campo_totalizador]/nombre"/>
										
								<variable id="id_totalizador" valor="{$id_campo_totalizador}"/>
								<variable id="valor_totalizador" valor="{$nombre_campo_totalizador}"/>
							
								<variable id="Formato.id_formato" valor="{//obtenerFormato/Formato/id_formato}"/>
								<variable id="Formato.id_modelo" valor="{//obtenerFormato/Formato/id_modelo}"/>
								<variable id="Formato.id_horario" valor="{//obtenerFormato/Formato/id_horario}"/>
								<variable id="id_horario" valor="{//obtenerFormato/Formato/id_horario}"/>
								<variable id="Formato.id_estructura" valor="{//obtenerFormato/Formato/id_estructura}"/>
								<variable id="Formato.id_campo_totalizador" valor="{//obtenerFormato/Formato/id_campo_totalizador}"/>
								
						
								<bloque-pestanas>
									<pestana titulo="Información Basica">
												
										<registro>
											<item>Nombre del Formato</item>
											<valor>
												<cajatexto id="Formato.nombre" valor="{//obtenerFormato/Formato/nombre}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/>
											</valor>
										</registro>
										
										<registro>
											<item>Descripción</item>
											<valor>
												<areatexto id="Formato.descripcion" valor="{//obtenerFormato/Formato/descripcion}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/>
											</valor>
										</registro>
										
										<variable id="Formato.tipoformato" valor="E"/>
										
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
											<item>Formato Salida por defecto</item>
											<valor>
												<cajatextoselector id="Formato.idformato_salida" valor="{//obtenerFormato/Formato/idformato_salida}">
													<opcion valor="" texto="-- Seleccione --"/>
													<xsl:for-each select="//formatos_salida/obtenerFormatosPorTipo/Listado/Formato">
														<opcion valor="{id_formato}" texto="{nombre}" />
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
										
										<registro>
											<item>Validacion formato menu</item>
											<valor>
												<cajatextoselector id="Formato.validar_formato_menu" valor="{//obtenerFormato/Formato/validar_formato_menu}">
													<opcion valor="" texto="-- Seleccione --"/>
													<xsl:for-each select="//obtenerListasDinamicas/listado/ListaDinamica">
														<opcion valor="{id_lista_dinamica}" texto="{nombre}" />
													</xsl:for-each>
												</cajatextoselector>
											</valor>
										</registro>
										
										<registro>
											<item>Autorizacion automatica</item>
											<valor>
												<cajachequeo id="Formato.autorizacion_automatica" valor="S" seleccionado="{boolean(//obtenerFormato/Formato/autorizacion_automatica='S')}"/>
											</valor>
										</registro>
										
										<registro>
											<item>Permitir liberacion de cargas fuera del horario de atencion</item>
											<valor>
												<cajachequeo id="Formato.horario_estricto" valor="N" seleccionado="{boolean(//obtenerFormato/Formato/horario_estricto='N')}"/>
											</valor>
										</registro>
										
										<registro>
											<item>Permitir archivos adjuntos</item>
											<valor>
												<cajachequeo id="Formato.usa_archivos_adjuntos" valor="S" seleccionado="{boolean(//obtenerFormato/Formato/usa_archivos_adjuntos='S')}" accion="toggleAdjuntosFormulario()"/>
											</valor>
										</registro>
										
										<bloque id="usa_adjuntos_formulario_block" visible="string-length(//obtenerFormato/Formato/usa_archivos_adjuntos)=0 or boolean(//obtenerFormato/Formato/usa_archivos_adjuntos != 'S')">
											<registro visible="string-length(//obtenerFormato/Formato/usa_archivos_adjuntos)=0 or boolean(//obtenerFormato/Formato/usa_archivos_adjuntos != 'S')">
												<item>Permitir adjuntos en formulario</item>
												<valor>
													<cajachequeo id="Formato.usa_adjuntos_formulario" valor="S" seleccionado="{boolean(//obtenerFormato/Formato/usa_adjuntos_formulario='S')}"/>
												</valor>
											</registro>
										</bloque>
										
										<bloque id="usa_adjuntos_adicionales_block" visible="string-length(//obtenerFormato/Formato/usa_adjuntos_formulario)=1 or boolean(//obtenerFormato/Formato/usa_adjuntos_formulario = 'S')">
											<registro visible="string-length(//obtenerFormato/Formato/usa_adjuntos_formulario)=1 or boolean(//obtenerFormato/Formato/usa_adjuntos_formulario = 'S')">
												<item>Mensaje información adjuntos</item>
												<valor> 
													<areatexto id="Formato.info_adjuntos_reporte" valor="{//obtenerFormato/Formato/info_adjuntos_reporte}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/> 
												</valor>
											</registro>
											<registro visible="string-length(//obtenerFormato/Formato/usa_adjuntos_formulario)=1 or boolean(//obtenerFormato/Formato/usa_adjuntos_formulario = 'S')">
												<item>Extensión adjuntos</item>
												<valor> 
													<cajatexto id="Formato.extensiones_adjuntos_reporte" valor="{//obtenerFormato/Formato/extensiones_adjuntos_reporte}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/> 
												</valor>
											</registro>
											<registro visible="string-length(//obtenerFormato/Formato/usa_adjuntos_formulario)=1 or boolean(//obtenerFormato/Formato/usa_adjuntos_formulario = 'S')">
												<item>Límite de adjuntos</item>
												<valor> 
													<cajatexto id="Formato.limite_adjuntos_reporte" valor="{//obtenerFormato/Formato/limite_adjuntos_reporte}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/> 
												</valor>
											</registro>
											<registro visible="string-length(//obtenerFormato/Formato/usa_adjuntos_formulario)=1 or boolean(//obtenerFormato/Formato/usa_adjuntos_formulario = 'S')">
												<item>Título de adjuntos</item>
												<valor> 
													<cajatexto id="Formato.titulo_seccion_adjuntos_reporte" valor="{//obtenerFormato/Formato/titulo_seccion_adjuntos_reporte}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/> 
												</valor>
											</registro>
											<registro visible="string-length(//obtenerFormato/Formato/usa_adjuntos_formulario)=1 or boolean(//obtenerFormato/Formato/usa_adjuntos_formulario = 'S')">
												<item>Mensaje de ayuda de adjuntos</item>
												<valor> 
													<areatexto id="Formato.ayuda_adjuntos_reporte" valor="{//obtenerFormato/Formato/ayuda_adjuntos_reporte}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/> 
												</valor>
											</registro>
										</bloque>
										
										<registro>
											<item>Tiene cargas relacionadas</item>
											<valor>
												<cajachequeo id="Formato.cargas_relacionadas" valor="S" seleccionado="{boolean(//obtenerFormato/Formato/cargas_relacionadas ='S')}" accion="toggleCargasRelacionadas()"/>
											</valor>
										</registro>
										
										<registro>
											<item>Requiere archivos adjuntos luego de la liberacion</item>
											<valor>
												<cajachequeo id="Formato.archivos_adjuntos_posteriores" valor="S" seleccionado="{boolean(//obtenerFormato/Formato/archivos_adjuntos_posteriores ='S')}" />
											</valor>
										</registro>
										
										<registro>
											<item>Aplicar Automáticamente</item>
											<valor>
												<cajachequeo id="Formato.aplicar_automatico" valor="S" seleccionado="{boolean(//obtenerFormato/Formato/aplicar_automatico='S')}"/>
											</valor>
										</registro>
										
										<!-- 
											Estrucuras aplicadas automaticamente al subir la carga
										 -->
										
										<registro>
											<item>Estructuras registradas automáticamente</item>
											<valor>
												<xsl:variable name="estructuras" select="//obtenerEstructurasPorPersona/listado/Estructura"/>
												<div id="estructuras_aplicar">
													<xsl:for-each select="//obtenerEstructurasAplicar/Listado/Integer">
														<xsl:variable name="id" select="."/>
														<div class="cuadro_fila" id="fila_{.}" name="fila_negocio" >
															<div class="cuadro_fila_item w20">
																<div class="eliminar" onclick="eliminarEstructura('{.}')" />
															</div>
															<div class="cuadro_fila_item w250">
																<p>
																	<xsl:value-of select="$estructuras[id_estructura=$id]/nombre"/>
																</p>
															</div>
															<input type="hidden" name="estructurasAplicar:[{position()}]" id="estructurasAplicar:[{position()}]"
																value="{.}" />
														</div>
													</xsl:for-each>
												</div>
												<cajatextoselector id="estructuras" accion="agregarEstructura(this);" >
													<opcion valor="" texto="-- Seleccione --" />
													<xsl:for-each select="$estructuras">
														<opcion valor="{id_estructura}" texto="{nombre}" />
													</xsl:for-each>
												</cajatextoselector>
											</valor>
										</registro>
										
										<registro>
											<item>HTML Encabezado</item>
											<valor>
												<areatexto id="Formato.encabezado_html" valor="{//obtenerFormato/Formato/encabezado_html}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/>
											</valor>
										</registro>
										
										<registro>
											<item>HTML Pie de pagina</item>
											<valor>
												<areatexto id="Formato.pie_html" valor="{//obtenerFormato/Formato/pie_html}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/>
											</valor>
										</registro>
										
										<registro>
											<item>Mensaje de alerta</item>
											<valor>
												<areatexto id="Formato.alerta_html" valor="{//obtenerFormato/Formato/alerta_html}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/>
											</valor>
										</registro>
										
										<registro>
											<item>Mensaje al realizar liberación</item>
											<valor>
												<areatexto id="Formato.mensaje_liberacion" valor="{//obtenerFormato/Formato/mensaje_liberacion}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/>
											</valor>
										</registro>
										
										<registro>
											<item>Valor del Tramite</item>
											<valor>
												<cajatexto id="Formato.valortramite" valor="{//obtenerFormato/Formato/valortramite}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/>
											</valor>
										</registro>
										
										<registro>
											<item>Endpoint de Validación de Campos</item>
											<valor>
												<cajatexto id="Formato.endpoint_validacion" valor="{//obtenerFormato/Formato/endpoint_validacion}" desactivado="{//obtenerFormato/Formato/bloqueado = 'S'}"/>
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
										
										
										<variable id="n_estructuras" valor="{count(//obtenerEstructurasAplicar/Listado/Integer)}" />
										
										<!-- PLANTILLA PARA MOSTRAR NEGOCIOS -->
										<div id="negocio_plantilla" style="display:none">
											
											<div class="cuadro_fila" id="fila_[ 1 ]" name="fila_negocio" >
												<div class="cuadro_fila_item w20">
													<div class="eliminar" onclick="eliminarEstructura([ 1 ])" />
												</div>
												<div class="cuadro_fila_item w250">
													<p>
														[ 2 ]
													</p>
												</div>
												<input type="hidden" name="estructurasAplicar:[[ 3 ]]" id="estructurasAplicar:[[ 3 ]]"
													value="[ 1 ]" />
											</div>
											
										</div>
												
										<!--Esto se debe ocultar cuando el checkbox de cargas relacionadas este desactivado-->
										<bloque id="bloque_cargas_relacionadas" estilo="grupo" display ="none">
										<subtitulo texto="Lote relacionada"/>
											<registro>
											<item>Formato relacionado</item>
											<valor>
												<cajatextoselector id="Formato.idformato_carga_relacionada" valor="{//obtenerFormato/Formato/idformato_carga_relacionada}">
													<opcion valor="" texto="-- Seleccione --"/>
													<xsl:for-each select="//formatos_entrada/obtenerFormatosPorTipo/Listado/Formato">
														<opcion valor="{id_formato}" texto="{nombre}" />
													</xsl:for-each>
												</cajatextoselector>
											</valor>
											</registro>
											
											<registro>
											<item>Vigencia del documento relacionado</item>
											<valor>
												<cajatextoselector id="Formato.diasvigencia_carga_relacionada" valor="{//obtenerFormato/Formato/diasvigencia_carga_relacionada}">
													<opcion valor="" texto="-- Seleccione --"/>
													<opcion valor="1" texto="1 día"/>
													<opcion valor="7" texto="1 semana"/>
													<opcion valor="30" texto="1 mes"/>
													<opcion valor="60" texto="1 bimestre"/>
													<opcion valor="90" texto="1 trimestre"/>
													<opcion valor="182" texto="1 semestre"/>
													<opcion valor="365" texto="1 año"/>
													<opcion valor="0" texto="Todos"/>
													
												</cajatextoselector>
											</valor>
											</registro>
											
									
										</bloque>
																		
									</pestana>
									
									<pestana titulo="Información de Acceso">
									
										<div>
											<xsl:if test="string-length(//obtenerFormato/Formato/formato_externo)>0">
												<xsl:attribute name="style">display:none</xsl:attribute>
											</xsl:if>
											
											
											<registro>
												<item>Solicita tabla de control a Usuario</item>
												<valor>
													<cajachequeo id="Formato.control_usuario" valor="S" seleccionado="{boolean(//obtenerFormato/Formato/control_usuario='S')}"/>
												</valor>
											</registro>
											
											<registro>
												<item>Numero de registros por Carga</item>
												<valor>
													<cajatexto id="Formato.registrosporcarga">
														<xsl:attribute name="valor">
															<xsl:choose>
																<xsl:when test="count(//obtenerFormato/Formato/registrosporcarga)>0">
																 	<xsl:value-of select="//obtenerFormato/Formato/registrosporcarga"/>
																</xsl:when>
																<xsl:otherwise>2000</xsl:otherwise>
															</xsl:choose>
														</xsl:attribute>
													</cajatexto>
												</valor>
											</registro>
											
											<registro>
												<item>Medios Disponibles</item>
												<valor>
													<cajachequeo id="Formato.medio_formulario" valor="S" texto="Interactivo" seleccionado="{boolean(//obtenerFormato/Formato/medio_formulario='S')}"/>
													<cajachequeo id="Formato.medio_excel" valor="S" texto="Masiva (Excel)" seleccionado="{boolean(//obtenerFormato/Formato/medio_excel='S')}"/>
												</valor>
											</registro>
											
										</div>
											
										<registro>
											<item>El usuario puede programar liberación</item>
											<valor>
												<cajachequeo id="Formato.prog_liberacion" valor="S" seleccionado="{boolean(//obtenerFormato/Formato/prog_liberacion='S')}"/>
											</valor>
										</registro>
										
										<registro>
											<item>Horario de Atencion</item>
											<valor>
												<xsl:call-template name="HORARIO" />
											</valor>
										</registro>
										
										
									</pestana>

									<xsl:call-template name="VARIABLES_LIBERACION"/>
									<xsl:call-template name="CAMPOS_CALCULADOS"/>
									<xsl:call-template name="ESTILOS_FORMATO"/>
								
								</bloque-pestanas>
							
								<area_botones>
									<xsl:if test="not(//obtenerFormato/Formato/bloqueado != 'S')">
										<boton estilo="eliminar" validacion="confirm('¿Está seguro de eliminar el formato?')" formulario="form_eliminar">Eliminar</boton>
									</xsl:if>	
								
									<xsl:if test="not(//obtenerFormato/Formato/bloqueado = 'S')">
										<boton estilo="guardar" formulario="form_edicion" validacion="page_validarGuardar()">Guardar Parametros Formato</boton>
									</xsl:if>
								</area_botones>
							
						
							
						</formulario>
						</pestana> 
						
						
						<xsl:if test="count(//obtenerFormato/Formato/id_formato)>0 and string-length(//obtenerFormato/Formato/formato_externo)=0">
							<!-- PESTANA DE CAMPOS DEL FORMATO -->
							<pestana titulo="ESTRUCTURA DEL FORMATO" action="mostrarEdicionEstructuraFormato();">
								<formulario id="form_edicion_estructura" destino="formatos/4.2.1.do">
									<variable id= "id_formato" valor="{//obtenerFormato/Formato/id_formato}"/>
								</formulario>
							</pestana>
						</xsl:if>
						
					</bloque-pestanas>
				
					
					<formulario id="form_eliminar" destino="formatos/4.7.do">
						<variable id="id_formato" valor="{//obtenerFormato/Formato/id_formato}"/>
					</formulario>
						
					<area_botones>
						<boton estilo="cancelar" destino="formatos/4.1.do">Cancelar</boton>
					</area_botones>
				
				</contenido>
					
			</principal>
			<xsl:call-template name="HORARIO_VENTANA" />
		</pagina>
		
	</xsl:template>
	

</xsl:stylesheet>
