<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	<xsl:include href="context://componentes/menu/acciones.xsl"/>
	
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Bienvenido">
			
			<javascript>system/info.js</javascript>
			
			<principal>
				<titulo icono="inicio">Consulta Soporte HTS</titulo>
				<contenido>
					<div class=" form-horizontal">
					<bloque-pestanas>
						<pestana titulo="Soporte {VERSION_APLICACION}">
							
							<form action="info.do" id="form_info">
								<div class="">
								
									<div class="row">
										<div class="form-group col-sm-8">
											 <label class="contol-label col-sm-4">Fuente</label>
											 <div class="col-sm-8">
											 	<valor>
													<select class="form-control" name="pool" style="width:90%" value="{pool}">
														<option value="CREADATOS">CREADATOS</option>
														<option value="DATASUITE">DATASUITE</option>
													</select>
												</valor>
											 </div>
										 </div>
										 
										 <div class="form-group col-sm-4">
											 <label class="contol-label col-sm-4">Decifrar</label>
											 <div class="col-sm-8">
											 	<valor>
													<cajachequeo2 id="crypto" value="S" seleccionado="{crypto='S'}" valor="S" valor2="N"/>
												</valor>
											 </div>
										 </div>
									 </div>
									 
									 <div style="margin-top: 15px">
									 	<textarea  class="form-control" id="caja_texto" name="sql"><xsl:value-of select="sql"/></textarea>
									 </div>
								</div>
																	
								<area_botones>
									<button type="button" onclick="osm_enviarFormulario('form_info')" value="ejecutar" class="btn btn-sm btn-primary"><i class="fa fa-play-circle-o" aria-hidden="true"></i>&#160;Ejecutar</button>
								</area_botones>
									
							</form>
							
							<xsl:if test="count(EXE)>0 and count(EXE/HashMap)=0">
								<alerta>Sin resultado</alerta>
							</xsl:if>
													
							<xsl:if test="count(EXE/HashMap)>0">
								<tabla>
									<encabezado>
										
										<titulo>#</titulo>
										
										<xsl:for-each select="EXE/HashMap[1]/*">
											<xsl:sort select="name()"/>
											<titulo><xsl:value-of select="name()"/></titulo>	
										</xsl:for-each>
										
									</encabezado>
									
									<xsl:for-each select="EXE/HashMap">
										<fila>
											<valor class="tabla_encabezado"><xsl:value-of select="position()"/></valor>
											<xsl:for-each select="*">
												<xsl:sort select="name()"/>
												<valor><xsl:value-of select="."/></valor>
											</xsl:for-each>
										</fila>
									</xsl:for-each>
								</tabla>
							</xsl:if>
							
						</pestana>
						
						<pestana titulo="Estado">
						<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
							<xsl:choose>
								<xsl:when test="count(info/SOBRAN/String)=0 and count(info/FALTAN/String)=0">
									<parrafo estilo="resaltado">
										No se presentan inconsistencias
									</parrafo>
								</xsl:when>
								<xsl:otherwise>
									<div class="alert alert-danger">
										<span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
	  									<span class="sr-only">Error:</span>
										<p>Se presentan inconsistencias</p>
									</div>
								</xsl:otherwise>
							</xsl:choose>
							</div>
							<div class="clearfix"></div>
							<div class="table-responsive">
							<xsl:if test="count(info/FALTAN/String)>0">
								<tabla>
									<encabezado>
										<titulo><div>Objetos Faltantes</div></titulo>
									</encabezado>
									
									<xsl:for-each select="info/FALTAN/String">
										<fila>
											<valor><xsl:value-of select="."/></valor>
										</fila>
									</xsl:for-each>
								</tabla>
							</xsl:if>
							
							<xsl:if test="count(info/SOBRAN/String)>0">
								<tabla>
									<encabezado>
										<titulo>Objetos Adicionales</titulo>
									</encabezado>
									
									<xsl:for-each select="info/SOBRAN/String">
										<fila>
											<valor><xsl:value-of select="."/></valor>
										</fila>
									</xsl:for-each>
								</tabla>
							</xsl:if>
							</div>
						</pestana>
						
						<pestana titulo="Servicios">
								<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
									<div class="form-group form-group-sm">
										 <label class="contol-label col-sm-4">Clase</label>
										 <div class="col-sm-8">							
											<valor>
												<cajatexto id="classname" />
											</valor>
										</div>
									</div>
			
							
									<div class="form-group form-group-sm">
										 <label class="contol-label col-sm-4">Método</label>
										 <div class="col-sm-8">						
											<valor>
												<cajatexto id="method" />
											</valor>
										</div>
									</div>
								
									<div class="form-group form-group-sm">
										 <label class="contol-label col-sm-4">Parámetros</label>
										 <div class="col-sm-8">
											<valor>
												<cajatexto id="params" />
											</valor>
										</div>
									</div>
	
								
								<area_botones>
									<boton estilo="primary" validacion="validarDatos()" accion="exeService()" ><i class="fa fa-play-circle-o" aria-hidden="true"></i>&#160;Ejecutar</boton>
								</area_botones>
								
								<div class="form-group form-group-sm">
									<textarea id="cajaService" class="form-control" style="width:100%; height: 300px" readonly="readonly" />
								</div>
							</div>
						</pestana>
						
					</bloque-pestanas>
					</div>
				</contenido>
				
			</principal>			
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
