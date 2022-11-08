package org.room803.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //自动设置方法
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器

public class Patient {
    /**
     * 病人的信息_数据库各列名
     */
    private int patient_id;//病人编号
    private String patient_name;//病人姓名
    private String patient_gender;//病人性别
    private String patient_date_start;//病人住院日期
    private String patient_dept;//病人病因类型
    private String patient_area;//病人住址
    private String patient_doc_id;//病人的医生编号
    private String patient_age;//病人年龄
    private String patient_condition;//病人是否入院
    private String patient_phone;//病人电话


    public String set() {
        String s;
        s = "(" + patient_id + "," + "'"+patient_name + "'"+ "," + "'"+ patient_gender +"'"+ "," + "'"+patient_date_start+"'" + ","
                +"'"+ patient_dept+"'" + "," +"'"+ patient_area +"'"+ "," + patient_doc_id + "," + patient_age + "," +"'"+ patient_condition + "'"+"," + "'"+patient_phone +"'"+ ")";
        //在数据库查询中，需要用到的(值1，值2，值3...)
        return s;

    }

    public String list() {
        String s;
        s = "(patient_id,patient_name,patient_gender,patient_date_start,patient_dept,patient_area,patient_doc_id,patient_age,patient_condition,patient_phone)";
        return s;
        //在数据库查询中，需要用到(列名1，列名2，列名3...)

    }


}
