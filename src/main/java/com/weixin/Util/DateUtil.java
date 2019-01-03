package com.weixin.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateUtil {

    public static String createOrderNo(){
        Date now=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyyMMddkkmm");
        Random ran = new Random();
        int rannumber = ran.nextInt(8999)+1000;
        String no = f.format(now)+rannumber;
        return no;
    }

    public static String nowTime(){
        Date now=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        return f.format(now);
    }
}
