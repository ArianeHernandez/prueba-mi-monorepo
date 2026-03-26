<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		<javascript>validacion_ip/12.1.js</javascript>
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Acceso por IP">
			<stylesheet>12.css</stylesheet>
			<principal>
				<titulo><texto key="VALIDACION IP" /></titulo>
				<contenido>
					
					<bloque-pestanas>
						<pestana titulo="Lista Autorizada">
							<p>A continuación puede editar la lista de rangos de ips publicas y privadas <b class="r">PERMITIDAS</b> para acceder a la aplicación.</p>
							<xsl:call-template name="diagrama_validacionip">
								<xsl:with-param name="tipo">B</xsl:with-param>
							</xsl:call-template>
						</pestana>
						
						<pestana titulo="Lista Restringida">
							<p>A continuación puede editar la lista de rangos de ips publicas y privadas <b class="r">RESTRINGIDAS</b> para acceder a la aplicación.</p>
							<xsl:call-template name="diagrama_validacionip">
								<xsl:with-param name="tipo">N</xsl:with-param>
							</xsl:call-template>
						</pestana>
					</bloque-pestanas>		
					<area_botones>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>	
					
					
						<script>
							ID_USUARIO='<xsl:value-of select="//Usuario/id_usuario" />';
						</script>
						
						<div id="TEMPLATE_PUBLICA" style="display:none">
							
							<div class="dv_ip_publica dv_ip_publica_simple" id="ip_publica_[ 1 ]">
								
								<h3>IP Publica</h3>
								<p>
									<input class="dv_input" type="text" id="dv_rango_ip_publica_desde_[ 1 ]" value="[ 2 ]" onchange="actualizarIpPublica([ 1 ], '[ 4 ]')"/>
									<input class="dv_input" type="text" id="dv_rango_ip_publica_hasta_[ 1 ]" value="[ 3 ]" onchange="actualizarIpPublica([ 1 ], '[ 4 ]')"/>
								</p>
								
								<a class="dv_del_ippublica" title="Eliminar IP Publica" onclick="eliminarIPPublica([ 1 ])">
									&#160;
								</a>
								
								<a hidden="true" class="dv_add_ipprivada" title="Adicionar IP Privada" onclick="adicionarIPPrivada([ 1 ], '[ 4 ]')">
									&#160;
								</a>
								
								<div hidden="true" id="dv_redes_privadas_[ 1 ]" class="dv_redes_privadas" style="position:relative">
									&#160;
								</div>
								
							</div>
							
						</div>
						
						<div id="TEMPLATE_PRIVADA" style="display:none">
							
							<div class="dv_ip_privada" id="ip_privada_[ 1 ]">
								
								<h3>IP Privada</h3>
								<p>
									<input class="dv_input" type="text" id="dv_rango_ip_privada_desde_[ 1 ]" value="[ 2 ]" onchange="actualizarIpPrivada([ 1 ], '[ 4 ]')"/>
									<input class="dv_input" type="text" id="dv_rango_ip_privada_hasta_[ 1 ]" value="[ 3 ]" onchange="actualizarIpPrivada([ 1 ], '[ 4 ]')"/>
								</p>
								
								<a class="dv_del_ipprivada" title="Eliminar IP Privada" onclick="eliminarIPPrivada([ 1 ])">
									&#160;
								</a>
								
							</div>
							
							
						</div>
				</contenido>
			</principal>
			
		</pagina>
		
		
		
		
		
	</xsl:template>
	
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template name="diagrama_validacionip">
		<xsl:param name="tipo"/>
		
			<div class="diagrama_validacion" id="diagrama_validacion_{$tipo}">
				
				<div class="dv_servidor">
					<h3>Servidor</h3>
					<p><script>document.write(SERVIDOR);</script></p>
				</div>
				
				<div class="dv_internet">
					<a class="dv_add_ippublica" title="Adicionar IP Publica" onclick="adicionarIPPublica('{$tipo}')">
						&#160;
					</a>
				</div>
				
				<div id="dv_redes_publicas_{$tipo}" class="dv_redes_publicas">
					&#160;
				</div>
				
				<div id="dv_publicas_final_{$tipo}" class="dv_publicas_final">
					&#160;
				</div>
				
						
			</div>
			
	</xsl:template>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	

</xsl:stylesheet>
