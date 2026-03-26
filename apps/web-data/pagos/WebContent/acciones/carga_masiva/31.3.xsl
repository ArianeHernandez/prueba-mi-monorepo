<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://acciones/carga_masiva/carga_masiva_template.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Cargue Masivo">

			<principal>
				<titulo>Cargue Masivo de Productos</titulo>
				<contenido>
					<div class="panel panel-default">

						<xsl:call-template name="CONT_CARGA">
							<xsl:with-param name="id">3</xsl:with-param>
							<xsl:with-param name="archivo">productos.xls</xsl:with-param>
						</xsl:call-template>
					</div>

				</contenido>
			</principal>
		</pagina>

	</xsl:template>


</xsl:stylesheet>
