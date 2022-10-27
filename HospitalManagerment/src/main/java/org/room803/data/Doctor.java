package org.room803.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //自动设置方法
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器

public class Doctor {
    private int doctor_id;//医生编号
    private String doctor_name;//医生姓名
    private String doctor_gender;//医生性别
    private String doctor_dept_id;//医生科室编号
    private String doctor_phone;//医生电话

    public String set() {
        String s;
        s = "(" + doctor_id + "," + doctor_name + "," + doctor_gender + "," + doctor_dept_id + "," + "doctor_phone" + ")";
        //在数据库查询中，需要用到的(值1，值2，值3...)
        return s;

    }

    public String list() {
        String s;
        s = "(doctor_id,doctor_name, doctor_gender, doctor_dept_id, doctor_phone)";
        return s;

        //在数据库查询中，需要用到(列名1，列名2，列名3...)

    }
}
