package com.yinhe.dama.web.controller;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataModule;
import com.yinhe.dama.iot.IotOperation;
import com.yinhe.dama.service.DataModuleService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ScannController
 * @Description 模块扫码
 * @Author lc
 * @Date 2020/4/2 0002 9:24
 * @Version 1.0
 */
@Controller
@RequestMapping("/scann")
public class ScannController {



    IotOperation iotOperation = new IotOperation();
    DataModule dataModule= new DataModule();
    @Resource
    DataModuleService dataModuleService;
    @RequestMapping("/modulescann")
    public synchronized void modulescann(String imei, String company, String url, String appid, HttpServletResponse response, HttpServletRequest r) throws Exception {
        System.err.println("注册传入参数: " +imei +"--+--"+company+"--+--"+url+"--+--"+appid);
        JSONObject jsonObject = iotOperation.register(imei,url,appid);
        System.err.println(jsonObject);
        if(jsonObject == null){
            JSONObject jsonOb = Encapsulation.getJsonObj(-1);
            Encapsulation.write(response, jsonOb);
        }else if(jsonObject.size() < 5){
            JSONObject jsonOb = Encapsulation.getJsonObj(0);
            Encapsulation.write(response, jsonOb);
        }else{
            String commid = jsonObject.optString("deviceid");
            String psk = jsonObject.optString("psk");
            dataModule.setModule_number(imei);
            dataModule.setPlatform_id(commid);
            dataModule.setModule_psk(psk);
            dataModule.setComid(Long.valueOf(company));
            int i = dataModuleService.scaddModule(dataModule,r);
            if(i > 0){
                JSONObject jsonOb = Encapsulation.getJsonObj(1);
                Encapsulation.write(response, jsonOb);
            }else{
                JSONObject jsonOb = Encapsulation.getJsonObj(10);
                Encapsulation.write(response, jsonOb);
            }

        }
    }

}
