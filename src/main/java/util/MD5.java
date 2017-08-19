package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 孵化种子 on 2017/8/6.
 */
public class MD5 {
    private static MessageDigest md5;
    
    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encryption(String string) {
        md5.update(string.getBytes());
        return new BigInteger(1, md5.digest()).toString(16);
    }
}
