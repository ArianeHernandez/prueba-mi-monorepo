<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
    <xsl:template match="acciones">
        
		<enlaces_encabezado>
			<enlace>Ayuda</enlace>
			<enlace>Salida Segura</enlace>
		</enlaces_encabezado>
	
    	<informacion-usuario nombre="{concat(//OSM-INIT-SESSION/Info/Persona/nombre,' ',//OSM-INIT-SESSION/Info/Persona/apellido)}" genero="{//OSM-INIT-SESSION/Info/Persona/genero}"
    	ultimo_ingreso="{//OSM-INIT-SESSION/Info/Credencial/fecha_ingreso}" ip="{//OSM-INIT-SESSION/Info/Credencial/ip_ingreso}"/>    	

		<menu_lateral>
			<titulo>Servicios</titulo>
			<xsl:for-each select="obtenerMenusPorAplicacion/Listado/Menu[count(autorizado)>0 and autorizado='true' and tipo='servicio']">
				<enlace icono="{icono}" destino="{direccion}"><titulo_enlace><xsl:value-of select="titulo"/></titulo_enlace></enlace>
			</xsl:for-each>
		</menu_lateral>
    	
    	<menu_lateral>
			<titulo>Personal</titulo>
			<xsl:for-each select="obtenerMenusPorAplicacion/Listado/Menu[count(autorizado)>0 and autorizado='true' and tipo='personal']">
				<enlace icono="{icono}" destino="{direccion}"><titulo_enlace><xsl:value-of select="titulo"/></titulo_enlace></enlace>
			</xsl:for-each>
		</menu_lateral>
	
    </xsl:template>
	
	
</xsl:stylesheet>