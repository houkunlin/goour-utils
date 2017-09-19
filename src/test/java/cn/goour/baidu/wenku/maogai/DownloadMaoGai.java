package cn.goour.baidu.wenku.maogai;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.goour.utils.http.Http;
import cn.goour.utils.http.HttpConfig;
import cn.goour.utils.http.Params;
import cn.goour.utils.io.FileUtils;

public class DownloadMaoGai {
	public static void main(String[] args){
//		String url = "https://wkbos.bdimg.com/v1/docconvert8640//wk/2c13af871512603fa4ceb662235cce38/0.json?responseCacheControl=max-age%3D3888000&responseExpires=Thu%2C%2002%20Nov%202017%2019%3A50%3A46%20%2B0800&authorization=bce-auth-v1%2Ffa1126e91489401fa7cc85045ce7179e%2F2017-09-18T11%3A50%3A46Z%2F3600%2Fhost%2Fe7850b63ecef41cbf82b79e62ad0ab82fe3bafd01add278efa07b85b71cbc51c&x-bce-range=0-15675&token=784f94e84877e3359284f0a2754c0ef0b773886edf28221a846c90daf5829aa8&expire=2017-09-18T12:50:46Z";
		String result = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			int i = 0;
			int j = 0;
			for (i = 0;  i< 1714690; i+=10000) {
				j = i+10000-1;
				byte[] re = getPage(i,j);
				result = new String(re);
				System.out.println(result.length());
				System.out.println(result.startsWith("wenku_"));
				JSONObject jsonObject = JSONObject.parseObject(result.replaceAll("(wenku_\\d\\()|(\\)$)", ""));
				JSONArray body = jsonObject.getJSONArray("body");
				
				for (Object object : body) {
					JSONObject json = (JSONObject)object;
					String c = json.getString("c");
					stringBuffer.append(c);
					if ("".equals(c.trim())) {
						stringBuffer.append("\r\n");
					}
				}
				try {
					System.out.println(String.format("加载范围：%d-%d", i,j));
					Thread.sleep(50000);
				} catch (Exception e) {
				}
			}
			i-=10000;
			j=1714690-1;
			System.out.println(i);
			System.out.println(j);
			byte[] re = getPage(i,j);
			result = new String(re);
			System.out.println(result.length());
			System.out.println(result.startsWith("wenku_"));
			JSONObject jsonObject = JSONObject.parseObject(result.replaceAll("(wenku_\\d\\()|(\\)$)", ""));
			JSONArray body = jsonObject.getJSONArray("body");
			
			for (Object object : body) {
				JSONObject json = (JSONObject)object;
				String c = json.getString("c");
				stringBuffer.append(c);
				if ("".equals(c.trim())) {
					stringBuffer.append("\r\n");
				}
			}
			
			System.out.println();
			File file = new File("maogai.txt");
			FileUtils.write(file, stringBuffer.toString().getBytes());
			System.out.println(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(result);
		}
//		System.out.println(body);

	}
	public static byte[] getPage(int s,int e) throws Exception {
		String url = "https://wkbos.bdimg.com/v1/docconvert8640//wk/2c13af871512603fa4ceb662235cce38/0.json";
		Params params = new Params();
		params.add("responseCacheControl", "max-age=3888000")
		.add("responseExpires", "Thu, 02 Nov 2017 20:53:52 +0800")
		.add("authorization", "bce-auth-v1/fa1126e91489401fa7cc85045ce7179e/2017-09-18T12:53:52Z/3600/host/b1435435f567ff2e130638ff4f77a6bfbfef2db3aa9cf158f4e37ed13a46e241")
		.add("x-bce-range", String.format("%d-%d", s,e))//1714690   15675
		.add("token", "b8f5a4dc5ad567a0fde628aca72ea809746891ea8c8fe8274a0c730b2e16f5b3")
		.add("expire", "2017-09-18T13:53:52Z");
		
		HttpConfig config = new HttpConfig(url);
		config.setParams(params)
		.setAcceptJson()
		.setAjax(true)
		.setHeader("host", "wkbos.bdimg.com")
		.setReferer("https://wenku.baidu.com/view/77fd45cf0875f46527d3240c844769eae009a3dd.html");
		byte[] re = Http.get(config);
		return re;
	}
	@Test
	public void testName() throws Exception {
		System.out.println(new String(getPage(0,1000)));
	}
}
