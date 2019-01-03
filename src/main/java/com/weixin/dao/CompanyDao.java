package com.weixin.dao;

import com.weixin.pojo.Company;
import com.weixin.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyDao {
    List<Orders> getOrderByStatus(String status);//1.通过status=accept列出未抢单的列表2.status=PIE展示未完成订单
    List<Orders> getOrderById(int id);//通过id查找订单
    String getSalt(String name);//从数据库获取盐值
    String getPwd(String name);//从数据库获取密码值
    boolean checkPassword(String pwd);//商家登录验证密码
    String getStatusById(int id);//
    boolean editStatus(@Param("id") int id ,@Param("status") String status ,@Param("cid") int cid);//点击抢单修改订单状态为已抢单,或者点击完成修改订单状态为已完成


    List<Orders> OrderBycompanyId(@Param("company_id")int company_id);
    List<Company> getCompanyByLogin(String login);
    void changeOrder(Integer id);

    void setTotal(Orders orders);//商家录入价格

}