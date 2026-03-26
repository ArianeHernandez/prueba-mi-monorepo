<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/firma/firmarFormulario.xsl"/>
	<xsl:include href="context://componentes/archivos_adjuntos/archivos_adjuntos.xsl"/>

	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Cargue Bancolombia SAP">
			
			<javascript>occired/bancolombia.js</javascript>

			<principal>
				<titulo>Cargue Estructura BCOL SAP</titulo>
				<contenido>
					
					<input type="hidden" id="id_carga" value="{ID_CARGA}"/>
					
					<xsl:choose>
						<xsl:when test="string-length(ID_CARGA)>0">
						
							<p><b>Por favor, espere un momento mientras se realiza la operación.</b></p>
							
							<div class="barra-progreso">
								<div class="barra-progreso-avance" id="progreso">
								</div>
							</div>
							
							<p id="tiempo_transcurrido"></p>
							
						</xsl:when>
						
						<xsl:otherwise>
							
							<alerta>Lo sentimos, No se ha podido realizar la operación.</alerta>
							
							<area_botones>
								<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
							</area_botones>
							
						</xsl:otherwise>
					</xsl:choose>
					
					
				</contenido>
			</principal>
			
		</pagina>

	</xsl:template>

	

</xsl:stylesheet>
