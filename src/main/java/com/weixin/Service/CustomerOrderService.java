package com.weixin.Service;

import com.weixin.pojo.Customer;
import com.weixin.pojo.DataService;
import com.weixin.pojo.Orders;
import com.weixin.pojo.Servcie;

import java.util.List;


public interface CustomerOrderService {
    List<Servcie> chooseService(int id);
    void creatOrder(Orders orders);
    List<Orders> checkOrdersbyuserId(String openid);
    List<DataService> queryDataService();
    String cancelOrderByUid(int id);
    Integer addUserAndBackid (Customer customer);//增加用户信息并返回用户id
}
