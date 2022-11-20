package org.room803.java;
import org.room803.data.*;
import java.sql.*;
public class SQL {
     static Connection conn;
    static Statement sql;
//连接数据库
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
                    url = "jdbc:sqlserver://192.168.0.108:1433;DatabaseName=Hospital";//数据库连接地址




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
    //显示表格
  public  ResultSet ShowTable(String s) {
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
  //登陆检测
  public int LoginCheck(String s1,String s2) throws SQLException {
      ResultSet rs;
      System.out.println(s1);
      System.out.println(s2);
      {
          try {
              rs = sql.executeQuery("SELECT * FROM Login WHERE Login_name = " + "'"+s1 + "'"
                      + "AND Password = " + "'" +s2 + "'");
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
      }
      int count = 0;
      while(rs.next()) {
          count = count + 1;
      }
      System.out.println(count);
      System.out.println("count");


      return count;
  }
    //修改功能
    public  void Update(String s1,int i, String...values) throws SQLException {
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
                        + " WHERE "+" patient_id"+  " ="+i );
                break;

            case "Doctor"://医生修改
                alter = sql.executeUpdate("UPDATE "+s1 + " set "+" doctor_name" + " = " + "'"+values[0] +"'"+","+
                        "doctor_gender" + "=" + "'"+values[1] +"'"+","+"doctor_dept" + "=" +"'"+ values[2]+"'" +","
                        +"doctor_phone" + "=" + "'"+values[3] +"'"+
                        " where " + s1 + "_id" + " = " + i);
                break;
            case "Ward"://病床修改
                alter = sql.executeUpdate("UPDATE "+ s1 +" set "+
                        "ward_bednum"+ " = "+ values[0]+"," +
                        "patient_date_start"+ " = "+"'"+ values[1]+
                        "'"+","+"patient_inhospital_id"+ " = "+ values[2]+","+
                        "ward_dept"+ " = "+ "'"+values[3]+"'" +"ward_doc_id"+values[4]+"ward_nurse_id"+values[5]+
                        " where " + s1 +"_id" + "=" + i );
                break;
            case "Medicine"://药物修改
                alter = sql.executeUpdate("UPDATE " + s1 + " set "+ "medicine_name" + " = "+"'"+ values[0]+"'"+","
                        + "medicine_price" + " = "+values[1] +","+ "medicine_quantity" + " = "+ values[2] +
                        " where " + s1 +"_id" + "=" + i );
                System.out.println(alter);
                break;
            case "Med_Instruments"://医疗仪器修改
                alter = sql.executeUpdate("UPDATE " + s1 + " set "+ "med_instruments_name" +  " = "+"'"+values[0]+"'"+","
                        + "med_instruments_price" + " = "+values[1] +","+ "med_instruments_quantity" +" = "+  values[2] +
                        " where " + s1 +"_id" + "=" + i);
                System.out.println(alter);
                break;
            case "ToOrder" ://医嘱修改
                alter = sql.executeUpdate("UPDATE " + s1 + " set "+
                        "order_id" +values[0]+","+
                        "patient_id" +values[1]+","+
                        "doctor_id" +values[2]+","+
                        "medicine_id" +values[3]+","+
                        "med_instruments_id" +values[4]+","+
                        "order_time" +"'"+values[5]+"'"+","+

                        " where " + "order" +"_id" + "=" + i);
                break;
            case "Nurse" :
                alter = sql.executeUpdate("UPDATE "+s1 + " set "+" nurse_name" + " = " + "'"+values[0] +"'"+","+
                        "nurse_gender" + "=" + "'"+values[1] +"'"+","+"nurse_dept" + "=" +"'"+ values[2]+"'" +","
                        +"nurse_phone" + "=" + "'"+values[3] +"'"+
                        " where " + s1 + "_id" + " = " + i);
                System.out.println("ok");
                break;

        }

    }

//增加功能
    public  void Insert(String s1, int i, String...values) throws SQLException {
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
           alter = sql.executeUpdate("INSERT into " + s1  + " values " + p1.set());//list已知，set需设置
            break;
        case "Login"://登录
            Login l1 = new Login(values[0],values[1] );
           alter = sql.executeUpdate("INSERT into " + s1  + " values " + l1.set());//list已知，set需设置
            System.out.println("增加成功");
            break;
        case "Ward"://病床
            Ward w1 = new Ward(i,values[0],values[1],values[2],values[3],values[4],values[5]);
            String s23 = "INSERT into " + s1  + " values " + w1.set();
            System.out.println(s23);
            alter = sql.executeUpdate("INSERT INTO " + s1  + " VALUES " + w1.set());//list已知，set需设置
            System.out.println(w1.set());
            break;
        case "Medicine"://药品
            Medicine m1 = new Medicine(i,values[0],values[1],values[2]);
            String s24 = "INSERT into " + s1  + " values " + m1.set();
            System.out.println(s24);
            alter = sql.executeUpdate("INSERT INTO " + s1  + " VALUES " + m1.set());//list已知，set需设置
            System.out.println(m1.set());
            break;
        case"Doctor"://医生
            Doctor d1 = new Doctor(i,values[0],values[1],values[2],values[3]);
            String s25 = "INSERT into " + s1  + " values " + d1.set();
            System.out.println(s25);
            alter = sql.executeUpdate("INSERT INTO " + s1  + " VALUES " + d1.set());//list已知，set需设置
            System.out.println(d1.set());
            break;
        case "Med_Instruments"://仪器
            Med_Instruments z1 = new Med_Instruments(i,values[0],values[1],values[2]);
            String s26 = "INSERT into " + s1  + " values " + z1.set();
            System.out.println(s26);
            alter = sql.executeUpdate("INSERT INTO " + s1  + " VALUES " + z1.set());//list已知，set需设置
            System.out.println(z1.set());
            break;
            case "ToOrder"://医嘱
            ToOrder t1 = new ToOrder(i,values[0],values[1],values[2],values[3],values[4]);
            String s27 = "INSERT into " + s1  + " values " + t1.set();
            System.out.println(s27);
            alter = sql.executeUpdate("INSERT INTO " + s1  + " VALUES " + t1.set());//list已知，set需设置
            System.out.println(t1.set());
            break;
            case "Nurse"://护士
            Nurse n1 = new Nurse(i,values[0],values[1],values[2],values[3]);
            String s28 = "INSERT into " + s1  + " values " + n1.set();
            System.out.println(s28);
            alter = sql.executeUpdate("INSERT INTO " + s1  + " VALUES " + n1.set());//list已知，set需设置
            System.out.println(n1.set());
            break;

    }

    }
    //删除功能
    public void delete(String s1,String s2,int i) throws SQLException {
        /**
         * s1是表名
         * s2是提交的列名id
         * s3是提交的id
         * sql1.delete("Login","Login_id","996");//删除-测试
         */
        int alter;
        alter = sql.executeUpdate("DELETE FROM "+s1+" WHERE "+s2+" = "+ i);
        System.out.println("删除成功");
    }
    public ResultSet Search(String s1,int i) throws SQLException {
        int alter;
        ResultSet rs = null;
        switch (s1) {
            case "Patient": {
                try {
                    rs = sql.executeQuery("SELECT * FROM " + s1 + " WHERE " + s1 + "_id = " + i);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            break;
            case "Doctor": {
                try {
                    rs = sql.executeQuery("SELECT * FROM " + s1 + " WHERE " + s1 + "_id = " + i);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            break;
            case "Medicine": {
                try {
                    rs = sql.executeQuery("SELECT * FROM " + s1 + " WHERE " + s1 + "_id = " + i);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            break;
            case "Med_Instruments": {
                try {
                    rs = sql.executeQuery("SELECT * FROM " + s1 + " WHERE " + s1 + "_id = " + i);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            break;
            case "Ward": {
                try {
                    rs = sql.executeQuery("SELECT * FROM " + s1 + " WHERE " + s1 + "_id = " + i);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            break;
            case "nurse": {
                try {
                    rs = sql.executeQuery("SELECT * FROM " + s1 + " WHERE " + s1 + "_id = " + i);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }



        }
        return rs;
    }

}




