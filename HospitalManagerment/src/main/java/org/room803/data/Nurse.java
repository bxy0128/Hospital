package org.room803.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //自动设置方法
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器

public class Nurse {
    private int nurse_id;//医生编号
    private String nurse_name;//医生姓名
    private String nurse_gender;//医生性别
    private String nurse_dept;//医生科室编号
    private String nurse_phone;//医生电话

    public String set() {
        String s;
        s = "(" + nurse_id + "," + nurse_name + "," + nurse_gender + "," + nurse_dept + "," + nurse_phone + ")";
        //在数据库查询中，需要用到的(值1，值2，值3...)
        return s;

    }

    public String list() {
        String s;
        s = "(nurse_id,nurse_name, nurse_gender, nurse_dept, nurse_phone)";
        return s;

        //在数据库查询中，需要用到(列名1，列名2，列名3...)

    }
}
