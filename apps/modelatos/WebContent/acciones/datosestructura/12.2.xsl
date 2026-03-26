<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<xsl:variable name="campos"
			select="//obtenerCamposPorEstructura/listado/Campo" />
		<xsl:variable name="datos"
			select="//obtenerDatosEstructura/listado/HashMap" />

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Datos Estructura">

			<principal>
				<titulo icono="estructura">Confirmación Guardado</titulo>
				<contenido>
				
					
					<xsl:choose>
						<xsl:when test="count(guardarDatosEstructura) > 0">
							<xsl:call-template name="guardado"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:call-template name="cargarDatos"/>
						</xsl:otherwise>
					</xsl:choose>

					<formulario id="form_editar" destino="datosestructura/12.1.do">
						<variable id="id_estructura" valor="{//ID_ESTRUCTURA}" />
					</formulario>
					<area_botones>
						<boton estilo="volver" formulario="form_editar">Volver a Editar</boton>
						<boton estilo="volver" destino="estructuras/1.1.do">Volver al Listado</boton>
						<boton estilo="crear" destino="estructuras/1.2.do">Crear Nueva</boton>
					</area_botones>

				</contenido>

			</principal>


		</pagina>

	</xsl:template>
	
	<xsl:template name="cargarDatos">
	
		<javascript>admin/12.2.js</javascript>
	
		<div class="mesg2" >
			<p id="mensaje_carga">Cargando archivo...</p>
		</div>
				
	</xsl:template>
	
	<xsl:template name="guardado">
		<xsl:choose>
			<xsl:when test="//guardarDatosEstructura/exitoso='true'">

				<div class="mesg2">
					<p>Los datos se han guardado Exitosamente.</p>
				</div>
			</xsl:when>
			<xsl:otherwise>
				<div class="mesg2 red">
					<p>No se ha podido realizar la operacion.</p>
				</div>
			</xsl:otherwise>

		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>
