<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://stylesheets/login/form.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina_simple tipo_login="true">
			<javascript>jsbn/jsbn.js</javascript>
			<javascript>jsbn/rng.js</javascript>
			<javascript>jsbn/rsa.js</javascript>
			<javascript>jsbn/base64.js</javascript>
			<javascript>jsbn/secure.js</javascript>
			<javascript>publico/0.js</javascript>
				
			<principal >
				<contenido>
				
       				<div class="login-content">
       					
       					<div class="login-body"> 
        					
							<div class="login-form">	
								
								<div class="base_area">
									<div class="header_area">
										<div class="header_area_logo"></div>
									</div>	
									
									<div class="form_area">	
										
									
										<div class="form-box">			
											
											<div class="login-logo"></div>
											
											<xsl:call-template name="login_form"/>
										
																	
											<button type="button btn-primary" class="btn btn-primary " id="btn_login" onmouseover="ocultarTecladoGrafico();  $(this).focus();">
												Ingresar
											</button>																			
																						
											<div id="olvido_pin" class="text-center">
												<a class="btn btn-info" href="../enrolamiento/crear_solicitud.pub" onmouseover="ocultarTecladoGrafico(); $(this).focus();">
														Registro
												</a>
												<a class="link" href="../CldXmn/CentralRecaudos/recuperarPin/69.pub" >
												Recuperar Contraseña 
												</a>
											</div>
											
										</div>
									</div>
								</div>
								
							</div>
							<!-- div class="login-text">																													
					            <h3 class="title_message">¿ Necesita ayuda ?</h3>
					            <p class="info_message">Contáctenos, para cualquier duda, inquietud asesoria o aclaración, relacionados con el uso de nuestra aplicación, por favor comuníquese con  servicio al cliente: <b>00 0000 0 0000</b> o línea fija <b>+ 57 (1) 000 00 00</b>, en el horario de 0:00 am a 0:00 pm.  </p>																	
							</div-->
						</div>
					</div>
					<div class="login-agua"></div>	
					<div class="footer_area"><p>Todos los derechos reservados 2020 - Superintendencia de Sociedades</p></div>
					
				</contenido>
			</principal>
			 
		</pagina_simple>
		
	</xsl:template>

</xsl:stylesheet>
