<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Bienvenido">
			
			<principal>
				<contenido>
					<bloque estilo="columna">
						<p>Certifico bajo gravedad de juramento que:</p>
						
						<formulario>
						
							<registro>
								<item style="text-align: left">
									Cuento con la autorización para tramitar esta solicitud (Si aplica):
								</item>
								<valor>
									<select class="put">
										<option>--Seleccione--</option>
										<option>Si</option>
										<option>No</option>
									</select>
								</valor>
							</registro>
							
							<div id="observ_1" style="text-align: center; justify-content: center; display: flex;">
								<textarea rows="5" cols="150" placeholder="Ingrese las observaciones" style="resize: none"></textarea>
							</div>
							
							<registro>
								<item style="text-align: left">
									La sociedad a la que represento lleva contabilidad regular de sus negocios:
								</item>
								<valor>
									<select class="put">
										<option>--Seleccione--</option>
										<option>Si</option>
										<option>No</option>
									</select>
								</valor>
							</registro>
							
							<div id="observ_2" style="text-align: center; justify-content: center; display: flex;">
								<textarea rows="5" cols="150" placeholder="Ingrese las observaciones" style="resize: none"></textarea>
							</div>
							
							<registro>
								<item style="text-align: left">
									¿El deudor tiene pasivos pensionales a cargo?:
								</item>
								<valor>
									<select class="put">
										<option>--Seleccione--</option>
										<option>Si</option>
										<option>No</option>
									</select>
								</valor>
							</registro>
							
							<div id="observ_3" style="text-align: center; justify-content: center; display: flex;">
								<textarea rows="5" cols="150" placeholder="Ingrese las observaciones" style="resize: none"></textarea>
							</div>
							
							<registro>
								<item style="text-align: left">
									El marco bajo el cual lleva la contabilidad de la organización:
								</item>
								<valor>
									<select class="put">
										<option>--Seleccione--</option>
										<option>Grupo 1</option>
										<option>Grupo 2</option>
										<option>Grupo 3</option>
									</select>
								</valor>
							</registro>
							
							<div id="observ_4" style="text-align: center; justify-content: center; display: flex;">
								<textarea rows="5" cols="150" placeholder="Ingrese las observaciones" style="resize: none"></textarea>
							</div>
							
							<registro>
								<item style="text-align: left">
									El deudor pertenece a un grupo empresarial en los términos del artículo 28 de la Ley 222 de 1995:
								</item>
								<valor>
									<select class="put">
										<option>--Seleccione--</option>
										<option>Si</option>
										<option>No</option>
									</select>
								</valor>
							</registro>
							
							<div id="observ_5" style="text-align: center; justify-content: center; display: flex;">
								<textarea rows="5" cols="150" placeholder="Ingrese las observaciones" style="resize: none"></textarea>
							</div>
							
							<registro>
								<item style="text-align: left">
									El deudor pertenece a un grupo empresarial en los términos del artículo 2.2.2.14.1.1 Decreto 1074 de 2015:
								</item>
								<valor>
									<select class="put">
										<option>--Seleccione--</option>
										<option>Si</option>
										<option>No</option>
									</select>
								</valor>
							</registro>
							
							<div id="observ_6" style="text-align: center; justify-content: center; display: flex;">
								<textarea rows="5" cols="150" placeholder="Ingrese las observaciones" style="resize: none"></textarea>
							</div>
						</formulario>
					</bloque>
					
					<area_botones>
						<boton estilo="guardar">Aceptar</boton>
					</area_botones>
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
