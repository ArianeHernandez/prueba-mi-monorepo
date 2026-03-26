<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<javascript>jquery-1.6.2.min.js</javascript>
		<javascript>jquery-ui-1.8.15.custom.min.js</javascript>
		<javascript>aplicaciones.js</javascript>
		<javascript>core.js</javascript>
		<stylesheet>jquery-ui-1.8.15.custom.css</stylesheet>
		<stylesheet>aplicaciones.css</stylesheet>
		
		<CONTENIDO>
			<xsl:call-template name="plantillas"/>
			<!-- Pestañas de las aplicaciones -->
			<div class="pestana" id="aplicaciones">
				<ul id="pestanas_aplicaciones"/>
			</div>
		</CONTENIDO>
		
	</xsl:template>
	
	<xsl:template name="plantillas">
		<div style="display:none">
			<ul id="PLANTILLA_APLICACION">
				<!-- Pestaña de aplicacion -->
				<li class="aplicacion" id="[ 1 ]"><a id="aplicacion_[ 1 ]" class="aplicacion" title="[ 3 ]">([ 1 ]) [ 2 ]</a></li>
			</ul>
			<div id="CONTENIDO_APLICACION">
				<!-- Contenido de la pestaña o div #aplicaciones  -->
				<div id="contenido_aplicacion_[ 1 ]">
					<!-- div para el listado de roles -->
					<div class="div_left" id="roles_[ 1 ]">
						&#160;
					</div>
					<!-- Div para mostrar el contenido del rol -->
					<div class="div_right" id="contenido_rol_[ 1 ]">&#160;</div>
					<div class="clear">&#160;</div>
				</div>
			</div>
			<!-- Plantilla de cada rol para el listado -->
			<div id="ROL">
				<div  id="rol_[ 1 ]"><a class="rol">([ 1 ]) [ 2 ]</a></div>
			</div>
			<!-- Contenido de cada rol -->
			<div id="CONTENIDO_ROL">
				<h2>([ 1 ]) [ 2 ]</h2>
				
				<div id="secciones_rol_[ 1 ]">
					<h3 href="#"><a>Usuarios</a></h3>
					<div>
						<p class="tit">Listado de usuarios:</p>
						<div id="usuarios_[ 1 ]">&#160;</div>
					</div>
					<h3 id="seccion_servicios_[ 1 ]" href="#"><a>Servicios</a></h3>
					<div>
						<p class="tit">Listado de servicios</p>
						<div id="servicios_[ 1 ]">&#160;</div>
					</div>
				</div>
			</div>
			
			<!-- Usuario -->
			<div id="USUARIO"><a class="usuario">([ 1 ]) [ 2 ]</a></div>
			<div id="SERVICIO">
				<div class="servicio" id="servicio_[ 1 ]"><a>([ 1 ]) [ 2 ]</a></div>
				<div class="urls" id="contenido_servicio_[ 1 ]">&#160;</div>
				<div>&#160;</div>
			</div>
			<div id="URL"><div class="url">[ 1 ]</div></div>
			
		</div>
		
	</xsl:template>
	
		
</xsl:stylesheet>

