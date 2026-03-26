<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template name="login_form">
					
		<formulario id="frm_ingreso" destino="inicio/0.do" class="form-horizontal">		
		
			
			
			<xsl:if test="count(usuario_invalido)>0">
				
				<div id="login_alert" class="alert alert-danger">
					<strong>Usuario o Contraseña Incorrecta</strong>
				</div>
			</xsl:if>
			
			<xsl:if test="count(acceso_denegado)>0">
				<div id="login_alert" class="alert alert-danger">
					<strong>Acceso denegado</strong>
				</div>
			</xsl:if>
			
			<xsl:if test="count(error_inesperado)>0">
				<div id="login_alert" class="alert alert-danger">
					<strong>Lo sentimos, ha ocurrido un error inesperado al intentar validar su acceso a la aplicación.</strong>
				</div>
			</xsl:if>
			
			<xsl:if test="count(cerrar_sesion)>0">
				<div id="login_alert" class="alert alert-info">
					<strong>La sesión ha sido cerrada.</strong>
				</div>
			</xsl:if>
			
			<xsl:if test="count(cerrar_sesion_tiempo)>0">
				<div id="login_alert" class="alert alert-danger">
					<strong>La sesión ha sido cerrada por inactividad.</strong>
				</div>
			</xsl:if>
			
			<componente_clave_ingreso identificacion="true" claveingresologin="true" recaptchaSiteKey="{//recaptchaSecret}"/>
			
		</formulario>
		
	</xsl:template>

</xsl:stylesheet>
