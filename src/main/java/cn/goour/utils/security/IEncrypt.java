package cn.goour.utils.security;

/**
 * @author 数据加密解密接口，实现了IEncrypt接口
 *
 */
public interface IEncrypt {
	/**
	 * @param data 加密内容，byte[]类型
	 * @param key 加密密钥，byte[]类型
	 * @return 返回密文，byte[]类型
	 * @throws Exception 抛出错误信息
	 */
	public byte[] EncryptionToByte(byte[] data, byte[] key) throws Exception;

	/**
	 * @param data 加密内容，String类型
	 * @param key 加密密钥，String类型
	 * @return 返回密文，byte[]类型
	 * @throws Exception 抛出错误信息
	 */
	public byte[] EncryptionToByte(String data, String key) throws Exception;

	/**
	 * @param data 加密内容，byte[]类型
	 * @param key 加密密钥，byte[]类型
	 * @return 返回十六进制密文，String类型
	 * @throws Exception 抛出错误信息
	 */
	public String EncryptionToString(byte[] data, byte[] key) throws Exception;

	/**
	 * @param data 加密内容，String类型
	 * @param key 加密密钥，String类型
	 * @return 返回十六进制密文，String类型
	 * @throws Exception 抛出错误信息
	 */
	public String EncryptionToString(String data, String key) throws Exception;

	/**
	 * @param data 解密密文，byte[]类型
	 * @param key 解密密钥，byte[]类型
	 * @return 返回明文，byte[]类型
	 * @throws Exception
	 */
	public byte[] DecryptionToByte(byte[] data, byte[] key) throws Exception;

	/**
	 * @param data 十六进制解密密文，String类型
	 * @param key 解密密钥，String类型
	 * @return 返回明文，byte[]类型
	 * @throws Exception
	 */
	public byte[] DecryptionToByte(String data, String key) throws Exception;

	/**
	 * @param data 解密密文，byte[]类型
	 * @param key 解密密钥，byte[]类型
	 * @return 返回明文，String类型
	 * @throws Exception
	 */
	public String DecryptionToString(byte[] data, byte[] key) throws Exception;

	/**
	 * @param data 十六进制解密密文，String类型
	 * @param key 解密密钥，String类型
	 * @return 返回明文，String类型
	 * @throws Exception
	 */
	public String DecryptionToString(String data, String key) throws Exception;

	/**
	 * @param data 加密内容，byte[]类型
	 * @return 返回密文，byte[]类型
	 * @throws Exception
	 */
	public byte[] EncryptionToByte(byte[] data) throws Exception;

	/**
	 * @param data 加密内容，String类型
	 * @return 返回密文，byte[]类型
	 * @throws Exception
	 */
	public byte[] EncryptionToByte(String data) throws Exception;

	/**
	 * @param data 加密内容，byte[]类型
	 * @return 返回十六进制密文，String类型
	 * @throws Exception
	 */
	public String EncryptionToString(byte[] data) throws Exception;

	/**
	 * @param data 加密内容，String类型
	 * @return 返回十六进制密文，String类型
	 * @throws Exception
	 */
	public String EncryptionToString(String data) throws Exception;

	/**
	 * @param data 解密密文，byte[]类型
	 * @return 返回明文，byte[]类型
	 * @throws Exception
	 */
	public byte[] DecryptionToByte(byte[] data) throws Exception;

	/**
	 * @param data 十六进制解密密文，String类型
	 * @return 返回明文，byte[]类型
	 * @throws Exception
	 */
	public byte[] DecryptionToByte(String data) throws Exception;

	/**
	 * @param data 解密密文，byte[]类型
	 * @return 返回明文，String类型
	 * @throws Exception
	 */
	public String DecryptionToString(byte[] data) throws Exception;

	/**
	 * @param data 十六进制解密密文，String类型
	 * @return 返回明文，String类型
	 * @throws Exception
	 */
	public String DecryptionToString(String data) throws Exception;
}
