package org.room803.java;





import org.room803.data.*;

import java.sql.*;

public class SQL {
     static Connection conn;
    static Statement sql;

         {
               try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //加载数据库驱动
               } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
               }
               String url;
               try {
                   String userName;
                    String userPwd;
                    // 连接数据库的语句
                    url = "jdbc:sqlserver://192.168.1.106:1433;DatabaseName=Hospital";//数据库连接地址

                    userName = "sa";//数据库连接名
                    userPwd = "123456";//数据库连接密码
                    conn = DriverManager.getConnection(url, userName, userPwd);
                    sql = conn.createStatement();
                    System.out.println("数据库连接成功");
               } catch (SQLException exp) {
                    System.out.println(exp);
                    System.out.println("数据库连接失败");
               }


          }

  public  ResultSet ShowTable(String s) {//显示表格
      /*将数据库封装成一个ResultSet类型
      * s是表名
      */
      ResultSet rs;
      {
          try {
              rs = sql.executeQuery("select * from "+s);
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
      }
      return rs;
  }
    public  void Update(String s1,int i, String...values) throws SQLException {//修改
        /**
         * s1代表需要修改的表名
         * values代表数据
         * i代表数据的id
         */
        int alter;
        switch (s1){
            case "Patient"://病人修改
                alter = sql.executeUpdate("UPDATE " + s1 + " set " +
                            "patient_name" + " = "+ "'"+values[0]+"'"+ ","+"patient_gender"+ " = "+ "'"+values[1]+"'"
                      + ","+"patient_date_start"+ " = "+ "'"+values[2]+"'"+ ","+"patient_dept"+ " = "+ "'"+values[3]+"'"
                      + ","+"patient_area"+ " = "+ "'"+values[4]+"'"+ ","+"patient_doc_id"+ " = "+ values[5]
                      + ","+"patient_age"+ " = "+ values[6]+ ","+"patient_condition"+ " = "+ "'"+values[7]+"'"
                      + ","+"patient_phone"+ " = "+ "'"+values[8]+"'"
                        + " where "+s1+ "_id"+  " ="+i );
            case "Doctor"://医生修改
                alter = sql.executeUpdate("UPDATE "+s1 + " set "+"doctor_name" + " = " + "'"+values[0] +"'"+","+
                        "doctor_gender" + "=" + "'"+values[1] +"'"+","+"doctor_dept_id" + "=" + values[2] +","
                        +"doctor_phone" + "=" + "'"+values[0] +"'"+
                        "where" + s1 + "_id" + " = " + i);
            case "Ward"://病床修改
                alter = sql.executeUpdate("UPDATE "+ s1 +" set "+"patient_inhospital_id"+ " = "+ values[0]+"," +
                        "ward_dept_id"+ " = "+ values[1]+","+"ward_bednum"+ " = "+ values[2]+","+
                        "patient_date_start"+ " = "+ "'"+values[2]+"'" +
                        " where " + s1 +"_id" + "=" + i );
            case "Medicine"://药物修改
                alter = sql.executeUpdate("UPDATE " + s1 + " set "+ "medicine_name" + "'"+ values[0]+"'"+","
                        + "medicine_price" + values[0] +","+ "medicine_quantify" +  values[0] +
                        " where " + s1 +"_id" + "=" + i );
            case "Med_Instruments"://医疗仪器修改
                alter = sql.executeUpdate("UPDATE " + s1 + " set "+ "med_instruments_name" + "'"+ values[0]+"'"+","
                        + "med_instruments_price" + values[0] +","+ "med_instruments_quantity" +  values[0] +
                        " where " + s1 +"_id" + "=" + i);

        }

    }
    public  void Insert(String s1, int i, String...values) throws SQLException {//增加
        /**
         * s1是表名
         * i是需要设置的id名
         * values是需要增加的数值列表，用String[]数组
         * sql1.Insert("Login",0,"996","11111");//增加-测试
         */
      int alter;
    switch (s1){
        case "Patient"://病人
           Patient p1 = new Patient(i,values[0],values[1],values[2],values[3],
                                   values[4],values[5],
                                    values[6],values[7],values[8] );
           alter = sql.executeUpdate("INSERT into " + s1 + p1.list() + " values " + p1.set());//list已知，set需设置
        case "Login"://登录
            login l1 = new login(values[0],values[1] );
           alter = sql.executeUpdate("INSERT into " + s1 + l1.list() + " values " + l1.set());//list已知，set需设置
            System.out.println("增加成功");
        case "Ward"://病床
            Ward w1 = new Ward(i,values[0],values[1],values[2],values[3]);
            alter = sql.executeUpdate("INSERT into " + s1 + w1.list() + " values " + w1.set());//list已知，set需设置
        case "Doctor"://医生
            Doctor d1 = new Doctor(i,values[0],values[1],values[3],values[4]);
            alter = sql.executeUpdate("INSERT into " + s1 + d1.list() + " values " + d1.set());//list已知，set需设置
        case "Medicine"://药物
            Medicine m1 = new Medicine(i,values[0],values[1],values[2]);
            alter = sql.executeUpdate("INSERT into " + s1 + m1.list() + " values " + m1.set());//list已知，set需设置
        case "Med_Instruments"://医疗仪器
            Med_Instruments m2 = new Med_Instruments(i,values[0],values[1],values[2]);
            alter = sql.executeUpdate("INSERT into " + s1 + m2.list() + " values " + m2.set());//list已知，set需设置

    }

    }
    public void delete(String s1,String s2,int i) throws SQLException {
        /**
         * s1是表名
         * s2是提交的列名id
         * s3是提交的id
         * sql1.delete("Login","Login_id","996");//删除-测试
         */
        int alter;
        alter = sql.executeUpdate("DELETE FROM "+s1+" WHERE "+s2+" = "+i);
        System.out.println("删除成功");
    }
}




