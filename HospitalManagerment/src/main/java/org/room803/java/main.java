package org.room803.java;

import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        SQL sql1 =   new SQL();


        // 模拟表格封装成json测试，封装在source//.json
        ResultSet r1 =  sql1.ShowTable("Ward");
        ToJson.resultSetToJson(r1,"Ward");
        ResultSet r2 =  sql1.ShowTable("Patient");
        ToJson.resultSetToJson(r2,"Patient");
        ResultSet r3 =  sql1.ShowTable("Medicine");
        ToJson.resultSetToJson(r3,"Medicine");
        ResultSet r4 =  sql1.ShowTable("Doctor");
        ToJson.resultSetToJson(r4,"Doctor");
        ResultSet r5 =  sql1.ShowTable("Med_instruments");
        ToJson.resultSetToJson(r5,"Med_instruments");
        ResultSet r6 =  sql1.ShowTable("Nurse");
        ToJson.resultSetToJson(r6,"Nurse");



    }

}
