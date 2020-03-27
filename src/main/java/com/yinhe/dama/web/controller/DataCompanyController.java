package com.yinhe.dama.web.controller;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataCompany;
import com.yinhe.dama.entity.DataUser;
import com.yinhe.dama.entity.KeyWord;
import com.yinhe.dama.service.DataCompanyService;
import com.yinhe.dama.service.DataUserService;
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
 * @ClassName DataCompanyController
 * @Description 公司controller
 * @Author lc
 * @Date 2020-3-26 13:56:47
 * @Version 1.0
 */
@Controller
@RequestMapping("/DataCompany")
public class DataCompanyController {

    @Resource
    DataCompanyService dataCompanyService;
    @Resource
    DataUserService dataUserService;

    static final KeyWord keyword = new KeyWord();
    /**
     * 多条件分页查询
     * @param
     * @return
     */
    @RequestMapping("/selectDaco")
    public void selectDaco(@RequestParam(value = "limit",required = false)int limit,
                           @RequestParam(value = "page",required = false)int page,
                           DataCompany dataCompany, HttpServletResponse response, HttpServletRequest request) throws Exception{
        if(Encapsulation.getIs(keyword.getComp_sel(),request)){
            dataCompany.setPageSize(limit);
            dataCompany.setPage((page-1) * limit);

            if(StringUtils.isEmpty(dataCompany.getConame())){
                dataCompany.setConame(null);
            }
            List<DataCompany> cslist = dataCompanyService.selectDaco(dataCompany);
            int a = dataCompanyService.selectCont(dataCompany);
            JSONObject jsonObject = Encapsulation.getJsonObject(cslist,a);
            Encapsulation.write(response, jsonObject);
        }else{
            JSONObject jsonObject = Encapsulation.getJsonObject(null,-1);
            Encapsulation.write(response, jsonObject);
        }

    }

    /**
     * 添加
     * @param
     * @return
     */
    @RequestMapping("/addDaco")
    public synchronized void addDaco(DataCompany dataCompany,HttpServletResponse response,HttpServletRequest request) throws Exception {

        if(Encapsulation.getIs(keyword.getComp_add(),request)){
            DataCompany dc = new DataCompany();
            dc.setConame(dataCompany.getConame());
            if(dataCompanyService.selectcoQc(dc) > 0){
                JSONObject jsonObject = Encapsulation.getJsonObj(2);
                Encapsulation.write(response, jsonObject);
            }else{
                int Is  = dataCompanyService.addDaco(dataCompany,request);
                if(Is > 0){
                    //成功
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    //未成功
                    JSONObject jsonObject = Encapsulation.getJsonObj(0);
                    Encapsulation.write(response, jsonObject);
                }
            }

        }else{
            JSONObject jsonObject = Encapsulation.getJsonObj(-1);
            Encapsulation.write(response, jsonObject);
        }

    }

    /**
     * 删除
     * @param response
     * @param ids 主键集合
     * @throws
     */
    @RequestMapping("/deleteDaco")
    public void deleteDaco(HttpServletResponse response, String ids,HttpServletRequest request) throws Exception{

        if(Encapsulation.getIs(keyword.getComp_del(),request)){
            List<DataUser> daac= dataUserService.selectCoid(ids);
            if(daac.size() > 0){
                JSONObject jsonObject = Encapsulation.getJsonObject(daac,0);
                Encapsulation.write(response, jsonObject);
            }else{
                int Is  = dataCompanyService.deleteDaco(ids,request);
                if(Is > 0){
                    //成功
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    //失败
                    JSONObject jsonObject = Encapsulation.getJsonObj(0);
                    Encapsulation.write(response, jsonObject);
                }
            }
        }else {
            JSONObject jsonObject = Encapsulation.getJsonObj(-1);
            Encapsulation.write(response, jsonObject);
        }

    }

    /**
     * 修改
     * @param dataCompany
     * @param response
     * @throws Exception
     */
    @RequestMapping("updateDaco")
    public synchronized void updateDaco(DataCompany dataCompany,HttpServletResponse response,HttpServletRequest request) throws Exception {

        if(Encapsulation.getIs(keyword.getComp_upd(),request)){

            DataCompany daone = new DataCompany();
            daone.setConame(dataCompany.getConame());
            daone.setCoid(dataCompany.getCoid());
            if(dataCompanyService.selectcoQc(daone) > 0){
                JSONObject jsonObject = Encapsulation.getJsonObj(2);
                Encapsulation.write(response, jsonObject);
            }else{
                int Is =  dataCompanyService.updateDaco(dataCompany,request);
                if(Is > 0){
                    //成功
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    //失败
                    JSONObject jsonObject = Encapsulation.getJsonObj(0);
                    Encapsulation.write(response, jsonObject);
                }
            }
        }else{
            JSONObject jsonObject = Encapsulation.getJsonObj(-1);
            Encapsulation.write(response, jsonObject);
        }
    }

}
