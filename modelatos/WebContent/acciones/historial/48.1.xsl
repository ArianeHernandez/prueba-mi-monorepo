<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Configuración historial">
			
			<javascript>admin/48.1.js</javascript>
			
			<principal>
				<titulo icono="listavalores">Configuración historial</titulo>
				<contenido>
				
					<variable id="ListaDinamica.id_lista_dinamica" valor="{//obtenerListaDinamica/ListaDinamica/id_lista_dinamica}"/>
					<variable id="guardar_listadinamica" valor="guardar_listadinamica"/>
					
					<bloque-pestanas>
					
						<pestana titulo="Configuración Inicial">
						
							<parrafo>
								Los datos del historial se obtienen a partir de una vista que se genera en base de datos.
							</parrafo>
							
							<ocultable textovisible="ocultar ayuda" textooculto="mostrar ayuda">
								<div class="">
									<ul>
									<br/><li>La consulta se debe construir siguiendo el siguiente esquema:</li>
									SELECT <b>[PARAMETRO_CONSULTA]</b> AS <b>[ALIAS]</b> FROM <b>[FUENTE]</b>
									<br/><br/>
									<li>Tener en cuenta que ya existe un dblink que puede ser usado como <b>@to_datasuite</b></li>
									<br/><br/>
									<li>En la parte del filtro se colocaran las condiciones para que se haga un filtro en los resultados.</li>
									<br/><br/>
									<li>Se debe desplegar nuevamente el proyecto para que la vista del historico sea generada nuevamente</li>
									</ul>
									 
								</div>
							</ocultable>
							<registro>
								<item>Descripción</item>
								<valor>
									<areatexto id="descripcion" valor="" />
								</valor>
							</registro>
							<registro>
								<item>Script de consulta</item>
								<valor>
									<areatexto id="scriptConsulta" valor="" />
								</valor>
							</registro>
							
							<area_botones>
								<boton estilo="guardar" accion="guardar()">Guardar</boton>
							</area_botones>
						</pestana>
						<pestana titulo="Configuración reporte">
							
							<registro>
								<item>Opciones de configuración</item>
								<valor>
									<cajatextoselector id="opciones_vista" valor="{//obtenerFormato/Formato/id_grupoformato}">
										<opcion valor="" texto="-- Seleccione --"/>
										
									</cajatextoselector>
								</valor>
							</registro>
							<registro>
								<item>Tipo</item>
								<valor>
									<cajatextoselector id="tipo_dato" valor="">
										<opcion valor="" texto="-- Seleccione --"/>
										<opcion valor="Fecha" texto="Fecha"/>
										<opcion valor="Texto" texto="Texto"/>
										<opcion valor="Selector" texto="Selector"/>
									</cajatextoselector>
								</valor>
							</registro>
							<registro>
								<item>Etiqueta</item>
								<valor>
									<cajatexto id="etiqueta_columna" valor=""/>
								</valor>
							</registro>
							<registro>
								<item>Mostrar</item>
								<valor>
									<cajachequeo id="mostrar" valor="" accion="" seleccionado="false">
									</cajachequeo>
								</valor>
							</registro>
							<registro>
								<item>Excel</item>
								<valor>
									<cajachequeo id="excel" valor="" accion="" seleccionado="false">
									</cajachequeo>
								</valor>
							</registro>
							<registro>
								<item>Filtrar</item>
								<valor>
									<cajachequeo id="filtrar" valor="" accion="" seleccionado="false">
									</cajachequeo>
								</valor>
							</registro>
							<registro>
								<item>Posición</item>
								<valor>
									<cajatexto id="posicion" valor=""/>
								</valor>
							</registro>
							<area_botones>
								<boton estilo="guardar" accion="guardarElemento()">Guardar</boton>
								
							</area_botones>
							<div class="tabla_borde table-responsive">
								<table cellpadding="0" cellspacing="0" class="table table-hover table-striped">
									<tbody id="tbodyId">
										<tr class="tabla_encabezado">
											<td>Posición</td>
											<td>Columna</td>
											<td>Tipo</td>
											<td>Etiqueta</td>
											<td>Mostrar</td>
											<td>Excel</td>
											<td>Filtrar</td>
											<td>Editar</td>
										</tr>
									</tbody>
								</table>
							</div>
						</pestana>
						<pestana titulo="Configuración rol">
							<input type="hidden" id="id_rol"/>
							<registro>
								<item>Roles</item>
								<valor>
									<cajatextoselector id="roles" valor="">
										<opcion valor="" texto="-- Seleccione --"/>
										
									</cajatextoselector>
								</valor>
							</registro>
							<registro>
								<item>Opciones de configuración</item>
								<valor>
									<cajatextoselector id="opciones_vista_rol" valor="">
										<opcion valor="" texto="-- Seleccione --"/>
										
									</cajatextoselector>
								</valor>
							</registro>
							<registro>
								<item>Valor</item>
								<valor>
									<cajatexto id="valor_rol" valor=""/>
								</valor>
							</registro>
							
							<area_botones>
								<boton id="btn_limpiar_rol" estilo="guardar" accion="limpiarConfRol()">Limpiar</boton>
								<boton id="btn_guardar_rol" estilo="guardar" accion="guardarConfRol()">Guardar</boton>
								<boton id="btn_actualizar_rol" estilo="guardar" accion="actualizarConfRol()">Actualizar</boton>
								<boton id="btn_borrar_rol" estilo="guardar" accion="borrarConfRol()">Borrar</boton>
								
								
							</area_botones>
							<div class="tabla_borde table-responsive">
								<table cellpadding="0" cellspacing="0" class="table table-hover table-striped">
									<tbody id="tbodyIdRol">
										<tr class="tabla_encabezado">
											<td>Rol</td>
											<td>Columna</td>
											<td>Valor</td>
											<td>Editar</td>
										</tr>
									</tbody>
								</table>
							</div>
						</pestana>
							
					</bloque-pestanas>
					
					<area_botones>
						
						<boton estilo="cancelar" destino="inicio/0.do">Ir al inicio</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
