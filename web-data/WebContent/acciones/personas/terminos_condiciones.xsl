<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Bienvenido">
			<javascript>terminos_condiciones/terminos_condiciones.js</javascript>
			
			<principal>
				<titulo icono="inicio">Bienvenido. <texto key="PLATAFORMA"/></titulo>
				<contenido>
					
						<div class="terminos_uso" id="botones_acepto">
							
			                <div class="area_terminos">
			                  <p> <texto key="NOTA DE CONFIDENCIALIDAD"/> </p>
			              	</div>
			                
		                	<div class="text-center">
		                	<h4>¿Está de acuerdo con los términos y condiciones de uso?</h4>
		                		<a href="#" onclick="$('#vn_ingreso_token').fadeIn();" class="btn btn-primary">Acepto</a>
								<a href="#" onclick="noAceptaTerminos()" class="btn btn-default">No Acepto</a>
							</div>
						</div>
			
				</contenido>
			</principal>
			
			<ventana id="vn_ingreso_token">
				<titulo>Token de SignApp</titulo>
				<contenido>
					<div class="form-group form-group-sm">	
						<label class="control-label col-sm-3">Ingreso token <a href="https://www.google.com/search?q=SignApp">SignApp</a> :</label>
						<div class="col-sm-9">
							<input class="form-control" id="token_signapp"></input>
						</div>
					</div>
					
					<area_botones>
						<boton onclick="aceptarTerminosValidarOTP()">Enviar</boton>
						<boton onclick="$('#vn_ingreso_token').fadeOut()">Cancelar</boton>
					</area_botones>
				</contenido>
				
			</ventana>
			
		</pagina>
		
	</xsl:template>
	

</xsl:stylesheet>
