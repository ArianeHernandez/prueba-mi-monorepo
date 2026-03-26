<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="aprobacion">
		<xsl:param name="volver_url" />

		<bloque-contenido>
			<titulo>Confirmación de Aprobación</titulo>
			<contenido>
				
				<xsl:choose>
					<xsl:when test="aprobarCarga/exitoso='true' and //OSM-INIT-SESSION/obtenerOsmoAutenticadorRol/OsmoAutenticaRol/TipoElementoSalidalistarRolesporLogin/id_rol=5">
						<parrafo estilo="resaltado"><texto key="LA CARGA HA SIDO REVISADA" /></parrafo>
					</xsl:when>
					<xsl:when test="aprobarCarga/exitoso='true' and //OSM-INIT-SESSION/obtenerOsmoAutenticadorRol/OsmoAutenticaRol/TipoElementoSalidalistarRolesporLogin/id_rol!=5" >
						<parrafo estilo="resaltado"><texto key="LA CARGA HA SIDO APROBADA" /></parrafo>
					</xsl:when>					

					<xsl:otherwise>
						<parrafo estilo="resaltado"><texto key="NO SE HA APROBADO LA CARGA" /></parrafo>
					</xsl:otherwise>
				</xsl:choose>
				
				<area_botones>
					<boton estilo="primary volver" destino="{$volver_url}">Volver</boton>
				</area_botones>
				
			</contenido>
		</bloque-contenido>

	</xsl:template>

	<xsl:template name="rechazo">
		<xsl:param name="volver_url" />
		<bloque-contenido>
			<titulo>Descartar Solicitud</titulo>
			<contenido>
				<xsl:choose>
					<xsl:when test="rechazarCarga/exitoso='true'">
						<parrafo estilo="resaltado"><texto key="LA CARGA HA SIDO RECHAZADA" /></parrafo>
					</xsl:when>
					
					<xsl:otherwise>
						<parrafo estilo="resaltado"><texto key="LA CARGA NO HA SIDO RECHAZADA" /></parrafo>
					</xsl:otherwise>
				</xsl:choose>
				
				<area_botones>
					<boton estilo="primary volver" destino="{$volver_url}">Volver</boton>
				</area_botones>
				
			</contenido>
		</bloque-contenido>
	</xsl:template>

	<xsl:template name="fallo">
		<xsl:param name="volver_url" />
		<parrafo estilo="resaltado">No se puede realizar la operación.</parrafo>

		<area_botones>
			<boton estilo="primary volver" destino="{$volver_url}">Volver</boton>
		</area_botones>
	</xsl:template>
	
	<xsl:template name="liberacion_parcial">
		
		<xsl:param name="volver_url" />

		<bloque-contenido>
			<titulo>Confirmación de Liberacion Parcial</titulo>
			<contenido>
				
				<xsl:choose>
					<xsl:when test="aprobarCarga/exitoso='true'">
						<parrafo estilo="resaltado"><texto key="LA CARGA HA SIDO LIBERADA PARCIALMENTE" /></parrafo>
					</xsl:when>

					<xsl:otherwise>
						<parrafo estilo="resaltado"><texto key="NO SE HA APROBADO LA CARGA" /></parrafo>
					</xsl:otherwise>
				</xsl:choose>
				
				<area_botones>
					<boton estilo="primary volver" destino="{$volver_url}">Volver</boton>
				</area_botones>
				
			</contenido>
		</bloque-contenido>

	</xsl:template>
	
</xsl:stylesheet>