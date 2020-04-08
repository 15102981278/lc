package com.yinhe.dama.web.controller;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataPlatformApp;
import com.yinhe.dama.entity.DataRegisterPlatform;
import com.yinhe.dama.service.DataPlatformAppService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName DataPlatformAppController
 * @Description 平台地址
 * @Author lc
 * @Date 2020/4/7 0007 16:09
 * @Version 1.0
 */
@Controller
@RequestMapping("/platformApp")
public class DataPlatformAppController {

    @Resource
    DataPlatformAppService dataPlatformAppService;

    @RequestMapping("/selectPlarformApp")
    public void selectPlarformApp(HttpServletResponse response) throws Exception{

            List<DataPlatformApp> cslist = dataPlatformAppService.selectPlarformApp();
            JSONObject jsonObject = Encapsulation.getJsonObject(cslist,0);
            Encapsulation.write(response, jsonObject);
    }

    @RequestMapping("/selectPlarform")
    public void selectPlarform(HttpServletResponse response) throws Exception{

        List<DataRegisterPlatform> cslist = dataPlatformAppService.selectPlarform();
        JSONObject jsonObject = Encapsulation.getJsonObject(cslist,0);
        Encapsulation.write(response, jsonObject);
    }

}
