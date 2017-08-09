package cn.goour.utils.tools;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.goour.utils.io.FileUtils;

/**
 * java 汉字转拼音 [包含20902个基本汉字+5059生僻字]
 * 
 * @author 
 * @version v1.2
 */
public class PinyinUtils {
	private static String data;
	private static HashMap<String, String> pinyins;
	static{
		data = "";
		try {
			String path = PinyinUtils.class.getClassLoader().getResource("Pinyin.db").getFile();
			data = FileUtils.read(path, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
public static void main(String[] args) {
	
}
	/**
	 * 解析汉字拼音
	 * 
	 * @param str
	 *            UTF8字符串
	 * @return 拼音字符串
	 */
	public static String pinyin(String str) {
		return pinyin(str, "all", "_", "[a-zA-Z\\d]");
	}

	/**
	 * 解析汉字拼音
	 * 
	 * @param str
	 *            UTF8字符串
	 * @param ret_format
	 *            返回格式 [all:全拼音|first:首字母|one:仅第一字符首字母]
	 * @return 拼音字符串
	 */
	public static String pinyin(String str, String ret_format) {
		return pinyin(str, ret_format, "_", "[a-zA-Z\\d]");
	}

	/**
	 * 解析汉字拼音
	 * 
	 * @param str
	 *            UTF8字符串
	 * @param ret_format
	 *            返回格式 [all:全拼音|first:首字母|one:仅第一字符首字母]
	 * @param placeholder
	 *            无法识别的字符占位符
	 * @return 拼音字符串
	 */
	public static String pinyin(String str, String ret_format, String placeholder) {
		return pinyin(str, ret_format, placeholder, "[a-zA-Z\\d]");
	}

	/**
	 * 解析汉字拼音
	 * 
	 * @param str
	 *            UTF8字符串
	 * @param ret_format
	 *            返回格式 [all:全拼音|first:首字母|one:仅第一字符首字母]
	 * @param placeholder
	 *            无法识别的字符占位符
	 * @param allow_chars
	 *            允许的非中文字符
	 * @return 拼音字符串
	 */
	public static String pinyin(String str, String ret_format, String placeholder, String allow_chars) {
		pinyins = null;
		if (null == pinyins) {
	
			String[] rows = data.split("\\|");
			pinyins = new HashMap<String, String>();
			for (String v : rows) {
				String py = null;
				String vals = null;
				String[] the = v.split(":");
				
				try {
					py = the[0];
					vals = the[1];
				} catch (Exception e) {
				}
				if (vals != null) {
					String[] chars = vals.split(",");
					for (String string : chars) {
						pinyins.put(string, py);
					}
				}
				
			}
		}
		str = str.trim();
		int len = str.length();
		StringBuffer rs = new StringBuffer();
		Pattern pattern = Pattern.compile(allow_chars);
		for (int i = 0; i < len; i++) {
			String chr = str.substring(i, i+1);
			if (chr.matches("^[\u4e00-\u9fa5]+$")) {//如果是汉字
				String re = pinyins.get(chr);
				if (re != null) {
					if (ret_format.equals("first")) {
						rs.append(re);
					} else {
						rs.append(re + " ");
					}
				} else {
					rs.append(placeholder);
				}
			}else{
				Matcher matcher = pattern.matcher(chr);
				if (matcher.find()) { // 用参数控制正则
					rs.append(chr);
				} else { // 其他字符用填充符代替
					rs.append(placeholder);
				}
			}
			if (ret_format.equals("one") && !rs.toString().equals("")) {
				return rs.substring(0, 1);
			}
		}
		return rs.toString();
	}
	
}
