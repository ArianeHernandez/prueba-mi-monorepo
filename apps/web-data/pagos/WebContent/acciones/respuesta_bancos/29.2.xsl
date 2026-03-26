<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<xsl:include href="context://stylesheets/templates/VentanaProcesoAdmin.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">
		

		<pagina>

			<principal>
			
				<titulo>Respuesta por banco</titulo>

				<contenido>
					<xsl:choose>
					<xsl:when test="//respuesta='1'">
						<parrafo>Transaccion exitosa. Se han actualizado con exito <xsl:value-of select="count(//LISTA_RESPUESTA_BANCOS/listaRespuestaBancos/RespuestaBanco[tieneErrores='false'])"/> registros de <xsl:value-of select="count(//LISTA_RESPUESTA_BANCOS/listaRespuestaBancos/RespuestaBanco)"/> registros para actualizar.</parrafo>
						
						<xsl:call-template name="plantillaResultado"/>
					</xsl:when>
					<xsl:when test="//respuesta='2'">
						<parrafo>Error! No hay registros validos en el archivo.</parrafo>
												
					</xsl:when>
					
					<xsl:when test="//respuesta='3'">
						<parrafo>Error! El archivo cargado no es un archivo excel</parrafo>
						
					</xsl:when>
					
					<xsl:when test="//respuesta='4'">
						<parrafo>Error! Archivo no existe</parrafo>
						
					</xsl:when>
					
					<xsl:when test="//respuesta='5'">
						<parrafo>El archivo cargado no tiene información valida!. No se pudo actualizar ningun registro por favor intentelo nuevamente</parrafo>
						<xsl:call-template name="plantillaResultado"/>
						
					</xsl:when>
					
					<xsl:when test="//respuesta='6'">
						<parrafo>Alerta! La totalidad de registros no fueron actualizados. Se actualizaron <xsl:value-of select="count(//LISTA_RESPUESTA_BANCOS/listaRespuestaBancos/RespuestaBanco[tieneErrores='false'])"/> registros de <xsl:value-of select="count(//LISTA_RESPUESTA_BANCOS/listaRespuestaBancos/RespuestaBanco)"/>. Por favor cargue nuevamente el archivo.</parrafo>
						<xsl:call-template name="plantillaResultado"/>
					</xsl:when>
					
					<xsl:when test="//respuesta='7'">
						<parrafo>Error! El archivo no corresponde con el banco seleccionado. Por favor verifique el archivo.</parrafo>
						
					</xsl:when>
					
					<xsl:otherwise>
						<parrafo>Error! Lo sentimos, ha ocurrido un error inesperado.</parrafo>
						
					</xsl:otherwise>
					</xsl:choose>
					
					
					
					<area_botones>
					<br/>
					<br/>
						<boton estilo="primary volver" id="bnt_volver" 
								accion="osm_go('respuesta_bancos/29.1.do');return false;">
								
						Volver</boton>
					</area_botones>
				</contenido>
			</principal>
			
			
		</pagina>
		
	</xsl:template>
	
	<xsl:template name="plantillaResultado">
		<xsl:if test="count(//LISTA_RESPUESTA_BANCOS/listaRespuestaBancos/RespuestaBanco[tieneErrores='true'])>0">
			
			<bloque-contenido>
				<titulo>Registros NO Actualizados</titulo>
				<contenido>
			
					<bloque estilo="grupo">
						
						<table class="table table-hover table-striped tb">
							<tr class="tabla_encabezado"><td style="width:10%;">Fila Archivo</td><td>Mensaje</td></tr>
							
							<xsl:for-each select="//LISTA_RESPUESTA_BANCOS/listaRespuestaBancos/RespuestaBanco[tieneErrores='true']">
							
								<tr class="tabla_fila">
									<td> <b> <xsl:value-of select="filaArchivo"/></b></td>
									<td><xsl:value-of select="mensajeRespuesta"/></td>
								</tr>
							
									
										
							</xsl:for-each>
						</table>
					</bloque>
			
				</contenido>
			</bloque-contenido>
			
		</xsl:if>
		
		<xsl:if test="count(//LISTA_RESPUESTA_BANCOS/listaRespuestaBancos/RespuestaBanco[tieneErrores='false'])>0">
			<bloque-contenido>
				<titulo>Registros Actualizados</titulo>
				<contenido>
			
					<bloque estilo="grupo">
						
						<table class="table table-hover table-striped tb">
							<tr class="tabla_encabezado"><td style="width:10%;">Fila Archivo</td><td>Mensaje</td></tr>
							
							<xsl:for-each select="//LISTA_RESPUESTA_BANCOS/listaRespuestaBancos/RespuestaBanco[tieneErrores='false']">
							
								<tr class="tabla_fila">
									<td> <b> <xsl:value-of select="filaArchivo"/></b></td>
									<td>El registro se actualizo exitosamente</td>
								</tr>
							
									
										
							</xsl:for-each>
						</table>
					</bloque>
			
				</contenido>
			</bloque-contenido>
		
		</xsl:if>
	
	</xsl:template>
	
	
	
	

</xsl:stylesheet>
