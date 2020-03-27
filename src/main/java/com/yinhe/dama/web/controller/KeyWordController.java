package com.yinhe.dama.web.controller;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.KeyWord;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName KeyWordController
 * @Description 权限
 * @Author lc
 * @Date 2020/3/17 0017 14:19
 * @Version 1.0
 */
@Controller
@RequestMapping("/keyword")
public class KeyWordController {

    private static final KeyWord keyword = new KeyWord();

    @RequestMapping("/passThrough")
    public synchronized void passThrough(String juau,HttpServletResponse response, HttpServletRequest request) throws Exception {

        switch (juau){
            //用户
            case "user_add":
                if(Encapsulation.getIs(keyword.getUser_add(),request)){
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    JSONObject jsonObject = Encapsulation.getJsonObj(-1);
                    Encapsulation.write(response, jsonObject);
                }
                break;
            case "user_upd":
                if(Encapsulation.getIs(keyword.getUser_upd(),request)){
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    JSONObject jsonObject = Encapsulation.getJsonObj(-1);
                    Encapsulation.write(response, jsonObject);
                }
                break;
            //日志
            case"reco_exp":
                if(Encapsulation.getIs(keyword.getReco_exp(),request)){
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    JSONObject jsonObject = Encapsulation.getJsonObj(-1);
                    Encapsulation.write(response, jsonObject);
                }
                break;
            //账号
            case "acco_add":
            if(Encapsulation.getIs(keyword.getAcco_add(),request)){
                JSONObject jsonObject = Encapsulation.getJsonObj(1);
                Encapsulation.write(response, jsonObject);
            }else{
                JSONObject jsonObject = Encapsulation.getJsonObj(-1);
                Encapsulation.write(response, jsonObject);
            }
            break;
            case "acco_upd":

                if(Encapsulation.getIs(keyword.getAcco_upd(),request)){
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    JSONObject jsonObject = Encapsulation.getJsonObj(-1);
                    Encapsulation.write(response, jsonObject);
                }
                break;
            case "acco_res":
                if(Encapsulation.getIs(keyword.getAcco_res(),request)){
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    JSONObject jsonObject = Encapsulation.getJsonObj(-1);
                    Encapsulation.write(response, jsonObject);
                }
                break;
            case "acco_edi":
                if(Encapsulation.getIs(keyword.getAcco_edi(),request)){
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    JSONObject jsonObject = Encapsulation.getJsonObj(-1);
                    Encapsulation.write(response, jsonObject);
                }
                break;
            case "acco_exp":
                if(Encapsulation.getIs(keyword.getAcco_exp(),request)){
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    JSONObject jsonObject = Encapsulation.getJsonObj(-1);
                    Encapsulation.write(response, jsonObject);
                }
                break;
            //公司
            case "comp_add":
                if(Encapsulation.getIs(keyword.getComp_add(),request)){
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    JSONObject jsonObject = Encapsulation.getJsonObj(-1);
                    Encapsulation.write(response, jsonObject);
                }
                break;
            case "comp_upd":
                if(Encapsulation.getIs(keyword.getComp_upd(),request)){
                    JSONObject jsonObject = Encapsulation.getJsonObj(1);
                    Encapsulation.write(response, jsonObject);
                }else{
                    JSONObject jsonObject = Encapsulation.getJsonObj(-1);
                    Encapsulation.write(response, jsonObject);
                }
                break;
        }
     }
}

