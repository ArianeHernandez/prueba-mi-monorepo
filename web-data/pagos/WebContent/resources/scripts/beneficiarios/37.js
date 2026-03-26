function eliminarBeneficiario(id){
	if(!confirm("¿Está seguro de eliminar el registro del beneficiario?")){
		return;
	}
	var rta = jsonrpc._("beneficiarioServicio.eliminarBeneficiario")(id);
	if (rta) {
		
		$("#fila_" + id).remove();
		alert("El registro del beneficiario se ha eliminado exitosamente.");
	}
	else{
		alert("No es posible eliminar el registro del beneficiario, intente nuevamente.");
	}
	
}
