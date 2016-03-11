package com.weibo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * string common class
 * Created by Sean on 16/3/10.
 */
public class StringUtil {

    public static String toMD5(String str) {
        if (str == null) {
            return null;
        }

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(str.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : bytes) {
                int temp = b & 0xff;
                if (temp < 0x10) {//小于0x10需要在前面添0,如0A
                    stringBuilder.append('0');
                }
                stringBuilder.append(Integer.toHexString(temp));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String s = "The quick brown fox jumps over the lazy dog";
        System.out.println(StringUtil.toMD5(s));
    }
}
