package com.weixin.Service;

import com.weixin.pojo.Company;
import com.weixin.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyService {
    List<Orders> showOrderByStatus(String status);//通过status=accept列出未抢单的列表,status=serving列出未完成订单
    List<Orders> getOrdersById(int id);
    String getSalt(String name);//通过姓名从数据库获取盐值
    String getPwd(String name);//通过姓名从数据库获取密码值
    boolean checkPassword(String pwd);//商家登录验证密码
    List<Company> getCompanyByLogin(String login);
    String getStatusById(int id);

    boolean editStatus(int id,String status,int cid);//根据订单的id和当前是抢单还是完成订单的界面来区分编辑的状态
    List<Orders> OrderBycompanyId(int company_id);//根据商家id查询订单的信息
    void changeOrder(Integer id);
    void setTotal(Double total,int id);//商家根据订单id录入价格
}
