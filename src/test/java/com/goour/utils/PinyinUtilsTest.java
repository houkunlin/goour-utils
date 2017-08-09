package com.goour.utils;

import cn.goour.utils.tools.PinyinUtils;

public class PinyinUtilsTest {
	public static void main(String[] args) {

		/*
		 * 测试用例 $start_time = microtime(1);
		 * 
		 * var_dump(pinyin('对多音字无能为力'));
		 * var_dump(pinyin('最全的PHP汉字转拼音库，比百度词典还全（dict.baidu.com）'));
		 * var_dump(pinyin('试试：㐀㐁㐄㐅㐆㐌㐖㐜')); var_dump(pinyin('一起开始数：12345'));
		 * var_dump(pinyin('海南')); var_dump(pinyin('乌鲁木齐'));
		 * var_dump(pinyin('前总理朱镕基')); var_dump(pinyin('仅首字母', 'first'));
		 * var_dump(pinyin('占-位-符-为-空', 'all', ''));
		 * var_dump(pinyin('不允许中文以外的字符', 'first', '', ''));
		 * 
		 * for ($i=0; $i<1e4; $i++) { // 性能次数，转换1万次 pinyin('对多音字无能为力');
		 * pinyin('最全的PHP汉字转拼音库，比百度词典还全（dict.baidu.com）');
		 * pinyin('试试：㐀㐁㐄㐅㐆㐌㐖㐜'); pinyin('一起开始数：12345'); pinyin('海南');
		 * pinyin('乌鲁木齐'); pinyin('前总理朱镕基'); pinyin('仅首字母', 'first');
		 * pinyin('占-位-符-为-空', 'all', ''); pinyin('不允许中文以外的字符', 'first', '',
		 * ''); }
		 * 
		 * echo number_format(microtime(1) - $start_time, 6);
		 */
		long start = System.currentTimeMillis();
		System.out.println("开始时间：" + start);

		System.out.println(PinyinUtils.pinyin("对多音字无能为力"));
		// System.out.println(PinyinUtils.pinyin("最全的PHP汉字转拼音库，比百度词典还全（dict.baidu.com）"));
		// System.out.println(PinyinUtils.pinyin("试试：㐀㐁㐄㐅㐆㐌㐖㐜"));
		// System.out.println(PinyinUtils.pinyin("一起开始数：12345"));
		// System.out.println(PinyinUtils.pinyin("海南"));
		// System.out.println(PinyinUtils.pinyin("乌鲁木齐"));
		// System.out.println(PinyinUtils.pinyin("前总理朱镕基"));
		// System.out.println(PinyinUtils.pinyin("仅首字母","first"));
		// System.out.println(PinyinUtils.pinyin("占-位-符-为-空","all",""));
		// System.out.println(PinyinUtils.pinyin("不允许中文以外的字符","first","",""));

//		for (int i = 0; i < 10000; i++) { // 性能次数，转换1万次 pinyin('对多音字无能为力');
//			System.out.println(i);
//			PinyinUtils.pinyin("最全的PHP汉字转拼音库，比百度词典还全（dict.baidu.com）");
//			PinyinUtils.pinyin("试试：㐀㐁㐄㐅㐆㐌㐖㐜");
//			PinyinUtils.pinyin("一起开始数：12345");
//			PinyinUtils.pinyin("海南");
//			PinyinUtils.pinyin("乌鲁木齐");
//			PinyinUtils.pinyin("前总理朱镕基");
//			PinyinUtils.pinyin("仅首字母", "first");
//			PinyinUtils.pinyin("占-位-符-为-空", "all", "");
//			PinyinUtils.pinyin("不允许中文以外的字符", "first", "", "");
//		}

		long end = System.currentTimeMillis();
		System.out.println("结束时间：" + end);
		System.out.println("耗费时间：" + (end - start) / 1000.0 + "s");
	}
}
