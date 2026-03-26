<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
 
	<xsl:template match="OSM-ACCION">

		<pagina titulo="Roles por servicio">
			<javascript>webdata/26.js</javascript>
			<stylesheet>26.css</stylesheet>
			<principal>
				<titulo>Servicios de Rol</titulo>
				<contenido>
					<div class="box-container">
					  		<div class="col-md-10 col-md-offset-1">
					  			<div class=" form-horizontal">
									<div id="ROL_PLANTILLA" style="display:none">
										<span id="fila_[ 3 ]_[ 1 ]" name="fila_rol_[ 3 ]">
											<a class="btn btn-sm btn-primary">
												<i class="fa fa-times" onclick="eliminarRol([ 3 ], [ 1 ])"/>																				
												<span>[ 2 ]</span>
											</a>
										</span>
									</div>
									
									<xsl:variable name="id_usuario" select="//OSM-INIT-SESSION/Info/Usuario/id_usuario"/>
									
									<xsl:for-each select="obtenerServicios/listado/Servicio[( count($id_usuario) > 0 and cliente='S') or ( count($id_usuario) = 0 and admin='S')]">
										<xsl:sort select="nombre"/>
										<div class="">
											<div class="form-group form-group-sm">
												<label class="control-label col-sm-4" onclick="cargarRoles({id_servicio})">
													<xsl:value-of select="nombre" />
												</label>
																		
												<div class="col-sm-8 ">
													<div>
														<select onchange="agregarRol({id_servicio}, this.value); this.value=''" class="form-control" id="select_rol_{id_servicio}">
															<opcion value="">--Seleccion Rol--</opcion>
															<xsl:for-each select="//obtenerRolesActivos/Listado/Rol[id_usuario = $id_usuario or count($id_usuario)=0 and count(id_usuario) = 0]">
																<xsl:sort select="nombre_rol"/>
																<option value="{id_rol}"><xsl:value-of select="nombre_rol"/></option>
															</xsl:for-each>
													</select>
													</div>
													<div>
														<div id="div_servicio_{id_servicio}" class="row-filter"></div>
													</div>
												</div>
											</div>
										<script>cargarRoles(<xsl:value-of select="id_servicio"/>);</script>
										</div>
									</xsl:for-each>
								</div>
						</div>
					</div>
				</contenido>

			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
