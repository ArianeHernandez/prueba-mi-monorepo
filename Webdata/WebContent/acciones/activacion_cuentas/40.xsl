<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/ReporteDim.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Activacion de Cuentas Bloqueadas">
			<stylesheet>reportedim.css</stylesheet>
			<javascript>activacion_cuentas/40.js</javascript>
			
			<principal>
				
				<titulo>Activacion de Cuentas Bloqueadas</titulo>
			
				<contenido>
					
					<xsl:call-template name="REPORTEDINAMICO">
						<xsl:with-param name="Reporte" select="//crearReporte/ReporteDinamico" />
					</xsl:call-template>
	
				</contenido>
			</principal>
			
			<ventana id="vn_confirmacion" icono="confirmacion">
				<titulo>Confirmación para Activar Cuenta</titulo>
				<contenido>
	
					<div class="alert  alert-info">
						<i class="fa fa-info-circle" aria-hidden="true"></i>
						<span class="sr-only">Info:</span>
						<p id="mensaje_cambio_activo">
							¿Desea activar la cuenta de usuario <b style="color:red" id="login_usuario_conf">.</b>?
						</p>
					</div>
	
					<area_botones>
						<boton estilo="primary aceptar" accion="activarCuenta();">Aceptar</boton>
						<boton estilo="danger" accion="cerrarVentanaValidacion();">Cancelar</boton>
					</area_botones>
				</contenido>
			</ventana>
	
		</pagina>
		
		
		
		
	</xsl:template>

</xsl:stylesheet>
