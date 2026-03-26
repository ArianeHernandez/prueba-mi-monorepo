<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/VentanaProcesoAdmin.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">


		<pagina titulo="Procesos">

			<javascript>proceso_administracion/20.1.js</javascript>
			<stylesheet>20.1.css</stylesheet>

			<principal>
				<titulo>Procesos</titulo>

				<contenido>
					<div class="box-container">
					<formulario destino="proceso_administracion/20.2.do" id="form_edicion_proceso_admin">
						<input type="hidden" name="id_proceso_admin" id="id_proceso_admin_es" valor="" />
						<input type="hidden" name="id_negocio" id="id_negocio_es" valor="" />
					</formulario>

					<div id="lista_procesos_admin" class="">No existen procesos</div>
					
					<div id="PLANTILLA_LISTA_PROCESOS_ADMIN" class=""  style="display:none">

						<!--  div id="encabezado_lista_procesos_admin" class="tabla_encabezado_float">
							<div class=" item_lista_encabezado">Proceso</div>
							<div class=" item_lista_encabezado">Editar</div>
							<div class=" item_lista_encabezado">Eliminar</div>
							<p style="clear:left"></p>
						</div-->

						<div id="contenido_lista_procesos_admin" class="list-group list-table">&#160;</div>

					</div>

					
					<div id="PLANTILLA_ITEM_PROCESO_ADMIN"  style="display:none">

						<div id="item_proceso_admin_[ 1 ]" class="list-group-item" >
							
							<div id="nombre_proceso_admin_[ 1 ]" class="list-content">																			
								<h4 class="list-title">[ 2 ]</h4>
							</div>
							
							

							<div class="list-group-btn">

								<a class="btn-primary btn btn-xs" onclick="eliminarProcesoAdmin([ 1 ]);">
									<i class="fa fa-times" aria-hidden="true"></i><span class="hide-xs">&#160; Eliminar</span>
								</a>

							</div>
							<div class="list-group-btn">

								<a class="btn-primary btn btn-xs" onclick="realizarEdicionProcesoAdmin([ 1 ], [ 3 ]);">
									<i class="fa fa-pencil-square-o" aria-hidden="true"></i><span class="hide-xs">&#160; Editar</span> 
								</a>

							</div>
						</div>

					</div>


					<script>listarProcesosAdmin();</script>


					<area_botones>
						<boton estilo="primary" accion="realizarCreacionProcesoAdmin();"><i class="fa fa-file-o" aria-hidden="true"></i> &#160; Crear Proceso</boton>
					</area_botones>
					</div>
				</contenido>



			</principal>

			<xsl:call-template name="VENTANA_CREA_PROCESO_ADMIN"></xsl:call-template>

		</pagina>
	</xsl:template>

</xsl:stylesheet>
