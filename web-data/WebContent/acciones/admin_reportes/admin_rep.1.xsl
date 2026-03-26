<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

		<xsl:template match="OSM-ACCION">

		<pagina titulo="Reportes">

			<javascript>admin_reportes/admin_rep.1.js</javascript>
			<stylesheet>20.1.css</stylesheet>

			<principal>
				<titulo>Administración de Reportes</titulo>

				<contenido>
				
					<formulario destino="admin_reportes/admin_rep.2.do" id="form_edicion_reporte_admin">
								<variable id="id_reporte" valor="" />
					</formulario>

					<div class="alert alert-info" id="lista_reportes_admin"><i class="fa fa-info-circle" aria-hidden="true"></i>No existen reportes</div>
					
					<div id="PLANTILLA_LISTA_REPORTES_ADMIN" style="display:none">

						<div id="encabezado_lista_reportes_admin" class="tabla_encabezado_float">
							<div class="item_lista_encabezado">Reportes en Base</div>
							<p style="clear:left"></p>
						</div>

						<div id="contenido_lista_reportes_admin">&#160;</div>

					</div>

					
					<div id="PLANTILLA_ITEM_REPORTE_ADMIN" style="display:none">

						<div id="item_reporte_admin_[ 1 ]" class="item_lista" >
							
							<div id="nombre_reporte_admin_[ 1 ]" class="celda">
								<p class="texto_celda">[ 2 ]</p>
							</div>
							
							<div class="row-btn">

								<a class="btn btn-primary"	onclick="realizarEdicionReporteAdmin('[ 1 ]');">
									<i class="fa fa-edit" aria-hidden="true"></i>&#160;Editar 
								</a>

							</div>

							<div class="row-btn">

								<a class="btn btn-danger" onclick="eliminarReporteAdmin('[ 1 ]');">
									<i class="fa fa-times" aria-hidden="true"></i>&#160;Eliminar
								</a>

							</div>
							
							<cajachequeo id="cargado_[ 1 ]" valor="[ 3 ]" accion="check('[ 1 ]')" seleccionado="[ 3 ]" texto="Cargado">
							</cajachequeo>
							
						</div>

					</div>


					<script>listarReportesAdmin();</script>


					<area_botones>
						<boton estilo="primary" accion="realizarCreacionReporteAdmin();"><i class="fa fa-plus" aria-hidden="true"></i>&#160;Crear Reporte</boton>
					</area_botones>

				</contenido>

			</principal>

		</pagina>
	</xsl:template>

</xsl:stylesheet>
