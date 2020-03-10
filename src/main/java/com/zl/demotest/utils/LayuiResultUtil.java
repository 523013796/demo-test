/**
 * User: zlin
 * Date: 2019/8/3
 * Time: 21:51
 **/

package com.zl.demotest.utils;

import com.alibaba.fastjson.JSONObject;

public class LayuiResultUtil {
    private Integer code;
    private String msg;
    private long count;
    private Object data;



    public static void putJSON(JSONObject obj, Long count, Object data){
        obj.put("code",200);
        obj.put("msg","");
        obj.put("count",count);
        obj.put("data",data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
