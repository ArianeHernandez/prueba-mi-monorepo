// ************************

var SECURE_RSA = new RSAKey();
SECURE_RSA.setPublic("c2a3ea03d28a8049b5cad185ea89f97507d03f75ff27290fff0ae8328a33f24ea59548ca8a0e07d01ee576795896f5cb45633c934c392f15ca83c2ba14ef07a22d2697418f4f3b8fedd33e785963e9f01ea2912534a419667549b1cf55499d692985e1a792f2d301de0c8b8be9ae6f09e66dc1c34d04d49fcf7d5b366ef61803", "10001");

// ------------------------

function secure_supports_html5_storage() {
	try {
		return 'localStorage' in window && window['localStorage'] !== null;
	} catch (e) {
		return false;
	}
}

function secure_encode(str) {
	var result = "";
	for (var i = 0; i < str.length; i++) {
		result += str.charCodeAt(i).toString(16);
	}
	return result;
}

// ------------------------

function secure_encrypt(txt) {

	if (txt == null) {
		txt = "";
	}

	txt = secure_encode(txt);

	var res = "";
	var len = 110;

	while (txt.length > len) {
		var a = txt.substring(0, len);
		res = res + hex2b64(SECURE_RSA.encrypt(a));
		txt = txt.substring(len);
	}

	return res + hex2b64(SECURE_RSA.encrypt(txt));
}

// ------------------------
