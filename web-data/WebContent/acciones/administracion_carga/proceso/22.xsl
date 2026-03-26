<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Administración de Carga por Proceso">
			
			<javascript>administracion_carga/proceso/22.js</javascript>
			
			<principal>
				<titulo>Administración de <texto key="CARGA" /> por Proceso</titulo>
				<contenido>
					<div class="box-container">
					<div class="panel panel-default">
 	 						<div class="panel-heading">Proceso</div>
					  
					  		<div class="panel-body">

								<div class="form-horizontal">
					
									<!-- LISTA DE PROCESOS --> 
									<xsl:choose>
										<xsl:when test="count(//obtenerSemaforoProcesoAdminPorAdministrativo/listado/SemaforoProcesoAdmin)>0">
											 	
											 	<tabla class="table table-striped" icono="service">
													<encabezado>
														<titulo>Nombre Proceso</titulo>
														<titulo>Negocio</titulo>
														<titulo>Pendientes</titulo>
													</encabezado>
													<xsl:for-each select="//obtenerSemaforoProcesoAdminPorAdministrativo/listado/SemaforoProcesoAdmin">
														<fila accion="verListadoCargas({id_proceso_admin})">
															<valor><xsl:value-of select="nombre_proceso"/></valor>
															<valor><xsl:value-of select="nombre_negocio"/></valor>
															<valor><xsl:value-of select="total_cargas"/></valor>
														</fila>
													</xsl:for-each>
												</tabla>
										
											<formulario destino="administracion_carga/proceso/22.1.do" id="form_siguiente">
												<input type="hidden" name="id_proceso_admin" id="id_proceso_admin_form_siguiente" value="" />
											</formulario>
				
										</xsl:when>
										<xsl:otherwise>
											<div class=" alert alert-info">
												<i class="fa fa-info-circle" aria-hidden="true"></i> No existen procesos asociados al administrativo.
											</div>
										</xsl:otherwise>
										
									</xsl:choose>
					
								</div>
							</div>
							<div class="panel-footer">
								<boton estilo="inicio" destino="inicio/0.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Ir al Inicio</boton>
							</div>
						</div>
					
					
					</div>
					
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
