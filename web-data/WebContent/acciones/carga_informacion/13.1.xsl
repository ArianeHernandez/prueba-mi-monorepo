<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Carga de Información">

			<javascript>carga_informacion/13.1.js</javascript>

			<principal>
				<titulo>Carga de Información</titulo>
				<contenido>
				<div class="box-container">
					
					<xsl:for-each select="obtenerNegociosPorUsuario/listado/Negocio">
						
						  <div class="panel-body">
						    <div class="list-group">
						    	<div class="list-group-item active">
								    <h4 class="list-group-item-heading"><xsl:value-of select="nombre" /></h4>
								    <p class="list-group-item-text"><xsl:value-of select="descripcion" /></p>
								 </div>
								 
								 <div class="list-group-item">
								 
								 
								 	<div class="row" id="div_negocio_{id_negocio}">
								 	
								 		<div id="div_formatos_{id_negocio}">
											<div id="cont_formatos_{id_negocio}">
											</div>
										</div>
								 	
								 	</div>
								 
								 </div>
								 
							</div>
						  </div>
						
						<script>
							$(function(){ cargarFormatos('<xsl:value-of select="//ID_PERSONA"/>', '<xsl:value-of select="id_negocio"/>'); });
						</script>
						
					</xsl:for-each>

					<div style="display:none" id="PLANTILLA_FILA">
					
						<div class="col-md-6">
							<div class="list-group">
						
							  <a href="#" onclick="enviar('[ 1 ]', '[ 4 ]')" class="list-group-item">
							    <h4 class="list-group-item-heading">[ 2 ]</h4>
							    <p class="list-group-item-text">[ 3 ]</p>
							  </a>
							  
							 </div>
						</div>
						 
					</div>

					<formulario id="form_formato" destino="carga_informacion/1a.do">
						<variable id="id_formato" />
						<variable id="id_negocio" />
					</formulario>


					<area_botones>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>

				</div>
				</contenido>
			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
