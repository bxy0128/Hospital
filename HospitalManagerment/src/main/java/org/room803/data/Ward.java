package com.hospital.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //自动设置方法
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
public class Ward {
    /**
     * 病房的信息_数据库各列名
     */
    private int ward_id;//病床编号
    private int patient_inhospital_id;//住院病人编号
    private int ward_dept_id;//病床科室

    private int ward_bednum;//病床床位
    private String patient_date_start;//入院日期



    public String set(){
        String s = new String();
        s = "("+ ward_id+","+patient_inhospital_id+","+ward_dept_id+","+ ward_bednum+","+ patient_date_start+")" ;
        //在数据库查询中，需要用到的(值1，值2，值3...)
        return s;
    }
    public String list(){
        String s = new String();
        s = "(patient_inhospital_id,ward_dept_id,ward_id,ward_bednum,patient_date_start)" ;
        return s;
        //在数据库查询中，需要用到(列名1，列名2，列名3...)

    }


}


