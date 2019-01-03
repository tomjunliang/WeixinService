package com.weixin.Util;

import com.weixin.pojo.AccessToken;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TokenUtil {

    private static AccessToken accessToken = null;

    private static void getNewAccessToken() throws Exception {

        URL u = new URL(AccessToken.url);
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
        if (accessToken == null) {
            accessToken = new AccessToken();
        }
        //获取字符串，返回message的时json格式的字符串
        JSONObject jsonObject = new JSONObject(message);
        accessToken.setAccess_token(jsonObject.getString("access_token"));
        String effTime = jsonObject.getString("expires_in");
        int eftime = Integer.parseInt(effTime);
        long effe = System.currentTimeMillis() + eftime * 1000;
        accessToken.setEffTime(effe);

    }

    public static String getAccessToken() {

        if (accessToken == null || System.currentTimeMillis() > accessToken.getEffTime()) {
            try {
                getNewAccessToken();

            } catch (Exception e) {
                return "err";
            }
        }



    System.out.println("token:"+ accessToken.getAccess_token());
    return accessToken.getAccess_token();
      }
}
