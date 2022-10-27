package org.room803.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //自动设置方法
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
public class login {
    private String Login_name;
    private String Password;


    public String set() {
        String s;
        s = "(" + Login_name + "," + Password + ")";
        return s;
        //在数据库查询中，需要用到(列名1，列名2，列名3...)

    }

    public String list() {
        String s;
        s = "(Login_name,Password)";
        return s;
        //在数据库查询中，需要用到的(值1，值2，值3...)

    }
}
