package org.room803.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //自动设置方法
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
public class ToOrder {
    private   int order_id;//订单编号
    private   String patient_id;//订单病人编号
    private   String doctor_id;//订单医生编号
    private     String medicine_id;//订单药品编号
    private    String med_instruments_id;//订单医疗器械编号
    private    String order_time;//订单时间
    public String set() {
        String s;


        s = "("+order_id+","+ "'"+ patient_id+ "'"+","+ "'"+doctor_id+ "'"+","+ "'"+medicine_id+ "'"+","+ "'"+
                med_instruments_id+ "'"+"," + "'"+order_time+ "'"+")";

        //在数据库查询中，需要用到的(值1，值2，值3...)
        return s;
    }

    public String list() {
        String s;
        s = "(order_id,patient_id,doctor_id,medicine_id,med_instruments_id,order_time)";
        return s;
        //在数据库查询中，需要用到(列名1，列名2，列名3...)

    }

}
