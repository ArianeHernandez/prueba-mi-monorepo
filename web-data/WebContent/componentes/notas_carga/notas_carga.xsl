<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="notas_carga">

		<xsl:param name="permiteAgregar"/>
		<xsl:param name="id_instancia"/>

		<javascript>notas_carga/notas_carga.js</javascript>

		<stylesheet>notas_carga.css</stylesheet>

		<escapar>

			<div id="contenido_notas">

				<input type="hidden" id="existen_notas" name="existen_notas"
					value="false" />

				<h2 class="bloque_contenido_titulo">Notas</h2>
				<div id="lista_notas" class="bloque_contenido_base" style="display: block;">
					<div class="bloque_contenido_contenido">
						<div class="alert alert-info" role="alert"><i class="fa fa-exclamation-circle" aria-hidden="true"></i> No existen notas</div>
					</div>
				</div>
				<xsl:if test="$permiteAgregar='true'">
				
					<h2 class="bloque_contenido_titulo">Adicionar Nota</h2>
					
					<div class="bloque_nueva_nota">
					
						<xsl:if test="count(//OSM-INIT-SESSION/Info/Usuario) = 0">
							<div class="row mtop">
								<div class="col-sm-4 text-right">Visibilidad:</div>
								<div class="col-sm-8">
									<select class="form-control" id="interno_nota_guardar" name="interno_nota_guardar">
										<option value="S">Unicamente usuarios autorizadores del proceso.</option>
										<option value="N">Todos los usuarios.</option>
									</select>
								</div>
							</div>
						</xsl:if>
						
						<div class="row">
							<div class="col-sm-4 text-right">Nota:</div>
							<div class="col-sm-8"><textarea class="form-control" style="height: 200px" id="input_nota_guardar">&#160;</textarea></div>
						</div>

						<div class="row-btn">
							<a class="btn btn-primary"
							onclick="guardarNota({//id_persona},{//id_carga_seleccionada},'{//Carga[id_carga=//id_carga_seleccionada]/estado}','{//Carga[id_carga=//id_carga_seleccionada]/id_revision}', '{$id_instancia}');">Adicionar Nota</a>
						</div>
						
					</div>
				</xsl:if>
			</div>

			<div id="PLANTILLA_LISTA_NOTAS" style="display:none">

				<div id="contenido_lista_notas">&#160;</div>

			</div>

<!-- 			<div id="PLANTILLA_ITEM_NOTA" style="display:none"> -->
<!-- 				<div id="item_nota_[ 1 ]" class="bloqueestilo bloque_nota"> -->

<!-- 					<div class="w180 bloque_comp_nota"> -->
<!-- 						<h3 class="bloque_contenido_titulo titulo_nota">Autor</h3> -->
<!-- 						<p class="parrafo_ parrafo_nota">[ 2 ]</p> -->
<!-- 					</div> -->

<!-- 					<div class="w180 bloque_comp_nota"> -->
<!-- 						<h3 class="bloque_contenido_titulo titulo_nota">Fecha</h3> -->
<!-- 						<p class="parrafo_ parrafo_nota">[ 3 ]</p> -->
<!-- 					</div> -->

<!-- 					<div class="w180 bloque_comp_nota"> -->
<!-- 						<h3 class="bloque_contenido_titulo titulo_nota">Estado</h3> -->
<!-- 						<p class="parrafo_ parrafo_nota">[ 5 ]</p> -->
<!-- 					</div> -->

<!-- 					<div id="contenido_revision_nota_[ 1 ]" class="w180 bloque_comp_nota">&#160;</div> -->

<!-- 					<div class="bloque_comp_nota p100"> -->
<!-- 						<h3 class="bloque_contenido_titulo titulo_nota">Nota</h3> -->
<!-- 						<div class="bloque_contenido_contenido bloque_parrafo_nota"> -->
<!-- 							<p id="contenido_nota_[ 1 ]" class="parrafo_ parrafo_nota">[ 4 ]</p> -->
<!-- 						</div> -->
<!-- 					</div> -->

<!-- 				</div> -->
<!-- 			</div> -->

			<div id="PLANTILLA_ITEM_NOTA" style="display:none">
				<div id="item_nota_[ 1 ]" class="bloqueestilo">
				
					<div class="bloque_nota">
					
						<div class="row">
						
							<div class="col-xs-8 bloqueestilo_imagen bloqueestilo_none" id="icono_adj[ 1 ]">
								
								<h4>[ 2 ]
									<span class="label label-primary interno_[ 6 ]">
										interno
									</span>
								</h4>
								
								<pre>[ 4 ]</pre>
								
							</div>
						
							<div class="col-xs-4 text-right">
								
								<p class="adj_instancia">[ 7 ]</p>
								<h5><b>[ 3 ]</b></h5>
								<p>[ 5 ]</p>
								
								<div id="contenido_revision_nota_[ 1 ]">&#160;</div>
								
							</div>
						
						</div>
						
					</div>

				</div>
			</div>

			<div id="PLANTILLA_ITEM_REVISION" style="display:none">
				<h3 class="bloque_contenido_titulo titulo_nota">Revisión</h3>
				<p class="parrafo_ parrafo_nota">[ 1 ]</p>
			</div>

			<script>
				listarNotas(<xsl:value-of select="//id_carga_seleccionada"></xsl:value-of>, '<xsl:value-of select="//Carga[id_carga=//id_carga_seleccionada]/estado"></xsl:value-of>');
			</script>
		</escapar>



	</xsl:template>


</xsl:stylesheet>