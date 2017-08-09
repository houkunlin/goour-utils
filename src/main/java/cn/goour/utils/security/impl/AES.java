package cn.goour.utils.security.impl;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import cn.goour.utils.security.AEncrypt;

/**
 * @author AES加密的密钥必须是16位
 *
 */
public class AES extends AEncrypt {
	private byte[] defaultKey = "Java Very Great!".getBytes();//AES加密的密钥必须是16位
	private static AEncrypt instance = null;

	public static synchronized AEncrypt getInstance() {
		if (instance == null) {
			synchronized (AES.class) {
				if (instance == null) {
					instance = new AES();
				}
			}
		}
		return instance;
	}

	@Override
	public byte[] EncryptionToByte(byte[] data, byte[] key) throws Exception {
		SecretKeySpec skey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skey);
		return cipher.doFinal(data);
	}

	@Override
	public byte[] DecryptionToByte(byte[] data, byte[] key) throws Exception {
		SecretKeySpec skey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skey);
		return cipher.doFinal(data);
	}

	@Override
	public byte[] EncryptionToByte(byte[] data) throws Exception {
		SecretKeySpec skey = new SecretKeySpec(defaultKey, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skey);
		return cipher.doFinal(data);
	}

	@Override
	public byte[] DecryptionToByte(byte[] data) throws Exception {
		SecretKeySpec skey = new SecretKeySpec(defaultKey, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skey);
		return cipher.doFinal(data);
	}
}
