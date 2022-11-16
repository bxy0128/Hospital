package org.room803.java;


import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.room803.data.Layui;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class ToJson {
    /*
    * 将数据库中的表提取出来，打包成json文件
    * 示例：
    *  ResultSet r1 =  sql1.ShowTable("Login");
        ToJson.resultSetToJson(r1,"Login");
    */
    public static String resultSetToJson(ResultSet rs,String s) throws SQLException, JSONException
    {
        // json数组
        JSONArray array = new JSONArray();
        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();
            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName =metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }
        Layui patient = new Layui();
        patient.setCode(0);//固定格式
        patient.setMsg("success");//固定格式
        patient.setCount(100);//数据的总个数
        patient.setData(JSONArray.fromObject(array));//查询的List集合
        Gson g = new Gson();
        String s1 = g.toJson(patient);//json文件
        System.out.println(array);
        String s99 = array.toString();
        System.out.println(s99);
        System.out.println("测试");
        System.out.println(s1);
        JSONObject jsonObject = JSONObject.fromObject(s1);
        String s2 = "D:\\桌面\\Hospital\\HospitalManagerment\\web\\json\\"+ s + ".json";
        File file = new File(s2);//将获取到的数据库列表传输到source文件夹内
        try {
            FileUtils.write(file, jsonObject.toString(), "utf-8", false);
            System.out.println("ok");
            System.out.println(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

return s99;

    }

}
