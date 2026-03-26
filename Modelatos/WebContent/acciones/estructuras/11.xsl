<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Estructuras">
			<javascript>admin/11.js</javascript>
			<stylesheet>11.css</stylesheet>

			<principal>
				<titulo icono="estructura">
					Ordenar Campos de la Estructura
					<xsl:value-of select="//obtenerEstructura/Estructura/nombre" />
				</titulo>
				<contenido>

					<bloque>
						<parrafo>Arrastre cada campo hasta la posición deseada</parrafo>
						<formulario id="form_campos" destino="estructuras/11.1.do" >
							<div class="ordenable">
								<xsl:for-each select="//obtenerCamposPorEstructura/listado/Campo">
									<xsl:sort select="orden" data-type="number"></xsl:sort>
									<div id="div_" class="bloqueestilo_resultado w400 item_orden">
										<h1 style="cursor:move">
											<xsl:value-of select="nombre"></xsl:value-of>
										</h1>
										<input type="hidden" name="campos:[{position()}].id_campo" value="{id_campo}" />
										<input type="hidden" name="campos:[{position()}].orden" value="{position()}" />
									</div>
								</xsl:for-each>
							</div>
						</formulario>
					</bloque>
					<area_botones>
						<boton estilo="volver" destino="estructuras/1.1.do">Volver</boton>
						<boton accion="guardar()">Guardar</boton>
					</area_botones>

				</contenido>

			</principal>
		</pagina>

	</xsl:template>

</xsl:stylesheet>
