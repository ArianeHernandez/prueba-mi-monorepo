<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/ListadoClientes.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">


		<pagina titulo="Crear Director de Negocio">

			<javascript>restriccion_administrativo/18.1.js</javascript>
			<stylesheet>18.1.css</stylesheet>



			<principal>
				<titulo>Restricciones de administrativos</titulo>

				<contenido>

						<div id="restricciones_administrativos" class="">No existen restricciones</div>

						<div id="PLANTILLA_LISTA_REST_ADMINV" style="display:none">

							<div id="encabezado_lista_rest_adminv" class="tabla_encabezado_float">
								<div class=" celdaFloat">Rol</div>
								<div class=" celdaFloat">Administrativo</div>
								<div class=" celdaFloat">Cliente</div>
								<div class=" celdaFloat">Negocio</div>
								<div class=" celdaFloatRight">Eliminar</div>
								<p style="clear:both"></p>
							</div>

							<div id="contenido_lista_rest_adminv">&#160;</div>

						</div>

						<div id="PLANTILLA_ITEM_REST_ADMINV" style="display:none">

							<div id="item_rest_adminv_[ 1 ]" class="item_lista">
								<div id="rol_rest_adminv_[ 1 ]" class=" celdaFloat">
									&#160;
								</div>
								<div id="adminv_rest_adminv_[ 1 ]" class=" celdaFloat">
									&#160;
								</div>
								<div id="cliente_rest_adminv_[ 1 ]" class=" celdaFloat">
									<xsl:call-template name="SELECTORCLIENTES">
										<xsl:with-param name="id">[ 1 ]</xsl:with-param>
										<xsl:with-param name="nombre_cliente">[ 2 ]</xsl:with-param>
										<xsl:with-param name="eliminarUsuario">eliminarUsuario('[ 1 ]')</xsl:with-param>
									</xsl:call-template>
								</div>
								<div id="negocio_rest_adminv_[ 1 ]" class=" celdaFloat">
									&#160;
								</div>
								<div class="celdaFloatRight">

									<boton accion="eliminarRestriccion([ 1 ])">
										<i class="fa fa-times" onclick="eliminarNegocio({id_negocio})"></i>&#160; Eliminar
        							</boton>

								</div>
								<p style="clear:both"></p>
							</div>

						</div>
						
					<area_botones>
						<boton estilo="crear" accion="crearRestriccion();">Crear Restricción</boton>
					</area_botones>

					<xsl:call-template name="PLANTILLAS_LISTADO_CLIENTES"/>
				</contenido>



			</principal>



		</pagina>
	</xsl:template>

</xsl:stylesheet>
