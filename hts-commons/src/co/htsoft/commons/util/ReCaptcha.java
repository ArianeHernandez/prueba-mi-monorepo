package co.htsoft.commons.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import co.htsoft.commons.net.CallPage;

public class ReCaptcha {

	public static String serverkey;
	public static String webkey;

	public static class RCResponse {
		public Boolean success;
	}

	public static Boolean validar(String token) {
		return validar(serverkey, token);
	}

	public static Boolean validar(String secret, String token) {

		Map<String, List<Object>> params = new HashMap<>();

		CallPage.addParam(params, "secret", secret);
		CallPage.addParam(params, "response", token);

		RCResponse resp = new Gson().fromJson(new CallPage().callPost("https://www.google.com/recaptcha/api/siteverify", params, null, false, "POST"), RCResponse.class);

		return resp.success;

	}

}
