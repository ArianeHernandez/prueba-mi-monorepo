<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:dpi="http://www.itosmosys.com/datapi/servicios/AdministradoresPorNegocio">
	
    <xsl:template match="obtenerAdministradoresPorNegocio">
        
    	<obtenerAdministradoresPorNegocio>
    		
    		<xsl:for-each select="//listado/TipoElementoSalidalista_negocios_vs_usua/administradores/AdministradoresArrayElement[estado='Activo']">
	    		<Administrador>
	    			<login><xsl:value-of select="login"/></login>
	    			<nombre><xsl:value-of select="nombre"/></nombre>
	    			<apellido></apellido>
	    			<correo></correo>
	    			<identificacion></identificacion>
	    			<telefono></telefono>
	    			<direccion></direccion>
	    			<genero></genero>
	    		</Administrador>
	    	</xsl:for-each>
    		
	    </obtenerAdministradoresPorNegocio>
    	
    </xsl:template>
	
</xsl:stylesheet>