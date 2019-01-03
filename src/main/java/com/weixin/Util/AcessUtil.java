package com.weixin.Util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AcessUtil {

    public static String postRequest(String url,String data)throws Exception{
        URL ul = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) ul.openConnection();

        connection.setDoOutput(true);//要发送数据出去，必须设置为可发送状态；
        OutputStream opt= connection.getOutputStream();//获取输出流
        opt.write(data.getBytes("UTF-8"));
        opt.close();
        InputStream ips = connection.getInputStream();
        int size = ips.available();
        byte[] bs = new byte[size];
        ips.read(bs);
        String message = new String(bs, "UTF-8");

        return message;
    }
}
