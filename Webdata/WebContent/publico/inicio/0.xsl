<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://stylesheets/login/form.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina_simple tipo_login="true">
			<javascript>alert("hola");</javascript>
			<javascript>jsbn/jsbn.js</javascript>
			<javascript>jsbn/rng.js</javascript>
			<javascript>jsbn/rsa.js</javascript>
			<javascript>jsbn/base64.js</javascript>
			<javascript>jsbn/secure.js</javascript>
			<javascript>publico/0.js</javascript>
			
			
		
			<principal>
				<contenido>
					<div class="base_area">
					
						<div class="header_area"></div>
						
						<div class="form_area">				
							<xsl:call-template name="login_form"/>
						</div>
							
					</div>
					
					<div class="footer_area">
					</div>
					
				</contenido>
			</principal>
			
		</pagina_simple>
		
	</xsl:template>

</xsl:stylesheet>
