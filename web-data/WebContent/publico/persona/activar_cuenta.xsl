<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Bienvenido">
			<javascript>publico/activar_cuenta.js</javascript>
			
			<enlaces_encabezado>
				<enlace>Ayuda</enlace>
				<enlace>Salida Segura</enlace>
			</enlaces_encabezado>
		
			<menu_lateral>
				<titulo>Bienvenido</titulo>
				<enlace icono="inicio" destino="">Home</enlace>
			</menu_lateral>
		
			<menu_lateral>
				<titulo>Hora del Sistema</titulo>
				<informacion_sistema />
			</menu_lateral>
		
			<principal>
				<xsl:if test="loginActual=''">
				
				<titulo icono="inicio">Bienvenido al Módulo de Insolvencia</titulo>
				<contenido>
					
				
					<xsl:if test="validarUsuario/Respuesta = '0' or validarUsuario/Respuesta = '-1'">
						
						<xsl:call-template name="cambio_clave"/>
						
					</xsl:if>
					
					<xsl:if test="validarUsuario/Respuesta='1'">
						<div class="alert alert-danger" role="alert">
							El enlace no es valido. Por favor solicite un enlace de activación nuevo. (1)
						</div>
					</xsl:if>
					
					<xsl:if test="validarUsuario/Respuesta='2'">
						<parrafo estilo="resaltado">
							El enlace no es valido. Por favor solicite un enlace de activación nuevo. (2)
						</parrafo>
					</xsl:if>
			
				</contenido>
				</xsl:if>
				<xsl:if test="loginActual!=''">
						
						<titulo>Redireccionando...</titulo>
						<contenido>
							<a id="reload_page" onclick="location.reload()"></a>
						</contenido>
				</xsl:if>
			</principal>
			
		</pagina>
		
	</xsl:template>
	
	
	<xsl:template name="cambio_clave">
		
		<formulario id="frm_activar" destino="persona/confirmacion.pub">
			
			<div id="area_enviar" style="display:none">
				<div  class="form-horizontal">
					<parrafo>Digite su contraseña de acceso al sistema.</parrafo>
					
					
					<div class="row">
						<div class="col-sm-6">
							<componente_clave_ingreso nuevaclaveingreso="true" confirmacionclave="true"/>
						</div>
					</div>
					
					<variable id="clave" valor="{//clave}" />
					<variable id="login" >
						<xsl:choose>
							<xsl:when test="id_cliente=''">
								<xsl:attribute name="valor"><xsl:value-of select="login"/></xsl:attribute>
							</xsl:when>
							<xsl:otherwise>
								<xsl:attribute name="valor"><xsl:value-of select="login"/>|<xsl:value-of select="id_cliente"/></xsl:attribute>
							</xsl:otherwise>
						</xsl:choose>
					</variable>
					<variable id="id_cliente" valor="{id_cliente}"/>
					
					<div>
						<boton formulario="frm_activar" validacion="validar_login()">Cambiar Clave</boton>
					</div>
				</div>
			</div>
			
			<div class="terminos_uso" id="botones_acepto">
				
                <div class="area_terminos">
                  <p> <texto key="NOTA DE CONFIDENCIALIDAD" /> </p>
              	</div>
                
				<div class="text-center">
                	<h4>¿Está de acuerdo con los términos y condiciones de uso?</h4>
                	<xsl:choose>
                		<xsl:when test="//validarUsuario/Respuesta = '-1'">
		                	<boton accion="aceptarTerminosValidarOTP('{//login}', true)">Acepto</boton>
                		</xsl:when>
                		<xsl:otherwise>
                			<a href="#" onclick="validarUsoToken('{//login}')" class="btn btn-default">Acepto</a>
                		</xsl:otherwise>
                	</xsl:choose>                	
					<a href="{//CONTEXT_PATH}" class="btn btn-default">No Acepto</a>
				</div>
				
			</div>
			
		</formulario>
		
		<ventana id="vn_ingreso_token">
			<titulo>Token de SignApp</titulo>
			<contenido>
				<div class="form-group form-group-sm" style="justify-content: center; display: flex; flex-direction: row">	
					<label class="control-label col-sm-3">Ingreso token <a href="https://www.google.com/search?q=SignApp">SignApp</a> :</label>
					<div class="col-sm-5">
						<input class="form-control" id="token_signapp"></input>
					</div>
				</div>
				
				<area_botones>
					<boton accion="aceptarTerminosValidarOTP('{//login}')">Enviar</boton>
					<boton accion="$('#vn_ingreso_token').fadeOut()">Cancelar</boton>
				</area_botones>
			</contenido>
			
		</ventana>
		
	</xsl:template>

</xsl:stylesheet>
