<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:include href="context://common/xsl/osm_html.xsl"/>
	<xsl:include href="context://stylesheets/templates/AlertaNotificaciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/Texto.xsl"/>
	<xsl:include href="context://stylesheets/templates/Estado.xsl"/>
	<xsl:include href="context://stylesheets/templates/TipoCarga.xsl"/>

	<xsl:param name="contextPath" />
	<xsl:param name="page" />

	<xsl:template match="/">
		
		<xsl:for-each select="//pagina">
			<xsl:call-template name="pagina"/>
		</xsl:for-each>
		
		<xsl:for-each select="//pagina_simple">
			<xsl:call-template name="pagina_simple"/>
		</xsl:for-each>
		
	</xsl:template>
	
	<xsl:template name="osm_mensaje">
		<script>
			
			function OSM_VALIDACIONMENSAJE(){
				<xsl:for-each select="//osm_mensaje">
					osm_alert("<xsl:value-of select="."/>");
				</xsl:for-each>
			}
			
			osm_listen("load",window, function(){ osm_timeout( OSM_VALIDACIONMENSAJE, 500) } );
			
		</script>
		
	</xsl:template>
	
	<xsl:template name="pagina_simple">
    	
    	<xsl:variable name="rnd_version" select="concat('HTS', translate(//version_aplicacion, ' $[]:/.','_______') )"/>
    	
        <html>
        	
            <head>
                <title>Módulo de Insolvencia  <xsl:value-of select="@titulo"/></title>
            	
            	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
			    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
            	
            	 <xsl:choose>
                	<xsl:when test="@tipo_login='true'">
                		<link rel="stylesheet" type="text/css" href="{$contextPath}/styles/login.css" />
                	</xsl:when>
                	<xsl:otherwise>
                		<link rel="stylesheet" type="text/css" href="{$contextPath}/styles/simple.css" />
                	</xsl:otherwise>
                </xsl:choose>
            	
            	<link rel="stylesheet" type="text/css" href="{$contextPath}/styles/color.css" />
            	
            	<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/styles/datePicker.css"/>
				<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/styles/selectorfecha.css"/>
				<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/styles/jquery-ui-1.8.19.custom.css"/>
				
            	
            	<xsl:for-each select="//stylesheet[string-length(.)>0]">
					<link rel="stylesheet" type="text/css" href="{$contextPath}/styles/{.}" />
				</xsl:for-each>
				
            	<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/styles/fonts.css"/>
            	<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/publicfiles/bootstrap/css/bootstrap.css"/>
            	<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/publicfiles/bootstrap/css/bootstrap-theme.css"/>
            	<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/publicfiles/font-awesome/css/font-awesome.min.css"/>
            	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,400i,700,700i" rel="stylesheet" />
            	
            	<link id="favicon" rel="shortcut icon" href="{$contextPath}/icons/icon.ico"/>
                <script> var CONTEXTPATH = '<xsl:value-of select="$contextPath"/>';</script>               
				
				<script type="text/javascript" src="{$contextPath}/{$rnd_version}/publicfiles/jquery/jquery-2.2.4.js">;</script>
                <script type="text/javascript" src="{$contextPath}/{$rnd_version}/publicfiles/underscore-min.js">;</script>
				<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/jquery/jquery-ui-1.13.3.js">;</script>
                <script type="text/javascript" src="{$contextPath}/publicfiles/alert/alert.js">;</script>
            	<script type="text/javascript" src="{$contextPath}/scripts/jquery.tools.min.js">;</script>
            	<script type="text/javascript" src="{$contextPath}/publicfiles/bootstrap/js/bootstrap.js">;</script>
				<script type="text/javascript" src="{$contextPath}/scripts/core.js"> </script>
            	<script type="text/javascript" src="{$contextPath}/scripts/color.js"> </script>
				<script type="text/javascript" src="{$contextPath}/scripts/skin.js"> </script>
				<script type="text/javascript" src="{$contextPath}/scripts/notification.js"> </script>
            	<script type="text/javascript" src="{$contextPath}/scripts/jsonrpc2.js"> </script>
				<script type="text/javascript" src="{$contextPath}/scripts/jquery/jquery.form.js"> </script>
				<script type="text/javascript" src="{$contextPath}/scripts/contenido/Texto.js"> </script>
				<script type="text/javascript" src="{$contextPath}/publicfiles/angularjs/angular.min.js"></script>
				
                <xsl:for-each select="//javascript[string-length(.)>0]">
					<script type="text/javascript" src="{$contextPath}/scripts/{.}"> </script>
				</xsl:for-each>
				<!-- Global site tag (gtag.js) - Google Analytics -->
				
            </head>
            <body onload="SKIN_INIT_TIME = {//system-time};">
        	                <xsl:for-each select="//ventana">
                    <xsl:call-template name="ventana"/>
                </xsl:for-each>
            	<div class="bodyContent" id="bodyContent">
            		<div class="head-gov"><a href="https://www.gov.co/home/" target="_blank" class="link-gov">Ir a Gov.co</a></div>
            		<xsl:apply-templates/>
            		<div class="clearfix"></div>
					<div class="footer-area">
						<div class="footer-content">
							<div class="col-md-2">
								<div class="pic img-f01 h-50"></div>
								<div class="pic img-f03 h-50" style="display: none;"></div>
								<div class="pic img-f02 h-70"></div>
							</div>
							<div class="col-md-5">
								<h4><b>Superintendencia de Sociedades</b></h4>
								<br/>
								<p>Dirección: <span>Avenida El Dorado No. 51-80 / Bogotá - Colombia</span></p>
								<p>Horario de Atención:  <span>lunes a viernes de 8:00 a.m. a 5:00 p.m.</span></p>
								<br/><br/><br/>
								<a href="https://twitter.com/ssociedades" target="_blank" class="red-s red-twitter">Twitter</a>
								<a href="https://instagram.com/ssociedades" target="_blank" class="red-s red-instagram">Instagram</a>
								<a href="https://www.facebook.com/SSociedades" target="_blank" class="red-s red-facebook">Facebook</a>
							</div>
							<div class="col-md-5"> 
								<h4>
									<span class="fa fa-phone fa-2x"></span>
									<b>Contacto</b>
								</h4>
								<p>Teléfono Conmutador:  <span>324 57 77 - 220 10 00</span></p>
								<p>Centro de Fax <span>220 10 00, opción 2</span></p>
								<p>Línea de atención al usuario: <span>018000114319</span></p>
								<p>Correo insittucional: <span>webmaster@supersociedades.gov.co</span></p>
								<p>Correo de notificaciones judiciales:<br/>notificacionesjudiciales@supersociedades.gov.co</p>
								<br/>
								<a href="#"> MAPA DEL SITIO </a>  <a href="#"> POLITICAS </a> 
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
                </div>
                
                
            </body>
        </html>
    </xsl:template>
	
	<xsl:template name="pagina">
    	
    	<xsl:variable name="rnd_version" select="concat('R', translate(//version_aplicacion, ' $[]:/.','1234567') )"/>
    	
        <html>
        	
            <head>
                <title>Módulo de Insolvencia <xsl:value-of select="@titulo"/></title>
                
            	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
			    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
                               
            	<link rel="stylesheet" type="text/css" href="{$contextPath}/{$rnd_version}/styles/color.css" />            	
            	<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/{$rnd_version}/styles/datePicker.css"/>
				<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/{$rnd_version}/styles/selectorfecha.css"/>
				<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/{$rnd_version}/styles/jquery-ui-1.8.19.custom.css"/>
            	
            	<xsl:for-each select="//stylesheet[string-length(.)>0]">
					<link rel="stylesheet" type="text/css" href="{$contextPath}/{$rnd_version}/styles/{.}?" />
				</xsl:for-each>
            	
            	<link id="favicon" rel="shortcut icon" href="{$contextPath}/icons/icon.ico"/>
            	<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/styles/fonts.css"/>
            	<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/{$rnd_version}/publicfiles/bootstrap/css/bootstrap.css"/>
            	<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/{$rnd_version}/publicfiles/bootstrap/css/bootstrap-theme.css"/>
            	<link rel="stylesheet" type="text/css" media="screen" href="{$contextPath}/{$rnd_version}/publicfiles/font-awesome/css/font-awesome.min.css"/>
            	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,400i,700,700i" rel="stylesheet" />
            	
            	 <xsl:choose>
                	<xsl:when test="@tipo_login='true'">
                		<link rel="stylesheet" type="text/css" href="{$contextPath}/{$rnd_version}/styles/login.css" />
                	</xsl:when>
                	<xsl:otherwise>
                		<link rel="stylesheet" type="text/css" href="{$contextPath}/{$rnd_version}/styles/index.css" />
                	</xsl:otherwise>
                </xsl:choose>
            	
                <script> var CONTEXTPATH = '<xsl:value-of select="$contextPath"/>';</script>  
                <script type="text/javascript" src="{$contextPath}/{$rnd_version}/publicfiles/jquery/jquery-2.2.4.js">;</script>
                <script type="text/javascript" src="{$contextPath}/{$rnd_version}/publicfiles/underscore-min.js">;</script>
				<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/jquery/jquery-ui-1.13.3.js">;</script>
                <script type="text/javascript" src="{$contextPath}/{$rnd_version}/publicfiles/alert/alert.js">;</script>
            	<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/jquery.tools.min.js">;</script>
            	<script type="text/javascript" src="{$contextPath}/{$rnd_version}/publicfiles/bootstrap/js/bootstrap.js">;</script>
				<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/core.js"> </script>
            	<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/color.js"> </script>
            	<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/skin.js"> </script>
            	<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/skout.js"> </script>
            	<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/notification.js"> </script>
				<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/jsonrpc2.js"> </script>
				<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/jquery/jquery.form.js"> </script>
				<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/contenido/Texto.js"> </script>
            	<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/general.js"> </script>
