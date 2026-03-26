package co.htsoft.commons.net;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import co.htsoft.commons.file.CharsetDF;
import co.htsoft.commons.log.HLogger;

public class CallPage {

	private List<String> cookie = new ArrayList<>();
	private int timeout = 60000;

	private static List<CallPageListener> listeners = Collections.synchronizedList(new ArrayList<CallPageListener>());

	public static void addListener(CallPageListener listener) {

		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	// ------

	public int getTimeout() {
		return timeout;
	}

	public CallPage setTimeout(int timeout) {
		this.timeout = timeout;
		return this;
	}

	public String call(String url) {
		return call(url, null, null, false);
	}

	public String call(String url, Map<String, List<Object>> params) {
		return call(url, params, null, false);
	}

	public String call(String url, Map<String, List<Object>> params, Map<String, String> headers) {
		return call(url, params, headers, false);
	}

	public String call(String url, Map<String, List<Object>> params, Boolean multipart) {
		return call(url, params, null, multipart);
	}

	public String call(String url, Map<String, List<Object>> params, Map<String, String> headers, Boolean multipart) {

		if (params == null || params.size() == 0) {
			return callGet(url, headers);
		} else {
			return callPost(url, params, headers, multipart, "POST");
		}

	}

	public static String join(Map<String, List<Object>> params) {

		String k = null;

		Set<String> keys = params.keySet();

		for (String key : keys) {

			try {
				List<Object> data = params.get(key);
				for (Object d : data) {
					k = (k == null) ? "" : (k + "&");
					k += key + "=" + URLEncoder.encode(d.toString(), "UTF-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return k;

	}

	// ------------------

	public String callGet(String url, Map<String, String> headers) {
		try {
			return callget(url, headers);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public String callget(String url, Map<String, String> headers) throws Exception {

			URL myurl = new URL(url);
			HttpURLConnection urlConn = (HttpURLConnection) myurl.openConnection();
			
			/*
			HttpsURLConnection urlConn = (HttpsURLConnection) myurl.openConnection();
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null, null, new SecureRandom());
			urlConn.setSSLSocketFactory(sslContext.getSocketFactory());
			*/
			
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(false);
			urlConn.setConnectTimeout(timeout);
			urlConn.setReadTimeout(timeout);
			urlConn.setRequestMethod("GET");

			// ------------------------------------------------------------------ registra la auditoria

			long time = System.currentTimeMillis();
			
			// ---------------------------------------------------------------- define headers

			urlConn.setRequestProperty("Connection", "Keep-Alive");
			urlConn.setRequestProperty("Cache-Control", "no-cache");
			urlConn.setRequestProperty("Pragma", "no-cache");

			urlConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			urlConn.setRequestProperty("Accept-Language", "es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3");

			if (cookie.size() > 0) {
				String c = getCookie();
				urlConn.setRequestProperty("Cookie", c);
			}
			urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");

			if (headers != null) {
				for (String key : headers.keySet()) {
					urlConn.setRequestProperty(key, headers.get(key));
				}
			}

			int responseCode = urlConn.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
				String location = urlConn.getHeaderField("Location");
				return location;
			}

			// ----------

			InputStream input = null;

			if (responseCode == 200) {
				input = urlConn.getInputStream();
			} else {
				input = urlConn.getErrorStream();
			}

			byte[] bytes = IOUtils.toByteArray(input);

			input.close();

			String resp = new String(bytes, CharsetDF.getCharset(bytes));

			registarAuditoria("GET", url, headers, null, true, responseCode, resp.getBytes(), true, System.currentTimeMillis() - time);
			
			return resp;
	}

	public String callPost(String url, Map<String, List<Object>> params, Map<String, String> headers, Boolean multipart, String method) {

		try {

			String crlf = "\r\n";
			String twoHyphens = "--";
			String boundary = "218681774630701";

			// -------------------------------------------------------------- realiza la conexion

			URL myurl = new URL(url);
			HttpURLConnection urlConn = (HttpURLConnection) myurl.openConnection();

			urlConn.setRequestMethod(method);

			if (multipart) {
				urlConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			} else {
				urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			}

			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(false);
			urlConn.setConnectTimeout(3000000);

			// ------------------------------------------------------------------ registra la auditoria

			long time = System.currentTimeMillis();

			// ---------------------------------------------------------------- define headers

			urlConn.setRequestProperty("Connection", "Keep-Alive");
			urlConn.setRequestProperty("Cache-Control", "no-cache");
			urlConn.setRequestProperty("Pragma", "no-cache");

			urlConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			urlConn.setRequestProperty("Accept-Language", "es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3");

			if (cookie.size() > 0) {

				String c = getCookie();

				urlConn.setRequestProperty("Cookie", c);
			}
			urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");

			if (headers != null) {
				for (String key : headers.keySet()) {
					urlConn.setRequestProperty(key, headers.get(key));
				}
			}

			// ------------------------------------------------------------------ llama el servicio

			DataOutputStream request = new DataOutputStream(urlConn.getOutputStream());

			if (multipart) {

				if (params != null) {
					for (String key : params.keySet()) {

						List<Object> dataList = params.get(key);

						if (dataList != null) {
							for (Object data : dataList) {

								request.writeBytes(twoHyphens + boundary + crlf);

								if (data == null) {

									request.writeBytes(crlf);

								} else {

									if (data instanceof File) {
										File file = (File) data;
										request.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + file.getName() + "\"" + crlf);
										request.writeBytes(crlf);

										request.write(IOUtils.toByteArray(new FileInputStream(file)));

									} else {

										request.writeBytes("Content-Disposition: form-data; name=\"" + key + "\";" + crlf);
										request.writeBytes(crlf);

										request.writeBytes(data.toString());
									}
								}

								request.writeBytes(crlf);

							}
						}

					}
				}

				request.writeBytes(twoHyphens + boundary + twoHyphens + crlf);

			} else {

				String content = null;

				if (params != null) {
					for (String key : params.keySet()) {

						List<Object> dataList = params.get(key);

						if (dataList != null) {
							for (Object data : dataList) {
								if (data == null) {
									data = "";
								}

								if (content == null) {
									content = "";
								} else {
									content += "&";
								}

								content += URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(data.toString(), "UTF-8");
							}
						}
					}
				}

				request.writeBytes(StringUtils.trimToEmpty(content));

			}

			request.flush();
			request.close();

			// -------------------------------------------------------------------- recibe headers

			Map<String, List<String>> resheaders = urlConn.getHeaderFields();
			if (resheaders != null) {

				Set<String> keys = resheaders.keySet();

				for (String headername : keys) {

					if (headername != null) {

						List<String> values = resheaders.get(headername);
						for (String val : values) {

							if (headername.equalsIgnoreCase("Set-Cookie")) {
								cookie.add(val.split(";")[0]);
							}

						}
					}
				}

			}

			String content = "";
			try {
				content = new Gson().toJson(params);
			} catch (Exception e) {
			}

			int responseCode = urlConn.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
				String location = urlConn.getHeaderField("Location");

				registarAuditoria("POST", url, headers, content.getBytes(), true, responseCode, location.getBytes(), true, System.currentTimeMillis() - time);

				return location;
			}

			// -------------------------------------------------------------------- recibe informacion

			InputStream input = null;

			if (responseCode == 200) {
				input = urlConn.getInputStream();
			} else {
				input = urlConn.getErrorStream();
			}

			byte[] bytes = IOUtils.toByteArray(input);

			input.close();

			String resp = new String(bytes, CharsetDF.getCharset(bytes));

			registarAuditoria(method, url, headers, content.getBytes(), true, responseCode, resp.getBytes(), true, System.currentTimeMillis() - time);

			return resp;

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public String callPostTLS1_2(String url, Map<String, List<Object>> params, Map<String, String> headers, Boolean multipart, String method) {

		try {

			String crlf = "\r\n";
			String twoHyphens = "--";
			String boundary = "218681774630701";

			// -------------------------------------------------------------- realiza la conexion

			URL myurl = new URL(url);
//			HttpURLConnection urlConn = (HttpURLConnection) myurl.openConnection();

			HttpsURLConnection urlConn = (HttpsURLConnection) myurl.openConnection();
			
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null, null, new SecureRandom());
			
			urlConn.setRequestMethod(method);

			if (multipart) {
				urlConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			} else {
				urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			}

			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(false);
			urlConn.setConnectTimeout(3000000);
			urlConn.setSSLSocketFactory(sslContext.getSocketFactory());

			// ------------------------------------------------------------------ registra la auditoria

			long time = System.currentTimeMillis();

			// ---------------------------------------------------------------- define headers

			urlConn.setRequestProperty("Connection", "Keep-Alive");
			urlConn.setRequestProperty("Cache-Control", "no-cache");
			urlConn.setRequestProperty("Pragma", "no-cache");

			urlConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			urlConn.setRequestProperty("Accept-Language", "es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3");

			if (cookie.size() > 0) {

				String c = getCookie();

				urlConn.setRequestProperty("Cookie", c);
			}
			urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");

			if (headers != null) {
				for (String key : headers.keySet()) {
					urlConn.setRequestProperty(key, headers.get(key));
				}
			}

			// ------------------------------------------------------------------ llama el servicio

			DataOutputStream request = new DataOutputStream(urlConn.getOutputStream());

			if (multipart) {

				if (params != null) {
					for (String key : params.keySet()) {

						List<Object> dataList = params.get(key);

						if (dataList != null) {
							for (Object data : dataList) {

								request.writeBytes(twoHyphens + boundary + crlf);

								if (data == null) {

									request.writeBytes(crlf);

								} else {

									if (data instanceof File) {
										File file = (File) data;
										request.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + file.getName() + "\"" + crlf);
										request.writeBytes("Content-Type: " + URLConnection.guessContentTypeFromName(file.getName()) + crlf);
										request.writeBytes("Content-Transfer-Encoding: binary" + crlf);
										request.writeBytes(crlf);

										request.write(IOUtils.toByteArray(new FileInputStream(file)));

									} else {

										request.writeBytes("Content-Disposition: form-data; name=\"" + key + "\";" + crlf);
										request.writeBytes(crlf);

										request.writeBytes(data.toString());
									}
								}

								request.writeBytes(crlf);

							}
						}

					}
				}

				request.writeBytes(twoHyphens + boundary + twoHyphens + crlf);

			} else {

				String content = null;

				if (params != null) {
					for (String key : params.keySet()) {

						List<Object> dataList = params.get(key);

						if (dataList != null) {
							for (Object data : dataList) {
								if (data == null) {
									data = "";
								}

								if (content == null) {
									content = "";
								} else {
									content += "&";
								}

								content += URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(data.toString(), "UTF-8");
							}
						}
					}
				}

				request.writeBytes(StringUtils.trimToEmpty(content));

			}

			request.flush();
			request.close();

			// -------------------------------------------------------------------- recibe headers

			Map<String, List<String>> resheaders = urlConn.getHeaderFields();
			if (resheaders != null) {

				Set<String> keys = resheaders.keySet();

				for (String headername : keys) {

					if (headername != null) {

						List<String> values = resheaders.get(headername);
						for (String val : values) {

							if (headername.equalsIgnoreCase("Set-Cookie")) {
								cookie.add(val.split(";")[0]);
							}

						}
					}
				}

			}

			String content = "";
			try {
				content = new Gson().toJson(params);
			} catch (Exception e) {
			}

			int responseCode = urlConn.getResponseCode();

			if (responseCode == HttpsURLConnection.HTTP_MOVED_TEMP || responseCode == HttpsURLConnection.HTTP_MOVED_PERM || responseCode == HttpsURLConnection.HTTP_SEE_OTHER) {
				String location = urlConn.getHeaderField("Location");

				registarAuditoria("POST", url, headers, content.getBytes(), true, responseCode, location.getBytes(), true, System.currentTimeMillis() - time);

				return location;
			}

			// -------------------------------------------------------------------- recibe informacion

			InputStream input = null;

			if (responseCode == 200) {
				input = urlConn.getInputStream();
			} else {
				input = urlConn.getErrorStream();
			}

			byte[] bytes = IOUtils.toByteArray(input);

			input.close();

			String resp = new String(bytes, CharsetDF.getCharset(bytes));

			registarAuditoria(method, url, headers, content.getBytes(), true, responseCode, resp.getBytes(), true, System.currentTimeMillis() - time);

			return resp;

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return null;
	}

	public String callPost(String url, String content, Map<String, String> headers) {
		
		try {
			return call(url, content, headers, "POST");			
		} catch (Throwable e) {
			HLogger.error("Error en CallPage, url: " + url, e);
		}
		return null;
	}

	public String call(String url, String content, Map<String, String> headers, String method) throws Throwable{
			// -------------------------------------------------------------- realiza la conexion

			URL myurl = new URL(url);
			HttpURLConnection urlConn = (HttpURLConnection) myurl.openConnection();
			
			/*
			HttpsURLConnection urlConn = (HttpsURLConnection) myurl.openConnection();
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null, null, new SecureRandom());
			urlConn.setSSLSocketFactory(sslContext.getSocketFactory());
			*/

			urlConn.setRequestMethod(method);
			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(false);
			urlConn.setConnectTimeout(timeout);
			urlConn.setReadTimeout(timeout);
			

			// ---------------------------------------------------------------- define headers

			if (cookie.size() > 0) {

				String c = getCookie();

				urlConn.setRequestProperty("Cookie", c);
			}

			if (headers != null) {
				for (String key : headers.keySet()) {
					urlConn.setRequestProperty(key, headers.get(key));
				}
			}

			// ------------------------------------------------------------------ registra la auditoria

			long time = System.currentTimeMillis();

			// ------------------------------------------------------------------ llama el servicio

			if (content != null) {

				DataOutputStream request = new DataOutputStream(urlConn.getOutputStream());

				request.writeBytes(StringUtils.trimToEmpty(content));

				request.flush();
				request.close();
			}

			// -------------------------------------------------------------------- recibe headers

			Map<String, List<String>> resheaders = urlConn.getHeaderFields();
			if (resheaders != null) {

				Set<String> keys = resheaders.keySet();

				for (String headername : keys) {

					if (headername != null) {

						List<String> values = resheaders.get(headername);
						for (String val : values) {

							if (headername.equalsIgnoreCase("Set-Cookie")) {
								cookie.add(val.split(";")[0]);
							}

						}
					}
				}

			}

			int responseCode = urlConn.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {

				String location = urlConn.getHeaderField("Location");

				if (content != null) {
					registarAuditoria(method, url, headers, content.getBytes(), true, responseCode, location.getBytes(), true, System.currentTimeMillis() - time);					
				} else {
					registarAuditoria(method, url, headers, null, true, responseCode, location.getBytes(), true, System.currentTimeMillis() - time);
				}
				

				return location;
			}

			// -------------------------------------------------------------------- recibe informacion

			InputStream input = null;
			
			if (responseCode == 200) {
				input = urlConn.getInputStream();
			} else {
				if (urlConn.getErrorStream() != null){
				input = urlConn.getErrorStream();
				} else {
				input = urlConn.getInputStream();
				}
			}


			
			byte[] bytes = IOUtils.toByteArray(input);

			input.close();

			String resp = new String(bytes, CharsetDF.getCharset(bytes));

			if (content != null) {
				registarAuditoria(method, url, headers, content.getBytes(), true, responseCode, resp.getBytes(), true, System.currentTimeMillis() - time);				
			} else {
				registarAuditoria(method, url, headers, null, true, responseCode, resp.getBytes(), true, System.currentTimeMillis() - time);
			}

			return resp;
	}
	
	public String callHttps(String url, String content, Map<String, String> headers, String method) throws Throwable{
		// -------------------------------------------------------------- realiza la conexion

		URL myurl = new URL(url);
		HttpsURLConnection urlConn = (HttpsURLConnection) myurl.openConnection();

		SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
		sslContext.init(null, null, new SecureRandom());
		
		urlConn.setRequestMethod(method);

		urlConn.setDoInput(true);
		urlConn.setDoOutput(true);
		urlConn.setUseCaches(false);
		urlConn.setInstanceFollowRedirects(false);
		urlConn.setConnectTimeout(3000000);
		urlConn.setSSLSocketFactory(sslContext.getSocketFactory());
		// ---------------------------------------------------------------- define headers

		if (cookie.size() > 0) {

			String c = getCookie();

			urlConn.setRequestProperty("Cookie", c);
		}

		if (headers != null) {
			for (String key : headers.keySet()) {
				urlConn.setRequestProperty(key, headers.get(key));
			}
		}

		// ------------------------------------------------------------------ registra la auditoria

		long time = System.currentTimeMillis();

		// ------------------------------------------------------------------ llama el servicio

		if (content != null) {

			DataOutputStream request = new DataOutputStream(urlConn.getOutputStream());

			request.writeBytes(StringUtils.trimToEmpty(content));

			request.flush();
			request.close();
		}

		// -------------------------------------------------------------------- recibe headers

		Map<String, List<String>> resheaders = urlConn.getHeaderFields();
		if (resheaders != null) {

			Set<String> keys = resheaders.keySet();

			for (String headername : keys) {

				if (headername != null) {

					List<String> values = resheaders.get(headername);
					for (String val : values) {

						if (headername.equalsIgnoreCase("Set-Cookie")) {
							cookie.add(val.split(";")[0]);
						}

					}
				}
			}

		}

		int responseCode = urlConn.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {

			String location = urlConn.getHeaderField("Location");

			registarAuditoria(method, url, headers, content.getBytes(), true, responseCode, location.getBytes(), true, System.currentTimeMillis() - time);

			return location;
		}

		// -------------------------------------------------------------------- recibe informacion

		InputStream input = null;

//		if (responseCode == 200) {
//			input = urlConn.getInputStream();
//		} else {
//			input = urlConn.getErrorStream();
//		}
		
		if (responseCode == 200) {
			input = urlConn.getInputStream();
		} else {
			if (urlConn.getErrorStream() != null){
			input = urlConn.getErrorStream();
			} else {
			input = urlConn.getInputStream();
			}
		}


		
		byte[] bytes = IOUtils.toByteArray(input);

		input.close();

		String resp = new String(bytes, CharsetDF.getCharset(bytes));

		registarAuditoria(method, url, headers, content.getBytes(), true, responseCode, resp.getBytes(), true, System.currentTimeMillis() - time);

		return resp;
}

	private void registarAuditoria(String method, String url, Map<String, String> requestHeaders, byte[] requestData, boolean isTextRequest, int responseCode, byte[] responseData, boolean isTextResponse, long totalTime) {
		for (CallPageListener listener : listeners) {
			try {
				listener.call(method, url, requestHeaders, requestData, isTextRequest, responseCode, responseData, isTextResponse, totalTime);
			} catch (Exception e) {
			}
		}
	}

	public <T> T callJSON(String url, Object parameter, Class<T> clazz) {
		return callJSON(url, parameter, clazz, null);
	}

	public <T> T callJSON(String url, Object parameter, Class<T> clazz, Map<String, String> headers, String method) {
		if (headers == null)
			headers = new HashMap<>();

		headers.put("Content-Type", "application/json");

		String content = "null";

		if (parameter != null) {
			content = new Gson().toJson(parameter);
			// á é í ó ú ñ Á É Í Ó Ú Ñ °
			content = content.replace("\u00E1", "\\u00E1");
			content = content.replace("\u00E9", "\\u00E9");
			content = content.replace("\u00ED", "\\u00ED");
			content = content.replace("\u00F3", "\\u00F3");
			content = content.replace("\u00FA", "\\u00FA");
			content = content.replace("\u00F1", "\\u00F1");
			content = content.replace("\u00C1", "\\u00C1");
			content = content.replace("\u00C9", "\\u00C9");
			content = content.replace("\u00CD", "\\u00CD");
			content = content.replace("\u00D3", "\\u00D3");
			content = content.replace("\u00DA", "\\u00DA");
			content = content.replace("\u00D1", "\\u00D1");
			content = content.replace("\u00B0", "\\u00B0");
		}

		try {
			String resp = call(url, content, headers, method);
			
			return new Gson().fromJson(resp, clazz);
			
		} catch (Throwable e) {
			HLogger.error("Error en CallPage, url: " + url, e);
		}
		return null;
		
	}

	public <T> T callJSON(String url, Class<T> clazz) {
		return callJSON(url, null, clazz, null, "GET");
	}

	public <T> T callJSON(String url, Object parameter, Class<T> clazz, Map<String, String> headers) {
		return callJSON(url, parameter, clazz, headers, "POST");

	}

	// ***************************************************************************************************************
	// ***************************************************************************************************************

	public String getCookie() {

		String c = null;
		for (String scookie : cookie) {

			if (c == null) {
				c = scookie;
			} else {
				c = c + "; " + scookie;
			}

		}

		return c;
	}

	public static Map<String, List<Object>> addParam(Map<String, List<Object>> map, String param, Object data) {

		if (data == null) {
			data = "";
		}

		if (map == null) {
			map = new HashMap<>();
		}

		List<Object> dataList = map.get(param);
		if (dataList == null) {
			dataList = new ArrayList<>();
			map.put(param, dataList);
		}

		dataList.add(data);

		return map;
	}

	public static String getInputValueByName(String html, String param) {

		if (html == null) {
			html = "";
		}

		String html_upper = html.toUpperCase();

		List<Integer> indexList = new ArrayList<>();

		int i = -1;

		while ((i = html_upper.indexOf("<INPUT", i + 1)) >= 0) {
			indexList.add(i);
		}

		for (Integer index : indexList) {

			String input = html.substring(index, html.indexOf(">", index) + 1);

			if (input.indexOf("name=\"" + param + "\"") > 0) {

				try {
					input = input.substring(input.indexOf("value=\"") + 7);
					input = input.substring(0, input.indexOf("\""));

					return input;

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		// ------------------------------------------------------

		int j = 0;
		if ((j = html.indexOf("|" + param + "|")) > 0) {

			String input = html.substring(j + ("|" + param + "|").length());

			input = input.substring(0, input.indexOf("|"));

			return input;

		}

		throw new RuntimeException("NO SE ENCONTRO EL PARAMETRO " + param);

	}

}
