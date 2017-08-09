package cn.goour.utils.tools;

/**
 * 将字符串转为数字类型的信息，如果转换失败则返回0
 * 
 * @author HouKunLin
 *
 */
public class String2Number {
	public static int parseInt(String num) {
		int re = 0;
		try {
			re = Integer.parseInt(num);
		} catch (Exception e) {
		}
		return re;
	}

	public static long parseLong(String num) {
		long re = 0;
		try {
			re = Long.parseLong(num);
		} catch (Exception e) {
		}
		return re;
	}

	public static float parseFloat(String num) {
		float re = 0;
		try {
			re = Float.parseFloat(num);
		} catch (Exception e) {
		}
		return re;
	}

	public static double parseDouble(String num) {
		double re = 0;
		try {
			re = Double.parseDouble(num);
		} catch (Exception e) {
		}
		return re;
	}
}
