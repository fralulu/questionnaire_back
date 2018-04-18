package com.infore.common.util;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;

public class CodeEncrypt {

	/**
	 * support MD2/MD5/SHA/SHA256/SHA384/SHA512
	 */
	public static final String KEY_SHA = "SHA";
	public static final String KEY_MD5 = "MD5";
	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 *  
	 * HmacMD5  
	 * HmacSHA1  
	 * HmacSHA256  
	 * HmacSHA384  
	 * HmacSHA512
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


	/**
	 * 根据给定摘要算法创建一个消息摘要实例
	 *
	 * @param algorithm
	 *            摘要算法名
	 * @return 消息摘要实例
	 * @see MessageDigest#getInstance(String)
	 * @throws RuntimeException
	 *             当 {@link java.security.NoSuchAlgorithmException} 发生时
	 */
	public static MessageDigest getDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) {
        return Base64.getDecoder().decode(key);
    }
    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptBASE64S(String key) {
        return new String(Base64.getDecoder().decode(key));
    }

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(String key) {
        byte[] b = null;
        b = key.getBytes();
		return Base64.getEncoder().encodeToString(b);
	}

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
	public static String encryptBASE64(byte[] key) {
		return Base64.getEncoder().encodeToString(key);
    }


	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptMD5(String data) {
		if (StringUtils.isBlank(data)) {
			return "";
		}
		MessageDigest md5 = getDigest(KEY_MD5);
		md5.update(data.getBytes());
		return byteArrayToHexString(md5.digest());

	}

	/**
	 * SHA加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptSHA(String data) {
		if (StringUtils.isBlank(data)) {
			return "";
		}
		MessageDigest sha = getDigest(KEY_SHA);
		sha.update(data.getBytes());
		return byteArrayToHexString(sha.digest());

	}

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() {
		KeyGenerator keyGenerator = null;
		try {
			keyGenerator = KeyGenerator.getInstance(KEY_MAC);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) {
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = null;
		try {
			mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new RuntimeException(e.getMessage());
		}
		return mac.doFinal(data);
	}

	/**
	 * 将一个字节转化成十六进制形式的字符串
	 * @param b 字节数组
	 * @return 字符串
	 */
	private static String byteToHexString(byte b) {
		int ret = b;
		if (ret < 0) {
			ret += 256;
		}
		int m = ret / 16;
		int n = ret % 16;
		return hexDigits[m] + hexDigits[n];
	}

	/**
	 * 转换字节数组为十六进制字符串
	 * @param bytes 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(byteToHexString(bytes[i]));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String s = CodeEncrypt.encryptSHA("123");
		String s1 = CodeEncrypt.encryptSHA("2347777fgddfgGSDFDS%%@");
		System.out.println("----s--"+s);
		System.out.println("----s1-"+s1);

        String rs = "[{\"type\":\"record\",\"tm\":[{\"beg\":\"14:00\",\"end\":\"16:00\"}]},{\"type\":\"patrol\",\"tm\":[{\"beg\":\"14:00\",\"end\":\"16:00\"}]}]";
        System.out.println(CodeEncrypt.encryptBASE64(rs));

        String rrr = "W3sidHlwZSI6InJlY29yZCIsInRtIjpbeyJiZWciOiIxNDowMCIsImVuZCI6IjE2OjAwIn1dfSx7InR5cGUiOiJwYXRyb2wiLCJ0bSI6W3siYmVnIjoiMTQ6MDAiLCJlbmQiOiIxNjowMCJ9XX1d";
        System.out.println(new String(CodeEncrypt.decryptBASE64(rrr)));

        String md5 = "register192.168.31.1318080StreamerRGJWc1lXSnBZVzF6YzI4eE1qTTBOUSUzRUbkh";

        System.out.println(CodeEncrypt.encryptMD5(md5));

	}
}
