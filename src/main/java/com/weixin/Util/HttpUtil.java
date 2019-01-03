package com.weixin.Util;

import com.weixin.pojo.AccessToken;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public static String get(String url) throws Exception{

        URL u = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) u.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();
        InputStream ips = connection.getInputStream();
        int size = ips.available();
        byte[] bs = new byte[size];
        ips.read(bs);
        String message = new String(bs, "UTF-8");


        return message;
    }
}
