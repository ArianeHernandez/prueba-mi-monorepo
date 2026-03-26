<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="REPORTEDINAMICO">
		<xsl:param name="Reporte" />

		<javascript>templates/ReporteDim.js</javascript>
		
		<bloque>
			
			<h5 class="titulo_reporte">
				<xsl:value-of select="$Reporte/titulo"></xsl:value-of>
			</h5>
			<contenido>
				
				<!--filtros-->
				<xsl:if test="count($Reporte/parametros/ParametroReporte[filtro='true']) > 0">
					<xsl:call-template name="filtros" >
						<xsl:with-param name="Reporte" select="$Reporte"/>
					</xsl:call-template>
				</xsl:if>
				
				<!-- reporte -->
				<xsl:if test="count($Reporte/*)>0">
					<div id="div_reporte" style="display:none">
						
						<div class="tabla_borde table-responsive">
						
							<table class="table table-hover table-striped" cellspacing="0" cellpadding="0">
								<thead id="titulo_{$Reporte/idReporte}" class="tabla_encabezado tabla_encabezado_1">
								</thead>
								<tbody id="contenido_{$Reporte/idReporte}">
								</tbody>
							</table>
							
						</div>

						<xsl:call-template name="paginacion" />

						<!--Navegacion-->
						<area_botones>
							<xsl:for-each select="$Reporte/navegacion/Navegacion">
								<boton class="btn btn-primary" formulario="form_{position()}">
									<xsl:value-of select="nombre"></xsl:value-of>
								</boton>

							</xsl:for-each>
							<!--boton class="btn btn-primary" accion="osm_go('ReporteDim?id={$Reporte/idReporte}', false)"
								icono="excel">
								Excel
							</boton-->
						</area_botones>
					</div>
					
					<xsl:for-each select="$Reporte/navegacion/Navegacion">
						<formulario id="form_{position()}" destino="{destino}">
							<xsl:for-each select="parametros/ParametroReporte">
								<input type="hidden" name="{nombre}" id="nav_para_{position()}_{nombre}"
									value="{valor}" />
							</xsl:for-each>
						</formulario>
					</xsl:for-each>
					
					<input type="hidden" id="totalNavegaciones" value="{count($Reporte/navegacion/Navegacion)}" />

					<!--Accion fila-->
					<form id="form_fila" name="form_fila" >
					
					</form>
					
					<input type="hidden" id="hidden_template" />
					<input type="hidden" id="subreporte" name="subreporte" value="{$Reporte/accionfila/subreporte}" />

					<!--Id del reporte-->
					<input type="hidden" id="idReporte" name="idReporte" value="{$Reporte/idReporte}" />
						
					<input type="hidden" id="intervalo_act" value="{$Reporte/intervalo_actualizacion}" />
				</xsl:if>
				
				<xsl:call-template name="plantillas" >
					<xsl:with-param name="Reporte" select="$Reporte"/>
				</xsl:call-template>
			</contenido>
		</bloque>

	</xsl:template>
	
	
	<xsl:template name="plantillas">
		<xsl:param name="Reporte"/>

		<div id="div_plantillas" class="table-responsive" style="display:none">
			<table class="table" >
				<tr class="tabla_fila" id="fila_template">
				</tr>
				<tr >
					<td id="registro_template"></td>
				</tr>
				<tr id="subreporte_template"> 
					<td colspan="[ 2 ]" >
						<div class="table-responsive">
							<table class="table table-bordered table-striped tabla_[ 3 ]" cellspacing="0" cellpadding="0">
								<thead id="titulo_[ 1 ]" class="tabla_encabezado tabla_encabezado_[ 3 ]">
								</thead>
								<tbody id="contenido_[ 1 ]">
								</tbody>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
	</xsl:template>
	

	<xsl:template name="filtros">
		<xsl:param name="Reporte" />
		
		<bloque-contenido>
			<titulo>Valores de búsqueda</titulo>
			<contenido>
				<div class=" form-horizontal">
				<formulario id="form_buscar" destino="{substring(//SERVERREQUEST,2)}">
					
					<xsl:for-each select="$Reporte/parametros/ParametroReporte">
						<xsl:sort select="orden" data-type="number"></xsl:sort>

						<xsl:variable name="nombre" select="nombre"/>

						<xsl:choose>
							<xsl:when test="filtro='true'">
								<registro>
									<item>
										<xsl:value-of select="titulo" />
									</item>
									<valor>
										<xsl:choose>
											<xsl:when test="tipo='date'">
												<input id="param_{$nombre}" name="{$nombre}" class="form-control date-pick" type="text"
													value="{substring($Reporte/valoresParametros/*[@name=$nombre], 0 ,11 )}" readonly="readonly" />
											</xsl:when>
											<xsl:when test="tipo='boolean'">
												<select id="param_{$nombre}" name="{$nombre}" class="selectboolean" readonly="readonly">
													<xsl:variable name="valor">
														<xsl:choose>
															<xsl:when test="string-length($Reporte/valoresParametros/*[@name=$nombre]) = 0">
																false
															</xsl:when>
															<xsl:otherwise>
																<xsl:value-of select="$Reporte/valoresParametros/*[@name=$nombre]"/>
															</xsl:otherwise>
														</xsl:choose>
													</xsl:variable>
													<xsl:attribute name="value" ><xsl:value-of select="normalize-space($valor)"/></xsl:attribute>				
													<option value="true">Si</option>
													<option value="false">No</option>
												</select>
											</xsl:when>
											<xsl:otherwise>
												<input id="param_{$nombre}" name="{$nombre}" class="form-control"
													type="text" value="{$Reporte/valoresParametros/*[@name=$nombre]}" />
											</xsl:otherwise>
										</xsl:choose>
									</valor>
								</registro>
							</xsl:when>
							<xsl:otherwise>
								<input type="hidden" id="param_{$nombre}" name="{$nombre}"
									value="{$Reporte/valoresParametros/*[@name=$nombre]}" />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:for-each>
					<variable id="id" valor="{$Reporte/nombre}" />
				</formulario>
				<area_botones>
					<boton icono="buscar" formulario="form_buscar" class="btn btn-primary" id="btn_reporte_buscar">
					<i class="fa fa-search" aria-hidden="true"></i>  Buscar</boton>
				</area_botones>
				</div>
			</contenido>
		</bloque-contenido>
	</xsl:template>

	<xsl:template name="paginacion">
		<nav aria-label="Page navigation" class="text-center">		
			<ul class="pagination">
					<li>
						<a id="pag_primero" onclick="ir_primero()" aria-label="ir al inicio">
							       <i class="fa fa-fast-backward" aria-hidden="true"></i>
						</a>
					</li>
					<li>
						<a id="pag_atras" onclick="ir_atras()" aria-label="anterior">
							        <i class="fa fa-backward" aria-hidden="true"></i>
						</a>
					</li>
					<li>
						<a id="pag_info_pags">
							<span id="pag_desde"></span> a 	<span id="pag_hasta"></span> de <span id="pag_total"></span> <span id="pag_info_carga">cargando datos ...</span>
						</a>
					</li>
					<li>
						<a id="pag_siguiente" onclick="ir_siguiente()" aria-label="siguiente">
							       <i class="fa fa-forward" aria-hidden="true"></i>
						</a>
					</li>
					<li>
						<a id="pag_ultimo" onclick="ir_ultimo()" aria-label="ir al final">
							        <i class="fa fa-fast-forward" aria-hidden="true"></i>
						</a>
					</li>
				
			</ul>
		</nav>
	</xsl:template>

</xsl:stylesheet>