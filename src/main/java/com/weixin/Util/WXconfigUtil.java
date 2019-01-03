package com.weixin.Util;

import com.weixin.pojo.*;
import org.apache.taglibs.standard.lang.jstl.Constants;
import org.json.JSONObject;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WXconfigUtil {

    private static JsSDKconfig  sdKconfig ;

    public static String getJsapiticket() throws  Exception{
        if (sdKconfig==null||System.currentTimeMillis()>sdKconfig.getExpired()) {

            String token = TokenUtil.getAccessToken();
            String appid = AccessToken.APPID;
            String APPSECRET = AccessToken.APPSECRET;
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + token + "&type=jsapi";
            System.out.println("请求了："+url );
            String message = HttpUtil.get(url);
            JSONObject jsonObject = new JSONObject(message);
            String Jsapiticket = jsonObject.getString("ticket");

            return Jsapiticket;
        }else{
            return sdKconfig.getJsapi_ticket();
        }

    }

    public static JsSDKconfig sign( String url) throws Exception {
        JsSDKconfig jsSDKconfig = new JsSDKconfig();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";
        // 注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + getJsapiticket() + "&noncestr=" + nonce_str
                + "&timestamp=" + timestamp + "&url=" + url;
        System.out.println(string1);
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //注意这里 要加上自己的appId
        jsSDKconfig.setAppId(AccessToken.APPID);
        System.out.println("AppId : "+AccessToken.APPID);
        jsSDKconfig.setJsapi_ticket(getJsapiticket());
        System.out.println("Jsapiticket: "+getJsapiticket());
        jsSDKconfig.setNonceStr(nonce_str);//ret.put("nonceStr", nonce_str);
        jsSDKconfig.setTimestamp(timestamp);//ret.put("timestamp", timestamp);
        jsSDKconfig.setSignature(signature);// ret.put("signature", signature);
        System.out.println("success");
        return jsSDKconfig;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }


    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
     /**
      * 创建菜单
      *
      * */
    public static String createButton(){
        Button button = new Button();
        SubButton subButton = new SubButton("96345动态");
        ViveButton viveButton = new ViveButton("http://wx.sx96345.com/WeiXinService/wx/party.html","活动报名");
        ViveButton viveButton2 = new ViveButton("http://wx.sx96345.com/WeiXinService/wx/wx-jiameng-xuzhi.html","志愿者招募");
        ViveButton viveButton3 = new ViveButton("http://wx.sx96345.com/WeiXinService/wx/party.html","服务者风采");
        ViveButton viveButton4 = new ViveButton("http://wx.sx96345.com/WeiXinService/wx/party.html","微心愿征集");
        subButton.getSub_button().add(viveButton);
        subButton.getSub_button().add(viveButton2);
        subButton.getSub_button().add(viveButton3);
        subButton.getSub_button().add(viveButton4);

        SubButton subButton2 = new SubButton("我要抢单");
        ViveButton viveButton5 = new ViveButton("http://wx.sx96345.com/WeiXinService/wx/wx-scale.html","我要加盟");
        ViveButton viveButton6 = new ViveButton("http://wx.sx96345.com/WeiXinService/wx/wx-login.html","我要抢单");
        ViveButton viveButton7 = new ViveButton("http://wx.sx96345.com/WeiXinService/wx/wx-sjdalogin.html","我的订单");
        ViveButton viveButton8 = new ViveButton("http://wx.sx96345.com/WeiXinService/wx/wx-login.html","推送");
        subButton2.getSub_button().add(viveButton5);
        subButton2.getSub_button().add(viveButton6);
        subButton2.getSub_button().add(viveButton7);
        subButton2.getSub_button().add(viveButton8);


        SubButton subButton3 = new SubButton("我要下单");
        ViveButton viveButton9 = new ViveButton("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx25d48e06986bad07&redirect_uri=http://wx.sx96345.com/WeiXinService/AccessWx/getOpenID&response_type=code&scope=snsapi_userinfo#wechat_redirect","我要下单");
        ViveButton viveButton10 = new ViveButton("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx25d48e06986bad07&redirect_uri=http://wx.sx96345.com/WeiXinService/AccessWx/getUserOrders&response_type=code&scope=snsapi_userinfo#wechat_redirect","我的订单");
        subButton3.getSub_button().add(viveButton9);
        subButton3.getSub_button().add(viveButton10);

        button.getButton().add(subButton);
        button.getButton().add(subButton2);
        button.getButton().add(subButton3);
        String result;
        JSONObject jsonObject = new JSONObject(button);
        System.out.println(jsonObject.toString());
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+TokenUtil.getAccessToken();
        System.out.println(url);
        try {
            result = AcessUtil.postRequest(url, jsonObject.toString());
        }catch (Exception e){
            result="err";
        }
        System.out.println(result);

        return result;
    }

}
