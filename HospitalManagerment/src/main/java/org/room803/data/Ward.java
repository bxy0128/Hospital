package org.room803.data;

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
    private String dept_bednum;//病床床位
    private String patient_date_start;//入院日期
    private String patient_inhospital_id;//住院病人编号
    private String ward_dept;//病床科室
    private String  ward_doc_id;//病床医生编号
    private String ward_nurse_id;//病床护士编号




    public String set() {
        String s;


        s = "("+ward_id+","+dept_bednum+","+"'"+patient_date_start+"'"+","+patient_inhospital_id+","+
                "'" +ward_dept+ "'" +ward_doc_id+ward_nurse_id+")";

        //在数据库查询中，需要用到的(值1，值2，值3...)
        return s;
    }

    public String list() {
        String s;
        s = "(ward_id,dept_bednum,patient_date_start,patient_inhospital_id,ward_dept,ward_doc_id,ward_nurse_id)";
        return s;
        //在数据库查询中，需要用到(列名1，列名2，列名3...)

    }


}


