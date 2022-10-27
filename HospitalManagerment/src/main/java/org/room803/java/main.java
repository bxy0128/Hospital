package org.room803.java;

import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        SQL sql1 =   new SQL();


        // 模拟表格封装成json测试，封装在source//.json
        ResultSet r1 =  sql1.ShowTable("Patient");
        ToJson.resultSetToJson(r1,"Patient");


    }

}
