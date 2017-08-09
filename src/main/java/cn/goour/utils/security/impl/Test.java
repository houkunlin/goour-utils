package cn.goour.utils.security.impl;

public class Test {
	public static void main(String[] args) {
		try {
			String data = "我是HouKunLin.";
			String key = "1234567890123456";
			String re = null;
			re=AES.getInstance().EncryptionToString(data,key);
			System.out.println("AESenKey:"+re);
			re=AES.getInstance().DecryptionToString(re, key);
			System.out.println("AESdeKey:"+re);
			System.out.println();
			
			re=AES.getInstance().EncryptionToString(data);
			System.out.println("AESen:"+re);
			re=AES.getInstance().DecryptionToString(re);
			System.out.println("AESde:"+re);
			System.out.println();
			System.out.println();
			
			
			re=Base64.getInstance().EncryptionToString(data);
			System.out.println("Base64en:"+re);
			re=Base64.getInstance().DecryptionToString(re);
			System.out.println("Base64de:"+re); 
			System.out.println();
			System.out.println();
			
			
			re=MD5Impl.getInstance().EncryptionToString(data);
			System.out.println("MD5en:"+re);
			re=MD5Impl.getInstance().DecryptionToString(re);
			System.out.println("MD5de:"+re);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
