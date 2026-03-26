<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/ListadoClientes.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		

		<pagina>
		
			<javascript>reporte_tipo_negocio/47.js</javascript>

			<principal>
			
				<titulo>Reporte de Tipos de Negocio</titulo>

				<contenido>
				
					<div class="box-container form-horizontal">
						<div class="panel panel-default">
							<div class="panel-heading">
								Rango de Fecha
							</div>
							<div class="panel-body">
								<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
																	
									<xsl:call-template name="LISTADOCLIENTES"></xsl:call-template>
									
									<registro>
										<item>Fecha Liberación Desde:</item>
										<valor>
											<cajafecha id="fecha_desde" valor=""/>
										</valor>
									</registro>
									
									<registro>
										<item>Fecha Liberación Hasta:</item>
										<valor>
											<cajafecha id="fecha_hasta" valor=""/>
										</valor>
									</registro>
									
									<registro>
										<item>Negocio:</item>
										<valor>
											<select id="select_negocio" class="form-control">
												<option value="">--Todos--</option>
												<xsl:for-each select="//obtenerListadoNegociosActivos/listado/Negocio">
													<option value="{id_negocio}">
														<xsl:value-of select="nombre" />
													</option>
												</xsl:for-each>
											</select>
										</valor>
									</registro>
								</div>
							</div>
							
							<div class="panel-footer">
								<form action="../DescargaArchivoServlet" id="form_download_report" style="display:inline" method="post">
									<input type="hidden" id="id_archivo" name="id_archivo" value=""/>
									<boton estilo="primary btn-sm" accion="descargar_reporte()"><i class="fa fa-cloud-download" aria-hidden="true"></i><span class="hide-xs">&#160; Descargar Reporte</span></boton>
					  			</form>
								<boton accion="buscar()"><i class="fa fa-search " aria-hidden="true"/>&#160;Consultar</boton>
							</div>
							
							<div class="tabla_borde table-responsive" >							
								<table class="table table-hover table-striped tb">
									<thead>
										<tr>
								         	<th>ID Encargo</th>
											<th>Nombre Cliente</th>
											<th>Tipo Negocio</th>
											<th>Producto</th>
											<th>Encargo</th>
											<th>Saldo</th>
											<th>Fecha Liberacion</th>
	       								</tr>
									</thead>
									<tbody id="tabla_tipo_negocio">
									
									</tbody>
								</table>
							</div>
												
						</div>
					</div>
					
				</contenido>
				
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
