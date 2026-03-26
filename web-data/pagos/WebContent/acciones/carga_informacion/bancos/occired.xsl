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

		<pagina titulo="Cargue Occidente">

			<principal>
				<titulo>Cargue Occidente</titulo>
				<contenido>
					<div class="box-container">
                	<div class="panel panel-default">
						<div class="panel-body">
							<form id="form_sendfile" action="{//CONTEXT_PATH}/CarguePlano" enctype="multipart/form-data">
							<div class=" form-horizontal">
								
								<parrafo>
									Seleccione el archivo a cargar
								</parrafo>
							
									
									<input type="hidden"  name="url" value="/carga_informacion/bancos/occired_0.do"/>
									<xsl:if test="not(//OSM-INIT-SESSION/Info/Usuario/uso_firma='S' and //esclientenatural='true') and not(//OSM-INIT-SESSION/Info/Usuario/uso_firma_preparador='S')">
										<div class="form-group form-group-sm">
											<label class="control-label col-sm-4"> Archivo </label>
											<div class="col-sm-8">
												<input type="file" class="form-control" name="archivo" id="archivo"/>
											</div>
										</div>
										
									</xsl:if>
									
									
								
								</div>
							</form>
						</div>
						
						<xsl:choose>
							<xsl:when test="(//OSM-INIT-SESSION/Info/Usuario/uso_firma='S' and //esclientenatural='true') or (//OSM-INIT-SESSION/Info/Usuario/uso_firma_preparador='S')">
								<area_botones>
									<xsl:call-template name="verificadorFirmaDocumento">
										<xsl:with-param name="formularioOrigen">form_sendfile</xsl:with-param>
										<xsl:with-param name="nombreFaseProceso">PREPARACION MASIVA</xsl:with-param>
										<xsl:with-param name="rutaBotonVolver">inicio/0.do</xsl:with-param>
									</xsl:call-template>
									
								</area_botones>
						
						</xsl:when>
						
						<xsl:otherwise>
						
							<div class="panel-footer">
								<boton estilo="volver" destino="inicio/0.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i> Volver</boton>
								<boton estilo="aceptar" formulario="form_sendfile" validacion="validarArc()"><i class="fa fa-check" aria-hidden="true"></i>	Aceptar</boton>
								
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
