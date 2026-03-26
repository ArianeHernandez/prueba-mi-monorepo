<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/ventanaBandejaEntrada.xsl"/>

	<xsl:decimal-format name="pesos" NaN="--" decimal-separator="," grouping-separator="."/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
	
		<pagina titulo="Administración de Carga">
			
			<javascript>administracion_carga/proceso/22.1.js</javascript>
			<principal>
				<titulo><texto key="CARGAS" /> del proceso <xsl:value-of select="//obtenerProcesoAdmin/Proceso/nombre"/></titulo>
				<contenido>
					<div class="box-container">
					<variable id="id_proceso_carga" valor="{//id_proceso_admin}"/>
					<variable id="id_administrativo" valor="{//id_administrativo}"/>
					
					<bloque-pestanas>
					<pestana titulo="Mis Pendientes">
						
						<xsl:call-template name="LISTARCARGASPENDIENTES" />
							
					</pestana>	
					
					<xsl:if test="//mostrarPendientesAdministrativo='true'">					
						<pestana titulo="Pendientes de administrativos a cargo" action="mostrarPendientesAdminHijos()">
						</pestana>
					</xsl:if>
					</bloque-pestanas>
					<area_botones>
						<boton estilo="volver" destino="administracion_carga/proceso/22.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Volver</boton>
					</area_botones>
					
					<!-- FORMULARIO ACCION CARGA -->
					<formulario id="frm_accion" destino="administracion_carga/proceso/22.4.do">
						<variable id="id_accion" valor="" />
						
						<!-- OBJETO INSTANCIA_ACCION-->
						<variable id="InstanciaAccion.id_instancia" valor="" />
						<variable id="InstanciaAccion.id_accion" valor="" />
						<variable id="InstanciaAccion.id_proceso_admin" valor="" />
						<variable id="InstanciaAccion.id_administrativo" valor="" />
						<variable id="InstanciaAccion.id_carga" valor="" />
						
					</formulario>
					
					<!-- FORMULARION APROBAR CARGA -->
					<formulario id="frm_aprobar" destino="administracion_carga/proceso/22.3.do">
						<variable id="accionaprobar" valor="S" />
					</formulario>
					
					<formulario id="frm_rechazar" destino="administracion_carga/proceso/22.3.do">
						<variable id="accionrechazar" valor="S" />
					</formulario>
				
				</div>
				</contenido>
			</principal>
			
		</pagina>
			
		
	</xsl:template>
	
	

</xsl:stylesheet>
