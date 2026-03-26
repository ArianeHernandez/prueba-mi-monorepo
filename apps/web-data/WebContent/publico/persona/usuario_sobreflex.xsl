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
				<titulo icono="inicio">Bienvenido. <texto key="PLATAFORMA"/></titulo>
				<contenido>
					
					<bloque-contenido>
						<titulo>Usuario de Acceso</titulo>
						<contenido>
							
							<xsl:choose>
								<xsl:when test="string-length(//login_usuario_sobreflex)>0">
								
									<parrafo estilo="resaltado">
										El nombre de usuario para acceder al sistema es: <xsl:value-of select="//login_usuario_sobreflex"/>
									</parrafo>
								
								</xsl:when>
								<xsl:otherwise>
									<parrafo estilo="resaltado">
										Lo sentimos, no se puede visualizar la información del usuario.
									</parrafo>
								</xsl:otherwise>
								
							</xsl:choose>
							
							<area_botones>
								<boton estilo="ingresar" destino="inicio/0.pub">Ingresar</boton>
							</area_botones>
				
						</contenido>
					</bloque-contenido>
			
				</contenido>
			</principal>
			
			<enlaces_pie>
				<enlace>inicio</enlace>
				<enlace>mapa del sitio</enlace>
				<enlace>condiciones de uso</enlace>
				<enlace>preguntas frecuentes</enlace>
				<enlace>aviso legal</enlace>
			</enlaces_pie>
			
		</pagina>
		
	</xsl:template>
	

</xsl:stylesheet>