<!--             	<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/pdf.js"> </script> -->
<!--             	<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/pdf.worker.js"> </script> -->
				<script type="text/javascript" src="{$contextPath}/publicfiles/angularjs/angular.min.js"></script>
				<!-- Global site tag (gtag.js) - Google Analytics -->
				
				
                <xsl:for-each select="//javascript[string-length(.)>0]">
					<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/{.}"> </script>
				</xsl:for-each>
				
<!-- 				<xsl:if test="$contextPath = '/WebData'"> -->
<!-- 					<script type="text/javascript" src="{$contextPath}/{$rnd_version}/scripts/saldo_liberacion/saldo_liberacion.js"> </script> -->
<!-- 				</xsl:if> -->
            	
            </head>
        	
            <body onload="SKIN_INIT_TIME = {//system-time};">
            	<xsl:call-template name="osm_mensaje"/>
            	
            	<div id="block_window">
		            
				</div>
				
            	<div id="block_window_loader"> </div>
            	
                <xsl:for-each select="//ventana">
                    <xsl:call-template name="ventana"/>
                </xsl:for-each>
            	
            	<div class="bloqueo_ventana" style="display:none" id="ventana_iframe">
		            <div class="bloqueo_ventana_fondo"> </div>
		            <div class="bloqueo_ventana_base">
		                <div class="icono48_{@icono} ventana_titulo">
		                    <h1 id="ventana_iframe_titulo"> </h1>
		                </div>
		                <div class="contenido_ventana">
		                    <iframe id="iframe_block" src="" frameborder="0" style="width: 100%">No se puede visualizar el contenido</iframe>
		                	
		                	<div class="row-btn">
		                		<a class="btn icono24_cerrar" onclick="osm_getObjeto('iframe_block').src=''; $('#ventana_iframe').hide(); ">Cerrar</a>
		                	</div>
		                	
		                </div>
					</div>
		        </div>
                
            	<div class="bodyContent" id="bodyContent">            		
            		<div class="all" id="contentsidebar">
	            		<xsl:if test="count(//menu_plugins/*)>0 and count(//no-plugins)=0">
	            			<xsl:attribute name="class">all all_plugin</xsl:attribute>
	            		</xsl:if>
	            		 
	            		<xsl:call-template name="skin_header"/>
	            		<xsl:call-template name="skin_menu"/>
	            		
	            		
	            		<div class="body-content">
	            			<xsl:if test="count(//menu_plugins/*)>0 and count(//no-plugins)=0">
	            				<xsl:attribute name="class">body-content-full</xsl:attribute>
	            			</xsl:if>
	            		
	            			<div class="col-top-line">	            
	            				<xsl:choose>
									<xsl:when test="count(//informacion-usuario)>0">
										<input type="hidden" id="skin_ip_cliente" value="{//client-ip}"/>
										<input type="hidden" id="skin_init_time" value="{//init-session/Info/Credencial/fecha_ingreso/@time}"/>						
										<div class="box-user">																										
											
											<div class="col-sm-6 box-user2">
												<div class="username"><xsl:value-of select="//informacion-usuario/@nombre"/></div>
												<div class="nav-nameuser"><xsl:value-of select="//init-session/Info/Credencial/login"/></div>
												<xsl:if test="//init-session/Info/Usuario/tipo_persona='N'">																						
													<div class="clientname" id="client_name"><xsl:value-of select="//osm_informacion-pagina/init-session/Info/Usuario/nombre"/><xsl:text> </xsl:text><xsl:value-of select="//osm_informacion-pagina/init-session/Info/Usuario/apellido"/> - <xsl:value-of select="//osm_informacion-pagina/init-session/Info/Usuario/identificacion"/></div>
													<div class="last_msg" id="last_msg"></div>												
												</xsl:if>
												<xsl:if test="//init-session/Info/Usuario/tipo_persona='J'">
													<div class="clientname" id="client_name"><xsl:value-of select="//osm_informacion-pagina/init-session/Info/Usuario/nombre"/> - <xsl:value-of select="//osm_informacion-pagina/init-session/Info/Usuario/identificacion"/></div>
												</xsl:if>
												<div class="timeout_msg" id="timeout_msg"> </div>
											</div>
										</div>
										<div class="last_msg" id="last_msg"></div>	
																										
									</xsl:when>					
									<xsl:otherwise>
										
											<p class="name"> </p>
											
										
									</xsl:otherwise>
									
								</xsl:choose>
								
	            			</div>	            		

							<div >

			            		<div class="contenido"> 
			            			
			            			<xsl:if test="count(//menu_plugins/*)>0 and count(//no-plugins)=0">
			        					<xsl:attribute name="class">contenido</xsl:attribute>
			        				</xsl:if>
			            			
			            			<xsl:if test="count(principal/titulo)>0">
				            			<h1 class="contenido_principal_titulo_icono icono32_{principal/titulo/@icono} ">
				                            <xsl:apply-templates select="principal/titulo"/> 
				                        </h1>
			                        </xsl:if>
			            			
			            			<xsl:apply-templates select="principal/contenido"/>
			            		</div>
			            		
			            		<xsl:if test="count(//menu_plugins/*)>0 and count(//no-plugins)=0">
			            			<a href="#menu-toggle" class="btn btn-sidebar" id="menu-toggle"><i class="fa fa-comments"></i></a>
			            			<div class="menu_plugins" id="sidebar-wrapper">
			            				<xsl:apply-templates select="//menu_plugins/*"/>
			            			</div>
			            		</xsl:if>
		            		
		            		</div>
		            	</div>
	            		
            		</div>
                </div>
            	<script>
					$("#menu-toggle").click(function(e) {
						e.preventDefault();
						$("#contentsidebar").toggleClass("toggled");
						});
				</script>
            	<xsl:call-template name="skin_footer"/>
            	
            	<xsl:if test="//menu_plugins//h3='Notificaciones'">
            		<xsl:call-template name="AlertaNotificaciones"/>
            	</xsl:if>
            	
            </body>
        </html>
    </xsl:template>
	
	<xsl:template name="skin_footer">
		<div class="clearfix"></div>	
		
	</xsl:template>
    
	<xsl:template name="skin_menu">
		
				<!-- menu nuevo -->
						
			<div class="col-left">	
				<nav class="navbar navbar-static-top marginBottom-0" role="navigation">
					    <div class="navbar-header">
					    	<a href="{//context-path}/inicio/0.do" class="navbar-logo"></a>
						    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-1">
						        <span class="sr-only">Toggle navigation</span>
						        <span class="icon-bar"></span>
						        <span class="icon-bar"></span>
						        <span class="icon-bar"></span>
						    </button>
					     
					    </div>
				
					
					   	<div class="collapse navbar-collapse" id="navbar-collapse-1">
					      <ul class="nav navbar-nav">
