package cn.goour.utils.security.impl;
import java.math.BigInteger;
import java.security.MessageDigest;

import cn.goour.utils.security.IEncryptNoKey;

public class MD5Impl implements IEncryptNoKey
{
	private static MD5Impl instance=null;
	public static synchronized MD5Impl getInstance(){
		if (instance == null) {
			synchronized (MD5Impl.class) {
				if (instance == null) {
					instance = new MD5Impl();
				}
			}
		}
		return instance;
	}
	public byte[] Encryption(byte[] data) throws Exception
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		return md.digest(data);
	}

	public byte[] Encryption(String data) throws Exception
	{
		return Encryption(data.getBytes("UTF-8"));
	}

	public String EncryptionToString(byte[] data) throws Exception
	{
		return new BigInteger(1,Encryption(data)).toString(16);
	}

	public String EncryptionToString(String data) throws Exception
	{
		return new BigInteger(1,Encryption(data)).toString(16);
	}

	public byte[] Decryption(byte[] data) throws Exception
	{
		return null;
	}

	public byte[] Decryption(String data) throws Exception
	{
		return null;
	}

	public String DecryptionToString(byte[] data) throws Exception
	{
		return null;
	}

	public String DecryptionToString(String data) throws Exception
	{
		return null;
	}


}
