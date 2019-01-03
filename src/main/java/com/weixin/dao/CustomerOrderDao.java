package com.weixin.dao;

import com.weixin.pojo.Customer;
import com.weixin.pojo.DataService;
import com.weixin.pojo.Orders;
import com.weixin.pojo.Servcie;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerOrderDao {
    //查询出前八的服务
    List<Servcie> queryService( int id);
    void insertOrder(Orders orders);
    List<Orders> checkOrdersbyuserId(@Param("openid") String openid);
    List<DataService> queryDataService();
    void cancelOrderByUid(@Param("id")int id);
    String queryOrderStatus(@Param("id")int id);
    Integer findUidByPhone(String telephone);//根据电话查看下单用户是否已下单，返回用户id，id为空则还未注册则，用户信息注册
    Integer addUser(Customer customer);//用户注册
}
