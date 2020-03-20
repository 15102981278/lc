package com.yinhe.dama.web.controller;

import com.yinhe.dama.entity.DataAccount;
import com.yinhe.dama.entity.DataUser;
import com.yinhe.dama.service.DataAccountService;
import com.yinhe.dama.service.DataUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
 * @parameter 模块方法的页面跳转
 * @author lc
 * @date 2020-3-12 16:00:52
 * @version 1.0.1
 * @other 
 */
@Controller
@RequestMapping("/StyleJump")
public class StyleJump{

	@Resource
	DataUserService dataUserService;
	@Resource
	DataAccountService dataAccountService;

	//用户
	@RequestMapping("/UserJump")
	public String UserJump(HttpServletRequest request , Model model){
		String num = request.getParameter("num");
        String main = "/error";

        if("UserAdd".equals(num)) {
				main = "/datauser/UserAdd";
        }
		if("UserUpd".equals(num)) {
			String uid = request.getParameter("uid");
			DataUser dataUser = dataUserService.selectUsid(Long.valueOf(uid));
			model.addAttribute("us",dataUser);
			main = "/datauser/UserUpd";
		}
		return main;
	}
	//账号
	@RequestMapping("/AccJump")
	public String AccJump(HttpServletRequest request , Model model){
		String num = request.getParameter("num");
		String main = "/error";
		if("AccAdd".equals(num)) {
				DataUser du = new DataUser();
				List<DataUser> dataUsers = dataUserService.selectDaus(du);
			System.err.println(dataUsers.get(0).getName());
				model.addAttribute("dau",dataUsers);
				main = "/dataacc/accadd";
		}
		if("AccEdit".equals(num)) {
			String accid = request.getParameter("accid");
			DataAccount dataAccount = dataAccountService.selectAcid(Long.valueOf(accid));
			if(dataAccount.getModuleo_authority() != null){
				StringBuffer auth =  new StringBuffer(dataAccount.getModuleo_authority());
				if(dataAccount.getModulet_authority() != null){
					auth.append(","+dataAccount.getModulet_authority());
						if(dataAccount.getOperate_authority() != null){
							auth.append(","+dataAccount.getOperate_authority());
						}
				}
				model.addAttribute("ac",auth);
			}

			main = "/ztree/ztree";
		}


		return main;
	}
}
