<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<pagina titulo="Bienvenido">
			<javascript>formatos/38.1.js</javascript>
			
			<principal>
				<titulo icono="inicio">Administración de grupos de formatos</titulo>
				<contenido>
					<xsl:choose>
						<xsl:when test="count(//obtenerTodosLosGruposformato/Listado/GrupoFormato)>0">
					
							<formulario destino="formatos/grupoformato/38.2.do" id="form_grupoformato">
								<variable id="id_grupoformato" valor="" />
							</formulario>
							
							<tabla>
								<encabezado>
									<titulo>Nombre grupo</titulo>
									<titulo>Descripción</titulo>
									<titulo>Orden</titulo>

								</encabezado>
								
								<xsl:for-each select="//obtenerTodosLosGruposformato/Listado/GrupoFormato">
									<xsl:sort select="nombre"/>
									<xsl:variable name="id_grupoformato" select="id_grupoformato" />
			
									<fila>
										
										<valor accion="seleccionarGrupo({id_grupoformato})">
												<b><xsl:value-of select="nombre" /></b>
										</valor>
										
										<valor accion="seleccionarGrupo({id_grupoformato})">
												<xsl:value-of select="descripcion" />
										</valor>
										<valor accion="seleccionarGrupo({id_grupoformato})">
												<xsl:value-of select="orden" />
										</valor>
										
									</fila>
								</xsl:for-each>
							</tabla>
							
							
						</xsl:when>
					</xsl:choose>
					
					<area_botones>
						<boton estilo="inicio" destino="inicio/0.do">Ir al Inicio</boton>
						<boton estilo="crear" destino="formatos/grupoformato/38.2.do">Crear Grupo</boton>
						
					</area_botones>
					
				</contenido>
			</principal>
			
		</pagina>

	</xsl:template>

</xsl:stylesheet>
