<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Bienvenido" tipo_login="true">
			<javascript>publico/0.js</javascript>
			
			<enlaces_encabezado>
				<enlace>Ayuda</enlace>
				<enlace>Salida Segura</enlace>
			</enlaces_encabezado>
		
			<principal>
				<titulo icono="inicio">Ayuda</titulo>
				<contenido>
							
					<alerta>No se encuentra ayuda disponible</alerta>
					
					<area_botones>
						<boton estilo="volver" destino="inicio/0.pub">Volver</boton>
					</area_botones>
			
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
