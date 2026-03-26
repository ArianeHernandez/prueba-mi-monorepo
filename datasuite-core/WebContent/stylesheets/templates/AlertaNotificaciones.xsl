<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="AlertaNotificaciones">
	
		<xsl:variable name="rnd_version" select="concat('HTS', translate(//version_aplicacion, ' $[]:/.','_______') )"/>

		<script src="{$contextPath}/{$rnd_version}/scripts/templates/alerta_notificaciones.js">&#160;</script>
		<link rel="stylesheet" type="text/css" href="{$contextPath}/{$rnd_version}/styles/alerta_notificaciones.css"/>
		<div id="div_anotificaciones" class="cont_anotificaciones">
			&#160;
		</div>
		<div class="oculto_anotificacion" id="template_anotificaciones">
			<div class="contenido_anotificacion" id="div_notifi_[ 1 ]" onclick="verAlertaNotificacion([ 1 ])">
				<div class="fecha_anotificacion">[ 5 ]</div>
				<div class="cont_anotificacion">
					<div class="titulo_anotificacion">[ 2 ]</div>
					<div class="de_anotificacion">De: </div><div class="emisor_anotificacion">[ 3 ]</div>
					<div class="msj_anotificacion">[ 4 ]</div>
				</div>
				<div class="clear">
					&#160;
				</div>
			</div>
		</div>
		<form id="form_anotificaciones" name="form_anotificaciones" action="{$contextPath}/notificaciones/21.do">
			<input type="hidden" name="id_notificacion" id="id_notificacion"/>
		</form>
	</xsl:template>
	
</xsl:stylesheet>