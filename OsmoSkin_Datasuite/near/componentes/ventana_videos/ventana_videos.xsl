<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="ventana_videos">

		
		<javascript>ventana_videos/ventana_videos.js</javascript>

		<stylesheet>ventana_videos.css</stylesheet>
						<ventana id="vn_videos">
						<contenido>
							<div role="tabpanel" class="tab-pane active" id="videos">
									
									<!-- slider -->
									
									<div id="carousel-videos" class="carousel slide" data-interval="false" data-ride="carousel" style="margin:0px !important">
										<!-- Indicators -->
										<ol class="carousel-indicators">
											<li data-target="#carousel-videos" data-slide-to="0" class="active"></li>
											<li data-target="#carousel-videos" data-slide-to="1"></li>
											<li data-target="#carousel-videos" data-slide-to="2"></li>
											<li data-target="#carousel-videos" data-slide-to="3"></li>
										</ol>
										
										 <!-- Controls -->
										<a class="left carousel-control" href="#carousel-videos" role="button" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
										<a class="right carousel-control" href="#carousel-videos" role="button" data-slide="next"><i class="fa fa-chevron-right"></i></a>
										
										  <!-- Wrapper for slides -->
										  
										<div class="carousel-inner" role="listbox">
										  
<!-- 										    <div class="item active"> -->
<!-- 										    	<h3 class="text-center mi-title titulo-ventana">Información Importante</h3> -->
<!-- 												<br/><br/> -->
<!-- 												<div style="text-align: justify;width: 90%;margin: 0 auto;border: 1px solid #9cb0e2;padding: 2%;border-radius: 10px;background-color: aliceblue;"> -->
<!-- 													<texto key="INFORMACION IMPORTANTE" /> -->
<!-- 												</div> -->
										
<!-- 										    </div> -->
										    <div class="item active">
										    	<h3 class="text-center mi-title titulo-ventana">Validación Biométrico - SignApp</h3>
												<br/><br/>
												<div class="row" style="display: flex; justify-content: center;">
													<texto key="VIDEO SIGNAPP" />
												</div>
										
										    </div>
										    
										    <div class="item">
										    	<h3 class="text-center mi-title titulo-ventana">Registro del Deudor en Módulo de Insolvencia - MI</h3>
												<br/><br/>
												<div class="row" style="display: flex; justify-content: center;" >
											    	<texto key="VIDEO REGISTRO"/>
												</div>
										    </div>
										    
										    <div class="item">
										    	<h3 class="text-center mi-title titulo-ventana">Realice su solicitud de insolvencia en línea</h3>
												<br/><br/>
												<div class="row" style="display: flex; justify-content: center;">
											    	<texto key="VIDEO SOLICITUD"/>
												</div>
										    </div>
										    								    
										    <div class="item">
												<h3 class="text-center mi-title titulo-ventana">Radique una respuesta de una inadmisión a una solicitud en trámite</h3>
												<br/><br/>
												<div class="row" style="display: flex; justify-content: center;">
											    	<texto key="VIDEO INADMISION"/>
												</div>
										    </div>
										  								  
										</div>
										
										 
									</div>
									
									<!-- End Slider -->
									
								</div>
								
								<div class="row-btn center" id="area_botones" style="display: flex; justify-content: center;">
									<boton estilo="volver" accion="cerrarVentanaVideos()"><i class="fa fa-times" aria-hidden="true"></i>&#160;Cerrar</boton>
								</div>

						</contenido>
					</ventana>


	</xsl:template>


</xsl:stylesheet>