/**   
 * Copyright © 2018 guopeng. All rights reserved.
 * 
 * @Title: RsaUtil.java 
 * @Prject: rest-api-client
 * @Package: com.kakuhou.utils 
 * @author: guopeng   
 * @date: 2018年5月8日 下午2:03:41 
 * @version: V1.0   
 */
package com.kakuhou.utils;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import lombok.extern.slf4j.Slf4j;

/**
 * rsa加解密工具
 * 
 * @author: guopeng
 * @date: 2018年5月8日
 * @title rsa加解密工具
 */
@Slf4j
public class RsaUtil {
	private static String RSA = "RSA";
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 随机生成RSA密钥对(默认密钥长度为1024)
	 * 
	 * @return
	 */
	public static KeyPair generateRSAKeyPair() {
		return generateRSAKeyPair(1024);
	}

	/**
	 * 随机生成RSA密钥对
	 * 
	 * @param keyLength
	 *            密钥长度，范围：512～2048<br>
	 *            一般1024
	 * @return
	 */
	public static KeyPair generateRSAKeyPair(int keyLength) {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
			kpg.initialize(keyLength);
			return kpg.genKeyPair();
		} catch (NoSuchAlgorithmException e) {
			log.error("error", e);
			return null;
		}
	}

	/**
	 * 从字符串中加载公钥
	 * 
	 * @param publicKeyStr
	 *            公钥数据字符串
	 * @throws Exception
	 *             加载公钥时产生的异常
	 */
	public static PublicKey loadPublicKey(String publicKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Utils.decode(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			log.error("error", e);
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			log.error("error", e);
			throw new Exception("公钥非法");
		} catch (NullPointerException e) {
			log.error("error", e);
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 从字符串中加载私钥<br>
	 * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
	 * 
	 * @param privateKeyStr
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
		try {
			byte[] buffer = Base64Utils.decode(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance(RSA);
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			log.error("error", e);
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			log.error("error", e);
			throw new Exception("私钥非法");
		} catch (NullPointerException e) {
			log.error("error", e);
			throw new Exception("私钥数据为空");
		}
	}

	/**
	 * <p>
	 * 公钥分段加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * <p>
	 * 公钥分段解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param publicKey
	 *            公钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] encryptedData, PublicKey publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * <p>
	 * 私钥分段加密
	 * </p>
	 * 
	 * @param data
	 *            源数据
	 * @param privateKey
	 *            私钥(BASE64编码)
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptedData;
	}

	/**
	 * <P>
	 * 私钥分段解密
	 * </p>
	 * 
	 * @param encryptedData
	 *            已加密数据
	 * @param privateKey
	 *            私钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] encryptedData, PrivateKey privateKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(RSA);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		return decryptedData;
	}

	/**
	 * @Description: RSA-privateKey-签名
	 * @author
	 * @param privateKey
	 * @param content
	 * @return 返回字节数组
	 * @throws Exception
	 */
	public static byte[] sign(PrivateKey privateKey, byte[] content) throws Exception {
		try {
			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initSign(privateKey);
			signature.update(content);
			byte[] signResult = signature.sign();
			return signResult;
		} catch (NoSuchAlgorithmException e) {
			log.error("error", e);
			throw new Exception("RSA-privateKey-签名异常");
		}
	}

	/**
	 * @Description: RSA-privateKey-签名
	 * @author
	 * @param privateKey
	 * @param contentBase64
	 *            【验证签名原文-base64编码字符串】
	 * @return 返回base64字符串
	 * @throws Exception
	 */
	public static String signBase64(RSAPrivateKey privateKey, String contentBase64) throws Exception {
		byte[] content = Base64Utils.decode(contentBase64);
		byte[] signResult = sign(privateKey, content);
		return Base64Utils.encode(signResult);
	}

	/**
	 * @Description: RSA-publicKey-验证签名
	 * @author
	 * @param publicKey
	 * @param content
	 *            【签名原文-字节数组】
	 * @param sign
	 *            【待验证签名-字节数组】
	 * @return 签名结果
	 * @throws Exception
	 */
	public static boolean verify(PublicKey publicKey, byte[] content, byte[] sign) throws Exception {
		try {
			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initVerify(publicKey);
			signature.update(content);
			return signature.verify(sign);
		} catch (NoSuchAlgorithmException e) {
			log.error("error", e);
			throw new Exception("RSA-publicKey-验证签名异常");
		}
	}

	/**
	 * @Description: RSA-publicKey-验证签名
	 * @author
	 * @param publicKey
	 * @param contentBase64
	 *            【签名原文-base64编码字符串】
	 * @param signBase64
	 *            【待验证签名-base64编码字符串】
	 * @return 签名结果
	 * @throws Exception
	 */
	public static boolean verifyBase64(PublicKey publicKey, String contentBase64, String signBase64) throws Exception {
		byte[] content = Base64Utils.decode(contentBase64);
		byte[] sign = Base64Utils.decode(signBase64);
		return verify(publicKey, content, sign);
	}
}
