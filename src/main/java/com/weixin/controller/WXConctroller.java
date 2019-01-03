package com.weixin.controller;


import com.weixin.Util.CheckUtil;
import com.weixin.Util.HttpUtil;
import com.weixin.Util.WXconfigUtil;
import com.weixin.pojo.AccessToken;
import com.weixin.pojo.JsSDKconfig;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/AccessWx")
public class WXConctroller {
    @RequestMapping(value="/verification",method= RequestMethod.GET)
    public void verification(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp =request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        if(CheckUtil.checkSignatur(signature,timestamp,nonce)){

            out.print(echostr);
        }
        out.flush();
        out.close();


    }

    @RequestMapping(value="verification",method= RequestMethod.POST)
    public void Reply(){

    }

    @RequestMapping(value="/getWXconfig")
    @ResponseBody
    public JsSDKconfig getWXconfig(String url){
        JsSDKconfig jsSDKconfig;
       try {
           jsSDKconfig = WXconfigUtil.sign(url);
           return jsSDKconfig;
       }catch (Exception e){
           System.out.println(e.getMessage());
           return null;
       }
    }
    @RequestMapping("/getOpenID")
    public String  getOpenId(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("code");
        System.out.println("code:"+code);

        String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+AccessToken.APPID.trim()+"&secret="+AccessToken.APPSECRET+"&code="+code+"&grant_type=authorization_code";
        System.out.println("jump" +url);
        System.out.println("Obtain OPenID");
        try {
            String message = HttpUtil.get(url);
            JSONObject jsonObject = new JSONObject(message);
            String web_access_token =jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");
            System.out.println("open: "+openid);
            HttpSession session = request.getSession();
            session.setAttribute("openid",openid);
            System.out.println("session :"+session.getAttribute("openid"));


        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Obtain OPenID err");

        }

        return "/wx-dashboard";
    }

    @RequestMapping("/getUserOrders")
    public String  getUserOrders(HttpServletRequest request, HttpServletResponse response){
        System.out.println("begin get Order by i must order");
        HttpSession session = request.getSession();
        String openid =(String)session.getAttribute("openid");
        System.out.println(session.getAttribute("openid"));
        if (openid == null) {

            String code = request.getParameter("code");
            System.out.println("code:"+code);
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+AccessToken.APPID+"&secret="+ AccessToken.APPSECRET+ "&code=" + code + "&grant_type=authorization_code";
            try {
                String message = HttpUtil.get(url);
                System.out.println(message);
                JSONObject jsonObject = new JSONObject(message);
            //    String web_access_token = jsonObject.getString("access_token");
                openid = jsonObject.getString("openid");

                session.setAttribute("openid", openid);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Obtain OPenID err");

            }

        }
        return  "/wx-yonghudingdan";
    }

    @RequestMapping("createMenu")
    @ResponseBody
    public String createMenu(){
       String  result = WXconfigUtil.createButton();
       if(result.equals("err")){
           return "err";
       }



        return "success";
    }


}
