<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<SELECCIONAR-NEGOCIO/>
		
		<pagina titulo="Administración de Listas Dinamicas">
			
			<javascript>admin/10.2.js</javascript>
			
			<principal>
				<titulo icono="listavalores">Administración de Listas Dinamicas</titulo>
				<contenido>
					
					<formulario id="form_desactivar" destino="listasdinamicas/10.1.do">
						<variable id="id_lista_dinamica" valor="{//obtenerListaDinamica/ListaDinamica/id_lista_dinamica}"/>
						<variable id="desactivar_listadinamica" valor="desactivar_listadinamica"/>
					</formulario>
					
															
					<formulario id="form_edicion" destino="listasdinamicas/10.1.do">
						<variable id="ListaDinamica.id_lista_dinamica" valor="{//obtenerListaDinamica/ListaDinamica/id_lista_dinamica}"/>
						<variable id="guardar_listadinamica" valor="guardar_listadinamica"/>
						
						<bloque-pestanas>
						
							<pestana titulo="Información Basica">
							
								<registro>
									<item>Nombre</item>
									<valor>
										<cajatexto id="ListaDinamica.nombre" valor="{//obtenerListaDinamica/ListaDinamica/nombre}" />
									</valor>
								</registro>
								
								<registro>
									<item>Fuente de datos</item>
									<valor>
										<cajatextoselector id="ListaDinamica.base_datos" valor="{//obtenerListaDinamica/ListaDinamica/base_datos}">
											<opcion valor="2" texto="DATOS" />
											<opcion valor="1" texto="METADATA" />
										</cajatextoselector>
									</valor>
								</registro>
								
								<registro>
									<item>Descripción</item>
									<valor>
										<areatexto id="ListaDinamica.descripcion" valor="{//obtenerListaDinamica/ListaDinamica/descripcion}" />
									</valor>
								</registro>
								
								<registro>
									<item>Servicio a ejecutar</item>
									<valor>
										<cajatexto id="ListaDinamica.servicio" valor="{//obtenerListaDinamica/ListaDinamica/servicio}" />
									</valor>
								</registro>
								
							</pestana>
							
							<pestana titulo="Datos">
							
								<parrafo>
									Los datos de la lista de valores se obtienen por medio de una consulta sobre la base de datos.
								</parrafo>
								
								<ocultable textovisible="ocultar ayuda" textooculto="mostrar ayuda">
									<div class="">
										<ul>
										<br/><li>La consulta se debe construir siguiendo el siguiente esquema:</li>
										SELECT <b>[IDENTIFICACION]</b>, <b>[ETIQUETA]</b> FROM <b>[FUENTE]</b>
										<br/><br/>
										<li>Los parámetros que se pasan de forma dinámica se identifican así:<br/></li>
										<b>parametro_json</b> - aplica cuando se recibe un parámetro
										<br/><br/>
										<li>Si hay más de un parámetro se agrega el identificador del número al final del nombre</li>
										<b>parametro_json_1</b> - primer parámtro
										<br/><b>parametro_json_2</b> - segundo parámetro
										<br/><br/>
										<li>Para los nombres de estructuras y campos la siguiente notación <b>$$</b>, en general todo debe ir en mayúscula, <br/>como se muestra a continuación:</li>
										 select $ESTRUCTURA.CAMPO$ from $ESTRUCTURA$ <br/>
										 WHERE $ESTRUCTURA.CAMPO TEXTO$ = $S(TEXTO)$<br/>
										 OR $ESTRUCTURA.CAMPO NUMERICO$ = $I(123)$
										 </ul>
										 
									</div>
								</ocultable>
								
								
								<div>
									<registro >
										<item>Identificación</item>
										<valor>
											<cajatexto id="ListaDinamica.c_id" valor="{//obtenerListaDinamica/ListaDinamica/c_id}" />
										</valor>
									</registro>
								</div>
								
								<registro>
									<item>Etiqueta</item>
									<valor>
										<areatexto id="ListaDinamica.c_nombre" valor="{//obtenerListaDinamica/ListaDinamica/c_nombre}" />
									</valor>
								</registro>
								
								<registro>
									<item>Fuente</item>
									<valor>
										<areatexto id="ListaDinamica.consulta" valor="{//obtenerListaDinamica/ListaDinamica/consulta}" />
									</valor>
								</registro>

								
							</pestana>
								
						</bloque-pestanas>
						
					</formulario>
					
					<area_botones>
						<boton estilo="guardar" accion="guardar()">Guardar</boton>
						<boton estilo="guardar" accion="eliminar()">Eliminar</boton>
						<boton estilo="cancelar" destino="listasdinamicas/10.1.do">Cancelar</boton>
					</area_botones>
					
				</contenido>
					
			</principal>
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
