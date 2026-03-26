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
	<xsl:variable name="contenido" select="//obtenerContenidosByURL/listado/Contenido"/>
		
		<pagina titulo="Crear {$contenido[etiqueta='Administrativo']/texto}">
			
			<principal>
			
				<titulo>Crear Delegado</titulo>
				
				<contenido>
				<div class="box-container">
				
					<xsl:call-template name="LISTADOPERSONAS">
						<xsl:with-param name="Persona" select="//obtenerAdministrativos/Listado/Persona"  />
						<xsl:with-param name="rol" >ADV</xsl:with-param>
						<xsl:with-param name="Destino">administrativo/16.2.do</xsl:with-param>
						<xsl:with-param name="total" select="//contarAdministrativos/Total"/>
						<xsl:with-param name="DestinoPaginacion">administrativo/16.1.do</xsl:with-param>
					</xsl:call-template>
					<area_botones>
						<div class="text-right">
							<boton class="btn btn-primary" estilo="primary" accion="mostrar_buscarpersona();" ><i class="fa fa-floppy-o" aria-hidden="true"/> Crear</boton>
						</div>
					</area_botones>
					
				</div>	
				</contenido>
			</principal>
			
				<xsl:call-template name="VENTANA_BUSCARPERSONA">
					<xsl:with-param name="Titulo">Crear Delegado</xsl:with-param>
					<xsl:with-param name="Formulario">CrearAdministrativo</xsl:with-param>
					<xsl:with-param name="Destino">administrativo/16.2.do</xsl:with-param>
					<xsl:with-param name="PersonaNatural" >SI</xsl:with-param>
				</xsl:call-template>
			<xsl:call-template name="LISTADOPERSONAS_VENTANAS" >
				<xsl:with-param name="todos">NO</xsl:with-param>
			</xsl:call-template>
		</pagina>
	</xsl:template>

</xsl:stylesheet>
