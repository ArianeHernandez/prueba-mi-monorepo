<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
	<xsl:variable name="contenido" select="//obtenerContenidosByURL/listado/Contenido"/>

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Edición de Clave" ocultar_menu="true">
			<javascript>configuracion/5.1.js</javascript>

			<principal>
				<titulo icono="usuarios"><xsl:value-of select="$contenido[etiqueta='TOKEN DE INGRESO TITULO']/texto"/></titulo>
				<contenido>
					<div class="box-container">

						<div class="row">
						
							<div class="col-sm-6">
								<form id="form_token">
									<div class="form-group">
										<label><xsl:value-of select="$contenido[etiqueta='TOKEN DE INGRESO NOMBRE CAMPO']/texto"/></label>
										<input type="text" class="form-control" name="token" />
									</div>
									<br/><br/>
									<button onclick="osm_enviarFormulario('form_token')" class="btn btn-primary">Continuar</button>
								</form>
		
								<xsl:if test="//errorToken = 'true'">
									<div class="alert alert-danger" role="alert">El token ingresado es inválido</div>
								</xsl:if>
								
							</div>
						
						</div>

						<div class="clearfix"></div>
					</div>
				</contenido>

			</principal>
		</pagina>

	</xsl:template>

</xsl:stylesheet>
