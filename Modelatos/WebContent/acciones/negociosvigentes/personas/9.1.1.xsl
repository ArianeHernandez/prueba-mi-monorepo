<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/ListadoPersonas.xsl" />
	<xsl:include href="context://stylesheets/templates/VentanaBuscarPersona.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		<pagina titulo="Directores de Negocio">
			<principal>
				<titulo>Listado Directores de Negocio</titulo>
				<contenido>
					
					<xsl:call-template name="LISTADOPERSONAS">
						<xsl:with-param name="Persona" select="//obtenerDirectoresNegocio/Listado/Persona"  />
						<xsl:with-param name="id_negocio" select="//id_negocio"/>
						<xsl:with-param name="rol" select="2"/>
						<xsl:with-param name="total" select="//contarPersonasUsuario/Total"/>
						<xsl:with-param name="DestinoPaginacion">negociosvigentes/personas/9.1.1.do</xsl:with-param>
					</xsl:call-template>
					
					<area_botones>
						<boton estilo="volver" destino="negociosvigentes/9.1.do">Volver</boton>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>
					
				</contenido>
			
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
