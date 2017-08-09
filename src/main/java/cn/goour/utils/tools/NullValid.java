package cn.goour.utils.tools;

/**
 * 判断字符串是否为空，null或者去掉连续空白后为空字符串的返回true，
 * 如果数字等于0返回true
 * 
 * @author HouKunLin
 *
 */
public class NullValid {
	/**
	 * null，空字符串，去除连续空白依旧是空字符串，以上条件返回真
	 * 
	 * @param arg0
	 * @return
	 */
	public static boolean isNull(String arg0) {
		if (arg0 == null) {
			return true;
		}
		return arg0.trim().equals("");
	}

	/**
	 * 判断传入值是否为0，为0返回真
	 * 
	 * @param arg0
	 * @return
	 */
	public static boolean isNull(int arg0) {
		if (arg0 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断传入值是否为0，为0返回真
	 * 
	 * @param arg0
	 * @return
	 */
	public static boolean isNull(long arg0) {
		if (arg0 == 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 判断传入的对象是否为空指针
	 * @param arg0
	 * @return
	 */
	public static boolean isNull(Object arg0) {
		if (arg0 == null) {
			return true;
		} else {
			return false;
		}
	}
}
