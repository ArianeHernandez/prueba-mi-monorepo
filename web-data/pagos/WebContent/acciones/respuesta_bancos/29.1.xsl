<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/VentanaProcesoAdmin.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">


		<pagina titulo="Respuesta bancos">

			<javascript>respuesta_bancos/29.1.js</javascript>
			
			<principal>
				<titulo>Respuesta bancos</titulo>

				<contenido>
					<div class="box-container form-horizontal">
					
						<div class="panel panel-default">
							<div class="panel-heading">Consulta</div>
							<div class="panel-body">
								<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
					
									<formulario id="form_sendfile" destino="file.archivorespuestabanco" dato="multipart/form-data">
										
										<!--SELECTOR DE BANCOS -->
										
										<div class="alert alert-info" role="alert"> <i class="fa fa-info-circle" aria-hidden="true"></i> Seleccione el banco para al cual va a cargar el archivo de respuesta</div>
										
										<div class="row-box">
											<div class="form-group form-group-sm">
												<label class="control-label col-sm-4">Banco</label>
												<div class="col-sm-8">
													<cajatextoselector class="form-control" id="id_banco" accion="" valor="">
															<opcion valor="" texto="-- Seleccione --"/>
															<xsl:for-each select="//obtenerBancosImplementados/listado/HashMap">
															<xsl:sort select="*/@name"/>
																<opcion valor="{*/@name}" texto="({*/@name}) {.}" />
															</xsl:for-each>
													</cajatextoselector>
												</div>										
											</div>								 	
										</div>
										<!-- COMPONENTE PARA CARGAR ARCHIVOS -->
										<div class="alert alert-info" role="alert"> <i class="fa fa-info-circle" aria-hidden="true"></i>Envíe el archivo con el formato específico para el banco seleccionado.</div>
										<div class="row-box">
											<registro>
												<item>Seleccionar Archivo</item>
												<valor>													 													   													   												
													 <cajaarchivo id="filename"/> 													  														  
												</valor>
											</registro>
										</div>
									</formulario>
								</div>
							</div>
							<div class="panel-footer">
								<boton estilo="excel" formulario="form_sendfile" validacion="validar_archivo()"><i class="fa fa-paper-plane" aria-hidden="true"></i>  Enviar Archivo</boton>
							</div>
						</div>
						
						<area_botones>
							
						</area_botones>
					
					</div>
				</contenido>

			</principal>
			
		</pagina>
	</xsl:template>

</xsl:stylesheet>
