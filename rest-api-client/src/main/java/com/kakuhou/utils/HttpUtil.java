package com.kakuhou.utils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

	private static final long DEFAULT_TIMEOUT = 60L;
	private static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
			.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();

	public static String doPost(Map<String, String> params, Map<String, String> headers, String url)
			throws IOException {
		Request request = new Request.Builder().url(url).headers(buildHeaders(headers)).post(buildRequestBody(params))
				.build();
		Response response = client.newCall(request).execute();
		String result = new String(response.body().bytes());
		System.out.println(result);
		return result;
	}

	private static Headers buildHeaders(Map<String, String> headers) {
		Headers.Builder builder = new Headers.Builder();
		headers.forEach((k, v) -> {
			builder.add(k, v);
		});
		return builder.build();
	}

	private static RequestBody buildRequestBody(Map<String, String> params) {
		FormBody.Builder builder = new FormBody.Builder();
		params.forEach((k, v) -> {
			builder.add(k, v);
		});
		return builder.build();
	}
}
