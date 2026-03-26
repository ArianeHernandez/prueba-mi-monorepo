<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/ListadoClientes.xsl" />
	<xsl:include href="context://stylesheets/templates/ListadoPersonas.xsl" />
	<xsl:include href="context://stylesheets/templates/VentanaBuscarPersona.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Titulo">
			<javascript>formato_cliente/32.1.js</javascript>
			<stylesheet>32.1.css</stylesheet>
		
			
			<principal>
				<titulo>Excepciones por cliente para formatos de entrada</titulo>
				<contenido>
					<div class="box-container form-horizontal">
					
						
						<xsl:variable name="excepciones" select="//obtenerExcepcionesFormatoPorClientePaginado/listado/ExcepcionFormatoCliente"/>
						<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
							<div class="row-box">
							<xsl:call-template name="LISTADOCLIENTES">
								<xsl:with-param name="Destino">formato_cliente/32.1.do</xsl:with-param>
								<xsl:with-param name="Usuario" select="//obtenerUsuario/Usuario"  />
								<xsl:with-param name="UsuarioActual" select="//id_usuario_actual"/>
								<xsl:with-param name="tipo_cliente" select="'T'"/>
								
							</xsl:call-template>
							</div>
						</div>
					
					<!-- VARIABLE QUE CUENTA LA CANTIDAD DE EXEPCIONES PREVIAMENTE CREADAS -->
					<input type="hidden" id="total_excepciones" value="{count($excepciones)}"/>
					<formulario destino="formato_cliente/32.2.do" id="form_guardar">
						<div id="EXCEPCIONES">
							<br/><br/>
							<xsl:choose>
								<xsl:when test="count($excepciones)>0">
							
								<table class="table table-hover table-striped tb">
									<thead>
										<tr class="tabla_encabezado">
											<td style="display:none"/>
											<td class="">Tipo excepción</td>
											<td class="">Formato</td>
											<td class="">Negocio</td>
											<td class="">Eliminar</td>
											
										</tr>
									</thead>
									<tbody id="contenido_estatico">
										<xsl:for-each select="$excepciones">
											<tr class="tabla_fila" id="fila_{position()}">
												
												<td style="display:none">
													<input type="hidden" id="excepciones:[{position()}].id_usuario" name="excepciones:[{position()}].id_usuario"  value="{//id_usuario_actual}"/>
												</td>
												
												<td class="">
													<cajatextoselector class="select_tipo_excepcion" id="excepciones:[{position()}].tipo_excepcion" accion="cambioTipoExcepcion(this.value, '{position()}');" valor="{tipo_excepcion}">
															<opcion valor="" texto="-- Seleccione --"/>
															<opcion valor="R" texto="Restriccion" />
															<opcion valor="A" texto="Adicion" />
													</cajatextoselector>
												</td>
												
												<td class="">
													<cajatextoselector class="select_formato" id="excepciones:[{position()}].id_formato" accion="" valor="{id_formato}">
															<opcion valor="" texto="-Seleccione-"/>
															<xsl:for-each select="//obtenerFormatosPorTipo/Listado/Formato">
																<opcion valor="{id_formato}" texto="{nombre}"/>
															</xsl:for-each>
													</cajatextoselector>
												</td>
												
												<td class="">
													<cajatextoselector class="select_negocio" id="excepciones:[{position()}].id_negocio" accion="" valor="{id_negocio}">
														<opcion valor="" texto="-Todos-"/>
														<xsl:variable name="tipo_excepcion" select="tipo_excepcion"></xsl:variable>
														<xsl:for-each select="//obtenerNegociosPorUsuario/listado/Negocio">
															<xsl:sort select="nombre"/>
															<opcion valor="{id_negocio}" texto="{nombre}" desactivado="{$
																tipo_excepcion='R'}"/>
														</xsl:for-each>
													</cajatextoselector>
												</td>
												
												<td class="">
													<boton estilo="eliminar" accion="eliminarExcepcion({position()})"><i class="fa fa-times" onclick="eliminarNegocio({id_negocio})"></i>&#160;<span class="hide-xs">Eliminar</span></boton>
												</td>
												
											</tr>
													
										</xsl:for-each>
									</tbody>
								</table>
							
								</xsl:when>
								
								<xsl:when test="//id_usuario_actual!=''">
									<div id="mensaje_no_hay_excepciones" class="alert alert-info">
										<i class="fa fa-info-circle" aria-hidden="true"></i> No hay excepciones creadas para el cliente.
										
									</div>
								</xsl:when>
								
								<xsl:otherwise>
									<div id="mensaje_seleccione_cliente" class="alert alert-info">
										<i class="fa fa-info-circle" aria-hidden="true"></i> Por favor seleccione un cliente para listar las excepciones.
										
									</div>
								</xsl:otherwise>
								
							</xsl:choose>
							
							
						</div>
					</formulario>
					
					<formulario destino="formato_cliente/32.1.do" id="form_pag">
						<paginacion id="_numeropagina_excepcionesformato" formulario="form_pag"
									numero="{//numeroPagina}" paginacion="{//TAMANO_PAGINA}"
									total="{//totalExcepcionesFormatoPorCliente/total}" />
					</formulario>
					
					<xsl:if test="//id_usuario_actual!=''">
						<br/><br/>
						<area_botones>
							<boton estilo="primary" accion="crearExcepcion();"><i class="fa fa-plus-square" aria-hidden="true"></i>&#160; Agregar Excepcion</boton>
							<boton estilo="primary" accion="return guardar();"><i class="fa fa-floppy-o" aria-hidden="true"></i>&#160; Guardar todo</boton>
						</area_botones>
					</xsl:if>
					
					<xsl:call-template name="plantillas" />
					
					</div>	
				</contenido>
			</principal>
		</pagina>
		
	</xsl:template>

	<xsl:template name="plantillas">
		<div style="display:none">
			<div id="PLANTILLA_LISTA_EXCEPCION">
				<div>
					<table class="table table-hover table-striped tb">
						<thead>
							<tr class="tabla_encabezado">
								<td style="display:none"/>
								<td class="">Tipo excepción</td>
								<td class="">Formato</td>
								<td class="">Negocio</td>
								<td class="">Eliminar</td>
								
							</tr>
						</thead>
						<tbody id="contenido_dinamico"></tbody>
					</table>
				</div>
			</div>

			<table>
				<tbody id="PLANTILLA_ITEM_LISTA_EXCEPCION">
					
					<tr class="tabla_fila" id="fila_[ 1 ]">
						
						<td style="display:none">
							<input type="hidden" id="excepciones:[[ 1 ]].id_usuario" name="excepciones:[[ 1 ]].id_usuario"  value="{//id_usuario_actual}"/>
						</td>
						<td class="">
							<cajatextoselector class="select_tipo_excepcion" id="excepciones:[[ 1 ]].tipo_excepcion" accion="cambioTipoExcepcion(this.value, '[ 1 ]');" valor="">
								<opcion valor="" texto="-Seleccione-"/>
								<opcion valor="A" texto="Adicion" />
								<opcion valor="R" texto="Restriccion" />
							</cajatextoselector>
						</td>
						<td class="">
							<cajatextoselector class="select_formato" id="excepciones:[[ 1 ]].id_formato" accion="" valor="">
								<opcion valor="" texto="-Seleccione-"/>
								<xsl:for-each select="//obtenerFormatosPorTipo/Listado/Formato">
									<xsl:sort select="nombre"/>
									<opcion valor="{id_formato}" texto="{nombre}"/>
								</xsl:for-each>
							</cajatextoselector>
						</td>
						<td class="">
							<cajatextoselector class="select_negocio" id="excepciones:[[ 1 ]].id_negocio" accion="" valor="">
								<opcion valor="" texto="-Todos-"/>
								<xsl:for-each select="//obtenerNegociosPorUsuario/listado/Negocio">
									<xsl:sort select="nombre"/>
									<opcion valor="{id_negocio}" texto="{nombre}"/>
								</xsl:for-each>
							</cajatextoselector>
						</td>
						<td class="">
							<boton estilo="inicio" accion="eliminarExcepcion([ 1 ])"><i class="fa fa-times" aria-hidden="true"></i>&#160; Eliminar</boton>
						</td>
						
					</tr>
				</tbody>
			</table>

		</div>
	</xsl:template>
	


</xsl:stylesheet>
