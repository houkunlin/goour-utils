package cn.goour.utils.http;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.IdentityHashMap;
import java.util.Map.Entry;

public class Params {
	private IdentityHashMap<String, Object> formData = new IdentityHashMap<String, Object>();
	private IdentityHashMap<String, File> fileData = new IdentityHashMap<String, File>();
	public Params() {
	}
	public void add(String key, Object...value){
		System.out.println("add(String key, Object value)");
		for (Object object : value) {
			formData.put(new String(key), object);
		}
		
	}
	public void add(String key, File...files){
		System.out.println("add(String key, File[] files)");
		if (files == null) {
			return ;
		}
		for (File file : files) {
			fileData.put(new String(key), file);
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
		
		return sBuffer.toString();
	}

	public IdentityHashMap<String, Object> getFormData() {
		return formData;
	}
	public IdentityHashMap<String, File> getFileData() {
		return fileData;
	}
	public static void main(String[] args) {
		Params params = new Params();
		params.add("a");
		params.add("a[]", "1",2,3,"1");
		params.add("b", "1");
		params.add("c", new File("").listFiles());
		params.add("c", new File(""));
		params.add("c", new File(""));
		System.out.println(params.getFromDataString());
		System.out.println(params.fileData);
		
		
		
		
//		IdentityHashMap<String,Object> map =new IdentityHashMap<String,Object>();
//		System.out.println(new String("xx").equals("xx"));
//		map.put(new String("xx"),"first");
//		map.put(new String("xx"),"second");
//		map.put(new String("xx"),"second3");
//		map.put(new String("xx1"),"1");
//		map.put(new String("xx1"),"2");
//		map.put(new String("xx1"),"2");
//		System.out.println("------------------");
//		System.out.println(map.size());
//		for (Entry<String, Object> entry : map.entrySet()) {
//			System.out.print(entry.getKey() +"    ");
//			System.out.println(entry.getValue());
//		}
//		System.out.println("idenMap="+map.containsKey("xx"));
//		System.out.println("idenMap="+map.get("xx"));
//		
//		System.out.println("------------------");
//		map.remove(new String("xx"));
//		System.out.println(map.size());
//		for (Entry<String, Object> entry : map.entrySet()) {
//			System.out.print(entry.getKey() +"    ");
//			System.out.println(entry.getValue());
//		}
	}
}
