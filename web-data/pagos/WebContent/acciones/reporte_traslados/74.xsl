<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		

		<pagina>
		
			<javascript>reporte_traslados/74.js</javascript>

			<principal>
			
				<titulo>Reporte de Traslados</titulo>

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
								</div>
							</div>						
							<div class="panel-footer">
								<boton accion="descargar_reporte()"><i class="fa fa-download " aria-hidden="true"/>&#160;Descargar</boton>
							</div>
						</div>
					</div>
					<form id="descarga_archivo" action="../ReporteTraslados">
						<input type="hidden" name="p_fecha_desde" id="p_fecha_desde"/>
						<input type="hidden" name="p_fecha_hasta" id="p_fecha_hasta"/>
					</form>
				
				</contenido>
				
			</principal>
			
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
