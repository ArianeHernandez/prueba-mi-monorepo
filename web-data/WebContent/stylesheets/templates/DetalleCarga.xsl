<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	
	<!-- TEMPLATE PRINCIPAL -->
	<xsl:template name="DETALLE_CARGA">
		<xsl:param name="validacionesActivas" />
		
		<xsl:choose>
			<xsl:when test="count(//obtenerDatosCargaPorFormato/nodo)>0">
				<xsl:call-template name="SIMPLE_DETALLE_CARGA">
					<xsl:with-param name="validacionesActivas" select="$validacionesActivas"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<b>No hay detalle para la carga.</b>
			</xsl:otherwise>
		</xsl:choose>
		
	</xsl:template>
	
	
	<xsl:template name="SIMPLE_DETALLE_CARGA">
		<xsl:param name="validacionesActivas"/>

		<!-- MEDIOS DE VISUALIZACION -->
		<xsl:if test= "//obtenerFormato/Formato/medio_excel='S' or //obtenerFormato/Formato/medio_pdf='S'">	
			
			<bloque pdfview="false">
				<area_botones estilo='barra'>
					<xsl:if test= "//obtenerFormato/Formato/medio_excel='S'">
						<boton estilo="primary xls" accion="verDetalleExcel({//ID_CARGA}, {//obtenerFormato/Formato/id_formato});">
							<span>ver excel</span>
						</boton>
					</xsl:if>
					
					<xsl:if test= "//obtenerFormato/Formato/medio_pdf='S'">
						<boton estilo="pdf" accion="window.open(CONTEXTPATH +'/carga/ver_carga.do_pdf?id_carga={//ID_CARGA}&amp;id_formato_salida={//obtenerFormato/Formato/id_formato}', 'file')">
							<span>ver pdf</span>
						</boton>
					</xsl:if>
				</area_botones>
			</bloque>
		</xsl:if>		
		
		
		<!-- RESUMEN DE VALIDACIONES -->
		<xsl:variable name="validacionesActivadas" select="//verificarValidacionesPorCarga/ValidacionesPorCarga/validaciones_activadas/*" />
	
		<xsl:if test="count(//verificarValidacionesPorCarga/ValidacionesPorCarga/registros_validados/*/*[.='false' ])>0">								
			<nota>										
				<xsl:for-each select="$validacionesActivadas">
					<xsl:variable name="id_validacion" select= "@name"></xsl:variable>
					
					<xsl:if test="count(//verificarValidacionesPorCarga/ValidacionesPorCarga/registros_validados/*/*[@name=$id_validacion and .='false' ])>0">
						- Se ha(n) encontrado <b><xsl:value-of select="count(//verificarValidacionesPorCarga/ValidacionesPorCarga/registros_validados/*/*[@name=$id_validacion and .='false' ])"/></b> registro(s) que no cumplen la validación   
						<b><xsl:value-of select="descripcion"></xsl:value-of></b><br/>
					</xsl:if> 
					
				</xsl:for-each>
			</nota>
		</xsl:if>
		
		
		<!-- TABLA DE FILTROS -->
		
		<xsl:if test="count(//obtenerFormatosFiltro/listado/FormatoFiltro)>0">
			
			<ocultable visible="false" textovisible="ocultar filtros" textooculto="ver filtros" >
			
			<bloque-contenido>
				<titulo>Filtros de Busqueda</titulo>
				<contenido>
					
					<div id="dc_filtrar">
						<xsl:for-each select="//obtenerFormatosFiltro/listado/FormatoFiltro">				
	
							<registro>
								<item><xsl:value-of select="nombre"/>: </item>
								<valor>
									
									<xsl:choose>
										<xsl:when test="string-length(id_listadinamica)=0">
											<cajatexto id="ValorFiltro:{position()}.valor" valor=""/>
										</xsl:when>
										<xsl:otherwise>
											<cajatextoselector id="ValorFiltro:{position()}.valor" valor="">
											</cajatextoselector>
											<variable id="ValorFiltro:{position()}.valor_id_listadinamica" valor="{id_listadinamica}"/>
										</xsl:otherwise>
									</xsl:choose>

									<variable id="ValorFiltro:{position()}.valor_id_tipocampo" valor="{id_tipocampo}"/>
									<variable id="ValorFiltro:{position()}.id_formatofiltro" valor="{id_formatofiltro}"/>
									<variable id="ValorFiltro:{position()}.valor_nombre" valor="{nombre}"/>
									
									
								</valor>
						 	</registro>
	
					 	</xsl:for-each>
						
					 </div>
					
					<area_botones>
						<boton accion="dc_filtrar()">Filtrar</boton>
					</area_botones>
				 	
				 </contenido>
			</bloque-contenido>
			
			</ocultable>
		</xsl:if>
		<br/>						
		<xsl:for-each select="//obtenerDatosCargaPorFormato/nodo">
