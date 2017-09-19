package cn.goour.utils.http;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.goour.utils.tools.GoourLinkMap;

public class HttpConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String UA_PHONE_UC_BASE = "User-Agent, NOKIA5700/ UCWEB7.0.2.37/28/999";
	public static final String UA_PHONE_Windows_Phone_Mango = "User-Agent, Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0; HTC; Titan)";
	public static final String UA_PHONE_Nokia_N97 = "User-Agent, Mozilla/5.0 (SymbianOS/9.4; Series60/5.0 NokiaN97-1/20.0.019; Profile/MIDP-2.1 Configuration/CLDC-1.1) AppleWebKit/525 (KHTML, like Gecko) BrowserNG/7.1.18124";
	public static final String UA_PHONE_Android_Opera_Mobile = "User-Agent, Opera/9.80 (Android 2.3.4; Linux; Opera Mobi/build-1107180945; U; en-GB) Presto/2.8.149 Version/11.10";
	public static final String UA_PHONE_Android_MQQBrowser = "User-Agent, MQQBrowser/26 Mozilla/5.0 (Linux; U; Android 2.3.7; zh-cn; MB200 Build/GRJ22; CyanogenMod-7) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
	public static final String UA_PHONE_iPhone_OS_4_3_3 = "User-Agent,Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5";
	public static final String UA_WINDOW_WIN10_Chrome_59 = "User-Agent,Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";
	public static final String UA_WINDOW_WIN10_Firefox_55 = "User-Agent,Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0";
	public static final String UA_WINDOW_IE6 = "User-Agent, Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)";
	public static final String UA_WINDOW_IE7 = "User-Agent,Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)";
	public static final String UA_WINDOW_IE8 = "User-Agent,Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)";
	public static final String UA_WINDOW_IE9 = "User-Agent,Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0;";
	public static final String UA_WINDOW_WIN10_IE11 = "User-Agent,Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729; rv:11.0) like Gecko";

	private String url;
	private Map<String, List<String>> backHeader = new HashMap<String, List<String>>();
	private Map<String, String> sendHeader = new HashMap<String, String>();
	@Deprecated
	private Map<String, Object> cookie = new GoourLinkMap<String, Object>();
	private int connectTimeout = 30000;
	private int readTimeout = 30000;
	private Params params;

	private boolean proxy;
	private String proxyHost;
	private int proxyPort;

	public static HttpConfig getInstance() {
		return new HttpConfig();
	}

	public HttpConfig() {
		sendHeader.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		sendHeader.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		sendHeader.put("User-Agent", HttpConfig.UA_WINDOW_WIN10_Firefox_55);
		sendHeader.put("Connection", "Keep-Alive");
		this.setAcceptDefault();
	}

	public HttpConfig(String url) {
		this();
		this.url = url;
	}

	public HttpConfig(String url, Params params) {
		this();
		this.url = url;
		this.params = params;
	}

	public HttpConfig(String url, Map<String, String> head) {
		this();
		this.url = url;
		this.sendHeader.putAll(head);
	}

	public HttpConfig(String url, Params params, Map<String, String> head) {
		this();
		this.url = url;
		this.params = params;
		this.sendHeader.putAll(head);
	}

	/**
	 * 链接服务器后会返回头部信息，采用此方法重置原有的头部信息，以方便其中的COOKIE可以在下次请求中使用
	 * 
	 * @param headerMap
	 */
	public void setBackHeader(Map<String, List<String>> headerMap) {
		for (String key : headerMap.keySet()) {
			if (key == null)
				continue;
			List<String> list = headerMap.get(key);
			List<String> list2 = this.backHeader.get(key);
			// System.out.println(key);
			if (list2 == null) {
				List<String> list3 = new ArrayList<String>();
				list3.addAll(list);
				this.backHeader.put(key, list3);
			} else {
				if (key.equals("Set-Cookie") || key.toLowerCase().equals("set-cookie")) {// 合并重复的Cookie
					list2.addAll(list);
					HashMap<String, Integer> map = new HashMap<String, Integer>();
					for (int i = 0; i < list2.size(); i++) {
						String cookieKey = list2.get(i).split("=")[0];
						if (map.containsKey(cookieKey)) {// 有重复的COOKIE键，新的COOKIE会覆盖旧的COOKIE
							list2.set(map.get(cookieKey), list2.get(i));
							list2.remove(i--);
							// System.out.print(i+cookieKey+" ");
						}
						map.put(cookieKey, i);
					}
				}
			}

		}
	}

	/**
	 * @return backHeader
	 */
	public Map<String, List<String>> getBackHeader() {
		return backHeader;
	}

	/**
	 * 通过setBackHeader(Map<String, List<String>> headerMap)
	 * 后获取其中的COOKIE信息，以字符串的形式返回
	 * 
	 * @return
	 */
	public String getBackHeaderCookieString() {
		StringBuffer re = new StringBuffer();
		if (backHeader != null) {
			List<String> ckie = backHeader.get("Set-Cookie");
			if (ckie != null) {
				for (int i = 0; i < ckie.size(); i++) {
					String coo = ckie.get(i);
					re.append(coo.split(";")[0] + ";");
				}
			}
		}
		return re.toString();
	}

	/**
	 * 以Map的形式返回setBackHeader(Map<String, List<String>> headerMap)的COOKIE信息
	 * 
	 * @return
	 */
	public String getBackHeaderCookieMap() {
		StringBuffer re = new StringBuffer();
		List<String> ckie = backHeader.get("Set-Cookie");
		if (ckie != null) {
			for (int i = 0; i < ckie.size(); i++) {
				String coo = ckie.get(i);
				System.out.println(ckie);
				re.append(coo.split(";")[0] + ";");
			}
		}
		return re.toString();
	}

	/**
	 * 返回url地址
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            要设置的 url
	 */
	public HttpConfig setUrl(String url) {
		this.url = url;
		return this;
	}

	/**
	 * @return referer
	 */
	public String getReferer() {
		return sendHeader.get("Referer");
	}

	/**
	 * 设置HTTP请求来源
	 * 
	 * @param referer
	 *            要设置的 referer
	 */
	public HttpConfig setReferer(String referer) {
		sendHeader.put("Referer", referer);
		return this;
	}

	/**
	 * @return userAgent
	 */
	public String getUserAgent() {
		return sendHeader.get("User-Agent");
	}

	/**
	 * 设置浏览器UA
	 * 
	 * @param userAgent
	 *            要设置的 userAgent
	 */
	public HttpConfig setUserAgent(String userAgent) {
		sendHeader.put("User-Agent", userAgent);
		return this;
	}

	/**
	 * 设置请求头部信息，重复key会被新的值覆盖
	 * 
	 * @param key
	 * @param value
	 */
	public HttpConfig setHeader(String key, String value) {
		sendHeader.put(key.toLowerCase(), value);
		return this;
	}

	public String getHeader(String key) {
		return sendHeader.get(key);
	}

	/**
	 * @return sendHeader
	 */
	public Map<String, String> getSendHeader() {
		return sendHeader;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public HttpConfig setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
		return this;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public HttpConfig setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
		return this;
	}

	public Params getParams() {
		return params;
	}

	/**
	 * 设置要发送的数据
	 * 
	 * @param params
	 */
	public HttpConfig setParams(Params params) {
		this.params = params;
		return this;
	}

	/**
	 * 设置是否以ajax方式提交数据
	 * 
	 * @param isAjax
	 */
	public HttpConfig setAjax(boolean isAjax) {
		if (isAjax) {
			sendHeader.put("X-Requested-With", "XMLHttpRequest");
		} else {
			sendHeader.remove("X-Requested-With");
		}
		return this;
	}

	public boolean isAjax() {
		String ajax = sendHeader.get("X-Requested-With");
		if (ajax != null && ajax.equals("XMLHttpRequest")) {
			return true;
		}
		return false;
	}

	public HttpConfig setGzip(boolean isGzip) {
		if (isGzip) {
			sendHeader.put("Accept-Encoding", "gzip, deflate, br");
		} else {
			sendHeader.remove("Accept-Encoding");
		}
		return this;
	}

	public HttpConfig setAcceptDefault() {
		sendHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		return this;
	}

	public HttpConfig setAcceptJson() {
		sendHeader.put("Accept", "application/json, text/javascript, */*; q=0.01");
		return this;
	}

	/**
	 * 该方法已被废弃20170809<br>
	 * 使用此方法设置COOKIE会覆盖setHeader("cookie",String);该方法设置的COOKIE信息，在COOKIE条目较多的时候建议使用该方法，
	 * 它会使代码更美观
	 * 
	 * @param key
	 * @param objects
	 */
	@Deprecated
	public void addSendCookie(String key, Object... objects) {
		if (objects == null) {
			return;
		}
		for (Object object : objects) {
			cookie.put(key, object);
		}
	}

	/**
	 * 该方法已被废弃20170809<br>
	 * 获取要发送的COOKIE信息
	 * 
	 * @return
	 */
	@Deprecated
	public String getSendCookie() {
		StringBuffer sBuffer = new StringBuffer();
		for (Entry<String, Object> item : cookie.entrySet()) {
			sBuffer.append(item.getKey());
			sBuffer.append("=");
			sBuffer.append(item.getValue());
			sBuffer.append(";");
		}
		int len = sBuffer.length();
		if (len > 0) {
			sBuffer.delete(len - 1, len);
		}
		return sBuffer.toString();
	}

	/**
	 * 该方法已被废弃20170809<br>
	 */
	@Deprecated
	public void clearSendCookie() {
		cookie.clear();
	}

	/**
	 * 该方法已被废弃20170809<br>
	 * 
	 * @param key
	 */
	@Deprecated
	public void removeSendCookie(String key) {
		cookie.remove(key);
	}

	public boolean isProxy() {
		return proxy;
	}

	public HttpConfig setProxy(boolean proxy) {
		this.proxy = proxy;
		return this;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	/**
	 * 设置代理服务器地址，如果该代理服务器是透明代理服务器，访问的服务器仍旧能够获取到我们的真实IP。<br>
	 * 透明代理IP服务器会把我们的真实IP放在HTTP请求头部的HTTP_X_FORWARDED_FOR字段信息里面。<br>
	 * 但如果是高匿名代理服务器的话，有可能会出现响应慢的情况
	 * 
	 * @param proxyHost
	 */
	public HttpConfig setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
		return this;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public HttpConfig setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
		return this;
	}
}
