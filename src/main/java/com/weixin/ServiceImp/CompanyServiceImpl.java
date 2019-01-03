package com.weixin.ServiceImp;

import com.weixin.Service.CompanyService;
import com.weixin.dao.CompanyDao;
import com.weixin.pojo.Company;
import com.weixin.pojo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Orders> showOrderByStatus(String status) {
        return companyDao.getOrderByStatus(status);
    }

    @Override
    public List<Orders> getOrdersById(int id) {

        return companyDao.getOrderById(id);
    }

    @Override
    public String getSalt(String name) {
        return companyDao.getSalt(name);
    }

    @Override
    public String getPwd(String name) {
        return companyDao.getPwd(name);
    }

    @Override
    public boolean checkPassword(String pwd) {
        return companyDao.checkPassword(pwd);
    }

    @Override
    public boolean editStatus(int id , String status , int cid) {
        return companyDao.editStatus(id,status,cid);
    }
    @Override
    public String getStatusById(int id) {
        return companyDao.getStatusById(id);
    }




    @Override
    public List<Orders> OrderBycompanyId(int company_id){

        return companyDao.OrderBycompanyId(company_id);
    }
    @Override
    public List<Company> getCompanyByLogin(String login){

        return companyDao.getCompanyByLogin(login);
    }
    @Override
    public void changeOrder(Integer id){
        companyDao.changeOrder(id);
    }

    @Override
    public  void setTotal(Double total, int id){
        Orders orders = new Orders();
        orders.setId(id);
        orders.setTotal(total);
        companyDao.setTotal(orders);
    }
}
