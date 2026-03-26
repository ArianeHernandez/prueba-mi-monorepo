<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Cargue Davivienda">

			<principal>
				<titulo icono="estructura">Confirmación Envío</titulo>
				<contenido>
		
					<xsl:choose>
						<xsl:when test="//exitoso='true'">
							<div class="alert  alert-info">
								<i class="fa fa-info-circle" aria-hidden="true"></i>
 								<span class="sr-only">Info:</span>
								<p>La <texto key="CARGA" /> Se envió correctamente.</p>
							</div>
						</xsl:when>
						<xsl:otherwise>
							<div class="alert alert-danger">
								<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
  								<span class="sr-only">Error:</span>
								<p>No se ha podido realizar el envío de la <texto key="CARGA" /></p>
							</div>
						</xsl:otherwise>
						
					</xsl:choose>
										
					<area_botones>
						<boton estilo="primary volver" destino="carga_informacion/bancos/davivienda.do">Volver</boton>
					</area_botones>
					
				</contenido>
			</principal>
			
		</pagina>

	</xsl:template>


</xsl:stylesheet>
