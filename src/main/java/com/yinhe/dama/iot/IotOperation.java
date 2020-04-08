package com.yinhe.dama.iot;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @ClassName IotOperation
 * @Description 调用IOT接口
 * @Author lc
 * @Date 2020/4/1 0001 16:38
 * @Version 1.0
 */
public class IotOperation {

    /**注册*/
    public synchronized JSONObject register(String imei, String url, String appid){

        try {
            if(StringUtils.isEmpty(imei)){
            }
            if(StringUtils.isEmpty(url)){
            }
            String dome = "?numbers="+Math.random();

            HashMap<String, String> params =new HashMap<>();

            params.put("app", appid);
            params.put("cmd", "registerdevice");
            params.put("devices", "["+imei+"]");
            ClientUtil clientUtil = new ClientUtil();
            String resp = clientUtil.doPost(url,params);
            System.err.println(resp+"/--/");
            JSONObject json_test = JSONObject.fromObject(resp);
            System.err.println(json_test+"/-/");
            JSONArray devices=json_test.optJSONArray("devices");
            JSONObject jo = devices.getJSONObject(0);
            return jo;
        }catch (Exception e){
            System.err.println("无数据返回");
        }
        return null;
    }
}
