<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<xsl:variable name="campos" select="//obtenerCamposPorEstructura/listado/Campo"/>
				
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Datos Estructura">
			<javascript>admin/12.1.js</javascript>
			<stylesheet>12.1.css</stylesheet>
			
			<principal>
				<titulo icono="estructura">Dato de la estructura <xsl:value-of select="//obtenerEstructura/Estructura/nombre" /></titulo>
				<contenido>
					
					<div class="tool_bar">
						<div class="item btn_agregar" onclick="agregarFila()">Agregar</div>
						<div class="item btn_guardar" onclick="guardar()">Guardar</div>
						<div class="item btn_cancelar" onclick="osm_go('estructuras/1.1.do');">Cancelar</div>
						<div class="item btn_excel" onclick="mostrarVentana('ventanaDescargarExcel')">Descargar Excel</div>
						<div class="item btn_excel" onclick="mostrarVentana('ventanaSubirExcel')">Cargar Excel</div>
						<div class="item btn_limpiar" style="display: none;" onclick="limpiarFiltro();">Limpiar Filtro</div>
						<div class="item mensaje"><span id="mensaje_estado">Cargando...</span></div>
					</div>
					
					<div class="scroll_datos">
						
						<table class="tabla_edicion" cellspacing="0" cellpadding="0">
							<thead>
								<tr>
									<td class="w020">&#160;</td>
									<xsl:for-each select="$campos">
										<xsl:sort select="orden" data-type="number"/>
										<td class="encabezado">
											
											<table class="tabla_edicion_encabezado">
												
												<tr>
											
													<td class="tabla_edicion_encabezado_td">
														<a class="filtrar" title="Filtrar datos" onclick="filtrarCampo('{id_campo}')">&#160;
														</a>
														<div class="caja_flotante ordenable" id="div_filtrar_{id_campo}"  title="Filtrar">
															<a class="limpiarfiltro"></a>
														</div>
													</td>
													
													<td class="tabla_edicion_encabezado_td">
														<a class="ordenable" onclick="ordenarPorCampo('{id_campo}')" title="Ordenar">
															<xsl:value-of select="nombre"/>
															<xsl:if test="unico = 'S'">&#160;*</xsl:if>
														</a>
													</td>
													
													<td class="tabla_edicion_encabezado_td">
														<a class="ordenar" title="Ordenar" onclick="ordenarPorCampo('{id_campo}')">&#160;
														</a>
													</td>
												
												</tr>
												
											</table>
											
											<xsl:if test="unico = 'S'">
												<input type="hidden" name="validar_unico" value="{id_campo}"/>
											</xsl:if>
											
											<xsl:if test="obligatorio = 'S'">
												<input type="hidden" name="validar_obligatorio" value="{id_campo}"/>
											</xsl:if>
											
											<xsl:if test="count(patronvalidacion)>0">
												<input type="hidden" name="validar_patron" value="{id_campo}"/>
												<input type="hidden" id="validar_patron_{id_campo}" name="validar_patron_{id_campo}" value="{patronvalidacion}"/>
											</xsl:if>
											
											<input type="hidden" id="nombre_campo_{id_campo}" value="{nombre}"/>
										</td>
									</xsl:for-each>
									<td class="w020"></td>
								</tr>
							</thead>
							<tbody id="contenido_datos">
								
							</tbody>
						</table>
					</div>
					<div style="display:none">
						
						<form id="formDatos" name="formDatos" action="12.2.do">
							<table >
								<tbody id="tabla_guardar">
								</tbody>
							</table>
							<input type="hidden" name="id_estructura" id="id_estructura" value="{//ID_ESTRUCTURA}" />
							<input type="hidden" name="guardar_datos" />
						</form>
						
						<form id="descargaExcel" name="descargaExcel" action="{//CONTEXT_PATH}/DescargarExcelEstructura">
							<input type="hidden" name="id_estructura" value="{//ID_ESTRUCTURA}" />
							<input type="hidden" id="descargar_datos" name="descargar_datos" value="" />
						</form>
						
						<table>
							<tbody id="PLANTILLA_FILA">
								<tr id="fila_[ 1 ]_[ 2 ]" class="fila">
									<td>[ 2 ]</td>
									<xsl:for-each select="$campos">
										<xsl:call-template name="TCampo">
											<xsl:with-param name="campo" select="."/>
											<xsl:with-param name="id_registro" select="'[ 1 ]'"/>
										</xsl:call-template>
									</xsl:for-each>
									<td>
										<a class="btn_eliminar w020">
											<xsl:attribute name="onclick">eliminarFilaReg('[ 1 ]_[ 2 ]');</xsl:attribute>
											&#160;
										</a>
										<input type="hidden" name="registros:[[ 1 ]].eliminar" id="eliminar_registro_[ 1 ]_[ 2 ]" value="false"/>
										<input type="hidden" name="registros:[[ 1 ]].id_registro" value="[ 1 ]" class="id_registro"/>
									</td>
									
								</tr>
							</tbody>
						</table>
						<table>
							<tbody id="TABLA_TEMPORAL">
							</tbody>
						</table>
						<xsl:for-each select="$campos">
							<input type="hidden" class="id_campo" value="{id_campo}" />
						</xsl:for-each>
					</div>
					
				</contenido>
				
			</principal>	
			
			<ventana id="ventanaDescargarExcel" icono="confirmacion">
				<titulo>Descargar archivo excel</titulo>
				<contenido>
					
					<bloque estilo="grupo">
						<subtitulo>Descargar los datos de la estructura?.</subtitulo>
						<parrafo>
							Al subir el archivo puede elegir si desea reemplazar todos los datos. 
						</parrafo>
					</bloque>
					
					<area_botones>
						<boton estilo="aceptar" accion="descargarExcel('S')">Si, descargar todo</boton>
						<boton estilo="aceptar" accion="descargarExcel('N')">No, descargar solo excel</boton>
						<boton estilo="cancelar" accion="cerrarVentana('ventanaDescargarExcel')">Cancelar</boton>
					</area_botones>
				</contenido>
			</ventana>
			
			<ventana id="ventanaSubirExcel" icono="confirmacion">
				<titulo>Cargar archivo excel</titulo>
				<contenido>
					
					<bloque estilo="grupo">
						<subtitulo>Reemplazar datos?</subtitulo>
						<parrafo>
							 Si selecciona subir todo, los datos del archivo excel reemplazan los datos de la estructura.
							 <br/>
							 Si selecciona adicionar datos, se actualizan o insertan los datos que va a cargar, no se elimina ningún registro. 
						</parrafo>
						<form id="cargarExcel" name="cargarExcel" action="{//CONTEXT_PATH}/CargarExcelEstructura" enctype="multipart/form-data">
							<input type="hidden" name="id_estructura" value="{//ID_ESTRUCTURA}" />
							<input type="hidden" name="reemplazar_todo" id="reemplazar_todo" value="" />
							<registro>
								<item>Archivo</item>
								<valor><cajaarchivo id="filename"/></valor>
							</registro>
						</form>
					</bloque>
					
					<area_botones>
						<boton estilo="aceptar" validacion="validarRemplazarDatos()" accion="enviarExcel('S')">Si, subir todo</boton>
						<boton estilo="aceptar" accion="enviarExcel('N')">No, adicionar o actualizar los datos</boton>
						<boton estilo="cancelar" accion="cerrarVentana('ventanaSubirExcel')">Cancelar</boton>
					</area_botones>
				</contenido>
			</ventana>
			
			
		</pagina>
		
	</xsl:template>
	
	<xsl:template name="TCampo">
		<xsl:param name="campo"/>
		<xsl:param name="dato"/>
		<xsl:param name="id_registro"/>
		
		<xsl:variable name="valor">
				<xsl:if test="string-length($dato) > 0">
					<xsl:value-of select="$dato/*[@name=concat('C',$campo/id_campo)]"/>
				</xsl:if>
		</xsl:variable>
		
		<td class="celda_tabla">
			<xsl:choose>
				<!-- Lista de valores -->
				<xsl:when test="string-length($campo/id_lista_valores) > 0">
					<select class="caja campo campo{$campo/id_campo}" value="{$valor}">
						<xsl:call-template name="onchange">
							<xsl:with-param name="tipo" select="$campo/id_tipocampo "/>
							<xsl:with-param name="campo" select="$campo"/>
							<xsl:with-param name="id_registro" select="$id_registro"/>
						</xsl:call-template>
						<option value="">--Seleccione--</option>
						<xsl:for-each select="$campo/valores/ValorLista">
							<option value="{id}"><xsl:value-of select="nombre"/></option>
						</xsl:for-each>
					</select>
				</xsl:when>
				<!-- Estructura -->
				<xsl:when test="string-length($campo/id_estructurarelacionada) > 0">
					<select class="caja campo  campo{$campo/id_campo}" value="{$valor}">
						<xsl:call-template name="onchange">
							<xsl:with-param name="tipo" select="$campo/id_tipocampo "/>
							<xsl:with-param name="campo" select="$campo"/>
							<xsl:with-param name="id_registro" select="$id_registro"/>
						</xsl:call-template>
						<option value="">--Seleccione--</option>
						<xsl:for-each select="$campo/datos/HashMap">
							<option value="{ID}"><xsl:value-of select="VISUALIZACION"/></option>
						</xsl:for-each>
					</select>
				</xsl:when>
				<!-- Booleano -->
				<xsl:when test="$campo/id_tipocampo = '2'">
					<input type="checkbox" class="caja ctr campo  campo{$campo/id_campo}" value="true" >
						<xsl:if test="$valor = 'true'">
							<xsl:attribute name="checked">checked</xsl:attribute>
						</xsl:if>
						<xsl:call-template name="onchange">
							<xsl:with-param name="tipo" select="$campo/id_tipocampo "/>
							<xsl:with-param name="campo" select="$campo"/>
							<xsl:with-param name="id_registro" select="$id_registro"/>
						</xsl:call-template>
					</input>
				</xsl:when>
				
				<!-- Fecha -->
				<xsl:when test="$campo/id_tipocampo = '4'">
					<input type="text" value="{substring($valor,1,10)}" readonly="readonly" class="campo  campo{$campo/id_campo}">
						<xsl:attribute name="class">
							<xsl:choose>
								<xsl:when test="string-length($dato) > 0">
									caja date-pick form-control
								</xsl:when>
								<xsl:otherwise>
									caja caja_fecha form-control
								</xsl:otherwise>
							</xsl:choose>
						</xsl:attribute>
						<xsl:call-template name="onchange">
							<xsl:with-param name="tipo" select="$campo/id_tipocampo "/>
							<xsl:with-param name="campo" select="$campo"/>
							<xsl:with-param name="id_registro" select="$id_registro"/>
						</xsl:call-template>
					</input>
				</xsl:when>
				<!-- Texto Largo -->
				<xsl:when test="$campo/id_tipocampo = '6'">
					<textarea class="caja campo  campo{$campo/id_campo}">
						<xsl:call-template name="onchange">
							<xsl:with-param name="tipo" select="$campo/id_tipocampo "/>
							<xsl:with-param name="campo" select="$campo"/>
							<xsl:with-param name="id_registro" select="$id_registro"/>
						</xsl:call-template>
						<xsl:value-of select="$valor"/>
					</textarea>
				</xsl:when>
				<!-- Texto -->
				<xsl:otherwise>
					<input type="text" class="caja campo  campo{$campo/id_campo}" value="{$valor}">
						<xsl:call-template name="onchange">
							<xsl:with-param name="tipo" select="$campo/id_tipocampo "/>
							<xsl:with-param name="campo" select="$campo"/>
							<xsl:with-param name="id_registro" select="$id_registro"/>
						</xsl:call-template>
					</input>
				</xsl:otherwise>
			</xsl:choose>
			<input type="hidden" name="registros:[{$id_registro}].campos:[{$campo/id_campo}].id_campo" value="{$campo/id_campo}"/>
		</td>
	</xsl:template>

	<xsl:template name="onchange">
		<xsl:param name="tipo"/>
		<xsl:param name="campo"/>
		<xsl:param name="id_registro"/>
		<xsl:variable name="tipos" select="//obtenerTipoCampos/listado/TipoCampo"/>
		
		<xsl:attribute name="id"><xsl:value-of select="concat('registros:[',$id_registro,'].campos:[',$campo/id_campo,'].valor')"/></xsl:attribute>
		<xsl:attribute name="name"><xsl:value-of select="concat('registros:[',$id_registro,'].campos:[',$campo/id_campo,'].valor')"/></xsl:attribute>
		<xsl:attribute name="maxlength"><xsl:value-of select="$tipos[id_tipocampo=$tipo]/longitud" /></xsl:attribute>
		<xsl:variable name="validacion" select="$tipos[id_tipocampo=$tipo]/patron_validacionJS"/>
		<xsl:if test="string-length($validacion) > 0 or $campo/obligatorio = 'S'">
			<xsl:attribute name="onchange">
				validarCampo(this, '<xsl:value-of select="$validacion"/>', 
				'<xsl:value-of select="$campo/obligatorio"/>', 
				'<xsl:value-of select="$tipos[id_tipocampo=$tipo]/nombre"/>',
				'<xsl:value-of select="$campo/nombre"/>'
				)
			</xsl:attribute>
		</xsl:if>
	</xsl:template>
	
</xsl:stylesheet>
