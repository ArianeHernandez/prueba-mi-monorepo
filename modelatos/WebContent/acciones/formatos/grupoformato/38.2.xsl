<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/horario.xsl"/>
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		<javascript>formatos/38.2.js</javascript>
		<stylesheet>38.2.css</stylesheet>
		
		<pagina titulo="Edición de Grupo">
			
			<principal>
				<titulo icono="formatos">Edición de grupos</titulo>
				<contenido>
					<xsl:variable name="grupo" select="//obtenerGrupoFormato/GrupoFormato"/>
					<formulario id="form_edicion" destino="formatos/grupoformato/38.3.do">
						<variable id="grupoFormato.id_grupoformato" valor="{$grupo/id_grupoformato}"/>
							
							<bloque estilo="grupo">
								<registro>
									<item>Nombre del Grupo</item>
									<valor>
										<cajatexto id="grupoFormato.nombre" valor="{$grupo/nombre}" />
									</valor>
								</registro>
								
								<registro>
									<item>Descripción</item>
									<valor>
										<cajatexto id="grupoFormato.descripcion" valor="{$grupo/descripcion}" />
									</valor>
								</registro>
								
								<registro>
									<item>Orden</item>
									<valor>
										<cajatexto id="grupoFormato.orden" valor="{$grupo/orden}" />
									</valor>
								</registro>
								
							</bloque>
								
					</formulario>
					
					<formulario id="form_eliminar" destino="formatos/grupoformato/38.4.do">
						<variable id="grupoFormato.id_grupoformato" valor="{$grupo/id_grupoformato}"/>
					</formulario>
						
					<area_botones>
						<xsl:if test="($grupo/id_grupoformato) != 0">
							<boton estilo="eliminar" formulario="form_eliminar" validacion="page_validarEliminar()">Eliminar</boton>
						</xsl:if>	
						<boton estilo="cancelar" destino="formatos/grupoformato/38.1.do">Cancelar</boton>
						<boton estilo="guardar" formulario="form_edicion" validacion="page_validarGuardar()">Guardar Grupo</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
			
		</pagina>
		
	</xsl:template>
	

</xsl:stylesheet>
