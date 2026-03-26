<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://stylesheets/login/form.xsl"/>
	<xsl:include href="context://componentes/ventana_videos/ventana_videos.xsl"/>
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
					
					<xsl:call-template name="ventana_videos"></xsl:call-template>
       			
       				<div class="login-content">
       				
	       				<div class="header">							
							<nav class="navbar navbar-default">
							  <div class="container-fluid">
							   
							    <div class="navbar-header">
							      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							        <span class="sr-only">Toggle navigation</span>
							        <span class="icon-bar"></span>
							        <span class="icon-bar"></span>
							        <span class="icon-bar"></span>
							      </button>							      
							      
							      <a class="navbar-brand logo-mix" href="https://www.supersociedades.gov.co/SitePages/Inicio.aspx"></a>							     
							    </div>
							
							    
							    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

							      
							      
								
							      
							      <ul class="nav navbar-nav navbar-right">
							        <li><a class="btn btn-primary btn-lg" onclick="verVentanaVideos()"><i class="fa fa-film"></i> Videos Tutoriales</a></li>
							      	<li><a class="btn btn-primary btn-lg" onclick="descargaPlantilla(54)"><i class="fa fa-book"></i> Manuales de Usuario</a></li>						      	
							        <li><button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-login-modal-sm">INGRESAR</button></li>
							        <li><a class="btn btn-info" href="../enrolamiento/crear_solicitud.pub">REGISTRARME</a></li>
							        
							        
							      </ul>
							    </div>
							  </div>
							</nav> 

						</div>
       					
       					<div class="login-body">        								
						  	<!-- Tab panes -->				  	
						  	<!-- Tab inicio -->
								<div>
									<div class="alert alert-chrome">
										<button type="button" class="close" data-dismiss="alert" aria-label="Close">
										  <span aria-hidden="true" class="fa fa-chevron-right"></span>
										</button>
										<p>Para una mejor experiencia ingresa al portal por</p>
										<h3>Google chrome</h3>
									</div>
								
									<div class="col-md-12">
										
										
										
										<h3 class="text-center h3-title"><b>Bienvenido al Módulo de Insolvencia</b></h3>
										<p class="text-center w-80">Bienvenido al <b>Módulo de insolvencia (MI)</b>, plataforma a través de la cual el usuario puede consultar y realizar los trámites de solicitud de admisión al trámite de Negociación de Emergencia, Reorganización Abreviada, Reorganización Ordinaria (Ley 1116 de 2006), Liquidación Simplificada, Liquidación Judicial y Validación Judicial (Sociedad). Adicionalmente podrá consultar el estado de las solicitudes, con las imágenes que se muestran a continuación y en el <b>menú de la parte superior.</b></p>
		       							<!-- p class="text-center w-70">Bienvenido al <b>Módulo de insolvencia (MI)</b>, acá puedes consultar y realizar los trámites de solicitud para tu organización o empresa, navega las solicitudes y los trámites con las imágenes que se muestran a continuación y en el menú de arriba podrás hacer consultas para conocer el proceso de solicitud.</p-->
		       							<br/><br/>
									
											
	
										
										<!-- Nav tabs -->
										  <ul class="nav nav-tabs" role="tablist">
										    <li role="presentation" class="col-md-3 text-center tab-index active">
										    	<a href="#registro" aria-controls="registro" role="tab" data-toggle="tab">
										    		<h4 class="text-center"><b>Registro de deudores</b> </h4>
									    			<div class="pic pic-01 h-160 pic-round"></div>																										
													<p class="text-center">Para iniciar el registro de un deudor en el módulo de insolvencia deberá tener a la mano su documento de identidad y celular.</p>
										    	</a>
										    </li>
										    <li role="presentation" class="col-md-3 text-center tab-index">
										    	<a id="solicitud_tab" href="#solicitud" aria-controls="solicitud" role="tab" data-toggle="tab">
										    		<h4 class="text-center"><b>Solicitud a un proceso de insolvencia</b></h4>
													<div class="pic pic-02 h-160 pic-round"></div>	
													<p class="text-center">Recuerde tener listos los documentos requeridos para la solicitud.  Haga uso de las plantillas para facilitar el proceso.</p>
										    	</a>
										    </li>
										    <li role="presentation" class="col-md-3 text-center tab-index">
										    	<a href="#respuesta" aria-controls="respuesta" role="tab" data-toggle="tab">
										    		<h4 class="text-center"><b>Respuesta a inadmisión de la solicitud</b></h4>
													<div class="pic pic-10 h-160 pic-round"></div>	
													<p class="text-center">Recuerde tener listos los documentos requeridos para la respuesta requerimiento, en caso que hayan sido solicitados mediante  oficio o auto de inadmisión. Se sugiere hacer uso de las plantillas para facilitar el proceso</p>
										    	</a>
										    </li>
										    <li role="presentation" class="col-md-3 text-center tab-index">
										    	<a href="#preguntas" aria-controls="preguntas" role="tab" data-toggle="tab">
										    		<h4 class="text-center"><b>Preguntas frecuentes</b></h4>		
													<div class="pic pic-03 h-160 pic-round"></div>													
													<p class="text-center">Ingrese aquí para consultar contenido de su interés.</p>
										    	</a>
										    </li>							    
										  </ul>
										
										
									</div>
									
								</div>
								<div class="clearfix"></div>
								<div class="tab-content">
								<!-- Tab empresa -->
								
								<div role="tabpanel" class="tab-pane active" id="registro">
									
									<!-- slider -->
									
									<div id="carousel-registro" class="carousel slide" data-interval="false" data-ride="carousel">
										<!-- Indicators -->
										<ol class="carousel-indicators">
											<li data-target="#carousel-registro" data-slide-to="0" class="active"></li>
											<li data-target="#carousel-registro" data-slide-to="1"></li>
											<li data-target="#carousel-registro" data-slide-to="2"></li>
											
										</ol>
										
										 <!-- Controls -->
										<a class="left carousel-control" href="#carousel-registro" role="button" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
										<a class="right carousel-control" href="#carousel-registro" role="button" data-slide="next"><i class="fa fa-chevron-right"></i></a>
										
										  <!-- Wrapper for slides -->
										  
										<div class="carousel-inner" role="listbox">
										  
										    <div class="item active">
										    
										     	<!-- vinculacion -->
												
												<div class="col-md-12">
													<h2 class="text-center mi-title"><b>Registro de deudores</b><span>(1/3)</span></h2>
													<br/><br/>
													<div class="row">												
														<div class="col-md-1">
															<h2 class="text-center"><b>1</b></h2>
														</div>
														<div class="col-md-7">
														 	<h2><b>Vinculación biométrica</b></h2>
															<br/>
															<div class="alert alert-info alert-icon">
																Debes descargar la aplicación <b>SignApp</b> antes de comenzar con estos pasos de registro
															</div>
															<br/><br/>
															<div class="signapp-logo"></div>
															<a href="https://play.google.com/store/apps/details?id=co.gov.supersociedades.signapp" target="_blank" class="google-play"></a>
															<a href="https://apps.apple.com/co/app/signapp-ssoc/id1515868902" target="_blank" class="app-store"></a>
															
														</div>
														<div class="col-md-4 text-right">
															<texto key="LINK VIDEO SIGNAPP" />
															<texto key="INSTRUCTIVO SIGNAPP" />
														</div>
													</div>	
													<br/><br/><br/>
													<div class="row ">								
														<div class="col-md-4 box-item">
															<h3 class="text-center list-num">1</h3>
															<div class="img img-fs-01 h-460"></div>
															<h6 class="text-center"><b>Ingrese el número de celular al cual estará asociado el proceso y digite el código de verificación recibido para validar su identidad</b></h6>								
														</div>
														<div class="col-md-4 box-item">
															<h3 class="text-center list-num">2</h3>	
															<div class="img img-fs-02 h-460"></div>
															<h6 class="text-center"><b>Escanee el código de barras de su documento de identificación alineandolo a línea roja</b></h6>							
														</div>
														<div class="col-md-4 box-item">
															<h3 class="text-center list-num">3</h3>							
															<div class="img img-fs-03 h-460"></div>
															<h6 class="text-center"><b>Tome una foto de la cara posterior de su documento de identificación</b></h6>	
														</div>
														<div class="col-md-4 box-item">
															<h3 class="text-center list-num">4</h3>
															<div class="img img-fs-04 h-460"></div>
															<h6 class="text-center"><b>Tome una foto de la cara frontal de su documento de identificación</b></h6>								
														</div>
														<div class="col-md-4 box-item">
															<h3 class="text-center list-num">5</h3>
															<div class="img img-fs-05 h-460"></div>
															<h6 class="text-center"><b>Tome una foto (Selfie) ubicando el rostro en el óvalo que muestra la aplicación</b></h6>								
														</div>
														<div class="col-md-4 box-item">
															<h3 class="text-center list-num">6</h3>								
															<div class=" img img-fs-06 h-460"></div>
															<h6 class="text-center"><b>Grabe una prueba de vida, moviendo la cabeza de derecha a izquierda</b></h6>
														</div>
														<div class="col-md-4 box-item">
															<h3 class="text-center list-num">7</h3>								
															<div class="img img-fs-07 h-460"></div>
															<h6 class="text-center"><b>La aplicación mostrará el resultado del análisis biométrico realizado a los datos ingresados</b></h6>
														</div>	
														<div class="col-md-4 box-item">
															<h3 class="text-center list-num">8</h3>								
															<div class="img img-fs-08 h-460"></div>
															<h6 class="text-center"><b>SignApp generará el Token de seguridad para continuar con el registro en el Módulo de Insolvencia</b></h6>
														</div>								
													</div>
												</div>
												
												<!-- fin vinculacion  -->
										    </div>
										    								    
										    <div class="item">
										       
												<!-- vinculacion -->
												
												<div class="col-md-12">
													<h2 class="text-center mi-title"><b>Registro de deudores</b><span>(2/3)</span></h2>
													<br/><br/>
													<div class="row">												
														<div class="col-md-1">
															<h2 class="text-center"><b>2</b></h2>
														</div>
														<div class="col-md-11">
														 	<h2><b>Registro del deudor solicitante</b></h2>
															<br/>													
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">1</h3>
														</div>
														<div class="col-md-11">	
															<p>Ingresar al Módulo de Insolvencia y dar clic en 'Registrarme'.</p>
															<div class="img img2-01 h-320"></div>																						
														</div>
														
													</div>	
													
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">2</h3>
														</div>
														<div class="col-md-11">	
															<p>Diligenciar formulario de registro e ingresar el Token de seguridad generado en SignApp.</p>
															<div class="alert alert-warning alert-icon">
																Tenga en cuenta que el tiempo de expiración del token es de <b>60 segundos</b>
															</div>
															<div class="alert alert-info alert-icon">
																En caso de expiración del token podrá generar uno nuevo en SignApp.
															</div>
															<div class="img img2-02 h-450"></div>
															
														
														</div>	
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">3</h3>
														</div>
														<div class="col-md-11">	
															<p>Se enviará un correo electrónico para la activación de la cuenta.</p>
															<div class="img img2-05 h-420"></div>								
														</div>
														
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">4</h3>
														</div>
														<div class="col-md-11">	
															<p>Se enviará un correo electrónico de confirmación de enrolamiento exitoso.</p>													
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">5</h3>
														</div>
														<div class="col-md-11">	
															<p>El aplicativo solicitará la aceptación de términos y condiciones para lo cual se generará un nuevo token de seguridad en SignApp que usted deberá ingresar para continuar.</p>
															<div class="img img2-06  h-420"></div>													
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">6</h3>
														</div>
														<div class="col-md-11">	
															<p>A continuación usted deberá crear la contraseña.</p>
															<div class="img img2-07 h-310"></div>													
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">7</h3>
														</div>
														<div class="col-md-11">	
															<p>Dar clic en el botón Ingresar.</p>
															<p>Una vez activada la cuenta, el sistema direccionará al usuario a la página principal para que ingrese a la aplicación.</p>
															<div class="img img2-08 h-300"></div>													
														</div>												
													</div>
													
													
													
												</div>	
												
												<!-- fin vinculacion -->
												
										    </div>
										    
										    <div class="item">
										    	
										    	<!-- vinculacion -->
												
												<div class="col-md-12">
													<h2 class="text-center mi-title"><b>Registro de deudores</b><span>(3/3)</span></h2>
													<br/><br/>
													<div class="row">
														<div class="col-md-1">
															<h2 class="text-center"><b>3</b></h2>
														</div>
														<div class="col-md-11">
														 	<h2><b>Registro de validadores</b></h2>
															<h4>Contador, revisor fiscal o apoderado</h4>
															<br/>
															
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">1</h3>
														</div>
														<div class="col-md-11">	
															<p>Dar clic en la opción usuarios.</p>
															<div class="img img3-01 h-370"></div>								
														</div>
														
													</div>	
													
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">2</h3>
														</div>
														<div class="col-md-11">	
															<p>Seleccionar la opción según el rol del usuario a crear (Apoderado, Contador o Revisor Fiscal).</p>
																										
														</div>	
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">3</h3>
														</div>
														<div class="col-md-11">	
															<p>La aplicación le mostrará el listado de los usuarios asociados a la solicitud.</p>																			
														</div>
														
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">4</h3>
														</div>
														<div class="col-md-11">	
															<p>Dar clic en Crear validador.</p>													
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">5</h3>
																
														</div>
														<div class="col-md-11">	
															<p>El módulo generará una ventana emergente y usted deberá ingresar los datos: </p>
															<ul>
																<li>Tipo de documento</li>
																<li>Número de documento</li>
															</ul>
															<p>Luego, le dará clic en continuar.</p>
															<div class="img img3-02 h-400"></div>																			
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">6</h3>
														</div>
														<div class="col-md-11">	
															<p>Finalmente, podrá dar clic en Aceptar. El sistema le notificará a través de un correo electrónico la creación del usuario. </p>																									
														</div>												
													</div>
													<!-- div class="row">						
														<div class="col-md-1">
															<h3 class="list-num">7</h3>
														</div>
														<div class="col-md-11">	
															<p>Finalmente, podrá dar clic en Aceptar. El sistema le notificará a través de un correo electrónico la creación del usuario.</p>
																											
														</div>												
													</div-->
													
													
													
												</div>	
												
												<!-- fin vinculacion -->
										    
										    </div>
										  								  
										</div>
										
										 
									</div>
									
									<!-- End Slider -->
									
								</div>
								
								<!-- Tab manual -->
								
								<div role="tabpanel" class="tab-pane" id="solicitud">
								
									<!-- slider -->
									
									<div id="carousel-solicitud" class="carousel slide" data-interval="false" data-ride="carousel">									
									<!-- Indicators -->
										<ol class="carousel-indicators">
											<li data-target="#carousel-solicitud" data-slide-to="0" class="active"></li>
											<li data-target="#carousel-solicitud" data-slide-to="1"></li>
											<li data-target="#carousel-solicitud" data-slide-to="2"></li>
											<li data-target="#carousel-solicitud" data-slide-to="3"></li>
										</ol>
										
										 <!-- Controls -->										 
										<a class="left carousel-control" href="#carousel-solicitud" role="button" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
										<a class="right carousel-control" href="#carousel-solicitud" role="button" data-slide="next"><i class="fa fa-chevron-right"></i></a>
										
										  <!-- Wrapper for slides -->
										  
										<div name="carousel-solicitud" class="carousel-inner" role="listbox">
										  
										    <div class="item active">
										    
										     	<!-- vinculacion -->
												
												<div class="col-md-12">
													<h2 class="text-center mi-title"><b>¿Cómo realizar la solicitud a un proceso de<br/> insolvencia de forma virtual?</b><span>(1/4)</span></h2>
													<br/><br/>
													<div class="row">												
														<div class="col-md-1">
															<h2 class="text-center"><b>1</b></h2>
														</div>
														<div class="col-md-8">
														 	<h2><b>Prepare los siguientes documentos</b></h2>
														 	<h4>para la presentación de la solicitud</h4>														
															
														</div>
														<div class="col-md-3 text-right">
															<a class="btn btn-primary btn-lg" onclick="descargaPlantilla(11)"><i class="fa fa-download"></i> descargar plantillas</a>
														</div>
													</div>
													
													<div class="row">								
														<div class="col-md-11 col-md-offset-1">		
															<div class="alert alert-info alert-icon">
																<b>IMPORTANTE:</b> A continuación, se entregan plantillas que sirven como base para la presentación de algunos documentos por parte del deudor. Sin embargo, las mismas deberán ajustarse a los requerimientos, necesidades, grupo NIIF y objeto social que desarrolla el deudor. Tenga en cuenta que las mismas deben estar suscritas por el representante legal, contador (si aplica) y revisor fiscal (si aplica). Se recomienda descargar las plantillas y abrirlas directamente desde su equipo local para su diligenciamiento y firma.
															</div>
															<div class="alert alert-warning alert-icon">
																Tenga en cuenta que lo indicado a continuación aplicará dependiendo de la calidad del deudor que presenta el trámite. 
															</div>	
															
															<ul class="list-item">
																<li><p>Memoria explicativa de la crisis.</p></li>
																<li><p>Certificación de estar en el supuesto de (Cesación de Pagos ó incapacidad de pago inminente).</p></li>
																<li><p>Certificación que acredite la relación de pasivos del Art. 32 de la Ley 1429 de 2010.</p></li>
																<li><p>Información financiera con corte al último día calendario del mes anterior a la presentación de la solicitud.</p></li>
																<li><p>Conjunto completo de estados financieros comparativos de los últimos tres años anteriores a la presentación de la solicitud.</p></li>
																<li><p>Inventario de activos y pasivos con corte al último día calendario del mes anterior a la presentación de la solicitud.</p></li>
																<li><p>Certificación de llevar contabilidad regular de los negocios.</p></li>
																<li><p>Certificación de encontrarse o no en causal de disolución.</p></li>
																<li><p>Certificación de tener o no pasivos pensionales.</p></li>
																<li><p>Plan de Negocios.</p></li>
																<li><p>Flujo de Caja.</p></li>
																<li><p>Proyecto de calificación y graduación de créditos.</p></li>
																<li><p>Certificación de tener o no bienes dados en garantía.</p></li>
																<li><p>Certificación de ser codeudor, garante, avalista.</p></li>											
																
															</ul>																																																	
														</div>	
														
														
													</div>
													
													
													
													
												</div>
												
												<!-- fin vinculacion  -->
										    </div>
										    								    
										    <div class="item">
										       
												<!-- vinculacion -->
												
												<div class="col-md-12">
													<h2 class="text-center mi-title"><b>¿Cómo realizar la solicitud a un proceso de<br/> insolvencia de forma virtual?</b><span>(2/4)</span></h2>
													<br/><br/>
													<div class="row">												
														<div class="col-md-1">
															<h2 class="text-center"><b>2</b></h2>
														</div>
														<div class="col-md-11">
														 	<h2><b>Diligenciamiento del formulario de solicitud</b></h2>														 																												
														</div>														
														
													</div>
													
													<div class="row">								
														<div class="col-md-11 col-md-offset-1">		
															<div class="alert alert-info alert-icon">
																<p><b>IMPORTANTE:</b>Tenga en cuenta que lo indicado a continuación aplicará dependiendo de la calidad del deudor que presenta el trámite.</p> 
															</div>
															<div class="alert alert-danger alert-icon">
																<p><b>ADVERTENCIA:</b> Se requiere haber registrado previamente la información del contador, revisor fiscal (si aplica) y apoderado (si aplica) para continuar con el diligenciamiento del formulario.</p>
															</div>
															<div class="alert alert-warning alert-icon">
																<p>Para salvaguardar la seguridad de su información personal y la de la sociedad, el aplicativo permite un tiempo máximo de inactividad de <b>12 horas</b>. Transcurrido este tiempo, el formulario se cerrará automáticamente y usted deberá iniciar el proceso nuevamente.</p> 
															</div>																																																		
														</div>																											
													</div>
													
													
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">1</h3>
														</div>
														<div class="col-md-11">	
															<p>Diligencie la totalidad de la información solicitada en el <b>formulario de solicitud</b> junto con las plantillas descritas en el punto 1 <b>"Prepare los siguientes documentos para la presentación de la solicitud"</b>. </p>
															<div class="img imgB2-01 h-500"></div>																													
														</div>	
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">2</h3>
														</div>
														<div class="col-md-11">	
 															<p>Dar clic en <b>"Guardar"</b> y espere a que el sistema cargue la información y los documentos del proceso. </p>
															<div class="img imgB2-02 h-400"></div>																													
														</div>	
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">3</h3>
														</div>
														<div class="col-md-11">	
															<p>Por último, el módulo le informará al usuario que la solicitud fue creada exitosamente y el número consecutivo del formulario. </p>
															<div class="img imgB2-03 h-240"></div>
															<div class="alert alert-info alert-icon">
																<p>Al finalizar este proceso los validadores recibirán un correo de notificación indicando que el deudor solicitante ha cargado una solicitud en el sistema para su revisión y aprobación.</p> 
																<p>Tenga en cuenta que la solicitud deberá ser revisada por los validadores en el sistema.</p>	 
															</div>																													
														</div>	
													</div>
													
												</div>	
												
												<!-- fin vinculacion -->
												
										    </div>
										    
										    <div class="item">
										    	
										    	<!-- item -->
												
												<div class="col-md-12">
													<h2 class="text-center mi-title"><b>¿Cómo realizar la solicitud a un proceso de<br/> insolvencia de forma virtual?</b><span>(3/4)</span></h2>
													<br/><br/>
													<div class="row">
														<div class="col-md-1">
															<h2 class="text-center"><b>3</b></h2>
														</div>
														<div class="col-md-11">
														 	<h2><b>Aprobación de la solicitud</b></h2>
														 	<h4>por parte del Contador y Revisor Fiscal si aplica</h4>
														 	<div class="alert alert-warning alert-icon">
														 		<p><b>Nota:</b> Este mismo procedimiento aplicará si el deudor solicitante tiene revisor fiscal.</p>
														 	</div>														
															<br/><br/>															
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">1</h3>
														</div>
														<div class="col-md-11">	
															<p>Iniciar sesión en Módulo de insolvencia (MI).</p>
															<div class="img imgB3-01 h-290"></div>								
														</div>
														
													</div>	
													
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">2</h3>
														</div>
														<div class="col-md-11">	
															<p>Ir al menú y dar clic en Enviar.</p>
															<div class="img imgB3-02  h-380"></div>											
														</div>	
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">3</h3>
														</div>
														<div class="col-md-11">	
															<p>Seleccionar la solicitud que desea revisar.</p>
															<div class="img imgB3-03 h-260"></div>																				
														</div>
														
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">4</h3>
														</div>
														<div class="col-md-11">	
															<p>El sistema mostrará la información general de la solicitud.</p>	
															<div class="img imgB3-04 h-300"></div>													
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">5</h3>
															<div class="img img3-03"></div>	
														</div>
														<div class="col-md-11">	
															<p>Al dar clic en la solicitud el usuario podrá ver el detalle de la solicitud de la misma.</p>
															<div class="img imgB3-05 h-440"></div>																				
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">6</h3>
														</div>
														<div class="col-md-11">	
															<p>El sistema generará una ventana emergente para confirmar la aprobación de la solicitud e Ingresar el token de seguridad generado en SignApp</p>
															<div class="img imgB3-06 h-460"></div>																									
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">7</h3>
														</div>
														<div class="col-md-11">	
															<p>El usuario dará clic en aprobar la solicitud, si la misma corresponde a la realidad.</p>
																											
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">8</h3>
														</div>
														<div class="col-md-11">	
															<p>El sistema generará una ventana emergente para confirmar la aprobación de la solicitud e Ingresar el token de seguridad generado en SignApp.</p>
															<div class="img imgB3-07 h-400"></div>																									
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">9</h3>
														</div>
														<div class="col-md-11">	
															<p>Finalmente, podrá dar clic en Radicar.</p>
																											
														</div>												
													</div>
												</div>	
												
												<!-- End item -->
										    
										    </div>
										    
										    <div class="item">
										    	
										    	<!-- item -->
												
												<div class="col-md-12">
													<h2 class="text-center mi-title"><b>¿Cómo realizar la solicitud a un proceso de<br/> insolvencia de forma virtual?</b><span>(4/4)</span></h2>
													<br/><br/>
													<div class="row">
														<div class="col-md-1">
															<h2 class="text-center"><b>4</b></h2>
														</div>
														<div class="col-md-11">
														 	<h2><b>Aprobación final</b></h2>
															<h4>del representante legal y envío de la solicitud</h4>
															<div class="alert alert-warning alert-icon">
														 		<p><b>Nota:</b> Este mismo procedimiento aplicará si el deudor solicitante tiene revisor fiscal.</p>
														 	</div>														
															<br/><br/>
															
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">1</h3>
														</div>
														<div class="col-md-11">	
															<p>Iniciar sesión en Módulo de insolvencia (MI).</p>
															<div class="img imgB4-01 h-290"></div>								
														</div>
														
													</div>	
													
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">2</h3>
														</div>
														<div class="col-md-11">	
															<p>Ir al menú y dar clic en "Enviar".</p>
															<div class="img imgB4-02 h-380"></div>											
														</div>	
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">3</h3>
														</div>
														<div class="col-md-11">	
															<p>Seleccionar la solicitud que desea enviar.</p>
															<div class="img imgB4-03 h-260"></div>																				
														</div>
														
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">4</h3>
														</div>
														<div class="col-md-11">	
															<p>El sistema mostrará la información general de la solicitud.</p>
															<div class="img imgB4-04 h-300"></div>														
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">5</h3>														
														</div>
														<div class="col-md-11">	
															<p>Al dar clic en la solicitud el usuario podrá ver el detalle de la solicitud de la misma.</p>
															<div class="img imgB4-05 h-440"></div>																			
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">6</h3>
														</div>
														<div class="col-md-11">	
															<p>En la opción Archivos Adjuntos podrá los documentos cargados en la solicitud.</p>
															<div class="img imgB4-06 h-460"></div>																			
														</div>													
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">7</h3>
														</div>
														<div class="col-md-11">	
															<p>El usuario dará clic en aprobar la solicitud, si la misma corresponde a la realidad.</p>																																
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">8</h3>														
														</div>
														<div class="col-md-11">	
															<p>El sistema generará una ventana emergente para confirmar la aprobación de la solicitud e Ingresar el token de seguridad generado en SignApp.</p>
															<div class="img imgB4-07 h-400"></div>																			
														</div>												
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">9</h3>
														</div>
														<div class="col-md-11">	
															<p>Finalmente, podrá dar clic en Radicar y la solicitud será radicada ante la Superintendencia de Sociedades.</p>																																
														</div>													
													</div>													
												</div>	
												
												<!-- End item -->
										    
										    </div>
										  								  
										</div>
										
										 
									</div>
									
								<!-- End Slider -->
									
									
								</div>
								
									<!--  Tab Rta Req -->
								
								<div role="tabpanel" class="tab-pane" id="respuesta">
									
									<!-- slider -->
									
									<div id="carousel-respuesta" class="carousel slide" data-interval="false" data-ride="carousel">
										<!-- Indicators -->
										<ol class="carousel-indicators">
											<li data-target="#carousel-respuesta" data-slide-to="0" class="active"></li>
											<li data-target="#carousel-respuesta" data-slide-to="1"></li>
											<li data-target="#carousel-respuesta" data-slide-to="2"></li>
											
										</ol>
										
										 <!-- Controls -->
										<a class="left carousel-control" href="#carousel-respuesta" role="button" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
										<a class="right carousel-control" href="#carousel-respuesta" role="button" data-slide="next"><i class="fa fa-chevron-right"></i></a>
										
										  <!-- Wrapper for slides -->
										  
										<div class="carousel-inner" role="listbox">
										  
										    <div class="item active">
										    
										     	<!-- vinculacion -->
												
												<div class="col-md-12">
													<h2 class="text-center mi-title"><b>¿Cómo presentar la respuesta de una inadmisión<br/> a una solicitud de forma virtual?</b><span>(1/3)</span></h2>
													<br/><br/>
													<div class="row">	
														<div class="col-md-1">
															<h2 class="text-center"><b>1</b></h2>
														</div>											
														<div class="col-md-10">
														 	<h4>Una vez se haya solicitado un requerimiento mediante oficio de inadmisión, se deben realizar los siguientes pasos para dar respuesta al requerimiento:</h4>															
														</div>
													</div>	
													<br/><br/><br/>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">1</h3>
														</div>
														<div class="col-md-11">	
															<p>Ingrese al Módulo de Insolvencia, seleccione el botón INGRESAR, diligencie usuario, contraseña y no soy un robot.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/1_1.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">2</h3>
														</div>
														<div class="col-md-11">	
															<p>En el menú Radicar otros documentos, de clic en Respuesta requerimiento.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/1_2.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">3</h3>
														</div>
														<div class="col-md-11">	
															<p>Seleccione el número de la solicitud o el número de proceso, sobre el cual va a presentar la respuesta requerimiento.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/1_3.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">4</h3>
														</div>
														<div class="col-md-11">	
															<p>En la sección de ARCHIVOS ADJUNTOS y según la información solicitada por la Superintendencia de Sociedades, seleccione el archivo y en la lista "Tipo de archivo" escoja el tipo de documento que va a cargar.</p>
															<p>El tipo de archivo "Otro documento" únicamente debe ser utilizado si el archivo no se encuentra en la lista desplegable.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/1_4.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">5</h3>
														</div>
														<div class="col-md-11">	
															<p>El sistema le mostrará un mensaje de creación exitosa de la respuesta requerimiento con el identificador. Tenga presente que la respuesta requerimiento aún no ha sido enviada a la Superintendencia de Sociedades y debe pasar por la validación del Contador y/o Revisor fiscal (si aplica).</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/1_6.png" class="img" />
															</div>							
														</div>
													</div>
												</div>
												
												<!-- fin vinculacion  -->
										    </div>
										    								    
										    <div class="item">
										       
												<!-- vinculacion -->
												
												<div class="col-md-12">
													<h2 class="text-center mi-title"><b>¿Cómo presentar la respuesta de una inadmisión<br/> a una solicitud de forma virtual?</b><span>(2/3)</span></h2>
													<br/><br/>
													<div class="row">												
														<div class="col-md-1">
															<h2 class="text-center"><b>2</b></h2>
														</div>
														<div class="col-md-10">
														 	<h4>La solicitud debe ser validada por el Contador y/o Revisor fiscal (si aplica) mediante los siguientes pasos:</h4>													
														</div>												
													</div>
													<br/><br/><br/>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">1</h3>
														</div>
														<div class="col-md-11">	
															<p>Ingrese al Módulo de Insolvencia, seleccione el botón INGRESAR, diligencie usuario, contraseña y no soy un robot.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/2_1.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">2</h3>
														</div>
														<div class="col-md-11">	
															<p>En el menú Enviar, seleccione Enviar solicitud.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/2_2.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">3</h3>
														</div>
														<div class="col-md-11">	
															<p>En la parte inferior de la página, de clic en RESPUESTA REQUERIMIENTO - Contador/Revisor Fiscal.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/2_3.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">4</h3>
														</div>
														<div class="col-md-11">	
															<p>Busque y seleccione el número de la respuesta requerimiento confirmado por el sistema.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/2_4.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">5</h3>
														</div>
														<div class="col-md-11">	
															<p>Revise la información de la respuesta requerimiento a través de las opciones: Detalle, Notas, Archivos adjuntos. De clic en confirmar solicitud y en la ventana emergente ingrese el token generado en SignApp y de clic en Aprobar.</p>
															<div class="imgB5-01"></div>						
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">6</h3>
														</div>
														<div class="col-md-11">	
															<p>El sistema le mostrará un mensaje de confirmación de envío de la respuesta requerimiento. Tenga presente que en este paso se confirma el envió de la respuesta requerimiento a radicación ante la Superintendencia de Sociedades.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/2_6.png" class="img" />
															</div>							
														</div>
													</div>
												</div>	
												
												<!-- fin vinculacion -->
												
										    </div>
										    
										    <div class="item">
										    	
										    	<!-- vinculacion -->
												
												<div class="col-md-12">
													<h2 class="text-center mi-title"><b>¿Cómo presentar la respuesta de una inadmisión<br/> a una solicitud de forma virtual?</b><span>(3/3)</span></h2>
													<br/><br/>
													<div class="row">
														<div class="col-md-1">
															<h2 class="text-center"><b>3</b></h2>
														</div>
														<div class="col-md-10">
														 	<h4>La solicitud debe ser revisada y enviada por el Deudor solicitante mediante los siguientes pasos:</h4>
														</div>												
													</div>
													<br/><br/><br/>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">1</h3>
														</div>
														<div class="col-md-11">	
															<p>Ingrese al Módulo de Insolvencia, seleccione el botón INGRESAR, diligencie usuario, contraseña y no soy un robot.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/3_1.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">2</h3>
														</div>
														<div class="col-md-11">	
															<p>En el menú Enviar, de clic en Enviar solicitud.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/3_2.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">3</h3>
														</div>
														<div class="col-md-11">	
															<p>De clic en RESPUESTA REQUERIMIENTO.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/3_3.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">4</h3>
														</div>
														<div class="col-md-11">	
															<p>Busque y seleccione el número de la respuesta requerimiento confirmado por el sistema.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/3_4.png" class="img" />
															</div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">5</h3>
														</div>
														<div class="col-md-11">	
															<p>Revise la información de la respuesta requerimiento a través de las opciones: Detalle, Notas, Archivos adjuntos. De clic en confirmar solicitud y en la ventana emergente ingrese el token generado en SignApp y de clic en Aprobar.</p>
															<div class="imgB5-02"></div>							
														</div>
													</div>
													<div class="row">								
														<div class="col-md-1">
															<h3 class="list-num">6</h3>
														</div>
														<div class="col-md-11">	
															<p>El sistema le mostrará un mensaje de confirmación de envío de la respuesta requerimiento. Tenga presente que en este paso la respuesta requerimiento es enviada a la Superintendencia de Sociedades.</p>
															<div class="col-md-12 box-item">
																<img src="../images/home/respuesta/3_6.png" class="img" />
															</div>							
														</div>
													</div>
												</div>	
												
												<!-- fin vinculacion -->
										    
										    </div>
										  								  
										</div>
										
										 
									</div>
									
									<!-- End Slider -->
									
								</div>
								
									<!-- Tab FAQ -->
									
								<div role="tabpanel" class="tab-pane" id="preguntas">
							
									<h2 class="text-center"><b>Preguntas frecuentes</b></h2>
									<br/><br/><br/>
									
								    <!-- Accordion -->
									<div class="col-sm-10 col-sm-offset-1">	
										<div class="panel-group" id="faq" role="tablist" aria-multiselectable="true">
											<div class="panel panel-default">
											  	<div class="panel-heading" role="tab" id="faqquest001">
											    	<h4 class="panel-title">
												      <a role="button" data-toggle="collapse" data-parent="#faq" href="#faqcollapse001" aria-expanded="true" aria-controls="faqcollapse001">
												        <ol><li>													        	
												        	Error al intentar registrarse en la aplicación SignApp, con el fin realizar la solicitud de admisión al proceso de insolvencia.
												        </li></ol>
												      </a>
											    	</h4>
											  	</div>
											  	<div id="faqcollapse001" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="faqquest001">
													<div class="panel-body">
														<ul class="list-item">
													  		<li>Si la validación biométrica es fallida se solicita al usuario ingresar a url alterna para poder 
																realizar el registro de validación biométrica:
																<a style="color:#337ab7" href="https://mi.ia.supersociedades.gov.co/WebData/registro_signapp_mod/registro_signapp.pub#nbb">https://mi.ia.supersociedades.gov.co/WebData/registro_signapp_mod/registro_signapp.pub</a> 
																Tenga en cuenta de no colocar tildes en los nombres y apellidos y los 
																documentos adjuntos cargarlos en formato jpg y png.
															</li>
															<li>
																Cuando el solicitante realice el registro deberá enviar un correo a 
																<a style="color:#337ab7" href = "mailto: soporte@supersociedades.gov.co">soporte@supersociedades.gov.co</a> con el nombre completo y cédula de la persona que se 
																registró en la url de signapp alterno, adjuntando hora , fecha del registro , número de celular 
																de contacto y evidencias del error del aplicativo.
															</li>
													  	</ul>
													</div>
											  	</div>
											</div>
											<div class="panel panel-default">
											  	<div class="panel-heading" role="tab" id="faqquest002">
											    	<h4 class="panel-title">
												      <a role="button" data-toggle="collapse" data-parent="#faq" href="#faqcollapse002" class="collapsed" aria-expanded="false" aria-controls="faqcollapse002">
												        <ol start="2"><li>
												        	El OTP (one time password) no es válido.
												        </li></ol> 
												      </a>
											    	</h4>
											  	</div>
											  	<div id="faqcollapse002" class="panel-collapse collapse" role="tabpanel" aria-labelledby="faqquest002">
													<div class="panel-body">
														<ul class="list-item">
													  		<li>
														  		Este caso puede presentarse cuando el usuario se demora más de 60 segundos en ingresar 
																el token (número que arroja la app de SignApp); para ello deberá ingresar nuevamente a la 
																aplicación de signapp y generar un nuevo token para realizar la aprobación que este 
																pendiente en el Modulo de Insolvencia. Cabe indicar que la validación biométrica es única 
																e intransferible y no podrá realizarse por terceros, como por ejemplo por el apoderado.
															</li>
													  	</ul>
													</div>
											  	</div>
											</div>
											<div class="panel panel-default">
											  	<div class="panel-heading" role="tab" id="faqquest003">
											    	<h4 class="panel-title">
												      <a role="button" data-toggle="collapse" data-parent="#faq" href="#faqcollapse003" class="collapsed" aria-expanded="false" aria-controls="faqcollapse003">
												        <ol start="3"><li>	
												        	Al cargar la solicitud en el MI, el sistema reporta que la carga es exitosa, sin embargo, 
															cuando el Contador y Revisor Fiscal tratan de entrar a aprobar la solicitud el sistema 
															informa que no hay registros asociados.
														</li></ol> 
												      </a>
											    	</h4>
											  	</div>
											  	<div id="faqcollapse003" class="panel-collapse collapse" role="tabpanel" aria-labelledby="faqquest003">
													<div class="panel-body">
														<ul class="list-item">
													  		<li>
													  			El solicitante deberá enviar un correo a <a style="color:#337ab7" href = "mailto: soporte@supersociedades.gov.co">soporte@supersociedades.gov.co</a>, indicando en el 
																asunto: <b>Pendiente aprobación de la solicitud, por parte del contador y/o revisor fiscal</b> con 
																la evidencia del mensaje reportado en el sistema , así mismo, remitir el número de contacto 
																para que posteriormente se suministre el respectivo soporte por parte de la 
																Superintendencia de Sociedades.
																													  			
															</li>
													  	</ul>
													</div>
											  	</div>
											</div>
											<div class="panel panel-default">
											  	<div class="panel-heading" role="tab" id="faqquest004">
											    	<h4 class="panel-title">
												      <a role="button" data-toggle="collapse" data-parent="#faq" href="#faqcollapse004" class="collapsed" aria-expanded="false" aria-controls="faqcollapse004">
												        <ol start="4"><li>
												        	Después de haber radicado una solicitud ¿Dónde puedo consultar en qué estado se 
															encuentra el tramite?
														</li></ol>
												      </a>
											    	</h4>
											  	</div>
											  	<div id="faqcollapse004" class="panel-collapse collapse" role="tabpanel" aria-labelledby="faqquest004">
													<div class="panel-body">
														<ul class="list-item">
													  		<li>
													  			<b>Oficio de requerimiento</b><br/>
																Una vez la Superintendencia de sociedades emite un oficio de inadmisión, notificará 
																al deudor mediante la notificación electrónica o dirección física relacionada en el 
																formulario de registro.
															</li>
															<li>
																<b>Solicitud admitida, inadmitida o rechazada</b><br/>
																El solicitante deberá ingresar a Baranda Virtual en la siguiente URL 
																<a style="color:#337ab7" href="https://servicios.supersociedades.gov.co/barandaVirtual/#!/app/procesos">https://servicios.supersociedades.gov.co/barandaVirtual/#!/app/procesos</a>, en la 
																opción de procesos con el número del Nit de la sociedad y en la pestaña consultar 
																providencias se podrán visualizar las radicaciones asociadas a la sociedad. 
															</li>
													  	</ul>
													</div>
											  	</div>
											</div>
											<div class="panel panel-default">
											  	<div class="panel-heading" role="tab" id="faqquest005">
											    	<h4 class="panel-title">
												      <a role="button" data-toggle="collapse" data-parent="#faq" href="#faqcollapse005" class="collapsed" aria-expanded="false" aria-controls="faqcollapse005">
												        <ol start="5"><li>
												        	 ¿Cómo es el proceso para dar respuesta a un requerimiento u oficio de inadmisión por medio del Módulo de Insolvencia?
														</li></ol>		
												      </a>
											    	</h4>
											  	</div>
											  	<div id="faqcollapse005" class="panel-collapse collapse" role="tabpanel" aria-labelledby="faqquest005">
													<div class="panel-body">
														<ul class="list-item">
													  		<li>
													  			El procedimiento de radicar la respuesta al requerimiento lo deberá realizar por el botón de 
																radicar otros documentos en el Modulo de Insolvencia y adjuntar toda la documentación 
																requerida. A continuación, la solicitud cargada con la respuesta al requerimiento deberá ser aprobada 
																por el contador y/o revisor fiscal y finalmente deberá ser enviado por el representante legal 
																para que se radique ante la superintendencia de sociedades.
															</li>
													  	</ul>
													</div>
											  	</div>
											</div>
											<div class="panel panel-default">
											  	<div class="panel-heading" role="tab" id="faqquest006">
											    	<h4 class="panel-title">
												      <a role="button" data-toggle="collapse" data-parent="#faq" href="#faqcollapse006" class="collapsed" aria-expanded="false" aria-controls="faqcollapse006">
												        <ol start="6"><li>
												        	Como se crea una parte del proceso
												        </li></ol>
												      </a>
											    	</h4>
											  	</div>
											  	<div id="faqcollapse006" class="panel-collapse collapse" role="tabpanel" aria-labelledby="faqquest006">
													<div class="panel-body">
														<ul class="list-item">
													  		<li>
													  			El usuario debe ingresar al MI con el perfil del representante legal, a continuación, en el 
																botón USUARIO podrá crear el contador, revisor fiscal y el apoderado.
															</li>
													  	</ul>
													</div>
											  	</div>
											</div>
											<div class="panel panel-default">
											  	<div class="panel-heading" role="tab" id="faqquest007">
											    	<h4 class="panel-title">
												      <a role="button" data-toggle="collapse" data-parent="#faq" href="#faqcollapse007" class="collapsed" aria-expanded="false" aria-controls="faqcollapse007">
												        <ol start="7"><li>
												        	 ¿Qué pasa si una parte del proceso está representando a varias sociedades?
												        </li></ol>
												      </a>
											    	</h4>
											  	</div>
											  	<div id="faqcollapse007" class="panel-collapse collapse" role="tabpanel" aria-labelledby="faqquest007">
													<div class="panel-body">
														<ul class="list-item">
													  		<li>
													  			Cada sociedad deberá crear un usuario y contraseña diferente para cada parte del proceso 
																que se relacione con más de una sociedad. 
															</li>
													  	</ul>
													</div>
											  	</div>
											</div>
											<div class="panel panel-default">
											  	<div class="panel-heading" role="tab" id="faqquest008">
											    	<h4 class="panel-title">
												      <a role="button" data-toggle="collapse" data-parent="#faq" href="#faqcollapse008" class="collapsed" aria-expanded="false" aria-controls="faqcollapse008">
												       <ol start="8"><li>
												        	Se realiza carga de la solicitud y el sistema reporta error <b>504 ERROR</b> no se pudo 
															satisfacer la solicitud. (Error de conexión con el servidor)
														</li></ol>
												      </a>
											    	</h4>
											  	</div>
											  	<div id="faqcollapse008" class="panel-collapse collapse" role="tabpanel" aria-labelledby="faqquest008">
													<div class="panel-body">
														<ul class="list-item">
													  		<li>
													  			La sociedad solicitante deberá notificar al correo <a style="color:#337ab7" href = "mailto: soporte@supersociedades.gov.co">soporte@supersociedades.gov.co</a> con el 
																asunto: <b>504 ERROR</b> suministrando la evidencia del incidente reportado con el nombre de 
																la sociedad, Nit y número de contacto para poder suministrar el respectivo soporte.
															</li>
													  	</ul>
													</div>
											  	</div>
											</div>
											<div class="panel panel-default">
											  	<div class="panel-heading" role="tab" id="faqquest009">
											    	<h4 class="panel-title">
												      <a role="button" data-toggle="collapse" data-parent="#faq" href="#faqcollapse009" class="collapsed" aria-expanded="false" aria-controls="faqcollapse009">
												       <ol start="9"><li>
												        	 Qué se debe hacer cuando se recibe un correo de <b>Notificación de Registro Fallido</b> después 
															 de haber realizado el registro de información básica de la sociedad en el MI
														</li></ol>	 
												      </a>
											    	</h4>
											  	</div>
											  	<div id="faqcollapse009" class="panel-collapse collapse" role="tabpanel" aria-labelledby="faqquest009">
													<div class="panel-body">
														<ul class="list-item">
													  		<li>
													  			La sociedad deberá enviar un correo a <a style="color:#337ab7" href = "mailto: soporte@supersociedades.gov.co">soporte@supersociedades.gov.co</a> con el asunto: 
																<b>Notificación de Registro Fallido</b>, donde debe adjuntar la evidencia del mensaje de 
																notificación de registro fallido y adjuntar la cámara de comercio de la sociedad con el 
																fin de validar la información suministrada. Una vez se valide el registro, se enviará el 
																correo de activación al solicitante para que de esta manera pueda activar la cuenta que 
																le permitirá ingreso al Módulo de Insolvencia.
															</li>
													  	</ul>
													</div>
											  	</div>
											</div>
										</div>
									</div>	
									<!-- Accordion -->
								
								</div>
									
								
									
									<!-- End Tab FAQ -->	
						 	</div>
						 	
							<!-- Fin Tab panes -->
							
							
							<br/><br/>	       							
							<div class="alert alert-chrome float-right">
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
								  <span aria-hidden="true" class="fa fa-chevron-right"></span>
								</button>
								<p>Para una mejor experiencia ingresa al portal por</p>
								<h3>Google chrome</h3>
							</div>
														
							
							
							<!-- Modal Login -->
							<div class="modal bs-login-modal-sm fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
							  <div class="modal-dialog modal-md" role="document">
							    <div class="modal-content">
							      <div class="form_area">
										<div class="form-box">
													
											<xsl:call-template name="login_form" />
												
											<div class="row-btn">
												
												<button type="button" class="btn pull-right btn-info" id="btn_login" onmouseover="ocultarTecladoGrafico();  $(this).focus();">
													Ingresar
												</button>	
												
												<a class="link" href="../recuperarPin/69.pub">
													Olvidé mi contraseña 
												</a>										
											</div>
										</div>
									</div>
							    </div>
							  </div>
							</div>
							
							<div class="modal fade picinfo-estudio-01" tabindex="-1" role="dialog" aria-labelledby="picinfo-registro-03">
							  <div class="modal-dialog modal-xxl" role="document">
							    <div class="modal-content">
							    	<div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><i class="fa fa-times"></i></span></button>								    
								    </div>
							    	<div class="modal-body">
							    		<div class="picinfo info-estudio-01 vh-70"></div>	
							    	</div>
							    </div>
							  </div>
							</div>
							<div class="modal fade picinfo-manual-02" tabindex="-1" role="dialog" aria-labelledby="picinfo-manual-02">
							  <div class="modal-dialog modal-xxl" role="document">
							    <div class="modal-content">
							    	<div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><i class="fa fa-times"></i></span></button>								    
								    </div>
							    	<div class="modal-body">
							    		<div class="picinfo info-manual-02 vh-70"></div>	
							    	</div>
							    </div>
							  </div>
							</div>	
							<div class="modal fade picinfo-manual-03" tabindex="-1" role="dialog" aria-labelledby="picinfo-manual-03">
							  <div class="modal-dialog modal-xxl" role="document">
								<div class="modal-content">
							    	<div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><i class="fa fa-times"></i></span></button>								    
								    </div>
							    	<div class="modal-body">
							    		<div class="picinfo info-manual-03 vh-70"></div>	
						    		</div>
							    </div>
							  </div>
							</div>
							<div class="modal fade picinfo-manual-04" tabindex="-1" role="dialog" aria-labelledby="picinfo-manual-04">
							  <div class="modal-dialog modal-xxl" role="document">
							    <div class="modal-content">
							    	<div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><i class="fa fa-times"></i></span></button>								    
								    </div>
							    	<div class="modal-body">
							    		<div class="picinfo info-manual-04 vh-70"></div>	
							    	</div>
							    </div>
							  </div>
							</div>
							<div class="modal fade picinfo-registro-02" tabindex="-1" role="dialog" aria-labelledby="picinfo-registro-02">
							  <div class="modal-dialog modal-xxl" role="document">
							    <div class="modal-content">
							    	<div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><i class="fa fa-times"></i></span></button>								    
								    </div>
							    	<div class="modal-body">
							    		<div class="picinfo info-registro-02 vh-70"></div>	
							    	</div>
							    </div>
							  </div>
							</div>	
							<div class="modal fade picinfo-registro-03" tabindex="-1" role="dialog" aria-labelledby="picinfo-registro-03">
							  <div class="modal-dialog modal-xxl" role="document">
							    <div class="modal-content">
							    	<div class="modal-header">
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true"><i class="fa fa-times"></i></span></button>								    
								    </div>
							    	<div class="modal-body">							    	
							    		<div class="picinfo info-registro-03 vh-70"></div>	
							    	</div>
							    </div>
							  </div>
							</div>							
							
							
							
							
						</div>
					</div>

					<div class="clearfix"></div>
					

					
				</contenido>
			</principal>
			 
		</pagina_simple>
		
		

	</xsl:template>

</xsl:stylesheet>
