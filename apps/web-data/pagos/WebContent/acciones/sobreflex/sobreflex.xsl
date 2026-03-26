<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	<xsl:include href="context://stylesheets/templates/ListadoClientes.xsl" />

    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
    <xsl:template match="OSM-ACCION">
    <pagina titulo="Sobreflex">
			
		<principal>
			<javascript>sobreflex/sobreflex.js</javascript>
		
			<titulo>Sobreflex</titulo>
			
			<contenido>
			
			
			<xsl:choose>
				<xsl:when test="//usosobreflex='true'">
				
		        <div>
		        	<xsl:if test="count(//usuario_seleccionado/obtenerUsuario/Usuario/id_usuario)=0">
					<div>
		        		<parrafo estilo="info"><i class="fa fa-info-circle" aria-hidden="true"></i>Por favor seleccione el Cliente para el cual se va a generar SOBREFLEX.</parrafo>
		        	</div>	
		        	
					</xsl:if>
		        	
			        <xsl:call-template name="LISTADOCLIENTES">
						<xsl:with-param name="Destino">sobreflex/sobreflex.do</xsl:with-param>
						<xsl:with-param name="Usuario" select="//usuario_seleccionado/obtenerUsuario/Usuario"  />
						<xsl:with-param name="UsuarioActual" select="//id_usuario_actual"/>
						<xsl:with-param name="tipo_cliente" select="'N'"/>
					</xsl:call-template>
					
				</div>	
				
				<formulario destino="sobreflex/sobreflex_1.do" id="form_sobreflex">
					<variable id="id_usuario_sel" valor="{//usuario_seleccionado/obtenerUsuario/Usuario/id_usuario}"></variable>
					<variable id="id_persona_sel" valor="{//usuario_seleccionado/obtenerUsuario/Usuario/id_persona}"></variable>
				</formulario>
				
				
				<xsl:choose>
					<xsl:when test="count(//usuario_seleccionado/obtenerUsuario/Usuario/id_usuario)>0">
						<div>
		        		<parrafo estilo="info"><i class="fa fa-info-circle" aria-hidden="true"></i>Para generar el SOBREFLEX, debe tener instalada una impresora en el equipo desde el cual va a crear el documento.</parrafo>
					
						</div>
						<area_botones>
							<boton estilo="primary" accion="crearSobre();">Crear Sobreflex</boton>
							
						</area_botones>
					
					</xsl:when>
				</xsl:choose>
				</xsl:when>
				<xsl:otherwise>
					<div>
		        		<parrafo estilo="info"><i class="fa fa-info-circle" aria-hidden="true"></i>La opción para la generación de SOBREFLEX se encuentra desactivada.</parrafo>
					
					</div>
				</xsl:otherwise>
			</xsl:choose>
			    
	        
	        </contenido>
	    </principal>
			    
	</pagina>     
    </xsl:template>
</xsl:stylesheet>