<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Edición Lista de Valores - {//obtenerListaValores/ListaValores/nombre}">
			<javascript>admin/3.2.js</javascript>
			
			<principal>
				<titulo icono="listavalores">Edición Lista de Valores - <xsl:value-of select="//obtenerListaValores/ListaValores/nombre"/></titulo>
				<contenido>
					
					<formulario id="form_edicion" destino="listasvalores/3.3.do">
						<variable id="ListaValores.id_lista_valores" valor="{//obtenerListaValores/ListaValores/id_lista_valores}"/>
						<variable id="ListaValores.id_modelo" valor="{//obtenerListaValores/ListaValores/id_modelo}"/>
						
						<bloque-contenido>
							<titulo icono="edicion">Información Basica</titulo>
							<contenido>
								
								<xsl:if test="string-length(//obtenerListaValores/ListaValores/id_lista_valores)>0">
									<registro>
										<item>Id</item>
										<valor>
											<parrafo estilo="resaltado" texto="L{//obtenerListaValores/ListaValores/id_lista_valores}" />
										</valor>
									</registro>
								</xsl:if>
								
								<registro>
									<item>Nombre de la Lista de Valores</item>
									<valor>
										<cajatexto id="ListaValores.nombre" valor="{//obtenerListaValores/ListaValores/nombre}" />
									</valor>
								</registro>
								
								<registro>
									<item>Descripción</item>
									<valor>
										<areatexto id="ListaValores.descripcion" valor="{//obtenerListaValores/ListaValores/descripcion}" />
									</valor>
								</registro>
								
								<registro>
									<item>Tipo de Dato</item>
									<valor>
										
										<xsl:choose>
											
											<xsl:when test="string-length(//obtenerListaValores/ListaValores/id_lista_valores)=0">
												<cajatextoselector id="ListaValores.id_tipocampo" valor="{//obtenerListaValores/ListaValores/id_tipocampo}">
													<opcion valor="" texto="-- Seleccione --"/>
													
													<xsl:for-each select="//TipoCampo[number(id_tipocampo)>0]">
														<xsl:sort select="nombre"/>
														<opcion valor="{id_tipocampo}" texto="{nombre}"/>
													</xsl:for-each>
												</cajatextoselector>
											</xsl:when>
											
											<xsl:otherwise>
												<variable id="ListaValores.id_tipocampo" valor="{//obtenerListaValores/ListaValores/id_tipocampo}"/>
												<parrafo texto="{//TipoCampo[id_tipocampo=//obtenerListaValores/ListaValores/id_tipocampo]/nombre}"/>
											</xsl:otherwise>
											
										</xsl:choose>
										
									</valor>
								</registro>
								<registro>
									<item>Campo Ordenamiento</item>
									<valor>
										<cajatextoselector id="ListaValores.criterio_orden" valor="{//obtenerListaValores/ListaValores/criterio_orden}">
											<opcion valor="V" valordefecto="true">Por Valor</opcion>
											<opcion valor="I">Por ID</opcion>
										</cajatextoselector>
									</valor>
								</registro>
								
								<registro>
									<item>Tipo de orden</item>
									<valor>
										<cajatextoselector id="ListaValores.tipo_orden" valor="{//obtenerListaValores/ListaValores/tipo_orden}">
											<opcion valor="A" valordefecto="true">Ascendente</opcion>
											<opcion valor="D">Descendente</opcion>
										</cajatextoselector>
									</valor>
								</registro>
								<!-- Actualizacion por base de datos -->
								<ocultable visible="false" textooculto="Opciones de Sincronización" textovisible="Ocultar">
									<bloque>
									<parrafo estilo="resaltado">Los datos de la lista de valores se obtienen por medio de una consulta sobre la base de datos.</parrafo>
									<registro>
										<item>Identificación</item>
										<valor>
											<cajatexto id="ListaValores.c_id" valor="{//obtenerListaValores/ListaValores/c_id}"/>
										</valor>
									</registro>
									<registro>
										<item>Etiqueta</item>
										<valor>
											<cajatexto id="ListaValores.c_nombre" valor="{//obtenerListaValores/ListaValores/c_nombre}"/>
										</valor>
									</registro>
									<registro>
										<item>Fuente</item>
										<valor>
											<areatexto id="ListaValores.consulta" valor="{//obtenerListaValores/ListaValores/consulta}"/>
										</valor>
									</registro>
									<registro>
										<item>Intervalo Actualización</item>
										<valor>
											<cajatexto id="ListaValores.intervalo_actualizacion" valor="{//obtenerListaValores/ListaValores/intervalo_actualizacion}"/>
										</valor>
									</registro>
									<registro>
										<item>Nombre Conexión</item>
										<valor>
											<cajatextoselector id="ListaValores.base_datos" valor="{//obtenerListaValores/ListaValores/base_datos}">
												<opcion valor="" texto="-- Seleccione --"/>
												<xsl:for-each select="//obtenerConexiones/listado/String">
													<opcion valor="{.}" texto="{.}"/>	
												</xsl:for-each>
											</cajatextoselector>
										</valor>
									</registro>
									</bloque>
								</ocultable>
							</contenido>
						</bloque-contenido>
						
						<variable id="var.num_elemento" valor="{count(//ValorLista)+1}"/>
						
						<bloque visible="{boolean(not(string-length(//obtenerListaValores/ListaValores/id_lista_valores)=0))}">
							<bloque-contenido>
								<titulo icono="edicion">Valores</titulo>
								<contenido>
									
									<xsl:for-each select="//num_elemento">
										<xsl:variable name="pp" select="position()"/>
										
										<bloque id="BloqueCampo_{position()}" visible="{boolean(count(//obtenerValoresLV/listado/ValorLista[position()=$pp]/id)>0)}">
											
											<variable id="bloqueCampoVisible_{position()}" valor="{boolean(count(//obtenerValoresLV/listado/ValorLista[position()=$pp]/id)>0)}"/>
											
											<bloque estilo="grupo">
													
												<registro>
													<item>Identificación (<xsl:value-of select="//TipoCampo[id_tipocampo=//obtenerListaValores/ListaValores/id_tipocampo]/nombre"/>) </item>
													<valor>
														<input type="text" class="cajatexto campo_id" autocomplete="off" name="ValorLista:{position()}.id" id="ValorLista:{position()}.id" value="{//obtenerValoresLV/listado/ValorLista[position()=$pp]/id}" />
													</valor>
												</registro>
												
												<registro>
													<item>Nombre</item>
													<valor>
														<cajatexto id="ValorLista:{position()}.nombre" valor="{//obtenerValoresLV/listado/ValorLista[position()=$pp]/nombre}" />
													</valor>
												</registro>
												
												<xsl:choose>
													<xsl:when test="count(//obtenerValoresLV/listado/ValorLista[position()=$pp]/id)>0">
														<registro>
															<item>Color Fondo</item>
															<valor>
																<cajacolor id="ValorLista:{position()}.colorfondo" valor="{//obtenerValoresLV/listado/ValorLista[position()=$pp]/colorfondo}"/>
															</valor>
														</registro>
												
														<registro>
															<item>Color Letra</item>
															<valor>
																<cajacolor id="ValorLista:{position()}.colorletra" valor="{//obtenerValoresLV/listado/ValorLista[position()=$pp]/colorletra}"/>
															</valor>
														</registro>
													</xsl:when>
													
													<xsl:when test="count(//obtenerValoresLV/listado/ValorLista[position()=$pp]/id)=0">
														<registro>
															<item>Color Fondo</item>
															<valor>
																<cajacolor id="ValorLista:{position()}.colorfondo" valor="#FFFFFF"/>
															</valor>
														</registro>
												
														<registro>
															<item>Color Letra</item>
															<valor>
																<cajacolor id="ValorLista:{position()}.colorletra" valor="#000000"/>
															</valor>
														</registro>
													</xsl:when>
												</xsl:choose>
												
												<area_botones>
													<boton estilo="eliminar" accion="page_eliminarElemento('{position()}')">Eliminar Campo</boton>
												</area_botones>
													
											</bloque>
											
										</bloque>
										
									</xsl:for-each>
									
									<area_botones>
										<boton id="btn_agregar_valor" estilo="crear" >Agregar Valor</boton>
									</area_botones>
									
									
									<xsl:if test="//TipoCampo[id_tipocampo=//obtenerListaValores/ListaValores/id_tipocampo]/tipo_dato='Date'">
										<script>
						            	  $(function()
											{
											$('.campo_id').datepicker({startDate:'01/01/1996'});
											});
										</script>
									</xsl:if>
									
									
								</contenido>
							</bloque-contenido>
						
						</bloque>
					
					</formulario>
					
					<formulario id="form_eliminar" destino="listasvalores/3.7.do">
						<variable id="id_lista_valores" valor="{obtenerListaValores/ListaValores/id_lista_valores}"/>
					</formulario>
					
					<area_botones>
						<boton estilo="eliminar" formulario="form_eliminar">Eliminar</boton>	
						<boton estilo="volver" destino="listasvalores/3.1.do">Volver</boton>
						<boton estilo="guardar" formulario="form_edicion" validacion="page_validarGuardar()">Guardar</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
