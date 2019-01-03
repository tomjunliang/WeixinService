package com.weixin.Test;


import com.weixin.ServiceImp.CustomerOrderServiceImp;
import com.weixin.Util.AcessUtil;
import com.weixin.Util.DateUtil;
import com.weixin.Util.TokenUtil;
import com.weixin.Util.WXconfigUtil;
import com.weixin.dao.CustomerOrderDao;
import com.weixin.pojo.*;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestMethod {

   @Autowired
    CustomerOrderServiceImp customerOrderServiceImp;

    @Test
    public  void TestAccessToken(){

        String asscess = TokenUtil.getAccessToken();
        String asscess2 = TokenUtil.getAccessToken();
        System.out.println(asscess);
        System.out.println(asscess2);
    }

    @Test
    public void createButton(){
      Button button = new Button();
        SubButton subButton = new SubButton("网站动态");
        ViveButton viveButton = new ViveButton("http://balabalatalk.com/wx/party.html","活动报名");
        ViveButton viveButton2 = new ViveButton("http://balabalatalk.com/wx/wx-jiameng-xuzhi.html","志愿者招募");
        ViveButton viveButton3 = new ViveButton("http://balabalatalk.com/wx/party.html","服务者风采");
        ViveButton viveButton4 = new ViveButton("http://balabalatalk.com/wx/party.html","微心愿征集");
        subButton.getSub_button().add(viveButton);
        subButton.getSub_button().add(viveButton2);
        subButton.getSub_button().add(viveButton3);
        subButton.getSub_button().add(viveButton4);

        SubButton subButton2 = new SubButton("我要加盟");
        ViveButton viveButton5 = new ViveButton("http://balabalatalk.com/wx/wx-scale.html","商户招募");
        ViveButton viveButton6 = new ViveButton("http://balabalatalk.com/wx/wx-login.html","我要抢单");
        ViveButton viveButton7 = new ViveButton("http://balabalatalk.com/wx/wx-sjdalogin.html","我的订单");
        ViveButton viveButton8 = new ViveButton("http://balabalatalk.com/wx/wx-login.html","推送");
        subButton2.getSub_button().add(viveButton5);
        subButton2.getSub_button().add(viveButton6);
        subButton2.getSub_button().add(viveButton7);
        subButton2.getSub_button().add(viveButton8);


        SubButton subButton3 = new SubButton("我要下单");
        ViveButton viveButton9 = new ViveButton("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AccessToken.APPID+"&redirect_uri=http://balabalatalk.com/AccessWx/getOpenID&response_type=code&scope=snsapi_userinfo#wechat_redirect","我要下单");
        ViveButton viveButton10 = new ViveButton("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AccessToken.APPID+"&redirect_uri=http://balabalatalk.com/AccessWx/getUserOrders&response_type=code&scope=snsapi_userinfo#wechat_redirect","我的订单");
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

    }
    @Test
    public void testGetJsapiTicket(){
        try{
            String ticket1 = WXconfigUtil.getJsapiticket();
            String ticket2 = WXconfigUtil.getJsapiticket();
            System.out.println(ticket1);
            System.out.println(ticket2);

        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }


    }

    //测试完数据库新加一条数据是否能拿到id
    @Test
    public void testAddUser(){
        Customer customer = new Customer();
        customer.setName("李大爷");
        customer.setAddress("北软路5号3路");
        customer.setTelephone("13158589555");
        customer.setSex(1);
        customer.setCredit(1);
        customer.setStatus("CLOSED");
        customer.setCreated_at(DateUtil.nowTime());
        customer.setUpdated_at(DateUtil.nowTime());

      int uid =  customerOrderServiceImp.addUserAndBackid(customer);
      System.out.println(uid);




    }

}
