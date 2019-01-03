package com.weixin.Util;

import java.security.MessageDigest;
import java.util.Arrays;

public class CheckUtil {
    public static final String tooken="lgzs";

    public static Boolean checkSignatur(String signature,String timestamp,String nonce){
     String [] arr = {tooken,timestamp,nonce};
        Arrays.sort(arr);
        StringBuffer sb = new StringBuffer();
        for(String a:arr){
            sb.append(a);
        }
        String temp = shang1(sb.toString());
        return temp.equals(signature);
    }

    public static String shang1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char[] encrype ={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try{
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = encrype[byte0 >>> 4 & 0xf];
                buf[k++] = encrype[byte0 & 0xf];
            }
            return new String(buf);
        }catch ( Exception e){
          return null;
        }
    }
}