<!-- 			<xsl:choose> -->
<!-- 				<xsl:when test="@total_registros=1"> -->
<!-- 					<xsl:choose> -->
<!-- 						<xsl:when test="$validacionesActivas='false'"> -->
<!-- 							<xsl:call-template name="visualizacion-nodo-formulario"> -->
<!-- 								<xsl:with-param name="paginar">true</xsl:with-param> -->
<!-- 								<xsl:with-param name="mostrarValidaciones">false</xsl:with-param> -->
<!-- 								<xsl:with-param name="visible">true</xsl:with-param> -->
<!-- 							</xsl:call-template> -->
<!-- 						</xsl:when> -->
<!-- 						<xsl:otherwise> -->
<!-- 							<xsl:call-template name="visualizacion-nodo-formulario"> -->
<!-- 								<xsl:with-param name="paginar">true</xsl:with-param> -->
<!-- 								<xsl:with-param name="visible">true</xsl:with-param> -->
<!-- 							</xsl:call-template> -->
<!-- 						</xsl:otherwise> -->
<!-- 					</xsl:choose> -->
<!-- 				</xsl:when> -->
<!-- 				<xsl:otherwise> -->
<!-- 					<xsl:choose> -->
<!-- 						<xsl:when test="$validacionesActivas='false'"> -->
<!-- 							<xsl:call-template name="visualizacion-nodo"> -->
<!-- 								<xsl:with-param name="paginar">true</xsl:with-param> -->
<!-- 								<xsl:with-param name="mostrarValidaciones">false</xsl:with-param> -->
<!-- 								<xsl:with-param name="visible">true</xsl:with-param> -->
<!-- 							</xsl:call-template> -->
<!-- 						</xsl:when> -->
<!-- 						<xsl:otherwise> -->
<!-- 							<xsl:call-template name="visualizacion-nodo"> -->
<!-- 								<xsl:with-param name="paginar">true</xsl:with-param> -->
<!-- 								<xsl:with-param name="visible">true</xsl:with-param> -->
<!-- 							</xsl:call-template> -->
<!-- 						</xsl:otherwise> -->
<!-- 					</xsl:choose> -->
<!-- 				</xsl:otherwise> -->
<!-- 			</xsl:choose> -->
			<xsl:choose>
				<xsl:when test="$validacionesActivas='false'">
					<xsl:call-template name="visualizacion-nodo">
						<xsl:with-param name="paginar">true</xsl:with-param>
						<xsl:with-param name="mostrarValidaciones">false</xsl:with-param>
						<xsl:with-param name="visible">true</xsl:with-param>
					</xsl:call-template>
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="visualizacion-nodo">
						<xsl:with-param name="paginar">true</xsl:with-param>
						<xsl:with-param name="visible">true</xsl:with-param>
					</xsl:call-template>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:for-each>
		
		

	</xsl:template>
	
	
	<xsl:template name="DETALLE_CARGA_AJAX">
		<xsl:param name="validacionesActivas" />
		
		<variable id="id_formato_salida" valor="{//FORMATO_SALIDA_SIMPLE_DETALLE}"/>
		<javascript>cargas/detallecarga.js</javascript>
		<stylesheet>detalleCarga/detalleCarga.css</stylesheet>
		<xsl:if test="string-length(//obtenerFormato/Formato/id_componente)>0">
			<javascript>componente/<xsl:value-of select="//obtenerFormato/Formato/id_componente"/>.js</javascript>
		</xsl:if>
		
		<ventana id="detallecarga_ventana" icono="confirmacion">
			<titulo id="titulo_detallecarga_ventana"><xsl:value-of select="//obtenerFormato/Formato/nombre"/></titulo>
			<contenido>

				<bloque estilo="grupo">
					<iframe name="iframe_block" id="iframe_block" src="" scrollable="yes" frameborder="0" style="width: 100%">No se puede visualizar el contenido</iframe>
				</bloque>
				
				<area_botones>
					<div id="iframe_botones" class="btn-group">
					</div>
					
					<boton estilo="danger" accion="osm_setValor('iframe_botones',''); osm_getObjeto('iframe_block').src=''; $('#detallecarga_ventana').hide(); ">Cerrar</boton>
					
				</area_botones>
				
			</contenido>
		</ventana>
		
		
		<ventana id="detallecarga_ventana_progresoExcel" icono="confirmacion">
			<titulo id="titulo_detallecarga_ventana">Descarga de Archivo Excel</titulo>
			<contenido>

				<bloque estilo="grupo">
					
					<div id="barra_progreso_excel">
					
						<div class="center">
							<p><b>Por favor, espere un momento mientras se realiza la generación del archivo.</b></p>
									
							<div class="barra-progreso">
								<div class="barra-progreso-avance" id="progresoexcel">
								</div>
							</div>
						</div>
						
						<p id="tiempo_transcurridoexcel"></p>
					</div>
					
					<div id="msg_barra_progreso_excel" style="display:none">
						<nota>
							El archivo se ha generado exitosamente.
						</nota>
					</div>
					
				</bloque>
				
				<div id="area_botones_descargaexcel" style="display:none">
					<area_botones>
						<boton estilo="primary guardar" accion="descargarArchivoExcel()">descargar</boton>
					</area_botones>
				</div>
				
			</contenido>
		</ventana>
		
		<bloque-contenido>
			<titulo>Detalle de la transacción</titulo>
			<contenido>
				<div class="bloque_pestanas_area">
					<div class="bloque_pestanas_titulos_area">
						<div onclick="cambiarPestana('tab_{//FORMATO_SALIDA_SIMPLE_DETALLE}');" 
						     class="bloque_pestanas_pestana_over" 
						     id="tab_{//FORMATO_SALIDA_SIMPLE_DETALLE}">
							<div><xsl:value-of select="//OSM-ACCION/obtenerFormato/Formato/formatocampobase/titulo"/> ( <span id="Pes_{//FORMATO_SALIDA_SIMPLE_DETALLE}" class="valor_pestana">?</span> )</div>
						</div>
						<xsl:for-each select="//obtenerSubFormatosPorFamilia/listado/Formato">
							<div onclick="cambiarPestana('tab_{id_formato}');" 
							     class="bloque_pestanas_pestana" 
							     id="tab_{id_formato}">
								<div><xsl:value-of select="formatocampobase/titulo"/> ( <span id="Pes_{id_formato}" class="valor_pestana">?</span> )</div>
							</div>
						</xsl:for-each>
					</div>
				</div>
				
				<div id="NOTA_CARGA_DETALLE">
					<nota>
						Cargando Información... <br/> Espere un momento por favor.
					</nota>
				</div>
				
				<div id="CONTENIDO_DETALLE"></div>
				
				<iframe id="IFRAME_DETALLE_CARGA" style="display:none" src="" onload="onloadframe(this);"/>
				
				<div class="paginacion" id="paginacion_detalle_carga" style="display:none">
					<table class="tabla_paginacion" border="0" cellspacing="0" cellpadding="0" width="200px">
						<tr>
							<td width="22px">
								<a onclick="paginacion_inicio()">
									<img src="images/paginacion/btn_inicio.jpg" title="Ir al Primero"/>
								</a>
							</td>
						
							<td width="22px">
								<a onclick="paginacion_anterior()">
									<img src="images/paginacion/btn_atras.jpg" title="Ir al Anterior"/>
								</a>
							</td>
						
							<td>
								<p id="texto_pagina"> Pagina 1</p>
							</td>
		
							<td width="22px">
								<a onclick="paginacion_siguiente()">
									<img src="images/paginacion/btn_siguiente.jpg" title="Ir al Siguiente"/>
								</a>
							</td>
						
							<td width="22px">
								<a onclick="paginacion_final()">
									<img src="images/paginacion/btn_fin.jpg" title="Ir al Ultimo"/>
								</a>
							</td>
						</tr>
					</table>
				</div>
		
		
				
			</contenido>
		</bloque-contenido>

		
	</xsl:template>
	
	
	<!-- TEMPLATES INTERNOS -->
	<xsl:template name="visualizacion-nodo">
		<xsl:param name="visible"/>
		<xsl:param name="paginar"/>
		<xsl:param name="mostrarValidaciones"/>
		<xsl:choose>
			
			<xsl:when test="@type='list'">
				
				<ocultable visible="false" textovisible="ocultar detalle" textooculto="ver detalle" >
					<xsl:if test="$visible='true'">
						<xsl:attribute name="visible">true</xsl:attribute>
					</xsl:if>
					<xsl:if test="count(element)>1">
						<xsl:attribute name="textooculto">ver (<xsl:value-of select="count(element)"/>)</xsl:attribute>
					</xsl:if>
				
					<tabla>
						
						<encabezado>
							<xsl:for-each select="element[1]/nodo">
								<titulo><xsl:value-of select="@name"/></titulo>
							</xsl:for-each>
							
							<xsl:if test="$mostrarValidaciones!='false'">
								<titulo>Validacion</titulo>
								<titulo>.</titulo>
							</xsl:if>
							
						</encabezado>
						
						<xsl:for-each select="element">
							<xsl:variable name="id_registro" select="@ID"/>
							<xsl:variable name="validacionesActivadasPorRegistro" select="//verificarValidacionesPorCarga/ValidacionesPorCarga/registros_validados/*[@name=$id_registro]/*[.='false']" />
							
							<fila>
								
								<xsl:for-each select="nodo">
									<xsl:variable name="id_registro_vista_corta" select="@ID_HIJO"/>
									<xsl:variable name="id_registro_vista_larga" select="@ID"/>
									
									<xsl:variable name="validacionesActivadasPorVistaLarga" select="//verificarValidacionesPorCarga/ValidacionesPorCarga/registros_validados/*[@name=$id_registro_vista_larga]/*[.='false']" />
									<xsl:variable name="validacionesActivadasPorVistaCorta" select="//verificarValidacionesPorCarga/ValidacionesPorCarga/registros_validados/*[@name=$id_registro_vista_corta]/*[.='false']" />
				
									<valor>
										
										<xsl:if test="$mostrarValidaciones!='false' and ( count($validacionesActivadasPorRegistro)>0 
																							or count($validacionesActivadasPorVistaCorta)>0 
																							or count($validacionesActivadasPorVistaLarga)>0)">
											<xsl:attribute name="class">tabla_fila_error</xsl:attribute>
										</xsl:if>
										
										<xsl:call-template name="visualizacion-nodo">
											<xsl:with-param name="mostrarValidaciones" select="$mostrarValidaciones"/>
										</xsl:call-template>
										
										
										<!-- SOLO SE MUESTRAN LAS VALIDACIONES EN EL MISMO CAMPO CUANDO ES VISTA CORTA -->
										<xsl:if test="$mostrarValidaciones!='false'">
											<xsl:if test="count($validacionesActivadasPorVistaCorta)>0">
												<div>
													<b>Validación:</b><br/> 
													<xsl:for-each select="$validacionesActivadasPorVistaCorta">
														<xsl:variable name="id_validacion" select= "@name"></xsl:variable>
														- <xsl:value-of select="//verificarValidacionesPorCarga/ValidacionesPorCarga/validaciones_activadas/*[@name=$id_validacion]/descripcion"/><br/> 
													</xsl:for-each>
												</div>
											</xsl:if>	
										</xsl:if>
										
									</valor>	
								</xsl:for-each>
								
								<xsl:if test="$mostrarValidaciones!='false'">
									<valor>
										<xsl:if test="count($validacionesActivadasPorRegistro)>0">
											<xsl:attribute name="class">tabla_fila_error</xsl:attribute>
											<xsl:for-each select="$validacionesActivadasPorRegistro">
												<xsl:variable name="id_validacion" select= "@name"></xsl:variable>
												- <xsl:value-of select="//verificarValidacionesPorCarga/ValidacionesPorCarga/validaciones_activadas/*[@name=$id_validacion]/descripcion"/><br/>
											</xsl:for-each>
										</xsl:if>	
									</valor>

									<valor>
										
										<xsl:variable name="id_componente" select="//obtenerFormato/Formato/id_componente"/>
										
										<xsl:if test="string-length(//obtenerFormato/Formato/id_componente)>0">
											<div id="cmp_{$id_registro}" class="reg_detalle" style="display:none">
												<boton estilo="primary" accion="CMP_ACCION({$id_registro})" ><xsl:value-of select="//obtenerComponentes/listado/Componente[id_componente=$id_componente]/descripcion"/></boton>
											</div>
										</xsl:if>
										
									</valor>


								</xsl:if>
							</fila>
						</xsl:for-each>
					</tabla>
				
					
					<xsl:if test="$paginar='true'">
						
						<input type="hidden" id="NUMERO_PAGINAS" value="{floor( ( number(//obtenerDatosCargaPorFormato/nodo/@total_registros) + number(//TAMANO_PAGINA) - 1 ) div number(//TAMANO_PAGINA) )}" />
						
					</xsl:if>
					
				</ocultable>
					
			</xsl:when>
			
			<xsl:when test="@type='object'">
				<xsl:variable name="id_registro" select="@ID"/>
				<xsl:variable name="validacionesActivadasPorRegistro" select="//verificarValidacionesPorCarga/ValidacionesPorCarga/registros_validados/*[@name=$id_registro]/*[.='false']" />
							
				<ocultable visible="false" textovisible="ocultar" textooculto="ver" >
				
					<tabla>
						<encabezado>
							<xsl:for-each select="nodo">
								<titulo><xsl:value-of select="@name"/></titulo>
							</xsl:for-each>
							
							<xsl:if test="$mostrarValidaciones!='false'">
								<titulo>Validacion</titulo>
							</xsl:if>
						</encabezado>
	
						<fila>
							<xsl:for-each select="nodo">
								<xsl:variable name="id_registro_vista_corta" select="@ID_HIJO"/>
								<xsl:variable name="id_registro_vista_larga" select="@ID"/>
									
								<xsl:variable name="validacionesActivadasPorVistaLarga" select="//verificarValidacionesPorCarga/ValidacionesPorCarga/registros_validados/*[@name=$id_registro_vista_larga]/*[.='false']" />
								<xsl:variable name="validacionesActivadasPorVistaCorta" select="//verificarValidacionesPorCarga/ValidacionesPorCarga/registros_validados/*[@name=$id_registro_vista_corta]/*[.='false']" />
				
							
								<valor >
									<xsl:if test="$mostrarValidaciones!='false' and ( count($validacionesActivadasPorRegistro)>0 
																							or count($validacionesActivadasPorVistaCorta)>0 
																							or count($validacionesActivadasPorVistaLarga)>0)">
											<xsl:attribute name="class">tabla_fila_error</xsl:attribute>
									</xsl:if>
									
									<xsl:call-template name="visualizacion-nodo">
											<xsl:with-param name="mostrarValidaciones" select="$mostrarValidaciones"/>
										</xsl:call-template>
									
									<!-- SOLO SE MUESTRAN LAS VALIDACIONES EN EL MISMO CAMPO CUANDO ES VISTA CORTA -->
									<xsl:if test="$mostrarValidaciones!='false'">
										<xsl:if test="count($validacionesActivadasPorVistaCorta)>0">
											<div>
												<b>Validacion(es):</b><br/> 
												<xsl:for-each select="$validacionesActivadasPorVistaCorta">
													<xsl:variable name="id_validacion" select= "@name"></xsl:variable>
													- <xsl:value-of select="//verificarValidacionesPorCarga/ValidacionesPorCarga/validaciones_activadas/*[@name=$id_validacion]/descripcion"/><br/> 
												</xsl:for-each>
											</div>
										</xsl:if>	
									</xsl:if>
									
								</valor>
							</xsl:for-each>
							
							<xsl:if test="$mostrarValidaciones!='false'">
								<valor>
									<xsl:if test="count($validacionesActivadasPorRegistro)>0">
											<xsl:attribute name="class">tabla_fila_error</xsl:attribute>
											<xsl:for-each select="$validacionesActivadasPorRegistro">
												<xsl:variable name="id_validacion" select= "@name"></xsl:variable>
												- <xsl:value-of select="//verificarValidacionesPorCarga/ValidacionesPorCarga/validaciones_activadas/*[@name=$id_validacion]/descripcion"/><br/>
											</xsl:for-each>
									</xsl:if>	
								</valor>
							</xsl:if>	
						</fila>
					</tabla>
				
				</ocultable>
				
				
			</xsl:when>
			
			<xsl:otherwise>
				<div style="color:{@colorletra}; background-color: {@colorfondo}; padding: 5px"><xsl:value-of select="@value"/></div>
			</xsl:otherwise>
			
			
			
		</xsl:choose>
		
		
	</xsl:template>
	
	<xsl:template name="visualizacion-nodo-formulario">
		<xsl:param name="visible"/>
		<xsl:param name="paginar"/>
		<xsl:param name="mostrarValidaciones"/>
		<xsl:choose>
			
			<xsl:when test="@type='list'">
				
				<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal bloque-seccion-estructura">
				<bloque-contenido>
					<titulo estilo="text-center" icono="edicion"><xsl:value-of select="@name"/></titulo>
					<contenido>
						<xsl:for-each select="element">
							<xsl:variable name="id_registro" select="@ID"/>
							<xsl:variable name="validacionesActivadasPorRegistro" select="//verificarValidacionesPorCarga/ValidacionesPorCarga/registros_validados/*[@name=$id_registro]/*[.='false']" />
							
								<xsl:for-each select="nodo">
									<xsl:variable name="id_registro_vista_corta" select="@ID_HIJO"/>
									<xsl:variable name="id_registro_vista_larga" select="@ID"/>
									
									<xsl:call-template name="visualizacion-nodo-formulario">
										<xsl:with-param name="mostrarValidaciones" select="$mostrarValidaciones"/>
									</xsl:call-template>

								</xsl:for-each>
								
						</xsl:for-each>
						

					</contenido>
				</bloque-contenido>
				</div>
				
			</xsl:when>
			
			<xsl:when test="@type='object'">
				<xsl:variable name="id_registro" select="@ID"/>
				<xsl:variable name="validacionesActivadasPorRegistro" select="//verificarValidacionesPorCarga/ValidacionesPorCarga/registros_validados/*[@name=$id_registro]/*[.='false']" />
				<div class="bloque-seccion-estructura">			
				<bloque-contenido>
					<titulo icono="edicion"><xsl:value-of select="@name"/></titulo>
					<div class="divider"></div>
					<contenido>
						<div class="divider"></div>
						<div class="panel-col form-horizontal">
								<xsl:for-each select="nodo">
									<xsl:variable name="id_registro_vista_corta" select="@ID_HIJO"/>
									<xsl:variable name="id_registro_vista_larga" select="@ID"/>
									
									<xsl:call-template name="visualizacion-nodo-formulario">
										<xsl:with-param name="mostrarValidaciones" select="$mostrarValidaciones"/>
									</xsl:call-template>

								</xsl:for-each>

						</div>
					</contenido>
				</bloque-contenido>
				</div>
				
			</xsl:when>
			
			<xsl:otherwise>
				<registro>
					<item>
						<xsl:value-of select="@name"/>
					</item>
					<valor>
						<input autocomplete="off" value="{@value}" type="text" class="form-control" readonly="readonly"></input>
					</valor>
				</registro>
			</xsl:otherwise>
			
			
			
		</xsl:choose>
		
		
	</xsl:template>
	
	
</xsl:stylesheet>