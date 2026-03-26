<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina_simple tipo_login="true">
			<javascript>publico/0.js</javascript>
			
			
		
			<principal>
				<contenido>
					
					<div class="base_area">
					
						<formulario id="frm_ingreso" destino="inicio/0.do">		
						
							<div class="header_area"></div>
							
							<div class="form_area">				
																						
								<componente_clave_ingreso identificacion="true" claveingreso="true" recaptchaSiteKey="{//recaptchaSecret}"/>
								
								<xsl:if test="count(usuario_invalido)>0">
									<div class="mesg2 red">
										<p>Usuario o Contraseña Incorrecta</p>
									</div>
								</xsl:if>
								
								<xsl:if test="count(acceso_denegado)>0">
									<div class="mesg2 red">
										<p>Acceso denegado</p>
									</div>
								</xsl:if>
								
								<xsl:if test="count(error_inesperado)>0">
									<div class="mesg2 red">
										<p>Lo sentimos, ha ocurrido un error inesperado al intentar validar su acceso a la aplicación.</p>
									</div>
								</xsl:if>
								
								<xsl:if test="count(cerrar_sesion)>0">
									<div class="mesg2">
										<p>La sesion ha sido cerrada.</p>
									</div>
								</xsl:if>
								
								<area_botones estilo="login">
									<boton estilo="inicio" formulario="frm_ingreso" validacion="validar_login()">Ingresar</boton>
								</area_botones>
							
							</div>
							
							
							
						</formulario>
					
					</div>
					
					<div class="footer_area">
					</div>
					
				</contenido>
			</principal>
			
		</pagina_simple>
		
	</xsl:template>

</xsl:stylesheet>
