/**
 * User: zlin
 * Date: 2019/8/4
 * Time: 18:30
 **/

package com.zl.demotest.utils;

import com.alibaba.fastjson.JSONObject;

public class MyResult {
   public static void MyResultUtil(JSONObject obj,Integer status,String message,Object data){
       obj.put("status",status);
       obj.put("message",message);
       obj.put("data",data);
   }
    public static JSONObject MyResultUtil(Integer status,String message,Object data){
        JSONObject obj = new JSONObject();
        obj.put("status",status);
        obj.put("message",message);
        obj.put("data",data);
        return obj;
    }

    /**
     * 封装layui表格的ajax数据形式
     * @param status
     * @param msg
     * @param count
     * @param data
     * @return
     */
   public static JSONObject result(Integer status,String msg,Long count,Object data){
       JSONObject o = new JSONObject();
       o.put("status",status);
       o.put("msg",msg);
       o.put("count",count);
       o.put("data",data);
       return o;
   }

    /**
     * 封装常规ajax数据返回形式
     * @param status
     * @param msg
     * @param data
     * @return
     */
   public static JSONObject result(Integer status,String msg,Object data){
       JSONObject o = new JSONObject();
       o.put("status",status);
       o.put("msg",msg);
       o.put("data",data);
       return o;
   }

    public static JSONObject result(Integer status,Object data){
        JSONObject o = new JSONObject();
        o.put("status",status);
        o.put("data",data);
        return o;
    }


    public static JSONObject result(Integer status){
        JSONObject o = new JSONObject();
        o.put("status",status);
        return o;
    }

}
