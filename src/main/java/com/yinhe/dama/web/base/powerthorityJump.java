package com.yinhe.dama.web.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName powerthorityJump
 * @Description 用户登录跳转
 * @Author lc
 * @Date 2020-3-12 09:43:58
 * @Version 1.0
 */
@Controller
@RequestMapping("/powerJump")
public class powerthorityJump{
	
	/**
     * 树菜单跳转
	 * @param
	 * @return
	 */
	@RequestMapping("/powerJump")
	public String authority(HttpServletRequest request){
		String num = request.getParameter("num");
        String main = "/error";
        
        switch(num) {
             case "lunb":
                main = "/lunbo/lunb";//公司风采
                break;
             case "xitongsm":
                main = "/lunbo/xitongsm";//系统说明
                break;
             case "account":
            	 main = "/dataacc/account";//账号信息
                break;
             case "operator":
                main = "/datauser/User";//用户信息
                break;
             case "record":
                main = "/record/record";//日志管理
                break;
             case "company":
                main = "/datacompany/company";//公司管理
                break;
             case "balanceMe":
            	  main = "/lc/balancesel/balanceMe";//个人基本资料
                 break;
             case "updatePass":
                main = "/lc/perInformation/updatePass";//修改密码
                 break;


        }
        return main;
	}
}
