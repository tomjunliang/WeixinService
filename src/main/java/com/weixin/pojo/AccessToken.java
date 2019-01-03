package com.weixin.pojo;


import org.json.JSONObject;

import javax.json.JsonObject;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AccessToken {

   // public  static final String APPID="wx25d48e06986bad07";
   // public  static final String APPSECRET="5a60ac2e80cd1142ccbead110e5355ed";
    public  static final String APPID="wxb520eef9fa4c641a";
    public  static final String APPSECRET="2bb356cce08ca609b9da587f3f0c333c";


    public  static final String url ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;
    private String access_token;
    private long  effTime;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getEffTime() {
        return effTime;
    }

    public void setEffTime(long effTime) {
        this.effTime = effTime;
    }
}
