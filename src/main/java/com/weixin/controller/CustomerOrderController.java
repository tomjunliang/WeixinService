package com.weixin.controller;

import com.weixin.Service.CustomerOrderService;
import com.weixin.Util.DataResult;
import com.weixin.Util.DatasResult;
import com.weixin.Util.DateUtil;
import com.weixin.pojo.Customer;
import com.weixin.pojo.DataService;
import com.weixin.pojo.Orders;
import com.weixin.pojo.Servcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerOrderController {
    @Autowired
    private CustomerOrderService customerOrderService;

    @RequestMapping("/chooseService")
    @ResponseBody
    public DatasResult<Servcie> chooseService(int id){
        DatasResult<Servcie> dataResult = new DatasResult();
        try{
            List list = customerOrderService.chooseService(id);
            dataResult.setStatus("success");
            dataResult.setDatas(list);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
           dataResult.setStatus("err");
        }
        return dataResult;
    }
    @RequestMapping("/creatOrder")
    @ResponseBody
    public DataResult creatOrder(String linkman, String linkphone, String addresss, int service_id, String note, HttpServletRequest request){
        DataResult<String> dataResult = new DataResult<String>();
        try {

            HttpSession session = request.getSession();
            System.out.println("get session");
            String openid =(String)session.getAttribute("openid");

            System.out.println("openid : "+openid);




            Orders orders = new Orders();



            orders.setOpenid(openid);
            orders.setLinkman(linkman);
            orders.setLinkphone(linkphone);
            orders.setAddress(addresss);
            orders.setService_id(service_id);
            orders.setNote(note);
            orders.setPayment_status(0);
            customerOrderService.creatOrder(orders);
            System.out.println(orders);
            dataResult.setStatus("success");

            return dataResult;
        }catch (Exception e) {
            System.out.println(e.getStackTrace());
            dataResult.setStatus("err");
            return dataResult;

        }
    }

    @RequestMapping("/checkOrder")
    @ResponseBody
    public DatasResult<Orders> checkOrder(HttpServletRequest request){
        System.out.println("bengin check orders by openid");
        DatasResult<Orders> datasResult = new DatasResult();
        HttpSession session = request.getSession();
        String openid =(String)session.getAttribute("openid");
        System.out.println(session.getAttribute("opnid"));
        if(openid ==null){
            datasResult.setStatus("notLogin");
        }else {
            try {
                System.out.println("success get session :"+openid);
                List<Orders> list = customerOrderService.checkOrdersbyuserId(openid);
                datasResult.setStatus("success");
                datasResult.setDatas(list);
            } catch (Exception e) {

                System.out.println(e.getStackTrace());

            }

        }

        return datasResult;
    }

    @RequestMapping("/chooseDateService")
    @ResponseBody
    public DatasResult<DataService> chooseDateService(){
        DatasResult<DataService> dataResult = new DatasResult();
        try{
            List list = customerOrderService.queryDataService();
            dataResult.setStatus("success");
            dataResult.setDatas(list);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            dataResult.setStatus("err");
        }
        return dataResult;
    }

    /**
     *取消订单
     *auth:ljl
     * */
    @RequestMapping("/cancelOrderByUid")
    @ResponseBody
    public DataResult<String> cancelOrderByUid(int id){
         DataResult<String> dataResult = new DataResult();
         try{
          String status =   customerOrderService.cancelOrderByUid(id);
            if(status.equals("success")) {
                dataResult.setStatus("success");
            }else{
                dataResult.setStatus("late");
            }
         }catch (Exception e){
             System.out.println(e.getStackTrace());
             dataResult.setStatus("err");
         }


        return dataResult;
    }


   /* @RequestMapping("/TestAddu")
    @ResponseBody
    public DataResult<Integer> TestAddu(){
        DataResult<Integer> dataResult = new DataResult();
        try{
            Customer customer = new Customer();
            customer.setName("李大爷");
            customer.setAddress("北软路5号3路");
            customer.setTelephone("13158589555");
            customer.setSex(1);
            customer.setCredit(1);
            customer.setStatus("CLOSED");
            customer.setCreated_at(DateUtil.nowTime());
            customer.setUpdated_at(DateUtil.nowTime());

            customerOrderService.addUserAndBackid(customer);
            System.out.println(customer.getId());
            dataResult.setStatus("success");
            dataResult.setData(customer.getId());

        }catch (Exception e){
            System.out.println(e.getMessage());
            dataResult.setStatus("err");
        }


        return dataResult;
    }

       */



}
