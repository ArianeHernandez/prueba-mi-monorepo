<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/horario.xsl"/>
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Parametrización">
			<javascript>parametrizacion_cliente/19.1.js</javascript>
			
			
			<principal>
				<titulo icono="formatos">Parametrizacion por cliente</titulo>
				<contenido>
					
					
					<formulario id="form_edicion" destino="parametrizacion_cliente/19.2.do">
						
						<variable id="id_usuario" valor="{//ID_USUARIO}"/>
						<variable id="id_horario" valor="{//obtenerHorarioLiberacion/Horario/id_horario}"/>
						<bloque-pestanas>
					
							<pestana titulo="Horarios">
								
								<registro>
									<item>Horario de liberacion</item>
									<valor>
									<xsl:call-template name="HORARIO" />
									</valor>
								</registro>
																
							</pestana>
							
						</bloque-pestanas>
						
					</formulario>
							
					<area_botones>
						<boton estilo="danger" destino="inicio/0.do">Cancelar</boton>
						<boton estilo="primary guardar" formulario="form_edicion" validacion="page_validarGuardar()">Guardar Parametrizacion</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
			<xsl:call-template name="HORARIO_VENTANA" />
		</pagina>
		
	</xsl:template>
	

</xsl:stylesheet>
