package com.goour.utils;

import cn.goour.utils.Regex.RegexUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RegexUtilsTest extends TestCase {
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RegexUtilsTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RegexUtilsTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
        String account = "houkunlin";
        String carNo = "桂B25135";
        String charNumber = "houu1324";
        String chinese = "我是汉字";
        String domain = "goour.cn";
        String email = "1184511588@qq.com";
        String ip = "139.129.41.145";
        String number = "123456789";
        String password = "Acvfff1251";
        String phoneNum = "15577405667";
        String qq = "1184511588";
        String url = "http://goour.cn/server.php";
        System.out.println("用户名\t\t:"+RegexUtils.isValidAccount(account));
        System.out.println("用户名(3-10)\t:"+RegexUtils.isValidAccount(account,3,8));
        System.out.println("车牌号\t\t:"+RegexUtils.isValidCarNo(carNo));
        System.out.println("字母和汉字\t:"+RegexUtils.isValidCharNumber(charNumber));
        System.out.println("全是汉字\t\t:"+RegexUtils.isValidChinese(chinese));
        System.out.println("域名\t\t:"+RegexUtils.isValidDomain(domain));
        System.out.println("电子邮件校验\t:"+RegexUtils.isValidEmail(email));
        System.out.println("IP\t\t:"+RegexUtils.isValidIP(ip));
        System.out.println("全是数字\t\t:"+RegexUtils.isValidNumber(number));
        System.out.println("密码\t\t:"+RegexUtils.isValidPassword(password));
        System.out.println("密码(3-10)\t:"+RegexUtils.isValidPassword(password,3,10));
        System.out.println("强密码\t\t:"+RegexUtils.isValidPasswordStrong(password));
        System.out.println("强密码(3-10)\t:"+RegexUtils.isValidPasswordStrong(password,3,10));
        System.out.println("手机号\t\t:"+RegexUtils.isValidPhoneNum(phoneNum));
        System.out.println("QQ\t\t:"+RegexUtils.isValidQQ(qq));
        System.out.println("url地址\t\t:"+RegexUtils.isValidUrl(url));
        
    }
}
