package util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by cellargalaxy on 2017/5/2.
 */
public class BASE64 {
	
	
	
	public static String encryptBASE64(String string) {
		return new BASE64Encoder().encode(string.getBytes());
	}
	
	public static String decryptBASE64(String string) throws IOException {
		return new String(new BASE64Decoder().decodeBuffer(string));
	}
	
	public static String encryptBASE64(String string, String coding) throws UnsupportedEncodingException {
		return new BASE64Encoder().encode(string.getBytes(coding));
	}
	
	public static String decryptBASE64(String string, String coding) throws IOException {
		return new String(new BASE64Decoder().decodeBuffer(string), coding);
	}
}
