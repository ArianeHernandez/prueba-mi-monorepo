var CALLBACK = null;
var viewport = document.querySelector("meta[name=viewport]");
var viewport_content = '';

function capturarFormulario(id_formulario, id_input_file, funcion_callback) {
	mostrarModalPdf();
	CALLBACK = funcion_callback;
	
	var minWidth = jsonrpc._("configuracionServicio.obtenerValorByEtiqueta")("MIN_WIDTH_PDF_FORMULARIO");
	var minHeight = jsonrpc._("configuracionServicio.obtenerValorByEtiqueta")("MIN_HEIGHT_PDF_FORMULARIO");
	var widthForm = jsonrpc._("configuracionServicio.obtenerValorByEtiqueta")("WIDTH_CANVAS_PDF_FORMULARIO");
	
	var viewport_content = viewport.getAttribute('content');
	viewport.setAttribute('content', 'width=' + widthForm);
	
	$("#"+id_formulario).css("width", widthForm + "px");
	$("#"+id_formulario).css("background-color","white");
	
	var useWidth = $("#"+id_formulario).width();
	var useHeight = $("#"+id_formulario).height();
	
	
	if (minWidth && useWidth < minWidth) {
		useWidth = minWidth;
	}
	
	if (minHeight && useHeight < minHeight) {
		useHeight = minHeight;
	}
	
	useWidth = convertir_px_mm(useWidth) + 40;
	useHeight = convertir_px_mm(useHeight) + 40;
	
	
	try {
		limpiarFakepath(id_input_file);
		generarPdf(widthForm, useWidth, useHeight, id_formulario);		
	} catch (err) {
		console.log(err);
		cerrarModalPdf();
		CALLBACK();
	}
	
}

function generarPdf(widthForm, useWidth, useHeight, id_formulario){
	html2canvas($("#"+id_formulario), 
			{	allowTaint: false,
        		useCORS: true,
        		foreignObjectRendering: true,
        		backgroundColor: '#ffffff'
			}
	).then(function(canvas){
		canvas = getCanvas(canvas);
		var img=canvas.toDataURL('image/jpeg', 1.0);
	   
		if (img === "data:,") {
			console.log("No se pudo generar imagen");
			cerrarModalPdf();
			CALLBACK();
		}
	   
		var doc = new jsPDF('p','mm',[useHeight, useWidth]);
		doc.addImage(img,'JPEG',20,20);
		var blob = doc.output('blob');
		subirArchivoBlob(blob);
	}).then(function () {
		viewport.setAttribute('content', viewport_content);
		$("#"+id_formulario).css("width","100%");
	});
}

function convertir_px_mm(valor_px) {
	var k = 0.2645833333;
	
	return valor_px * k;
}

function limpiarFakepath(id_input_file) {
	$(id_input_file).each(function(){
		var value = $(this).val();
		var id = $(this).attr("id");
		
		if (value) {
			$(this).val("");
		}
	});
}

function subirArchivoBlob(blob) {

	var file = new File([blob], "PDF_Formulario.pdf", { type: "application/pdf", });
	
	var idArchivoAdjunto = jsonrpc._("archivosAdjJsonServicio.obtenerSiguiente")();
	var idCarga = osm_getValorEntero("id_carga");
	var idPersona = osm_getValor("id_persona");
	
	if (validarS3Adjuntos()) {
		var adjunto = {
				id_archivo_adjunto: idArchivoAdjunto,
				ruta: "",
				id_carga: idCarga,
				nombre_archivo: "PDF_Formulario",
				extension_archivo: "pdf",
				descripcion: "Archivo Formulario Diligenciado",
				long_bytes: "",
				resumen_hash: "",
				interno: "N",
				id_persona: idPersona,
				estado: "A",
				nombre_instancia: "",
				radicado: "",
				id_tipo_archivo: "61" 
		};
		
		enviarAdjuntoS3(file, adjunto, function () {
			cerrarModalPdf();
			CALLBACK();
		}, function () {});
	} else {
		var form_data = new FormData();
		form_data.append("caja_archivo_adjunto", file );
		form_data.append("id_archivo_adjunto", idArchivoAdjunto);
		form_data.append("id_carga", idCarga);
		form_data.append("descripcion_archivo_adj","Archivo Formulario Diligenciado");
		form_data.append("id_tipo_archivo","61");
		$.ajax({
			method : "POST",
			url : "../adjunto.subirarchivo",
			contentType : "multipart/form-data;charset=iso-8859-1",
			data : form_data,
			processData: false,
			contentType: false,
			cache: false,
		}).done(function () {
			cerrarModalPdf();
			CALLBACK();
		});
	}
}

function getCanvas(srcCanvas) {
	var destinationCanvas = document.createElement("canvas");
	destinationCanvas.width = srcCanvas.width;
	destinationCanvas.height = srcCanvas.height;

	var destCtx = destinationCanvas.getContext('2d');

	destCtx.fillStyle = "#FFFFFF";
	destCtx.fillRect(0,0,srcCanvas.width,srcCanvas.height);

	destCtx.drawImage(srcCanvas, 0, 0);

	return destinationCanvas;
}
