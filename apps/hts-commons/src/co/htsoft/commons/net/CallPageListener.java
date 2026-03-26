package co.htsoft.commons.net;

import java.util.Map;

public interface CallPageListener {
	public void call(String method, String url, Map<String, String> requestHeaders, byte[] requestData, boolean isTextRequest, int responseCode, byte[] responseData, boolean isTextResponse, long totalTime);
}
