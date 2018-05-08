package com.kakuhou.client;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.kakuhou.client.impl.RightClientImpl;
import com.kakuhou.utils.RsaUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientFactory {

	private static PublicKey publicKey;

	private static PrivateKey privateKey;

	private static String clientId;

	public static void init(String clientId, String privateKey, String publicKey) {
		try {
			ClientFactory.publicKey = RsaUtil.loadPublicKey(publicKey);
			ClientFactory.privateKey = RsaUtil.loadPrivateKey(privateKey);
			ClientFactory.clientId = clientId;
		} catch (Exception e) {
			log.error("init error", e);
			throw new RuntimeException(e);
		}

	}

	private static class clinetHolder {
		private static RightClient rightClient = (RightClient) new RightClientImpl().initClient(publicKey, privateKey,
				clientId);
	}

	public static RightClient getRightClient() {
		return clinetHolder.rightClient;
	}
}
