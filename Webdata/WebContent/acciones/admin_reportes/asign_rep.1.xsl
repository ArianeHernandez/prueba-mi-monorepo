<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
			
		<pagina titulo="Asignación de Reportes">
			
			<javascript>admin_reportes/asign_rep.1.js</javascript>
			<stylesheet>20.1.css</stylesheet>
						
			<principal>
				<titulo icono="formatos">Asignación de Reportes</titulo>
				<contenido>
					
					<formulario destino="admin_reportes/asign_rep.2.do" id="form_asignacion_reporte">
						<variable id="id_asignacion" valor="" />
					</formulario>
		
					<div id="lista_reportes_admin">No se han asignado reportes</div>
					
					<div id="PLANTILLA_LISTA_REPORTES_ADMIN" style="display:none">

						<div id="encabezado_lista_reportes_admin" class="tabla_encabezado_float">
							<div class="item_lista_encabezado">Reportes Asignados</div>
							<p style="clear:left"></p>
						</div>

						<div id="contenido_lista_reportes_admin">&#160;</div>

					</div>
					
					<div id="PLANTILLA_ITEM_REPORTE_ADMIN" style="display:none">

						<div id="item_reporte_admin_[ 1 ]" class="item_lista" >
							
							<div id="nombre_reporte_admin_[ 1 ]" class="celda">
								<p class="texto_celda">[ 2 ]</p>
							</div>
							
							<div class="celda">

								<a class="btn btn-primary"	onclick="actualizarAsignacion('[ 1 ]');">
									Editar 
								</a>

							</div>

							<div class="celda">

								<a class="btn btn-danger" onclick="eliminarAsignacion('[ 1 ]');">
									Eliminar
								</a>

							</div>
							
						</div>

					</div>

					<script>listarReportesAsignados();</script>
								
				<area_botones>
					<boton estilo="primary" formulario="form_asignacion_reporte">Crear Asignación</boton>
				</area_botones>
							
				</contenido>
					
			</principal>

		</pagina>
		
	</xsl:template>
	
</xsl:stylesheet>
