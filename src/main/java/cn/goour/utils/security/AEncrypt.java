package cn.goour.utils.security;

import java.math.BigInteger;

/**
 * @author 数据加密解密部分实现的抽象类
 *
 */
public abstract class AEncrypt implements IEncrypt{
	public abstract byte[] EncryptionToByte(byte[] data, byte[] key) throws Exception;

	public byte[] EncryptionToByte(String data, String key) throws Exception {
		return EncryptionToByte(data.getBytes("UTF-8"),key.getBytes("UTF-8"));
	}

	public String EncryptionToString(byte[] data, byte[] key) throws Exception {
		return byteArrayToHexStr(EncryptionToByte(data,key));
	}

	public String EncryptionToString(String data, String key) throws Exception {
		return byteArrayToHexStr(EncryptionToByte(data,key));
	}

	public abstract byte[] DecryptionToByte(byte[] data, byte[] key) throws Exception;

	public byte[] DecryptionToByte(String HexData, String key) throws Exception {
		return DecryptionToByte(HexStrToByteArray(HexData),key.getBytes("UTF-8"));
	}

	public String DecryptionToString(byte[] data, byte[] key) throws Exception {
		return new String(DecryptionToByte(data, key));
	}

	public String DecryptionToString(String HexData, String key) throws Exception {
		return new String(DecryptionToByte(HexData, key));
	}
	public static String byteArrayToHexStr(byte[] bytes){
		return new BigInteger(1, bytes).toString(16);
	}
	public static byte[] HexStrToByteArray(String hexstr)
	{
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}
	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}
	

	public abstract byte[] EncryptionToByte(byte[] data) throws Exception;

	public byte[] EncryptionToByte(String data) throws Exception {
		return EncryptionToByte(data.getBytes("UTF-8"));
	}

	public String EncryptionToString(byte[] data) throws Exception {
		return byteArrayToHexStr(EncryptionToByte(data));
	}

	public String EncryptionToString(String data) throws Exception {
		return byteArrayToHexStr(EncryptionToByte(data));
	}

	public abstract byte[] DecryptionToByte(byte[] data) throws Exception;

	public byte[] DecryptionToByte(String HexData) throws Exception {
		return DecryptionToByte(HexStrToByteArray(HexData));
	}

	public String DecryptionToString(byte[] data) throws Exception {
		return new String(DecryptionToByte(data));
	}

	public String DecryptionToString(String HexData) throws Exception {
		return new String(DecryptionToByte(HexData));
	}
}
