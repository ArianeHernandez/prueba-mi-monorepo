<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:template match="OSM-ACCION">

		<SELECCIONAR-NEGOCIO />

		<pagina titulo="Agrupar Giros">

			<javascript>webdata/24.1.js</javascript>
			<stylesheet>23.css</stylesheet>

			<principal>
				<titulo>Agrupar Giros</titulo>
				<contenido>
					
						<parrafo id="mensaje">
							Generando archivo...
						</parrafo>
						
						<div class="barra-progreso" id="barra-progreso">
							<div class="barra-progreso-avance" id="progreso">
							</div>
						</div>
							
						
						<area_botones>
						  	<a class="btn btn-default" onclick="osm_go('/ArchivoBanco', false)" id="btn_archivo" style="display:none">
						  		Descargar Archivo
						  	</a>
						</area_botones>		
							
					<area_botones>
						<boton estilo="primary volver" destino="agrupar_giros/24.do">Volver</boton>
						<boton estilo="primary inicio" destino="inicio/0.do">Ir al Inicio</boton>
					</area_botones>
					
				</contenido>
			</principal>
		</pagina>

	</xsl:template>


</xsl:stylesheet>