<!-- -->

				<xsl:for-each select="//menu_lateral[count(enlace)>0]">
					<xsl:if test="string-length(enlace/@destino)>0 or (string-length(enlace/@destino)=0 and count(enlace/submenu/enlace)>0) or string-length(enlace/@formulario)>0">
				
				 <li>
					<xsl:variable name="menu_lateral_title" select="titulo"/>
							<xsl:choose>
								<xsl:when test="count(//obtenerContenidosByURL/listado/Contenido[etiqueta=$menu_lateral_title]/texto)>0">
									<a class="dropdown-toggle n1 icono_{@icono}" data-toggle="dropdown" role="button" aria-expanded="false">
										<xsl:value-of select="//obtenerContenidosByURL/listado/Contenido[etiqueta=$menu_lateral_title]/texto"/>									
									</a>
								</xsl:when>
								
								<xsl:otherwise>
									<a class="dropdown-toggle n2 icono_{@icono}" data-toggle="dropdown" role="button" aria-expanded="false"><xsl:value-of select="titulo"/> <b class="caret"></b></a>
								</xsl:otherwise>
							</xsl:choose>
							
							
							<!-- submenu -->
								<ul class="dropdown-menu" role="menu">
									
									<xsl:for-each select="enlace">
										<xsl:if test="count(submenu/enlace)=0 and (string-length(@destino)>0 or string-length(@formulario)>0)">
											<li><a class="item n3 icono_{@icono}">
												<xsl:attribute name="onclick">
													<xsl:if test="string-length(@accion)>0"><xsl:value-of select="@accion"/>;</xsl:if>
													<xsl:if test="string-length(@formulario)>0">osm_enviarFormulario('<xsl:value-of select="@formulario"/>');</xsl:if>
													<xsl:if test="string-length(@destino)>0"> osm_go("<xsl:value-of select="@destino"/>")</xsl:if>
														return true;
												</xsl:attribute>
												
												<xsl:variable name="submenu_title" select="titulo_enlace"/>
													
												<xsl:choose>
													<xsl:when test="count(//obtenerContenidosByURL/listado/Contenido[etiqueta=$submenu_title]/texto)>0">
														 <xsl:value-of select="//obtenerContenidosByURL/listado/Contenido[etiqueta=$submenu_title]/texto"/>
													</xsl:when>
													
													<xsl:otherwise>
														 <xsl:value-of select="titulo_enlace"/>
													</xsl:otherwise>
												</xsl:choose>
												
												<xsl:apply-templates select="formulario"/>
											</a></li>
											</xsl:if>
												
												<!-- submenu submenu -->
												
												<xsl:if test="count(submenu/enlace)>0">
													<li class="dropdown dropdown-submenu">
														<a class="dropdown-toggle n4 icono_{@icono}" data-toggle="dropdown" role="button" aria-expanded="false">
															<xsl:value-of select="titulo_enlace"/>
														</a>											
														<ul id="submenu_submenu" class="dropdown-menu" role="menu">
															<xsl:for-each select="submenu/enlace">
												
																<li><a class="icono_{@icono}" >
																	<xsl:attribute name="onclick">
																		<xsl:if test="string-length(@accion)>0"><xsl:value-of select="@accion"/>;</xsl:if>
																		<xsl:if test="string-length(@formulario)>0">osm_enviarFormulario('<xsl:value-of select="@formulario"/>');</xsl:if>
																		<xsl:if test="string-length(@destino)>0"> osm_go("<xsl:value-of select="@destino"/>")</xsl:if>
																			return true;
																	</xsl:attribute>
																						
																	<xsl:value-of select="titulo_enlace"/>
																	<xsl:apply-templates select="formulario"/>
																</a></li>
												
															</xsl:for-each>
														</ul>
													</li>
												</xsl:if>
												<!-- fin submenu submenu -->
												
											</xsl:for-each>
										</ul>
									</li>
								</xsl:if>			
						
								<xsl:apply-templates select="formulario"/>
								</xsl:for-each>
								
								
								</ul>
								
								<xsl:if test="count(//init-session/Info/Credencial/login) > 0">
								<ul class="nav navbar-nav nav-perfil pull-right">
									<xsl:if test="count(//init-session/*)>0">			
										<li><a class="nav-exit" id="nav-exit2" onclick="osm_go('inicio/0.pub?logout')" > Salida</a></li> 
									</xsl:if>									
								</ul>
								
																		
								</xsl:if>	
							</div>
						
					</nav>
				</div>

		
			<!-- fin menu nuevo -->
		
	</xsl:template>
	
	<xsl:template name="skin_header">
		
		<div class="header">
			<div class="">
				<a href="{//context-path}/inicio/0.do" class="logo">
					<p><xsl:value-of select="//obtenerContenidosByURL/listado/Contenido[etiqueta='texto_titulo']/texto"/> </p>
				</a>
			</div>
			<div class="">
				<div class="col-top">
			
				<xsl:choose>
					<xsl:when test="count(//informacion-usuario)>0">
						<input type="hidden" id="skin_ip_cliente" value="{//client-ip}"/>
						<input type="hidden" id="skin_init_time" value="{//init-session/Info/Credencial/fecha_ingreso/@time}"/>						
											
						
						<div class="col-md-6 box-user">
							<div class="username"><xsl:value-of select="//informacion-usuario/@nombre"/></div>
							<div class="nav-nameuser"><xsl:value-of select="//init-session/Info/Credencial/login"/></div>							
							<xsl:if test="//init-session/Info/Usuario/tipo_persona='J'">
								<div class="clientname" id="client_name"><xsl:value-of select="//osm_informacion-pagina/init-session/Info/Usuario/nombre"/> - <xsl:value-of select="//osm_informacion-pagina/init-session/Info/Usuario/identificacion"/></div>
							</xsl:if>
							<div class="timeout_msg" id="timeout_msg"> </div>
						</div>
						<div class="col-md-6 box-ip">						
							<div class="last_msg" id="last_msg"> </div>	
							<xsl:if test="//init-session/Info/Usuario/tipo_persona='N'">
								<div class="clientname" id="client_name"><xsl:value-of select="//osm_informacion-pagina/init-session/Info/Usuario/nombre"/><xsl:text> </xsl:text><xsl:value-of select="//osm_informacion-pagina/init-session/Info/Usuario/apellido"/> - <xsl:value-of select="//osm_informacion-pagina/init-session/Info/Usuario/identificacion"/></div>
							</xsl:if>								
														
						</div>
																						
					</xsl:when>	
									
					<xsl:otherwise>
						<p class="name"> </p>
						
					</xsl:otherwise>
						
				</xsl:choose>
						
						
				</div>
			</div>
		
		</div>	
	</xsl:template>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="parrafo">
		<div class="alert alert-{@estilo}">       
            <xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="@texto"/>
        	<xsl:apply-templates/> 
        </div>
    </xsl:template>
    
	<xsl:template name="informacion-sistema">
        <div class="informacionsistema">
            <div class="informacionsistema_hora" id="is_hora">00:00</div>
            <div class="informacionsistema_seg" id="is_seg">:00</div>
            <div class="informacionsistema_dia" id="is_dia">Viernes</div>
            <div class="informacionsistema_fecha" id="is_fecha">25/07/2008</div>
        </div>
    </xsl:template>
    
	<xsl:template match="area_botones">
		
		<xsl:choose>
			
			<xsl:when test="@estilo='barra'">
				<div class="row-btn area_botones_barra">
					<xsl:apply-templates/>
				</div>
			</xsl:when>
			
			<xsl:when test="@estilo='simple'">
				<div class="row-btn">
					<xsl:apply-templates/>
				</div>
			</xsl:when>
			
			<xsl:when test="@estilo='login'">
				<div class="row-btn">
					<xsl:apply-templates/>
				</div>
			</xsl:when>
			
			<xsl:otherwise>
				<div class="row-btn">
	        			<xsl:apply-templates/>
	        		<div class="clear"> </div>
		        </div>
			</xsl:otherwise>
		</xsl:choose>
		
		<div class="clear"> </div>
        
    </xsl:template>
    
	<xsl:template match="boton">
        <a estilo="btn btn-sm btn-{@estilo}">
        	<xsl:attribute name="onclick">
        		<xsl:if test="string-length(@validacion)>0">if(!<xsl:value-of select="@validacion"/>){ return false; }</xsl:if>
        		<xsl:if test="string-length(@accion)>0"><xsl:value-of select="@accion"/>;</xsl:if>
				<xsl:if test="string-length(@formulario)>0">osm_enviarFormulario('<xsl:value-of select="@formulario"/>');</xsl:if>
        		<xsl:if test="string-length(@destino)>0"> osm_go("<xsl:value-of select="@destino"/>")</xsl:if>
        		return false;
			</xsl:attribute>
            <xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            </xsl:if>
        	<xsl:if test="count(@visible)>0 and not(@visible='true')">
				<xsl:attribute name="style">display:none</xsl:attribute>
			</xsl:if>
			<xsl:choose>
				<xsl:when test="@estilo = 'volver'">
					<xsl:attribute name="class">btn btn-sm btn-inicio</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'eliminar'">
					<xsl:attribute name="class">btn btn-sm btn-default</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'cancelar'">
					<xsl:attribute name="class">btn btn-sm btn-default</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'danger'">
					<xsl:attribute name="class">btn btn-sm btn-default</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'guardar'">
					<xsl:attribute name="class">btn btn-sm btn-primary</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'aceptar'">
					<xsl:attribute name="class">btn btn-sm btn-primary</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'inicio'">
					<xsl:attribute name="class">btn btn-sm btn-inicio</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'info'">
					<xsl:attribute name="class">btn btn-sm btn-info</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'detalle'">
					<xsl:attribute name="class">btn btn-sm btn-info</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'history'">
					<xsl:attribute name="class">btn btn-sm btn-info</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'crear'">
					<xsl:attribute name="class">btn btn-sm btn-primary</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'download'">
					<xsl:attribute name="class">btn btn-sm btn-primary</xsl:attribute>
				</xsl:when>
				<xsl:when test="@estilo = 'editar'">
					<xsl:attribute name="class">btn btn-sm btn-primary</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="class">btn btn-sm btn-primary</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
			
			
			
             <xsl:apply-templates/>
        </a>
            
	</xsl:template>

	<xsl:template match="componente_clave_ingreso">

	<div class="componente_clave_ingreso">
	
		<div class="caja_selector" id="caja_selector">&#160;</div>

		<div class="caja_teclado" id="caja_teclado">
		
            <div class="contenido_caja_teclado" id="caja_teclado2">
                <div class="teclado_grafico" id="teclado_grafico">
                
                
                <div id="teclado_mayu" style="display: none;">
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 20px;">Q</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 48px;">W</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 76px;">E</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 104px;">R</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 132px;">T</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 160px;">Y</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 188px;">U</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 216px;">I</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 244px;">O</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 272px;">P</a>
                    
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 34px;">A</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 62px;">S</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 90px;">D</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 118px;">F</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 146px;">G</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 174px;">H</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 202px;">J</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 230px;">K</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 258px;">L</a>
                    
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 72px;">Z</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 100px;">X</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 128px;">C</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 156px;">V</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 184px;">B</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 212px;">N</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 240px;">M</a>
                </div>
                
                <div id="teclado_minu" style="display: block;">
               		<a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 20px;">q</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 48px;">w</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 76px;">e</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 104px;">r</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 132px;">t</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 160px;">y</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 188px;">u</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 216px;">i</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 244px;">o</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 272px;">p</a>
                    
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 34px;">a</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 62px;">s</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 90px;">d</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 118px;">f</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 146px;">g</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 174px;">h</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 202px;">j</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 230px;">k</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 258px;">l</a>
                    
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 72px;">z</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 100px;">x</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 128px;">c</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 156px;">v</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 184px;">b</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 212px;">n</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 240px;">m</a>
                </div>
                	
                		
                	<a class="tecla_teclado" id="tshift" onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 44px;">
                		<i class="fa fa-arrow-up" aria-hidden="true"></i></a>
                	
                	
                	
                	<a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 95px; left: 34px;">#</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 95px; left: 62px;">$</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 95px; left: 90px;">%</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 95px; left: 118px;">/</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 95px; left: 146px;">.</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 95px; left: 174px;">:</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 95px; left: 202px;">-</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 95px; left: 230px;">_</a>
                    <a class="tecla_teclado"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 95px; left: 258px;">*</a>
                	
                	
                	
                    <a class="tecla_teclado tecla_numero"  id="tn0" onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 320px;">1</a>
                    <a class="tecla_teclado tecla_numero"  id="tn1" onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 348px;">2</a>
                    <a class="tecla_teclado tecla_numero"  id="tn2" onmousedown="teclaAction( this, 'caja_selector');" style="top: 15px; left: 376px;">3</a>
                    
                    <a class="tecla_teclado tecla_numero"  id="tn3" onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 320px;">4</a>
                    <a class="tecla_teclado tecla_numero"  id="tn4" onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 348px;">5</a>
                    <a class="tecla_teclado tecla_numero"  id="tn5" onmousedown="teclaAction( this, 'caja_selector');" style="top: 42px; left: 376px;">6</a>
                    
                    <a class="tecla_teclado tecla_numero"  id="tn6" onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 320px;">7</a>
                    <a class="tecla_teclado tecla_numero"  id="tn7" onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 348px;">8</a>
                    <a class="tecla_teclado tecla_numero"  id="tn8" onmousedown="teclaAction( this, 'caja_selector');" style="top: 69px; left: 376px;">9</a>
                    
                    <a class="tecla_teclado t2"  onmousedown="teclaAction( this, 'caja_selector');" style="top: 95px; left: 320px; width: 54px;">Borrar</a>
                    <a class="tecla_teclado tecla_numero"  id="tn9" onmousedown="teclaAction( this, 'caja_selector');" style="top: 95px; left: 376px;">0</a>
                	
                </div>
                <script> ordenarteclado();</script>
            </div>
        </div>

		<xsl:if test="@cliente_id='true'">
			<div class="form-group form-group-sm">
				<label for="auth_clienteid">Nombre de Usuario</label>
				<input type="text" autocomplete="off"  class="form-control" id="auth_clienteid" name="auth_clienteid" placeholder="" onblur="this.nofocus()"  onfocus="ocultarTecladoGrafico(); inputtextfocus(this,'caja_selector', 0);"/>
				<input type="hidden" name="auth_username" id="usuario_cliente"/>
			</div>
		</xsl:if>
		
		<xsl:choose>
			<xsl:when test="@identificacion='true' and @desactivarCopyPaste='true'">
				<div class="form-group form-group-sm">
					<div class="input-group input-group-sm">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<input type="text" autocomplete="off"  class="form-control" id="auth_username" name="auth_username" placeholder="Nombre de Usuario" oncopy="return false;" oncut="return false;" onpaste="return false;" onblur="this.nofocus()"  onfocus="ocultarTecladoGrafico(); inputtextfocus(this,'caja_selector', 0);"/>
					</div>	
				</div>
			</xsl:when>
			<xsl:when test="@identificacion='true'">
				<div class="form-group form-group-sm">
					<div class="input-group input-group-sm">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<input type="text" autocomplete="off"  class="form-control" id="auth_username" name="auth_username" placeholder="Nombre de Usuario" onblur="this.nofocus()"  onfocus="ocultarTecladoGrafico(); inputtextfocus(this,'caja_selector', 0);"/>
					</div>	
				</div>
			</xsl:when>		
		</xsl:choose>
		
		<xsl:choose>
			<xsl:when test="@claveingresologin='true' and @desactivarCopyPaste='true'">
			<div class="form-group form-group-sm">
				<div class="input-group input-group-sm">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input type="password" autocomplete="off" class="form-control eTeclado" id="auth_password" name="auth_password" placeholder="Contraseña"  oncopy="return false;" oncut="return false;" onpaste="return false;" onfocus="inputtextfocus(this,'caja_selector', {count(@identificacion)*35}); verTecladoGrafico()" onkeyup="validarPassUp(this);"  onkeydown="validarPassDown(this)"/>
				</div>
			</div>
			</xsl:when>
			<xsl:when test="@claveingresologin='true'">
			<div class="form-group form-group-sm">
				<div class="input-group input-group-sm">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input type="password" autocomplete="off" class="form-control eTeclado" id="auth_password" name="auth_password" placeholder="Contraseña"  onfocus="inputtextfocus(this,'caja_selector', {count(@identificacion)*35}); verTecladoGrafico()" onkeyup="validarPassUp(this);"  onkeydown="validarPassDown(this)"/>
				</div>
			</div>
			</xsl:when>		
		</xsl:choose>
		
		<xsl:if test="@claveingreso='true'">
		<div class="form-group form-group-sm">
			<label class="col-sm-5 control-label" for="auth_password">Contraseña Actual</label>
			 <div class="col-sm-7">
				<div class="input-group input-group-sm">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input type="password" autocomplete="off" class="form-control eTeclado" id="auth_password" name="auth_password" placeholder=""  onfocus="inputtextfocus(this,'caja_selector', {count(@identificacion)*35}); verTecladoGrafico()" onkeyup="validarPassUp(this);"  onkeydown="validarPassDown(this)"/>
				</div>
			</div>
		</div>
		</xsl:if>
	
       	<xsl:if test="@nuevaclaveingreso='true'">
       	<div class="form-group form-group-sm">
			<label class="col-sm-5 control-label" for="new_password">Nueva Contraseña</label>
			 <div class="col-sm-7">
				<div class="input-group input-group-sm">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input type="password" autocomplete="off"  class="form-control eTeclado" id="new_password" name="new_password" placeholder=""  onfocus="inputtextfocus(this,'caja_selector', {count(@identificacion)*35}); verTecladoGrafico()" onkeyup="validarPassUp(this);"  onkeydown="validarPassDown(this)"/>
				</div>
			</div>
		</div>
        </xsl:if>
	            	
       	<xsl:if test="@confirmacionclave='true'">
       	<div class="form-group form-group-sm">
       		<label class="col-sm-5 control-label" for="new_password2">Confirmación de Nueva Contraseña</label>
       		 <div class="col-sm-7">
	       		<div class="input-group input-group-sm">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input type="password" autocomplete="off"  class="form-control eTeclado" id="new_password2" name="new_password2" placeholder="" onfocus="inputtextfocus(this,'caja_selector', {count(@identificacion)*35}); verTecladoGrafico()" onkeyup="validarPassUp(this);"  onkeydown="validarPassDown(this)"/>
				</div>
			</div>
		</div>
        </xsl:if>
	   <xsl:if test="string-length(@recaptchaSiteKey ) > 0">
			<script src='https://www.google.com/recaptcha/api.js?hl=es'/>
	      	<center class="captcha" style="margin: 20px 0px">
	       		<div class="g-recaptcha" data-sitekey="{@recaptchaSiteKey}"></div>
	      	</center>
		</xsl:if>       
			
	</div>
	
    </xsl:template>
    
	<xsl:template match="bloque">
		<section id="{@id}" class="bloqueestilo_{@estilo}" style="position:relative; top:0px; left:0px; display:{@display}; {@style};" onmouseover="this.className='bloqueestilo_{@estilo} bloqueestilo_{@estilo}_over'" onmouseout="this.className='bloqueestilo_{@estilo}'" onclick="{@accion}">
			<xsl:if test="count(@visible)>0 and not(@visible='true')">
				<xsl:attribute name="style">display:none</xsl:attribute>
			</xsl:if>
			<xsl:if test="string-length(.)=0 and count(*)=0"> </xsl:if>
			<xsl:apply-templates/>
			<div style="clear:both"></div>
		</section>
	</xsl:template>
	
	<xsl:template match="bloque-contenido">
        <div class="panel panel-default bloque_contenido_base" id="{@id}">
            <xsl:attribute name="style">
                <xsl:choose>
                    <xsl:when test="@visible='false'">display:none</xsl:when>
                    <xsl:otherwise>display:block</xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>
            <div class="panel-heading {titulo/@estilo}">
                <xsl:if test="count(titulo/@id)>0">
                    <xsl:attribute name="id">
                        <xsl:value-of select="titulo/@id"/>
                    </xsl:attribute>
                </xsl:if>
                <xsl:value-of select="titulo"/>
                <xsl:apply-templates select="titulo/*"/>
            </div>
        	<div class="panel-body bloque_contenido_contenido">
            	<xsl:apply-templates select="contenido"/> 
            </div>
        </div>
    </xsl:template>
    
	<xsl:template match="bloque-pestanas">
		
		<xsl:variable name="canti_activos" select="count(pestana[@visible='true'])"/>
		
		<div class="bloque_pestanas_area">
			
			<xsl:variable name="idbase" select="generate-id(.)"/>

			<div class="bloque_pestanas_titulos_area">
				
				<xsl:for-each select="pestana">
					
					<xsl:variable name="idbase" select="$idbase"/>
					
					<div id='P_{$idbase}_{position()}'>
						<xsl:attribute name="class">
							<xsl:choose>
								<xsl:when test="($canti_activos = 0 and position()=1) or @visible='true'">bloque_pestanas_pestana_over</xsl:when>
								<xsl:otherwise>bloque_pestanas_pestana</xsl:otherwise>
							</xsl:choose> icono_<xsl:value-of select="@icono"/>
						</xsl:attribute>
						
						<xsl:attribute name="onclick">
							<xsl:if test="count(@action)>0">
								<xsl:value-of select="@action"/>;
							</xsl:if>
							
							<xsl:for-each select="../pestana"> osm_setClassname('<xsl:value-of select="concat('P_',$idbase,'_',position())"/>', 'bloque_pestanas_pestana icono_<xsl:value-of select="@icono"/>'); </xsl:for-each>;
							<xsl:for-each select="../pestana"> $('#<xsl:value-of select="concat($idbase,'_',position())"/>').hide(); </xsl:for-each>;
							$('#<xsl:value-of select="concat($idbase,'_',position())"/>').fadeIn(400);
							osm_setClassname('<xsl:value-of select="concat('P_',$idbase,'_',position())"/>', 'bloque_pestanas_pestana_over icono_<xsl:value-of select="@icono"/>');
						</xsl:attribute>
						
						<div><xsl:value-of select="@titulo"/></div>
					</div>
				</xsl:for-each>
				
				<div class="bloque_pestanas_separador"> </div>
			</div>
			
			<div class="bloque_pestanas_contenido_area">
				
				<xsl:for-each select="pestana">
					<div class="bloque_pestanas_contenido" id="{$idbase}_{position()}">
						<xsl:attribute name="style">
							<xsl:choose>
								<xsl:when test="($canti_activos = 0 and position()=1) or @visible='true'">display:block</xsl:when>
								<xsl:otherwise>display:none</xsl:otherwise>
							</xsl:choose>
						</xsl:attribute>
						
						<span></span>
						
						<xsl:apply-templates/>
						 
					</div>
				</xsl:for-each>
			</div>
						
		</div>
		
	</xsl:template>
	
	
	<xsl:template match="bloque-mensaje">
        <div class="bloque_vcontenido_base" id="{@id}">
            <xsl:attribute name="style">
                <xsl:choose>
                    <xsl:when test="@visible='false'">display:none</xsl:when>
                    <xsl:otherwise>display:block</xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>
            <div class="bloque_vcontenido_inf">
                <div class="bloque_vcontenido_sup">
                    <h2 class="icono_{titulo/@icono}">
                        <xsl:if test="count(titulo/@id)>0">
                            <xsl:attribute name="id">
                                <xsl:value-of select="titulo/@id"/>
                            </xsl:attribute>
                        </xsl:if>
                        <xsl:value-of select="titulo"/>
                    </h2>
                    <xsl:apply-templates select="contenido"/>
                </div>
            </div>
        </div>
    </xsl:template>
	
	<xsl:template match="registro">
        	<!--div class=" form-horizontal box-container"-->
	            <xsl:if test="count(@id)>0">
	                <xsl:attribute name="id">
	                    <xsl:value-of select="@id"/>
	                </xsl:attribute>
	            </xsl:if>
	            <xsl:attribute name="style">
	            	<xsl:if test="count(@ancho)>0">
	        			width:<xsl:value-of select="@ancho"/>
	        		</xsl:if>
	                <xsl:if test="@visible='false'">display:none</xsl:if>
	            </xsl:attribute>
	            
        		<div class="form-group form-group-sm">
        			<xsl:choose>
        				<xsl:when test="@unico='true'">
        						<div class="col-sm-12 ">
					                <xsl:apply-templates select="valor"/> 
					            </div>
        				</xsl:when>
        				<xsl:otherwise>
			        			<label class="control-label col-md-5 col-sm-5">
						                <xsl:if test="count(item/@id)>0">
						                    <xsl:attribute name="id">
						                        <xsl:value-of select="item/@id"/>
						                    </xsl:attribute>
						                </xsl:if>
			        					<xsl:apply-templates select="item"/>
					            </label>
		        				
		        				<div class="control-label-value col-md-7 col-sm-6">
						             <xsl:apply-templates select="valor"/> 
					            </div>
        				</xsl:otherwise>
        			</xsl:choose>
        			
        		</div>
      				            
	        <!-- /div-->		
			<div style="clear:both"></div>
    </xsl:template>
    
	<xsl:template match="cajatexto">
        <input class="form-control" type="text" value="{@valor}" autocomplete="off"  alias="{@alias}" reference="{@reference}" onclick="{@onclick}" onmouseover="{@onmouseover}" onfocus="{@onfocus}">
        	<xsl:if test="count(@accion)>0">
        		<xsl:attribute name="onkeypress">
        			var keynum;	if(window.event){ keynum = event.keyCode; } else if(event.which){ keynum = event.which; }; if(keynum==13){<xsl:value-of select="@accion"/>;}
        		</xsl:attribute>
        	</xsl:if>
        	<xsl:if test="count(@onchange)>0">
	   			<xsl:attribute name="onchange">
		   			<xsl:value-of select="@onchange"/>
		   		</xsl:attribute>
		   	</xsl:if>
		   	<xsl:if test="count(@onblur)>0">
	   			<xsl:attribute name="onblur">
		   			<xsl:value-of select="@onblur"/>
		   		</xsl:attribute>
		   	</xsl:if>
		    <xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            	<xsl:attribute name="name">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            </xsl:if>
        	<xsl:if test="count(@maxlength)>0">
                <xsl:attribute name="maxlength">
                    <xsl:value-of select="@maxlength"/>
                </xsl:attribute>
            </xsl:if>
        	<xsl:if test="@desactivado='true'">
				<xsl:attribute name="readonly">readonly</xsl:attribute>
			</xsl:if>
			<xsl:if test="count(@placeholder)>0">
				<xsl:attribute name="placeholder">
					<xsl:value-of select="@placeholder"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:if test="@bloqueado='true'">
				<xsl:attribute name="style">pointer-events:none</xsl:attribute>
			</xsl:if>
        </input>
    </xsl:template>
	
	<xsl:template match="cajaarchivo">
        <input class="form-control" type="file" value="{@valor}">
            <xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            	<xsl:attribute name="name">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            </xsl:if>
        	<xsl:if test="@desactivado='true'">
				<xsl:attribute name="readonly">readonly</xsl:attribute>
			</xsl:if>
        </input>
    </xsl:template>

	<xsl:template match="cajachequeo">
        <div class="cajachequeo_area">
        	
			<input class="cajachequeo {@class}" type="checkbox" value="{@valor}" autocomplete="off" id="{@id}" name="{@id}" onclick="{@onclick}" onmouseover="{@onmouseover}">
				<xsl:attribute name="onchange">
	        		<xsl:if test="string-length(@validacion)>0">if(!<xsl:value-of select="@validacion"/>){ return false; }</xsl:if>
	        		<xsl:if test="string-length(@accion)>0"><xsl:value-of select="@accion"/>;</xsl:if>
	    		</xsl:attribute>
	            <xsl:if test="@seleccionado='true'">
	            	<xsl:attribute name="checked">checked</xsl:attribute>
	            </xsl:if>
				<xsl:if test="@desactivado='true'">
					<xsl:attribute name="readonly">readonly</xsl:attribute>
				</xsl:if>
	        </input> <xsl:value-of select="@texto"/>
        	
	    </div>
    </xsl:template>

	<xsl:template match="cajachequeo2">
		
        <div id="cajachequeo2_divarea_{@id}">
        	<xsl:choose>
        		<xsl:when test="@desactivado='true'"></xsl:when>
        		<xsl:otherwise>
        			<xsl:attribute name="onclick">
		        		var tmp = osm_getObjeto('<xsl:value-of select="@id"/>');
		        		var ttpm =  osm_getObjeto('cajachequeo2_divarea_<xsl:value-of select="@id"/>');

		        		var valido = true;
		        		
		        		<xsl:if test="string-length(@validacion)>0">
		        		 	valido = <xsl:value-of select="@validacion"/>;
		        		</xsl:if>
		        		
		        		if(valido){
		        		
			        		if(tmp.checked){
			        			ttpm.className = 'cajachequeo2_false';
			        			osm_setValor('<xsl:value-of select="@id"/>', '<xsl:value-of select="@valor2"/>');
			        			tmp.checked = false;
			        		}else{
			        			ttpm.className = 'cajachequeo2_true';
			        			osm_setValor('<xsl:value-of select="@id"/>', '<xsl:value-of select="@valor"/>');
			        			tmp.checked = true;
			        		}
		        		
		        			<xsl:value-of select="@accion"/>;
		        		}
	        		
	        	</xsl:attribute>
        		</xsl:otherwise>
        	</xsl:choose>
        	
        	<xsl:attribute name="class">
	        	<xsl:choose>
	        		<xsl:when test="@seleccionado='true'">cajachequeo2_true</xsl:when>
	        		<xsl:otherwise >cajachequeo2_false</xsl:otherwise>
	        	</xsl:choose>
        	</xsl:attribute>
        	
        	<input type="hidden" id="{@id}" name="{@id}" class="{@class}">
				<xsl:attribute name="value">
		        	<xsl:choose>
		        		<xsl:when test="@seleccionado='true'"><xsl:value-of select="@valor"/></xsl:when>
		        		<xsl:otherwise><xsl:value-of select="@valor2"/></xsl:otherwise>
		        	</xsl:choose>
	        	</xsl:attribute>
			</input>
        	
        	 	

	    </div>
		
		<script>
    		var tmp = osm_getObjeto('<xsl:value-of select="@id"/>');
    		tmp.hiddenvalue = '<xsl:value-of select="@valor"/>';
        	<xsl:choose>
        		<xsl:when test="@seleccionado='true'"> tmp.checked = true; </xsl:when>
        		<xsl:otherwise> tmp.checked = false; </xsl:otherwise>
        	</xsl:choose>
			
			tmp.divarea = osm_getObjeto('cajachequeo2_divarea_<xsl:value-of select="@id"/>');
			
			tmp.setChecked = function(val){
				
				if(val){
					this.divarea.className = 'cajachequeo2_true';
	    			osm_setValor(this.id, '<xsl:value-of select="@valor"/>');
	    			this.checked = true;
				}else{
					this.divarea.className = 'cajachequeo2_false';
	    			osm_setValor(this.id, '<xsl:value-of select="@valor2"/>');
	    			this.checked = false;
				}
			}
			
    	</script>
		
    </xsl:template>
		
	<xsl:template match="cajacolor">
		
		<div style="float: left">
		    <div id="ccambas_{@id}" class="COLORPICK"> </div>
			<input type="hidden" id="{@id}" name="{@id}" value="{@valor}"/>
			<a id="botonpickid_{@id}" style="cursor:pointer" onclick="colorpickinit('ccambas_{@id}','{@id}','botonpickid_{@id}','divcolor_{@id}'); return false;">
				<div id="divcolor_{@id}" style="display:block; width: 20px; height: 20px; border: 1px solid black; background-color:{@valor}"> </div>
			</a>
		</div>
		
	</xsl:template>
	
	<xsl:template match="areatexto">
        <textarea class="form-control" type="text" autocomplete="off" onchange="{@accion};" onclick="{@onclick}" onmouseover="{@onmouseover}"><xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            	<xsl:attribute name="name">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
	        	<xsl:if test="@desactivado='true'">
					<xsl:attribute name="readonly">readonly</xsl:attribute>
				</xsl:if>
            </xsl:if><xsl:if test="string-length(@valor)=0"> </xsl:if><xsl:value-of select="@valor"/></textarea>
    </xsl:template>

	<xsl:template match="cajaclave">
        <input class="form-control" type="password" value="{@valor}" autocomplete="off">
        	<xsl:if test="count(@accion)>0">
        		<xsl:attribute name="onkeypress">
        			var keynum;	if(window.event){ keynum = event.keyCode; } else if(event.which){ keynum = event.which; }; if(keynum==13){<xsl:value-of select="@accion"/>;}
        		</xsl:attribute>
        	</xsl:if>
            <xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            	<xsl:attribute name="name">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            </xsl:if>
        	<xsl:if test="@desactivado='true'">
				<xsl:attribute name="readonly">readonly</xsl:attribute>
			</xsl:if>
        </input>
    </xsl:template>
    
	<xsl:template match="cajatextoselector">
        <select class="form-control {@class}" autocomplete="off" onchange="{@accion};" onclick="{@onclick}" onmouseover="{@onmouseover}">
            <xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            	<xsl:attribute name="name">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            </xsl:if>
        	<xsl:if test="count(@accion)>0">
			      <xsl:attribute name="onchange">
			             <xsl:value-of select="@accion"/>
			       </xsl:attribute>
			</xsl:if>
        	<xsl:if test="@desactivado='true'">
				<xsl:attribute name="readonly">readonly</xsl:attribute>
			</xsl:if>
        	
            <xsl:apply-templates>
            	<xsl:with-param name="valordefecto" select="@valor"/>
            </xsl:apply-templates>
             
        </select>
    </xsl:template>
   	
	<xsl:template match="cajafecha">
		<input type="text" name="{@id}" id="{@id}" value="{@valor}" class="date-pick form-control {@class}">
			<xsl:if test="count(@placeholder)>0">
				<xsl:attribute name="placeholder">
					<xsl:value-of select="@placeholder"/>
				</xsl:attribute>
			</xsl:if>
		</input>
	</xsl:template> 
	
    <xsl:template match="opcion">
    	<xsl:param name="valordefecto"/>
		<option value="{@valor}">
			<xsl:if test="@valor=$valordefecto or @seleccionado='true'">
				<xsl:attribute name="selected">selected</xsl:attribute>
			</xsl:if>
			<xsl:if test="@desactivado='true'">
				<xsl:attribute name="disabled">disabled</xsl:attribute>
			</xsl:if>
            <xsl:value-of select="@texto"/><xsl:apply-templates/>
         </option>
    </xsl:template>
    
    <xsl:template match="grupo_opcion">
    		<optgroup label="{@titulo}">
	             <xsl:apply-templates/>
	         </optgroup>
    </xsl:template>
    
	<xsl:template match="constante">
        <div class="constante">
            <xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="@texto"/><xsl:value-of select="@valor"/><xsl:apply-templates/> 
		</div>
    </xsl:template>
    
	<xsl:template name="ventana">
        <div class="bloqueo_ventana" style="display:none">
            <xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            </xsl:if>
            <div class="bloqueo_ventana_fondo">
				  
			</div>
            <div class="bloqueo_ventana_base">
                <div class="icono48_{@icono} ventana_titulo">
                    <h1>
                        <xsl:apply-templates select="titulo"/>
                    </h1>
                </div>
                <div class="contenido_ventana panel-body">
                    <xsl:apply-templates select="contenido"/>
                </div>
			</div>
        </div>
    </xsl:template>
    
	<xsl:template match="subtitulo">
        <h2>
        	<xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:if test="count(@accion)>0">
                <xsl:attribute name="onclick">
                    <xsl:value-of select="@accion"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:if test="count(@class)>0">
                <xsl:attribute name="class">
                    <xsl:value-of select="@class"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="@texto"/><xsl:apply-templates/>
        </h2>
    </xsl:template>
	
	<xsl:template match="titulo[name(..)!='principal']">
        <div class="panel-heading">
        	<xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="@texto"/><xsl:apply-templates/>
        </div>
    </xsl:template>

	<xsl:template match="tabla">
		
		<div class="tabla_borde table-responsive">
			
			<xsl:if test="count(@id)>0">
                <xsl:attribute name="id">
                    <xsl:value-of select="@id"/>
                </xsl:attribute>
            </xsl:if>
            
			<table class="table table-hover table-striped" cellspacing="0" cellpadding="0">
				<xsl:for-each select="encabezado">
					<tr class="tabla_encabezado">
						
						<xsl:if test="count(../@icono)>0">
							<td width="1"> </td>
						</xsl:if>
						
						<xsl:for-each select="titulo">
							<td>
								<xsl:if test="count(@accion)>0">
					                <xsl:attribute name="onclick">
					                    <xsl:value-of select="@accion"/>
					                </xsl:attribute>
					            </xsl:if>
								<xsl:if test="count(@class)>0">
					                <xsl:attribute name="class">
					                    <xsl:value-of select="@class"/>
					                </xsl:attribute>
					            </xsl:if>
								<xsl:apply-templates/>
							</td>
						</xsl:for-each>
					</tr>
				</xsl:for-each>
				<xsl:for-each select="fila">
					
					<tr class="tabla_fila {@class}" onmouseover="this.className='tabla_fila_over {@class}'" onmouseout="this.className='tabla_fila {@class}'">
						 <xsl:if test="count(@id)>0">
			                <xsl:attribute name="id">
			                    <xsl:value-of select="@id"/>
			                </xsl:attribute>
			            </xsl:if>
						<xsl:if test="count(@accion)>0">
			                <xsl:attribute name="onclick">
			                    <xsl:value-of select="@accion"/>
			                </xsl:attribute>
			            </xsl:if>
						
						<xsl:if test="count(../@icono)>0">
							<td width="1"><img src="{$contextPath}/images/records/{../@icono}.png"/></td>
						</xsl:if>
						
						<xsl:for-each select="valor">
							<td >
								<xsl:if test="count(@class)>0">
					                <xsl:attribute name="class">
					                    <xsl:value-of select="@class"/>
					                </xsl:attribute>
					            </xsl:if>
								<xsl:if test="count(@accion)>0">
					                <xsl:attribute name="onclick">
					                    <xsl:value-of select="@accion"/>
					                </xsl:attribute>
					            </xsl:if>
								<xsl:if test="count(@id)>0">
					                <xsl:attribute name="id">
					                    <xsl:value-of select="@id"/>
					                </xsl:attribute>
					            </xsl:if>
								<xsl:apply-templates/>
							</td>
						</xsl:for-each>
					</tr>
				</xsl:for-each>
				
			</table>
		
		</div>
		
	</xsl:template>
	
	<xsl:template match="paginacion">
		
		<xsl:variable name="totalpaginas" select="floor( ( number(@total) + number(@paginacion) - 1 ) div number(@paginacion) )"/>
		<input type="hidden" name="{@id}" id="{@id}" value="{@numero}"/>
		
		<xsl:if test="( (number(@numero)-1)*number(@paginacion) ) >= number(@total)  and  number(@total)>0">
			<script>
					osm_listen("load", window, function(e){
						
						osm_setValor('<xsl:value-of select="@id"></xsl:value-of>', 1 );
						osm_enviarFormulario('<xsl:value-of select="@formulario"></xsl:value-of>');
	
					});
			</script>
		
		</xsl:if>
		
			
		
			<xsl:if test="2 > ceiling(number(@total) div number(@paginacion))">
				<xsl:attribute name="style">display:none</xsl:attribute>
			</xsl:if>
			
			
		
			<nav aria-label="Page navigation" class="text-center">		
				<ul class="pagination">
					
					<xsl:if test="number(@numero)>1">
						<li>
							<a onclick="osm_setValor('{@id}', 1 );  osm_enviarFormulario('{@formulario}')" aria-label="ir al inicio">
							       <i class="fa fa-fast-backward" aria-hidden="true"></i>
							</a>
						</li>
					</xsl:if>
					
					<xsl:if test="number(@numero)>1">
						<li>
							<a onclick="osm_setValor('{@id}', {@numero} - 1 );  osm_enviarFormulario('{@formulario}')" aria-label="anterior">
							        <i class="fa fa-backward" aria-hidden="true"></i>
							</a>
						</li>
					</xsl:if>
					
					<li>
						<a> Pagina <xsl:value-of select="@numero"/> de <xsl:value-of select="ceiling(number(@total) div number(@paginacion))"/></a>
					</li>

					<xsl:if test="number($totalpaginas)>number(@numero)">					
						<li>
							<a onclick="osm_setValor('{@id}', {@numero} +1 );  osm_enviarFormulario('{@formulario}')" aria-label="siguiente">
							       <i class="fa fa-forward" aria-hidden="true"></i>
							</a>
						</li>
					</xsl:if>
					
					<xsl:if test="number($totalpaginas)>number(@numero)">
						<li>
							<a onclick="osm_setValor('{@id}', {$totalpaginas} );  osm_enviarFormulario('{@formulario}')" aria-label="ir al final">
							        <i class="fa fa-fast-forward" aria-hidden="true"></i>
							</a>
						</li>
					</xsl:if>
			</ul>
		</nav>
			
		
	</xsl:template>
	
	<xsl:template match="formulario">
		<form action="{$contextPath}/{@destino}" method="post" enctype="{@dato}" onsubmit="return false;" >
			<xsl:if test="string-length(@id)>0">
				<xsl:attribute name="name"><xsl:value-of select="@id"/></xsl:attribute>
				<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>
			</xsl:if>
			<input type="hidden" name="csrfToken" value="f1b8105da0274dae86b17813b6fdc1532imhf38xjj8kuqo8ch7ee9jly4dyhku6"/> 
			<input type="hidden" name="osm_lastpage" value="{$page}"/>
			<input type="hidden" name="osm_ticket" value="{//osm_informacion-pagina/osm_tiquete}"/>
			<xsl:apply-templates/>
		</form>
	</xsl:template>
	
	<xsl:template match="escapar">
		<xsl:copy-of select="*"/>
	</xsl:template>
	
	<xsl:template match="variable">
		<input type="hidden" id="{@id}" name="{@id}" value="{@valor}" class="{@class}"/>
	</xsl:template>

	<xsl:template match="ocultable">
		
		<xsl:variable name="idgen" select="generate-id(.)"/>
		<div>
			
			<div class="ocultable_oculto">
				<xsl:if test="not(@visible='false')">
					<xsl:attribute name="style">display:none</xsl:attribute>	
				</xsl:if>
				<a class="ocultable_link btn btn-info btn-sm" onclick="$(this).parent().parent().find('.ocultable_oculto').hide(); $(this).parent().parent().find('.ocultable_contenido').slideDown(200);" style="cursor:pointer" ><i class="fa fa-chevron-circle-down" aria-hidden="true"></i>&#160; <xsl:value-of select="@textooculto"/></a>
			</div>
			
			<div class="ocultable_contenido">
				
				<xsl:if test="@visible='false'">
					<xsl:attribute name="style">display:none</xsl:attribute>	
				</xsl:if>
				<xsl:apply-templates/>
				<br/>
				<br/>
				<a class="ocultable_link btn btn-info btn-sm" onclick="$(this).parent().parent().find('.ocultable_oculto').show(); $(this).parent().parent().find('.ocultable_contenido').slideUp(200);" style="cursor:pointer" ><i class="fa fa-chevron-circle-up" aria-hidden="true"></i>&#160; <xsl:value-of select="@textovisible"/></a>
				<br/>

			</div>
		</div>

	</xsl:template>
	
	<xsl:template match="javascript">
		<xsl:if test="count(@accion)>0">
			<script><xsl:value-of select="@accion"/></script>
		</xsl:if>
	</xsl:template>

	<xsl:template match="stylesheet">
	</xsl:template>

	<xsl:template match="nota">
		<div class="alert alert-info " style="{@style}">
			<i class="fa fa-info-circle" aria-hidden="true">&#160;</i><span id="{@id}"><xsl:apply-templates/></span>
		</div>		
	</xsl:template>
	
	<xsl:template match="alerta">
		<div class="alert alert-danger " style="{@style}">
			<i class="fa fa-exclamation-triangle" aria-hidden="true">&#160;</i><span id="{@id}"><xsl:apply-templates/></span>
		</div>		
	</xsl:template>
	
	<xsl:template match="aviso">
		<div class="alert alert-warning" style="{@style}">
			<i class="fa fa-info-circle" aria-hidden="true">&#160;</i><span id="{@id}"><xsl:apply-templates/></span>
		</div>		
	</xsl:template>

	<xsl:template match="dialogo">
		<script>
			$(function() {
				$( "#<xsl:value-of select="@id"/>" ).dialog({
					autoOpen: false,
					width: 680
				});
			});
		</script>
		<div id="{@id}">
			<xsl:if test="count(@titulo)>0">
				<xsl:attribute name="title">
					<xsl:value-of select="@titulo"/>
				</xsl:attribute>
			</xsl:if>
			<xsl:apply-templates/>
		</div>
		<a class="ocultable_link btn btn-info btn-sm" style="cursor:pointer">
			<xsl:attribute name="onclick">
				$(function() {
					$("#<xsl:value-of select="@id"/>" ).dialog( "open" );
				});
			</xsl:attribute>
			<xsl:value-of select="@textoboton"/>
		</a>
	</xsl:template>
	
	<xsl:template match="ventana"></xsl:template>
	
	 <xsl:template match="@*|node()">
	    <xsl:copy>
	      <xsl:apply-templates select="@*|node()"/>
	    </xsl:copy>
	  </xsl:template>
	
</xsl:stylesheet>
