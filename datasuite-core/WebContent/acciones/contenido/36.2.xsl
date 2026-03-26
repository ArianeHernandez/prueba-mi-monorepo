<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Parametrizacion de Contenido">
		
			<stylesheet>SimpleTextEditor.css</stylesheet>
			<javascript>contenido/36.2.js</javascript>
			<javascript>contenido/SimpleTextEditor.js</javascript>
			
			
		
		
			<principal>
				<titulo>Parametrizacion de Contenido</titulo>
				<contenido>
					
				  			
					<bloque-contenido>
					<titulo>Edición</titulo>
					<contenido>
						<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
						
						<registro>
						<item>URL del contenido: </item>
						<valor>
							<cajatexto class="" valor="{//obtenerContenidoPorID/Contenido/url}" desactivado="true"/>	
						</valor>
					 	</registro>
				
				
				
						<registro>
						<item>Etiqueta:</item>
						<valor>
							<cajatexto class="" valor="{//obtenerContenidoPorID/Contenido/etiqueta}" desactivado="true"/>	
						</valor>
					 	</registro>
					
					 	
				
					 	<registro>
						<item>Texto:</item>
						<valor>
						
						<variable id="texto_caja_texto" valor="{//obtenerContenidoPorID/Contenido/texto}"/>
						
						<formulario id="form_guardar_contenido" destino="contenido/36.3.do">
							<variable id="contenido.id_contenido" valor="{//obtenerContenidoPorID/Contenido/id_contenido}"/> 
							<variable id="contenido.texto" valor=""/> 
					
					
							<textarea id="caja_texto" name="caja_texto" style="width:100%; height: 100px" >
														
							</textarea>
							
							
						</formulario>	
							
						</valor>
					 	</registro>
					 	
					 	
					 	</div>
					</contenido>
					</bloque-contenido>
					
			 	
				<div class="row-btn" id="area_botones">
					
						<boton estilo="guardar" accion="guardarContenido()"><i class="fa fa-save" aria-hidden="true"></i>&#160;Guardar</boton>
						<boton estilo="volver" destino="contenido/36.1.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Volver al listado</boton>
					
				</div>
					
					
				</contenido>
			</principal>
		</pagina>
		
	</xsl:template>
	


</xsl:stylesheet>
