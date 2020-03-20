package com.yinhe.dama.web.base;

import com.yinhe.dama.aop.Encapsulation;
import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.service.DataAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName LoginJumpController
 * @Description 用户登录跳转
 * @Author lc
 * @Date 2020/3/11 0011 15:05
 * @Version 1.0
 */
@Controller
@RequestMapping("/LoginJumpController")
public class LoginJumpController {

    @Resource
    DataAccountService dataAccountService;
    private static final String  [] now = {"02","03","04","05","021","022","031","041","042","051","052","053"};
    /**
     * 点击登录
     * @param scode 账号
     * @param pass  密码
     * @param session
     * @param response
     * @throws IOException
     */
    @PostMapping("/login")
    public  void login(String scode, String pass, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws IOException {

        DataAccount dataAccount = new DataAccount();
        if(!StringUtils.isEmpty(scode) && !StringUtils.isEmpty(pass)){
            dataAccount.setAccount(scode);
            dataAccount.setPassword(pass);
            dataAccount = dataAccountService.selectDataAccount(dataAccount);
            if(dataAccount != null){
                if(dataAccount.getAuthority() != 0 && dataAccount.getU_id() == null){
                    response.getWriter().print(2);
                }else{

                    String ip = Encapsulation.getIp(request);
                    String aaa  = Encapsulation.getTime();
                    dataAccount.setLogon_time(aaa);
                    dataAccount.setIp(ip);
                    session.setAttribute("acco",dataAccount);
                    dataAccountService.upAcc(dataAccount,request);
                    response.getWriter().print(1);
                }
            }else{
                response.getWriter().print(0);
            }
        }else{
            response.getWriter().print(0);
        }
    }

    /**
     * 权限跳转
     * @param
     * @return
     */
    @RequestMapping("/authority")
    public String authority(HttpSession session, Model model) {
        DataAccount accountSession = (DataAccount) session.getAttribute("acco");

        if(null != accountSession.getModuleo_authority() && !"".equals(accountSession.getModuleo_authority())){
            String sb = accountSession.getModuleo_authority();
            if(null != accountSession.getModulet_authority() && !"".equals(accountSession.getModulet_authority())){
                sb = sb + ","+accountSession.getModulet_authority();
            }
            String [] out = sb.split(",");
            StringBuffer jum = Encapsulation.getStr(now,out);
            model.addAttribute("jum",jum);
        }
        return "/start";
    }
}
