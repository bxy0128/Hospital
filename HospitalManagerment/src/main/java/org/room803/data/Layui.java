package org.room803.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.json.JSONArray;

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

    public JSONArray getData() {
        return data;
    }
    public void setData(JSONArray data){
        this.data = data;
    }

}