<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="ResumenNotificaciones">

		<javascript>templates/resumen_notificaciones.js</javascript>
		<stylesheet>resumen_notificaciones.css</stylesheet>
			<div class="resumen_rnotificaciones">
				<div class="titulo_rnotificaciones clearfix">
					<span class="titulo_rnotificaciones">Notificaciones (<span id="sinleer_rnotificaciones">0</span>)</span>
					<a class="link_rnotificaciones frgt fnt10" onclick="osm_go('notificaciones/21.do')">Ver más</a>
				</div>
				<div class="lista_rnotificaciones" id="lista_rnotificaciones">
					<div id="sin_rnotificaciones">No tiene notificaciones</div>
				</div>
			</div>
			<div class="oculto_rnotificaciones" id="template_rnotificaciones">
				<div class="item_rnotificaciones" onclick="verNotificacion('[ 1 ]')" name="[ 4 ]">
					<span class="titulo_rnotificacion">[ 2 ]</span>
					<span >[ 3 ]</span>
				</div>
			</div>
			<form id="form_rnotificaciones" name="form_rnotificaciones" action="{//CONTEXT_PATH}/notificaciones/21.do">
				<input type="hidden" name="id_notificacion" id="id_notificacion"/>
			</form>
	</xsl:template>
</xsl:stylesheet>