package com.yinhe.dama.web.controller;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.KeyWord;
import com.yinhe.dama.service.DataAccountService;
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
 * @Description 账号Controller
 * @Author lc
 * @Date 2020/3/16 0016 11:05
 * @Version 1.0
 */
@Controller
@RequestMapping("/DataAccount")
public class DataAccountController {

    @Resource
    DataAccountService dataAccountService;
    static final KeyWord keyword = new KeyWord();
    /**
     * 多条件分页查询
     * @param
     * @return
     */
    @RequestMapping("/selectDaac")
    public void selectDaac(@RequestParam(value = "limit",required = false)int limit,
                           @RequestParam(value = "page",required = false)int page,
                           DataAccount dataAccount, HttpServletResponse response,HttpServletRequest request) throws Exception{

        if(Encapsulation.getIs(keyword.getAcco_sel(),request)){

        }else{
            JSONObject jsonObject = Encapsulation.getJsonObject(null,-1);
            Encapsulation.write(response, jsonObject);
        }
        dataAccount.setPageSize(limit);
        dataAccount.setPage((page-1) * limit);

        if(StringUtils.isEmpty(dataAccount.getAccount())){
            dataAccount.setAccount(null);
        }
        if(StringUtils.isEmpty(dataAccount.getU_name())){
            dataAccount.setU_name(null);
        }

        List<DataAccount> cslist = dataAccountService.selectDaac(dataAccount);
        int a = dataAccountService.selectCont(dataAccount);
        JSONObject jsonObject = Encapsulation.getJsonObject(cslist,a);
        Encapsulation.write(response, jsonObject);
    }

    /**查询用户账号数*/
    @RequestMapping("/selectAcuid")
    public synchronized void selectAcuid(DataAccount dataAccount, HttpServletResponse response) throws Exception {
            int a = dataAccountService.selectAcuid(dataAccount);
            JSONObject jsonObject = Encapsulation.getJsonObj(a);
            Encapsulation.write(response, jsonObject);
    }

    /**
     * 添加
     * @param
     * @return
     */
    @RequestMapping("/addAcco")
    public synchronized void addAcco(DataAccount dataAccount, HttpServletResponse response, HttpServletRequest request) throws Exception {

        if(Encapsulation.getIs(keyword.getAcco_add(),request)){
                int Is  = dataAccountService.addAcco(dataAccount,request);
                if(Is > 0){
                    //成功
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    //未成功
                    JSONObject jsonObject = Encapsulation.getJsonObj(0);
                    Encapsulation.write(response, jsonObject);
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
    @RequestMapping("/deleteacc")
    public void deleteacc(HttpServletResponse response, String ids,HttpServletRequest request) throws Exception{

        if(Encapsulation.getIs(keyword.getAcco_del(),request)){
            int Is  = dataAccountService.deleteAcco(ids,request);
            if(Is > 0){
                //成功
                JSONObject jsonObject = Encapsulation.getJsonObj(1);
                Encapsulation.write(response, jsonObject);
            }else{
                //失败
                JSONObject jsonObject = Encapsulation.getJsonObj(0);
                Encapsulation.write(response, jsonObject);
            }
        }else {
            JSONObject jsonObject = Encapsulation.getJsonObj(-1);
            Encapsulation.write(response, jsonObject);
        }
    }

    /**
     * 修改
     * @param dataAccount
     * @param response
     * @throws Exception
     */
    @RequestMapping("updateAcco")
    public synchronized void updateAcco(DataAccount dataAccount,HttpServletResponse response,HttpServletRequest request) throws Exception {

        if(Encapsulation.getIs(keyword.getAcco_upd(),request)){

            if(dataAccountService.selectacQc(dataAccount) > 0){
                JSONObject jsonObject = Encapsulation.getJsonObj(2);
                Encapsulation.write(response, jsonObject);
            }else{
                int Is =  dataAccountService.updateAccou(dataAccount,request);
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

    /**
     * 导出
     * @throws
     */
    @RequestMapping("/exportaco")
    public void exportaco(DataAccount dataAccount,HttpServletResponse response,HttpServletRequest request) throws Exception {
        if(Encapsulation.getIs(keyword.getAcco_exp(),request)){
            if(StringUtils.isEmpty(dataAccount.getU_name())){
                dataAccount.setU_name(null);
            }
            if(StringUtils.isEmpty(dataAccount.getAccount())){
                dataAccount.setAccount(null);
            }
            dataAccountService.exportaco(dataAccount,response,request);
        }else{
            JSONObject jsonObject = Encapsulation.getJsonObj(-1);
            Encapsulation.write(response, jsonObject);
        }
    }

    /**
     * 重置密码
     * @param dataAccount
     * @param response
     * @throws Exception
     */
    @RequestMapping("updatePas")
    public synchronized void updatePas(DataAccount dataAccount,HttpServletResponse response,HttpServletRequest request) throws Exception {

        if(Encapsulation.getIs(keyword.getAcco_res(),request)){

                int Is =  dataAccountService.updatePas(dataAccount,request);
                if(Is > 0){
                    //成功
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    //失败
                    JSONObject jsonObject = Encapsulation.getJsonObj(0);
                    Encapsulation.write(response, jsonObject);
                }
        }else{
            JSONObject jsonObject = Encapsulation.getJsonObj(-1);
            Encapsulation.write(response, jsonObject);
        }
    }

    /**
     * 编辑权限
     * @param ids
     * @param response
     * @throws Exception
     */
    @RequestMapping("editAuth")
    public synchronized void editAuth(String ids,Long accid,HttpServletResponse response,HttpServletRequest request) throws Exception {
        if(Encapsulation.getIs(keyword.getAcco_edi(),request)){
            int Is =  dataAccountService.editAuth(ids,accid,request);
            System.err.println("con I :  "+Is);
            if(Is > 0){
                //成功
                JSONObject jsonObject = Encapsulation.getJsonObj(1);
                Encapsulation.write(response, jsonObject);
            }else{
                //失败
                JSONObject jsonObject = Encapsulation.getJsonObj(0);
                Encapsulation.write(response, jsonObject);
            }
        }else{
            JSONObject jsonObject = Encapsulation.getJsonObj(-1);
            Encapsulation.write(response, jsonObject);
        }
    }
}
