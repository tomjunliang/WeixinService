package com.weixin.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/*
    商家登录验证密码
    @params 键盘输入的密码，数据库中取出的盐值
    进行加密与数据库中的加密后的密码进行对比
 */
public class PassKeyUtil {
    public static String checkPassword(String psd,String salt) throws NoSuchAlgorithmException {
        String wibble = "wibble";
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update((psd + wibble + salt).getBytes());
        StringBuilder buf = new StringBuilder();
        byte[] bits = md.digest();
        for (byte bit : bits) {
            int a = bit;
            if (a < 0) {
                a += 256;
            }
            if (a < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(checkPassword("123456","490510600.18839912850138052"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
