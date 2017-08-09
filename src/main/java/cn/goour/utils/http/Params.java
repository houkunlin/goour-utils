package cn.goour.utils.http;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import cn.goour.utils.tools.GoourLinkMap;

public class Params {
	private Map<String, Object> formData = new GoourLinkMap<String, Object>();
	private Map<String, File> fileData = new GoourLinkMap<String, File>();
	public Params() {
		
	}
	public void add(String key, Object...objs){
		if (objs == null) {
			return ;
		}
		for (Object object : objs) {
			formData.put(key, object);
		}
		
	}
	public void add(String key, File...files){
		if (files == null) {
			return ;
		}
		for (File file : files) {
			fileData.put(key, file);
		}
	}
	public void remove(String key){
		removeForm(key);
		removeFile(key);
	}
	public void removeForm(String key){
		for(Entry<String, Object> item:formData.entrySet()){
			if (item.getKey().equals(key)) {
				formData.remove(item.getKey());
			}
		}
	}
	public void removeFile(String key){
		for(Entry<String, File> item:fileData.entrySet()){
			if (item.getKey().equals(key)) {
				fileData.remove(item.getKey());
			}
		}
	}
	public void clear() {
		formData.clear();
		fileData.clear();
	}
	public void clearFormData(){
		formData.clear();
	}
	public void clearFileData() {
		fileData.clear();
	}
	public boolean isFormDataEmpty(){
		return formData.isEmpty();
	}
	public boolean isFileDataEmpty(){
		return fileData.isEmpty();
	}
	public Map<String, Object> getFormData() {
		return formData;
	}
	public Map<String, File> getFileData() {
		return fileData;
	}
	public String getFromDataString(){
		StringBuffer sBuffer = new StringBuffer();
		for(Entry<String, Object> item:formData.entrySet()){
			sBuffer.append(item.getKey());
			sBuffer.append("=");
			try {
				sBuffer.append(URLEncoder.encode(item.getValue().toString(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			sBuffer.append("&");
		}
		int len = sBuffer.length();
		if (len > 0) {
			sBuffer.delete(len-1, len);
		}
		return sBuffer.toString();
	}

	public static void main(String[] args) {
		Params params = new Params();
		params.add("a[]");
		params.add("a[]", 1,2,3,4);
		params.add("b", "1");
		params.add("b", "1");
		params.add("c", "1");
		params.add("d", "1");
		params.add("c", new File("/").listFiles());
		params.add("c", new File(""));
		params.add("c", new File(""));
		System.out.println(params.getFromDataString());
		
		
	}
}
