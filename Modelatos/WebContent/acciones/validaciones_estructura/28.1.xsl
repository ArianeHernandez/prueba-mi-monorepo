<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">


		<pagina titulo="Validaciones por estructura">

			<javascript>validacion_estructura/28.1.js</javascript>
			<stylesheet>28.1.css</stylesheet>

			<principal>
				<titulo>Validaciones por estructura</titulo>

				<contenido>
					<formulario destino="validaciones_estructura/28.2.do" id="form_edicion_validaciones_estructura">
						<variable id="id_estructura" valor="" />
						<variable id="id_validacion_estructura" valor="" />
						<variable id="editar" valor=""/>
					</formulario>
					
					
					<formulario destino="validaciones_estructura/28.4.do" id="form_eliminar_validacion">
						<variable id="id_validacion_estructura" valor="" />
					</formulario>
					
					
					<!--SELECTOR DE ESTRUCTURAS -->
					
					<parrafo>
						Seleccione la estructura para listar las validaciones asociadas.<br/>
					</parrafo>
					
					<registro>
					<item>Estructura:</item>
					<valor>
					<cajatextoselector class="" id="selector_estructura" accion="listarValidacionesPorEstructura(this.value)" valor="{//id_estructura}">
							<opcion valor="" texto="-- Seleccione --"/>
							<xsl:for-each select="//obtenerEstructuras/listado/Estructura">
								<xsl:sort select="nombre"/>
								<opcion valor="{id_estructura}" texto="{nombre}" />
							</xsl:for-each>
					</cajatextoselector>
					</valor>
				 	</registro>
				 	
				 	<br/>
				 	<br/>
					

					<div id="lista_validaciones_estructura"></div>
					
					<xsl:call-template name="plantillas" />
					
					<div id="mensaje_error" style="display:none">
						<parrafo estilo="resaltado">
							No existen validaciones para la estructura
						</parrafo>
					</div>
					
					
					
					<area_botones>
						<br/><br/>
						<boton estilo="crear" accion="crearValidacionPorEstructura();">Crear Validacion</boton>
					</area_botones>

				</contenido>

			</principal>
			
		</pagina>
	</xsl:template>
	
	
	<xsl:template name="plantillas">
		<div style="display:none">
			<div id="PLANTILLA_LISTA_VALIDACIONES_POR_ESTRUCTURA">
				<div>
					<table class="tabla">
						<thead>
							<tr class="tabla_encabezado">
								
								<td class="">Nombre validación</td>
								<td class="">Tipo</td>
								<td class="">Editar</td>
								<td class="">Eliminar</td>
								
							</tr>
						</thead>
						<tbody id="contenido_lista_validaciones_por_estructura"></tbody>
					</table>

					
				</div>
			</div>

			<table>
				<tbody id="PLANTILLA_ITEM_VALIDACION_ESTRUCTURA">
					<tr class="tabla_fila" id="fila_[ 1 ]">
						
						<td class=""><b>[ 3 ]</b></td>
						<td class="">[ 2 ]</td>
						<td class="">
							<a class="btn icono24_editar"	onclick="editarValidacionPorEstructura([ 1 ]);">
									Editar 
							</a>
						</td>
						<td class="">
							<a class="btn icono24_eliminar" onclick="eliminarValidacionPorEstructura([ 1 ]);">
									Eliminar
								</a>
						</td>
						
						
					</tr>
				</tbody>
			</table>

		</div>
	</xsl:template>

</xsl:stylesheet>
