<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		
		<pagina>
			
			<principal>
				<javascript>recuperarPin/69.js</javascript>
				
				<!-- titulo icono="inicio"></titulo-->
				
				<contenido>
					<div class="register-page col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
					
					<div class="panel panel-default">
	 					
	 					<div class="panel-heading"><b>Recuperar Contraseña</b></div>
				  
				  		<div class="panel-body form-horizontal" id="form_area">
							
							<div class="" id="area_registro_1">
							
								<div class="">
										<div class="form-group form-group-sm">
										    <label class="col-sm-4 control-label">Nombre de Usuario</label>
										    <div class="col-sm-8">
										    	<input type="text" class="form-control" id="nombre_usuario"/>
										    </div>
										</div>
										
										<xsl:if test="count( //recaptchaSecret ) > 0">
			       							<script src='https://www.google.com/recaptcha/api.js?hl=es'/>
									        <center style="margin: 20px 0px">
									        	<div class="g-recaptcha" data-sitekey="{//recaptchaSecret}"></div>
									        </center>
								        </xsl:if> 
									
										<div class="row-btn text-right">
											<a type="button" class="btn btn-default" href="/WebData"> Volver</a>
											<button type="button" class="btn btn-primary" id="btn_continuar">Continuar</button>
										</div>
										
									
								</div>
								
							</div>
						</div>
						
						<div class="" id="area_registro_2" style="display:none">
							
								<div class="">
							
									<div class="form-horizontal" id="form_area">
									
										<div class="form-group form-group-sm">
										    <label class="col-sm-4 control-label">Código de Confirmación</label>
										    <div class="col-sm-8">
										    	<input type="text" class="form-control" id="codigo_confirmacion"/>
										    </div>
										</div>
										
										<div id="area_pin" style="display:none">
										
											<div class="alert alert-success" role="alert">
												Ingrese la clave de acceso.
											</div>
											
											<div class="row">
												<div class="col-sm-8" style="height: 200px">
													<componente_clave_ingreso nuevaclaveingreso='true' confirmacionclave='true'/>
												</div>
											</div>
											
										</div>
										
										<div class="form-group form-group-sm text-right">
											<button type="button" class="btn btn-primary" id="btn_finalizar">Finalizar</button>
										</div>
										
									</div>
								</div>
								
							</div>
						
						</div>
						
					</div>
				</contenido>
			</principal>
			
		</pagina>
		
		
	</xsl:template>
	
</xsl:stylesheet>

