<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/ListadoNegocios.xsl" />
	<xsl:include href="context://stylesheets/templates/ListadoPersonas.xsl" />
	<xsl:include href="context://stylesheets/templates/VentanaBuscarPersona.xsl" />
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Crear Director de Negocio">
			
			<principal>
				<titulo>Crear Director de Negocio</titulo>
				<contenido>
				<div class="box-container">
					
					<xsl:call-template name="LISTADONEGOCIOS">
						<xsl:with-param name="Destino">director_negocio/7.1.do</xsl:with-param>
						<xsl:with-param name="Negocio" select="//obtenerListadoNegociosActivos/listado/Negocio"  />
						<xsl:with-param name="NegocioActual" select="//id_negocio_actual"/>
					</xsl:call-template>
					
					<xsl:call-template name="LISTADOPERSONAS">
						<xsl:with-param name="Persona" select="//obtenerDirectoresNegocio/Listado/Persona"  />
						<xsl:with-param name="id_negocio" select="//id_negocio_actual"/>
						<xsl:with-param name="rol" >DN</xsl:with-param>
						<xsl:with-param name="Destino">director_negocio/7.2.do</xsl:with-param>
						<xsl:with-param name="total" select="//contarDirectoresNegocio/Total"/>
						<xsl:with-param name="DestinoPaginacion">director_negocio/7.1.do</xsl:with-param>
					</xsl:call-template>
					
					<area_botones>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
						<boton estilo="primary" accion="mostrar_buscarpersona();" >Crear Director de Negocio</boton>
					</area_botones>
				
				</div>	
				</contenido>
			</principal>
			
				<xsl:call-template name="VENTANA_BUSCARPERSONA">
					<xsl:with-param name="Titulo">Crear Director de Negocio</xsl:with-param>
					<xsl:with-param name="Formulario">CrearDirector</xsl:with-param>
					<xsl:with-param name="Destino">director_negocio/7.2.do</xsl:with-param>
					<xsl:with-param name="Variables">
						<variable id="id_negocio" valor="{//id_negocio_actual}" />
					</xsl:with-param>
					<xsl:with-param name="PersonaNatural" >SI</xsl:with-param>
				</xsl:call-template>
			<xsl:call-template name="LISTADOPERSONAS_VENTANAS" />
		</pagina>
	</xsl:template>

</xsl:stylesheet>
