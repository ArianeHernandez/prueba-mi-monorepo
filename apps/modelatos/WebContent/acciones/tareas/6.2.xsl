<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Tareas Programadas">
			<javascript>tareas/6.2.js</javascript>

			<principal>
				<titulo icono="variable">Tareas Programadas</titulo>
				<contenido>

					<bloque>
						<titulo>Datos de la Tarea</titulo>
						<formulario id="form_guardar" destino="tareas/6.3.do">
							<xsl:if test="normalize-space(//obtenerTarea/Tarea/id_tarea) != ''">
								<variable id="Tarea.id_tarea" valor="{//obtenerTarea/Tarea/id_tarea}" />
							</xsl:if>

							<registro>
								<item>Nombre</item>
								<valor>
									<cajatexto id="Tarea.nombre" valor="{//obtenerTarea/Tarea/nombre}"></cajatexto>
								</valor>
							</registro>

							<registro>
								<item>EndPoint</item>
								<valor>
									<cajatexto id="Tarea.end_point" valor="{//obtenerTarea/Tarea/end_point}"></cajatexto>
								</valor>
							</registro>

							<registro>
								<item>Intervalo de Repetición</item>
								<valor>
									<cajatexto id="Tarea.intervalo_ejecucion"
										valor="{//obtenerTarea/Tarea/intervalo_ejecucion}"></cajatexto>
								</valor>
							</registro>

							<registro>
								<item>Repetir cada</item>
								<valor>
									<cajatextoselector id="Tarea.tipo_intervalo" valor="{//obtenerTarea/Tarea/tipo_intervalo}">
										<opcion texto="--Seleccion un tipo de intervalo--"/>
										<xsl:for-each select="obtenerTiposIntervalos/Listado/*">
											<xsl:sort select="."/>
											<opcion texto="{.}" valor="{@name}" />
										</xsl:for-each>
									</cajatextoselector>
								</valor>
							</registro>

							<registro>
								<item>Fecha de Inicio</item>
								<valor>
									<input readonly="readonly" type="text" id="fecha" value=""
										class="form-control date-pick" />
									<select id="hora" class="caja_hora" />
									:
									<select id="minuto" class="caja_hora" />
								</valor>
							</registro>
							
							<xsl:if test="normalize-space(//obtenerTarea/Tarea/id_tarea) = ''">
								<registro>
									<item>Activar</item>
									<valor>
										<cajachequeo id="Tarea.activar" valor="S"/>
									</valor>
								</registro>
							</xsl:if>
							<xsl:if test="normalize-space(//obtenerTarea/Tarea/id_tarea) != ''">
								<variable id="Tarea.activar" valor="{//obtenerTarea/Tarea/activar}" />
							</xsl:if>
							<variable id="Tarea.fecha_inicio" valor="{//obtenerTarea/Tarea/fecha_inicio}" />
						</formulario>
					</bloque>
					
					
					<xsl:if test="normalize-space(//obtenerTarea/Tarea/id_tarea) != ''">
						<bloque>
							<titulo>Estado de Ejecución</titulo>
							<div id="div_estado">
							</div>
							<div class="estilo_nota" id="div_estado_proceso" style="display:none">
								Proxima ejecución
							</div>
							<area_botones estilo='login'>
								<a id="caja_accion_ok" class="caja_accion_ok  btn" onclick="activar()" style="display:none">
									Activar
								</a>
								<a id="caja_accion_cancel" class="caja_accion_cancel btn" onclick="desactivar()" style="display:none">
									Desactivar
								</a>
							</area_botones>
						</bloque>
					</xsl:if>
					<area_botones>
						<boton estilo="volver" destino="tareas/6.1.do">Volver</boton>
						<boton estilo="crear" validacion="validarTarea()" formulario="form_guardar">Guardar</boton>
					</area_botones>

				</contenido>

			</principal>

		</pagina>

	</xsl:template>

</xsl:stylesheet>
