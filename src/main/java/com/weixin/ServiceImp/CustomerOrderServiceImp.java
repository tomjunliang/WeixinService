package com.weixin.ServiceImp;

import com.weixin.Service.CustomerOrderService;
import com.weixin.Util.DateUtil;
import com.weixin.dao.CustomerOrderDao;
import com.weixin.pojo.Customer;
import com.weixin.pojo.DataService;
import com.weixin.pojo.Orders;
import com.weixin.pojo.Servcie;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CustomerOrderServiceImp implements CustomerOrderService {
    @Autowired
    private CustomerOrderDao customerOrderDao;

    @Override
    public List<Servcie> chooseService(int id){

        return customerOrderDao.queryService(id);
    }
    @Override
    @Transactional
    public void creatOrder(Orders orders){//创建订单
        /*
         * 开始创建订单前，先使用下单号码查询一遍是否注册
         * */
        Integer uid = findUidByPhone(orders.getLinkman());
        if (uid==null){
            Customer c = new Customer();
            Customer customer = new Customer();
            customer.setName(orders.getLinkman());
            customer.setAddress(orders.getAddress());
            customer.setTelephone(orders.getLinkphone());
            customer.setSex(1);
            customer.setCredit(1);
            customer.setStatus("CLOSED");
            customer.setCreated_at(DateUtil.nowTime());
            customer.setUpdated_at(DateUtil.nowTime());
            addUserAndBackid(customer);
            uid = customer.getId();
        }
        orders.setUser_id(uid);
        orders.setCreated_at(DateUtil.nowTime());
        orders.setStatus("ACCEPT");
        orders.setNo(DateUtil.createOrderNo());
        orders.setOrder_type(1);
        orders.setOrder_type_code("NORMAL");

        customerOrderDao.insertOrder(orders);
    }

    @Override
    public  List<Orders> checkOrdersbyuserId(String openid){

       List list = customerOrderDao.checkOrdersbyuserId(openid);

        return list;
    }

    @Override
    public  List<DataService> queryDataService(){
        List list = customerOrderDao.queryDataService();
        return list;
    }

   @Override
   @Transactional
   public String cancelOrderByUid(int id){
        String status = customerOrderDao.queryOrderStatus(id);

        if (status.equals("ACCEPT")){
        customerOrderDao.cancelOrderByUid(id);
            return "success";
        }else{

            return "late";

        }


   }

   @Override
   public  Integer addUserAndBackid (Customer customer){
         Integer uid =  customerOrderDao.addUser(customer);

        return uid;
   }
   /*
   * 根据电话号码查询用户是否注册
   * */
   public Integer findUidByPhone(String telephone){
        Integer uid =customerOrderDao.findUidByPhone(telephone);
        return uid;
   }







}
