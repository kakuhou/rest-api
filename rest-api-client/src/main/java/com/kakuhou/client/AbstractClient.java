package com.kakuhou.client;

import java.lang.reflect.Type;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.kakuhou.base.Msg;
import com.kakuhou.base.Rq;
import com.kakuhou.base.Rt;
import com.kakuhou.base.Url;
import com.kakuhou.utils.Base64Utils;
import com.kakuhou.utils.HttpUtil;
import com.kakuhou.utils.RsaUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractClient {

	private static Gson gson = new Gson();

	private PublicKey publicKey;

	private PrivateKey privateKey;

	private String clientId;

	private String domain;

	public abstract Client initClient(String domain, PublicKey publicKey, PrivateKey privateKey, String clientId);

	public <T> Rt<T> doRequest(Rq rq) throws Exception {
		String body = gson.toJson(rq);
		Msg msg = new Msg();
		msg.setData(Base64Utils.encode(RsaUtil.encryptByPublicKey(body.getBytes(), publicKey)));
		msg.setSign(RsaUtil.signBase64(privateKey, msg.getData()));
		Url url = rq.getClass().getAnnotation(Url.class);
		Msg reMsg = doHttp(domain + url.value(), msg, clientId);
		verify(reMsg);
		return Msg2Rt(reMsg);
	}

	private Msg doHttp(String url, Msg msg, String clientId) throws Exception {
		Map<String, String> params = new HashMap<>();
		params.put("data", msg.getData());
		params.put("sign", msg.getSign());
		Map<String, String> headers = new HashMap<>();
		headers.put("clientId", clientId);
		return gson.fromJson(HttpUtil.doPost(params, headers, url), Msg.class);
	}

	private void verify(Msg msg) throws Exception {
		if (!RsaUtil.verifyBase64(publicKey, msg.getData(), msg.getSign())) {
			throw new SignatureException("验签失败");
		}
	}

	@SuppressWarnings("serial")
	private <T> Rt<T> Msg2Rt(Msg msg) throws Exception {
		String json = new String(RsaUtil.decryptByPrivateKey(Base64Utils.decode(msg.getData()), privateKey));
		Type type2 = new TypeToken<Rt<T>>() {}.getType();
		return gson.fromJson(json, type2);
	}
}
