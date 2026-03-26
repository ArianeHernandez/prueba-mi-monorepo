

function notificarInactividad () {
	if (Notification.permission === "granted") {
		const notification = new Notification("Cierre de sesi\u00f3n por inactividad", 
			{ body: "La sesi\u00f3n del M\u00f3dulo de Insolvencia se cerrar\u00e1 en los pr\u00f3ximos cinco minutos, aseg\u00farese de haber finalizado las operaciones."});
		
		cambiarIconoNotificacion();
	}
}

function cambiarIconoNotificacion() {
	var src = CONTEXTPATH + "/icons/icon2.ico";
	document.getElementById('favicon').href = src;
}

function cambiarIconoDefault() {
	var src = CONTEXTPATH + "/icons/icon.ico";
	document.getElementById('favicon').href = src;
}