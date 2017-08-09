package cn.goour.utils.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	/**
	 * 传入正则表达式和字符串，判断是否匹配
	 * 
	 * @param regex 正则表达式
	 * @param regexStr 欲匹配的字符串
	 * @return
	 */
	public static boolean isValid(String regex,String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	/**
	 * 电子邮件
	 * 
	 * @param regexStr
	 * @return 验证结果
	 */
	public static boolean isValidEmail(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 手机号
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidPhoneNum(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^((13[0-9])|(147)|(15[^4,\\D])|(17[0-9])|(18[0,0-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 车牌号
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidCarNo(String regexStr) {
		//^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$
		//^[A-Za-z]{1}[A-Za-z_0-9]{5}$
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 网址
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidUrl(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^((http)|(https))+:[^\\s]+\\.[^\\s]*$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 邮政编码
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidPostalcode(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[0-8]\\d{5}(?!\\d)$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 纯汉字
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidChinese(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]+$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * ip
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidIP(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 验证是否是一个网段信息，如：192.168.199.1/24
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidIPNetWork(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})/(\\d{1,2})$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	/**
	 * 全是数字
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidNumber(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[0-9]*$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 全是字母和数字
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidCharNumber(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 域名
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidDomain(String regexStr) {
		//^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?$
		//^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(/.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+/.?
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 用户名是否合法。<br>
	 * 帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidAccount(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{4,15}$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 用户名是否合法。<br>
	 * 帐号是否合法(字母开头，允许lenMin到lenMax字节，允许字母数字下划线)
	 * 
	 * @param regexStr
	 * @param lenMin
	 *            最小长度
	 * @param lenMax
	 *            最大长度
	 * @return
	 */
	public static boolean isValidAccount(String regexStr, int lenMin, int lenMax) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(String.format("^[a-zA-Z][a-zA-Z0-9_]{%d,%d}$", lenMin, lenMax));
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 密码是否合法。<br>
	 * 密码(以字母开头，长度在6~18之间，只能包含字母、数字和下划线)
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidPassword(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[a-zA-Z]\\w{5,17}$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 密码是否合法。<br>
	 * 密码(以字母开头，长度在lenMin到lenMax之间，只能包含字母、数字和下划线)
	 * 
	 * @param regexStr
	 * @param lenMin
	 *            最小长度
	 * @param lenMax
	 *            最大长度
	 * @return
	 */
	public static boolean isValidPassword(String regexStr, int lenMin, int lenMax) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(String.format("^[a-zA-Z]\\w{%d,%d}$", lenMin, lenMax));
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 强密码。<br>
	 * 强密码(必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间)
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidPasswordStrong(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 强密码。<br>
	 * 强密码(必须包含大小写字母和数字的组合，不能使用特殊字符，长度在lenMin到lenMax之间)
	 * 
	 * @param regexStr
	 * @param lenMin
	 *            最小长度
	 * @param lenMax
	 *            最大长度
	 * @return
	 */
	public static boolean isValidPasswordStrong(String regexStr, int lenMin, int lenMax) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(String.format("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{%d,%d}$", lenMin, lenMax));
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 腾讯QQ
	 * 
	 * @param regexStr
	 * @return
	 */
	public static boolean isValidQQ(String regexStr) {
		if (regexStr == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[1-9][0-9]{4,}$");
		Matcher matcher = pattern.matcher(regexStr);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		String r = "192.168.199.1/0";
		System.out.println(isValidIP(r));
		Pattern pattern = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})/(\\d{1,2})$");
		Matcher matcher = pattern.matcher(r);
		if (matcher.find()) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				System.out.println(matcher.group(i));
			}
		}
	}
}
