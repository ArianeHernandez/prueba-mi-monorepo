<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="LISTADOCLIENTES">
		<xsl:param name="Destino" />
		<xsl:param name="Usuario" />
		<xsl:param name="UsuarioActual" />
		<xsl:param name="tipo_cliente"/><!-- J / N / T -->
		<javascript>templates/ListadoClientes.js</javascript>
		<stylesheet>lista_clientes.css</stylesheet>

		<formulario destino="{$Destino}" class="form-horizontal" id="form_cliente">
			<variable id="cliente_destino" valor="{$Destino}"/>
			
			<div class="form-horizontal caja_cliente">
			
				<registro>
					<item>Cliente:</item>
					<valor>
					<div >
						<span id="sel_cliente">
							<xsl:if test="$Usuario != ''">
								<xsl:value-of select="$Usuario[id_usuario=$UsuarioActual]/nombre" />
							</xsl:if>
						</span>
						<span>
							<a class="btn btn-primary btn_buscar_cliente" onclick="mostrarBusqueda()"><i class="fa fa-search" aria-hidden="true"></i></a>
						</span>
						<input type="hidden" name="id_usuario" id="id_usuario" value="{$UsuarioActual}" />
						<input type="hidden" name="tipo_cliente" id="tipo_cliente" value="{$tipo_cliente}" />
					</div>
					</valor>
				</registro>
			
			</div>
			
		</formulario>
		
		<xsl:call-template name="cuadro_buscar"/>
		<xsl:call-template name="PLANTILLAS_LISTADO_CLIENTES"/>
	</xsl:template>
	
	<xsl:template name="SELECTORCLIENTES">
		<xsl:param name="id"/>
		<xsl:param name="nombre_cliente"/>
		<xsl:param name="eliminarUsuario"/>
		<javascript>templates/ListadoClientes.js</javascript>
		<stylesheet>lista_clientes.css</stylesheet>
		
		<div id="cliente_{$id}"  class="caja_cliente">
			<span id="sel_cliente{$id}" class="span_nom">
				<xsl:value-of select="$nombre_cliente"/>
			</span>
			<xsl:if test="string-length($eliminarUsuario) > 0">
				<a class="btn_eliminar" onclick="{$eliminarUsuario}; osm_setValor('sel_cliente{$id}', '')"></a>
			</xsl:if>
			<a class="btn_buscar" onclick="mostrarBusquedaUsuario('{$id}', this);"></a>
			<xsl:call-template name="cuadro_buscar"/>
		</div>
	</xsl:template>
	
	<xsl:template name="cuadro_buscar">
		<div style="position:relative; top: 0px; left: 0px;">
			<div class="cuadro_info" id="div_buscar">
				<div class="input-group input-group-sm" >
					<input type="text" id="nombre_busqueda" class="form-control" placeholder="buscar" onkeyup="iniciarBusqueda(this)"/>
					<span class="input-group-btn"><a class="btn btn-primary" onclick="cerrarBusqueda();"><i class="fa fa-times" aria-hidden="true"></i></a></span>
				</div>
	
				<div class="row-filter transparent" id="resultados_clientes"></div>
				<div class="cuadro_info_footer" id="cuadro_info_footer" style="display:none">
					Mostrando primeros 10 resultados
				</div>
			</div>
		</div>
	
	</xsl:template>
	
	<xsl:template name="PLANTILLAS_LISTADO_CLIENTES">
		<div id="plantilla_cliente" style="display:none" >
			<a class="btn btn-primary btn-block" id="fila_[ 1 ]" name="[ 4 ]" onclick="seleccionCliente([ 1 ])">
				<i class="fa fa-plus" aria-hidden="true"></i>  
				<span id="cont_cliente_[ 1 ]">
					[ 2 ]
					-
					[ 3 ]
				</span>
				
				<div class="clearing"/>
			</a>
		</div>
	</xsl:template>
	
</xsl:stylesheet>