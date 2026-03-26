<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template name="CONT_CARGA">
		<xsl:param name="id" />
		<xsl:param name="archivo"/>
		
		<xsl:variable name="rta" select="//obtenerRespuestas/Objeto" />
		<xsl:variable name="reload">
			$(function(){window.setTimeout( function(){ osm_go('carga_masiva/31.<xsl:value-of select="$id"/>.do?idCargue=<xsl:value-of select="//ID_CARGUE_MASIVO"/>',false) },4000)});
		</xsl:variable>
		<div class="panel-body">
			<div class="panel-col col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 form-horizontal">
		<xsl:choose>
			<xsl:when test="count($rta/String) > 0">
				<div class="alert alert-success">
					<i class="fa fa-check-circle" aria-hidden="true"></i> <b>Se finalizó la <texto key="CARGA" /> con los siguientes detalles:</b>
				</div>
				<xsl:for-each select="$rta/String">
					<parrafo>
						<xsl:value-of select="." />
					</parrafo>
				</xsl:for-each>
			</xsl:when>
			<xsl:when test="count($rta/*) > 0">
				<nota>
					<b>
						Se han cargado
						<xsl:value-of select="$rta/registrosCargados" />
						registro de
						<xsl:value-of select="$rta/totalRegistros" />
					</b>
				</nota>
				<script>
					<xsl:value-of select="$reload"/>
				</script>
			</xsl:when>
			<xsl:when test="string-length($rta) > 0">
				<nota>
					<b>
						<xsl:value-of select="$rta" />
					</b>
				</nota>
				<script>
					<xsl:value-of select="$reload"/>
				</script>
			</xsl:when>
			<xsl:otherwise>
				<formulario id="form_sendfile" destino="CargueMasivo"
					dato="multipart/form-data">
					<div class="row-form form-horizontal">
						<div class="form-group form-group-sm">				
							<div class="col-sm-12">
								<div class="input-group input-group-sm">				
									<valor>
										<cajaarchivo id="archivo"  class="form-control" />
										<variable id="tipo" valor="{$id}" />
										<variable id="url" valor="/carga_masiva/31.{$id}.do" />
									</valor>
									<div class="input-group-btn">
										<boton estilo="primary aceptar" validacion="validarArc()" formulario="form_sendfile">Cargar</boton>
									</div>	
								</div>
							</div>						
						</div>
					</div>
				</formulario>
				
				<script>
					function validarArc(){
						if(osm_esVacio(osm_getValor('archivo'))){
							alert("Debe seleccionar el archivo!");
							return false;
						}
						return true;
					}
				</script>
			</xsl:otherwise>
		</xsl:choose>
		</div>
		</div>
		<div class="panel-footer">
			<boton estilo="volver" destino="carga_masiva/31.do"><i class="fa fa-caret-square-o-left" aria-hidden="true"></i>&#160;Volver</boton>
			<boton estilo="guardar" accion="osm_go('/publicfiles/cargue_masivo/{$archivo}',false);" ><i class="fa fa-cloud-download" aria-hidden="true"></i>&#160;Descargar Plantilla</boton>
			
		</div>

	</xsl:template>

</xsl:stylesheet>