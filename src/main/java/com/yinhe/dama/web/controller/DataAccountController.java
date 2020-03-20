package com.yinhe.dama.web.controller;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.service.DataAccountService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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

@Resource DataAccountService dataAccountService;

    /**
     * 多条件分页查询
     * @param
     * @return
     */
    @RequestMapping("/selectDaac")
    public void selectDaac(@RequestParam(value = "limit",required = false)int limit,
                           @RequestParam(value = "page",required = false)int page,
                           DataAccount dataAccount, HttpServletResponse response) throws Exception{

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



}
