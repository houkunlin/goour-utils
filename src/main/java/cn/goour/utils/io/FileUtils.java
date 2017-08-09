package cn.goour.utils.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class FileUtils {
	public static void write(String path, String content, String encoding) throws UnsupportedEncodingException, FileNotFoundException, IOException{
		File file = new File(path);
		file.delete();
		BufferedWriter writer = null;
		try {
			file.createNewFile();
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw e;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void write(String filepath, byte[] content) throws FileNotFoundException, IOException {
		File file = new File(filepath);
		file.delete();
		try {
			file.createNewFile();
			write(file, content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void write(File file, byte[] content) throws FileNotFoundException, IOException {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(content);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
	}

	public static String read(String path, String encoding) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		StringBuffer content = new StringBuffer();
		File file = new File(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
			String line = null;
			int len = 0;
			String hString = "\r\n";
			while ((line = reader.readLine()) != null) {
				if (len > 0) {
					len += hString.length();
					content.append(hString);
				}
				len += line.length();
				content.append(line);
			}
			reader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw e;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content.toString();
	}
	public static byte[] read(String filepath) throws FileNotFoundException, IOException {
		return read(new File(filepath));
	}
	public static byte[] read(File file) throws FileNotFoundException, IOException{
		FileInputStream in = null;
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		try {
			in = new FileInputStream(file);
			byte[] buff = new byte[1024];
			int len = 0;
			while ((len=in.read(buff)) != -1) {
				result.write(buff, 0, len);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		byte[] bytes = result.toByteArray();
		result.close();
		return bytes;
	}
	
	public static void saveObject(File file,Object object) throws FileNotFoundException, IOException {
		FileOutputStream in1 = null;
		ObjectOutputStream in2 = null;
		try {
			in1 = new FileOutputStream(file);
			in2 = new ObjectOutputStream(in1);
			in2.writeObject(object);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				in1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Object readObject(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream in1 = null;
		ObjectInputStream in2 = null;
		Object object = null;
		try {
			in1 = new FileInputStream(file);
			in2 = new ObjectInputStream(in1);
			object = in2.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				in1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return object;
	}
}