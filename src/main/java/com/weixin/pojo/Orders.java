package com.weixin.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Orders {
    /*
    * o.linkman AS linkman ,
        o.linkphone AS linkphone,
         o.address AS address,
         o.service_id AS service,
         o.note AS note,
         o.no AS no,
         o.status AS status,
         o.user_id AS user_id,
         o.order_type AS order_type,
         o.created_at as created_at,
         o.order_type_code AS order_type_code,
         s.name AS name
    * */

    private Integer id;
    private String linkman;
    private String linkphone;
    private Integer companyId;
    private Integer back_user_id;
    private String address;
    private Integer service_id;
    private String note;
    private String no;
    private String name;
    private String openid;
    private String status;
    private Integer user_id;
    private Integer back_note;
    private String  back_order_status;
    private Integer user_area_id;
    private Integer order_type;
    private String order_type_code;
    private String  service_time;
    private String accept_time;
    private String pie_time;
    private String back_time;
    private String created_at;
    private String updated_at;
    private String service_started_at;
    private String service_ended_at;
    private Double total;
    private Integer payment_status;

}
