package com.weixin.controller;

import com.weixin.Service.CompanyService;
import com.weixin.Util.DataResult;
import com.weixin.Util.DatasResult;
import com.weixin.Util.PassKeyUtil;
import com.weixin.pojo.Company;
import com.weixin.pojo.Orders;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    DatasResult<Orders> datasResult = new DatasResult();

    /*
    根据status状态查询订单
     */
    @RequestMapping("/showOrderByStatus")
    @ResponseBody
    public DatasResult<Orders> getOrders(String status) {
        DataResult dataResult = new DataResult();
        List list = companyService.showOrderByStatus(status);
        datasResult.setDatas(list);
        for (Object o : list
        ) {
            System.out.println(o);
        }
        return datasResult;
    }

    /*
   点击抢单修改订单状态
    */
    @RequestMapping("/editStatus")
    @ResponseBody
    public DataResult clickEditStatus(@RequestParam int id, @RequestParam String status, HttpSession session) {
        DataResult dataResult = new DataResult();
        int cid = (Integer) session.getAttribute("company_id");
        if ("ACCEPT".equals(companyService.getStatusById(id))) {
            if (companyService.editStatus(id, status, cid)) {
                dataResult.setStatus("success");
                //返回一个成功的msg到前端页面然后ajax修改按钮的值
            }
        } else {
            dataResult.setStatus("failed");
        }
        /*
        companyService.getOrdersById(id);
        需求变成从后台获取list列表订单时使用
         */
        return dataResult;
    }

    /*
    验证商户登录
    @params login输入的姓名
    @params password输入的密码
    @params key密码加密后的字符串
     */
    @RequestMapping("/checkPwd")
    @ResponseBody
    public DataResult checkCompanyPwd(@RequestParam String login, @RequestParam String password, HttpSession session) throws UnsupportedEncodingException {
        DataResult dataResult = new DataResult();
        int i = 0;
        String key = null;
        List<Company> list;
   //     String logins = new String(login.getBytes("ISO8859-1"), "utf-8");
        list = companyService.getCompanyByLogin(login);

            while (i < list.size()) {
                String pwd = list.get(i).getPassword();
                String salt = list.get(i).getSalt();
                try {
                    key = PassKeyUtil.checkPassword(password, salt);

                } catch (NoSuchAlgorithmException e) {
                    System.out.println("key这里出错了！");
                    dataResult.setStatus(e.getMessage());
                }
                if (key.equals(pwd)) {
                    System.out.println(list.get(i).getId());
                    dataResult.setStatus("true");
                    session.setAttribute("company_id", list.get(i).getId());
                    break;
                } else {
                    i++;
                }
            }

        if (list.size() == 0) {
             dataResult.setStatus(login+",不存在");
        }

            return dataResult;

    }

    /*
     * 商家点击我的订单，如果session中已经有商家信息则为已登陆跳转到商家订单展示页面，否则为未登录状态，让商家去登陆
     * auth:ljl
     * */
    @RequestMapping("/verifyCompanyLogin")
    @ResponseBody
    public DataResult<String> verifyCompanyLogin(HttpServletRequest request) {
        DataResult dataResult = new DataResult();
        HttpSession session = request.getSession();
        Integer company_id = (Integer) session.getAttribute("company_id");
        if (company_id == null) {
            dataResult.setData("notLogin");
        } else {
            dataResult.setData("logined");
        }

        return dataResult;
    }

    /**
     * 展示商家的订单
     * auth：ljl
     */
    @RequestMapping("/companyOrder")
    @ResponseBody
    public DatasResult<Orders> OrderBycompanyId(HttpServletRequest request) {
        DatasResult<Orders> datasResult = new DatasResult();
        HttpSession session = request.getSession();
        try {
            int company_id = (Integer) session.getAttribute("company_id");
            List<Orders> list = companyService.OrderBycompanyId(company_id);
            datasResult.setDatas(list);
            datasResult.setStatus("success");

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            datasResult.setStatus("err");
        }

        return datasResult;
    }

    /*
     * 商家点击已完成改变订单状态
     * auth：ljl
     *
     * */
    @RequestMapping("/changeOrder")
    @ResponseBody
    public DataResult<String> changgeOrder(Integer id) {
        DataResult<String> dataResult = new DataResult<String>();
        try {
            companyService.changeOrder(id);
            dataResult.setStatus("success");
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            dataResult.setStatus("err");
        }
        return dataResult;
    }

    /**
     *
     * 录入价格
     * auth：ljl
     *
     * */
    @RequestMapping("/setOrderTotal")
    @ResponseBody
    public DataResult<String> setOrderTotal(Double total,Integer id) {
        DataResult<String> dataResult = new DataResult<String>();
        try {
            companyService.setTotal(total,id);
            dataResult.setStatus("success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            dataResult.setStatus("err");
        }
        return dataResult;
    }


}
