package com.osmosyscol.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

public class CallPage {

	public static String call(String url) {
		return call(url, "");
	}

	public static String call(String url, String content) {

		if (content != null && content.length() == 0) {
			content = null;
		}

		return (new String(callB(url, content)));
	}

	public static byte[] callB(String url) {
		return callB(url, null);
	}

	public static byte[] callB(String url, String content) {

		try {

			// -------------------------------------------------------------- realiza la conexion

			URL myurl = new URL(url);
			HttpURLConnection urlConn = (HttpURLConnection) myurl.openConnection();

			if (content == null) {

				urlConn.setRequestMethod("GET");

			} else {

				urlConn.setRequestMethod("POST");
				urlConn.setDoInput(true);
				urlConn.setDoOutput(true);
				urlConn.setUseCaches(false);
				urlConn.setInstanceFollowRedirects(false);

				// ---------------------------------------------------------------- define headers
			}

			urlConn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

			urlConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			urlConn.setRequestProperty("Accept-Language", "es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3");

			urlConn.setRequestProperty("Accept-Encoding", "deflate");
			urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");

			urlConn.setConnectTimeout(300000);

			// ------------------------------------------------------------------ llama el servicio

			if (content != null) {
				DataOutputStream request = new DataOutputStream(urlConn.getOutputStream());

				request.writeBytes(StringUtils.trimToEmpty(content));

				request.flush();
				request.close();
			}

			// -------------------------------------------------------------------- recibe headers

			int responseCode = urlConn.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
				return null;
			}

			// -------------------------------------------------------------------- recibe informacion

			InputStream in = urlConn.getInputStream();
			ByteArrayOutputStream fout = new ByteArrayOutputStream();

			try {

				final byte data[] = new byte[1024];
				int count;
				while ((count = in.read(data, 0, 1024)) != -1) {
					fout.write(data, 0, count);
				}
			} catch (Exception e) {
				// e.printStackTrace();
			} finally {
				if (in != null) {
					in.close();
				}
				if (fout != null) {
					fout.close();
				}
			}

			byte[] bytes = fout.toByteArray();

			return bytes;

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return null;
	}

	private List<String> cookie = new ArrayList<>();
	private int timeout = 3000000;

	// ------

	public int getTimeout() {
		return timeout;
	}

	public CallPage setTimeout(int timeout) {
		this.timeout = timeout;
		return this;
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
			return callPost(url, params, headers, multipart);
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
			URL myurl = new URL(url);
			HttpURLConnection urlConn = (HttpURLConnection) myurl.openConnection();

			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(false);
			urlConn.setConnectTimeout(timeout);
			urlConn.setRequestMethod("GET");

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

			InputStream input = urlConn.getInputStream();
			byte[] bytes = IOUtils.toByteArray(input);

			input.close();

			String resp = new String(bytes);

			return resp;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String callPost(String url, Map<String, List<Object>> params, Map<String, String> headers, Boolean multipart) {

		try {

			String crlf = "\r\n";
			String twoHyphens = "--";
			String boundary = "218681774630701";

			// -------------------------------------------------------------- realiza la conexion

			URL myurl = new URL(url);
			HttpURLConnection urlConn = (HttpURLConnection) myurl.openConnection();

			urlConn.setRequestMethod("POST");

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

			int responseCode = urlConn.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
				String location = urlConn.getHeaderField("Location");
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

			String resp = new String(bytes);

			return resp;

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return null;
	}

	public String callPost(String url, String content, Map<String, String> headers) {
		return call(url, content, headers, "POST");
	}

	private String call(String url, String content, Map<String, String> headers, String method) {
		try {

			// -------------------------------------------------------------- realiza la conexion

			URL myurl = new URL(url);
			HttpURLConnection urlConn = (HttpURLConnection) myurl.openConnection();

			urlConn.setRequestMethod(method);

			urlConn.setDoInput(true);
			urlConn.setDoOutput(true);
			urlConn.setUseCaches(false);
			urlConn.setInstanceFollowRedirects(false);
			urlConn.setConnectTimeout(3000000);

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

			// ------------------------------------------------------------------ llama el servicio

			DataOutputStream request = new DataOutputStream(urlConn.getOutputStream());

			request.writeBytes(StringUtils.trimToEmpty(content));

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

			int responseCode = urlConn.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP || responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
				String location = urlConn.getHeaderField("Location");
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

			String resp = new String(bytes);

			return resp;

		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
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
		}
		// System.out.println();
		// System.out.println("callJSON URL: " + url);
		// System.out.println("callJSON Content:" + content);

		String resp = call(url, content, headers, method);

		// System.out.println("callJSON Resp:" + resp);
		// System.out.println();

		return new Gson().fromJson(resp, clazz);
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
