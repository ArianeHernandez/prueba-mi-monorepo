<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/ListadoClientes.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Transacción Manual">


			<javascript>carga_admin/25.js</javascript>
			<principal>
				<titulo>Transacción Manual</titulo>
				<contenido>
					<div class="box-container">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
									<xsl:call-template name="LISTADOCLIENTES">
										<xsl:with-param name="Destino">carga_admin/25.do</xsl:with-param>
										<xsl:with-param name="Usuario" select="obtenerUsuario/Usuario"  />
										<xsl:with-param name="UsuarioActual" select="id_usuario_actual"/>
										<xsl:with-param name="tipo_cliente" select="'J'"/>
										
									</xsl:call-template>
										
									<xsl:if test="count(obtenerUsuario/Usuario) > 0 ">
										<xsl:choose>
											<xsl:when test="count(obtenerNegociosPorUsuario/listado/Negocio) = 0">
												<div class="alert alert-danger">
													<i class="fa fa-exclamation-triangle" aria-hidden="true"></i>
				  									<span class="sr-only">Error:</span>
													El cliente <b>NO</b> tienen negocios
												</div>
											</xsl:when>
											<xsl:otherwise>
												<bloque>
													<xsl:for-each select="obtenerNegociosPorUsuario/listado/Negocio">
														<div id="div_negocio_{id_negocio}" class="bloqueestilo_resultado" onclick="cargarFormatos({//obtenerUsuario/Usuario/id_persona}, {id_negocio})">
															<h1>
																<xsl:value-of select="nombre" />
															</h1>
															<xsl:if test="normalize-space(descripcion) != ''">
																 - <xsl:value-of select="descripcion"/>
															</xsl:if>
														</div>
													</xsl:for-each>
												</bloque>
												
												<div style="display:none" id="div_formatos">
													<bloque>
														<div class="tabla_borde table-responsive">
															<table class="table table-hover table-striped tb" cellspacing="0" cellpadding="0">
																<thead>
																	<tr class="tabla_encabezado">
																		<td>Nombre Formato</td>
																		<td>Descripción</td>
																	</tr>
																</thead>
																<tbody id="cont_formatos">
																</tbody>
															</table>
														</div>
													</bloque>
												</div>
														
												<div style="display:none">
													<table icono="service">
														<tbody id="PLANTILLA_FILA">
															<tr onclick="enviar('[ 1 ]', '[ 4 ]')" class="tabla_fila" onmouseover="this.className='tabla_fila_over'" onmouseout="this.className='tabla_fila'">
																<td>[ 2 ]</td>
																<td>[ 3 ]</td>
															</tr>
														</tbody>
													</table>
												</div>
												
												<formulario id="form_formato" destino="carga_informacion/1a.do">
													<variable id="id_formato" />
													<variable id="id_negocio" />
												</formulario>
											
											</xsl:otherwise>
										</xsl:choose>
								
										
									</xsl:if>
									
								</div>
							</div>
							<div class="panel-footer">
								<boton estilo="inicio" destino="inicio/0.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Ir al Inicio</boton>
							</div>	
						</div>			
						

					</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
