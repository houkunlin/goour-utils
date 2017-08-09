package cn.goour.utils.http;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import cn.goour.utils.io.FileUtils;
import cn.goour.utils.io.IO;
import cn.goour.utils.tools.FileMimeType;

public class Http {
	public static void main(String[] args) {
		// System.out.println();
		Params params = new Params();
		File f = new File("D:\\Users\\HouKunLin\\Pictures\\Saved Pictures");
		File[] files = f.listFiles();
		// System.out.println(f.getAbsolutePath());
		// System.out.println(Arrays.toString(files));
		File[] files2 = new File[3];
		files2[0] = files[0];
		files2[1] = files[1];
		files2[2] = files[2];
		params.add("a[]", "1");
		params.add("a[]", "2");
		params.add("a[]", "3");
		params.add("a[]", "4");
		params.add("a[]", "5");
		// params.add("b", files[0]);
		// params.add("c[]", files2);

		HttpConfig config = new HttpConfig("http://114.215.91.232/lsHelper/admin/");
		config = new HttpConfig("http://127.0.0.1/test/server.php");
		config = new HttpConfig("http://www.baidu.com");
		config = new HttpConfig("https://goour.cn/server.php?sd=dsdsd");
		config.setHeader("cookie", "asd[]=2323;asd[]=2323;asd[]=2323侯坤林");
		config.setParams(params);

		try {
			// String aa = HttpUtil.get(config.getUrl());
			// System.out.println(aa);
			config.setProxy(true);
			config.setProxyHost("122.157.82.51");
			config.setProxyPort(8118);
			// @SuppressWarnings("static-access")
			long start = System.nanoTime();
			long start1 = System.currentTimeMillis();
			byte[] re = get(config);
			long end = System.nanoTime();
			long end1 = System.currentTimeMillis();
			System.out.println(end - start);
			System.out.println(end1 - start1);
			System.out.println("返回的COOKIE：" + config.getBackHeaderCookieString());
			String reString = new String(re, "utf-8");
			System.out.println(reString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * HTTP GET请求
	 * 
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public static byte[] get(HttpConfig config) throws Exception {
		byte[] content = null;
		String url = config.getUrl();
		Params params = config.getParams();
		if (params != null) {
			if (url.indexOf("?") != -1) {// 在url末尾有问号
				url += "&" + params.getFromDataString();
			} else {
				url += "?" + params.getFromDataString();
			}
		}

		InputStream in = null;
		try {
			URL realUrl = new URL(url);

			HttpURLConnection conn = getHttpURLConnection(realUrl, config);

			setHeader(conn, config);
			conn.setRequestMethod("GET");

			conn.setInstanceFollowRedirects(false);
			conn.setUseCaches(false);
			conn.connect();

			in = getInputStream(conn, config);

			content = IO.read(in);
			in.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * POST数据，如果没有设置上传文件信息，则使用一般表单post，如果有设置文件信息，则采用文件上传
	 * 
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public static byte[] post(HttpConfig config) throws Exception {
		Params params = config.getParams();
		if (params != null && params.getFileData() != null) {
			return postFile(config);
		} else {
			return postForm(config);
		}
	}

	/**
	 * HTTP POST一般请求
	 * 
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public static byte[] postForm(HttpConfig config) throws Exception {
		byte[] content = null;
		String url = config.getUrl();

		InputStream in = null;
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = getHttpURLConnection(realUrl, config);

			setHeader(conn, config);
			conn.setRequestMethod("POST");

			conn.setInstanceFollowRedirects(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

			PrintWriter out = new PrintWriter(conn.getOutputStream());

			Params params = config.getParams();
			if (params != null) {
				out.print(params.getFromDataString());
			}
			out.flush();
			out.close();

			in = getInputStream(conn, config);

			content = IO.read(in);

			in.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return content;
	}

	/**
	 * HTTP DELETE请求
	 * 
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public static byte[] delete(HttpConfig config) throws Exception {
		byte[] content = null;
		String url = config.getUrl();
		InputStream in = null;
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = getHttpURLConnection(realUrl, config);

			setHeader(conn, config);
			conn.setRequestMethod("DELETE");

			conn.setInstanceFollowRedirects(false);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

			PrintWriter out = new PrintWriter(conn.getOutputStream());

			Params params = config.getParams();
			if (params != null) {
				out.print(params.getFromDataString());
			}
			out.flush();
			out.close();

			in = getInputStream(conn, config);

			content = IO.read(in);

			in.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return content;
	}

	/**
	 * 该方法已被抛弃，并不再维护代码20170809<br>
	 * 不建议使用该方法，如需上传文件，请使用post(HttpConfig config, Map<String, Object> mapObj,
	 * Map<String, File[]> mapFiles)方法， 把第二参数置为null
	 * 
	 * @param config
	 * @param files
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public static byte[] postFile(HttpConfig config, File[] files, String key) throws Exception {
		byte[] content = null;
		String url = config.getUrl();
		URL realUrl = new URL(url);
		HttpURLConnection conn = getHttpURLConnection(realUrl, config);

		String boundary = "--------" + System.currentTimeMillis();

		setHeader(conn, config);
		conn.setRequestMethod("POST");

		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);

		OutputStream out1 = new DataOutputStream(conn.getOutputStream());
		String sendDataHeader = "Content-Type: multipart/form-data; boundary=" + boundary + "\r\n\r\n--" + boundary
				+ "\r\n";
		out1.write(sendDataHeader.getBytes());

		byte[] end = ("\r\n--" + boundary + "\r\n").getBytes();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			byte[] fileBytes = FileUtils.read(file);
			byte[] head = ("Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + file.getName()
					+ "\"\r\n" + "Content-Type: " + FileMimeType.getFileMimeType(file) + "\r\n\r\n").getBytes();
			if (i >= files.length - 1) {
				end = ("\r\n--" + boundary + "--\r\n").getBytes();
			}
			out1.write(head);
			out1.write(fileBytes);
			out1.write(end);
			out1.flush();
		}
		out1.flush();
		out1.close();

		InputStream in = null;

		in = getInputStream(conn, config);

		content = IO.read(in);

		in.close();
		conn.disconnect();
		return content;
	}

	/**
	 * HTTP POST请求，文件上传请求
	 * 
	 * @param config
	 * @param mapObj
	 *            表单信息，可为null
	 * @param mapFiles
	 *            文件信息，可为null
	 * @return
	 * @throws Exception
	 */
	public static byte[] postFile(HttpConfig config) throws Exception {
		byte[] content = null;
		String url = config.getUrl();

		Map<String, Object> mapObj = null;
		Map<String, File> mapFiles = null;

		Params params = config.getParams();
		if (params != null) {
			mapObj = params.getFormData();
			mapFiles = params.getFileData();
		}

		InputStream in = null;
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = getHttpURLConnection(realUrl, config);

			String boundary1 = "--------" + System.currentTimeMillis();
			String boundary2 = "--" + boundary1;

			setHeader(conn, config);
			conn.setRequestMethod("POST");

			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary1);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

			OutputStream out1 = new DataOutputStream(conn.getOutputStream());
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();

			String sendDataHeader = "Content-Type: multipart/form-data; boundary=" + boundary1 + "\r\n";
			bytes.write(sendDataHeader.getBytes());

			byte[] start = boundary2.getBytes();
			byte[] brn = "\r\n".getBytes();
			if (mapObj != null && !mapObj.isEmpty()) {
				for (Entry<String, Object> item : mapObj.entrySet()) {
					String key = item.getKey();
					byte[] head = ("Content-Disposition: form-data; name=\"" + key + "\"" + "\r\n\r\n").getBytes();
					byte[] body = item.getValue().toString().getBytes();
					bytes.write(brn);
					bytes.write(start);
					bytes.write(brn);
					bytes.write(head);
					bytes.write(body);
				}
			}
			if (mapFiles != null && !mapFiles.isEmpty()) {
				for (Entry<String, File> item : mapFiles.entrySet()) {
					String key = item.getKey();
					File file = item.getValue();
					byte[] body = FileUtils.read(file);
					byte[] head = ("Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + file.getName()
							+ "\"\r\n" + "Content-Type: " + FileMimeType.getFileMimeType(file) + "\r\n\r\n").getBytes();
					bytes.write(brn);
					bytes.write(start);
					bytes.write(brn);
					bytes.write(head);
					bytes.write(body);
				}
			}

			bytes.write(brn);
			bytes.write(start);
			bytes.write("--".getBytes());
			bytes.write(brn);

			out1.write(bytes.toByteArray());
			out1.flush();
			out1.close();

			in = getInputStream(conn, config);

			content = IO.read(in);
			bytes.close();
			in.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	public static void showHeaders(Map<String, List<String>> map) {
		for (String key : map.keySet()) {
			System.out.println(key + "--->" + map.get(key));
		}
	}

	public static void showURL(URL url) {
		System.out.println("HTTP Host :" + url.getHost());
		System.out.println("HTTP Url:" + url.getProtocol() + "://" + url.getHost() + ":"
				+ (url.getPort() == -1 ? url.getDefaultPort() : url.getPort()) + url.getPath());
		System.out.println("HTTP Query:" + url.getQuery());
	}

	/**
	 * 设置发送头部信息
	 * 
	 * @param conn
	 * @param config
	 */
	public static void setHeader(HttpURLConnection conn, HttpConfig config) {
		conn.setConnectTimeout(config.getConnectTimeout());
		conn.setReadTimeout(config.getReadTimeout());
		// 设置头部信息
		for (Entry<String, String> header : config.getSendHeader().entrySet()) {
			conn.setRequestProperty(header.getKey(), header.getValue());
		}

		String cookieString = config.getHeader("cookie");
		if (cookieString == null || cookieString.trim().equals("")) {
			cookieString = "";
		}
		// 复用COOKIE信息，获取到上次http访问得到的COOKIE信息，再加入新的COOKIE
		conn.setRequestProperty("Cookie", config.getBackHeaderCookieString() + cookieString);

	}

	/**
	 * 得到相对地址，在页面重定向的时候要用到
	 * 
	 * @param absolutePath
	 * @param relativePath
	 * @return
	 */
	public static String getAbsUrl(String absolutePath, String relativePath) {
		try {
			URL absoluteUrl = new URL(absolutePath);
			URL parseUrl = new URL(absoluteUrl, relativePath);
			return parseUrl.toString();
		} catch (MalformedURLException e) {
			return "";
		}
	}

	/**
	 * 使用此方法解决多次重定向问题。 之所以需要第二个参数，主要是重定向的时候可能需要传入上次访问时获得的COOKIE信息
	 * 
	 * @param conn
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public static InputStream getInputStream(HttpURLConnection conn, HttpConfig config) throws Exception {
		InputStream in = conn.getInputStream();
		config.setBackHeader(conn.getHeaderFields());

		// showHeaders(conn.getHeaderFields());

		int i = 0;
		while (conn.getHeaderField("Location") != null) {
			String locationUrl = conn.getHeaderField("Location");
			locationUrl = getAbsUrl(config.getUrl(), locationUrl);

			in.close();// 关闭上一个输入流
			conn.disconnect();

			config.setUrl(locationUrl);
			conn = Http.getLocation(config);

			in = conn.getInputStream();
			config.setBackHeader(conn.getHeaderFields());

			// showHeaders(conn.getHeaderFields());

			if (++i > 15) {
				throw new IOException("重定向次数太多！");
			}
		}
		return in;
	}

	/**
	 * 执行重定向操作，获得重定向后新的连接
	 * 
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public static HttpURLConnection getLocation(HttpConfig config) throws Exception {
		String url = config.getUrl();
		URL realUrl = new URL(url);
		HttpURLConnection conn = getHttpURLConnection(realUrl, config);

		setHeader(conn, config);

		conn.setInstanceFollowRedirects(false);
		conn.setUseCaches(false);
		conn.connect();
		return conn;
	}

	/**
	 * 传入URL返回请求对象
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static HttpURLConnection getHttpURLConnection(URL url, HttpConfig config) throws Exception {
		// showURL(url);
		HttpURLConnection conn = null;
		if (isHttps(url)) {
			conn = initHttps(url, config);
		} else {
			conn = initHttp(url, config);
		}
		return conn;
	}

	/**
	 * 打开与指定URL的链接
	 * 
	 * @param url
	 * @param config
	 * @return
	 * @throws IOException
	 */
	public static URLConnection openConnection(URL url, HttpConfig config) throws IOException {
		HttpURLConnection conn;
		if (config.isProxy()) {// 使用代理模式
			Proxy proxy = new Proxy(Type.HTTP,
					new InetSocketAddress(config.getProxyHost(), config.getProxyPort()));
			conn = (HttpURLConnection) url.openConnection(proxy);
		} else {
			conn = (HttpURLConnection) url.openConnection();
		}
		return conn;
	}

	/**
	 * 以HTTP协议初始化请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static HttpURLConnection initHttp(URL url, HttpConfig config) throws IOException {
		URLConnection conn1 = openConnection(url, config);

		HttpURLConnection conn = (HttpURLConnection) conn1;
		return conn;
	}

	public static boolean isHttps(URL url) {
		return url.getProtocol().startsWith("https");
	}

	/**
	 * 以HTTPS协议初始化请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static HttpURLConnection initHttps(URL url, HttpConfig config) throws IOException, Exception {
		URLConnection conn1 = openConnection(url, config);
		HttpsURLConnection conn = (HttpsURLConnection) conn1;

		// Certificate[] list = conn.getServerCertificates();
		// for (int i = 0; i < list.length; i++) {
		// Certificate cert = list[i];
		// }

		// 设置域名校验
		conn.setHostnameVerifier(new MyHostnameVerifier());
		conn.setSSLSocketFactory(MyX509TrustManager.getSocketFactory());

		return conn;
	}
}
