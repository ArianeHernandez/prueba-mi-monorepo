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
		
			<javascript>reporte_pagos_general/45.js</javascript>

			<principal>
			
				<titulo>Reporte de Pagos General</titulo>

				<contenido>
				
					<div class="box-container form-horizontal">
						<div class="panel panel-default">
							<div class="panel-heading">
								Rango de Fecha
							</div>
							<div class="panel-body">
								<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
									<registro>
										<item>Fecha Desde:</item>
										<valor>
											<cajafecha id="fecha_desde" valor=""/>
										</valor>
									</registro>
									
									<registro>
										<item>Fecha Hasta:</item>
										<valor>
											<cajafecha id="fecha_hasta" valor=""/>
										</valor>
									</registro>
									
									<xsl:call-template name="LISTADOCLIENTES"></xsl:call-template>
									
									<registro>
										<item>Identificador carga:</item>
										<valor>
											<cajatexto id="id_carga" valor=""/>
										</valor>
									</registro>
								</div>
							</div>						
							<div class="panel-footer">
								<boton accion="descargar_reporte()"><i class="fa fa-download " aria-hidden="true"/>&#160;Descargar</boton>
							</div>
						</div>
					</div>
					
					<form id="descarga_archivo" action="../ReportePagosGeneral">
						<input type="hidden" name="p_fecha_desde" id="p_fecha_desde"/>
						<input type="hidden" name="p_fecha_hasta" id="p_fecha_hasta"/>
						<input type="hidden" name="p_id_cliente" id="p_id_cliente"/>
						<input type="hidden" name="p_id_carga" id="p_id_carga"/>
					</form>
				
				</contenido>
				
			</principal>
			
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
