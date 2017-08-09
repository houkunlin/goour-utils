package cn.goour.utils.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by HouKunLin on 2017/6/11.
 */

public class IO {
	// private static final Logger logger = LoggerFactory.getLogger(IO.class);
	public static byte[] read(InputStream in) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream(1024);
		byte[] buff = new byte[1024];
		byte[] bytes = null;
		int len = 0;
		// System.out.println("IO_end:"+result.size());
				// logger.info("IO_end:"+result.size());
		try {
			while ((len = in.read(buff)) != -1) {// 在这里会产生阻塞
				result.write(buff, 0, len);
				// System.out.println("IO_While_in:"+in.available());
				// System.out.println("IO_While_re:"+result.size());
				// logger.info("IO_While_in:"+in.available());
				// logger.info("IO_While_re:"+result.size());
				// if (result.size() >= in.available()) {
				// break;
				// }
			}
			bytes = result.toByteArray();
			result.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				result.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}
}
