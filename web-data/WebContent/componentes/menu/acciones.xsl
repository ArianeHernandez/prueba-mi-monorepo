<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:include href="context://stylesheets/templates/semaforoCargasPendientesPorProcesoAdmin.xsl"/>
	<xsl:include href="context://stylesheets/templates/ResumenNotificaciones.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
    <xsl:template match="acciones">
        
		<enlaces_encabezado>
			<enlace>Ayuda</enlace>
			<enlace>Salida Segura</enlace>
		</enlaces_encabezado>
	
    	<informacion-usuario nombre="{concat(//OSM-INIT-SESSION/Info/Persona/nombre,' ',//OSM-INIT-SESSION/Info/Persona/apellido)}" genero="{//OSM-INIT-SESSION/Info/Persona/genero}" negocio="{//OSM-INIT-SESSION/Info/Negocio/nombre}" 
    						ultimo_ingreso="{//OSM-INIT-SESSION/Info/Credencial/fecha_ingreso}" ip="{//OSM-INIT-SESSION/Info/Credencial/ip_ingreso}"/>    	

		<xsl:variable name="serv" select="//OSM-INIT-SESSION/obtenerServiciosAdministrativo/listado/Servicio"/>
    	
    	<!-- ******************************************************************************************************************************* -->
    	
    	<xsl:if test="//menuPreparador/autorizado='true'">
	    	<xsl:for-each select="//menusPreparador/MenuGrupoFormato" >
	    		<xsl:sort select="grupoFormato/orden"/>
	    		<xsl:sort select="grupoFormato/nombre"/>
	    		
		    	<menu_lateral>
					<titulo><xsl:value-of select="grupoFormato/nombre"></xsl:value-of></titulo>
					<xsl:variable name="grupo" select="grupoFormato/id_grupoformato"></xsl:variable>
					
					<xsl:for-each select="listaProcesoFormato/ProcesoFormato">
						<xsl:sort select="proceso/nombre"/>
					
						<xsl:if test="count(formatoAsociados/Formato)=1">
							<enlace icono="{icono}" formulario="form_menu_{$grupo}_{proceso/id_proceso}">
							<xsl:variable name="lowercase" select="'abcdefghijklmnopqrstuvwxyzáéíóú'" />
							<xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚ'" />
							<xsl:variable name="proceso" select="proceso/nombre" />
								<xsl:choose>
									<xsl:when test="contains($proceso, 'NEAR') or contains($proceso, 'near')">
										<xsl:variable name="s1" select="translate($proceso, $uppercase, $lowercase)"/>
										<xsl:variable name="s2" select="concat(
																			  translate(
																			    substring($s1, 1, 1),
																			    $uppercase,
																			    $lowercase
																			  ),
																			  substring($s1,2,string-length($s1)-1)
																			)"/>
										<titulo_enlace>
											<xsl:call-template name="replace-string">
											  <xsl:with-param name="text" select="$s2"/>
											  <xsl:with-param name="replace" select="'near'" />
											  <xsl:with-param name="with" select="'NEAR'"/>
											</xsl:call-template>
										</titulo_enlace>
										<formulario id="form_menu_{$grupo}_{proceso/id_proceso}" destino="carga_informacion/1a.do">
											<input type="hidden" name="id_formato" value="{formatoAsociados/Formato/id_formato}"/>
											<input type="hidden" name="id_proceso" value="{proceso/id_proceso}"/>
										</formulario>
									</xsl:when>
									<xsl:otherwise>
										<xsl:variable name="s1" select="translate($proceso, $uppercase, $lowercase)"/>
										<titulo_enlace>
											<xsl:value-of select="concat(
																			  translate(
																			    substring($s1, 1, 1),
																			    $uppercase,
																			    $lowercase
																			  ),
																			  substring($s1,2,string-length($s1)-1)
																			)"/>
										</titulo_enlace>
										<formulario id="form_menu_{$grupo}_{proceso/id_proceso}" destino="carga_informacion/1a.do">
											<input type="hidden" name="id_formato" value="{formatoAsociados/Formato/id_formato}"/>
											<input type="hidden" name="id_proceso" value="{proceso/id_proceso}"/>
										</formulario>
									</xsl:otherwise>
								</xsl:choose>
								
							</enlace>
						</xsl:if>
						
						<xsl:if test="count(formatoAsociados/Formato)>1">
							<enlace icono="{icono}" >
								<titulo_enlace><xsl:value-of select="proceso/nombre"/></titulo_enlace>
								<submenu>
									<xsl:variable name="proceso" select="proceso/id_proceso"></xsl:variable>
									<xsl:for-each select="formatoAsociados/Formato">
										<enlace icono="{icono}" formulario="form_menu_{$proceso}_{id_formato}">
											<titulo_enlace><xsl:value-of select="nombre"/></titulo_enlace>
											
											<formulario id="form_menu_{$proceso}_{id_formato}" destino="carga_informacion/1a.do">
												<input type="hidden" name="id_formato"  value="{id_formato}"/>
												<input type="hidden" name="id_proceso"  value="{$proceso}"/>
											</formulario>
										</enlace>
									
									</xsl:for-each>
									
								</submenu>
								
								<formulario id="form_menu_{$grupo}_{proceso/id_proceso}" destino="carga_informacion/1a.do">
									<input type="hidden" name="id_formato" value="{formatoAsociados/Formato/id_formato}"/>
									<input type="hidden" name="id_proceso" value="{proceso/id_proceso}"/>
								</formulario>
							</enlace>
						</xsl:if>
						
						
					</xsl:for-each>
				</menu_lateral>
			</xsl:for-each>
    	</xsl:if>
    	
    	<!-- ******************************************************************************************************************************* -->
    	
    	<xsl:for-each select="//obtenerSeccionesMenu/Listado/SeccionMenu">
    	
			<xsl:sort data-type="number" order="ascending"/>
			
			<xsl:variable name="titulo" select="nombre"/>
			<xsl:variable name="seccion" select="id_seccion"/>
			
	    	<xsl:call-template name="agregar-seccion">
	    		<xsl:with-param name="titulo" select="$titulo"/>
	    		<xsl:with-param name="seccion" select="$seccion"/>
	    	</xsl:call-template>
	    	
    	</xsl:for-each>
    	
    	<!-- ******************************************************************************************************************************* -->
		
    	<xsl:if test="//menuReportes/autorizado='true'">
    		
    		<xsl:if test="//menuReportes/autorizado='true'">
		    	<menu_lateral>
					<titulo>Reportes</titulo>
					<xsl:for-each select="//OSM-SESSION/menusReporte/AsignacionReporte">
						<xsl:sort select="titulo"/>
						<enlace icono="{icono}" formulario="form_reporte_{position()}">
							<titulo_enlace><xsl:value-of select="titulo"/></titulo_enlace>
							<formulario id="form_reporte_{position()}" destino="reportedim/reporte.do">
								<variable id="id" valor="{id_reporte}"/>
							</formulario>
						</enlace>
					</xsl:for-each>
				</menu_lateral>
			</xsl:if>
		</xsl:if>
    	
    	<!-- ******************************************************************************************************************************* -->

    	<xsl:if test="count(obtenerMenusPorAplicacion/Listado/Menu[count(autorizado)>0 and autorizado='true' and tipo='personal'])>0">
	    	<menu_lateral>
				<titulo>MENU PERSONAL</titulo>
				<xsl:for-each select="obtenerMenusPorAplicacion/Listado/Menu[count(autorizado)>0 and autorizado='true' and tipo='personal']">
					<xsl:sort select="titulo"/>
					<enlace icono="{icono}" destino="{direccion}"><titulo_enlace><xsl:value-of select="titulo"/></titulo_enlace></enlace>
				</xsl:for-each>
			</menu_lateral>
		</xsl:if>
		
    	<!-- ******************************************************************************************************************************* -->
    	
    	<xsl:if test="count(plugin[autorizado='true']) > 0 or count (obtenerPluginsAutorizados/Listado/Plugin) > 0">
	    	<menu_plugins>
	    		<div class="bloque_plugin">
	    			
	    			<xsl:for-each select="obtenerCarpetasPlugins/Listado/String">
	    				<javascript>../../<xsl:value-of select="."/></javascript>
	    			</xsl:for-each>
	    			
	    			<xsl:for-each select="obtenerPluginsAutorizados/Listado/Plugin">
	    				<div class="panel panel-primary">
	    					<div class="panel-heading"><xsl:value-of select="nombre"/></div>
	    					
		    				<div  class="panel-body" id="{elemento}" />
	    					
		    				<script>$(document).ready(<xsl:value-of select="recurso"/>);</script>
	    				</div>
    					
    					<variable class="input_plantilla_elemento" id="input_plantilla_{elemento}" valor="{plantilla}"/>
	    				
	    			</xsl:for-each>
	    			
					<xsl:for-each select="plugin[autorizado='true']">
						<div class="panel panel-primary">
							<div class="panel-heading"><xsl:value-of select="@name"/></div>
							
							<div class="panel-body">
								<xsl:choose>
									<xsl:when test="@name='Procesos'">
										<xsl:call-template name="semaforoCargasPorProcesos"/>
									</xsl:when>
									<xsl:when test="@name='Notificaciones'">
										<xsl:call-template name="ResumenNotificaciones"/>
									</xsl:when>
								</xsl:choose>
								
							</div>

						</div>
					</xsl:for-each>
	    			
				</div>
			</menu_plugins>
		</xsl:if>
	
    	<!-- ******************************************************************************************************************************* -->

    	<enlaces_pie>
			<enlace>inicio</enlace>
			<enlace>mapa del sitio</enlace>
			<enlace>condiciones de uso</enlace>
			<enlace>preguntas frecuentes</enlace>
			<enlace>aviso legal</enlace>
		</enlaces_pie>
    	
    </xsl:template>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template name="agregar-seccion">
		<xsl:param name="seccion"/>
		<xsl:param name="titulo"/>
		
		<xsl:if test="count(//obtenerMenusPorAplicacion/Listado/Menu[count(autorizado)>0 and autorizado='true' and tipo=$seccion])>0">
	    	<menu_lateral>
				<titulo><xsl:value-of select="$titulo"/></titulo>
	    		<xsl:for-each select="//obtenerMenusPorAplicacion/Listado/Menu[not(id_padre) and direccion='#' and tipo=$seccion]">
	    			<xsl:sort select="id_menu" data-type="number"/>
	    			<xsl:variable name="id_padre" select="id_menu" />
	    			<enlace icono="{icono}">
	    				<titulo_enlace><xsl:value-of select="titulo"/></titulo_enlace>
		    			<submenu>
		    				<xsl:for-each select="//obtenerMenusPorAplicacion/Listado/Menu[count(autorizado)>0 and autorizado='true' and tipo=$seccion and id_padre=$id_padre]">
								<xsl:sort select="titulo"/>
								<enlace icono="{icono}" destino="{direccion}"><titulo_enlace><xsl:value-of select="titulo"/></titulo_enlace></enlace>
							</xsl:for-each>
		    			</submenu>
	    			</enlace>
	    		</xsl:for-each>
				<xsl:for-each select="//obtenerMenusPorAplicacion/Listado/Menu[count(autorizado)>0 and autorizado='true' and tipo=$seccion and not(id_padre) and direccion!='#']">
					<xsl:sort select="id_menu" data-type="number"/>
					<enlace icono="{icono}" destino="{direccion}"><titulo_enlace><xsl:value-of select="titulo"/></titulo_enlace></enlace>
				</xsl:for-each>
			</menu_lateral>
		</xsl:if>
		
	</xsl:template>
	
	<xsl:template name="replace-string">
	    <xsl:param name="text"/>
	    <xsl:param name="replace"/>
	    <xsl:param name="with"/>
	    <xsl:choose>
	      <xsl:when test="contains($text,$replace)">
	        <xsl:value-of select="substring-before($text,$replace)"/>
	        <xsl:value-of select="$with"/>
	        <xsl:call-template name="replace-string">
	          <xsl:with-param name="text"
				select="substring-after($text,$replace)"/>
	          <xsl:with-param name="replace" select="$replace"/>
	          <xsl:with-param name="with" select="$with"/>
	        </xsl:call-template>
	      </xsl:when>
	      <xsl:otherwise>
	        <xsl:value-of select="$text"/>
	      </xsl:otherwise>
	    </xsl:choose>
	  </xsl:template>
		<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
</xsl:stylesheet>