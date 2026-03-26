<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Variables">
			<javascript>admin/5.2.js</javascript>
			
			<principal>
				<titulo icono="variable">Edicion de Variables</titulo>
				<contenido>
					
					<formulario id="form_edicion" destino="variables/5.1.do">
						<variable id="AccionGuardarVariable" valor="S"/>
						
						<variable id="Variable.id_variable" valor="{//obtenerVariable/Variable/id_variable}"/>
						<variable id="Variable.id_modelo" valor="{//obtenerVariable/Variable/id_modelo}"/>
						
						<bloque-contenido>
							<titulo>Información Básica</titulo>
							<contenido>
								
								<xsl:if test="count(//obtenerVariable/Variable)>0">
									<registro>
										<item>Identificador</item>
										<valor>
											<xsl:value-of select="//obtenerVariable/Variable/id_variable" />
										</valor>
									</registro>
								</xsl:if>
								
								<registro>
									<item>Nombre de la Variable</item>
									<valor>
										<cajatexto id="Variable.nombre_variable" valor="{//obtenerVariable/Variable/nombre_variable}" />
									</valor>
								</registro>
								
								<registro>
									<item>Valor por defecto</item>
									<valor>
										<cajatexto id="Variable.valor_variable" valor="{//obtenerVariable/Variable/valor_variable}" />
									</valor>
								</registro>
								
								<registro>
									<item>Visible por Usuario</item>
									<valor>
										<cajatextoselector id="Variable.visibilidad" valor="{//obtenerVariable/Variable/visibilidad}">
											<opcion valor="S" texto="SI"/>
											<opcion valor="N" texto="NO"/>
										</cajatextoselector>
									</valor>
								</registro>
								
								<registro>
									<item>Editable por Usuario</item>
									<valor>
										<cajatextoselector id="Variable.editable" valor="{//obtenerVariable/Variable/editable}">
											<opcion valor="S" texto="SI"/>
											<opcion valor="N" texto="NO"/>
										</cajatextoselector>
									</valor>
								</registro>
								
								<registro>
									<item>Descripción</item>
									<valor>
										<areatexto id="Variable.descripcion" valor="{//obtenerVariable/Variable/descripcion}" />
									</valor>
								</registro>
								
							</contenido>
						</bloque-contenido>
					</formulario>	
						

					
					<area_botones>
						<boton estilo="volver" destino="variables/5.1.do">Volver</boton>
						<boton estilo="guardar" formulario="form_edicion" validacion="page_validarGuardar()">Guardar</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
