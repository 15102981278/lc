package com.yinhe.dama.web.controller;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.DataCompany;
import com.yinhe.dama.entity.DataUser;
import com.yinhe.dama.entity.KeyWord;
import com.yinhe.dama.service.DataAccountService;
import com.yinhe.dama.service.DataCompanyService;
import com.yinhe.dama.service.DataUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName DataUserController
 * @Description 用户controller
 * @Author lc
 * @Date 2020/3/12 0012 14:17
 * @Version 1.0
 */
@Controller
@RequestMapping("/DataUser")
public class  DataUserController {

    @Resource
    DataUserService dataUserService;
    @Resource
    DataAccountService dataAccountService;
    @Resource
    DataCompanyService dataCompanyService;
    static final KeyWord keyword = new KeyWord();
    /**
     * 多条件分页查询
     * @param
     * @return
     */
    @RequestMapping("/selectDaus")
    public void selectDaus(@RequestParam(value = "limit",required = false)int limit,
                           @RequestParam(value = "page",required = false)int page,
                           DataUser dataUser, HttpServletResponse response, HttpServletRequest request) throws Exception{
        if(Encapsulation.getIs(keyword.getUser_sel(),request)){
            dataUser.setPageSize(limit);
            dataUser.setPage((page-1) * limit);

            if(StringUtils.isEmpty(dataUser.getName())){
                dataUser.setName(null);
            }
            if(StringUtils.isEmpty(dataUser.getCompany())){
                dataUser.setCompany(null);
            }
            List<DataUser> cslist = dataUserService.selectDaus(dataUser);
            int a = dataUserService.selectCont(dataUser);
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
    @RequestMapping("/addDaus")
    public synchronized void addDaus(DataUser dataUser,HttpServletResponse response,HttpServletRequest request) throws Exception {

        if(Encapsulation.getIs(keyword.getUser_add(),request)){
            String time  = Encapsulation.getTime();
            dataUser.setCreattime(time);
            DataUser daone = new DataUser();
            daone.setPhone(dataUser.getPhone());
            if(dataUserService.selectQc(daone) > 0){
                JSONObject jsonObject = Encapsulation.getJsonObj(2);
                Encapsulation.write(response, jsonObject);
            }else{
                int Is  = dataUserService.addDaus(dataUser,request);
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
    @RequestMapping("/deletecount")
    public void deletecount(HttpServletResponse response, String ids,HttpServletRequest request) throws Exception{

        if(Encapsulation.getIs(keyword.getUser_del(),request)){
            List<DataAccount> daac= dataAccountService.selectUid(ids);
            if(daac.size() > 0){
                JSONObject jsonObject = Encapsulation.getJsonObject(daac,0);
                Encapsulation.write(response, jsonObject);
            }else{
                int Is  = dataUserService.deleteDaus(ids,request);
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
     * @param dataUser
     * @param response
     * @throws Exception
     */
    @RequestMapping("updateDaus")
    public synchronized void updateDaus(DataUser dataUser,HttpServletResponse response,HttpServletRequest request) throws Exception {

        if(Encapsulation.getIs(keyword.getUser_upd(),request)){

            DataUser daone = new DataUser();
            daone.setUid(dataUser.getUid());
            daone.setPhone(dataUser.getPhone());
            if(dataUserService.selectQc(daone) > 0){
                JSONObject jsonObject = Encapsulation.getJsonObj(2);
                Encapsulation.write(response, jsonObject);
            }else{
                int Is =  dataUserService.updateDaus(dataUser,request);
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
     * 查看个人信息
     * @throws Exception
     */
    @RequestMapping("meinfo")
    public synchronized void meinfo(HttpSession session,HttpServletResponse response) throws Exception {
        DataAccount dataAccount = (DataAccount) session.getAttribute("acco");
        JsonConfig config = new JsonConfig();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(dataAccount, config);
        jsonObject.put("data", jsonArray);

        if(dataAccount.getAuthority() == 0){
            jsonObject.put("cana", "超级管理员");
        }else{
            DataCompany dataCompany = dataCompanyService.selectCoid(dataAccount.getDataUser().getCompany());
            jsonObject.put("cana", dataCompany.getConame());
        }
        Encapsulation.write(response, jsonObject);
    }

}
