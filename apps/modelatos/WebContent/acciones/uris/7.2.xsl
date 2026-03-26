<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Uris">
			<javascript>admin/7.2.js</javascript>
			
			<principal>
				<titulo icono="variable">Edicion de Uris</titulo>
				<contenido>
					
					<formulario id="form_edicion" destino="uris/7.1.do">
						<variable id="AccionGuardarUri" valor="S"/>
						
						<variable id="Uri.id_uri" valor="{//obtenerUri/uri/id_uri}"/>
												
						<bloque-contenido>
							<titulo>Información Básica</titulo>
							<contenido>
								
								<xsl:if test="count(//obtenerUri/uri)>0">
									<registro>
										<item>Identificador</item>
										<valor>
											<xsl:value-of select="//obtenerUri/uri/id_uri" />
										</valor>
									</registro>
								</xsl:if>
								
								<registro>
									<item>Nombre de la Uri</item>
									<valor>
										<cajatexto id="Uri.nombre" valor="{//obtenerUri/uri/nombre}" />
									</valor>
								</registro>
								
								<registro>
									<item>EndPoint</item>
									<valor>
										<cajatexto id="Uri.endPoint" valor="{//obtenerUri/uri/endPoint}" />
									</valor>
								</registro>
								
							</contenido>
						</bloque-contenido>
					</formulario>	
						
					
					<formulario id="form_eliminar" destino="uris/7.1.do">
						<variable id="AccionEliminarUri" valor="S"/>
						<variable id="id_uri" valor="{//obtenerUri/uri/id_uri}"/>
					</formulario>
					
					<area_botones>
						<boton estilo="volver" destino="uris/7.1.do">Volver</boton>
						<boton estilo="eliminar" formulario="form_eliminar">Eliminar</boton>
						<boton estilo="guardar" formulario="form_edicion" validacion="page_validarGuardar()">Guardar</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
