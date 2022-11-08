package org.room803.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //自动设置方法
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
public class Med_Instruments {
        private int med_instruments_id;//治疗仪器编号
    private String med_instruments_name;//治疗仪器姓名
    private String med_instruments_price;//治疗仪器价格
    private String med_instruments_quantity;//治疗仪器数量

    public String set() {
        String s;
        s = "(" + med_instruments_id + "," + "'"+ med_instruments_name + "'"+ "," + "'"+ med_instruments_price + "'"+ "," + "'"+ med_instruments_quantity + "'"+ ")";
        //在数据库查询中，需要用到的(值1，值2，值3...)
        return s;

    }

    public String list() {
        String s;
        s = "(med_instruments_id,med_instruments_name,med_instruments_price,med_instruments_quantity)";
        return s;

        //在数据库查询中，需要用到(列名1，列名2，列名3...)

    }
}
