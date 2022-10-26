package com.hospital.Data;
import com.alibaba.fastjson2.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //自动设置方法
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
public class Layui {
    /**
     * 将json封装成Layui格式
     */
    int code;
    String msg;
    int count;
    JSONArray data;

}