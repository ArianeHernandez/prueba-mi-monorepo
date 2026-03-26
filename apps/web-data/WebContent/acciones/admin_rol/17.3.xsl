<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Confirmación Guardado">
			
			<principal>
				<titulo icono="formatos">Confirmación Guardado</titulo>
				<contenido>
				<div class="box-container">
					<xsl:choose>

						<xsl:when test="//guardarRol/guardado ='true'">
								<xsl:choose>
									<xsl:when test="count(//obtenerRol/Rol/id_rol)>0">
										<div class="alert alert-info"><i class="fa fa-info-circle" aria-hidden="true"></i> El rol '<xsl:value-of select="//obtenerRol/Rol/nombre_rol"/>' se ha guardado Exitosamente.</div>
									</xsl:when>
									
									<xsl:otherwise>
										<div class="alert alert-success"><i class="fa fa-check-circle" aria-hidden="true"></i>El rol se ha guardado exitosamente.</div>
									</xsl:otherwise>
									
								</xsl:choose>	
						</xsl:when>
						
						<xsl:otherwise>
								<div class="alert alert-danger"><i class="fa fa-exclamation-circle" aria-hidden="true"></i>No se ha podido realizar la operacion.</div>
						</xsl:otherwise>
						
					</xsl:choose>
					
					
					<formulario id="form_editar" destino="admin_rol/17.2.do">
						<variable id="id_rol" valor="{//obtenerRol/Rol/id_rol}"/>
					</formulario>
										
					<area_botones>
						<xsl:if test="(//obtenerRol/Rol/id_rol)>0">
							<boton estilo="primary editar" formulario="form_editar">Editar Rol</boton>
							
						</xsl:if>
						<boton estilo="primary volver" destino="admin_rol/17.1.do">Volver al Listado</boton>
						<boton estilo="primary" destino="admin_rol/17.2.do">Crear Nuevo</boton>
						
					</area_botones>
					</div>
				</contenido>
			</principal>
			
		</pagina>
				
	</xsl:template>

</xsl:stylesheet>
