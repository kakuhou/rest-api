package com.kakuhou.client;

import java.lang.reflect.Type;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kakuhou.base.Msg;
import com.kakuhou.base.Rq;
import com.kakuhou.base.Rt;
import com.kakuhou.utils.Base64Utils;
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
		Msg reMsg = doHttp(msg, clientId);
		verify(reMsg);
		return Msg2Rt(reMsg);
	}

	private Msg doHttp(Msg msg, String clientId) throws Exception {
		return null;
	}

	private void verify(Msg msg) throws Exception {
		if (!RsaUtil.verifyBase64(publicKey, msg.getData(), msg.getSign())) {
			throw new SignatureException("验签失败");
		}
	}

	private <T> Rt<T> Msg2Rt(Msg msg) throws Exception {
		String json = new String(RsaUtil.decryptByPrivateKey(Base64Utils.decode(msg.getData()), privateKey));
		Type type = new TypeToken<T>() {
		}.getType();
		return gson.fromJson(json, type);
	}
}
