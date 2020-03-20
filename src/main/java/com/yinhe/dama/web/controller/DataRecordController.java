package com.yinhe.dama.web.controller;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataRecord;
import com.yinhe.dama.entity.KeyWord;
import com.yinhe.dama.service.DataRecordService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName DataAccountController
 * @Description 日志
 * @Author lc
 * @Date 2020-3-18 11:38:40
 * @Version 1.0
 */
@Controller
@RequestMapping("/DataRecord")
public class DataRecordController {

    @Resource
    DataRecordService dataRecordService;
    static final KeyWord keyword = new KeyWord();
    /**
     * 多条件分页查询
     * @param
     * @return
     */
    @RequestMapping("/selectDare")
    public void selectDare(@RequestParam(value = "limit",required = false)int limit,
                           @RequestParam(value = "page",required = false)int page,
                           DataRecord dataRecord, HttpServletResponse response, HttpServletRequest request) throws Exception{
        if(Encapsulation.getIs(keyword.getReco_sel(),request)){

            dataRecord.setPageSize(limit);
            dataRecord.setPage((page-1) * limit);

            if(StringUtils.isEmpty(dataRecord.getRecname())){
                dataRecord.setRecname(null);
            }
            if(StringUtils.isEmpty(dataRecord.getRecaccount())){
                dataRecord.setRecaccount(null);
            }

            List<DataRecord> cslist = dataRecordService.selectDare(dataRecord);
            int a = dataRecordService.selectCont(dataRecord);
            JSONObject jsonObject = Encapsulation.getJsonObject(cslist,a);
            Encapsulation.write(response, jsonObject);
        }else{
            JSONObject jsonObject = Encapsulation.getJsonObject(null,-1);
            Encapsulation.write(response, jsonObject);
        }
    }

    /**
     * 导出
     * @throws
     */
    @RequestMapping("/exportrec")
    public void exportrec(DataRecord dataRecord,HttpServletResponse response,HttpServletRequest request) throws Exception {
        if(Encapsulation.getIs(keyword.getReco_exp(),request)){
            if(StringUtils.isEmpty(dataRecord.getRecname())){
                dataRecord.setRecname(null);
            }
            if(StringUtils.isEmpty(dataRecord.getRecaccount())){
                dataRecord.setRecaccount(null);
            }
            dataRecordService.exportrec(dataRecord,response,request);

        }else{
            JSONObject jsonObject = Encapsulation.getJsonObj(-1);
            Encapsulation.write(response, jsonObject);
        }
    }

}
