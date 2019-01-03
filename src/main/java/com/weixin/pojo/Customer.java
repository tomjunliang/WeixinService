package com.weixin.pojo;

import com.weixin.Util.DateUtil;

public class Customer {
    /**
     * 96345对应user表记录用户信息，对订单操作时user_id必须与user表的id一一对应否则无法派单
     * auth：ljl
     *
     *
     * */
    private Integer id;
    private String telephone;
    private String name;
    private String address;
    private Integer sex;
    private String status;
    private Integer credit;
    private String created_at;
    private String updated_at;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSex() {
        return sex;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

}
