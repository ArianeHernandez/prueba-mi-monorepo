<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/semaforoCargasPendientesPorAdminHijo.xsl"/>
	
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
	
		<pagina titulo="Administración de Carga">
			
			<stylesheet>carga/listado_carga.css</stylesheet>
			<javascript>administracion_carga/proceso/22.1.1.js</javascript>
			<principal>
				<titulo><texto key="CARGAS" /> del proceso <xsl:value-of select="//obtenerProcesoAdmin/Proceso/nombre"/></titulo>
				<contenido>
					<div class="box-container">
					<variable id="id_proceso_carga" valor="{//id_proceso_admin}"/>
					<variable id="id_administrativo" valor="{//id_administrativo}"/>
					
					<bloque-pestanas>
						
					<pestana titulo="Mis Pendientes" action="mostrarPaginaMisPendientes()">
					</pestana>
					
					<pestana titulo="Pendientes de administrativos a cargo" visible='true'>
						<bloque-contenido>
							<titulo>Instancia del proceso a consultar</titulo>
							<contenido>
							
								<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
									<parrafo estilo="info">
										<i class="fa fa-info-circle" aria-hidden="true"></i>
										Seleccione la instancia del proceso de la cual quiere ver los pendientes de los administrativos a cargo.
									</parrafo>
									
									
									<registro>
									<item>Instancias: </item>
									<valor>
									<cajatextoselector class="" id="select_instancia_hija" accion="guardarInstanciaParaListarAdministrativosHijo(this.value)" valor="">
											<opcion valor="" texto="-- Seleccione la instancia del proceso a consultar --"/>
											<xsl:for-each select="//obtenerInstanciasDelProcesoParaRolesHijo/listado/Instancia">
												<opcion valor="{id_instancia}" texto="{nombre}" />	
											</xsl:for-each>
									</cajatextoselector>
									</valor>
								 	</registro>
									
									
									<!-- TEMPLATE PARA SEMAFOROS DE LOS ADMINISTRATIVOS HIJO-->
									<xsl:call-template name="semaforoCargasPorAdminHijo"/>
								
								</div>
							 </contenido>			
						</bloque-contenido>
					</pestana>
						
					</bloque-pestanas>
					<area_botones>
						<boton estilo="volver" destino="administracion_carga/proceso/22.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Volver</boton>
					</area_botones>
					
					<!-- Formularios -->
					<formulario id="frm_rechazar_admin_hijo" destino="administracion_carga/proceso/22.3.1.do">
						<variable id="accionrechazar" valor="S" />
					</formulario>
					
					<formulario id="frm_aprobar_admin_hijo" destino="administracion_carga/proceso/22.3.1.do">
						<variable id="accionaprobar" valor="S" />
					</formulario>
					
					<formulario id="frm_accion_admin_hijo" destino="administracion_carga/proceso/22.4.1.do">
						<variable id="id_accion_admin_hijo" valor="" />
									
						<!-- OBJETO INSTANCIA_ACCION-->
						<variable id="InstanciaAccion.id_instancia" valor="" />
						<variable id="InstanciaAccion.id_accion" valor="" />
						<variable id="InstanciaAccion.id_proceso_admin" valor="" />
						<variable id="InstanciaAccion.id_administrativo" valor="" />
						<variable id="InstanciaAccion.id_carga" valor="" />
						
					</formulario>
					</div>
				</contenido>
			</principal>
			
		
			
		</pagina>
		
	</xsl:template>


</xsl:stylesheet>
