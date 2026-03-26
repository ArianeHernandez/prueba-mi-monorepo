<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/firma/verificadorFirmaDocumento.xsl"/>
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Cargue Bancolombia SAP">

			<principal>
				<titulo>Cargue Bancolombia SAP</titulo>
				<contenido>
					<div class="box-container">
					
						<div class="panel panel-default">
							<div class="panel-heading">Consulta</div>
							<div class="panel-body">
								<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
								
									<div class="alert alert-info">
										<i class="fa fa-info-circle" aria-hidden="true"></i> Seleccione el archivo a cargar
									</div>
									<div class="row-box">
									<form id="form_sendfile" action="{//CONTEXT_PATH}/CarguePlano" enctype="multipart/form-data">
										<input type="hidden"  name="url" value="/carga_informacion/bancos/bancolombia_0.do"/>
										<xsl:if test="not(//OSM-INIT-SESSION/Info/Usuario/uso_firma='S' and //esclientenatural='true') and not(//OSM-INIT-SESSION/Info/Usuario/uso_firma_preparador='S')">
										
										<div class="form-group">
											<label class="col-sm-4 control-label">Archivo</label>
											<div class="col-sm-8">
												<input type="file" class="form-control" name="archivo" id="archivo" />
											</div>
										</div>
										
										</xsl:if>
									</form>
									</div>
								</div>
							</div>
							
									<xsl:choose>
										<xsl:when test="(//OSM-INIT-SESSION/Info/Usuario/uso_firma='S' and //esclientenatural='true') or (//OSM-INIT-SESSION/Info/Usuario/uso_firma_preparador='S')">
											<div class="panel-footer">
												<xsl:call-template name="verificadorFirmaDocumento">
													<xsl:with-param name="formularioOrigen">form_sendfile</xsl:with-param>
													<xsl:with-param name="nombreFaseProceso">PREPARACION MASIVA</xsl:with-param>
													<xsl:with-param name="rutaBotonVolver">inicio/0.do</xsl:with-param>
												</xsl:call-template>
												
											</div>
									
										</xsl:when>
										
										<xsl:otherwise>
															
											<div class="panel-footer">												
												<boton estilo="volver" destino="inicio/0.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160; Volver</boton>
												<boton estilo="aceptar" formulario="form_sendfile" validacion="validarArc()">
													<i class="fa fa-check" aria-hidden="true"></i>&#160; Aceptar
												</boton>
											</div>
											
											<script>
												function validarArc(){
													if(osm_esVacio(osm_getValor('archivo'))){
														alert('Seleccione un archivo!'); 
														return false;
													}
													return true;
												}
											</script>
										
										</xsl:otherwise>
									
									
									</xsl:choose>
						</div>
					</div>
				</contenido>
			</principal>
			
		</pagina>

	</xsl:template>


</xsl:stylesheet>
