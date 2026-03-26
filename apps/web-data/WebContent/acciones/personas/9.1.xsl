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
		
		<xsl:variable name="Titulo">
			<xsl:choose>
				<xsl:when test="rol_actual='REV'">Revisor</xsl:when>
				<xsl:when test="rol_actual='PRE'">Preparador</xsl:when>
				<xsl:when test="rol_actual='LIB'">Liberador</xsl:when>
			</xsl:choose>
		</xsl:variable>
		<pagina titulo="{$Titulo}">
			<principal>
				<xsl:choose>
					<xsl:when test="//rol_actual = 'REV'">
						<titulo>Crear <texto key="REVISOR"/></titulo>
					</xsl:when>
					<xsl:when test="//rol_actual = 'PRE'">
						<titulo>Crear <texto key="PREPARADOR"/></titulo>
					</xsl:when>
					<xsl:when test="//rol_actual = 'LIB'">
						<titulo>Crear <xsl:value-of select="$Titulo" /></titulo>
					</xsl:when>
				</xsl:choose>
				
				<contenido>
					
					<xsl:call-template name="LISTADOPERSONAS">
						<xsl:with-param name="Persona" select="//obtenerPersonasUsuario/Listado/Persona"  />
						<xsl:with-param name="id_usuario" select="//id_usuario_actual"/>
						<xsl:with-param name="rol" select="//rol_actual"/>
						<xsl:with-param name="Destino">personas/9.2.do</xsl:with-param>
						<xsl:with-param name="total" select="//contarPersonasUsuario/Total"/>
						<xsl:with-param name="DestinoPaginacion">personas/9.1.do</xsl:with-param>
						<xsl:with-param name="Titulo"><xsl:value-of select="$Titulo"/></xsl:with-param>
						<xsl:with-param name="TiposProcesosPorIdUsuario" select="//obtenerTiposProcesosPorIdUsuario/listadoTipoProceso/TipoProceso"/>
					</xsl:call-template>
					
					<area_botones>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
						<xsl:choose>
							<xsl:when test="//rol_actual = 'REV'">
								<boton estilo="primary" accion="mostrar_buscarpersona();" >Crear <texto key="REVISOR"/></boton>
							</xsl:when>
							<xsl:when test="//rol_actual = 'PRE'">
							<boton estilo="primary" accion="mostrar_buscarpersona();" >Crear <texto key="PREPARADOR"/></boton>
							</xsl:when>
							<xsl:when test="//rol_actual = 'LIB'">
								<boton estilo="primary" accion="mostrar_buscarpersona();" >Crear <xsl:value-of select="$Titulo" /></boton>
							</xsl:when>
						</xsl:choose>
						
					</area_botones>
					
				</contenido>
			
			</principal>
			
			<xsl:call-template name="VENTANA_BUSCARPERSONA">
					<xsl:with-param name="Titulo">Crear <xsl:value-of select="$Titulo" /></xsl:with-param>
					<xsl:with-param name="Formulario">Crear<xsl:value-of select="$Titulo" /></xsl:with-param>
					<xsl:with-param name="Destino">personas/9.2.do</xsl:with-param>
					<xsl:with-param name="Variables">
						<variable id="id_negocio" valor="{//id_negocio_actual}" />
					</xsl:with-param>
					<xsl:with-param name="PersonaNatural" >SI</xsl:with-param>
				</xsl:call-template>
			<xsl:call-template name="LISTADOPERSONAS_VENTANAS" >
				<xsl:with-param name="todos">NO</xsl:with-param>
			</xsl:call-template>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
