package com.yinhe.dama.web.controller;

import com.alibaba.druid.util.StringUtils;
import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataModule;
import com.yinhe.dama.entity.KeyWord;
import com.yinhe.dama.service.DataModuleService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName DataModuleController
 * @Description TODO
 * @Author lc
 * @Date 2020/4/1 0001 10:03
 * @Version 1.0
 */
@Controller
@RequestMapping("/datamodule")
public class DataModuleController {

    @Resource
    DataModuleService dataModuleService;
    static final KeyWord keyword = new KeyWord();

    /**
     * 多条件分页查询
     * @param
     * @return
     */
    @RequestMapping("/selectDaco")
    public void selectDamo(@RequestParam(value = "limit",required = false)int limit,
                           @RequestParam(value = "page",required = false)int page,
                           DataModule dataModule, HttpServletResponse response, HttpServletRequest request) throws Exception{
        if(Encapsulation.getIs(keyword.getModu_sel(),request)){
            dataModule.setPageSize(limit);
            dataModule.setPage((page-1) * limit);

            if(StringUtils.isEmpty(dataModule.getModule_number())){
                dataModule.setModule_number(null);
            }
            if(StringUtils.isEmpty(dataModule.getPlatform_id())){
                dataModule.setPlatform_id(null);
            }
            if(StringUtils.isEmpty(dataModule.getConam())){
                dataModule.setConam(null);
            }
            if(StringUtils.isEmpty(dataModule.getLast_time())){
                dataModule.setLast_time(null);
            }else{
                dataModule.setEndtime(dataModule.getLast_time().substring(22,41));
                dataModule.setLast_time(dataModule.getLast_time().substring(0,19));
            }
            List<DataModule> cslist = dataModuleService.selectDamo(dataModule);
            int a = dataModuleService.selectCont(dataModule);
            JSONObject jsonObject = Encapsulation.getJsonObject(cslist,a);
            Encapsulation.write(response, jsonObject);
        }else{
            JSONObject jsonObject = Encapsulation.getJsonObject(null,-1);
            Encapsulation.write(response, jsonObject);
        }

    }
}
